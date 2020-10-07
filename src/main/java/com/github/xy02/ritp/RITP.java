package com.github.xy02.ritp;

import com.github.xy02.util.Rx;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import ritp.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RITP {
    private static GroupedInput getGroupedInput(Observable<byte[]> buffers) throws Throwable {
        Observable<Frame> frames = buffers.map(Frame::parseFrom).share();
        Function<Frame.TypeCase, Observable<Frame>> getFramesByType = Rx.getSubValues(frames, Frame::getTypeCase);
        Observable<Object> remoteClose = getFramesByType.apply(Frame.TypeCase.CLOSE)
                .take(1).flatMap(frame -> Observable.error(new Error("close"))).cache();
        Observable<Info> info = getFramesByType.apply(Frame.TypeCase.INFO)
                .map(Frame::getInfo).cache();
        Observable<Integer> pullsToGetMsg = getFramesByType.apply(Frame.TypeCase.PULL)
                .map(Frame::getPull);
        Observable<Msg> msgs = getFramesByType.apply(Frame.TypeCase.MSG)
                .map(Frame::getMsg);
        Observable<Object> errInfoMoreThanOnce = info.scan(0, (acc, v) -> acc + 1)
                .filter(n -> n > 1)
                .take(1)
                .flatMap(x -> Observable.error(new Error("cannot send Info more than once")));
        return new GroupedInput(info, msgs, pullsToGetMsg, remoteClose, errInfoMoreThanOnce);
    }

    private static Observable<Integer> getPullIncrements(Observable<Object> msgs, Observable<Integer> pulls, Exception overflowErr) {
        return Observable.create(emitter -> {
            Disposable sub = Observable.merge(msgs.map(x -> -1), pulls).scan(
                    new WindowState(0, 0, 0), (preState, num) -> {
                        int increment = preState.getIncrement();
                        int decrement = preState.getDecrement();
                        int windowSize = preState.getWindowSize();
                        if (num > 0) increment += num;
                        else decrement -= num;
                        if (decrement > windowSize) throw overflowErr;
                        if (decrement >= windowSize / 2) {
                            windowSize = windowSize - decrement + increment;
                            if (increment > 0) emitter.onNext(increment);
                            increment = 0;
                            decrement = 0;
                        }
                        return new WindowState(windowSize, increment, decrement);
                    }).subscribe(x -> {
            }, emitter::onError, emitter::onComplete);
            emitter.setDisposable(Disposable.fromAction(sub::dispose));
        });
    }

    private static OutputContext getOutputContext(Observable<Msg> msgs, Observable<Integer> pullsToGetMsg) {
        Queue<Msg> outputQueue = new ConcurrentLinkedQueue<>();
        Observable<Msg> msgsFromQueue = pullsToGetMsg
                .flatMap(pull -> Observable.fromIterable(outputQueue).take(pull))
                .share();
        Subject<Msg> msgSender = PublishSubject.create();
        Subject<Integer> sendNotifier = BehaviorSubject.createDefault(0);
        Observable<Integer> sendableMsgsAmounts = Observable.merge(sendNotifier, pullsToGetMsg, msgsFromQueue.map(x -> -1))
                .scan(0, Integer::sum).replay(1).refCount();
        Observable<Frame> msgFramesToSend = Observable.merge(
                msgsFromQueue,
                msgSender.withLatestFrom(sendableMsgsAmounts,
                        (msg, amount) -> amount > 0 ?
                                Observable.just(msg) :
                                Observable.<Msg>empty().doOnComplete(() -> outputQueue.add(msg))
                ).flatMap(x -> x).doOnNext(x -> sendNotifier.onNext(-1))
        ).map(msg -> Frame.newBuilder().setMsg(msg).build());
        Subject<Integer> msgPuller = PublishSubject.create();
        Observable<Integer> pullIncrements = getPullIncrements(msgs.cast(Object.class), msgPuller, new ProtocolException("input msg overflow"));
        Observable<Frame> pullFramesToSend = pullIncrements.map(pull -> Frame.newBuilder().setPull(pull).build());
        AtomicInteger sid = new AtomicInteger();
        Supplier<Integer> newStreamId = sid::getAndIncrement;
        return new OutputContext(msgSender, msgPuller, pullFramesToSend, msgFramesToSend, newStreamId);
    }

    private static InputContext getInputContext(Observable<Msg> msgs) throws Throwable {
        Function<Msg.TypeCase, Observable<Msg>> getMsgsByType = Rx.getSubValues(msgs, Msg::getTypeCase);
        Observable<Msg> closeMsgs = getMsgsByType.apply(Msg.TypeCase.CLOSE);
        Function<Integer, Observable<Msg>> getCloseMsgsByStreamId = Rx.getSubValues(closeMsgs, Msg::getStreamId);
        Observable<Msg> pullMsgs = getMsgsByType.apply(Msg.TypeCase.PULL);
        Function<Integer, Observable<Msg>> getPullMsgsByStreamId = Rx.getSubValues(pullMsgs, Msg::getStreamId);
        Observable<Msg> endMsgs = getMsgsByType.apply(Msg.TypeCase.END);
        Function<Integer, Observable<Msg>> getEndMsgsByStreamId = Rx.getSubValues(endMsgs, Msg::getStreamId);
        Observable<Msg> bufMsgs = getMsgsByType.apply(Msg.TypeCase.BUF);
        Function<Integer, Observable<Msg>> getBufMsgsByStreamId = Rx.getSubValues(bufMsgs, Msg::getStreamId);
        Observable<Msg> headerMsgs = getMsgsByType.apply(Msg.TypeCase.HEADER);
        Function<String, Observable<Msg>> getHeaderMsgsByFn = Rx.getSubValues(headerMsgs, msg -> msg.getHeader().getFn());
        return new InputContext(getHeaderMsgsByFn, getCloseMsgsByStreamId, getPullMsgsByStreamId, getEndMsgsByStreamId, getBufMsgsByStreamId);
    }

    private static Function<String, Observable<OnStream>> registerWith(
            Subject<Msg> msgSender,
            Function<String, Observable<Msg>> getHeaderMsgsByFn,
            Function<Integer, Observable<Msg>> getEndMsgsByStreamId,
            Function<Integer, Observable<Msg>> getBufMsgsByStreamId
    ) {
        return fn -> getHeaderMsgsByFn.apply(fn).map(msg -> {
            int streamId = msg.getStreamId();
            Header header = msg.getHeader();
            Observable<End> theEnd = getEndMsgsByStreamId.apply(streamId).take(1)
                    .flatMap(m -> m.getEnd().getReason() != End.Reason.COMPLETE ?
                            Observable.error(new Exception("cancel")) :
                            Observable.just(m.getEnd()));
            Subject<Integer> bufPuller = PublishSubject.create();
            Observable<byte[]> bufs = getBufMsgsByStreamId.apply(streamId)
                    .map(m -> m.getBuf().toByteArray())
                    .takeUntil(theEnd)
                    .takeUntil(bufPuller.ignoreElements().toObservable())
                    .share();
            Observable<Integer> pullIncrements = getPullIncrements(bufs.cast(Object.class), bufPuller, new ProtocolException("input buf overflow"));
            Observable<Msg> pullMsgsToSend = pullIncrements
                    .map(pull -> Msg.newBuilder().setPull(pull))
                    .concatWith(Observable.just(Msg.newBuilder().setClose(Close.newBuilder().setReason(Close.Reason.APPLICATION_ERROR).setMessage(""))))
                    .onErrorReturn(err -> Msg.newBuilder().setClose(Close.newBuilder().setReason(Close.Reason.APPLICATION_ERROR).setMessage(err.getMessage())))
                    .map(builder -> builder.setStreamId(streamId).build());
            pullMsgsToSend.subscribe(msgSender); //side effect
            return new OnStream(header, bufs, bufPuller);
        });
    }

    private static Function<Header, Stream> streamWith(Supplier<Integer> newStreamId, Subject<Msg> msgSender,
                                                       Function<Integer, Observable<Msg>> getCloseMsgsByStreamId,
                                                       Function<Integer, Observable<Msg>> getPullMsgsByStreamId) {
        return header -> {
            int streamId = newStreamId.get();
            Subject<byte[]> bufSender = PublishSubject.create();
            Subject<Boolean> sendable = BehaviorSubject.createDefault(false);
            Observable<Object> remoteClose = getCloseMsgsByStreamId.apply(streamId).take(1)
                    .flatMap(msg -> Observable.error(new Exception(msg.getClose().getMessage())));
            Observable<Msg> sendingMsgs = bufSender.takeUntil(remoteClose)
                    .withLatestFrom(sendable, (buf, ok) -> ok ?
                            Observable.just(Msg.newBuilder().setBuf(ByteString.copyFrom(buf))) :
                            Observable.<Msg.Builder>empty())
                    .flatMap(o -> o)
                    .startWithItem(Msg.newBuilder().setHeader(header))
                    .concatWith(Observable.just(Msg.newBuilder().setEnd(End.newBuilder().setReason(End.Reason.COMPLETE))))
                    .onErrorReturn(err -> Msg.newBuilder().setEnd(End.newBuilder().setReason(End.Reason.CANCEL).setMessage(err.getMessage())))
                    .map(builder -> builder.setStreamId(streamId).build())
                    .doOnEach(msgSender);
            Observable<Integer> pulls = getPullMsgsByStreamId.apply(streamId).map(Msg::getPull)
                    .takeUntil(remoteClose).share();
            Observable<Integer> sendableAmounts = Observable.merge(sendingMsgs.map(x -> -1), pulls)
                    .scan(0, Integer::sum).replay(1).refCount();
            Observable<Boolean> isSendable = sendableAmounts.map(amount -> amount > 0).distinctUntilChanged()
                    .doOnEach(sendable)
                    .replay(1).refCount();
            return new Stream(pulls, sendableAmounts, isSendable, bufSender);
        };
    }

    public static Function<Observable<Socket>, Observable<Connection>> initWith(Info myInfo) {
        return sockets -> sockets.flatMap(socket -> {
            GroupedInput gi = getGroupedInput(socket.getBuffers());
            OutputContext output = getOutputContext(gi.getMsgs(), gi.getPullsToGetMsg());
            InputContext input = getInputContext(gi.getMsgs());
            Observable<Object> errs = gi.getErrInfoMoreThanOnce();
            Observable<byte[]> sendingBytes = Observable.merge(output.getMsgFramesToSend(), output.getPullFramesToSend())
                    .startWithItem(Frame.newBuilder().setInfo(myInfo).build())
                    .takeUntil(errs)
                    .onErrorReturn(err -> err instanceof ProtocolException ?
                            Frame.newBuilder().setClose(Close.newBuilder()
                                    .setReason(Close.Reason.PROTOCOL_ERROR)
                                    .setMessage(err.getMessage())
                            ).build() :
                            Frame.newBuilder().setClose(Close.newBuilder()
                                    .setReason(Close.Reason.APPLICATION_ERROR)
                                    .setMessage(err.getMessage())
                            ).build())
                    .takeUntil(gi.getRemoteClose())
                    .map(AbstractMessageLite::toByteArray);
            return gi.getInfo().doOnSubscribe(disposable -> sendingBytes.subscribe(socket.getSender()))
                    .map(remoteInfo -> {
                        Function<String, Observable<OnStream>> register = registerWith(output.getMsgSender(),
                                input.getGetHeaderMsgsByFn(), input.getGetEndMsgsByStreamId(), input.getGetBufMsgsByStreamId());
                        Function<Header, Stream> stream = streamWith(output.getNewStreamId(), output.getMsgSender(),
                                input.getGetCloseMsgsByStreamId(), input.getGetPullMsgsByStreamId());
                        return new Connection(remoteInfo, gi.getMsgs(), output.getMsgPuller(), register, stream);
                    });
        });
    }
}

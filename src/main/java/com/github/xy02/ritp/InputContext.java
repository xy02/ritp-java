package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import ritp.Msg;

public class InputContext {
    private Function<String, Observable<Msg>> getHeaderMsgsByFn;
    private Function<Integer, Observable<Msg>> getCloseMsgsByStreamId;
    private Function<Integer, Observable<Msg>> getPullMsgsByStreamId;
    private Function<Integer, Observable<Msg>> getEndMsgsByStreamId;
    private Function<Integer, Observable<Msg>> getBufMsgsByStreamId;

    public InputContext(Function<String, Observable<Msg>> getHeaderMsgsByFn, Function<Integer, Observable<Msg>> getCloseMsgsByStreamId, Function<Integer, Observable<Msg>> getPullMsgsByStreamId, Function<Integer, Observable<Msg>> getEndMsgsByStreamId, Function<Integer, Observable<Msg>> getBufMsgsByStreamId) {
        this.getHeaderMsgsByFn = getHeaderMsgsByFn;
        this.getCloseMsgsByStreamId = getCloseMsgsByStreamId;
        this.getPullMsgsByStreamId = getPullMsgsByStreamId;
        this.getEndMsgsByStreamId = getEndMsgsByStreamId;
        this.getBufMsgsByStreamId = getBufMsgsByStreamId;
    }

    public Function<String, Observable<Msg>> getGetHeaderMsgsByFn() {
        return getHeaderMsgsByFn;
    }

    public Function<Integer, Observable<Msg>> getGetCloseMsgsByStreamId() {
        return getCloseMsgsByStreamId;
    }

    public Function<Integer, Observable<Msg>> getGetPullMsgsByStreamId() {
        return getPullMsgsByStreamId;
    }

    public Function<Integer, Observable<Msg>> getGetEndMsgsByStreamId() {
        return getEndMsgsByStreamId;
    }

    public Function<Integer, Observable<Msg>> getGetBufMsgsByStreamId() {
        return getBufMsgsByStreamId;
    }
}

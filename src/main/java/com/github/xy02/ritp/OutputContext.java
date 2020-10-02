package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.Subject;
import ritp.Frame;
import ritp.Msg;

public class OutputContext {
    private Subject<Msg> msgSender;
    private Subject<Integer> msgPuller;
    private Observable<Frame> pullFramesToSend;
    private Observable<Frame> msgFramesToSend;
    private Supplier<Integer> newStreamId;

    public OutputContext(Subject<Msg> msgSender, Subject<Integer> msgPuller, Observable<Frame> pullFramesToSend, Observable<Frame> msgFramesToSend, Supplier<Integer> newStreamId) {
        this.msgSender = msgSender;
        this.msgPuller = msgPuller;
        this.pullFramesToSend = pullFramesToSend;
        this.msgFramesToSend = msgFramesToSend;
        this.newStreamId = newStreamId;
    }

    public Subject<Msg> getMsgSender() {
        return msgSender;
    }

    public Subject<Integer> getMsgPuller() {
        return msgPuller;
    }

    public Observable<Frame> getPullFramesToSend() {
        return pullFramesToSend;
    }

    public Observable<Frame> getMsgFramesToSend() {
        return msgFramesToSend;
    }

    public Supplier<Integer> getNewStreamId() {
        return newStreamId;
    }
}

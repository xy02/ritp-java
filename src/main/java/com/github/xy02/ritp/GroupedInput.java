package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import ritp.Info;
import ritp.Msg;

public class GroupedInput {
    private Observable<Info> info;
    private Observable<Msg> msgs;
    private Observable<Integer> pullsToGetMsg;
    private Observable<Object> remoteClose;
    private Observable<Object> errInfoMoreThanOnce;

    public GroupedInput(Observable<Info> info, Observable<Msg> msgs, Observable<Integer> pullsToGetMsg, Observable<Object> remoteClose, Observable<Object> errInfoMoreThanOnce) {
        this.info = info;
        this.msgs = msgs;
        this.pullsToGetMsg = pullsToGetMsg;
        this.remoteClose = remoteClose;
        this.errInfoMoreThanOnce = errInfoMoreThanOnce;
    }

    public Observable<Info> getInfo() {
        return info;
    }

    public Observable<Msg> getMsgs() {
        return msgs;
    }

    public Observable<Integer> getPullsToGetMsg() {
        return pullsToGetMsg;
    }

    public Observable<Object> getRemoteClose() {
        return remoteClose;
    }

    public Observable<Object> getErrInfoMoreThanOnce() {
        return errInfoMoreThanOnce;
    }
}

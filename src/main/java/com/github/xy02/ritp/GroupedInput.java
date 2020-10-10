package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import ritp.Info;
import ritp.Msg;

public class GroupedInput {
    private Single<Info> info;
    private Observable<Msg> msgs;
    private Observable<Integer> pullsToGetMsg;
    private Observable<Object> remoteClose;

    public GroupedInput(Single<Info> info, Observable<Msg> msgs, Observable<Integer> pullsToGetMsg, Observable<Object> remoteClose) {
        this.info = info;
        this.msgs = msgs;
        this.pullsToGetMsg = pullsToGetMsg;
        this.remoteClose = remoteClose;
    }

    public Single<Info> getInfo() {
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
}

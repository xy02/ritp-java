package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.Subject;
import ritp.Header;
import ritp.Info;
import ritp.Msg;

public class Connection {
    private Info remoteInfo;
    private Observable<Msg> msgs;
    private Subject<Integer> msgPuller;
    private Function<String, Observable<OnStream>> register;
    private Function<Header, Stream> stream;

    public Connection(Info remoteInfo, Observable<Msg> msgs, Subject<Integer> msgPuller, Function<String, Observable<OnStream>> register, Function<Header, Stream> stream) {
        this.remoteInfo = remoteInfo;
        this.msgs = msgs;
        this.msgPuller = msgPuller;
        this.register = register;
        this.stream = stream;
    }

    public Info getRemoteInfo() {
        return remoteInfo;
    }

    public Observable<Msg> getMsgs() {
        return msgs;
    }

    public Subject<Integer> getMsgPuller() {
        return msgPuller;
    }

    public Function<String, Observable<OnStream>> getRegister() {
        return register;
    }

    public Function<Header, Stream> getStream() {
        return stream;
    }
}

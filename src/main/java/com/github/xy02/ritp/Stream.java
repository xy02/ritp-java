package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import ritp.Msg;

public class Stream {
    private Observable<Integer> pulls;
    private Observable<Integer> sendableAmounts;
    private Observable<Boolean> isSendable;
    private Observable<Msg> sendingMsgs;
    private Subject<byte[]> bufSender;

    public Stream(Observable<Integer> pulls, Observable<Integer> sendableAmounts, Observable<Boolean> isSendable, Observable<Msg> sendingMsgs, Subject<byte[]> bufSender) {
        this.pulls = pulls;
        this.sendableAmounts = sendableAmounts;
        this.isSendable = isSendable;
        this.sendingMsgs = sendingMsgs;
        this.bufSender = bufSender;
    }

    public Observable<Integer> getPulls() {
        return pulls;
    }

    public Observable<Integer> getSendableAmounts() {
        return sendableAmounts;
    }

    public Observable<Boolean> getIsSendable() {
        return isSendable;
    }

    public Observable<Msg> getSendingMsgs() {
        return sendingMsgs;
    }

    public Subject<byte[]> getBufSender() {
        return bufSender;
    }
}

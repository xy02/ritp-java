package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;

public class Stream {
    private Observable<Integer> pulls;
    private Observable<Integer> sendableAmounts;
    private Observable<Boolean> isSendable;
    private Subject<byte[]> bufSender;

    public Stream(Observable<Integer> pulls, Observable<Integer> sendableAmounts, Observable<Boolean> isSendable, Subject<byte[]> bufSender) {
        this.pulls = pulls;
        this.sendableAmounts = sendableAmounts;
        this.isSendable = isSendable;
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

    public Subject<byte[]> getBufSender() {
        return bufSender;
    }
}

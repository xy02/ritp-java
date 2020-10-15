package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;

public class Stream {
    private Observable<Integer> pulls;
    private Observable<Boolean> isSendable;

    public Stream(Observable<Integer> pulls, Observable<Boolean> isSendable) {
        this.pulls = pulls;
        this.isSendable = isSendable;
    }

    public Observable<Integer> getPulls() {
        return pulls;
    }

    public Observable<Boolean> getIsSendable() {
        return isSendable;
    }
}

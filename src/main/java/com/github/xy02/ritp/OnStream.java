package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import ritp.Header;

public class OnStream {
    private Header header;
    private Observable<byte[]> bufs;
    private Subject<Integer> bufPuller;

    public OnStream(Header header, Observable<byte[]> bufs, Subject<Integer> bufPuller) {
        this.header = header;
        this.bufs = bufs;
        this.bufPuller = bufPuller;
    }

    public Header getHeader() {
        return header;
    }

    public Observable<byte[]> getBufs() {
        return bufs;
    }

    public Subject<Integer> getBufPuller() {
        return bufPuller;
    }
}

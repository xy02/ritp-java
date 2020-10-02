package com.github.xy02.ritp;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;

public class Socket {
    private Observable<byte[]> buffers;
    private Subject<byte[]> sender;

    public Socket(Observable<byte[]> buffers, Subject<byte[]> sender) {
        this.buffers = buffers;
        this.sender = sender;
    }

    public Observable<byte[]> getBuffers() {
        return buffers;
    }

    public Subject<byte[]> getSender() {
        return sender;
    }
}


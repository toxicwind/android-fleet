package com.google.firebase.firestore.remote;

import c.a.za;
import com.google.firebase.firestore.remote.Stream.StreamCallback;

/* JADX INFO: loaded from: classes.dex */
public interface Stream<CallbackType extends StreamCallback> {

    public enum State {
        Initial,
        Starting,
        Open,
        Healthy,
        Error,
        Backoff
    }

    public interface StreamCallback {
        void onClose(za zaVar);

        void onOpen();
    }

    void inhibitBackoff();

    boolean isOpen();

    boolean isStarted();

    void start();

    void stop();
}

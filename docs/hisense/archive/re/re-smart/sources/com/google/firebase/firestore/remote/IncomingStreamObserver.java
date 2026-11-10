package com.google.firebase.firestore.remote;

import c.a.C0219ea;
import c.a.za;

/* JADX INFO: loaded from: classes.dex */
public interface IncomingStreamObserver<RespT> {
    void onClose(za zaVar);

    void onHeaders(C0219ea c0219ea);

    void onNext(RespT respt);

    void onOpen();
}

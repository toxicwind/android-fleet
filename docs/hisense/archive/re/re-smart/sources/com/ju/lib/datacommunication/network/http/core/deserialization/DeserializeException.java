package com.ju.lib.datacommunication.network.http.core.deserialization;

/* JADX INFO: loaded from: classes2.dex */
public class DeserializeException extends Exception {
    public static final long serialVersionUID = 9219149484003769334L;

    public DeserializeException() {
    }

    public DeserializeException(String str, Throwable th) {
        super(str, th);
    }

    public DeserializeException(String str) {
        super(str);
    }

    public DeserializeException(Throwable th) {
        super(th);
    }
}

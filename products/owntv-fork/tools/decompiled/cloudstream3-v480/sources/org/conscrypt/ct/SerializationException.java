package org.conscrypt.ct;

/* JADX INFO: loaded from: classes8.dex */
public class SerializationException extends Exception {
    private static final long serialVersionUID = -5317873136664833411L;

    public SerializationException() {
    }

    public SerializationException(String str) {
        super(str);
    }

    public SerializationException(String str, Throwable th) {
        super(str, th);
    }

    public SerializationException(Throwable th) {
        super(th);
    }
}

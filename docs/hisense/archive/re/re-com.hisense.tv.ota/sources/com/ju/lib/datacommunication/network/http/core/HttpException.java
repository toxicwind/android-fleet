package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes.dex */
public class HttpException extends Exception {
    private static final long serialVersionUID = -259187862179187092L;
    private final int mCode;
    private r mTrace;

    public HttpException() {
        this(-1);
    }

    public HttpException(int i) {
        this.mCode = i;
    }

    public HttpException(int i, String str) {
        super(str);
        this.mCode = i;
    }

    public HttpException(int i, String str, Throwable th) {
        super(str, th);
        this.mCode = i;
    }

    public int a() {
        return this.mCode;
    }

    public void b(r rVar) {
        this.mTrace = rVar;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String message = getMessage();
        String name = HttpException.class.getName();
        if (message == null) {
            return name + ": code = " + this.mCode;
        }
        return name + ": code = " + this.mCode + ", msg = " + message;
    }
}

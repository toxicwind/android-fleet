package com.ju.lib.datacommunication.network.http.core;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class HttpException extends Exception {
    public static final long serialVersionUID = -259187862179187092L;
    public final int mCode;
    public HiResponse.Trace mTrace;

    public HttpException() {
        this.mCode = -1;
    }

    public int getCode() {
        return this.mCode;
    }

    public HiResponse.Trace getTrace() {
        return this.mTrace;
    }

    public void setTrace(HiResponse.Trace trace) {
        this.mTrace = trace;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String message = getMessage();
        String name = HttpException.class.getName();
        if (message == null) {
            StringBuilder sbB = a.b(name, ": code = ");
            sbB.append(this.mCode);
            return sbB.toString();
        }
        StringBuilder sbB2 = a.b(name, ": code = ");
        sbB2.append(this.mCode);
        sbB2.append(", msg = ");
        sbB2.append(message);
        return sbB2.toString();
    }

    public HttpException(int i) {
        this.mCode = i;
    }

    public HttpException(int i, String str, Throwable th) {
        super(str, th);
        this.mCode = i;
    }

    public HttpException(int i, String str) {
        super(str);
        this.mCode = i;
    }

    public HttpException(int i, Throwable th) {
        super(th);
        this.mCode = i;
    }
}

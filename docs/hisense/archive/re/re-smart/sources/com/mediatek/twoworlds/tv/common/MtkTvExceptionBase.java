package com.mediatek.twoworlds.tv.common;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvExceptionBase extends Exception {
    public static final long serialVersionUID = 1;
    public int errorCode;
    public String errorMessage;

    public MtkTvExceptionBase() {
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbA = a.a("Error Code=");
        sbA.append(this.errorCode);
        sbA.append(" \tError Message=");
        sbA.append(this.errorMessage);
        return sbA.toString();
    }

    public MtkTvExceptionBase(String str) {
        this.errorMessage = str;
    }

    public MtkTvExceptionBase(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }
}

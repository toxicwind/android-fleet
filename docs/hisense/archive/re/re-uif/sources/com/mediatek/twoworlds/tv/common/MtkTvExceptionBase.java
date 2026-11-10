package com.mediatek.twoworlds.tv.common;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvExceptionBase extends Exception {
    private static final long serialVersionUID = 1;
    private int errorCode;
    private String errorMessage;

    public MtkTvExceptionBase() {
    }

    public MtkTvExceptionBase(String str) {
        this.errorMessage = str;
    }

    public MtkTvExceptionBase(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "Error Code=" + this.errorCode + " \tError Message=" + this.errorMessage;
    }
}

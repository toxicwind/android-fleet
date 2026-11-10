package com.ju.lib.utils.base;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class JuPlatException extends Exception {
    public int code;
    public String message;

    public JuPlatException(int i, String str) {
        super(str);
        this.code = i;
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbA = a.a("JuPlatException{code=");
        sbA.append(this.code);
        sbA.append(", message='");
        sbA.append(this.message);
        sbA.append('\'');
        sbA.append('}');
        return sbA.toString();
    }
}

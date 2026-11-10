package com.hmct.cloud.sdk.bean.global;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ErrorInfo implements Serializable {
    private static final long serialVersionUID = -8298461589532954871L;
    private String errorName = "";
    private String errorCode = "";

    public String a() {
        return this.errorCode;
    }

    public String b() {
        return this.errorName;
    }

    public void c(String str) {
        this.errorCode = str;
    }

    public void d(String str) {
        this.errorName = str;
    }
}

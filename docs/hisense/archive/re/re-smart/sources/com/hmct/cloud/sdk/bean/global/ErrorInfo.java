package com.hmct.cloud.sdk.bean.global;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorInfo implements Serializable {
    public static final long serialVersionUID = -8298461589532954871L;
    public String errorName = "";
    public String errorCode = "";

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorName() {
        return this.errorName;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorName(String str) {
        this.errorName = str;
    }
}

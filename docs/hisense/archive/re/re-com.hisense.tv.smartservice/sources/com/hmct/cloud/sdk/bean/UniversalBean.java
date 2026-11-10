package com.hmct.cloud.sdk.bean;

import com.ju.lib.datacommunication.network.http.core.HiResponse;

/* JADX INFO: loaded from: classes2.dex */
public class UniversalBean {
    public String errorMsg;
    public boolean isSuccess;
    public HiResponse response;
    public String result;

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public HiResponse getResponse() {
        return this.response;
    }

    public String getResult() {
        return this.result;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public void setResponse(HiResponse hiResponse) {
        this.response = hiResponse;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}

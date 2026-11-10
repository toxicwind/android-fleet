package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes2.dex */
public enum DebugCheckType {
    Param("traceParam.txt"),
    Url("traceUrl.txt"),
    RespCode("traceStatusCode.txt"),
    RespBody("traceResponse.txt");

    public String filePath;

    DebugCheckType(String str) {
        this.filePath = str;
    }
}

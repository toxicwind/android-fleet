package com.ju.lib.datacommunication.network.http.debug;

import b.a.a.a.a;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class DebugTrace {
    public String callBackCls;
    public HashMap<String, String> requestParams;
    public String respBody;
    public int respCode;
    public String stackTrace;
    public String url;

    public String toString() {
        StringBuilder sbA = a.a("url: ");
        sbA.append(this.url);
        sbA.append("\nstackTrace: ");
        sbA.append(this.stackTrace);
        sbA.append("\ncallBackCls: ");
        return a.a(sbA, this.callBackCls, "\n");
    }
}

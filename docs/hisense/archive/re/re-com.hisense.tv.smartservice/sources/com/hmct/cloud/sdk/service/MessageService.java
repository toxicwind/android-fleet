package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.n;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MessageService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1990a;

    public MessageService(IHttpApi iHttpApi) {
        this.f1990a = iHttpApi;
    }

    public static MessageService getService(IHttpApi iHttpApi) {
        return n.a(iHttpApi);
    }

    public abstract String getMsg(String str, boolean z, HashMap<String, String> map);

    public abstract String getMsgChannel(String str, boolean z, HashMap<String, String> map);

    public abstract String getMsgConfigs(String str, boolean z, HashMap<String, String> map);
}

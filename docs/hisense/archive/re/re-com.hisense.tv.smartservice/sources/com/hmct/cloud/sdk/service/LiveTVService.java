package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.l;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LiveTVService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1988a;

    public LiveTVService(IHttpApi iHttpApi) {
        this.f1988a = iHttpApi;
    }

    public static LiveTVService getService(IHttpApi iHttpApi) {
        return l.a(iHttpApi);
    }

    public abstract String getSystemConfig(String str, boolean z, HashMap<String, String> map);
}

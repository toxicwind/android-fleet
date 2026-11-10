package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.o;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NetCheckService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1991a;

    public NetCheckService(IHttpApi iHttpApi) {
        this.f1991a = iHttpApi;
    }

    public static NetCheckService getService(IHttpApi iHttpApi) {
        return o.a(iHttpApi);
    }

    public abstract String getAPPCleanStrategy(String str, boolean z, HashMap<String, String> map);

    public abstract String getDataInfo(String str, boolean z, HashMap<String, String> map);
}

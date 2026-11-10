package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.z;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WebAppStoreApiService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f2002a;

    public WebAppStoreApiService(IHttpApi iHttpApi) {
        this.f2002a = iHttpApi;
    }

    public static WebAppStoreApiService getService(IHttpApi iHttpApi) {
        return z.a(iHttpApi);
    }

    public abstract String getRelatedApps(String str, boolean z, HashMap<String, String> map);

    public abstract String getWebAppDetail(String str, boolean z, HashMap<String, String> map);
}

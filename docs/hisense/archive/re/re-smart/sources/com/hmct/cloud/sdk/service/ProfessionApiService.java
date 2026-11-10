package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.p;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ProfessionApiService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1992a;

    public ProfessionApiService(IHttpApi iHttpApi) {
        this.f1992a = iHttpApi;
    }

    public static ProfessionApiService getService(IHttpApi iHttpApi) {
        return p.a(iHttpApi);
    }

    public abstract String bindInfo(String str, boolean z, HashMap<String, String> map);

    public abstract String configUrl(String str, boolean z, HashMap<String, String> map);

    public abstract String deviceInfo(String str, boolean z, HashMap<String, String> map);
}

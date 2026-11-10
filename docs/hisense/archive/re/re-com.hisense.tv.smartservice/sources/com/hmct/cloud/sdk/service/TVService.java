package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.r;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TVService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1994a;

    public TVService(IHttpApi iHttpApi) {
        this.f1994a = iHttpApi;
    }

    public static TVService getService(IHttpApi iHttpApi) {
        return r.a(iHttpApi);
    }

    public abstract String pixelratio(String str, boolean z, HashMap<String, String> map);

    public abstract String qrurl(String str, boolean z, HashMap<String, String> map);

    public abstract String shipment(String str, boolean z, HashMap<String, String> map);

    public abstract String staticConfig(String str, boolean z, HashMap<String, String> map);
}

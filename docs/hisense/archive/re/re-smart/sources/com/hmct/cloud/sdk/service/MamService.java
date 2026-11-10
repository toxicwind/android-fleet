package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.m;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MamService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1989a;

    public MamService(IHttpApi iHttpApi) {
        this.f1989a = iHttpApi;
    }

    public static MamService getService(IHttpApi iHttpApi) {
        return m.a(iHttpApi);
    }

    public abstract String getContentDetailByFilterValue(String str, boolean z, HashMap<String, String> map);

    public abstract String getContentListByIds(String str, boolean z, String str2);
}

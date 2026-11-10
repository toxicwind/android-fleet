package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.j;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LauncherService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1986a;

    public LauncherService(IHttpApi iHttpApi) {
        this.f1986a = iHttpApi;
    }

    public static LauncherService getService(IHttpApi iHttpApi) {
        return j.a(iHttpApi);
    }

    public abstract String getSystemConfig(String str, boolean z, String str2, HashMap<String, String> map);
}

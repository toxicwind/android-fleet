package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.x;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VoiceService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f2000a;

    public VoiceService(IHttpApi iHttpApi) {
        this.f2000a = iHttpApi;
    }

    public static VoiceService getService(IHttpApi iHttpApi) {
        return x.a(iHttpApi);
    }

    public abstract String getCodeAuthUrl(String str, boolean z, HashMap<String, String> map);

    public abstract String getDevicePortalUrl(String str, boolean z, HashMap<String, String> map);
}

package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.k;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LiveApiService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1987a;

    public LiveApiService(IHttpApi iHttpApi) {
        this.f1987a = iHttpApi;
    }

    public static LiveApiService getService(IHttpApi iHttpApi) {
        return k.a(iHttpApi);
    }

    public abstract String getAllchannels_LIVETV(String str, boolean z, HashMap<String, String> map, String str2);

    public abstract String getCategoryList(String str, boolean z, HashMap<String, String> map);

    public abstract String getChannelList(String str, boolean z, HashMap<String, String> map);

    public abstract String getCurrentPlay(String str, boolean z, HashMap<String, String> map);

    public abstract String getCurrentPlay_LIVETV(String str, boolean z, HashMap<String, String> map, String str2);

    public abstract String getProgramList(String str, boolean z, HashMap<String, String> map);
}

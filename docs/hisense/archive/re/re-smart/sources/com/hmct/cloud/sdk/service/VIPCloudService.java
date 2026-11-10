package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.v;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VIPCloudService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1998a;

    public VIPCloudService(IHttpApi iHttpApi) {
        this.f1998a = iHttpApi;
    }

    public static VIPCloudService getService(IHttpApi iHttpApi) {
        return v.a(iHttpApi);
    }

    public abstract String favoriteDelete(String str, boolean z, String str2);

    public abstract String favoriteDownload(String str, boolean z, HashMap<String, String> map);

    public abstract String favoriteQuery(String str, boolean z, HashMap<String, String> map);

    public abstract String favoriteUniversalDownload(String str, boolean z, HashMap<String, String> map);

    public abstract String favoriteUpload(String str, boolean z, String str2);

    public abstract String playHistoryDownload(String str, boolean z, HashMap<String, String> map);

    public abstract String playHistoryUpload(String str, boolean z, String str2);
}

package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.d;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ElecManualService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1979a;

    public ElecManualService(IHttpApi iHttpApi) {
        this.f1979a = iHttpApi;
    }

    public static ElecManualService getService(IHttpApi iHttpApi) {
        return d.a(iHttpApi);
    }

    public abstract String elecDelete(String str, boolean z, HashMap<String, String> map);

    public abstract String elecInfoUpload(String str, boolean z, HashMap<String, String> map);

    public abstract String elecQuery(String str, boolean z, HashMap<String, String> map);

    public abstract String fuzzySearch(String str, boolean z, HashMap<String, String> map);

    public abstract String getElecInfoDownloadDetail(String str, boolean z, HashMap<String, String> map);
}

package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.h;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HiVipEcifService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1984a;

    public HiVipEcifService(IHttpApi iHttpApi) {
        this.f1984a = iHttpApi;
    }

    public static HiVipEcifService getService(IHttpApi iHttpApi) {
        return h.a(iHttpApi);
    }

    public abstract String devList(String str, boolean z, HashMap<String, String> map);

    public abstract String devSave(String str, boolean z, HashMap<String, String> map);

    public abstract String devUpdate(String str, boolean z, HashMap<String, String> map);

    public abstract String relationDelete(String str, boolean z, HashMap<String, String> map);

    public abstract String relationList(String str, boolean z, HashMap<String, String> map);

    public abstract String relationSave(String str, boolean z, HashMap<String, String> map);

    public abstract String relationUpdate(String str, boolean z, HashMap<String, String> map);
}

package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.i;
import com.ju.lib.datacommunication.network.http.IHttpApi;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IotService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1985a;

    public IotService(IHttpApi iHttpApi) {
        this.f1985a = iHttpApi;
    }

    public static IotService getService(IHttpApi iHttpApi) {
        return i.a(iHttpApi);
    }

    public abstract String devicesDelete(String str, boolean z, String str2);

    public abstract String devicesDetail(String str, boolean z, String str2);

    public abstract String devicesDiscovery(String str, boolean z, String str2);

    public abstract String devicesExecute(String str, boolean z, String str2);

    public abstract String devicesList(String str, boolean z, String str2);

    public abstract String devicesQuery(String str, boolean z, String str2);

    public abstract String devicesUnlink(String str, boolean z, String str2);

    public abstract String devicesUpdateName(String str, boolean z, String str2);

    public abstract String partnersDetail(String str, boolean z, String str2);

    public abstract String partnersList(String str, boolean z, String str2);

    public abstract String roomsList(String str, boolean z, String str2);
}

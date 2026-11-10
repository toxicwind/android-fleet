package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.remotectl.LatestVersionInfoRely;
import com.hmct.cloud.sdk.service.a.c;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeviceRemoteCtlService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1978a;

    public DeviceRemoteCtlService(IHttpApi iHttpApi) {
        this.f1978a = iHttpApi;
    }

    public static DeviceRemoteCtlService getService(IHttpApi iHttpApi) {
        return c.a(iHttpApi);
    }

    public abstract LatestVersionInfoRely getLatestVersionInfo(String str, boolean z, HashMap<String, String> map);

    public abstract ReplyInfo uploadCmdResult(String str, boolean z, HashMap<String, String> map);
}

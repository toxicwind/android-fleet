package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.basp.AllocNewMacReply;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.service.a.e;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EsnService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1980a;

    public EsnService(IHttpApi iHttpApi) {
        this.f1980a = iHttpApi;
    }

    public static EsnService getService(IHttpApi iHttpApi) {
        return e.a(iHttpApi);
    }

    public abstract AllocNewMacReply allocNewMac(String str, boolean z, HashMap<String, String> map);

    public abstract ReplyInfo reportAllocNewMacEvent(String str, boolean z, HashMap<String, String> map);
}

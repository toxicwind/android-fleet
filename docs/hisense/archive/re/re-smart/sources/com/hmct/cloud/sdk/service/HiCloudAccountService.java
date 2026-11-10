package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.account.CustomerInfo;
import com.hmct.cloud.sdk.bean.account.DevSerialReply;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.service.a.g;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HiCloudAccountService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1982a;

    public HiCloudAccountService(IHttpApi iHttpApi) {
        this.f1982a = iHttpApi;
    }

    public static HiCloudAccountService getService(IHttpApi iHttpApi) {
        return g.a(iHttpApi);
    }

    public abstract ReplyInfo createSignflagByToken(String str, boolean z, HashMap<String, String> map);

    public abstract String delThirdBindInfo(String str, boolean z, HashMap<String, String> map);

    public abstract CustomerInfo getCustomerInfo(String str, boolean z, HashMap<String, String> map);

    public abstract DevSerialReply getDevSerial(String str, boolean z, HashMap<String, String> map);

    public abstract String getSignInfoByCode(String str, boolean z, HashMap<String, String> map);

    public abstract String getThirdBindInfo(String str, boolean z, HashMap<String, String> map);

    public abstract SignonReplyInfo refreshToken2(String str, boolean z, HashMap<String, String> map);

    public abstract SignonReplyInfo signIn(String str, boolean z, HashMap<String, String> map);

    public abstract String thirdTokenLoginV2(String str, boolean z, HashMap<String, String> map);
}

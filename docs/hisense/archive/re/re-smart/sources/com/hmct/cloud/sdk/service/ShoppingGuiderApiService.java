package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.q;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ShoppingGuiderApiService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1993a;

    public ShoppingGuiderApiService(IHttpApi iHttpApi) {
        this.f1993a = iHttpApi;
    }

    public static ShoppingGuiderApiService getService(IHttpApi iHttpApi) {
        return q.a(iHttpApi);
    }

    public abstract String checkstatus(String str, boolean z, HashMap<String, String> map);

    public abstract String getAllGoodsList(String str, boolean z, HashMap<String, String> map);

    public abstract String getGoodsDetail(String str, boolean z, HashMap<String, String> map);

    public abstract String getGuideGoodsList(String str, boolean z, HashMap<String, String> map);

    public abstract String getLayoutGoodsList(String str, boolean z, HashMap<String, String> map);

    public abstract String getModuleInfos(String str, boolean z, HashMap<String, String> map);

    public abstract String getbrowserhistory(String str, boolean z, HashMap<String, String> map);

    public abstract String getlabletemplatelist(String str, boolean z, HashMap<String, String> map);

    public abstract String initBasicConfig(String str, boolean z, HashMap<String, String> map);

    public abstract String initbasicconfig(String str, boolean z, HashMap<String, String> map);

    public abstract String postBrowserHistory(String str, boolean z, HashMap<String, String> map);

    public abstract String testTest(String str, boolean z, HashMap<String, String> map);
}

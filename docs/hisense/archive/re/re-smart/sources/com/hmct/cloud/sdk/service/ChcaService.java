package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.service.a.b;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ChcaService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1977a;

    public ChcaService(IHttpApi iHttpApi) {
        this.f1977a = iHttpApi;
    }

    public static ChcaService getService(IHttpApi iHttpApi) {
        return b.a(iHttpApi);
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudService
    public final String a(String str, int i) {
        if (i != 0 || str == null || str.equals("")) {
            return str;
        }
        try {
            return b(str, i);
        } catch (NullPointerException e2) {
            SDKUtil.LogD(Constants.LOGTAG, "error in deCompressData in ChcaService.");
            e2.printStackTrace();
            return "";
        }
    }

    public abstract String getCategoryList(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getHotWordList(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getMatchedChannels(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getSearchList(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getServiceList(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getSpecifiedProgList(String str, boolean z, String str2, HashMap<String, String> map);

    public abstract String getStaticProgList(String str, boolean z, String str2, HashMap<String, String> map);
}

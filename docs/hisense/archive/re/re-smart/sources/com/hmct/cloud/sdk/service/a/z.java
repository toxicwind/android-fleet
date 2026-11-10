package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.WebAppStoreApiService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class z extends WebAppStoreApiService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile WebAppStoreApiService f2072b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2073c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2074d = "networkError";

    public z(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static WebAppStoreApiService a(IHttpApi iHttpApi) {
        if (f2072b == null) {
            synchronized (z.class) {
                if (f2072b == null) {
                    f2072b = new z(iHttpApi);
                }
            }
        }
        return f2072b;
    }

    @Override // com.hmct.cloud.sdk.service.WebAppStoreApiService
    public String getRelatedApps(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "webappstoreapi/application/getRelatedApps", map);
    }

    @Override // com.hmct.cloud.sdk.service.WebAppStoreApiService
    public String getWebAppDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "webappstoreapi/application/getWebAppDetail", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("WebAppStoreApiService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(((WebAppStoreApiService) this).f2002a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createAppStoreSignature(), f2073c, f2074d);
    }
}

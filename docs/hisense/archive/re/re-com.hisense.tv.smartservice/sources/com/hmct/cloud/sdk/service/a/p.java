package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.ProfessionApiService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class p extends ProfessionApiService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile ProfessionApiService f2044b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2045c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2046d = "networkError";

    public p(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static ProfessionApiService a(IHttpApi iHttpApi) {
        if (f2044b == null) {
            synchronized (p.class) {
                if (f2044b == null) {
                    f2044b = new p(iHttpApi);
                }
            }
        }
        return f2044b;
    }

    @Override // com.hmct.cloud.sdk.service.ProfessionApiService
    public String bindInfo(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "professionApi/bindInfo", map);
    }

    @Override // com.hmct.cloud.sdk.service.ProfessionApiService
    public String configUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "professionApi/configUrl", map);
    }

    @Override // com.hmct.cloud.sdk.service.ProfessionApiService
    public String deviceInfo(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "professionApi/deviceInfo", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("VIPCloudService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        String strEncyptMapMD5 = HiCloudKey.encyptMapMD5(map2);
        HashMap map3 = new HashMap();
        map3.put("X-Sign-For", strEncyptMapMD5);
        return HiCloudService.a(((ProfessionApiService) this).f1992a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2045c, f2046d, map3);
    }
}

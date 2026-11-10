package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.TVService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class r extends TVService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile TVService f2050b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2051c = "{\"error\":{\"code\":000000,\"message\":\"Request had invalid credentials.\"}}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2052d = "message";

    public r(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static TVService a(IHttpApi iHttpApi) {
        if (f2050b == null) {
            synchronized (r.class) {
                if (f2050b == null) {
                    f2050b = new r(iHttpApi);
                }
            }
        }
        return f2050b;
    }

    @Override // com.hmct.cloud.sdk.service.TVService
    public String pixelratio(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "1.0/policy/tv/pixelratio", map);
    }

    @Override // com.hmct.cloud.sdk.service.TVService
    public String qrurl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "1.0/policy/tv/qrurl", map);
    }

    @Override // com.hmct.cloud.sdk.service.TVService
    public String shipment(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "1.0/policy/tv/shipment", map);
    }

    @Override // com.hmct.cloud.sdk.service.TVService
    public String staticConfig(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "1.0/policy/tv/static/config", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("LiveApiService", "domainName is null");
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
        return HiCloudService.a(((TVService) this).f1994a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2051c, f2052d, map3);
    }
}

package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.HiVipEcifService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class h extends HiVipEcifService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile HiVipEcifService f2022b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2023c = "{\"code\":1,\"message\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2024d = "networkError";

    public h(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static HiVipEcifService a(IHttpApi iHttpApi) {
        if (f2022b == null) {
            synchronized (h.class) {
                if (f2022b == null) {
                    f2022b = new h(iHttpApi);
                }
            }
        }
        return f2022b;
    }

    private String b(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiVipEcifService", "domainName is null");
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
        return HiCloudService.b(((HiVipEcifService) this).f1984a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2023c, f2024d, map3);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String devList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ecif/dev/list", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String devSave(String str, boolean z, HashMap<String, String> map) {
        return b(str, z, "ecif/dev/save", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String devUpdate(String str, boolean z, HashMap<String, String> map) {
        return b(str, z, "ecif/dev/update", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String relationDelete(String str, boolean z, HashMap<String, String> map) {
        return b(str, z, "ecif/user/relation/delete", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String relationList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "ecif/user/relation/list", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String relationSave(String str, boolean z, HashMap<String, String> map) {
        return b(str, z, "ecif/user/relation/save", map);
    }

    @Override // com.hmct.cloud.sdk.service.HiVipEcifService
    public String relationUpdate(String str, boolean z, HashMap<String, String> map) {
        return b(str, z, "ecif/user/relation/update", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiVipEcifService", "domainName is null");
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
        return HiCloudService.a(((HiVipEcifService) this).f1984a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2023c, f2024d, map3);
    }
}

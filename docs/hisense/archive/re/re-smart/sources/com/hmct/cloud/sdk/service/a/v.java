package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.VIPCloudService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class v extends VIPCloudService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile VIPCloudService f2058b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2059c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2060d = "networkError";

    public v(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static VIPCloudService a(IHttpApi iHttpApi) {
        if (f2058b == null) {
            synchronized (v.class) {
                if (f2058b == null) {
                    f2058b = new v(iHttpApi);
                }
            }
        }
        return f2058b;
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String favoriteDelete(String str, boolean z, String str2) {
        return a(str, z, "vipcloud/favorite/delete", str2);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String favoriteDownload(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "vipcloud/internal/favorite_download", map);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String favoriteQuery(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "vipcloud/favorite/query", map);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String favoriteUniversalDownload(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "vipcloud/favorite/download", map);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String favoriteUpload(String str, boolean z, String str2) {
        return a(str, z, "vipcloud/favorite/upload", str2);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String playHistoryDownload(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "vipcloud/playhistory/download", map);
    }

    @Override // com.hmct.cloud.sdk.service.VIPCloudService
    public String playHistoryUpload(String str, boolean z, String str2) {
        return a(str, z, "vipcloud/playhistory/upload", str2);
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
        return HiCloudService.a(((VIPCloudService) this).f1998a, HiCloudService.a(str, str2, z), map2, JsonSignature.createJsonSignature(), f2059c, f2060d, map3);
    }

    private String a(String str, boolean z, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("VIPCloudService", "domainName is null");
            return null;
        }
        String strEncyptStringMD5 = HiCloudKey.encyptStringMD5(str3);
        HashMap map = new HashMap();
        map.put("X-Sign-For", strEncyptStringMD5);
        return HiCloudService.a(((VIPCloudService) this).f1998a, HiCloudService.a(str, str2, z), str3, JsonSignature.createJsonSignature(), f2059c, f2060d, map, "application/json");
    }
}

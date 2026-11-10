package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.MamService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class m extends MamService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile MamService f2036b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2037c = "{\"success\":false,\"message\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2038d = "networkError";

    public m(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static MamService a(IHttpApi iHttpApi) {
        if (f2036b == null) {
            synchronized (m.class) {
                if (f2036b == null) {
                    f2036b = new m(iHttpApi);
                }
            }
        }
        return f2036b;
    }

    @Override // com.hmct.cloud.sdk.service.MamService
    public String getContentDetailByFilterValue(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("MamService", "domainName is null");
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
        return HiCloudService.a(((MamService) this).f1989a, HiCloudService.a(str, "mam/online/getContentDetailByFilterValue", z), map2, JsonSignature.createJsonSignature(), f2037c, f2038d, map3);
    }

    @Override // com.hmct.cloud.sdk.service.MamService
    public String getContentListByIds(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("MamService", "domainName is null");
            return null;
        }
        String strEncyptStringMD5 = HiCloudKey.encyptStringMD5(str2);
        HashMap map = new HashMap();
        map.put("X-Sign-For", strEncyptStringMD5);
        return HiCloudService.a(((MamService) this).f1989a, HiCloudService.a(str, "mam/online/getContentListByIds", z), str2, JsonSignature.createJsonSignature(), f2037c, f2038d, map, "application/json");
    }
}

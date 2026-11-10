package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.NetCheckService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class o extends NetCheckService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile NetCheckService f2041b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2042c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2043d = "networkError";

    public o(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static NetCheckService a(IHttpApi iHttpApi) {
        if (f2041b == null) {
            synchronized (o.class) {
                if (f2041b == null) {
                    f2041b = new o(iHttpApi);
                }
            }
        }
        return f2041b;
    }

    @Override // com.hmct.cloud.sdk.service.NetCheckService
    public String getAPPCleanStrategy(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "netcheck/whitelist", map);
    }

    @Override // com.hmct.cloud.sdk.service.NetCheckService
    public String getDataInfo(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "netcheck/datainfo", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("NetCheckService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.FORMAT, "1");
        map.put(Params.DEVICETYPE, "1");
        map2.put(Params.LANGUAGEID, SDKUtil.getLocaleLanguage());
        map2.put(Params.INVOKESOURCE, "1");
        map.put(Params.RANDSTR, UUID.randomUUID().toString());
        a(map2);
        return a(((NetCheckService) this).f1991a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2042c, f2043d);
    }
}

package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.LiveTVService;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class l extends LiveTVService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile LiveTVService f2033b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2034c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2035d = "networkError";

    public l(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static LiveTVService a(IHttpApi iHttpApi) {
        if (f2033b == null) {
            synchronized (l.class) {
                if (f2033b == null) {
                    f2033b = new l(iHttpApi);
                }
            }
        }
        return f2033b;
    }

    @Override // com.hmct.cloud.sdk.service.LiveTVService
    public String getSystemConfig(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("LiveTVService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        if (TextUtils.isEmpty(map2.get("version"))) {
            map2.put("version", "7.5");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.FORMAT, "0");
        map2.put(Params.LANGUAGEID, SDKUtil.getLocaleLanguage());
        map2.put(Params.INVOKESOURCE, "1");
        map2.put(Params.RANDSTR, UUID.randomUUID().toString());
        a(map2);
        return a(((LiveTVService) this).f1988a, HiCloudService.a(str, "livetv/event/getSystemConfig", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2034c, f2035d);
    }
}

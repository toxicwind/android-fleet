package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.LiveApiService;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class k extends LiveApiService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile LiveApiService f2030b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2031c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2032d = "networkError";

    public k(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static LiveApiService a(IHttpApi iHttpApi) {
        if (f2030b == null) {
            synchronized (k.class) {
                if (f2030b == null) {
                    f2030b = new k(iHttpApi);
                }
            }
        }
        return f2030b;
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getAllchannels_LIVETV(String str, boolean z, HashMap<String, String> map, String str2) {
        if (map != null) {
            if (TextUtils.isEmpty(map.get("version"))) {
                map.put("version", "7.5");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis() / 1000);
            map.put("timeStamp", sb.toString());
            map.put(Params.DEVICETYPE, "1");
            map.put(Params.FORMAT, "1");
            map.put(Params.LANGUAGEID, SDKUtil.getLocaleLanguage());
            map.put(Params.INVOKESOURCE, "1");
            map.put(Params.RANDSTR, UUID.randomUUID().toString());
            map.put("appVersion", str2);
        }
        return a(str, z, "liveapi/allchannels", map);
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getCategoryList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "liveapi/wechat/categorylist", map);
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getChannelList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "liveapi/wechat/channellist", map);
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getCurrentPlay(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "liveapi/wechat/currentplay", map);
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getCurrentPlay_LIVETV(String str, boolean z, HashMap<String, String> map, String str2) {
        if (map != null) {
            if (TextUtils.isEmpty(map.get("version"))) {
                map.put("version", "7.5");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis() / 1000);
            map.put("timeStamp", sb.toString());
            map.put(Params.DEVICETYPE, "1");
            map.put(Params.FORMAT, "1");
            map.put(Params.LANGUAGEID, SDKUtil.getLocaleLanguage());
            map.put(Params.INVOKESOURCE, "1");
            map.put(Params.RANDSTR, UUID.randomUUID().toString());
            map.put("appVersion", str2);
        }
        return a(str, z, "liveapi/currentplay", map);
    }

    @Override // com.hmct.cloud.sdk.service.LiveApiService
    public String getProgramList(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "liveapi/wechat/programlist", map);
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
        return a(((LiveApiService) this).f1987a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2031c, f2032d);
    }
}

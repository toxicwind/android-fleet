package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.LauncherService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class j extends LauncherService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile LauncherService f2028b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2029c = "networkError";

    public j(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static LauncherService a(IHttpApi iHttpApi) {
        if (f2028b == null) {
            synchronized (j.class) {
                if (f2028b == null) {
                    f2028b = new j(iHttpApi);
                }
            }
        }
        return f2028b;
    }

    @Override // com.hmct.cloud.sdk.service.LauncherService
    public String getSystemConfig(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("LauncherService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(a(((LauncherService) this).f1986a, a(str, str2, "GetConfigFileAddr", z, map2), new HashMap(), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2029c), 0);
    }

    public static String a(String str, String str2, String str3, boolean z, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("https://");
        } else {
            sb.append("http://");
        }
        b.a.a.a.a.a(sb, str, "/", str2, "?");
        sb.append("action=");
        sb.append(str3);
        sb.append("&");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (!value.equals("")) {
                try {
                    value = URLEncoder.encode(map.get(key), "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            b.a.a.a.a.a(sb, key, "=", value, "&");
        }
        return sb.toString();
    }
}

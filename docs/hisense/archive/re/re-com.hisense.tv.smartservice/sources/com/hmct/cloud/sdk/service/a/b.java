package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.ChcaService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class b extends ChcaService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile ChcaService f2005b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2006c = "networkError";

    public b(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static ChcaService a(IHttpApi iHttpApi) {
        if (f2005b == null) {
            synchronized (b.class) {
                if (f2005b == null) {
                    f2005b = new b(iHttpApi);
                }
            }
        }
        return f2005b;
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getCategoryList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getHotWordList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getMatchedChannels(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getSearchList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getServiceList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getSpecifiedProgList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    @Override // com.hmct.cloud.sdk.service.ChcaService
    public String getStaticProgList(String str, boolean z, String str2, HashMap<String, String> map) {
        return a(str, z, "cgi-bin/chcaportal_index.fcgi", str2, map);
    }

    private String a(String str, boolean z, String str2, String str3, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("LiveApiService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(((ChcaService) this).f1977a, a(str, str3, z, map2), null, new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2006c);
    }

    public static String a(String str, String str2, boolean z, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("https://");
        } else {
            sb.append("http://");
        }
        b.a.a.a.a.a(sb, str, "/", "cgi-bin/chcaportal_index.fcgi?", "action=");
        sb.append(str2);
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

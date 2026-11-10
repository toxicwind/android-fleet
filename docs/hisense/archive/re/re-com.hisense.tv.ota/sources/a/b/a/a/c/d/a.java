package a.b.a.a.c.d;

import a.b.a.a.d.c;
import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.ju.lib.datacommunication.network.http.e;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class a extends a.b.a.a.c.a {
    private static String c = "D5B6D8584F94B432";
    private static String d = "205681D89D731A8F";
    private static volatile a.b.a.a.c.a e;

    private a(e eVar) {
        super(eVar);
    }

    public static a.b.a.a.c.a g(e eVar) {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a(eVar);
                }
            }
        }
        return e;
    }

    private static String h(String str) {
        if (str != null && str.length() > 6) {
            String strSubstring = str.substring(3, 6);
            if ("006".equalsIgnoreCase(strSubstring)) {
                return "6";
            }
            if ("007".equalsIgnoreCase(strSubstring)) {
                return "7";
            }
            if ("008".equalsIgnoreCase(strSubstring)) {
                return "8";
            }
            if ("003".equalsIgnoreCase(strSubstring)) {
                return "9";
            }
            if ("010".equalsIgnoreCase(strSubstring)) {
                return "10";
            }
            if ("00b".equalsIgnoreCase(strSubstring)) {
                return "7";
            }
            if ("f00".equalsIgnoreCase(strSubstring)) {
                return "11";
            }
        }
        return "99";
    }

    private static HashMap i(HashMap map) {
        map.put("version", "7.4");
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map.put("timeStamp", sb.toString());
        map.put("format", "0");
        map.put("languageId", "0");
        map.put("sourceType", "1");
        map.put("randStr", UUID.randomUUID().toString());
        return map;
    }

    @Override // a.b.a.a.c.a
    public SignonReplyInfo f(String str, boolean z, HashMap map) {
        String strC;
        if (TextUtils.isEmpty(str)) {
            com.ju.lib.datacommunication.network.http.i.b.c("HiCloudAccountService", "domainName is null");
            return null;
        }
        String str2 = (String) map.get("deviceId");
        String str3 = (String) map.get("loginName");
        String str4 = (String) map.get("password");
        String str5 = (String) map.get("appSecret");
        HashMap map2 = new HashMap();
        i(map2);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        String str6 = (String) map2.get("timeStamp");
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str6) || TextUtils.isEmpty(str4)) {
            strC = "";
        } else {
            byte[] bArrE = c.e(str4);
            byte[] bArrE2 = c.e(str3 + str6);
            c.f(bArrE, bArrE2);
            strC = c.c(bArrE2);
        }
        map2.put("signature", strC);
        map2.remove("password");
        map2.put("serviceType", h(str2));
        map2.put("appSecret", new a.b.a.a.d.a().a(str5, c, d));
        b(map2);
        try {
            return a.b.a.a.a.a.b(c(this.b, a.b.a.a.c.b.a(str, "aaa/sign_in", z), com.ju.lib.datacommunication.network.http.core.signature.a.d(map2, com.ju.lib.datacommunication.network.http.core.signature.a.g(), "sign"), new com.ju.lib.datacommunication.network.http.core.signature.c(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

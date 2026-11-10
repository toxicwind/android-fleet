package a.b.a.a.c.d;

import a.b.a.a.c.c;
import a.b.a.a.d.d;
import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.ju.lib.datacommunication.network.http.e;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b extends c {
    private static volatile c c = null;
    private static String d = "networkError";

    private b(e eVar) {
        super(eVar);
    }

    public static c h(e eVar) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(eVar);
                }
            }
        }
        return c;
    }

    private String i(String str, boolean z, String str2, HashMap map) {
        if (TextUtils.isEmpty(str)) {
            com.ju.lib.datacommunication.network.http.i.b.c("UpgradeService", "domainName is null");
            return null;
        }
        HashMap map2 = new HashMap();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", "7.4");
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put("format", "0");
        if (TextUtils.isEmpty((CharSequence) map2.get("languageId"))) {
            map2.put("languageId", "0");
        }
        map2.put("sourceType", "1");
        b(map2);
        com.ju.lib.datacommunication.network.http.i.b.b("UpgradeService", "postparams is " + map2);
        return c(this.b, a.b.a.a.c.b.a(str, str2, z), com.ju.lib.datacommunication.network.http.core.signature.a.d(map2, com.ju.lib.datacommunication.network.http.core.signature.a.g(), "sign"), new com.ju.lib.datacommunication.network.http.core.signature.c(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", d);
    }

    @Override // a.b.a.a.c.c
    public DevUpgradeReply e(String str, boolean z, HashMap map) {
        try {
            return d.a(i(str, z, "upgrade/check_version", map));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // a.b.a.a.c.c
    public ReplyInfo g(String str, boolean z, HashMap map) {
        try {
            return d.b(i(str, z, "upgrade/report_upgrade_event", map));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.ju.lib.datacommunication.network.http.dns;

import com.ju.lib.datacommunication.network.http.core.v;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f115a = new Object();
    private static Pattern b = Pattern.compile("GSLB\\d+", 66);
    private static String[] c;

    private static List b() {
        if (c == null) {
            String strE = e("log.tag.httpdns");
            if ("SIT".equals(strE)) {
                c = new String[]{"10.18.217.70", "10.18.217.71"};
            } else if ("UT".equals(strE)) {
                c = new String[]{"10.18.210.145", "10.18.210.156"};
            } else {
                c = new String[]{"118.178.149.42", "49.233.125.19"};
            }
            if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.GslbHostName", "Default gslb ips: ", Arrays.toString(c), ",httpdns flag:", strE);
            }
        }
        return Arrays.asList(c);
    }

    private static final String e(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.GslbHostName", e, "getProp");
            return "";
        }
    }

    public static final g f(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            try {
                return new e(file);
            } catch (Exception e) {
                com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.GslbHostName", e, "create FileImpl error! ");
            }
        }
        return new f();
    }

    abstract v c();

    public final List d() {
        List listG;
        ArrayList arrayList;
        synchronized (f115a) {
            v vVarC = c();
            if (vVarC == null) {
                listG = b();
            } else {
                listG = vVarC.g();
                if (listG.isEmpty()) {
                    listG = b();
                }
            }
            arrayList = new ArrayList(listG.size() + 1);
            arrayList.addAll(listG);
            arrayList.add("lbgs.hismarttv.com");
        }
        return arrayList;
    }

    public final void g(v vVar) {
        synchronized (f115a) {
            h(vVar);
        }
    }

    abstract void h(v vVar);
}

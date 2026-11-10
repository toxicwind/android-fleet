package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.s;
import com.ju.lib.datacommunication.network.http.core.u;
import com.ju.lib.datacommunication.network.http.core.v;
import com.ju.lib.datacommunication.network.http.core.y;
import com.ju.lib.datacommunication.network.http.core.z;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j implements y, o {
    public static final String n = "HTTP2." + j.class.getSimpleName();
    private static volatile long o = p.j(180.0d);
    private static long p = p.j(300.0d);
    private static final byte[] q = {105, 108, 111, 118, 101, 36, 38, 104, 97, 116, 101, 119, 111, 114, 107, 44, 53, 49, 56};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ju.lib.datacommunication.network.http.core.e f118a;
    private g b;
    private h c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private l i;
    private m j;
    private String k;
    private volatile long l = -p;
    private File m;

    public j(com.ju.lib.datacommunication.network.http.core.e eVar, File file, String str, String str2, String str3, String str4, String str5, l lVar, com.ju.lib.datacommunication.network.http.f fVar) throws Throwable {
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        if (eVar == null) {
            throw new NullPointerException("HiHttpClient is null! ");
        }
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("DeviceId is empty! ");
        }
        this.m = file;
        this.b = g.f(file);
        this.f118a = eVar;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.i = lVar;
        this.c = new h(fVar);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((str + new String(q, "UTF-8")).getBytes("UTF-8"));
            this.j = new m(messageDigest.digest());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
        p.h(this.m, this);
    }

    private List e(List list, HttpGslbException.GslbException gslbException) {
        if (list == null) {
            list = new ArrayList();
        }
        list.add(gslbException);
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Object... objArr) {
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            com.ju.lib.datacommunication.network.http.i.b.d(n, objArr);
        }
    }

    private void g(HttpGslbException httpGslbException) {
        l lVar = this.i;
        if (lVar != null) {
            lVar.b(httpGslbException);
        }
    }

    private void h(String str, String str2, HttpGslbException.Status status) {
        l lVar = this.i;
        if (lVar != null) {
            HttpGslbException httpGslbException = new HttpGslbException(str, new Exception(str2));
            httpGslbException.b(status);
            lVar.b(httpGslbException);
        }
    }

    private void i(String str, String str2, List list) {
        l lVar = this.i;
        if (lVar != null) {
            lVar.a(str, str2, list, new Object[0]);
        }
    }

    private i j(String... strArr) throws HttpGslbException {
        String str;
        HttpGslbException.GslbException gslbException;
        List listE;
        char c;
        char c2 = 2;
        char c3 = 0;
        f("Gslb requestGslb: ", Arrays.toString(strArr));
        HashMap map = new HashMap();
        map.put("device_id", this.d);
        map.put("appVersionCode", this.e);
        map.put("appVersionName", this.f);
        map.put("appPackageName", this.g);
        map.put("appVersion", this.h);
        map.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        StringBuilder sb = new StringBuilder();
        try {
            map.put("custom_id", !TextUtils.isEmpty(this.k) ? this.j.c(this.k) : this.j.c(""));
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) {
                    sb.append(";");
                }
                sb.append(strArr[i]);
            }
            map.put("host", this.j.c(sb.toString()));
            List listE2 = null;
            for (String str2 : this.b.d()) {
                try {
                    com.ju.lib.datacommunication.network.http.core.e eVar = this.f118a;
                    com.ju.lib.datacommunication.network.http.core.l lVar = new com.ju.lib.datacommunication.network.http.core.l();
                    lVar.u("http://" + str2 + "/d", map);
                    lVar.m("Host", "lbgs.hismarttv.com");
                    s sVarD = eVar.d(lVar.k());
                    if (sVarD.d()) {
                        String strB = this.j.b(sVarD.a().a());
                        Object[] objArr = new Object[6];
                        objArr[c3] = "Gslb host=";
                        objArr[1] = strArr[c3];
                        objArr[c2] = ", gslbHost=";
                        objArr[3] = str2;
                        objArr[4] = ", body = ";
                        objArr[5] = strB;
                        f(objArr);
                        return new i(p.g(this.m, strB, this), str2);
                    }
                    str = str2;
                    try {
                        listE = e(listE2, new HttpGslbException.GslbException(str2, sb.toString(), false, sVarD.b(), sVarD.c(), null));
                    } catch (HttpException e) {
                        e = e;
                    } catch (Error e2) {
                        e = e2;
                        com.ju.lib.datacommunication.network.http.i.b.f(n, e, "request: " + str);
                        gslbException = new HttpGslbException.GslbException(str, sb.toString(), false, -3, e.getMessage(), e);
                        listE = e(listE2, gslbException);
                    } catch (Exception e3) {
                        e = e3;
                        com.ju.lib.datacommunication.network.http.i.b.f(n, e, "request: " + str);
                        gslbException = new HttpGslbException.GslbException(str, sb.toString(), false, -2, e.getMessage(), e);
                        listE = e(listE2, gslbException);
                    } catch (Throwable th) {
                        th = th;
                        com.ju.lib.datacommunication.network.http.i.b.f(n, th, "request: " + str);
                        gslbException = new HttpGslbException.GslbException(str, sb.toString(), false, -4, th.getMessage(), th);
                        listE = e(listE2, gslbException);
                    }
                    listE2 = listE;
                    c = 0;
                    c3 = c;
                    c2 = 2;
                } catch (HttpException e4) {
                    e = e4;
                    str = str2;
                } catch (Error e5) {
                    e = e5;
                    str = str2;
                } catch (Exception e6) {
                    e = e6;
                    str = str2;
                } catch (Throwable th2) {
                    th = th2;
                    str = str2;
                }
                c = 0;
                com.ju.lib.datacommunication.network.http.i.b.f(n, e, "request: " + str);
                if (!this.c.a()) {
                    HttpGslbException httpGslbException = new HttpGslbException(sb.toString(), e);
                    httpGslbException.b(HttpGslbException.Status.NetworkNotConnected);
                    throw httpGslbException;
                }
                listE2 = e(listE2, new HttpGslbException.GslbException(str, sb.toString(), false, -1, e.getMessage(), e));
                c3 = c;
                c2 = 2;
            }
            HttpGslbException httpGslbException2 = new HttpGslbException(sb.toString(), listE2);
            httpGslbException2.b(HttpGslbException.Status.Other);
            throw httpGslbException2;
        } catch (Exception e7) {
            HttpGslbException httpGslbException3 = new HttpGslbException(sb.toString(), e7);
            httpGslbException3.b(HttpGslbException.Status.RequestParamError);
            throw httpGslbException3;
        }
    }

    private v k(String str) throws UnknownHostException {
        InetAddress[] allByName = InetAddress.getAllByName(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (InetAddress inetAddress : allByName) {
            String string = inetAddress.toString();
            linkedHashSet.add(string.substring(string.lastIndexOf("/") + 1));
        }
        u uVar = new u();
        uVar.j(str);
        uVar.f(linkedHashSet);
        return uVar.h();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.y
    public void a(String str, InetAddress inetAddress) {
        if (!com.ju.lib.datacommunication.network.http.i.b.e()) {
            c.d().b(str, inetAddress, 300L);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("connect failed: ");
        stringBuffer.append(str);
        stringBuffer.append(" , ");
        stringBuffer.append(inetAddress);
        com.ju.lib.datacommunication.network.http.i.b.d(n, stringBuffer.toString());
    }

    @Override // com.ju.lib.datacommunication.network.http.core.y
    public v b(String str) throws UnknownHostException {
        String str2;
        HttpGslbException.Status status;
        f("Gslb parse, ", str);
        if (TextUtils.isEmpty(str)) {
            throw new UnknownHostException("hostname == null");
        }
        if (p.a(str)) {
            f(" parse ip: ", str);
        } else {
            synchronized (str.intern()) {
                try {
                    if (c.d().f(str)) {
                        f("Unsupported: ", str);
                        return k(str);
                    }
                    v vVarC = c.d().c(str);
                    if (vVarC != null) {
                        return vVarC;
                    }
                    if (SystemClock.elapsedRealtime() - this.l < o) {
                        f("Gslb error time! ");
                        h(str, "gslb frozen", HttpGslbException.Status.Frozen);
                        return k(str);
                    }
                    try {
                        i iVarJ = j(str, "lbgs.hismarttv.com");
                        v vVar = null;
                        boolean z = false;
                        for (v vVar2 : iVarJ.f117a) {
                            if (vVar2 != null) {
                                if ("lbgs.hismarttv.com".equals(vVar2.e())) {
                                    if (!vVar2.g().contains("127.0.0.1")) {
                                        this.b.g(vVar2);
                                    }
                                } else if (vVar2.g().contains("127.0.0.1")) {
                                    c.d().a(vVar2.e());
                                    if (str.equals(vVar2.e())) {
                                        z = true;
                                    }
                                } else {
                                    c.d().g(vVar2);
                                    if (str.equals(vVar2.e())) {
                                        i(iVarJ.b, str, vVar2.f());
                                        vVar = vVar2;
                                    }
                                }
                            }
                        }
                        if (vVar != null) {
                            return vVar;
                        }
                        if (z) {
                            str2 = "host name not supported";
                            status = HttpGslbException.Status.ParsedListUnsupported;
                        } else {
                            str2 = "parsed list invalid";
                            status = HttpGslbException.Status.ParsedListInvalid;
                        }
                        h(str, str2, status);
                        com.ju.lib.datacommunication.network.http.i.b.d(n, "System Dns! ");
                    } catch (HttpGslbException e) {
                        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                            com.ju.lib.datacommunication.network.http.i.b.f(n, e, "requestGslb gslb error! ");
                        }
                        g(e);
                        if (e.a() != HttpGslbException.Status.NetworkNotConnected) {
                            this.l = SystemClock.elapsedRealtime();
                        }
                        return k(str);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return k(str);
    }

    @Override // com.ju.lib.datacommunication.network.http.dns.o
    public void c(n nVar) {
        z zVarD = this.f118a.e().d();
        long jC = nVar.c();
        if (zVarD.d() != jC || zVarD.k() != jC) {
            this.f118a = new com.ju.lib.datacommunication.network.http.core.e(k.c(jC, null));
        }
        o = nVar.b();
    }
}

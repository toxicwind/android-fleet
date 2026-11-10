package com.ju.lib.datacommunication.network.http;

import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.core.w;
import com.ju.lib.datacommunication.network.http.dns.l;
import com.ju.lib.datacommunication.network.http.g.j;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f77a;
    private long b;
    private long c;
    private File d;
    private boolean e;
    private String f;
    private File g;
    private w h;
    private boolean i;
    private String j;
    private File k;
    private boolean l;
    private j m;
    private String n;
    private String o;
    private String p;
    private String q;
    private List r;
    private List s;
    private l t;
    private f u;
    private b v;
    private com.ju.lib.datacommunication.network.http.error.a w;
    private SSLSocketFactory x;
    private X509TrustManager y;

    c() {
        this.f77a = 5000L;
        this.b = 5000L;
        this.c = 0L;
        this.e = false;
        this.f = "";
        this.i = true;
        this.j = "";
        this.l = true;
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = new ArrayList();
        this.s = new ArrayList();
        this.v = null;
        this.w = null;
    }

    c(c cVar) {
        this.f77a = 5000L;
        this.b = 5000L;
        this.c = 0L;
        this.e = false;
        this.f = "";
        this.i = true;
        this.j = "";
        this.l = true;
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = new ArrayList();
        this.s = new ArrayList();
        this.v = null;
        this.w = null;
        this.f77a = cVar.f77a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.e = cVar.e;
        this.f = cVar.f;
        this.g = cVar.g;
        this.h = cVar.h;
        this.i = cVar.i;
        this.j = cVar.j;
        this.k = cVar.k;
        this.p = cVar.p;
        this.n = cVar.n;
        this.o = cVar.o;
        this.q = cVar.q;
        this.r = cVar.r;
        this.s = cVar.s;
        this.t = cVar.t;
        this.u = cVar.u;
        this.l = cVar.l;
        j jVar = cVar.m;
        if (jVar != null) {
            this.m = jVar.a();
        }
        this.v = cVar.v;
        this.w = cVar.w;
        this.y = cVar.y;
        this.x = cVar.x;
    }

    public c A(boolean z) {
        this.i = z;
        return this;
    }

    public c B(boolean z, String str, File file) {
        C(z, str, file, null, "", "", "", "");
        return this;
    }

    public c C(boolean z, String str, File file, l lVar, String str2, String str3, String str4, String str5) {
        if (!z) {
            this.f = "";
        } else {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Http dns need a real devicdId! ");
            }
            com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.CONFIG", "deviceId = " + str);
            this.f = str;
            if (file != null) {
                file.mkdirs();
            }
        }
        this.p = str4;
        this.n = str2;
        this.o = str3;
        this.q = str5;
        this.g = file;
        this.e = z;
        this.t = lVar;
        return this;
    }

    public c D(long j) {
        this.f77a = j;
        return this;
    }

    public c E(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        this.x = sSLSocketFactory;
        this.y = x509TrustManager;
        return this;
    }

    public w a() {
        return this.h;
    }

    public File b() {
        return this.d;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.b;
    }

    public List e() {
        return this.r;
    }

    public List f() {
        return this.s;
    }

    public String g() {
        return this.f;
    }

    public j h() {
        return this.m;
    }

    public l i() {
        return this.t;
    }

    public File j() {
        return this.g;
    }

    public f k() {
        return this.u;
    }

    public b l() {
        return this.v;
    }

    public long m() {
        return this.f77a;
    }

    public com.ju.lib.datacommunication.network.http.error.a n() {
        return this.w;
    }

    public SSLSocketFactory o() {
        return this.x;
    }

    public X509TrustManager p() {
        return this.y;
    }

    public String q() {
        return this.p;
    }

    public String r() {
        return this.j;
    }

    public String s() {
        return this.n;
    }

    public String t() {
        return this.o;
    }

    public File u() {
        return this.k;
    }

    public String v() {
        return this.q;
    }

    public boolean w() {
        return this.i;
    }

    public boolean x() {
        return this.e;
    }

    public boolean y() {
        return this.l;
    }

    public c z(long j) {
        this.b = j;
        return this;
    }
}

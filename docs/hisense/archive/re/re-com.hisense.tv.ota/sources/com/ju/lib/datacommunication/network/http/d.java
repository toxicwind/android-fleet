package com.ju.lib.datacommunication.network.http;

import com.ju.lib.datacommunication.network.http.core.y;
import com.ju.lib.datacommunication.network.http.core.z;
import com.ju.lib.datacommunication.network.http.dns.j;
import com.ju.lib.datacommunication.network.http.dns.k;
import com.ju.lib.datacommunication.network.http.dns.p;
import com.ju.lib.datacommunication.network.http.g.h;
import com.ju.lib.datacommunication.network.http.g.l;
import com.ju.lib.datacommunication.network.http.g.r;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile e f101a;
    private static c b;
    private static y c;
    private static l d;

    static {
        String str = "HTTP2." + d.class.getSimpleName();
        d = null;
    }

    private static com.ju.lib.datacommunication.network.http.impl.l a(c cVar, com.ju.lib.datacommunication.network.http.impl.l lVar) {
        return k.c(p.j(1.2d), lVar);
    }

    public static final e b() {
        if (f101a == null) {
            synchronized (d.class) {
                if (f101a == null) {
                    d(new c());
                    return f101a;
                }
            }
        }
        return f101a;
    }

    public static final c c() {
        synchronized (d.class) {
            if (b == null) {
                return new c();
            }
            return new c(b);
        }
    }

    private static void d(c cVar) {
        if (com.ju.lib.datacommunication.network.http.debug.f.k()) {
            com.ju.lib.datacommunication.network.http.debug.f.g().j(cVar.q());
        }
        z zVar = new z();
        zVar.r(cVar.d());
        zVar.z(cVar.m());
        zVar.w(cVar.w());
        zVar.x(true);
        zVar.p(cVar.a());
        com.ju.lib.datacommunication.network.http.core.e.l(cVar.l());
        if (cVar.c() > 0) {
            zVar.q(cVar.b(), cVar.c());
        }
        if (cVar.p() != null && cVar.o() != null) {
            zVar.A(cVar.o());
            zVar.B(cVar.p());
        }
        com.ju.lib.datacommunication.network.http.impl.l lVarA = null;
        if (cVar.x()) {
            lVarA = f101a == null ? a(cVar, null) : a(cVar, (com.ju.lib.datacommunication.network.http.impl.l) f101a.a().e());
            j jVar = new j(new com.ju.lib.datacommunication.network.http.core.e(lVarA), cVar.j(), cVar.g(), cVar.s(), cVar.t(), cVar.q(), cVar.v(), cVar.i(), cVar.k());
            c = jVar;
            zVar.u(jVar);
        }
        if (cVar.u() != null) {
            d = cVar.y() ? h.r(cVar.u().getPath(), cVar.r(), cVar.g()) : r.r(cVar.u().getPath(), cVar.r(), cVar.g(), cVar.h());
            zVar.v(d);
        }
        if (cVar.n() != null) {
            com.ju.lib.datacommunication.network.http.error.c.a().h(cVar.n());
            com.ju.lib.datacommunication.network.http.error.c.a().g(cVar.k());
        }
        List listE = cVar.e();
        if (listE != null && !listE.isEmpty()) {
            zVar.s(listE);
        }
        List listF = cVar.f();
        if (listF != null && !listF.isEmpty()) {
            zVar.t(listF);
        }
        zVar.y(cVar.k());
        com.ju.lib.datacommunication.network.http.core.e eVar = new com.ju.lib.datacommunication.network.http.core.e(new com.ju.lib.datacommunication.network.http.impl.l(zVar, lVarA));
        f101a = new a(eVar);
        eVar.f();
    }

    public static final e e(c cVar) {
        e eVar;
        if (cVar == null) {
            throw new NullPointerException("HttpConfig is null! ");
        }
        synchronized (d.class) {
            c cVar2 = new c(cVar);
            b = cVar2;
            d(cVar2);
            eVar = f101a;
        }
        return eVar;
    }
}

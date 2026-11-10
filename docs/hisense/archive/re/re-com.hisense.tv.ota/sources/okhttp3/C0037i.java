package okhttp3;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0037i {
    private static final String k = okhttp3.g0.k.k.l().m() + "-Sent-Millis";
    private static final String l = okhttp3.g0.k.k.l().m() + "-Received-Millis";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f218a;
    private final K b;
    private final String c;
    private final Protocol d;
    private final int e;
    private final String f;
    private final K g;
    private final I h;
    private final long i;
    private final long j;

    C0037i(c0 c0Var) {
        this.f218a = c0Var.W().k().toString();
        this.b = okhttp3.g0.h.g.n(c0Var);
        this.c = c0Var.W().g();
        this.d = c0Var.U();
        this.e = c0Var.p();
        this.f = c0Var.Q();
        this.g = c0Var.O();
        this.h = c0Var.B();
        this.i = c0Var.X();
        this.j = c0Var.V();
    }

    C0037i(okio.B b) {
        try {
            okio.j jVarD = okio.t.d(b);
            this.f218a = jVarD.E();
            this.c = jVarD.E();
            J j = new J();
            int iM = C0050j.M(jVarD);
            for (int i = 0; i < iM; i++) {
                j.b(jVarD.E());
            }
            this.b = j.d();
            okhttp3.g0.h.m mVarA = okhttp3.g0.h.m.a(jVarD.E());
            this.d = mVarA.f201a;
            this.e = mVarA.b;
            this.f = mVarA.c;
            J j2 = new J();
            int iM2 = C0050j.M(jVarD);
            for (int i2 = 0; i2 < iM2; i2++) {
                j2.b(jVarD.E());
            }
            String strE = j2.e(k);
            String strE2 = j2.e(l);
            j2.f(k);
            j2.f(l);
            this.i = strE != null ? Long.parseLong(strE) : 0L;
            this.j = strE2 != null ? Long.parseLong(strE2) : 0L;
            this.g = j2.d();
            if (a()) {
                String strE3 = jVarD.E();
                if (strE3.length() > 0) {
                    throw new IOException("expected \"\" but was \"" + strE3 + "\"");
                }
                this.h = I.c(!jVarD.n() ? TlsVersion.a(jVarD.E()) : TlsVersion.SSL_3_0, C0058s.a(jVarD.E()), c(jVarD), c(jVarD));
            } else {
                this.h = null;
            }
            b.close();
        } catch (Throwable th) {
            b.close();
            throw th;
        }
    }

    private boolean a() {
        return this.f218a.startsWith("https://");
    }

    private List c(okio.j jVar) throws IOException {
        int iM = C0050j.M(jVar);
        if (iM == -1) {
            return Collections.emptyList();
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ArrayList arrayList = new ArrayList(iM);
            for (int i = 0; i < iM; i++) {
                String strE = jVar.E();
                okio.h hVar = new okio.h();
                hVar.g0(ByteString.d(strE));
                arrayList.add(certificateFactory.generateCertificate(hVar.D()));
            }
            return arrayList;
        } catch (CertificateException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void e(okio.i iVar, List list) throws IOException {
        try {
            iVar.k(list.size()).q(10);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                iVar.f(ByteString.l(((Certificate) list.get(i)).getEncoded()).a()).q(10);
            }
        } catch (CertificateEncodingException e) {
            throw new IOException(e.getMessage());
        }
    }

    public boolean b(Y y, c0 c0Var) {
        return this.f218a.equals(y.k().toString()) && this.c.equals(y.g()) && okhttp3.g0.h.g.o(c0Var, this.b, y);
    }

    public c0 d(okhttp3.g0.g.k kVar) {
        String strC = this.g.c("Content-Type");
        String strC2 = this.g.c("Content-Length");
        X x = new X();
        x.o(this.f218a);
        x.h(this.c, null);
        x.g(this.b);
        Y yB = x.b();
        b0 b0Var = new b0();
        b0Var.p(yB);
        b0Var.n(this.d);
        b0Var.g(this.e);
        b0Var.k(this.f);
        b0Var.j(this.g);
        b0Var.b(new C0036h(kVar, strC, strC2));
        b0Var.h(this.h);
        b0Var.q(this.i);
        b0Var.o(this.j);
        return b0Var.c();
    }

    public void f(okhttp3.g0.g.i iVar) throws IOException {
        okio.i iVarC = okio.t.c(iVar.d(0));
        iVarC.f(this.f218a).q(10);
        iVarC.f(this.c).q(10);
        iVarC.k(this.b.g()).q(10);
        int iG = this.b.g();
        for (int i = 0; i < iG; i++) {
            iVarC.f(this.b.e(i)).f(": ").f(this.b.h(i)).q(10);
        }
        iVarC.f(new okhttp3.g0.h.m(this.d, this.e, this.f).toString()).q(10);
        iVarC.k(this.g.g() + 2).q(10);
        int iG2 = this.g.g();
        for (int i2 = 0; i2 < iG2; i2++) {
            iVarC.f(this.g.e(i2)).f(": ").f(this.g.h(i2)).q(10);
        }
        iVarC.f(k).f(": ").k(this.i).q(10);
        iVarC.f(l).f(": ").k(this.j).q(10);
        if (a()) {
            iVarC.q(10);
            iVarC.f(this.h.a().d()).q(10);
            e(iVarC, this.h.e());
            e(iVarC, this.h.d());
            iVarC.f(this.h.f().c()).q(10);
        }
        iVarC.close();
    }
}

package okhttp3.g0.g;

import java.io.IOException;
import okhttp3.J;
import okhttp3.K;
import okhttp3.N;
import okhttp3.O;
import okhttp3.Protocol;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okio.A;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class b implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n f187a;

    public b(n nVar) {
        this.f187a = nVar;
    }

    private c0 b(c cVar, c0 c0Var) {
        A aB;
        if (cVar == null || (aB = cVar.b()) == null) {
            return c0Var;
        }
        a aVar = new a(this, c0Var.a().P(), cVar, t.c(aB));
        String strM = c0Var.M("Content-Type");
        long jP = c0Var.a().p();
        b0 b0VarS = c0Var.S();
        b0VarS.b(new okhttp3.g0.h.j(strM, jP, t.d(aVar)));
        return b0VarS.c();
    }

    private static K c(K k, K k2) {
        J j = new J();
        int iG = k.g();
        for (int i = 0; i < iG; i++) {
            String strE = k.e(i);
            String strH = k.h(i);
            if ((!"Warning".equalsIgnoreCase(strE) || !strH.startsWith("1")) && (d(strE) || !e(strE) || k2.c(strE) == null)) {
                okhttp3.g0.a.f182a.b(j, strE, strH);
            }
        }
        int iG2 = k2.g();
        for (int i2 = 0; i2 < iG2; i2++) {
            String strE2 = k2.e(i2);
            if (!d(strE2) && e(strE2)) {
                okhttp3.g0.a.f182a.b(j, strE2, k2.h(i2));
            }
        }
        return j.d();
    }

    static boolean d(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }

    static boolean e(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    private static c0 f(c0 c0Var) {
        if (c0Var == null || c0Var.a() == null) {
            return c0Var;
        }
        b0 b0VarS = c0Var.S();
        b0VarS.b(null);
        return b0VarS.c();
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        n nVar = this.f187a;
        c0 c0VarA = nVar != null ? nVar.a(n.a()) : null;
        e eVarC = new d(System.currentTimeMillis(), n.a(), c0VarA).c();
        Y y = eVarC.f189a;
        c0 c0Var = eVarC.b;
        n nVar2 = this.f187a;
        if (nVar2 != null) {
            nVar2.e(eVarC);
        }
        if (c0VarA != null && c0Var == null) {
            okhttp3.g0.e.g(c0VarA.a());
        }
        if (y == null && c0Var == null) {
            b0 b0Var = new b0();
            b0Var.p(n.a());
            b0Var.n(Protocol.HTTP_1_1);
            b0Var.g(504);
            b0Var.k("Unsatisfiable Request (only-if-cached)");
            b0Var.b(okhttp3.g0.e.c);
            b0Var.q(-1L);
            b0Var.o(System.currentTimeMillis());
            return b0Var.c();
        }
        if (y == null) {
            b0 b0VarS = c0Var.S();
            b0VarS.d(f(c0Var));
            return b0VarS.c();
        }
        try {
            c0 c0VarF = n.f(y);
            if (c0VarF == null && c0VarA != null) {
                okhttp3.g0.e.g(c0VarA.a());
            }
            if (c0Var != null) {
                if (c0VarF.p() == 304) {
                    b0 b0VarS2 = c0Var.S();
                    b0VarS2.j(c(c0Var.O(), c0VarF.O()));
                    b0VarS2.q(c0VarF.X());
                    b0VarS2.o(c0VarF.V());
                    b0VarS2.d(f(c0Var));
                    b0VarS2.l(f(c0VarF));
                    c0 c0VarC = b0VarS2.c();
                    c0VarF.a().close();
                    this.f187a.b();
                    this.f187a.c(c0Var, c0VarC);
                    return c0VarC;
                }
                okhttp3.g0.e.g(c0Var.a());
            }
            b0 b0VarS3 = c0VarF.S();
            b0VarS3.d(f(c0Var));
            b0VarS3.l(f(c0VarF));
            c0 c0VarC2 = b0VarS3.c();
            if (this.f187a != null) {
                if (okhttp3.g0.h.g.c(c0VarC2) && e.a(c0VarC2, y)) {
                    return b(this.f187a.d(c0VarC2), c0VarC2);
                }
                if (okhttp3.g0.h.h.a(y.g())) {
                    try {
                        this.f187a.f(y);
                    } catch (IOException unused) {
                    }
                }
            }
            return c0VarC2;
        } catch (Throwable th) {
            if (c0VarA != null) {
                okhttp3.g0.e.g(c0VarA.a());
            }
            throw th;
        }
    }
}

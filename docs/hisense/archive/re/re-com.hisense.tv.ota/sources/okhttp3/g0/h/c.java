package okhttp3.g0.h;

import java.net.ProtocolException;
import okhttp3.N;
import okhttp3.O;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.e0;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class c implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f196a;

    public c(boolean z) {
        this.f196a = z;
    }

    @Override // okhttp3.O
    public c0 a(N n) throws ProtocolException {
        b0 b0VarS;
        e0 e0VarD;
        i iVar = (i) n;
        d dVarI = iVar.i();
        okhttp3.internal.connection.h hVarK = iVar.k();
        okhttp3.internal.connection.c cVar = (okhttp3.internal.connection.c) iVar.e();
        Y yA = iVar.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        iVar.h().o(iVar.g());
        dVarI.c(yA);
        iVar.h().n(iVar.g(), yA);
        b0 b0VarA = null;
        if (h.b(yA.g()) && yA.a() != null) {
            if ("100-continue".equalsIgnoreCase(yA.c("Expect"))) {
                dVarI.e();
                iVar.h().s(iVar.g());
                b0VarA = dVarI.a(true);
            }
            if (b0VarA == null) {
                iVar.h().m(iVar.g());
                b bVar = new b(dVarI.f(yA, yA.a().a()));
                okio.i iVarC = t.c(bVar);
                yA.a().e(iVarC);
                iVarC.close();
                iVar.h().l(iVar.g(), bVar.b);
            } else if (!cVar.p()) {
                hVarK.j();
            }
        }
        dVarI.b();
        if (b0VarA == null) {
            iVar.h().s(iVar.g());
            b0VarA = dVarI.a(false);
        }
        b0VarA.p(yA);
        b0VarA.h(hVarK.d().m());
        b0VarA.q(jCurrentTimeMillis);
        b0VarA.o(System.currentTimeMillis());
        c0 c0VarC = b0VarA.c();
        int iP = c0VarC.p();
        if (iP == 100) {
            b0 b0VarA2 = dVarI.a(false);
            b0VarA2.p(yA);
            b0VarA2.h(hVarK.d().m());
            b0VarA2.q(jCurrentTimeMillis);
            b0VarA2.o(System.currentTimeMillis());
            c0VarC = b0VarA2.c();
            iP = c0VarC.p();
        }
        iVar.h().r(iVar.g(), c0VarC);
        if (this.f196a && iP == 101) {
            b0VarS = c0VarC.S();
            e0VarD = okhttp3.g0.e.c;
        } else {
            b0VarS = c0VarC.S();
            e0VarD = dVarI.d(c0VarC);
        }
        b0VarS.b(e0VarD);
        c0 c0VarC2 = b0VarS.c();
        if ("close".equalsIgnoreCase(c0VarC2.W().c("Connection")) || "close".equalsIgnoreCase(c0VarC2.M("Connection"))) {
            hVarK.j();
        }
        if ((iP != 204 && iP != 205) || c0VarC2.a().p() <= 0) {
            return c0VarC2;
        }
        throw new ProtocolException("HTTP " + iP + " had non-zero Content-Length: " + c0VarC2.a().p());
    }
}

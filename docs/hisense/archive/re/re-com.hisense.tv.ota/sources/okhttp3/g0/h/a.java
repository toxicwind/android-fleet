package okhttp3.g0.h;

import java.util.List;
import okhttp3.A;
import okhttp3.C0064y;
import okhttp3.J;
import okhttp3.N;
import okhttp3.O;
import okhttp3.P;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.a0;
import okhttp3.b0;
import okhttp3.c0;
import okio.n;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class a implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final A f195a;

    public a(A a2) {
        this.f195a = a2;
    }

    private String b(List list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            C0064y c0064y = (C0064y) list.get(i);
            sb.append(c0064y.c());
            sb.append('=');
            sb.append(c0064y.k());
        }
        return sb.toString();
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        Y yA = n.a();
        X xH = yA.h();
        a0 a0VarA = yA.a();
        if (a0VarA != null) {
            P pB = a0VarA.b();
            if (pB != null) {
                xH.f("Content-Type", pB.toString());
            }
            long jA = a0VarA.a();
            if (jA != -1) {
                xH.f("Content-Length", Long.toString(jA));
                xH.l("Transfer-Encoding");
            } else {
                xH.f("Transfer-Encoding", "chunked");
                xH.l("Content-Length");
            }
        }
        boolean z = false;
        if (yA.c("Host") == null) {
            xH.f("Host", okhttp3.g0.e.s(yA.k(), false));
        }
        if (yA.c("Connection") == null) {
            xH.f("Connection", "Keep-Alive");
        }
        if (yA.c("Accept-Encoding") == null && yA.c("Range") == null) {
            z = true;
            xH.f("Accept-Encoding", "gzip");
        }
        List listA = this.f195a.a(yA.k());
        if (!listA.isEmpty()) {
            xH.f("Cookie", b(listA));
        }
        if (yA.c("User-Agent") == null) {
            xH.f("User-Agent", okhttp3.g0.f.a());
        }
        c0 c0VarF = n.f(xH.b());
        g.g(this.f195a, yA.k(), c0VarF.O());
        b0 b0VarS = c0VarF.S();
        b0VarS.p(yA);
        if (z && "gzip".equalsIgnoreCase(c0VarF.M("Content-Encoding")) && g.c(c0VarF)) {
            n nVar = new n(c0VarF.a().P());
            J jF = c0VarF.O().f();
            jF.f("Content-Encoding");
            jF.f("Content-Length");
            b0VarS.j(jF.d());
            b0VarS.b(new j(c0VarF.M("Content-Type"), -1L, t.d(nVar)));
        }
        return b0VarS.c();
    }
}

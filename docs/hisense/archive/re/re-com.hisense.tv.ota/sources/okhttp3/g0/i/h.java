package okhttp3.g0.i;

import java.io.EOFException;
import java.io.IOException;
import okhttp3.J;
import okhttp3.K;
import okhttp3.M;
import okhttp3.T;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.e0;
import okhttp3.g0.h.k;
import okhttp3.g0.h.m;
import okio.A;
import okio.B;
import okio.D;
import okio.i;
import okio.j;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class h implements okhttp3.g0.h.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final T f205a;
    final okhttp3.internal.connection.h b;
    final j c;
    final i d;
    int e = 0;
    private long f = 262144;

    public h(T t, okhttp3.internal.connection.h hVar, j jVar, i iVar) {
        this.f205a = t;
        this.b = hVar;
        this.c = jVar;
        this.d = iVar;
    }

    private String m() {
        String strL = this.c.l(this.f);
        this.f -= (long) strL.length();
        return strL;
    }

    @Override // okhttp3.g0.h.d
    public b0 a(boolean z) {
        int i = this.e;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.e);
        }
        try {
            m mVarA = m.a(m());
            b0 b0Var = new b0();
            b0Var.n(mVarA.f201a);
            b0Var.g(mVarA.b);
            b0Var.k(mVarA.c);
            b0Var.j(n());
            if (z && mVarA.b == 100) {
                return null;
            }
            if (mVarA.b == 100) {
                this.e = 3;
                return b0Var;
            }
            this.e = 4;
            return b0Var;
        } catch (EOFException e) {
            IOException iOException = new IOException("unexpected end of stream on " + this.b);
            iOException.initCause(e);
            throw iOException;
        }
    }

    @Override // okhttp3.g0.h.d
    public void b() {
        this.d.flush();
    }

    @Override // okhttp3.g0.h.d
    public void c(Y y) {
        o(y.e(), k.a(y, this.b.d().a().b().type()));
    }

    @Override // okhttp3.g0.h.d
    public void cancel() {
        okhttp3.internal.connection.c cVarD = this.b.d();
        if (cVarD != null) {
            cVarD.e();
        }
    }

    @Override // okhttp3.g0.h.d
    public e0 d(c0 c0Var) {
        okhttp3.internal.connection.h hVar = this.b;
        hVar.f.q(hVar.e);
        String strM = c0Var.M("Content-Type");
        if (!okhttp3.g0.h.g.c(c0Var)) {
            return new okhttp3.g0.h.j(strM, 0L, t.d(k(0L)));
        }
        if ("chunked".equalsIgnoreCase(c0Var.M("Transfer-Encoding"))) {
            return new okhttp3.g0.h.j(strM, -1L, t.d(i(c0Var.W().k())));
        }
        long jB = okhttp3.g0.h.g.b(c0Var);
        return jB != -1 ? new okhttp3.g0.h.j(strM, jB, t.d(k(jB))) : new okhttp3.g0.h.j(strM, -1L, t.d(l()));
    }

    @Override // okhttp3.g0.h.d
    public void e() {
        this.d.flush();
    }

    @Override // okhttp3.g0.h.d
    public A f(Y y, long j) {
        if ("chunked".equalsIgnoreCase(y.c("Transfer-Encoding"))) {
            return h();
        }
        if (j != -1) {
            return j(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    void g(okio.m mVar) {
        D dI = mVar.i();
        mVar.j(D.d);
        dI.a();
        dI.b();
    }

    public A h() {
        if (this.e == 1) {
            this.e = 2;
            return new c(this);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public B i(M m) {
        if (this.e == 4) {
            this.e = 5;
            return new d(this, m);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public A j(long j) {
        if (this.e == 1) {
            this.e = 2;
            return new e(this, j);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public B k(long j) {
        if (this.e == 4) {
            this.e = 5;
            return new f(this, j);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public B l() {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        okhttp3.internal.connection.h hVar = this.b;
        if (hVar == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.e = 5;
        hVar.j();
        return new g(this);
    }

    public K n() {
        J j = new J();
        while (true) {
            String strM = m();
            if (strM.length() == 0) {
                return j.d();
            }
            okhttp3.g0.a.f182a.a(j, strM);
        }
    }

    public void o(K k, String str) {
        if (this.e != 0) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.d.f(str).f("\r\n");
        int iG = k.g();
        for (int i = 0; i < iG; i++) {
            this.d.f(k.e(i)).f(": ").f(k.h(i)).f("\r\n");
        }
        this.d.f("\r\n");
        this.e = 1;
    }
}

package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;
import okhttp3.C0029a;
import okhttp3.C0061v;
import okhttp3.H;
import okhttp3.InterfaceC0053m;
import okhttp3.N;
import okhttp3.T;
import okhttp3.f0;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0029a f225a;
    private e b;
    private f0 c;
    private final C0061v d;
    public final InterfaceC0053m e;
    public final H f;
    private final Object g;
    private final f h;
    private int i;
    private c j;
    private boolean k;
    private boolean l;
    private boolean m;
    private okhttp3.g0.h.d n;

    public h(C0061v c0061v, C0029a c0029a, InterfaceC0053m interfaceC0053m, H h, Object obj) {
        this.d = c0061v;
        this.f225a = c0029a;
        this.e = interfaceC0053m;
        this.f = h;
        this.h = new f(c0029a, p(), interfaceC0053m, h);
        this.g = obj;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    private Socket e(boolean z, boolean z2, boolean z3) {
        Socket socketR;
        if (z3) {
            this.n = null;
        }
        if (z2) {
            this.l = true;
        }
        c cVar = this.j;
        if (cVar == null) {
            return null;
        }
        if (z) {
            cVar.k = true;
        }
        if (this.n != null) {
            return null;
        }
        if (!this.l && !this.j.k) {
            return null;
        }
        l(this.j);
        if (this.j.n.isEmpty()) {
            this.j.o = System.nanoTime();
            if (okhttp3.g0.a.f182a.e(this.d, this.j)) {
                socketR = this.j.r();
            } else {
                socketR = null;
            }
        } else {
            socketR = null;
        }
        this.j = null;
        return socketR;
    }

    private c f(int i, int i2, int i3, int i4, boolean z) throws Throwable {
        Socket socketN;
        Socket socketF;
        c cVar;
        c cVar2;
        f0 f0VarC;
        boolean z2;
        boolean z3;
        e eVar;
        synchronized (this.d) {
            if (this.l) {
                throw new IllegalStateException("released");
            }
            if (this.n != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.m) {
                throw new IOException("Canceled");
            }
            c cVar3 = this.j;
            socketN = n();
            socketF = null;
            if (this.j != null) {
                cVar2 = this.j;
                cVar = null;
            } else {
                cVar = cVar3;
                cVar2 = null;
            }
            if (!this.k) {
                cVar = null;
            }
            if (cVar2 == null) {
                okhttp3.g0.a.f182a.h(this.d, this.f225a, this, null);
                if (this.j != null) {
                    cVar2 = this.j;
                    f0VarC = null;
                    z2 = true;
                } else {
                    f0VarC = this.c;
                }
            } else {
                f0VarC = null;
            }
            z2 = false;
        }
        okhttp3.g0.e.h(socketN);
        if (cVar != null) {
            this.f.h(this.e, cVar);
        }
        if (z2) {
            this.f.g(this.e, cVar2);
        }
        if (cVar2 != null) {
            this.c = this.j.a();
            return cVar2;
        }
        if (f0VarC != null || ((eVar = this.b) != null && eVar.b())) {
            z3 = false;
        } else {
            this.b = this.h.e();
            z3 = true;
        }
        synchronized (this.d) {
            if (this.m) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List listA = this.b.a();
                int size = listA.size();
                for (int i5 = 0; i5 < size; i5++) {
                    f0 f0Var = (f0) listA.get(i5);
                    okhttp3.g0.a.f182a.h(this.d, this.f225a, this, f0Var);
                    if (this.j != null) {
                        cVar2 = this.j;
                        this.c = f0Var;
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z2) {
                if (f0VarC == null) {
                    f0VarC = this.b.c();
                }
                this.c = f0VarC;
                this.i = 0;
                cVar2 = new c(this.d, f0VarC);
                a(cVar2, false);
            }
        }
        if (z2) {
            this.f.g(this.e, cVar2);
            return cVar2;
        }
        cVar2.f(i, i2, i3, i4, z, this.e, this.f);
        p().a(cVar2.a());
        synchronized (this.d) {
            this.k = true;
            okhttp3.g0.a.f182a.i(this.d, cVar2);
            if (cVar2.p()) {
                socketF = okhttp3.g0.a.f182a.f(this.d, this.f225a, this);
                cVar2 = this.j;
            }
        }
        okhttp3.g0.e.h(socketF);
        this.f.g(this.e, cVar2);
        return cVar2;
    }

    private c g(int i, int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarF = f(i, i2, i3, i4, z);
            synchronized (this.d) {
                if (cVarF.l == 0 && !cVarF.p()) {
                    return cVarF;
                }
                if (cVarF.o(z2)) {
                    return cVarF;
                }
                j();
            }
        }
    }

    private void l(c cVar) {
        int size = cVar.n.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) cVar.n.get(i)).get() == this) {
                cVar.n.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    private Socket n() {
        c cVar = this.j;
        if (cVar == null || !cVar.k) {
            return null;
        }
        return e(false, false, true);
    }

    private d p() {
        return okhttp3.g0.a.f182a.j(this.d);
    }

    public void a(c cVar, boolean z) {
        if (this.j != null) {
            throw new IllegalStateException();
        }
        this.j = cVar;
        this.k = z;
        cVar.n.add(new g(this, this.g));
    }

    public void b() {
        okhttp3.g0.h.d dVar;
        c cVar;
        synchronized (this.d) {
            this.m = true;
            dVar = this.n;
            cVar = this.j;
        }
        if (dVar != null) {
            dVar.cancel();
        } else if (cVar != null) {
            cVar.e();
        }
    }

    public okhttp3.g0.h.d c() {
        okhttp3.g0.h.d dVar;
        synchronized (this.d) {
            dVar = this.n;
        }
        return dVar;
    }

    public synchronized c d() {
        return this.j;
    }

    public boolean h() {
        e eVar;
        return this.c != null || ((eVar = this.b) != null && eVar.b()) || this.h.c();
    }

    public okhttp3.g0.h.d i(T t, N n, boolean z) {
        try {
            okhttp3.g0.h.d dVarQ = g(n.d(), n.c(), n.b(), t.w(), t.D(), z).q(t, n, this);
            synchronized (this.d) {
                this.n = dVarQ;
            }
            return dVarQ;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    public void j() {
        c cVar;
        Socket socketE;
        synchronized (this.d) {
            cVar = this.j;
            socketE = e(true, false, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        okhttp3.g0.e.h(socketE);
        if (cVar != null) {
            this.f.h(this.e, cVar);
        }
    }

    public void k() {
        c cVar;
        Socket socketE;
        synchronized (this.d) {
            cVar = this.j;
            socketE = e(false, true, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        okhttp3.g0.e.h(socketE);
        if (cVar != null) {
            okhttp3.g0.a.f182a.k(this.e, null);
            this.f.h(this.e, cVar);
            this.f.a(this.e);
        }
    }

    public Socket m(c cVar) {
        if (this.n != null || this.j.n.size() != 1) {
            throw new IllegalStateException();
        }
        Reference reference = (Reference) this.j.n.get(0);
        Socket socketE = e(true, false, false);
        this.j = cVar;
        cVar.n.add(reference);
        return socketE;
    }

    public f0 o() {
        return this.c;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0047  */
    /* JADX WARN: Code duplicated, block: B:30:0x0052 A[Catch: all -> 0x0066, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000a, B:8:0x0012, B:13:0x001e, B:28:0x0048, B:30:0x0052, B:34:0x0058, B:11:0x001a, B:14:0x0021, B:16:0x0025, B:18:0x002d, B:20:0x0031, B:22:0x0037, B:25:0x003d), top: B:42:0x0003 }] */
    public void q(IOException iOException) {
        c cVar;
        boolean z;
        c cVar2;
        Socket socketE;
        synchronized (this.d) {
            cVar = null;
            if (iOException instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException) iOException).errorCode;
                if (errorCode == ErrorCode.REFUSED_STREAM) {
                    int i = this.i + 1;
                    this.i = i;
                    if (i > 1) {
                        this.c = null;
                        z = true;
                    } else {
                        z = false;
                    }
                    cVar2 = this.j;
                    socketE = e(z, false, true);
                    if (this.j == null && this.k) {
                        cVar = cVar2;
                    }
                } else {
                    if (errorCode != ErrorCode.CANCEL) {
                        this.c = null;
                        z = true;
                    } else {
                        z = false;
                    }
                    cVar2 = this.j;
                    socketE = e(z, false, true);
                    if (this.j == null) {
                        cVar = cVar2;
                    }
                }
            } else {
                if (this.j == null || (this.j.p() && !(iOException instanceof ConnectionShutdownException))) {
                    z = false;
                } else {
                    if (this.j.l == 0) {
                        if (this.c != null && iOException != null) {
                            this.h.a(this.c, iOException);
                        }
                        this.c = null;
                    }
                    z = true;
                }
                cVar2 = this.j;
                socketE = e(z, false, true);
                if (this.j == null) {
                    cVar = cVar2;
                }
            }
            throw th;
        }
        okhttp3.g0.e.h(socketE);
        if (cVar != null) {
            this.f.h(this.e, cVar);
        }
    }

    public void r(boolean z, okhttp3.g0.h.d dVar, long j, IOException iOException) {
        c cVar;
        Socket socketE;
        boolean z2;
        this.f.p(this.e, j);
        synchronized (this.d) {
            if (dVar != null) {
                if (dVar == this.n) {
                    if (!z) {
                        this.j.l++;
                    }
                    cVar = this.j;
                    socketE = e(z, false, true);
                    if (this.j != null) {
                        cVar = null;
                    }
                    z2 = this.l;
                }
            }
            throw new IllegalStateException("expected " + this.n + " but was " + dVar);
        }
        okhttp3.g0.e.h(socketE);
        if (cVar != null) {
            this.f.h(this.e, cVar);
        }
        if (iOException != null) {
            this.f.b(this.e, okhttp3.g0.a.f182a.k(this.e, iOException));
        } else if (z2) {
            okhttp3.g0.a.f182a.k(this.e, null);
            this.f.a(this.e);
        }
    }

    public String toString() {
        c cVarD = d();
        return cVarD != null ? cVarD.toString() : this.f225a.toString();
    }
}

package okhttp3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okio.C0069d;

/* JADX INFO: loaded from: classes.dex */
final class W implements InterfaceC0053m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final T f170a;
    final okhttp3.g0.h.l b;
    final C0069d c;
    private H d;
    final Y e;
    final boolean f;
    private boolean g;

    private W(T t, Y y, boolean z) {
        this.f170a = t;
        this.e = y;
        this.f = z;
        this.b = new okhttp3.g0.h.l(t, z);
        U u = new U(this);
        this.c = u;
        u.g(t.c(), TimeUnit.MILLISECONDS);
    }

    private void d() {
        this.b.k(okhttp3.g0.k.k.l().p("response.body().close()"));
    }

    static W h(T t, Y y, boolean z) {
        W w = new W(t, y, z);
        w.d = t.l().a(w);
        return w;
    }

    @Override // okhttp3.InterfaceC0053m
    public void B(InterfaceC0054n interfaceC0054n) {
        synchronized (this) {
            if (this.g) {
                throw new IllegalStateException("Already Executed");
            }
            this.g = true;
        }
        d();
        this.d.c(this);
        this.f170a.j().a(new V(this, interfaceC0054n));
    }

    @Override // okhttp3.InterfaceC0053m
    public Y a() {
        return this.e;
    }

    public void c() {
        this.b.b();
    }

    @Override // okhttp3.InterfaceC0053m
    public c0 e() {
        synchronized (this) {
            if (this.g) {
                throw new IllegalStateException("Already Executed");
            }
            this.g = true;
        }
        d();
        this.c.k();
        this.d.c(this);
        try {
            try {
                this.f170a.j().b(this);
                c0 c0VarG = g();
                if (c0VarG == null) {
                    throw new IOException("Canceled");
                }
                this.f170a.j().f(this);
                return c0VarG;
            } catch (IOException e) {
                IOException iOExceptionJ = j(e);
                this.d.b(this, iOExceptionJ);
                throw iOExceptionJ;
            }
        } catch (Throwable th) {
            this.f170a.j().f(this);
            throw th;
        }
        this.f170a.j().f(this);
        throw th;
    }

    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public W clone() {
        return h(this.f170a, this.e, this.f);
    }

    c0 g() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f170a.q());
        arrayList.add(this.b);
        arrayList.add(new okhttp3.g0.h.a(this.f170a.i()));
        arrayList.add(new okhttp3.g0.g.b(this.f170a.r()));
        arrayList.add(new okhttp3.internal.connection.a(this.f170a));
        if (!this.f) {
            arrayList.addAll(this.f170a.s());
        }
        arrayList.add(new okhttp3.g0.h.c(this.f));
        c0 c0VarF = new okhttp3.g0.h.i(arrayList, null, null, null, 0, this.e, this, this.d, this.f170a.f(), this.f170a.C(), this.f170a.G()).f(this.e);
        if (!this.b.e()) {
            return c0VarF;
        }
        okhttp3.g0.e.g(c0VarF);
        throw new IOException("Canceled");
    }

    String i() {
        return this.e.k().z();
    }

    IOException j(IOException iOException) {
        if (!this.c.n()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    String k() {
        StringBuilder sb = new StringBuilder();
        sb.append(p() ? "canceled " : "");
        sb.append(this.f ? "web socket" : "call");
        sb.append(" to ");
        sb.append(i());
        return sb.toString();
    }

    @Override // okhttp3.InterfaceC0053m
    public boolean p() {
        return this.b.e();
    }
}

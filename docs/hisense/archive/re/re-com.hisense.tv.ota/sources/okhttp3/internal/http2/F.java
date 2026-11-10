package okhttp3.internal.http2;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class F {
    long b;
    final int c;
    final y d;
    private InterfaceC0038a f;
    private boolean g;
    private final D h;
    final C i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f230a = 0;
    private final Deque e = new ArrayDeque();
    final E j = new E(this);
    final E k = new E(this);
    ErrorCode l = null;

    F(int i, y yVar, boolean z, boolean z2, okhttp3.K k) {
        if (yVar == null) {
            throw new NullPointerException("connection == null");
        }
        this.c = i;
        this.d = yVar;
        this.b = yVar.t.d();
        this.h = new D(this, yVar.s.d());
        C c = new C(this);
        this.i = c;
        this.h.e = z2;
        c.c = z;
        if (k != null) {
            this.e.add(k);
        }
        if (l() && k != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!l() && k == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    private boolean g(ErrorCode errorCode) {
        synchronized (this) {
            if (this.l != null) {
                return false;
            }
            if (this.h.e && this.i.c) {
                return false;
            }
            this.l = errorCode;
            notifyAll();
            this.d.g0(this.c);
            return true;
        }
    }

    void c(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void d() {
        boolean z;
        boolean zM;
        synchronized (this) {
            z = !this.h.e && this.h.d && (this.i.c || this.i.b);
            zM = m();
        }
        if (z) {
            f(ErrorCode.CANCEL);
        } else {
            if (zM) {
                return;
            }
            this.d.g0(this.c);
        }
    }

    void e() throws IOException {
        C c = this.i;
        if (c.b) {
            throw new IOException("stream closed");
        }
        if (c.c) {
            throw new IOException("stream finished");
        }
        if (this.l != null) {
            throw new StreamResetException(this.l);
        }
    }

    public void f(ErrorCode errorCode) {
        if (g(errorCode)) {
            this.d.o0(this.c, errorCode);
        }
    }

    public void h(ErrorCode errorCode) {
        if (g(errorCode)) {
            this.d.p0(this.c, errorCode);
        }
    }

    public int i() {
        return this.c;
    }

    public okio.A j() {
        synchronized (this) {
            if (!this.g && !l()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.i;
    }

    public okio.B k() {
        return this.h;
    }

    public boolean l() {
        return this.d.f244a == ((this.c & 1) == 1);
    }

    public synchronized boolean m() {
        if (this.l != null) {
            return false;
        }
        return ((this.h.e || this.h.d) && (this.i.c || this.i.b) && this.g) ? false : true;
    }

    public okio.D n() {
        return this.j;
    }

    void o(okio.j jVar, int i) {
        this.h.a(jVar, i);
    }

    void p() {
        boolean zM;
        synchronized (this) {
            this.h.e = true;
            zM = m();
            notifyAll();
        }
        if (zM) {
            return;
        }
        this.d.g0(this.c);
    }

    void q(List list) {
        boolean zM;
        synchronized (this) {
            this.g = true;
            this.e.add(okhttp3.g0.e.H(list));
            zM = m();
            notifyAll();
        }
        if (zM) {
            return;
        }
        this.d.g0(this.c);
    }

    synchronized void r(ErrorCode errorCode) {
        if (this.l == null) {
            this.l = errorCode;
            notifyAll();
        }
    }

    public synchronized okhttp3.K s() {
        this.j.k();
        while (this.e.isEmpty() && this.l == null) {
            try {
                t();
            } catch (Throwable th) {
                this.j.u();
                throw th;
            }
        }
        this.j.u();
        if (this.e.isEmpty()) {
            throw new StreamResetException(this.l);
        }
        return (okhttp3.K) this.e.removeFirst();
    }

    void t() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public okio.D u() {
        return this.k;
    }
}

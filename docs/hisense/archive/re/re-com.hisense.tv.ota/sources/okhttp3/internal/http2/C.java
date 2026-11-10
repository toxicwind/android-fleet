package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
final class C implements okio.A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.h f227a = new okio.h();
    boolean b;
    boolean c;
    final /* synthetic */ F d;

    C(F f) {
        this.d = f;
    }

    private void a(boolean z) {
        long jMin;
        synchronized (this.d) {
            this.d.k.k();
            while (this.d.b <= 0 && !this.c && !this.b && this.d.l == null) {
                try {
                    this.d.t();
                } catch (Throwable th) {
                    this.d.k.u();
                    throw th;
                }
            }
            this.d.k.u();
            this.d.e();
            jMin = Math.min(this.d.b, this.f227a.c0());
            this.d.b -= jMin;
        }
        this.d.k.k();
        try {
            this.d.d.m0(this.d.c, z && jMin == this.f227a.c0(), this.f227a, jMin);
        } finally {
            this.d.k.u();
        }
    }

    @Override // okio.A
    public okio.D b() {
        return this.d.k;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.d) {
            if (this.b) {
                return;
            }
            if (!this.d.i.c) {
                if (this.f227a.c0() > 0) {
                    while (this.f227a.c0() > 0) {
                        a(true);
                    }
                } else {
                    F f = this.d;
                    f.d.m0(f.c, true, null, 0L);
                }
            }
            synchronized (this.d) {
                this.b = true;
            }
            this.d.d.flush();
            this.d.d();
        }
    }

    @Override // okio.A, java.io.Flushable
    public void flush() {
        synchronized (this.d) {
            this.d.e();
        }
        while (this.f227a.c0() > 0) {
            a(false);
            this.d.d.flush();
        }
    }

    @Override // okio.A
    public void o(okio.h hVar, long j) {
        this.f227a.o(hVar, j);
        while (this.f227a.c0() >= 16384) {
            a(false);
        }
    }
}

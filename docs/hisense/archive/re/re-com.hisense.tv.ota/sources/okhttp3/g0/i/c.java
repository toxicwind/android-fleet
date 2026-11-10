package okhttp3.g0.i;

import okio.A;
import okio.D;
import okio.m;

/* JADX INFO: loaded from: classes.dex */
final class c implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m f203a;
    private boolean b;
    final /* synthetic */ h c;

    c(h hVar) {
        this.c = hVar;
        this.f203a = new m(this.c.d.b());
    }

    @Override // okio.A
    public D b() {
        return this.f203a;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.b) {
            return;
        }
        this.b = true;
        this.c.d.f("0\r\n\r\n");
        this.c.g(this.f203a);
        this.c.e = 3;
    }

    @Override // okio.A, java.io.Flushable
    public synchronized void flush() {
        if (this.b) {
            return;
        }
        this.c.d.flush();
    }

    @Override // okio.A
    public void o(okio.h hVar, long j) {
        if (this.b) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return;
        }
        this.c.d.G(j);
        this.c.d.f("\r\n");
        this.c.d.o(hVar, j);
        this.c.d.f("\r\n");
    }
}

package okhttp3.g0.i;

import java.net.ProtocolException;
import okio.A;
import okio.D;
import okio.m;

/* JADX INFO: loaded from: classes.dex */
final class e implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m f204a;
    private boolean b;
    private long c;
    final /* synthetic */ h d;

    e(h hVar, long j) {
        this.d = hVar;
        this.f204a = new m(this.d.d.b());
        this.c = j;
    }

    @Override // okio.A
    public D b() {
        return this.f204a;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ProtocolException {
        if (this.b) {
            return;
        }
        this.b = true;
        if (this.c > 0) {
            throw new ProtocolException("unexpected end of stream");
        }
        this.d.g(this.f204a);
        this.d.e = 3;
    }

    @Override // okio.A, java.io.Flushable
    public void flush() {
        if (this.b) {
            return;
        }
        this.d.d.flush();
    }

    @Override // okio.A
    public void o(okio.h hVar, long j) throws ProtocolException {
        if (this.b) {
            throw new IllegalStateException("closed");
        }
        okhttp3.g0.e.f(hVar.c0(), 0L, j);
        if (j <= this.c) {
            this.d.d.o(hVar, j);
            this.c -= j;
            return;
        }
        throw new ProtocolException("expected " + this.c + " bytes but received " + j);
    }
}

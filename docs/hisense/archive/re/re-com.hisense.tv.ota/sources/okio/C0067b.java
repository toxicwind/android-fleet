package okio;

import java.io.IOException;

/* JADX INFO: renamed from: okio.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0067b implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ B f264a;
    final /* synthetic */ C0069d b;

    C0067b(C0069d c0069d, B b) {
        this.b = c0069d;
        this.f264a = b;
    }

    @Override // okio.B
    public long K(h hVar, long j) {
        this.b.k();
        try {
            try {
                long jK = this.f264a.K(hVar, j);
                this.b.m(true);
                return jK;
            } catch (IOException e) {
                throw this.b.l(e);
            }
        } catch (Throwable th) {
            this.b.m(false);
            throw th;
        }
    }

    @Override // okio.B
    public D b() {
        return this.b;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            try {
                this.f264a.close();
                this.b.m(true);
            } catch (IOException e) {
                throw this.b.l(e);
            }
        } catch (Throwable th) {
            this.b.m(false);
            throw th;
        }
    }

    public String toString() {
        return "AsyncTimeout.source(" + this.f264a + ")";
    }
}

package okhttp3.internal.http2;

import java.io.IOException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
final class z implements okio.B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.j f245a;
    int b;
    byte c;
    int d;
    int e;
    short f;

    z(okio.j jVar) {
        this.f245a = jVar;
    }

    private void a() throws IOException {
        int i = this.d;
        int iP = B.P(this.f245a);
        this.e = iP;
        this.b = iP;
        byte bH = (byte) (this.f245a.H() & 255);
        this.c = (byte) (this.f245a.H() & 255);
        if (B.e.isLoggable(Level.FINE)) {
            B.e.fine(C0043f.b(true, this.d, this.b, bH, this.c));
        }
        int iL = this.f245a.L() & Integer.MAX_VALUE;
        this.d = iL;
        if (bH != 9) {
            C0043f.d("%s != TYPE_CONTINUATION", Byte.valueOf(bH));
            throw null;
        }
        if (iL == i) {
            return;
        }
        C0043f.d("TYPE_CONTINUATION streamId changed", new Object[0]);
        throw null;
    }

    @Override // okio.B
    public long K(okio.h hVar, long j) throws IOException {
        while (true) {
            int i = this.e;
            if (i != 0) {
                long jK = this.f245a.K(hVar, Math.min(j, i));
                if (jK == -1) {
                    return -1L;
                }
                this.e = (int) (((long) this.e) - jK);
                return jK;
            }
            this.f245a.m(this.f);
            this.f = (short) 0;
            if ((this.c & 4) != 0) {
                return -1L;
            }
            a();
        }
    }

    @Override // okio.B
    public okio.D b() {
        return this.f245a.b();
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}

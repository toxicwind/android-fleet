package okhttp3.internal.http2;

import java.io.IOException;

/* JADX INFO: renamed from: okhttp3.internal.http2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0044g extends okio.l {
    boolean b;
    long c;
    final /* synthetic */ C0045h d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0044g(C0045h c0045h, okio.B b) {
        super(b);
        this.d = c0045h;
        this.b = false;
        this.c = 0L;
    }

    private void e(IOException iOException) {
        if (this.b) {
            return;
        }
        this.b = true;
        C0045h c0045h = this.d;
        c0045h.b.r(false, c0045h, this.c, iOException);
    }

    @Override // okio.l, okio.B
    public long K(okio.h hVar, long j) throws IOException {
        try {
            long jK = a().K(hVar, j);
            if (jK > 0) {
                this.c += jK;
            }
            return jK;
        } catch (IOException e) {
            e(e);
            throw e;
        }
    }

    @Override // okio.l, okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        e(null);
    }
}

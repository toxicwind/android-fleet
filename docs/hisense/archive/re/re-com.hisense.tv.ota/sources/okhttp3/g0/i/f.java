package okhttp3.g0.i;

import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class f extends b {
    private long e;

    f(h hVar, long j) {
        super(hVar);
        this.e = j;
        if (j == 0) {
            a(true, null);
        }
    }

    @Override // okhttp3.g0.i.b, okio.B
    public long K(okio.h hVar, long j) throws ProtocolException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.b) {
            throw new IllegalStateException("closed");
        }
        long j2 = this.e;
        if (j2 == 0) {
            return -1L;
        }
        long jK = super.K(hVar, Math.min(j2, j));
        if (jK == -1) {
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a(false, protocolException);
            throw protocolException;
        }
        long j3 = this.e - jK;
        this.e = j3;
        if (j3 == 0) {
            a(true, null);
        }
        return jK;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.b) {
            return;
        }
        if (this.e != 0 && !okhttp3.g0.e.p(this, 100, TimeUnit.MILLISECONDS)) {
            a(false, null);
        }
        this.b = true;
    }
}

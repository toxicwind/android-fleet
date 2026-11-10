package okhttp3.g0.i;

/* JADX INFO: loaded from: classes.dex */
class g extends b {
    private boolean e;

    g(h hVar) {
        super(hVar);
    }

    @Override // okhttp3.g0.i.b, okio.B
    public long K(okio.h hVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.b) {
            throw new IllegalStateException("closed");
        }
        if (this.e) {
            return -1L;
        }
        long jK = super.K(hVar, j);
        if (jK != -1) {
            return jK;
        }
        this.e = true;
        a(true, null);
        return -1L;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.b) {
            return;
        }
        if (!this.e) {
            a(false, null);
        }
        this.b = true;
    }
}

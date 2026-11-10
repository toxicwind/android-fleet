package okhttp3;

/* JADX INFO: renamed from: okhttp3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0033e extends okio.k {
    final /* synthetic */ okhttp3.g0.g.i b;
    final /* synthetic */ C0034f c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0033e(C0034f c0034f, okio.A a2, C0050j c0050j, okhttp3.g0.g.i iVar) {
        super(a2);
        this.c = c0034f;
        this.b = iVar;
    }

    @Override // okio.k, okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.c.e) {
            if (this.c.d) {
                return;
            }
            this.c.d = true;
            this.c.e.c++;
            super.close();
            this.b.b();
        }
    }
}

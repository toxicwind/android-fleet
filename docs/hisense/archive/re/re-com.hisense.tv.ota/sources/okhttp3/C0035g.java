package okhttp3;

/* JADX INFO: renamed from: okhttp3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0035g extends okio.l {
    final /* synthetic */ okhttp3.g0.g.k b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0035g(C0036h c0036h, okio.B b, okhttp3.g0.g.k kVar) {
        super(b);
        this.b = kVar;
    }

    @Override // okio.l, okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.b.close();
        super.close();
    }
}

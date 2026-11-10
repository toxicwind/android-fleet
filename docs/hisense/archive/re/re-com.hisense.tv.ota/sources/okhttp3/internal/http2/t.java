package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
final class t extends okhttp3.g0.b {
    final boolean b;
    final int c;
    final int d;
    final /* synthetic */ y e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    t(y yVar, boolean z, int i, int i2) {
        super("OkHttp %s ping %08x%08x", yVar.d, Integer.valueOf(i), Integer.valueOf(i2));
        this.e = yVar;
        this.b = z;
        this.c = i;
        this.d = i2;
    }

    @Override // okhttp3.g0.b
    public void k() {
        this.e.n0(this.b, this.c, this.d);
    }
}

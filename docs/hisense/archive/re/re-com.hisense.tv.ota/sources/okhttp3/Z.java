package okhttp3;

/* JADX INFO: loaded from: classes.dex */
class Z extends a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ P f173a;
    final /* synthetic */ int b;
    final /* synthetic */ byte[] c;
    final /* synthetic */ int d;

    Z(P p, int i, byte[] bArr, int i2) {
        this.f173a = p;
        this.b = i;
        this.c = bArr;
        this.d = i2;
    }

    @Override // okhttp3.a0
    public long a() {
        return this.b;
    }

    @Override // okhttp3.a0
    public P b() {
        return this.f173a;
    }

    @Override // okhttp3.a0
    public void e(okio.i iVar) {
        iVar.j(this.c, this.d, this.b);
    }
}

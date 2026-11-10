package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
class v extends okhttp3.g0.b {
    final /* synthetic */ boolean b;
    final /* synthetic */ L c;
    final /* synthetic */ x d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    v(x xVar, String str, Object[] objArr, boolean z, L l) {
        super(str, objArr);
        this.d = xVar;
        this.b = z;
        this.c = l;
    }

    @Override // okhttp3.g0.b
    public void k() {
        this.d.l(this.b, this.c);
    }
}

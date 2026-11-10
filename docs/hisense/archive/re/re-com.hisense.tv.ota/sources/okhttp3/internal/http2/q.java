package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
final class q extends okhttp3.g0.b {
    final /* synthetic */ y b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    q(y yVar) {
        super("OkHttp %s ping", yVar.d);
        this.b = yVar;
    }

    @Override // okhttp3.g0.b
    public void k() {
        boolean z;
        synchronized (this.b) {
            if (this.b.l < this.b.k) {
                z = true;
            } else {
                y.M(this.b);
                z = false;
            }
        }
        y yVar = this.b;
        if (z) {
            yVar.U();
        } else {
            yVar.n0(false, 1, 0);
        }
    }
}

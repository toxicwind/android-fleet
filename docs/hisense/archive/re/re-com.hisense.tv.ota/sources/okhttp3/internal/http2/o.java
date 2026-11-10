package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
class o extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ ErrorCode c;
    final /* synthetic */ y d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    o(y yVar, String str, Object[] objArr, int i, ErrorCode errorCode) {
        super(str, objArr);
        this.d = yVar;
        this.b = i;
        this.c = errorCode;
    }

    @Override // okhttp3.g0.b
    public void k() {
        this.d.j.c(this.b, this.c);
        synchronized (this.d) {
            this.d.x.remove(Integer.valueOf(this.b));
        }
    }
}

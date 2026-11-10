package okhttp3.internal.http2;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class u extends okhttp3.g0.b {
    final /* synthetic */ F b;
    final /* synthetic */ x c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    u(x xVar, String str, Object[] objArr, F f) {
        super(str, objArr);
        this.c = xVar;
        this.b = f;
    }

    @Override // okhttp3.g0.b
    public void k() {
        try {
            this.c.c.b.d(this.b);
        } catch (IOException e) {
            okhttp3.g0.k.k.l().t(4, "Http2Connection.Listener failure for " + this.c.c.d, e);
            try {
                this.b.f(ErrorCode.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}

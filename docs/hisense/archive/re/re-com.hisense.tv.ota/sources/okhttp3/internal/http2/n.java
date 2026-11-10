package okhttp3.internal.http2;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class n extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ okio.h c;
    final /* synthetic */ int d;
    final /* synthetic */ boolean e;
    final /* synthetic */ y f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    n(y yVar, String str, Object[] objArr, int i, okio.h hVar, int i2, boolean z) {
        super(str, objArr);
        this.f = yVar;
        this.b = i;
        this.c = hVar;
        this.d = i2;
        this.e = z;
    }

    @Override // okhttp3.g0.b
    public void k() {
        try {
            boolean zD = this.f.j.d(this.b, this.c, this.d, this.e);
            if (zD) {
                this.f.v.S(this.b, ErrorCode.CANCEL);
            }
            if (zD || this.e) {
                synchronized (this.f) {
                    this.f.x.remove(Integer.valueOf(this.b));
                }
            }
        } catch (IOException unused) {
        }
    }
}

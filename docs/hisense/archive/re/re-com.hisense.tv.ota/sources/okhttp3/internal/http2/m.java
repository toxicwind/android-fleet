package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class m extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ List c;
    final /* synthetic */ boolean d;
    final /* synthetic */ y e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    m(y yVar, String str, Object[] objArr, int i, List list, boolean z) {
        super(str, objArr);
        this.e = yVar;
        this.b = i;
        this.c = list;
        this.d = z;
    }

    @Override // okhttp3.g0.b
    public void k() {
        boolean zB = this.e.j.b(this.b, this.c, this.d);
        if (zB) {
            try {
                this.e.v.S(this.b, ErrorCode.CANCEL);
            } catch (IOException unused) {
                return;
            }
        }
        if (zB || this.d) {
            synchronized (this.e) {
                try {
                    this.e.x.remove(Integer.valueOf(this.b));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}

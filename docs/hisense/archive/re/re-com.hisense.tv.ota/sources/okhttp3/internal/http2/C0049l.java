package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;

/* JADX INFO: renamed from: okhttp3.internal.http2.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0049l extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ List c;
    final /* synthetic */ y d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0049l(y yVar, String str, Object[] objArr, int i, List list) {
        super(str, objArr);
        this.d = yVar;
        this.b = i;
        this.c = list;
    }

    @Override // okhttp3.g0.b
    public void k() {
        if (this.d.j.a(this.b, this.c)) {
            try {
                this.d.v.S(this.b, ErrorCode.CANCEL);
                synchronized (this.d) {
                    try {
                        this.d.x.remove(Integer.valueOf(this.b));
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IOException unused) {
            }
        }
    }
}

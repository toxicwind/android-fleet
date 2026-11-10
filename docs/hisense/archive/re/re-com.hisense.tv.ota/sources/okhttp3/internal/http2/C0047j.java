package okhttp3.internal.http2;

import java.io.IOException;

/* JADX INFO: renamed from: okhttp3.internal.http2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0047j extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ long c;
    final /* synthetic */ y d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0047j(y yVar, String str, Object[] objArr, int i, long j) {
        super(str, objArr);
        this.d = yVar;
        this.b = i;
        this.c = j;
    }

    @Override // okhttp3.g0.b
    public void k() {
        try {
            this.d.v.V(this.b, this.c);
        } catch (IOException unused) {
            this.d.U();
        }
    }
}

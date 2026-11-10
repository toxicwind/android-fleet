package okhttp3.internal.http2;

import java.io.IOException;

/* JADX INFO: renamed from: okhttp3.internal.http2.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0046i extends okhttp3.g0.b {
    final /* synthetic */ int b;
    final /* synthetic */ ErrorCode c;
    final /* synthetic */ y d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0046i(y yVar, String str, Object[] objArr, int i, ErrorCode errorCode) {
        super(str, objArr);
        this.d = yVar;
        this.b = i;
        this.c = errorCode;
    }

    @Override // okhttp3.g0.b
    public void k() {
        try {
            this.d.o0(this.b, this.c);
        } catch (IOException unused) {
            this.d.U();
        }
    }
}

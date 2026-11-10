package okhttp3.g0.i;

import java.io.IOException;
import okio.B;
import okio.D;
import okio.m;

/* JADX INFO: loaded from: classes.dex */
abstract class b implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final m f202a;
    protected boolean b;
    protected long c;
    final /* synthetic */ h d;

    private b(h hVar) {
        this.d = hVar;
        this.f202a = new m(this.d.c.b());
        this.c = 0L;
    }

    @Override // okio.B
    public long K(okio.h hVar, long j) {
        try {
            long jK = this.d.c.K(hVar, j);
            if (jK > 0) {
                this.c += jK;
            }
            return jK;
        } catch (IOException e) {
            a(false, e);
            throw e;
        }
    }

    protected final void a(boolean z, IOException iOException) {
        h hVar = this.d;
        int i = hVar.e;
        if (i == 6) {
            return;
        }
        if (i != 5) {
            throw new IllegalStateException("state: " + this.d.e);
        }
        hVar.g(this.f202a);
        h hVar2 = this.d;
        hVar2.e = 6;
        okhttp3.internal.connection.h hVar3 = hVar2.b;
        if (hVar3 != null) {
            hVar3.r(!z, hVar2, this.c, iOException);
        }
    }

    @Override // okio.B
    public D b() {
        return this.f202a;
    }
}

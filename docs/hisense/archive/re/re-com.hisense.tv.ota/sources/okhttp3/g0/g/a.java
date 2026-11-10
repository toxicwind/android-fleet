package okhttp3.g0.g;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okio.B;
import okio.D;

/* JADX INFO: loaded from: classes.dex */
class a implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f186a;
    final /* synthetic */ okio.j b;
    final /* synthetic */ c c;
    final /* synthetic */ okio.i d;

    a(b bVar, okio.j jVar, c cVar, okio.i iVar) {
        this.b = jVar;
        this.c = cVar;
        this.d = iVar;
    }

    @Override // okio.B
    public long K(okio.h hVar, long j) throws IOException {
        try {
            long jK = this.b.K(hVar, j);
            if (jK != -1) {
                hVar.P(this.d.c(), hVar.c0() - jK, jK);
                this.d.F();
                return jK;
            }
            if (!this.f186a) {
                this.f186a = true;
                this.d.close();
            }
            return -1L;
        } catch (IOException e) {
            if (!this.f186a) {
                this.f186a = true;
                this.c.a();
            }
            throw e;
        }
    }

    @Override // okio.B
    public D b() {
        return this.b.b();
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.f186a && !okhttp3.g0.e.p(this, 100, TimeUnit.MILLISECONDS)) {
            this.f186a = true;
            this.c.a();
        }
        this.b.close();
    }
}

package okhttp3;

import java.io.IOException;

/* JADX INFO: renamed from: okhttp3.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0034f implements okhttp3.g0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okhttp3.g0.g.i f180a;
    private okio.A b;
    private okio.A c;
    boolean d;
    final /* synthetic */ C0050j e;

    C0034f(C0050j c0050j, okhttp3.g0.g.i iVar) {
        this.e = c0050j;
        this.f180a = iVar;
        okio.A aD = iVar.d(1);
        this.b = aD;
        this.c = new C0033e(this, aD, c0050j, iVar);
    }

    @Override // okhttp3.g0.g.c
    public void a() {
        synchronized (this.e) {
            if (this.d) {
                return;
            }
            this.d = true;
            this.e.d++;
            okhttp3.g0.e.g(this.b);
            try {
                this.f180a.a();
            } catch (IOException unused) {
            }
        }
    }

    @Override // okhttp3.g0.g.c
    public okio.A b() {
        return this.c;
    }
}

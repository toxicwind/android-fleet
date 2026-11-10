package okhttp3.g0.g;

import java.io.IOException;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ l f190a;

    f(l lVar) {
        this.f190a = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f190a) {
            if ((!this.f190a.n) || this.f190a.o) {
                return;
            }
            try {
                this.f190a.Z();
            } catch (IOException unused) {
                this.f190a.p = true;
            }
            try {
                if (this.f190a.R()) {
                    this.f190a.W();
                    this.f190a.l = 0;
                }
            } catch (IOException unused2) {
                this.f190a.q = true;
                this.f190a.j = t.c(t.b());
            }
        }
    }
}

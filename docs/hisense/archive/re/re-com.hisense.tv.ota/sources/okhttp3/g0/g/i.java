package okhttp3.g0.g;

import java.io.FileNotFoundException;
import java.io.IOException;
import okio.A;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final j f191a;
    final boolean[] b;
    private boolean c;
    final /* synthetic */ l d;

    i(l lVar, j jVar) {
        this.d = lVar;
        this.f191a = jVar;
        this.b = jVar.e ? null : new boolean[lVar.h];
    }

    public void a() {
        synchronized (this.d) {
            if (this.c) {
                throw new IllegalStateException();
            }
            if (this.f191a.f == this) {
                this.d.e(this, false);
            }
            this.c = true;
        }
    }

    public void b() {
        synchronized (this.d) {
            if (this.c) {
                throw new IllegalStateException();
            }
            if (this.f191a.f == this) {
                this.d.e(this, true);
            }
            this.c = true;
        }
    }

    void c() {
        if (this.f191a.f != this) {
            return;
        }
        int i = 0;
        while (true) {
            l lVar = this.d;
            if (i >= lVar.h) {
                this.f191a.f = null;
                return;
            } else {
                try {
                    lVar.f194a.b(this.f191a.d[i]);
                } catch (IOException unused) {
                }
                i++;
            }
        }
    }

    public A d(int i) {
        synchronized (this.d) {
            if (this.c) {
                throw new IllegalStateException();
            }
            if (this.f191a.f != this) {
                return t.b();
            }
            if (!this.f191a.e) {
                this.b[i] = true;
            }
            try {
                return new h(this, this.d.f194a.f(this.f191a.d[i]));
            } catch (FileNotFoundException unused) {
                return t.b();
            }
        }
    }
}

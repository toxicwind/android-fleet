package c.a.b;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public final class Kd extends Za {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Ec.a f1050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1051b;

    public Kd(Ec.a aVar) {
        this.f1050a = aVar;
    }

    @Override // c.a.b.Ec.a
    public void a(Nd.a aVar) {
        if (!this.f1051b) {
            this.f1050a.a(aVar);
        } else if (aVar instanceof Closeable) {
            C0149mb.a((Closeable) aVar);
        }
    }

    @Override // c.a.b.Ec.a
    public void a(boolean z) {
        this.f1051b = true;
        this.f1050a.a(z);
    }

    @Override // c.a.b.Ec.a
    public void a(Throwable th) {
        this.f1051b = true;
        this.f1050a.a(th);
    }
}

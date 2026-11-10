package c.a.c;

import c.a.c.r.a;

/* JADX INFO: loaded from: classes2.dex */
public class n implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f1715a;

    public n(r rVar) {
        this.f1715a = rVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.f1715a.W;
        if (runnable != null) {
            runnable.run();
        }
        r rVar = this.f1715a;
        rVar.w = rVar.new a(rVar.k, this.f1715a.l);
        this.f1715a.s.execute(this.f1715a.w);
        synchronized (this.f1715a.o) {
            this.f1715a.G = Integer.MAX_VALUE;
            this.f1715a.h();
        }
        this.f1715a.X.set(null);
    }
}

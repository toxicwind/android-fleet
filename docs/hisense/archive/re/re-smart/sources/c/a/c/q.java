package c.a.c;

/* JADX INFO: loaded from: classes2.dex */
public class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f1720a;

    public q(r rVar) {
        this.f1720a = rVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1720a.s.execute(this.f1720a.w);
        synchronized (this.f1720a.o) {
            this.f1720a.G = Integer.MAX_VALUE;
            this.f1720a.h();
        }
    }
}

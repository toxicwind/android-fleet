package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Qa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f1121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Pa.d f1122b;

    public Qa(Pa.d dVar, boolean z) {
        this.f1122b = dVar;
        this.f1121a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1121a) {
            Pa pa = Pa.this;
            pa.v = true;
            if (pa.s > 0) {
                Pa.this.u.reset().start();
            }
        }
        Pa.this.A = false;
    }
}

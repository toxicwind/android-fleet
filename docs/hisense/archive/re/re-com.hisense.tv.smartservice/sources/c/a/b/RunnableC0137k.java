package c.a.b;

/* JADX INFO: renamed from: c.a.b.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0137k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0147m f1324a;

    public RunnableC0137k(C0147m c0147m) {
        this.f1324a = c0147m;
    }

    @Override // java.lang.Runnable
    public void run() {
        Ec ec = this.f1324a.f1351c;
        if (ec.isClosed()) {
            return;
        }
        if (ec.r()) {
            ec.close();
        } else {
            ec.r = true;
        }
    }
}

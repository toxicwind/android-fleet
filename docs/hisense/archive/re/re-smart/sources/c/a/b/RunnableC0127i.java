package c.a.b;

/* JADX INFO: renamed from: c.a.b.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0127i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Tc f1268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0147m f1269b;

    public RunnableC0127i(C0147m c0147m, Tc tc) {
        this.f1269b = c0147m;
        this.f1268a = tc;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f1269b.f1351c.a(this.f1268a);
        } catch (Throwable th) {
            C0167q c0167q = this.f1269b.f1350b;
            ((c.a.c.k.b) c0167q.f1410a).a(new RunnableC0162p(c0167q, th));
            this.f1269b.f1351c.close();
        }
    }
}

package c.a.b;

/* JADX INFO: renamed from: c.a.b.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0122h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0147m f1264b;

    public RunnableC0122h(C0147m c0147m, int i) {
        this.f1264b = c0147m;
        this.f1263a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1264b.f1351c.isClosed()) {
            return;
        }
        try {
            this.f1264b.f1351c.b(this.f1263a);
        } catch (Throwable th) {
            C0167q c0167q = this.f1264b.f1350b;
            ((c.a.c.k.b) c0167q.f1410a).a(new RunnableC0162p(c0167q, th));
            this.f1264b.f1351c.close();
        }
    }
}

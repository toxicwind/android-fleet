package c.a.b;

/* JADX INFO: renamed from: c.a.b.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0152n implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1374a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0167q f1375b;

    public RunnableC0152n(C0167q c0167q, int i) {
        this.f1375b = c0167q;
        this.f1374a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1375b.f1411b.a(this.f1374a);
    }
}

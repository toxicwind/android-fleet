package c.a.b;

/* JADX INFO: renamed from: c.a.b.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0162p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Throwable f1401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0167q f1402b;

    public RunnableC0162p(C0167q c0167q, Throwable th) {
        this.f1402b = c0167q;
        this.f1401a = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1402b.f1411b.a(this.f1401a);
    }
}

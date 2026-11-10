package c.a.b;

/* JADX INFO: renamed from: c.a.b.ia, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0128ia implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1270a;

    public RunnableC0128ia(C0138ka c0138ka) {
        this.f1270a = c0138ka;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1270a.h.halfClose();
    }
}

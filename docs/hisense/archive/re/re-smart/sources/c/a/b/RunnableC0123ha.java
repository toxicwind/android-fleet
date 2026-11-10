package c.a.b;

/* JADX INFO: renamed from: c.a.b.ha, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0123ha implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1266b;

    public RunnableC0123ha(C0138ka c0138ka, int i) {
        this.f1266b = c0138ka;
        this.f1265a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1266b.h.request(this.f1265a);
    }
}

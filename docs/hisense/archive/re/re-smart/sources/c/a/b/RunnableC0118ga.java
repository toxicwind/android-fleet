package c.a.b;

/* JADX INFO: renamed from: c.a.b.ga, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0118ga implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f1260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1261b;

    public RunnableC0118ga(C0138ka c0138ka, boolean z) {
        this.f1261b = c0138ka;
        this.f1260a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1261b.h.setMessageCompression(this.f1260a);
    }
}

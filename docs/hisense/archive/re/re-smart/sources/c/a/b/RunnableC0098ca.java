package c.a.b;

/* JADX INFO: renamed from: c.a.b.ca, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0098ca implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ StringBuilder f1236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1237b;

    public RunnableC0098ca(C0138ka c0138ka, StringBuilder sb) {
        this.f1237b = c0138ka;
        this.f1236a = sb;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1237b.a(c.a.za.f1939f.b(this.f1236a.toString()), true);
    }
}

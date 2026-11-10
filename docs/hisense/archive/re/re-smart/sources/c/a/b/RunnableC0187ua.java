package c.a.b;

/* JADX INFO: renamed from: c.a.b.ua, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0187ua implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f1476b;

    public RunnableC0187ua(Na na, int i) {
        this.f1476b = na;
        this.f1475a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1476b.f1088c.b(this.f1475a);
    }
}

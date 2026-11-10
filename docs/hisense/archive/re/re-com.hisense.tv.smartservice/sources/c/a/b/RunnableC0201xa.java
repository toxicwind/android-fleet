package c.a.b;

/* JADX INFO: renamed from: c.a.b.xa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0201xa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f1507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f1508b;

    public RunnableC0201xa(Na na, boolean z) {
        this.f1508b = na;
        this.f1507a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1508b.f1088c.b(this.f1507a);
    }
}

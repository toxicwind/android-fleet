package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ba implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f898b;

    public Ba(Na na, int i) {
        this.f898b = na;
        this.f897a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f898b.f1088c.d(this.f897a);
    }
}

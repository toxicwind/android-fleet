package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
final class _b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1206a;

    public _b(C0130ic c0130ic) {
        this.f1206a = c0130ic;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1206a.g();
        if (this.f1206a.J != null) {
            this.f1206a.J.a();
        }
        if (this.f1206a.I != null) {
            this.f1206a.I.f1290a.f1488b.b();
        }
    }
}

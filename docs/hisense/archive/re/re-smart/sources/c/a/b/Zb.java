package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
final class Zb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1204a;

    public Zb(C0130ic c0130ic) {
        this.f1204a = c0130ic;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1204a.R.get() || this.f1204a.I == null) {
            return;
        }
        this.f1204a.b(false);
        C0130ic.W(this.f1204a);
    }
}

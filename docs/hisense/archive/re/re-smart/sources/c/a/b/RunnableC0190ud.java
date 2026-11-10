package c.a.b;

/* JADX INFO: renamed from: c.a.b.ud, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
class RunnableC0190ud implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1484a;

    public RunnableC0190ud(AbstractC0171qd.i iVar) {
        this.f1484a = iVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.f1420f.execute(new RunnableC0185td(this));
    }
}

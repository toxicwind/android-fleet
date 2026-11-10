package c.a.b;

/* JADX INFO: renamed from: c.a.b.td, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0185td implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RunnableC0190ud f1474a;

    public RunnableC0185td(RunnableC0190ud runnableC0190ud) {
        this.f1474a = runnableC0190ud;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.i iVar = this.f1474a.f1484a;
        AbstractC0171qd.this.b(AbstractC0171qd.this.a(iVar.f1439a.f1444d + 1, false));
    }
}

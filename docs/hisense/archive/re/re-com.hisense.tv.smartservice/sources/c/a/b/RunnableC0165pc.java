package c.a.b;

/* JADX INFO: renamed from: c.a.b.pc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class RunnableC0165pc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j f1407a;

    public RunnableC0165pc(C0130ic.j jVar) {
        this.f1407a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C0130ic.this.M == null) {
            if (this.f1407a.f1297a.get() == C0130ic.g) {
                this.f1407a.f1297a.set(null);
            }
            C0130ic.this.Q.a(C0130ic.f1274d);
        }
    }
}

package c.a.b;

/* JADX INFO: renamed from: c.a.b.bc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class RunnableC0095bc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1234a;

    public RunnableC0095bc(C0130ic c0130ic) {
        this.f1234a = c0130ic;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1234a.S) {
            return;
        }
        this.f1234a.S = true;
        C0130ic.q(this.f1234a);
    }
}

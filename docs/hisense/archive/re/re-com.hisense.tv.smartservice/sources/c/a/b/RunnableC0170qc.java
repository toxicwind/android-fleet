package c.a.b;

import java.util.Iterator;

/* JADX INFO: renamed from: c.a.b.qc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class RunnableC0170qc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j f1414a;

    public RunnableC0170qc(C0130ic.j jVar) {
        this.f1414a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1414a.f1297a.get() == C0130ic.g) {
            this.f1414a.f1297a.set(null);
        }
        if (C0130ic.this.M != null) {
            Iterator it = C0130ic.this.M.iterator();
            while (it.hasNext()) {
                ((C0130ic.j.a) it.next()).cancel("Channel is forcefully shutdown", null);
            }
        }
        C0130ic.this.Q.b(C0130ic.f1273c);
    }
}

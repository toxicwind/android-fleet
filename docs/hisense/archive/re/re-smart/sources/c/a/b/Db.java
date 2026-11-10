package c.a.b;

import c.a.AbstractC0225i;

/* JADX INFO: loaded from: classes2.dex */
public class Db implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ib f933a;

    public Db(Ib ib) {
        this.f933a = ib;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f933a.j.a(AbstractC0225i.a.INFO, "Terminated");
        Ib ib = this.f933a;
        C0194vc c0194vc = (C0194vc) ib.f1012e;
        C0130ic.this.L.remove(ib);
        c.a.K.b(C0130ic.this.aa.f830d, ib);
        C0130ic.J(C0130ic.this);
    }
}

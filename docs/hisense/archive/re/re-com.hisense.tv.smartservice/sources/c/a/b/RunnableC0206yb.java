package c.a.b;

import c.a.AbstractC0225i;
import c.a.EnumC0234s;

/* JADX INFO: renamed from: c.a.b.yb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0206yb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ib f1516a;

    public RunnableC0206yb(Ib ib) {
        this.f1516a = ib;
    }

    @Override // java.lang.Runnable
    public void run() {
        Ib ib = this.f1516a;
        ib.p = null;
        ib.j.a(AbstractC0225i.a.INFO, "CONNECTING after backoff");
        Ib.a(this.f1516a, EnumC0234s.CONNECTING);
        Ib.a(this.f1516a);
    }
}

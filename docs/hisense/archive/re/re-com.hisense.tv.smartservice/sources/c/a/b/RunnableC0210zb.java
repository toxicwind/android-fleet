package c.a.b;

import c.a.AbstractC0225i;
import c.a.EnumC0234s;

/* JADX INFO: renamed from: c.a.b.zb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0210zb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ib f1536a;

    public RunnableC0210zb(Ib ib) {
        this.f1536a = ib;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1536a.w.f1906a == EnumC0234s.IDLE) {
            this.f1536a.j.a(AbstractC0225i.a.INFO, "CONNECTING as requested");
            Ib.a(this.f1536a, EnumC0234s.CONNECTING);
            Ib.a(this.f1536a);
        }
    }
}

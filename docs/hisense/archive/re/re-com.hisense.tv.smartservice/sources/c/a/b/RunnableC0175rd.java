package c.a.b;

import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: c.a.b.rd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0175rd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.e f1451a;

    public RunnableC0175rd(AbstractC0171qd.e eVar) {
        this.f1451a = eVar;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0082 A[Catch: all -> 0x006b, TryCatch #0 {all -> 0x006b, blocks: (B:4:0x0018, B:21:0x009c, B:7:0x0025, B:9:0x004a, B:11:0x0054, B:19:0x006d, B:20:0x0082), top: B:31:0x0018 }] */
    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.d dVar;
        AbstractC0171qd abstractC0171qd = AbstractC0171qd.this;
        boolean z = false;
        AbstractC0171qd.j jVarA = abstractC0171qd.a(abstractC0171qd.s.f1437e, false);
        synchronized (AbstractC0171qd.this.m) {
            try {
                dVar = null;
                boolean z2 = true;
                if (this.f1451a.f1428a.f1427c) {
                    z = true;
                } else {
                    AbstractC0171qd.this.s = AbstractC0171qd.this.s.a(jVarA);
                    if (AbstractC0171qd.this.a(AbstractC0171qd.this.s)) {
                        if (AbstractC0171qd.this.q != null) {
                            AbstractC0171qd.k kVar = AbstractC0171qd.this.q;
                            if (kVar.f1448d.get() <= kVar.f1446b) {
                                z2 = false;
                            }
                            if (!z2) {
                                AbstractC0171qd.this.s = AbstractC0171qd.this.s.b();
                                AbstractC0171qd.this.x = null;
                            }
                        }
                        AbstractC0171qd abstractC0171qd2 = AbstractC0171qd.this;
                        dVar = new AbstractC0171qd.d(AbstractC0171qd.this.m);
                        abstractC0171qd2.x = dVar;
                    } else {
                        AbstractC0171qd.this.s = AbstractC0171qd.this.s.b();
                        AbstractC0171qd.this.x = null;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            jVarA.f1441a.a(c.a.za.f1937d.b("Unneeded hedging"));
            return;
        }
        if (dVar != null) {
            dVar.a(AbstractC0171qd.this.h.schedule(new AbstractC0171qd.e(dVar), AbstractC0171qd.this.k.f1405b, TimeUnit.NANOSECONDS));
        }
        AbstractC0171qd.this.b(jVarA);
    }
}

package c.a.b;

import c.a.AbstractC0225i;
import c.a.C0235t;
import c.a.EnumC0234s;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Kb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Ib.d f1043b;

    public Kb(Ib.d dVar, c.a.za zaVar) {
        this.f1043b = dVar;
        this.f1042a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (Ib.this.w.f1906a == EnumC0234s.SHUTDOWN) {
            return;
        }
        Cc cc = Ib.this.v;
        Ib.d dVar = this.f1043b;
        X x = dVar.f1019a;
        if (cc == x) {
            Ib ib = Ib.this;
            ib.v = null;
            ib.l.b();
            Ib.a(Ib.this, EnumC0234s.IDLE);
            return;
        }
        Ib ib2 = Ib.this;
        if (ib2.u == x) {
            Preconditions.checkState(ib2.w.f1906a == EnumC0234s.CONNECTING, "Expected state is CONNECTING, actual state is %s", Ib.this.w.f1906a);
            Ib.c cVar = Ib.this.l;
            c.a.B b2 = cVar.f1016a.get(cVar.f1017b);
            cVar.f1018c++;
            if (cVar.f1018c >= b2.f783b.size()) {
                cVar.f1017b++;
                cVar.f1018c = 0;
            }
            Ib.c cVar2 = Ib.this.l;
            if (cVar2.f1017b < cVar2.f1016a.size()) {
                Ib.a(Ib.this);
                return;
            }
            Ib ib3 = Ib.this;
            ib3.u = null;
            ib3.l.b();
            Ib ib4 = Ib.this;
            c.a.za zaVar = this.f1042a;
            ib4.k.b();
            Preconditions.checkArgument(!zaVar.c(), "The error status must not be OK");
            ib4.a(new C0235t(EnumC0234s.TRANSIENT_FAILURE, zaVar));
            if (ib4.n == null) {
                ib4.n = ((Sa.a) ib4.f1011d).a();
            }
            long jA = ((Sa) ib4.n).a() - ib4.o.elapsed(TimeUnit.NANOSECONDS);
            ib4.j.a(AbstractC0225i.a.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", ib4.c(zaVar), Long.valueOf(jA));
            Preconditions.checkState(ib4.p == null, "previous reconnectTask is not done");
            ib4.p = ib4.k.a(new RunnableC0206yb(ib4), jA, TimeUnit.NANOSECONDS, ib4.g);
        }
    }
}

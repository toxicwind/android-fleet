package c.a.b;

import c.a.C0235t;
import c.a.EnumC0234s;
import com.google.common.base.Preconditions;
import java.util.Queue;

/* JADX INFO: loaded from: classes2.dex */
public class Cb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Ib f923b;

    public Cb(Ib ib, c.a.za zaVar) {
        this.f923b = ib;
        this.f922a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f923b.w.f1906a == EnumC0234s.SHUTDOWN) {
            return;
        }
        Ib ib = this.f923b;
        ib.x = this.f922a;
        Cc cc = ib.v;
        Ib ib2 = this.f923b;
        X x = ib2.u;
        ib2.v = null;
        ib2.u = null;
        EnumC0234s enumC0234s = EnumC0234s.SHUTDOWN;
        ib2.k.b();
        ib2.a(C0235t.a(enumC0234s));
        this.f923b.l.b();
        if (this.f923b.s.isEmpty()) {
            Ib ib3 = this.f923b;
            c.a.Ea ea = ib3.k;
            Db db = new Db(ib3);
            Queue<Runnable> queue = ea.f796b;
            Preconditions.checkNotNull(db, "runnable is null");
            queue.add(db);
            ea.a();
        }
        Ib ib4 = this.f923b;
        ib4.k.b();
        c.a.Ea.b bVar = ib4.p;
        if (bVar != null) {
            bVar.a();
            ib4.p = null;
            ib4.n = null;
        }
        c.a.Ea.b bVar2 = this.f923b.q;
        if (bVar2 != null) {
            bVar2.a();
            this.f923b.r.b(this.f922a);
            Ib ib5 = this.f923b;
            ib5.q = null;
            ib5.r = null;
        }
        if (cc != null) {
            cc.b(this.f922a);
        }
        if (x != null) {
            x.b(this.f922a);
        }
    }
}

package c.a.b;

import c.a.C0235t;
import c.a.EnumC0234s;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class Jb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ib.d f1037a;

    public Jb(Ib.d dVar) {
        this.f1037a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Ib.d dVar = this.f1037a;
        Ib ib = Ib.this;
        ib.n = null;
        if (ib.x != null) {
            Preconditions.checkState(ib.v == null, "Unexpected non-null activeTransport");
            Ib.d dVar2 = this.f1037a;
            dVar2.f1019a.b(Ib.this.x);
            return;
        }
        X x = ib.u;
        X x2 = dVar.f1019a;
        if (x == x2) {
            ib.v = x2;
            ib.u = null;
            EnumC0234s enumC0234s = EnumC0234s.READY;
            ib.k.b();
            ib.a(C0235t.a(enumC0234s));
        }
    }
}

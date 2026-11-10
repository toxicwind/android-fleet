package c.a.b;

import c.a.EnumC0234s;
import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Bb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ List f899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Ib f900b;

    public Bb(Ib ib, List list) {
        this.f900b = ib;
        this.f899a = list;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0091  */
    @Override // java.lang.Runnable
    public void run() {
        Cc cc;
        SocketAddress socketAddressA = this.f900b.l.a();
        Ib.c cVar = this.f900b.l;
        cVar.f1016a = this.f899a;
        cVar.b();
        Ib ib = this.f900b;
        ib.m = this.f899a;
        if (ib.w.f1906a == EnumC0234s.READY || this.f900b.w.f1906a == EnumC0234s.CONNECTING) {
            Ib.c cVar2 = this.f900b.l;
            boolean z = false;
            for (int i = 0; i < cVar2.f1016a.size(); i++) {
                int iIndexOf = cVar2.f1016a.get(i).f783b.indexOf(socketAddressA);
                if (iIndexOf != -1) {
                    cVar2.f1017b = i;
                    cVar2.f1018c = iIndexOf;
                    z = true;
                    break;
                }
            }
            if (z) {
                cc = null;
            } else if (this.f900b.w.f1906a == EnumC0234s.READY) {
                cc = this.f900b.v;
                Ib ib2 = this.f900b;
                ib2.v = null;
                ib2.l.b();
                Ib.a(this.f900b, EnumC0234s.IDLE);
            } else {
                this.f900b.u.b(c.a.za.l.b("InternalSubchannel closed pending transport due to address change"));
                Ib ib3 = this.f900b;
                ib3.u = null;
                ib3.l.b();
                Ib.a(this.f900b);
                cc = null;
            }
        } else {
            cc = null;
        }
        if (cc != null) {
            Ib ib4 = this.f900b;
            if (ib4.q != null) {
                ib4.r.b(c.a.za.l.b("InternalSubchannel closed transport early due to address change"));
                this.f900b.q.a();
                Ib ib5 = this.f900b;
                ib5.q = null;
                ib5.r = null;
            }
            Ib ib6 = this.f900b;
            ib6.r = cc;
            ib6.q = ib6.k.a(new Ab(this), 5L, TimeUnit.SECONDS, this.f900b.g);
        }
    }
}

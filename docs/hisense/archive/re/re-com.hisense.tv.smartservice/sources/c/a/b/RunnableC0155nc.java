package c.a.b;

import c.a.AbstractC0225i;
import c.a.C0086b;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/* JADX INFO: renamed from: c.a.b.nc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
final class RunnableC0155nc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.ia.f f1380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0130ic.i f1381b;

    public RunnableC0155nc(C0130ic.i iVar, c.a.ia.f fVar) {
        this.f1381b = iVar;
        this.f1380a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Bc bc;
        Object obj;
        List<c.a.B> list = this.f1380a.f1863a;
        C0130ic.this.Z.a(AbstractC0225i.a.DEBUG, "Resolved address: {0}, config={1}", list, this.f1380a.f1864b);
        if (C0130ic.this.ca != C0130ic.k.SUCCESS) {
            C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Address resolved: {0}", list);
            C0130ic.this.ca = C0130ic.k.SUCCESS;
        }
        C0130ic.this.oa = null;
        c.a.ia.f fVar = this.f1380a;
        c.a.ia.b bVar = fVar.f1865c;
        c.a.M m = (c.a.M) fVar.f1864b.a(c.a.M.f832a);
        Bc bc2 = (bVar == null || (obj = bVar.f1862b) == null) ? null : (Bc) obj;
        c.a.za zaVar = bVar != null ? bVar.f1861a : null;
        if (C0130ic.this.ga) {
            if (bc2 != null) {
                if (m != null) {
                    C0130ic.this.ba.a(m);
                    if (bc2.a() != null) {
                        C0130ic.this.Z.a(AbstractC0225i.a.DEBUG, "Method configs in service config will be discarded due to presence ofconfig-selector");
                    }
                } else {
                    C0130ic.this.ba.a(bc2.a());
                }
            } else if (C0130ic.this.ea != null) {
                bc2 = C0130ic.this.ea;
                C0130ic.this.ba.a(bc2.a());
                C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Received no service config, using default service config");
            } else if (zaVar == null) {
                bc2 = C0130ic.f1276f;
                C0130ic.this.ba.a(null);
            } else {
                if (!C0130ic.this.fa) {
                    C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Fallback to error due to invalid first service config without default config");
                    this.f1381b.a(bVar.f1861a);
                    return;
                }
                bc2 = C0130ic.this.da;
            }
            bc = bc2;
            if (!bc.equals(C0130ic.this.da)) {
                AbstractC0225i abstractC0225i = C0130ic.this.Z;
                AbstractC0225i.a aVar = AbstractC0225i.a.INFO;
                Object[] objArr = new Object[1];
                objArr[0] = bc == C0130ic.f1276f ? " to empty" : "";
                abstractC0225i.a(aVar, "Service config changed{0}", objArr);
                C0130ic.this.da = bc;
            }
            try {
                C0130ic.this.fa = true;
            } catch (RuntimeException e2) {
                C0130ic.f1271a.log(Level.WARNING, b.a.a.a.a.a(b.a.a.a.a.a("["), C0130ic.this.i, "] Unexpected exception from parsing service config"), (Throwable) e2);
            }
        } else {
            if (bc2 != null) {
                C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Service config from name resolver discarded by channel settings");
            }
            bc = C0130ic.this.ea == null ? C0130ic.f1276f : C0130ic.this.ea;
            if (m != null) {
                C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Config selector from name resolver discarded by channel settings");
            }
            C0130ic.this.ba.a(bc.a());
        }
        C0086b c0086b = this.f1380a.f1864b;
        C0130ic.i iVar = this.f1381b;
        if (iVar.f1294a == C0130ic.this.I) {
            C0086b.a aVarB = c0086b.b();
            aVarB.a(c.a.M.f832a);
            Map<String, ?> map = bc.f906f;
            if (map != null) {
                aVarB.a(c.a.U.f844a, map);
                aVarB.a();
            }
            C0191v.a aVar2 = this.f1381b.f1294a.f1290a;
            C0086b c0086b2 = C0086b.f876a;
            c.a.za zaVarA = aVar2.a(new c.a.U.f(list, aVarB.a(), bc.f905e, null));
            if (zaVarA.c()) {
                return;
            }
            this.f1381b.b(zaVarA.a(this.f1381b.f1295b + " was used"));
        }
    }
}

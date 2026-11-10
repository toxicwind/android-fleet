package c.a.b;

import c.a.AbstractC0225i;
import c.a.C0086b;
import c.a.EnumC0234s;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: c.a.b.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0191v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.W f1485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1486b;

    /* JADX INFO: renamed from: c.a.b.v$a */
    @VisibleForTesting
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.U.c f1487a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.a.U f1488b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.a.V f1489c;

        public a(c.a.U.c cVar) {
            this.f1487a = cVar;
            this.f1489c = C0191v.this.f1485a.a(C0191v.this.f1486b);
            c.a.V v = this.f1489c;
            if (v == null) {
                throw new IllegalStateException(b.a.a.a.a.a(b.a.a.a.a.a("Could not find policy '"), C0191v.this.f1486b, "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files."));
            }
            this.f1488b = v.a(cVar);
        }

        public c.a.za a(c.a.U.f fVar) {
            List<c.a.B> list = fVar.f853a;
            C0086b c0086b = fVar.f854b;
            Fd.b bVar = (Fd.b) fVar.f855c;
            C0186u c0186u = null;
            if (bVar == null) {
                try {
                    bVar = new Fd.b(C0191v.a(C0191v.this, C0191v.this.f1486b, "using default policy"), null);
                } catch (e e2) {
                    this.f1487a.a(EnumC0234s.TRANSIENT_FAILURE, new c(c.a.za.k.b(e2.getMessage())));
                    this.f1488b.c();
                    this.f1489c = null;
                    this.f1488b = new d(c0186u);
                    return c.a.za.f1936c;
                }
            }
            if (this.f1489c == null || !bVar.f969a.a().equals(this.f1489c.a())) {
                this.f1487a.a(EnumC0234s.CONNECTING, new b(c0186u));
                this.f1488b.c();
                this.f1489c = bVar.f969a;
                c.a.U u = this.f1488b;
                this.f1488b = this.f1489c.a(this.f1487a);
                C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Load balancer changed from {0} to {1}", u.getClass().getSimpleName(), this.f1488b.getClass().getSimpleName());
            }
            Object obj = bVar.f970b;
            if (obj != null) {
                C0130ic.this.Z.a(AbstractC0225i.a.DEBUG, "Load-balancing config: {0}", bVar.f970b);
            }
            c.a.U u2 = this.f1488b;
            if (!fVar.f853a.isEmpty()) {
                C0086b c0086b2 = C0086b.f876a;
                u2.a(new c.a.U.f(fVar.f853a, c0086b, obj, null));
                return c.a.za.f1936c;
            }
            u2.a();
            return c.a.za.l.b("NameResolver returned no usable address. addrs=" + list + ", attrs=" + c0086b);
        }
    }

    /* JADX INFO: renamed from: c.a.b.v$b */
    private static final class b extends c.a.U.h {
        public b() {
        }

        @Override // c.a.U.h
        public c.a.U.d a(c.a.U.e eVar) {
            return c.a.U.d.f848a;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) b.class).toString();
        }

        public /* synthetic */ b(C0186u c0186u) {
        }
    }

    /* JADX INFO: renamed from: c.a.b.v$c */
    private static final class c extends c.a.U.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.za f1491a;

        public c(c.a.za zaVar) {
            this.f1491a = zaVar;
        }

        @Override // c.a.U.h
        public c.a.U.d a(c.a.U.e eVar) {
            return c.a.U.d.b(this.f1491a);
        }
    }

    /* JADX INFO: renamed from: c.a.b.v$d */
    private static final class d extends c.a.U {
        public /* synthetic */ d(C0186u c0186u) {
        }

        @Override // c.a.U
        public void a(c.a.U.f fVar) {
        }

        @Override // c.a.U
        public void a(c.a.za zaVar) {
        }

        @Override // c.a.U
        public void c() {
        }
    }

    /* JADX INFO: renamed from: c.a.b.v$e */
    @VisibleForTesting
    static final class e extends Exception {
        public /* synthetic */ e(String str, C0186u c0186u) {
            super(str);
        }
    }

    public C0191v(String str) {
        c.a.W wA = c.a.W.a();
        Preconditions.checkNotNull(wA, "registry");
        this.f1485a = wA;
        Preconditions.checkNotNull(str, "defaultPolicy");
        this.f1486b = str;
    }

    public a a(c.a.U.c cVar) {
        return new a(cVar);
    }

    public static /* synthetic */ c.a.V a(C0191v c0191v, String str, String str2) throws e {
        c.a.V vA = c0191v.f1485a.a(str);
        if (vA != null) {
            return vA;
        }
        throw new e("Trying to load '" + str + "' because " + str2 + ", but it's unavailable", null);
    }

    public c.a.ia.b a(Map<String, ?> map) {
        List<Fd.a> listA;
        if (map != null) {
            try {
                listA = Fd.a(Fd.a(map));
            } catch (RuntimeException e2) {
                return new c.a.ia.b(c.a.za.f1938e.b("can't parse load balancer configuration").b(e2));
            }
        } else {
            listA = null;
        }
        if (listA == null || listA.isEmpty()) {
            return null;
        }
        return Fd.a(listA, this.f1485a);
    }
}

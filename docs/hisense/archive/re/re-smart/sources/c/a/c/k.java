package c.a.c;

import androidx.core.app.NotificationCompat;
import c.a.AbstractC0230n;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.Ca;
import c.a.P;
import c.a.Q;
import c.a.b.AbstractC0087a;
import c.a.b.AbstractC0173rb;
import c.a.b.C0149mb;
import c.a.b.Ld;
import c.a.b.N;
import c.a.b.Od;
import c.a.b.Td;
import c.a.b.Ud;
import c.a.b.Vc;
import c.a.za;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.util.List;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes2.dex */
public class k extends AbstractC0087a {
    public static final d.g h = new d.g();
    public final C0223ga<?, ?> i;
    public final String j;
    public final Ld k;
    public String l;
    public Object m;
    public volatile int n;
    public final b o;
    public final a p;
    public final C0086b q;
    public boolean r;

    public k(C0223ga<?, ?> c0223ga, C0219ea c0219ea, e eVar, r rVar, z zVar, Object obj, int i, int i2, String str, String str2, Ld ld, Td td, C0220f c0220f, boolean z) {
        super(new x(), ld, td, c0219ea, c0220f, z && c0223ga.h);
        this.n = -1;
        this.p = new a();
        this.r = false;
        Preconditions.checkNotNull(ld, "statsTraceCtx");
        this.k = ld;
        this.i = c0223ga;
        this.l = str;
        this.j = str2;
        this.q = rVar.x;
        this.o = new b(i, ld, obj, eVar, zVar, rVar, i2, c0223ga.f1833b);
    }

    @Override // c.a.b.M
    public C0086b getAttributes() {
        return this.q;
    }

    @Override // c.a.b.M
    public void a(String str) {
        Preconditions.checkNotNull(str, "authority");
        this.l = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public class a implements AbstractC0087a.b {
        public a() {
        }

        public void a(za zaVar) {
            c.b.c.f1949a.a("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (k.this.o.y) {
                    k.this.o.b(zaVar, true, null);
                }
                c.b.c.f1949a.b("OkHttpClientStream$Sink.cancel");
            } catch (Throwable th) {
                c.b.c.f1949a.b("OkHttpClientStream$Sink.cancel");
                throw th;
            }
        }

        public void a(Ud ud, boolean z, boolean z2, int i) {
            d.g gVar;
            c.b.c.f1949a.a("OkHttpClientStream$Sink.writeFrame");
            if (ud == null) {
                gVar = k.h;
            } else {
                gVar = ((w) ud).f1750a;
                int i2 = (int) gVar.f2102c;
                if (i2 > 0) {
                    k.this.a(i2);
                }
            }
            try {
                synchronized (k.this.o.y) {
                    b.a(k.this.o, gVar, z, z2);
                    k.this.f1209b.a(i);
                }
                c.b.c.f1949a.b("OkHttpClientStream$Sink.writeFrame");
            } catch (Throwable th) {
                c.b.c.f1949a.b("OkHttpClientStream$Sink.writeFrame");
                throw th;
            }
        }

        public void a(C0219ea c0219ea, byte[] bArr) {
            c.b.c.f1949a.a("OkHttpClientStream$Sink.writeHeaders");
            String str = "/" + k.this.i.f1833b;
            if (bArr != null) {
                k.this.r = true;
                str = str + "?" + BaseEncoding.BASE64.encode(bArr);
            }
            try {
                synchronized (k.this.o.y) {
                    b.a(k.this.o, c0219ea, str);
                }
                c.b.c.f1949a.b("OkHttpClientStream$Sink.writeHeaders");
            } catch (Throwable th) {
                c.b.c.f1949a.b("OkHttpClientStream$Sink.writeHeaders");
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public class b extends AbstractC0173rb {
        public d.g A;
        public boolean B;
        public boolean C;
        public boolean D;
        public int E;
        public int F;
        public final e G;
        public final z H;
        public final r I;
        public boolean J;
        public final c.b.d K;
        public final int x;
        public final Object y;
        public List<c.a.c.a.a.d> z;

        public b(int i, Ld ld, Object obj, e eVar, z zVar, r rVar, int i2, String str) {
            super(i, ld, k.this.f1209b);
            this.A = new d.g();
            this.B = false;
            this.C = false;
            this.D = false;
            this.J = true;
            Preconditions.checkNotNull(obj, "lock");
            this.y = obj;
            this.G = eVar;
            this.H = zVar;
            this.I = rVar;
            this.E = i2;
            this.F = i2;
            this.x = i2;
            this.K = c.b.c.f1949a.a(str, Long.MIN_VALUE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void a(List<c.a.c.a.a.d> list, boolean z) {
            za zaVar;
            StringBuilder sb;
            za zaVarA;
            if (z) {
                C0219ea c0219eaA = P.a(A.a(list));
                Preconditions.checkNotNull(c0219eaA, "trailers");
                if (this.t == null && !this.w) {
                    this.t = d(c0219eaA);
                    if (this.t != null) {
                        this.u = c0219eaA;
                    }
                }
                za zaVar2 = this.t;
                if (zaVar2 != null) {
                    this.t = zaVar2.a("trailers: " + c0219eaA);
                    b(this.t, false, this.u);
                    return;
                }
                za zaVar3 = (za) c0219eaA.b(Q.f843b);
                if (zaVar3 != null) {
                    zaVarA = zaVar3.b((String) c0219eaA.b(Q.f842a));
                } else if (this.w) {
                    zaVarA = za.f1938e.b("missing GRPC status in response");
                } else {
                    Integer num = (Integer) c0219eaA.b(AbstractC0173rb.s);
                    zaVarA = (num != null ? C0149mb.a(num.intValue()) : za.k.b("missing HTTP status code")).a("missing GRPC status, inferred error from HTTP status code");
                }
                AbstractC0173rb.c(c0219eaA);
                Preconditions.checkNotNull(zaVarA, NotificationCompat.CATEGORY_STATUS);
                Preconditions.checkNotNull(c0219eaA, "trailers");
                if (this.p) {
                    AbstractC0087a.f1208a.log(Level.INFO, "Received trailers on closed stream:\n {1}\n {2}", new Object[]{zaVarA, c0219eaA});
                    return;
                }
                for (Ca ca : this.h.f1069a) {
                    ((AbstractC0230n) ca).a(c0219eaA);
                }
                a(zaVarA, N.a.PROCESSED, false, c0219eaA);
                return;
            }
            C0219ea c0219eaA2 = P.a(A.a(list));
            Preconditions.checkNotNull(c0219eaA2, "headers");
            za zaVar4 = this.t;
            if (zaVar4 != null) {
                this.t = zaVar4.a("headers: " + c0219eaA2);
                return;
            }
            try {
                if (this.w) {
                    this.t = za.k.b("Received headers twice");
                    zaVar = this.t;
                    c0219eaA2 = c0219eaA2;
                    if (zaVar != null) {
                        sb = new StringBuilder();
                        sb.append("headers: ");
                        sb.append(c0219eaA2);
                        za zaVarA2 = zaVar.a(sb.toString());
                    }
                } else {
                    Integer num2 = (Integer) c0219eaA2.b(AbstractC0173rb.s);
                    if (num2 == null || num2.intValue() < 100 || num2.intValue() >= 200) {
                        this.w = true;
                        this.t = d(c0219eaA2);
                        if (this.t != null) {
                            zaVar = this.t;
                            if (zaVar != null) {
                                c0219eaA2 = c0219eaA2;
                                sb = new StringBuilder();
                                sb.append("headers: ");
                                sb.append(c0219eaA2);
                                za zaVarA3 = zaVar.a(sb.toString());
                            }
                        } else {
                            AbstractC0173rb.c(c0219eaA2);
                            a(c0219eaA2);
                            zaVar = this.t;
                            c0219eaA2 = c0219eaA2;
                            if (zaVar != null) {
                                sb = new StringBuilder();
                                sb.append("headers: ");
                                sb.append(c0219eaA2);
                                za zaVarA4 = zaVar.a(sb.toString());
                            }
                        }
                    } else {
                        zaVar = this.t;
                        if (zaVar != null) {
                            c0219eaA2 = c0219eaA2;
                            sb = new StringBuilder();
                            sb.append("headers: ");
                            sb.append(c0219eaA2);
                            za zaVarA5 = zaVar.a(sb.toString());
                        }
                    }
                }
                c0219eaA2 = c0219eaA2;
                c0219eaA2 = c0219eaA2;
            } finally {
                za zaVar5 = this.t;
                if (zaVar5 != null) {
                    this.t = zaVar5.a("headers: " + c0219eaA2);
                    this.u = c0219eaA2;
                    this.v = AbstractC0173rb.b(c0219eaA2);
                }
            }
        }

        public final void b(za zaVar, boolean z, C0219ea c0219ea) {
            if (this.D) {
                return;
            }
            this.D = true;
            if (!this.J) {
                this.I.a(k.this.n, zaVar, N.a.PROCESSED, z, c.a.c.a.a.a.CANCEL, c0219ea);
                return;
            }
            r rVar = this.I;
            k kVar = k.this;
            rVar.H.remove(kVar);
            rVar.a(kVar);
            this.z = null;
            this.A.clear();
            this.J = false;
            if (c0219ea == null) {
                c0219ea = new C0219ea();
            }
            a(zaVar, N.a.PROCESSED, true, c0219ea);
        }

        public void d(int i) {
            Preconditions.checkState(k.this.n == -1, "the stream has been started with id %s", i);
            k.this.n = i;
            b bVar = k.this.o;
            super.c();
            Td td = bVar.f1253c;
            td.f1159c++;
            ((Od) td.f1158b).a();
            if (this.J) {
                this.G.a(k.this.r, false, k.this.n, 0, this.z);
                for (Ca ca : k.this.k.f1069a) {
                    ((AbstractC0230n) ca).b();
                }
                this.z = null;
                if (this.A.f2102c > 0) {
                    this.H.a(this.B, k.this.n, this.A, this.C);
                }
                this.J = false;
            }
        }

        public void a(d.g gVar, boolean z) throws Throwable {
            this.E -= (int) gVar.f2102c;
            if (this.E < 0) {
                this.G.a(k.this.n, c.a.c.a.a.a.FLOW_CONTROL_ERROR);
                this.I.a(k.this.n, za.k.b("Received data size exceeded our receiving window size"), N.a.PROCESSED, false, null, null);
                return;
            }
            u uVar = new u(gVar);
            za zaVar = this.t;
            boolean z2 = false;
            if (zaVar != null) {
                StringBuilder sbA = b.a.a.a.a.a("DATA-----------------------------\n");
                sbA.append(Vc.a(uVar, this.v));
                this.t = zaVar.a(sbA.toString());
                uVar.f1748a.clear();
                if (this.t.q.length() > 1000 || z) {
                    b(this.t, false, this.u);
                    return;
                }
                return;
            }
            if (!this.w) {
                b(za.k.b("headers not received before payload"), false, new C0219ea());
                return;
            }
            int iM = uVar.m();
            Preconditions.checkNotNull(uVar, "frame");
            try {
                try {
                    if (this.p) {
                        AbstractC0087a.f1208a.log(Level.INFO, "Received data on closed stream");
                        uVar.f1748a.clear();
                    } else {
                        try {
                            this.f1251a.a(uVar);
                        } catch (Throwable th) {
                            a(th);
                        }
                    }
                    if (z) {
                        if (iM > 0) {
                            this.t = za.k.b("Received unexpected EOS on non-empty DATA frame from server");
                        } else {
                            this.t = za.k.b("Received unexpected EOS on empty DATA frame from server");
                        }
                        this.u = new C0219ea();
                        a(this.t, N.a.PROCESSED, false, this.u);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (z2) {
                        uVar.f1748a.clear();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                z2 = true;
            }
        }

        @Override // c.a.b.Ec.a
        public void a(Throwable th) {
            b(za.a(th), true, new C0219ea());
        }

        @Override // c.a.b.Ec.a
        public void a(int i) {
            this.F -= i;
            int i2 = this.F;
            float f2 = i2;
            int i3 = this.x;
            if (f2 <= i3 * 0.5f) {
                int i4 = i3 - i2;
                this.E += i4;
                this.F = i2 + i4;
                this.G.windowUpdate(k.this.n, i4);
            }
        }

        public void a(Runnable runnable) {
            synchronized (this.y) {
                runnable.run();
            }
        }

        @Override // c.a.b.AbstractC0087a.c, c.a.b.Ec.a
        public void a(boolean z) {
            if (!e()) {
                this.I.a(k.this.n, null, N.a.PROCESSED, false, c.a.c.a.a.a.CANCEL, null);
            } else {
                this.I.a(k.this.n, null, N.a.PROCESSED, false, null, null);
            }
            Preconditions.checkState(this.p, "status should have been reported on deframer closed");
            this.m = true;
            if (this.q && z) {
                a(za.k.b("Encountered end-of-stream mid-frame"), N.a.PROCESSED, true, new C0219ea());
            }
            Runnable runnable = this.n;
            if (runnable != null) {
                runnable.run();
                this.n = null;
            }
        }

        public static /* synthetic */ void a(b bVar, d.g gVar, boolean z, boolean z2) {
            if (bVar.D) {
                return;
            }
            if (bVar.J) {
                bVar.A.write(gVar, (int) gVar.f2102c);
                bVar.B |= z;
                bVar.C |= z2;
            } else {
                Preconditions.checkState(k.this.n != -1, "streamId should be set");
                bVar.H.a(z, k.this.n, gVar, z2);
            }
        }

        public static /* synthetic */ void a(b bVar, C0219ea c0219ea, String str) {
            bVar.z = f.a(c0219ea, str, k.this.l, k.this.j, k.this.r, bVar.I.E == null);
            r rVar = bVar.I;
            k kVar = k.this;
            za zaVar = rVar.y;
            if (zaVar != null) {
                kVar.o.a(zaVar, N.a.REFUSED, true, new C0219ea());
            } else if (rVar.r.size() >= rVar.G) {
                rVar.H.add(kVar);
                rVar.b(kVar);
            } else {
                rVar.c(kVar);
            }
        }
    }
}

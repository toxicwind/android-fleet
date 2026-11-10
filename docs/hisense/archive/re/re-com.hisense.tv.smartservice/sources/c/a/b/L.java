package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0236u;
import c.a.C0238w;
import c.a.C0240y;
import c.a.InterfaceC0231o;
import c.a.InterfaceC0233q;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class L<ReqT, RespT> extends AbstractC0226j<ReqT, RespT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1052a = Logger.getLogger(L.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f1053b = FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING.getBytes(Charset.forName("US-ASCII"));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0223ga<ReqT, RespT> f1054c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.d f1055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Executor f1056e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1057f;
    public final A g;
    public final C0236u h;
    public volatile ScheduledFuture<?> i;
    public final boolean j;
    public C0220f k;
    public M l;
    public volatile boolean m;
    public boolean n;
    public boolean o;
    public final b p;
    public final ScheduledExecutorService r;
    public boolean s;
    public final L<ReqT, RespT>.c q = new c(null);
    public C0240y t = C0240y.f1929b;
    public c.a.r u = c.a.r.f1897a;

    interface b {
    }

    private final class c implements C0236u.a {
        public /* synthetic */ c(E e2) {
        }
    }

    private class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f1062a;

        public d(long j) {
            this.f1062a = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0198wb c0198wb = new C0198wb();
            L.this.l.a(c0198wb);
            long jAbs = Math.abs(this.f1062a) / TimeUnit.SECONDS.toNanos(1L);
            long jAbs2 = Math.abs(this.f1062a) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sbA = b.a.a.a.a.a("deadline exceeded after ");
            if (this.f1062a < 0) {
                sbA.append('-');
            }
            sbA.append(jAbs);
            sbA.append(String.format(Locale.US, ".%09d", Long.valueOf(jAbs2)));
            sbA.append("s. ");
            sbA.append(c0198wb);
            L.this.l.a(c.a.za.f1939f.a(sbA.toString()));
        }
    }

    public L(C0223ga<ReqT, RespT> c0223ga, Executor executor, C0220f c0220f, b bVar, ScheduledExecutorService scheduledExecutorService, A a2, c.a.M m) {
        this.f1054c = c0223ga;
        this.f1055d = c.b.c.f1949a.a(c0223ga.f1833b, System.identityHashCode(this));
        boolean z = true;
        if (executor == DirectExecutor.INSTANCE) {
            this.f1056e = new Cd();
            this.f1057f = true;
        } else {
            this.f1056e = new Ed(executor);
            this.f1057f = false;
        }
        this.g = a2;
        this.h = C0236u.c();
        C0223ga.c cVar = c0223ga.f1832a;
        if (cVar != C0223ga.c.UNARY && cVar != C0223ga.c.SERVER_STREAMING) {
            z = false;
        }
        this.j = z;
        this.k = c0220f;
        this.p = bVar;
        this.r = scheduledExecutorService;
        c.b.c.f1949a.a("ClientCall.<init>", this.f1055d);
    }

    @Override // c.a.AbstractC0226j
    public void cancel(String str, Throwable th) {
        c.b.c.f1949a.b("ClientCall.cancel", this.f1055d);
        try {
            a(str, th);
        } finally {
            c.b.c.f1949a.c("ClientCall.cancel", this.f1055d);
        }
    }

    @Override // c.a.AbstractC0226j
    public C0086b getAttributes() {
        M m = this.l;
        return m != null ? m.getAttributes() : C0086b.f876a;
    }

    @Override // c.a.AbstractC0226j
    public void halfClose() {
        c.b.c.f1949a.b("ClientCall.halfClose", this.f1055d);
        try {
            Preconditions.checkState(this.l != null, "Not started");
            Preconditions.checkState(!this.n, "call was cancelled");
            Preconditions.checkState(!this.o, "call already half-closed");
            this.o = true;
            this.l.b();
        } finally {
            c.b.c.f1949a.c("ClientCall.halfClose", this.f1055d);
        }
    }

    @Override // c.a.AbstractC0226j
    public boolean isReady() {
        return this.l.isReady();
    }

    @Override // c.a.AbstractC0226j
    public void request(int i) {
        c.b.c.f1949a.b("ClientCall.request", this.f1055d);
        try {
            boolean z = true;
            Preconditions.checkState(this.l != null, "Not started");
            if (i < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
            this.l.b(i);
        } finally {
            c.b.c.f1949a.c("ClientCall.request", this.f1055d);
        }
    }

    @Override // c.a.AbstractC0226j
    public void sendMessage(ReqT reqt) {
        c.b.c.f1949a.b("ClientCall.sendMessage", this.f1055d);
        try {
            a(reqt);
        } finally {
            c.b.c.f1949a.c("ClientCall.sendMessage", this.f1055d);
        }
    }

    @Override // c.a.AbstractC0226j
    public void setMessageCompression(boolean z) {
        Preconditions.checkState(this.l != null, "Not started");
        this.l.a(z);
    }

    @Override // c.a.AbstractC0226j
    public void start(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
        c.b.c.f1949a.b("ClientCall.start", this.f1055d);
        try {
            a(aVar, c0219ea);
        } finally {
            c.b.c.f1949a.c("ClientCall.start", this.f1055d);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(FirebaseAnalytics.Param.METHOD, this.f1054c).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class a implements N {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0226j.a<RespT> f1058a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.a.za f1059b;

        public a(AbstractC0226j.a<RespT> aVar) {
            Preconditions.checkNotNull(aVar, "observer");
            this.f1058a = aVar;
        }

        public static /* synthetic */ void a(a aVar, c.a.za zaVar) {
            aVar.f1059b = zaVar;
            L.this.l.a(zaVar);
        }

        public final void b(c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
            C0238w c0238wA = L.this.a();
            if (zaVar.p == c.a.za.a.CANCELLED && c0238wA != null && c0238wA.a()) {
                C0198wb c0198wb = new C0198wb();
                L.this.l.a(c0198wb);
                zaVar = c.a.za.f1939f.a("ClientCall was cancelled at or after deadline. " + c0198wb);
                c0219ea = new C0219ea();
            }
            L.this.f1056e.execute(new J(this, c.b.c.a(), zaVar, c0219ea));
        }

        @Override // c.a.b.N
        public void a(C0219ea c0219ea) {
            c.b.c.f1949a.b("ClientStreamListener.headersRead", L.this.f1055d);
            try {
                L.this.f1056e.execute(new H(this, c.b.c.a(), c0219ea));
            } finally {
                c.b.c.f1949a.c("ClientStreamListener.headersRead", L.this.f1055d);
            }
        }

        @Override // c.a.b.Nd
        public void a(Nd.a aVar) {
            c.b.c.f1949a.b("ClientStreamListener.messagesAvailable", L.this.f1055d);
            try {
                L.this.f1056e.execute(new I(this, c.b.c.a(), aVar));
            } finally {
                c.b.c.f1949a.c("ClientStreamListener.messagesAvailable", L.this.f1055d);
            }
        }

        @Override // c.a.b.N
        public void a(c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
            c.b.c.f1949a.b("ClientStreamListener.closed", L.this.f1055d);
            try {
                b(zaVar, aVar, c0219ea);
            } finally {
                c.b.c.f1949a.c("ClientStreamListener.closed", L.this.f1055d);
            }
        }

        @Override // c.a.b.Nd
        public void a() {
            if (L.this.f1054c.f1832a.a()) {
                return;
            }
            c.b.c.f1949a.b("ClientStreamListener.onReady", L.this.f1055d);
            try {
                L.this.f1056e.execute(new K(this, c.b.c.a()));
            } finally {
                c.b.c.f1949a.c("ClientStreamListener.onReady", L.this.f1055d);
            }
        }
    }

    public final void b() {
        this.h.a(this.q);
        ScheduledFuture<?> scheduledFuture = this.i;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public final void a(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
        InterfaceC0233q interfaceC0233q;
        M c0125hc;
        Preconditions.checkState(this.l == null, "Already started");
        Preconditions.checkState(!this.n, "call was cancelled");
        Preconditions.checkNotNull(aVar, "observer");
        Preconditions.checkNotNull(c0219ea, "headers");
        if (this.h.e()) {
            this.l = Ic.f1023a;
            this.f1056e.execute(new F(this, aVar));
            return;
        }
        Bc.a aVar2 = (Bc.a) this.k.a(Bc.a.f907a);
        if (aVar2 != null) {
            Long l = aVar2.f908b;
            if (l != null) {
                C0238w c0238wA = C0238w.a(l.longValue(), TimeUnit.NANOSECONDS);
                C0238w c0238w = this.k.f1813b;
                if (c0238w == null || c0238wA.compareTo(c0238w) < 0) {
                    this.k = this.k.a(c0238wA);
                }
            }
            Boolean bool = aVar2.f909c;
            if (bool != null) {
                this.k = bool.booleanValue() ? this.k.b() : this.k.c();
            }
            Integer num = aVar2.f910d;
            if (num != null) {
                C0220f c0220f = this.k;
                Integer num2 = c0220f.j;
                if (num2 != null) {
                    this.k = c0220f.a(Math.min(num2.intValue(), aVar2.f910d.intValue()));
                } else {
                    this.k = c0220f.a(num.intValue());
                }
            }
            Integer num3 = aVar2.f911e;
            if (num3 != null) {
                C0220f c0220f2 = this.k;
                Integer num4 = c0220f2.k;
                if (num4 != null) {
                    this.k = c0220f2.b(Math.min(num4.intValue(), aVar2.f911e.intValue()));
                } else {
                    this.k = c0220f2.b(num3.intValue());
                }
            }
        }
        String str = this.k.f1817f;
        if (str != null) {
            interfaceC0233q = this.u.f1898b.get(str);
            if (interfaceC0233q == null) {
                this.l = Ic.f1023a;
                this.f1056e.execute(new G(this, aVar, str));
                return;
            }
        } else {
            interfaceC0233q = InterfaceC0231o.b.f1883a;
        }
        C0240y c0240y = this.t;
        boolean z = this.s;
        c0219ea.a(C0149mb.f1360c);
        if (interfaceC0233q != InterfaceC0231o.b.f1883a) {
            c0219ea.a(C0149mb.f1360c, interfaceC0233q.a());
        }
        c0219ea.a(C0149mb.f1361d);
        byte[] bArr = c0240y.f1931d;
        if (bArr.length != 0) {
            c0219ea.a(C0149mb.f1361d, bArr);
        }
        c0219ea.a(C0149mb.f1362e);
        c0219ea.a(C0149mb.f1363f);
        if (z) {
            c0219ea.a(C0149mb.f1363f, f1053b);
        }
        C0238w c0238wA2 = a();
        if (!(c0238wA2 != null && c0238wA2.a())) {
            C0238w c0238wD = this.h.d();
            C0238w c0238w2 = this.k.f1813b;
            if (f1052a.isLoggable(Level.FINE) && c0238wA2 != null && c0238wA2.equals(c0238wD)) {
                StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(Math.max(0L, c0238wA2.a(TimeUnit.NANOSECONDS)))));
                if (c0238w2 == null) {
                    sb.append(" Explicit call timeout was not set.");
                } else {
                    sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(c0238w2.a(TimeUnit.NANOSECONDS))));
                }
                f1052a.fine(sb.toString());
            }
            b bVar = this.p;
            C0223ga<ReqT, RespT> c0223ga = this.f1054c;
            C0220f c0220f3 = this.k;
            C0236u c0236u = this.h;
            C0130ic.a aVar3 = (C0130ic.a) bVar;
            if (!C0130ic.this.ka) {
                O oA = aVar3.a(new Pc(c0223ga, c0219ea, c0220f3));
                C0236u c0236uA = c0236u.a();
                try {
                    c0125hc = oA.a(c0223ga, c0219ea, c0220f3, C0149mb.a(c0220f3, c0219ea, 0, false));
                    c0236u.a(c0236uA);
                } catch (Throwable th) {
                    c0236u.a(c0236uA);
                    throw th;
                }
            } else {
                AbstractC0171qd.k kVar = C0130ic.this.da.f904d;
                Bc.a aVar4 = (Bc.a) c0220f3.a(Bc.a.f907a);
                c0125hc = new C0125hc(aVar3, c0223ga, c0219ea, c0220f3, aVar4 == null ? null : aVar4.f912f, aVar4 == null ? null : aVar4.g, kVar, c0236u);
            }
            this.l = c0125hc;
        } else {
            this.l = new Ta(c.a.za.f1939f.b("ClientCall started after deadline exceeded: " + c0238wA2), N.a.PROCESSED, C0149mb.a(this.k, c0219ea, 0, false));
        }
        if (this.f1057f) {
            this.l.a();
        }
        String str2 = this.k.f1815d;
        if (str2 != null) {
            this.l.a(str2);
        }
        Integer num5 = this.k.j;
        if (num5 != null) {
            this.l.c(num5.intValue());
        }
        Integer num6 = this.k.k;
        if (num6 != null) {
            this.l.d(num6.intValue());
        }
        if (c0238wA2 != null) {
            this.l.a(c0238wA2);
        }
        this.l.a(interfaceC0233q);
        boolean z2 = this.s;
        if (z2) {
            this.l.b(z2);
        }
        this.l.a(this.t);
        A a2 = this.g;
        a2.f883b.add(1L);
        ((Od) a2.f882a).a();
        this.l.a(new a(aVar));
        this.h.a((C0236u.a) this.q, (Executor) DirectExecutor.INSTANCE);
        if (c0238wA2 != null && !c0238wA2.equals(this.h.d()) && this.r != null) {
            long jA = c0238wA2.a(TimeUnit.NANOSECONDS);
            this.i = this.r.schedule(new Sb(new d(jA)), jA, TimeUnit.NANOSECONDS);
        }
        if (this.m) {
            b();
        }
    }

    public final C0238w a() {
        C0238w c0238w = this.k.f1813b;
        C0238w c0238wD = this.h.d();
        if (c0238w != null) {
            if (c0238wD == null) {
                return c0238w;
            }
            c0238w.a(c0238wD);
            c0238w.a(c0238wD);
            if (c0238w.f1926f - c0238wD.f1926f < 0) {
                return c0238w;
            }
        }
        return c0238wD;
    }

    public final void a(String str, Throwable th) {
        c.a.za zaVarB;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            f1052a.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (this.n) {
            return;
        }
        this.n = true;
        try {
            if (this.l != null) {
                c.a.za zaVar = c.a.za.f1937d;
                if (str != null) {
                    zaVarB = zaVar.b(str);
                } else {
                    zaVarB = zaVar.b("Call cancelled without message");
                }
                if (th != null) {
                    zaVarB = zaVarB.b(th);
                }
                this.l.a(zaVarB);
            }
        } finally {
            b();
        }
    }

    public final void a(ReqT reqt) {
        Preconditions.checkState(this.l != null, "Not started");
        Preconditions.checkState(!this.n, "call was cancelled");
        Preconditions.checkState(!this.o, "call was half-closed");
        try {
            if (this.l instanceof AbstractC0171qd) {
                ((AbstractC0171qd) this.l).a(reqt);
            } else {
                this.l.a(((c.a.d.a.b.a) this.f1054c.f1835d).a(reqt));
            }
            if (this.j) {
                return;
            }
            this.l.flush();
        } catch (Error e2) {
            this.l.a(c.a.za.f1937d.b("Client sendMessage() failed with Error"));
            throw e2;
        } catch (RuntimeException e3) {
            this.l.a(c.a.za.f1937d.b(e3).b("Failed to stream message"));
        }
    }

    public final void a(AbstractC0226j.a<RespT> aVar, c.a.za zaVar, C0219ea c0219ea) {
        aVar.onClose(zaVar, c0219ea);
    }
}

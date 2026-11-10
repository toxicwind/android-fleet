package c.a.c;

import c.a.AbstractC0225i;
import c.a.Ga;
import c.a.Y;
import c.a.b.AbstractC0097c;
import c.a.b.C0149mb;
import c.a.b.C0176s;
import c.a.b.C0207yc;
import c.a.b.Id;
import c.a.b.P;
import c.a.b.Rb;
import c.a.b.Td;
import c.a.b.X;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedLong;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends AbstractC0097c<h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @VisibleForTesting
    public static final c.a.c.a.c f1695a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f1696b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Id.b<Executor> f1697c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0207yc f1698d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Executor f1700f;
    public ScheduledExecutorService g;
    public SocketFactory h;
    public SSLSocketFactory i;
    public HostnameVerifier k;
    public boolean q;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Td.a f1699e = Td.f1157a;
    public c.a.c.a.c l = f1695a;
    public a m = a.TLS;
    public long n = UnsignedLong.UNSIGNED_MASK;
    public long o = C0149mb.j;
    public int p = 65535;
    public int r = 4194304;
    public int s = Integer.MAX_VALUE;
    public final boolean j = false;

    private enum a {
        TLS,
        PLAINTEXT
    }

    private final class b implements C0207yc.a {
        public /* synthetic */ b(g gVar) {
        }

        @Override // c.a.b.C0207yc.a
        public int a() {
            return h.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final class c implements C0207yc.b {
        public /* synthetic */ c(g gVar) {
        }
    }

    static final class d implements P {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Executor f1706a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1707b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f1708c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Td.a f1709d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final SocketFactory f1710e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final SSLSocketFactory f1711f;
        public final HostnameVerifier g;
        public final c.a.c.a.c h;
        public final int i;
        public final boolean j;
        public final C0176s k;
        public final long l;
        public final int m;
        public final boolean n;
        public final int o;
        public final ScheduledExecutorService p;
        public final boolean q;
        public boolean r;

        public /* synthetic */ d(Executor executor, ScheduledExecutorService scheduledExecutorService, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, c.a.c.a.c cVar, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, Td.a aVar, boolean z3, g gVar) {
            this.f1708c = scheduledExecutorService == null;
            this.p = this.f1708c ? (ScheduledExecutorService) Id.a(C0149mb.o) : scheduledExecutorService;
            this.f1710e = socketFactory;
            this.f1711f = sSLSocketFactory;
            this.g = hostnameVerifier;
            this.h = cVar;
            this.i = i;
            this.j = z;
            this.k = new C0176s("keepalive time nanos", j);
            this.l = j2;
            this.m = i2;
            this.n = z2;
            this.o = i3;
            this.q = z3;
            this.f1707b = executor == null;
            Preconditions.checkNotNull(aVar, "transportTracerFactory");
            this.f1709d = aVar;
            if (this.f1707b) {
                this.f1706a = (Executor) Id.a(h.f1697c);
            } else {
                this.f1706a = executor;
            }
        }

        @Override // c.a.b.P
        public X a(SocketAddress socketAddress, P.a aVar, AbstractC0225i abstractC0225i) {
            if (this.r) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            C0176s.a aVarA = this.k.a();
            r rVar = new r((InetSocketAddress) socketAddress, aVar.f1098a, aVar.f1100c, aVar.f1099b, this.f1706a, this.f1710e, this.f1711f, this.g, this.h, this.i, this.m, aVar.f1101d, new i(this, aVarA), this.o, this.f1709d.a(), this.q);
            if (this.j) {
                long j = aVarA.f1455a;
                long j2 = this.l;
                boolean z = this.n;
                rVar.M = true;
                rVar.N = j;
                rVar.O = j2;
                rVar.P = z;
            }
            return rVar;
        }

        @Override // c.a.b.P, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.r) {
                return;
            }
            this.r = true;
            if (this.f1708c) {
                Id.a(C0149mb.o, this.p);
            }
            if (this.f1707b) {
                Id.a(h.f1697c, this.f1706a);
            }
        }

        @Override // c.a.b.P
        public ScheduledExecutorService n() {
            return this.p;
        }
    }

    static {
        Logger.getLogger(h.class.getName());
        c.a.c.a.c.a aVar = new c.a.c.a.c.a(c.a.c.a.c.f1624b);
        aVar.a(c.a.c.a.a.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, c.a.c.a.a.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, c.a.c.a.a.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, c.a.c.a.a.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384);
        aVar.a(c.a.c.a.l.TLS_1_2);
        aVar.a(true);
        f1695a = aVar.a();
        f1696b = TimeUnit.DAYS.toNanos(1000L);
        f1697c = new g();
        EnumSet.of(Ga.MTLS, Ga.CUSTOM_MANAGERS);
    }

    public h(String str) {
        g gVar = null;
        this.f1698d = new C0207yc(str, new c(gVar), new b(gVar));
    }

    public static h forTarget(String str) {
        return new h(str);
    }

    @Override // c.a.b.AbstractC0097c
    public Y<?> c() {
        return this.f1698d;
    }

    public P e() {
        return new d(this.f1700f, this.g, this.h, f(), this.k, this.l, this.r, this.n != UnsignedLong.UNSIGNED_MASK, this.n, this.o, this.p, this.q, this.s, this.f1699e, false, null);
    }

    @VisibleForTesting
    public SSLSocketFactory f() {
        int iOrdinal = this.m.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                return null;
            }
            StringBuilder sbA = b.a.a.a.a.a("Unknown negotiation type: ");
            sbA.append(this.m);
            throw new RuntimeException(sbA.toString());
        }
        try {
            if (this.i == null) {
                this.i = SSLContext.getInstance("Default", c.a.c.a.j.f1646c.f1647d).getSocketFactory();
            }
            return this.i;
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException("TLS Provider failure", e2);
        }
    }

    public int g() {
        int iOrdinal = this.m.ordinal();
        if (iOrdinal == 0) {
            return 443;
        }
        if (iOrdinal == 1) {
            return 80;
        }
        throw new AssertionError(b.a.a.a.a.a(new StringBuilder(), this.m, " not handled"));
    }

    public h scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        this.g = scheduledExecutorService;
        return this;
    }

    public h sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        Preconditions.checkState(!this.j, "Cannot change security when using ChannelCredentials");
        this.i = sSLSocketFactory;
        this.m = a.TLS;
        return this;
    }

    public h transportExecutor(Executor executor) {
        this.f1700f = executor;
        return this;
    }

    @Override // c.a.Y
    public h a(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive");
        this.n = timeUnit.toNanos(j);
        this.n = Math.max(this.n, Rb.f1126a);
        if (this.n >= f1696b) {
            this.n = UnsignedLong.UNSIGNED_MASK;
        }
        return this;
    }

    @Override // c.a.Y
    public h b() {
        Preconditions.checkState(!this.j, "Cannot change security when using ChannelCredentials");
        this.m = a.PLAINTEXT;
        return this;
    }
}

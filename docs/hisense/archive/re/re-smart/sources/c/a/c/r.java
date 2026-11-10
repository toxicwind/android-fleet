package c.a.c;

import c.a.Aa;
import c.a.AbstractC0230n;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.H;
import c.a.O;
import c.a.b.AbstractC0193vb;
import c.a.b.C0104db;
import c.a.b.C0149mb;
import c.a.b.C0188ub;
import c.a.b.Cc;
import c.a.b.Ed;
import c.a.b.Id;
import c.a.b.Ld;
import c.a.b.M;
import c.a.b.N;
import c.a.b.Rb;
import c.a.b.Td;
import c.a.b.X;
import c.a.za;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.primitives.UnsignedLong;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.installations.Utils;
import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import d.C;
import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class r implements X, e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<c.a.c.a.a.a, za> f1721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f1722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k[] f1723c;
    public C0188ub A;
    public boolean B;
    public boolean C;
    public final SocketFactory D;
    public SSLSocketFactory E;
    public HostnameVerifier F;
    public final c.a.c.a.c I;
    public c.a.c.a.a.c J;
    public ScheduledExecutorService K;
    public Rb L;
    public boolean M;
    public long N;
    public long O;
    public boolean P;
    public final Runnable Q;
    public final int R;
    public final boolean S;
    public final Td T;

    @VisibleForTesting
    public final H V;
    public Runnable W;
    public SettableFuture<Void> X;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InetSocketAddress f1724d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1725e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1726f;
    public final Supplier<Stopwatch> h;
    public final int i;
    public Cc.a j;
    public c.a.c.a.a.b k;
    public s l;
    public e m;
    public z n;
    public final O p;
    public int q;
    public final Executor s;
    public final Ed t;
    public final int u;
    public int v;
    public a w;
    public C0086b x;
    public za y;
    public boolean z;
    public final Random g = new Random();
    public final Object o = new Object();
    public final Map<Integer, k> r = new HashMap();
    public int G = 0;
    public final Deque<k> H = new LinkedList();
    public final AbstractC0193vb<k> U = new l(this);

    static {
        EnumMap enumMap = new EnumMap(c.a.c.a.a.a.class);
        enumMap.put(c.a.c.a.a.a.NO_ERROR, za.k.b("No error: A GRPC status of OK should have been sent"));
        enumMap.put(c.a.c.a.a.a.PROTOCOL_ERROR, za.k.b("Protocol error"));
        enumMap.put(c.a.c.a.a.a.INTERNAL_ERROR, za.k.b("Internal error"));
        enumMap.put(c.a.c.a.a.a.FLOW_CONTROL_ERROR, za.k.b("Flow control error"));
        enumMap.put(c.a.c.a.a.a.STREAM_CLOSED, za.k.b("Stream closed"));
        enumMap.put(c.a.c.a.a.a.FRAME_TOO_LARGE, za.k.b("Frame too large"));
        enumMap.put(c.a.c.a.a.a.REFUSED_STREAM, za.l.b("Refused stream"));
        enumMap.put(c.a.c.a.a.a.CANCEL, za.f1937d.b("Cancelled"));
        enumMap.put(c.a.c.a.a.a.COMPRESSION_ERROR, za.k.b("Compression error"));
        enumMap.put(c.a.c.a.a.a.CONNECT_ERROR, za.k.b("Connect error"));
        enumMap.put(c.a.c.a.a.a.ENHANCE_YOUR_CALM, za.i.b("Enhance your calm"));
        enumMap.put(c.a.c.a.a.a.INADEQUATE_SECURITY, za.g.b("Inadequate security"));
        f1721a = Collections.unmodifiableMap(enumMap);
        f1722b = Logger.getLogger(r.class.getName());
        f1723c = new k[0];
    }

    public r(InetSocketAddress inetSocketAddress, String str, String str2, C0086b c0086b, Executor executor, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, c.a.c.a.c cVar, int i, int i2, H h, Runnable runnable, int i3, Td td, boolean z) {
        Preconditions.checkNotNull(inetSocketAddress, AddressResponse.sAddress);
        this.f1724d = inetSocketAddress;
        this.f1725e = str;
        this.u = i;
        this.i = i2;
        Preconditions.checkNotNull(executor, "executor");
        this.s = executor;
        this.t = new Ed(executor);
        this.q = 3;
        this.D = socketFactory == null ? SocketFactory.getDefault() : socketFactory;
        this.E = sSLSocketFactory;
        this.F = hostnameVerifier;
        Preconditions.checkNotNull(cVar, "connectionSpec");
        this.I = cVar;
        this.h = C0149mb.p;
        this.f1726f = C0149mb.a("okhttp", str2);
        this.V = h;
        Preconditions.checkNotNull(runnable, "tooManyPingsRunnable");
        this.Q = runnable;
        this.R = i3;
        if (td == null) {
            throw new NullPointerException();
        }
        this.T = td;
        this.p = O.a((Class<?>) r.class, inetSocketAddress.toString());
        C0086b.a aVarA = C0086b.a();
        aVarA.a(C0104db.f1242b, c0086b);
        this.x = aVarA.a();
        this.S = z;
        f();
    }

    public static /* synthetic */ Socket a(r rVar, Socket socket) {
        return socket;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.p.f839d).add(AddressResponse.sAddress, this.f1724d).toString();
    }

    public static /* synthetic */ int b(r rVar, int i) {
        int i2 = rVar.v + i;
        rVar.v = i2;
        return i2;
    }

    @VisibleForTesting
    public int d() {
        URI uriA = C0149mb.a(this.f1725e);
        return uriA.getPort() != -1 ? uriA.getPort() : this.f1724d.getPort();
    }

    public final Throwable e() {
        synchronized (this.o) {
            if (this.y != null) {
                return this.y.a();
            }
            return new Aa(za.l.b("Connection closed"));
        }
    }

    public final void f() {
        synchronized (this.o) {
            this.T.a(new m(this));
        }
    }

    @VisibleForTesting
    public void g() {
        synchronized (this.o) {
            this.m.connectionPreface();
            c.a.c.a.a.i iVar = new c.a.c.a.a.i();
            iVar.a(7, 0, this.i);
            e eVar = this.m;
            eVar.f1688d.a(s.a.OUTBOUND, iVar);
            try {
                eVar.f1687c.b(iVar);
            } catch (IOException e2) {
                ((r) eVar.f1686b).a(e2);
            }
            if (this.i > 65535) {
                this.m.windowUpdate(0, this.i - 65535);
            }
        }
    }

    public final boolean h() {
        boolean z = false;
        while (!this.H.isEmpty() && this.r.size() < this.G) {
            c(this.H.poll());
            z = true;
        }
        return z;
    }

    public final void i() {
        if (this.y == null || !this.r.isEmpty() || !this.H.isEmpty() || this.B) {
            return;
        }
        this.B = true;
        Rb rb = this.L;
        if (rb != null) {
            rb.e();
            Id.a(C0149mb.o, this.K);
            this.K = null;
        }
        C0188ub c0188ub = this.A;
        if (c0188ub != null) {
            c0188ub.a(e());
            this.A = null;
        }
        if (!this.z) {
            this.z = true;
            this.m.a(0, c.a.c.a.a.a.NO_ERROR, new byte[0]);
        }
        this.m.close();
    }

    @Override // c.a.b.Cc
    public void b(za zaVar) {
        synchronized (this.o) {
            if (this.y != null) {
                return;
            }
            this.y = zaVar;
            this.j.a(this.y);
            i();
        }
    }

    public final void c(k kVar) {
        Preconditions.checkState(kVar.n == -1, "StreamId already assigned");
        this.r.put(Integer.valueOf(this.q), kVar);
        b(kVar);
        kVar.o.d(this.q);
        C0223ga.c cVar = kVar.i.f1832a;
        if ((cVar != C0223ga.c.UNARY && cVar != C0223ga.c.SERVER_STREAMING) || kVar.r) {
            this.m.flush();
        }
        int i = this.q;
        if (i >= 2147483645) {
            this.q = Integer.MAX_VALUE;
            a(Integer.MAX_VALUE, c.a.c.a.a.a.NO_ERROR, za.l.b("Stream ids exhausted"));
        } else {
            this.q = i + 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public class a implements c.a.c.a.a.b.a, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final s f1727a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.a.c.a.a.b f1728b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1729c;

        public a(r rVar, c.a.c.a.a.b bVar) {
            s sVar = new s(Level.FINE, r.class);
            r.this = rVar;
            this.f1729c = true;
            this.f1728b = bVar;
            this.f1727a = sVar;
        }

        public void a() {
        }

        public void a(int i, int i2, int i3, boolean z) {
        }

        public void a(boolean z, int i, d.i iVar, int i2) {
            this.f1727a.a(s.a.INBOUND, i, iVar.getBuffer(), i2, z);
            k kVarA = r.this.a(i);
            if (kVarA != null) {
                long j = i2;
                iVar.c(j);
                d.g gVar = new d.g();
                gVar.write(iVar.getBuffer(), j);
                c.b.c.f1949a.a("OkHttpClientTransport$ClientFrameHandler.data", kVarA.o.K);
                synchronized (r.this.o) {
                    kVarA.o.a(gVar, z);
                }
            } else {
                if (!r.this.b(i)) {
                    r.this.a(c.a.c.a.a.a.PROTOCOL_ERROR, "Received data for unknown stream: " + i);
                    return;
                }
                synchronized (r.this.o) {
                    r.this.m.a(i, c.a.c.a.a.a.INVALID_STREAM);
                }
                iVar.skip(i2);
            }
            r.b(r.this, i2);
            if (r.this.v >= r.this.i * 0.5f) {
                synchronized (r.this.o) {
                    r.this.m.windowUpdate(0, r.this.v);
                }
                r.this.v = 0;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Logger logger;
            za zaVarB;
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpClientTransport");
            while (((c.a.c.a.a.g.c) this.f1728b).a(this)) {
                try {
                    try {
                        if (r.this.L != null) {
                            r.this.L.a();
                        }
                    } catch (Throwable th) {
                        r.this.a(0, c.a.c.a.a.a.PROTOCOL_ERROR, za.k.b("error in frame handler").b(th));
                        try {
                            this.f1728b.close();
                        } catch (IOException e2) {
                            e = e2;
                            logger = r.f1722b;
                            logger.log(Level.INFO, "Exception closing frame reader", (Throwable) e);
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        this.f1728b.close();
                    } catch (IOException e3) {
                        r.f1722b.log(Level.INFO, "Exception closing frame reader", (Throwable) e3);
                    }
                    r.this.j.b();
                    Thread.currentThread().setName(name);
                    throw th2;
                }
            }
            synchronized (r.this.o) {
                zaVarB = r.this.y;
            }
            if (zaVarB == null) {
                zaVarB = za.l.b("End of stream or IOException");
            }
            r.this.a(0, c.a.c.a.a.a.INTERNAL_ERROR, zaVarB);
            try {
                this.f1728b.close();
            } catch (IOException e4) {
                e = e4;
                logger = r.f1722b;
                logger.log(Level.INFO, "Exception closing frame reader", (Throwable) e);
            }
            r.this.j.b();
            Thread.currentThread().setName(name);
        }

        @VisibleForTesting
        public a(c.a.c.a.a.b bVar, s sVar) {
            this.f1729c = true;
            this.f1728b = bVar;
            this.f1727a = sVar;
        }

        public void a(boolean z, boolean z2, int i, int i2, List<c.a.c.a.a.d> list, c.a.c.a.a.e eVar) {
            s sVar = this.f1727a;
            s.a aVar = s.a.INBOUND;
            if (sVar.a()) {
                sVar.f1731a.log(sVar.f1732b, aVar + " HEADERS: streamId=" + i + " headers=" + list + " endStream=" + z2);
            }
            za zaVarB = null;
            boolean z3 = true;
            if (r.this.R != Integer.MAX_VALUE) {
                long jG = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    c.a.c.a.a.d dVar = list.get(i3);
                    jG += (long) (dVar.g.g() + dVar.f1571f.g() + 32);
                }
                int iMin = (int) Math.min(jG, 2147483647L);
                if (iMin > r.this.R) {
                    za zaVar = za.i;
                    Object[] objArr = new Object[3];
                    objArr[0] = z2 ? "trailer" : "header";
                    objArr[1] = Integer.valueOf(r.this.R);
                    objArr[2] = Integer.valueOf(iMin);
                    zaVarB = zaVar.b(String.format("Response %s metadata larger than %d: %d", objArr));
                }
            }
            synchronized (r.this.o) {
                k kVar = (k) r.this.r.get(Integer.valueOf(i));
                if (kVar == null) {
                    if (r.this.b(i)) {
                        r.this.m.a(i, c.a.c.a.a.a.INVALID_STREAM);
                    }
                } else if (zaVarB == null) {
                    c.b.c.f1949a.a("OkHttpClientTransport$ClientFrameHandler.headers", kVar.o.K);
                    kVar.o.a(list, z2);
                } else {
                    if (!z2) {
                        r.this.m.a(i, c.a.c.a.a.a.CANCEL);
                    }
                    kVar.o.a(zaVarB, false, new C0219ea());
                }
                z3 = false;
            }
            if (z3) {
                r.this.a(c.a.c.a.a.a.PROTOCOL_ERROR, "Received header for unknown stream: " + i);
            }
        }

        public void a(int i, c.a.c.a.a.a aVar) {
            this.f1727a.a(s.a.INBOUND, i, aVar);
            za zaVarA = r.a(aVar).a("Rst Stream");
            za.a aVar2 = zaVarA.p;
            boolean z = aVar2 == za.a.CANCELLED || aVar2 == za.a.DEADLINE_EXCEEDED;
            synchronized (r.this.o) {
                k kVar = (k) r.this.r.get(Integer.valueOf(i));
                if (kVar != null) {
                    c.b.c.f1949a.a("OkHttpClientTransport$ClientFrameHandler.rstStream", kVar.o.K);
                    r.this.a(i, zaVarA, aVar == c.a.c.a.a.a.REFUSED_STREAM ? N.a.REFUSED : N.a.PROCESSED, z, null, null);
                }
            }
        }

        public void a(boolean z, c.a.c.a.a.i iVar) {
            boolean zA;
            this.f1727a.a(s.a.INBOUND, iVar);
            synchronized (r.this.o) {
                if (iVar.a(4)) {
                    r.this.G = iVar.f1622d[4];
                }
                if (iVar.a(7)) {
                    zA = r.this.n.a(iVar.f1622d[7]);
                } else {
                    zA = false;
                }
                if (this.f1729c) {
                    r.this.j.a();
                    this.f1729c = false;
                }
                r.this.m.a(iVar);
                if (zA) {
                    r.this.n.b();
                }
                r.this.h();
            }
        }

        public void a(boolean z, int i, int i2) {
            C0188ub c0188ub;
            long j = (((long) i) << 32) | (((long) i2) & 4294967295L);
            this.f1727a.a(s.a.INBOUND, j);
            if (!z) {
                synchronized (r.this.o) {
                    r.this.m.ping(true, i, i2);
                }
                return;
            }
            synchronized (r.this.o) {
                try {
                    if (r.this.A == null) {
                        r.f1722b.warning("Received unexpected ping ack. No ping outstanding");
                    } else if (r.this.A.f1478b == j) {
                        c0188ub = r.this.A;
                        r.this.A = null;
                    } else {
                        r.f1722b.log(Level.WARNING, String.format("Received unexpected ping ack. Expecting %d, got %d", Long.valueOf(r.this.A.f1478b), Long.valueOf(j)));
                    }
                    c0188ub = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (c0188ub != null) {
                c0188ub.a();
            }
        }

        public void a(int i, int i2, List<c.a.c.a.a.d> list) {
            s sVar = this.f1727a;
            s.a aVar = s.a.INBOUND;
            if (sVar.a()) {
                sVar.f1731a.log(sVar.f1732b, aVar + " PUSH_PROMISE: streamId=" + i + " promisedStreamId=" + i2 + " headers=" + list);
            }
            synchronized (r.this.o) {
                r.this.m.a(i, c.a.c.a.a.a.PROTOCOL_ERROR);
            }
        }

        public void a(int i, long j) {
            this.f1727a.a(s.a.INBOUND, i, j);
            if (j == 0) {
                if (i == 0) {
                    r.this.a(c.a.c.a.a.a.PROTOCOL_ERROR, "Received 0 flow control window increment.");
                    return;
                } else {
                    r.this.a(i, za.k.b("Received 0 flow control window increment."), N.a.PROCESSED, false, c.a.c.a.a.a.PROTOCOL_ERROR, null);
                    return;
                }
            }
            boolean z = false;
            synchronized (r.this.o) {
                try {
                    if (i == 0) {
                        r.this.n.a(null, (int) j);
                        return;
                    }
                    k kVar = (k) r.this.r.get(Integer.valueOf(i));
                    if (kVar != null) {
                        r.this.n.a(kVar, (int) j);
                    } else if (!r.this.b(i)) {
                        z = true;
                    }
                    if (z) {
                        r.this.a(c.a.c.a.a.a.PROTOCOL_ERROR, "Received window_update for unknown stream: " + i);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // c.a.b.O
    public /* bridge */ /* synthetic */ M a(C0223ga c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
        return a((C0223ga<?, ?>) c0223ga, c0219ea, c0220f, abstractC0230nArr);
    }

    @Override // c.a.b.O
    public void a(c.a.b.O.a aVar, Executor executor) {
        long jNextLong;
        C0188ub c0188ub;
        synchronized (this.o) {
            boolean z = true;
            Preconditions.checkState(this.m != null);
            if (this.B) {
                C0188ub.a(aVar, executor, e());
                return;
            }
            if (this.A != null) {
                c0188ub = this.A;
                jNextLong = 0;
                z = false;
            } else {
                jNextLong = this.g.nextLong();
                Stopwatch stopwatch = this.h.get();
                stopwatch.start();
                C0188ub c0188ub2 = new C0188ub(jNextLong, stopwatch);
                this.A = c0188ub2;
                this.T.f1162f++;
                c0188ub = c0188ub2;
            }
            if (z) {
                this.m.ping(false, (int) (jNextLong >>> 32), (int) jNextLong);
            }
            c0188ub.a(aVar, executor);
        }
    }

    public k[] b() {
        k[] kVarArr;
        synchronized (this.o) {
            kVarArr = (k[]) this.r.values().toArray(f1723c);
        }
        return kVarArr;
    }

    public final void b(k kVar) {
        if (!this.C) {
            this.C = true;
            Rb rb = this.L;
            if (rb != null) {
                rb.b();
            }
        }
        if (kVar.f1211d) {
            this.U.a(kVar, true);
        }
    }

    public boolean b(int i) {
        boolean z;
        synchronized (this.o) {
            z = true;
            if (i >= this.q || (i & 1) != 1) {
                z = false;
            }
        }
        return z;
    }

    @VisibleForTesting
    public String c() {
        URI uriA = C0149mb.a(this.f1725e);
        if (uriA.getHost() != null) {
            return uriA.getHost();
        }
        return this.f1725e;
    }

    @Override // c.a.b.O
    public k a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) throws Throwable {
        Preconditions.checkNotNull(c0223ga, FirebaseAnalytics.Param.METHOD);
        Preconditions.checkNotNull(c0219ea, "headers");
        C0086b c0086b = this.x;
        Ld ld = new Ld(abstractC0230nArr);
        for (AbstractC0230n abstractC0230n : abstractC0230nArr) {
            abstractC0230n.a(c0086b, c0219ea);
        }
        synchronized (this.o) {
            try {
                try {
                    return new k(c0223ga, c0219ea, this.m, this, this.n, this.o, this.u, this.i, this.f1725e, this.f1726f, ld, this.T, c0220f, this.S);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // c.a.b.Cc
    public Runnable a(Cc.a aVar) {
        Preconditions.checkNotNull(aVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.j = aVar;
        if (this.M) {
            this.K = (ScheduledExecutorService) Id.a(C0149mb.o);
            this.L = new Rb(new Rb.a(this), this.K, this.N, this.O, this.P);
            this.L.d();
        }
        if (this.f1724d == null) {
            synchronized (this.o) {
                this.m = new e(this, this.J, this.l);
                this.n = new z(this, this.m);
            }
            Ed ed = this.t;
            n nVar = new n(this);
            Queue<Runnable> queue = ed.f958d;
            Preconditions.checkNotNull(nVar, "'r' must not be null.");
            queue.add(nVar);
            ed.a(nVar);
            return null;
        }
        d dVar = new d(this.t, this);
        c.a.c.a.a.g gVar = new c.a.c.a.a.g();
        c.a.c.a.a.g.d dVar2 = new c.a.c.a.a.g.d(d.t.a(dVar), true);
        synchronized (this.o) {
            this.m = new e(this, dVar2, new s(Level.FINE, r.class));
            this.n = new z(this, this.m);
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Ed ed2 = this.t;
        p pVar = new p(this, countDownLatch, dVar, gVar);
        Queue<Runnable> queue2 = ed2.f958d;
        Preconditions.checkNotNull(pVar, "'r' must not be null.");
        queue2.add(pVar);
        ed2.a(pVar);
        try {
            g();
            countDownLatch.countDown();
            Ed ed3 = this.t;
            q qVar = new q(this);
            Queue<Runnable> queue3 = ed3.f958d;
            Preconditions.checkNotNull(qVar, "'r' must not be null.");
            queue3.add(qVar);
            ed3.a(qVar);
            return null;
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    public final Socket a(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, String str, String str2) throws Aa {
        Socket socketCreateSocket;
        try {
            if (inetSocketAddress2.getAddress() != null) {
                socketCreateSocket = this.D.createSocket(inetSocketAddress2.getAddress(), inetSocketAddress2.getPort());
            } else {
                socketCreateSocket = this.D.createSocket(inetSocketAddress2.getHostName(), inetSocketAddress2.getPort());
            }
            socketCreateSocket.setTcpNoDelay(true);
            C cB = d.t.b(socketCreateSocket);
            d.h hVarA = d.t.a(d.t.a(socketCreateSocket));
            b.g.a.g gVarA = a(inetSocketAddress, str, str2);
            b.g.a.d dVar = gVarA.f771a;
            hVarA.a(String.format("CONNECT %s:%d HTTP/1.1", dVar.f750a, Integer.valueOf(dVar.f751b))).a("\r\n");
            int length = gVarA.f773c.f747a.length / 2;
            for (int i = 0; i < length; i++) {
                hVarA.a(gVarA.f773c.a(i)).a(": ").a(gVarA.f773c.b(i)).a("\r\n");
            }
            hVarA.a("\r\n");
            hVarA.flush();
            b.g.a.a.a.a aVarA = b.g.a.a.a.a.a(a(cB));
            while (!a(cB).equals("")) {
            }
            if (aVarA.f745b >= 200 && aVarA.f745b < 300) {
                return socketCreateSocket;
            }
            d.g gVar = new d.g();
            try {
                socketCreateSocket.shutdownOutput();
                cB.read(gVar, 1024L);
            } catch (IOException e2) {
                gVar.a("Unable to read body: " + e2.toString());
            }
            try {
                socketCreateSocket.close();
            } catch (IOException unused) {
            }
            throw new Aa(za.l.b(String.format("Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", Integer.valueOf(aVarA.f745b), aVarA.f746c, gVar.o())));
        } catch (IOException e3) {
            throw za.l.b("Failed trying to connect with proxy").b(e3).a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0179  */
    /* JADX WARN: Code duplicated, block: B:111:0x0188  */
    /* JADX WARN: Code duplicated, block: B:117:0x019a  */
    /* JADX WARN: Code duplicated, block: B:120:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:143:0x01f9 A[PHI: r17
      0x01f9: PHI (r17v1 java.lang.String) = (r17v0 java.lang.String), (r17v0 java.lang.String), (r17v0 java.lang.String), (r17v6 java.lang.String) binds: [B:176:0x01f9, B:142:0x01f7, B:127:0x01d4, B:90:0x014d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:191:0x010b A[EDGE_INSN: B:191:0x010b->B:74:0x010b BREAK  A[LOOP:1: B:69:0x00f7->B:73:0x0105], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:194:0x017b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:198:0x0198 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:199:0x0194 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:201:0x018c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:204:0x0185 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:73:0x0105 A[LOOP:1: B:69:0x00f7->B:73:0x0105, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:84:0x0132  */
    /* JADX WARN: Code duplicated, block: B:87:0x0138  */
    /* JADX WARN: Code duplicated, block: B:88:0x0148  */
    /* JADX WARN: Code duplicated, block: B:92:0x0151  */
    /* JADX WARN: Code duplicated, block: B:94:0x015a  */
    /* JADX WARN: Code duplicated, block: B:97:0x0162  */
    /* JADX WARN: Code duplicated, block: B:99:0x0166  */
    public final b.g.a.g a(InetSocketAddress inetSocketAddress, String str, String str2) {
        String lowerCase;
        int i;
        int i2;
        int i3;
        int i4;
        InetAddress byAddress;
        byte[] address;
        int i5;
        int i6;
        int i7;
        int i8;
        d.g gVar;
        int i9;
        int i10;
        int i11;
        boolean z;
        int i12;
        int i13;
        int iA;
        b.g.a.d.a aVar = new b.g.a.d.a();
        aVar.f753a = "https";
        String hostName = inetSocketAddress.getHostName();
        if (hostName != null) {
            int i14 = 0;
            String strA = b.g.a.d.a(hostName, 0, hostName.length(), false);
            int i15 = 1;
            if (strA.startsWith("[") && strA.endsWith("]")) {
                int length = strA.length() - 1;
                byte[] bArr = new byte[16];
                int i16 = -1;
                int i17 = -1;
                int i18 = 0;
                while (true) {
                    try {
                        if (i15 < length) {
                            if (i14 != bArr.length) {
                                int i19 = i15 + 2;
                                if (i19 > length || !strA.regionMatches(i15, "::", i18, 2)) {
                                    if (i14 != 0) {
                                        if (strA.regionMatches(i15, Utils.APP_ID_IDENTIFICATION_SUBSTRING, i18, 1)) {
                                            i15++;
                                        } else if (strA.regionMatches(i15, ".", i18, 1)) {
                                            int i20 = i14 - 2;
                                            int i21 = i20;
                                            int i22 = i17;
                                            while (true) {
                                                if (i22 < length) {
                                                    if (i21 != bArr.length) {
                                                        if (i21 != i20) {
                                                            if (strA.charAt(i22) == '.') {
                                                                i22++;
                                                            }
                                                        }
                                                        int i23 = 0;
                                                        int i24 = i22;
                                                        while (true) {
                                                            if (i24 < length) {
                                                                i2 = i16;
                                                                char cCharAt = strA.charAt(i24);
                                                                hostName = hostName;
                                                                if (cCharAt >= '0' && cCharAt <= '9') {
                                                                    if ((i23 != 0 || i22 == i24) && (i23 = ((i23 * 10) + cCharAt) - 48) <= 255) {
                                                                        i24++;
                                                                        i16 = i2;
                                                                        hostName = hostName;
                                                                    }
                                                                }
                                                            } else {
                                                                hostName = hostName;
                                                                i2 = i16;
                                                            }
                                                            if (i24 - i22 != 0) {
                                                                bArr[i21] = (byte) i23;
                                                                i21++;
                                                                i22 = i24;
                                                                i16 = i2;
                                                                hostName = hostName;
                                                            }
                                                        }
                                                    }
                                                    hostName = hostName;
                                                    i2 = i16;
                                                } else {
                                                    hostName = hostName;
                                                    i2 = i16;
                                                    if (i21 == i20 + 4) {
                                                        z = true;
                                                    }
                                                    if (!z) {
                                                        i14 += 2;
                                                    }
                                                    if (byAddress == null) {
                                                        lowerCase = null;
                                                    } else {
                                                        address = byAddress.getAddress();
                                                        if (address.length == 16) {
                                                            i5 = i4;
                                                            i6 = 16;
                                                            i7 = -1;
                                                            i8 = i5;
                                                            while (i8 < address.length) {
                                                                i10 = i8;
                                                                for (i9 = i6; i10 < i9 && address[i10] == 0 && address[i10 + 1] == 0; i9 = 16) {
                                                                    i10 += 2;
                                                                }
                                                                i11 = i10 - i8;
                                                                if (i11 > i5) {
                                                                    i7 = i8;
                                                                    i5 = i11;
                                                                }
                                                                i8 = i10 + 2;
                                                                i6 = 16;
                                                            }
                                                            gVar = new d.g();
                                                            while (i4 < address.length) {
                                                                if (i4 == i7) {
                                                                    gVar.writeByte(58);
                                                                    i4 += i5;
                                                                    if (i4 == 16) {
                                                                        gVar.writeByte(58);
                                                                    }
                                                                } else {
                                                                    if (i4 > 0) {
                                                                        gVar.writeByte(58);
                                                                    }
                                                                    gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                                                    i4 += 2;
                                                                }
                                                            }
                                                            lowerCase = gVar.o();
                                                        } else {
                                                            throw new AssertionError();
                                                        }
                                                    }
                                                }
                                                z = false;
                                                if (!z) {
                                                    i14 += 2;
                                                }
                                                if (byAddress == null) {
                                                    lowerCase = null;
                                                } else {
                                                    address = byAddress.getAddress();
                                                    if (address.length == 16) {
                                                        i5 = i4;
                                                        i6 = 16;
                                                        i7 = -1;
                                                        i8 = i5;
                                                        while (i8 < address.length) {
                                                            i10 = i8;
                                                            while (i10 < i9) {
                                                                i10 += 2;
                                                            }
                                                            i11 = i10 - i8;
                                                            if (i11 > i5) {
                                                                i7 = i8;
                                                                i5 = i11;
                                                            }
                                                            i8 = i10 + 2;
                                                            i6 = 16;
                                                        }
                                                        gVar = new d.g();
                                                        while (i4 < address.length) {
                                                            if (i4 == i7) {
                                                                gVar.writeByte(58);
                                                                i4 += i5;
                                                                if (i4 == 16) {
                                                                    gVar.writeByte(58);
                                                                }
                                                            } else {
                                                                if (i4 > 0) {
                                                                    gVar.writeByte(58);
                                                                }
                                                                gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                                                i4 += 2;
                                                            }
                                                        }
                                                        lowerCase = gVar.o();
                                                    } else {
                                                        throw new AssertionError();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i17 = i15;
                                    i16 = i16;
                                    i12 = 0;
                                    i15 = i17;
                                    while (i15 < length) {
                                        iA = b.g.a.d.a(strA.charAt(i15));
                                        if (iA == -1) {
                                            break;
                                        }
                                        i12 = (i12 << 4) + iA;
                                        i15++;
                                    }
                                    i13 = i15 - i17;
                                    if (i13 == 0 && i13 <= 4) {
                                        int i25 = i14 + 1;
                                        bArr[i14] = (byte) ((i12 >>> 8) & 255);
                                        i14 = i25 + 1;
                                        bArr[i25] = (byte) (i12 & 255);
                                        i18 = 0;
                                        hostName = hostName;
                                    }
                                } else if (i16 == -1) {
                                    i16 = i14 + 2;
                                    if (i19 == length) {
                                        hostName = hostName;
                                        i3 = i16;
                                        if (i16 != bArr.length) {
                                            if (i3 != -1) {
                                                int i26 = i16 - i3;
                                                System.arraycopy(bArr, i3, bArr, bArr.length - i26, i26);
                                                Arrays.fill(bArr, i3, (bArr.length - i16) + i3, (byte) 0);
                                                i4 = 0;
                                            }
                                            if (byAddress == null) {
                                                lowerCase = null;
                                            } else {
                                                address = byAddress.getAddress();
                                                if (address.length == 16) {
                                                    i5 = i4;
                                                    i6 = 16;
                                                    i7 = -1;
                                                    i8 = i5;
                                                    while (i8 < address.length) {
                                                        i10 = i8;
                                                        while (i10 < i9) {
                                                            i10 += 2;
                                                        }
                                                        i11 = i10 - i8;
                                                        if (i11 > i5) {
                                                            i7 = i8;
                                                            i5 = i11;
                                                        }
                                                        i8 = i10 + 2;
                                                        i6 = 16;
                                                    }
                                                    gVar = new d.g();
                                                    while (i4 < address.length) {
                                                        if (i4 == i7) {
                                                            gVar.writeByte(58);
                                                            i4 += i5;
                                                            if (i4 == 16) {
                                                                gVar.writeByte(58);
                                                            }
                                                        } else {
                                                            if (i4 > 0) {
                                                                gVar.writeByte(58);
                                                            }
                                                            gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                                            i4 += 2;
                                                        }
                                                    }
                                                    lowerCase = gVar.o();
                                                } else {
                                                    throw new AssertionError();
                                                }
                                            }
                                        } else {
                                            i4 = 0;
                                        }
                                        byAddress = InetAddress.getByAddress(bArr);
                                        if (byAddress == null) {
                                            lowerCase = null;
                                        } else {
                                            address = byAddress.getAddress();
                                            if (address.length == 16) {
                                                i5 = i4;
                                                i6 = 16;
                                                i7 = -1;
                                                i8 = i5;
                                                while (i8 < address.length) {
                                                    i10 = i8;
                                                    while (i10 < i9) {
                                                        i10 += 2;
                                                    }
                                                    i11 = i10 - i8;
                                                    if (i11 > i5) {
                                                        i7 = i8;
                                                        i5 = i11;
                                                    }
                                                    i8 = i10 + 2;
                                                    i6 = 16;
                                                }
                                                gVar = new d.g();
                                                while (i4 < address.length) {
                                                    if (i4 == i7) {
                                                        gVar.writeByte(58);
                                                        i4 += i5;
                                                        if (i4 == 16) {
                                                            gVar.writeByte(58);
                                                        }
                                                    } else {
                                                        if (i4 > 0) {
                                                            gVar.writeByte(58);
                                                        }
                                                        gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                                        i4 += 2;
                                                    }
                                                }
                                                lowerCase = gVar.o();
                                            } else {
                                                throw new AssertionError();
                                            }
                                        }
                                    } else {
                                        i14 = i16;
                                        i17 = i19;
                                        i12 = 0;
                                        i15 = i17;
                                        while (i15 < length) {
                                            iA = b.g.a.d.a(strA.charAt(i15));
                                            if (iA == -1) {
                                                break;
                                                break;
                                            }
                                            i12 = (i12 << 4) + iA;
                                            i15++;
                                        }
                                        i13 = i15 - i17;
                                        if (i13 == 0) {
                                        }
                                    }
                                }
                                i4 = 0;
                                byAddress = null;
                                if (byAddress == null) {
                                    lowerCase = null;
                                } else {
                                    address = byAddress.getAddress();
                                    if (address.length == 16) {
                                        i5 = i4;
                                        i6 = 16;
                                        i7 = -1;
                                        i8 = i5;
                                        while (i8 < address.length) {
                                            i10 = i8;
                                            while (i10 < i9) {
                                                i10 += 2;
                                            }
                                            i11 = i10 - i8;
                                            if (i11 > i5) {
                                                i7 = i8;
                                                i5 = i11;
                                            }
                                            i8 = i10 + 2;
                                            i6 = 16;
                                        }
                                        gVar = new d.g();
                                        while (i4 < address.length) {
                                            if (i4 == i7) {
                                                gVar.writeByte(58);
                                                i4 += i5;
                                                if (i4 == 16) {
                                                    gVar.writeByte(58);
                                                }
                                            } else {
                                                if (i4 > 0) {
                                                    gVar.writeByte(58);
                                                }
                                                gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                                i4 += 2;
                                            }
                                        }
                                        lowerCase = gVar.o();
                                    } else {
                                        throw new AssertionError();
                                    }
                                }
                            }
                            hostName = hostName;
                            i4 = 0;
                            byAddress = null;
                            if (byAddress == null) {
                                lowerCase = null;
                            } else {
                                address = byAddress.getAddress();
                                if (address.length == 16) {
                                    i5 = i4;
                                    i6 = 16;
                                    i7 = -1;
                                    i8 = i5;
                                    while (i8 < address.length) {
                                        i10 = i8;
                                        while (i10 < i9) {
                                            i10 += 2;
                                        }
                                        i11 = i10 - i8;
                                        if (i11 > i5) {
                                            i7 = i8;
                                            i5 = i11;
                                        }
                                        i8 = i10 + 2;
                                        i6 = 16;
                                    }
                                    gVar = new d.g();
                                    while (i4 < address.length) {
                                        if (i4 == i7) {
                                            gVar.writeByte(58);
                                            i4 += i5;
                                            if (i4 == 16) {
                                                gVar.writeByte(58);
                                            }
                                        } else {
                                            if (i4 > 0) {
                                                gVar.writeByte(58);
                                            }
                                            gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                            i4 += 2;
                                        }
                                    }
                                    lowerCase = gVar.o();
                                } else {
                                    throw new AssertionError();
                                }
                            }
                        } else {
                            hostName = hostName;
                            i2 = i16;
                        }
                        if (i16 != bArr.length) {
                            if (i3 != -1) {
                                i4 = 0;
                                byAddress = null;
                            } else {
                                int i27 = i16 - i3;
                                System.arraycopy(bArr, i3, bArr, bArr.length - i27, i27);
                                Arrays.fill(bArr, i3, (bArr.length - i16) + i3, (byte) 0);
                                i4 = 0;
                            }
                            if (byAddress == null) {
                                lowerCase = null;
                            } else {
                                address = byAddress.getAddress();
                                if (address.length == 16) {
                                    i5 = i4;
                                    i6 = 16;
                                    i7 = -1;
                                    i8 = i5;
                                    while (i8 < address.length) {
                                        i10 = i8;
                                        while (i10 < i9) {
                                            i10 += 2;
                                        }
                                        i11 = i10 - i8;
                                        if (i11 > i5) {
                                            i7 = i8;
                                            i5 = i11;
                                        }
                                        i8 = i10 + 2;
                                        i6 = 16;
                                    }
                                    gVar = new d.g();
                                    while (i4 < address.length) {
                                        if (i4 == i7) {
                                            gVar.writeByte(58);
                                            i4 += i5;
                                            if (i4 == 16) {
                                                gVar.writeByte(58);
                                            }
                                        } else {
                                            if (i4 > 0) {
                                                gVar.writeByte(58);
                                            }
                                            gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                            i4 += 2;
                                        }
                                    }
                                    lowerCase = gVar.o();
                                } else {
                                    throw new AssertionError();
                                }
                            }
                        } else {
                            i4 = 0;
                        }
                        byAddress = InetAddress.getByAddress(bArr);
                        if (byAddress == null) {
                            lowerCase = null;
                        } else {
                            address = byAddress.getAddress();
                            if (address.length == 16) {
                                i5 = i4;
                                i6 = 16;
                                i7 = -1;
                                i8 = i5;
                                while (i8 < address.length) {
                                    i10 = i8;
                                    while (i10 < i9) {
                                        i10 += 2;
                                    }
                                    i11 = i10 - i8;
                                    if (i11 > i5) {
                                        i7 = i8;
                                        i5 = i11;
                                    }
                                    i8 = i10 + 2;
                                    i6 = 16;
                                }
                                gVar = new d.g();
                                while (i4 < address.length) {
                                    if (i4 == i7) {
                                        gVar.writeByte(58);
                                        i4 += i5;
                                        if (i4 == 16) {
                                            gVar.writeByte(58);
                                        }
                                    } else {
                                        if (i4 > 0) {
                                            gVar.writeByte(58);
                                        }
                                        gVar.f(((address[i4] & 255) << 8) | (address[i4 + 1] & 255));
                                        i4 += 2;
                                    }
                                }
                                lowerCase = gVar.o();
                            } else {
                                throw new AssertionError();
                            }
                        }
                    } catch (UnknownHostException unused) {
                        throw new AssertionError();
                    }
                    i16 = i14;
                    i3 = i2;
                }
            } else {
                hostName = hostName;
                try {
                    lowerCase = IDN.toASCII(strA).toLowerCase(Locale.US);
                    if (!lowerCase.isEmpty()) {
                        for (0; i < lowerCase.length(); i + 1) {
                            char cCharAt2 = lowerCase.charAt(i);
                            i = (cCharAt2 > 31 && cCharAt2 < 127 && " #%/:?@[\\]".indexOf(cCharAt2) == -1) ? i + 1 : 0;
                            i14 = 1;
                        }
                        if (i14 != 0) {
                        }
                    }
                } catch (IllegalArgumentException unused2) {
                }
                lowerCase = null;
            }
            if (lowerCase != null) {
                aVar.f756d = lowerCase;
                int port = inetSocketAddress.getPort();
                if (port > 0 && port <= 65535) {
                    aVar.f757e = port;
                    if (aVar.f753a != null) {
                        if (aVar.f756d != null) {
                            b.g.a.d dVar = new b.g.a.d(aVar, null);
                            b.g.a.g.a aVar2 = new b.g.a.g.a();
                            aVar2.f775a = dVar;
                            aVar2.f777c.a(HttpHeaders.HOST, dVar.f750a + Utils.APP_ID_IDENTIFICATION_SUBSTRING + dVar.f751b);
                            aVar2.f777c.a(HttpHeaders.USER_AGENT, this.f1726f);
                            if (str != null && str2 != null) {
                                try {
                                    aVar2.f777c.a(HttpHeaders.PROXY_AUTHORIZATION, "Basic " + d.j.a((str + Utils.APP_ID_IDENTIFICATION_SUBSTRING + str2).getBytes("ISO-8859-1")).a());
                                } catch (UnsupportedEncodingException unused3) {
                                    throw new AssertionError();
                                }
                            }
                            if (aVar2.f775a != null) {
                                return new b.g.a.g(aVar2, null);
                            }
                            throw new IllegalStateException("url == null");
                        }
                        throw new IllegalStateException("host == null");
                    }
                    throw new IllegalStateException("scheme == null");
                }
                throw new IllegalArgumentException(b.a.a.a.a.a("unexpected port: ", port));
            }
            throw new IllegalArgumentException(b.a.a.a.a.a("unexpected host: ", hostName));
        }
        throw new IllegalArgumentException("host == null");
    }

    public static String a(C c2) throws EOFException {
        d.g gVar = new d.g();
        while (c2.read(gVar, 1L) != -1) {
            if (gVar.g(gVar.f2102c - 1) == 10) {
                long jA = gVar.a((byte) 10, 0L, UnsignedLong.UNSIGNED_MASK);
                if (jA != -1) {
                    return gVar.i(jA);
                }
                if (UnsignedLong.UNSIGNED_MASK < gVar.f2102c && gVar.g(9223372036854775806L) == 13 && gVar.g(UnsignedLong.UNSIGNED_MASK) == 10) {
                    return gVar.i(UnsignedLong.UNSIGNED_MASK);
                }
                d.g gVar2 = new d.g();
                gVar.a(gVar2, 0L, Math.min(32L, gVar.f2102c));
                StringBuilder sbA = b.a.a.a.a.a("\\n not found: limit=");
                sbA.append(Math.min(gVar.f2102c, UnsignedLong.UNSIGNED_MASK));
                sbA.append(" content=");
                sbA.append(gVar2.n().b());
                sbA.append((char) 8230);
                throw new EOFException(sbA.toString());
            }
        }
        StringBuilder sbA2 = b.a.a.a.a.a("\\n not found: ");
        sbA2.append(gVar.n().b());
        throw new EOFException(sbA2.toString());
    }

    @Override // c.a.N
    public O a() {
        return this.p;
    }

    @Override // c.a.b.Cc
    public void a(za zaVar) {
        b(zaVar);
        synchronized (this.o) {
            Iterator<Map.Entry<Integer, k>> it = this.r.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, k> next = it.next();
                it.remove();
                next.getValue().o.a(zaVar, false, new C0219ea());
                a(next.getValue());
            }
            for (k kVar : this.H) {
                kVar.o.a(zaVar, true, new C0219ea());
                a(kVar);
            }
            this.H.clear();
            i();
        }
    }

    public void a(Throwable th) {
        Preconditions.checkNotNull(th, "failureCause");
        a(0, c.a.c.a.a.a.INTERNAL_ERROR, za.l.b(th));
    }

    public final void a(c.a.c.a.a.a aVar, String str) {
        a(0, aVar, a(aVar).a(str));
    }

    public final void a(int i, c.a.c.a.a.a aVar, za zaVar) {
        synchronized (this.o) {
            if (this.y == null) {
                this.y = zaVar;
                this.j.a(zaVar);
            }
            if (aVar != null && !this.z) {
                this.z = true;
                this.m.a(0, aVar, new byte[0]);
            }
            Iterator<Map.Entry<Integer, k>> it = this.r.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, k> next = it.next();
                if (next.getKey().intValue() > i) {
                    it.remove();
                    next.getValue().o.a(zaVar, N.a.REFUSED, false, new C0219ea());
                    a(next.getValue());
                }
            }
            for (k kVar : this.H) {
                kVar.o.a(zaVar, N.a.REFUSED, true, new C0219ea());
                a(kVar);
            }
            this.H.clear();
            i();
        }
    }

    public void a(int i, za zaVar, N.a aVar, boolean z, c.a.c.a.a.a aVar2, C0219ea c0219ea) {
        synchronized (this.o) {
            k kVarRemove = this.r.remove(Integer.valueOf(i));
            if (kVarRemove != null) {
                if (aVar2 != null) {
                    this.m.a(i, c.a.c.a.a.a.CANCEL);
                }
                if (zaVar != null) {
                    k.b bVar = kVarRemove.o;
                    if (c0219ea == null) {
                        c0219ea = new C0219ea();
                    }
                    bVar.a(zaVar, aVar, z, c0219ea);
                }
                if (!h()) {
                    i();
                    a(kVarRemove);
                }
            }
        }
    }

    public final void a(k kVar) {
        if (this.C && this.H.isEmpty() && this.r.isEmpty()) {
            this.C = false;
            Rb rb = this.L;
            if (rb != null) {
                rb.c();
            }
        }
        if (kVar.f1211d) {
            this.U.a(kVar, false);
        }
    }

    public k a(int i) {
        k kVar;
        synchronized (this.o) {
            kVar = this.r.get(Integer.valueOf(i));
        }
        return kVar;
    }

    @VisibleForTesting
    public static za a(c.a.c.a.a.a aVar) {
        za zaVar = f1721a.get(aVar);
        if (zaVar != null) {
            return zaVar;
        }
        za zaVar2 = za.f1938e;
        StringBuilder sbA = b.a.a.a.a.a("Unknown http2 error code: ");
        sbA.append(aVar.t);
        return zaVar2.b(sbA.toString());
    }
}

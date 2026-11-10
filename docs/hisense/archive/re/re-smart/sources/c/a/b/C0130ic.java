package c.a.b;

import c.a.AbstractC0222g;
import c.a.AbstractC0224h;
import c.a.AbstractC0225i;
import c.a.AbstractC0226j;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0229m;
import c.a.C0236u;
import c.a.C0240y;
import c.a.EnumC0234s;
import c.a.InterfaceC0227k;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import java.lang.reflect.Array;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: c.a.b.ic, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0130ic extends c.a.X implements c.a.N<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @VisibleForTesting
    public static final Logger f1271a = Logger.getLogger(C0130ic.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @VisibleForTesting
    public static final Pattern f1272b = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @VisibleForTesting
    public static final c.a.za f1273c = c.a.za.l.b("Channel shutdownNow invoked");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @VisibleForTesting
    public static final c.a.za f1274d = c.a.za.l.b("Channel shutdown invoked");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @VisibleForTesting
    public static final c.a.za f1275e = c.a.za.l.b("Subchannel shutdown invoked");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Bc f1276f = new Bc(null, new HashMap(), new HashMap(), null, null, null);
    public static final c.a.M g = new Ub();
    public static final AbstractC0226j<Object, Object> h = new C0115fc();
    public final Supplier<Stopwatch> A;
    public final long B;
    public final InterfaceC0196w.a D;
    public final AbstractC0222g E;
    public final String F;
    public c.a.ia G;
    public boolean H;
    public h I;
    public volatile c.a.U.h J;
    public boolean K;
    public Collection<j.a<?, ?>> M;
    public final C0182ta P;
    public final n Q;
    public boolean S;
    public boolean T;
    public volatile boolean U;
    public final A.a W;
    public final A X;
    public final D Y;
    public final AbstractC0225i Z;
    public final c.a.K aa;
    public final j ba;
    public Bc da;
    public final Bc ea;
    public boolean fa;
    public final boolean ga;
    public final c.a.O i;
    public final long ia;
    public final String j;
    public final long ja;
    public final String k;
    public final boolean ka;
    public final c.a.ia.c l;
    public final Cc.a la;
    public final c.a.ia.a m;

    @VisibleForTesting
    public final AbstractC0193vb<Object> ma;
    public final C0191v n;
    public c.a.Ea.b na;
    public final P o;
    public InterfaceC0196w oa;
    public final l p;
    public final L.b pa;
    public final Executor q;
    public final Yc qa;
    public final Jc<? extends Executor> r;
    public final e s;
    public final e t;
    public final Pd u;
    public final int v;
    public boolean x;
    public final C0240y y;
    public final c.a.r z;

    @VisibleForTesting
    public final c.a.Ea w = new c.a.Ea(new C0100cc(this));
    public final Y C = new Y();
    public final Set<Ib> L = new HashSet(16, 0.75f);
    public final Object N = new Object();
    public final Set<Kc> O = new HashSet(1, 0.75f);
    public final AtomicBoolean R = new AtomicBoolean(false);
    public final CountDownLatch V = new CountDownLatch(1);
    public k ca = k.NO_RESOLUTION;
    public final AbstractC0171qd.c ha = new AbstractC0171qd.c();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$a */
    final class a implements L.b {
        public /* synthetic */ a(Ub ub) {
        }

        public final O a(c.a.U.e eVar) {
            c.a.U.h hVar = C0130ic.this.J;
            if (C0130ic.this.R.get()) {
                return C0130ic.this.P;
            }
            if (hVar != null) {
                O oA = C0149mb.a(hVar.a(eVar), ((Pc) eVar).f1117a.a());
                return oA != null ? oA : C0130ic.this.P;
            }
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0120gc runnableC0120gc = new RunnableC0120gc(this);
            b.a.a.a.a.a(runnableC0120gc, "runnable is null", ea.f796b, runnableC0120gc, ea);
            return C0130ic.this.P;
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$b */
    static final class b<ReqT, RespT> extends c.a.D<ReqT, RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.M f1278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AbstractC0222g f1279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Executor f1280c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0223ga<ReqT, RespT> f1281d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final C0236u f1282e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public C0220f f1283f;
        public AbstractC0226j<ReqT, RespT> g;

        public b(c.a.M m, AbstractC0222g abstractC0222g, Executor executor, C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
            this.f1278a = m;
            this.f1279b = abstractC0222g;
            this.f1281d = c0223ga;
            this.f1280c = c0220f.f1814c != null ? c0220f.f1814c : executor;
            this.f1283f = c0220f.a(this.f1280c);
            this.f1282e = C0236u.c();
        }

        @Override // c.a.D, c.a.ma, c.a.AbstractC0226j
        public void cancel(String str, Throwable th) {
            AbstractC0226j<ReqT, RespT> abstractC0226j = this.g;
            if (abstractC0226j != null) {
                abstractC0226j.cancel(str, th);
            }
        }

        @Override // c.a.D, c.a.ma
        public AbstractC0226j<ReqT, RespT> delegate() {
            return this.g;
        }

        @Override // c.a.D, c.a.AbstractC0226j
        public void start(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
            c.a.M.a aVarA = this.f1278a.a(new Pc(this.f1281d, c0219ea, this.f1283f));
            c.a.za zaVar = aVarA.f833a;
            if (!zaVar.c()) {
                this.f1280c.execute(new C0135jc(this, aVar, zaVar));
                this.g = (AbstractC0226j<ReqT, RespT>) C0130ic.h;
                return;
            }
            InterfaceC0227k interfaceC0227k = aVarA.f835c;
            Bc.a aVarA2 = ((Bc) aVarA.f834b).a(this.f1281d);
            if (aVarA2 != null) {
                this.f1283f = this.f1283f.a(Bc.a.f907a, aVarA2);
            }
            if (interfaceC0227k != null) {
                this.g = interfaceC0227k.a(this.f1281d, this.f1283f, this.f1279b);
            } else {
                this.g = this.f1279b.a(this.f1281d, this.f1283f);
            }
            this.g.start(aVar, c0219ea);
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$c */
    @VisibleForTesting
    class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0130ic.this.na = null;
            C0130ic.b(C0130ic.this);
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$d */
    private final class d implements Cc.a {
        public /* synthetic */ d(Ub ub) {
        }

        @Override // c.a.b.Cc.a
        public void a() {
        }

        @Override // c.a.b.Cc.a
        public void a(c.a.za zaVar) {
            Preconditions.checkState(C0130ic.this.R.get(), "Channel must have been shut down");
        }

        @Override // c.a.b.Cc.a
        public void b() {
            Preconditions.checkState(C0130ic.this.R.get(), "Channel must have been shut down");
            C0130ic.this.T = true;
            C0130ic.this.c(false);
            C0130ic.q(C0130ic.this);
            C0130ic.J(C0130ic.this);
        }

        @Override // c.a.b.Cc.a
        public void a(boolean z) {
            C0130ic c0130ic = C0130ic.this;
            c0130ic.ma.a(c0130ic.P, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$e */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Jc<? extends Executor> f1286a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Executor f1287b;

        public e(Jc<? extends Executor> jc) {
            Preconditions.checkNotNull(jc, "executorPool");
            this.f1286a = jc;
        }

        public synchronized Executor a() {
            if (this.f1287b == null) {
                Object objA = Id.a(((Jd) this.f1286a).f1038a);
                Preconditions.checkNotNull(objA, "%s.getObject()", this.f1287b);
                this.f1287b = (Executor) objA;
            }
            return this.f1287b;
        }

        public synchronized void b() {
            if (this.f1287b != null) {
                Jc<? extends Executor> jc = this.f1286a;
                Id.a((Id.b<Executor>) ((Jd) jc).f1038a, this.f1287b);
                this.f1287b = null;
            }
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$f */
    private final class f extends AbstractC0193vb<Object> {
        public /* synthetic */ f(Ub ub) {
        }

        @Override // c.a.b.AbstractC0193vb
        public void a() {
            C0130ic.this.g();
        }

        @Override // c.a.b.AbstractC0193vb
        public void b() {
            if (C0130ic.this.R.get()) {
                return;
            }
            C0130ic.V(C0130ic.this);
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$g */
    private class g implements Runnable {
        public /* synthetic */ g(Ub ub) {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0130ic.W(C0130ic.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$j */
    class j extends AbstractC0222g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1298b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicReference<c.a.M> f1297a = new AtomicReference<>(C0130ic.g);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final AbstractC0222g f1299c = new C0160oc(this);

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c.a.b.ic$j$a */
        final class a<ReqT, RespT> extends C0138ka<ReqT, RespT> {
            public final C0236u l;
            public final C0223ga<ReqT, RespT> m;
            public final C0220f n;

            /* JADX INFO: renamed from: c.a.b.ic$j$a$a, reason: collision with other inner class name */
            final class RunnableC0009a implements Runnable {
                public RunnableC0009a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (C0130ic.this.M != null) {
                        C0130ic.this.M.remove(a.this);
                        if (C0130ic.this.M.isEmpty()) {
                            C0130ic c0130ic = C0130ic.this;
                            c0130ic.ma.a(c0130ic.N, false);
                            C0130ic.this.M = null;
                            if (C0130ic.this.R.get()) {
                                C0130ic.this.Q.a(C0130ic.f1274d);
                            }
                        }
                    }
                }
            }

            public a(C0236u c0236u, C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
                super(C0130ic.this.a(c0220f), C0130ic.this.p, c0220f.f1813b);
                this.l = c0236u;
                this.m = c0223ga;
                this.n = c0220f;
            }

            @Override // c.a.b.C0138ka
            public void a() {
                c.a.Ea ea = C0130ic.this.w;
                RunnableC0009a runnableC0009a = new RunnableC0009a();
                b.a.a.a.a.a(runnableC0009a, "runnable is null", ea.f796b, runnableC0009a, ea);
            }
        }

        public /* synthetic */ j(String str, Ub ub) {
            Preconditions.checkNotNull(str, "authority");
            this.f1298b = str;
        }

        @Override // c.a.AbstractC0222g
        public <ReqT, RespT> AbstractC0226j<ReqT, RespT> a(C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
            if (this.f1297a.get() != C0130ic.g) {
                return b(c0223ga, c0220f);
            }
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0174rc runnableC0174rc = new RunnableC0174rc(this);
            b.a.a.a.a.a(runnableC0174rc, "runnable is null", ea.f796b, runnableC0174rc, ea);
            if (this.f1297a.get() != C0130ic.g) {
                return b(c0223ga, c0220f);
            }
            if (C0130ic.this.R.get()) {
                return new C0179sc(this);
            }
            a aVar = new a(C0236u.c(), c0223ga, c0220f);
            c.a.Ea ea2 = C0130ic.this.w;
            RunnableC0184tc runnableC0184tc = new RunnableC0184tc(this, aVar);
            b.a.a.a.a.a(runnableC0184tc, "runnable is null", ea2.f796b, runnableC0184tc, ea2);
            return aVar;
        }

        @Override // c.a.AbstractC0222g
        public String b() {
            return this.f1298b;
        }

        public final <ReqT, RespT> AbstractC0226j<ReqT, RespT> b(C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
            c.a.M m = this.f1297a.get();
            if (m == null) {
                return this.f1299c.a(c0223ga, c0220f);
            }
            if (!(m instanceof Bc.b)) {
                return new b(m, this.f1299c, C0130ic.this.q, c0223ga, c0220f);
            }
            Bc.a aVarA = ((Bc.b) m).f913b.a(c0223ga);
            if (aVarA != null) {
                c0220f = c0220f.a(Bc.a.f907a, aVarA);
            }
            return this.f1299c.a(c0223ga, c0220f);
        }

        public void a(c.a.M m) {
            c.a.M m2 = this.f1297a.get();
            this.f1297a.set(m);
            if (m2 != C0130ic.g || C0130ic.this.M == null) {
                return;
            }
            for (a aVar : C0130ic.this.M) {
                C0130ic.this.a(aVar.n).execute(new RunnableC0189uc(aVar));
            }
        }
    }

    /* JADX INFO: renamed from: c.a.b.ic$k */
    enum k {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    /* JADX INFO: renamed from: c.a.b.ic$l */
    private static final class l implements ScheduledExecutorService {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ScheduledExecutorService f1306a;

        public /* synthetic */ l(ScheduledExecutorService scheduledExecutorService, Ub ub) {
            Preconditions.checkNotNull(scheduledExecutorService, "delegate");
            this.f1306a = scheduledExecutorService;
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j, TimeUnit timeUnit) {
            return this.f1306a.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f1306a.execute(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
            return this.f1306a.invokeAll(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
            return (T) this.f1306a.invokeAny(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return this.f1306a.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return this.f1306a.isTerminated();
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.f1306a.schedule(callable, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.f1306a.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.f1306a.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Callable<T> callable) {
            return this.f1306a.submit(callable);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
            return this.f1306a.invokeAll(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
            return (T) this.f1306a.invokeAny(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f1306a.schedule(runnable, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public Future<?> submit(Runnable runnable) {
            return this.f1306a.submit(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.f1306a.submit(runnable, t);
        }
    }

    public C0130ic(C0207yc c0207yc, P p, InterfaceC0196w.a aVar, Jc<? extends Executor> jc, Supplier<Stopwatch> supplier, List<InterfaceC0227k> list, Pd pd) {
        Ub ub = null;
        this.Q = new n(ub);
        this.da = f1276f;
        this.fa = false;
        this.la = new d(ub);
        this.ma = new f(ub);
        this.pa = new a(ub);
        String str = c0207yc.l;
        Preconditions.checkNotNull(str, "target");
        this.j = str;
        this.i = c.a.O.a("Channel", this.j);
        Preconditions.checkNotNull(pd, "timeProvider");
        this.u = pd;
        Jc<? extends Executor> jc2 = c0207yc.g;
        Preconditions.checkNotNull(jc2, "executorPool");
        this.r = jc2;
        Object objA = Id.a(((Jd) this.r).f1038a);
        Preconditions.checkNotNull(objA, "executor");
        this.q = (Executor) objA;
        AbstractC0224h abstractC0224h = c0207yc.m;
        this.o = new C0208z(p, c0207yc.n, this.q);
        new C0208z(p, null, this.q);
        this.p = new l(this.o.n(), ub);
        int i2 = c0207yc.B;
        this.v = i2;
        this.Y = new D(this.i, i2, ((Od) pd).a(), b.a.a.a.a.a(b.a.a.a.a.a("Channel for '"), this.j, "'"));
        this.Z = new B(this.Y, pd);
        c.a.oa oaVar = c0207yc.F;
        c.a.oa oaVar2 = oaVar == null ? C0149mb.k : oaVar;
        this.ka = c0207yc.z;
        this.n = new C0191v(c0207yc.q);
        Jc<? extends Executor> jc3 = c0207yc.h;
        Preconditions.checkNotNull(jc3, "offloadExecutorPool");
        this.t = new e(jc3);
        c.a.la laVar = c0207yc.j;
        Bd bd = new Bd(this.ka, c0207yc.v, c0207yc.w, this.n);
        Integer numValueOf = Integer.valueOf(c0207yc.M.a());
        if (oaVar2 == null) {
            throw new NullPointerException();
        }
        c.a.Ea ea = this.w;
        if (ea == null) {
            throw new NullPointerException();
        }
        l lVar = this.p;
        if (lVar == null) {
            throw new NullPointerException();
        }
        AbstractC0225i abstractC0225i = this.Z;
        if (abstractC0225i == null) {
            throw new NullPointerException();
        }
        this.m = new c.a.ia.a(numValueOf, oaVar2, ea, bd, lVar, abstractC0225i, new ExecutorC0105dc(this), null);
        this.k = c0207yc.p;
        this.l = c0207yc.k;
        this.G = a(this.j, this.k, this.l, this.m);
        Preconditions.checkNotNull(jc, "balancerRpcExecutorPool");
        this.s = new e(jc);
        this.P = new C0182ta(this.q, this.w);
        C0182ta c0182ta = this.P;
        Cc.a aVar2 = this.la;
        c0182ta.h = aVar2;
        c0182ta.f1468e = new RunnableC0163pa(c0182ta, aVar2);
        c0182ta.f1469f = new RunnableC0168qa(c0182ta, aVar2);
        c0182ta.g = new RunnableC0172ra(c0182ta, aVar2);
        this.D = aVar;
        Map<String, ?> map = c0207yc.C;
        if (map != null) {
            c.a.ia.b bVarA = bd.a(map);
            Preconditions.checkState(bVarA.f1861a == null, "Default config is invalid: %s", bVarA.f1861a);
            this.ea = (Bc) bVarA.f1862b;
            this.da = this.ea;
        } else {
            this.ea = null;
        }
        this.ga = c0207yc.D;
        this.ba = new j(this.G.a(), ub);
        j jVar = this.ba;
        if (c0207yc.E != null) {
            throw null;
        }
        this.E = C0229m.a(jVar, list);
        Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        this.A = supplier;
        long j2 = c0207yc.u;
        if (j2 == -1) {
            this.B = j2;
        } else {
            Preconditions.checkArgument(j2 >= C0207yc.f1519c, "invalid idleTimeoutMillis %s", c0207yc.u);
            this.B = c0207yc.u;
        }
        this.qa = new Yc(new g(ub), this.w, this.o.n(), supplier.get());
        this.x = c0207yc.r;
        C0240y c0240y = c0207yc.s;
        Preconditions.checkNotNull(c0240y, "decompressorRegistry");
        this.y = c0240y;
        c.a.r rVar = c0207yc.t;
        Preconditions.checkNotNull(rVar, "compressorRegistry");
        this.z = rVar;
        this.F = c0207yc.o;
        this.ja = c0207yc.x;
        this.ia = c0207yc.y;
        this.W = new Wb(this, pd);
        this.X = this.W.a();
        c.a.K k2 = c0207yc.A;
        Preconditions.checkNotNull(k2);
        this.aa = k2;
        c.a.K.a(this.aa.f829c, this);
        if (this.ga) {
            return;
        }
        if (this.ea != null) {
            this.Z.a(AbstractC0225i.a.INFO, "Service config look-up disabled, using default service config");
        }
        this.fa = true;
    }

    public static /* synthetic */ void D(C0130ic c0130ic) {
        c0130ic.w.b();
        c0130ic.f();
        c0130ic.h();
    }

    public static /* synthetic */ void J(C0130ic c0130ic) {
        if (!c0130ic.U && c0130ic.R.get() && c0130ic.L.isEmpty() && c0130ic.O.isEmpty()) {
            c0130ic.Z.a(AbstractC0225i.a.INFO, "Terminated");
            c.a.K.b(c0130ic.aa.f829c, c0130ic);
            ((Jd) c0130ic.r).a(c0130ic.q);
            c0130ic.s.b();
            c0130ic.t.b();
            c0130ic.o.close();
            c0130ic.U = true;
            c0130ic.V.countDown();
        }
    }

    public static /* synthetic */ void V(C0130ic c0130ic) {
        long j2 = c0130ic.B;
        if (j2 == -1) {
            return;
        }
        c0130ic.qa.a(j2, TimeUnit.MILLISECONDS);
    }

    public static /* synthetic */ void W(C0130ic c0130ic) {
        c0130ic.c(true);
        c0130ic.P.a((c.a.U.h) null);
        c0130ic.Z.a(AbstractC0225i.a.INFO, "Entering IDLE state");
        c0130ic.C.a(EnumC0234s.IDLE);
        if (c0130ic.ma.a(c0130ic.N, c0130ic.P)) {
            c0130ic.g();
        }
    }

    public static /* synthetic */ void q(C0130ic c0130ic) {
        if (c0130ic.S) {
            Iterator<Ib> it = c0130ic.L.iterator();
            while (it.hasNext()) {
                it.next().a(f1273c);
            }
            Iterator<Kc> it2 = c0130ic.O.iterator();
            while (it2.hasNext()) {
                it2.next().f1044a.a(f1273c);
            }
        }
    }

    @Override // c.a.X
    public /* bridge */ /* synthetic */ c.a.X d() {
        d();
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.i.f839d).add("target", this.j).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$i */
    final class i extends c.a.ia.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h f1294a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c.a.ia f1295b;

        public i(h hVar, c.a.ia iaVar) {
            Preconditions.checkNotNull(hVar, "helperImpl");
            this.f1294a = hVar;
            Preconditions.checkNotNull(iaVar, "resolver");
            this.f1295b = iaVar;
        }

        @Override // c.a.ia.d
        public void a(c.a.ia.f fVar) {
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0155nc runnableC0155nc = new RunnableC0155nc(this, fVar);
            b.a.a.a.a.a(runnableC0155nc, "runnable is null", ea.f796b, runnableC0155nc, ea);
        }

        public final void b(c.a.za zaVar) {
            C0130ic.f1271a.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{C0130ic.this.i, zaVar});
            j jVar = C0130ic.this.ba;
            if (jVar.f1297a.get() == C0130ic.g) {
                jVar.a(null);
            }
            if (C0130ic.this.ca != k.ERROR) {
                C0130ic.this.Z.a(AbstractC0225i.a.WARNING, "Failed to resolve name: {0}", zaVar);
                C0130ic.this.ca = k.ERROR;
            }
            if (this.f1294a != C0130ic.this.I) {
                return;
            }
            this.f1294a.f1290a.f1488b.a(zaVar);
            if (C0130ic.this.na != null) {
                c.a.Ea.a aVar = C0130ic.this.na.f801a;
                if ((aVar.f800c || aVar.f799b) ? false : true) {
                    return;
                }
            }
            if (C0130ic.this.oa == null) {
                C0130ic c0130ic = C0130ic.this;
                c0130ic.oa = ((Sa.a) c0130ic.D).a();
            }
            long jA = ((Sa) C0130ic.this.oa).a();
            C0130ic.this.Z.a(AbstractC0225i.a.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(jA));
            C0130ic c0130ic2 = C0130ic.this;
            c0130ic2.na = c0130ic2.w.a(c0130ic2.new c(), jA, TimeUnit.NANOSECONDS, C0130ic.this.o.n());
        }

        @Override // c.a.ia.d
        public void a(c.a.za zaVar) {
            Preconditions.checkArgument(!zaVar.c(), "the error status must not be OK");
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0150mc runnableC0150mc = new RunnableC0150mc(this, zaVar);
            b.a.a.a.a.a(runnableC0150mc, "runnable is null", ea.f796b, runnableC0150mc, ea);
        }
    }

    public final void b(boolean z) {
        ScheduledFuture<?> scheduledFuture;
        Yc yc = this.qa;
        yc.f1200f = false;
        if (!z || (scheduledFuture = yc.g) == null) {
            return;
        }
        scheduledFuture.cancel(false);
        yc.g = null;
    }

    public final void f() {
        this.w.b();
        c.a.Ea.b bVar = this.na;
        if (bVar != null) {
            bVar.a();
            this.na = null;
            this.oa = null;
        }
    }

    @VisibleForTesting
    public void g() {
        this.w.b();
        if (this.R.get() || this.K) {
            return;
        }
        if (!this.ma.f1493a.isEmpty()) {
            b(false);
        } else {
            long j2 = this.B;
            if (j2 != -1) {
                this.qa.a(j2, TimeUnit.MILLISECONDS);
            }
        }
        if (this.I != null) {
            return;
        }
        this.Z.a(AbstractC0225i.a.INFO, "Exiting idle mode");
        h hVar = new h(null);
        hVar.f1290a = this.n.a(hVar);
        this.I = hVar;
        this.G.a(new i(hVar, this.G));
        this.H = true;
    }

    public final void h() {
        this.w.b();
        if (this.H) {
            this.G.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$h */
    final class h extends c.a.U.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public C0191v.a f1290a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1291b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1292c;

        public /* synthetic */ h(Ub ub) {
        }

        @Override // c.a.U.c
        public c.a.U.g a(c.a.U.a aVar) {
            C0130ic.this.w.b();
            Preconditions.checkState(!C0130ic.this.T, "Channel is being terminated");
            return C0130ic.this.new m(aVar, this);
        }

        @Override // c.a.U.c
        public void a(EnumC0234s enumC0234s, c.a.U.h hVar) {
            C0130ic.this.w.b();
            Preconditions.checkNotNull(enumC0234s, "newState");
            Preconditions.checkNotNull(hVar, "newPicker");
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0145lc runnableC0145lc = new RunnableC0145lc(this, hVar, enumC0234s);
            b.a.a.a.a.a(runnableC0145lc, "runnable is null", ea.f796b, runnableC0145lc, ea);
        }

        @Override // c.a.U.c
        public void a() {
            C0130ic.this.w.b();
            this.f1291b = true;
            c.a.Ea ea = C0130ic.this.w;
            RunnableC0140kc runnableC0140kc = new RunnableC0140kc(this);
            b.a.a.a.a.a(runnableC0140kc, "runnable is null", ea.f796b, runnableC0140kc, ea);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$m */
    public final class m extends AbstractC0117g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.U.a f1307a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h f1308b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.a.O f1309c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final B f1310d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final D f1311e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public List<c.a.B> f1312f;
        public Ib g;
        public boolean h;
        public boolean i;
        public c.a.Ea.b j;

        public m(c.a.U.a aVar, h hVar) {
            this.f1312f = aVar.f845a;
            if (C0130ic.this.k != null) {
                List<c.a.B> listB = b(aVar.f845a);
                C0086b c0086b = C0086b.f876a;
                List<c.a.B> list = aVar.f845a;
                Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
                Collections.unmodifiableList(new ArrayList(list));
                C0086b c0086b2 = aVar.f846b;
                Preconditions.checkNotNull(c0086b2, "attrs");
                Object[][] objArr = aVar.f847c;
                Object[][] objArr2 = (Object[][]) Array.newInstance((Class<?>) Object.class, objArr.length, 2);
                System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                Preconditions.checkArgument(!listB.isEmpty(), "addrs is empty");
                aVar = new c.a.U.a(Collections.unmodifiableList(new ArrayList(listB)), c0086b2, objArr2, null);
            }
            Preconditions.checkNotNull(aVar, "args");
            this.f1307a = aVar;
            Preconditions.checkNotNull(hVar, "helper");
            this.f1308b = hVar;
            this.f1309c = c.a.O.a("Subchannel", C0130ic.this.E.b());
            c.a.O o = this.f1309c;
            int i = C0130ic.this.v;
            long jA = ((Od) C0130ic.this.u).a();
            StringBuilder sbA = b.a.a.a.a.a("Subchannel for ");
            sbA.append(aVar.f845a);
            this.f1311e = new D(o, i, jA, sbA.toString());
            this.f1310d = new B(this.f1311e, C0130ic.this.u);
        }

        @Override // c.a.U.g
        public void a(c.a.U.i iVar) {
            C0130ic.this.w.b();
            Preconditions.checkState(!this.h, "already started");
            Preconditions.checkState(!this.i, "already shutdown");
            Preconditions.checkState(!C0130ic.this.T, "Channel is being terminated");
            this.h = true;
            Ib ib = new Ib(this.f1307a.f845a, C0130ic.this.E.b(), C0130ic.this.F, C0130ic.this.D, C0130ic.this.o, C0130ic.this.o.n(), C0130ic.this.A, C0130ic.this.w, new C0194vc(this, iVar), C0130ic.this.aa, C0130ic.this.W.a(), this.f1311e, this.f1309c, this.f1310d);
            D d2 = C0130ic.this.Y;
            c.a.J.a aVar = c.a.J.a.CT_INFO;
            Long lValueOf = Long.valueOf(((Od) C0130ic.this.u).a());
            Preconditions.checkNotNull("Child Subchannel started", "description");
            Preconditions.checkNotNull(aVar, "severity");
            Preconditions.checkNotNull(lValueOf, "timestampNanos");
            Preconditions.checkState(true, "at least one of channelRef and subchannelRef must be null");
            d2.a(new c.a.J("Child Subchannel started", aVar, lValueOf.longValue(), null, ib, null));
            this.g = ib;
            c.a.K.a(C0130ic.this.aa.f830d, ib);
            C0130ic.this.L.add(ib);
        }

        @Override // c.a.U.g
        public void b() {
            C0130ic.this.w.b();
            Preconditions.checkState(this.h, "not started");
            this.g.b();
        }

        @Override // c.a.U.g
        public void c() {
            c.a.Ea.b bVar;
            C0130ic.this.w.b();
            if (this.g == null) {
                this.i = true;
                return;
            }
            if (!this.i) {
                this.i = true;
            } else {
                if (!C0130ic.this.T || (bVar = this.j) == null) {
                    return;
                }
                bVar.a();
                this.j = null;
            }
            if (C0130ic.this.T) {
                this.g.b(C0130ic.f1274d);
            } else {
                this.j = C0130ic.this.w.a(new Sb(new RunnableC0199wc(this)), 5L, TimeUnit.SECONDS, C0130ic.this.o.n());
            }
        }

        public String toString() {
            return this.f1309c.toString();
        }

        public final List<c.a.B> b(List<c.a.B> list) {
            ArrayList arrayList = new ArrayList();
            for (c.a.B b2 : list) {
                List<SocketAddress> list2 = b2.f783b;
                C0086b.a aVarB = b2.f784c.b();
                aVarB.a(c.a.B.f782a);
                arrayList.add(new c.a.B(list2, aVarB.a()));
            }
            return Collections.unmodifiableList(arrayList);
        }

        @Override // c.a.U.g
        public void a(List<c.a.B> list) {
            C0130ic.this.w.b();
            this.f1312f = list;
            if (C0130ic.this.k != null) {
                list = b(list);
            }
            this.g.a(list);
        }
    }

    public final void c(boolean z) {
        this.w.b();
        if (z) {
            Preconditions.checkState(this.H, "nameResolver is not started");
            Preconditions.checkState(this.I != null, "lbHelper is null");
        }
        if (this.G != null) {
            this.w.b();
            c.a.Ea.b bVar = this.na;
            if (bVar != null) {
                bVar.a();
                this.na = null;
                this.oa = null;
            }
            this.G.c();
            this.H = false;
            if (z) {
                this.G = a(this.j, this.k, this.l, this.m);
            } else {
                this.G = null;
            }
        }
        h hVar = this.I;
        if (hVar != null) {
            C0191v.a aVar = hVar.f1290a;
            aVar.f1488b.c();
            aVar.f1488b = null;
            this.I = null;
        }
        this.J = null;
    }

    @Override // c.a.X
    public c.a.X e() {
        this.Z.a(AbstractC0225i.a.DEBUG, "shutdownNow() called");
        d();
        j jVar = this.ba;
        c.a.Ea ea = C0130ic.this.w;
        RunnableC0170qc runnableC0170qc = new RunnableC0170qc(jVar);
        b.a.a.a.a.a(runnableC0170qc, "runnable is null", ea.f796b, runnableC0170qc, ea);
        c.a.Ea ea2 = this.w;
        RunnableC0095bc runnableC0095bc = new RunnableC0095bc(this);
        b.a.a.a.a.a(runnableC0095bc, "runnable is null", ea2.f796b, runnableC0095bc, ea2);
        return this;
    }

    @Override // c.a.X
    public C0130ic d() {
        this.Z.a(AbstractC0225i.a.DEBUG, "shutdown() called");
        if (!this.R.compareAndSet(false, true)) {
            return this;
        }
        c.a.Ea ea = this.w;
        RunnableC0090ac runnableC0090ac = new RunnableC0090ac(this);
        b.a.a.a.a.a(runnableC0090ac, "runnable is null", ea.f796b, runnableC0090ac, ea);
        j jVar = this.ba;
        c.a.Ea ea2 = C0130ic.this.w;
        RunnableC0165pc runnableC0165pc = new RunnableC0165pc(jVar);
        b.a.a.a.a.a(runnableC0165pc, "runnable is null", ea2.f796b, runnableC0165pc, ea2);
        c.a.Ea ea3 = this.w;
        Vb vb = new Vb(this);
        b.a.a.a.a.a(vb, "runnable is null", ea3.f796b, vb, ea3);
        return this;
    }

    public static /* synthetic */ void b(C0130ic c0130ic) {
        c0130ic.w.b();
        if (c0130ic.H) {
            c0130ic.G.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ic$n */
    final class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1313a = new Object();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Collection<M> f1314b = new HashSet();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.a.za f1315c;

        public /* synthetic */ n(Ub ub) {
        }

        public void a(c.a.za zaVar) {
            synchronized (this.f1313a) {
                if (this.f1315c != null) {
                    return;
                }
                this.f1315c = zaVar;
                boolean zIsEmpty = this.f1314b.isEmpty();
                if (zIsEmpty) {
                    C0130ic.this.P.b(zaVar);
                }
            }
        }

        public void b(c.a.za zaVar) {
            ArrayList arrayList;
            a(zaVar);
            synchronized (this.f1313a) {
                arrayList = new ArrayList(this.f1314b);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((M) it.next()).a(zaVar);
            }
            C0130ic.this.P.a(zaVar);
        }

        public c.a.za a(AbstractC0171qd<?> abstractC0171qd) {
            synchronized (this.f1313a) {
                if (this.f1315c != null) {
                    return this.f1315c;
                }
                this.f1314b.add(abstractC0171qd);
                return null;
            }
        }

        public void b(AbstractC0171qd<?> abstractC0171qd) {
            c.a.za zaVar;
            synchronized (this.f1313a) {
                this.f1314b.remove(abstractC0171qd);
                if (this.f1314b.isEmpty()) {
                    zaVar = this.f1315c;
                    this.f1314b = new HashSet();
                } else {
                    zaVar = null;
                }
            }
            if (zaVar != null) {
                C0130ic.this.P.b(zaVar);
            }
        }
    }

    @Override // c.a.N
    public c.a.O a() {
        return this.i;
    }

    public final Executor a(C0220f c0220f) {
        Executor executor = c0220f.f1814c;
        return executor == null ? this.q : executor;
    }

    @Override // c.a.AbstractC0222g
    public String b() {
        return this.E.b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        if (r2 != null) goto L16;
     */
    @com.google.common.annotations.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static c.a.ia a(java.lang.String r7, java.lang.String r8, c.a.ia.c r9, c.a.ia.a r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.net.URI r2 = new java.net.URI     // Catch: java.net.URISyntaxException -> Lc
            r2.<init>(r7)     // Catch: java.net.URISyntaxException -> Lc
            goto L15
        Lc:
            r2 = move-exception
            java.lang.String r2 = r2.getMessage()
            r0.append(r2)
            r2 = r1
        L15:
            if (r2 == 0) goto L1e
            c.a.ia r2 = r9.a(r2, r10)
            if (r2 == 0) goto L1e
            goto L4c
        L1e:
            java.util.regex.Pattern r2 = c.a.b.C0130ic.f1272b
            java.util.regex.Matcher r2 = r2.matcher(r7)
            boolean r2 = r2.matches()
            java.lang.String r3 = ""
            if (r2 != 0) goto L5c
            java.net.URI r2 = new java.net.URI     // Catch: java.net.URISyntaxException -> L55
            java.lang.String r4 = r9.a()     // Catch: java.net.URISyntaxException -> L55
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.net.URISyntaxException -> L55
            r5.<init>()     // Catch: java.net.URISyntaxException -> L55
            java.lang.String r6 = "/"
            r5.append(r6)     // Catch: java.net.URISyntaxException -> L55
            r5.append(r7)     // Catch: java.net.URISyntaxException -> L55
            java.lang.String r5 = r5.toString()     // Catch: java.net.URISyntaxException -> L55
            r2.<init>(r4, r3, r5, r1)     // Catch: java.net.URISyntaxException -> L55
            c.a.ia r2 = r9.a(r2, r10)
            if (r2 == 0) goto L5c
        L4c:
            if (r8 != 0) goto L4f
            return r2
        L4f:
            c.a.b.ec r7 = new c.a.b.ec
            r7.<init>(r2, r8)
            return r7
        L55:
            r7 = move-exception
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r7)
            throw r8
        L5c:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r7
            r7 = 1
            int r10 = r0.length()
            if (r10 <= 0) goto L73
            java.lang.String r10 = " ("
            java.lang.String r1 = ")"
            java.lang.String r3 = b.a.a.a.a.a(r10, r0, r1)
        L73:
            r9[r7] = r3
            java.lang.String r7 = "cannot find a NameResolver for %s%s"
            java.lang.String r7 = java.lang.String.format(r7, r9)
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.C0130ic.a(java.lang.String, java.lang.String, c.a.ia$c, c.a.ia$a):c.a.ia");
    }

    @Override // c.a.X
    public void c() {
        c.a.Ea ea = this.w;
        Zb zb = new Zb(this);
        b.a.a.a.a.a(zb, "runnable is null", ea.f796b, zb, ea);
    }

    public static /* synthetic */ void a(C0130ic c0130ic, c.a.U.h hVar) {
        c0130ic.J = hVar;
        c0130ic.P.a(hVar);
    }

    public final void a(c.a.U.h hVar) {
        this.J = hVar;
        this.P.a(hVar);
    }

    @Override // c.a.X
    public boolean a(long j2, TimeUnit timeUnit) {
        return this.V.await(j2, timeUnit);
    }

    @Override // c.a.AbstractC0222g
    public <ReqT, RespT> AbstractC0226j<ReqT, RespT> a(C0223ga<ReqT, RespT> c0223ga, C0220f c0220f) {
        return this.E.a(c0223ga, c0220f);
    }

    @Override // c.a.X
    public EnumC0234s a(boolean z) {
        EnumC0234s enumC0234s = this.C.f1190b;
        if (enumC0234s != null) {
            if (z && enumC0234s == EnumC0234s.IDLE) {
                c.a.Ea ea = this.w;
                _b _bVar = new _b(this);
                b.a.a.a.a.a(_bVar, "runnable is null", ea.f796b, _bVar, ea);
            }
            return enumC0234s;
        }
        throw new UnsupportedOperationException("Channel state API is not implemented");
    }

    @Override // c.a.X
    public void a(EnumC0234s enumC0234s, Runnable runnable) {
        c.a.Ea ea = this.w;
        Xb xb = new Xb(this, runnable, enumC0234s);
        b.a.a.a.a.a(xb, "runnable is null", ea.f796b, xb, ea);
    }
}

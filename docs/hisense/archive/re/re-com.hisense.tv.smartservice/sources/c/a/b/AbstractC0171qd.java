package c.a.b;

import androidx.core.app.NotificationManagerCompat;
import c.a.AbstractC0230n;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0236u;
import c.a.C0238w;
import c.a.C0240y;
import c.a.InterfaceC0233q;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: renamed from: c.a.b.qd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0171qd<ReqT> implements M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @VisibleForTesting
    public static final C0219ea.e<String> f1415a = C0219ea.e.a("grpc-previous-rpc-attempts", C0219ea.f1798a);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @VisibleForTesting
    public static final C0219ea.e<String> f1416b = C0219ea.e.a("grpc-retry-pushback-ms", C0219ea.f1798a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.a.za f1417c = c.a.za.f1937d.b("Stream thrown away because RetriableStream committed");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Random f1418d = new Random();
    public boolean A;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0223ga<ReqT, ?> f1419e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Executor f1420f;
    public final ScheduledExecutorService h;
    public final C0219ea i;
    public final Ad j;
    public final C0164pb k;
    public final boolean l;
    public final c n;
    public final long o;
    public final long p;
    public final k q;
    public long u;
    public N v;
    public d w;
    public d x;
    public long y;
    public c.a.za z;
    public final Executor g = new c.a.Ea(new Zc(this));
    public final Object m = new Object();
    public final C0198wb r = new C0198wb();
    public volatile h s = new h(new ArrayList(8), Collections.emptyList(), null, null, false, false, false, 0);
    public final AtomicBoolean t = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$a */
    interface a {
        void a(j jVar);
    }

    /* JADX INFO: renamed from: c.a.b.qd$b */
    class b extends AbstractC0230n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j f1421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f1422b;

        public b(j jVar) {
            this.f1421a = jVar;
        }

        @Override // c.a.Ca
        public void d(long j) {
            if (AbstractC0171qd.this.s.f1438f != null) {
                return;
            }
            synchronized (AbstractC0171qd.this.m) {
                if (AbstractC0171qd.this.s.f1438f == null && !this.f1421a.f1442b) {
                    this.f1422b += j;
                    if (this.f1422b <= AbstractC0171qd.this.u) {
                        return;
                    }
                    if (this.f1422b > AbstractC0171qd.this.o) {
                        this.f1421a.f1443c = true;
                    } else {
                        long jAddAndGet = AbstractC0171qd.this.n.f1424a.addAndGet(this.f1422b - AbstractC0171qd.this.u);
                        AbstractC0171qd.this.u = this.f1422b;
                        if (jAddAndGet > AbstractC0171qd.this.p) {
                            this.f1421a.f1443c = true;
                        }
                    }
                    Runnable runnableA = this.f1421a.f1443c ? AbstractC0171qd.this.a(this.f1421a) : null;
                    if (runnableA != null) {
                        runnableA.run();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: c.a.b.qd$c */
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicLong f1424a = new AtomicLong();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$e */
    final class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f1428a;

        public e(d dVar) {
            this.f1428a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0171qd.this.f1420f.execute(new RunnableC0175rd(this));
        }
    }

    /* JADX INFO: renamed from: c.a.b.qd$f */
    private static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f1430a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f1431b;

        public f(boolean z, long j) {
            this.f1430a = z;
            this.f1431b = j;
        }
    }

    /* JADX INFO: renamed from: c.a.b.qd$g */
    class g implements a {
        public g() {
        }

        @Override // c.a.b.AbstractC0171qd.a
        public void a(j jVar) {
            jVar.f1441a.a(new i(jVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$h */
    static final class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f1433a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<a> f1434b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Collection<j> f1435c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Collection<j> f1436d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1437e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final j f1438f;
        public final boolean g;
        public final boolean h;

        public h(List<a> list, Collection<j> collection, Collection<j> collection2, j jVar, boolean z, boolean z2, boolean z3, int i) {
            this.f1434b = list;
            Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.f1435c = collection;
            this.f1438f = jVar;
            this.f1436d = collection2;
            this.g = z;
            this.f1433a = z2;
            this.h = z3;
            this.f1437e = i;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState((z2 && jVar == null) ? false : true, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(jVar)) || (collection.size() == 0 && jVar.f1442b), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((z && jVar == null) ? false : true, "cancelled should imply committed");
        }

        public h a() {
            return new h(this.f1434b, this.f1435c, this.f1436d, this.f1438f, true, this.f1433a, this.h, this.f1437e);
        }

        public h b() {
            return this.h ? this : new h(this.f1434b, this.f1435c, this.f1436d, this.f1438f, this.g, this.f1433a, true, this.f1437e);
        }

        public h c(j jVar) {
            jVar.f1442b = true;
            if (!this.f1435c.contains(jVar)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.f1435c);
            arrayList.remove(jVar);
            return new h(this.f1434b, Collections.unmodifiableCollection(arrayList), this.f1436d, this.f1438f, this.g, this.f1433a, this.h, this.f1437e);
        }

        public h d(j jVar) {
            Collection collectionUnmodifiableCollection;
            List<a> list;
            Preconditions.checkState(!this.f1433a, "Already passThrough");
            if (jVar.f1442b) {
                collectionUnmodifiableCollection = this.f1435c;
            } else if (this.f1435c.isEmpty()) {
                collectionUnmodifiableCollection = Collections.singletonList(jVar);
            } else {
                ArrayList arrayList = new ArrayList(this.f1435c);
                arrayList.add(jVar);
                collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = collectionUnmodifiableCollection;
            boolean z = this.f1438f != null;
            List<a> list2 = this.f1434b;
            if (z) {
                Preconditions.checkState(this.f1438f == jVar, "Another RPC attempt has already committed");
                list = null;
            } else {
                list = list2;
            }
            return new h(list, collection, this.f1436d, this.f1438f, this.g, z, this.h, this.f1437e);
        }

        public h a(j jVar) {
            Collection collectionUnmodifiableCollection;
            Preconditions.checkState(!this.h, "hedging frozen");
            Preconditions.checkState(this.f1438f == null, "already committed");
            Collection<j> collection = this.f1436d;
            if (collection == null) {
                collectionUnmodifiableCollection = Collections.singleton(jVar);
            } else {
                ArrayList arrayList = new ArrayList(collection);
                arrayList.add(jVar);
                collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            return new h(this.f1434b, this.f1435c, collectionUnmodifiableCollection, this.f1438f, this.g, this.f1433a, this.h, this.f1437e + 1);
        }

        public h b(j jVar) {
            ArrayList arrayList = new ArrayList(this.f1436d);
            arrayList.remove(jVar);
            return new h(this.f1434b, this.f1435c, Collections.unmodifiableCollection(arrayList), this.f1438f, this.g, this.f1433a, this.h, this.f1437e);
        }

        public h a(j jVar, j jVar2) {
            ArrayList arrayList = new ArrayList(this.f1436d);
            arrayList.remove(jVar);
            arrayList.add(jVar2);
            return new h(this.f1434b, this.f1435c, Collections.unmodifiableCollection(arrayList), this.f1438f, this.g, this.f1433a, this.h, this.f1437e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$j */
    static final class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public M f1441a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1442b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1443c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1444d;

        public j(int i) {
            this.f1444d = i;
        }
    }

    /* JADX INFO: renamed from: c.a.b.qd$k */
    static final class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1446b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1447c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final AtomicInteger f1448d = new AtomicInteger();

        public k(float f2, float f3) {
            this.f1447c = (int) (f3 * 1000.0f);
            this.f1445a = (int) (f2 * 1000.0f);
            int i = this.f1445a;
            this.f1446b = i / 2;
            this.f1448d.set(i);
        }

        @VisibleForTesting
        public boolean a() {
            int i;
            int i2;
            do {
                i = this.f1448d.get();
                if (i == 0) {
                    return false;
                }
                i2 = i + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            } while (!this.f1448d.compareAndSet(i, Math.max(i2, 0)));
            return i2 > this.f1446b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof k)) {
                return false;
            }
            k kVar = (k) obj;
            return this.f1445a == kVar.f1445a && this.f1447c == kVar.f1447c;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.f1445a), Integer.valueOf(this.f1447c)});
        }
    }

    public AbstractC0171qd(C0223ga<ReqT, ?> c0223ga, C0219ea c0219ea, c cVar, long j2, long j3, Executor executor, ScheduledExecutorService scheduledExecutorService, Ad ad, C0164pb c0164pb, k kVar) {
        this.f1419e = c0223ga;
        this.n = cVar;
        this.o = j2;
        this.p = j3;
        this.f1420f = executor;
        this.h = scheduledExecutorService;
        this.i = c0219ea;
        this.j = ad;
        if (ad != null) {
            this.y = ad.f890b;
        }
        this.k = c0164pb;
        Preconditions.checkArgument(ad == null || c0164pb == null, "Should not provide both retryPolicy and hedgingPolicy");
        this.l = c0164pb != null;
        this.q = kVar;
    }

    @Override // c.a.b.Md
    public final void flush() {
        h hVar = this.s;
        if (hVar.f1433a) {
            hVar.f1438f.f1441a.flush();
        } else {
            a((a) new C0111ed(this));
        }
    }

    @Override // c.a.b.M
    public final C0086b getAttributes() {
        return this.s.f1438f != null ? this.s.f1438f.f1441a.getAttributes() : C0086b.f876a;
    }

    @Override // c.a.b.Md
    public final boolean isReady() {
        Iterator<j> it = this.s.f1435c.iterator();
        while (it.hasNext()) {
            if (it.next().f1441a.isReady()) {
                return true;
            }
        }
        return false;
    }

    @Override // c.a.b.M
    public final void d(int i2) {
        a((a) new C0131id(this, i2));
    }

    @Override // c.a.b.M
    public final void c(int i2) {
        a((a) new C0126hd(this, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$d */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1425a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Future<?> f1426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1427c;

        public d(Object obj) {
            this.f1425a = obj;
        }

        public void a(Future<?> future) {
            synchronized (this.f1425a) {
                if (!this.f1427c) {
                    this.f1426b = future;
                }
            }
        }

        public Future<?> a() {
            this.f1427c = true;
            return this.f1426b;
        }
    }

    public final void c() {
        Future<?> futureA;
        synchronized (this.m) {
            if (this.x != null) {
                futureA = this.x.a();
                this.x = null;
            } else {
                futureA = null;
            }
            this.s = this.s.b();
        }
        if (futureA != null) {
            futureA.cancel(false);
        }
    }

    public static /* synthetic */ void b(AbstractC0171qd abstractC0171qd, j jVar) {
        Runnable runnableA = abstractC0171qd.a(jVar);
        if (runnableA != null) {
            runnableA.run();
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x001b A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x002f A[Catch: all -> 0x00a7, DONT_GENERATE, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x0031 A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0050 A[Catch: all -> 0x00a7, TRY_ENTER, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0056 A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x0064 A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0070 A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:5:0x0007, B:7:0x000b, B:9:0x000f, B:11:0x0013, B:13:0x0015, B:15:0x0019, B:17:0x001b, B:19:0x0023, B:21:0x002f, B:23:0x0031, B:24:0x0036, B:34:0x0050, B:36:0x0054, B:38:0x0056, B:40:0x0064, B:42:0x007c, B:41:0x0070), top: B:60:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x0054 A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007d, code lost:
    
        r2 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0085, code lost:
    
        if (r2.hasNext() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0087, code lost:
    
        r4 = (c.a.b.AbstractC0171qd.a) r2.next();
        r4.a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0092, code lost:
    
        if ((r4 instanceof c.a.b.AbstractC0171qd.g) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0094, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0095, code lost:
    
        if (r1 == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0097, code lost:
    
        r4 = r8.s;
        r5 = r4.f1438f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009b, code lost:
    
        if (r5 == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x009d, code lost:
    
        if (r5 == r9) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a2, code lost:
    
        if (r4.g == false) goto L71;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(c.a.b.AbstractC0171qd.j r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            r3 = r0
            r2 = r1
        L4:
            java.lang.Object r4 = r8.m
            monitor-enter(r4)
            c.a.b.qd$h r5 = r8.s     // Catch: java.lang.Throwable -> La7
            if (r1 == 0) goto L1b
            c.a.b.qd$j r6 = r5.f1438f     // Catch: java.lang.Throwable -> La7
            if (r6 == 0) goto L15
            c.a.b.qd$j r6 = r5.f1438f     // Catch: java.lang.Throwable -> La7
            if (r6 == r9) goto L15
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            goto L37
        L15:
            boolean r6 = r5.g     // Catch: java.lang.Throwable -> La7
            if (r6 == 0) goto L1b
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            goto L37
        L1b:
            java.util.List<c.a.b.qd$a> r6 = r5.f1434b     // Catch: java.lang.Throwable -> La7
            int r6 = r6.size()     // Catch: java.lang.Throwable -> La7
            if (r2 != r6) goto L50
            c.a.b.qd$h r0 = r5.d(r9)     // Catch: java.lang.Throwable -> La7
            r8.s = r0     // Catch: java.lang.Throwable -> La7
            boolean r0 = r8.isReady()     // Catch: java.lang.Throwable -> La7
            if (r0 != 0) goto L31
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            return
        L31:
            c.a.b.od r0 = new c.a.b.od     // Catch: java.lang.Throwable -> La7
            r0.<init>(r8)     // Catch: java.lang.Throwable -> La7
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
        L37:
            if (r0 == 0) goto L3f
            java.util.concurrent.Executor r9 = r8.g
            r9.execute(r0)
            return
        L3f:
            c.a.b.M r0 = r9.f1441a
            c.a.b.qd$h r1 = r8.s
            c.a.b.qd$j r1 = r1.f1438f
            if (r1 != r9) goto L4a
            c.a.za r9 = r8.z
            goto L4c
        L4a:
            c.a.za r9 = c.a.b.AbstractC0171qd.f1417c
        L4c:
            r0.a(r9)
            return
        L50:
            boolean r6 = r9.f1442b     // Catch: java.lang.Throwable -> La7
            if (r6 == 0) goto L56
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            return
        L56:
            int r6 = r2 + 128
            java.util.List<c.a.b.qd$a> r7 = r5.f1434b     // Catch: java.lang.Throwable -> La7
            int r7 = r7.size()     // Catch: java.lang.Throwable -> La7
            int r6 = java.lang.Math.min(r6, r7)     // Catch: java.lang.Throwable -> La7
            if (r3 != 0) goto L70
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La7
            java.util.List<c.a.b.qd$a> r5 = r5.f1434b     // Catch: java.lang.Throwable -> La7
            java.util.List r2 = r5.subList(r2, r6)     // Catch: java.lang.Throwable -> La7
            r3.<init>(r2)     // Catch: java.lang.Throwable -> La7
            goto L7c
        L70:
            r3.clear()     // Catch: java.lang.Throwable -> La7
            java.util.List<c.a.b.qd$a> r5 = r5.f1434b     // Catch: java.lang.Throwable -> La7
            java.util.List r2 = r5.subList(r2, r6)     // Catch: java.lang.Throwable -> La7
            r3.addAll(r2)     // Catch: java.lang.Throwable -> La7
        L7c:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            java.util.Iterator r2 = r3.iterator()
        L81:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto La4
            java.lang.Object r4 = r2.next()
            c.a.b.qd$a r4 = (c.a.b.AbstractC0171qd.a) r4
            r4.a(r9)
            boolean r4 = r4 instanceof c.a.b.AbstractC0171qd.g
            if (r4 == 0) goto L95
            r1 = 1
        L95:
            if (r1 == 0) goto L81
            c.a.b.qd$h r4 = r8.s
            c.a.b.qd$j r5 = r4.f1438f
            if (r5 == 0) goto La0
            if (r5 == r9) goto La0
            goto La4
        La0:
            boolean r4 = r4.g
            if (r4 == 0) goto L81
        La4:
            r2 = r6
            goto L4
        La7:
            r9 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> La7
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.AbstractC0171qd.b(c.a.b.qd$j):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.qd$i */
    final class i implements N {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j f1439a;

        public i(j jVar) {
            this.f1439a = jVar;
        }

        @Override // c.a.b.N
        public void a(C0219ea c0219ea) {
            int i;
            int i2;
            AbstractC0171qd.b(AbstractC0171qd.this, this.f1439a);
            if (AbstractC0171qd.this.s.f1438f == this.f1439a) {
                if (AbstractC0171qd.this.q != null) {
                    k kVar = AbstractC0171qd.this.q;
                    do {
                        i = kVar.f1448d.get();
                        i2 = kVar.f1445a;
                        if (i == i2) {
                            break;
                        }
                    } while (!kVar.f1448d.compareAndSet(i, Math.min(kVar.f1447c + i, i2)));
                }
                AbstractC0171qd.this.g.execute(new RunnableC0180sd(this, c0219ea));
            }
        }

        public final Integer b(C0219ea c0219ea) {
            String str = (String) c0219ea.b(AbstractC0171qd.f1416b);
            if (str == null) {
                return null;
            }
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        /* JADX WARN: Code duplicated, block: B:95:0x0223  */
        @Override // c.a.b.N
        public void a(c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
            f fVar;
            d dVar;
            synchronized (AbstractC0171qd.this.m) {
                AbstractC0171qd.this.s = AbstractC0171qd.this.s.c(this.f1439a);
                AbstractC0171qd.this.r.a(zaVar.p);
            }
            j jVar = this.f1439a;
            if (!jVar.f1443c) {
                if (AbstractC0171qd.this.s.f1438f == null) {
                    boolean z = false;
                    boolean z2 = true;
                    if (aVar == N.a.REFUSED && AbstractC0171qd.this.t.compareAndSet(false, true)) {
                        j jVarA = AbstractC0171qd.this.a(this.f1439a.f1444d, true);
                        if (AbstractC0171qd.this.l) {
                            synchronized (AbstractC0171qd.this.m) {
                                AbstractC0171qd.this.s = AbstractC0171qd.this.s.a(this.f1439a, jVarA);
                                if (!AbstractC0171qd.this.a(AbstractC0171qd.this.s) && AbstractC0171qd.this.s.f1436d.size() == 1) {
                                    z = true;
                                }
                            }
                            if (z) {
                                AbstractC0171qd.b(AbstractC0171qd.this, jVarA);
                            }
                        } else if (AbstractC0171qd.this.j == null || AbstractC0171qd.this.j.f889a == 1) {
                            AbstractC0171qd.b(AbstractC0171qd.this, jVarA);
                        }
                        AbstractC0171qd.this.f1420f.execute(new wd(this, jVarA));
                        return;
                    }
                    if (aVar == N.a.DROPPED) {
                        if (AbstractC0171qd.this.l) {
                            AbstractC0171qd.this.c();
                        }
                    } else {
                        AbstractC0171qd.this.t.set(true);
                        if (!AbstractC0171qd.this.l) {
                            long nanos = 0;
                            if (AbstractC0171qd.this.j == null) {
                                fVar = new f(false, 0L);
                            } else {
                                boolean zContains = AbstractC0171qd.this.j.f894f.contains(zaVar.p);
                                Integer numB = b(c0219ea);
                                boolean z3 = (AbstractC0171qd.this.q == null || (!zContains && (numB == null || numB.intValue() >= 0))) ? false : !AbstractC0171qd.this.q.a();
                                if (AbstractC0171qd.this.j.f889a <= this.f1439a.f1444d + 1 || z3) {
                                    z2 = false;
                                } else if (numB == null) {
                                    if (zContains) {
                                        nanos = (long) (AbstractC0171qd.f1418d.nextDouble() * AbstractC0171qd.this.y);
                                        AbstractC0171qd abstractC0171qd = AbstractC0171qd.this;
                                        abstractC0171qd.y = Math.min((long) (abstractC0171qd.y * AbstractC0171qd.this.j.f892d), AbstractC0171qd.this.j.f891c);
                                    } else {
                                        z2 = false;
                                    }
                                } else if (numB.intValue() >= 0) {
                                    nanos = TimeUnit.MILLISECONDS.toNanos(numB.intValue());
                                    AbstractC0171qd abstractC0171qd2 = AbstractC0171qd.this;
                                    abstractC0171qd2.y = abstractC0171qd2.j.f890b;
                                } else {
                                    z2 = false;
                                }
                                fVar = new f(z2, nanos);
                            }
                            if (fVar.f1430a) {
                                synchronized (AbstractC0171qd.this.m) {
                                    AbstractC0171qd abstractC0171qd3 = AbstractC0171qd.this;
                                    dVar = new d(AbstractC0171qd.this.m);
                                    abstractC0171qd3.w = dVar;
                                }
                                dVar.a(AbstractC0171qd.this.h.schedule(new RunnableC0190ud(this), fVar.f1431b, TimeUnit.NANOSECONDS));
                                return;
                            }
                        } else {
                            Integer numB2 = b(c0219ea);
                            boolean z4 = !AbstractC0171qd.this.k.f1406c.contains(zaVar.p);
                            boolean z5 = (AbstractC0171qd.this.q == null || (z4 && (numB2 == null || numB2.intValue() >= 0))) ? false : !AbstractC0171qd.this.q.a();
                            if (!z4 && !z5) {
                                z = true;
                            }
                            if (z) {
                                AbstractC0171qd.this.a(numB2);
                            }
                            synchronized (AbstractC0171qd.this.m) {
                                AbstractC0171qd.this.s = AbstractC0171qd.this.s.b(this.f1439a);
                                if (z && (AbstractC0171qd.this.a(AbstractC0171qd.this.s) || !AbstractC0171qd.this.s.f1436d.isEmpty())) {
                                    return;
                                }
                            }
                        }
                    }
                }
                AbstractC0171qd.b(AbstractC0171qd.this, this.f1439a);
                if (AbstractC0171qd.this.s.f1438f == this.f1439a) {
                    AbstractC0171qd.this.g.execute(new xd(this, zaVar, aVar, c0219ea));
                    return;
                }
                return;
            }
            AbstractC0171qd.b(AbstractC0171qd.this, jVar);
            if (AbstractC0171qd.this.s.f1438f == this.f1439a) {
                AbstractC0171qd.this.g.execute(new RunnableC0195vd(this, zaVar, aVar, c0219ea));
            }
        }

        @Override // c.a.b.Nd
        public void a(Nd.a aVar) {
            h hVar = AbstractC0171qd.this.s;
            Preconditions.checkState(hVar.f1438f != null, "Headers should be received prior to messages.");
            if (hVar.f1438f != this.f1439a) {
                return;
            }
            AbstractC0171qd.this.g.execute(new yd(this, aVar));
        }

        @Override // c.a.b.Nd
        public void a() {
            if (AbstractC0171qd.this.isReady()) {
                AbstractC0171qd.this.g.execute(new zd(this));
            }
        }
    }

    public final Runnable a(j jVar) {
        List<a> list;
        Collection collectionEmptyList;
        Future<?> future;
        Future<?> future2;
        synchronized (this.m) {
            if (this.s.f1438f != null) {
                return null;
            }
            Collection<j> collection = this.s.f1435c;
            h hVar = this.s;
            boolean z = true;
            Preconditions.checkState(hVar.f1438f == null, "Already committed");
            List<a> list2 = hVar.f1434b;
            if (hVar.f1435c.contains(jVar)) {
                list = null;
                collectionEmptyList = Collections.singleton(jVar);
            } else {
                list = list2;
                collectionEmptyList = Collections.emptyList();
                z = false;
            }
            this.s = new h(list, collectionEmptyList, hVar.f1436d, jVar, hVar.g, z, hVar.h, hVar.f1437e);
            this.n.f1424a.addAndGet(-this.u);
            if (this.w != null) {
                Future<?> futureA = this.w.a();
                this.w = null;
                future = futureA;
            } else {
                future = null;
            }
            if (this.x != null) {
                Future<?> futureA2 = this.x.a();
                this.x = null;
                future2 = futureA2;
            } else {
                future2 = null;
            }
            return new RunnableC0091ad(this, collection, jVar, future, future2);
        }
    }

    public final j a(int i2, boolean z) {
        j jVar = new j(i2);
        C0156nd c0156nd = new C0156nd(this, new b(jVar));
        C0219ea c0219ea = this.i;
        C0219ea c0219ea2 = new C0219ea();
        c0219ea2.a(c0219ea);
        if (i2 > 0) {
            c0219ea2.a(f1415a, String.valueOf(i2));
        }
        C0125hc c0125hc = (C0125hc) this;
        C0220f c0220fA = c0125hc.C.a(c0156nd);
        AbstractC0230n[] abstractC0230nArrA = C0149mb.a(c0220fA, c0219ea2, i2, z);
        O oA = c0125hc.E.a(new Pc(c0125hc.B, c0219ea2, c0220fA));
        C0236u c0236uA = c0125hc.D.a();
        try {
            M mA = oA.a(c0125hc.B, c0219ea2, c0220fA, abstractC0230nArrA);
            c0125hc.D.a(c0236uA);
            jVar.f1441a = mA;
            return jVar;
        } catch (Throwable th) {
            c0125hc.D.a(c0236uA);
            throw th;
        }
    }

    @Override // c.a.b.Md
    public final void b(int i2) {
        h hVar = this.s;
        if (hVar.f1433a) {
            hVar.f1438f.f1441a.b(i2);
        } else {
            a((a) new C0146ld(this, i2));
        }
    }

    @Override // c.a.b.M
    public final void b(boolean z) {
        a((a) new C0116fd(this, z));
    }

    @Override // c.a.b.M
    public final void b() {
        a((a) new C0121gd(this));
    }

    @Override // c.a.b.M
    public final void a(N n) {
        this.v = n;
        C0125hc c0125hc = (C0125hc) this;
        c.a.za zaVarA = C0130ic.this.Q.a(c0125hc);
        if (zaVarA != null) {
            a(zaVarA);
            return;
        }
        synchronized (this.m) {
            this.s.f1434b.add(new g());
        }
        j jVarA = a(0, false);
        if (this.l) {
            d dVar = null;
            synchronized (this.m) {
                try {
                    this.s = this.s.a(jVarA);
                    if (a(this.s)) {
                        if (this.q != null) {
                            k kVar = this.q;
                            if (kVar.f1448d.get() > kVar.f1446b) {
                                dVar = new d(this.m);
                                this.x = dVar;
                            }
                        } else {
                            dVar = new d(this.m);
                            this.x = dVar;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (dVar != null) {
                dVar.a(this.h.schedule(new e(dVar), this.k.f1405b, TimeUnit.NANOSECONDS));
            }
        }
        b(jVarA);
    }

    public final void a(Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 0) {
            c();
            return;
        }
        synchronized (this.m) {
            if (this.x == null) {
                return;
            }
            Future<?> futureA = this.x.a();
            d dVar = new d(this.m);
            this.x = dVar;
            if (futureA != null) {
                futureA.cancel(false);
            }
            dVar.a(this.h.schedule(new e(dVar), num.intValue(), TimeUnit.MILLISECONDS));
        }
    }

    @Override // c.a.b.M
    public final void a(c.a.za zaVar) {
        j jVar = new j(0);
        jVar.f1441a = new Ic();
        Runnable runnableA = a(jVar);
        if (runnableA != null) {
            runnableA.run();
            this.g.execute(new RunnableC0166pd(this, zaVar));
            return;
        }
        j jVar2 = null;
        synchronized (this.m) {
            if (this.s.f1435c.contains(this.s.f1438f)) {
                jVar2 = this.s.f1438f;
            } else {
                this.z = zaVar;
            }
            this.s = this.s.a();
        }
        if (jVar2 != null) {
            jVar2.f1441a.a(zaVar);
        }
    }

    public final void a(a aVar) {
        Collection<j> collection;
        synchronized (this.m) {
            if (!this.s.f1433a) {
                this.s.f1434b.add(aVar);
            }
            collection = this.s.f1435c;
        }
        Iterator<j> it = collection.iterator();
        while (it.hasNext()) {
            aVar.a(it.next());
        }
    }

    @Override // c.a.b.Md
    public final void a(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    public final void a(ReqT reqt) {
        h hVar = this.s;
        if (hVar.f1433a) {
            hVar.f1438f.f1441a.a(((c.a.d.a.b.a) this.f1419e.f1835d).a(reqt));
        } else {
            a((a) new C0151md(this, reqt));
        }
    }

    @Override // c.a.b.Md
    public void a() {
        a((a) new C0141kd(this));
    }

    @Override // c.a.b.Md
    public final void a(InterfaceC0233q interfaceC0233q) {
        a((a) new C0096bd(this, interfaceC0233q));
    }

    @Override // c.a.b.Md
    public final void a(boolean z) {
        a((a) new C0136jd(this, z));
    }

    @Override // c.a.b.M
    public final void a(String str) {
        a((a) new _c(this, str));
    }

    @Override // c.a.b.M
    public final void a(C0240y c0240y) {
        a((a) new C0106dd(this, c0240y));
    }

    @Override // c.a.b.M
    public final void a(C0238w c0238w) {
        a((a) new C0101cd(this, c0238w));
    }

    @Override // c.a.b.M
    public void a(C0198wb c0198wb) {
        h hVar;
        synchronized (this.m) {
            c0198wb.a("closed", this.r);
            hVar = this.s;
        }
        if (hVar.f1438f != null) {
            C0198wb c0198wb2 = new C0198wb();
            hVar.f1438f.f1441a.a(c0198wb2);
            c0198wb.a("committed", c0198wb2);
            return;
        }
        C0198wb c0198wb3 = new C0198wb();
        for (j jVar : hVar.f1435c) {
            C0198wb c0198wb4 = new C0198wb();
            jVar.f1441a.a(c0198wb4);
            c0198wb3.f1502a.add(String.valueOf(c0198wb4));
        }
        c0198wb.a("open", c0198wb3);
    }

    public final boolean a(h hVar) {
        return hVar.f1438f == null && hVar.f1437e < this.k.f1404a && !hVar.h;
    }
}

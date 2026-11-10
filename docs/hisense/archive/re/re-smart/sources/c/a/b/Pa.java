package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.C0086b;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import com.google.firebase.installations.Utils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class Pa extends c.a.ia {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1102a = Logger.getLogger(Pa.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set<String> f1103b = Collections.unmodifiableSet(new HashSet(Arrays.asList("clientLanguage", "percentage", "clientHostname", "serviceConfig")));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f1104c = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f1105d = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f1106e = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @VisibleForTesting
    public static boolean f1107f = Boolean.parseBoolean(f1104c);

    @VisibleForTesting
    public static boolean g = Boolean.parseBoolean(f1105d);

    @VisibleForTesting
    public static boolean h = Boolean.parseBoolean(f1106e);
    public static final f i;
    public static String j;
    public boolean A;
    public c.a.ia.d B;

    @VisibleForTesting
    public final c.a.oa k;
    public final Random l = new Random();
    public volatile a m = c.INSTANCE;
    public final AtomicReference<e> n = new AtomicReference<>();
    public final String o;
    public final String p;
    public final int q;
    public final Id.b<Executor> r;
    public final long s;
    public final c.a.Ea t;
    public final Stopwatch u;
    public boolean v;
    public boolean w;
    public Executor x;
    public final boolean y;
    public final c.a.ia.g z;

    @VisibleForTesting
    public interface a {
    }

    protected static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.a.za f1108a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<c.a.B> f1109b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.a.ia.b f1110c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public C0086b f1111d;

        public /* synthetic */ b(Oa oa) {
        }
    }

    private enum c implements a {
        INSTANCE;

        public List<InetAddress> a(String str) {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.ia.d f1114a;

        public d(c.a.ia.d dVar) {
            Preconditions.checkNotNull(dVar, "savedListener");
            this.f1114a = dVar;
        }

        /* JADX WARN: Code duplicated, block: B:58:0x00f6  */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b bVar;
            Throwable th;
            b bVar2;
            boolean z;
            b bVarA;
            IOException e2;
            c.a.Ea ea;
            Qa qa;
            List<c.a.B> listSingletonList;
            if (Pa.f1102a.isLoggable(Level.FINER)) {
                Logger logger = Pa.f1102a;
                StringBuilder sbA = b.a.a.a.a.a("Attempting DNS resolution of ");
                sbA.append(Pa.this.p);
                logger.finer(sbA.toString());
            }
            c.a.ia.b bVar3 = null;
            try {
                try {
                    c.a.B bC = Pa.c(Pa.this);
                    List<c.a.B> listEmptyList = Collections.emptyList();
                    C0086b c0086b = C0086b.f876a;
                    try {
                        if (bC == null) {
                            bVarA = Pa.this.a(false);
                            try {
                                if (bVarA.f1108a != null) {
                                    this.f1114a.a(bVarA.f1108a);
                                    z = bVarA.f1108a == null;
                                    ea = Pa.this.t;
                                    qa = new Qa(this, z);
                                } else {
                                    if (bVarA.f1109b != null) {
                                        listEmptyList = bVarA.f1109b;
                                    }
                                    bVar3 = bVarA.f1110c != null ? bVarA.f1110c : null;
                                    if (bVarA.f1111d != null) {
                                        c0086b = bVarA.f1111d;
                                    }
                                    List<c.a.B> list = listEmptyList;
                                    bVar2 = bVarA;
                                    listSingletonList = list;
                                }
                            } catch (IOException e3) {
                                e2 = e3;
                                this.f1114a.a(c.a.za.l.b("Unable to resolve host " + Pa.this.p).b(e2));
                                z = bVarA == null && bVarA.f1108a == null;
                                ea = Pa.this.t;
                                qa = new Qa(this, z);
                            }
                            b.a.a.a.a.a(qa, "runnable is null", ea.f796b, qa, ea);
                        }
                        if (Pa.f1102a.isLoggable(Level.FINER)) {
                            Pa.f1102a.finer("Using proxy address " + bC);
                        }
                        listSingletonList = Collections.singletonList(bC);
                        bVar2 = null;
                        this.f1114a.a(new c.a.ia.f(listSingletonList, c0086b, bVar3));
                        z = bVar2 != null && bVar2.f1108a == null;
                        ea = Pa.this.t;
                        qa = new Qa(this, z);
                    } catch (IOException e4) {
                        e = e4;
                        e2 = e;
                        bVarA = bVar2;
                        this.f1114a.a(c.a.za.l.b("Unable to resolve host " + Pa.this.p).b(e2));
                        if (bVarA == null) {
                        }
                        ea = Pa.this.t;
                        qa = new Qa(this, z);
                    } catch (Throwable th2) {
                        th = th2;
                        bVar = bVar2;
                        z = bVar != null && bVar.f1108a == null;
                        c.a.Ea ea2 = Pa.this.t;
                        Qa qa2 = new Qa(this, z);
                        b.a.a.a.a.a(qa2, "runnable is null", ea2.f796b, qa2, ea2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e5) {
                e = e5;
                bVar2 = null;
            } catch (Throwable th4) {
                bVar = null;
                th = th4;
            }
            b.a.a.a.a.a(qa, "runnable is null", ea.f796b, qa, ea);
        }
    }

    @VisibleForTesting
    public interface e {
        List<String> a(String str);
    }

    interface f {
        e a();

        Throwable b();
    }

    static {
        f fVar = null;
        try {
            try {
                try {
                    f fVar2 = (f) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, Pa.class.getClassLoader()).asSubclass(f.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (fVar2.b() != null) {
                        f1102a.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", fVar2.b());
                    } else {
                        fVar = fVar2;
                    }
                } catch (Exception e2) {
                    f1102a.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", (Throwable) e2);
                }
            } catch (Exception e3) {
                f1102a.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", (Throwable) e3);
            }
        } catch (ClassCastException e4) {
            f1102a.log(Level.FINE, "Unable to cast JndiResourceResolverFactory, skipping.", (Throwable) e4);
        } catch (ClassNotFoundException e5) {
            f1102a.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", (Throwable) e5);
        }
        i = fVar;
    }

    public Pa(String str, String str2, c.a.ia.a aVar, Id.b<Executor> bVar, Stopwatch stopwatch, boolean z) {
        Preconditions.checkNotNull(aVar, "args");
        this.r = bVar;
        StringBuilder sb = new StringBuilder();
        sb.append("//");
        Preconditions.checkNotNull(str2, "name");
        sb.append(str2);
        URI uriCreate = URI.create(sb.toString());
        Preconditions.checkArgument(uriCreate.getHost() != null, "Invalid DNS name: %s", str2);
        String authority = uriCreate.getAuthority();
        Preconditions.checkNotNull(authority, "nameUri (%s) doesn't have an authority", uriCreate);
        this.o = authority;
        this.p = uriCreate.getHost();
        if (uriCreate.getPort() == -1) {
            this.q = aVar.f1855a;
        } else {
            this.q = uriCreate.getPort();
        }
        c.a.oa oaVar = aVar.f1856b;
        Preconditions.checkNotNull(oaVar, "proxyDetector");
        this.k = oaVar;
        long nanos = 0;
        if (!z) {
            String property = System.getProperty("networkaddress.cache.ttl");
            long j2 = 30;
            if (property != null) {
                try {
                    j2 = Long.parseLong(property);
                } catch (NumberFormatException unused) {
                    f1102a.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{"networkaddress.cache.ttl", property, 30L});
                }
            }
            nanos = j2 > 0 ? TimeUnit.SECONDS.toNanos(j2) : j2;
        }
        this.s = nanos;
        Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.u = stopwatch;
        c.a.Ea ea = aVar.f1857c;
        Preconditions.checkNotNull(ea, "syncContext");
        this.t = ea;
        this.x = aVar.g;
        this.y = this.x == null;
        c.a.ia.g gVar = aVar.f1858d;
        Preconditions.checkNotNull(gVar, "serviceConfigParser");
        this.z = gVar;
    }

    public static /* synthetic */ c.a.B c(Pa pa) {
        c.a.na naVarA = pa.k.a(InetSocketAddress.createUnresolved(pa.p, pa.q));
        if (naVarA != null) {
            return new c.a.B(naVarA);
        }
        return null;
    }

    @Override // c.a.ia
    public void b() {
        Preconditions.checkState(this.B != null, "not started");
        d();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0028  */
    public final void d() {
        boolean z;
        if (this.A || this.w) {
            return;
        }
        if (this.v) {
            long j2 = this.s;
            if (j2 == 0 || (j2 > 0 && this.u.elapsed(TimeUnit.NANOSECONDS) > this.s)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        if (z) {
            this.A = true;
            this.x.execute(new d(this.B));
        }
    }

    public final List<c.a.B> e() {
        Exception e2 = null;
        try {
            try {
                List<InetAddress> listA = ((c) this.m).a(this.p);
                ArrayList arrayList = new ArrayList(listA.size());
                Iterator<InetAddress> it = listA.iterator();
                while (it.hasNext()) {
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(it.next(), this.q);
                    arrayList.add(new c.a.B(Collections.singletonList(inetSocketAddress), C0086b.f876a));
                }
                return Collections.unmodifiableList(arrayList);
            } catch (Exception e3) {
                e2 = e3;
                Throwables.throwIfUnchecked(e2);
                throw new RuntimeException(e2);
            }
        } catch (Throwable th) {
            if (e2 != null) {
                f1102a.log(Level.FINE, "Address resolution failure", (Throwable) e2);
            }
            throw th;
        }
    }

    @Override // c.a.ia
    public String a() {
        return this.o;
    }

    @Override // c.a.ia
    public void a(c.a.ia.d dVar) {
        Preconditions.checkState(this.B == null, "already started");
        if (this.y) {
            this.x = (Executor) Id.a(this.r);
        }
        Preconditions.checkNotNull(dVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.B = dVar;
        d();
    }

    @Override // c.a.ia
    public void c() {
        if (this.w) {
            return;
        }
        this.w = true;
        Executor executor = this.x;
        if (executor == null || !this.y) {
            return;
        }
        Id.a(this.r, executor);
        this.x = null;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0074  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [c.a.b.Oa] */
    /* JADX WARN: Type inference failed for: r1v6, types: [c.a.ia$b] */
    /* JADX WARN: Type inference failed for: r1v7, types: [c.a.ia$b] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public b a(boolean z) {
        e eVarA;
        f fVar;
        c.a.ia.b bVar;
        c.a.ia.b bVarA = 0;
        bVarA = 0;
        b bVar2 = new b(bVarA);
        try {
            bVar2.f1109b = e();
        } catch (Exception e2) {
            if (!z) {
                c.a.za zaVar = c.a.za.l;
                StringBuilder sbA = b.a.a.a.a.a("Unable to resolve host ");
                sbA.append(this.p);
                bVar2.f1108a = zaVar.b(sbA.toString()).b(e2);
                return bVar2;
            }
        }
        if (h) {
            List<String> listEmptyList = Collections.emptyList();
            boolean z2 = f1107f;
            boolean z3 = g;
            String str = this.p;
            if (!z2) {
                z3 = false;
            } else if (!"localhost".equalsIgnoreCase(str)) {
                if (str.contains(Utils.APP_ID_IDENTIFICATION_SUBSTRING)) {
                    z3 = false;
                } else {
                    int i2 = 0;
                    boolean z4 = true;
                    while (i2 < str.length()) {
                        char cCharAt = str.charAt(i2);
                        if (cCharAt != '.') {
                            z4 = (z4 ? 1 : 0) & (cCharAt >= '0' && cCharAt <= '9');
                        }
                        i2++;
                        z4 = z4;
                    }
                    if (z4) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                }
            }
            if (z3) {
                eVarA = this.n.get();
                if (eVarA == null && (fVar = i) != null) {
                    eVarA = fVar.a();
                }
            } else {
                eVarA = null;
            }
            if (eVarA != null) {
                try {
                    listEmptyList = eVarA.a("_grpc_config." + this.p);
                } catch (Exception e3) {
                    f1102a.log(Level.FINE, "ServiceConfig resolution failure", (Throwable) e3);
                }
            }
            if (!listEmptyList.isEmpty()) {
                Random random = this.l;
                if (j == null) {
                    try {
                        j = InetAddress.getLocalHost().getHostName();
                    } catch (UnknownHostException e4) {
                        throw new RuntimeException(e4);
                    }
                }
                String str2 = j;
                try {
                    Iterator<Map<String, ?>> it = a(listEmptyList).iterator();
                    Map<String, ?> mapA = null;
                    while (it.hasNext()) {
                        try {
                            mapA = a(it.next(), random, str2);
                            if (mapA != null) {
                                break;
                            }
                        } catch (RuntimeException e5) {
                            bVar = new c.a.ia.b(c.a.za.f1938e.b("failed to pick service config choice").b(e5));
                        }
                    }
                    bVar = mapA == null ? null : new c.a.ia.b(mapA);
                } catch (IOException | RuntimeException e6) {
                    bVar = new c.a.ia.b(c.a.za.f1938e.b("failed to parse TXT records").b(e6));
                }
                if (bVar != null) {
                    c.a.za zaVar2 = bVar.f1861a;
                    if (zaVar2 != null) {
                        bVarA = new c.a.ia.b(zaVar2);
                    } else {
                        bVarA = this.z.a((Map) bVar.f1862b);
                    }
                }
            } else {
                f1102a.log(Level.FINE, "No TXT records found for {0}", new Object[]{this.p});
            }
            bVar2.f1110c = bVarA;
        }
        return bVar2;
    }

    @VisibleForTesting
    public static List<Map<String, ?>> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.startsWith("grpc_config=")) {
                f1102a.log(Level.FINE, "Ignoring non service config {0}", new Object[]{str});
            } else {
                Object objA = Mb.a(str.substring(12));
                if (objA instanceof List) {
                    List list2 = (List) objA;
                    Nb.a((List<?>) list2);
                    arrayList.addAll(list2);
                } else {
                    throw new ClassCastException(b.a.a.a.a.a("wrong type ", objA));
                }
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    public static Map<String, ?> a(Map<String, ?> map, Random random, String str) {
        boolean z;
        boolean z2;
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Verify.verify(f1103b.contains(entry.getKey()), "Bad key: %s", entry);
        }
        List<String> listD = Nb.d(map, "clientLanguage");
        if (listD != null && !listD.isEmpty()) {
            Iterator<String> it = listD.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                if ("java".equalsIgnoreCase(it.next())) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                return null;
            }
        }
        Double dE = Nb.e(map, "percentage");
        if (dE != null) {
            int iIntValue = dE.intValue();
            Verify.verify(iIntValue >= 0 && iIntValue <= 100, "Bad percentage: %s", dE);
            if (random.nextInt(100) >= iIntValue) {
                return null;
            }
        }
        List<String> listD2 = Nb.d(map, "clientHostname");
        if (listD2 != null && !listD2.isEmpty()) {
            Iterator<String> it2 = listD2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                if (it2.next().equals(str)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return null;
            }
        }
        Map<String, ?> mapG = Nb.g(map, "serviceConfig");
        if (mapG != null) {
            return mapG;
        }
        throw new VerifyException(String.format("key '%s' missing in '%s'", map, "serviceConfig"));
    }
}

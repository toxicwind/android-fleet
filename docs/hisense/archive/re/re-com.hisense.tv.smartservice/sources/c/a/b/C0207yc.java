package c.a.b;

import c.a.AbstractC0213c;
import c.a.AbstractC0218e;
import c.a.AbstractC0224h;
import c.a.C0240y;
import c.a.InterfaceC0227k;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.ws.RealWebSocket;

/* JADX INFO: renamed from: c.a.b.yc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0207yc extends c.a.Y<C0207yc> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1517a = Logger.getLogger(C0207yc.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @VisibleForTesting
    public static final long f1518b = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f1519c = TimeUnit.SECONDS.toMillis(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Jc<? extends Executor> f1520d = new Jd(C0149mb.n);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0240y f1521e = C0240y.f1929b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c.a.r f1522f = c.a.r.f1897a;
    public c.a.K A;
    public int B;
    public Map<String, ?> C;
    public boolean D;
    public AbstractC0213c E;
    public c.a.oa F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public final b L;
    public final a M;
    public Jc<? extends Executor> g;
    public Jc<? extends Executor> h;
    public final List<InterfaceC0227k> i;
    public final c.a.la j;
    public c.a.ia.c k;
    public final String l;
    public final AbstractC0224h m;
    public final AbstractC0218e n;
    public String o;
    public String p;
    public String q;
    public boolean r;
    public C0240y s;
    public c.a.r t;
    public long u;
    public int v;
    public int w;
    public long x;
    public long y;
    public boolean z;

    /* JADX INFO: renamed from: c.a.b.yc$a */
    public interface a {
        int a();
    }

    /* JADX INFO: renamed from: c.a.b.yc$b */
    public interface b {
    }

    /* JADX INFO: renamed from: c.a.b.yc$c */
    private static final class c implements a {
        public /* synthetic */ c(C0203xc c0203xc) {
        }

        @Override // c.a.b.C0207yc.a
        public int a() {
            return 443;
        }
    }

    public C0207yc(String str, b bVar, a aVar) {
        Jc<? extends Executor> jc = f1520d;
        this.g = jc;
        this.h = jc;
        this.i = new ArrayList();
        this.j = c.a.la.a();
        this.k = this.j.f1868c;
        this.q = "pick_first";
        this.s = f1521e;
        this.t = f1522f;
        this.u = f1518b;
        this.v = 5;
        this.w = 5;
        this.x = RealWebSocket.MAX_QUEUE_SIZE;
        this.y = FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES;
        this.z = true;
        this.A = c.a.K.f828b;
        this.D = true;
        this.G = true;
        this.H = true;
        this.I = true;
        this.J = false;
        this.K = true;
        Preconditions.checkNotNull(str, "target");
        this.l = str;
        C0203xc c0203xc = null;
        this.n = null;
        Preconditions.checkNotNull(bVar, "clientTransportFactoryBuilder");
        this.L = bVar;
        if (aVar != null) {
            this.M = aVar;
        } else {
            this.M = new c(c0203xc);
        }
    }

    @Override // c.a.Y
    public c.a.X a() {
        InterfaceC0227k interfaceC0227k;
        InterfaceC0227k interfaceC0227k2;
        P pE = c.a.c.h.this.e();
        Sa.a aVar = new Sa.a();
        Jd jd = new Jd(C0149mb.n);
        Supplier<Stopwatch> supplier = C0149mb.p;
        ArrayList arrayList = new ArrayList(this.i);
        if (this.G) {
            try {
                interfaceC0227k2 = (InterfaceC0227k) Class.forName("io.grpc.census.InternalCensusStatsAccessor").getDeclaredMethod("getClientInterceptor", Boolean.TYPE, Boolean.TYPE, Boolean.TYPE).invoke(null, Boolean.valueOf(this.H), Boolean.valueOf(this.I), Boolean.valueOf(this.J));
            } catch (ClassNotFoundException e2) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e2);
                interfaceC0227k2 = null;
            } catch (IllegalAccessException e3) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e3);
                interfaceC0227k2 = null;
            } catch (NoSuchMethodException e4) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e4);
                interfaceC0227k2 = null;
            } catch (InvocationTargetException e5) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e5);
                interfaceC0227k2 = null;
            }
            if (interfaceC0227k2 != null) {
                arrayList.add(0, interfaceC0227k2);
            }
        }
        if (this.K) {
            try {
                interfaceC0227k = (InterfaceC0227k) Class.forName("io.grpc.census.InternalCensusTracingAccessor").getDeclaredMethod("getClientInterceptor", new Class[0]).invoke(null, new Object[0]);
            } catch (ClassNotFoundException e6) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e6);
                interfaceC0227k = null;
            } catch (IllegalAccessException e7) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e7);
                interfaceC0227k = null;
            } catch (NoSuchMethodException e8) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e8);
                interfaceC0227k = null;
            } catch (InvocationTargetException e9) {
                f1517a.log(Level.FINE, "Unable to apply census stats", (Throwable) e9);
                interfaceC0227k = null;
            }
            if (interfaceC0227k != null) {
                arrayList.add(0, interfaceC0227k);
            }
        }
        return new C0211zc(new C0130ic(this, pE, aVar, jd, supplier, arrayList, Pd.f1120a));
    }
}

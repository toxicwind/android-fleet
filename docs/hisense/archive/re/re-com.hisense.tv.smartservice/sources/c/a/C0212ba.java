package c.a;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.ba, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0212ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1548a = Logger.getLogger(C0212ba.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static C0212ba f1549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet<Z> f1550c = new LinkedHashSet<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<Z> f1551d = Collections.emptyList();

    /* JADX INFO: renamed from: c.a.ba$a */
    private static final class a implements xa<Z> {
        public /* synthetic */ a(C0085aa c0085aa) {
        }

        @Override // c.a.xa
        public boolean a(Z z) {
            z.a();
            return true;
        }

        @Override // c.a.xa
        public int b(Z z) {
            return z.b();
        }
    }

    @VisibleForTesting
    public static List<Class<?>> b() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("c.a.c.j"));
        } catch (ClassNotFoundException e2) {
            f1548a.log(Level.FINE, "Unable to find OkHttpChannelProvider", (Throwable) e2);
        }
        try {
            arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
        } catch (ClassNotFoundException e3) {
            f1548a.log(Level.FINE, "Unable to find NettyChannelProvider", (Throwable) e3);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final synchronized void a(Z z) {
        z.a();
        Preconditions.checkArgument(true, "isAvailable() returned false");
        this.f1550c.add(z);
    }

    @VisibleForTesting
    public synchronized List<Z> c() {
        return this.f1551d;
    }

    public final synchronized void d() {
        ArrayList arrayList = new ArrayList(this.f1550c);
        Collections.sort(arrayList, Collections.reverseOrder(new C0085aa(this)));
        this.f1551d = Collections.unmodifiableList(arrayList);
    }

    public static synchronized C0212ba a() {
        if (f1549b == null) {
            List<Z> listA = b.b.a.c.a.a(Z.class, b(), Z.class.getClassLoader(), new a(null));
            f1549b = new C0212ba();
            for (Z z : listA) {
                f1548a.fine("Service loader found " + z);
                z.a();
                f1549b.a(z);
            }
            f1549b.d();
        }
        return f1549b;
    }
}

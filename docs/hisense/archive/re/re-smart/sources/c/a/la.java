package c.a;

import androidx.core.os.EnvironmentCompat;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class la {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1866a = Logger.getLogger(la.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static la f1867b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ia.c f1868c = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1869d = EnvironmentCompat.MEDIA_UNKNOWN;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashSet<ja> f1870e = new LinkedHashSet<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImmutableMap<String, ja> f1871f = ImmutableMap.of();

    private static final class b implements xa<ja> {
        public /* synthetic */ b(ka kaVar) {
        }

        @Override // c.a.xa
        public boolean a(ja jaVar) {
            jaVar.b();
            return true;
        }

        @Override // c.a.xa
        public int b(ja jaVar) {
            jaVar.c();
            return 5;
        }
    }

    @VisibleForTesting
    public synchronized Map<String, ja> b() {
        return this.f1871f;
    }

    public final synchronized void c() {
        HashMap map = new HashMap();
        byte b2 = -2147483648;
        String strA = EnvironmentCompat.MEDIA_UNKNOWN;
        for (ja jaVar : this.f1870e) {
            String strA2 = jaVar.a();
            ja jaVar2 = (ja) map.get(strA2);
            if (jaVar2 != null) {
            } else {
                map.put(strA2, jaVar);
            }
            if (b2 < 5) {
                strA = jaVar.a();
                b2 = 5;
            }
        }
        this.f1871f = ImmutableMap.copyOf((Map) map);
        this.f1869d = strA;
    }

    private final class a extends ia.c {
        public /* synthetic */ a(ka kaVar) {
        }

        @Override // c.a.ia.c
        public ia a(URI uri, ia.a aVar) {
            ja jaVar = la.this.b().get(uri.getScheme());
            if (jaVar == null) {
                return null;
            }
            return jaVar.a(uri, aVar);
        }

        @Override // c.a.ia.c
        public String a() {
            String str;
            synchronized (la.this) {
                str = la.this.f1869d;
            }
            return str;
        }
    }

    public final synchronized void a(ja jaVar) {
        jaVar.b();
        Preconditions.checkArgument(true, "isAvailable() returned false");
        this.f1870e.add(jaVar);
    }

    public static synchronized la a() {
        if (f1867b == null) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(Class.forName("c.a.b.Ra"));
            } catch (ClassNotFoundException e2) {
                f1866a.log(Level.FINE, "Unable to find DNS NameResolver", (Throwable) e2);
            }
            List<ja> listA = b.b.a.c.a.a(ja.class, Collections.unmodifiableList(arrayList), ja.class.getClassLoader(), new b(null));
            if (listA.isEmpty()) {
                f1866a.warning("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
            }
            f1867b = new la();
            for (ja jaVar : listA) {
                f1866a.fine("Service loader found " + jaVar);
                jaVar.b();
                f1867b.a(jaVar);
            }
            f1867b.c();
        }
        return f1867b;
    }
}

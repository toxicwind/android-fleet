package c.a;

import com.google.common.base.Preconditions;
import com.ju.lib.datareport.StrategyBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f857a = Logger.getLogger(W.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static W f858b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Iterable<Class<?>> f859c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet<V> f860d = new LinkedHashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap<String, V> f861e = new LinkedHashMap<>();

    private static final class a implements xa<V> {
        @Override // c.a.xa
        public boolean a(V v) {
            return v.c();
        }

        @Override // c.a.xa
        public int b(V v) {
            return v.b();
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("c.a.b.Oc"));
        } catch (ClassNotFoundException e2) {
            f857a.log(Level.WARNING, "Unable to find pick-first LoadBalancer", (Throwable) e2);
        }
        try {
            arrayList.add(Class.forName("c.a.f.c"));
        } catch (ClassNotFoundException e3) {
            f857a.log(Level.FINE, "Unable to find round-robin LoadBalancer", (Throwable) e3);
        }
        f859c = Collections.unmodifiableList(arrayList);
    }

    public final synchronized void a(V v) {
        Preconditions.checkArgument(v.c(), "isAvailable() returned false");
        this.f860d.add(v);
    }

    public final synchronized void b() {
        this.f861e.clear();
        for (V v : this.f860d) {
            String strA = v.a();
            V v2 = this.f861e.get(strA);
            if (v2 == null || v2.b() < v.b()) {
                this.f861e.put(strA, v);
            }
        }
    }

    public static synchronized W a() {
        if (f858b == null) {
            List<V> listA = b.b.a.c.a.a(V.class, f859c, V.class.getClassLoader(), new a());
            f858b = new W();
            for (V v : listA) {
                f857a.fine("Service loader found " + v);
                if (v.c()) {
                    f858b.a(v);
                }
            }
            f858b.b();
        }
        return f858b;
    }

    public synchronized V a(String str) {
        LinkedHashMap<String, V> linkedHashMap;
        linkedHashMap = this.f861e;
        Preconditions.checkNotNull(str, StrategyBean.Columns.POLICY);
        return linkedHashMap.get(str);
    }
}

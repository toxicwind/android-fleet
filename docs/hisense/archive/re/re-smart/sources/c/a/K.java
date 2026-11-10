package c.a;

import com.google.common.annotations.VisibleForTesting;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f827a = Logger.getLogger(K.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final K f828b = new K();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentNavigableMap<Long, N<Object>> f829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ConcurrentMap<Long, N<Object>> f830d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ConcurrentMap<Long, N<Object>> f831e;

    @VisibleForTesting
    public K() {
        new ConcurrentSkipListMap();
        this.f829c = new ConcurrentSkipListMap();
        this.f830d = new ConcurrentHashMap();
        this.f831e = new ConcurrentHashMap();
        new ConcurrentHashMap();
    }

    public static <T extends N<?>> void a(Map<Long, T> map, T t) {
        map.put(Long.valueOf(t.a().f839d), t);
    }

    public static <T extends N<?>> void b(Map<Long, T> map, T t) {
        map.remove(Long.valueOf(t.a().f839d));
    }
}

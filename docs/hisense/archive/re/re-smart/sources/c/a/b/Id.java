package c.a.b;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class Id {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Id f1024a = new Id(new Gd());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final IdentityHashMap<b<?>, a> f1025b = new IdentityHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1026c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ScheduledExecutorService f1027d;

    /* JADX INFO: Access modifiers changed from: private */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1028a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1029b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ScheduledFuture<?> f1030c;

        public a(Object obj) {
            this.f1028a = obj;
        }
    }

    public interface b<T> {
        T a();

        void a(T t);
    }

    interface c {
    }

    public Id(c cVar) {
        this.f1026c = cVar;
    }

    public synchronized <T> T b(b<T> bVar) {
        a aVar;
        aVar = this.f1025b.get(bVar);
        if (aVar == null) {
            aVar = new a(bVar.a());
            this.f1025b.put(bVar, aVar);
        }
        if (aVar.f1030c != null) {
            aVar.f1030c.cancel(false);
            aVar.f1030c = null;
        }
        aVar.f1029b++;
        return (T) aVar.f1028a;
    }

    public static <T> T a(b<T> bVar) {
        return (T) f1024a.b(bVar);
    }

    public static <T> T a(b<T> bVar, T t) {
        f1024a.b(bVar, t);
        return null;
    }

    public synchronized <T> T b(b<T> bVar, T t) {
        a aVar = this.f1025b.get(bVar);
        if (aVar != null) {
            Preconditions.checkArgument(t == aVar.f1028a, "Releasing the wrong instance");
            Preconditions.checkState(aVar.f1029b > 0, "Refcount has already reached zero");
            aVar.f1029b--;
            if (aVar.f1029b == 0) {
                Preconditions.checkState(aVar.f1030c == null, "Destroy task already scheduled");
                if (this.f1027d == null) {
                    this.f1027d = ((Gd) this.f1026c).a();
                }
                aVar.f1030c = this.f1027d.schedule(new Sb(new Hd(this, aVar, bVar, t)), 1L, TimeUnit.SECONDS);
            }
        } else {
            throw new IllegalArgumentException("No cached instance found for " + bVar);
        }
        return null;
    }
}

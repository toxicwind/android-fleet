package c.a;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ia {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1855a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final oa f1856b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Ea f1857c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final g f1858d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ScheduledExecutorService f1859e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final AbstractC0225i f1860f;
        public final Executor g;

        public /* synthetic */ a(Integer num, oa oaVar, Ea ea, g gVar, ScheduledExecutorService scheduledExecutorService, AbstractC0225i abstractC0225i, Executor executor, ha haVar) {
            Preconditions.checkNotNull(num, "defaultPort not set");
            this.f1855a = num.intValue();
            Preconditions.checkNotNull(oaVar, "proxyDetector not set");
            this.f1856b = oaVar;
            Preconditions.checkNotNull(ea, "syncContext not set");
            this.f1857c = ea;
            Preconditions.checkNotNull(gVar, "serviceConfigParser not set");
            this.f1858d = gVar;
            this.f1859e = scheduledExecutorService;
            this.f1860f = abstractC0225i;
            this.g = executor;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("defaultPort", this.f1855a).add("proxyDetector", this.f1856b).add("syncContext", this.f1857c).add("serviceConfigParser", this.f1858d).add("scheduledExecutorService", this.f1859e).add("channelLogger", this.f1860f).add("executor", this.g).toString();
        }
    }

    public static abstract class c {
        public abstract ia a(URI uri, a aVar);

        public abstract String a();
    }

    public static abstract class d implements e {
        public abstract void a(f fVar);

        public abstract void a(za zaVar);
    }

    public interface e {
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<B> f1863a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0086b f1864b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b f1865c;

        public f(List<B> list, C0086b c0086b, b bVar) {
            this.f1863a = Collections.unmodifiableList(new ArrayList(list));
            Preconditions.checkNotNull(c0086b, "attributes");
            this.f1864b = c0086b;
            this.f1865c = bVar;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Objects.equal(this.f1863a, fVar.f1863a) && Objects.equal(this.f1864b, fVar.f1864b) && Objects.equal(this.f1865c, fVar.f1865c);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f1863a, this.f1864b, this.f1865c});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("addresses", this.f1863a).add("attributes", this.f1864b).add("serviceConfig", this.f1865c).toString();
        }
    }

    public static abstract class g {
        public abstract b a(Map<String, ?> map);
    }

    public abstract String a();

    public abstract void a(d dVar);

    public abstract void b();

    public abstract void c();

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final za f1861a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1862b;

        public b(Object obj) {
            Preconditions.checkNotNull(obj, "config");
            this.f1862b = obj;
            this.f1861a = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return Objects.equal(this.f1861a, bVar.f1861a) && Objects.equal(this.f1862b, bVar.f1862b);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f1861a, this.f1862b});
        }

        public String toString() {
            return this.f1862b != null ? MoreObjects.toStringHelper(this).add("config", this.f1862b).toString() : MoreObjects.toStringHelper(this).add(DynamicDomainHelper.DYNAMIC_ERROR, this.f1861a).toString();
        }

        public b(za zaVar) {
            this.f1862b = null;
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            this.f1861a = zaVar;
            Preconditions.checkArgument(!zaVar.c(), "cannot use OK status: %s", zaVar);
        }
    }
}

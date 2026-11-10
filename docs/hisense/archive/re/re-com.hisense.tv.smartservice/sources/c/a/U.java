package c.a;

import androidx.core.app.NotificationCompat;
import c.a.b.C0130ic;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0086b.C0007b<Map<String, ?>> f844a = new C0086b.C0007b<>("health-checking-config");

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<B> f845a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0086b f846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object[][] f847c;

        public /* synthetic */ a(List list, C0086b c0086b, Object[][] objArr, T t) {
            Preconditions.checkNotNull(list, "addresses are not set");
            this.f845a = list;
            Preconditions.checkNotNull(c0086b, "attrs");
            this.f846b = c0086b;
            Preconditions.checkNotNull(objArr, "customOptions");
            this.f847c = objArr;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("addrs", this.f845a).add("attrs", this.f846b).add("customOptions", Arrays.deepToString(this.f847c)).toString();
        }
    }

    public static abstract class b {
        public abstract U a(c cVar);
    }

    public static abstract class c {
        public g a(a aVar) {
            throw new UnsupportedOperationException();
        }

        public abstract void a(EnumC0234s enumC0234s, h hVar);

        public void a() {
            throw new UnsupportedOperationException();
        }
    }

    public static abstract class e {
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<B> f853a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0086b f854b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object f855c;

        public /* synthetic */ f(List list, C0086b c0086b, Object obj, T t) {
            Preconditions.checkNotNull(list, "addresses");
            this.f853a = Collections.unmodifiableList(new ArrayList(list));
            Preconditions.checkNotNull(c0086b, "attributes");
            this.f854b = c0086b;
            this.f855c = obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Objects.equal(this.f853a, fVar.f853a) && Objects.equal(this.f854b, fVar.f854b) && Objects.equal(this.f855c, fVar.f855c);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f853a, this.f854b, this.f855c});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("addresses", this.f853a).add("attributes", this.f854b).add("loadBalancingPolicyConfig", this.f855c).toString();
        }
    }

    public static abstract class g {
        public void a(i iVar) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public abstract void b();

        public abstract void c();

        public final B a() {
            C0130ic.m mVar = (C0130ic.m) this;
            mVar.k.w.b();
            Preconditions.checkState(mVar.h, "not started");
            List<B> list = mVar.f1312f;
            Preconditions.checkState(list.size() == 1, "%s does not have exactly one group", list);
            return list.get(0);
        }

        public void a(List<B> list) {
            throw new UnsupportedOperationException();
        }
    }

    public static abstract class h {
        public abstract d a(e eVar);

        @Deprecated
        public void a() {
        }
    }

    public interface i {
        void a(C0235t c0235t);
    }

    public abstract void a(f fVar);

    public abstract void a(za zaVar);

    public boolean a() {
        return false;
    }

    public void b() {
    }

    public abstract void c();

    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f848a = new d(null, null, za.f1936c, false);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f849b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final AbstractC0230n.a f850c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final za f851d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f852e;

        public d(g gVar, AbstractC0230n.a aVar, za zaVar, boolean z) {
            this.f849b = gVar;
            this.f850c = aVar;
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            this.f851d = zaVar;
            this.f852e = z;
        }

        public static d a(g gVar) {
            Preconditions.checkNotNull(gVar, "subchannel");
            return new d(gVar, null, za.f1936c, false);
        }

        public static d b(za zaVar) {
            Preconditions.checkArgument(!zaVar.c(), "error status shouldn't be OK");
            return new d(null, null, zaVar, false);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return Objects.equal(this.f849b, dVar.f849b) && Objects.equal(this.f851d, dVar.f851d) && Objects.equal(this.f850c, dVar.f850c) && this.f852e == dVar.f852e;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f849b, this.f851d, this.f850c, Boolean.valueOf(this.f852e)});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("subchannel", this.f849b).add("streamTracerFactory", this.f850c).add(NotificationCompat.CATEGORY_STATUS, this.f851d).add("drop", this.f852e).toString();
        }

        public static d a(za zaVar) {
            Preconditions.checkArgument(!zaVar.c(), "drop status shouldn't be OK");
            return new d(null, null, zaVar, true);
        }
    }
}

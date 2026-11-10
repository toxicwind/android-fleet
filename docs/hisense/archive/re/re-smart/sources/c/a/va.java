package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class va {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Collection<C0223ga<?, ?>> f1916b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1917c;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1918a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<C0223ga<?, ?>> f1919b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f1920c;

        public /* synthetic */ a(String str, ua uaVar) {
            Preconditions.checkNotNull(str, "name");
            this.f1918a = str;
        }
    }

    public va(a aVar) {
        this.f1915a = aVar.f1918a;
        String str = this.f1915a;
        List<C0223ga<?, ?>> list = aVar.f1919b;
        HashSet hashSet = new HashSet(list.size());
        for (C0223ga<?, ?> c0223ga : list) {
            Preconditions.checkNotNull(c0223ga, FirebaseAnalytics.Param.METHOD);
            String str2 = c0223ga.f1834c;
            Preconditions.checkArgument(str.equals(str2), "service names %s != %s", str2, str);
            Preconditions.checkArgument(hashSet.add(c0223ga.f1833b), "duplicate name %s", c0223ga.f1833b);
        }
        this.f1916b = Collections.unmodifiableList(new ArrayList(aVar.f1919b));
        this.f1917c = aVar.f1920c;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.f1915a).add("schemaDescriptor", this.f1917c).add("methods", this.f1916b).omitNullValues().toString();
    }
}

package c.a.b;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Fd {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f967a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, ?> f968b;

        public a(String str, Map<String, ?> map) {
            Preconditions.checkNotNull(str, "policyName");
            this.f967a = str;
            Preconditions.checkNotNull(map, "rawConfigValue");
            this.f968b = map;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f967a.equals(aVar.f967a) && this.f968b.equals(aVar.f968b);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f967a, this.f968b});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("policyName", this.f967a).add("rawConfigValue", this.f968b).toString();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.V f969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f970b;

        public b(c.a.V v, Object obj) {
            Preconditions.checkNotNull(v, "provider");
            this.f969a = v;
            this.f970b = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return Objects.equal(this.f969a, bVar.f969a) && Objects.equal(this.f970b, bVar.f970b);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f969a, this.f970b});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("provider", this.f969a).add("config", this.f970b).toString();
        }
    }

    public static Set<c.a.za.a> a(Map<String, ?> map, String str) {
        c.a.za.a aVarValueOf;
        List<?> listB = Nb.b(map, str);
        if (listB == null) {
            return null;
        }
        EnumSet enumSetNoneOf = EnumSet.noneOf(c.a.za.a.class);
        for (Object obj : listB) {
            if (obj instanceof Double) {
                Double d2 = (Double) obj;
                int iIntValue = d2.intValue();
                Verify.verify(((double) iIntValue) == d2.doubleValue(), "Status code %s is not integral", obj);
                aVarValueOf = c.a.za.a(iIntValue).p;
                Verify.verify(aVarValueOf.s == d2.intValue(), "Status code %s is not valid", obj);
            } else {
                if (!(obj instanceof String)) {
                    throw new VerifyException("Can not convert status code " + obj + " to Status.Code, because its type is " + obj.getClass());
                }
                try {
                    aVarValueOf = c.a.za.a.valueOf((String) obj);
                } catch (IllegalArgumentException e2) {
                    throw new VerifyException(b.a.a.a.a.a("Status code ", obj, " is not valid"), e2);
                }
            }
            enumSetNoneOf.add(aVarValueOf);
        }
        return Collections.unmodifiableSet(enumSetNoneOf);
    }

    @VisibleForTesting
    public static List<Map<String, ?>> a(Map<String, ?> map) {
        String strH;
        ArrayList arrayList = new ArrayList();
        if (map.containsKey("loadBalancingConfig")) {
            arrayList.addAll(Nb.c(map, "loadBalancingConfig"));
        }
        if (arrayList.isEmpty() && (strH = Nb.h(map, "loadBalancingPolicy")) != null) {
            arrayList.add(Collections.singletonMap(strH.toLowerCase(Locale.ROOT), Collections.emptyMap()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static List<a> a(List<Map<String, ?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map<String, ?> map : list) {
            if (map.size() == 1) {
                String key = map.entrySet().iterator().next().getKey();
                arrayList.add(new a(key, Nb.g(map, key)));
            } else {
                StringBuilder sbA = b.a.a.a.a.a("There are ");
                sbA.append(map.size());
                sbA.append(" fields in a LoadBalancingConfig object. Exactly one is expected. Config=");
                sbA.append(map);
                throw new RuntimeException(sbA.toString());
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static c.a.ia.b a(List<a> list, c.a.W w) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : list) {
            String str = aVar.f967a;
            c.a.V vA = w.a(str);
            if (vA == null) {
                arrayList.add(str);
            } else {
                if (!arrayList.isEmpty()) {
                    Logger.getLogger(Fd.class.getName()).log(Level.FINEST, "{0} specified by Service Config are not available", arrayList);
                }
                c.a.ia.b bVarA = vA.a(aVar.f968b);
                return bVarA.f1861a != null ? bVarA : new c.a.ia.b(new b(vA, bVarA.f1862b));
            }
        }
        return new c.a.ia.b(c.a.za.f1938e.b("None of " + arrayList + " specified by Service Config are available."));
    }
}

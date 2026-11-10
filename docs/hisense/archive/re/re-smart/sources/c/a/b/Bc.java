package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.C0220f;
import c.a.C0223ga;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class Bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, a> f902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, a> f903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AbstractC0171qd.k f904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Map<String, ?> f906f;

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0220f.a<a> f907a = C0220f.a.a("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Long f908b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Boolean f909c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Integer f910d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Integer f911e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Ad f912f;
        public final C0164pb g;

        public a(Map<String, ?> map, boolean z, int i, int i2) {
            Ad ad;
            C0164pb c0164pb;
            this.f908b = Nb.i(map, Tools.KEY_TIMEOUT);
            this.f909c = Nb.a(map, "waitForReady");
            this.f910d = Nb.f(map, "maxResponseMessageBytes");
            Integer num = this.f910d;
            if (num != null) {
                Preconditions.checkArgument(num.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", this.f910d);
            }
            this.f911e = Nb.f(map, "maxRequestMessageBytes");
            Integer num2 = this.f911e;
            if (num2 != null) {
                Preconditions.checkArgument(num2.intValue() >= 0, "maxOutboundMessageSize %s exceeds bounds", this.f911e);
            }
            Map<String, ?> mapG = z ? Nb.g(map, "retryPolicy") : null;
            if (mapG == null) {
                ad = null;
            } else {
                Integer numF = Nb.f(mapG, "maxAttempts");
                Preconditions.checkNotNull(numF, "maxAttempts cannot be empty");
                int iIntValue = numF.intValue();
                Preconditions.checkArgument(iIntValue >= 2, "maxAttempts must be greater than 1: %s", iIntValue);
                int iMin = Math.min(iIntValue, i);
                Long lI = Nb.i(mapG, "initialBackoff");
                Preconditions.checkNotNull(lI, "initialBackoff cannot be empty");
                long jLongValue = lI.longValue();
                Preconditions.checkArgument(jLongValue > 0, "initialBackoffNanos must be greater than 0: %s", jLongValue);
                Long lI2 = Nb.i(mapG, "maxBackoff");
                Preconditions.checkNotNull(lI2, "maxBackoff cannot be empty");
                long jLongValue2 = lI2.longValue();
                Preconditions.checkArgument(jLongValue2 > 0, "maxBackoff must be greater than 0: %s", jLongValue2);
                Double dE = Nb.e(mapG, "backoffMultiplier");
                Preconditions.checkNotNull(dE, "backoffMultiplier cannot be empty");
                double dDoubleValue = dE.doubleValue();
                Preconditions.checkArgument(dDoubleValue > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "backoffMultiplier must be greater than 0: %s", Double.valueOf(dDoubleValue));
                Long lI3 = Nb.i(mapG, "perAttemptRecvTimeout");
                Preconditions.checkArgument(lI3 == null || lI3.longValue() >= 0, "perAttemptRecvTimeout cannot be negative: %s", lI3);
                Set<c.a.za.a> setA = Fd.a(mapG, "retryableStatusCodes");
                Verify.verify(setA != null, "%s is required in retry policy", "retryableStatusCodes");
                Verify.verify(!setA.contains(c.a.za.a.OK), "%s must not contain OK", "retryableStatusCodes");
                Preconditions.checkArgument((lI3 == null && setA.isEmpty()) ? false : true, "retryableStatusCodes cannot be empty without perAttemptRecvTimeout");
                ad = new Ad(iMin, jLongValue, jLongValue2, dDoubleValue, lI3, setA);
            }
            this.f912f = ad;
            Map<String, ?> mapG2 = z ? Nb.g(map, "hedgingPolicy") : null;
            if (mapG2 == null) {
                c0164pb = null;
            } else {
                Integer numF2 = Nb.f(mapG2, "maxAttempts");
                Preconditions.checkNotNull(numF2, "maxAttempts cannot be empty");
                int iIntValue2 = numF2.intValue();
                Preconditions.checkArgument(iIntValue2 >= 2, "maxAttempts must be greater than 1: %s", iIntValue2);
                int iMin2 = Math.min(iIntValue2, i2);
                Long lI4 = Nb.i(mapG2, "hedgingDelay");
                Preconditions.checkNotNull(lI4, "hedgingDelay cannot be empty");
                long jLongValue3 = lI4.longValue();
                Preconditions.checkArgument(jLongValue3 >= 0, "hedgingDelay must not be negative: %s", jLongValue3);
                Set<c.a.za.a> setA2 = Fd.a(mapG2, "nonFatalStatusCodes");
                if (setA2 == null) {
                    setA2 = Collections.unmodifiableSet(EnumSet.noneOf(c.a.za.a.class));
                } else {
                    Verify.verify(!setA2.contains(c.a.za.a.OK), "%s must not contain OK", "nonFatalStatusCodes");
                }
                c0164pb = new C0164pb(iMin2, jLongValue3, setA2);
            }
            this.g = c0164pb;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equal(this.f908b, aVar.f908b) && Objects.equal(this.f909c, aVar.f909c) && Objects.equal(this.f910d, aVar.f910d) && Objects.equal(this.f911e, aVar.f911e) && Objects.equal(this.f912f, aVar.f912f) && Objects.equal(this.g, aVar.g);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f908b, this.f909c, this.f910d, this.f911e, this.f912f, this.g});
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("timeoutNanos", this.f908b).add("waitForReady", this.f909c).add("maxInboundMessageSize", this.f910d).add("maxOutboundMessageSize", this.f911e).add("retryPolicy", this.f912f).add("hedgingPolicy", this.g).toString();
        }
    }

    static final class b extends c.a.M {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bc f913b;

        public /* synthetic */ b(Bc bc, Ac ac) {
            this.f913b = bc;
        }

        @Override // c.a.M
        public c.a.M.a a(c.a.U.e eVar) {
            Bc bc = this.f913b;
            Preconditions.checkNotNull(bc, "config");
            Preconditions.checkState(bc != null, "config is not set");
            return new c.a.M.a(c.a.za.f1936c, bc, null, null);
        }
    }

    public Bc(a aVar, Map<String, a> map, Map<String, a> map2, AbstractC0171qd.k kVar, Object obj, Map<String, ?> map3) {
        this.f901a = aVar;
        this.f902b = Collections.unmodifiableMap(new HashMap(map));
        this.f903c = Collections.unmodifiableMap(new HashMap(map2));
        this.f904d = kVar;
        this.f905e = obj;
        this.f906f = map3 != null ? Collections.unmodifiableMap(new HashMap(map3)) : null;
    }

    public static Bc a(Map<String, ?> map, boolean z, int i, int i2, Object obj) {
        AbstractC0171qd.k kVar;
        Map<String, ?> mapG;
        AbstractC0171qd.k kVar2;
        if (z) {
            if (map == null || (mapG = Nb.g(map, "retryThrottling")) == null) {
                kVar2 = null;
            } else {
                float fFloatValue = Nb.e(mapG, "maxTokens").floatValue();
                float fFloatValue2 = Nb.e(mapG, "tokenRatio").floatValue();
                Preconditions.checkState(fFloatValue > 0.0f, "maxToken should be greater than zero");
                Preconditions.checkState(fFloatValue2 > 0.0f, "tokenRatio should be greater than zero");
                kVar2 = new AbstractC0171qd.k(fFloatValue, fFloatValue2);
            }
            kVar = kVar2;
        } else {
            kVar = null;
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        Map<String, ?> mapG2 = map == null ? null : Nb.g(map, "healthCheckConfig");
        List<Map<String, ?>> listC = Nb.c(map, "methodConfig");
        if (listC == null) {
            return new Bc(null, map2, map3, kVar, obj, mapG2);
        }
        a aVar = null;
        for (Map<String, ?> map4 : listC) {
            a aVar2 = new a(map4, z, i, i2);
            List<Map<String, ?>> listC2 = Nb.c(map4, "name");
            if (listC2 != null && !listC2.isEmpty()) {
                for (Map<String, ?> map5 : listC2) {
                    String strH = Nb.h(map5, NotificationCompat.CATEGORY_SERVICE);
                    String strH2 = Nb.h(map5, FirebaseAnalytics.Param.METHOD);
                    if (Platform.stringIsNullOrEmpty(strH)) {
                        Preconditions.checkArgument(Platform.stringIsNullOrEmpty(strH2), "missing service name for method %s", strH2);
                        Preconditions.checkArgument(aVar == null, "Duplicate default method config in service config %s", map);
                        aVar = aVar2;
                    } else if (Platform.stringIsNullOrEmpty(strH2)) {
                        Preconditions.checkArgument(!map3.containsKey(strH), "Duplicate service %s", strH);
                        map3.put(strH, aVar2);
                    } else {
                        String strA = C0223ga.a(strH, strH2);
                        Preconditions.checkArgument(!map2.containsKey(strA), "Duplicate method name %s", strA);
                        map2.put(strA, aVar2);
                    }
                }
            }
        }
        return new Bc(aVar, map2, map3, kVar, obj, mapG2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Bc.class != obj.getClass()) {
            return false;
        }
        Bc bc = (Bc) obj;
        return Objects.equal(this.f901a, bc.f901a) && Objects.equal(this.f902b, bc.f902b) && Objects.equal(this.f903c, bc.f903c) && Objects.equal(this.f904d, bc.f904d) && Objects.equal(this.f905e, bc.f905e);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f901a, this.f902b, this.f903c, this.f904d, this.f905e});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("defaultMethodConfig", this.f901a).add("serviceMethodMap", this.f902b).add("serviceMap", this.f903c).add("retryThrottling", this.f904d).add("loadBalancingConfig", this.f905e).toString();
    }

    public c.a.M a() {
        Ac ac = null;
        if (this.f903c.isEmpty() && this.f902b.isEmpty() && this.f901a == null) {
            return null;
        }
        return new b(this, ac);
    }

    public a a(C0223ga<?, ?> c0223ga) {
        a aVar = this.f902b.get(c0223ga.f1833b);
        if (aVar == null) {
            aVar = this.f903c.get(c0223ga.f1834c);
        }
        return aVar == null ? this.f901a : aVar;
    }
}

package c.a;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class ta {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1908a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final va f1909b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Map<String, ra<?, ?>> f1910c = new HashMap();

        public /* synthetic */ a(va vaVar, sa saVar) {
            Preconditions.checkNotNull(vaVar, "serviceDescriptor");
            this.f1909b = vaVar;
            this.f1908a = vaVar.f1915a;
        }

        public <ReqT, RespT> a a(C0223ga<ReqT, RespT> c0223ga, qa<ReqT, RespT> qaVar) {
            Preconditions.checkNotNull(c0223ga, "method must not be null");
            Preconditions.checkNotNull(qaVar, "handler must not be null");
            ra<?, ?> raVar = new ra<>(c0223ga, qaVar);
            C0223ga<ReqT, RespT> c0223ga2 = raVar.f1899a;
            Preconditions.checkArgument(this.f1908a.equals(c0223ga2.f1834c), "Method name should be prefixed with service name and separated with '/'. Expected service name: '%s'. Actual fully qualifed method name: '%s'.", this.f1908a, c0223ga2.f1833b);
            String str = c0223ga2.f1833b;
            Preconditions.checkState(!this.f1910c.containsKey(str), "Method by same name already registered: %s", str);
            this.f1910c.put(str, raVar);
            return this;
        }
    }

    public /* synthetic */ ta(va vaVar, Map map, sa saVar) {
        Preconditions.checkNotNull(vaVar, "serviceDescriptor");
        Collections.unmodifiableMap(new HashMap(map));
    }
}

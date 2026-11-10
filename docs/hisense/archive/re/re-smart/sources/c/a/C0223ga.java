package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: renamed from: c.a.ga, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0223ga<ReqT, RespT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b<ReqT> f1835d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b<RespT> f1836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1837f;
    public final boolean g;
    public final boolean h;
    public final boolean i;

    /* JADX INFO: renamed from: c.a.ga$a */
    public static final class a<ReqT, RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b<ReqT> f1838a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b<RespT> f1839b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c f1840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1841d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1842e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1843f;
        public Object g;
        public boolean h;

        public /* synthetic */ a(C0221fa c0221fa) {
        }

        public C0223ga<ReqT, RespT> a() {
            return new C0223ga<>(this.f1840c, this.f1841d, this.f1838a, this.f1839b, this.g, this.f1842e, this.f1843f, this.h, null);
        }
    }

    /* JADX INFO: renamed from: c.a.ga$b */
    public interface b<T> {
    }

    /* JADX INFO: renamed from: c.a.ga$c */
    public enum c {
        UNARY,
        CLIENT_STREAMING,
        SERVER_STREAMING,
        BIDI_STREAMING,
        UNKNOWN;

        public final boolean a() {
            return this == UNARY || this == SERVER_STREAMING;
        }
    }

    /* JADX INFO: renamed from: c.a.ga$d */
    public interface d<T> extends e<T> {
    }

    /* JADX INFO: renamed from: c.a.ga$e */
    public interface e<T> extends b<T> {
    }

    public /* synthetic */ C0223ga(c cVar, String str, b bVar, b bVar2, Object obj, boolean z, boolean z2, boolean z3, C0221fa c0221fa) {
        new AtomicReferenceArray(2);
        Preconditions.checkNotNull(cVar, "type");
        this.f1832a = cVar;
        Preconditions.checkNotNull(str, "fullMethodName");
        this.f1833b = str;
        Preconditions.checkNotNull(str, "fullMethodName");
        int iLastIndexOf = str.lastIndexOf(47);
        this.f1834c = iLastIndexOf == -1 ? null : str.substring(0, iLastIndexOf);
        Preconditions.checkNotNull(bVar, "requestMarshaller");
        this.f1835d = bVar;
        Preconditions.checkNotNull(bVar2, "responseMarshaller");
        this.f1836e = bVar2;
        this.f1837f = obj;
        this.g = z;
        this.h = z2;
        this.i = z3;
    }

    public InputStream a(ReqT reqt) {
        return ((c.a.d.a.b.a) this.f1835d).a(reqt);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("fullMethodName", this.f1833b).add("type", this.f1832a).add("idempotent", this.g).add("safe", this.h).add("sampledToLocalTracing", this.i).add("requestMarshaller", this.f1835d).add("responseMarshaller", this.f1836e).add("schemaDescriptor", this.f1837f).omitNullValues().toString();
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        Preconditions.checkNotNull(str, "fullServiceName");
        sb.append(str);
        sb.append("/");
        Preconditions.checkNotNull(str2, "methodName");
        sb.append(str2);
        return sb.toString();
    }

    public static <ReqT, RespT> a<ReqT, RespT> a() {
        a<ReqT, RespT> aVar = new a<>(null);
        aVar.f1838a = null;
        aVar.f1839b = null;
        return aVar;
    }
}

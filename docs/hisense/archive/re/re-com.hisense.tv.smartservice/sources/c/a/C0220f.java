package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: c.a.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0220f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0220f f1812a = new C0220f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0238w f1813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Executor f1814c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1815d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC0218e f1816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1817f;
    public Object[][] g;
    public List<AbstractC0230n.a> h;
    public Boolean i;
    public Integer j;
    public Integer k;

    /* JADX INFO: renamed from: c.a.f$a */
    public static final class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1818a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final T f1819b;

        public a(String str, T t) {
            this.f1818a = str;
            this.f1819b = t;
        }

        public static <T> a<T> a(String str) {
            Preconditions.checkNotNull(str, "debugString");
            return new a<>(str, null);
        }

        public String toString() {
            return this.f1818a;
        }
    }

    public C0220f() {
        this.h = Collections.emptyList();
        this.g = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
    }

    public C0220f a(AbstractC0218e abstractC0218e) {
        C0220f c0220f = new C0220f(this);
        c0220f.f1816e = abstractC0218e;
        return c0220f;
    }

    public C0220f b() {
        C0220f c0220f = new C0220f(this);
        c0220f.i = Boolean.TRUE;
        return c0220f;
    }

    public C0220f c() {
        C0220f c0220f = new C0220f(this);
        c0220f.i = Boolean.FALSE;
        return c0220f;
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(this).add("deadline", this.f1813b).add("authority", this.f1815d).add("callCredentials", this.f1816e);
        Executor executor = this.f1814c;
        return toStringHelperAdd.add("executor", executor != null ? executor.getClass() : null).add("compressorName", this.f1817f).add("customOptions", Arrays.deepToString(this.g)).add("waitForReady", a()).add("maxInboundMessageSize", this.j).add("maxOutboundMessageSize", this.k).add("streamTracerFactories", this.h).toString();
    }

    public C0220f a(String str) {
        C0220f c0220f = new C0220f(this);
        c0220f.f1817f = str;
        return c0220f;
    }

    public C0220f b(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        C0220f c0220f = new C0220f(this);
        c0220f.k = Integer.valueOf(i);
        return c0220f;
    }

    public C0220f(C0220f c0220f) {
        this.h = Collections.emptyList();
        this.f1813b = c0220f.f1813b;
        this.f1815d = c0220f.f1815d;
        this.f1816e = c0220f.f1816e;
        this.f1814c = c0220f.f1814c;
        this.f1817f = c0220f.f1817f;
        this.g = c0220f.g;
        this.i = c0220f.i;
        this.j = c0220f.j;
        this.k = c0220f.k;
        this.h = c0220f.h;
    }

    public C0220f a(C0238w c0238w) {
        C0220f c0220f = new C0220f(this);
        c0220f.f1813b = c0238w;
        return c0220f;
    }

    public C0220f a(long j, TimeUnit timeUnit) {
        return a(C0238w.a(j, timeUnit));
    }

    public C0220f a(Executor executor) {
        C0220f c0220f = new C0220f(this);
        c0220f.f1814c = executor;
        return c0220f;
    }

    public C0220f a(AbstractC0230n.a aVar) {
        C0220f c0220f = new C0220f(this);
        ArrayList arrayList = new ArrayList(this.h.size() + 1);
        arrayList.addAll(this.h);
        arrayList.add(aVar);
        c0220f.h = Collections.unmodifiableList(arrayList);
        return c0220f;
    }

    public <T> C0220f a(a<T> aVar, T t) {
        Preconditions.checkNotNull(aVar, "key");
        Preconditions.checkNotNull(t, "value");
        C0220f c0220f = new C0220f(this);
        int i = 0;
        while (true) {
            Object[][] objArr = this.g;
            if (i >= objArr.length) {
                i = -1;
                break;
            }
            if (aVar.equals(objArr[i][0])) {
                break;
            }
            i++;
        }
        c0220f.g = (Object[][]) Array.newInstance((Class<?>) Object.class, this.g.length + (i == -1 ? 1 : 0), 2);
        Object[][] objArr2 = this.g;
        System.arraycopy(objArr2, 0, c0220f.g, 0, objArr2.length);
        if (i == -1) {
            c0220f.g[this.g.length] = new Object[]{aVar, t};
        } else {
            c0220f.g[i] = new Object[]{aVar, t};
        }
        return c0220f;
    }

    public <T> T a(a<T> aVar) {
        Preconditions.checkNotNull(aVar, "key");
        int i = 0;
        while (true) {
            Object[][] objArr = this.g;
            if (i < objArr.length) {
                if (aVar.equals(objArr[i][0])) {
                    return (T) this.g[i][1];
                }
                i++;
            } else {
                return aVar.f1819b;
            }
        }
    }

    public boolean a() {
        return Boolean.TRUE.equals(this.i);
    }

    public C0220f a(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        C0220f c0220f = new C0220f(this);
        c0220f.j = Integer.valueOf(i);
        return c0220f;
    }
}

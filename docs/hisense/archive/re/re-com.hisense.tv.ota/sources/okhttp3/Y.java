package okhttp3;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final M f172a;
    final String b;
    final K c;
    final a0 d;
    final Map e;
    private volatile C0052l f;

    Y(X x) {
        this.f172a = x.f171a;
        this.b = x.b;
        this.c = x.c.d();
        this.d = x.d;
        this.e = okhttp3.g0.e.v(x.e);
    }

    public a0 a() {
        return this.d;
    }

    public C0052l b() {
        C0052l c0052l = this.f;
        if (c0052l != null) {
            return c0052l;
        }
        C0052l c0052lK = C0052l.k(this.c);
        this.f = c0052lK;
        return c0052lK;
    }

    public String c(String str) {
        return this.c.c(str);
    }

    public List d(String str) {
        return this.c.i(str);
    }

    public K e() {
        return this.c;
    }

    public boolean f() {
        return this.f172a.m();
    }

    public String g() {
        return this.b;
    }

    public X h() {
        return new X(this);
    }

    public Object i() {
        return j(Object.class);
    }

    public Object j(Class cls) {
        return cls.cast(this.e.get(cls));
    }

    public M k() {
        return this.f172a;
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.f172a + ", tags=" + this.e + '}';
    }
}

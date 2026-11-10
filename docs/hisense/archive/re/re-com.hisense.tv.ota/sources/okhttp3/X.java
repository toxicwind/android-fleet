package okhttp3;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    M f171a;
    String b;
    J c;
    a0 d;
    Map e;

    public X() {
        this.e = Collections.emptyMap();
        this.b = "GET";
        this.c = new J();
    }

    X(Y y) {
        this.e = Collections.emptyMap();
        this.f171a = y.f172a;
        this.b = y.b;
        this.d = y.d;
        this.e = y.e.isEmpty() ? Collections.emptyMap() : new LinkedHashMap(y.e);
        this.c = y.c.f();
    }

    public X a(String str, String str2) {
        this.c.a(str, str2);
        return this;
    }

    public Y b() {
        if (this.f171a != null) {
            return new Y(this);
        }
        throw new IllegalStateException("url == null");
    }

    public X c(a0 a0Var) {
        h("DELETE", a0Var);
        return this;
    }

    public X d() {
        h("GET", null);
        return this;
    }

    public X e() {
        h("HEAD", null);
        return this;
    }

    public X f(String str, String str2) {
        this.c.g(str, str2);
        return this;
    }

    public X g(K k) {
        this.c = k.f();
        return this;
    }

    public X h(String str, a0 a0Var) {
        if (str == null) {
            throw new NullPointerException("method == null");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        }
        if (a0Var != null && !okhttp3.g0.h.h.b(str)) {
            throw new IllegalArgumentException("method " + str + " must not have a request body.");
        }
        if (a0Var != null || !okhttp3.g0.h.h.e(str)) {
            this.b = str;
            this.d = a0Var;
            return this;
        }
        throw new IllegalArgumentException("method " + str + " must have a request body.");
    }

    public X i(a0 a0Var) {
        h("PATCH", a0Var);
        return this;
    }

    public X j(a0 a0Var) {
        h("POST", a0Var);
        return this;
    }

    public X k(a0 a0Var) {
        h("PUT", a0Var);
        return this;
    }

    public X l(String str) {
        this.c.f(str);
        return this;
    }

    public X m(Class cls, Object obj) {
        if (cls == null) {
            throw new NullPointerException("type == null");
        }
        if (obj == null) {
            this.e.remove(cls);
        } else {
            if (this.e.isEmpty()) {
                this.e = new LinkedHashMap();
            }
            this.e.put(cls, cls.cast(obj));
        }
        return this;
    }

    public X n(Object obj) {
        m(Object.class, obj);
        return this;
    }

    public X o(String str) {
        StringBuilder sb;
        int i;
        if (str == null) {
            throw new NullPointerException("url == null");
        }
        if (!str.regionMatches(true, 0, "ws:", 0, 3)) {
            if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                sb = new StringBuilder();
                sb.append("https:");
                i = 4;
            }
            p(M.k(str));
            return this;
        }
        sb = new StringBuilder();
        sb.append("http:");
        i = 3;
        sb.append(str.substring(i));
        str = sb.toString();
        p(M.k(str));
        return this;
    }

    public X p(M m) {
        if (m == null) {
            throw new NullPointerException("url == null");
        }
        this.f171a = m;
        return this;
    }
}

package okhttp3;

/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Y f175a;
    Protocol b;
    int c;
    String d;
    I e;
    J f;
    e0 g;
    c0 h;
    c0 i;
    c0 j;
    long k;
    long l;

    public b0() {
        this.c = -1;
        this.f = new J();
    }

    b0(c0 c0Var) {
        this.c = -1;
        this.f175a = c0Var.f177a;
        this.b = c0Var.b;
        this.c = c0Var.c;
        this.d = c0Var.d;
        this.e = c0Var.e;
        this.f = c0Var.f.f();
        this.g = c0Var.g;
        this.h = c0Var.h;
        this.i = c0Var.i;
        this.j = c0Var.j;
        this.k = c0Var.k;
        this.l = c0Var.l;
    }

    private void e(c0 c0Var) {
        if (c0Var.g != null) {
            throw new IllegalArgumentException("priorResponse.body != null");
        }
    }

    private void f(String str, c0 c0Var) {
        if (c0Var.g != null) {
            throw new IllegalArgumentException(str + ".body != null");
        }
        if (c0Var.h != null) {
            throw new IllegalArgumentException(str + ".networkResponse != null");
        }
        if (c0Var.i != null) {
            throw new IllegalArgumentException(str + ".cacheResponse != null");
        }
        if (c0Var.j == null) {
            return;
        }
        throw new IllegalArgumentException(str + ".priorResponse != null");
    }

    public b0 a(String str, String str2) {
        this.f.a(str, str2);
        return this;
    }

    public b0 b(e0 e0Var) {
        this.g = e0Var;
        return this;
    }

    public c0 c() {
        if (this.f175a == null) {
            throw new IllegalStateException("request == null");
        }
        if (this.b == null) {
            throw new IllegalStateException("protocol == null");
        }
        if (this.c >= 0) {
            if (this.d != null) {
                return new c0(this);
            }
            throw new IllegalStateException("message == null");
        }
        throw new IllegalStateException("code < 0: " + this.c);
    }

    public b0 d(c0 c0Var) {
        if (c0Var != null) {
            f("cacheResponse", c0Var);
        }
        this.i = c0Var;
        return this;
    }

    public b0 g(int i) {
        this.c = i;
        return this;
    }

    public b0 h(I i) {
        this.e = i;
        return this;
    }

    public b0 i(String str, String str2) {
        this.f.g(str, str2);
        return this;
    }

    public b0 j(K k) {
        this.f = k.f();
        return this;
    }

    public b0 k(String str) {
        this.d = str;
        return this;
    }

    public b0 l(c0 c0Var) {
        if (c0Var != null) {
            f("networkResponse", c0Var);
        }
        this.h = c0Var;
        return this;
    }

    public b0 m(c0 c0Var) {
        if (c0Var != null) {
            e(c0Var);
        }
        this.j = c0Var;
        return this;
    }

    public b0 n(Protocol protocol) {
        this.b = protocol;
        return this;
    }

    public b0 o(long j) {
        this.l = j;
        return this;
    }

    public b0 p(Y y) {
        this.f175a = y;
        return this;
    }

    public b0 q(long j) {
        this.k = j;
        return this;
    }
}

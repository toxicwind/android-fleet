package okhttp3;

/* JADX INFO: renamed from: okhttp3.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0062w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f257a;
    String[] b;
    String[] c;
    boolean d;

    public C0062w(C0063x c0063x) {
        this.f257a = c0063x.f258a;
        this.b = c0063x.c;
        this.c = c0063x.d;
        this.d = c0063x.b;
    }

    C0062w(boolean z) {
        this.f257a = z;
    }

    public C0063x a() {
        return new C0063x(this);
    }

    public C0062w b(String... strArr) {
        if (!this.f257a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.b = (String[]) strArr.clone();
        return this;
    }

    public C0062w c(C0058s... c0058sArr) {
        if (!this.f257a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        String[] strArr = new String[c0058sArr.length];
        for (int i = 0; i < c0058sArr.length; i++) {
            strArr[i] = c0058sArr[i].f254a;
        }
        b(strArr);
        return this;
    }

    public C0062w d(boolean z) {
        if (!this.f257a) {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        this.d = z;
        return this;
    }

    public C0062w e(String... strArr) {
        if (!this.f257a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.c = (String[]) strArr.clone();
        return this;
    }

    public C0062w f(TlsVersion... tlsVersionArr) {
        if (!this.f257a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        String[] strArr = new String[tlsVersionArr.length];
        for (int i = 0; i < tlsVersionArr.length; i++) {
            strArr[i] = tlsVersionArr[i].javaName;
        }
        e(strArr);
        return this;
    }
}

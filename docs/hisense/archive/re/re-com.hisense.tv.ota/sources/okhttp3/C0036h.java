package okhttp3;

/* JADX INFO: renamed from: okhttp3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0036h extends e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final okhttp3.g0.g.k f217a;
    private final okio.j b;
    private final String c;
    private final String d;

    C0036h(okhttp3.g0.g.k kVar, String str, String str2) {
        this.f217a = kVar;
        this.c = str;
        this.d = str2;
        this.b = okio.t.d(new C0035g(this, kVar.e(1), kVar));
    }

    @Override // okhttp3.e0
    public P B() {
        String str = this.c;
        if (str != null) {
            return P.d(str);
        }
        return null;
    }

    @Override // okhttp3.e0
    public okio.j P() {
        return this.b;
    }

    @Override // okhttp3.e0
    public long p() {
        try {
            if (this.d != null) {
                return Long.parseLong(this.d);
            }
            return -1L;
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}

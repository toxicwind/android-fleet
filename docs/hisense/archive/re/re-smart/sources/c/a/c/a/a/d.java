package c.a.c.a.a;

import okhttp3.internal.http2.Header;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d.j f1566a = d.j.d(Header.RESPONSE_STATUS_UTF8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d.j f1567b = d.j.d(Header.TARGET_METHOD_UTF8);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d.j f1568c = d.j.d(Header.TARGET_PATH_UTF8);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d.j f1569d = d.j.d(Header.TARGET_SCHEME_UTF8);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d.j f1570e = d.j.d(Header.TARGET_AUTHORITY_UTF8);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d.j f1571f;
    public final d.j g;
    public final int h;

    static {
        d.j.d(":host");
        d.j.d(":version");
    }

    public d(String str, String str2) {
        this(d.j.d(str), d.j.d(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f1571f.equals(dVar.f1571f) && this.g.equals(dVar.g);
    }

    public int hashCode() {
        return this.g.hashCode() + ((this.f1571f.hashCode() + 527) * 31);
    }

    public String toString() {
        return String.format("%s: %s", this.f1571f.j(), this.g.j());
    }

    public d(d.j jVar, String str) {
        this(jVar, d.j.d(str));
    }

    public d(d.j jVar, d.j jVar2) {
        this.f1571f = jVar;
        this.g = jVar2;
        this.h = jVar2.g() + jVar.g() + 32;
    }
}

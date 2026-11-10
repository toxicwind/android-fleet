package okhttp3;

import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0056p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f252a;
    final String b;
    final String c;
    final ByteString d;

    boolean a(String str) {
        if (!this.f252a.startsWith("*.")) {
            return str.equals(this.b);
        }
        int iIndexOf = str.indexOf(46);
        if ((str.length() - iIndexOf) - 1 == this.b.length()) {
            String str2 = this.b;
            if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0056p) {
            C0056p c0056p = (C0056p) obj;
            if (this.f252a.equals(c0056p.f252a) && this.c.equals(c0056p.c) && this.d.equals(c0056p.d)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f252a.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        return this.c + this.d.a();
    }
}

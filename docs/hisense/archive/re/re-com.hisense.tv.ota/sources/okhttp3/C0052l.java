package okhttp3;

import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: okhttp3.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0052l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f248a;
    private final boolean b;
    private final int c;
    private final int d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final int h;
    private final int i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    String m;

    static {
        C0051k c0051k = new C0051k();
        c0051k.c();
        c0051k.a();
        C0051k c0051k2 = new C0051k();
        c0051k2.d();
        c0051k2.b(Integer.MAX_VALUE, TimeUnit.SECONDS);
        c0051k2.a();
    }

    C0052l(C0051k c0051k) {
        this.f248a = c0051k.f247a;
        this.b = c0051k.b;
        this.c = c0051k.c;
        this.d = -1;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = c0051k.d;
        this.i = c0051k.e;
        this.j = c0051k.f;
        this.k = c0051k.g;
        this.l = c0051k.h;
    }

    private C0052l(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.f248a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = str;
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f248a) {
            sb.append("no-cache, ");
        }
        if (this.b) {
            sb.append("no-store, ");
        }
        if (this.c != -1) {
            sb.append("max-age=");
            sb.append(this.c);
            sb.append(", ");
        }
        if (this.d != -1) {
            sb.append("s-maxage=");
            sb.append(this.d);
            sb.append(", ");
        }
        if (this.e) {
            sb.append("private, ");
        }
        if (this.f) {
            sb.append("public, ");
        }
        if (this.g) {
            sb.append("must-revalidate, ");
        }
        if (this.h != -1) {
            sb.append("max-stale=");
            sb.append(this.h);
            sb.append(", ");
        }
        if (this.i != -1) {
            sb.append("min-fresh=");
            sb.append(this.i);
            sb.append(", ");
        }
        if (this.j) {
            sb.append("only-if-cached, ");
        }
        if (this.k) {
            sb.append("no-transform, ");
        }
        if (this.l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0041  */
    /* JADX WARN: Code duplicated, block: B:28:0x0099  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:37:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:40:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:43:0x00da  */
    /* JADX WARN: Code duplicated, block: B:44:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:49:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:50:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:52:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:53:0x0104  */
    /* JADX WARN: Code duplicated, block: B:55:0x010c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0112  */
    /* JADX WARN: Code duplicated, block: B:58:0x011b  */
    /* JADX WARN: Code duplicated, block: B:59:0x011e  */
    /* JADX WARN: Code duplicated, block: B:61:0x0126  */
    /* JADX WARN: Code duplicated, block: B:62:0x0129  */
    /* JADX WARN: Code duplicated, block: B:64:0x0131  */
    /* JADX WARN: Code duplicated, block: B:87:0x0133 A[SYNTHETIC] */
    public static C0052l k(K k) {
        int i;
        int iH;
        String strTrim;
        int iH2;
        String strTrim2;
        int iG = k.g();
        boolean z = true;
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        int iF = -1;
        int iF2 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int iF3 = -1;
        int iF4 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        for (int i2 = 0; i2 < iG; i2++) {
            String strE = k.e(i2);
            String strH = k.h(i2);
            if (strE.equalsIgnoreCase("Cache-Control")) {
                if (str == null) {
                    str = strH;
                }
                for (i = 0; i < strH.length(); i = iH2) {
                    iH = okhttp3.g0.h.g.h(strH, i, "=,;");
                    strTrim = strH.substring(i, iH).trim();
                    if (iH != strH.length() || strH.charAt(iH) == ',' || strH.charAt(iH) == ';') {
                        iH2 = iH + 1;
                        strTrim2 = null;
                    } else {
                        int i3 = okhttp3.g0.h.g.i(strH, iH + 1);
                        if (i3 >= strH.length() || strH.charAt(i3) != '\"') {
                            iH2 = okhttp3.g0.h.g.h(strH, i3, ",;");
                            strTrim2 = strH.substring(i3, iH2).trim();
                        } else {
                            int i4 = i3 + 1;
                            int iH3 = okhttp3.g0.h.g.h(strH, i4, "\"");
                            strTrim2 = strH.substring(i4, iH3);
                            iH2 = iH3 + 1;
                        }
                    }
                    if ("no-cache".equalsIgnoreCase(strTrim)) {
                        z2 = true;
                    } else if ("no-store".equalsIgnoreCase(strTrim)) {
                        z3 = true;
                    } else if ("max-age".equalsIgnoreCase(strTrim)) {
                        iF = okhttp3.g0.h.g.f(strTrim2, -1);
                    } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                        iF2 = okhttp3.g0.h.g.f(strTrim2, -1);
                    } else if ("private".equalsIgnoreCase(strTrim)) {
                        z4 = true;
                    } else if ("public".equalsIgnoreCase(strTrim)) {
                        z5 = true;
                    } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                        z6 = true;
                    } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                        iF3 = okhttp3.g0.h.g.f(strTrim2, Integer.MAX_VALUE);
                    } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                        iF4 = okhttp3.g0.h.g.f(strTrim2, -1);
                    } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                        z7 = true;
                    } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                        z8 = true;
                    } else if ("immutable".equalsIgnoreCase(strTrim)) {
                        z9 = true;
                    }
                }
            } else {
                if (strE.equalsIgnoreCase("Pragma")) {
                }
            }
            z = false;
            while (i < strH.length()) {
                iH = okhttp3.g0.h.g.h(strH, i, "=,;");
                strTrim = strH.substring(i, iH).trim();
                if (iH != strH.length()) {
                    iH2 = iH + 1;
                    strTrim2 = null;
                } else {
                    iH2 = iH + 1;
                    strTrim2 = null;
                }
                if ("no-cache".equalsIgnoreCase(strTrim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(strTrim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(strTrim)) {
                    iF = okhttp3.g0.h.g.f(strTrim2, -1);
                } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                    iF2 = okhttp3.g0.h.g.f(strTrim2, -1);
                } else if ("private".equalsIgnoreCase(strTrim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(strTrim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                    iF3 = okhttp3.g0.h.g.f(strTrim2, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                    iF4 = okhttp3.g0.h.g.f(strTrim2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                    z8 = true;
                } else if ("immutable".equalsIgnoreCase(strTrim)) {
                    z9 = true;
                }
            }
        }
        return new C0052l(z2, z3, iF, iF2, z4, z5, z6, iF3, iF4, z7, z8, z9, !z ? null : str);
    }

    public boolean b() {
        return this.e;
    }

    public boolean c() {
        return this.f;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.f248a;
    }

    public boolean i() {
        return this.b;
    }

    public boolean j() {
        return this.j;
    }

    public String toString() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        String strA = a();
        this.m = strA;
        return strA;
    }
}

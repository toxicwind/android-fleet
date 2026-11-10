package okhttp3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class M {
    private static final char[] i = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f164a;
    private final String b;
    private final String c;
    final String d;
    final int e;
    private final List f;
    private final String g;
    private final String h;

    M(L l) {
        this.f164a = l.f163a;
        this.b = s(l.b, false);
        this.c = s(l.c, false);
        this.d = l.d;
        this.e = l.c();
        t(l.f, false);
        List list = l.g;
        this.f = list != null ? t(list, true) : null;
        String str = l.h;
        this.g = str != null ? s(str, false) : null;
        this.h = l.toString();
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int iCharCount = i2;
        while (iCharCount < i3) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !z4)) {
                if (str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || v(str, iCharCount, i3)))) && (iCodePointAt != 43 || !z3))) {
                    iCharCount += Character.charCount(iCodePointAt);
                }
            }
            okio.h hVar = new okio.h();
            hVar.s0(str, i2, iCharCount);
            c(hVar, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
            return hVar.Y();
        }
        return str.substring(i2, i3);
    }

    static String b(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }

    static void c(okio.h hVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        char[] cArr = i;
        okio.h hVar2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    hVar.r0(z ? "+" : "%2B");
                } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && z4) || str2.indexOf(iCodePointAt) != -1 || (iCodePointAt == 37 && (!z || (z2 && !v(str, i2, i3)))))) {
                    if (hVar2 == null) {
                        hVar2 = new okio.h();
                    }
                    if (charset == null || charset.equals(okhttp3.g0.e.i)) {
                        hVar2.t0(iCodePointAt);
                    } else {
                        hVar2.p0(str, i2, Character.charCount(iCodePointAt) + i2, charset);
                    }
                    while (!hVar2.n()) {
                        int iH = hVar2.H() & 255;
                        hVar.k0(37);
                        hVar.k0(cArr[(iH >> 4) & 15]);
                        hVar.k0(cArr[iH & 15]);
                    }
                } else {
                    hVar.t0(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    public static int d(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public static M k(String str) {
        L l = new L();
        l.h(null, str);
        return l.a();
    }

    static void n(StringBuilder sb, List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = (String) list.get(i2);
            String str2 = (String) list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    static void q(StringBuilder sb, List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append((String) list.get(i2));
        }
    }

    static String r(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                okio.h hVar = new okio.h();
                hVar.s0(str, i2, i4);
                u(hVar, str, i4, i3, z);
                return hVar.Y();
            }
        }
        return str.substring(i2, i3);
    }

    static String s(String str, boolean z) {
        return r(str, 0, str.length(), z);
    }

    private List t(List list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str = (String) list.get(i2);
            arrayList.add(str != null ? s(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0039  */
    static void u(okio.h hVar, String str, int i2, int i3, boolean z) {
        int i4;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (iCodePointAt == 37 && (i4 = i2 + 2) < i3) {
                int iK = okhttp3.g0.e.k(str.charAt(i2 + 1));
                int iK2 = okhttp3.g0.e.k(str.charAt(i4));
                if (iK == -1 || iK2 == -1) {
                    hVar.t0(iCodePointAt);
                } else {
                    hVar.k0((iK << 4) + iK2);
                    i2 = i4;
                }
            } else if (iCodePointAt == 43 && z) {
                hVar.k0(32);
            } else {
                hVar.t0(iCodePointAt);
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    static boolean v(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && okhttp3.g0.e.k(str.charAt(i2 + 1)) != -1 && okhttp3.g0.e.k(str.charAt(i4)) != -1;
    }

    static List y(String str) {
        String strSubstring;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int iIndexOf = str.indexOf(38, i2);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i2);
            if (iIndexOf2 == -1 || iIndexOf2 > iIndexOf) {
                arrayList.add(str.substring(i2, iIndexOf));
                strSubstring = null;
            } else {
                arrayList.add(str.substring(i2, iIndexOf2));
                strSubstring = str.substring(iIndexOf2 + 1, iIndexOf);
            }
            arrayList.add(strSubstring);
            i2 = iIndexOf + 1;
        }
        return arrayList;
    }

    public M A(String str) {
        L lP = p(str);
        if (lP != null) {
            return lP.a();
        }
        return null;
    }

    public String B() {
        return this.f164a;
    }

    public URI C() {
        L lO = o();
        lO.o();
        String string = lO.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public String e() {
        if (this.g == null) {
            return null;
        }
        return this.h.substring(this.h.indexOf(35) + 1);
    }

    public boolean equals(Object obj) {
        return (obj instanceof M) && ((M) obj).h.equals(this.h);
    }

    public String f() {
        if (this.c.isEmpty()) {
            return "";
        }
        return this.h.substring(this.h.indexOf(58, this.f164a.length() + 3) + 1, this.h.indexOf(64));
    }

    public String g() {
        int iIndexOf = this.h.indexOf(47, this.f164a.length() + 3);
        String str = this.h;
        return this.h.substring(iIndexOf, okhttp3.g0.e.o(str, iIndexOf, str.length(), "?#"));
    }

    public List h() {
        int iIndexOf = this.h.indexOf(47, this.f164a.length() + 3);
        String str = this.h;
        int iO = okhttp3.g0.e.o(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iO) {
            int i2 = iIndexOf + 1;
            int iN = okhttp3.g0.e.n(this.h, i2, iO, '/');
            arrayList.add(this.h.substring(i2, iN));
            iIndexOf = iN;
        }
        return arrayList;
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    public String i() {
        if (this.f == null) {
            return null;
        }
        int iIndexOf = this.h.indexOf(63) + 1;
        String str = this.h;
        return this.h.substring(iIndexOf, okhttp3.g0.e.n(str, iIndexOf, str.length(), '#'));
    }

    public String j() {
        if (this.b.isEmpty()) {
            return "";
        }
        int length = this.f164a.length() + 3;
        String str = this.h;
        return this.h.substring(length, okhttp3.g0.e.o(str, length, str.length(), ":@"));
    }

    public String l() {
        return this.d;
    }

    public boolean m() {
        return this.f164a.equals("https");
    }

    public L o() {
        L l = new L();
        l.f163a = this.f164a;
        l.b = j();
        l.c = f();
        l.d = this.d;
        l.e = this.e != d(this.f164a) ? this.e : -1;
        l.f.clear();
        l.f.addAll(h());
        l.d(i());
        l.h = e();
        return l;
    }

    public L p(String str) {
        try {
            L l = new L();
            l.h(this, str);
            return l;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public String toString() {
        return this.h;
    }

    public int w() {
        return this.e;
    }

    public String x() {
        if (this.f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        n(sb, this.f);
        return sb.toString();
    }

    public String z() {
        L lP = p("/...");
        lP.t("");
        lP.j("");
        return lP.a().toString();
    }
}

package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: okhttp3.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0064y {
    private static final Pattern j = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern l = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f259a;
    private final String b;
    private final long c;
    private final String d;
    private final String e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;

    private C0064y(String str, String str2, long j2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f259a = str;
        this.b = str2;
        this.c = j2;
        this.d = str3;
        this.e = str4;
        this.f = z;
        this.g = z2;
        this.i = z3;
        this.h = z4;
    }

    private static int a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static boolean b(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !okhttp3.g0.e.J(str);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00c0 A[PHI: r0
      0x00c0: PHI (r0v16 long) = (r0v2 long), (r0v6 long) binds: [B:42:0x00be, B:53:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    static C0064y d(long j2, M m2, String str) {
        long j3;
        C0064y c0064y;
        String str2;
        String strSubstring;
        int length = str.length();
        char c = ';';
        int iN = okhttp3.g0.e.n(str, 0, length, ';');
        char c2 = '=';
        int iN2 = okhttp3.g0.e.n(str, 0, iN, '=');
        if (iN2 == iN) {
            return null;
        }
        String strI = okhttp3.g0.e.I(str, 0, iN2);
        if (strI.isEmpty() || okhttp3.g0.e.x(strI) != -1) {
            return null;
        }
        String strI2 = okhttp3.g0.e.I(str, iN2 + 1, iN);
        if (okhttp3.g0.e.x(strI2) != -1) {
            return null;
        }
        int i = iN + 1;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String strG = null;
        boolean z4 = true;
        long jI = -1;
        long jH = 253402300799999L;
        String str3 = null;
        while (i < length) {
            int iN3 = okhttp3.g0.e.n(str, i, length, c);
            int iN4 = okhttp3.g0.e.n(str, i, iN3, c2);
            String strI3 = okhttp3.g0.e.I(str, i, iN4);
            String strI4 = iN4 < iN3 ? okhttp3.g0.e.I(str, iN4 + 1, iN3) : "";
            if (strI3.equalsIgnoreCase("expires")) {
                try {
                    jH = h(strI4, 0, strI4.length());
                    z3 = true;
                } catch (NumberFormatException | IllegalArgumentException unused) {
                }
            } else if (strI3.equalsIgnoreCase("max-age")) {
                jI = i(strI4);
                z3 = true;
            } else if (strI3.equalsIgnoreCase("domain")) {
                strG = g(strI4);
                z4 = false;
            } else if (strI3.equalsIgnoreCase("path")) {
                str3 = strI4;
            } else if (strI3.equalsIgnoreCase("secure")) {
                z = true;
            } else if (strI3.equalsIgnoreCase("httponly")) {
                z2 = true;
            }
            i = iN3 + 1;
            c = ';';
            c2 = '=';
        }
        long j4 = Long.MIN_VALUE;
        if (jI == Long.MIN_VALUE) {
            j3 = j4;
        } else if (jI != -1) {
            j4 = j2 + (jI <= 9223372036854775L ? jI * 1000 : Long.MAX_VALUE);
            if (j4 < j2 || j4 > 253402300799999L) {
                j3 = 253402300799999L;
            } else {
                j3 = j4;
            }
        } else {
            j3 = jH;
        }
        String strL = m2.l();
        if (strG == null) {
            str2 = strL;
            c0064y = null;
        } else {
            if (!b(strL, strG)) {
                return null;
            }
            c0064y = null;
            str2 = strG;
        }
        if (strL.length() != str2.length() && okhttp3.g0.m.a.c().d(str2) == null) {
            return c0064y;
        }
        if (str3 == null || !str3.startsWith("/")) {
            String strG2 = m2.g();
            int iLastIndexOf = strG2.lastIndexOf(47);
            strSubstring = iLastIndexOf != 0 ? strG2.substring(0, iLastIndexOf) : "/";
        } else {
            strSubstring = str3;
        }
        return new C0064y(strI, strI2, j3, str2, strSubstring, z, z2, z4, z3);
    }

    public static C0064y e(M m2, String str) {
        return d(System.currentTimeMillis(), m2, str);
    }

    public static List f(M m2, K k2) {
        List listI = k2.i("Set-Cookie");
        int size = listI.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            C0064y c0064yE = e(m2, (String) listI.get(i));
            if (c0064yE != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(c0064yE);
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    private static String g(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String strD = okhttp3.g0.e.d(str);
        if (strD != null) {
            return strD;
        }
        throw new IllegalArgumentException();
    }

    private static long h(String str, int i, int i2) {
        int iA = a(str, i, i2, false);
        Matcher matcher = m.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int iIndexOf = -1;
        int i6 = -1;
        int i7 = -1;
        while (iA < i2) {
            int iA2 = a(str, iA + 1, i2, true);
            matcher.region(iA, iA2);
            if (i4 == -1 && matcher.usePattern(m).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
                i6 = Integer.parseInt(matcher.group(2));
                i7 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(l).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (iIndexOf == -1 && matcher.usePattern(k).matches()) {
                iIndexOf = k.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i3 == -1 && matcher.usePattern(j).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
            }
            iA = a(str, iA2 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += 1900;
        }
        if (i3 >= 0 && i3 <= 69) {
            i3 += 2000;
        }
        if (i3 < 1601) {
            throw new IllegalArgumentException();
        }
        if (iIndexOf == -1) {
            throw new IllegalArgumentException();
        }
        if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        }
        if (i4 < 0 || i4 > 23) {
            throw new IllegalArgumentException();
        }
        if (i6 < 0 || i6 > 59) {
            throw new IllegalArgumentException();
        }
        if (i7 < 0 || i7 > 59) {
            throw new IllegalArgumentException();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(okhttp3.g0.e.n);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, i3);
        gregorianCalendar.set(2, iIndexOf - 1);
        gregorianCalendar.set(5, i5);
        gregorianCalendar.set(11, i4);
        gregorianCalendar.set(12, i6);
        gregorianCalendar.set(13, i7);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    private static long i(String str) {
        try {
            long j2 = Long.parseLong(str);
            if (j2 <= 0) {
                return Long.MIN_VALUE;
            }
            return j2;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e;
        }
    }

    public String c() {
        return this.f259a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0064y)) {
            return false;
        }
        C0064y c0064y = (C0064y) obj;
        return c0064y.f259a.equals(this.f259a) && c0064y.b.equals(this.b) && c0064y.d.equals(this.d) && c0064y.e.equals(this.e) && c0064y.c == this.c && c0064y.f == this.f && c0064y.g == this.g && c0064y.h == this.h && c0064y.i == this.i;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f259a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31;
        long j2 = this.c;
        return ((((((((iHashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (!this.f ? 1 : 0)) * 31) + (!this.g ? 1 : 0)) * 31) + (!this.h ? 1 : 0)) * 31) + (!this.i ? 1 : 0);
    }

    String j(boolean z) {
        String strA;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f259a);
        sb.append('=');
        sb.append(this.b);
        if (this.h) {
            if (this.c == Long.MIN_VALUE) {
                strA = "; max-age=0";
            } else {
                sb.append("; expires=");
                strA = okhttp3.g0.h.f.a(new Date(this.c));
            }
            sb.append(strA);
        }
        if (!this.i) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.d);
        }
        sb.append("; path=");
        sb.append(this.e);
        if (this.f) {
            sb.append("; secure");
        }
        if (this.g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String k() {
        return this.b;
    }

    public String toString() {
        return j(false);
    }
}

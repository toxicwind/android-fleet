package okhttp3;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f163a;
    String d;
    final List f;
    List g;
    String h;
    String b = "";
    String c = "";
    int e = -1;

    public L() {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        arrayList.add("");
    }

    private static String b(String str, int i, int i2) {
        return okhttp3.g0.e.d(M.r(str, i, i2, false));
    }

    private boolean f(String str) {
        return str.equals(".") || str.equalsIgnoreCase("%2e");
    }

    private boolean g(String str) {
        return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
    }

    private static int i(String str, int i, int i2) {
        try {
            int i3 = Integer.parseInt(M.a(str, i, i2, "", false, false, false, true, null));
            if (i3 <= 0 || i3 > 65535) {
                return -1;
            }
            return i3;
        } catch (NumberFormatException unused) {
        }
    }

    private void k() {
        List list = this.f;
        if (!((String) list.remove(list.size() - 1)).isEmpty() || this.f.isEmpty()) {
            this.f.add("");
        } else {
            List list2 = this.f;
            list2.set(list2.size() - 1, "");
        }
    }

    private static int m(String str, int i, int i2) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':') {
                return i;
            }
            if (cCharAt == '[') {
                do {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } while (str.charAt(i) != ']');
            }
            i++;
        }
        return i2;
    }

    private void n(String str, int i, int i2, boolean z, boolean z2) {
        String strA = M.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true, null);
        if (f(strA)) {
            return;
        }
        if (g(strA)) {
            k();
            return;
        }
        List list = this.f;
        if (((String) list.get(list.size() - 1)).isEmpty()) {
            List list2 = this.f;
            list2.set(list2.size() - 1, strA);
        } else {
            this.f.add(strA);
        }
        if (z) {
            this.f.add("");
        }
    }

    private void p(String str, int i, int i2) {
        if (i == i2) {
            return;
        }
        char cCharAt = str.charAt(i);
        if (cCharAt == '/' || cCharAt == '\\') {
            this.f.clear();
            this.f.add("");
            i++;
        } else {
            List list = this.f;
            list.set(list.size() - 1, "");
        }
        while (true) {
            int i3 = i;
            if (i3 >= i2) {
                return;
            }
            i = okhttp3.g0.e.o(str, i3, i2, "/\\");
            boolean z = i < i2;
            n(str, i3, i, z, true);
            if (z) {
                i++;
            }
        }
    }

    private static int r(String str, int i, int i2) {
        if (i2 - i < 2) {
            return -1;
        }
        char cCharAt = str.charAt(i);
        if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
            while (true) {
                i++;
                if (i >= i2) {
                    break;
                }
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                    if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                        if (cCharAt2 < '0' || cCharAt2 > '9') {
                            if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                if (cCharAt2 == ':') {
                                    return i;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static int s(String str, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\\' && cCharAt != '/') {
                break;
            }
            i3++;
            i++;
        }
        return i3;
    }

    public M a() {
        if (this.f163a == null) {
            throw new IllegalStateException("scheme == null");
        }
        if (this.d != null) {
            return new M(this);
        }
        throw new IllegalStateException("host == null");
    }

    int c() {
        int i = this.e;
        return i != -1 ? i : M.d(this.f163a);
    }

    public L d(String str) {
        this.g = str != null ? M.y(M.b(str, " \"'<>#", true, false, true, true)) : null;
        return this;
    }

    public L e(String str) {
        if (str == null) {
            throw new NullPointerException("host == null");
        }
        String strB = b(str, 0, str.length());
        if (strB != null) {
            this.d = strB;
            return this;
        }
        throw new IllegalArgumentException("unexpected host: " + str);
    }

    L h(M m, String str) {
        int iO;
        int i;
        int iE = okhttp3.g0.e.E(str, 0, str.length());
        int iF = okhttp3.g0.e.F(str, iE, str.length());
        int iR = r(str, iE, iF);
        if (iR != -1) {
            if (str.regionMatches(true, iE, "https:", 0, 6)) {
                this.f163a = "https";
                iE += 6;
            } else {
                if (!str.regionMatches(true, iE, "http:", 0, 5)) {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, iR) + "'");
                }
                this.f163a = "http";
                iE += 5;
            }
        } else {
            if (m == null) {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            this.f163a = m.f164a;
        }
        int iS = s(str, iE, iF);
        char c = '?';
        char c2 = '#';
        if (iS >= 2 || m == null || !m.f164a.equals(this.f163a)) {
            boolean z = false;
            boolean z2 = false;
            int i2 = iE + iS;
            while (true) {
                iO = okhttp3.g0.e.o(str, i2, iF, "@/\\?#");
                byte bCharAt = iO != iF ? str.charAt(iO) : (byte) -1;
                if (bCharAt == -1 || bCharAt == c2 || bCharAt == 47 || bCharAt == 92 || bCharAt == c) {
                    break;
                }
                if (bCharAt == 64) {
                    if (z) {
                        i = iO;
                        this.c += "%40" + M.a(str, i2, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                    } else {
                        int iN = okhttp3.g0.e.n(str, i2, iO, ':');
                        i = iO;
                        String strA = M.a(str, i2, iN, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        if (z2) {
                            strA = this.b + "%40" + strA;
                        }
                        this.b = strA;
                        if (iN != i) {
                            this.c = M.a(str, iN + 1, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            z = true;
                        }
                        z2 = true;
                    }
                    i2 = i + 1;
                }
                c = '?';
                c2 = '#';
            }
            int iM = m(str, i2, iO);
            int i3 = iM + 1;
            if (i3 < iO) {
                this.d = b(str, i2, iM);
                int i4 = i(str, i3, iO);
                this.e = i4;
                if (i4 == -1) {
                    throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i3, iO) + '\"');
                }
            } else {
                this.d = b(str, i2, iM);
                this.e = M.d(this.f163a);
            }
            if (this.d == null) {
                throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i2, iM) + '\"');
            }
            iE = iO;
        } else {
            this.b = m.j();
            this.c = m.f();
            this.d = m.d;
            this.e = m.e;
            this.f.clear();
            this.f.addAll(m.h());
            if (iE == iF || str.charAt(iE) == '#') {
                d(m.i());
            }
        }
        int iO2 = okhttp3.g0.e.o(str, iE, iF, "?#");
        p(str, iE, iO2);
        if (iO2 < iF && str.charAt(iO2) == '?') {
            int iN2 = okhttp3.g0.e.n(str, iO2, iF, '#');
            this.g = M.y(M.a(str, iO2 + 1, iN2, " \"'<>#", true, false, true, true, null));
            iO2 = iN2;
        }
        if (iO2 < iF && str.charAt(iO2) == '#') {
            this.h = M.a(str, 1 + iO2, iF, "", true, false, false, false, null);
        }
        return this;
    }

    public L j(String str) {
        if (str == null) {
            throw new NullPointerException("password == null");
        }
        this.c = M.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return this;
    }

    public L l(int i) {
        if (i > 0 && i <= 65535) {
            this.e = i;
            return this;
        }
        throw new IllegalArgumentException("unexpected port: " + i);
    }

    L o() {
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            this.f.set(i, M.b((String) this.f.get(i), "[]", true, true, false, true));
        }
        List list = this.g;
        if (list != null) {
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String str = (String) this.g.get(i2);
                if (str != null) {
                    this.g.set(i2, M.b(str, "\\^`{|}", true, true, true, true));
                }
            }
        }
        String str2 = this.h;
        if (str2 != null) {
            this.h = M.b(str2, " \"#<>\\^`{|}", true, true, false, false);
        }
        return this;
    }

    public L q(String str) {
        if (str == null) {
            throw new NullPointerException("scheme == null");
        }
        String str2 = "http";
        if (!str.equalsIgnoreCase("http")) {
            str2 = "https";
            if (!str.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
        }
        this.f163a = str2;
        return this;
    }

    public L t(String str) {
        if (str == null) {
            throw new NullPointerException("username == null");
        }
        this.b = M.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return this;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = this.f163a;
        if (str2 != null) {
            sb.append(str2);
            str = "://";
        } else {
            str = "//";
        }
        sb.append(str);
        if (!this.b.isEmpty() || !this.c.isEmpty()) {
            sb.append(this.b);
            if (!this.c.isEmpty()) {
                sb.append(':');
                sb.append(this.c);
            }
            sb.append('@');
        }
        String str3 = this.d;
        if (str3 != null) {
            if (str3.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.d);
                sb.append(']');
            } else {
                sb.append(this.d);
            }
        }
        if (this.e != -1 || this.f163a != null) {
            int iC = c();
            String str4 = this.f163a;
            if (str4 == null || iC != M.d(str4)) {
                sb.append(':');
                sb.append(iC);
            }
        }
        M.q(sb, this.f);
        if (this.g != null) {
            sb.append('?');
            M.n(sb, this.g);
        }
        if (this.h != null) {
            sb.append('#');
            sb.append(this.h);
        }
        return sb.toString();
    }
}

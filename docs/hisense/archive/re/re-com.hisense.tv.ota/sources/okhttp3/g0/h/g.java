package okhttp3.g0.h;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.A;
import okhttp3.C0064y;
import okhttp3.J;
import okhttp3.K;
import okhttp3.M;
import okhttp3.Y;
import okhttp3.c0;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    static {
        ByteString.h("\"\\");
        ByteString.h("\t ,=");
    }

    public static long a(K k) {
        return j(k.c("Content-Length"));
    }

    public static long b(c0 c0Var) {
        return a(c0Var.O());
    }

    public static boolean c(c0 c0Var) {
        if (c0Var.W().g().equals("HEAD")) {
            return false;
        }
        int iP = c0Var.p();
        return (((iP >= 100 && iP < 200) || iP == 204 || iP == 304) && b(c0Var) == -1 && !"chunked".equalsIgnoreCase(c0Var.M("Transfer-Encoding"))) ? false : true;
    }

    public static boolean d(K k) {
        return k(k).contains("*");
    }

    public static boolean e(c0 c0Var) {
        return d(c0Var.O());
    }

    public static int f(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static void g(A a2, M m, K k) {
        if (a2 == A.f155a) {
            return;
        }
        List listF = C0064y.f(m, k);
        if (listF.isEmpty()) {
            return;
        }
        a2.b(m, listF);
    }

    public static int h(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int i(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    private static long j(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static Set k(K k) {
        Set setEmptySet = Collections.emptySet();
        int iG = k.g();
        for (int i = 0; i < iG; i++) {
            if ("Vary".equalsIgnoreCase(k.e(i))) {
                String strH = k.h(i);
                if (setEmptySet.isEmpty()) {
                    setEmptySet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : strH.split(",")) {
                    setEmptySet.add(str.trim());
                }
            }
        }
        return setEmptySet;
    }

    private static Set l(c0 c0Var) {
        return k(c0Var.O());
    }

    public static K m(K k, K k2) {
        Set setK = k(k2);
        if (setK.isEmpty()) {
            return new J().d();
        }
        J j = new J();
        int iG = k.g();
        for (int i = 0; i < iG; i++) {
            String strE = k.e(i);
            if (setK.contains(strE)) {
                j.a(strE, k.h(i));
            }
        }
        return j.d();
    }

    public static K n(c0 c0Var) {
        return m(c0Var.R().W().e(), c0Var.O());
    }

    public static boolean o(c0 c0Var, K k, Y y) {
        for (String str : l(c0Var)) {
            if (!okhttp3.g0.e.q(k.i(str), y.d(str))) {
                return false;
            }
        }
        return true;
    }
}

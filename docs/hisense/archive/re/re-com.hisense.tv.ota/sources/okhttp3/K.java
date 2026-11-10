package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String[] f162a;

    K(J j) {
        List list = j.f161a;
        this.f162a = (String[]) list.toArray(new String[list.size()]);
    }

    static void a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                throw new IllegalArgumentException(okhttp3.g0.e.r("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
            }
        }
    }

    static void b(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("value for name " + str2 + " == null");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                throw new IllegalArgumentException(okhttp3.g0.e.r("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str2, str));
            }
        }
    }

    private static String d(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public String c(String str) {
        return d(this.f162a, str);
    }

    public String e(int i) {
        return this.f162a[i * 2];
    }

    public boolean equals(Object obj) {
        return (obj instanceof K) && Arrays.equals(((K) obj).f162a, this.f162a);
    }

    public J f() {
        J j = new J();
        Collections.addAll(j.f161a, this.f162a);
        return j;
    }

    public int g() {
        return this.f162a.length / 2;
    }

    public String h(int i) {
        return this.f162a[(i * 2) + 1];
    }

    public int hashCode() {
        return Arrays.hashCode(this.f162a);
    }

    public List i(String str) {
        int iG = g();
        ArrayList arrayList = null;
        for (int i = 0; i < iG; i++) {
            if (str.equalsIgnoreCase(e(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(h(i));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iG = g();
        for (int i = 0; i < iG; i++) {
            sb.append(e(i));
            sb.append(": ");
            sb.append(h(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}

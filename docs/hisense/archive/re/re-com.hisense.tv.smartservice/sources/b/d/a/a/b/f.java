package b.d.a.a.b;

import com.google.common.primitives.UnsignedLong;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f102a = String.valueOf(Long.MIN_VALUE).substring(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f103b = String.valueOf(UnsignedLong.UNSIGNED_MASK);

    public static long a(String str, long j) {
        String strTrim;
        int length;
        if (str == null || (length = (strTrim = str.trim()).length()) == 0) {
            return j;
        }
        int i = 0;
        if (length > 0) {
            char cCharAt = strTrim.charAt(0);
            if (cCharAt == '+') {
                strTrim = strTrim.substring(1);
                length = strTrim.length();
            } else if (cCharAt == '-') {
                i = 1;
            }
        }
        while (i < length) {
            char cCharAt2 = strTrim.charAt(i);
            if (cCharAt2 > '9' || cCharAt2 < '0') {
                try {
                    return (long) a(strTrim);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
            i++;
        }
        try {
            return Long.parseLong(strTrim);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static int b(char[] cArr, int i, int i2) {
        int i3 = cArr[i] - '0';
        if (i2 > 4) {
            int i4 = i + 1;
            int i5 = ((cArr[i4] - '0') + (i3 * 10)) * 10;
            int i6 = i4 + 1;
            int i7 = ((cArr[i6] - '0') + i5) * 10;
            int i8 = i6 + 1;
            int i9 = ((cArr[i8] - '0') + i7) * 10;
            i = i8 + 1;
            i3 = (cArr[i] - '0') + i9;
            i2 -= 4;
            if (i2 > 4) {
                int i10 = i + 1;
                int i11 = ((cArr[i10] - '0') + (i3 * 10)) * 10;
                int i12 = i10 + 1;
                int i13 = ((cArr[i12] - '0') + i11) * 10;
                int i14 = i12 + 1;
                return (cArr[i14 + 1] - '0') + (((cArr[i14] - '0') + i13) * 10);
            }
        }
        if (i2 <= 1) {
            return i3;
        }
        int i15 = i + 1;
        int i16 = (i3 * 10) + (cArr[i15] - '0');
        if (i2 <= 2) {
            return i16;
        }
        int i17 = i15 + 1;
        int i18 = (i16 * 10) + (cArr[i17] - '0');
        return i2 > 3 ? (i18 * 10) + (cArr[i17 + 1] - '0') : i18;
    }

    public static double a(String str) {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    public static BigDecimal a(char[] cArr) {
        return a(cArr, 0, cArr.length);
    }

    public static BigDecimal a(char[] cArr, int i, int i2) {
        try {
            return new BigDecimal(cArr, i, i2);
        } catch (NumberFormatException unused) {
            throw new NumberFormatException(b.a.a.a.a.a("Value \"", new String(cArr, i, i2), "\" can not be represented as BigDecimal"));
        }
    }
}

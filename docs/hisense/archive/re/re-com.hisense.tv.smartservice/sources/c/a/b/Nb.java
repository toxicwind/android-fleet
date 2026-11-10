package c.a.b;

import com.google.common.math.LongMath;
import com.google.common.primitives.UnsignedLong;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Nb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f1095a = TimeUnit.SECONDS.toNanos(1);

    public static Boolean a(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", obj, str, map));
    }

    public static List<?> b(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof List) {
            return (List) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", obj, str, map));
    }

    public static List<Map<String, ?>> c(Map<String, ?> map, String str) {
        List listB = b(map, str);
        if (listB == null) {
            return null;
        }
        a((List<?>) listB);
        return listB;
    }

    public static List<String> d(Map<String, ?> map, String str) {
        List listB = b(map, str);
        if (listB == null) {
            return null;
        }
        for (int i = 0; i < listB.size(); i++) {
            if (!(listB.get(i) instanceof String)) {
                throw new ClassCastException(String.format("value '%s' for idx %d in '%s' is not string", listB.get(i), Integer.valueOf(i), listB));
            }
        }
        return listB;
    }

    public static Double e(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            return (Double) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Double", obj, str, map));
    }

    public static Integer f(Map<String, ?> map, String str) {
        Double dE = e(map, str);
        if (dE == null) {
            return null;
        }
        int iIntValue = dE.intValue();
        if (iIntValue == dE.doubleValue()) {
            return Integer.valueOf(iIntValue);
        }
        throw new ClassCastException(b.a.a.a.a.a("Number expected to be integer: ", dE));
    }

    public static Map<String, ?> g(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", obj, str, map));
    }

    public static String h(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", obj, str, map));
    }

    public static Long i(Map<String, ?> map, String str) {
        String strH = h(map, str);
        if (strH == null) {
            return null;
        }
        try {
            return Long.valueOf(a(strH));
        } catch (ParseException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static List<Map<String, ?>> a(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof Map)) {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", list.get(i), Integer.valueOf(i), list));
            }
        }
        return list;
    }

    public static long a(String str) throws ParseException {
        boolean z;
        String strSubstring;
        int iCharAt;
        if (!str.isEmpty() && str.charAt(str.length() - 1) == 's') {
            if (str.charAt(0) == '-') {
                str = str.substring(1);
                z = true;
            } else {
                z = false;
            }
            String strSubstring2 = str.substring(0, str.length() - 1);
            int iIndexOf = strSubstring2.indexOf(46);
            if (iIndexOf != -1) {
                strSubstring = strSubstring2.substring(iIndexOf + 1);
                strSubstring2 = strSubstring2.substring(0, iIndexOf);
            } else {
                strSubstring = "";
            }
            long j = Long.parseLong(strSubstring2);
            if (strSubstring.isEmpty()) {
                iCharAt = 0;
            } else {
                iCharAt = 0;
                for (int i = 0; i < 9; i++) {
                    iCharAt *= 10;
                    if (i < strSubstring.length()) {
                        if (strSubstring.charAt(i) >= '0' && strSubstring.charAt(i) <= '9') {
                            iCharAt = (strSubstring.charAt(i) - '0') + iCharAt;
                        } else {
                            throw new ParseException("Invalid nanoseconds.", 0);
                        }
                    }
                }
            }
            if (j >= 0) {
                if (z) {
                    j = -j;
                    iCharAt = -iCharAt;
                }
                try {
                    return a(j, iCharAt);
                } catch (IllegalArgumentException unused) {
                    throw new ParseException("Duration value is out of range.", 0);
                }
            }
            throw new ParseException(b.a.a.a.a.a("Invalid duration string: ", str), 0);
        }
        throw new ParseException(b.a.a.a.a.a("Invalid duration string: ", str), 0);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0065  */
    public static long a(long j, int i) {
        boolean z;
        long j2 = i;
        long j3 = f1095a;
        if (j2 <= (-j3) || j2 >= j3) {
            j = LongMath.checkedAdd(j, j2 / f1095a);
            i = (int) (j2 % f1095a);
        }
        if (j > 0 && i < 0) {
            i = (int) (((long) i) + f1095a);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (((long) i) - f1095a);
            j++;
        }
        if (j < -315576000000L || j > 315576000000L) {
            z = false;
        } else {
            long j4 = i;
            if (j4 < -999999999 || j4 >= f1095a || ((j < 0 || i < 0) && (j > 0 || i > 0))) {
                z = false;
            } else {
                z = true;
            }
        }
        if (z) {
            long nanos = TimeUnit.SECONDS.toNanos(j);
            long j5 = i;
            long j6 = nanos + j5;
            return (((j5 ^ nanos) > 0L ? 1 : ((j5 ^ nanos) == 0L ? 0 : -1)) < 0) | ((nanos ^ j6) >= 0) ? j6 : ((j6 >>> 63) ^ 1) + UnsignedLong.UNSIGNED_MASK;
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(j), Integer.valueOf(i)));
    }
}

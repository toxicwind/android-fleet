package com.google.common.primitives;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int iMin = Math.min(jArr.length, jArr2.length);
            for (int i = 0; i < iMin; i++) {
                if (jArr[i] != jArr2[i]) {
                    return UnsignedLongs.compare(jArr[i], jArr2[i]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    private static final class ParseOverflowDetection {
        public static final long[] maxValueDivs = new long[37];
        public static final int[] maxValueMods = new int[37];
        public static final int[] maxSafeDigits = new int[37];

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i = 2; i <= 36; i++) {
                long j = i;
                maxValueDivs[i] = UnsignedLongs.divide(-1L, j);
                maxValueMods[i] = (int) UnsignedLongs.remainder(-1L, j);
                maxSafeDigits[i] = bigInteger.toString(i).length() - 1;
            }
        }

        public static boolean overflowInParse(long j, int i, int i2) {
            if (j < 0) {
                return true;
            }
            long[] jArr = maxValueDivs;
            if (j < jArr[i2]) {
                return false;
            }
            return j > jArr[i2] || i > maxValueMods[i2];
        }
    }

    public static int compare(long j, long j2) {
        return Longs.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
    }

    @CanIgnoreReturnValue
    public static long decode(String str) {
        ParseRequest parseRequestFromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(parseRequestFromString.rawValue, parseRequestFromString.radix);
        } catch (NumberFormatException e2) {
            NumberFormatException numberFormatException = new NumberFormatException(str.length() != 0 ? "Error parsing value: ".concat(str) : new String("Error parsing value: "));
            numberFormatException.initCause(e2);
            throw numberFormatException;
        }
    }

    public static long divide(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + ((long) (compare(j - (j3 * j2), j2) < 0 ? 0 : 1));
    }

    public static long flip(long j) {
        return j ^ Long.MIN_VALUE;
    }

    public static String join(String str, long... jArr) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int i = 1; i < jArr.length; i++) {
            sb.append(str);
            sb.append(toString(jArr[i]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j = jArr[0] ^ Long.MIN_VALUE;
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i] ^ Long.MIN_VALUE;
            if (j2 > j) {
                j = j2;
            }
        }
        return j ^ Long.MIN_VALUE;
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j = jArr[0] ^ Long.MIN_VALUE;
        for (int i = 1; i < jArr.length; i++) {
            long j2 = jArr[i] ^ Long.MIN_VALUE;
            if (j2 < j) {
                j = j2;
            }
        }
        return j ^ Long.MIN_VALUE;
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long remainder(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (compare(j3, j2) < 0) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static void sort(long[] jArr) {
        if (jArr == null) {
            throw new NullPointerException();
        }
        sort(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr) {
        if (jArr == null) {
            throw new NullPointerException();
        }
        sortDescending(jArr, 0, jArr.length);
    }

    public static String toString(long j) {
        return toString(j, 10);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str, int i) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        if (i < 2 || i > 36) {
            throw new NumberFormatException(a.a(26, "illegal radix: ", i));
        }
        int i2 = ParseOverflowDetection.maxSafeDigits[i] - 1;
        long j = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            int iDigit = Character.digit(str.charAt(i3), i);
            if (iDigit == -1) {
                throw new NumberFormatException(str);
            }
            if (i3 > i2 && ParseOverflowDetection.overflowInParse(j, iDigit, i)) {
                throw new NumberFormatException(str.length() != 0 ? "Too large for unsigned long: ".concat(str) : new String("Too large for unsigned long: "));
            }
            j = (j * ((long) i)) + ((long) iDigit);
        }
        return j;
    }

    public static String toString(long j, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i);
        if (j == 0) {
            return "0";
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        char[] cArr = new char[64];
        int length = cArr.length;
        int i2 = i - 1;
        if ((i & i2) == 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                length--;
                cArr[length] = Character.forDigit(((int) j) & i2, i);
                j >>>= iNumberOfTrailingZeros;
            } while (j != 0);
        } else {
            long jDivide = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : divide(j, i);
            long j2 = i;
            length--;
            cArr[length] = Character.forDigit((int) (j - (jDivide * j2)), i);
            while (jDivide > 0) {
                length--;
                cArr[length] = Character.forDigit((int) (jDivide % j2), i);
                jDivide /= j2;
            }
        }
        return new String(cArr, length, cArr.length - length);
    }

    public static void sort(long[] jArr, int i, int i2) {
        if (jArr != null) {
            Preconditions.checkPositionIndexes(i, i2, jArr.length);
            for (int i3 = i; i3 < i2; i3++) {
                jArr[i3] = Long.MIN_VALUE ^ jArr[i3];
            }
            Arrays.sort(jArr, i, i2);
            while (i < i2) {
                jArr[i] = jArr[i] ^ Long.MIN_VALUE;
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }

    public static void sortDescending(long[] jArr, int i, int i2) {
        if (jArr != null) {
            Preconditions.checkPositionIndexes(i, i2, jArr.length);
            for (int i3 = i; i3 < i2; i3++) {
                jArr[i3] = UnsignedLong.UNSIGNED_MASK ^ jArr[i3];
            }
            Arrays.sort(jArr, i, i2);
            while (i < i2) {
                jArr[i] = jArr[i] ^ UnsignedLong.UNSIGNED_MASK;
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }
}

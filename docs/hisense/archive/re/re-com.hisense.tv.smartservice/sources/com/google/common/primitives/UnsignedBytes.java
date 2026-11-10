package com.google.common.primitives;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Comparator;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = -128;
    public static final byte MAX_VALUE = -1;
    public static final int UNSIGNED_MASK = 255;

    @VisibleForTesting
    static class LexicographicalComparatorHolder {
        public static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName().concat("$UnsafeComparator");
        public static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();

        enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int iMin = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < iMin; i++) {
                    int iCompare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        @VisibleForTesting
        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;

            public static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            public static final Unsafe theUnsafe = getUnsafe();
            public static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);

            static {
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || BYTE_ARRAY_BASE_OFFSET % 8 != 0 || theUnsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
                }
            }

            public static Unsafe getUnsafe() {
                try {
                    try {
                        return Unsafe.getUnsafe();
                    } catch (PrivilegedActionException e2) {
                        throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                    }
                } catch (SecurityException unused) {
                    return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.1
                        @Override // java.security.PrivilegedExceptionAction
                        public Unsafe run() throws IllegalAccessException {
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                }
            }

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int iMin = Math.min(bArr.length, bArr2.length);
                int i = iMin & (-8);
                int i2 = 0;
                while (i2 < i) {
                    long j = i2;
                    long j2 = theUnsafe.getLong(bArr, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    long j3 = theUnsafe.getLong(bArr2, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    if (j2 != j3) {
                        if (BIG_ENDIAN) {
                            return UnsignedLongs.compare(j2, j3);
                        }
                        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & (-8);
                        return ((int) ((j2 >>> iNumberOfTrailingZeros) & 255)) - ((int) ((j3 >>> iNumberOfTrailingZeros) & 255));
                    }
                    i2 += 8;
                }
                while (i2 < iMin) {
                    int iCompare = UnsignedBytes.compare(bArr[i2], bArr2[i2]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                    i2++;
                }
                return bArr.length - bArr2.length;
            }
        }

        public static Comparator<byte[]> getBestComparator() {
            try {
                return (Comparator) Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                return PureJavaComparator.INSTANCE;
            }
        }
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long j) {
        Preconditions.checkArgument((j >> 8) == 0, "out of range: %s", j);
        return (byte) j;
    }

    public static int compare(byte b2, byte b3) {
        return (b2 & 255) - (b3 & 255);
    }

    public static byte flip(byte b2) {
        return (byte) (b2 ^ MAX_POWER_OF_TWO);
    }

    public static String join(String str, byte... bArr) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() + 3) * bArr.length);
        sb.append(bArr[0] & 255);
        for (int i = 1; i < bArr.length; i++) {
            sb.append(str);
            sb.append(toString(bArr[i]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    public static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i = bArr[0] & 255;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (i3 > i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i = bArr[0] & 255;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (i3 < i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    public static byte saturatedCast(long j) {
        if (j > 255) {
            return (byte) -1;
        }
        if (j < 0) {
            return (byte) 0;
        }
        return (byte) j;
    }

    public static void sort(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        sort(bArr, 0, bArr.length);
    }

    public static void sortDescending(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        sortDescending(bArr, 0, bArr.length);
    }

    public static int toInt(byte b2) {
        return b2 & 255;
    }

    @Beta
    public static String toString(byte b2) {
        return toString(b2, 10);
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str, int i) {
        if (str == null) {
            throw new NullPointerException();
        }
        int i2 = Integer.parseInt(str, i);
        if ((i2 >> 8) == 0) {
            return (byte) i2;
        }
        throw new NumberFormatException(a.a(25, "out of range: ", i2));
    }

    @Beta
    public static String toString(byte b2, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i);
        return Integer.toString(b2 & 255, i);
    }

    public static void sort(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            Preconditions.checkPositionIndexes(i, i2, bArr.length);
            for (int i3 = i; i3 < i2; i3++) {
                bArr[i3] = (byte) (bArr[i3] ^ MAX_POWER_OF_TWO);
            }
            Arrays.sort(bArr, i, i2);
            while (i < i2) {
                bArr[i] = (byte) (bArr[i] ^ MAX_POWER_OF_TWO);
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }

    public static void sortDescending(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            Preconditions.checkPositionIndexes(i, i2, bArr.length);
            for (int i3 = i; i3 < i2; i3++) {
                bArr[i3] = (byte) (bArr[i3] ^ Ascii.DEL);
            }
            Arrays.sort(bArr, i, i2);
            while (i < i2) {
                bArr[i] = (byte) (bArr[i] ^ Ascii.DEL);
                i++;
            }
            return;
        }
        throw new NullPointerException();
    }
}

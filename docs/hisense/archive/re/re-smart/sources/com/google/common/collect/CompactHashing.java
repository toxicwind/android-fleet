package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.ju.lib.datalayer.database.asist.SQLStatement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class CompactHashing {
    public static final int BYTE_MASK = 255;
    public static final int BYTE_MAX_SIZE = 256;
    public static final int DEFAULT_SIZE = 3;
    public static final int HASH_TABLE_BITS_MASK = 31;
    public static final int HASH_TABLE_BITS_MAX_BITS = 5;
    public static final int MAX_SIZE = 1073741823;
    public static final int MIN_HASH_TABLE_SIZE = 4;
    public static final int MODIFICATION_COUNT_INCREMENT = 32;
    public static final int SHORT_MASK = 65535;
    public static final int SHORT_MAX_SIZE = 65536;
    public static final byte UNSET = 0;

    public static Object createTable(int i) {
        if (i < 2 || i > 1073741824 || Integer.highestOneBit(i) != i) {
            throw new IllegalArgumentException(a.a(52, "must be power of 2 between 2^1 and 2^30: ", i));
        }
        if (i <= 256) {
            return new byte[i];
        }
        return i <= 65536 ? new short[i] : new int[i];
    }

    public static int getHashPrefix(int i, int i2) {
        return i & (~i2);
    }

    public static int getNext(int i, int i2) {
        return i & i2;
    }

    public static int maskCombine(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    public static int newCapacity(int i) {
        return (i + 1) * (i < 32 ? 4 : 2);
    }

    public static int remove(Object obj, Object obj2, int i, Object obj3, int[] iArr, Object[] objArr, Object[] objArr2) {
        int iSmearedHash = Hashing.smearedHash(obj);
        int i2 = iSmearedHash & i;
        int iTableGet = tableGet(obj3, i2);
        if (iTableGet == 0) {
            return -1;
        }
        int i3 = ~i;
        int i4 = iSmearedHash & i3;
        int i5 = -1;
        while (true) {
            int i6 = iTableGet - 1;
            int i7 = iArr[i6];
            if ((i7 & i3) == i4 && Objects.equal(obj, objArr[i6]) && (objArr2 == null || Objects.equal(obj2, objArr2[i6]))) {
                int i8 = i7 & i;
                if (i5 == -1) {
                    tableSet(obj3, i2, i8);
                } else {
                    iArr[i5] = maskCombine(iArr[i5], i8, i);
                }
                return i6;
            }
            int i9 = i7 & i;
            if (i9 == 0) {
                return -1;
            }
            i5 = i6;
            iTableGet = i9;
        }
    }

    public static void tableClear(Object obj) {
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
    }

    public static int tableGet(Object obj, int i) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i] & 255;
        }
        return obj instanceof short[] ? ((short[]) obj)[i] & SQLStatement.NONE : ((int[]) obj)[i];
    }

    public static void tableSet(Object obj, int i, int i2) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i] = (byte) i2;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i] = (short) i2;
        } else {
            ((int[]) obj)[i] = i2;
        }
    }

    public static int tableSize(int i) {
        return Math.max(4, Hashing.closedTableSize(i + 1, 1.0d));
    }
}

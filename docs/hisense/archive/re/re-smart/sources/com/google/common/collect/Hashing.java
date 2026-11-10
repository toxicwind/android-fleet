package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Hashing {
    public static final long C1 = -862048943;
    public static final long C2 = 461845907;
    public static final int MAX_TABLE_SIZE = 1073741824;

    public static int closedTableSize(int i, double d2) {
        int iMax = Math.max(i, 2);
        int iHighestOneBit = Integer.highestOneBit(iMax);
        if (iMax <= ((int) (d2 * ((double) iHighestOneBit)))) {
            return iHighestOneBit;
        }
        int i2 = iHighestOneBit << 1;
        if (i2 > 0) {
            return i2;
        }
        return 1073741824;
    }

    public static boolean needsResizing(int i, int i2, double d2) {
        return ((double) i) > d2 * ((double) i2) && i2 < 1073741824;
    }

    public static int smear(int i) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i) * C1), 15)) * C2);
    }

    public static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }
}

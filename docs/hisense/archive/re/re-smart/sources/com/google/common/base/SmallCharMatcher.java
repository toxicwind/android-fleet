package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {
    public static final int C1 = -862048943;
    public static final int C2 = 461845907;
    public static final double DESIRED_LOAD_FACTOR = 0.5d;
    public static final int MAX_SIZE = 1023;
    public final boolean containsZero;
    public final long filter;
    public final char[] table;

    public SmallCharMatcher(char[] cArr, long j, boolean z, String str) {
        super(str);
        this.table = cArr;
        this.filter = j;
        this.containsZero = z;
    }

    private boolean checkFilter(int i) {
        return 1 == ((this.filter >> i) & 1);
    }

    @VisibleForTesting
    public static int chooseTableSize(int i) {
        if (i == 1) {
            return 2;
        }
        int iHighestOneBit = Integer.highestOneBit(i - 1) << 1;
        while (((double) iHighestOneBit) * 0.5d < i) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static CharMatcher from(BitSet bitSet, String str) {
        int i;
        int iCardinality = bitSet.cardinality();
        boolean z = bitSet.get(0);
        char[] cArr = new char[chooseTableSize(iCardinality)];
        int length = cArr.length - 1;
        int iNextSetBit = bitSet.nextSetBit(0);
        long j = 0;
        while (iNextSetBit != -1) {
            long j2 = (1 << iNextSetBit) | j;
            int iSmear = smear(iNextSetBit);
            while (true) {
                i = iSmear & length;
                if (cArr[i] == 0) {
                    break;
                }
                iSmear = i + 1;
            }
            cArr[i] = (char) iNextSetBit;
            iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1);
            j = j2;
        }
        return new SmallCharMatcher(cArr, j, z, str);
    }

    public static int smear(int i) {
        return Integer.rotateLeft(i * (-862048943), 15) * 461845907;
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c2) {
        if (c2 == 0) {
            return this.containsZero;
        }
        if (!checkFilter(c2)) {
            return false;
        }
        int length = this.table.length - 1;
        int iSmear = smear(c2) & length;
        int i = iSmear;
        do {
            char[] cArr = this.table;
            if (cArr[i] == 0) {
                return false;
            }
            if (cArr[i] == c2) {
                return true;
            }
            i = (i + 1) & length;
        } while (i != iSmear);
        return false;
    }

    @Override // com.google.common.base.CharMatcher
    public void setBits(BitSet bitSet) {
        if (this.containsZero) {
            bitSet.set(0);
        }
        for (char c2 : this.table) {
            if (c2 != 0) {
                bitSet.set(c2);
            }
        }
    }
}

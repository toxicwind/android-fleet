package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
final class FixedSampleSizeRechunker {

    public static final class Results {
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        public Results(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i;
            this.timestamps = jArr2;
            this.flags = iArr2;
        }
    }

    public static Results rechunk(int i, long[] jArr, int[] iArr, long j) {
        int i2 = 8192 / i;
        int iCeilDivide = 0;
        for (int i3 : iArr) {
            iCeilDivide += Util.ceilDivide(i3, i2);
        }
        long[] jArr2 = new long[iCeilDivide];
        int[] iArr2 = new int[iCeilDivide];
        long[] jArr3 = new long[iCeilDivide];
        int[] iArr3 = new int[iCeilDivide];
        int i4 = 0;
        int i5 = 0;
        int iMax = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            long j2 = jArr[i6];
            while (i7 > 0) {
                int iMin = Math.min(i2, i7);
                jArr2[i5] = j2;
                iArr2[i5] = i * iMin;
                iMax = Math.max(iMax, iArr2[i5]);
                jArr3[i5] = ((long) i4) * j;
                iArr3[i5] = 1;
                j2 += (long) iArr2[i5];
                i4 += iMin;
                i7 -= iMin;
                i5++;
            }
        }
        return new Results(jArr2, iArr2, iMax, jArr3, iArr3);
    }
}

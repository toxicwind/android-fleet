package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
final class TrackSampleTable {
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;

    TrackSampleTable(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
        Assertions.checkArgument(iArr.length == jArr2.length);
        Assertions.checkArgument(jArr.length == jArr2.length);
        Assertions.checkArgument(iArr2.length == jArr2.length);
        this.offsets = jArr;
        this.sizes = iArr;
        this.maximumSize = i;
        this.timestampsUs = jArr2;
        this.flags = iArr2;
        this.sampleCount = jArr.length;
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long j) {
        for (int iBinarySearchFloor = Util.binarySearchFloor(this.timestampsUs, j, true, false); iBinarySearchFloor >= 0; iBinarySearchFloor--) {
            if ((this.flags[iBinarySearchFloor] & 1) != 0) {
                return iBinarySearchFloor;
            }
        }
        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long j) {
        for (int iBinarySearchCeil = Util.binarySearchCeil(this.timestampsUs, j, true, false); iBinarySearchCeil < this.timestampsUs.length; iBinarySearchCeil++) {
            if ((this.flags[iBinarySearchCeil] & 1) != 0) {
                return iBinarySearchCeil;
            }
        }
        return -1;
    }

    public int getSyncSampleIndex(int i) {
        while (i < this.sampleCount) {
            if ((this.flags[i] & 1) != 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

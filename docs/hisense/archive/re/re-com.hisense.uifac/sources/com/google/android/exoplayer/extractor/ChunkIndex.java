package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
public final class ChunkIndex implements SeekMap {
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.length = iArr.length;
        this.sizes = iArr;
        this.offsets = jArr;
        this.durationsUs = jArr2;
        this.timesUs = jArr3;
    }

    public int getChunkIndex(long j) {
        return Util.binarySearchFloor(this.timesUs, j, true, true);
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        return this.offsets[getChunkIndex(j)];
    }
}

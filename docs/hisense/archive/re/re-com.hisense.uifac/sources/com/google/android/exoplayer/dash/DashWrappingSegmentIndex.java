package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.RangedUri;
import com.google.android.exoplayer.extractor.ChunkIndex;

/* JADX INFO: loaded from: classes.dex */
final class DashWrappingSegmentIndex implements DashSegmentIndex {
    private final ChunkIndex chunkIndex;

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getFirstSegmentNum() {
        return 0;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public boolean isExplicit() {
        return true;
    }

    public DashWrappingSegmentIndex(ChunkIndex chunkIndex, String str) {
        this.chunkIndex = chunkIndex;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getLastSegmentNum(long j) {
        return this.chunkIndex.length - 1;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public long getTimeUs(int i) {
        return this.chunkIndex.timesUs[i];
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public long getDurationUs(int i, long j) {
        return this.chunkIndex.durationsUs[i];
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public RangedUri getSegmentUrl(int i) {
        ChunkIndex chunkIndex = this.chunkIndex;
        return new RangedUri(null, chunkIndex.offsets[i], chunkIndex.sizes[i]);
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getSegmentNum(long j, long j2) {
        return this.chunkIndex.getChunkIndex(j);
    }
}

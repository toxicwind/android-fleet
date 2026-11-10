package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.dash.DashSegmentIndex;

/* JADX INFO: loaded from: classes.dex */
final class DashSingleSegmentIndex implements DashSegmentIndex {
    private final RangedUri uri;

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public long getDurationUs(int i, long j) {
        return j;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getFirstSegmentNum() {
        return 0;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getLastSegmentNum(long j) {
        return 0;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public int getSegmentNum(long j, long j2) {
        return 0;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public long getTimeUs(int i) {
        return 0L;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public boolean isExplicit() {
        return true;
    }

    public DashSingleSegmentIndex(RangedUri rangedUri) {
        this.uri = rangedUri;
    }

    @Override // com.google.android.exoplayer.dash.DashSegmentIndex
    public RangedUri getSegmentUrl(int i) {
        return this.uri;
    }
}

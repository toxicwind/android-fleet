package com.google.android.exoplayer.extractor.ts;

import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class PtsTimestampAdjuster {
    private final long firstSampleTimestampUs;
    private volatile long lastPts = Long.MIN_VALUE;
    private long timestampOffsetUs;

    public PtsTimestampAdjuster(long j) {
        this.firstSampleTimestampUs = j;
    }

    public void reset() {
        this.lastPts = Long.MIN_VALUE;
    }

    public boolean isInitialized() {
        return this.lastPts != Long.MIN_VALUE;
    }

    public long adjustTimestamp(long j) {
        if (this.lastPts != Long.MIN_VALUE) {
            long j2 = (this.lastPts + 4294967296L) / 8589934592L;
            long j3 = ((j2 - 1) * 8589934592L) + j;
            j += j2 * 8589934592L;
            if (Math.abs(j3 - this.lastPts) < Math.abs(j - this.lastPts)) {
                j = j3;
            }
        }
        long jPtsToUs = ptsToUs(j);
        if (this.firstSampleTimestampUs != LongCompanionObject.MAX_VALUE && this.lastPts == Long.MIN_VALUE) {
            this.timestampOffsetUs = this.firstSampleTimestampUs - jPtsToUs;
        }
        this.lastPts = j;
        return jPtsToUs + this.timestampOffsetUs;
    }

    public static long ptsToUs(long j) {
        return (j * 1000000) / 90000;
    }

    public static long usToPts(long j) {
        return (j * 90000) / 1000000;
    }
}

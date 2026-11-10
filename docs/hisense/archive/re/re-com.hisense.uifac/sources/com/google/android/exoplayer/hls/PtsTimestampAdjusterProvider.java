package com.google.android.exoplayer.hls;

import android.util.SparseArray;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;

/* JADX INFO: loaded from: classes.dex */
public final class PtsTimestampAdjusterProvider {
    private final SparseArray<PtsTimestampAdjuster> ptsTimestampAdjusters = new SparseArray<>();

    public PtsTimestampAdjuster getAdjuster(boolean z, int i, long j) {
        PtsTimestampAdjuster ptsTimestampAdjuster = this.ptsTimestampAdjusters.get(i);
        if (z && ptsTimestampAdjuster == null) {
            ptsTimestampAdjuster = new PtsTimestampAdjuster(j);
            this.ptsTimestampAdjusters.put(i, ptsTimestampAdjuster);
        }
        if (z) {
            return ptsTimestampAdjuster;
        }
        if (ptsTimestampAdjuster == null || !ptsTimestampAdjuster.isInitialized()) {
            return null;
        }
        return ptsTimestampAdjuster;
    }

    public void reset() {
        this.ptsTimestampAdjusters.clear();
    }
}

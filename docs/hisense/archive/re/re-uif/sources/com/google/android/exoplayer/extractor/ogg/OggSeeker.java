package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class OggSeeker {
    private long totalSamples;
    private final OggUtil.PageHeader pageHeader = new OggUtil.PageHeader();
    private final ParsableByteArray headerArray = new ParsableByteArray(282);
    private long audioDataLength = -1;

    OggSeeker() {
    }

    public void setup(long j, long j2) {
        Assertions.checkArgument(j > 0 && j2 > 0);
        this.audioDataLength = j;
        this.totalSamples = j2;
    }

    public long getNextSeekPosition(long j, ExtractorInput extractorInput) throws InterruptedException, IOException {
        Assertions.checkState((this.audioDataLength == -1 || this.totalSamples == 0) ? false : true);
        OggUtil.populatePageHeader(extractorInput, this.pageHeader, this.headerArray, false);
        long j2 = j - this.pageHeader.granulePosition;
        if (j2 <= 0 || j2 > 72000) {
            OggUtil.PageHeader pageHeader = this.pageHeader;
            return (extractorInput.getPosition() - (((long) (pageHeader.bodySize + pageHeader.headerSize)) * ((long) (j2 <= 0 ? 2 : 1)))) + ((j2 * this.audioDataLength) / this.totalSamples);
        }
        extractorInput.resetPeekPosition();
        return -1L;
    }
}

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class TrackFragment {
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public boolean hasDataOffset;
    public DefaultSampleValues header;
    public int length;
    public long nextFragmentDecodeTime;
    public int[] sampleCompositionTimeOffsetTable;
    public long[] sampleDecodingTimeTable;
    public ParsableByteArray sampleEncryptionData;
    public int sampleEncryptionDataLength;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public int[] sampleSizeTable;
    public TrackEncryptionBox trackEncryptionBox;

    TrackFragment() {
    }

    public void reset() {
        this.length = 0;
        this.nextFragmentDecodeTime = 0L;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }

    public void initTables(int i) {
        this.length = i;
        int[] iArr = this.sampleSizeTable;
        if (iArr == null || iArr.length < i) {
            int i2 = (i * MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DESC_ALLOCED) / 100;
            this.sampleSizeTable = new int[i2];
            this.sampleCompositionTimeOffsetTable = new int[i2];
            this.sampleDecodingTimeTable = new long[i2];
            this.sampleIsSyncFrameTable = new boolean[i2];
            this.sampleHasSubsampleEncryptionTable = new boolean[i2];
        }
    }

    public void initEncryptionData(int i) {
        ParsableByteArray parsableByteArray = this.sampleEncryptionData;
        if (parsableByteArray == null || parsableByteArray.limit() < i) {
            this.sampleEncryptionData = new ParsableByteArray(i);
        }
        this.sampleEncryptionDataLength = i;
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void fillEncryptionData(ExtractorInput extractorInput) throws InterruptedException, IOException {
        extractorInput.readFully(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public void fillEncryptionData(ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTime(int i) {
        return this.sampleDecodingTimeTable[i] + ((long) this.sampleCompositionTimeOffsetTable[i]);
    }
}

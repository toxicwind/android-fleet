package com.google.android.exoplayer.extractor.mp3;

import com.google.android.exoplayer.util.MpegAudioHeader;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
final class XingSeeker implements Mp3Extractor.Seeker {
    private final long durationUs;
    private final long firstFramePosition;
    private final int headerSize;
    private final long inputLength;
    private final long sizeBytes;
    private final long[] tableOfContents;

    public static XingSeeker create(MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray, long j, long j2) {
        int unsignedIntToInt;
        int i = mpegAudioHeader.samplesPerFrame;
        int i2 = mpegAudioHeader.sampleRate;
        long j3 = j + ((long) mpegAudioHeader.frameSize);
        int i3 = parsableByteArray.readInt();
        if ((i3 & 1) != 1 || (unsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedIntToInt, ((long) i) * 1000000, i2);
        if ((i3 & 6) != 6) {
            return new XingSeeker(j3, jScaleLargeTimestamp, j2);
        }
        long unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        parsableByteArray.skipBytes(1);
        long[] jArr = new long[99];
        for (int i4 = 0; i4 < 99; i4++) {
            jArr[i4] = parsableByteArray.readUnsignedByte();
        }
        return new XingSeeker(j3, jScaleLargeTimestamp, j2, jArr, unsignedIntToInt2, mpegAudioHeader.frameSize);
    }

    private XingSeeker(long j, long j2, long j3) {
        this(j, j2, j3, null, 0L, 0);
    }

    private XingSeeker(long j, long j2, long j3, long[] jArr, long j4, int i) {
        this.firstFramePosition = j;
        this.durationUs = j2;
        this.inputLength = j3;
        this.tableOfContents = jArr;
        this.sizeBytes = j4;
        this.headerSize = i;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        if (!isSeekable()) {
            return this.firstFramePosition;
        }
        float f = (j * 100.0f) / this.durationUs;
        float f2 = 0.0f;
        if (f > 0.0f) {
            if (f >= 100.0f) {
                f2 = 256.0f;
            } else {
                int i = (int) f;
                f2 = i != 0 ? this.tableOfContents[i - 1] : 0.0f;
                f2 += ((i < 99 ? this.tableOfContents[i] : 256.0f) - f2) * (f - i);
            }
        }
        long jRound = Math.round(((double) f2) * 0.00390625d * this.sizeBytes);
        long j2 = this.firstFramePosition;
        long j3 = jRound + j2;
        long j4 = this.inputLength;
        return Math.min(j3, j4 != -1 ? j4 - 1 : ((j2 - ((long) this.headerSize)) + this.sizeBytes) - 1);
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j) {
        if (isSeekable()) {
            long j2 = this.firstFramePosition;
            if (j >= j2) {
                double d = ((j - j2) * 256.0d) / this.sizeBytes;
                int iBinarySearchFloor = Util.binarySearchFloor(this.tableOfContents, (long) d, true, false) + 1;
                long timeUsForTocPosition = getTimeUsForTocPosition(iBinarySearchFloor);
                long j3 = iBinarySearchFloor == 0 ? 0L : this.tableOfContents[iBinarySearchFloor - 1];
                long j4 = iBinarySearchFloor == 99 ? 256L : this.tableOfContents[iBinarySearchFloor];
                return timeUsForTocPosition + (j4 != j3 ? (long) (((getTimeUsForTocPosition(iBinarySearchFloor + 1) - timeUsForTocPosition) * (d - j3)) / (j4 - j3)) : 0L);
            }
        }
        return 0L;
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getDurationUs() {
        return this.durationUs;
    }

    private long getTimeUsForTocPosition(int i) {
        return (this.durationUs * ((long) i)) / 100;
    }
}

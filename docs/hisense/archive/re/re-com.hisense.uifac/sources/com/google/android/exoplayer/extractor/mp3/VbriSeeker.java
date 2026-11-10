package com.google.android.exoplayer.extractor.mp3;

import com.google.android.exoplayer.util.MpegAudioHeader;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
final class VbriSeeker implements Mp3Extractor.Seeker {
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public static VbriSeeker create(MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray, long j, long j2) {
        int unsignedByte;
        parsableByteArray.skipBytes(10);
        int i = parsableByteArray.readInt();
        if (i <= 0) {
            return null;
        }
        int i2 = mpegAudioHeader.sampleRate;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(i, 1000000 * ((long) (i2 >= 32000 ? 1152 : 576)), i2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        int unsignedShort3 = parsableByteArray.readUnsignedShort();
        int i3 = 2;
        parsableByteArray.skipBytes(2);
        long j3 = j + ((long) mpegAudioHeader.frameSize);
        int i4 = unsignedShort + 1;
        long[] jArr = new long[i4];
        long[] jArr2 = new long[i4];
        jArr[0] = 0;
        jArr2[0] = j3;
        int i5 = 1;
        while (i5 < i4) {
            if (unsignedShort3 == 1) {
                unsignedByte = parsableByteArray.readUnsignedByte();
            } else if (unsignedShort3 == i3) {
                unsignedByte = parsableByteArray.readUnsignedShort();
            } else if (unsignedShort3 == 3) {
                unsignedByte = parsableByteArray.readUnsignedInt24();
            } else {
                if (unsignedShort3 != 4) {
                    return null;
                }
                unsignedByte = parsableByteArray.readUnsignedIntToInt();
            }
            int i6 = i4;
            long j4 = unsignedByte;
            int i7 = unsignedShort3;
            j3 += j4 * ((long) unsignedShort2);
            jArr[i5] = (((long) i5) * jScaleLargeTimestamp) / ((long) unsignedShort);
            jArr2[i5] = j2 == -1 ? j3 : Math.min(j2, j3);
            i5++;
            i4 = i6;
            unsignedShort3 = i7;
            i3 = 2;
        }
        return new VbriSeeker(jArr, jArr2, jScaleLargeTimestamp);
    }

    private VbriSeeker(long[] jArr, long[] jArr2, long j) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        return this.positions[Util.binarySearchFloor(this.timesUs, j, true, true)];
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getDurationUs() {
        return this.durationUs;
    }
}

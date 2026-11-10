package com.google.android.exoplayer.util;

import com.google.android.exoplayer.extractor.SeekMap;

/* JADX INFO: loaded from: classes.dex */
public final class FlacSeekTable {
    private final long[] offsets;
    private final long[] sampleNumbers;

    public static FlacSeekTable parseSeekTable(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(1);
        int unsignedInt24 = parsableByteArray.readUnsignedInt24() / 18;
        long[] jArr = new long[unsignedInt24];
        long[] jArr2 = new long[unsignedInt24];
        for (int i = 0; i < unsignedInt24; i++) {
            jArr[i] = parsableByteArray.readLong();
            jArr2[i] = parsableByteArray.readLong();
            parsableByteArray.skipBytes(2);
        }
        return new FlacSeekTable(jArr, jArr2);
    }

    private FlacSeekTable(long[] jArr, long[] jArr2) {
        this.sampleNumbers = jArr;
        this.offsets = jArr2;
    }

    public SeekMap createSeekMap(final long j, final long j2) {
        return new SeekMap() { // from class: com.google.android.exoplayer.util.FlacSeekTable.1
            @Override // com.google.android.exoplayer.extractor.SeekMap
            public boolean isSeekable() {
                return true;
            }

            @Override // com.google.android.exoplayer.extractor.SeekMap
            public long getPosition(long j3) {
                return j + FlacSeekTable.this.offsets[Util.binarySearchFloor(FlacSeekTable.this.sampleNumbers, (j3 * j2) / 1000000, true, true)];
            }
        };
    }
}

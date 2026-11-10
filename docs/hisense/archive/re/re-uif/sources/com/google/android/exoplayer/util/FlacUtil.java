package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes.dex */
public final class FlacUtil {
    public static long extractSampleTimestamp(FlacStreamInfo flacStreamInfo, ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        long uTF8EncodedLong = parsableByteArray.readUTF8EncodedLong();
        int i = flacStreamInfo.minBlockSize;
        if (i == flacStreamInfo.maxBlockSize) {
            uTF8EncodedLong *= (long) i;
        }
        return (uTF8EncodedLong * 1000000) / ((long) flacStreamInfo.sampleRate);
    }
}

package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes.dex */
public final class FlacStreamInfo {
    public final int bitsPerSample;
    public final int channels;
    public final int maxBlockSize;
    public final int minBlockSize;
    public final int sampleRate;
    public final long totalSamples;

    public FlacStreamInfo(byte[] bArr, int i) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i * 8);
        this.minBlockSize = parsableBitArray.readBits(16);
        this.maxBlockSize = parsableBitArray.readBits(16);
        parsableBitArray.readBits(24);
        parsableBitArray.readBits(24);
        this.sampleRate = parsableBitArray.readBits(20);
        this.channels = parsableBitArray.readBits(3) + 1;
        this.bitsPerSample = parsableBitArray.readBits(5) + 1;
        this.totalSamples = parsableBitArray.readBits(36);
    }

    public int bitRate() {
        return this.bitsPerSample * this.sampleRate;
    }

    public long durationUs() {
        return (this.totalSamples * 1000000) / ((long) this.sampleRate);
    }
}

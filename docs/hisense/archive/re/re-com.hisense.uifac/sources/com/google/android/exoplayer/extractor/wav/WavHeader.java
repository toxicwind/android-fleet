package com.google.android.exoplayer.extractor.wav;

/* JADX INFO: loaded from: classes.dex */
final class WavHeader {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataSize;
    private long dataStartPosition;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public WavHeader(int i, int i2, int i3, int i4, int i5, int i6) {
        this.numChannels = i;
        this.sampleRateHz = i2;
        this.averageBytesPerSecond = i3;
        this.blockAlignment = i4;
        this.bitsPerSample = i5;
        this.encoding = i6;
    }

    public long getDurationUs() {
        return ((this.dataSize / ((long) this.blockAlignment)) * 1000000) / ((long) this.sampleRateHz);
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    public int getBitrate() {
        return this.sampleRateHz * this.bitsPerSample * this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public long getPosition(long j) {
        long j2 = (j * ((long) this.averageBytesPerSecond)) / 1000000;
        int i = this.blockAlignment;
        return ((j2 / ((long) i)) * ((long) i)) + this.dataStartPosition;
    }

    public long getTimeUs(long j) {
        return (j * 1000000) / ((long) this.averageBytesPerSecond);
    }

    public boolean hasDataBounds() {
        return (this.dataStartPosition == 0 || this.dataSize == 0) ? false : true;
    }

    public void setDataBounds(long j, long j2) {
        this.dataStartPosition = j;
        this.dataSize = j2;
    }

    public int getEncoding() {
        return this.encoding;
    }
}

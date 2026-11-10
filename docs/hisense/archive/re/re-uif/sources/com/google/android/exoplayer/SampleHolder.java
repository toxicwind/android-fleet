package com.google.android.exoplayer;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class SampleHolder {
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo = new CryptoInfo();
    public ByteBuffer data;
    public int flags;
    public int size;
    public long timeUs;

    public SampleHolder(int i) {
        this.bufferReplacementMode = i;
    }

    public void ensureSpaceForWrite(int i) throws IllegalStateException {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null) {
            this.data = createReplacementBuffer(i);
            return;
        }
        int iCapacity = byteBuffer.capacity();
        int iPosition = this.data.position();
        int i2 = i + iPosition;
        if (iCapacity >= i2) {
            return;
        }
        ByteBuffer byteBufferCreateReplacementBuffer = createReplacementBuffer(i2);
        if (iPosition > 0) {
            this.data.position(0);
            this.data.limit(iPosition);
            byteBufferCreateReplacementBuffer.put(this.data);
        }
        this.data = byteBufferCreateReplacementBuffer;
    }

    public boolean isEncrypted() {
        return (this.flags & 2) != 0;
    }

    public boolean isDecodeOnly() {
        return (this.flags & 134217728) != 0;
    }

    public boolean isSyncFrame() {
        return (this.flags & 1) != 0;
    }

    public void clearData() {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private ByteBuffer createReplacementBuffer(int i) {
        int i2 = this.bufferReplacementMode;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.data;
        throw new IllegalStateException("Buffer too small (" + (byteBuffer == null ? 0 : byteBuffer.capacity()) + " < " + i + ")");
    }

    public void copyFrom(SampleHolder sampleHolder) throws IllegalStateException {
        ensureSpaceForWrite(sampleHolder.data.limit());
        int iPosition = sampleHolder.data.position();
        sampleHolder.data.position(0);
        this.data.put(sampleHolder.data);
        sampleHolder.data.position(iPosition);
        this.data.position(iPosition);
        CryptoInfo cryptoInfo = this.cryptoInfo;
        CryptoInfo cryptoInfo2 = sampleHolder.cryptoInfo;
        cryptoInfo.set(cryptoInfo2.numSubSamples, cryptoInfo2.numBytesOfClearData, cryptoInfo2.numBytesOfEncryptedData, cryptoInfo2.key, cryptoInfo2.iv, cryptoInfo2.mode);
        this.flags = sampleHolder.flags;
        this.timeUs = sampleHolder.timeUs;
        this.size = sampleHolder.size;
    }
}

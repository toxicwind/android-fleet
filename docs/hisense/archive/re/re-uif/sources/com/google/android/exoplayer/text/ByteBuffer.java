package com.google.android.exoplayer.text;

/* JADX INFO: loaded from: classes.dex */
public class ByteBuffer {
    private byte[] buffer;
    private long length;

    public ByteBuffer(byte[] bArr) throws Exception {
        this.length = bArr.length;
        this.buffer = bArr;
    }

    public int getByte(long j) throws Exception {
        return this.buffer[(int) j] & 255;
    }

    public int getWord(long j) throws Exception {
        int i = (int) j;
        byte[] bArr = this.buffer;
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    public int getDWord(long j) throws Exception {
        int i = (int) j;
        byte[] bArr = this.buffer;
        return ((bArr[i] & 255) << 24) | (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 1] & 255) << 16);
    }

    public long getSize() {
        return this.length;
    }
}

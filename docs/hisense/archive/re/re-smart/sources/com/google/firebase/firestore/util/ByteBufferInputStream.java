package com.google.firebase.firestore.util;

import androidx.annotation.NonNull;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class ByteBufferInputStream extends InputStream {
    public ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.buffer.remaining();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.buffer.hasRemaining()) {
            return this.buffer.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(@NonNull byte[] bArr, int i, int i2) {
        if (!this.buffer.hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(i2, this.buffer.remaining());
        this.buffer.get(bArr, i, iMin);
        return iMin;
    }
}

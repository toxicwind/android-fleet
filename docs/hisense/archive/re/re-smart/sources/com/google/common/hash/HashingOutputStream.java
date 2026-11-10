package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class HashingOutputStream extends FilterOutputStream {
    public final Hasher hasher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingOutputStream(HashFunction hashFunction, OutputStream outputStream) {
        super(outputStream);
        if (outputStream == null) {
            throw new NullPointerException();
        }
        Hasher hasherNewHasher = hashFunction.newHasher();
        Preconditions.checkNotNull(hasherNewHasher);
        this.hasher = hasherNewHasher;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        this.hasher.putByte((byte) i);
        ((FilterOutputStream) this).out.write(i);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.hasher.putBytes(bArr, i, i2);
        ((FilterOutputStream) this).out.write(bArr, i, i2);
    }
}

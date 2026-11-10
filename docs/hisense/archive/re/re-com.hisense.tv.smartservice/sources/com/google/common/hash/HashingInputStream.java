package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class HashingInputStream extends FilterInputStream {
    public final Hasher hasher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingInputStream(HashFunction hashFunction, InputStream inputStream) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        }
        Hasher hasherNewHasher = hashFunction.newHasher();
        Preconditions.checkNotNull(hasherNewHasher);
        this.hasher = hasherNewHasher;
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    @CanIgnoreReturnValue
    public int read() throws IOException {
        int i = ((FilterInputStream) this).in.read();
        if (i != -1) {
            this.hasher.putByte((byte) i);
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new IOException("reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    @CanIgnoreReturnValue
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (i3 != -1) {
            this.hasher.putBytes(bArr, i, i3);
        }
        return i3;
    }
}

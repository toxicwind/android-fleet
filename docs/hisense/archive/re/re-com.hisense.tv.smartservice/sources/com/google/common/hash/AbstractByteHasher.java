package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
public abstract class AbstractByteHasher extends AbstractHasher {
    public final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public abstract void update(byte b2);

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putByte(byte b2) {
        update(b2);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c2) {
        this.scratch.putChar(c2);
        update(2);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putInt(int i) {
        this.scratch.putInt(i);
        update(4);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j) {
        this.scratch.putLong(j);
        update(8);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s) {
        this.scratch.putShort(s);
        update(2);
        return this;
    }

    public void update(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            update(bArr[i3]);
        }
    }

    public void update(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            update(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
            return;
        }
        for (int iRemaining = byteBuffer.remaining(); iRemaining > 0; iRemaining--) {
            update(byteBuffer.get());
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        if (bArr != null) {
            update(bArr);
            return this;
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        update(bArr, i, i2);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        update(byteBuffer);
        return this;
    }

    private Hasher update(int i) {
        try {
            update(this.scratch.array(), 0, i);
            return this;
        } finally {
            this.scratch.clear();
        }
    }
}

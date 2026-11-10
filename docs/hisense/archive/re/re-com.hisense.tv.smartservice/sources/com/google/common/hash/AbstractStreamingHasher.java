package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
public abstract class AbstractStreamingHasher extends AbstractHasher {
    public final ByteBuffer buffer;
    public final int bufferSize;
    public final int chunkSize;

    public AbstractStreamingHasher(int i) {
        this(i, i);
    }

    private void munch() {
        this.buffer.flip();
        while (this.buffer.remaining() >= this.chunkSize) {
            process(this.buffer);
        }
        this.buffer.compact();
    }

    private void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    private Hasher putBytesInternal(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(byteBuffer);
            munchIfFull();
            return this;
        }
        int iPosition = this.bufferSize - this.buffer.position();
        for (int i = 0; i < iPosition; i++) {
            this.buffer.put(byteBuffer.get());
        }
        munch();
        while (byteBuffer.remaining() >= this.chunkSize) {
            process(byteBuffer);
        }
        this.buffer.put(byteBuffer);
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        munch();
        this.buffer.flip();
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.limit());
        }
        return makeHash();
    }

    public abstract HashCode makeHash();

    public abstract void process(ByteBuffer byteBuffer);

    public void processRemaining(ByteBuffer byteBuffer) {
        byteBuffer.position(byteBuffer.limit());
        byteBuffer.limit(this.chunkSize + 7);
        while (true) {
            int iPosition = byteBuffer.position();
            int i = this.chunkSize;
            if (iPosition >= i) {
                byteBuffer.limit(i);
                byteBuffer.flip();
                process(byteBuffer);
                return;
            }
            byteBuffer.putLong(0L);
        }
    }

    public AbstractStreamingHasher(int i, int i2) {
        Preconditions.checkArgument(i2 % i == 0);
        this.buffer = ByteBuffer.allocate(i2 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = i2;
        this.chunkSize = i;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putByte(byte b2) {
        this.buffer.put(b2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putChar(char c2) {
        this.buffer.putChar(c2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putInt(int i) {
        this.buffer.putInt(i);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putLong(long j) {
        this.buffer.putLong(j);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putShort(short s) {
        this.buffer.putShort(s);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(byte[] bArr, int i, int i2) {
        putBytesInternal(ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN));
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(ByteBuffer byteBuffer) {
        ByteOrder byteOrderOrder = byteBuffer.order();
        try {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            putBytesInternal(byteBuffer);
            return this;
        } finally {
            byteBuffer.order(byteOrderOrder);
        }
    }
}

package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
public abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher
    public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
        funnel.funnel(t, this);
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putBoolean(boolean z) {
        return putByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c2) {
        putByte((byte) c2);
        putByte((byte) (c2 >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putDouble(double d2) {
        return putLong(Double.doubleToRawLongBits(d2));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putFloat(float f2) {
        return putInt(Float.floatToRawIntBits(f2));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putInt(int i) {
        putByte((byte) i);
        putByte((byte) (i >>> 8));
        putByte((byte) (i >>> 16));
        putByte((byte) (i >>> 24));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j) {
        for (int i = 0; i < 64; i += 8) {
            putByte((byte) (j >>> i));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s) {
        putByte((byte) s);
        putByte((byte) (s >>> 8));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            putChar(charSequence.charAt(i));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            putByte(bArr[i + i3]);
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            putBytes(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
        } else {
            for (int iRemaining = byteBuffer.remaining(); iRemaining > 0; iRemaining--) {
                putByte(byteBuffer.get());
            }
        }
        return this;
    }
}

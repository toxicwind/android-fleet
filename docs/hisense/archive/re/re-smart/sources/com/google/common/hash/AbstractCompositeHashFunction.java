package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    public static final long serialVersionUID = 0;
    public final HashFunction[] functions;

    /* JADX INFO: renamed from: com.google.common.hash.AbstractCompositeHashFunction$1, reason: invalid class name */
    public class AnonymousClass1 implements Hasher {
        public final /* synthetic */ Hasher[] val$hashers;

        public AnonymousClass1(Hasher[] hasherArr) {
            this.val$hashers = hasherArr;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return AbstractCompositeHashFunction.this.makeHash(this.val$hashers);
        }

        @Override // com.google.common.hash.Hasher
        public <T> Hasher putObject(T t, Funnel<? super T> funnel) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putObject(t, funnel);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBoolean(boolean z) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBoolean(z);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b2) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putByte(b2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c2) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putChar(c2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putDouble(double d2) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putDouble(d2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putFloat(float f2) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putFloat(f2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putInt(i);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putLong(j);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putShort(short s) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putShort(s);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putString(charSequence, charset);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putUnencodedChars(CharSequence charSequence) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putUnencodedChars(charSequence);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBytes(bArr);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i, int i2) {
            for (Hasher hasher : this.val$hashers) {
                hasher.putBytes(bArr, i, i2);
            }
            return this;
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            int iPosition = byteBuffer.position();
            for (Hasher hasher : this.val$hashers) {
                byteBuffer.position(iPosition);
                hasher.putBytes(byteBuffer);
            }
            return this;
        }
    }

    public AbstractCompositeHashFunction(HashFunction... hashFunctionArr) {
        for (HashFunction hashFunction : hashFunctionArr) {
            Preconditions.checkNotNull(hashFunction);
        }
        this.functions = hashFunctionArr;
    }

    private Hasher fromHashers(Hasher[] hasherArr) {
        return new AnonymousClass1(hasherArr);
    }

    public abstract HashCode makeHash(Hasher[] hasherArr);

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i = 0; i < hasherArr.length; i++) {
            hasherArr[i] = this.functions[i].newHasher();
        }
        return new AnonymousClass1(hasherArr);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i) {
        Preconditions.checkArgument(i >= 0);
        Hasher[] hasherArr = new Hasher[this.functions.length];
        for (int i2 = 0; i2 < hasherArr.length; i2++) {
            hasherArr[i2] = this.functions[i2].newHasher(i);
        }
        return new AnonymousClass1(hasherArr);
    }
}

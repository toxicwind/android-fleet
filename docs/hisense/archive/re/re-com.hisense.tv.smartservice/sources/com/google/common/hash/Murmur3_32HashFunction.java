package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.firebase.firestore.local.EncodedPath;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {
    public static final int C1 = -862048943;
    public static final int C2 = 461845907;
    public static final int CHUNK_SIZE = 4;
    public static final long serialVersionUID = 0;
    public final int seed;
    public static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
    public static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    @CanIgnoreReturnValue
    private static final class Murmur3_32Hasher extends AbstractHasher {
        public long buffer;
        public int h1;
        public int shift;
        public int length = 0;
        public boolean isDone = false;

        public Murmur3_32Hasher(int i) {
            this.h1 = i;
        }

        private void update(int i, long j) {
            long j2 = this.buffer;
            int i2 = this.shift;
            this.buffer = ((j & 4294967295L) << i2) | j2;
            this.shift = (i * 8) + i2;
            this.length += i;
            if (this.shift >= 32) {
                this.h1 = Murmur3_32HashFunction.mixH1(this.h1, Murmur3_32HashFunction.mixK1((int) this.buffer));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            this.h1 ^= Murmur3_32HashFunction.mixK1((int) this.buffer);
            return Murmur3_32HashFunction.fmix(this.h1, this.length);
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b2) {
            update(1, b2 & 255);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c2) {
            update(2, c2);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i) {
            update(4, i);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j) {
            update(4, (int) j);
            update(4, j >>> 32);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            if (!Charsets.UTF_8.equals(charset)) {
                return putBytes(charSequence.toString().getBytes(charset));
            }
            int length = charSequence.length();
            int i = 0;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                char cCharAt = charSequence.charAt(i);
                char cCharAt2 = charSequence.charAt(i + 1);
                char cCharAt3 = charSequence.charAt(i + 2);
                char cCharAt4 = charSequence.charAt(i + 3);
                if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                    break;
                }
                update(4, (cCharAt2 << '\b') | cCharAt | (cCharAt3 << EncodedPath.ENCODED_NUL) | (cCharAt4 << 24));
                i = i2;
            }
            while (i < length) {
                char cCharAt5 = charSequence.charAt(i);
                if (cCharAt5 < 128) {
                    update(1, cCharAt5);
                } else if (cCharAt5 < 2048) {
                    update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(cCharAt5));
                } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(cCharAt5));
                } else {
                    int iCodePointAt = Character.codePointAt(charSequence, i);
                    if (iCodePointAt == cCharAt5) {
                        putBytes(charSequence.subSequence(i, length).toString().getBytes(charset));
                        return this;
                    }
                    i++;
                    update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(iCodePointAt));
                }
                i++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i, int i2) {
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            while (true) {
                int i4 = i3 + 4;
                if (i4 > i2) {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(bArr, i3 + i));
                i3 = i4;
            }
            while (i3 < i2) {
                putByte(bArr[i + i3]);
                i3++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            ByteOrder byteOrderOrder = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                putInt(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                putByte(byteBuffer.get());
            }
            byteBuffer.order(byteOrderOrder);
            return this;
        }
    }

    public Murmur3_32HashFunction(int i) {
        this.seed = i;
    }

    public static long charToThreeUtf8Bytes(char c2) {
        return (((c2 & '?') | 128) << 16) | (((c2 >>> '\f') | 480) & 255) | ((((c2 >>> 6) & 63) | 128) << 8);
    }

    public static long charToTwoUtf8Bytes(char c2) {
        return (((c2 & '?') | 128) << 8) | (((c2 >>> 6) | 960) & 255);
    }

    public static long codePointToFourUtf8Bytes(int i) {
        return ((((long) (i >>> 18)) | 240) & 255) | ((((long) ((i >>> 12) & 63)) | 128) << 8) | ((((long) ((i >>> 6) & 63)) | 128) << 16) | ((((long) (i & 63)) | 128) << 24);
    }

    public static HashCode fmix(int i, int i2) {
        int i3 = i ^ i2;
        int i4 = (i3 ^ (i3 >>> 16)) * (-2048144789);
        int i5 = (i4 ^ (i4 >>> 13)) * (-1028477387);
        return HashCode.fromInt(i5 ^ (i5 >>> 16));
    }

    public static int getIntLittleEndian(byte[] bArr, int i) {
        return Ints.fromBytes(bArr[i + 3], bArr[i + 2], bArr[i + 1], bArr[i]);
    }

    public static int mixH1(int i, int i2) {
        return (Integer.rotateLeft(i ^ i2, 13) * 5) - 430675100;
    }

    public static int mixK1(int i) {
        return Integer.rotateLeft(i * (-862048943), 15) * 461845907;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Murmur3_32HashFunction) && this.seed == ((Murmur3_32HashFunction) obj).seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        int i3 = 0;
        int iMixH1 = this.seed;
        int i4 = 0;
        while (true) {
            int i5 = i4 + 4;
            if (i5 > i2) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1(getIntLittleEndian(bArr, i4 + i)));
            i4 = i5;
        }
        int i6 = 0;
        while (i4 < i2) {
            i3 ^= (bArr[i + i4] & 255) << i6;
            i4++;
            i6 += 8;
        }
        return fmix(mixK1(i3) ^ iMixH1, i2);
    }

    public int hashCode() {
        return Murmur3_32HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int i) {
        return fmix(mixH1(this.seed, mixK1(i)), 4);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j) {
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j)), mixK1((int) (j >>> 32))), 8);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        if (!Charsets.UTF_8.equals(charset)) {
            return hashBytes(charSequence.toString().getBytes(charset));
        }
        int length = charSequence.length();
        int i = 0;
        int iMixH1 = this.seed;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 4;
            if (i4 > length) {
                break;
            }
            char cCharAt = charSequence.charAt(i2);
            char cCharAt2 = charSequence.charAt(i2 + 1);
            char cCharAt3 = charSequence.charAt(i2 + 2);
            char cCharAt4 = charSequence.charAt(i2 + 3);
            if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1((cCharAt2 << '\b') | cCharAt | (cCharAt3 << EncodedPath.ENCODED_NUL) | (cCharAt4 << 24)));
            i3 += 4;
            i2 = i4;
        }
        long jCharToThreeUtf8Bytes = 0;
        while (i2 < length) {
            char cCharAt5 = charSequence.charAt(i2);
            if (cCharAt5 < 128) {
                jCharToThreeUtf8Bytes |= ((long) cCharAt5) << i;
                i += 8;
                i3++;
            } else if (cCharAt5 < 2048) {
                jCharToThreeUtf8Bytes |= charToTwoUtf8Bytes(cCharAt5) << i;
                i += 16;
                i3 += 2;
            } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                jCharToThreeUtf8Bytes |= charToThreeUtf8Bytes(cCharAt5) << i;
                i += 24;
                i3 += 3;
            } else {
                int iCodePointAt = Character.codePointAt(charSequence, i2);
                if (iCodePointAt == cCharAt5) {
                    return hashBytes(charSequence.toString().getBytes(charset));
                }
                i2++;
                jCharToThreeUtf8Bytes |= codePointToFourUtf8Bytes(iCodePointAt) << i;
                i3 += 4;
            }
            if (i >= 32) {
                iMixH1 = mixH1(iMixH1, mixK1((int) jCharToThreeUtf8Bytes));
                jCharToThreeUtf8Bytes >>>= 32;
                i -= 32;
            }
            i2++;
        }
        return fmix(mixK1((int) jCharToThreeUtf8Bytes) ^ iMixH1, i3);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int iMixK1 = this.seed;
        for (int i = 1; i < charSequence.length(); i += 2) {
            iMixK1 = mixH1(iMixK1, mixK1(charSequence.charAt(i - 1) | (charSequence.charAt(i) << EncodedPath.ENCODED_NUL)));
        }
        if ((charSequence.length() & 1) == 1) {
            iMixK1 ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(iMixK1, charSequence.length() * 2);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        int i = this.seed;
        StringBuilder sb = new StringBuilder(31);
        sb.append("Hashing.murmur3_32(");
        sb.append(i);
        sb.append(")");
        return sb.toString();
    }
}

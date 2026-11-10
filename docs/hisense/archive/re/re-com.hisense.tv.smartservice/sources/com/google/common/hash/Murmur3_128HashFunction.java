package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    public static final long serialVersionUID = 0;
    public final int seed;
    public static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    public static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {
        public static final long C1 = -8663945395140668459L;
        public static final long C2 = 5545529020109919103L;
        public static final int CHUNK_SIZE = 16;
        public long h1;
        public long h2;
        public int length;

        public Murmur3_128Hasher(int i) {
            super(16, 16);
            long j = i;
            this.h1 = j;
            this.h2 = j;
            this.length = 0;
        }

        private void bmix64(long j, long j2) {
            this.h1 = mixK1(j) ^ this.h1;
            this.h1 = Long.rotateLeft(this.h1, 27);
            long j3 = this.h1;
            long j4 = this.h2;
            this.h1 = j3 + j4;
            this.h1 = (this.h1 * 5) + 1390208809;
            this.h2 = mixK2(j2) ^ j4;
            this.h2 = Long.rotateLeft(this.h2, 31);
            this.h2 += this.h1;
            this.h2 = (this.h2 * 5) + 944331445;
        }

        public static long fmix64(long j) {
            long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
            long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
            return j3 ^ (j3 >>> 33);
        }

        public static long mixK1(long j) {
            return Long.rotateLeft(j * C1, 31) * C2;
        }

        public static long mixK2(long j) {
            return Long.rotateLeft(j * C2, 33) * C1;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j = this.h1;
            int i = this.length;
            this.h1 = j ^ ((long) i);
            this.h2 ^= (long) i;
            long j2 = this.h1;
            long j3 = this.h2;
            this.h1 = j2 + j3;
            long j4 = this.h1;
            this.h2 = j3 + j4;
            this.h1 = fmix64(j4);
            this.h2 = fmix64(this.h2);
            long j5 = this.h1;
            long j6 = this.h2;
            this.h1 = j5 + j6;
            this.h2 = j6 + this.h1;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer byteBuffer) {
            bmix64(byteBuffer.getLong(), byteBuffer.getLong());
            this.length += 16;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer byteBuffer) {
            long j;
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            long j7;
            long j8;
            long j9;
            long j10;
            long j11;
            long j12;
            long j13;
            long j14;
            this.length = byteBuffer.remaining() + this.length;
            switch (byteBuffer.remaining()) {
                case 1:
                    j = 0;
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 2:
                    j2 = 0;
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 3:
                    j3 = 0;
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 4:
                    j4 = 0;
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 5:
                    j5 = 0;
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 6:
                    j6 = 0;
                    j5 = j6 ^ (((long) (byteBuffer.get(5) & 255)) << 40);
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 7:
                    j6 = (((long) (byteBuffer.get(6) & 255)) << 48) ^ 0;
                    j5 = j6 ^ (((long) (byteBuffer.get(5) & 255)) << 40);
                    j4 = j5 ^ (((long) (byteBuffer.get(4) & 255)) << 32);
                    j3 = j4 ^ (((long) (byteBuffer.get(3) & 255)) << 24);
                    j2 = j3 ^ (((long) (byteBuffer.get(2) & 255)) << 16);
                    j = j2 ^ (((long) (byteBuffer.get(1) & 255)) << 8);
                    j7 = ((long) (byteBuffer.get(0) & 255)) ^ j;
                    j8 = 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 8:
                    j8 = 0;
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 9:
                    j9 = 0;
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 10:
                    j10 = 0;
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 11:
                    j11 = 0;
                    j10 = j11 ^ (((long) (byteBuffer.get(10) & 255)) << 16);
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 12:
                    j12 = 0;
                    j11 = j12 ^ (((long) (byteBuffer.get(11) & 255)) << 24);
                    j10 = j11 ^ (((long) (byteBuffer.get(10) & 255)) << 16);
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 13:
                    j13 = 0;
                    j12 = j13 ^ (((long) (byteBuffer.get(12) & 255)) << 32);
                    j11 = j12 ^ (((long) (byteBuffer.get(11) & 255)) << 24);
                    j10 = j11 ^ (((long) (byteBuffer.get(10) & 255)) << 16);
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 14:
                    j14 = 0;
                    j13 = j14 ^ (((long) (byteBuffer.get(13) & 255)) << 40);
                    j12 = j13 ^ (((long) (byteBuffer.get(12) & 255)) << 32);
                    j11 = j12 ^ (((long) (byteBuffer.get(11) & 255)) << 24);
                    j10 = j11 ^ (((long) (byteBuffer.get(10) & 255)) << 16);
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                case 15:
                    j14 = (((long) (byteBuffer.get(14) & 255)) << 48) ^ 0;
                    j13 = j14 ^ (((long) (byteBuffer.get(13) & 255)) << 40);
                    j12 = j13 ^ (((long) (byteBuffer.get(12) & 255)) << 32);
                    j11 = j12 ^ (((long) (byteBuffer.get(11) & 255)) << 24);
                    j10 = j11 ^ (((long) (byteBuffer.get(10) & 255)) << 16);
                    j9 = j10 ^ (((long) (byteBuffer.get(9) & 255)) << 8);
                    j8 = j9 ^ ((long) (byteBuffer.get(8) & 255));
                    j7 = byteBuffer.getLong() ^ 0;
                    this.h1 = mixK1(j7) ^ this.h1;
                    this.h2 = mixK2(j8) ^ this.h2;
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
    }

    public Murmur3_128HashFunction(int i) {
        this.seed = i;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 128;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Murmur3_128HashFunction) && this.seed == ((Murmur3_128HashFunction) obj).seed;
    }

    public int hashCode() {
        return Murmur3_128HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        int i = this.seed;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Hashing.murmur3_128(");
        sb.append(i);
        sb.append(")");
        return sb.toString();
    }
}

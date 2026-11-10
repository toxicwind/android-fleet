package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class SipHashFunction extends AbstractHashFunction implements Serializable {
    public static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    public static final long serialVersionUID = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1953c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1954d;
    public final long k0;
    public final long k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        public static final int CHUNK_SIZE = 8;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f1955b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1956c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1957d;
        public long finalM;
        public long v0;
        public long v1;
        public long v2;
        public long v3;

        public SipHasher(int i, int i2, long j, long j2) {
            super(8, 8);
            this.v0 = 8317987319222330741L;
            this.v1 = 7237128888997146477L;
            this.v2 = 7816392313619706465L;
            this.v3 = 8387220255154660723L;
            this.f1955b = 0L;
            this.finalM = 0L;
            this.f1956c = i;
            this.f1957d = i2;
            this.v0 ^= j;
            this.v1 ^= j2;
            this.v2 ^= j;
            this.v3 ^= j2;
        }

        private void processM(long j) {
            this.v3 ^= j;
            sipRound(this.f1956c);
            this.v0 = j ^ this.v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.v0;
                long j2 = this.v1;
                this.v0 = j + j2;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j2, 13);
                this.v3 = Long.rotateLeft(this.v3, 16);
                long j3 = this.v1;
                long j4 = this.v0;
                this.v1 = j3 ^ j4;
                this.v3 ^= this.v2;
                this.v0 = Long.rotateLeft(j4, 32);
                long j5 = this.v2;
                long j6 = this.v1;
                this.v2 = j5 + j6;
                this.v0 += this.v3;
                this.v1 = Long.rotateLeft(j6, 17);
                this.v3 = Long.rotateLeft(this.v3, 21);
                long j7 = this.v1;
                long j8 = this.v2;
                this.v1 = j7 ^ j8;
                this.v3 ^= this.v0;
                this.v2 = Long.rotateLeft(j8, 32);
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            this.finalM ^= this.f1955b << 56;
            processM(this.finalM);
            this.v2 ^= 255;
            sipRound(this.f1957d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer byteBuffer) {
            this.f1955b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer byteBuffer) {
            this.f1955b += (long) byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << i;
                i += 8;
            }
        }
    }

    public SipHashFunction(int i, int i2, long j, long j2) {
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f1953c = i;
        this.f1954d = i2;
        this.k0 = j;
        this.k1 = j2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f1953c == sipHashFunction.f1953c && this.f1954d == sipHashFunction.f1954d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1;
    }

    public int hashCode() {
        return (int) ((((long) ((SipHashFunction.class.hashCode() ^ this.f1953c) ^ this.f1954d)) ^ this.k0) ^ this.k1);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f1953c, this.f1954d, this.k0, this.k1);
    }

    public String toString() {
        int i = this.f1953c;
        int i2 = this.f1954d;
        long j = this.k0;
        long j2 = this.k1;
        StringBuilder sb = new StringBuilder(81);
        sb.append("Hashing.sipHash");
        sb.append(i);
        sb.append(i2);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        return sb.toString();
    }
}

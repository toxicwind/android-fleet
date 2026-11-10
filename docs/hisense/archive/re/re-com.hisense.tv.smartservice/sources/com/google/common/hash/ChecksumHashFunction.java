package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
    public static final long serialVersionUID = 0;
    public final int bits;
    public final ImmutableSupplier<? extends Checksum> checksumSupplier;
    public final String toString;

    private final class ChecksumHasher extends AbstractByteHasher {
        public final Checksum checksum;

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            long value = this.checksum.getValue();
            return ChecksumHashFunction.this.bits == 32 ? HashCode.fromInt((int) value) : HashCode.fromLong(value);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b2) {
            this.checksum.update(b2);
        }

        public ChecksumHasher(Checksum checksum) {
            if (checksum == null) {
                throw new NullPointerException();
            }
            this.checksum = checksum;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bArr, int i, int i2) {
            this.checksum.update(bArr, i, i2);
        }
    }

    public ChecksumHashFunction(ImmutableSupplier<? extends Checksum> immutableSupplier, int i, String str) {
        if (immutableSupplier == null) {
            throw new NullPointerException();
        }
        this.checksumSupplier = immutableSupplier;
        Preconditions.checkArgument(i == 32 || i == 64, "bits (%s) must be either 32 or 64", i);
        this.bits = i;
        if (str == null) {
            throw new NullPointerException();
        }
        this.toString = str;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new ChecksumHasher(this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }
}

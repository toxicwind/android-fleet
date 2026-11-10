package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class MessageDigestHashFunction extends AbstractHashFunction implements Serializable {
    public final int bytes;
    public final MessageDigest prototype;
    public final boolean supportsClone;
    public final String toString;

    public MessageDigestHashFunction(String str, String str2) {
        this.prototype = getMessageDigest(str);
        this.bytes = this.prototype.getDigestLength();
        if (str2 == null) {
            throw new NullPointerException();
        }
        this.toString = str2;
        this.supportsClone = supportsClone(this.prototype);
    }

    public static MessageDigest getMessageDigest(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public static boolean supportsClone(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bytes * 8;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        AnonymousClass1 anonymousClass1 = null;
        if (this.supportsClone) {
            try {
                return new MessageDigestHasher((MessageDigest) this.prototype.clone(), this.bytes, anonymousClass1);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MessageDigestHasher(getMessageDigest(this.prototype.getAlgorithm()), this.bytes, anonymousClass1);
    }

    public String toString() {
        return this.toString;
    }

    public Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString, null);
    }

    private static final class MessageDigestHasher extends AbstractByteHasher {
        public final int bytes;
        public final MessageDigest digest;
        public boolean done;

        public MessageDigestHasher(MessageDigest messageDigest, int i) {
            this.digest = messageDigest;
            this.bytes = i;
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return this.bytes == this.digest.getDigestLength() ? HashCode.fromBytesNoCopy(this.digest.digest()) : HashCode.fromBytesNoCopy(Arrays.copyOf(this.digest.digest(), this.bytes));
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b2) {
            checkNotDone();
            this.digest.update(b2);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bArr, int i, int i2) {
            checkNotDone();
            this.digest.update(bArr, i, i2);
        }

        public /* synthetic */ MessageDigestHasher(MessageDigest messageDigest, int i, AnonymousClass1 anonymousClass1) {
            this.digest = messageDigest;
            this.bytes = i;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(ByteBuffer byteBuffer) {
            checkNotDone();
            this.digest.update(byteBuffer);
        }
    }

    private static final class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final String algorithmName;
        public final int bytes;
        public final String toString;

        public SerializedForm(String str, int i, String str2) {
            this.algorithmName = str;
            this.bytes = i;
            this.toString = str2;
        }

        private Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }

        public /* synthetic */ SerializedForm(String str, int i, String str2, AnonymousClass1 anonymousClass1) {
            this.algorithmName = str;
            this.bytes = i;
            this.toString = str2;
        }
    }

    public MessageDigestHashFunction(String str, int i, String str2) {
        if (str2 != null) {
            this.toString = str2;
            this.prototype = getMessageDigest(str);
            int digestLength = this.prototype.getDigestLength();
            Preconditions.checkArgument(i >= 4 && i <= digestLength, "bytes (%s) must be >= 4 and < %s", i, digestLength);
            this.bytes = i;
            this.supportsClone = supportsClone(this.prototype);
            return;
        }
        throw new NullPointerException();
    }
}

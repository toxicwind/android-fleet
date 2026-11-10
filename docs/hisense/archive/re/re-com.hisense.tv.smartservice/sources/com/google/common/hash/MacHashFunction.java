package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class MacHashFunction extends AbstractHashFunction {
    public final int bits;
    public final Key key;
    public final Mac prototype;
    public final boolean supportsClone;
    public final String toString;

    public MacHashFunction(String str, Key key, String str2) {
        this.prototype = getMac(str, key);
        if (key == null) {
            throw new NullPointerException();
        }
        this.key = key;
        if (str2 == null) {
            throw new NullPointerException();
        }
        this.toString = str2;
        this.bits = this.prototype.getMacLength() * 8;
        this.supportsClone = supportsClone(this.prototype);
    }

    public static Mac getMac(String str, Key key) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(key);
            return mac;
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public static boolean supportsClone(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        AnonymousClass1 anonymousClass1 = null;
        if (this.supportsClone) {
            try {
                return new MacHasher((Mac) this.prototype.clone(), anonymousClass1);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MacHasher(getMac(this.prototype.getAlgorithm(), this.key), anonymousClass1);
    }

    public String toString() {
        return this.toString;
    }

    private static final class MacHasher extends AbstractByteHasher {
        public boolean done;
        public final Mac mac;

        public MacHasher(Mac mac) {
            this.mac = mac;
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return HashCode.fromBytesNoCopy(this.mac.doFinal());
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b2) {
            checkNotDone();
            this.mac.update(b2);
        }

        public /* synthetic */ MacHasher(Mac mac, AnonymousClass1 anonymousClass1) {
            this.mac = mac;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bArr) {
            checkNotDone();
            this.mac.update(bArr);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bArr, int i, int i2) {
            checkNotDone();
            this.mac.update(bArr, i, i2);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(ByteBuffer byteBuffer) {
            checkNotDone();
            if (byteBuffer != null) {
                this.mac.update(byteBuffer);
                return;
            }
            throw new NullPointerException();
        }
    }
}

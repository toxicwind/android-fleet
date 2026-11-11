package dev.whyoleg.cryptography.random;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyRandom.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u000b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0096\u0080\bR\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/random/CryptographyRandomImpl;", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "secureRandom", "Ljava/security/SecureRandom;", "<init>", "(Ljava/security/SecureRandom;)V", "getSecureRandom", "()Ljava/security/SecureRandom;", "nextBits", "", "bitCount", "nextInt", "until", "nextLong", "", "nextBoolean", "", "nextDouble", "", "nextFloat", "", "nextBytes", "", "array", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class CryptographyRandomImpl extends CryptographyRandom {
    private final SecureRandom secureRandom;

    public CryptographyRandomImpl(SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(secureRandom, "secureRandom");
        this.secureRandom = secureRandom;
    }

    public final SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    @Override // kotlin.random.Random
    public int nextBits(int bitCount) {
        return CryptographyRandom_jvmKt.takeUpperBits(this.secureRandom.nextInt(), bitCount);
    }

    @Override // kotlin.random.Random
    public int nextInt() {
        return this.secureRandom.nextInt();
    }

    @Override // kotlin.random.Random
    public int nextInt(int until) {
        return this.secureRandom.nextInt(until);
    }

    @Override // kotlin.random.Random
    public long nextLong() {
        return this.secureRandom.nextLong();
    }

    @Override // kotlin.random.Random
    public boolean nextBoolean() {
        return this.secureRandom.nextBoolean();
    }

    @Override // kotlin.random.Random
    public double nextDouble() {
        return this.secureRandom.nextDouble();
    }

    @Override // kotlin.random.Random
    public float nextFloat() {
        return this.secureRandom.nextFloat();
    }

    @Override // kotlin.random.Random
    public byte[] nextBytes(byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length == 0) {
            return array;
        }
        this.secureRandom.nextBytes(array);
        return array;
    }
}

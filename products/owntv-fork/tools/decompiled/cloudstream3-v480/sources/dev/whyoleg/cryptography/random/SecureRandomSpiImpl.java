package dev.whyoleg.cryptography.random;

import java.security.SecureRandomSpi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyRandom.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0014J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/random/SecureRandomSpiImpl;", "Ljava/security/SecureRandomSpi;", "cryptographyRandom", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "<init>", "(Ldev/whyoleg/cryptography/random/CryptographyRandom;)V", "engineSetSeed", "", "seed", "", "engineNextBytes", "bytes", "engineGenerateSeed", "numBytes", "", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class SecureRandomSpiImpl extends SecureRandomSpi {
    private final CryptographyRandom cryptographyRandom;

    public SecureRandomSpiImpl(CryptographyRandom cryptographyRandom) {
        Intrinsics.checkNotNullParameter(cryptographyRandom, "cryptographyRandom");
        this.cryptographyRandom = cryptographyRandom;
    }

    @Override // java.security.SecureRandomSpi
    protected void engineSetSeed(byte[] seed) {
        Intrinsics.checkNotNullParameter(seed, "seed");
        throw new UnsupportedOperationException();
    }

    @Override // java.security.SecureRandomSpi
    protected void engineNextBytes(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length == 0) {
            return;
        }
        this.cryptographyRandom.nextBytes(bytes);
    }

    @Override // java.security.SecureRandomSpi
    protected byte[] engineGenerateSeed(int numBytes) {
        throw new UnsupportedOperationException();
    }
}

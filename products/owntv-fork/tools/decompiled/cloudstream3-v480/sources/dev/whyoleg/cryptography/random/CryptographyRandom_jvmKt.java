package dev.whyoleg.cryptography.random;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyRandom.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\u0080\u0004\u001a\n\u0010\u0004\u001a\u00020\u0001H\u0080\u0080\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0082\u0080\u0004¨\u0006\b"}, d2 = {"asCryptographyRandom", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "Ljava/security/SecureRandom;", "asSecureRandom", "defaultCryptographyRandom", "takeUpperBits", "", "bitCount", "cryptography-random"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CryptographyRandom_jvmKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int takeUpperBits(int i, int i2) {
        return (i >>> (32 - i2)) & ((-i2) >> 31);
    }

    public static final CryptographyRandom asCryptographyRandom(SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(secureRandom, "<this>");
        return new CryptographyRandomImpl(secureRandom);
    }

    public static final SecureRandom asSecureRandom(CryptographyRandom cryptographyRandom) {
        Intrinsics.checkNotNullParameter(cryptographyRandom, "<this>");
        return cryptographyRandom instanceof CryptographyRandomImpl ? ((CryptographyRandomImpl) cryptographyRandom).getSecureRandom() : new SecureRandomImpl(cryptographyRandom);
    }

    public static final CryptographyRandom defaultCryptographyRandom() {
        return asCryptographyRandom(new SecureRandom());
    }
}

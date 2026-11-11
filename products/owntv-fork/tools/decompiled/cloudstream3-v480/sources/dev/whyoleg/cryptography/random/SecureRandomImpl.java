package dev.whyoleg.cryptography.random;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyRandom.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/random/SecureRandomImpl;", "Ljava/security/SecureRandom;", "cryptographyRandom", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "<init>", "(Ldev/whyoleg/cryptography/random/CryptographyRandom;)V", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class SecureRandomImpl extends SecureRandom {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecureRandomImpl(CryptographyRandom cryptographyRandom) {
        super(new SecureRandomSpiImpl(cryptographyRandom), null);
        Intrinsics.checkNotNullParameter(cryptographyRandom, "cryptographyRandom");
    }
}

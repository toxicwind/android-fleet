package dev.whyoleg.cryptography;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CryptographyAlgorithmNotFoundException.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "IllegalStateException is throw instead")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\bF\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyAlgorithmNotFoundException;", "Ldev/whyoleg/cryptography/CryptographyException;", "algorithm", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "<init>", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CryptographyAlgorithmNotFoundException extends CryptographyException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CryptographyAlgorithmNotFoundException(CryptographyAlgorithmId<?> algorithm) {
        super("Algorithm not found: " + algorithm);
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
    }
}

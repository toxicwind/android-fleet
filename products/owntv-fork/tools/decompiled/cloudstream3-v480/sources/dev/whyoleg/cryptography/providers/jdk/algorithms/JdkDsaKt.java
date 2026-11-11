package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.SHA1;
import dev.whyoleg.cryptography.algorithms.SHA224;
import dev.whyoleg.cryptography.algorithms.SHA256;
import dev.whyoleg.cryptography.algorithms.SHA384;
import dev.whyoleg.cryptography.algorithms.SHA3_224;
import dev.whyoleg.cryptography.algorithms.SHA3_256;
import dev.whyoleg.cryptography.algorithms.SHA3_384;
import dev.whyoleg.cryptography.algorithms.SHA3_512;
import dev.whyoleg.cryptography.algorithms.SHA512;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkDsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0082\u0080\u0004\u001a\u000e\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0082\u0080\u0004¨\u0006\u0007"}, d2 = {"dsaSignatureAlgorithmName", "", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "byteSize", "", "Ljava/math/BigInteger;", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkDsaKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String dsaSignatureAlgorithmName(CryptographyAlgorithmId<Digest> cryptographyAlgorithmId) {
        if (cryptographyAlgorithmId == null) {
            return "NONEwithDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA1.INSTANCE)) {
            return "SHA1withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA224.INSTANCE)) {
            return "SHA224withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA256.INSTANCE)) {
            return "SHA256withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA384.INSTANCE)) {
            return "SHA384withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA512.INSTANCE)) {
            return "SHA512withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_224.INSTANCE)) {
            return "SHA3-224withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_256.INSTANCE)) {
            return "SHA3-256withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_384.INSTANCE)) {
            return "SHA3-384withDSA";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_512.INSTANCE)) {
            return "SHA3-512withDSA";
        }
        throw new IllegalStateException("Unsupported hash algorithm for DSA: " + cryptographyAlgorithmId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int byteSize(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) / 8;
    }
}

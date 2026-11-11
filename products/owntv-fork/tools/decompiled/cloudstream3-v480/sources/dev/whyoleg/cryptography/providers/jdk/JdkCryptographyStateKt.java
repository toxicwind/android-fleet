package dev.whyoleg.cryptography.providers.jdk;

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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkCryptographyState.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0080\u0080\u0004\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0080\u0080\u0004¨\u0006\u0005"}, d2 = {"hashECAlgorithmName", "", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "hashAlgorithmName", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkCryptographyStateKt {
    public static final String hashECAlgorithmName(CryptographyAlgorithmId<Digest> cryptographyAlgorithmId) {
        if (cryptographyAlgorithmId == null) {
            return "NONE";
        }
        return hashAlgorithmName(cryptographyAlgorithmId);
    }

    public static final String hashAlgorithmName(CryptographyAlgorithmId<Digest> cryptographyAlgorithmId) {
        Intrinsics.checkNotNullParameter(cryptographyAlgorithmId, "<this>");
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA1.INSTANCE)) {
            return "SHA1";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA224.INSTANCE)) {
            return "SHA224";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA256.INSTANCE)) {
            return "SHA256";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA384.INSTANCE)) {
            return "SHA384";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA512.INSTANCE)) {
            return "SHA512";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_224.INSTANCE)) {
            return "SHA3-224";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_256.INSTANCE)) {
            return "SHA3-256";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_384.INSTANCE)) {
            return "SHA3-384";
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_512.INSTANCE)) {
            return "SHA3-512";
        }
        throw new IllegalStateException("Unsupported hash algorithm: " + cryptographyAlgorithmId);
    }
}

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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkHmac.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0082\u0080\u0004¨\u0006\u0004"}, d2 = {"blockSize", "", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkHmacKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int blockSize(CryptographyAlgorithmId<Digest> cryptographyAlgorithmId) {
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA1.INSTANCE) || Intrinsics.areEqual(cryptographyAlgorithmId, SHA224.INSTANCE) || Intrinsics.areEqual(cryptographyAlgorithmId, SHA256.INSTANCE)) {
            return 64;
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA384.INSTANCE) || Intrinsics.areEqual(cryptographyAlgorithmId, SHA512.INSTANCE)) {
            return 128;
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_224.INSTANCE)) {
            return 144;
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_256.INSTANCE)) {
            return 136;
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_384.INSTANCE)) {
            return 104;
        }
        if (Intrinsics.areEqual(cryptographyAlgorithmId, SHA3_512.INSTANCE)) {
            return 72;
        }
        throw new IllegalStateException("Unsupported hash algorithm: " + cryptographyAlgorithmId);
    }
}

package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.operations.Hasher;
import kotlin.Metadata;

/* JADX INFO: compiled from: Digest.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0006\u001a\u00020\u0007H¦\u0080\u0004R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/Digest;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "hasher", "Ldev/whyoleg/cryptography/operations/Hasher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Digest extends CryptographyAlgorithm {
    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<Digest> getId();

    Hasher hasher();
}

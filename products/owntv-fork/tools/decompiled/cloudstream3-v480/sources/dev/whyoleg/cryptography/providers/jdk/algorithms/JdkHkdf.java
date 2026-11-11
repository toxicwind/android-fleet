package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseHkdf;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkHkdf.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0094\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkHkdf;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHkdf;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "provider", "Ldev/whyoleg/cryptography/CryptographyProvider;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ldev/whyoleg/cryptography/CryptographyProvider;)V", "digestSize", "", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkHkdf extends BaseHkdf {
    private final JdkCryptographyState state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkHkdf(JdkCryptographyState state, CryptographyProvider provider) {
        super(provider);
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseHkdf
    protected int digestSize(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Pooled<MessageDigest> pooledMessageDigest = this.state.messageDigest(digest.getName());
        Object objBorrow = pooledMessageDigest.borrow();
        try {
            return ((MessageDigest) objBorrow).getDigestLength();
        } finally {
            pooledMessageDigest.recycle(objBorrow);
        }
    }
}

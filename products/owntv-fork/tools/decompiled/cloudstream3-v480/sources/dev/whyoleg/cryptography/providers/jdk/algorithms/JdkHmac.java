package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.HMAC;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseHmac;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyStateKt;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkMacSignature;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkHmac.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000fB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0094\u0080\u0004J \u0010\u000b\u001a\u00020\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\r\u001a\u00020\u000eH\u0094\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkHmac;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "blockSize", "", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "wrapKey", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "rawKey", "", "HmacKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkHmac extends BaseHmac {
    private final JdkCryptographyState state;

    public JdkHmac(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseHmac
    protected int blockSize(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return JdkHmacKt.blockSize(digest);
    }

    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseHmac
    protected HMAC.Key wrapKey(CryptographyAlgorithmId<Digest> digest, byte[] rawKey) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return new HmacKey(this, digest, rawKey);
    }

    /* JADX INFO: compiled from: JdkHmac.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u001f\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0013\u0010\n\u001a\u00060\u000bj\u0002`\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkHmac$HmacKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac$BaseKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "key", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkHmac;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;[B)V", "secretKey", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "signature", "Ldev/whyoleg/cryptography/providers/jdk/operations/JdkMacSignature;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class HmacKey extends BaseHmac.BaseKey {
        private final SecretKey secretKey;
        private final JdkMacSignature signature;
        final /* synthetic */ JdkHmac this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HmacKey(JdkHmac jdkHmac, CryptographyAlgorithmId<Digest> digest, byte[] key) {
            super(jdkHmac, digest, key);
            Intrinsics.checkNotNullParameter(digest, "digest");
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = jdkHmac;
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "Hmac" + JdkCryptographyStateKt.hashAlgorithmName(digest));
            this.secretKey = secretKeySpec;
            JdkCryptographyState jdkCryptographyState = jdkHmac.state;
            String algorithm = secretKeySpec.getAlgorithm();
            Intrinsics.checkNotNullExpressionValue(algorithm, "getAlgorithm(...)");
            this.signature = new JdkMacSignature(jdkCryptographyState, secretKeySpec, algorithm);
        }

        @Override // dev.whyoleg.cryptography.algorithms.HMAC.Key
        public SignatureGenerator signatureGenerator() {
            return this.signature;
        }

        @Override // dev.whyoleg.cryptography.algorithms.HMAC.Key
        public SignatureVerifier signatureVerifier() {
            return this.signature;
        }
    }
}

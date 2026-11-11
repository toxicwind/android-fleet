package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseAes;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkMacSignature;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkAesCmac.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u000bB\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0094\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCmac;", "Ldev/whyoleg/cryptography/algorithms/AES$CMAC;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "Ldev/whyoleg/cryptography/algorithms/AES$CMAC$Key;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapKey", "rawKey", "", "AesCmacKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkAesCmac extends BaseAes<AES.CMAC.Key> implements AES.CMAC {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return AES.CMAC.INSTANCE;
    }

    public JdkAesCmac(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseAes
    public AES.CMAC.Key wrapKey(byte[] rawKey) {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return new AesCmacKey(this, rawKey);
    }

    /* JADX INFO: compiled from: JdkAesCmac.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\f0\u0002R\b\u0012\u0004\u0012\u00020\u00010\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCmac$AesCmacKey;", "Ldev/whyoleg/cryptography/algorithms/AES$CMAC$Key;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "rawKey", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCmac;[B)V", "secretKey", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "signature", "Ldev/whyoleg/cryptography/providers/jdk/operations/JdkMacSignature;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class AesCmacKey extends BaseAes<AES.CMAC.Key>.BaseKey implements AES.CMAC.Key {
        private final SecretKey secretKey;
        private final JdkMacSignature signature;
        final /* synthetic */ JdkAesCmac this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AesCmacKey(JdkAesCmac jdkAesCmac, byte[] rawKey) {
            super(jdkAesCmac, rawKey);
            Intrinsics.checkNotNullParameter(rawKey, "rawKey");
            this.this$0 = jdkAesCmac;
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AESCMAC");
            this.secretKey = secretKeySpec;
            this.signature = new JdkMacSignature(jdkAesCmac.state, secretKeySpec, "AESCMAC");
        }

        @Override // dev.whyoleg.cryptography.algorithms.AES.CMAC.Key
        public SignatureGenerator signatureGenerator() {
            return this.signature;
        }

        @Override // dev.whyoleg.cryptography.algorithms.AES.CMAC.Key
        public SignatureVerifier signatureVerifier() {
            return this.signature;
        }
    }
}

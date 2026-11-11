package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.operations.IvCipher;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseAes;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkAesCbc.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u000bB\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0094\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCbc;", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "Ldev/whyoleg/cryptography/algorithms/AES$CBC$Key;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapKey", "rawKey", "", "AesCbcKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkAesCbc extends BaseAes<AES.CBC.Key> implements AES.CBC {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return AES.CBC.INSTANCE;
    }

    public JdkAesCbc(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseAes
    public AES.CBC.Key wrapKey(byte[] rawKey) {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return new AesCbcKey(this, rawKey);
    }

    /* JADX INFO: compiled from: JdkAesCbc.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\f0\u0002R\b\u0012\u0004\u0012\u00020\u00010\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCbc$AesCbcKey;", "Ldev/whyoleg/cryptography/algorithms/AES$CBC$Key;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "rawKey", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesCbc;[B)V", "secretKey", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "padding", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class AesCbcKey extends BaseAes<AES.CBC.Key>.BaseKey implements AES.CBC.Key {
        private final SecretKey secretKey;
        final /* synthetic */ JdkAesCbc this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AesCbcKey(JdkAesCbc jdkAesCbc, byte[] rawKey) {
            super(jdkAesCbc, rawKey);
            Intrinsics.checkNotNullParameter(rawKey, "rawKey");
            this.this$0 = jdkAesCbc;
            this.secretKey = new SecretKeySpec(rawKey, "AES");
        }

        @Override // dev.whyoleg.cryptography.algorithms.AES.CBC.Key
        public IvCipher cipher(boolean padding) {
            String str;
            JdkCryptographyState jdkCryptographyState = this.this$0.state;
            SecretKey secretKey = this.secretKey;
            if (padding) {
                str = "AES/CBC/PKCS5Padding";
            } else {
                str = "AES/CBC/NoPadding";
            }
            return new JdkAesIvCipher(jdkCryptographyState, secretKey, 16, str);
        }
    }
}

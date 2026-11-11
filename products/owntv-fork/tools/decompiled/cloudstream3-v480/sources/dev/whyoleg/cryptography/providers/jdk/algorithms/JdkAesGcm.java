package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.operations.IvAuthenticatedCipher;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseAes;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkAesGcm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u000bB\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0094\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesGcm;", "Ldev/whyoleg/cryptography/algorithms/AES$GCM;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "Ldev/whyoleg/cryptography/algorithms/AES$GCM$Key;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapKey", "rawKey", "", "AesGcmKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkAesGcm extends BaseAes<AES.GCM.Key> implements AES.GCM {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return AES.GCM.INSTANCE;
    }

    public JdkAesGcm(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseAes
    public AES.GCM.Key wrapKey(byte[] rawKey) {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return new AesGcmKey(this, rawKey);
    }

    /* JADX INFO: compiled from: JdkAesGcm.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\f0\u0002R\b\u0012\u0004\u0012\u00020\u00010\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesGcm$AesGcmKey;", "Ldev/whyoleg/cryptography/algorithms/AES$GCM$Key;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "rawKey", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesGcm;[B)V", "secretKey", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "cipher", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "tagSize", "Ldev/whyoleg/cryptography/BinarySize;", "cipher-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class AesGcmKey extends BaseAes<AES.GCM.Key>.BaseKey implements AES.GCM.Key {
        private final SecretKey secretKey;
        final /* synthetic */ JdkAesGcm this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AesGcmKey(JdkAesGcm jdkAesGcm, byte[] rawKey) {
            super(jdkAesGcm, rawKey);
            Intrinsics.checkNotNullParameter(rawKey, "rawKey");
            this.this$0 = jdkAesGcm;
            this.secretKey = new SecretKeySpec(rawKey, "AES");
        }

        @Override // dev.whyoleg.cryptography.algorithms.AES.GCM.Key
        /* JADX INFO: renamed from: cipher-6q1zMKY */
        public IvAuthenticatedCipher mo8678cipher6q1zMKY(int tagSize) {
            return new JdkAesGcmCipher(this.this$0.state, this.secretKey, BinarySize.m8660getInBitsimpl(tagSize));
        }
    }
}

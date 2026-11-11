package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaRaw.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u001a\u001b\u001cB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR1\u0010\n\u001a\u001e\u0012\b\u0012\u00060\fj\u0002`\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R9\u0010\u0012\u001a&\u0012\b\u0012\u00060\u0014j\u0002`\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0013X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R'\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "RsaRawKeyPair", "RsaRawPublicKey", "RsaRawPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkRsaRaw extends JdkRsa<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, RSA.RAW.KeyPair> implements RSA.RAW {
    private final Function2<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, RSA.RAW.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.RAW.PublicKey, RSA.RAW.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.RAW.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return RSA.RAW.CC.$default$getId(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkRsaRaw(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkRsaRaw$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkRsaRaw$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkRsaRaw$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.RAW.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.RAW.PublicKey, RSA.RAW.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, RSA.RAW.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaRaw.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw$RsaRawKeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class RsaRawKeyPair implements RSA.RAW.KeyPair {
        private final RSA.RAW.PrivateKey privateKey;
        private final RSA.RAW.PublicKey publicKey;

        public RsaRawKeyPair(RSA.RAW.PublicKey publicKey, RSA.RAW.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.RAW.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.RAW.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaRaw.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B#\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw$RsaRawPublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "encryptor", "Ldev/whyoleg/cryptography/operations/Encryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaRawPublicKey extends JdkRsa<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, RSA.RAW.KeyPair>.RsaPublicEncodableKey implements RSA.RAW.PublicKey {
        final /* synthetic */ JdkRsaRaw this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaRawPublicKey(JdkRsaRaw jdkRsaRaw, PublicKey key, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsaRaw, key, digest);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaRaw;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.RAW.PublicKey
        public Encryptor encryptor() {
            return new RsaRawEncryptor(this.this$0.getState(), getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaRaw.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003B-\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw$RsaRawPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;)V", "decryptor", "Ldev/whyoleg/cryptography/operations/Decryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaRawPrivateKey extends JdkRsa<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, RSA.RAW.KeyPair>.RsaPrivateEncodableKey implements RSA.RAW.PrivateKey {
        final /* synthetic */ JdkRsaRaw this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaRawPrivateKey(JdkRsaRaw jdkRsaRaw, PrivateKey key, CryptographyAlgorithmId<Digest> digest, RSA.RAW.PublicKey publicKey) {
            super(jdkRsaRaw, key, digest, publicKey);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaRaw;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.RAW.PrivateKey
        public Decryptor decryptor() {
            return new RsaRawDecryptor(this.this$0.getState(), getKey());
        }
    }
}

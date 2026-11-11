package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.operations.AuthenticatedDecryptor;
import dev.whyoleg.cryptography.operations.AuthenticatedEncryptor;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaOaep.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u001a\u001b\u001cB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR1\u0010\n\u001a\u001e\u0012\b\u0012\u00060\fj\u0002`\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R9\u0010\u0012\u001a&\u0012\b\u0012\u00060\u0014j\u0002`\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0013X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R'\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "RsaOaepKeyPair", "RsaOaepPublicKey", "RsaOaepPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkRsaOaep extends JdkRsa<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, RSA.OAEP.KeyPair> implements RSA.OAEP {
    private final Function2<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, RSA.OAEP.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.OAEP.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return RSA.OAEP.CC.$default$getId(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkRsaOaep(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkRsaOaep$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkRsaOaep$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkRsaOaep$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.OAEP.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, RSA.OAEP.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaOaep.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep$RsaOaepKeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class RsaOaepKeyPair implements RSA.OAEP.KeyPair {
        private final RSA.OAEP.PrivateKey privateKey;
        private final RSA.OAEP.PublicKey publicKey;

        public RsaOaepKeyPair(RSA.OAEP.PublicKey publicKey, RSA.OAEP.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.OAEP.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.OAEP.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaOaep.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B#\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep$RsaOaepPublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "encryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedEncryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaOaepPublicKey extends JdkRsa<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, RSA.OAEP.KeyPair>.RsaPublicEncodableKey implements RSA.OAEP.PublicKey {
        final /* synthetic */ JdkRsaOaep this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaOaepPublicKey(JdkRsaOaep jdkRsaOaep, PublicKey key, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsaOaep, key, digest);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaOaep;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.OAEP.PublicKey
        public AuthenticatedEncryptor encryptor() {
            return new RsaOaepEncryptor(this.this$0.getState(), getKey(), this.this$0.hashAlgorithmName(getDigest()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaOaep.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003B-\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep$RsaOaepPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;)V", "decryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaOaepPrivateKey extends JdkRsa<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, RSA.OAEP.KeyPair>.RsaPrivateEncodableKey implements RSA.OAEP.PrivateKey {
        final /* synthetic */ JdkRsaOaep this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaOaepPrivateKey(JdkRsaOaep jdkRsaOaep, PrivateKey key, CryptographyAlgorithmId<Digest> digest, RSA.OAEP.PublicKey publicKey) {
            super(jdkRsaOaep, key, digest, publicKey);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaOaep;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.OAEP.PrivateKey
        public AuthenticatedDecryptor decryptor() {
            return new RsaOaepDecryptor(this.this$0.getState(), getKey(), this.this$0.hashAlgorithmName(getDigest()));
        }
    }
}

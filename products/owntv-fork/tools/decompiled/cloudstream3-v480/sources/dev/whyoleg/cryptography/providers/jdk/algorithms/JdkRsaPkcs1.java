package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyStateKt;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureVerifier;
import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u001d\u001e\u001fB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0094\u0080\u0004R1\u0010\n\u001a\u001e\u0012\b\u0012\u00060\fj\u0002`\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R9\u0010\u0012\u001a&\u0012\b\u0012\u00060\u0014j\u0002`\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0013X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R'\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006 "}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "hashAlgorithmName", "", "digest", "RsaPkcs1KeyPair", "RsaPkcs1PublicKey", "RsaPkcs1PrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkRsaPkcs1 extends JdkRsa<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, RSA.PKCS1.KeyPair> implements RSA.PKCS1 {
    private final Function2<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, RSA.PKCS1.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.PKCS1.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return RSA.PKCS1.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkRsaPkcs1(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkRsaPkcs1$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkRsaPkcs1$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkRsaPkcs1$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.PKCS1.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, RSA.PKCS1.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected String hashAlgorithmName(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return JdkCryptographyStateKt.hashAlgorithmName(digest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPkcs1.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1$RsaPkcs1KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class RsaPkcs1KeyPair implements RSA.PKCS1.KeyPair {
        private final RSA.PKCS1.PrivateKey privateKey;
        private final RSA.PKCS1.PublicKey publicKey;

        public RsaPkcs1KeyPair(RSA.PKCS1.PublicKey publicKey, RSA.PKCS1.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.PKCS1.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.PKCS1.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPkcs1.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B#\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1$RsaPkcs1PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "encryptor", "Ldev/whyoleg/cryptography/operations/Encryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaPkcs1PublicKey extends JdkRsa<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, RSA.PKCS1.KeyPair>.RsaPublicEncodableKey implements RSA.PKCS1.PublicKey {
        final /* synthetic */ JdkRsaPkcs1 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPkcs1PublicKey(JdkRsaPkcs1 jdkRsaPkcs1, PublicKey key, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsaPkcs1, key, digest);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaPkcs1;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PKCS1.PublicKey
        public SignatureVerifier signatureVerifier() {
            return new JdkSignatureVerifier(this.this$0.getState(), getKey(), this.this$0.hashAlgorithmName(getDigest()) + "withRSA", null);
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PKCS1.PublicKey
        public Encryptor encryptor() {
            return new RsaPkcs1Encryptor(this.this$0.getState(), getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPkcs1.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003B-\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1$RsaPkcs1PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;)V", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "decryptor", "Ldev/whyoleg/cryptography/operations/Decryptor;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaPkcs1PrivateKey extends JdkRsa<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, RSA.PKCS1.KeyPair>.RsaPrivateEncodableKey implements RSA.PKCS1.PrivateKey {
        final /* synthetic */ JdkRsaPkcs1 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPkcs1PrivateKey(JdkRsaPkcs1 jdkRsaPkcs1, PrivateKey key, CryptographyAlgorithmId<Digest> digest, RSA.PKCS1.PublicKey publicKey) {
            super(jdkRsaPkcs1, key, digest, publicKey);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaPkcs1;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PKCS1.PrivateKey
        public SignatureGenerator signatureGenerator() {
            return new JdkSignatureGenerator(this.this$0.getState(), getKey(), this.this$0.hashAlgorithmName(getDigest()) + "withRSA", null);
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PKCS1.PrivateKey
        public Decryptor decryptor() {
            return new RsaPkcs1Decryptor(this.this$0.getState(), getKey());
        }
    }
}

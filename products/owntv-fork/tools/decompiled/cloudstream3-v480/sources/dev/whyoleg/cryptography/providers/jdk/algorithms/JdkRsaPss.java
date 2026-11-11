package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureVerifier;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPss.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u001a\u001b\u001cB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR1\u0010\n\u001a\u001e\u0012\b\u0012\u00060\fj\u0002`\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R9\u0010\u0012\u001a&\u0012\b\u0012\u00060\u0014j\u0002`\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0013X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R'\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "RsaPssKeyPair", "RsaPssPublicKey", "RsaPssPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkRsaPss extends JdkRsa<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, RSA.PSS.KeyPair> implements RSA.PSS {
    private final Function2<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, RSA.PSS.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.PSS.PublicKey, RSA.PSS.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.PSS.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return RSA.PSS.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkRsaPss(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkRsaPss$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkRsaPss$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkRsaPss$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<PublicKey, CryptographyAlgorithmId<Digest>, RSA.PSS.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.PSS.PublicKey, RSA.PSS.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkRsa
    protected Function2<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, RSA.PSS.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPss.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss$RsaPssKeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class RsaPssKeyPair implements RSA.PSS.KeyPair {
        private final RSA.PSS.PrivateKey privateKey;
        private final RSA.PSS.PublicKey publicKey;

        public RsaPssKeyPair(RSA.PSS.PublicKey publicKey, RSA.PSS.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.PSS.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.KeyPair
        public RSA.PSS.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPss.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B#\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss$RsaPssPublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "saltSize", "Ldev/whyoleg/cryptography/BinarySize;", "signatureVerifier-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaPssPublicKey extends JdkRsa<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, RSA.PSS.KeyPair>.RsaPublicEncodableKey implements RSA.PSS.PublicKey {
        final /* synthetic */ JdkRsaPss this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPssPublicKey(JdkRsaPss jdkRsaPss, PublicKey key, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsaPss, key, digest);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaPss;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PSS.PublicKey
        public SignatureVerifier signatureVerifier() {
            Pooled<MessageDigest> pooledMessageDigest = this.this$0.getState().messageDigest(this.this$0.hashAlgorithmName(getDigest()));
            Object objBorrow = pooledMessageDigest.borrow();
            try {
                return mo8715signatureVerifier6q1zMKY(BinarySize.INSTANCE.m8671getBytesM1VWhS4(((MessageDigest) objBorrow).getDigestLength()));
            } finally {
                pooledMessageDigest.recycle(objBorrow);
            }
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PSS.PublicKey
        /* JADX INFO: renamed from: signatureVerifier-6q1zMKY */
        public SignatureVerifier mo8715signatureVerifier6q1zMKY(int saltSize) {
            String strHashAlgorithmName = this.this$0.hashAlgorithmName(getDigest());
            return new JdkSignatureVerifier(this.this$0.getState(), getKey(), "RSASSA-PSS", new PSSParameterSpec(strHashAlgorithmName, "MGF1", new MGF1ParameterSpec(strHashAlgorithmName), BinarySize.m8661getInBytesimpl(saltSize), 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkRsaPss.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003B-\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss$RsaPssPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;)V", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "saltSize", "Ldev/whyoleg/cryptography/BinarySize;", "signatureGenerator-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class RsaPssPrivateKey extends JdkRsa<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, RSA.PSS.KeyPair>.RsaPrivateEncodableKey implements RSA.PSS.PrivateKey {
        final /* synthetic */ JdkRsaPss this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPssPrivateKey(JdkRsaPss jdkRsaPss, PrivateKey key, CryptographyAlgorithmId<Digest> digest, RSA.PSS.PublicKey publicKey) {
            super(jdkRsaPss, key, digest, publicKey);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsaPss;
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PSS.PrivateKey
        public SignatureGenerator signatureGenerator() {
            Pooled<MessageDigest> pooledMessageDigest = this.this$0.getState().messageDigest(this.this$0.hashAlgorithmName(getDigest()));
            Object objBorrow = pooledMessageDigest.borrow();
            try {
                return mo8714signatureGenerator6q1zMKY(BinarySize.INSTANCE.m8671getBytesM1VWhS4(((MessageDigest) objBorrow).getDigestLength()));
            } finally {
                pooledMessageDigest.recycle(objBorrow);
            }
        }

        @Override // dev.whyoleg.cryptography.algorithms.RSA.PSS.PrivateKey
        /* JADX INFO: renamed from: signatureGenerator-6q1zMKY */
        public SignatureGenerator mo8714signatureGenerator6q1zMKY(int saltSize) {
            String strHashAlgorithmName = this.this$0.hashAlgorithmName(getDigest());
            return new JdkSignatureGenerator(this.this$0.getState(), getKey(), "RSASSA-PSS", new PSSParameterSpec(strHashAlgorithmName, "MGF1", new MGF1ParameterSpec(strHashAlgorithmName), BinarySize.m8661getInBytesimpl(saltSize), 1));
        }
    }
}

package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.EC;
import dev.whyoleg.cryptography.algorithms.ECDH;
import dev.whyoleg.cryptography.operations.SharedSecretGenerator;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkKeyAgreementKt;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyAgreement;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: JdkEcdh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u0019\u001a\u001bB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR+\u0010\n\u001a\u0018\u0012\b\u0012\u00060\fj\u0002`\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R3\u0010\u0011\u001a \u0012\b\u0012\u00060\u0013j\u0002`\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0012X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDH;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "EcdhKeyPair", "EcdhPublicKey", "EcdhPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkEcdh extends JdkEc<ECDH.PublicKey, ECDH.PrivateKey, ECDH.KeyPair> implements ECDH {
    private final Function2<ECDH.PublicKey, ECDH.PrivateKey, ECDH.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, EC.Curve, ECDH.PublicKey, ECDH.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, EC.Curve, ECDH.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return ECDH.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkEcdh(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkEcdh$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkEcdh$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkEcdh$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function2<PublicKey, EC.Curve, ECDH.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function3<PrivateKey, EC.Curve, ECDH.PublicKey, ECDH.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function2<ECDH.PublicKey, ECDH.PrivateKey, ECDH.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdh.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh$EcdhKeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class EcdhKeyPair implements ECDH.KeyPair {
        private final ECDH.PrivateKey privateKey;
        private final ECDH.PublicKey publicKey;

        public EcdhKeyPair(ECDH.PublicKey publicKey, ECDH.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EC.KeyPair
        public ECDH.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EC.KeyPair
        public ECDH.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdh.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\u0012\u0004\u0012\u00020\u00040\u0006B\u001d\bF\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0096\u0080\u0004R\u0019\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u000fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh$EcdhPublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;Ljava/security/PublicKey;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "sharedSecretGenerator", "generateSharedSecretToByteArrayBlocking", "", "other", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class EcdhPublicKey extends JdkEc<ECDH.PublicKey, ECDH.PrivateKey, ECDH.KeyPair>.BaseEcPublicKey implements ECDH.PublicKey, SharedSecretGenerator<ECDH.PrivateKey> {
        private final Pooled<KeyAgreement> keyAgreement;
        final /* synthetic */ JdkEcdh this$0;

        public /* synthetic */ EcdhPublicKey(JdkEcdh jdkEcdh, PublicKey publicKey, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEcdh, publicKey, str);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(ECDH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, privateKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(ECDH.PrivateKey privateKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(privateKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(ECDH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, privateKey, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcdhPublicKey(JdkEcdh jdkEcdh, PublicKey key, String curve) {
            super(jdkEcdh, key, curve, null);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEcdh;
            this.keyAgreement = jdkEcdh.getState().keyAgreement("ECDH");
        }

        @Override // dev.whyoleg.cryptography.algorithms.ECDH.PublicKey
        public SharedSecretGenerator<ECDH.PrivateKey> sharedSecretGenerator() {
            return this;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(ECDH.PrivateKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (!(other instanceof EcdhPrivateKey)) {
                throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
            }
            return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.this$0.getState(), ((EcdhPrivateKey) other).getKey(), getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdh.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u00032\b\u0012\u0004\u0012\u00020\u00040\u0006B'\bF\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0096\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0096\u0080\u0004R\u0019\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0011j\u0002`\u00120\u0010X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh$EcdhPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;Ljava/security/PrivateKey;Ljava/lang/String;Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "sharedSecretGenerator", "generateSharedSecretToByteArrayBlocking", "", "other", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class EcdhPrivateKey extends JdkEc<ECDH.PublicKey, ECDH.PrivateKey, ECDH.KeyPair>.BaseEcPrivateKey implements ECDH.PrivateKey, SharedSecretGenerator<ECDH.PublicKey> {
        private final Pooled<KeyAgreement> keyAgreement;
        final /* synthetic */ JdkEcdh this$0;

        public /* synthetic */ EcdhPrivateKey(JdkEcdh jdkEcdh, PrivateKey privateKey, String str, ECDH.PublicKey publicKey, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEcdh, privateKey, str, publicKey);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(ECDH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, publicKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(ECDH.PublicKey publicKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(publicKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(ECDH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, publicKey, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcdhPrivateKey(JdkEcdh jdkEcdh, PrivateKey key, String curve, ECDH.PublicKey publicKey) {
            super(jdkEcdh, key, curve, publicKey, null);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEcdh;
            this.keyAgreement = jdkEcdh.getState().keyAgreement("ECDH");
        }

        @Override // dev.whyoleg.cryptography.algorithms.ECDH.PrivateKey
        public SharedSecretGenerator<ECDH.PublicKey> sharedSecretGenerator() {
            return this;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(ECDH.PublicKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (!(other instanceof EcdhPublicKey)) {
                throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
            }
            return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.this$0.getState(), getKey(), ((EcdhPublicKey) other).getKey());
        }
    }
}

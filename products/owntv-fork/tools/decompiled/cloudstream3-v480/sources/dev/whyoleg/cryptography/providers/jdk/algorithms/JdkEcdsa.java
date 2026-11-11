package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.EC;
import dev.whyoleg.cryptography.algorithms.ECDSA;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.base.algorithms.DssRawSignatureGenerator;
import dev.whyoleg.cryptography.providers.base.algorithms.DssRawSignatureVerifier;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyStateKt;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureVerifier;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECKey;
import java.security.spec.ECParameterSpec;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u0005:\u0003\u0019\u001a\u001bB\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR+\u0010\n\u001a\u0018\u0012\b\u0012\u00060\fj\u0002`\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00020\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R3\u0010\u0011\u001a \u0012\b\u0012\u00060\u0013j\u0002`\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0012X\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u000bX\u0094\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDSA;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "EcdsaKeyPair", "EcdsaPublicKey", "EcdsaPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkEcdsa extends JdkEc<ECDSA.PublicKey, ECDSA.PrivateKey, ECDSA.KeyPair> implements ECDSA {
    private final Function2<ECDSA.PublicKey, ECDSA.PrivateKey, ECDSA.KeyPair> wrapKeyPair;
    private final Function3<PrivateKey, EC.Curve, ECDSA.PublicKey, ECDSA.PrivateKey> wrapPrivateKey;
    private final Function2<PublicKey, EC.Curve, ECDSA.PublicKey> wrapPublicKey;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return ECDSA.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JdkEcdsa(JdkCryptographyState state) {
        super(state, null);
        Intrinsics.checkNotNullParameter(state, "state");
        this.wrapPublicKey = new JdkEcdsa$wrapPublicKey$1(this);
        this.wrapPrivateKey = new JdkEcdsa$wrapPrivateKey$1(this);
        this.wrapKeyPair = JdkEcdsa$wrapKeyPair$1.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function2<PublicKey, EC.Curve, ECDSA.PublicKey> getWrapPublicKey() {
        return this.wrapPublicKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function3<PrivateKey, EC.Curve, ECDSA.PublicKey, ECDSA.PrivateKey> getWrapPrivateKey() {
        return this.wrapPrivateKey;
    }

    @Override // dev.whyoleg.cryptography.providers.jdk.algorithms.JdkEc
    protected Function2<ECDSA.PublicKey, ECDSA.PrivateKey, ECDSA.KeyPair> getWrapKeyPair() {
        return this.wrapKeyPair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdsa.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa$EcdsaKeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class EcdsaKeyPair implements ECDSA.KeyPair {
        private final ECDSA.PrivateKey privateKey;
        private final ECDSA.PublicKey publicKey;

        public EcdsaKeyPair(ECDSA.PublicKey publicKey, ECDSA.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EC.KeyPair
        public ECDSA.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EC.KeyPair
        public ECDSA.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdsa.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B\u001d\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa$EcdsaPublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;Ljava/security/PublicKey;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/ECDSA$SignatureFormat;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class EcdsaPublicKey extends JdkEc<ECDSA.PublicKey, ECDSA.PrivateKey, ECDSA.KeyPair>.BaseEcPublicKey implements ECDSA.PublicKey {
        final /* synthetic */ JdkEcdsa this$0;

        /* JADX INFO: compiled from: JdkEcdsa.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ECDSA.SignatureFormat.values().length];
                try {
                    iArr[ECDSA.SignatureFormat.DER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ECDSA.SignatureFormat.RAW.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ EcdsaPublicKey(JdkEcdsa jdkEcdsa, PublicKey publicKey, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEcdsa, publicKey, str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcdsaPublicKey(JdkEcdsa jdkEcdsa, PublicKey key, String curve) {
            super(jdkEcdsa, key, curve, null);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEcdsa;
        }

        @Override // dev.whyoleg.cryptography.algorithms.ECDSA.PublicKey
        public SignatureVerifier signatureVerifier(CryptographyAlgorithmId<Digest> digest, ECDSA.SignatureFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            JdkSignatureVerifier jdkSignatureVerifier = new JdkSignatureVerifier(this.this$0.getState(), getKey(), JdkCryptographyStateKt.hashECAlgorithmName(digest) + "withECDSA", null);
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i == 1) {
                return jdkSignatureVerifier;
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            PublicKey key = getKey();
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.interfaces.ECKey");
            ECParameterSpec params = ((ECKey) key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            return new DssRawSignatureVerifier(jdkSignatureVerifier, JdkEcKt.curveOrderSize(params));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JdkEcdsa.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00180\u0002R\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003B'\bF\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa$EcdsaPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;Ljava/security/PrivateKey;Ljava/lang/String;Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/ECDSA$SignatureFormat;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class EcdsaPrivateKey extends JdkEc<ECDSA.PublicKey, ECDSA.PrivateKey, ECDSA.KeyPair>.BaseEcPrivateKey implements ECDSA.PrivateKey {
        final /* synthetic */ JdkEcdsa this$0;

        /* JADX INFO: compiled from: JdkEcdsa.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ECDSA.SignatureFormat.values().length];
                try {
                    iArr[ECDSA.SignatureFormat.DER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ECDSA.SignatureFormat.RAW.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ EcdsaPrivateKey(JdkEcdsa jdkEcdsa, PrivateKey privateKey, String str, ECDSA.PublicKey publicKey, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEcdsa, privateKey, str, publicKey);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcdsaPrivateKey(JdkEcdsa jdkEcdsa, PrivateKey key, String curve, ECDSA.PublicKey publicKey) {
            super(jdkEcdsa, key, curve, publicKey, null);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEcdsa;
        }

        @Override // dev.whyoleg.cryptography.algorithms.ECDSA.PrivateKey
        public SignatureGenerator signatureGenerator(CryptographyAlgorithmId<Digest> digest, ECDSA.SignatureFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            JdkSignatureGenerator jdkSignatureGenerator = new JdkSignatureGenerator(this.this$0.getState(), getKey(), JdkCryptographyStateKt.hashECAlgorithmName(digest) + "withECDSA", null);
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i == 1) {
                return jdkSignatureGenerator;
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            PrivateKey key = getKey();
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.interfaces.ECKey");
            ECParameterSpec params = ((ECKey) key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            return new DssRawSignatureGenerator(jdkSignatureGenerator, JdkEcKt.curveOrderSize(params));
        }
    }
}

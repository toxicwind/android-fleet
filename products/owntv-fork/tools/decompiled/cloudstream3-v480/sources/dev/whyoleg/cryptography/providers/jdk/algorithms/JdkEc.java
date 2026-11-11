package dev.whyoleg.cryptography.providers.jdk.algorithms;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.algorithms.EC;
import dev.whyoleg.cryptography.algorithms.EC.KeyPair;
import dev.whyoleg.cryptography.algorithms.EC.PrivateKey;
import dev.whyoleg.cryptography.algorithms.EC.PublicKey;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.internal.BouncyCastleBridge;
import dev.whyoleg.cryptography.providers.jdk.internal.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidParameterSpecException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JdkEc.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u0004*\u0014\b\u0002\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00050\u0007:\u000534567B\u0011\bD\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010 \u001a\u00020!H\u0082\u0080\u0004J%\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010%\u001a\u00020\u0012H\u0086\u0080\u0004¢\u0006\u0004\b&\u0010'J%\u0010(\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00028\u00010#2\u0006\u0010%\u001a\u00020\u0012H\u0086\u0080\u0004¢\u0006\u0004\b*\u0010'J\u001f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00020,2\u0006\u0010%\u001a\u00020\u0012H\u0086\u0080\u0004¢\u0006\u0004\b-\u0010.R\u0015\u0010\b\u001a\u00020\tX\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR)\u0010\u000e\u001a\u0018\u0012\b\u0012\u00060\u0010j\u0002`\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028\u00000\u000fX¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R1\u0010\u0015\u001a \u0012\b\u0012\u00060\u0017j\u0002`\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u00010\u0016X¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR%\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000fX¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0014R\u0019\u0010/\u001a\u000200*\u00020\u00128BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b1\u00102\u0082\u0001\u000289¨\u0006:"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "KP", "Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/EC;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "getState", "()Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "algorithmParameters", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "spec", "Ljava/security/spec/AlgorithmParameterSpec;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "curve", "publicKeyDecoder-yfdS0HE", "(Ljava/lang/String;)Ldev/whyoleg/cryptography/materials/Decoder;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "privateKeyDecoder-yfdS0HE", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keyPairGenerator-yfdS0HE", "(Ljava/lang/String;)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "jdkName", "", "getJdkName-yfdS0HE", "(Ljava/lang/String;)Ljava/lang/String;", "EcKeyPairGenerator", "EcPublicKeyDecoder", "EcPrivateKeyDecoder", "BaseEcPublicKey", "BaseEcPrivateKey", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkEc<PublicK extends EC.PublicKey, PrivateK extends EC.PrivateKey<PublicK>, KP extends EC.KeyPair<PublicK, PrivateK>> implements EC<PublicK, PrivateK, KP> {
    private final JdkCryptographyState state;

    public /* synthetic */ JdkEc(JdkCryptographyState jdkCryptographyState, DefaultConstructorMarker defaultConstructorMarker) {
        this(jdkCryptographyState);
    }

    protected abstract Function2<PublicK, PrivateK, KP> getWrapKeyPair();

    protected abstract Function3<java.security.PrivateKey, EC.Curve, PublicK, PrivateK> getWrapPrivateKey();

    protected abstract Function2<java.security.PublicKey, EC.Curve, PublicK> getWrapPublicKey();

    private JdkEc(JdkCryptographyState jdkCryptographyState) {
        this.state = jdkCryptographyState;
    }

    protected final JdkCryptographyState getState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlgorithmParameters algorithmParameters(AlgorithmParameterSpec spec) throws InvalidParameterSpecException {
        AlgorithmParameters algorithmParameters = this.state.algorithmParameters("EC");
        algorithmParameters.init(spec);
        return algorithmParameters;
    }

    @Override // dev.whyoleg.cryptography.algorithms.EC
    /* JADX INFO: renamed from: publicKeyDecoder-yfdS0HE */
    public final Decoder<EC.PublicKey.Format, PublicK> mo8689publicKeyDecoderyfdS0HE(String curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new EcPublicKeyDecoder(this, curve, null);
    }

    @Override // dev.whyoleg.cryptography.algorithms.EC
    /* JADX INFO: renamed from: privateKeyDecoder-yfdS0HE */
    public final Decoder<EC.PrivateKey.Format, PrivateK> mo8688privateKeyDecoderyfdS0HE(String curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new EcPrivateKeyDecoder(this, curve, null);
    }

    @Override // dev.whyoleg.cryptography.algorithms.EC
    /* JADX INFO: renamed from: keyPairGenerator-yfdS0HE */
    public final KeyGenerator<KP> mo8687keyPairGeneratoryfdS0HE(String curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new EcKeyPairGenerator(this, curve, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getJdkName-yfdS0HE, reason: not valid java name */
    public final String m8749getJdkNameyfdS0HE(String str) {
        if (EC.Curve.m8693equalsimpl0(str, EC.Curve.INSTANCE.m8700getP256pVITJAk())) {
            return "secp256r1";
        }
        if (EC.Curve.m8693equalsimpl0(str, EC.Curve.INSTANCE.m8701getP384pVITJAk())) {
            return "secp384r1";
        }
        return EC.Curve.m8693equalsimpl0(str, EC.Curve.INSTANCE.m8702getP521pVITJAk()) ? "secp521r1" : str;
    }

    /* JADX INFO: compiled from: JdkEc.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u0017\u0010\u000b\u001a\u00028\u0002*\u00060\fj\u0002`\rH\u0094\u0080\u0004¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$EcKeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "(Ljava/security/KeyPair;)Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EcKeyPairGenerator extends JdkKeyPairGenerator<KP> {
        private final String curve;
        final /* synthetic */ JdkEc<PublicK, PrivateK, KP> this$0;

        public /* synthetic */ EcKeyPairGenerator(JdkEc jdkEc, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEc, str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcKeyPairGenerator(JdkEc jdkEc, String curve) {
            super(jdkEc.getState(), "EC");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEc;
            this.curve = curve;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        protected void init(KeyPairGenerator keyPairGenerator) throws InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(keyPairGenerator, "<this>");
            keyPairGenerator.initialize(new ECGenParameterSpec(this.this$0.m8749getJdkNameyfdS0HE(this.curve)), getState().getSecureRandom());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        public KP convert(java.security.KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            Function2<java.security.PublicKey, EC.Curve, PublicK> wrapPublicKey = this.this$0.getWrapPublicKey();
            java.security.PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNullExpressionValue(publicKey, "getPublic(...)");
            PublicK publickInvoke = wrapPublicKey.invoke(publicKey, EC.Curve.m8690boximpl(this.curve));
            Function3<java.security.PrivateKey, EC.Curve, PublicK, PrivateK> wrapPrivateKey = this.this$0.getWrapPrivateKey();
            java.security.PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
            return this.this$0.getWrapKeyPair().invoke(publickInvoke, wrapPrivateKey.invoke(privateKey, EC.Curve.m8690boximpl(this.curve), publickInvoke));
        }
    }

    /* JADX INFO: compiled from: JdkEc.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00028\u0000*\u00060\u0018j\u0002`\u0019H\u0094\u0080\u0004¢\u0006\u0002\u0010\u001aJ\u001f\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001dJ\u0017\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0016J\u0017\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016R\u0011\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0007R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$EcPublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "parameters", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "curveName", "", "getCurveName", "()Ljava/lang/String;", "fromPrivateKey", "privateKey", "Ljava/security/interfaces/ECPrivateKey;", "(Ljava/security/interfaces/ECPrivateKey;)Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "fromEncodedPrivateKey", "bytes", "", "([B)Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "(Ljava/security/PublicKey;)Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "decodeFromByteArrayBlocking", "format", "(Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;[B)Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "decodeFromJwk", "decodeFromRaw", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EcPublicKeyDecoder extends JdkPublicKeyDecoder<EC.PublicKey.Format, PublicK> {
        private final String curve;
        private final AlgorithmParameters parameters;
        final /* synthetic */ JdkEc<PublicK, PrivateK, KP> this$0;

        public /* synthetic */ EcPublicKeyDecoder(JdkEc jdkEc, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEc, str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcPublicKeyDecoder(JdkEc jdkEc, String curve) {
            super(jdkEc.getState(), "EC");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEc;
            this.curve = curve;
            this.parameters = jdkEc.algorithmParameters(new ECGenParameterSpec(jdkEc.m8749getJdkNameyfdS0HE(curve)));
        }

        private final String getCurveName() {
            return JdkEcKt.curveName(this.parameters);
        }

        public final PublicK fromPrivateKey(ECPrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            ECPublicKeySpec eCPublicKeySpecDeriveEcPublicKey = BouncyCastleBridge.INSTANCE.deriveEcPublicKey(privateKey, getCurveName());
            if (eCPublicKeySpecDeriveEcPublicKey != null) {
                return (PublicK) decode(eCPublicKeySpecDeriveEcPublicKey);
            }
            throw new IllegalStateException("Getting public key from private key for EC is not supported in JDK without BouncyCastle APIs".toString());
        }

        public final PublicK fromEncodedPrivateKey(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            byte[] ecPublicKeyFromPrivateKeyPkcs8 = KeysKt.getEcPublicKeyFromPrivateKeyPkcs8(bytes);
            if (ecPublicKeyFromPrivateKeyPkcs8 != null) {
                return (PublicK) decodeFromRaw(ecPublicKeyFromPrivateKeyPkcs8);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder
        public PublicK convert(java.security.PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            if (!(publicKey instanceof ECPublicKey)) {
                throw new IllegalStateException("Check failed.");
            }
            JdkEc<PublicK, PrivateK, KP> jdkEc = this.this$0;
            ECParameterSpec params = ((ECPublicKey) publicKey).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            String strCurveName = JdkEcKt.curveName(jdkEc.algorithmParameters(params));
            if (!Intrinsics.areEqual(getCurveName(), strCurveName)) {
                throw new IllegalStateException(("Key curve " + strCurveName + " is not equal to expected curve " + getCurveName()).toString());
            }
            return this.this$0.getWrapPublicKey().invoke(publicKey, EC.Curve.m8690boximpl(this.curve));
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public PublicK decodeFromByteArrayBlocking(EC.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.JWK.INSTANCE)) {
                return (PublicK) decodeFromJwk(bytes);
            }
            if (!Intrinsics.areEqual(format, EC.PublicKey.Format.RAW.INSTANCE) && !Intrinsics.areEqual(format, EC.PublicKey.Format.RAW.Compressed.INSTANCE)) {
                if (Intrinsics.areEqual(format, EC.PublicKey.Format.DER.INSTANCE)) {
                    return (PublicK) decodeFromDer(bytes);
                }
                if (Intrinsics.areEqual(format, EC.PublicKey.Format.PEM.INSTANCE)) {
                    return (PublicK) decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes));
                }
                throw new NoWhenBranchMatchedException();
            }
            return (PublicK) decodeFromRaw(bytes);
        }

        private final PublicK decodeFromJwk(byte[] bytes) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) this.parameters.getParameterSpec(ECParameterSpec.class);
            JsonWebKeys jsonWebKeys = JsonWebKeys.INSTANCE;
            String str = this.curve;
            Intrinsics.checkNotNull(eCParameterSpec);
            return (PublicK) decodeFromRaw(jsonWebKeys.m8740decodeEcPublicKeyXWxlsIE(str, JdkEcKt.curveOrderSize(eCParameterSpec), bytes));
        }

        public final PublicK decodeFromRaw(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (bytes.length == 0) {
                throw new IllegalStateException("Encoded key is empty!".toString());
            }
            ECParameterSpec eCParameterSpec = (ECParameterSpec) this.parameters.getParameterSpec(ECParameterSpec.class);
            Intrinsics.checkNotNull(eCParameterSpec);
            return (PublicK) decode(new ECPublicKeySpec(JdkEcKt.decodePoint(eCParameterSpec, bytes), eCParameterSpec));
        }
    }

    /* JADX INFO: compiled from: JdkEc.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00010\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000f\u001a\u00028\u0001*\u00060\u0010j\u0002`\u0011H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0012J\u001f\u0010\u0013\u001a\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0017J\u0017\u0010\u0018\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\u00028\u00012\u0006\u0010\u001b\u001a\u00020\u0016H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0019J%\u0010\u001c\u001a\u00028\u00012\n\u0010\u001d\u001a\u00060\u0010j\u0002`\u00112\b\u0010\u001e\u001a\u0004\u0018\u00018\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010\u001fR\u0011\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0007R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$EcPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "parameters", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "curveName", "", "getCurveName", "()Ljava/lang/String;", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "(Ljava/security/PrivateKey;)Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "(Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;[B)Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "decodeFromJwk", "([B)Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "decodeFromDer", TvContractCompat.PARAM_INPUT, "create", "privateKey", "publicKey", "(Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;)Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EcPrivateKeyDecoder extends JdkPrivateKeyDecoder<EC.PrivateKey.Format, PrivateK> {
        private final String curve;
        private final AlgorithmParameters parameters;
        final /* synthetic */ JdkEc<PublicK, PrivateK, KP> this$0;

        public /* synthetic */ EcPrivateKeyDecoder(JdkEc jdkEc, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEc, str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private EcPrivateKeyDecoder(JdkEc jdkEc, String curve) {
            super(jdkEc.getState(), "EC");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEc;
            this.curve = curve;
            this.parameters = jdkEc.algorithmParameters(new ECGenParameterSpec(jdkEc.m8749getJdkNameyfdS0HE(curve)));
        }

        private final String getCurveName() {
            return JdkEcKt.curveName(this.parameters);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public PrivateK convert(java.security.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            return (PrivateK) create(privateKey, null);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public PrivateK decodeFromByteArrayBlocking(EC.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.JWK.INSTANCE)) {
                return (PrivateK) decodeFromJwk(bytes);
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.RAW.INSTANCE)) {
                return (PrivateK) decode(new ECPrivateKeySpec(new BigInteger(1, bytes), (ECParameterSpec) this.parameters.getParameterSpec(ECParameterSpec.class)));
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.DER.INSTANCE)) {
                return (PrivateK) decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.PEM.INSTANCE)) {
                return (PrivateK) decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes));
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.DER.SEC1.INSTANCE)) {
                return (PrivateK) decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.convertEcPrivateKeyFromSec1ToPkcs8(bytes));
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.PEM.SEC1.INSTANCE)) {
                return (PrivateK) decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.convertEcPrivateKeyFromSec1ToPkcs8(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8812getEcPrivateKey2EFq_Wg(), bytes)));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final PrivateK decodeFromJwk(byte[] bytes) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) this.parameters.getParameterSpec(ECParameterSpec.class);
            JsonWebKeys jsonWebKeys = JsonWebKeys.INSTANCE;
            String str = this.curve;
            Intrinsics.checkNotNull(eCParameterSpec);
            JsonWebKeys.EcPrivateKeyComponents ecPrivateKeyComponentsM8739decodeEcPrivateKeyXWxlsIE = jsonWebKeys.m8739decodeEcPrivateKeyXWxlsIE(str, JdkEcKt.curveOrderSize(eCParameterSpec), bytes);
            return (PrivateK) this.this$0.getWrapPrivateKey().invoke(decodeRaw(new ECPrivateKeySpec(new BigInteger(1, ecPrivateKeyComponentsM8739decodeEcPrivateKeyXWxlsIE.getPrivateKey()), eCParameterSpec)), EC.Curve.m8690boximpl(this.curve), new EcPublicKeyDecoder(this.this$0, this.curve, null).decodeFromRaw(ecPrivateKeyComponentsM8739decodeEcPrivateKeyXWxlsIE.getPublicKey()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public PrivateK decodeFromDer(byte[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            return (PrivateK) create(decodeFromDerRaw(input), new EcPublicKeyDecoder(this.this$0, this.curve, null).fromEncodedPrivateKey(input));
        }

        private final PrivateK create(java.security.PrivateKey privateKey, PublicK publicKey) {
            if (!(privateKey instanceof ECPrivateKey)) {
                throw new IllegalStateException("Check failed.");
            }
            JdkEc<PublicK, PrivateK, KP> jdkEc = this.this$0;
            ECParameterSpec params = ((ECPrivateKey) privateKey).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            String strCurveName = JdkEcKt.curveName(jdkEc.algorithmParameters(params));
            if (!Intrinsics.areEqual(getCurveName(), strCurveName)) {
                throw new IllegalStateException(("Key curve " + strCurveName + " is not equal to expected curve " + getCurveName()).toString());
            }
            return this.this$0.getWrapPrivateKey().invoke(privateKey, EC.Curve.m8690boximpl(this.curve), publicKey);
        }
    }

    /* JADX INFO: compiled from: JdkEc.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b¤\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001d\bF\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003H\u0086\u0080\u0004J\n\u0010\u0011\u001a\u00020\u000fH\u0082\u0080\u0004J\n\u0010\u0012\u001a\u00020\u000fH\u0082\u0080\u0004J\n\u0010\u0013\u001a\u00020\u000fH\u0082\u0080\u0004J\u001e\u0010\u0014\u001a\u00020\u0015*\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0082\u0080\u0004R\u0019\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;Ljava/security/PublicKey;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getKey", "()Ljava/security/PublicKey;", "Ljava/lang/String;", "encodeToByteArrayBlocking", "", "format", "encodeToJwk", "compressedToByteArray", "uncompressedToByteArray", "putCoordinate", "", ES6Iterator.VALUE_PROPERTY, "Ljava/math/BigInteger;", "initialOffset", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    protected abstract class BaseEcPublicKey extends JdkEncodableKey<EC.PublicKey.Format> implements EC.PublicKey {
        private final String curve;
        private final java.security.PublicKey key;
        final /* synthetic */ JdkEc<PublicK, PrivateK, KP> this$0;

        public /* synthetic */ BaseEcPublicKey(JdkEc jdkEc, java.security.PublicKey publicKey, String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEc, publicKey, str);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private BaseEcPublicKey(JdkEc jdkEc, java.security.PublicKey key, String curve) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEc;
            this.key = key;
            this.curve = curve;
        }

        public final java.security.PublicKey getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(EC.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.JWK.INSTANCE)) {
                return encodeToJwk();
            }
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.RAW.INSTANCE)) {
                return uncompressedToByteArray();
            }
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.RAW.Compressed.INSTANCE)) {
                return compressedToByteArray();
            }
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, EC.PublicKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }

        private final byte[] encodeToJwk() {
            java.security.PublicKey publicKey = this.key;
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.ECPublicKey");
            JsonWebKeys jsonWebKeys = JsonWebKeys.INSTANCE;
            String str = this.curve;
            ECParameterSpec params = ((ECPublicKey) this.key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            return jsonWebKeys.m8742encodeEcPublicKeyXWxlsIE(str, JdkEcKt.curveOrderSize(params), uncompressedToByteArray());
        }

        private final byte[] compressedToByteArray() {
            java.security.PublicKey publicKey = this.key;
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.ECPublicKey");
            ECParameterSpec params = ((ECPublicKey) this.key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            int iCurveOrderSize = JdkEcKt.curveOrderSize(params);
            byte[] bArr = new byte[iCurveOrderSize + 1];
            bArr[0] = ((ECPublicKey) this.key).getW().getAffineY().testBit(0) ? (byte) 3 : (byte) 2;
            BigInteger affineX = ((ECPublicKey) this.key).getW().getAffineX();
            Intrinsics.checkNotNullExpressionValue(affineX, "getAffineX(...)");
            putCoordinate(bArr, affineX, iCurveOrderSize);
            return bArr;
        }

        private final byte[] uncompressedToByteArray() {
            java.security.PublicKey publicKey = this.key;
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.ECPublicKey");
            ECParameterSpec params = ((ECPublicKey) this.key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            int iCurveOrderSize = JdkEcKt.curveOrderSize(params);
            int i = iCurveOrderSize * 2;
            byte[] bArr = new byte[i + 1];
            bArr[0] = 4;
            BigInteger affineX = ((ECPublicKey) this.key).getW().getAffineX();
            Intrinsics.checkNotNullExpressionValue(affineX, "getAffineX(...)");
            putCoordinate(bArr, affineX, iCurveOrderSize);
            BigInteger affineY = ((ECPublicKey) this.key).getW().getAffineY();
            Intrinsics.checkNotNullExpressionValue(affineY, "getAffineY(...)");
            putCoordinate(bArr, affineY, i);
            return bArr;
        }

        private final void putCoordinate(byte[] bArr, BigInteger bigInteger, int i) {
            byte[] byteArray = bigInteger.toByteArray();
            Intrinsics.checkNotNull(byteArray);
            int length = byteArray.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (byteArray[i2] != 0) {
                    break;
                } else {
                    i2++;
                }
            }
            int i3 = i2 == -1 ? 0 : i2;
            ArraysKt.copyInto$default(byteArray, bArr, (i - byteArray.length) + 1 + i3, i3, 0, 8, (Object) null);
        }
    }

    /* JADX INFO: compiled from: JdkEc.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b¤\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B'\bF\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0010\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0086\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0013H\u0082\u0080\u0004J\u000e\u0010\u0016\u001a\u00020\u0013*\u00020\u0017H\u0082\u0080\u0004R\u0019\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00018\u0000X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u000f¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc$BaseEcPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEc;Ljava/security/PrivateKey;Ljava/lang/String;Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getKey", "()Ljava/security/PrivateKey;", "Ljava/lang/String;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "getPublicKeyBlocking", "()Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "encodeToByteArrayBlocking", "", "format", "encodeToJwk", "secretToByteArray", "Ljava/security/interfaces/ECPrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    protected abstract class BaseEcPrivateKey extends JdkEncodableKey<EC.PrivateKey.Format> implements EC.PrivateKey<PublicK> {
        private final String curve;
        private final java.security.PrivateKey key;
        private PublicK publicKey;
        final /* synthetic */ JdkEc<PublicK, PrivateK, KP> this$0;

        public /* synthetic */ BaseEcPrivateKey(JdkEc jdkEc, java.security.PrivateKey privateKey, String str, EC.PublicKey publicKey, DefaultConstructorMarker defaultConstructorMarker) {
            this(jdkEc, privateKey, str, publicKey);
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private BaseEcPrivateKey(JdkEc jdkEc, java.security.PrivateKey key, String curve, PublicK publick) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEc;
            this.key = key;
            this.curve = curve;
            this.publicKey = publick;
        }

        public final java.security.PrivateKey getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public PublicK getPublicKeyBlocking() {
            if (this.publicKey == null) {
                java.security.PrivateKey privateKey = this.key;
                Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.ECPrivateKey");
                this.publicKey = (PublicK) new EcPublicKeyDecoder(this.this$0, this.curve, null).fromPrivateKey((ECPrivateKey) this.key);
            }
            PublicK publick = this.publicKey;
            Intrinsics.checkNotNull(publick);
            return publick;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(EC.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.JWK.INSTANCE)) {
                return encodeToJwk();
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.RAW.INSTANCE)) {
                java.security.PrivateKey privateKey = this.key;
                Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.ECPrivateKey");
                return secretToByteArray((ECPrivateKey) privateKey);
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.DER.SEC1.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.convertEcPrivateKeyFromPkcs8ToSec1(encodeToDer());
            }
            if (Intrinsics.areEqual(format, EC.PrivateKey.Format.PEM.SEC1.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8812getEcPrivateKey2EFq_Wg(), dev.whyoleg.cryptography.providers.base.materials.KeysKt.convertEcPrivateKeyFromPkcs8ToSec1(encodeToDer()));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final byte[] encodeToJwk() {
            java.security.PrivateKey privateKey = this.key;
            Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.ECPrivateKey");
            ECParameterSpec params = ((ECPrivateKey) this.key).getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            return JsonWebKeys.INSTANCE.m8741encodeEcPrivateKeyDFwOJLc(this.curve, JdkEcKt.curveOrderSize(params), getPublicKeyBlocking().encodeToByteArrayBlocking(EC.PublicKey.Format.RAW.INSTANCE), secretToByteArray((ECPrivateKey) this.key));
        }

        private final byte[] secretToByteArray(ECPrivateKey eCPrivateKey) {
            int i;
            ECParameterSpec params = eCPrivateKey.getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            int iCurveOrderSize = JdkEcKt.curveOrderSize(params);
            byte[] byteArray = eCPrivateKey.getS().toByteArray();
            Intrinsics.checkNotNull(byteArray);
            int length = byteArray.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i = -1;
                    break;
                }
                if (byteArray[i2] != 0) {
                    i = i2;
                    break;
                }
                i2++;
            }
            if (i == -1 && byteArray.length == iCurveOrderSize) {
                return byteArray;
            }
            if (i == -1) {
                return ArraysKt.copyInto$default(byteArray, new byte[iCurveOrderSize], iCurveOrderSize - byteArray.length, 0, 0, 12, (Object) null);
            }
            return ArraysKt.copyInto$default(byteArray, new byte[iCurveOrderSize], (iCurveOrderSize - byteArray.length) + i, i, 0, 8, (Object) null);
        }
    }
}

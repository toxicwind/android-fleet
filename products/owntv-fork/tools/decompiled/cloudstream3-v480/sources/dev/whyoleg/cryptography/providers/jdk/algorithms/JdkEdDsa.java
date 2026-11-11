package dev.whyoleg.cryptography.providers.jdk.algorithms;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.EdDSA;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.internal.BouncyCastleBridge;
import dev.whyoleg.cryptography.providers.jdk.internal.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureVerifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.Ed25519AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.Ed448AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEdDsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0006\u0016\u0017\u0018\u0019\u001a\u001bB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004J\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\u00020\u0007*\u00020\b8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;", "Ldev/whyoleg/cryptography/algorithms/EdDSA;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "identifier", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "getIdentifier", "(Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "curve", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$KeyPair;", "PublicKeyDecoder", "PrivateKeyDecoder", "KeyPairGenerator", "EdDsaKeyPair", "EdDsaPublicKey", "EdDsaPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkEdDsa implements EdDSA {
    private final JdkCryptographyState state;

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EdDSA.Curve.values().length];
            try {
                iArr[EdDSA.Curve.Ed25519.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EdDSA.Curve.Ed448.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // dev.whyoleg.cryptography.algorithms.EdDSA, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return EdDSA.INSTANCE;
    }

    public JdkEdDsa(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlgorithmIdentifier getIdentifier(EdDSA.Curve curve) {
        int i = WhenMappings.$EnumSwitchMapping$0[curve.ordinal()];
        if (i == 1) {
            return Ed25519AlgorithmIdentifier.INSTANCE;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return Ed448AlgorithmIdentifier.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.algorithms.EdDSA
    public Decoder<EdDSA.PublicKey.Format, EdDSA.PublicKey> publicKeyDecoder(EdDSA.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new PublicKeyDecoder(this, curve);
    }

    @Override // dev.whyoleg.cryptography.algorithms.EdDSA
    public Decoder<EdDSA.PrivateKey.Format, EdDSA.PrivateKey> privateKeyDecoder(EdDSA.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new PrivateKeyDecoder(this, curve);
    }

    @Override // dev.whyoleg.cryptography.algorithms.EdDSA
    public KeyGenerator<EdDSA.KeyPair> keyPairGenerator(EdDSA.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new KeyPairGenerator(this, curve);
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\u00032\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0086\u0080\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u0003*\u00060\u0010j\u0002`\u0011H\u0094\u0080\u0004J\u001a\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$PublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)V", "fromPrivateKey", "privateKey", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "fromEncodedPrivateKey", "bytes", "", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "decodeFromByteArrayBlocking", "format", "decodeFromRaw", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class PublicKeyDecoder extends JdkPublicKeyDecoder<EdDSA.PublicKey.Format, EdDSA.PublicKey> {
        private final EdDSA.Curve curve;
        final /* synthetic */ JdkEdDsa this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PublicKeyDecoder(JdkEdDsa jdkEdDsa, EdDSA.Curve curve) {
            super(jdkEdDsa.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEdDsa;
            this.curve = curve;
        }

        public final EdDSA.PublicKey fromPrivateKey(PrivateKey privateKey) {
            EdDSA.PublicKey publicKeyDecodeFromRaw;
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            Pair<PublicKey, byte[]> pairDeriveEdDsaPublicKey = BouncyCastleBridge.INSTANCE.deriveEdDsaPublicKey(privateKey, this.curve);
            if (pairDeriveEdDsaPublicKey != null) {
                PublicKey publicKeyComponent1 = pairDeriveEdDsaPublicKey.component1();
                byte[] bArrComponent2 = pairDeriveEdDsaPublicKey.component2();
                if (publicKeyComponent1 == null || (publicKeyDecodeFromRaw = convert(publicKeyComponent1)) == null) {
                    publicKeyDecodeFromRaw = bArrComponent2 != null ? decodeFromRaw(bArrComponent2) : null;
                }
                if (publicKeyDecodeFromRaw != null) {
                    return publicKeyDecodeFromRaw;
                }
            }
            throw new IllegalStateException("Getting public key from private key for EdDSA is not supported in JDK without BouncyCastle APIs".toString());
        }

        public final EdDSA.PublicKey fromEncodedPrivateKey(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            byte[] bArrM8754getPublicKeyFromPrivateKeyPkcs84RESAxk = KeysKt.m8754getPublicKeyFromPrivateKeyPkcs84RESAxk(this.this$0.getIdentifier(this.curve).getAlgorithm(), bytes);
            if (bArrM8754getPublicKeyFromPrivateKeyPkcs84RESAxk != null) {
                return decodeFromRaw(bArrM8754getPublicKeyFromPrivateKeyPkcs84RESAxk);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder
        public EdDSA.PublicKey convert(PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            return new EdDsaPublicKey(this.this$0, getState(), publicKey, this.curve);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public EdDSA.PublicKey decodeFromByteArrayBlocking(EdDSA.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.JWK.INSTANCE)) {
                return decodeFromRaw(JsonWebKeys.INSTANCE.decodeOkpPublicKey(this.curve.name(), bytes));
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.RAW.INSTANCE)) {
                return decodeFromRaw(bytes);
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final EdDSA.PublicKey decodeFromRaw(byte[] bytes) {
            return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapSubjectPublicKeyInfo(this.this$0.getIdentifier(this.curve), bytes));
        }
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u0003*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0094\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$PrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)V", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "decodeFromDer", TvContractCompat.PARAM_INPUT, "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class PrivateKeyDecoder extends JdkPrivateKeyDecoder<EdDSA.PrivateKey.Format, EdDSA.PrivateKey> {
        private final EdDSA.Curve curve;
        final /* synthetic */ JdkEdDsa this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PrivateKeyDecoder(JdkEdDsa jdkEdDsa, EdDSA.Curve curve) {
            super(jdkEdDsa.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEdDsa;
            this.curve = curve;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public EdDSA.PrivateKey convert(PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            return new EdDsaPrivateKey(this.this$0, getState(), privateKey, null, this.curve);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public EdDSA.PrivateKey decodeFromByteArrayBlocking(EdDSA.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.JWK.INSTANCE)) {
                JsonWebKeys.OkpPrivateKeyComponents okpPrivateKeyComponentsDecodeOkpPrivateKey = JsonWebKeys.INSTANCE.decodeOkpPrivateKey(this.curve.name(), bytes);
                return new EdDsaPrivateKey(this.this$0, getState(), decodeFromDerRaw(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapCurvePrivateKeyInfo(0, this.this$0.getIdentifier(this.curve), okpPrivateKeyComponentsDecodeOkpPrivateKey.getPrivateKey())), new PublicKeyDecoder(this.this$0, this.curve).decodeFromByteArrayBlocking((EdDSA.PublicKey.Format) EdDSA.PublicKey.Format.RAW.INSTANCE, okpPrivateKeyComponentsDecodeOkpPrivateKey.getPublicKey()), this.curve);
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.RAW.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapCurvePrivateKeyInfo(0, this.this$0.getIdentifier(this.curve), bytes));
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public EdDSA.PrivateKey decodeFromDer(byte[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            return new EdDsaPrivateKey(this.this$0, getState(), decodeFromDerRaw(input), new PublicKeyDecoder(this.this$0, this.curve).fromEncodedPrivateKey(input), this.curve);
        }
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u0002*\u00060\fj\u0002`\rH\u0094\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$KeyPair;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)V", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class KeyPairGenerator extends JdkKeyPairGenerator<EdDSA.KeyPair> {
        private final EdDSA.Curve curve;
        final /* synthetic */ JdkEdDsa this$0;

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        protected void init(java.security.KeyPairGenerator keyPairGenerator) {
            Intrinsics.checkNotNullParameter(keyPairGenerator, "<this>");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KeyPairGenerator(JdkEdDsa jdkEdDsa, EdDSA.Curve curve) {
            super(jdkEdDsa.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEdDsa;
            this.curve = curve;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        public EdDSA.KeyPair convert(KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            JdkEdDsa jdkEdDsa = this.this$0;
            JdkCryptographyState state = getState();
            PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNullExpressionValue(publicKey, "getPublic(...)");
            EdDsaPublicKey edDsaPublicKey = new EdDsaPublicKey(jdkEdDsa, state, publicKey, this.curve);
            JdkEdDsa jdkEdDsa2 = this.this$0;
            JdkCryptographyState state2 = getState();
            PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
            return new EdDsaKeyPair(edDsaPublicKey, new EdDsaPrivateKey(jdkEdDsa2, state2, privateKey, edDsaPublicKey, this.curve));
        }
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$EdDsaKeyPair;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class EdDsaKeyPair implements EdDSA.KeyPair {
        private final EdDSA.PrivateKey privateKey;
        private final EdDSA.PublicKey publicKey;

        public EdDsaKeyPair(EdDSA.PublicKey publicKey, EdDSA.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EdDSA.KeyPair
        public EdDSA.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EdDSA.KeyPair
        public EdDSA.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B%\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0096\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0010H\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$EdDsaPublicKey;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)V", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "encodeToByteArrayBlocking", "", "format", "encodeToRaw", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EdDsaPublicKey extends JdkEncodableKey<EdDSA.PublicKey.Format> implements EdDSA.PublicKey {
        private final EdDSA.Curve curve;
        private final PublicKey key;
        private final JdkCryptographyState state;
        final /* synthetic */ JdkEdDsa this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EdDsaPublicKey(JdkEdDsa jdkEdDsa, JdkCryptographyState state, PublicKey key, EdDSA.Curve curve) {
            super(key);
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEdDsa;
            this.state = state;
            this.key = key;
            this.curve = curve;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EdDSA.PublicKey
        public SignatureVerifier signatureVerifier() {
            return new JdkSignatureVerifier(this.state, this.key, "EdDSA", null);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(EdDSA.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.JWK.INSTANCE)) {
                return JsonWebKeys.INSTANCE.encodeOkpPublicKey(this.curve.name(), encodeToRaw());
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.RAW.INSTANCE)) {
                return encodeToRaw();
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, EdDSA.PublicKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }

        private final byte[] encodeToRaw() {
            return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8746unwrapSubjectPublicKeyInfo4RESAxk(this.this$0.getIdentifier(this.curve).getAlgorithm(), encodeToDer());
        }
    }

    /* JADX INFO: compiled from: JdkEdDsa.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B/\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\nH\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa$EdDsaPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEdDsa;Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;)V", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "getPublicKeyBlocking", "encodeToByteArrayBlocking", "", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class EdDsaPrivateKey extends JdkEncodableKey<EdDSA.PrivateKey.Format> implements EdDSA.PrivateKey {
        private final EdDSA.Curve curve;
        private final PrivateKey key;
        private EdDSA.PublicKey publicKey;
        private final JdkCryptographyState state;
        final /* synthetic */ JdkEdDsa this$0;

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation<? super EdDSA.PublicKey> continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EdDsaPrivateKey(JdkEdDsa jdkEdDsa, JdkCryptographyState state, PrivateKey key, EdDSA.PublicKey publicKey, EdDSA.Curve curve) {
            super(key);
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkEdDsa;
            this.state = state;
            this.key = key;
            this.publicKey = publicKey;
            this.curve = curve;
        }

        @Override // dev.whyoleg.cryptography.algorithms.EdDSA.PrivateKey
        public SignatureGenerator signatureGenerator() {
            return new JdkSignatureGenerator(this.state, this.key, "EdDSA", null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public EdDSA.PublicKey getPublicKeyBlocking() {
            if (this.publicKey == null) {
                this.publicKey = new PublicKeyDecoder(this.this$0, this.curve).fromPrivateKey(this.key);
            }
            EdDSA.PublicKey publicKey = this.publicKey;
            Intrinsics.checkNotNull(publicKey);
            return publicKey;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(EdDSA.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.JWK.INSTANCE)) {
                return JsonWebKeys.INSTANCE.encodeOkpPrivateKey(this.curve.name(), getPublicKeyBlocking().encodeToByteArrayBlocking(EdDSA.PublicKey.Format.RAW.INSTANCE), encodeToByteArrayBlocking((EdDSA.PrivateKey.Format) EdDSA.PrivateKey.Format.RAW.INSTANCE));
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.RAW.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8743unwrapCurvePrivateKeyInfo4RESAxk(this.this$0.getIdentifier(this.curve).getAlgorithm(), encodeToDer());
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, EdDSA.PrivateKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}

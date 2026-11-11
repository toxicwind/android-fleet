package dev.whyoleg.cryptography.providers.jdk.algorithms;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.XDH;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SharedSecretGenerator;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.internal.BouncyCastleBridge;
import dev.whyoleg.cryptography.providers.jdk.internal.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkKeyAgreementKt;
import dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.X25519AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.X448AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyAgreement;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: JdkXdh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0006\u0016\u0017\u0018\u0019\u001a\u001bB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004J\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u000f\u001a\u00020\bH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\u00020\u0007*\u00020\b8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;", "Ldev/whyoleg/cryptography/algorithms/XDH;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "identifier", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "getIdentifier", "(Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "curve", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/XDH$KeyPair;", "PublicKeyDecoder", "PrivateKeyDecoder", "KeyPairGenerator", "XdhKeyPair", "XdhPublicKey", "XdhPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkXdh implements XDH {
    private final JdkCryptographyState state;

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[XDH.Curve.values().length];
            try {
                iArr[XDH.Curve.X25519.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[XDH.Curve.X448.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // dev.whyoleg.cryptography.algorithms.XDH, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return XDH.INSTANCE;
    }

    public JdkXdh(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlgorithmIdentifier getIdentifier(XDH.Curve curve) {
        int i = WhenMappings.$EnumSwitchMapping$0[curve.ordinal()];
        if (i == 1) {
            return X25519AlgorithmIdentifier.INSTANCE;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return X448AlgorithmIdentifier.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.algorithms.XDH
    public Decoder<XDH.PublicKey.Format, XDH.PublicKey> publicKeyDecoder(XDH.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new PublicKeyDecoder(this, curve);
    }

    @Override // dev.whyoleg.cryptography.algorithms.XDH
    public Decoder<XDH.PrivateKey.Format, XDH.PrivateKey> privateKeyDecoder(XDH.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new PrivateKeyDecoder(this, curve);
    }

    @Override // dev.whyoleg.cryptography.algorithms.XDH
    public KeyGenerator<XDH.KeyPair> keyPairGenerator(XDH.Curve curve) {
        Intrinsics.checkNotNullParameter(curve, "curve");
        return new KeyPairGenerator(this, curve);
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\u00032\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0086\u0080\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u0003*\u00060\u0010j\u0002`\u0011H\u0094\u0080\u0004J\u001a\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$PublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)V", "fromPrivateKey", "privateKey", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "fromEncodedPrivateKey", "bytes", "", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "decodeFromByteArrayBlocking", "format", "decodeFromRaw", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class PublicKeyDecoder extends JdkPublicKeyDecoder<XDH.PublicKey.Format, XDH.PublicKey> {
        private final XDH.Curve curve;
        final /* synthetic */ JdkXdh this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PublicKeyDecoder(JdkXdh jdkXdh, XDH.Curve curve) {
            super(jdkXdh.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkXdh;
            this.curve = curve;
        }

        public final XDH.PublicKey fromPrivateKey(PrivateKey privateKey) {
            XDH.PublicKey publicKeyDecodeFromRaw;
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            Pair<PublicKey, byte[]> pairDeriveXdhPublicKey = BouncyCastleBridge.INSTANCE.deriveXdhPublicKey(privateKey, this.curve);
            if (pairDeriveXdhPublicKey != null) {
                PublicKey publicKeyComponent1 = pairDeriveXdhPublicKey.component1();
                byte[] bArrComponent2 = pairDeriveXdhPublicKey.component2();
                if (publicKeyComponent1 == null || (publicKeyDecodeFromRaw = convert(publicKeyComponent1)) == null) {
                    publicKeyDecodeFromRaw = bArrComponent2 != null ? decodeFromRaw(bArrComponent2) : null;
                }
                if (publicKeyDecodeFromRaw != null) {
                    return publicKeyDecodeFromRaw;
                }
            }
            throw new IllegalStateException("Getting public key from private key for XDH is not supported in JDK without BouncyCastle APIs".toString());
        }

        public final XDH.PublicKey fromEncodedPrivateKey(byte[] bytes) {
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
        public XDH.PublicKey convert(PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            return new XdhPublicKey(this.this$0, getState(), publicKey, this.curve);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public XDH.PublicKey decodeFromByteArrayBlocking(XDH.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.JWK.INSTANCE)) {
                return decodeFromRaw(JsonWebKeys.INSTANCE.decodeOkpPublicKey(this.curve.name(), bytes));
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.RAW.INSTANCE)) {
                return decodeFromRaw(bytes);
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final XDH.PublicKey decodeFromRaw(byte[] bytes) {
            return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapSubjectPublicKeyInfo(this.this$0.getIdentifier(this.curve), bytes));
        }
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u0003*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0094\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$PrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)V", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "decodeFromDer", TvContractCompat.PARAM_INPUT, "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class PrivateKeyDecoder extends JdkPrivateKeyDecoder<XDH.PrivateKey.Format, XDH.PrivateKey> {
        private final XDH.Curve curve;
        final /* synthetic */ JdkXdh this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PrivateKeyDecoder(JdkXdh jdkXdh, XDH.Curve curve) {
            super(jdkXdh.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkXdh;
            this.curve = curve;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public XDH.PrivateKey convert(PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            return new XdhPrivateKey(this.this$0, getState(), privateKey, null, this.curve);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public XDH.PrivateKey decodeFromByteArrayBlocking(XDH.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.JWK.INSTANCE)) {
                JsonWebKeys.OkpPrivateKeyComponents okpPrivateKeyComponentsDecodeOkpPrivateKey = JsonWebKeys.INSTANCE.decodeOkpPrivateKey(this.curve.name(), bytes);
                return new XdhPrivateKey(this.this$0, getState(), decodeFromDerRaw(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapCurvePrivateKeyInfo(0, this.this$0.getIdentifier(this.curve), okpPrivateKeyComponentsDecodeOkpPrivateKey.getPrivateKey())), new PublicKeyDecoder(this.this$0, this.curve).decodeFromByteArrayBlocking((XDH.PublicKey.Format) XDH.PublicKey.Format.RAW.INSTANCE, okpPrivateKeyComponentsDecodeOkpPrivateKey.getPublicKey()), this.curve);
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.RAW.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.wrapCurvePrivateKeyInfo(0, this.this$0.getIdentifier(this.curve), bytes));
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public XDH.PrivateKey decodeFromDer(byte[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            return new XdhPrivateKey(this.this$0, getState(), decodeFromDerRaw(input), new PublicKeyDecoder(this.this$0, this.curve).fromEncodedPrivateKey(input), this.curve);
        }
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u0002*\u00060\fj\u0002`\rH\u0094\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "Ldev/whyoleg/cryptography/algorithms/XDH$KeyPair;", "curve", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)V", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class KeyPairGenerator extends JdkKeyPairGenerator<XDH.KeyPair> {
        private final XDH.Curve curve;
        final /* synthetic */ JdkXdh this$0;

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        protected void init(java.security.KeyPairGenerator keyPairGenerator) {
            Intrinsics.checkNotNullParameter(keyPairGenerator, "<this>");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KeyPairGenerator(JdkXdh jdkXdh, XDH.Curve curve) {
            super(jdkXdh.state, curve.name());
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkXdh;
            this.curve = curve;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        public XDH.KeyPair convert(KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            JdkXdh jdkXdh = this.this$0;
            JdkCryptographyState state = getState();
            PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNullExpressionValue(publicKey, "getPublic(...)");
            XdhPublicKey xdhPublicKey = new XdhPublicKey(jdkXdh, state, publicKey, this.curve);
            JdkXdh jdkXdh2 = this.this$0;
            JdkCryptographyState state2 = getState();
            PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
            return new XdhKeyPair(xdhPublicKey, new XdhPrivateKey(jdkXdh2, state2, privateKey, xdhPublicKey, this.curve));
        }
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$XdhKeyPair;", "Ldev/whyoleg/cryptography/algorithms/XDH$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class XdhKeyPair implements XDH.KeyPair {
        private final XDH.PrivateKey privateKey;
        private final XDH.PublicKey publicKey;

        public XdhKeyPair(XDH.PublicKey publicKey, XDH.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.XDH.KeyPair
        public XDH.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.XDH.KeyPair
        public XDH.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00050\u0004B%\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0003H\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u0017H\u0082\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\u00060\tj\u0002`\nX\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0013j\u0002`\u00140\u0012X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$XdhPublicKey;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey$Format;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)V", "getKey", "()Ljava/security/PublicKey;", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "sharedSecretGenerator", "generateSharedSecretToByteArrayBlocking", "", "other", "encodeToByteArrayBlocking", "format", "encodeToRaw", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class XdhPublicKey extends JdkEncodableKey<XDH.PublicKey.Format> implements XDH.PublicKey, SharedSecretGenerator<XDH.PrivateKey> {
        private final XDH.Curve curve;
        private final PublicKey key;
        private final Pooled<KeyAgreement> keyAgreement;
        private final JdkCryptographyState state;
        final /* synthetic */ JdkXdh this$0;

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(XDH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, privateKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(XDH.PrivateKey privateKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(privateKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(XDH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, privateKey, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public XdhPublicKey(JdkXdh jdkXdh, JdkCryptographyState state, PublicKey key, XDH.Curve curve) {
            super(key);
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkXdh;
            this.state = state;
            this.key = key;
            this.curve = curve;
            this.keyAgreement = state.keyAgreement("XDH");
        }

        public final PublicKey getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.algorithms.XDH.PublicKey
        public SharedSecretGenerator<XDH.PrivateKey> sharedSecretGenerator() {
            return this;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(XDH.PrivateKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (!(other instanceof XdhPrivateKey)) {
                throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
            }
            return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.state, ((XdhPrivateKey) other).getKey(), this.key);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(XDH.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.JWK.INSTANCE)) {
                return JsonWebKeys.INSTANCE.encodeOkpPublicKey(this.curve.name(), encodeToRaw());
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.RAW.INSTANCE)) {
                return encodeToRaw();
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, XDH.PublicKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }

        private final byte[] encodeToRaw() {
            return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8746unwrapSubjectPublicKeyInfo4RESAxk(this.this$0.getIdentifier(this.curve).getAlgorithm(), encodeToDer());
        }
    }

    /* JADX INFO: compiled from: JdkXdh.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00050\u0004B/\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0003H\u0096\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\u00060\tj\u0002`\nX\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0014j\u0002`\u00150\u0013X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh$XdhPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/XDH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "publicKey", "curve", "Ldev/whyoleg/cryptography/algorithms/XDH$Curve;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkXdh;Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/algorithms/XDH$PublicKey;Ldev/whyoleg/cryptography/algorithms/XDH$Curve;)V", "getKey", "()Ljava/security/PrivateKey;", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "sharedSecretGenerator", "getPublicKeyBlocking", "generateSharedSecretToByteArrayBlocking", "", "other", "encodeToByteArrayBlocking", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class XdhPrivateKey extends JdkEncodableKey<XDH.PrivateKey.Format> implements XDH.PrivateKey, SharedSecretGenerator<XDH.PublicKey> {
        private final XDH.Curve curve;
        private final PrivateKey key;
        private final Pooled<KeyAgreement> keyAgreement;
        private XDH.PublicKey publicKey;
        private final JdkCryptographyState state;
        final /* synthetic */ JdkXdh this$0;

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(XDH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, publicKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(XDH.PublicKey publicKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(publicKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(XDH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, publicKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation<? super XDH.PublicKey> continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public XdhPrivateKey(JdkXdh jdkXdh, JdkCryptographyState state, PrivateKey key, XDH.PublicKey publicKey, XDH.Curve curve) {
            super(key);
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(curve, "curve");
            this.this$0 = jdkXdh;
            this.state = state;
            this.key = key;
            this.publicKey = publicKey;
            this.curve = curve;
            this.keyAgreement = state.keyAgreement("XDH");
        }

        public final PrivateKey getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.algorithms.XDH.PrivateKey
        public SharedSecretGenerator<XDH.PublicKey> sharedSecretGenerator() {
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public XDH.PublicKey getPublicKeyBlocking() {
            if (this.publicKey == null) {
                this.publicKey = new PublicKeyDecoder(this.this$0, this.curve).fromPrivateKey(this.key);
            }
            XDH.PublicKey publicKey = this.publicKey;
            Intrinsics.checkNotNull(publicKey);
            return publicKey;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(XDH.PublicKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (!(other instanceof XdhPublicKey)) {
                throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
            }
            return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.state, this.key, ((XdhPublicKey) other).getKey());
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(XDH.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.JWK.INSTANCE)) {
                return JsonWebKeys.INSTANCE.encodeOkpPrivateKey(this.curve.name(), getPublicKeyBlocking().encodeToByteArrayBlocking(XDH.PublicKey.Format.RAW.INSTANCE), encodeToByteArrayBlocking((XDH.PrivateKey.Format) XDH.PrivateKey.Format.RAW.INSTANCE));
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.RAW.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8743unwrapCurvePrivateKeyInfo4RESAxk(this.this$0.getIdentifier(this.curve).getAlgorithm(), encodeToDer());
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, XDH.PrivateKey.Format.PEM.INSTANCE)) {
                return dev.whyoleg.cryptography.providers.base.materials.KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}

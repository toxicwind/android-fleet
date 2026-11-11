package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.DH;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.ParametersGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SharedSecretGenerator;
import dev.whyoleg.cryptography.providers.base.materials.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkKeyAgreementKt;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHGenParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: JdkDh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001:\t\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0096\u0080\u0004J\u0016\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0096\u0080\u0004J\u0016\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0007H\u0096\u0080\u0004J)\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;", "Ldev/whyoleg/cryptography/algorithms/DH;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "parametersDecoder", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "parametersGenerator", "Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "primeSize", "Ldev/whyoleg/cryptography/BinarySize;", "privateValueSize", "parametersGenerator-EFf-y7U", "(ILdev/whyoleg/cryptography/BinarySize;)Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "DhKeyPairGenerator", "DhPublicKeyDecoder", "DhPrivateKeyDecoder", "DhKeyPair", "DhPublicKey", "DhPrivateKey", "DhParametersDecoder", "DhParametersGenerator", "JdkDhParameters", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkDh implements DH {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.algorithms.DH, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return DH.INSTANCE;
    }

    public JdkDh(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.algorithms.DH
    public Decoder<DH.PublicKey.Format, DH.PublicKey> publicKeyDecoder() {
        return new DhPublicKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DH
    public Decoder<DH.PrivateKey.Format, DH.PrivateKey> privateKeyDecoder() {
        return new DhPrivateKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DH
    public Decoder<DH.Parameters.Format, DH.Parameters> parametersDecoder() {
        return new DhParametersDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DH
    /* JADX INFO: renamed from: parametersGenerator-EFf-y7U */
    public ParametersGenerator<DH.Parameters> mo8683parametersGeneratorEFfy7U(int primeSize, BinarySize privateValueSize) {
        return new DhParametersGenerator(this, primeSize, privateValueSize, null);
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u0002*\u00060\fj\u0002`\rH\u0094\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhKeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$KeyPair;", "parameters", "Ljavax/crypto/spec/DHParameterSpec;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;Ljavax/crypto/spec/DHParameterSpec;)V", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhKeyPairGenerator extends JdkKeyPairGenerator<DH.KeyPair> {
        private final DHParameterSpec parameters;
        final /* synthetic */ JdkDh this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DhKeyPairGenerator(JdkDh jdkDh, DHParameterSpec parameters) {
            super(jdkDh.state, "DH");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            this.this$0 = jdkDh;
            this.parameters = parameters;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        protected void init(KeyPairGenerator keyPairGenerator) throws InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(keyPairGenerator, "<this>");
            keyPairGenerator.initialize(this.parameters, getState().getSecureRandom());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        public DH.KeyPair convert(KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            JdkDh jdkDh = this.this$0;
            PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type javax.crypto.interfaces.DHPublicKey");
            DhPublicKey dhPublicKey = new DhPublicKey(jdkDh, (DHPublicKey) publicKey);
            JdkDh jdkDh2 = this.this$0;
            PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type javax.crypto.interfaces.DHPrivateKey");
            return new DhKeyPair(dhPublicKey, new DhPrivateKey(jdkDh2, (DHPrivateKey) privateKey, dhPublicKey));
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0080\u0004J\u0012\u0010\t\u001a\u00020\u0003*\u00060\nj\u0002`\u000bH\u0094\u0080\u0004J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhPublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;)V", "fromPrivateKey", "privateKey", "Ljavax/crypto/interfaces/DHPrivateKey;", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhPublicKeyDecoder extends JdkPublicKeyDecoder<DH.PublicKey.Format, DH.PublicKey> {
        public DhPublicKeyDecoder() {
            super(JdkDh.this.state, "DH");
        }

        public final DH.PublicKey fromPrivateKey(DHPrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            DHParameterSpec params = privateKey.getParams();
            return decode(new DHPublicKeySpec(params.getG().modPow(privateKey.getX(), params.getP()), params.getP(), params.getG()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder
        public DH.PublicKey convert(PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            if (!(publicKey instanceof DHPublicKey)) {
                throw new IllegalStateException("Check failed.");
            }
            return new DhPublicKey(JdkDh.this, (DHPublicKey) publicKey);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DH.PublicKey decodeFromByteArrayBlocking(DH.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, DH.PublicKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, DH.PublicKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u0003*\u00060\u0007j\u0002`\bH\u0094\u0080\u0004J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;)V", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhPrivateKeyDecoder extends JdkPrivateKeyDecoder<DH.PrivateKey.Format, DH.PrivateKey> {
        public DhPrivateKeyDecoder() {
            super(JdkDh.this.state, "DH");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public DH.PrivateKey convert(PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            if (!(privateKey instanceof DHPrivateKey)) {
                throw new IllegalStateException("Check failed.");
            }
            return new DhPrivateKey(JdkDh.this, (DHPrivateKey) privateKey, null);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DH.PrivateKey decodeFromByteArrayBlocking(DH.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, DH.PrivateKey.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, DH.PrivateKey.Format.PEM.INSTANCE)) {
                return decodeFromDer(KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhKeyPair;", "Ldev/whyoleg/cryptography/algorithms/DH$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class DhKeyPair implements DH.KeyPair {
        private final DH.PrivateKey privateKey;
        private final DH.PublicKey publicKey;

        public DhKeyPair(DH.PublicKey publicKey, DH.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DH.KeyPair
        public DH.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DH.KeyPair
        public DH.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0011\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096\u0080\u0004J\u0012\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0003H\u0096\u0080\u0004R\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\f\u0012\b\u0012\u00060\u000ej\u0002`\u000f0\rX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhPublicKey;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "key", "Ljavax/crypto/interfaces/DHPublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;Ljavax/crypto/interfaces/DHPublicKey;)V", "getKey", "()Ljavax/crypto/interfaces/DHPublicKey;", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "sharedSecretGenerator", "generateSharedSecretToByteArrayBlocking", "", "other", "encodeToByteArrayBlocking", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhPublicKey extends JdkEncodableKey<DH.PublicKey.Format> implements DH.PublicKey, SharedSecretGenerator<DH.PrivateKey> {
        private final DHPublicKey key;
        private final Pooled<KeyAgreement> keyAgreement;
        final /* synthetic */ JdkDh this$0;

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(DH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, privateKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(DH.PrivateKey privateKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(privateKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(DH.PrivateKey privateKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, privateKey, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DhPublicKey(JdkDh jdkDh, DHPublicKey key) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = jdkDh;
            this.key = key;
            this.keyAgreement = jdkDh.state.keyAgreement("DH");
        }

        public final DHPublicKey getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DH.PublicKey
        public SharedSecretGenerator<DH.PrivateKey> sharedSecretGenerator() {
            return this;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(DH.PrivateKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (other instanceof DhPrivateKey) {
                return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.this$0.state, ((DhPrivateKey) other).getKey(), this.key);
            }
            throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DH.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DH.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, DH.PublicKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00050\u0004B\u001b\bF\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0011\u001a\u00020\u0005H\u0096\u0080\u0004J\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004R\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "key", "Ljavax/crypto/interfaces/DHPrivateKey;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;Ljavax/crypto/interfaces/DHPrivateKey;Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;)V", "getKey", "()Ljavax/crypto/interfaces/DHPrivateKey;", "keyAgreement", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "getPublicKeyBlocking", "sharedSecretGenerator", "generateSharedSecretToByteArrayBlocking", "", "other", "encodeToByteArrayBlocking", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhPrivateKey extends JdkEncodableKey<DH.PrivateKey.Format> implements DH.PrivateKey, SharedSecretGenerator<DH.PublicKey> {
        private final DHPrivateKey key;
        private final Pooled<KeyAgreement> keyAgreement;
        private DH.PublicKey publicKey;
        final /* synthetic */ JdkDh this$0;

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecret(DH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecret$suspendImpl(this, publicKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ ByteString generateSharedSecretBlocking(DH.PublicKey publicKey) {
            return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(generateSharedSecretToByteArrayBlocking(publicKey));
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public /* synthetic */ Object generateSharedSecretToByteArray(DH.PublicKey publicKey, Continuation continuation) {
            return SharedSecretGenerator.CC.generateSharedSecretToByteArray$suspendImpl(this, publicKey, continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation<? super DH.PublicKey> continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DhPrivateKey(JdkDh jdkDh, DHPrivateKey key, DH.PublicKey publicKey) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = jdkDh;
            this.key = key;
            this.publicKey = publicKey;
            this.keyAgreement = jdkDh.state.keyAgreement("DH");
        }

        public final DHPrivateKey getKey() {
            return this.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public DH.PublicKey getPublicKeyBlocking() {
            if (this.publicKey == null) {
                this.publicKey = this.this$0.new DhPublicKeyDecoder().fromPrivateKey(this.key);
            }
            DH.PublicKey publicKey = this.publicKey;
            Intrinsics.checkNotNull(publicKey);
            return publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DH.PrivateKey
        public SharedSecretGenerator<DH.PublicKey> sharedSecretGenerator() {
            return this;
        }

        @Override // dev.whyoleg.cryptography.operations.SharedSecretGenerator
        public byte[] generateSharedSecretToByteArrayBlocking(DH.PublicKey other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (other instanceof DhPublicKey) {
                return JdkKeyAgreementKt.doAgreement(this.keyAgreement, this.this$0.state, this.key, ((DhPublicKey) other).getKey());
            }
            throw new IllegalStateException("Only key produced by JDK provider is supported".toString());
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DH.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DH.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, DH.PrivateKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0082\u0080\u0004¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhParametersDecoder;", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "decodeFromDer", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhParametersDecoder implements Decoder<DH.Parameters.Format, DH.Parameters> {
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteArray(EncodingFormat encodingFormat, byte[] bArr, Continuation<? super DH.Parameters> continuation) {
            return Decoder.CC.decodeFromByteArray$suspendImpl(this, encodingFormat, bArr, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteString(EncodingFormat encodingFormat, ByteString byteString, Continuation<? super DH.Parameters> continuation) {
            return Decoder.CC.decodeFromByteString$suspendImpl(this, encodingFormat, byteString, continuation);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [dev.whyoleg.cryptography.algorithms.DH$Parameters, java.lang.Object] */
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ DH.Parameters decodeFromByteStringBlocking(EncodingFormat encodingFormat, ByteString byteString) {
            return Decoder.CC.$default$decodeFromByteStringBlocking(this, encodingFormat, byteString);
        }

        public DhParametersDecoder() {
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DH.Parameters decodeFromByteArrayBlocking(DH.Parameters.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, DH.Parameters.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, DH.Parameters.Format.PEM.INSTANCE)) {
                return decodeFromDer(KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8810getDhParameters2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final DH.Parameters decodeFromDer(byte[] bytes) throws IOException {
            JdkDh jdkDh = JdkDh.this;
            AlgorithmParameters algorithmParameters = jdkDh.state.algorithmParameters("DH");
            algorithmParameters.init(bytes);
            return new JdkDhParameters(jdkDh, algorithmParameters);
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\t\u001a\u00020\n*\u00060\u000bj\u0002`\fH\u0094\u0080\u0004J\u0012\u0010\r\u001a\u00020\u0002*\u00060\u000ej\u0002`\u000fH\u0094\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\bR\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$DhParametersGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkParametersGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "primeSize", "Ldev/whyoleg/cryptography/BinarySize;", "privateValueSize", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;ILdev/whyoleg/cryptography/BinarySize;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "init", "", "Ljava/security/AlgorithmParameterGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameterGenerator;", "convert", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DhParametersGenerator extends JdkParametersGenerator<DH.Parameters> {
        private final int primeSize;
        private final BinarySize privateValueSize;

        public /* synthetic */ DhParametersGenerator(JdkDh jdkDh, int i, BinarySize binarySize, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, binarySize);
        }

        private DhParametersGenerator(int i, BinarySize binarySize) {
            super(JdkDh.this.state, "DH");
            this.primeSize = i;
            this.privateValueSize = binarySize;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator
        protected void init(AlgorithmParameterGenerator algorithmParameterGenerator) throws InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(algorithmParameterGenerator, "<this>");
            if (this.privateValueSize != null) {
                algorithmParameterGenerator.init(new DHGenParameterSpec(BinarySize.m8660getInBitsimpl(this.primeSize), BinarySize.m8660getInBitsimpl(this.privateValueSize.getBits())), getState().getSecureRandom());
            } else {
                algorithmParameterGenerator.init(BinarySize.m8660getInBitsimpl(this.primeSize), getState().getSecureRandom());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator
        public DH.Parameters convert(AlgorithmParameters algorithmParameters) {
            Intrinsics.checkNotNullParameter(algorithmParameters, "<this>");
            return new JdkDhParameters(JdkDh.this, algorithmParameters);
        }
    }

    /* JADX INFO: compiled from: JdkDh.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\bF\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh$JdkDhParameters;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "parameters", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDh;Ljava/security/AlgorithmParameters;)V", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$KeyPair;", "encodeToByteArrayBlocking", "", "format", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class JdkDhParameters implements DH.Parameters {
        private final AlgorithmParameters parameters;
        final /* synthetic */ JdkDh this$0;

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ Object encodeToByteArray(EncodingFormat encodingFormat, Continuation continuation) {
            return Encodable.CC.encodeToByteArray$suspendImpl(this, encodingFormat, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ Object encodeToByteString(EncodingFormat encodingFormat, Continuation continuation) {
            return Encodable.CC.encodeToByteString$suspendImpl(this, encodingFormat, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ ByteString encodeToByteStringBlocking(EncodingFormat encodingFormat) {
            return Encodable.CC.$default$encodeToByteStringBlocking(this, encodingFormat);
        }

        public JdkDhParameters(JdkDh jdkDh, AlgorithmParameters parameters) {
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            this.this$0 = jdkDh;
            this.parameters = parameters;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DH.Parameters
        public KeyGenerator<DH.KeyPair> keyPairGenerator() throws InvalidParameterSpecException {
            JdkDh jdkDh = this.this$0;
            AlgorithmParameterSpec parameterSpec = this.parameters.getParameterSpec(DHParameterSpec.class);
            Intrinsics.checkNotNullExpressionValue(parameterSpec, "getParameterSpec(...)");
            return new DhKeyPairGenerator(jdkDh, (DHParameterSpec) parameterSpec);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DH.Parameters.Format format) throws IOException {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DH.Parameters.Format.DER.INSTANCE)) {
                byte[] encoded = this.parameters.getEncoded();
                Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
                return encoded;
            }
            if (!Intrinsics.areEqual(format, DH.Parameters.Format.PEM.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            String strM8810getDhParameters2EFq_Wg = PemLabel.INSTANCE.m8810getDhParameters2EFq_Wg();
            byte[] encoded2 = this.parameters.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded2, "getEncoded(...)");
            return KeysKt.m8747wrapPemunSj4pc(strM8810getDhParameters2EFq_Wg, encoded2);
        }
    }
}

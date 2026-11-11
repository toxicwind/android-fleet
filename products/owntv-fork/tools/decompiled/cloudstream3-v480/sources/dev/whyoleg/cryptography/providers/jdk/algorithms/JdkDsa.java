package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.DSA;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.ParametersGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.providers.base.algorithms.DssRawSignatureGenerator;
import dev.whyoleg.cryptography.providers.base.algorithms.DssRawSignatureVerifier;
import dev.whyoleg.cryptography.providers.base.materials.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkSignatureVerifier;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import io.ktor.util.NonceKt$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidParameterSpecException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkDsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001:\t\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0096\u0080\u0004J\u0016\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0096\u0080\u0004J\u0016\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0007H\u0096\u0080\u0004J)\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;", "Ldev/whyoleg/cryptography/algorithms/DSA;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;", "parametersDecoder", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters;", "parametersGenerator", "Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "primeSize", "Ldev/whyoleg/cryptography/BinarySize;", "subprimeSize", "parametersGenerator-EFf-y7U", "(ILdev/whyoleg/cryptography/BinarySize;)Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "DsaPublicKeyDecoder", "DsaPrivateKeyDecoder", "DsaParametersDecoder", "DsaParametersGenerator", "JdkDsaParameters", "DsaKeyPairGenerator", "DsaKeyPair", "DsaPublicKey", "DsaPrivateKey", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkDsa implements DSA {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.algorithms.DSA, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return DSA.INSTANCE;
    }

    public JdkDsa(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.algorithms.DSA
    public Decoder<DSA.PublicKey.Format, DSA.PublicKey> publicKeyDecoder() {
        return new DsaPublicKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DSA
    public Decoder<DSA.PrivateKey.Format, DSA.PrivateKey> privateKeyDecoder() {
        return new DsaPrivateKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DSA
    public Decoder<DSA.Parameters.Format, DSA.Parameters> parametersDecoder() {
        return new DsaParametersDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.DSA
    /* JADX INFO: renamed from: parametersGenerator-EFf-y7U */
    public ParametersGenerator<DSA.Parameters> mo8685parametersGeneratorEFfy7U(int primeSize, BinarySize subprimeSize) {
        return new DsaParametersGenerator(this, primeSize, subprimeSize, null);
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0080\u0004J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\u0012\u0010\r\u001a\u00020\u0003*\u00060\u000ej\u0002`\u000fH\u0094\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaPublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;)V", "fromPrivateKey", "privateKey", "Ljava/security/interfaces/DSAPrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaPublicKeyDecoder extends JdkPublicKeyDecoder<DSA.PublicKey.Format, DSA.PublicKey> {
        public DsaPublicKeyDecoder() {
            super(JdkDsa.this.state, "DSA");
        }

        public final DSA.PublicKey fromPrivateKey(DSAPrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            DSAParams params = privateKey.getParams();
            return decode(new DSAPublicKeySpec(params.getG().modPow(privateKey.getX(), params.getP()), params.getP(), params.getQ(), params.getG()));
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DSA.PublicKey decodeFromByteArrayBlocking(DSA.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (!Intrinsics.areEqual(format, DSA.PublicKey.Format.DER.INSTANCE)) {
                if (!Intrinsics.areEqual(format, DSA.PublicKey.Format.PEM.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                bytes = KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes);
            }
            return decodeFromDer(bytes);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder
        public DSA.PublicKey convert(PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            return new DsaPublicKey(JdkDsa.this, publicKey);
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u0003*\u00060\u000bj\u0002`\fH\u0094\u0080\u0004¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaPrivateKeyDecoder extends JdkPrivateKeyDecoder<DSA.PrivateKey.Format, DSA.PrivateKey> {
        public DsaPrivateKeyDecoder() {
            super(JdkDsa.this.state, "DSA");
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DSA.PrivateKey decodeFromByteArrayBlocking(DSA.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (!Intrinsics.areEqual(format, DSA.PrivateKey.Format.DER.INSTANCE)) {
                if (!Intrinsics.areEqual(format, DSA.PrivateKey.Format.PEM.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                bytes = KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes);
            }
            return decodeFromDer(bytes);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public DSA.PrivateKey convert(PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            if (!(privateKey instanceof DSAPrivateKey)) {
                throw new IllegalStateException("Check failed.");
            }
            return new DsaPrivateKey(JdkDsa.this, (DSAPrivateKey) privateKey, null);
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0082\u0080\u0004¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaParametersDecoder;", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters$Format;", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "decodeFromDer", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaParametersDecoder implements Decoder<DSA.Parameters.Format, DSA.Parameters> {
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteArray(EncodingFormat encodingFormat, byte[] bArr, Continuation<? super DSA.Parameters> continuation) {
            return Decoder.CC.decodeFromByteArray$suspendImpl(this, encodingFormat, bArr, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteString(EncodingFormat encodingFormat, ByteString byteString, Continuation<? super DSA.Parameters> continuation) {
            return Decoder.CC.decodeFromByteString$suspendImpl(this, encodingFormat, byteString, continuation);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [dev.whyoleg.cryptography.algorithms.DSA$Parameters, java.lang.Object] */
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ DSA.Parameters decodeFromByteStringBlocking(EncodingFormat encodingFormat, ByteString byteString) {
            return Decoder.CC.$default$decodeFromByteStringBlocking(this, encodingFormat, byteString);
        }

        public DsaParametersDecoder() {
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public DSA.Parameters decodeFromByteArrayBlocking(DSA.Parameters.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, DSA.Parameters.Format.DER.INSTANCE)) {
                return decodeFromDer(bytes);
            }
            if (Intrinsics.areEqual(format, DSA.Parameters.Format.PEM.INSTANCE)) {
                return decodeFromDer(KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8811getDsaParameters2EFq_Wg(), bytes));
            }
            throw new NoWhenBranchMatchedException();
        }

        private final DSA.Parameters decodeFromDer(byte[] bytes) throws IOException {
            JdkDsa jdkDsa = JdkDsa.this;
            AlgorithmParameters algorithmParameters = jdkDsa.state.algorithmParameters("DSA");
            algorithmParameters.init(bytes);
            return new JdkDsaParameters(jdkDsa, algorithmParameters);
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\t\u001a\u00020\n*\u00060\u000bj\u0002`\fH\u0094\u0080\u0004J\u0012\u0010\r\u001a\u00020\u0002*\u00060\u000ej\u0002`\u000fH\u0094\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\bR\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaParametersGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkParametersGenerator;", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters;", "primeSize", "Ldev/whyoleg/cryptography/BinarySize;", "subprimeSize", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;ILdev/whyoleg/cryptography/BinarySize;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "init", "", "Ljava/security/AlgorithmParameterGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameterGenerator;", "convert", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaParametersGenerator extends JdkParametersGenerator<DSA.Parameters> {
        private final int primeSize;
        private final BinarySize subprimeSize;

        public /* synthetic */ DsaParametersGenerator(JdkDsa jdkDsa, int i, BinarySize binarySize, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, binarySize);
        }

        private DsaParametersGenerator(int i, BinarySize binarySize) {
            super(JdkDsa.this.state, "DSA");
            this.primeSize = i;
            this.subprimeSize = binarySize;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator
        protected void init(AlgorithmParameterGenerator algorithmParameterGenerator) throws InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(algorithmParameterGenerator, "<this>");
            if (this.subprimeSize != null) {
                NonceKt$$ExternalSyntheticApiModelOutline0.m8857m$1();
                algorithmParameterGenerator.init((AlgorithmParameterSpec) NonceKt$$ExternalSyntheticApiModelOutline0.m(BinarySize.m8660getInBitsimpl(this.primeSize), BinarySize.m8660getInBitsimpl(this.subprimeSize.getBits())), getState().getSecureRandom());
            } else {
                algorithmParameterGenerator.init(BinarySize.m8660getInBitsimpl(this.primeSize), getState().getSecureRandom());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkParametersGenerator
        public DSA.Parameters convert(AlgorithmParameters algorithmParameters) {
            Intrinsics.checkNotNullParameter(algorithmParameters, "<this>");
            return new JdkDsaParameters(JdkDsa.this, algorithmParameters);
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\bF\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$JdkDsaParameters;", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters;", "parameters", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;Ljava/security/AlgorithmParameters;)V", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/DSA$KeyPair;", "encodeToByteArrayBlocking", "", "format", "Ldev/whyoleg/cryptography/algorithms/DSA$Parameters$Format;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class JdkDsaParameters implements DSA.Parameters {
        private final AlgorithmParameters parameters;
        final /* synthetic */ JdkDsa this$0;

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

        public JdkDsaParameters(JdkDsa jdkDsa, AlgorithmParameters parameters) {
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            this.this$0 = jdkDsa;
            this.parameters = parameters;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DSA.Parameters
        public KeyGenerator<DSA.KeyPair> keyPairGenerator() throws InvalidParameterSpecException {
            JdkDsa jdkDsa = this.this$0;
            AlgorithmParameterSpec parameterSpec = this.parameters.getParameterSpec(DSAParameterSpec.class);
            Intrinsics.checkNotNullExpressionValue(parameterSpec, "getParameterSpec(...)");
            return new DsaKeyPairGenerator(jdkDsa, (DSAParameterSpec) parameterSpec);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DSA.Parameters.Format format) throws IOException {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DSA.Parameters.Format.DER.INSTANCE)) {
                byte[] encoded = this.parameters.getEncoded();
                Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
                return encoded;
            }
            if (!Intrinsics.areEqual(format, DSA.Parameters.Format.PEM.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            String strM8811getDsaParameters2EFq_Wg = PemLabel.INSTANCE.m8811getDsaParameters2EFq_Wg();
            byte[] encoded2 = this.parameters.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded2, "getEncoded(...)");
            return KeysKt.m8747wrapPemunSj4pc(strM8811getDsaParameters2EFq_Wg, encoded2);
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\nH\u0094\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\u0002*\u00060\fj\u0002`\rH\u0094\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaKeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "Ldev/whyoleg/cryptography/algorithms/DSA$KeyPair;", "parameters", "Ljava/security/spec/DSAParameterSpec;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;Ljava/security/spec/DSAParameterSpec;)V", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaKeyPairGenerator extends JdkKeyPairGenerator<DSA.KeyPair> {
        private final DSAParameterSpec parameters;
        final /* synthetic */ JdkDsa this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DsaKeyPairGenerator(JdkDsa jdkDsa, DSAParameterSpec parameters) {
            super(jdkDsa.state, "DSA");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            this.this$0 = jdkDsa;
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
        public DSA.KeyPair convert(KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            JdkDsa jdkDsa = this.this$0;
            PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.DSAPublicKey");
            DsaPublicKey dsaPublicKey = new DsaPublicKey(jdkDsa, (DSAPublicKey) publicKey);
            JdkDsa jdkDsa2 = this.this$0;
            PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.DSAPrivateKey");
            DsaPublicKey dsaPublicKey2 = dsaPublicKey;
            return new DsaKeyPair(dsaPublicKey2, new DsaPrivateKey(jdkDsa2, (DSAPrivateKey) privateKey, dsaPublicKey2));
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaKeyPair;", "Ldev/whyoleg/cryptography/algorithms/DSA$KeyPair;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;)V", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class DsaKeyPair implements DSA.KeyPair {
        private final DSA.PrivateKey privateKey;
        private final DSA.PublicKey publicKey;

        public DsaKeyPair(DSA.PublicKey publicKey, DSA.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DSA.KeyPair
        public DSA.PublicKey getPublicKey() {
            return this.publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DSA.KeyPair
        public DSA.PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0015\bF\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaPublicKey;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey$Format;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;Ljava/security/PublicKey;)V", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/DSA$SignatureFormat;", "encodeToByteArrayBlocking", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaPublicKey extends JdkEncodableKey<DSA.PublicKey.Format> implements DSA.PublicKey {
        private final PublicKey key;
        final /* synthetic */ JdkDsa this$0;

        /* JADX INFO: compiled from: JdkDsa.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DSA.SignatureFormat.values().length];
                try {
                    iArr[DSA.SignatureFormat.DER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DSA.SignatureFormat.RAW.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DsaPublicKey(JdkDsa jdkDsa, PublicKey key) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = jdkDsa;
            this.key = key;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DSA.PublicKey
        public SignatureVerifier signatureVerifier(CryptographyAlgorithmId<Digest> digest, DSA.SignatureFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            JdkSignatureVerifier jdkSignatureVerifier = new JdkSignatureVerifier(this.this$0.state, this.key, JdkDsaKt.dsaSignatureAlgorithmName(digest), null);
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i == 1) {
                return jdkSignatureVerifier;
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            PublicKey publicKey = this.key;
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.DSAPublicKey");
            BigInteger q = ((DSAPublicKey) publicKey).getParams().getQ();
            Intrinsics.checkNotNullExpressionValue(q, "getQ(...)");
            return new DssRawSignatureVerifier(jdkSignatureVerifier, JdkDsaKt.byteSize(q));
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DSA.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DSA.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, DSA.PublicKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: JdkDsa.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\n\u001a\u00020\u0007H\u0096\u0080\u0004J\"\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0003H\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa$DsaPrivateKey;", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/DSA$PrivateKey$Format;", "key", "Ljava/security/interfaces/DSAPrivateKey;", "publicKey", "Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkDsa;Ljava/security/interfaces/DSAPrivateKey;Ldev/whyoleg/cryptography/algorithms/DSA$PublicKey;)V", "getPublicKeyBlocking", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/DSA$SignatureFormat;", "encodeToByteArrayBlocking", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class DsaPrivateKey extends JdkEncodableKey<DSA.PrivateKey.Format> implements DSA.PrivateKey {
        private final DSAPrivateKey key;
        private DSA.PublicKey publicKey;
        final /* synthetic */ JdkDsa this$0;

        /* JADX INFO: compiled from: JdkDsa.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DSA.SignatureFormat.values().length];
                try {
                    iArr[DSA.SignatureFormat.DER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DSA.SignatureFormat.RAW.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation<? super DSA.PublicKey> continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DsaPrivateKey(JdkDsa jdkDsa, DSAPrivateKey key, DSA.PublicKey publicKey) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = jdkDsa;
            this.key = key;
            this.publicKey = publicKey;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public DSA.PublicKey getPublicKeyBlocking() {
            if (this.publicKey == null) {
                this.publicKey = this.this$0.new DsaPublicKeyDecoder().fromPrivateKey(this.key);
            }
            DSA.PublicKey publicKey = this.publicKey;
            Intrinsics.checkNotNull(publicKey);
            return publicKey;
        }

        @Override // dev.whyoleg.cryptography.algorithms.DSA.PrivateKey
        public SignatureGenerator signatureGenerator(CryptographyAlgorithmId<Digest> digest, DSA.SignatureFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            JdkSignatureGenerator jdkSignatureGenerator = new JdkSignatureGenerator(this.this$0.state, this.key, JdkDsaKt.dsaSignatureAlgorithmName(digest), null);
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i == 1) {
                return jdkSignatureGenerator;
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            BigInteger q = this.key.getParams().getQ();
            Intrinsics.checkNotNullExpressionValue(q, "getQ(...)");
            return new DssRawSignatureGenerator(jdkSignatureGenerator, JdkDsaKt.byteSize(q));
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public byte[] encodeToByteArrayBlocking(DSA.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, DSA.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, DSA.PrivateKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}

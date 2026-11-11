package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import dev.whyoleg.cryptography.algorithms.RSA.KeyPair;
import dev.whyoleg.cryptography.algorithms.RSA.PrivateKey;
import dev.whyoleg.cryptography.algorithms.RSA.PublicKey;
import dev.whyoleg.cryptography.algorithms.SHA1;
import dev.whyoleg.cryptography.algorithms.SHA224;
import dev.whyoleg.cryptography.algorithms.SHA256;
import dev.whyoleg.cryptography.algorithms.SHA384;
import dev.whyoleg.cryptography.algorithms.SHA3_224;
import dev.whyoleg.cryptography.algorithms.SHA3_256;
import dev.whyoleg.cryptography.algorithms.SHA3_384;
import dev.whyoleg.cryptography.algorithms.SHA3_512;
import dev.whyoleg.cryptography.algorithms.SHA512;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntKt;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import dev.whyoleg.cryptography.providers.base.materials.KeysKt;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkEncodableKey;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder;
import dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.RSAKt;
import dev.whyoleg.cryptography.serialization.asn1.modules.RsaAlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u0004*\u0014\b\u0002\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00050\u0007:\u0005./012B\u0011\bD\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0094\u0080\u0004J$\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00010\"2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0086\u0080\u0004J$\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00028\u00000\"2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0086\u0080\u0004J5\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00020'2\u0006\u0010(\u001a\u00020)2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010*\u001a\u00020+H\u0086\u0080\u0004¢\u0006\u0004\b,\u0010-R\u0015\u0010\b\u001a\u00020\tX\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR/\u0010\u000e\u001a\u001e\u0012\b\u0012\u00060\u0010j\u0002`\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0004\u0012\u00028\u00000\u000fX¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R7\u0010\u0016\u001a&\u0012\b\u0012\u00060\u0018j\u0002`\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u00010\u0017X¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR%\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000fX¤\u0084\b¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015\u0082\u0001\u00043456¨\u00067"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "KP", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "getState", "()Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "wrapPublicKey", "Lkotlin/Function2;", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "getWrapPublicKey", "()Lkotlin/jvm/functions/Function2;", "wrapPrivateKey", "Lkotlin/Function3;", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "getWrapPrivateKey", "()Lkotlin/jvm/functions/Function3;", "wrapKeyPair", "getWrapKeyPair", "hashAlgorithmName", "", "digest", "privateKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keySize", "Ldev/whyoleg/cryptography/BinarySize;", "publicExponent", "Ldev/whyoleg/cryptography/bigint/BigInt;", "keyPairGenerator-imL9hLU", "(ILdev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/bigint/BigInt;)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "RsaPublicKeyDecoder", "RsaPrivateKeyDecoder", "RsaKeyPairGenerator", "RsaPublicEncodableKey", "RsaPrivateEncodableKey", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss;", "Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkRsa<PublicK extends RSA.PublicKey, PrivateK extends RSA.PrivateKey<PublicK>, KP extends RSA.KeyPair<PublicK, PrivateK>> implements RSA<PublicK, PrivateK, KP> {
    private final JdkCryptographyState state;

    public /* synthetic */ JdkRsa(JdkCryptographyState jdkCryptographyState, DefaultConstructorMarker defaultConstructorMarker) {
        this(jdkCryptographyState);
    }

    protected abstract Function2<PublicK, PrivateK, KP> getWrapKeyPair();

    protected abstract Function3<java.security.PrivateKey, CryptographyAlgorithmId<Digest>, PublicK, PrivateK> getWrapPrivateKey();

    protected abstract Function2<java.security.PublicKey, CryptographyAlgorithmId<Digest>, PublicK> getWrapPublicKey();

    private JdkRsa(JdkCryptographyState jdkCryptographyState) {
        this.state = jdkCryptographyState;
    }

    protected final JdkCryptographyState getState() {
        return this.state;
    }

    protected String hashAlgorithmName(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        if (Intrinsics.areEqual(digest, SHA1.INSTANCE)) {
            return "SHA-1";
        }
        if (Intrinsics.areEqual(digest, SHA224.INSTANCE)) {
            return "SHA-224";
        }
        if (Intrinsics.areEqual(digest, SHA256.INSTANCE)) {
            return "SHA-256";
        }
        if (Intrinsics.areEqual(digest, SHA384.INSTANCE)) {
            return "SHA-384";
        }
        if (Intrinsics.areEqual(digest, SHA512.INSTANCE)) {
            return "SHA-512";
        }
        if (Intrinsics.areEqual(digest, SHA3_224.INSTANCE)) {
            return "SHA3-224";
        }
        if (Intrinsics.areEqual(digest, SHA3_256.INSTANCE)) {
            return "SHA3-256";
        }
        if (Intrinsics.areEqual(digest, SHA3_384.INSTANCE)) {
            return "SHA3-384";
        }
        if (Intrinsics.areEqual(digest, SHA3_512.INSTANCE)) {
            return "SHA3-512";
        }
        throw new IllegalStateException("Unsupported hash algorithm: " + digest);
    }

    @Override // dev.whyoleg.cryptography.algorithms.RSA
    public final Decoder<RSA.PrivateKey.Format, PrivateK> privateKeyDecoder(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new RsaPrivateKeyDecoder(this, digest);
    }

    @Override // dev.whyoleg.cryptography.algorithms.RSA
    public final Decoder<RSA.PublicKey.Format, PublicK> publicKeyDecoder(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new RsaPublicKeyDecoder(this, digest);
    }

    @Override // dev.whyoleg.cryptography.algorithms.RSA
    /* JADX INFO: renamed from: keyPairGenerator-imL9hLU */
    public final KeyGenerator<KP> mo8712keyPairGeneratorimL9hLU(int keySize, CryptographyAlgorithmId<Digest> digest, BigInt publicExponent) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Intrinsics.checkNotNullParameter(publicExponent, "publicExponent");
        return new RsaKeyPairGenerator(this, new RSAKeyGenParameterSpec(BinarySize.m8660getInBitsimpl(keySize), BigIntKt.toJavaBigInteger(publicExponent)), digest);
    }

    /* JADX INFO: compiled from: JdkRsa.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00028\u0000*\u00060\tj\u0002`\nH\u0094\u0080\u0004¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0014R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPublicKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "convert", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "(Ljava/security/PublicKey;)Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "fromPrivateKey", "privateKey", "Ljava/security/interfaces/RSAPrivateCrtKey;", "(Ljava/security/interfaces/RSAPrivateCrtKey;)Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "(Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;[B)Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class RsaPublicKeyDecoder extends JdkPublicKeyDecoder<RSA.PublicKey.Format, PublicK> {
        private final CryptographyAlgorithmId<Digest> digest;
        final /* synthetic */ JdkRsa<PublicK, PrivateK, KP> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPublicKeyDecoder(JdkRsa jdkRsa, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsa.getState(), "RSA");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsa;
            this.digest = digest;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPublicKeyDecoder
        public PublicK convert(java.security.PublicKey publicKey) {
            Intrinsics.checkNotNullParameter(publicKey, "<this>");
            return this.this$0.getWrapPublicKey().invoke(publicKey, this.digest);
        }

        public final PublicK fromPrivateKey(RSAPrivateCrtKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            return (PublicK) decode(new RSAPublicKeySpec(privateKey.getModulus(), privateKey.getPublicExponent()));
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public PublicK decodeFromByteArrayBlocking(RSA.PublicKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.JWK.INSTANCE)) {
                JsonWebKeys.RsaPublicKeyComponents rsaPublicKeyComponentsDecodeRsaPublicKey = JsonWebKeys.INSTANCE.decodeRsaPublicKey(this.this$0.getId(), this.digest, bytes);
                return (PublicK) decode(new RSAPublicKeySpec(new BigInteger(1, rsaPublicKeyComponentsDecodeRsaPublicKey.getN()), new BigInteger(1, rsaPublicKeyComponentsDecodeRsaPublicKey.getE())));
            }
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.JWK.INSTANCE)) {
                throw new IllegalStateException("unreachable".toString());
            }
            if (!Intrinsics.areEqual(format, RSA.PublicKey.Format.DER.INSTANCE)) {
                if (Intrinsics.areEqual(format, RSA.PublicKey.Format.PEM.INSTANCE)) {
                    bytes = KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), bytes);
                } else if (Intrinsics.areEqual(format, RSA.PublicKey.Format.DER.PKCS1.INSTANCE)) {
                    bytes = KeysKt.wrapSubjectPublicKeyInfo(RsaAlgorithmIdentifier.INSTANCE, bytes);
                } else {
                    if (!Intrinsics.areEqual(format, RSA.PublicKey.Format.PEM.PKCS1.INSTANCE)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    bytes = KeysKt.wrapSubjectPublicKeyInfo(RsaAlgorithmIdentifier.INSTANCE, KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8817getRsaPublicKey2EFq_Wg(), bytes));
                }
            }
            return (PublicK) decodeFromDer(bytes);
        }
    }

    /* JADX INFO: compiled from: JdkRsa.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00010\u0001B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00028\u0001*\u00060\tj\u0002`\nH\u0094\u0080\u0004¢\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¢\u0006\u0002\u0010\u0010R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "convert", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "(Ljava/security/PrivateKey;)Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "decodeFromByteArrayBlocking", "format", "bytes", "", "(Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;[B)Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class RsaPrivateKeyDecoder extends JdkPrivateKeyDecoder<RSA.PrivateKey.Format, PrivateK> {
        private final CryptographyAlgorithmId<Digest> digest;
        final /* synthetic */ JdkRsa<PublicK, PrivateK, KP> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPrivateKeyDecoder(JdkRsa jdkRsa, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsa.getState(), "RSA");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsa;
            this.digest = digest;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkPrivateKeyDecoder
        public PrivateK convert(java.security.PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(privateKey, "<this>");
            return this.this$0.getWrapPrivateKey().invoke(privateKey, this.digest, null);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public PrivateK decodeFromByteArrayBlocking(RSA.PrivateKey.Format format, byte[] bytes) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.JWK.INSTANCE)) {
                JsonWebKeys.RsaPrivateKeyComponents rsaPrivateKeyComponentsDecodeRsaPrivateKey = JsonWebKeys.INSTANCE.decodeRsaPrivateKey(this.this$0.getId(), this.digest, bytes);
                return this.this$0.getWrapPrivateKey().invoke(decodeRaw(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getN()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getE()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getD()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getP()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getQ()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getDp()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getDq()), new BigInteger(1, rsaPrivateKeyComponentsDecodeRsaPrivateKey.getQi()))), this.digest, null);
            }
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.JWK.INSTANCE)) {
                throw new IllegalStateException("unreachable".toString());
            }
            if (!Intrinsics.areEqual(format, RSA.PrivateKey.Format.DER.INSTANCE)) {
                if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.PEM.INSTANCE)) {
                    bytes = KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), bytes);
                } else if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.DER.PKCS1.INSTANCE)) {
                    bytes = KeysKt.wrapPrivateKeyInfo(0, RsaAlgorithmIdentifier.INSTANCE, bytes);
                } else {
                    if (!Intrinsics.areEqual(format, RSA.PrivateKey.Format.PEM.PKCS1.INSTANCE)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    bytes = KeysKt.wrapPrivateKeyInfo(0, RsaAlgorithmIdentifier.INSTANCE, KeysKt.m8744unwrapPemunSj4pc(PemLabel.INSTANCE.m8816getRsaPrivateKey2EFq_Wg(), bytes));
                }
            }
            return (PrivateK) decodeFromDer(bytes);
        }
    }

    /* JADX INFO: compiled from: JdkRsa.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00020\u0001B\u001f\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u00020\n*\u00060\u000bj\u0002`\fH\u0094\u0080\u0004J\u0017\u0010\r\u001a\u00028\u0002*\u00060\u000ej\u0002`\u000fH\u0094\u0080\u0004¢\u0006\u0002\u0010\u0010R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaKeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "keyGenParameters", "Ljava/security/spec/RSAKeyGenParameterSpec;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;Ljava/security/spec/RSAKeyGenParameterSpec;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "init", "", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "(Ljava/security/KeyPair;)Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class RsaKeyPairGenerator extends JdkKeyPairGenerator<KP> {
        private final CryptographyAlgorithmId<Digest> digest;
        private final RSAKeyGenParameterSpec keyGenParameters;
        final /* synthetic */ JdkRsa<PublicK, PrivateK, KP> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaKeyPairGenerator(JdkRsa jdkRsa, RSAKeyGenParameterSpec keyGenParameters, CryptographyAlgorithmId<Digest> digest) {
            super(jdkRsa.getState(), "RSA");
            Intrinsics.checkNotNullParameter(keyGenParameters, "keyGenParameters");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsa;
            this.keyGenParameters = keyGenParameters;
            this.digest = digest;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        protected void init(KeyPairGenerator keyPairGenerator) throws InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(keyPairGenerator, "<this>");
            keyPairGenerator.initialize(this.keyGenParameters, getState().getSecureRandom());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.materials.JdkKeyPairGenerator
        public KP convert(java.security.KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "<this>");
            Function2<java.security.PublicKey, CryptographyAlgorithmId<Digest>, PublicK> wrapPublicKey = this.this$0.getWrapPublicKey();
            java.security.PublicKey publicKey = keyPair.getPublic();
            Intrinsics.checkNotNullExpressionValue(publicKey, "getPublic(...)");
            PublicK publickInvoke = wrapPublicKey.invoke(publicKey, this.digest);
            Function3<java.security.PrivateKey, CryptographyAlgorithmId<Digest>, PublicK, PrivateK> wrapPrivateKey = this.this$0.getWrapPrivateKey();
            java.security.PrivateKey privateKey = keyPair.getPrivate();
            Intrinsics.checkNotNullExpressionValue(privateKey, "getPrivate(...)");
            return this.this$0.getWrapKeyPair().invoke(publickInvoke, wrapPrivateKey.invoke(privateKey, this.digest, publickInvoke));
        }
    }

    /* JADX INFO: compiled from: JdkRsa.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\b¤\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\bF\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0086\u0080\u0004R\u0019\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPublicEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "getKey", "()Ljava/security/PublicKey;", "getDigest", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "encodeToByteArrayBlocking", "", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    protected abstract class RsaPublicEncodableKey extends JdkEncodableKey<RSA.PublicKey.Format> {
        private final CryptographyAlgorithmId<Digest> digest;
        private final java.security.PublicKey key;
        final /* synthetic */ JdkRsa<PublicK, PrivateK, KP> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPublicEncodableKey(JdkRsa jdkRsa, java.security.PublicKey key, CryptographyAlgorithmId<Digest> digest) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsa;
            this.key = key;
            this.digest = digest;
        }

        protected final java.security.PublicKey getKey() {
            return this.key;
        }

        protected final CryptographyAlgorithmId<Digest> getDigest() {
            return this.digest;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(RSA.PublicKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.JWK.INSTANCE)) {
                java.security.PublicKey publicKey = this.key;
                Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.RSAPublicKey");
                RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
                JsonWebKeys jsonWebKeys = JsonWebKeys.INSTANCE;
                CryptographyAlgorithmId<?> id = this.this$0.getId();
                CryptographyAlgorithmId<Digest> cryptographyAlgorithmId = this.digest;
                BigInteger modulus = rSAPublicKey.getModulus();
                Intrinsics.checkNotNullExpressionValue(modulus, "getModulus(...)");
                byte[] bArrMagnitudeToByteArray = BigIntKt.toKotlinBigInt(modulus).magnitudeToByteArray();
                BigInteger publicExponent = rSAPublicKey.getPublicExponent();
                Intrinsics.checkNotNullExpressionValue(publicExponent, "getPublicExponent(...)");
                return jsonWebKeys.encodeRsaPublicKey(id, cryptographyAlgorithmId, bArrMagnitudeToByteArray, BigIntKt.toKotlinBigInt(publicExponent).magnitudeToByteArray());
            }
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8815getPublicKey2EFq_Wg(), encodeToDer());
            }
            if (Intrinsics.areEqual(format, RSA.PublicKey.Format.DER.PKCS1.INSTANCE)) {
                return KeysKt.m8746unwrapSubjectPublicKeyInfo4RESAxk(RSAKt.getRSA(ObjectIdentifier.INSTANCE), encodeToDer());
            }
            if (!Intrinsics.areEqual(format, RSA.PublicKey.Format.PEM.PKCS1.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8817getRsaPublicKey2EFq_Wg(), KeysKt.m8746unwrapSubjectPublicKeyInfo4RESAxk(RSAKt.getRSA(ObjectIdentifier.INSTANCE), encodeToDer()));
        }
    }

    /* JADX INFO: compiled from: JdkRsa.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0002\b¤\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B-\bF\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\u0010\n\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0012\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0086\u0080\u0004R\u0019\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa$RsaPrivateEncodableKey;", "Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "publicKey", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsa;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;)V", "getKey", "()Ljava/security/PrivateKey;", "getDigest", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "getPublicKeyBlocking", "()Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "encodeToByteArrayBlocking", "", "format", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    protected abstract class RsaPrivateEncodableKey extends JdkEncodableKey<RSA.PrivateKey.Format> implements RSA.PrivateKey<PublicK> {
        private final CryptographyAlgorithmId<Digest> digest;
        private final java.security.PrivateKey key;
        private PublicK publicKey;
        final /* synthetic */ JdkRsa<PublicK, PrivateK, KP> this$0;

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public /* synthetic */ Object getPublicKey(Continuation continuation) {
            return PublicKeyAccessor.CC.getPublicKey$suspendImpl(this, continuation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaPrivateEncodableKey(JdkRsa jdkRsa, java.security.PrivateKey key, CryptographyAlgorithmId<Digest> digest, PublicK publick) {
            super(key);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = jdkRsa;
            this.key = key;
            this.digest = digest;
            this.publicKey = publick;
        }

        protected final java.security.PrivateKey getKey() {
            return this.key;
        }

        protected final CryptographyAlgorithmId<Digest> getDigest() {
            return this.digest;
        }

        @Override // dev.whyoleg.cryptography.operations.PublicKeyAccessor
        public final PublicK getPublicKeyBlocking() {
            if (this.publicKey == null) {
                RsaPublicKeyDecoder rsaPublicKeyDecoder = new RsaPublicKeyDecoder(this.this$0, this.digest);
                java.security.PrivateKey privateKey = this.key;
                Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.RSAPrivateCrtKey");
                this.publicKey = (PublicK) rsaPublicKeyDecoder.fromPrivateKey((RSAPrivateCrtKey) privateKey);
            }
            PublicK publick = this.publicKey;
            Intrinsics.checkNotNull(publick);
            return publick;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(RSA.PrivateKey.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.JWK.INSTANCE)) {
                java.security.PrivateKey privateKey = this.key;
                Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.RSAPrivateCrtKey");
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) privateKey;
                JsonWebKeys jsonWebKeys = JsonWebKeys.INSTANCE;
                CryptographyAlgorithmId<?> id = this.this$0.getId();
                CryptographyAlgorithmId<Digest> cryptographyAlgorithmId = this.digest;
                BigInteger modulus = rSAPrivateCrtKey.getModulus();
                Intrinsics.checkNotNullExpressionValue(modulus, "getModulus(...)");
                byte[] bArrMagnitudeToByteArray = BigIntKt.toKotlinBigInt(modulus).magnitudeToByteArray();
                BigInteger publicExponent = rSAPrivateCrtKey.getPublicExponent();
                Intrinsics.checkNotNullExpressionValue(publicExponent, "getPublicExponent(...)");
                byte[] bArrMagnitudeToByteArray2 = BigIntKt.toKotlinBigInt(publicExponent).magnitudeToByteArray();
                BigInteger privateExponent = rSAPrivateCrtKey.getPrivateExponent();
                Intrinsics.checkNotNullExpressionValue(privateExponent, "getPrivateExponent(...)");
                byte[] bArrMagnitudeToByteArray3 = BigIntKt.toKotlinBigInt(privateExponent).magnitudeToByteArray();
                BigInteger primeP = rSAPrivateCrtKey.getPrimeP();
                Intrinsics.checkNotNullExpressionValue(primeP, "getPrimeP(...)");
                byte[] bArrMagnitudeToByteArray4 = BigIntKt.toKotlinBigInt(primeP).magnitudeToByteArray();
                BigInteger primeQ = rSAPrivateCrtKey.getPrimeQ();
                Intrinsics.checkNotNullExpressionValue(primeQ, "getPrimeQ(...)");
                byte[] bArrMagnitudeToByteArray5 = BigIntKt.toKotlinBigInt(primeQ).magnitudeToByteArray();
                BigInteger primeExponentP = rSAPrivateCrtKey.getPrimeExponentP();
                Intrinsics.checkNotNullExpressionValue(primeExponentP, "getPrimeExponentP(...)");
                byte[] bArrMagnitudeToByteArray6 = BigIntKt.toKotlinBigInt(primeExponentP).magnitudeToByteArray();
                BigInteger primeExponentQ = rSAPrivateCrtKey.getPrimeExponentQ();
                Intrinsics.checkNotNullExpressionValue(primeExponentQ, "getPrimeExponentQ(...)");
                byte[] bArrMagnitudeToByteArray7 = BigIntKt.toKotlinBigInt(primeExponentQ).magnitudeToByteArray();
                BigInteger crtCoefficient = rSAPrivateCrtKey.getCrtCoefficient();
                Intrinsics.checkNotNullExpressionValue(crtCoefficient, "getCrtCoefficient(...)");
                return jsonWebKeys.encodeRsaPrivateKey(id, cryptographyAlgorithmId, bArrMagnitudeToByteArray, bArrMagnitudeToByteArray2, bArrMagnitudeToByteArray3, bArrMagnitudeToByteArray4, bArrMagnitudeToByteArray5, bArrMagnitudeToByteArray6, bArrMagnitudeToByteArray7, BigIntKt.toKotlinBigInt(crtCoefficient).magnitudeToByteArray());
            }
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.DER.INSTANCE)) {
                return encodeToDer();
            }
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.PEM.INSTANCE)) {
                return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8814getPrivateKey2EFq_Wg(), encodeToDer());
            }
            if (Intrinsics.areEqual(format, RSA.PrivateKey.Format.DER.PKCS1.INSTANCE)) {
                return KeysKt.m8745unwrapPrivateKeyInfo4RESAxk(RSAKt.getRSA(ObjectIdentifier.INSTANCE), encodeToDer());
            }
            if (!Intrinsics.areEqual(format, RSA.PrivateKey.Format.PEM.PKCS1.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            return KeysKt.m8747wrapPemunSj4pc(PemLabel.INSTANCE.m8816getRsaPrivateKey2EFq_Wg(), KeysKt.m8745unwrapPrivateKeyInfo4RESAxk(RSAKt.getRSA(ObjectIdentifier.INSTANCE), encodeToDer()));
        }
    }
}

package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.DelicateCryptographyApi;
import dev.whyoleg.cryptography.algorithms.RSA.KeyPair;
import dev.whyoleg.cryptography.algorithms.RSA.PrivateKey;
import dev.whyoleg.cryptography.algorithms.RSA.PublicKey;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntKt;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.AuthenticatedDecryptor;
import dev.whyoleg.cryptography.operations.AuthenticatedEncryptor;
import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u0004*\u0014\b\u0002\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u00020\u0007:\u0007\u0018\u0019\u001a\u001b\u001c\u001d\u001eJ$\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH¦\u0080\u0004J$\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH¦\u0080\u0004J;\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H¦\u0080\u0004¢\u0006\u0004\b\u0016\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "KP", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keySize", "Ldev/whyoleg/cryptography/BinarySize;", "publicExponent", "Ldev/whyoleg/cryptography/bigint/BigInt;", "keyPairGenerator-imL9hLU", "(ILdev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/bigint/BigInt;)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "KeyPair", "PublicKey", "PrivateKey", "OAEP", "PSS", "PKCS1", "RAW", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface RSA<PublicK extends PublicKey, PrivateK extends PrivateKey<PublicK>, KP extends KeyPair<PublicK, PrivateK>> extends CryptographyAlgorithm {

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\bg\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u0002*\u000e\b\u0004\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005R\u0013\u0010\u0006\u001a\u00028\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u00028\u0004X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "", "publicKey", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "privateKey", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair<PublicK extends PublicKey, PrivateK extends PrivateKey<PublicK>> {
        PrivateK getPrivateKey();

        PublicK getPublicKey();
    }

    /* JADX INFO: renamed from: keyPairGenerator-imL9hLU, reason: not valid java name */
    KeyGenerator<KP> mo8712keyPairGeneratorimL9hLU(int keySize, CryptographyAlgorithmId<Digest> digest, BigInt publicExponent);

    Decoder<PrivateKey.Format, PrivateK> privateKeyDecoder(CryptographyAlgorithmId<Digest> digest);

    Decoder<PublicKey.Format, PublicK> publicKeyDecoder(CryptographyAlgorithmId<Digest> digest);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: RSA.kt */
    public final /* synthetic */ class CC {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: keyPairGenerator-imL9hLU$default, reason: not valid java name */
        public static /* synthetic */ KeyGenerator m8713keyPairGeneratorimL9hLU$default(RSA rsa, int i, CryptographyAlgorithmId cryptographyAlgorithmId, BigInt bigInt, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: keyPairGenerator-imL9hLU");
            }
            if ((i2 & 1) != 0) {
                i = BinarySize.INSTANCE.m8670getBitsM1VWhS4(4096);
            }
            if ((i2 & 2) != 0) {
                cryptographyAlgorithmId = SHA512.INSTANCE;
            }
            if ((i2 & 4) != 0) {
                bigInt = BigIntKt.toBigInt(65537);
            }
            return rsa.mo8712keyPairGeneratorimL9hLU(i, cryptographyAlgorithmId, bigInt);
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends Encodable<Format> {

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static abstract class Format implements EncodingFormat {
            public /* synthetic */ Format(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Format() {
            }

            @Override // dev.whyoleg.cryptography.materials.EncodingFormat
            public final String toString() {
                return getName();
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class JWK extends Format {
                public static final JWK INSTANCE = new JWK();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof JWK)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return -1819476043;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "<init>", "()V", "Generic", "PKCS1", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER$PKCS1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class DER extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ DER(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private DER() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PublicKey$Format$DER$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion extends DER {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "DER";
                    }
                }

                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER$PKCS1;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class PKCS1 extends DER {
                    public static final PKCS1 INSTANCE = new PKCS1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof PKCS1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return 1950278528;
                    }

                    private PKCS1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "DER/PKCS#1";
                    }
                }
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format;", "<init>", "()V", "Generic", "PKCS1", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM$PKCS1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class PEM extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ PEM(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private PEM() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PublicKey$Format$PEM$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion extends PEM {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "PEM";
                    }
                }

                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM$PKCS1;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class PKCS1 extends PEM {
                    public static final PKCS1 INSTANCE = new PKCS1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof PKCS1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return 1593110343;
                    }

                    private PKCS1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "PEM/PKCS#1";
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/PublicKeyAccessor;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey<PublicK extends PublicKey> extends Encodable<Format>, PublicKeyAccessor<PublicK> {

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static abstract class Format implements EncodingFormat {
            public /* synthetic */ Format(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Format() {
            }

            @Override // dev.whyoleg.cryptography.materials.EncodingFormat
            public final String toString() {
                return getName();
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class JWK extends Format {
                public static final JWK INSTANCE = new JWK();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof JWK)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return 1275849891;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "<init>", "()V", "Generic", "PKCS1", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER$PKCS1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class DER extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ DER(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private DER() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PrivateKey$Format$DER$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion extends DER {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "DER";
                    }
                }

                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER$PKCS1;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class PKCS1 extends DER {
                    public static final PKCS1 INSTANCE = new PKCS1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof PKCS1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return -1024989714;
                    }

                    private PKCS1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "DER/PKCS#1";
                    }
                }
            }

            /* JADX INFO: compiled from: RSA.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format;", "<init>", "()V", "Generic", "PKCS1", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM$PKCS1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class PEM extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ PEM(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private PEM() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PrivateKey$Format$PEM$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion extends PEM {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "PEM";
                    }
                }

                /* JADX INFO: compiled from: RSA.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM$PKCS1;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class PKCS1 extends PEM {
                    public static final PKCS1 INSTANCE = new PKCS1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof PKCS1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return -1382157899;
                    }

                    private PKCS1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "PEM/PKCS#1";
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0004\t\n\u000b\fR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$OAEP;", "Ldev/whyoleg/cryptography/algorithms/RSA;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface OAEP extends RSA<PublicKey, PrivateKey, KeyPair> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface KeyPair extends KeyPair<PublicKey, PrivateKey> {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "decryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PrivateKey extends PrivateKey<PublicKey> {
            AuthenticatedDecryptor decryptor();
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "encryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedEncryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PublicKey extends PublicKey {
            AuthenticatedEncryptor encryptor();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<OAEP> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$OAEP$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: RSA.kt */
        public final /* synthetic */ class CC {
            public static CryptographyAlgorithmId $default$getId(OAEP _this) {
                return OAEP.INSTANCE;
            }
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/RSA$OAEP;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<OAEP> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("RSA-OAEP");
            }
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0004\t\n\u000b\fR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PSS;", "Ldev/whyoleg/cryptography/algorithms/RSA;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PSS extends RSA<PublicKey, PrivateKey, KeyPair> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PSS$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface KeyPair extends KeyPair<PublicKey, PrivateKey> {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦\u0080\u0004¢\u0006\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "saltSize", "Ldev/whyoleg/cryptography/BinarySize;", "signatureGenerator-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PrivateKey extends PrivateKey<PublicKey> {
            SignatureGenerator signatureGenerator();

            /* JADX INFO: renamed from: signatureGenerator-6q1zMKY, reason: not valid java name */
            SignatureGenerator mo8714signatureGenerator6q1zMKY(int saltSize);
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "saltSize", "Ldev/whyoleg/cryptography/BinarySize;", "signatureVerifier-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PublicKey extends PublicKey {
            SignatureVerifier signatureVerifier();

            /* JADX INFO: renamed from: signatureVerifier-6q1zMKY, reason: not valid java name */
            SignatureVerifier mo8715signatureVerifier6q1zMKY(int saltSize);
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<PSS> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PSS$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: RSA.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PSS$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/RSA$PSS;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<PSS> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("RSA-PSS");
            }
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0004\t\n\u000b\fR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1;", "Ldev/whyoleg/cryptography/algorithms/RSA;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PKCS1 extends RSA<PublicKey, PrivateKey, KeyPair> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface KeyPair extends KeyPair<PublicKey, PrivateKey> {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004J\n\u0010\u0005\u001a\u00020\u0006H§\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "decryptor", "Ldev/whyoleg/cryptography/operations/Decryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PrivateKey extends PrivateKey<PublicKey> {
            @DelicateCryptographyApi
            Decryptor decryptor();

            SignatureGenerator signatureGenerator();
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\n\u0010\u0004\u001a\u00020\u0005H§\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "encryptor", "Ldev/whyoleg/cryptography/operations/Encryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PublicKey extends PublicKey {
            @DelicateCryptographyApi
            Encryptor encryptor();

            SignatureVerifier signatureVerifier();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<PKCS1> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$PKCS1$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: RSA.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<PKCS1> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("RSA-PKCS1-V1.5");
            }
        }
    }

    /* JADX INFO: compiled from: RSA.kt */
    @DelicateCryptographyApi
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0004\t\n\u000b\fR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$RAW;", "Ldev/whyoleg/cryptography/algorithms/RSA;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface RAW extends RSA<PublicKey, PrivateKey, KeyPair> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$RAW$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface KeyPair extends KeyPair<PublicKey, PrivateKey> {
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "decryptor", "Ldev/whyoleg/cryptography/operations/Decryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PrivateKey extends PrivateKey<PublicKey> {
            Decryptor decryptor();
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/RSA$PublicKey;", "encryptor", "Ldev/whyoleg/cryptography/operations/Encryptor;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface PublicKey extends PublicKey {
            Encryptor encryptor();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<RAW> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.RSA$RAW$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: RSA.kt */
        public final /* synthetic */ class CC {
            public static CryptographyAlgorithmId $default$getId(RAW _this) {
                return RAW.INSTANCE;
            }
        }

        /* JADX INFO: compiled from: RSA.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/RSA$RAW$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/RSA$RAW;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<RAW> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("RSA-RAW");
            }
        }
    }
}

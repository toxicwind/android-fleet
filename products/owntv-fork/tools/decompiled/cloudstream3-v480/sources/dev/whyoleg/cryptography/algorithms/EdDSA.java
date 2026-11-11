package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EdDSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u0000 \u00122\u00020\u0001:\u0005\u0012\u0013\u0014\u0015\u0016J\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000bH¦\u0080\u0004J\u001e\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u00072\u0006\u0010\n\u001a\u00020\u000bH¦\u0080\u0004J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u000bH¦\u0080\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "curve", "Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$KeyPair;", "Companion", "Curve", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface EdDSA extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: EdDSA.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$Curve;", "", "<init>", "(Ljava/lang/String;I)V", "Ed25519", "Ed448", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum Curve {
        Ed25519,
        Ed448;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Curve> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: EdDSA.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0013\u0010\u0002\u001a\u00020\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$KeyPair;", "", "publicKey", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair {
        PrivateKey getPrivateKey();

        PublicKey getPublicKey();
    }

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<EdDSA> getId();

    KeyGenerator<KeyPair> keyPairGenerator(Curve curve);

    Decoder<PrivateKey.Format, PrivateKey> privateKeyDecoder(Curve curve);

    Decoder<PublicKey.Format, PublicKey> publicKeyDecoder(Curve curve);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.EdDSA$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: EdDSA.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: EdDSA.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/EdDSA;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<EdDSA> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("EdDSA");
        }
    }

    /* JADX INFO: compiled from: EdDSA.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0005J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends Encodable<Format> {
        SignatureVerifier signatureVerifier();

        /* JADX INFO: compiled from: EdDSA.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0006\u0007\b\tB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "RAW", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$RAW;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 1590929730;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$RAW;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class RAW extends Format {
                public static final RAW INSTANCE = new RAW();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof RAW)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return 1590936748;
                }

                private RAW() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "RAW";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class DER extends Format {
                public static final DER INSTANCE = new DER();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof DER)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return 1590923413;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class PEM extends Format {
                public static final PEM INSTANCE = new PEM();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof PEM)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return 1590934940;
                }

                private PEM() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "PEM";
                }
            }
        }
    }

    /* JADX INFO: compiled from: EdDSA.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00040\u0003:\u0001\u0007J\n\u0010\u0005\u001a\u00020\u0006H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/PublicKeyAccessor;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PublicKey;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey extends Encodable<Format>, PublicKeyAccessor<PublicKey> {
        SignatureGenerator signatureGenerator();

        /* JADX INFO: compiled from: EdDSA.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0006\u0007\b\tB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "RAW", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$RAW;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -375753546;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$RAW;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class RAW extends Format {
                public static final RAW INSTANCE = new RAW();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof RAW)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return -375746528;
                }

                private RAW() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "RAW";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class DER extends Format {
                public static final DER INSTANCE = new DER();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof DER)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return -375759863;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: EdDSA.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EdDSA$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class PEM extends Format {
                public static final PEM INSTANCE = new PEM();

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof PEM)) {
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return -375748336;
                }

                private PEM() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "PEM";
                }
            }
        }
    }
}

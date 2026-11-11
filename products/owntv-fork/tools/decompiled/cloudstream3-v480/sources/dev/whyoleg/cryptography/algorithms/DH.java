package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.ParametersGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import dev.whyoleg.cryptography.operations.SharedSecretGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DH.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u0000 \u00172\u00020\u0001:\u0005\u0017\u0018\u0019\u001a\u001bJ\u0016\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H¦\u0080\u0004J\u0016\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007H¦\u0080\u0004J\u0016\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0007H¦\u0080\u0004J+\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H¦\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "parametersDecoder", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "parametersGenerator", "Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "primeSize", "Ldev/whyoleg/cryptography/BinarySize;", "privateValueSize", "parametersGenerator-EFf-y7U", "(ILdev/whyoleg/cryptography/BinarySize;)Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "Companion", "Parameters", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface DH extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0013\u0010\u0002\u001a\u00020\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$KeyPair;", "", "publicKey", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "privateKey", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair {
        PrivateKey getPrivateKey();

        PublicKey getPublicKey();
    }

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<DH> getId();

    Decoder<Parameters.Format, Parameters> parametersDecoder();

    /* JADX INFO: renamed from: parametersGenerator-EFf-y7U, reason: not valid java name */
    ParametersGenerator<Parameters> mo8683parametersGeneratorEFfy7U(int primeSize, BinarySize privateValueSize);

    Decoder<PrivateKey.Format, PrivateKey> privateKeyDecoder();

    Decoder<PublicKey.Format, PublicKey> publicKeyDecoder();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.DH$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DH.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = DH.INSTANCE;
        }

        /* JADX INFO: renamed from: parametersGenerator-EFf-y7U$default, reason: not valid java name */
        public static /* synthetic */ ParametersGenerator m8684parametersGeneratorEFfy7U$default(DH dh, int i, BinarySize binarySize, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parametersGenerator-EFf-y7U");
            }
            if ((i2 & 2) != 0) {
                binarySize = null;
            }
            return dh.mo8683parametersGeneratorEFfy7U(i, binarySize);
        }
    }

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/DH;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<DH> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("DH");
        }
    }

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006J\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$Parameters;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$KeyPair;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Parameters extends Encodable<Format> {
        KeyGenerator<KeyPair> keyPairGenerator();

        /* JADX INFO: compiled from: DH.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format$PEM;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 551541404;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/DH$Parameters$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 551552931;
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

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006J\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "sharedSecretGenerator", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends Encodable<Format> {
        SharedSecretGenerator<PrivateKey> sharedSecretGenerator();

        /* JADX INFO: compiled from: DH.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format$PEM;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 1284987156;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 1284998683;
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

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00040\u0003:\u0001\u0007J\u0010\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/PublicKeyAccessor;", "Ldev/whyoleg/cryptography/algorithms/DH$PublicKey;", "sharedSecretGenerator", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey extends Encodable<Format>, PublicKeyAccessor<PublicKey> {
        SharedSecretGenerator<PublicKey> sharedSecretGenerator();

        /* JADX INFO: compiled from: DH.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format$PEM;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -1269849238;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: DH.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/DH$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -1269837711;
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

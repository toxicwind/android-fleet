package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.algorithms.EC.KeyPair;
import dev.whyoleg.cryptography.algorithms.EC.PrivateKey;
import dev.whyoleg.cryptography.algorithms.EC.PublicKey;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.PublicKeyAccessor;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u0004*\u0014\b\u0002\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u00020\u0007:\u0004\u0016\u0017\u0018\u0019J%\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u000b\u001a\u00020\fH¦\u0080\u0004¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH¦\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u000eJ\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH¦\u0080\u0004¢\u0006\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "KP", "Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "publicKeyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "curve", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "publicKeyDecoder-yfdS0HE", "(Ljava/lang/String;)Ldev/whyoleg/cryptography/materials/Decoder;", "privateKeyDecoder", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "privateKeyDecoder-yfdS0HE", "keyPairGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keyPairGenerator-yfdS0HE", "(Ljava/lang/String;)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Curve", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface EC<PublicK extends PublicKey, PrivateK extends PrivateKey<PublicK>, KP extends KeyPair<PublicK, PrivateK>> extends CryptographyAlgorithm {

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\bg\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u0002*\u000e\b\u0004\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005R\u0013\u0010\u0006\u001a\u00028\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u00028\u0004X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "PrivateK", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "", "publicKey", "getPublicKey", "()Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "privateKey", "getPrivateKey", "()Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair<PublicK extends PublicKey, PrivateK extends PrivateKey<PublicK>> {
        PrivateK getPrivateKey();

        PublicK getPublicKey();
    }

    /* JADX INFO: renamed from: keyPairGenerator-yfdS0HE, reason: not valid java name */
    KeyGenerator<KP> mo8687keyPairGeneratoryfdS0HE(String curve);

    /* JADX INFO: renamed from: privateKeyDecoder-yfdS0HE, reason: not valid java name */
    Decoder<PrivateKey.Format, PrivateK> mo8688privateKeyDecoderyfdS0HE(String curve);

    /* JADX INFO: renamed from: publicKeyDecoder-yfdS0HE, reason: not valid java name */
    Decoder<PublicKey.Format, PublicK> mo8689publicKeyDecoderyfdS0HE(String curve);

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "", "name", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getName", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @JvmInline
    public static final class Curve {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String name;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Curve m8690boximpl(String str) {
            return new Curve(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static String m8691constructorimpl(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return name;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m8692equalsimpl(String str, Object obj) {
            return (obj instanceof Curve) && Intrinsics.areEqual(str, ((Curve) obj).m8696unboximpl());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m8693equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m8694hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m8695toStringimpl(String str) {
            return "Curve(name=" + str + ')';
        }

        public boolean equals(Object other) {
            return m8692equalsimpl(this.name, other);
        }

        public int hashCode() {
            return m8694hashCodeimpl(this.name);
        }

        public String toString() {
            return m8695toStringimpl(this.name);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ String m8696unboximpl() {
            return this.name;
        }

        /* JADX INFO: compiled from: EC.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0015\u0010\n\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0015\u0010\f\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0015\u0010\u000e\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0015\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007R\u0015\u0010\u0012\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$Curve$Companion;", "", "<init>", "()V", "P256", "Ldev/whyoleg/cryptography/algorithms/EC$Curve;", "getP256-pVITJAk", "()Ljava/lang/String;", "P384", "getP384-pVITJAk", "P521", "getP521-pVITJAk", "secp256k1", "getSecp256k1-pVITJAk", "brainpoolP256r1", "getBrainpoolP256r1-pVITJAk", "brainpoolP384r1", "getBrainpoolP384r1-pVITJAk", "brainpoolP512r1", "getBrainpoolP512r1-pVITJAk", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getP256-pVITJAk, reason: not valid java name */
            public final String m8700getP256pVITJAk() {
                return Curve.m8691constructorimpl("P-256");
            }

            /* JADX INFO: renamed from: getP384-pVITJAk, reason: not valid java name */
            public final String m8701getP384pVITJAk() {
                return Curve.m8691constructorimpl("P-384");
            }

            /* JADX INFO: renamed from: getP521-pVITJAk, reason: not valid java name */
            public final String m8702getP521pVITJAk() {
                return Curve.m8691constructorimpl("P-521");
            }

            /* JADX INFO: renamed from: getSecp256k1-pVITJAk, reason: not valid java name */
            public final String m8703getSecp256k1pVITJAk() {
                return Curve.m8691constructorimpl("secp256k1");
            }

            /* JADX INFO: renamed from: getBrainpoolP256r1-pVITJAk, reason: not valid java name */
            public final String m8697getBrainpoolP256r1pVITJAk() {
                return Curve.m8691constructorimpl("brainpoolP256r1");
            }

            /* JADX INFO: renamed from: getBrainpoolP384r1-pVITJAk, reason: not valid java name */
            public final String m8698getBrainpoolP384r1pVITJAk() {
                return Curve.m8691constructorimpl("brainpoolP384r1");
            }

            /* JADX INFO: renamed from: getBrainpoolP512r1-pVITJAk, reason: not valid java name */
            public final String m8699getBrainpoolP512r1pVITJAk() {
                return Curve.m8691constructorimpl("brainpoolP512r1");
            }
        }

        private /* synthetic */ Curve(String str) {
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends Encodable<Format> {

        /* JADX INFO: compiled from: EC.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0006\u0007\b\tB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "RAW", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -164862489;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "<init>", "()V", "Uncompressed", "Compressed", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW$Compressed;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW$Uncompressed;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class RAW extends Format {

                /* JADX INFO: renamed from: Uncompressed, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ RAW(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private RAW() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.EC$PublicKey$Format$RAW$Uncompressed, reason: from kotlin metadata */
                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW$Uncompressed;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion extends RAW {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "RAW";
                    }
                }

                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW$Compressed;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$RAW;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class Compressed extends RAW {
                    public static final Compressed INSTANCE = new Compressed();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof Compressed)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return -876424098;
                    }

                    private Compressed() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "RAW/COMPRESSED";
                    }
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -164868806;
                }

                private DER() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "DER";
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return -164857279;
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

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "PublicK", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "Ldev/whyoleg/cryptography/operations/PublicKeyAccessor;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey<PublicK extends PublicKey> extends Encodable<Format>, PublicKeyAccessor<PublicK> {

        /* JADX INFO: compiled from: EC.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0006\u0007\b\tB\t\bD¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "<init>", "()V", "toString", "", "JWK", "RAW", "DER", "PEM", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$RAW;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$JWK;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 1029262513;
                }

                private JWK() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "JWK";
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$RAW;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
                    return 1029269531;
                }

                private RAW() {
                    super(null);
                }

                @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                public String getName() {
                    return "RAW";
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "<init>", "()V", "Generic", "SEC1", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER$SEC1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class DER extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ DER(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private DER() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.EC$PrivateKey$Format$DER$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER$Generic;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER$SEC1;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$DER;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class SEC1 extends DER {
                    public static final SEC1 INSTANCE = new SEC1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof SEC1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return -137777718;
                    }

                    private SEC1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "DER/SEC1";
                    }
                }
            }

            /* JADX INFO: compiled from: EC.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\t\bD¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format;", "<init>", "()V", "Generic", "SEC1", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM$SEC1;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static abstract class PEM extends Format {

                /* JADX INFO: renamed from: Generic, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                public /* synthetic */ PEM(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private PEM() {
                    super(null);
                }

                /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.EC$PrivateKey$Format$PEM$Generic, reason: from kotlin metadata */
                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM$Generic;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

                /* JADX INFO: compiled from: EC.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM$SEC1;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey$Format$PEM;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class SEC1 extends PEM {
                    public static final SEC1 INSTANCE = new SEC1();

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof SEC1)) {
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return -842035933;
                    }

                    private SEC1() {
                        super(null);
                    }

                    @Override // dev.whyoleg.cryptography.materials.EncodingFormat
                    public String getName() {
                        return "PEM/SEC1";
                    }
                }
            }
        }
    }
}

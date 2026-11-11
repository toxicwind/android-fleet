package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.DelicateCryptographyApi;
import dev.whyoleg.cryptography.algorithms.AES.Key;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.Cipher;
import dev.whyoleg.cryptography.operations.IvAuthenticatedCipher;
import dev.whyoleg.cryptography.operations.IvCipher;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AES.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\n\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018J\u0016\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005H¦\u0080\u0004J!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\b\b\u0002\u0010\t\u001a\u00020\nH¦\u0080\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\t\u001a\u00020\rH\u0097\u0080\u0004¢\u0006\u0004\b\u000e\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES;", "K", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keySize", "Ldev/whyoleg/cryptography/BinarySize;", "keyGenerator-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/symmetric/SymmetricKeySize;", "keyGenerator-__FLmac", "Key", "GCM", "CCM", "CTR", "CBC", "CMAC", "ECB", "OFB", "CFB", "CFB8", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AES<K extends Key> extends CryptographyAlgorithm {
    Decoder<Key.Format, K> keyDecoder();

    /* JADX INFO: renamed from: keyGenerator-6q1zMKY, reason: not valid java name */
    KeyGenerator<K> mo8672keyGenerator6q1zMKY(int keySize);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by overload with BinarySize", replaceWith = @ReplaceWith(expression = "keyGenerator(keySize.value)", imports = {}))
    /* JADX INFO: renamed from: keyGenerator-__FLmac, reason: not valid java name */
    KeyGenerator<K> mo8673keyGenerator__FLmac(int keySize);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AES.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: keyGenerator-6q1zMKY$default, reason: not valid java name */
        public static /* synthetic */ KeyGenerator m8675keyGenerator6q1zMKY$default(AES aes, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: keyGenerator-6q1zMKY");
            }
            if ((i2 & 1) != 0) {
                i = Key.Size.INSTANCE.m8682getB256XsYwlU8();
            }
            return aes.mo8672keyGenerator6q1zMKY(i);
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$Key;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "Format", "Size", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Key extends Encodable<Format> {

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "", "<init>", "(Ljava/lang/String;I)V", "RAW", "JWK", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum Format implements EncodingFormat {
            RAW,
            JWK;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<Format> getEntries() {
                return $ENTRIES;
            }

            @Override // dev.whyoleg.cryptography.materials.EncodingFormat
            public /* bridge */ /* synthetic */ String getName() {
                return name();
            }
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0015\u0010\n\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$Key$Size;", "", "<init>", "()V", "B128", "Ldev/whyoleg/cryptography/BinarySize;", "getB128-XsYwlU8", "()I", "B192", "getB192-XsYwlU8", "B256", "getB256-XsYwlU8", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Size {
            public static final Size INSTANCE = new Size();

            private Size() {
            }

            /* JADX INFO: renamed from: getB128-XsYwlU8, reason: not valid java name */
            public final int m8680getB128XsYwlU8() {
                return BinarySize.INSTANCE.m8670getBitsM1VWhS4(128);
            }

            /* JADX INFO: renamed from: getB192-XsYwlU8, reason: not valid java name */
            public final int m8681getB192XsYwlU8() {
                return BinarySize.INSTANCE.m8670getBitsM1VWhS4(192);
            }

            /* JADX INFO: renamed from: getB256-XsYwlU8, reason: not valid java name */
            public final int m8682getB256XsYwlU8() {
                return BinarySize.INSTANCE.m8670getBitsM1VWhS4(256);
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$GCM;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$GCM$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface GCM extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<GCM> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$GCM$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$GCM$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$GCM;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<GCM> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-GCM");
            }
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$GCM$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "tagSize", "Ldev/whyoleg/cryptography/BinarySize;", "cipher-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            /* JADX INFO: renamed from: cipher-6q1zMKY, reason: not valid java name */
            IvAuthenticatedCipher mo8678cipher6q1zMKY(int tagSize);

            /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$GCM$Key$-CC, reason: invalid class name */
            /* JADX INFO: compiled from: AES.kt */
            public final /* synthetic */ class CC {
                /* JADX INFO: renamed from: cipher-6q1zMKY$default, reason: not valid java name */
                public static /* synthetic */ IvAuthenticatedCipher m8679cipher6q1zMKY$default(Key key, int i, int i2, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cipher-6q1zMKY");
                    }
                    if ((i2 & 1) != 0) {
                        i = BinarySize.INSTANCE.m8670getBitsM1VWhS4(128);
                    }
                    return key.mo8678cipher6q1zMKY(i);
                }
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CCM;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CCM$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CCM extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CCM> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CCM$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CCM$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CCM;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CCM> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CCM");
            }
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CCM$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "tagSize", "Ldev/whyoleg/cryptography/BinarySize;", "cipher-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            /* JADX INFO: renamed from: cipher-6q1zMKY, reason: not valid java name */
            IvAuthenticatedCipher mo8676cipher6q1zMKY(int tagSize);

            /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CCM$Key$-CC, reason: invalid class name */
            /* JADX INFO: compiled from: AES.kt */
            public final /* synthetic */ class CC {
                /* JADX INFO: renamed from: cipher-6q1zMKY$default, reason: not valid java name */
                public static /* synthetic */ IvAuthenticatedCipher m8677cipher6q1zMKY$default(Key key, int i, int i2, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cipher-6q1zMKY");
                    }
                    if ((i2 & 1) != 0) {
                        i = BinarySize.INSTANCE.m8670getBitsM1VWhS4(128);
                    }
                    return key.mo8676cipher6q1zMKY(i);
                }
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CTR;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CTR$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CTR extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CTR$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            IvCipher cipher();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CTR> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CTR$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CTR$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CTR;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CTR> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CTR");
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CBC$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CBC extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CBC> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CBC$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CBC$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CBC> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CBC");
            }
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CBC$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "padding", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            IvCipher cipher(boolean padding);

            /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CBC$Key$-CC, reason: invalid class name */
            /* JADX INFO: compiled from: AES.kt */
            public final /* synthetic */ class CC {
                public static /* synthetic */ IvCipher cipher$default(Key key, boolean z, int i, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cipher");
                    }
                    if ((i & 1) != 0) {
                        z = true;
                    }
                    return key.cipher(z);
                }
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CMAC;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CMAC$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CMAC extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\n\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CMAC$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            SignatureGenerator signatureGenerator();

            SignatureVerifier signatureVerifier();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CMAC> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CMAC$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CMAC$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CMAC;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CMAC> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CMAC");
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @DelicateCryptographyApi
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$ECB;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$ECB$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface ECB extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<ECB> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$ECB$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$ECB$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$ECB;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<ECB> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-ECB");
            }
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$ECB$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/Cipher;", "padding", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            Cipher cipher(boolean padding);

            /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$ECB$Key$-CC, reason: invalid class name */
            /* JADX INFO: compiled from: AES.kt */
            public final /* synthetic */ class CC {
                public static /* synthetic */ Cipher cipher$default(Key key, boolean z, int i, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cipher");
                    }
                    if ((i & 1) != 0) {
                        z = true;
                    }
                    return key.cipher(z);
                }
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @DelicateCryptographyApi
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$OFB;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$OFB$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface OFB extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$OFB$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            IvCipher cipher();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<OFB> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$OFB$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$OFB$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$OFB;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<OFB> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-OFB");
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @DelicateCryptographyApi
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CFB$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CFB extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            IvCipher cipher();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CFB> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CFB$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CFB;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CFB> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CFB");
            }
        }
    }

    /* JADX INFO: compiled from: AES.kt */
    @DelicateCryptographyApi
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bR\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB8;", "Ldev/whyoleg/cryptography/algorithms/AES;", "Ldev/whyoleg/cryptography/algorithms/AES$CFB8$Key;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CFB8 extends AES<Key> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB8$Key;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cipher", "Ldev/whyoleg/cryptography/operations/IvCipher;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Key extends Key {
            IvCipher cipher();
        }

        @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
        CryptographyAlgorithmId<CFB8> getId();

        /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.AES$CFB8$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: AES.kt */
        public final /* synthetic */ class CC {
        }

        /* JADX INFO: compiled from: AES.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/AES$CFB8$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/AES$CFB8;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion extends CryptographyAlgorithmId<CFB8> {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
                super("AES-CFB8");
            }
        }
    }
}

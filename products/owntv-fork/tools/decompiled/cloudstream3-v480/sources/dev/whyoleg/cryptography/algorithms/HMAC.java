package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: HMAC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u000e2\u00020\u0001:\u0002\u000e\u000fJ$\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H¦\u0080\u0004J \u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H¦\u0080\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HMAC;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digest", "Ldev/whyoleg/cryptography/algorithms/Digest;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface HMAC extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<HMAC> getId();

    Decoder<Key.Format, Key> keyDecoder(CryptographyAlgorithmId<Digest> digest);

    KeyGenerator<Key> keyGenerator(CryptographyAlgorithmId<Digest> digest);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.HMAC$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: HMAC.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = HMAC.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ KeyGenerator keyGenerator$default(HMAC hmac, CryptographyAlgorithmId cryptographyAlgorithmId, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: keyGenerator");
            }
            if ((i & 1) != 0) {
                cryptographyAlgorithmId = SHA512.INSTANCE;
            }
            return hmac.keyGenerator(cryptographyAlgorithmId);
        }
    }

    /* JADX INFO: compiled from: HMAC.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HMAC$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/HMAC;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<HMAC> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("HMAC");
        }
    }

    /* JADX INFO: compiled from: HMAC.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0007J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004J\n\u0010\u0005\u001a\u00020\u0006H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Key extends Encodable<Format> {
        SignatureGenerator signatureGenerator();

        SignatureVerifier signatureVerifier();

        /* JADX INFO: compiled from: HMAC.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "", "<init>", "(Ljava/lang/String;I)V", "RAW", "JWK", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
    }
}

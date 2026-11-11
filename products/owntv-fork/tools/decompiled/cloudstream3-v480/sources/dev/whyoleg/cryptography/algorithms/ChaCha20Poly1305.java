package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.IvAuthenticatedCipher;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ChaCha20Poly1305.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \f2\u00020\u0001:\u0002\f\rJ\u0016\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H¦\u0080\u0004J\u0010\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH¦\u0080\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Companion", "Key", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ChaCha20Poly1305 extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<ChaCha20Poly1305> getId();

    Decoder<Key.Format, Key> keyDecoder();

    KeyGenerator<Key> keyGenerator();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ChaCha20Poly1305.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: ChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<ChaCha20Poly1305> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("ChaCha20-Poly1305");
        }
    }

    /* JADX INFO: compiled from: ChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0005J\n\u0010\u0003\u001a\u00020\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "Ldev/whyoleg/cryptography/materials/Encodable;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "cipher", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "Format", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Key extends Encodable<Format> {
        IvAuthenticatedCipher cipher();

        /* JADX INFO: compiled from: ChaCha20Poly1305.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "", "<init>", "(Ljava/lang/String;I)V", "RAW", "JWK", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

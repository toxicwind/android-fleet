package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ECDSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0005\t\n\u000b\f\rR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA;", "Ldev/whyoleg/cryptography/algorithms/EC;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "SignatureFormat", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ECDSA extends EC<PublicKey, PrivateKey, KeyPair> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ECDSA.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair extends EC.KeyPair<PublicKey, PrivateKey> {
    }

    /* JADX INFO: compiled from: ECDSA.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\"\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "signatureGenerator", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/ECDSA$SignatureFormat;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey extends EC.PrivateKey<PublicKey> {
        SignatureGenerator signatureGenerator(CryptographyAlgorithmId<Digest> digest, SignatureFormat format);
    }

    /* JADX INFO: compiled from: ECDSA.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "signatureVerifier", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "format", "Ldev/whyoleg/cryptography/algorithms/ECDSA$SignatureFormat;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends EC.PublicKey {
        SignatureVerifier signatureVerifier(CryptographyAlgorithmId<Digest> digest, SignatureFormat format);
    }

    /* JADX INFO: compiled from: ECDSA.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA$SignatureFormat;", "", "<init>", "(Ljava/lang/String;I)V", "RAW", "DER", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum SignatureFormat {
        RAW,
        DER;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<SignatureFormat> getEntries() {
            return $ENTRIES;
        }
    }

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<ECDSA> getId();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.ECDSA$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ECDSA.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: ECDSA.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDSA$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/ECDSA;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<ECDSA> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("ECDSA");
        }
    }
}

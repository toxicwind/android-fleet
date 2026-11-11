package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.operations.SharedSecretGenerator;
import kotlin.Metadata;

/* JADX INFO: compiled from: ECDH.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000 \t2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0004\t\n\u000b\fR\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDH;", "Ldev/whyoleg/cryptography/algorithms/EC;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Companion", "KeyPair", "PublicKey", "PrivateKey", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ECDH extends EC<PublicKey, PrivateKey, KeyPair> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ECDH.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDH$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/EC$KeyPair;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface KeyPair extends EC.KeyPair<PublicKey, PrivateKey> {
    }

    /* JADX INFO: compiled from: ECDH.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PrivateKey;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "sharedSecretGenerator", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PrivateKey extends EC.PrivateKey<PublicKey> {
        SharedSecretGenerator<PublicKey> sharedSecretGenerator();
    }

    /* JADX INFO: compiled from: ECDH.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;", "Ldev/whyoleg/cryptography/algorithms/EC$PublicKey;", "sharedSecretGenerator", "Ldev/whyoleg/cryptography/operations/SharedSecretGenerator;", "Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface PublicKey extends EC.PublicKey {
        SharedSecretGenerator<PrivateKey> sharedSecretGenerator();
    }

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<ECDH> getId();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.ECDH$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ECDH.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: ECDH.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/ECDH$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/ECDH;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<ECDH> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("ECDH");
        }
    }
}

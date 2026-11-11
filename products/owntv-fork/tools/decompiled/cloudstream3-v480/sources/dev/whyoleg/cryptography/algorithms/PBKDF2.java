package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.operations.SecretDerivation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: PBKDF2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J7\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH¦\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0013R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/PBKDF2;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "secretDerivation", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "digest", "Ldev/whyoleg/cryptography/algorithms/Digest;", "iterations", "", "outputSize", "Ldev/whyoleg/cryptography/BinarySize;", "salt", "", "secretDerivation-EcvvZ38", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;II[B)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "Lkotlinx/io/bytestring/ByteString;", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;IILkotlinx/io/bytestring/ByteString;)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface PBKDF2 extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<PBKDF2> getId();

    /* JADX INFO: renamed from: secretDerivation-EcvvZ38, reason: not valid java name */
    SecretDerivation mo8709secretDerivationEcvvZ38(CryptographyAlgorithmId<Digest> digest, int iterations, int outputSize, ByteString salt);

    /* JADX INFO: renamed from: secretDerivation-EcvvZ38, reason: not valid java name */
    SecretDerivation mo8710secretDerivationEcvvZ38(CryptographyAlgorithmId<Digest> digest, int iterations, int outputSize, byte[] salt);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.PBKDF2$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: PBKDF2.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$secretDerivation-EcvvZ38, reason: not valid java name */
        public static SecretDerivation m8711$default$secretDerivationEcvvZ38(PBKDF2 _this, CryptographyAlgorithmId digest, int i, int i2, ByteString salt) {
            Intrinsics.checkNotNullParameter(digest, "digest");
            Intrinsics.checkNotNullParameter(salt, "salt");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.mo8710secretDerivationEcvvZ38((CryptographyAlgorithmId<Digest>) digest, i, i2, salt.getData());
        }
    }

    /* JADX INFO: compiled from: PBKDF2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/PBKDF2$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/PBKDF2;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<PBKDF2> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("PBKDF2");
        }
    }
}

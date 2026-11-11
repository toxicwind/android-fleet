package dev.whyoleg.cryptography.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithm;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.operations.SecretDerivation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: HKDF.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J=\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH¦\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010J=\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0096\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0012R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HKDF;", "Ldev/whyoleg/cryptography/CryptographyAlgorithm;", "id", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "getId", "()Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "secretDerivation", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "digest", "Ldev/whyoleg/cryptography/algorithms/Digest;", "outputSize", "Ldev/whyoleg/cryptography/BinarySize;", "salt", "", "info", "secretDerivation-nkIq3jI", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;I[B[B)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "Lkotlinx/io/bytestring/ByteString;", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;ILkotlinx/io/bytestring/ByteString;Lkotlinx/io/bytestring/ByteString;)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface HKDF extends CryptographyAlgorithm {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Override // dev.whyoleg.cryptography.CryptographyAlgorithm
    CryptographyAlgorithmId<HKDF> getId();

    /* JADX INFO: renamed from: secretDerivation-nkIq3jI, reason: not valid java name */
    SecretDerivation mo8704secretDerivationnkIq3jI(CryptographyAlgorithmId<Digest> digest, int outputSize, ByteString salt, ByteString info);

    /* JADX INFO: renamed from: secretDerivation-nkIq3jI, reason: not valid java name */
    SecretDerivation mo8705secretDerivationnkIq3jI(CryptographyAlgorithmId<Digest> digest, int outputSize, byte[] salt, byte[] info);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.algorithms.HKDF$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: HKDF.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = HKDF.INSTANCE;
        }

        /* JADX INFO: renamed from: secretDerivation-nkIq3jI$default, reason: not valid java name */
        public static /* synthetic */ SecretDerivation m8708secretDerivationnkIq3jI$default(HKDF hkdf, CryptographyAlgorithmId cryptographyAlgorithmId, int i, byte[] bArr, byte[] bArr2, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: secretDerivation-nkIq3jI");
            }
            if ((i2 & 8) != 0) {
                bArr2 = null;
            }
            return hkdf.mo8705secretDerivationnkIq3jI((CryptographyAlgorithmId<Digest>) cryptographyAlgorithmId, i, bArr, bArr2);
        }

        /* JADX INFO: renamed from: secretDerivation-nkIq3jI$default, reason: not valid java name */
        public static /* synthetic */ SecretDerivation m8707secretDerivationnkIq3jI$default(HKDF hkdf, CryptographyAlgorithmId cryptographyAlgorithmId, int i, ByteString byteString, ByteString byteString2, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: secretDerivation-nkIq3jI");
            }
            if ((i2 & 8) != 0) {
                byteString2 = null;
            }
            return hkdf.mo8704secretDerivationnkIq3jI((CryptographyAlgorithmId<Digest>) cryptographyAlgorithmId, i, byteString, byteString2);
        }

        /* JADX INFO: renamed from: $default$secretDerivation-nkIq3jI, reason: not valid java name */
        public static SecretDerivation m8706$default$secretDerivationnkIq3jI(HKDF _this, CryptographyAlgorithmId digest, int i, ByteString byteString, ByteString byteString2) {
            byte[] data;
            Intrinsics.checkNotNullParameter(digest, "digest");
            byte[] data2 = null;
            if (byteString != null) {
                UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
                data = byteString.getData();
            } else {
                data = null;
            }
            if (byteString2 != null) {
                UnsafeByteStringOperations unsafeByteStringOperations2 = UnsafeByteStringOperations.INSTANCE;
                data2 = byteString2.getData();
            }
            return _this.mo8705secretDerivationnkIq3jI((CryptographyAlgorithmId<Digest>) digest, i, data, data2);
        }
    }

    /* JADX INFO: compiled from: HKDF.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/HKDF$Companion;", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/HKDF;", "<init>", "()V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyAlgorithmId<HKDF> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("HKDF");
        }
    }
}

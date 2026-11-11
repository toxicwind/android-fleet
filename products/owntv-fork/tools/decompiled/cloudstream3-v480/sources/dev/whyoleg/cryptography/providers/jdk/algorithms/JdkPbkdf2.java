package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.PBKDF2;
import dev.whyoleg.cryptography.operations.SecretDerivation;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyStateKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkPbkdf2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J7\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkPbkdf2;", "Ldev/whyoleg/cryptography/algorithms/PBKDF2;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "secretDerivation", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "iterations", "", "outputSize", "Ldev/whyoleg/cryptography/BinarySize;", "salt", "", "secretDerivation-EcvvZ38", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;II[B)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkPbkdf2 implements PBKDF2 {
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.algorithms.PBKDF2, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return PBKDF2.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.algorithms.PBKDF2
    /* JADX INFO: renamed from: secretDerivation-EcvvZ38 */
    public /* synthetic */ SecretDerivation mo8709secretDerivationEcvvZ38(CryptographyAlgorithmId cryptographyAlgorithmId, int i, int i2, ByteString byteString) {
        return PBKDF2.CC.m8711$default$secretDerivationEcvvZ38(this, cryptographyAlgorithmId, i, i2, byteString);
    }

    public JdkPbkdf2(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.algorithms.PBKDF2
    /* JADX INFO: renamed from: secretDerivation-EcvvZ38 */
    public SecretDerivation mo8710secretDerivationEcvvZ38(CryptographyAlgorithmId<Digest> digest, int iterations, int outputSize, byte[] salt) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        Intrinsics.checkNotNullParameter(salt, "salt");
        return new JdkPbkdf2SecretDerivation(this.state, "PBKDF2WithHmac" + JdkCryptographyStateKt.hashAlgorithmName(digest), salt, iterations, BinarySize.m8660getInBitsimpl(outputSize));
    }
}

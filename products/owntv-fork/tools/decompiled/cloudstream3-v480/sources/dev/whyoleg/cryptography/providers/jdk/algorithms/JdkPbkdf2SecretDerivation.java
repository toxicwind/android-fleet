package dev.whyoleg.cryptography.providers.jdk.algorithms;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.operations.SecretDerivation;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkPbkdf2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B1\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\u0096\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkPbkdf2SecretDerivation;", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithm", "", "salt", "", "iterations", "", "outputSizeBits", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/lang/String;[BII)V", "factory", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/SecretKeyFactory;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKeyFactory;", "deriveSecretToByteArrayBlocking", TvContractCompat.PARAM_INPUT, "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkPbkdf2SecretDerivation implements SecretDerivation {
    private final Pooled<SecretKeyFactory> factory;
    private final int iterations;
    private final int outputSizeBits;
    private final byte[] salt;

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ Object deriveSecret(ByteString byteString, Continuation continuation) {
        return SecretDerivation.CC.deriveSecret$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ Object deriveSecret(byte[] bArr, Continuation continuation) {
        return SecretDerivation.CC.deriveSecret$suspendImpl(this, bArr, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ ByteString deriveSecretBlocking(ByteString byteString) {
        return SecretDerivation.CC.$default$deriveSecretBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ ByteString deriveSecretBlocking(byte[] bArr) {
        return SecretDerivation.CC.$default$deriveSecretBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ Object deriveSecretToByteArray(ByteString byteString, Continuation continuation) {
        return SecretDerivation.CC.deriveSecretToByteArray$suspendImpl(this, byteString, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ Object deriveSecretToByteArray(byte[] bArr, Continuation continuation) {
        return SecretDerivation.CC.deriveSecretToByteArray$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public /* synthetic */ byte[] deriveSecretToByteArrayBlocking(ByteString byteString) {
        return SecretDerivation.CC.$default$deriveSecretToByteArrayBlocking(this, byteString);
    }

    public JdkPbkdf2SecretDerivation(JdkCryptographyState state, String algorithm, byte[] salt, int i, int i2) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(salt, "salt");
        this.salt = salt;
        this.iterations = i;
        this.outputSizeBits = i2;
        this.factory = state.secretKeyFactory(algorithm);
    }

    @Override // dev.whyoleg.cryptography.operations.SecretDerivation
    public byte[] deriveSecretToByteArrayBlocking(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        char[] charArray = StringsKt.decodeToString$default(input, 0, 0, true, 3, null).toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        PBEKeySpec pBEKeySpec = new PBEKeySpec(charArray, this.salt, this.iterations, this.outputSizeBits);
        Pooled<SecretKeyFactory> pooled = this.factory;
        Object objBorrow = pooled.borrow();
        try {
            byte[] encoded = ((SecretKeyFactory) objBorrow).generateSecret(pBEKeySpec).getEncoded();
            pooled.recycle(objBorrow);
            Intrinsics.checkNotNullExpressionValue(encoded, "use(...)");
            return encoded;
        } catch (Throwable th) {
            pooled.recycle(objBorrow);
            throw th;
        }
    }
}

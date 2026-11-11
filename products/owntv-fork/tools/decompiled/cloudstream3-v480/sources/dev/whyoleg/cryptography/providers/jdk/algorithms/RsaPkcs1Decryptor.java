package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaPkcs1Decryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseDecryptor;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createDecryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class RsaPkcs1Decryptor implements BaseDecryptor {
    private final Pooled<Cipher> cipher;
    private final PrivateKey key;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(ByteString byteString, Continuation continuation) {
        return Decryptor.CC.decrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(byte[] bArr, Continuation continuation) {
        return Decryptor.CC.decrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString) {
        return Decryptor.CC.$default$decryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr) {
        return BaseDecryptor.CC.$default$decryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

    public RsaPkcs1Decryptor(JdkCryptographyState state, PrivateKey key) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        this.state = state;
        this.key = key;
        this.cipher = JdkCryptographyState.cipher$default(state, "RSA/ECB/PKCS1Padding", false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public CipherFunction createDecryptFunction() throws InvalidKeyException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(2, this.key, this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }
}

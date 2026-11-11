package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaPkcs1Encryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseEncryptor;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PublicKey;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class RsaPkcs1Encryptor implements BaseEncryptor {
    private final Pooled<Cipher> cipher;
    private final PublicKey key;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(ByteString byteString, Continuation continuation) {
        return Encryptor.CC.encrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(byte[] bArr, Continuation continuation) {
        return Encryptor.CC.encrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ ByteString encryptBlocking(ByteString byteString) {
        return Encryptor.CC.$default$encryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr) {
        return BaseEncryptor.CC.$default$encryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink) {
        return BaseEncryptor.CC.$default$encryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource) {
        return BaseEncryptor.CC.$default$encryptingSource(this, rawSource);
    }

    public RsaPkcs1Encryptor(JdkCryptographyState state, PublicKey key) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        this.state = state;
        this.key = key;
        this.cipher = JdkCryptographyState.cipher$default(state, "RSA/ECB/PKCS1Padding", false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    public CipherFunction createEncryptFunction() throws InvalidKeyException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(1, this.key, this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }
}

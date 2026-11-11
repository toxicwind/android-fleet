package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseCipher;
import dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkAesEcb.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0010H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesEcbCipher;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseCipher;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljavax/crypto/SecretKey;Ljava/lang/String;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "createDecryptFunction", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkAesEcbCipher implements BaseCipher {
    private final Pooled<Cipher> cipher;
    private final SecretKey key;
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

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr) {
        return BaseDecryptor.CC.$default$decryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

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

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr) {
        return BaseEncryptor.CC.$default$encryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink) {
        return BaseEncryptor.CC.$default$encryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource) {
        return BaseEncryptor.CC.$default$encryptingSource(this, rawSource);
    }

    public JdkAesEcbCipher(JdkCryptographyState state, SecretKey key, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.key = key;
        this.cipher = JdkCryptographyState.cipher$default(state, algorithm, false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    public CipherFunction createEncryptFunction() throws InvalidKeyException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(1, this.key, this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public CipherFunction createDecryptFunction() throws InvalidKeyException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(2, this.key, this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }
}

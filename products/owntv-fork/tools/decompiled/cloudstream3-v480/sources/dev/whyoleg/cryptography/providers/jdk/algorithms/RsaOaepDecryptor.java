package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.AuthenticatedDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkRsaOaep.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0002\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaOaepDecryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedDecryptor;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "hashAlgorithmName", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;Ljava/lang/String;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createDecryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "associatedData", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class RsaOaepDecryptor implements BaseAuthenticatedDecryptor {
    private final Pooled<Cipher> cipher;
    private final String hashAlgorithmName;
    private final PrivateKey key;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public /* synthetic */ CipherFunction createDecryptFunction() {
        return createDecryptFunction(null);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(ByteString byteString, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ Object decrypt(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(byte[] bArr, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ Object decrypt(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString, ByteString byteString2) {
        return AuthenticatedDecryptor.CC.$default$decryptBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr, byte[] bArr2) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink, ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink, byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource, ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor, dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource, byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource, bArr);
    }

    public RsaOaepDecryptor(JdkCryptographyState state, PrivateKey key, String hashAlgorithmName) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(hashAlgorithmName, "hashAlgorithmName");
        this.state = state;
        this.key = key;
        this.hashAlgorithmName = hashAlgorithmName;
        this.cipher = JdkCryptographyState.cipher$default(state, "RSA/ECB/OAEPPadding", false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor
    public CipherFunction createDecryptFunction(byte[] associatedData) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(2, this.key, new OAEPParameterSpec(this.hashAlgorithmName, "MGF1", new MGF1ParameterSpec(this.hashAlgorithmName), associatedData != null ? new PSource.PSpecified(associatedData) : PSource.PSpecified.DEFAULT), this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }
}

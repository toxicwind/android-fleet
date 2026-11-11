package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.AuthenticatedEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
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
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0002\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaOaepEncryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedEncryptor;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "hashAlgorithmName", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PublicKey;Ljava/lang/String;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "associatedData", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class RsaOaepEncryptor implements BaseAuthenticatedEncryptor {
    private final Pooled<Cipher> cipher;
    private final String hashAlgorithmName;
    private final PublicKey key;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    public /* synthetic */ CipherFunction createEncryptFunction() {
        return createEncryptFunction(null);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(ByteString byteString, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ Object encrypt(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(byte[] bArr, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ Object encrypt(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ ByteString encryptBlocking(ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ ByteString encryptBlocking(ByteString byteString, ByteString byteString2) {
        return AuthenticatedEncryptor.CC.$default$encryptBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr, byte[] bArr2) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink, ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink, byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource, ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor, dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource, byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource, bArr);
    }

    public RsaOaepEncryptor(JdkCryptographyState state, PublicKey key, String hashAlgorithmName) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(hashAlgorithmName, "hashAlgorithmName");
        this.state = state;
        this.key = key;
        this.hashAlgorithmName = hashAlgorithmName;
        this.cipher = JdkCryptographyState.cipher$default(state, "RSA/ECB/OAEPPadding", false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor
    public CipherFunction createEncryptFunction(byte[] associatedData) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(1, this.key, new OAEPParameterSpec(this.hashAlgorithmName, "MGF1", new MGF1ParameterSpec(this.hashAlgorithmName), associatedData != null ? new PSource.PSpecified(associatedData) : PSource.PSpecified.DEFAULT), this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }
}

package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.operations.Encryptor;
import dev.whyoleg.cryptography.operations.IvDecryptor;
import dev.whyoleg.cryptography.operations.IvEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseImplicitIvDecryptFunction;
import dev.whyoleg.cryptography.providers.base.operations.BaseImplicitIvEncryptFunction;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvCipher;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkAesIvCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B-\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\bH\u0082\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesIvCipher;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseIvCipher;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "ivSize", "", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljavax/crypto/SecretKey;ILjava/lang/String;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "createDecryptFunction", "createEncryptFunctionWithIv", "iv", "", "createDecryptFunctionWithIv", "startIndex", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkAesIvCipher implements BaseIvCipher {
    private final Pooled<Cipher> cipher;
    private final int ivSize;
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

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ Object decryptWithIv(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return IvDecryptor.CC.decryptWithIv$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ Object decryptWithIv(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return IvDecryptor.CC.decryptWithIv$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ ByteString decryptWithIvBlocking(ByteString byteString, ByteString byteString2) {
        return IvDecryptor.CC.$default$decryptWithIvBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ byte[] decryptWithIvBlocking(byte[] bArr, byte[] bArr2) {
        return BaseIvDecryptor.CC.$default$decryptWithIvBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(ByteString byteString, RawSink rawSink) {
        return IvDecryptor.CC.$default$decryptingSinkWithIv(this, byteString, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(byte[] bArr, RawSink rawSink) {
        return BaseIvDecryptor.CC.$default$decryptingSinkWithIv(this, bArr, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(ByteString byteString, RawSource rawSource) {
        return IvDecryptor.CC.$default$decryptingSourceWithIv(this, byteString, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(byte[] bArr, RawSource rawSource) {
        return BaseIvDecryptor.CC.$default$decryptingSourceWithIv(this, bArr, rawSource);
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

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ Object encryptWithIv(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return IvEncryptor.CC.encryptWithIv$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ Object encryptWithIv(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return IvEncryptor.CC.encryptWithIv$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ ByteString encryptWithIvBlocking(ByteString byteString, ByteString byteString2) {
        return IvEncryptor.CC.$default$encryptWithIvBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ byte[] encryptWithIvBlocking(byte[] bArr, byte[] bArr2) {
        return BaseIvEncryptor.CC.$default$encryptWithIvBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink) {
        return BaseEncryptor.CC.$default$encryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(ByteString byteString, RawSink rawSink) {
        return IvEncryptor.CC.$default$encryptingSinkWithIv(this, byteString, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(byte[] bArr, RawSink rawSink) {
        return BaseIvEncryptor.CC.$default$encryptingSinkWithIv(this, bArr, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource) {
        return BaseEncryptor.CC.$default$encryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(ByteString byteString, RawSource rawSource) {
        return IvEncryptor.CC.$default$encryptingSourceWithIv(this, byteString, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(byte[] bArr, RawSource rawSource) {
        return BaseIvEncryptor.CC.$default$encryptingSourceWithIv(this, bArr, rawSource);
    }

    public JdkAesIvCipher(JdkCryptographyState state, SecretKey key, int i, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.key = key;
        this.ivSize = i;
        this.cipher = JdkCryptographyState.cipher$default(state, algorithm, false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    public CipherFunction createEncryptFunction() {
        byte[] bArr = new byte[this.ivSize];
        this.state.getSecureRandom().nextBytes(bArr);
        return new BaseImplicitIvEncryptFunction(bArr, createEncryptFunctionWithIv(bArr));
    }

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesIvCipher$createDecryptFunction$1, reason: invalid class name */
    /* JADX INFO: compiled from: JdkAesIvCipher.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<byte[], Integer, CipherFunction> {
        AnonymousClass1(Object obj) {
            super(2, obj, JdkAesIvCipher.class, "createDecryptFunctionWithIv", "createDecryptFunctionWithIv([BI)Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", 0);
        }

        public final CipherFunction invoke(byte[] p0, int i) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((JdkAesIvCipher) this.receiver).createDecryptFunctionWithIv(p0, i);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ CipherFunction invoke(byte[] bArr, Integer num) {
            return invoke(bArr, num.intValue());
        }
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public CipherFunction createDecryptFunction() {
        return new BaseImplicitIvDecryptFunction(this.ivSize, new AnonymousClass1(this));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseIvEncryptor
    public CipherFunction createEncryptFunctionWithIv(byte[] iv) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(iv, "iv");
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(1, this.key, new IvParameterSpec(iv), this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CipherFunction createDecryptFunctionWithIv(byte[] iv, int startIndex) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(2, this.key, new IvParameterSpec(iv, startIndex, this.ivSize), this.state.getSecureRandom());
        return new JdkCipherFunction(resourceBorrowResource);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseIvDecryptor
    public CipherFunction createDecryptFunctionWithIv(byte[] iv) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        return createDecryptFunctionWithIv(iv, 0);
    }
}

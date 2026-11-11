package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.operations.AuthenticatedDecryptor;
import dev.whyoleg.cryptography.operations.AuthenticatedEncryptor;
import dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor;
import dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor;
import dev.whyoleg.cryptography.operations.IvDecryptor;
import dev.whyoleg.cryptography.operations.IvEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseImplicitIvDecryptFunction;
import dev.whyoleg.cryptography.providers.base.operations.BaseImplicitIvEncryptFunction;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedCipher;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedEncryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkAesGcm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004J\u0014\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004J\u001c\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004J,\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082\u0080\u0004J\u001c\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkAesGcmCipher;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseIvAuthenticatedCipher;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "tagSizeBits", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljavax/crypto/SecretKey;I)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "associatedData", "", "createDecryptFunction", "createEncryptFunctionWithIv", "iv", "createDecryptFunctionWithIv", "startIndex", "ivSize", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkAesGcmCipher implements BaseIvAuthenticatedCipher {
    private final Pooled<Cipher> cipher;
    private final SecretKey key;
    private final JdkCryptographyState state;
    private final int tagSizeBits;

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public /* synthetic */ CipherFunction createDecryptFunction() {
        return createDecryptFunction(null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    public /* synthetic */ CipherFunction createEncryptFunction() {
        return createEncryptFunction(null);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(ByteString byteString, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ Object decrypt(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(byte[] bArr, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ Object decrypt(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return AuthenticatedDecryptor.CC.decrypt$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString, ByteString byteString2) {
        return AuthenticatedDecryptor.CC.$default$decryptBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr, byte[] bArr2) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ Object decryptWithIv(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return IvAuthenticatedDecryptor.CC.decryptWithIv$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ Object decryptWithIv(ByteString byteString, ByteString byteString2, ByteString byteString3, Continuation continuation) {
        return IvAuthenticatedDecryptor.CC.decryptWithIv$suspendImpl(this, byteString, byteString2, byteString3, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ Object decryptWithIv(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return IvAuthenticatedDecryptor.CC.decryptWithIv$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ Object decryptWithIv(byte[] bArr, byte[] bArr2, byte[] bArr3, Continuation continuation) {
        return IvAuthenticatedDecryptor.CC.decryptWithIv$suspendImpl(this, bArr, bArr2, bArr3, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ ByteString decryptWithIvBlocking(ByteString byteString, ByteString byteString2) {
        return IvAuthenticatedDecryptor.CC.$default$decryptWithIvBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ ByteString decryptWithIvBlocking(ByteString byteString, ByteString byteString2, ByteString byteString3) {
        return IvAuthenticatedDecryptor.CC.$default$decryptWithIvBlocking(this, byteString, byteString2, byteString3);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ byte[] decryptWithIvBlocking(byte[] bArr, byte[] bArr2) {
        return IvAuthenticatedDecryptor.CC.$default$decryptWithIvBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ byte[] decryptWithIvBlocking(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return BaseIvAuthenticatedDecryptor.CC.$default$decryptWithIvBlocking(this, bArr, bArr2, bArr3);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink, ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink, byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSink(this, rawSink, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(ByteString byteString, RawSink rawSink) {
        return IvDecryptor.CC.$default$decryptingSinkWithIv(this, byteString, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(ByteString byteString, RawSink rawSink, ByteString byteString2) {
        return IvAuthenticatedDecryptor.CC.$default$decryptingSinkWithIv(this, byteString, rawSink, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(byte[] bArr, RawSink rawSink) {
        return IvAuthenticatedDecryptor.CC.$default$decryptingSinkWithIv(this, bArr, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ RawSink decryptingSinkWithIv(byte[] bArr, RawSink rawSink, byte[] bArr2) {
        return BaseIvAuthenticatedDecryptor.CC.$default$decryptingSinkWithIv(this, bArr, rawSink, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource, ByteString byteString) {
        return AuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource, byte[] bArr) {
        return BaseAuthenticatedDecryptor.CC.$default$decryptingSource(this, rawSource, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(ByteString byteString, RawSource rawSource) {
        return IvDecryptor.CC.$default$decryptingSourceWithIv(this, byteString, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(ByteString byteString, RawSource rawSource, ByteString byteString2) {
        return IvAuthenticatedDecryptor.CC.$default$decryptingSourceWithIv(this, byteString, rawSource, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(byte[] bArr, RawSource rawSource) {
        return IvAuthenticatedDecryptor.CC.$default$decryptingSourceWithIv(this, bArr, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    public /* synthetic */ RawSource decryptingSourceWithIv(byte[] bArr, RawSource rawSource, byte[] bArr2) {
        return BaseIvAuthenticatedDecryptor.CC.$default$decryptingSourceWithIv(this, bArr, rawSource, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(ByteString byteString, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ Object encrypt(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ Object encrypt(byte[] bArr, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ Object encrypt(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return AuthenticatedEncryptor.CC.encrypt$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ ByteString encryptBlocking(ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ ByteString encryptBlocking(ByteString byteString, ByteString byteString2) {
        return AuthenticatedEncryptor.CC.$default$encryptBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ byte[] encryptBlocking(byte[] bArr, byte[] bArr2) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ Object encryptWithIv(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return IvAuthenticatedEncryptor.CC.encryptWithIv$suspendImpl(this, byteString, byteString2, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ Object encryptWithIv(ByteString byteString, ByteString byteString2, ByteString byteString3, Continuation continuation) {
        return IvAuthenticatedEncryptor.CC.encryptWithIv$suspendImpl(this, byteString, byteString2, byteString3, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ Object encryptWithIv(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return IvAuthenticatedEncryptor.CC.encryptWithIv$suspendImpl(this, bArr, bArr2, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ Object encryptWithIv(byte[] bArr, byte[] bArr2, byte[] bArr3, Continuation continuation) {
        return IvAuthenticatedEncryptor.CC.encryptWithIv$suspendImpl(this, bArr, bArr2, bArr3, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ ByteString encryptWithIvBlocking(ByteString byteString, ByteString byteString2) {
        return IvAuthenticatedEncryptor.CC.$default$encryptWithIvBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ ByteString encryptWithIvBlocking(ByteString byteString, ByteString byteString2, ByteString byteString3) {
        return IvAuthenticatedEncryptor.CC.$default$encryptWithIvBlocking(this, byteString, byteString2, byteString3);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ byte[] encryptWithIvBlocking(byte[] bArr, byte[] bArr2) {
        return IvAuthenticatedEncryptor.CC.$default$encryptWithIvBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ byte[] encryptWithIvBlocking(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return BaseIvAuthenticatedEncryptor.CC.$default$encryptWithIvBlocking(this, bArr, bArr2, bArr3);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink, ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSink(RawSink rawSink, byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSink(this, rawSink, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(ByteString byteString, RawSink rawSink) {
        return IvEncryptor.CC.$default$encryptingSinkWithIv(this, byteString, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(ByteString byteString, RawSink rawSink, ByteString byteString2) {
        return IvAuthenticatedEncryptor.CC.$default$encryptingSinkWithIv(this, byteString, rawSink, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(byte[] bArr, RawSink rawSink) {
        return IvAuthenticatedEncryptor.CC.$default$encryptingSinkWithIv(this, bArr, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ RawSink encryptingSinkWithIv(byte[] bArr, RawSink rawSink, byte[] bArr2) {
        return BaseIvAuthenticatedEncryptor.CC.$default$encryptingSinkWithIv(this, bArr, rawSink, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource, ByteString byteString) {
        return AuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSource(RawSource rawSource, byte[] bArr) {
        return BaseAuthenticatedEncryptor.CC.$default$encryptingSource(this, rawSource, bArr);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(ByteString byteString, RawSource rawSource) {
        return IvEncryptor.CC.$default$encryptingSourceWithIv(this, byteString, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(ByteString byteString, RawSource rawSource, ByteString byteString2) {
        return IvAuthenticatedEncryptor.CC.$default$encryptingSourceWithIv(this, byteString, rawSource, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(byte[] bArr, RawSource rawSource) {
        return IvAuthenticatedEncryptor.CC.$default$encryptingSourceWithIv(this, bArr, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    public /* synthetic */ RawSource encryptingSourceWithIv(byte[] bArr, RawSource rawSource, byte[] bArr2) {
        return BaseIvAuthenticatedEncryptor.CC.$default$encryptingSourceWithIv(this, bArr, rawSource, bArr2);
    }

    public JdkAesGcmCipher(JdkCryptographyState state, SecretKey key, int i) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        this.state = state;
        this.key = key;
        this.tagSizeBits = i;
        this.cipher = JdkCryptographyState.cipher$default(state, "AES/GCM/NoPadding", false, 2, null);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor
    public CipherFunction createEncryptFunction(byte[] associatedData) {
        byte[] bArr = new byte[12];
        this.state.getSecureRandom().nextBytes(bArr);
        return new BaseImplicitIvEncryptFunction(bArr, createEncryptFunctionWithIv(bArr, associatedData));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor
    public CipherFunction createDecryptFunction(final byte[] associatedData) {
        return new BaseImplicitIvDecryptFunction(12, new Function2() { // from class: dev.whyoleg.cryptography.providers.jdk.algorithms.JdkAesGcmCipher$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return JdkAesGcmCipher.createDecryptFunction$lambda$0(this.f$0, associatedData, (byte[]) obj, ((Integer) obj2).intValue());
            }
        });
    }

    static final CipherFunction createDecryptFunction$lambda$0(JdkAesGcmCipher jdkAesGcmCipher, byte[] bArr, byte[] iv, int i) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        return jdkAesGcmCipher.createDecryptFunctionWithIv(iv, i, 12, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedEncryptor
    public CipherFunction createEncryptFunctionWithIv(byte[] iv, byte[] associatedData) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(iv, "iv");
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        Cipher cipherAccess = resourceBorrowResource.access();
        cipherAccess.init(1, this.key, new GCMParameterSpec(this.tagSizeBits, iv), this.state.getSecureRandom());
        if (associatedData != null) {
            cipherAccess.updateAAD(associatedData);
        }
        return new JdkCipherFunction(resourceBorrowResource);
    }

    private final CipherFunction createDecryptFunctionWithIv(byte[] iv, int startIndex, int ivSize, byte[] associatedData) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        Cipher cipherAccess = resourceBorrowResource.access();
        cipherAccess.init(2, this.key, new GCMParameterSpec(this.tagSizeBits, iv, startIndex, ivSize), this.state.getSecureRandom());
        if (associatedData != null) {
            cipherAccess.updateAAD(associatedData);
        }
        return new JdkCipherFunction(resourceBorrowResource);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedDecryptor
    public CipherFunction createDecryptFunctionWithIv(byte[] iv, byte[] associatedData) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        return createDecryptFunctionWithIv(iv, 0, iv.length, associatedData);
    }
}

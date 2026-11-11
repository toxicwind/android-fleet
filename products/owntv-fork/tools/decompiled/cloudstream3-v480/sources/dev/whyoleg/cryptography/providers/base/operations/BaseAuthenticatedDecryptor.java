package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.AuthenticatedDecryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseAuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H¦\u0080\u0004J\n\u0010\u0003\u001a\u00020\u0004H\u0096\u0080\u0004J\u0012\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0096\u0080\u0004J\u0012\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0096\u0080\u0004J\u001c\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseDecryptor;", "createDecryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "associatedData", "", "decryptBlocking", "ciphertext", "decryptingSource", "Lkotlinx/io/RawSource;", "decryptingSink", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseAuthenticatedDecryptor extends AuthenticatedDecryptor, BaseDecryptor {
    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    CipherFunction createDecryptFunction();

    CipherFunction createDecryptFunction(byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    byte[] decryptBlocking(byte[] ciphertext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    byte[] decryptBlocking(byte[] ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    RawSink decryptingSink(RawSink plaintext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    RawSink decryptingSink(RawSink plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    RawSource decryptingSource(RawSource ciphertext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedDecryptor
    RawSource decryptingSource(RawSource ciphertext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedDecryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseAuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        public static byte[] $default$decryptBlocking(BaseAuthenticatedDecryptor _this, byte[] ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return AuthenticatedDecryptor.CC.$default$decryptBlocking(_this, ciphertext);
        }

        public static RawSource $default$decryptingSource(BaseAuthenticatedDecryptor _this, RawSource ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return AuthenticatedDecryptor.CC.$default$decryptingSource(_this, ciphertext);
        }

        public static RawSink $default$decryptingSink(BaseAuthenticatedDecryptor _this, RawSink plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return AuthenticatedDecryptor.CC.$default$decryptingSink(_this, plaintext);
        }

        public static byte[] $default$decryptBlocking(BaseAuthenticatedDecryptor _this, byte[] ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return CipherFunction.CC.transform$default(_this.createDecryptFunction(bArr), ciphertext, 0, 0, 6, null);
        }

        public static RawSource $default$decryptingSource(BaseAuthenticatedDecryptor _this, RawSource ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createDecryptFunction(bArr).transformedSource(ciphertext);
        }

        public static RawSink $default$decryptingSink(BaseAuthenticatedDecryptor _this, RawSink plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createDecryptFunction(bArr).transformedSink(plaintext);
        }
    }
}

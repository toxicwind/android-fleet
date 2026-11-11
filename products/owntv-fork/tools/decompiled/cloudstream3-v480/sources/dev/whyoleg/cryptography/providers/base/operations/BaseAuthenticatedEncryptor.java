package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.AuthenticatedEncryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseAuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H¦\u0080\u0004J\n\u0010\u0003\u001a\u00020\u0004H\u0096\u0080\u0004J\u0012\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0096\u0080\u0004J\u0012\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\nH\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0096\u0080\u0004J\u001c\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedEncryptor;", "Ldev/whyoleg/cryptography/operations/AuthenticatedEncryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseEncryptor;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "associatedData", "", "encryptBlocking", "plaintext", "encryptingSource", "Lkotlinx/io/RawSource;", "encryptingSink", "Lkotlinx/io/RawSink;", "ciphertext", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseAuthenticatedEncryptor extends AuthenticatedEncryptor, BaseEncryptor {
    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor
    CipherFunction createEncryptFunction();

    CipherFunction createEncryptFunction(byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    byte[] encryptBlocking(byte[] plaintext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    byte[] encryptBlocking(byte[] plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    RawSink encryptingSink(RawSink ciphertext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    RawSink encryptingSink(RawSink ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor, dev.whyoleg.cryptography.operations.Encryptor
    RawSource encryptingSource(RawSource plaintext);

    @Override // dev.whyoleg.cryptography.operations.AuthenticatedEncryptor
    RawSource encryptingSource(RawSource plaintext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseAuthenticatedEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseAuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        public static byte[] $default$encryptBlocking(BaseAuthenticatedEncryptor _this, byte[] plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return AuthenticatedEncryptor.CC.$default$encryptBlocking(_this, plaintext);
        }

        public static RawSource $default$encryptingSource(BaseAuthenticatedEncryptor _this, RawSource plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return AuthenticatedEncryptor.CC.$default$encryptingSource(_this, plaintext);
        }

        public static RawSink $default$encryptingSink(BaseAuthenticatedEncryptor _this, RawSink ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return AuthenticatedEncryptor.CC.$default$encryptingSink(_this, ciphertext);
        }

        public static byte[] $default$encryptBlocking(BaseAuthenticatedEncryptor _this, byte[] plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return CipherFunction.CC.transform$default(_this.createEncryptFunction(bArr), plaintext, 0, 0, 6, null);
        }

        public static RawSource $default$encryptingSource(BaseAuthenticatedEncryptor _this, RawSource plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createEncryptFunction(bArr).transformedSource(plaintext);
        }

        public static RawSink $default$encryptingSink(BaseAuthenticatedEncryptor _this, RawSink ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createEncryptFunction(bArr).transformedSink(ciphertext);
        }
    }
}

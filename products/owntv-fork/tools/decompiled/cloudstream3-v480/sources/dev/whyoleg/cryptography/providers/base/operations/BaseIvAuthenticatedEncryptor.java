package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.DelicateCryptographyApi;
import dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseIvAuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H¦\u0080\u0004J$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004J$\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseIvAuthenticatedEncryptor;", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedEncryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedEncryptor;", "createEncryptFunctionWithIv", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "iv", "", "associatedData", "encryptWithIvBlocking", "plaintext", "encryptingSourceWithIv", "Lkotlinx/io/RawSource;", "encryptingSinkWithIv", "Lkotlinx/io/RawSink;", "ciphertext", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseIvAuthenticatedEncryptor extends IvAuthenticatedEncryptor, BaseAuthenticatedEncryptor {
    CipherFunction createEncryptFunctionWithIv(byte[] iv, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    @DelicateCryptographyApi
    byte[] encryptWithIvBlocking(byte[] iv, byte[] plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    @DelicateCryptographyApi
    RawSink encryptingSinkWithIv(byte[] iv, RawSink ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedEncryptor
    @DelicateCryptographyApi
    RawSource encryptingSourceWithIv(byte[] iv, RawSource plaintext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseIvAuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static byte[] $default$encryptWithIvBlocking(BaseIvAuthenticatedEncryptor _this, byte[] iv, byte[] plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return CipherFunction.CC.transform$default(_this.createEncryptFunctionWithIv(iv, bArr), plaintext, 0, 0, 6, null);
        }

        @DelicateCryptographyApi
        public static RawSource $default$encryptingSourceWithIv(BaseIvAuthenticatedEncryptor _this, byte[] iv, RawSource plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createEncryptFunctionWithIv(iv, bArr).transformedSource(plaintext);
        }

        @DelicateCryptographyApi
        public static RawSink $default$encryptingSinkWithIv(BaseIvAuthenticatedEncryptor _this, byte[] iv, RawSink ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createEncryptFunctionWithIv(iv, bArr).transformedSink(ciphertext);
        }
    }
}

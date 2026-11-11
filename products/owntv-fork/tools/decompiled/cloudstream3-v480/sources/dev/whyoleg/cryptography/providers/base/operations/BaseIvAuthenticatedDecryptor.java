package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.DelicateCryptographyApi;
import dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseIvAuthenticatedCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H¦\u0080\u0004J$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004J$\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseIvAuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedDecryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseAuthenticatedDecryptor;", "createDecryptFunctionWithIv", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "iv", "", "associatedData", "decryptWithIvBlocking", "ciphertext", "decryptingSourceWithIv", "Lkotlinx/io/RawSource;", "decryptingSinkWithIv", "Lkotlinx/io/RawSink;", "plaintext", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseIvAuthenticatedDecryptor extends IvAuthenticatedDecryptor, BaseAuthenticatedDecryptor {
    CipherFunction createDecryptFunctionWithIv(byte[] iv, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    @DelicateCryptographyApi
    byte[] decryptWithIvBlocking(byte[] iv, byte[] ciphertext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    @DelicateCryptographyApi
    RawSink decryptingSinkWithIv(byte[] iv, RawSink plaintext, byte[] associatedData);

    @Override // dev.whyoleg.cryptography.operations.IvAuthenticatedDecryptor
    @DelicateCryptographyApi
    RawSource decryptingSourceWithIv(byte[] iv, RawSource ciphertext, byte[] associatedData);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseIvAuthenticatedDecryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseIvAuthenticatedCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static byte[] $default$decryptWithIvBlocking(BaseIvAuthenticatedDecryptor _this, byte[] iv, byte[] ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return CipherFunction.CC.transform$default(_this.createDecryptFunctionWithIv(iv, bArr), ciphertext, 0, 0, 6, null);
        }

        @DelicateCryptographyApi
        public static RawSource $default$decryptingSourceWithIv(BaseIvAuthenticatedDecryptor _this, byte[] iv, RawSource ciphertext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createDecryptFunctionWithIv(iv, bArr).transformedSource(ciphertext);
        }

        @DelicateCryptographyApi
        public static RawSink $default$decryptingSinkWithIv(BaseIvAuthenticatedDecryptor _this, byte[] iv, RawSink plaintext, byte[] bArr) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createDecryptFunctionWithIv(iv, bArr).transformedSink(plaintext);
        }
    }
}

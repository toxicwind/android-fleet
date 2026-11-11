package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.DelicateCryptographyApi;
import dev.whyoleg.cryptography.operations.IvEncryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseIvCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦\u0080\u0004J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0097\u0080\u0004J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0097\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\rH\u0097\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseIvEncryptor;", "Ldev/whyoleg/cryptography/operations/IvEncryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseEncryptor;", "createEncryptFunctionWithIv", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "iv", "", "encryptWithIvBlocking", "plaintext", "encryptingSinkWithIv", "Lkotlinx/io/RawSink;", "ciphertext", "encryptingSourceWithIv", "Lkotlinx/io/RawSource;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseIvEncryptor extends IvEncryptor, BaseEncryptor {
    CipherFunction createEncryptFunctionWithIv(byte[] iv);

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    @DelicateCryptographyApi
    byte[] encryptWithIvBlocking(byte[] iv, byte[] plaintext);

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    @DelicateCryptographyApi
    RawSink encryptingSinkWithIv(byte[] iv, RawSink ciphertext);

    @Override // dev.whyoleg.cryptography.operations.IvEncryptor
    @DelicateCryptographyApi
    RawSource encryptingSourceWithIv(byte[] iv, RawSource plaintext);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseIvEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseIvCipher.kt */
    public final /* synthetic */ class CC {
        @DelicateCryptographyApi
        public static byte[] $default$encryptWithIvBlocking(BaseIvEncryptor _this, byte[] iv, byte[] plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return CipherFunction.CC.transform$default(_this.createEncryptFunctionWithIv(iv), plaintext, 0, 0, 6, null);
        }

        @DelicateCryptographyApi
        public static RawSink $default$encryptingSinkWithIv(BaseIvEncryptor _this, byte[] iv, RawSink ciphertext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createEncryptFunctionWithIv(iv).transformedSink(ciphertext);
        }

        @DelicateCryptographyApi
        public static RawSource $default$encryptingSourceWithIv(BaseIvEncryptor _this, byte[] iv, RawSource plaintext) {
            Intrinsics.checkNotNullParameter(iv, "iv");
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createEncryptFunctionWithIv(iv).transformedSource(plaintext);
        }
    }
}

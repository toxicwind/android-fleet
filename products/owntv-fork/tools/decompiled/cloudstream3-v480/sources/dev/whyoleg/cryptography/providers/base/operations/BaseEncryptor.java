package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.operations.Encryptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: BaseCipher.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0096\u0080\u0004J\u0012\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseEncryptor;", "Ldev/whyoleg/cryptography/operations/Encryptor;", "createEncryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "encryptBlocking", "", "plaintext", "encryptingSource", "Lkotlinx/io/RawSource;", "encryptingSink", "Lkotlinx/io/RawSink;", "ciphertext", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface BaseEncryptor extends Encryptor {
    CipherFunction createEncryptFunction();

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    byte[] encryptBlocking(byte[] plaintext);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    RawSink encryptingSink(RawSink ciphertext);

    @Override // dev.whyoleg.cryptography.operations.Encryptor
    RawSource encryptingSource(RawSource plaintext);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.providers.base.operations.BaseEncryptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: BaseCipher.kt */
    public final /* synthetic */ class CC {
        public static byte[] $default$encryptBlocking(BaseEncryptor _this, byte[] plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return CipherFunction.CC.transform$default(_this.createEncryptFunction(), plaintext, 0, 0, 6, null);
        }

        public static RawSource $default$encryptingSource(BaseEncryptor _this, RawSource plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return _this.createEncryptFunction().transformedSource(plaintext);
        }

        public static RawSink $default$encryptingSink(BaseEncryptor _this, RawSink ciphertext) {
            Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
            return _this.createEncryptFunction().transformedSink(ciphertext);
        }
    }
}

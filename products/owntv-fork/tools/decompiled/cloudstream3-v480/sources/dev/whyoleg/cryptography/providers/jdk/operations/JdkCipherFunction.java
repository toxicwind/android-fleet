package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.providers.base.BytesKt;
import dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkCipherFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u001b\bF\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0094\u0080\u0004J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0094\u0080\u0004J2\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0094\u0080\u0004J\n\u0010\u0016\u001a\u00020\u000fH\u0094\u0080\u0004J\u001a\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\tH\u0094\u0080\u0004J\"\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0094\u0080\u0004J2\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0094\u0080\u0004J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0019\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\t8TX\u0094\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkCipherFunction;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction;", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;)V", "blockSize", "", "getBlockSize", "()I", "maxOutputSize", "inputSize", "transformToByteArray", "", "source", "startIndex", "endIndex", "transformIntoByteArray", "destination", "destinationOffset", "finalizeToByteArray", "finalizeIntoByteArray", "transformAndFinalizeToByteArray", "transformAndFinalizeIntoByteArray", "close", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class JdkCipherFunction extends BaseCipherFunction {
    private final Pooled.Resource<Cipher> cipher;

    public JdkCipherFunction(Pooled.Resource<Cipher> cipher) {
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        this.cipher = cipher;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int getBlockSize() {
        return this.cipher.access().getBlockSize();
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int maxOutputSize(int inputSize) {
        return this.cipher.access().getOutputSize(inputSize);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] transformToByteArray(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        byte[] bArrUpdate = this.cipher.access().update(source, startIndex, endIndex - startIndex);
        return bArrUpdate == null ? BytesKt.getEmptyByteArray() : bArrUpdate;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int transformIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        int i = endIndex - startIndex;
        BytesKt.checkBounds(destination.length, destinationOffset, maxOutputSize(i) + destinationOffset);
        return this.cipher.access().update(source, startIndex, i, destination, destinationOffset);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] finalizeToByteArray() throws BadPaddingException, IllegalBlockSizeException {
        byte[] bArrDoFinal = this.cipher.access().doFinal();
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int finalizeIntoByteArray(byte[] destination, int destinationOffset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        BytesKt.checkBounds(destination.length, destinationOffset, maxOutputSize(0) + destinationOffset);
        return this.cipher.access().doFinal(destination, destinationOffset);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] transformAndFinalizeToByteArray(byte[] source, int startIndex, int endIndex) throws BadPaddingException, IllegalBlockSizeException {
        Intrinsics.checkNotNullParameter(source, "source");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        byte[] bArrDoFinal = this.cipher.access().doFinal(source, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int transformAndFinalizeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        int i = endIndex - startIndex;
        BytesKt.checkBounds(destination.length, destinationOffset, maxOutputSize(i) + destinationOffset);
        return this.cipher.access().doFinal(source, startIndex, i, destination, destinationOffset);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.cipher.close();
    }
}

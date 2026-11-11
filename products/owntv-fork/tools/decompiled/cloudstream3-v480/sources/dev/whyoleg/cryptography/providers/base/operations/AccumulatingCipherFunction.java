package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccumulatingCipherFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B\u001d\bF\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\u000bH\u0082\u0080\u0004J\"\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010H\u0094\u0080\u0004J\u001a\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0094\u0080\u0004J\"\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0094\u0080\u0004J2\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0094\u0080\u0004J\n\u0010\u001e\u001a\u00020\u0004H\u0094\u0080\u0004J\u001a\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0010H\u0094\u0080\u0004J\"\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0094\u0080\u0004J2\u0010!\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0094\u0080\u0004R\u001b\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\u00020\u00108TX\u0094\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/AccumulatingCipherFunction;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction;", "finalize", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "isClosed", "", "accumulator", "close", "", "ensureNotClosed", "accumulate", "source", "startIndex", "", "endIndex", "blockSize", "getBlockSize", "()I", "maxOutputSize", "inputSize", "maxInputSize", "initialMaxInputSize", "expectedMaxOutputSize", "transformToByteArray", "transformIntoByteArray", "destination", "destinationOffset", "finalizeToByteArray", "finalizeIntoByteArray", "transformAndFinalizeToByteArray", "transformAndFinalizeIntoByteArray", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccumulatingCipherFunction extends BaseCipherFunction {
    private byte[] accumulator;
    private final Function1<byte[], byte[]> finalize;
    private boolean isClosed;

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int getBlockSize() {
        return 0;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int maxInputSize(int initialMaxInputSize, int expectedMaxOutputSize) {
        return Integer.MAX_VALUE;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int maxOutputSize(int inputSize) {
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AccumulatingCipherFunction(Function1<? super byte[], byte[]> finalize) {
        Intrinsics.checkNotNullParameter(finalize, "finalize");
        this.finalize = finalize;
        this.accumulator = BytesKt.getEmptyByteArray();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.isClosed = true;
        this.accumulator = BytesKt.getEmptyByteArray();
    }

    private final void ensureNotClosed() {
        if (this.isClosed) {
            throw new IllegalStateException("Already closed".toString());
        }
    }

    private final void accumulate(byte[] source, int startIndex, int endIndex) {
        ensureNotClosed();
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        this.accumulator = ArraysKt.plus(this.accumulator, ArraysKt.copyOfRange(source, startIndex, endIndex));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] transformToByteArray(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        accumulate(source, startIndex, endIndex);
        return BytesKt.getEmptyByteArray();
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int transformIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        accumulate(source, startIndex, endIndex);
        return 0;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] finalizeToByteArray() {
        ensureNotClosed();
        return this.finalize.invoke(this.accumulator);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int finalizeIntoByteArray(byte[] destination, int destinationOffset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        byte[] bArrFinalizeToByteArray = finalizeToByteArray();
        ArraysKt.copyInto$default(bArrFinalizeToByteArray, destination, destinationOffset, 0, 0, 12, (Object) null);
        return bArrFinalizeToByteArray.length;
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected byte[] transformAndFinalizeToByteArray(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        accumulate(source, startIndex, endIndex);
        return finalizeToByteArray();
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
    protected int transformAndFinalizeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        accumulate(source, startIndex, endIndex);
        return finalizeIntoByteArray(destination, destinationOffset);
    }
}

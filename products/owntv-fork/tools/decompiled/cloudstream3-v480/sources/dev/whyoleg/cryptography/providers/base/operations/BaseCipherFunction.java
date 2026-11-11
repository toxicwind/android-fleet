package dev.whyoleg.cryptography.providers.base.operations;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import java.io.EOFException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io.Sink;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: BaseCipherFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0002)*B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H¤\u0080\u0004J\u001a\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0094\u0080\u0004J&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007H\u0094\u0080\u0004J8\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007H¤\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0010H\u0094\u0080\u0004J\u001c\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0007H¤\u0080\u0004J&\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007H\u0094\u0080\u0004J8\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007H\u0094\u0080\u0004J\"\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0096\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u001dH\u0096\u0080\u0004J\u0012\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0096\u0080\u0004J\"\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0082\u0080\u0004J\u0012\u0010'\u001a\u00020(2\u0006\u0010$\u001a\u00020#H\u0082\u0080\u0004R\u0013\u0010\u0006\u001a\u00020\u0007X¤\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006+"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction;", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "blockSize", "", "getBlockSize", "()I", "maxOutputSize", "inputSize", "maxInputSize", "initialMaxInputSize", "expectedMaxOutputSize", "transformToByteArray", "", "source", "startIndex", "endIndex", "transformIntoByteArray", "destination", "destinationOffset", "finalizeToByteArray", "finalizeIntoByteArray", "transformAndFinalizeToByteArray", "transformAndFinalizeIntoByteArray", "transform", "transformedSource", "Lkotlinx/io/RawSource;", "transformedSink", "Lkotlinx/io/RawSink;", "sink", "transformTo", "inputBuffer", "Lkotlinx/io/Buffer;", "outputBuffer", "maxInputCount", "", "finalizeTo", "", "TransformedSource", "TransformedSink", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseCipherFunction implements CipherFunction, AutoCloseable {
    protected abstract int finalizeIntoByteArray(byte[] destination, int destinationOffset);

    protected abstract int getBlockSize();

    protected abstract int maxOutputSize(int inputSize);

    protected abstract int transformIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex);

    protected int maxInputSize(int initialMaxInputSize, int expectedMaxOutputSize) {
        if (initialMaxInputSize < 0) {
            throw new IllegalStateException(("initialMaxInputSize must be >= 0, but was " + initialMaxInputSize).toString());
        }
        if (expectedMaxOutputSize < 0) {
            throw new IllegalStateException(("expectedMaxOutputSize must be >= 0, but was " + expectedMaxOutputSize).toString());
        }
        if (maxOutputSize(initialMaxInputSize) <= expectedMaxOutputSize) {
            return initialMaxInputSize;
        }
        if (maxOutputSize(0) > expectedMaxOutputSize) {
            return -1;
        }
        int blockSize = getBlockSize() > 0 ? getBlockSize() : 16;
        do {
            initialMaxInputSize -= blockSize;
            if (initialMaxInputSize <= 0) {
                return -1;
            }
        } while (maxOutputSize(initialMaxInputSize) > expectedMaxOutputSize);
        return initialMaxInputSize;
    }

    public static /* synthetic */ byte[] transformToByteArray$default(BaseCipherFunction baseCipherFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformToByteArray");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return baseCipherFunction.transformToByteArray(bArr, i, i2);
    }

    protected byte[] transformToByteArray(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        byte[] bArr = new byte[maxOutputSize(endIndex - startIndex)];
        return BytesKt.ensureSizeExactly(bArr, transformIntoByteArray(source, bArr, 0, startIndex, endIndex));
    }

    public static /* synthetic */ int transformIntoByteArray$default(BaseCipherFunction baseCipherFunction, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return baseCipherFunction.transformIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    protected byte[] finalizeToByteArray() {
        byte[] bArr = new byte[maxOutputSize(0)];
        return BytesKt.ensureSizeExactly(bArr, finalizeIntoByteArray$default(this, bArr, 0, 2, null));
    }

    public static /* synthetic */ int finalizeIntoByteArray$default(BaseCipherFunction baseCipherFunction, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finalizeIntoByteArray");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return baseCipherFunction.finalizeIntoByteArray(bArr, i);
    }

    public static /* synthetic */ byte[] transformAndFinalizeToByteArray$default(BaseCipherFunction baseCipherFunction, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformAndFinalizeToByteArray");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return baseCipherFunction.transformAndFinalizeToByteArray(bArr, i, i2);
    }

    protected byte[] transformAndFinalizeToByteArray(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        byte[] bArr = new byte[maxOutputSize(endIndex - startIndex)];
        return BytesKt.ensureSizeExactly(bArr, transformAndFinalizeIntoByteArray(source, bArr, 0, startIndex, endIndex));
    }

    public static /* synthetic */ int transformAndFinalizeIntoByteArray$default(BaseCipherFunction baseCipherFunction, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transformAndFinalizeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return baseCipherFunction.transformAndFinalizeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    protected int transformAndFinalizeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        BytesKt.checkBounds(destination.length, destinationOffset, maxOutputSize(endIndex - startIndex) + destinationOffset);
        int iTransformIntoByteArray = transformIntoByteArray(source, destination, destinationOffset, startIndex, endIndex);
        return iTransformIntoByteArray + finalizeIntoByteArray(destination, destinationOffset + iTransformIntoByteArray);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public byte[] transform(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        BaseCipherFunction baseCipherFunction = this;
        try {
            byte[] bArrTransformAndFinalizeToByteArray = transformAndFinalizeToByteArray(source, startIndex, endIndex);
            AutoCloseableKt.closeFinally(baseCipherFunction, null);
            return bArrTransformAndFinalizeToByteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(baseCipherFunction, th);
                throw th2;
            }
        }
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSource transformedSource(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new TransformedSource(this, source);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.CipherFunction
    public RawSink transformedSink(RawSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new TransformedSink(this, sink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int transformTo(Buffer inputBuffer, Buffer outputBuffer, long maxInputCount) throws EOFException {
        int i;
        int iMaxInputSize;
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        if (inputBuffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = inputBuffer.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        int iMin = (int) Math.min(maxInputCount, head.getLimit() - pos);
        int iMaxOutputSize = maxOutputSize(iMin);
        if (iMaxOutputSize == -1) {
            i = -1;
            iMaxInputSize = -1;
        } else if (iMaxOutputSize <= UnsafeBufferOperations.INSTANCE.getMaxSafeWriteCapacity()) {
            iMaxInputSize = iMin;
            i = iMaxOutputSize;
        } else {
            int maxSafeWriteCapacity = UnsafeBufferOperations.INSTANCE.getMaxSafeWriteCapacity();
            i = maxSafeWriteCapacity;
            iMaxInputSize = maxInputSize(iMin, maxSafeWriteCapacity);
        }
        if (iMaxInputSize == -1 || i == -1 || i == 0) {
            Sink.CC.write$default(outputBuffer, transformToByteArray(bArrDataAsByteArray, pos, pos + iMin), 0, 0, 6, null);
        } else {
            UnsafeBufferOperations unsafeBufferOperations2 = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment = outputBuffer.writableSegment(i);
            byte[] bArrDataAsByteArray2 = segmentWritableSegment.dataAsByteArray(false);
            int limit = segmentWritableSegment.getLimit();
            int length = bArrDataAsByteArray2.length;
            int iTransformIntoByteArray = transformIntoByteArray(bArrDataAsByteArray, bArrDataAsByteArray2, limit, pos, pos + iMaxInputSize);
            if (iTransformIntoByteArray == i) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray2, iTransformIntoByteArray);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iTransformIntoByteArray);
                outputBuffer.setSizeMut(outputBuffer.getSizeMut() + ((long) iTransformIntoByteArray));
            } else {
                if (iTransformIntoByteArray < 0 || iTransformIntoByteArray > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iTransformIntoByteArray + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iTransformIntoByteArray != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray2, iTransformIntoByteArray);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iTransformIntoByteArray);
                    outputBuffer.setSizeMut(outputBuffer.getSizeMut() + ((long) iTransformIntoByteArray));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    outputBuffer.recycleTail();
                }
            }
            iMin = iMaxInputSize;
        }
        if (iMin == 0) {
            return iMin;
        }
        if (iMin < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iMin > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        inputBuffer.skip(iMin);
        return iMin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finalizeTo(Buffer outputBuffer) {
        int iMaxOutputSize = maxOutputSize(0);
        if (iMaxOutputSize == 0) {
            return;
        }
        if (iMaxOutputSize == -1 || iMaxOutputSize > UnsafeBufferOperations.INSTANCE.getMaxSafeWriteCapacity()) {
            Sink.CC.write$default(outputBuffer, finalizeToByteArray(), 0, 0, 6, null);
            return;
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Segment segmentWritableSegment = outputBuffer.writableSegment(iMaxOutputSize);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int limit = segmentWritableSegment.getLimit();
        int length = bArrDataAsByteArray.length;
        int iFinalizeIntoByteArray = finalizeIntoByteArray(bArrDataAsByteArray, limit);
        if (iFinalizeIntoByteArray == iMaxOutputSize) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iFinalizeIntoByteArray);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iFinalizeIntoByteArray);
            outputBuffer.setSizeMut(outputBuffer.getSizeMut() + ((long) iFinalizeIntoByteArray));
            return;
        }
        if (iFinalizeIntoByteArray < 0 || iFinalizeIntoByteArray > segmentWritableSegment.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: " + iFinalizeIntoByteArray + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
        }
        if (iFinalizeIntoByteArray != 0) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iFinalizeIntoByteArray);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iFinalizeIntoByteArray);
            outputBuffer.setSizeMut(outputBuffer.getSizeMut() + ((long) iFinalizeIntoByteArray));
        } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
            outputBuffer.recycleTail();
        }
    }

    /* JADX INFO: compiled from: BaseCipherFunction.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\fH\u0096\u0080\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction$TransformedSource;", "Lkotlinx/io/RawSource;", "source", "<init>", "(Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction;Lkotlinx/io/RawSource;)V", "isFinalized", "", "isClosed", "inputBuffer", "Lkotlinx/io/Buffer;", "outputBuffer", "readAtMostTo", "", "sink", "byteCount", "close", "", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class TransformedSource implements RawSource {
        private final Buffer inputBuffer;
        private boolean isClosed;
        private boolean isFinalized;
        private final Buffer outputBuffer;
        private final RawSource source;
        final /* synthetic */ BaseCipherFunction this$0;

        public TransformedSource(BaseCipherFunction baseCipherFunction, RawSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.this$0 = baseCipherFunction;
            this.source = source;
            this.inputBuffer = new Buffer();
            this.outputBuffer = new Buffer();
        }

        @Override // kotlinx.io.RawSource
        public long readAtMostTo(Buffer sink, long byteCount) throws EOFException {
            BaseCipherFunction baseCipherFunction;
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (byteCount < 0) {
                throw new IllegalArgumentException(("byteCount[" + byteCount + "] < 0").toString());
            }
            if (this.isClosed) {
                throw new IllegalStateException("Already closed".toString());
            }
            if (byteCount == 0) {
                return 0L;
            }
            while (this.outputBuffer.getSizeMut() == 0 && !this.isFinalized) {
                if (this.source.readAtMostTo(this.inputBuffer, UnsafeBufferOperations.INSTANCE.getMaxSafeWriteCapacity()) != -1) {
                    this.this$0.transformTo(this.inputBuffer, this.outputBuffer, Long.MAX_VALUE);
                } else {
                    this.isFinalized = true;
                    while (true) {
                        long sizeMut = this.inputBuffer.getSizeMut();
                        baseCipherFunction = this.this$0;
                        if (sizeMut == 0) {
                            break;
                        }
                        baseCipherFunction.transformTo(this.inputBuffer, this.outputBuffer, Long.MAX_VALUE);
                    }
                    baseCipherFunction.finalizeTo(this.outputBuffer);
                }
            }
            return this.outputBuffer.readAtMostTo(sink, byteCount);
        }

        @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
        public void close() throws Throwable {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            this.inputBuffer.clear();
            this.outputBuffer.clear();
            this.source.close();
            th = null;
            try {
                UByte$$ExternalSyntheticBackport0.m9014m((Object) this.this$0);
            } catch (Throwable th) {
                if (th == null) {
                    th = th;
                } else {
                    ExceptionsKt.addSuppressed(th, th);
                }
            }
            if (th != null) {
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: BaseCipherFunction.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\nH\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\nH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction$TransformedSink;", "Lkotlinx/io/RawSink;", "sink", "<init>", "(Ldev/whyoleg/cryptography/providers/base/operations/BaseCipherFunction;Lkotlinx/io/RawSink;)V", "isClosed", "", "outputBuffer", "Lkotlinx/io/Buffer;", "write", "", "source", "byteCount", "", "flush", "close", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class TransformedSink implements RawSink {
        private boolean isClosed;
        private final Buffer outputBuffer;
        private final RawSink sink;
        final /* synthetic */ BaseCipherFunction this$0;

        public TransformedSink(BaseCipherFunction baseCipherFunction, RawSink sink) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            this.this$0 = baseCipherFunction;
            this.sink = sink;
            this.outputBuffer = new Buffer();
        }

        @Override // kotlinx.io.RawSink
        public void write(Buffer source, long byteCount) {
            Intrinsics.checkNotNullParameter(source, "source");
            if (byteCount < 0) {
                throw new IllegalArgumentException(("byteCount[" + byteCount + "] < 0").toString());
            }
            if (this.isClosed) {
                throw new IllegalStateException("Already closed".toString());
            }
            while (byteCount > 0) {
                byteCount -= (long) this.this$0.transformTo(source, this.outputBuffer, byteCount);
            }
            this.outputBuffer.transferTo(this.sink);
        }

        @Override // kotlinx.io.RawSink, java.io.Flushable
        public void flush() {
            this.sink.flush();
        }

        @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
        public void close() throws Throwable {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            try {
                this.this$0.finalizeTo(this.outputBuffer);
                this.outputBuffer.transferTo(this.sink);
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            this.outputBuffer.clear();
            try {
                this.sink.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    ExceptionsKt.addSuppressed(th, th2);
                }
            }
            try {
                UByte$$ExternalSyntheticBackport0.m9014m((Object) this.this$0);
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    ExceptionsKt.addSuppressed(th, th3);
                }
            }
            if (th != null) {
                throw th;
            }
        }
    }
}

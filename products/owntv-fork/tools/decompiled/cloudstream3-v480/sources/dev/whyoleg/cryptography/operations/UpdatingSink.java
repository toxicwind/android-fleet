package dev.whyoleg.cryptography.operations;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSink;
import kotlinx.io.Segment;
import kotlinx.io.unsafe.BufferIterationContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: UpdateFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\bH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\bH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/operations/UpdatingSink;", "Lkotlinx/io/RawSink;", "sink", "function", "Ldev/whyoleg/cryptography/operations/UpdateFunction;", "<init>", "(Lkotlinx/io/RawSink;Ldev/whyoleg/cryptography/operations/UpdateFunction;)V", "write", "", "source", "Lkotlinx/io/Buffer;", "byteCount", "", "flush", "close", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdatingSink implements RawSink {
    private final UpdateFunction function;
    private final RawSink sink;

    public UpdatingSink(RawSink sink, UpdateFunction function) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(function, "function");
        this.sink = sink;
        this.function = function;
    }

    @Override // kotlinx.io.RawSink
    public void write(Buffer source, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        source.require(byteCount);
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        BufferIterationContext bufferIterationContext = UnsafeBufferOperationsKt.BufferIterationContextImpl;
        long j = byteCount;
        for (Segment head = source.getHead(); head != null && j > 0; head = bufferIterationContext.next(head)) {
            byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
            int pos = head.getPos();
            int iMin = (int) Math.min(j, head.getLimit() - pos);
            this.function.update(bArrDataAsByteArray, pos, pos + iMin);
            j -= (long) iMin;
        }
        this.sink.write(source, byteCount);
    }

    @Override // kotlinx.io.RawSink, java.io.Flushable
    public void flush() {
        this.sink.flush();
    }

    @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
    public void close() {
        this.sink.close();
    }
}

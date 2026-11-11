package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSource;
import kotlinx.io.Segment;
import kotlinx.io.unsafe.BufferIterationContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: UpdateFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096\u0080\u0004J\b\u0010\f\u001a\u00020\rH\u0016R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/operations/UpdatingSource;", "Lkotlinx/io/RawSource;", "source", "function", "Ldev/whyoleg/cryptography/operations/UpdateFunction;", "<init>", "(Lkotlinx/io/RawSource;Ldev/whyoleg/cryptography/operations/UpdateFunction;)V", "readAtMostTo", "", "sink", "Lkotlinx/io/Buffer;", "byteCount", "close", "", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdatingSource implements RawSource {
    private final UpdateFunction function;
    private final RawSource source;

    public UpdatingSource(RawSource source, UpdateFunction function) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(function, "function");
        this.source = source;
        this.function = function;
    }

    @Override // kotlinx.io.RawSource
    public long readAtMostTo(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long atMostTo = this.source.readAtMostTo(sink, byteCount);
        if (atMostTo != -1) {
            long sizeMut = sink.getSizeMut() - atMostTo;
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            long j = 0;
            if (sizeMut < 0) {
                throw new IllegalArgumentException(("Offset must be non-negative: " + sizeMut).toString());
            }
            if (sizeMut >= sink.getSizeMut()) {
                throw new IndexOutOfBoundsException("Offset should be less than buffer's size (" + sink.getSizeMut() + "): " + sizeMut);
            }
            if (sink.getHead() == null) {
                BufferIterationContext bufferIterationContext = UnsafeBufferOperationsKt.BufferIterationContextImpl;
                int i = (int) (sizeMut - (-1));
                Segment next = null;
                while (next != null) {
                    this.function.update(next.dataAsByteArray(true), next.getPos() + i, next.getLimit());
                    next = bufferIterationContext.next(next);
                    i = 0;
                }
            } else if (sink.getSizeMut() - sizeMut < sizeMut) {
                Segment tail = sink.getTail();
                long sizeMut2 = sink.getSizeMut();
                while (tail != null && sizeMut2 > sizeMut) {
                    sizeMut2 -= (long) (tail.getLimit() - tail.getPos());
                    if (sizeMut2 <= sizeMut) {
                        break;
                    }
                    tail = tail.getPrev();
                }
                BufferIterationContext bufferIterationContext2 = UnsafeBufferOperationsKt.BufferIterationContextImpl;
                int i2 = (int) (sizeMut - sizeMut2);
                while (tail != null) {
                    this.function.update(tail.dataAsByteArray(true), tail.getPos() + i2, tail.getLimit());
                    tail = bufferIterationContext2.next(tail);
                    i2 = 0;
                }
            } else {
                Segment head = sink.getHead();
                while (head != null) {
                    long limit = ((long) (head.getLimit() - head.getPos())) + j;
                    if (limit > sizeMut) {
                        break;
                    }
                    head = head.getNext();
                    j = limit;
                }
                BufferIterationContext bufferIterationContext3 = UnsafeBufferOperationsKt.BufferIterationContextImpl;
                int i3 = (int) (sizeMut - j);
                while (head != null) {
                    this.function.update(head.dataAsByteArray(true), head.getPos() + i3, head.getLimit());
                    head = bufferIterationContext3.next(head);
                    i3 = 0;
                }
            }
        }
        return atMostTo;
    }

    @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }
}

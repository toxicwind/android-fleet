package kotlinx.io.unsafe;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.AlwaysSharedCopyTracker;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io._UtilKt;

/* JADX INFO: compiled from: UnsafeBufferOperations.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0005H\u0086\u0080\u0004Jo\u0010\u0010\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2K\u0010\u0011\u001aG\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u0012H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J<\u0010\u0010\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00050\u0017H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001Jw\u0010\u001a\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00052K\u0010\u001c\u001aG\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00050\u0012H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001Jb\u0010\u001a\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000526\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00050\u0017H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001J\\\u0010 \u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b28\u0010!\u001a4\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\t0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001Jy\u0010 \u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%2M\u0010!\u001aI\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110%¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\t0\u0012H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001JM\u0010(\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b26\u0010)\u001a2\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\t0\u0017H\u0086\u0088\u0004ø\u0001\u0000R\u0015\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"Lkotlinx/io/unsafe/UnsafeBufferOperations;", "", "<init>", "()V", "maxSafeWriteCapacity", "", "getMaxSafeWriteCapacity", "()I", "moveToTail", "", "buffer", "Lkotlinx/io/Buffer;", "bytes", "", "startIndex", "endIndex", "readFromHead", "readAction", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "startIndexInclusive", "endIndexExclusive", "Lkotlin/Function2;", "Lkotlinx/io/unsafe/SegmentReadContext;", "Lkotlinx/io/Segment;", "writeToTail", "minimumCapacity", "writeAction", "Lkotlinx/io/unsafe/SegmentWriteContext;", "context", "tail", "iterate", "iterationAction", "Lkotlinx/io/unsafe/BufferIterationContext;", TtmlNode.TAG_HEAD, TypedValues.CycleType.S_WAVE_OFFSET, "", "segment", "startOfTheSegmentOffset", "forEachSegment", "action", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UnsafeBufferOperations {
    public static final UnsafeBufferOperations INSTANCE = new UnsafeBufferOperations();

    public final int getMaxSafeWriteCapacity() {
        return 8192;
    }

    private UnsafeBufferOperations() {
    }

    public static /* synthetic */ void moveToTail$default(UnsafeBufferOperations unsafeBufferOperations, Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        unsafeBufferOperations.moveToTail(buffer, bArr, i, i2);
    }

    public final void moveToTail(Buffer buffer, byte[] bytes, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        _UtilKt.checkBounds(bytes.length, startIndex, endIndex);
        Segment segmentNew$kotlinx_io_core = Segment.INSTANCE.new$kotlinx_io_core(bytes, startIndex, endIndex, AlwaysSharedCopyTracker.INSTANCE, false);
        Segment tail = buffer.getTail();
        if (tail == null) {
            buffer.setHead(segmentNew$kotlinx_io_core);
            buffer.setTail(segmentNew$kotlinx_io_core);
        } else {
            buffer.setTail(tail.push$kotlinx_io_core(segmentNew$kotlinx_io_core));
        }
        buffer.setSizeMut(buffer.getSizeMut() + ((long) (endIndex - startIndex)));
    }

    public final int readFromHead(Buffer buffer, Function3<? super byte[], ? super Integer, ? super Integer, Integer> readAction) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(readAction, "readAction");
        if (buffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = buffer.getHead();
        Intrinsics.checkNotNull(head);
        int iIntValue = readAction.invoke(head.dataAsByteArray(true), Integer.valueOf(head.getPos()), Integer.valueOf(head.getLimit())).intValue();
        if (iIntValue == 0) {
            return iIntValue;
        }
        if (iIntValue < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iIntValue > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        buffer.skip(iIntValue);
        return iIntValue;
    }

    public final int readFromHead(Buffer buffer, Function2<? super SegmentReadContext, ? super Segment, Integer> readAction) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(readAction, "readAction");
        if (buffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = buffer.getHead();
        Intrinsics.checkNotNull(head);
        int iIntValue = readAction.invoke(UnsafeBufferOperationsKt.SegmentReadContextImpl, head).intValue();
        if (iIntValue == 0) {
            return iIntValue;
        }
        if (iIntValue < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iIntValue > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        buffer.skip(iIntValue);
        return iIntValue;
    }

    public final int writeToTail(Buffer buffer, int minimumCapacity, Function3<? super byte[], ? super Integer, ? super Integer, Integer> writeAction) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(writeAction, "writeAction");
        Segment segmentWritableSegment = buffer.writableSegment(minimumCapacity);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int iIntValue = writeAction.invoke(bArrDataAsByteArray, Integer.valueOf(segmentWritableSegment.getLimit()), Integer.valueOf(bArrDataAsByteArray.length)).intValue();
        if (iIntValue == minimumCapacity) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iIntValue));
            return iIntValue;
        }
        if (iIntValue < 0 || iIntValue > segmentWritableSegment.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: " + iIntValue + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
        }
        if (iIntValue != 0) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iIntValue));
            return iIntValue;
        }
        if (SegmentKt.isEmpty(segmentWritableSegment)) {
            buffer.recycleTail();
        }
        return iIntValue;
    }

    public final int writeToTail(Buffer buffer, int minimumCapacity, Function2<? super SegmentWriteContext, ? super Segment, Integer> writeAction) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(writeAction, "writeAction");
        Segment segmentWritableSegment = buffer.writableSegment(minimumCapacity);
        int iIntValue = writeAction.invoke(UnsafeBufferOperationsKt.SegmentWriteContextImpl, segmentWritableSegment).intValue();
        if (iIntValue == minimumCapacity) {
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iIntValue));
            return iIntValue;
        }
        if (iIntValue < 0 || iIntValue > segmentWritableSegment.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: " + iIntValue + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
        }
        if (iIntValue != 0) {
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iIntValue));
            return iIntValue;
        }
        if (SegmentKt.isEmpty(segmentWritableSegment)) {
            buffer.recycleTail();
        }
        return iIntValue;
    }

    public final void iterate(Buffer buffer, Function2<? super BufferIterationContext, ? super Segment, Unit> iterationAction) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(iterationAction, "iterationAction");
        iterationAction.invoke(UnsafeBufferOperationsKt.BufferIterationContextImpl, buffer.getHead());
    }

    public final void iterate(Buffer buffer, long offset, Function3<? super BufferIterationContext, ? super Segment, ? super Long, Unit> iterationAction) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(iterationAction, "iterationAction");
        long j = 0;
        if (offset < 0) {
            throw new IllegalArgumentException(("Offset must be non-negative: " + offset).toString());
        }
        if (offset >= buffer.getSizeMut()) {
            throw new IndexOutOfBoundsException("Offset should be less than buffer's size (" + buffer.getSizeMut() + "): " + offset);
        }
        if (buffer.getHead() == null) {
            iterationAction.invoke(UnsafeBufferOperationsKt.BufferIterationContextImpl, null, -1L);
            return;
        }
        if (buffer.getSizeMut() - offset < offset) {
            Segment tail = buffer.getTail();
            long sizeMut = buffer.getSizeMut();
            while (tail != null && sizeMut > offset) {
                sizeMut -= (long) (tail.getLimit() - tail.getPos());
                if (sizeMut <= offset) {
                    break;
                } else {
                    tail = tail.getPrev();
                }
            }
            iterationAction.invoke(UnsafeBufferOperationsKt.BufferIterationContextImpl, tail, Long.valueOf(sizeMut));
            return;
        }
        Segment head = buffer.getHead();
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + j;
            if (limit > offset) {
                break;
            }
            head = head.getNext();
            j = limit;
        }
        iterationAction.invoke(UnsafeBufferOperationsKt.BufferIterationContextImpl, head, Long.valueOf(j));
    }

    public final void forEachSegment(Buffer buffer, Function2<? super SegmentReadContext, ? super Segment, Unit> action) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(action, "action");
        for (Segment head = buffer.getHead(); head != null; head = head.getNext()) {
            action.invoke(UnsafeBufferOperationsKt.SegmentReadContextImpl, head);
        }
    }
}

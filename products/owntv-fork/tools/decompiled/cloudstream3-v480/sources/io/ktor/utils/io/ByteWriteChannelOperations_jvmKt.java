package io.ktor.utils.io;

import io.ktor.utils.io.core.OutputArraysJVMKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io.SinksJvmKt;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ByteWriteChannelOperations.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u0006\u0010\u0005\u001aA\u0010\u000e\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\tH\u0086@¢\u0006\u0004\b\u000e\u0010\u000f\u001a/\u0010\u0010\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0019\u0010\u0010\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0001¢\u0006\u0004\b\u0010\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "Ljava/nio/ByteBuffer;", ES6Iterator.VALUE_PROPERTY, "", "writeByteBuffer", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "", "min", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "buffer", "block", "write", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeAvailable", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/jvm/functions/Function1;)I", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;)V", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteWriteChannelOperations_jvmKt {
    public static final Object writeByteBuffer(ByteWriteChannel byteWriteChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        OutputArraysJVMKt.writeByteBuffer(byteWriteChannel.getWriteBuffer(), byteBuffer);
        Object objFlush = byteWriteChannel.flush(continuation);
        return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
    }

    public static final Object writeFully(ByteWriteChannel byteWriteChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        OutputArraysJVMKt.writeByteBuffer(byteWriteChannel.getWriteBuffer(), byteBuffer);
        Object objFlush = byteWriteChannel.flush(continuation);
        return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
    }

    public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return write(byteWriteChannel, i, function1, continuation);
    }

    public static final Object write(ByteWriteChannel byteWriteChannel, int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Buffer bufferField = byteWriteChannel.getWriteBuffer().getBufferField();
        Segment segmentWritableSegment = bufferField.writableSegment(i);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int limit = segmentWritableSegment.getLimit();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, limit, bArrDataAsByteArray.length - limit);
        Intrinsics.checkNotNull(byteBufferWrap);
        function1.invoke(byteBufferWrap);
        int iPosition = byteBufferWrap.position() - limit;
        if (iPosition == i) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
            bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition));
        } else {
            if (iPosition < 0 || iPosition > segmentWritableSegment.getRemainingCapacity()) {
                throw new IllegalStateException(("Invalid number of bytes written: " + iPosition + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
            }
            if (iPosition != 0) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
                bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition));
            } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                bufferField.recycleTail();
            }
        }
        Object objFlush = byteWriteChannel.flush(continuation);
        return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
    }

    public static /* synthetic */ int writeAvailable$default(ByteWriteChannel byteWriteChannel, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return writeAvailable(byteWriteChannel, i, function1);
    }

    public static final int writeAvailable(ByteWriteChannel byteWriteChannel, int i, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (i <= 0) {
            throw new IllegalArgumentException("min should be positive".toString());
        }
        if (i > 1048576) {
            throw new IllegalArgumentException(("Min(" + i + ") shouldn't be greater than 1048576").toString());
        }
        if (byteWriteChannel.isClosedForWrite()) {
            return -1;
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Buffer bufferField = byteWriteChannel.getWriteBuffer().getBufferField();
        Segment segmentWritableSegment = bufferField.writableSegment(i);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int limit = segmentWritableSegment.getLimit();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, limit, bArrDataAsByteArray.length - limit);
        Intrinsics.checkNotNull(byteBufferWrap);
        block.invoke(byteBufferWrap);
        int iPosition = byteBufferWrap.position() - limit;
        int iPosition2 = byteBufferWrap.position() - limit;
        if (iPosition2 == i) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition2);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition2);
            bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition2));
            return iPosition;
        }
        if (iPosition2 < 0 || iPosition2 > segmentWritableSegment.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: " + iPosition2 + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
        }
        if (iPosition2 != 0) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition2);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition2);
            bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition2));
            return iPosition;
        }
        if (SegmentKt.isEmpty(segmentWritableSegment)) {
            bufferField.recycleTail();
        }
        return iPosition;
    }

    public static final void writeAvailable(ByteWriteChannel byteWriteChannel, ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        SinksJvmKt.write(byteWriteChannel.getWriteBuffer(), buffer);
    }
}

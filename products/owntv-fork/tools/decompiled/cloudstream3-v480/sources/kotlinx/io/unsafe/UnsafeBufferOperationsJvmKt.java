package kotlinx.io.unsafe;

import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;

/* JADX INFO: compiled from: UnsafeBufferOperationsJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aB\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\u001a{\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f2>\u0010\u0005\u001a:\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u0010H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010\u0014\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"readFromHead", "", "Lkotlinx/io/unsafe/UnsafeBufferOperations;", "buffer", "Lkotlinx/io/Buffer;", "readAction", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "", "writeToTail", "minimumCapacity", "writeAction", "readBulk", "", "iovec", "", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "iovecSize", "(Lkotlinx/io/unsafe/UnsafeBufferOperations;Lkotlinx/io/Buffer;[Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function2;)J", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UnsafeBufferOperationsJvmKt {
    public static final long readBulk(UnsafeBufferOperations unsafeBufferOperations, Buffer buffer, ByteBuffer[] iovec, Function2<? super ByteBuffer[], ? super Integer, Long> readAction) throws EOFException {
        int i;
        Intrinsics.checkNotNullParameter(unsafeBufferOperations, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(iovec, "iovec");
        Intrinsics.checkNotNullParameter(readAction, "readAction");
        Segment head = buffer.getHead();
        if (head == null) {
            throw new IllegalArgumentException("buffer is empty.");
        }
        if (iovec.length == 0) {
            throw new IllegalArgumentException("iovec is empty.");
        }
        int i2 = 0;
        long j = 0;
        while (true) {
            int pos = head.getPos();
            int limit = head.getLimit() - pos;
            i = i2 + 1;
            iovec[i2] = ByteBuffer.wrap(head.dataAsByteArray(true), pos, limit).slice().asReadOnlyBuffer();
            j += (long) limit;
            head = head.getNext();
            if (head == null || i >= iovec.length) {
                break;
            }
            i2 = i;
        }
        long jLongValue = readAction.invoke(iovec, Integer.valueOf(i)).longValue();
        if (jLongValue == 0) {
            return jLongValue;
        }
        if (jLongValue < 0 || jLongValue > j) {
            throw new IllegalStateException("readAction should return a value in range [0, " + j + "], but returned: " + jLongValue);
        }
        buffer.skip(jLongValue);
        return jLongValue;
    }

    public static final int readFromHead(UnsafeBufferOperations unsafeBufferOperations, Buffer buffer, Function1<? super ByteBuffer, Unit> readAction) throws EOFException {
        Intrinsics.checkNotNullParameter(unsafeBufferOperations, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(readAction, "readAction");
        if (buffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = buffer.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(bArrDataAsByteArray, pos, head.getLimit() - pos).slice().asReadOnlyBuffer();
        Intrinsics.checkNotNull(byteBufferAsReadOnlyBuffer);
        readAction.invoke(byteBufferAsReadOnlyBuffer);
        int iPosition = byteBufferAsReadOnlyBuffer.position();
        if (iPosition == 0) {
            return iPosition;
        }
        if (iPosition < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iPosition > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        buffer.skip(iPosition);
        return iPosition;
    }

    public static final int writeToTail(UnsafeBufferOperations unsafeBufferOperations, Buffer buffer, int i, Function1<? super ByteBuffer, Unit> writeAction) {
        Intrinsics.checkNotNullParameter(unsafeBufferOperations, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(writeAction, "writeAction");
        Segment segmentWritableSegment = buffer.writableSegment(i);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int limit = segmentWritableSegment.getLimit();
        ByteBuffer byteBufferSlice = ByteBuffer.wrap(bArrDataAsByteArray, limit, bArrDataAsByteArray.length - limit).slice();
        Intrinsics.checkNotNull(byteBufferSlice);
        writeAction.invoke(byteBufferSlice);
        int iPosition = byteBufferSlice.position();
        if (iPosition == i) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iPosition));
            return iPosition;
        }
        if (iPosition < 0 || iPosition > segmentWritableSegment.getRemainingCapacity()) {
            throw new IllegalStateException(("Invalid number of bytes written: " + iPosition + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
        }
        if (iPosition != 0) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
            buffer.setSizeMut(buffer.getSizeMut() + ((long) iPosition));
            return iPosition;
        }
        if (SegmentKt.isEmpty(segmentWritableSegment)) {
            buffer.recycleTail();
        }
        return iPosition;
    }
}

package kotlinx.io;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.unsafe.BufferIterationContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: compiled from: BuffersJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\b\u001a\u001e\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\u0080\b\u001a&\u0010\u0004\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0082\u0080\u0004\u001a \u0010\n\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\u0080\u0004\u001a*\u0010\r\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\u0080\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0087\u0080\b\u001a\u000e\u0010\u0015\u001a\u00020\u0016*\u00020\u0001H\u0086\u0080\u0004¨\u0006\u0017"}, d2 = {"transferFrom", "Lkotlinx/io/Buffer;", TvContractCompat.PARAM_INPUT, "Ljava/io/InputStream;", "write", "byteCount", "", "", "forever", "", "readTo", "out", "Ljava/io/OutputStream;", "copyTo", "startIndex", "endIndex", "readAtMostTo", "", "sink", "Ljava/nio/ByteBuffer;", "source", "asByteChannel", "Ljava/nio/channels/ByteChannel;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BuffersJvmKt {
    @IgnorableReturnValue
    public static final Buffer transferFrom(Buffer buffer, InputStream input) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        write(buffer, input, Long.MAX_VALUE, true);
        return buffer;
    }

    @IgnorableReturnValue
    public static final Buffer write(Buffer buffer, InputStream input, long j) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (j >= 0) {
            write(buffer, input, j, false);
            return buffer;
        }
        throw new IllegalArgumentException(("byteCount (" + j + ") < 0").toString());
    }

    private static final void write(Buffer buffer, InputStream inputStream, long j, boolean z) throws IOException {
        long j2 = j;
        boolean z2 = false;
        while (!z2) {
            if (j2 <= 0 && !z) {
                return;
            }
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment = buffer.writableSegment(1);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            int limit = segmentWritableSegment.getLimit();
            int i = inputStream.read(bArrDataAsByteArray, limit, (int) Math.min(j2, bArrDataAsByteArray.length - limit));
            if (i != -1) {
                j2 -= (long) i;
            } else {
                if (!z) {
                    throw new EOFException("Stream exhausted before " + j + " bytes were read.");
                }
                i = 0;
                z2 = true;
            }
            if (i == 1) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, i);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i);
                buffer.setSizeMut(buffer.getSizeMut() + ((long) i));
            } else {
                if (i < 0 || i > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + i + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (i != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, i);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i);
                    buffer.setSizeMut(buffer.getSizeMut() + ((long) i));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    buffer.recycleTail();
                }
            }
        }
    }

    public static /* synthetic */ void readTo$default(Buffer buffer, OutputStream outputStream, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = buffer.getSizeMut();
        }
        readTo(buffer, outputStream, j);
    }

    public static final void readTo(Buffer buffer, OutputStream out, long j) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(buffer.getSizeMut(), 0L, j);
        while (j > 0) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            if (buffer.exhausted()) {
                throw new IllegalArgumentException("Buffer is empty".toString());
            }
            Segment head = buffer.getHead();
            Intrinsics.checkNotNull(head);
            byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
            int pos = head.getPos();
            int iMin = (int) Math.min(j, head.getLimit() - pos);
            out.write(bArrDataAsByteArray, pos, iMin);
            if (iMin != 0) {
                if (iMin < 0) {
                    throw new IllegalStateException("Returned negative read bytes count");
                }
                if (iMin > head.getSize()) {
                    throw new IllegalStateException("Returned too many bytes");
                }
                buffer.skip(iMin);
            }
            j -= (long) iMin;
        }
    }

    public static /* synthetic */ void copyTo$default(Buffer buffer, OutputStream outputStream, long j, long j2, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.getSizeMut();
        }
        copyTo(buffer, outputStream, j3, j2);
    }

    public static final void copyTo(Buffer buffer, OutputStream out, long j, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkBounds(buffer.getSizeMut(), j, j2);
        if (j == j2) {
            return;
        }
        long j3 = j2 - j;
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        if (j < 0) {
            throw new IllegalArgumentException(("Offset must be non-negative: " + j).toString());
        }
        if (j >= buffer.getSizeMut()) {
            throw new IndexOutOfBoundsException("Offset should be less than buffer's size (" + buffer.getSizeMut() + "): " + j);
        }
        if (buffer.getHead() == null) {
            BufferIterationContext bufferIterationContext = UnsafeBufferOperationsKt.BufferIterationContextImpl;
            Segment next = null;
            Intrinsics.checkNotNull(null);
            int i = (int) (j - (-1));
            while (j3 > 0) {
                byte[] bArrDataAsByteArray = next.dataAsByteArray(true);
                int pos = next.getPos();
                int iMin = (int) Math.min((next.getLimit() - pos) - i, j3);
                out.write(bArrDataAsByteArray, pos + i, iMin);
                j3 -= (long) iMin;
                next = bufferIterationContext.next(next);
                if (next == null) {
                    return;
                } else {
                    i = 0;
                }
            }
            return;
        }
        if (buffer.getSizeMut() - j < j) {
            Segment tail = buffer.getTail();
            long sizeMut = buffer.getSizeMut();
            while (tail != null && sizeMut > j) {
                sizeMut -= (long) (tail.getLimit() - tail.getPos());
                if (sizeMut <= j) {
                    break;
                } else {
                    tail = tail.getPrev();
                }
            }
            BufferIterationContext bufferIterationContext2 = UnsafeBufferOperationsKt.BufferIterationContextImpl;
            Intrinsics.checkNotNull(tail);
            int i2 = (int) (j - sizeMut);
            while (j3 > 0) {
                byte[] bArrDataAsByteArray2 = tail.dataAsByteArray(true);
                int pos2 = tail.getPos();
                int iMin2 = (int) Math.min((tail.getLimit() - pos2) - i2, j3);
                out.write(bArrDataAsByteArray2, pos2 + i2, iMin2);
                j3 -= (long) iMin2;
                tail = bufferIterationContext2.next(tail);
                if (tail == null) {
                    return;
                } else {
                    i2 = 0;
                }
            }
            return;
        }
        Segment head = buffer.getHead();
        long j4 = 0;
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + j4;
            if (limit > j) {
                break;
            }
            head = head.getNext();
            j4 = limit;
        }
        BufferIterationContext bufferIterationContext3 = UnsafeBufferOperationsKt.BufferIterationContextImpl;
        Intrinsics.checkNotNull(head);
        int i3 = (int) (j - j4);
        while (j3 > 0) {
            byte[] bArrDataAsByteArray3 = head.dataAsByteArray(true);
            int pos3 = head.getPos();
            int iMin3 = (int) Math.min((head.getLimit() - pos3) - i3, j3);
            out.write(bArrDataAsByteArray3, pos3 + i3, iMin3);
            j3 -= (long) iMin3;
            head = bufferIterationContext3.next(head);
            if (head == null) {
                return;
            } else {
                i3 = 0;
            }
        }
    }

    public static final int readAtMostTo(Buffer buffer, ByteBuffer sink) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (buffer.exhausted()) {
            return -1;
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        if (buffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = buffer.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        int iMin = Math.min(sink.remaining(), head.getLimit() - pos);
        sink.put(bArrDataAsByteArray, pos, iMin);
        if (iMin == 0) {
            return iMin;
        }
        if (iMin < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iMin > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        buffer.skip(iMin);
        return iMin;
    }

    @IgnorableReturnValue
    public static final Buffer transferFrom(Buffer buffer, ByteBuffer source) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        while (iRemaining > 0) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment = buffer.writableSegment(1);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            int limit = segmentWritableSegment.getLimit();
            int iMin = Math.min(iRemaining, bArrDataAsByteArray.length - limit);
            source.get(bArrDataAsByteArray, limit, iMin);
            if (iMin == 1) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMin);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMin);
                buffer.setSizeMut(buffer.getSizeMut() + ((long) iMin));
            } else {
                if (iMin < 0 || iMin > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iMin + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iMin != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMin);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMin);
                    buffer.setSizeMut(buffer.getSizeMut() + ((long) iMin));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    buffer.recycleTail();
                }
            }
            iRemaining -= iMin;
        }
        return buffer;
    }

    public static final ByteChannel asByteChannel(final Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return new ByteChannel() { // from class: kotlinx.io.BuffersJvmKt.asByteChannel.1
            @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.nio.channels.Channel
            public boolean isOpen() {
                return true;
            }

            @Override // java.nio.channels.ReadableByteChannel
            public int read(ByteBuffer sink) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return BuffersJvmKt.readAtMostTo(buffer, sink);
            }

            @Override // java.nio.channels.WritableByteChannel
            public int write(ByteBuffer source) {
                Intrinsics.checkNotNullParameter(source, "source");
                long sizeMut = buffer.getSizeMut();
                BuffersJvmKt.transferFrom(buffer, source);
                return (int) (buffer.getSizeMut() - sizeMut);
            }
        };
    }
}

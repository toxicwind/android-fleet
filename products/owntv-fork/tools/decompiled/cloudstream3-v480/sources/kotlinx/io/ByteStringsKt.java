package kotlinx.io;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: ByteStrings.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u000e\u0010\b\u001a\u00020\u0004*\u00020\tH\u0086\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\u0004*\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u0010\u000b\u001a\u00020\f*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\fH\u0086\u0080\u0004\u001a \u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\fH\u0086\u0080\u0004¨\u0006\u000e"}, d2 = {"write", "", "Lkotlinx/io/Sink;", "byteString", "Lkotlinx/io/bytestring/ByteString;", "startIndex", "", "endIndex", "readByteString", "Lkotlinx/io/Source;", "byteCount", "indexOf", "", "Lkotlinx/io/Buffer;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteStringsKt {
    public static /* synthetic */ void write$default(Sink sink, ByteString byteString, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteString.getSize();
        }
        write(sink, byteString, i, i2);
    }

    public static final void write(Sink sink, ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        _UtilKt.checkBounds(byteString.getSize(), i, i2);
        if (i2 == i) {
            return;
        }
        Buffer bufferField = sink.getBufferField();
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        byte[] data = byteString.getData();
        while (i < i2) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment = bufferField.writableSegment(1);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            int limit = segmentWritableSegment.getLimit();
            int iMin = Math.min(i2 - i, bArrDataAsByteArray.length - limit);
            int i3 = i + iMin;
            ArraysKt.copyInto(data, bArrDataAsByteArray, limit, i, i3);
            if (iMin == 1) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMin);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMin);
                bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iMin));
            } else {
                if (iMin < 0 || iMin > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iMin + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iMin != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMin);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMin);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iMin));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    bufferField.recycleTail();
                }
            }
            i = i3;
        }
        sink.hintEmit();
    }

    public static final ByteString readByteString(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(SourcesKt.readByteArray(source));
    }

    public static final ByteString readByteString(Source source, int i) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(SourcesKt.readByteArray(source, i));
    }

    public static /* synthetic */ long indexOf$default(Source source, ByteString byteString, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return indexOf(source, byteString, j);
    }

    public static final long indexOf(Source source, ByteString byteString, long j) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        long jMax = Math.max(0L, j);
        if (ByteStringKt.isEmpty(byteString)) {
            source.request(jMax);
            return Math.min(jMax, source.getBufferField().getSizeMut());
        }
        while (source.request(((long) byteString.getSize()) + jMax)) {
            long jIndexOf = indexOf(source.getBufferField(), byteString, jMax);
            if (jIndexOf >= 0) {
                return jIndexOf;
            }
            jMax = (source.getBufferField().getSizeMut() - ((long) byteString.getSize())) + 1;
        }
        return -1L;
    }

    public static /* synthetic */ long indexOf$default(Buffer buffer, ByteString byteString, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return indexOf(buffer, byteString, j);
    }

    public static final long indexOf(Buffer buffer, ByteString byteString, long j) {
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        long size = 0;
        long jMax = Math.max(0L, Math.min(j, buffer.getSizeMut()));
        if (ByteStringKt.isEmpty(byteString)) {
            return jMax;
        }
        if (jMax > buffer.getSizeMut() - ((long) byteString.getSize())) {
            return -1L;
        }
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        byte[] data = byteString.getData();
        if (buffer.getHead() == null) {
            return -1L;
        }
        if (buffer.getSizeMut() - jMax < jMax) {
            Segment tail = buffer.getTail();
            long sizeMut = buffer.getSizeMut();
            while (tail != null && sizeMut > jMax) {
                sizeMut -= (long) (tail.getLimit() - tail.getPos());
                if (sizeMut <= jMax) {
                    break;
                }
                tail = tail.getPrev();
            }
            if (sizeMut == -1) {
                return -1L;
            }
            do {
                Intrinsics.checkNotNull(tail);
                int iMax = Math.max((int) (jMax - sizeMut), 0);
                int iIndexOfBytesInbound = SegmentKt.indexOfBytesInbound(tail, data, iMax);
                if (iIndexOfBytesInbound == -1) {
                    int iIndexOfBytesOutbound = SegmentKt.indexOfBytesOutbound(tail, data, Math.max(iMax, (tail.getSize() - data.length) + 1));
                    if (iIndexOfBytesOutbound == -1) {
                        sizeMut += (long) tail.getSize();
                        tail = tail.getNext();
                        if (tail == null) {
                            break;
                        }
                    } else {
                        j3 = iIndexOfBytesOutbound;
                    }
                } else {
                    j3 = iIndexOfBytesInbound;
                }
                return sizeMut + j3;
            } while (((long) byteString.getSize()) + sizeMut <= buffer.getSizeMut());
            return -1L;
        }
        Segment head = buffer.getHead();
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + size;
            if (limit > jMax) {
                break;
            }
            head = head.getNext();
            size = limit;
        }
        if (size == -1) {
            return -1L;
        }
        do {
            Intrinsics.checkNotNull(head);
            int iMax2 = Math.max((int) (jMax - size), 0);
            int iIndexOfBytesInbound2 = SegmentKt.indexOfBytesInbound(head, data, iMax2);
            if (iIndexOfBytesInbound2 == -1) {
                int iIndexOfBytesOutbound2 = SegmentKt.indexOfBytesOutbound(head, data, Math.max(iMax2, (head.getSize() - data.length) + 1));
                if (iIndexOfBytesOutbound2 == -1) {
                    size += (long) head.getSize();
                    head = head.getNext();
                    if (head == null) {
                        break;
                    }
                } else {
                    j2 = iIndexOfBytesOutbound2;
                }
            } else {
                j2 = iIndexOfBytesInbound2;
            }
            return size + j2;
        } while (((long) byteString.getSize()) + size <= buffer.getSizeMut());
        return -1L;
    }
}

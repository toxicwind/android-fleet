package kotlinx.io;

import androidx.collection.SieveCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringBuilder;
import kotlinx.io.bytestring.ByteStringKt;
import kotlinx.io.unsafe.SegmentReadContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: compiled from: Buffers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004\u001a*\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\u0080\u0004¨\u0006\t"}, d2 = {"snapshot", "Lkotlinx/io/bytestring/ByteString;", "Lkotlinx/io/Buffer;", "indexOf", "", "byte", "", "startIndex", "endIndex", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BuffersKt {
    public static final ByteString snapshot(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.getSizeMut() == 0) {
            return ByteStringKt.ByteString();
        }
        if (buffer.getSizeMut() > SieveCacheKt.NodeLinkMask) {
            throw new IllegalStateException(("Buffer is too long (" + buffer.getSizeMut() + ") to be converted into a byte string.").toString());
        }
        ByteStringBuilder byteStringBuilder = new ByteStringBuilder((int) buffer.getSizeMut());
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        for (Segment head = buffer.getHead(); head != null; head = head.getNext()) {
            SegmentReadContext unused = UnsafeBufferOperationsKt.SegmentReadContextImpl;
            byteStringBuilder.append(head.dataAsByteArray(true), head.getPos(), head.getLimit());
        }
        return byteStringBuilder.toByteString();
    }

    public static /* synthetic */ long indexOf$default(Buffer buffer, byte b, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.getSizeMut();
        }
        return indexOf(buffer, b, j3, j2);
    }

    public static final long indexOf(Buffer buffer, byte b, long j, long j2) {
        long size;
        long j3;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        long jMin = Math.min(j2, buffer.getSizeMut());
        _UtilKt.checkBounds(buffer.getSizeMut(), j, jMin);
        if (j == jMin || buffer.getHead() == null) {
            return -1L;
        }
        if (buffer.getSizeMut() - j < j) {
            Segment tail = buffer.getTail();
            size = buffer.getSizeMut();
            while (tail != null && size > j) {
                size -= (long) (tail.getLimit() - tail.getPos());
                if (size <= j) {
                    break;
                }
                tail = tail.getPrev();
            }
            if (size == -1) {
                return -1L;
            }
            while (jMin > size) {
                Intrinsics.checkNotNull(tail);
                int iIndexOf = SegmentKt.indexOf(tail, b, Math.max((int) (j - size), 0), Math.min(tail.getSize(), (int) (jMin - size)));
                if (iIndexOf != -1) {
                    j3 = iIndexOf;
                } else {
                    size += (long) tail.getSize();
                    tail = tail.getNext();
                    if (tail == null || size >= jMin) {
                        return -1L;
                    }
                }
            }
            throw new IllegalStateException("Check failed.");
        }
        Segment head = buffer.getHead();
        size = 0;
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + size;
            if (limit > j) {
                break;
            }
            head = head.getNext();
            size = limit;
        }
        if (size == -1) {
            return -1L;
        }
        while (jMin > size) {
            Intrinsics.checkNotNull(head);
            int iIndexOf2 = SegmentKt.indexOf(head, b, Math.max((int) (j - size), 0), Math.min(head.getSize(), (int) (jMin - size)));
            if (iIndexOf2 != -1) {
                j3 = iIndexOf2;
            } else {
                size += (long) head.getSize();
                head = head.getNext();
                if (head == null || size >= jMin) {
                    return -1L;
                }
            }
        }
        throw new IllegalStateException("Check failed.");
        return size + j3;
    }
}

package kotlinx.io;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Segment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0080\u0080\u0004\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0080\u0004\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0080\u0004\u001a\u000e\u0010\u000b\u001a\u00020\f*\u00020\u0002H\u0081\u0080\u0004¨\u0006\r"}, d2 = {"indexOf", "", "Lkotlinx/io/Segment;", "byte", "", "startOffset", "endOffset", "indexOfBytesInbound", "bytes", "", "indexOfBytesOutbound", "isEmpty", "", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SegmentKt {
    public static final int indexOf(Segment segment, byte b, int i, int i2) {
        Intrinsics.checkNotNullParameter(segment, "<this>");
        if (i < 0 || i >= segment.getSize()) {
            throw new IllegalArgumentException(String.valueOf(i).toString());
        }
        if (i > i2 || i2 > segment.getSize()) {
            throw new IllegalArgumentException(String.valueOf(i2).toString());
        }
        int pos = segment.getPos();
        byte[] bArrDataAsByteArray = segment.dataAsByteArray(true);
        while (i < i2) {
            if (bArrDataAsByteArray[pos + i] == b) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final int indexOfBytesInbound(Segment segment, byte[] bytes, int i) {
        int iIndexOf;
        Intrinsics.checkNotNullParameter(segment, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int size = (segment.getSize() - bytes.length) + 1;
        byte b = bytes[0];
        byte[] bArrDataAsByteArray = segment.dataAsByteArray(true);
        while (i < size && (iIndexOf = indexOf(segment, b, i, size)) >= 0) {
            int length = bytes.length;
            for (int i2 = 1; i2 < length; i2++) {
                if (bArrDataAsByteArray[segment.getPos() + iIndexOf + i2] != bytes[i2]) {
                    i++;
                }
            }
            return iIndexOf;
        }
        return -1;
    }

    public static final int indexOfBytesOutbound(Segment segment, byte[] bytes, int i) {
        Intrinsics.checkNotNullParameter(segment, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        byte b = bytes[0];
        while (i >= 0 && i < segment.getSize() && indexOf(segment, b, i, segment.getSize()) >= 0) {
            byte[] bArrDataAsByteArray = segment.dataAsByteArray(true);
            Segment next = segment;
            int i2 = i;
            for (byte b2 : bytes) {
                if (i2 == next.getSize()) {
                    next = next.getNext();
                    if (next == null) {
                        return -1;
                    }
                    bArrDataAsByteArray = next.dataAsByteArray(true);
                    i2 = 0;
                }
                if (b2 != bArrDataAsByteArray[next.getPos() + i2]) {
                    i++;
                } else {
                    i2++;
                }
            }
            return i;
        }
        return -1;
    }

    public static final boolean isEmpty(Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "<this>");
        return segment.getSize() == 0;
    }
}

package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.C1150SegmentedByteString;
import okio.Segment;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: renamed from: okio.internal.-SegmentedByteString, reason: invalid class name */
/* JADX INFO: compiled from: SegmentedByteString.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a]\u0010\t\u001a\u00020\n*\u00020\u00072K\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\n0\fH\u0080\bø\u0001\u0000\u001aj\u0010\t\u001a\u00020\n*\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00012K\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\n0\fH\u0082\b\u001a\u001d\u0010\u0015\u001a\u00020\u0016*\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0018*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\r\u0010\u0019\u001a\u00020\u0001*\u00020\u0007H\u0080\b\u001a\r\u0010\u001a\u001a\u00020\r*\u00020\u0007H\u0080\b\u001a%\u0010\u001b\u001a\u00020\n*\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u001e\u001a\u00020\u001f*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u001e\u001a\u00020\u001f*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0080\b\u001a-\u0010\"\u001a\u00020\n*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0080\b\u001a\u0017\u0010%\u001a\u00020\u001f*\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010&H\u0080\b\u001a\r\u0010'\u001a\u00020\u0001*\u00020\u0007H\u0080\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006("}, d2 = {"binarySearch", "", "", ES6Iterator.VALUE_PROPERTY, "fromIndex", "toIndex", "segment", "Lokio/SegmentedByteString;", CloudStreamPackage.POSITION_EXTRA, "forEachSegment", "", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "data", TypedValues.CycleType.S_WAVE_OFFSET, "byteCount", "beginIndex", "endIndex", "commonSubstring", "Lokio/ByteString;", "commonInternalGet", "", "commonGetSize", "commonToByteArray", "commonWrite", "buffer", "Lokio/Buffer;", "commonRangeEquals", "", "other", "otherOffset", "commonCopyInto", TypedValues.AttributesType.S_TARGET, "targetOffset", "commonEquals", "", "commonHashCode", "okio"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SegmentedByteString {
    public static final int binarySearch(int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return (-i2) - 1;
    }

    public static final int segment(C1150SegmentedByteString c1150SegmentedByteString, int i) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        int iBinarySearch = binarySearch(c1150SegmentedByteString.getDirectory(), i + 1, 0, c1150SegmentedByteString.getSegments().length);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    public static final void forEachSegment(C1150SegmentedByteString c1150SegmentedByteString, Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = c1150SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = c1150SegmentedByteString.getDirectory()[length + i];
            int i4 = c1150SegmentedByteString.getDirectory()[i];
            action.invoke(c1150SegmentedByteString.getSegments()[i], Integer.valueOf(i3), Integer.valueOf(i4 - i2));
            i++;
            i2 = i4;
        }
    }

    private static final void forEachSegment(C1150SegmentedByteString c1150SegmentedByteString, int i, int i2, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        int iSegment = segment(c1150SegmentedByteString, i);
        while (i < i2) {
            int i3 = iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1];
            int i4 = c1150SegmentedByteString.getDirectory()[iSegment] - i3;
            int i5 = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment];
            int iMin = Math.min(i2, i4 + i3) - i;
            function3.invoke(c1150SegmentedByteString.getSegments()[iSegment], Integer.valueOf(i5 + (i - i3)), Integer.valueOf(iMin));
            i += iMin;
            iSegment++;
        }
    }

    public static final ByteString commonSubstring(C1150SegmentedByteString c1150SegmentedByteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        }
        if (i2 > c1150SegmentedByteString.size()) {
            throw new IllegalArgumentException(("endIndex=" + i2 + " > length(" + c1150SegmentedByteString.size() + ')').toString());
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + i2 + " < beginIndex=" + i).toString());
        }
        if (i == 0 && i2 == c1150SegmentedByteString.size()) {
            return c1150SegmentedByteString;
        }
        if (i == i2) {
            return ByteString.EMPTY;
        }
        int iSegment = segment(c1150SegmentedByteString, i);
        int iSegment2 = segment(c1150SegmentedByteString, i2 - 1);
        byte[][] bArr = (byte[][]) ArraysKt.copyOfRange(c1150SegmentedByteString.getSegments(), iSegment, iSegment2 + 1);
        byte[][] bArr2 = bArr;
        int[] iArr = new int[bArr2.length * 2];
        if (iSegment <= iSegment2) {
            int i4 = iSegment;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(c1150SegmentedByteString.getDirectory()[i4] - i, i3);
                int i6 = i5 + 1;
                iArr[i5 + bArr2.length] = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + i4];
                if (i4 == iSegment2) {
                    break;
                }
                i4++;
                i5 = i6;
            }
        }
        int i7 = iSegment != 0 ? c1150SegmentedByteString.getDirectory()[iSegment - 1] : 0;
        int length = bArr2.length;
        iArr[length] = iArr[length] + (i - i7);
        return new C1150SegmentedByteString(bArr, iArr);
    }

    public static final byte commonInternalGet(C1150SegmentedByteString c1150SegmentedByteString, int i) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        okio.SegmentedByteString.checkOffsetAndCount(c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length - 1], i, 1L);
        int iSegment = segment(c1150SegmentedByteString, i);
        return c1150SegmentedByteString.getSegments()[iSegment][(i - (iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1])) + c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment]];
    }

    public static final int commonGetSize(C1150SegmentedByteString c1150SegmentedByteString) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        return c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length - 1];
    }

    public static final byte[] commonToByteArray(C1150SegmentedByteString c1150SegmentedByteString) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        byte[] bArr = new byte[c1150SegmentedByteString.size()];
        int length = c1150SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = c1150SegmentedByteString.getDirectory()[length + i];
            int i5 = c1150SegmentedByteString.getDirectory()[i];
            int i6 = i5 - i2;
            ArraysKt.copyInto(c1150SegmentedByteString.getSegments()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public static final boolean commonRangeEquals(C1150SegmentedByteString c1150SegmentedByteString, int i, ByteString other, int i2, int i3) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (i < 0 || i > c1150SegmentedByteString.size() - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = segment(c1150SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1];
            int i6 = c1150SegmentedByteString.getDirectory()[iSegment] - i5;
            int i7 = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!other.rangeEquals(i2, c1150SegmentedByteString.getSegments()[iSegment], i7 + (i - i5), iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }

    public static final boolean commonRangeEquals(C1150SegmentedByteString c1150SegmentedByteString, int i, byte[] other, int i2, int i3) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (i < 0 || i > c1150SegmentedByteString.size() - i3 || i2 < 0 || i2 > other.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = segment(c1150SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1];
            int i6 = c1150SegmentedByteString.getDirectory()[iSegment] - i5;
            int i7 = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!okio.SegmentedByteString.arrayRangeEquals(c1150SegmentedByteString.getSegments()[iSegment], i7 + (i - i5), other, i2, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }

    public static final void commonCopyInto(C1150SegmentedByteString c1150SegmentedByteString, int i, byte[] target, int i2, int i3) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        long j = i3;
        okio.SegmentedByteString.checkOffsetAndCount(c1150SegmentedByteString.size(), i, j);
        okio.SegmentedByteString.checkOffsetAndCount(target.length, i2, j);
        int i4 = i3 + i;
        int iSegment = segment(c1150SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1];
            int i6 = c1150SegmentedByteString.getDirectory()[iSegment] - i5;
            int i7 = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            int i8 = i7 + (i - i5);
            ArraysKt.copyInto(c1150SegmentedByteString.getSegments()[iSegment], target, i2, i8, i8 + iMin);
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
    }

    public static final boolean commonEquals(C1150SegmentedByteString c1150SegmentedByteString, Object obj) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        if (obj == c1150SegmentedByteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == c1150SegmentedByteString.size() && c1150SegmentedByteString.rangeEquals(0, byteString, 0, c1150SegmentedByteString.size())) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(C1150SegmentedByteString c1150SegmentedByteString) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        int hashCode$okio = c1150SegmentedByteString.getHashCode();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = c1150SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = c1150SegmentedByteString.getDirectory()[length + i];
            int i5 = c1150SegmentedByteString.getDirectory()[i];
            byte[] bArr = c1150SegmentedByteString.getSegments()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        c1150SegmentedByteString.setHashCode$okio(i2);
        return i2;
    }

    public static final void commonWrite(C1150SegmentedByteString c1150SegmentedByteString, Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(c1150SegmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int i3 = i + i2;
        int iSegment = segment(c1150SegmentedByteString, i);
        while (i < i3) {
            int i4 = iSegment == 0 ? 0 : c1150SegmentedByteString.getDirectory()[iSegment - 1];
            int i5 = c1150SegmentedByteString.getDirectory()[iSegment] - i4;
            int i6 = c1150SegmentedByteString.getDirectory()[c1150SegmentedByteString.getSegments().length + iSegment];
            int iMin = Math.min(i3, i5 + i4) - i;
            int i7 = i6 + (i - i4);
            Segment segment = new Segment(c1150SegmentedByteString.getSegments()[iSegment], i7, i7 + iMin, true, false);
            if (buffer.head == null) {
                segment.prev = segment;
                segment.next = segment.prev;
                buffer.head = segment.next;
            } else {
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(segment);
            }
            i += iMin;
            iSegment++;
        }
        buffer.setSize$okio(buffer.size() + ((long) i2));
    }
}

package kotlinx.io.bytestring;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00020\u0003\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001d\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00020\u0005\"\u00020\u0006H\u0086\u0080\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\n\u0010\u0000\u001a\u00020\u0001H\u0087\u0080\u0004\u001a \u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a \u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a \u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a \u0010\u0013\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a \u0010\u0013\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a \u0010\u0013\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0086\u0080\u0004\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010\u0016\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010\u0016\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0086\u0080\u0004\u001a2\u0010\u0017\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u000eH\u0082\u0080\u0004\u001a2\u0010\u0017\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u000eH\u0082\u0080\u0004\u001a\u000e\u0010\u001c\u001a\u00020\u0015*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u001d\u001a\u00020\u0015*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u001e\u001a\u00020\u001f*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010 \u001a\u00020\u0001*\u00020\u001fH\u0086\u0080\u0004\u001a\u0016\u0010!\u001a\u00020\u0015*\u00020\u00012\u0006\u0010\"\u001a\u00020\u0003H\u0086\u0080\u0004\"\u0019\u0010\t\u001a\u00020\n*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006#"}, d2 = {"ByteString", "Lkotlinx/io/bytestring/ByteString;", "bytes", "", "", "Lkotlin/UByteArray;", "Lkotlin/UByte;", "ByteString-GBYM_sE", "([B)Lkotlinx/io/bytestring/ByteString;", "indices", "Lkotlin/ranges/IntRange;", "getIndices", "(Lkotlinx/io/bytestring/ByteString;)Lkotlin/ranges/IntRange;", "indexOf", "", "byte", "startIndex", "byteString", "byteArray", "lastIndexOf", "startsWith", "", "endsWith", "rangeEquals", TypedValues.CycleType.S_WAVE_OFFSET, "other", "otherOffset", "byteCount", "isEmpty", "isNotEmpty", "decodeToString", "", "encodeToByteString", "contentEquals", "array", "kotlinx-io-bytestring"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteStringKt {
    public static final ByteString ByteString(byte... bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length == 0) {
            return ByteString.INSTANCE.getEMPTY$kotlinx_io_bytestring();
        }
        return ByteString.INSTANCE.wrap$kotlinx_io_bytestring(bytes);
    }

    /* JADX INFO: renamed from: ByteString-GBYM_sE, reason: not valid java name */
    public static final ByteString m10609ByteStringGBYM_sE(byte... bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (UByteArray.m9026isEmptyimpl(bytes)) {
            return ByteString.INSTANCE.getEMPTY$kotlinx_io_bytestring();
        }
        return ByteString.INSTANCE.wrap$kotlinx_io_bytestring(bytes);
    }

    public static final ByteString ByteString() {
        return ByteString.INSTANCE.getEMPTY$kotlinx_io_bytestring();
    }

    public static final IntRange getIndices(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return RangesKt.until(0, byteString.getSize());
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte b, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(byteString, b, i);
    }

    public static final int indexOf(ByteString byteString, byte b, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        byte[] backingArrayReference = byteString.getData();
        int size = byteString.getSize();
        for (int iMax = Math.max(i, 0); iMax < size; iMax++) {
            if (backingArrayReference[iMax] == b) {
                return iMax;
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(byteString, byteString2, i);
    }

    public static final int indexOf(ByteString byteString, ByteString byteString2, int i) {
        ByteString byteString3;
        ByteString byteString4;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteString2, "byteString");
        if (isEmpty(byteString2)) {
            return Math.max(Math.min(i, byteString.getSize()), 0);
        }
        byte[] backingArrayReference = byteString.getData();
        byte b = byteString2.get(0);
        int iMax = Math.max(i, 0);
        int size = byteString.getSize() - byteString2.getSize();
        if (iMax > size) {
            return -1;
        }
        int i2 = iMax;
        while (true) {
            if (backingArrayReference[i2] == b) {
                byteString3 = byteString;
                byteString4 = byteString2;
                if (rangeEquals$default(byteString3, i2, byteString4, 0, 0, 12, (Object) null)) {
                    return i2;
                }
            } else {
                byteString3 = byteString;
                byteString4 = byteString2;
            }
            if (i2 == size) {
                return -1;
            }
            i2++;
            byteString = byteString3;
            byteString2 = byteString4;
        }
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(byteString, bArr, i);
    }

    public static final int indexOf(ByteString byteString, byte[] byteArray, int i) {
        ByteString byteString2;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        if (byteArray.length == 0) {
            return Math.max(Math.min(i, byteString.getSize()), 0);
        }
        byte[] backingArrayReference = byteString.getData();
        byte b = byteArray[0];
        int iMax = Math.max(0, i);
        int size = byteString.getSize() - byteArray.length;
        if (iMax > size) {
            return -1;
        }
        int i2 = iMax;
        while (true) {
            if (backingArrayReference[i2] == b) {
                byteString2 = byteString;
                bArr = byteArray;
                if (rangeEquals$default(byteString2, i2, bArr, 0, 0, 12, (Object) null)) {
                    return i2;
                }
            } else {
                byteString2 = byteString;
                bArr = byteArray;
            }
            if (i2 == size) {
                return -1;
            }
            i2++;
            byteString = byteString2;
            byteArray = bArr;
        }
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte b, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return lastIndexOf(byteString, b, i);
    }

    public static final int lastIndexOf(ByteString byteString, byte b, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        byte[] backingArrayReference = byteString.getData();
        int size = byteString.getSize() - 1;
        int iMax = Math.max(0, i);
        if (iMax > size) {
            return -1;
        }
        while (backingArrayReference[size] != b) {
            if (size == iMax) {
                return -1;
            }
            size--;
        }
        return size;
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return lastIndexOf(byteString, byteString2, i);
    }

    public static final int lastIndexOf(ByteString byteString, ByteString byteString2, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteString2, "byteString");
        if (isEmpty(byteString2)) {
            return byteString.getSize();
        }
        int size = byteString.getSize() - byteString2.getSize();
        int iMax = Math.max(0, i);
        if (iMax > size) {
            return -1;
        }
        int i2 = size;
        while (true) {
            ByteString byteString3 = byteString;
            ByteString byteString4 = byteString2;
            if (rangeEquals$default(byteString3, i2, byteString4, 0, 0, 8, (Object) null)) {
                return i2;
            }
            if (i2 == iMax) {
                return -1;
            }
            i2--;
            byteString = byteString3;
            byteString2 = byteString4;
        }
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return lastIndexOf(byteString, bArr, i);
    }

    public static final int lastIndexOf(ByteString byteString, byte[] byteArray, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        if (byteArray.length == 0) {
            return byteString.getSize();
        }
        int size = byteString.getSize() - byteArray.length;
        int iMax = Math.max(0, i);
        if (iMax > size) {
            return -1;
        }
        int i2 = size;
        while (true) {
            ByteString byteString2 = byteString;
            byte[] bArr = byteArray;
            if (rangeEquals$default(byteString2, i2, bArr, 0, 0, 8, (Object) null)) {
                return i2;
            }
            if (i2 == iMax) {
                return -1;
            }
            i2--;
            byteString = byteString2;
            byteArray = bArr;
        }
    }

    public static final boolean startsWith(ByteString byteString, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        if (byteArray.length > byteString.getSize()) {
            return false;
        }
        return rangeEquals$default(byteString, 0, byteArray, 0, 0, 12, (Object) null);
    }

    public static final boolean startsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteString2, "byteString");
        if (byteString2.getSize() > byteString.getSize()) {
            return false;
        }
        return byteString2.getSize() == byteString.getSize() ? byteString.equals(byteString2) : rangeEquals$default(byteString, 0, byteString2, 0, 0, 12, (Object) null);
    }

    public static final boolean endsWith(ByteString byteString, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        if (byteArray.length > byteString.getSize()) {
            return false;
        }
        return rangeEquals$default(byteString, byteString.getSize() - byteArray.length, byteArray, 0, 0, 12, (Object) null);
    }

    public static final boolean endsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(byteString2, "byteString");
        if (byteString2.getSize() > byteString.getSize()) {
            return false;
        }
        return byteString2.getSize() == byteString.getSize() ? byteString.equals(byteString2) : rangeEquals$default(byteString, byteString.getSize() - byteString2.getSize(), byteString2, 0, 0, 12, (Object) null);
    }

    static /* synthetic */ boolean rangeEquals$default(ByteString byteString, int i, ByteString byteString2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = byteString2.getSize() - i2;
        }
        return rangeEquals(byteString, i, byteString2, i2, i3);
    }

    private static final boolean rangeEquals(ByteString byteString, int i, ByteString byteString2, int i2, int i3) {
        byte[] backingArrayReference = byteString.getData();
        byte[] backingArrayReference2 = byteString2.getData();
        for (int i4 = 0; i4 < i3; i4++) {
            if (backingArrayReference[i + i4] != backingArrayReference2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ boolean rangeEquals$default(ByteString byteString, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length - i2;
        }
        return rangeEquals(byteString, i, bArr, i2, i3);
    }

    private static final boolean rangeEquals(ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        byte[] backingArrayReference = byteString.getData();
        for (int i4 = 0; i4 < i3; i4++) {
            if (backingArrayReference[i + i4] != bArr[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isEmpty(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return byteString.getSize() == 0;
    }

    public static final boolean isNotEmpty(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return !isEmpty(byteString);
    }

    public static final String decodeToString(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return StringsKt.decodeToString(byteString.getData());
    }

    public static final ByteString encodeToByteString(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return ByteString.INSTANCE.wrap$kotlinx_io_bytestring(StringsKt.encodeToByteArray(str));
    }

    public static final boolean contentEquals(ByteString byteString, byte[] array) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        return Arrays.equals(byteString.getData(), array);
    }
}

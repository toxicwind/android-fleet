package kotlin.collections;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0014\u0010\u0015\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0019\u0010\u001a\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u001e\u0010\u000b\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u001f\u0010\u0010\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b \u0010\u0015\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", TtmlNode.LEFT, TtmlNode.RIGHT, "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UArraySortingKt {
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m9406partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM9023getw2LRezQ = UByteArray.m9023getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM9023getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m9023getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m9023getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM9023getw2LRezQ2 = UByteArray.m9023getw2LRezQ(bArr, i);
                UByteArray.m9028setVurrAj0(bArr, i, UByteArray.m9023getw2LRezQ(bArr, i2));
                UByteArray.m9028setVurrAj0(bArr, i2, bM9023getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m9410quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM9406partition4UcCI2c = m9406partition4UcCI2c(bArr, i, i2);
        int i3 = iM9406partition4UcCI2c - 1;
        if (i < i3) {
            m9410quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM9406partition4UcCI2c < i2) {
            m9410quickSort4UcCI2c(bArr, iM9406partition4UcCI2c, i2);
        }
    }

    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m9407partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM9286getMh2AYeg = UShortArray.m9286getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM9286getMh2AYeg = UShortArray.m9286getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = sM9286getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM9286getMh2AYeg, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m9286getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM9286getMh2AYeg2 = UShortArray.m9286getMh2AYeg(sArr, i);
                UShortArray.m9291set01HTLdE(sArr, i, UShortArray.m9286getMh2AYeg(sArr, i2));
                UShortArray.m9291set01HTLdE(sArr, i2, sM9286getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m9411quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM9407partitionAa5vz7o = m9407partitionAa5vz7o(sArr, i, i2);
        int i3 = iM9407partitionAa5vz7o - 1;
        if (i < i3) {
            m9411quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM9407partitionAa5vz7o < i2) {
            m9411quickSortAa5vz7o(sArr, iM9407partitionAa5vz7o, i2);
        }
    }

    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m9408partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM9102getpVg5ArA = UIntArray.m9102getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compare(UIntArray.m9102getpVg5ArA(iArr, i) ^ Integer.MIN_VALUE, iM9102getpVg5ArA ^ Integer.MIN_VALUE) < 0) {
                i++;
            }
            while (Integer.compare(UIntArray.m9102getpVg5ArA(iArr, i2) ^ Integer.MIN_VALUE, iM9102getpVg5ArA ^ Integer.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM9102getpVg5ArA2 = UIntArray.m9102getpVg5ArA(iArr, i);
                UIntArray.m9107setVXSXFK8(iArr, i, UIntArray.m9102getpVg5ArA(iArr, i2));
                UIntArray.m9107setVXSXFK8(iArr, i2, iM9102getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m9412quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM9408partitionoBK06Vg = m9408partitionoBK06Vg(iArr, i, i2);
        int i3 = iM9408partitionoBK06Vg - 1;
        if (i < i3) {
            m9412quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM9408partitionoBK06Vg < i2) {
            m9412quickSortoBK06Vg(iArr, iM9408partitionoBK06Vg, i2);
        }
    }

    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m9405partitionnroSd4(long[] jArr, int i, int i2) {
        long jM9181getsVKNKU = ULongArray.m9181getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compare(ULongArray.m9181getsVKNKU(jArr, i) ^ Long.MIN_VALUE, jM9181getsVKNKU ^ Long.MIN_VALUE) < 0) {
                i++;
            }
            while (Long.compare(ULongArray.m9181getsVKNKU(jArr, i2) ^ Long.MIN_VALUE, jM9181getsVKNKU ^ Long.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM9181getsVKNKU2 = ULongArray.m9181getsVKNKU(jArr, i);
                ULongArray.m9186setk8EXiF4(jArr, i, ULongArray.m9181getsVKNKU(jArr, i2));
                ULongArray.m9186setk8EXiF4(jArr, i2, jM9181getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m9409quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM9405partitionnroSd4 = m9405partitionnroSd4(jArr, i, i2);
        int i3 = iM9405partitionnroSd4 - 1;
        if (i < i3) {
            m9409quickSortnroSd4(jArr, i, i3);
        }
        if (iM9405partitionnroSd4 < i2) {
            m9409quickSortnroSd4(jArr, iM9405partitionnroSd4, i2);
        }
    }

    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m9414sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m9410quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m9415sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m9411quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m9416sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m9412quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m9413sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m9409quickSortnroSd4(array, i, i2 - 1);
    }
}

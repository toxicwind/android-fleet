package com.fleeksoft.ksoup.ported;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KsoupExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0086\b¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a5\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"binarySearch", "", ExifInterface.GPS_DIRECTION_TRUE, "", "", "element", "([Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "", "key", "binarySearchBy", "comparison", "Lkotlin/Function1;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KsoupExtKt {
    public static final <T extends Comparable<? super T>> int binarySearch(T[] tArr, T element) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        int length = tArr.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareValues = ComparisonsKt.compareValues(tArr[i2], element);
            if (iCompareValues < 0) {
                i = i2 + 1;
            } else {
                if (iCompareValues <= 0) {
                    return i2;
                }
                length = i2 - 1;
            }
        }
        return -(i + 1);
    }

    public static final int binarySearch(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = (i2 + length) >>> 1;
            int i4 = iArr[i3];
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                if (i4 <= i) {
                    return i3;
                }
                length = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final <T> int binarySearchBy(T[] tArr, Function1<? super T, Integer> comparison) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparison, "comparison");
        int length = tArr.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iIntValue = comparison.invoke(tArr[i2]).intValue();
            if (iIntValue < 0) {
                i = i2 + 1;
            } else {
                if (iIntValue <= 0) {
                    return i2;
                }
                length = i2 - 1;
            }
        }
        return -(i + 1);
    }
}

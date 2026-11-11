package com.fleeksoft.ksoup.ported;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoreExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a2\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0005*\u0012\u0012\u0004\u0012\u0002H\u00050\u0006j\b\u0012\u0004\u0012\u0002H\u0005`\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¨\u0006\u000b"}, d2 = {"codePointsToString", "", "", "removeRange", "", ExifInterface.LONGITUDE_EAST, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fromIndex", "", "toIndex", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CoreExtensionsKt {
    public static final String codePointsToString(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (!(iArr.length == 0)) {
            StringBuilder sb = new StringBuilder();
            for (int i : iArr) {
                sb.appendCodePoint(i);
            }
            return sb.toString();
        }
        return "";
    }

    public static final <E> void removeRange(ArrayList<E> arrayList, int i, int i2) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        int i3 = i2 - 1;
        if (i > i3) {
            return;
        }
        while (true) {
            arrayList.remove(i3);
            if (i3 == i) {
                return;
            } else {
                i3--;
            }
        }
    }
}

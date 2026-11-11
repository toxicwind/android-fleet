package androidx.compose.runtime.collection;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: ArrayUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u001aL\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0080\b¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"fastCopyInto", "", ExifInterface.GPS_DIRECTION_TRUE, "destination", "destinationOffset", "", "startIndex", "endIndex", "([Ljava/lang/Object;[Ljava/lang/Object;III)[Ljava/lang/Object;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ArrayUtils_androidKt {
    public static final <T> T[] fastCopyInto(T[] tArr, T[] tArr2, int i, int i2, int i3) {
        System.arraycopy(tArr, i2, tArr2, i, i3 - i2);
        return tArr2;
    }
}

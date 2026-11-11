package androidx.compose.ui.util;

import androidx.collection.SieveCacheKt;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: InlineClassHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0019\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\b\u001a\u0011\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\t\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b\u001a\u0019\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0086\b\u001a\u0011\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0001H\u0086\b¨\u0006\u000e"}, d2 = {"packFloats", "", "val1", "", "val2", "unpackFloat1", ES6Iterator.VALUE_PROPERTY, "unpackAbsFloat1", "unpackFloat2", "unpackAbsFloat2", "packInts", "", "unpackInt1", "unpackInt2", "ui-util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class InlineClassHelperKt {
    public static final long packInts(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    public static final int unpackInt1(long j) {
        return (int) (j >> 32);
    }

    public static final int unpackInt2(long j) {
        return (int) (j & 4294967295L);
    }

    public static final long packFloats(float f, float f2) {
        return (((long) Float.floatToRawIntBits(f2)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32);
    }

    public static final float unpackFloat1(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    public static final float unpackAbsFloat1(long j) {
        return Float.intBitsToFloat((int) ((j >> 32) & SieveCacheKt.NodeLinkMask));
    }

    public static final float unpackFloat2(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    public static final float unpackAbsFloat2(long j) {
        return Float.intBitsToFloat((int) (j & SieveCacheKt.NodeLinkMask));
    }
}

package kotlinx.atomicfu.locks;

import androidx.collection.SieveCacheKt;
import androidx.media3.common.C;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ParkingDelegator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"addNanosToSeconds", "", "nanos", "", "atomicfu"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ParkingDelegatorKt {
    public static final int addNanosToSeconds(int i, long j) {
        return (int) RangesKt.coerceIn(((long) i) + (j / C.NANOS_PER_SECOND), SieveCacheKt.NodeMetaAndPreviousMask, SieveCacheKt.NodeLinkMask);
    }

    public static final long addNanosToSeconds(long j, long j2) {
        if (j2 < 0) {
            throw new IllegalStateException("Cannot wait for a negative number of nanoseconds".toString());
        }
        long j3 = (j2 / C.NANOS_PER_SECOND) + j;
        if ((j ^ j3) >= 0 || j < 0) {
            return j3;
        }
        throw new IllegalStateException(("Nano seconds addition overflowed, current time in seconds is " + j).toString());
    }
}

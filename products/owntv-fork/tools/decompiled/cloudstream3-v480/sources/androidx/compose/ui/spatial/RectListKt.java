package androidx.compose.ui.spatial;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: RectList.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b*\u001a\u0019\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0080\b\u001a9\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0080\b\u001a\u0011\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0019\u0010 \u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010!\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0080\b\u001a\u0011\u0010\"\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010#\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a!\u0010$\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0080\b\u001a\u0019\u0010%\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010&\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010'\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010(\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\nH\u0080\b\u001a\u0011\u0010+\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\nH\u0080\b\u001a)\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\nH\u0080\b\u001a\r\u00101\u001a\u00020\n*\u00020\u0019H\u0080\b\u001aP\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u0001H\u0000\u001a8\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0006X\u0080T¢\u0006\u0004\n\u0002\u0010\u0007\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"LongsPerItem", "", "InitialSize", "Lower26Bits", "Lower9Bits", "EverythingButParentId", "Lkotlin/ULong;", "J", "EverythingButLastChildOffset", "PackedIntsLowestBit", "", "PackedIntsHighestBit", "TombStone", "AxisNorth", "AxisSouth", "AxisWest", "AxisEast", "packXY", "x", "y", "packMeta", "itemId", "parentId", "lastChildOffset", "updated", "", "focusable", "gesturable", "unpackMetaValue", "meta", "unpackMetaParentId", "unpackMetaLastChildOffset", "metaWithParentId", "metaWithUpdated", "metaMarkUpdated", "metaUnMarkUpdated", "metaMarkFlags", "metaWithLastChildOffset", "unpackMetaFocusable", "unpackMetaGesturable", "unpackMetaUpdated", "unpackX", "xy", "unpackY", "rectIntersectsRect", "srcLT", "srcRB", "destLT", "destRB", "toLong", "distanceScore", "axis", "queryL", "queryT", "queryR", "queryB", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "distanceScoreAlongAxis", "distanceMin", "distanceMax", "queryCrossAxisMax", "queryCrossAxisMin", "crossAxisMax", "crossAxisMin", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RectListKt {
    public static final int AxisEast = 3;
    public static final int AxisNorth = 0;
    public static final int AxisSouth = 1;
    public static final int AxisWest = 2;
    public static final long EverythingButLastChildOffset = -2301339409586323457L;
    public static final long EverythingButParentId = -4503599560261633L;
    public static final int InitialSize = 64;
    public static final int LongsPerItem = 3;
    public static final int Lower26Bits = 67108863;
    public static final int Lower9Bits = 511;
    private static final long PackedIntsHighestBit = -9223372034707292160L;
    private static final long PackedIntsLowestBit = 4294967297L;
    public static final long TombStone = 2305843009213693951L;

    public static final long metaMarkFlags(long j, boolean z, boolean z2) {
        return (j & 4611686018427387903L) | ((z ? 1L : 0L) * 4611686018427387904L) | ((z2 ? 1L : 0L) * Long.MIN_VALUE);
    }

    public static final long metaMarkUpdated(long j) {
        return j | LockFreeTaskQueueCore.CLOSED_MASK;
    }

    public static final long metaUnMarkUpdated(long j) {
        return j & (-2305843009213693953L);
    }

    public static final long metaWithLastChildOffset(long j, int i) {
        return (j & EverythingButLastChildOffset) | (((long) (i & 511)) << 52);
    }

    public static final long metaWithParentId(long j, int i) {
        return (j & EverythingButParentId) | (((long) (i & Lower26Bits)) << 26);
    }

    public static final long metaWithUpdated(long j, boolean z) {
        return (j & (-2305843009213693953L)) | ((z ? 1L : 0L) << 61);
    }

    public static final long packMeta(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        long j = ((long) (i3 & 511)) << 52;
        return ((long) (i & Lower26Bits)) | j | ((z2 ? 1L : 0L) << 62) | ((z3 ? 1L : 0L) << 63) | ((z ? 1L : 0L) << 61) | (((long) (i2 & Lower26Bits)) << 26);
    }

    public static final long packXY(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    public static final boolean rectIntersectsRect(long j, long j2, long j3, long j4) {
        return ((((j4 - j) - 4294967297L) | ((j2 - j3) - 4294967297L)) & (-9223372034707292160L)) == 0;
    }

    public static final long toLong(boolean z) {
        return z ? 1L : 0L;
    }

    public static final int unpackMetaFocusable(long j) {
        return ((int) (j >> 62)) & 1;
    }

    public static final int unpackMetaGesturable(long j) {
        return ((int) (j >> 63)) & 1;
    }

    public static final int unpackMetaLastChildOffset(long j) {
        return ((int) (j >> 52)) & 511;
    }

    public static final int unpackMetaParentId(long j) {
        return ((int) (j >> 26)) & Lower26Bits;
    }

    public static final int unpackMetaUpdated(long j) {
        return ((int) (j >> 61)) & 1;
    }

    public static final int unpackMetaValue(long j) {
        return ((int) j) & Lower26Bits;
    }

    public static final int unpackX(long j) {
        return (int) (j >> 32);
    }

    public static final int unpackY(long j) {
        return (int) j;
    }

    public static final int distanceScore(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (i == 0) {
            return distanceScoreAlongAxis(i3, i9, i4, i2, i8, i6);
        }
        if (i == 1) {
            return distanceScoreAlongAxis(i7, i5, i4, i2, i8, i6);
        }
        if (i == 2) {
            return distanceScoreAlongAxis(i2, i8, i5, i3, i9, i7);
        }
        if (i != 3) {
            return Integer.MAX_VALUE;
        }
        return distanceScoreAlongAxis(i6, i4, i5, i3, i9, i7);
    }

    public static final int distanceScoreAlongAxis(int i, int i2, int i3, int i4, int i5, int i6) {
        return ((i - i2) + 1) * ((((i3 - i4) + Math.max(i4, i6)) - Math.min(i3, i5)) + 1);
    }
}

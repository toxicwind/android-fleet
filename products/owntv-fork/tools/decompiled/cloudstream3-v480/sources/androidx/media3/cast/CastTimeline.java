package androidx.media3.cast;

import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
final class CastTimeline extends Timeline {
    public static final CastTimeline EMPTY_CAST_TIMELINE = new CastTimeline(new int[0], new SparseArray());
    private final long[] defaultPositionsUs;
    private final long[] durationsUs;
    private final int[] ids;
    private final SparseIntArray idsToIndex;
    private final boolean[] isLive;
    private final MediaItem[] mediaItems;

    public static final class ItemData {
        public final String contentId;
        public final long defaultPositionUs;
        public final long durationUs;
        public final boolean isLive;
        public final MediaItem mediaItem;
        static final String UNKNOWN_CONTENT_ID = "UNKNOWN_CONTENT_ID";
        public static final ItemData EMPTY = new ItemData(C.TIME_UNSET, C.TIME_UNSET, false, MediaItem.EMPTY, UNKNOWN_CONTENT_ID);

        public ItemData(long j, long j2, boolean z, MediaItem mediaItem, String str) {
            this.durationUs = j;
            this.defaultPositionUs = j2;
            this.isLive = z;
            this.mediaItem = mediaItem;
            this.contentId = str;
        }

        public ItemData copyWithNewValues(long j, long j2, boolean z, MediaItem mediaItem, String str) {
            return (j == this.durationUs && j2 == this.defaultPositionUs && z == this.isLive && str.equals(this.contentId) && mediaItem.equals(this.mediaItem)) ? this : new ItemData(j, j2, z, mediaItem, str);
        }
    }

    public CastTimeline(int[] iArr, SparseArray<ItemData> sparseArray) {
        int length = iArr.length;
        this.idsToIndex = new SparseIntArray(length);
        this.ids = Arrays.copyOf(iArr, length);
        this.durationsUs = new long[length];
        this.defaultPositionsUs = new long[length];
        this.isLive = new boolean[length];
        this.mediaItems = new MediaItem[length];
        int i = 0;
        while (true) {
            int[] iArr2 = this.ids;
            if (i >= iArr2.length) {
                return;
            }
            int i2 = iArr2[i];
            this.idsToIndex.put(i2, i);
            ItemData itemData = sparseArray.get(i2, ItemData.EMPTY);
            this.mediaItems[i] = itemData.mediaItem;
            this.durationsUs[i] = itemData.durationUs;
            this.defaultPositionsUs[i] = itemData.defaultPositionUs == C.TIME_UNSET ? 0L : itemData.defaultPositionUs;
            this.isLive[i] = itemData.isLive;
            i++;
        }
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.ids.length;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
        long j2 = this.durationsUs[i];
        boolean z = j2 == C.TIME_UNSET;
        Integer numValueOf = Integer.valueOf(this.ids[i]);
        MediaItem mediaItem = this.mediaItems[i];
        return window.set(numValueOf, mediaItem, null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, !z, z, this.isLive[i] ? mediaItem.liveConfiguration : null, this.defaultPositionsUs[i], j2, i, i, 0L);
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return this.ids.length;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        int i2 = this.ids[i];
        return period.set(Integer.valueOf(i2), Integer.valueOf(i2), i, this.durationsUs[i], 0L);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object obj) {
        if (obj instanceof Integer) {
            return this.idsToIndex.get(((Integer) obj).intValue(), -1);
        }
        return -1;
    }

    @Override // androidx.media3.common.Timeline
    public Integer getUidOfPeriod(int i) {
        return Integer.valueOf(this.ids[i]);
    }

    @Override // androidx.media3.common.Timeline
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CastTimeline)) {
            return false;
        }
        CastTimeline castTimeline = (CastTimeline) obj;
        return Arrays.equals(this.ids, castTimeline.ids) && Arrays.equals(this.durationsUs, castTimeline.durationsUs) && Arrays.equals(this.defaultPositionsUs, castTimeline.defaultPositionsUs) && Arrays.equals(this.isLive, castTimeline.isLive);
    }

    @Override // androidx.media3.common.Timeline
    public int hashCode() {
        return (((((Arrays.hashCode(this.ids) * 31) + Arrays.hashCode(this.durationsUs)) * 31) + Arrays.hashCode(this.defaultPositionsUs)) * 31) + Arrays.hashCode(this.isLive);
    }
}

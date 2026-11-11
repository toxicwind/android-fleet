package androidx.media3.session;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class QueueTimeline extends Timeline {
    public static final QueueTimeline DEFAULT = new QueueTimeline(ImmutableList.of(), null);
    private static final Object FAKE_WINDOW_UID = new Object();
    private final QueuedMediaItem fakeQueuedMediaItem;
    private final ImmutableList<QueuedMediaItem> queuedMediaItems;

    private QueueTimeline(ImmutableList<QueuedMediaItem> immutableList, QueuedMediaItem queuedMediaItem) {
        this.queuedMediaItems = immutableList;
        this.fakeQueuedMediaItem = queuedMediaItem;
    }

    public static QueueTimeline create(List<MediaSessionCompat.QueueItem> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            MediaSessionCompat.QueueItem queueItem = list.get(i);
            builder.add(new QueuedMediaItem(LegacyConversions.convertToMediaItem(queueItem), queueItem.getQueueId(), C.TIME_UNSET));
        }
        return new QueueTimeline(builder.build(), null);
    }

    public QueueTimeline copy() {
        return new QueueTimeline(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }

    public long getQueueId(int i) {
        if (i < 0 || i >= this.queuedMediaItems.size()) {
            return -1L;
        }
        return this.queuedMediaItems.get(i).queueId;
    }

    public QueueTimeline copyWithFakeMediaItem(MediaItem mediaItem, long j) {
        return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1L, j));
    }

    public QueueTimeline copyWithClearedFakeMediaItem() {
        return new QueueTimeline(this.queuedMediaItems, null);
    }

    public QueueTimeline copyWithNewMediaItem(int i, MediaItem mediaItem, long j) {
        Preconditions.checkArgument(i < this.queuedMediaItems.size() || (i == this.queuedMediaItems.size() && this.fakeQueuedMediaItem != null));
        if (i == this.queuedMediaItems.size()) {
            return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1L, j));
        }
        long j2 = this.queuedMediaItems.get(i).queueId;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        builder.add(new QueuedMediaItem(mediaItem, j2, j));
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i + 1, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithNewMediaItems(int i, List<MediaItem> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        for (int i2 = 0; i2 < list.size(); i2++) {
            builder.add(new QueuedMediaItem(list.get(i2), -1L, C.TIME_UNSET));
        }
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithRemovedMediaItems(int i, int i2) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i2, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithMovedMediaItems(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList(this.queuedMediaItems);
        Util.moveItems(arrayList, i, i2, i3);
        return new QueueTimeline(ImmutableList.copyOf((Collection) arrayList), this.fakeQueuedMediaItem);
    }

    public boolean contains(MediaItem mediaItem) {
        QueuedMediaItem queuedMediaItem = this.fakeQueuedMediaItem;
        if (queuedMediaItem != null && mediaItem.equals(queuedMediaItem.mediaItem)) {
            return true;
        }
        for (int i = 0; i < this.queuedMediaItems.size(); i++) {
            if (mediaItem.equals(this.queuedMediaItems.get(i).mediaItem)) {
                return true;
            }
        }
        return false;
    }

    public MediaItem getMediaItemAt(int i) {
        if (i >= getWindowCount()) {
            return null;
        }
        return getQueuedMediaItem(i).mediaItem;
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.queuedMediaItems.size() + (this.fakeQueuedMediaItem == null ? 0 : 1);
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i);
        window.set(FAKE_WINDOW_UID, queuedMediaItem.mediaItem, null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, true, false, null, 0L, Util.msToUs(queuedMediaItem.durationMs), i, i, 0L);
        return window;
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return getWindowCount();
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i);
        period.set(Long.valueOf(queuedMediaItem.queueId), null, i, Util.msToUs(queuedMediaItem.durationMs), 0L);
        return period;
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.common.Timeline
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QueueTimeline)) {
            return false;
        }
        QueueTimeline queueTimeline = (QueueTimeline) obj;
        return Objects.equals(this.queuedMediaItems, queueTimeline.queuedMediaItems) && Objects.equals(this.fakeQueuedMediaItem, queueTimeline.fakeQueuedMediaItem);
    }

    @Override // androidx.media3.common.Timeline
    public int hashCode() {
        return Objects.hash(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }

    private QueuedMediaItem getQueuedMediaItem(int i) {
        QueuedMediaItem queuedMediaItem;
        return (i != this.queuedMediaItems.size() || (queuedMediaItem = this.fakeQueuedMediaItem) == null) ? this.queuedMediaItems.get(i) : queuedMediaItem;
    }

    private static final class QueuedMediaItem {
        public final long durationMs;
        public final MediaItem mediaItem;
        public final long queueId;

        public QueuedMediaItem(MediaItem mediaItem, long j, long j2) {
            this.mediaItem = mediaItem;
            this.queueId = j;
            this.durationMs = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof QueuedMediaItem)) {
                return false;
            }
            QueuedMediaItem queuedMediaItem = (QueuedMediaItem) obj;
            return this.queueId == queuedMediaItem.queueId && this.mediaItem.equals(queuedMediaItem.mediaItem) && this.durationMs == queuedMediaItem.durationMs;
        }

        public int hashCode() {
            long j = this.queueId;
            int iHashCode = (((217 + ((int) (j ^ (j >>> 32)))) * 31) + this.mediaItem.hashCode()) * 31;
            long j2 = this.durationMs;
            return iHashCode + ((int) (j2 ^ (j2 >>> 32)));
        }
    }
}

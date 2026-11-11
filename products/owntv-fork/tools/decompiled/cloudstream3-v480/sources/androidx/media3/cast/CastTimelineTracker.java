package androidx.media3.cast;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class CastTimelineTracker {
    private final MediaItemConverter mediaItemConverter;
    private final SparseArray<CastTimeline.ItemData> itemIdToData = new SparseArray<>();
    final HashMap<String, MediaItem> mediaItemsByContentId = new HashMap<>();

    public CastTimelineTracker(MediaItemConverter mediaItemConverter) {
        this.mediaItemConverter = mediaItemConverter;
    }

    public void onMediaItemsSet(List<MediaItem> list, MediaQueueItem[] mediaQueueItemArr) {
        this.mediaItemsByContentId.clear();
        onMediaItemsAdded(list, mediaQueueItemArr);
    }

    public void onMediaItemsAdded(List<MediaItem> list, MediaQueueItem[] mediaQueueItemArr) {
        for (int i = 0; i < list.size(); i++) {
            this.mediaItemsByContentId.put(((MediaInfo) Preconditions.checkNotNull(mediaQueueItemArr[i].getMedia())).getContentId(), list.get(i));
        }
    }

    public CastTimeline getCastTimeline(RemoteMediaClient remoteMediaClient) {
        int[] itemIds = remoteMediaClient.getMediaQueue().getItemIds();
        if (itemIds.length > 0) {
            removeUnusedItemDataEntries(itemIds);
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if (mediaStatus == null || mediaStatus.getMediaInfo() == null) {
            return CastTimeline.EMPTY_CAST_TIMELINE;
        }
        int currentItemId = mediaStatus.getCurrentItemId();
        String contentId = ((MediaInfo) Preconditions.checkNotNull(mediaStatus.getMediaInfo())).getContentId();
        MediaItem mediaItem = this.mediaItemsByContentId.get(contentId);
        if (mediaItem == null) {
            mediaItem = MediaItem.EMPTY;
        }
        updateItemData(currentItemId, mediaItem, mediaStatus.getMediaInfo(), contentId, C.TIME_UNSET);
        for (MediaQueueItem mediaQueueItem : mediaStatus.getQueueItems()) {
            long startTime = (long) (mediaQueueItem.getStartTime() * 1000000.0d);
            MediaInfo media = mediaQueueItem.getMedia();
            String contentId2 = media != null ? media.getContentId() : "UNKNOWN_CONTENT_ID";
            MediaItem mediaItem2 = this.mediaItemsByContentId.get(contentId2);
            int itemId = mediaQueueItem.getItemId();
            if (mediaItem2 == null) {
                mediaItem2 = this.mediaItemConverter.toMediaItem(mediaQueueItem);
            }
            updateItemData(itemId, mediaItem2, media, contentId2, startTime);
        }
        return new CastTimeline(itemIds, this.itemIdToData);
    }

    private void updateItemData(int i, MediaItem mediaItem, MediaInfo mediaInfo, String str, long j) {
        boolean z;
        CastTimeline.ItemData itemData = this.itemIdToData.get(i, CastTimeline.ItemData.EMPTY);
        long streamDurationUs = CastUtils.getStreamDurationUs(mediaInfo);
        if (streamDurationUs == C.TIME_UNSET) {
            streamDurationUs = itemData.durationUs;
        }
        if (mediaInfo == null) {
            z = itemData.isLive;
        } else {
            z = mediaInfo.getStreamType() == 2;
        }
        boolean z2 = z;
        if (j == C.TIME_UNSET) {
            j = itemData.defaultPositionUs;
        }
        this.itemIdToData.put(i, itemData.copyWithNewValues(streamDurationUs, j, z2, mediaItem, str));
    }

    private void removeUnusedItemDataEntries(int[] iArr) {
        HashSet hashSet = new HashSet(iArr.length * 2);
        int i = 0;
        for (int i2 : iArr) {
            hashSet.add(Integer.valueOf(i2));
        }
        while (i < this.itemIdToData.size()) {
            if (hashSet.contains(Integer.valueOf(this.itemIdToData.keyAt(i)))) {
                i++;
            } else {
                this.mediaItemsByContentId.remove(this.itemIdToData.valueAt(i).contentId);
                this.itemIdToData.removeAt(i);
            }
        }
    }
}

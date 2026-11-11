package androidx.media3.cast;

import androidx.media3.common.MediaItem;
import com.google.android.gms.cast.MediaQueueItem;

/* JADX INFO: loaded from: classes4.dex */
public interface MediaItemConverter {
    MediaItem toMediaItem(MediaQueueItem mediaQueueItem);

    MediaQueueItem toMediaQueueItem(MediaItem mediaItem);
}

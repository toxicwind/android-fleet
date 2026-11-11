package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public interface PreloadManagerListener {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.source.preload.PreloadManagerListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCompleted(PreloadManagerListener _this, MediaItem mediaItem) {
        }

        public static void $default$onError(PreloadManagerListener _this, PreloadException preloadException) {
        }
    }

    void onCompleted(MediaItem mediaItem);

    void onError(PreloadException preloadException);
}

package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.DataSpec;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface AdsLoader {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.source.ads.AdsLoader$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$handleContentTimelineChanged(AdsLoader _this, AdsMediaSource adsMediaSource, Timeline timeline) {
            return false;
        }
    }

    public interface EventListener {

        /* JADX INFO: renamed from: androidx.media3.exoplayer.source.ads.AdsLoader$EventListener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onAdClicked(EventListener _this) {
            }

            public static void $default$onAdLoadError(EventListener _this, AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec) {
            }

            public static void $default$onAdPlaybackState(EventListener _this, AdPlaybackState adPlaybackState) {
            }

            public static void $default$onAdTapped(EventListener _this) {
            }
        }

        void onAdClicked();

        void onAdLoadError(AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec);

        void onAdPlaybackState(AdPlaybackState adPlaybackState);

        void onAdTapped();
    }

    public interface Provider {
        AdsLoader getAdsLoader(MediaItem.AdsConfiguration adsConfiguration);
    }

    boolean handleContentTimelineChanged(AdsMediaSource adsMediaSource, Timeline timeline);

    void handlePrepareComplete(AdsMediaSource adsMediaSource, int i, int i2);

    void handlePrepareError(AdsMediaSource adsMediaSource, int i, int i2, IOException iOException);

    void release();

    void setPlayer(Player player);

    void setSupportedContentTypes(int... iArr);

    void start(AdsMediaSource adsMediaSource, DataSpec dataSpec, Object obj, AdViewProvider adViewProvider, EventListener eventListener);

    void stop(AdsMediaSource adsMediaSource, EventListener eventListener);
}

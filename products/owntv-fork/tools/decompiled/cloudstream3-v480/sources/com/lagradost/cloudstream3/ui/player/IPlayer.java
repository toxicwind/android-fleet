package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Rational;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\bH&J\u000f\u0010\t\u001a\u0004\u0018\u00010\nH&¢\u0006\u0002\u0010\u000bJ\u000f\u0010\f\u001a\u0004\u0018\u00010\nH&¢\u0006\u0002\u0010\u000bJ\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&J\u001a\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0012\u001a\u00020\nH&J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\nH&J0\u0010\u0015\u001a\u00020\u00052\u0014\b\u0001\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u00172\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aH'J\b\u0010\u001c\u001a\u00020\u0005H&J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020\u0005H&J\u0016\u0010!\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001aH&Jo\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\b2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\n2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\b\u00100\u001a\u0004\u0018\u00010/2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\b2\b\b\u0002\u00102\u001a\u00020\bH&¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H&J\u0012\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u0003H&J\b\u00108\u001a\u00020\bH&J\u0016\u00109\u001a\u00020\u00052\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H&J\u0012\u0010:\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010/H&J\n\u0010;\u001a\u0004\u0018\u00010/H&J\u001a\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020>2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&J\b\u0010?\u001a\u00020\u0005H&J\b\u0010@\u001a\u00020\u0005H&J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H&J\b\u0010B\u001a\u00020\u0005H&J\b\u0010C\u001a\u00020\bH&J\b\u0010D\u001a\u00020EH&J\n\u0010F\u001a\u0004\u0018\u00010GH&J(\u0010H\u001a\u00020\u00052\b\b\u0002\u0010I\u001a\u00020\u001b2\b\b\u0002\u0010J\u001a\u00020\u001b2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010LH&J/\u0010M\u001a\u00020\u00052\b\u0010N\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u001bH&¢\u0006\u0002\u0010PJ\u000e\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0\u001aH&¨\u0006SÀ\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "", "getPlaybackSpeed", "", "setPlaybackSpeed", "", "speed", "getIsPlaying", "", "getDuration", "", "()Ljava/lang/Long;", "getPosition", "seekTime", "time", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "seekTo", "getSubtitleOffset", "setSubtitleOffset", TypedValues.CycleType.S_WAVE_OFFSET, "initCallbacks", "eventHandler", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "requestedListeningPercentages", "", "", "releaseCallbacks", "updateSubtitleStyle", "style", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "saveData", "addTimeStamps", "timeStamps", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "loadPlayer", "context", "Landroid/content/Context;", "sameEpisode", "link", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "data", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "startPosition", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", MediaTrack.ROLE_SUBTITLE, "autoPlay", "preview", "(Landroid/content/Context;ZLcom/lagradost/cloudstream3/utils/ExtractorLink;Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;Ljava/lang/Long;Ljava/util/Set;Lcom/lagradost/cloudstream3/ui/player/SubtitleData;Ljava/lang/Boolean;Z)V", "reloadPlayer", "getPreview", "Landroid/graphics/Bitmap;", "fraction", "hasPreview", "setActiveSubtitles", "setPreferredSubtitles", "getCurrentPreferredSubtitle", "handleEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/lagradost/cloudstream3/ui/player/CSPlayerEvent;", "onStop", "onPause", "onResume", "release", "isActive", "getVideoTracks", "Lcom/lagradost/cloudstream3/ui/player/CurrentTracks;", "getAspectRatio", "Landroid/util/Rational;", "setMaxVideoSize", "width", "height", "id", "", "setPreferredAudioTrack", "trackLanguage", "formatIndex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getSubtitleCues", "Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IPlayer {
    void addTimeStamps(List<VideoSkipStamp> timeStamps);

    Rational getAspectRatio();

    SubtitleData getCurrentPreferredSubtitle();

    Long getDuration();

    boolean getIsPlaying();

    float getPlaybackSpeed();

    Long getPosition();

    Bitmap getPreview(float fraction);

    List<SubtitleCue> getSubtitleCues();

    long getSubtitleOffset();

    CurrentTracks getVideoTracks();

    void handleEvent(CSPlayerEvent event, PlayerEventSource source);

    boolean hasPreview();

    void initCallbacks(Function1<? super PlayerEvent, Unit> eventHandler, List<Integer> requestedListeningPercentages);

    boolean isActive();

    void loadPlayer(Context context, boolean sameEpisode, ExtractorLink link, ExtractorUri data, Long startPosition, Set<SubtitleData> subtitles, SubtitleData subtitle, Boolean autoPlay, boolean preview);

    void onPause();

    void onResume(Context context);

    void onStop();

    void release();

    void releaseCallbacks();

    void reloadPlayer(Context context);

    void saveData();

    void seekTime(long time, PlayerEventSource source);

    void seekTo(long time, PlayerEventSource source);

    void setActiveSubtitles(Set<SubtitleData> subtitles);

    void setMaxVideoSize(int width, int height, String id);

    void setPlaybackSpeed(float speed);

    void setPreferredAudioTrack(String trackLanguage, String id, Integer formatIndex);

    boolean setPreferredSubtitles(SubtitleData subtitle);

    void setSubtitleOffset(long offset);

    void updateSubtitleStyle(SaveCaptionStyle style);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.IPlayer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: IPlayer.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void seekTime$default(IPlayer iPlayer, long j, PlayerEventSource playerEventSource, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: seekTime");
            }
            if ((i & 2) != 0) {
                playerEventSource = PlayerEventSource.UI;
            }
            iPlayer.seekTime(j, playerEventSource);
        }

        public static /* synthetic */ void seekTo$default(IPlayer iPlayer, long j, PlayerEventSource playerEventSource, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: seekTo");
            }
            if ((i & 2) != 0) {
                playerEventSource = PlayerEventSource.UI;
            }
            iPlayer.seekTo(j, playerEventSource);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void initCallbacks$default(IPlayer iPlayer, Function1 function1, List list, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initCallbacks");
            }
            if ((i & 2) != 0) {
                list = null;
            }
            iPlayer.initCallbacks(function1, list);
        }

        public static /* synthetic */ void loadPlayer$default(IPlayer iPlayer, Context context, boolean z, ExtractorLink extractorLink, ExtractorUri extractorUri, Long l, Set set, SubtitleData subtitleData, Boolean bool, boolean z2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadPlayer");
            }
            if ((i & 4) != 0) {
                extractorLink = null;
            }
            if ((i & 8) != 0) {
                extractorUri = null;
            }
            if ((i & 16) != 0) {
                l = null;
            }
            if ((i & 128) != 0) {
                bool = true;
            }
            if ((i & 256) != 0) {
                z2 = true;
            }
            iPlayer.loadPlayer(context, z, extractorLink, extractorUri, l, set, subtitleData, bool, z2);
        }

        public static /* synthetic */ void handleEvent$default(IPlayer iPlayer, CSPlayerEvent cSPlayerEvent, PlayerEventSource playerEventSource, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleEvent");
            }
            if ((i & 2) != 0) {
                playerEventSource = PlayerEventSource.UI;
            }
            iPlayer.handleEvent(cSPlayerEvent, playerEventSource);
        }

        public static /* synthetic */ void setMaxVideoSize$default(IPlayer iPlayer, int i, int i2, String str, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setMaxVideoSize");
            }
            if ((i3 & 1) != 0) {
                i = Integer.MAX_VALUE;
            }
            if ((i3 & 2) != 0) {
                i2 = Integer.MAX_VALUE;
            }
            if ((i3 & 4) != 0) {
                str = null;
            }
            iPlayer.setMaxVideoSize(i, i2, str);
        }

        public static /* synthetic */ void setPreferredAudioTrack$default(IPlayer iPlayer, String str, String str2, Integer num, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setPreferredAudioTrack");
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                num = null;
            }
            iPlayer.setPreferredAudioTrack(str, str2, num);
        }
    }

    /* JADX INFO: compiled from: IPlayer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }
}

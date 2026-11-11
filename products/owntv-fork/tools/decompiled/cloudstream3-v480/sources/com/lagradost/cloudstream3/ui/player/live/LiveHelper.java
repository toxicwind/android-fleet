package com.lagradost.cloudstream3.ui.player.live;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.utils.AppDebug;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\u0006R&\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/live/LiveHelper;", "", "<init>", "()V", "liveManagers", "Ljava/util/WeakHashMap;", "Landroidx/media3/common/Player;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/ui/player/live/LiveManager;", "Landroidx/media3/common/Player$Listener;", "registerPlayer", "", "player", "unregisterPlayer", "getLiveManager", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveHelper {
    public static final LiveHelper INSTANCE = new LiveHelper();
    private static final WeakHashMap<Player, Pair<LiveManager, Player.Listener>> liveManagers = new WeakHashMap<>();

    private LiveHelper() {
    }

    public final LiveManager getLiveManager(Player player) {
        Pair<LiveManager, Player.Listener> pair = liveManagers.get(player);
        if (pair != null) {
            return pair.getFirst();
        }
        return null;
    }

    public final void registerPlayer(final Player player) {
        if (player != null) {
            WeakHashMap<Player, Pair<LiveManager, Player.Listener>> weakHashMap = liveManagers;
            if (weakHashMap.containsKey(player)) {
                return;
            }
            final LiveManager liveManager = new LiveManager(player);
            Player.Listener listener = new Player.Listener() { // from class: com.lagradost.cloudstream3.ui.player.live.LiveHelper$registerPlayer$listener$1
                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                    Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onAudioSessionIdChanged(int i) {
                    Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                    Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onCues(CueGroup cueGroup) {
                    Player.Listener.CC.$default$onCues(this, cueGroup);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onCues(List list) {
                    Player.Listener.CC.$default$onCues(this, list);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                    Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
                    Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onEvents(Player player2, Player.Events events) {
                    Player.Listener.CC.$default$onEvents(this, player2, events);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onIsLoadingChanged(boolean z) {
                    Player.Listener.CC.$default$onIsLoadingChanged(this, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onIsPlayingChanged(boolean z) {
                    Player.Listener.CC.$default$onIsPlayingChanged(this, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onLoadingChanged(boolean z) {
                    Player.Listener.CC.$default$onLoadingChanged(this, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                    Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
                    Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                    Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                    Player.Listener.CC.$default$onMetadata(this, metadata);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
                    Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlaybackStateChanged(int i) {
                    Player.Listener.CC.$default$onPlaybackStateChanged(this, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
                    Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                    Player.Listener.CC.$default$onPlayerError(this, playbackException);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                    Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
                    Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                    Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onPositionDiscontinuity(int i) {
                    Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onRenderedFirstFrame() {
                    Player.Listener.CC.$default$onRenderedFirstFrame(this);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onRepeatModeChanged(int i) {
                    Player.Listener.CC.$default$onRepeatModeChanged(this, i);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                    Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                    Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                    Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                    Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
                    Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                    Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onTracksChanged(Tracks tracks) {
                    Player.Listener.CC.$default$onTracksChanged(this, tracks);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                    Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
                }

                @Override // androidx.media3.common.Player.Listener
                public /* synthetic */ void onVolumeChanged(float f) {
                    Player.Listener.CC.$default$onVolumeChanged(this, f);
                }

                @Override // androidx.media3.common.Player.Listener
                public void onTimelineChanged(Timeline timeline, int reason) {
                    Intrinsics.checkNotNullParameter(timeline, "timeline");
                    Timeline.Window window = new Timeline.Window();
                    timeline.getWindow(player.getCurrentMediaItemIndex(), window);
                    if (window.isDynamic) {
                        liveManager.submitLivestreamChunk(new LivestreamChunk(window.getDurationMs(), 0L, 2, null));
                    }
                    Player.Listener.CC.$default$onTimelineChanged(this, timeline, reason);
                }

                @Override // androidx.media3.common.Player.Listener
                public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
                    Intrinsics.checkNotNullParameter(oldPosition, "oldPosition");
                    Intrinsics.checkNotNullParameter(newPosition, "newPosition");
                    Player.Listener.CC.$default$onPositionDiscontinuity(this, oldPosition, newPosition, reason);
                    long timeAheadOfLive = liveManager.getTimeAheadOfLive(newPosition.positionMs);
                    if (timeAheadOfLive > 100) {
                        player.seekTo(newPosition.positionMs - timeAheadOfLive);
                    }
                }
            };
            synchronized (weakHashMap) {
                player.addListener(listener);
                weakHashMap.put(player, TuplesKt.to(liveManager, listener));
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        if (AppDebug.INSTANCE.isDebug()) {
            ArchComponentExtKt.logError(new DebugException("LiveHelper registerPlayer called with null player!"));
        }
    }

    public final void unregisterPlayer(Player player) {
        if (player != null) {
            WeakHashMap<Player, Pair<LiveManager, Player.Listener>> weakHashMap = liveManagers;
            if (weakHashMap.containsKey(player)) {
                synchronized (weakHashMap) {
                    Pair<LiveManager, Player.Listener> pair = weakHashMap.get(player);
                    if (pair != null) {
                        player.removeListener(pair.component2());
                    }
                    weakHashMap.remove(player);
                }
                return;
            }
            return;
        }
        if (AppDebug.INSTANCE.isDebug()) {
            ArchComponentExtKt.logError(new DebugException("LiveHelper unregisterPlayer called with null player!"));
        }
    }
}

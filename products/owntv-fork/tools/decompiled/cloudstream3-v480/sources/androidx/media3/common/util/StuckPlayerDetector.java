package androidx.media3.common.util;

import android.os.Handler;
import android.os.Message;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class StuckPlayerDetector {
    private static final int MSG_STUCK_BUFFERING_TIMEOUT = 1;
    private static final int MSG_STUCK_PLAYING_NOT_ENDING_TIMEOUT = 3;
    private static final int MSG_STUCK_PLAYING_TIMEOUT = 2;
    private static final int MSG_STUCK_SUPPRESSED_TIMEOUT = 4;
    private final Callback callback;
    private final Clock clock;
    private final HandlerWrapper handler;
    private final Timeline.Period period = new Timeline.Period();
    private final Player player;
    private final Player.Listener playerListener;
    private final StuckBufferingDetector stuckBufferingDetector;
    private final StuckPlayingDetector stuckPlayingDetector;
    private final StuckPlayingNotEndingDetector stuckPlayingNotEndingDetector;
    private final StuckSuppressedDetector stuckSuppressedDetector;

    public interface Callback {
        void onStuckPlayerDetected(StuckPlayerException stuckPlayerException);
    }

    public StuckPlayerDetector(Player player, Callback callback, Clock clock, int i, int i2, int i3, int i4) {
        this.player = player;
        this.callback = callback;
        this.clock = clock;
        this.handler = clock.createHandler(player.getApplicationLooper(), new Handler.Callback() { // from class: androidx.media3.common.util.StuckPlayerDetector$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f$0.handleMessage(message);
            }
        });
        this.stuckBufferingDetector = new StuckBufferingDetector(i);
        this.stuckPlayingDetector = new StuckPlayingDetector(i2);
        this.stuckPlayingNotEndingDetector = new StuckPlayingNotEndingDetector(i3);
        this.stuckSuppressedDetector = new StuckSuppressedDetector(i4);
        Player.Listener listener = new Player.Listener() { // from class: androidx.media3.common.util.StuckPlayerDetector.1
            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioSessionIdChanged(int i5) {
                Player.Listener.CC.$default$onAudioSessionIdChanged(this, i5);
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
            public /* synthetic */ void onDeviceVolumeChanged(int i5, boolean z) {
                Player.Listener.CC.$default$onDeviceVolumeChanged(this, i5, z);
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
            public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i5) {
                Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMetadata(Metadata metadata) {
                Player.Listener.CC.$default$onMetadata(this, metadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i5) {
                Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackStateChanged(int i5) {
                Player.Listener.CC.$default$onPlaybackStateChanged(this, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i5) {
                Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i5);
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
            public /* synthetic */ void onPlayerStateChanged(boolean z, int i5) {
                Player.Listener.CC.$default$onPlayerStateChanged(this, z, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(int i5) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i5) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i5);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRenderedFirstFrame() {
                Player.Listener.CC.$default$onRenderedFirstFrame(this);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRepeatModeChanged(int i5) {
                Player.Listener.CC.$default$onRepeatModeChanged(this, i5);
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
            public /* synthetic */ void onSurfaceSizeChanged(int i5, int i6) {
                Player.Listener.CC.$default$onSurfaceSizeChanged(this, i5, i6);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTimelineChanged(Timeline timeline, int i5) {
                Player.Listener.CC.$default$onTimelineChanged(this, timeline, i5);
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
            public void onEvents(Player player2, Player.Events events) {
                StuckPlayerDetector.this.onPlayerEvents();
            }
        };
        this.playerListener = listener;
        player.addListener(listener);
    }

    public void release() {
        this.handler.removeCallbacksAndMessages(null);
        this.player.removeListener(this.playerListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlayerEvents() {
        this.stuckBufferingDetector.update();
        this.stuckPlayingDetector.update();
        this.stuckPlayingNotEndingDetector.update();
        this.stuckSuppressedDetector.update();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.stuckBufferingDetector.update();
            return true;
        }
        if (i == 2) {
            this.stuckPlayingDetector.update();
            return true;
        }
        if (i == 3) {
            this.stuckPlayingNotEndingDetector.update();
            return true;
        }
        if (i != 4) {
            return false;
        }
        this.stuckSuppressedDetector.update();
        return true;
    }

    private final class StuckBufferingDetector {
        private int adGroupIndex;
        private int adIndexInAdGroup;
        private long bufferedDurationInOtherPeriodsMs;
        private long bufferedPositionInPeriodMs;
        private boolean isBuffering;
        private Object periodUid;
        private long startRealtimeMs;
        private final int stuckBufferingTimeoutMs;

        public StuckBufferingDetector(int i) {
            this.stuckBufferingTimeoutMs = i;
        }

        public void update() {
            if (StuckPlayerDetector.this.player.getPlaybackState() == 2 && StuckPlayerDetector.this.player.getPlayWhenReady() && StuckPlayerDetector.this.player.getPlaybackSuppressionReason() == 0) {
                Timeline currentTimeline = StuckPlayerDetector.this.player.getCurrentTimeline();
                Object uidOfPeriod = currentTimeline.isEmpty() ? null : currentTimeline.getUidOfPeriod(StuckPlayerDetector.this.player.getCurrentPeriodIndex());
                int currentAdGroupIndex = StuckPlayerDetector.this.player.getCurrentAdGroupIndex();
                int currentAdIndexInAdGroup = StuckPlayerDetector.this.player.getCurrentAdIndexInAdGroup();
                long bufferedPosition = StuckPlayerDetector.this.player.getBufferedPosition();
                long jMax = Math.max(0L, StuckPlayerDetector.this.player.getTotalBufferedDuration() - Math.max(0L, bufferedPosition - StuckPlayerDetector.this.player.getCurrentPosition()));
                if (uidOfPeriod != null && currentAdGroupIndex == -1) {
                    bufferedPosition -= currentTimeline.getPeriodByUid(uidOfPeriod, StuckPlayerDetector.this.period).getPositionInWindowMs();
                }
                long jElapsedRealtime = StuckPlayerDetector.this.clock.elapsedRealtime();
                if (this.isBuffering && Objects.equals(uidOfPeriod, this.periodUid) && currentAdGroupIndex == this.adGroupIndex && currentAdIndexInAdGroup == this.adIndexInAdGroup && bufferedPosition == this.bufferedPositionInPeriodMs && jMax == this.bufferedDurationInOtherPeriodsMs) {
                    if (jElapsedRealtime - this.startRealtimeMs >= this.stuckBufferingTimeoutMs) {
                        StuckPlayerDetector.this.callback.onStuckPlayerDetected(new StuckPlayerException(1, this.stuckBufferingTimeoutMs));
                        return;
                    }
                    return;
                }
                this.isBuffering = true;
                this.startRealtimeMs = jElapsedRealtime;
                this.periodUid = uidOfPeriod;
                this.adGroupIndex = currentAdGroupIndex;
                this.adIndexInAdGroup = currentAdIndexInAdGroup;
                this.bufferedPositionInPeriodMs = bufferedPosition;
                this.bufferedDurationInOtherPeriodsMs = jMax;
                StuckPlayerDetector.this.handler.removeMessages(1);
                StuckPlayerDetector.this.handler.sendEmptyMessageDelayed(1, this.stuckBufferingTimeoutMs);
                return;
            }
            if (this.isBuffering) {
                StuckPlayerDetector.this.handler.removeMessages(1);
            }
            this.isBuffering = false;
        }
    }

    private final class StuckPlayingDetector {
        private int adGroupIndex;
        private int adIndexInAdGroup;
        private long currentPositionInPeriodMs;
        private boolean isPlaying;
        private Object periodUid;
        private long startRealtimeMs;
        private final int stuckPlayingTimeoutMs;

        public StuckPlayingDetector(int i) {
            this.stuckPlayingTimeoutMs = i;
        }

        public void update() {
            if (StuckPlayerDetector.this.player.isPlaying()) {
                Timeline currentTimeline = StuckPlayerDetector.this.player.getCurrentTimeline();
                Object uidOfPeriod = currentTimeline.isEmpty() ? null : currentTimeline.getUidOfPeriod(StuckPlayerDetector.this.player.getCurrentPeriodIndex());
                int currentAdGroupIndex = StuckPlayerDetector.this.player.getCurrentAdGroupIndex();
                int currentAdIndexInAdGroup = StuckPlayerDetector.this.player.getCurrentAdIndexInAdGroup();
                long currentPosition = StuckPlayerDetector.this.player.getCurrentPosition();
                if (uidOfPeriod != null && currentAdGroupIndex == -1) {
                    currentPosition -= currentTimeline.getPeriodByUid(uidOfPeriod, StuckPlayerDetector.this.period).getPositionInWindowMs();
                }
                long jElapsedRealtime = StuckPlayerDetector.this.clock.elapsedRealtime();
                if (this.isPlaying && Objects.equals(uidOfPeriod, this.periodUid) && currentAdGroupIndex == this.adGroupIndex && currentAdIndexInAdGroup == this.adIndexInAdGroup && currentPosition == this.currentPositionInPeriodMs) {
                    if (jElapsedRealtime - this.startRealtimeMs >= this.stuckPlayingTimeoutMs) {
                        StuckPlayerDetector.this.callback.onStuckPlayerDetected(new StuckPlayerException(2, this.stuckPlayingTimeoutMs));
                        return;
                    }
                    return;
                }
                this.isPlaying = true;
                this.startRealtimeMs = jElapsedRealtime;
                this.periodUid = uidOfPeriod;
                this.adGroupIndex = currentAdGroupIndex;
                this.adIndexInAdGroup = currentAdIndexInAdGroup;
                this.currentPositionInPeriodMs = currentPosition;
                StuckPlayerDetector.this.handler.removeMessages(2);
                StuckPlayerDetector.this.handler.sendEmptyMessageDelayed(2, this.stuckPlayingTimeoutMs);
                return;
            }
            if (this.isPlaying) {
                StuckPlayerDetector.this.handler.removeMessages(2);
            }
            this.isPlaying = false;
        }
    }

    private final class StuckPlayingNotEndingDetector {
        private int adGroupIndex;
        private int adIndexInAdGroup;
        private boolean isPlayingAndReachedDuration;
        private Object periodUid;
        private long startRealtimeMs;
        private final int stuckPlayingNotEndingTimeoutMs;

        public StuckPlayingNotEndingDetector(int i) {
            this.stuckPlayingNotEndingTimeoutMs = i;
        }

        public void update() {
            long duration;
            Timeline currentTimeline = StuckPlayerDetector.this.player.getCurrentTimeline();
            Object uidOfPeriod = currentTimeline.isEmpty() ? null : currentTimeline.getUidOfPeriod(StuckPlayerDetector.this.player.getCurrentPeriodIndex());
            int currentAdGroupIndex = StuckPlayerDetector.this.player.getCurrentAdGroupIndex();
            int currentAdIndexInAdGroup = StuckPlayerDetector.this.player.getCurrentAdIndexInAdGroup();
            long currentPosition = StuckPlayerDetector.this.player.getCurrentPosition();
            if (uidOfPeriod == null || currentAdGroupIndex != -1) {
                duration = currentAdGroupIndex != -1 ? StuckPlayerDetector.this.player.getDuration() : -9223372036854775807L;
            } else {
                currentTimeline.getPeriodByUid(uidOfPeriod, StuckPlayerDetector.this.period);
                currentPosition -= StuckPlayerDetector.this.period.getPositionInWindowMs();
                duration = StuckPlayerDetector.this.period.getDurationMs();
            }
            boolean zIsPlaying = StuckPlayerDetector.this.player.isPlaying();
            if (!zIsPlaying || duration == C.TIME_UNSET || currentPosition < duration) {
                StuckPlayerDetector.this.handler.removeMessages(3);
                if (zIsPlaying && duration != C.TIME_UNSET) {
                    StuckPlayerDetector.this.handler.sendEmptyMessageDelayed(3, (int) Math.ceil((duration - currentPosition) / StuckPlayerDetector.this.player.getPlaybackParameters().speed));
                }
                this.isPlayingAndReachedDuration = false;
                return;
            }
            long jElapsedRealtime = StuckPlayerDetector.this.clock.elapsedRealtime();
            if (this.isPlayingAndReachedDuration && Objects.equals(uidOfPeriod, this.periodUid) && currentAdGroupIndex == this.adGroupIndex && currentAdIndexInAdGroup == this.adIndexInAdGroup) {
                if (jElapsedRealtime - this.startRealtimeMs >= this.stuckPlayingNotEndingTimeoutMs) {
                    StuckPlayerDetector.this.callback.onStuckPlayerDetected(new StuckPlayerException(3, this.stuckPlayingNotEndingTimeoutMs));
                    return;
                }
                return;
            }
            this.isPlayingAndReachedDuration = true;
            this.startRealtimeMs = jElapsedRealtime;
            this.periodUid = uidOfPeriod;
            this.adGroupIndex = currentAdGroupIndex;
            this.adIndexInAdGroup = currentAdIndexInAdGroup;
            StuckPlayerDetector.this.handler.removeMessages(3);
            StuckPlayerDetector.this.handler.sendEmptyMessageDelayed(3, this.stuckPlayingNotEndingTimeoutMs);
        }
    }

    private final class StuckSuppressedDetector {
        private boolean isSuppressed;
        private long startRealtimeMs;
        private final int stuckSuppressedTimeoutMs;
        private int suppressionReason;

        public StuckSuppressedDetector(int i) {
            this.stuckSuppressedTimeoutMs = i;
        }

        public void update() {
            int playbackSuppressionReason = StuckPlayerDetector.this.player.getPlaybackSuppressionReason();
            if (StuckPlayerDetector.this.player.getPlayWhenReady() && StuckPlayerDetector.this.player.getPlaybackState() != 1 && StuckPlayerDetector.this.player.getPlaybackState() != 4 && playbackSuppressionReason != 0 && playbackSuppressionReason != 1) {
                long jElapsedRealtime = StuckPlayerDetector.this.clock.elapsedRealtime();
                if (this.isSuppressed && this.suppressionReason == playbackSuppressionReason) {
                    if (jElapsedRealtime - this.startRealtimeMs >= this.stuckSuppressedTimeoutMs) {
                        StuckPlayerDetector.this.callback.onStuckPlayerDetected(new StuckPlayerException(4, this.stuckSuppressedTimeoutMs));
                        return;
                    }
                    return;
                } else {
                    this.isSuppressed = true;
                    this.startRealtimeMs = jElapsedRealtime;
                    this.suppressionReason = playbackSuppressionReason;
                    StuckPlayerDetector.this.handler.removeMessages(4);
                    StuckPlayerDetector.this.handler.sendEmptyMessageDelayed(4, this.stuckSuppressedTimeoutMs);
                    return;
                }
            }
            if (this.isSuppressed) {
                StuckPlayerDetector.this.handler.removeMessages(4);
            }
            this.isSuppressed = false;
        }
    }
}

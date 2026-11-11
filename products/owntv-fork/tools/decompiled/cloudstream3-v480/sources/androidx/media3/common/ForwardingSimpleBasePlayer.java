package androidx.media3.common;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.text.CueGroup;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: loaded from: classes4.dex */
public class ForwardingSimpleBasePlayer extends SimpleBasePlayer {
    private Metadata lastTimedMetadata;
    private LivePositionSuppliers livePositionSuppliers;
    private int pendingDiscontinuityReason;
    private boolean pendingFirstFrameRendered;
    private long pendingPositionDiscontinuityNewPositionMs;
    private int playWhenReadyChangeReason;
    private Player player;
    private final Player.Listener playerListener;

    public ForwardingSimpleBasePlayer(Player player) {
        super(player.getApplicationLooper());
        initializeForwardingState(player);
        PlayerListener playerListener = new PlayerListener();
        this.playerListener = playerListener;
        player.addListener(playerListener);
    }

    protected final void setPlayer(Player player) {
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player.getApplicationLooper() != player2.getApplicationLooper()) {
            throw new IllegalArgumentException("Trying to swap players with non-matching loopers.");
        }
        player2.removeListener(this.playerListener);
        player.addListener(this.playerListener);
        initializeForwardingState(player);
        this.pendingPositionDiscontinuityNewPositionMs = player.getCurrentPosition();
        invalidateState();
    }

    protected final Player getPlayer() {
        return this.player;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected SimpleBasePlayer.State getState() {
        Tracks currentTracks;
        SimpleBasePlayer.State.Builder builder = new SimpleBasePlayer.State.Builder();
        LivePositionSuppliers livePositionSuppliers = this.livePositionSuppliers;
        if (this.player.isCommandAvailable(16)) {
            builder.setAdBufferedPositionMs(livePositionSuppliers.bufferedPositionSupplier);
            builder.setAdPositionMs(livePositionSuppliers.currentPositionSupplier);
        }
        if (this.player.isCommandAvailable(21)) {
            builder.setAudioAttributes(this.player.getAudioAttributes());
        }
        builder.setAvailableCommands(this.player.getAvailableCommands());
        if (this.player.isCommandAvailable(16)) {
            builder.setContentBufferedPositionMs(livePositionSuppliers.contentBufferedPositionSupplier);
            builder.setContentPositionMs(livePositionSuppliers.contentPositionSupplier);
            if (this.player.isCommandAvailable(17)) {
                builder.setCurrentAd(this.player.getCurrentAdGroupIndex(), this.player.getCurrentAdIndexInAdGroup());
            }
        }
        if (this.player.isCommandAvailable(28)) {
            builder.setCurrentCues(this.player.getCurrentCues());
        }
        if (this.player.isCommandAvailable(17)) {
            builder.setCurrentMediaItemIndex(this.player.getCurrentMediaItemIndex());
        }
        builder.setDeviceInfo(this.player.getDeviceInfo());
        if (this.player.isCommandAvailable(23)) {
            builder.setDeviceVolume(this.player.getDeviceVolume());
            builder.setIsDeviceMuted(this.player.isDeviceMuted());
        }
        builder.setIsLoading(this.player.isLoading());
        builder.setMaxSeekToPreviousPositionMs(this.player.getMaxSeekToPreviousPosition());
        if (this.pendingFirstFrameRendered) {
            builder.setNewlyRenderedFirstFrame(true);
            this.pendingFirstFrameRendered = false;
        }
        builder.setPlaybackParameters(this.player.getPlaybackParameters());
        builder.setPlaybackState(this.player.getPlaybackState());
        builder.setPlaybackSuppressionReason(this.player.getPlaybackSuppressionReason());
        builder.setPlayerError(this.player.getPlayerError());
        if (this.player.isCommandAvailable(17)) {
            if (this.player.isCommandAvailable(30)) {
                currentTracks = this.player.getCurrentTracks();
            } else {
                currentTracks = Tracks.EMPTY;
            }
            builder.setPlaylist(this.player.getCurrentTimeline(), currentTracks, this.player.isCommandAvailable(18) ? this.player.getMediaMetadata() : null);
        }
        if (this.player.isCommandAvailable(18)) {
            builder.setPlaylistMetadata(this.player.getPlaylistMetadata());
        }
        builder.setPlayWhenReady(this.player.getPlayWhenReady(), this.playWhenReadyChangeReason);
        long j = this.pendingPositionDiscontinuityNewPositionMs;
        if (j != C.TIME_UNSET) {
            builder.setPositionDiscontinuity(this.pendingDiscontinuityReason, j);
            this.pendingPositionDiscontinuityNewPositionMs = C.TIME_UNSET;
        }
        builder.setRepeatMode(this.player.getRepeatMode());
        builder.setSeekBackIncrementMs(this.player.getSeekBackIncrement());
        builder.setSeekForwardIncrementMs(this.player.getSeekForwardIncrement());
        builder.setShuffleModeEnabled(this.player.getShuffleModeEnabled());
        builder.setSurfaceSize(this.player.getSurfaceSize());
        builder.setTimedMetadata(this.lastTimedMetadata);
        if (this.player.isCommandAvailable(16)) {
            builder.setTotalBufferedDurationMs(livePositionSuppliers.totalBufferedPositionSupplier);
        }
        builder.setTrackSelectionParameters(this.player.getTrackSelectionParameters());
        builder.setVideoSize(this.player.getVideoSize());
        if (this.player.isCommandAvailable(22)) {
            builder.setVolume(this.player.getVolume());
        }
        return builder.build();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        this.player.setPlayWhenReady(z);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handlePrepare() {
        this.player.prepare();
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleStop() {
        this.player.stop();
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleRelease() {
        this.player.release();
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetRepeatMode(int i) {
        this.player.setRepeatMode(i);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetShuffleModeEnabled(boolean z) {
        this.player.setShuffleModeEnabled(z);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        this.player.setPlaybackParameters(playbackParameters);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        this.player.setTrackSelectionParameters(trackSelectionParameters);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        this.player.setPlaylistMetadata(mediaMetadata);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected final ListenableFuture<?> handleSetVolume(float f) {
        this.player.setVolume(f);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetVolume(float f, int i) {
        if (i == 0) {
            this.player.setVolume(f);
        } else if (i == 1) {
            this.player.mute();
        } else if (i == 2) {
            this.player.unmute();
        } else {
            throw new IllegalStateException("Unknown volume operation type: " + i);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetDeviceVolume(int i, int i2) {
        boolean zIsCommandAvailable = this.player.isCommandAvailable(33);
        Player player = this.player;
        if (zIsCommandAvailable) {
            player.setDeviceVolume(i, i2);
        } else {
            player.setDeviceVolume(i);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleIncreaseDeviceVolume(int i) {
        boolean zIsCommandAvailable = this.player.isCommandAvailable(34);
        Player player = this.player;
        if (zIsCommandAvailable) {
            player.increaseDeviceVolume(i);
        } else {
            player.increaseDeviceVolume();
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleDecreaseDeviceVolume(int i) {
        boolean zIsCommandAvailable = this.player.isCommandAvailable(34);
        Player player = this.player;
        if (zIsCommandAvailable) {
            player.decreaseDeviceVolume(i);
        } else {
            player.decreaseDeviceVolume();
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetDeviceMuted(boolean z, int i) {
        boolean zIsCommandAvailable = this.player.isCommandAvailable(34);
        Player player = this.player;
        if (zIsCommandAvailable) {
            player.setDeviceMuted(z, i);
        } else {
            player.setDeviceMuted(z);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        this.player.setAudioAttributes(audioAttributes, z);
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.setVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.setVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.setVideoSurfaceHolder((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.player.setVideoSurface((Surface) obj);
        } else {
            throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleClearVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.clearVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.clearVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.clearVideoSurfaceHolder((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.player.clearVideoSurface((Surface) obj);
        } else if (obj == null) {
            this.player.clearVideoSurface();
        } else {
            throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetMediaItems(List<MediaItem> list, int i, long j) {
        boolean z = list.size() == 1 && this.player.isCommandAvailable(31);
        if (i == -1) {
            Player player = this.player;
            if (z) {
                player.setMediaItem(list.get(0));
            } else {
                player.setMediaItems(list);
            }
        } else {
            Player player2 = this.player;
            if (z) {
                player2.setMediaItem(list.get(0), j);
            } else {
                player2.setMediaItems(list, i, j);
            }
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleAddMediaItems(int i, List<MediaItem> list) {
        int size = list.size();
        Player player = this.player;
        if (size == 1) {
            player.addMediaItem(i, list.get(0));
        } else {
            player.addMediaItems(i, list);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleMoveMediaItems(int i, int i2, int i3) {
        int i4 = i + 1;
        Player player = this.player;
        if (i2 == i4) {
            player.moveMediaItem(i, i3);
        } else {
            player.moveMediaItems(i, i2, i3);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleReplaceMediaItems(int i, int i2, List<MediaItem> list) {
        if (i2 == i + 1 && list.size() == 1) {
            this.player.replaceMediaItem(i, list.get(0));
        } else {
            this.player.replaceMediaItems(i, i2, list);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleRemoveMediaItems(int i, int i2) {
        int i3 = i + 1;
        Player player = this.player;
        if (i2 == i3) {
            player.removeMediaItem(i);
        } else {
            player.removeMediaItems(i, i2);
        }
        return Futures.immediateVoidFuture();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSeek(int i, long j, int i2) {
        switch (i2) {
            case 4:
                this.player.seekToDefaultPosition();
                break;
            case 5:
                this.player.seekTo(j);
                break;
            case 6:
                this.player.seekToPreviousMediaItem();
                break;
            case 7:
                this.player.seekToPrevious();
                break;
            case 8:
                this.player.seekToNextMediaItem();
                break;
            case 9:
                this.player.seekToNext();
                break;
            case 10:
                if (i != -1) {
                    this.player.seekTo(i, j);
                }
                break;
            case 11:
                this.player.seekBack();
                break;
            case 12:
                this.player.seekForward();
                break;
            default:
                throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    @EnsuresNonNull({"this.player", "lastTimedMetadata", "playWhenReadyChangeReason", "pendingDiscontinuityReason", "livePositionSuppliers"})
    private void initializeForwardingState(Player player) {
        this.player = player;
        this.lastTimedMetadata = new Metadata(C.TIME_UNSET, new Metadata.Entry[0]);
        this.playWhenReadyChangeReason = 1;
        this.pendingDiscontinuityReason = 5;
        this.livePositionSuppliers = new LivePositionSuppliers(player);
    }

    private static final class LivePositionSuppliers {
        public final SimpleBasePlayer.LivePositionSupplier bufferedPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier contentBufferedPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier contentPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier currentPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier totalBufferedPositionSupplier;

        public LivePositionSuppliers(final Player player) {
            Objects.requireNonNull(player);
            this.currentPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.ForwardingSimpleBasePlayer$LivePositionSuppliers$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    return player.getCurrentPosition();
                }
            });
            Objects.requireNonNull(player);
            this.bufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.ForwardingSimpleBasePlayer$LivePositionSuppliers$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    return player.getBufferedPosition();
                }
            });
            Objects.requireNonNull(player);
            this.contentPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.ForwardingSimpleBasePlayer$LivePositionSuppliers$$ExternalSyntheticLambda2
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    return player.getContentPosition();
                }
            });
            Objects.requireNonNull(player);
            this.contentBufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.ForwardingSimpleBasePlayer$LivePositionSuppliers$$ExternalSyntheticLambda3
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    return player.getContentBufferedPosition();
                }
            });
            Objects.requireNonNull(player);
            this.totalBufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.ForwardingSimpleBasePlayer$LivePositionSuppliers$$ExternalSyntheticLambda4
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    return player.getTotalBufferedDuration();
                }
            });
        }

        public void disconnect(long j, long j2) {
            this.currentPositionSupplier.disconnect(j);
            this.bufferedPositionSupplier.disconnect(j);
            this.contentPositionSupplier.disconnect(j2);
            this.contentBufferedPositionSupplier.disconnect(j2);
            this.totalBufferedPositionSupplier.disconnect(0L);
        }
    }

    private class PlayerListener implements Player.Listener {
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
        public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
            Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
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

        private PlayerListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMetadata(Metadata metadata) {
            ForwardingSimpleBasePlayer.this.lastTimedMetadata = metadata;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean z, int i) {
            ForwardingSimpleBasePlayer.this.playWhenReadyChangeReason = i;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            ForwardingSimpleBasePlayer.this.pendingDiscontinuityReason = i;
            ForwardingSimpleBasePlayer.this.pendingPositionDiscontinuityNewPositionMs = positionInfo2.positionMs;
            ForwardingSimpleBasePlayer.this.livePositionSuppliers.disconnect(positionInfo.positionMs, positionInfo.contentPositionMs);
            ForwardingSimpleBasePlayer.this.livePositionSuppliers = new LivePositionSuppliers(ForwardingSimpleBasePlayer.this.player);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            ForwardingSimpleBasePlayer.this.pendingFirstFrameRendered = true;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            ForwardingSimpleBasePlayer.this.invalidateState();
        }
    }
}

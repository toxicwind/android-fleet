package androidx.media3.cast;

import android.content.Context;
import android.media.MediaRouter2;
import android.media.MediaRouter2$RouteCallback;
import android.media.MediaRouter2$TransferCallback;
import android.media.RouteDiscoveryPreference;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Range;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BasePlayer;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.ModuleUnavailableException;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import j$.util.Objects;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes4.dex */
public final class RemoteCastPlayer extends BasePlayer {
    private static final long[] EMPTY_TRACK_ID_ARRAY;
    public static final float MAX_SPEED_SUPPORTED = 2.0f;
    private static final int MAX_VOLUME = 20;
    public static final float MIN_SPEED_SUPPORTED = 0.5f;
    static final Player.Commands PERMANENT_AVAILABLE_COMMANDS;
    private static final long PROGRESS_REPORT_PERIOD_MS = 1000;
    private static final String TAG = "RemoteCastPlayer";
    private final Api30Impl api30Impl;
    private Player.Commands availableCommands;
    private final CastContext castContext;
    private final Cast.Listener castListener;
    private CastSession castSession;
    private CastTimeline currentTimeline;
    private Tracks currentTracks;
    private int currentWindowIndex;
    private DeviceInfo deviceInfo;
    private int deviceVolume;
    private SessionAvailabilityListener internalSessionAvailabilityListener;
    private boolean isMuted;
    private long lastReportedPositionMs;
    private final ListenerSet<Player.Listener> listeners;
    private final long maxSeekToPreviousPositionMs;
    private final MediaItemConverter mediaItemConverter;
    private MediaMetadata mediaMetadata;
    private final MediaQueue.Callback mediaQueueCallback;
    private Player.PositionInfo pendingMediaItemRemovalPosition;
    private int pendingSeekCount;
    private long pendingSeekPositionMs;
    private int pendingSeekWindowIndex;
    private final Timeline.Period period;
    private final StateHolder<Boolean> playWhenReady;
    private final PlaybackException playbackException;
    private final StateHolder<PlaybackParameters> playbackParameters;
    private int playbackState;
    private MediaMetadata playlistMetadata;
    private RemoteMediaClient remoteMediaClient;
    private final StateHolder<Integer> repeatMode;
    private final long seekBackIncrementMs;
    private final long seekForwardIncrementMs;
    private final SeekResultCallback seekResultCallback;
    private SessionAvailabilityListener sessionAvailabilityListener;
    private final StatusListener statusListener;
    private final CastTimelineTracker timelineTracker;
    private final StateHolder<Float> volume;
    public static final DeviceInfo DEVICE_INFO_REMOTE_EMPTY = new DeviceInfo.Builder(1).setMaxVolume(20).build();
    private static final Range<Integer> RANGE_DEVICE_VOLUME = new Range<>(0, 20);
    private static final Range<Float> RANGE_VOLUME = new Range<>(Float.valueOf(0.0f), Float.valueOf(1.0f));

    @Override // androidx.media3.common.Player
    public void clearVideoSurface() {
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurface(Surface surface) {
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
    }

    @Override // androidx.media3.common.Player
    public void clearVideoTextureView(TextureView textureView) {
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdGroupIndex() {
        return -1;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdIndexInAdGroup() {
        return -1;
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackSuppressionReason() {
        return 0;
    }

    @Override // androidx.media3.common.Player
    public boolean getShuffleModeEnabled() {
        return false;
    }

    @Override // androidx.media3.common.Player
    public boolean isLoading() {
        return false;
    }

    @Override // androidx.media3.common.Player
    public boolean isPlayingAd() {
        return false;
    }

    @Override // androidx.media3.common.Player
    public void mute() {
    }

    @Override // androidx.media3.common.Player
    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
    }

    @Override // androidx.media3.common.Player
    public void setShuffleModeEnabled(boolean z) {
    }

    @Override // androidx.media3.common.Player
    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurface(Surface surface) {
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurfaceView(SurfaceView surfaceView) {
    }

    @Override // androidx.media3.common.Player
    public void setVideoTextureView(TextureView textureView) {
    }

    @Override // androidx.media3.common.Player
    public void unmute() {
    }

    static /* synthetic */ int access$2006(RemoteCastPlayer remoteCastPlayer) {
        int i = remoteCastPlayer.pendingSeekCount - 1;
        remoteCastPlayer.pendingSeekCount = i;
        return i;
    }

    public static final class Builder {
        private boolean buildCalled;
        private final Context context;
        private MediaItemConverter mediaItemConverter = new DefaultMediaItemConverter();
        private long seekBackIncrementMs = 5000;
        private long seekForwardIncrementMs = 15000;
        private long maxSeekToPreviousPositionMs = 3000;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMediaItemConverter(MediaItemConverter mediaItemConverter) {
            Preconditions.checkState(!this.buildCalled);
            this.mediaItemConverter = (MediaItemConverter) Preconditions.checkNotNull(mediaItemConverter);
            return this;
        }

        public Builder setSeekBackIncrementMs(long j) {
            Preconditions.checkArgument(j > 0);
            Preconditions.checkState(!this.buildCalled);
            this.seekBackIncrementMs = j;
            return this;
        }

        public Builder setSeekForwardIncrementMs(long j) {
            Preconditions.checkArgument(j > 0);
            Preconditions.checkState(!this.buildCalled);
            this.seekForwardIncrementMs = j;
            return this;
        }

        public Builder setMaxSeekToPreviousPositionMs(long j) {
            Preconditions.checkArgument(j >= 0);
            Preconditions.checkState(!this.buildCalled);
            this.maxSeekToPreviousPositionMs = j;
            return this;
        }

        public RemoteCastPlayer build() {
            Preconditions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new RemoteCastPlayer(this);
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.cast");
        PERMANENT_AVAILABLE_COMMANDS = new Player.Commands.Builder().addAll(1, 2, 3, 4, 10, 23, 26, 34, 25, 33, 15, 13, 16, 17, 18, 19, 31, 20, 30, 32).build();
        EMPTY_TRACK_ID_ARRAY = new long[0];
    }

    private RemoteCastPlayer(Builder builder) {
        this(builder.context, getSharedInstantSafely(builder.context), builder.mediaItemConverter, builder.seekBackIncrementMs, builder.seekForwardIncrementMs, builder.maxSeekToPreviousPositionMs);
    }

    RemoteCastPlayer(Context context, CastContext castContext, MediaItemConverter mediaItemConverter, long j, long j2, long j3) {
        Player.Commands commandsBuild;
        Preconditions.checkArgument(j > 0 && j2 > 0);
        Preconditions.checkArgument(j3 >= 0);
        if (castContext != null) {
            Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.3] [" + Util.DEVICE_DEBUG_INFO + "]");
        }
        this.castContext = castContext;
        this.mediaItemConverter = mediaItemConverter;
        this.seekBackIncrementMs = j;
        this.seekForwardIncrementMs = j2;
        this.maxSeekToPreviousPositionMs = j3;
        this.timelineTracker = new CastTimelineTracker(mediaItemConverter);
        this.period = new Timeline.Period();
        this.castListener = new CastListener();
        StatusListener statusListener = new StatusListener();
        this.statusListener = statusListener;
        this.mediaQueueCallback = new MediaQueueCallback();
        this.seekResultCallback = new SeekResultCallback();
        this.listeners = new ListenerSet<>(Looper.getMainLooper(), Clock.DEFAULT, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda13
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                this.f$0.m7370lambda$new$0$androidxmedia3castRemoteCastPlayer((Player.Listener) obj, flagSet);
            }
        });
        this.playWhenReady = new StateHolder<>(false);
        this.repeatMode = new StateHolder<>(0);
        this.deviceVolume = 20;
        this.volume = new StateHolder<>(Float.valueOf(1.0f));
        this.playbackParameters = new StateHolder<>(PlaybackParameters.DEFAULT);
        this.playbackState = 1;
        this.playbackException = castContext == null ? new PlaybackException("Failed to initialize Cast. This may be due to Google Play services not being available, see https://support.google.com/googleplay/answer/9037938", new ModuleUnavailableException(new Exception("Module Unavailable")), 1001) : null;
        this.currentTimeline = CastTimeline.EMPTY_CAST_TIMELINE;
        this.mediaMetadata = MediaMetadata.EMPTY;
        this.playlistMetadata = MediaMetadata.EMPTY;
        this.currentTracks = Tracks.EMPTY;
        if (castContext == null) {
            commandsBuild = new Player.Commands.Builder().build();
        } else {
            commandsBuild = new Player.Commands.Builder().addAll(PERMANENT_AVAILABLE_COMMANDS).build();
        }
        this.availableCommands = commandsBuild;
        this.pendingSeekWindowIndex = -1;
        this.pendingSeekPositionMs = C.TIME_UNSET;
        if (castContext != null) {
            SessionManager sessionManager = castContext.getSessionManager();
            sessionManager.addSessionManagerListener(statusListener, CastSession.class);
            setCastSession(sessionManager.getCurrentCastSession());
        }
        updateInternalStateAndNotifyIfChanged();
        if (Build.VERSION.SDK_INT >= 30 && context != null) {
            Api30Impl api30Impl = new Api30Impl(context);
            this.api30Impl = api30Impl;
            api30Impl.initialize();
            this.deviceInfo = api30Impl.fetchDeviceInfo();
            return;
        }
        this.api30Impl = null;
        this.deviceInfo = DEVICE_INFO_REMOTE_EMPTY;
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7370lambda$new$0$androidxmedia3castRemoteCastPlayer(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this, new Player.Events(flagSet));
    }

    public MediaQueueItem getItem(int i) {
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null || this.currentTimeline.getIndexOfPeriod(Integer.valueOf(i)) == -1) {
            return null;
        }
        return mediaStatus.getItemById(i);
    }

    public boolean isCastSessionAvailable() {
        return this.remoteMediaClient != null;
    }

    public void setSessionAvailabilityListener(SessionAvailabilityListener sessionAvailabilityListener) {
        this.sessionAvailabilityListener = sessionAvailabilityListener;
    }

    void setInternalSessionAvailabilityListener(SessionAvailabilityListener sessionAvailabilityListener) {
        this.internalSessionAvailabilityListener = sessionAvailabilityListener;
    }

    @Override // androidx.media3.common.Player
    public Looper getApplicationLooper() {
        return Looper.getMainLooper();
    }

    @Override // androidx.media3.common.Player
    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.common.Player
    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> list, boolean z) {
        setMediaItems(list, z ? 0 : getCurrentMediaItemIndex(), z ? C.TIME_UNSET : getContentPosition());
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> list, int i, long j) {
        setMediaItemsInternal(list, i, j, this.repeatMode.value.intValue());
    }

    @Override // androidx.media3.common.Player
    public void addMediaItems(int i, List<MediaItem> list) {
        Preconditions.checkArgument(i >= 0);
        addMediaItemsInternal(list, i < this.currentTimeline.getWindowCount() ? ((Integer) this.currentTimeline.getWindow(i, this.window).uid).intValue() : 0);
    }

    @Override // androidx.media3.common.Player
    public void moveMediaItems(int i, int i2, int i3) {
        Preconditions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
        int windowCount = this.currentTimeline.getWindowCount();
        int iMin = Math.min(i2, windowCount);
        int i4 = iMin - i;
        int iMin2 = Math.min(i3, windowCount - i4);
        if (i >= windowCount || i == iMin || i == iMin2) {
            return;
        }
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = ((Integer) this.currentTimeline.getWindow(i5 + i, this.window).uid).intValue();
        }
        moveMediaItemsInternal(iArr, i, iMin2);
    }

    @Override // androidx.media3.common.Player
    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        Preconditions.checkArgument(i >= 0 && i <= i2);
        int windowCount = this.currentTimeline.getWindowCount();
        if (i > windowCount) {
            return;
        }
        int iMin = Math.min(i2, windowCount);
        addMediaItems(iMin, list);
        removeMediaItems(i, iMin);
    }

    @Override // androidx.media3.common.Player
    public void removeMediaItems(int i, int i2) {
        Preconditions.checkArgument(i >= 0 && i2 >= i);
        int windowCount = this.currentTimeline.getWindowCount();
        int iMin = Math.min(i2, windowCount);
        if (i >= windowCount || i == iMin) {
            return;
        }
        int i3 = iMin - i;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = ((Integer) this.currentTimeline.getWindow(i4 + i, this.window).uid).intValue();
        }
        removeMediaItemsInternal(iArr);
    }

    @Override // androidx.media3.common.Player
    public Player.Commands getAvailableCommands() {
        return this.availableCommands;
    }

    @Override // androidx.media3.common.Player
    public void prepare() {
        if (this.playbackException != null) {
            this.listeners.sendEvent(10, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda17
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    this.f$0.m7371lambda$prepare$1$androidxmedia3castRemoteCastPlayer((Player.Listener) obj);
                }
            });
            this.listeners.sendEvent(10, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda18
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    this.f$0.m7372lambda$prepare$2$androidxmedia3castRemoteCastPlayer((Player.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$prepare$1$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7371lambda$prepare$1$androidxmedia3castRemoteCastPlayer(Player.Listener listener) {
        listener.onPlayerErrorChanged(this.playbackException);
    }

    /* JADX INFO: renamed from: lambda$prepare$2$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7372lambda$prepare$2$androidxmedia3castRemoteCastPlayer(Player.Listener listener) {
        listener.onPlayerError(this.playbackException);
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackState() {
        if (getCurrentTimeline().isEmpty()) {
            return 1;
        }
        return this.playbackState;
    }

    @Override // androidx.media3.common.Player
    public PlaybackException getPlayerError() {
        return this.playbackException;
    }

    @Override // androidx.media3.common.Player
    public void setPlayWhenReady(boolean z) {
        if (this.remoteMediaClient == null) {
            return;
        }
        setPlayerStateAndNotifyIfChanged(z, 1, this.playbackState);
        this.listeners.flushEvents();
        if (getMediaStatus() == null) {
            return;
        }
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResultPlay = z ? remoteMediaClient.play() : remoteMediaClient.pause();
        this.playWhenReady.pendingResultCallback = new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: androidx.media3.cast.RemoteCastPlayer.1
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                if (RemoteCastPlayer.this.remoteMediaClient != null) {
                    RemoteCastPlayer.this.updatePlayerStateAndNotifyIfChanged(this);
                    RemoteCastPlayer.this.listeners.flushEvents();
                }
            }
        };
        pendingResultPlay.setResultCallback(this.playWhenReady.pendingResultCallback);
    }

    @Override // androidx.media3.common.Player
    public boolean getPlayWhenReady() {
        return this.playWhenReady.value.booleanValue();
    }

    @Override // androidx.media3.common.BasePlayer
    protected void seekTo(int i, long j, int i2, boolean z) {
        if (i == -1) {
            return;
        }
        Preconditions.checkArgument(i >= 0);
        if (this.currentTimeline.isEmpty() || i < this.currentTimeline.getWindowCount()) {
            MediaStatus mediaStatus = getMediaStatus();
            if (j == C.TIME_UNSET) {
                j = 0;
            }
            if (mediaStatus != null) {
                int currentMediaItemIndex = getCurrentMediaItemIndex();
                RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
                if (currentMediaItemIndex != i) {
                    remoteMediaClient.queueJumpToItem(((Integer) this.currentTimeline.getPeriod(i, this.period).uid).intValue(), j, null).setResultCallback(this.seekResultCallback);
                } else {
                    remoteMediaClient.seek(j).setResultCallback(this.seekResultCallback);
                }
                final Player.PositionInfo currentPositionInfo = getCurrentPositionInfo();
                this.pendingSeekCount++;
                this.pendingSeekWindowIndex = i;
                this.pendingSeekPositionMs = j;
                final Player.PositionInfo currentPositionInfo2 = getCurrentPositionInfo();
                this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda8
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        RemoteCastPlayer.lambda$seekTo$3(currentPositionInfo, currentPositionInfo2, (Player.Listener) obj);
                    }
                });
                if (currentPositionInfo.mediaItemIndex != currentPositionInfo2.mediaItemIndex) {
                    final MediaItem mediaItem = getCurrentTimeline().getWindow(i, this.window).mediaItem;
                    this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda9
                        @Override // androidx.media3.common.util.ListenerSet.Event
                        public final void invoke(Object obj) {
                            ((Player.Listener) obj).onMediaItemTransition(mediaItem, 2);
                        }
                    });
                    MediaMetadata mediaMetadata = this.mediaMetadata;
                    MediaMetadata mediaMetadataInternal = getMediaMetadataInternal();
                    this.mediaMetadata = mediaMetadataInternal;
                    if (!mediaMetadata.equals(mediaMetadataInternal)) {
                        this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda10
                            @Override // androidx.media3.common.util.ListenerSet.Event
                            public final void invoke(Object obj) {
                                this.f$0.m7373lambda$seekTo$5$androidxmedia3castRemoteCastPlayer((Player.Listener) obj);
                            }
                        });
                    }
                }
                updateAvailableCommandsAndNotifyIfChanged();
            }
            this.listeners.flushEvents();
        }
    }

    static /* synthetic */ void lambda$seekTo$3(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(1);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, 1);
    }

    /* JADX INFO: renamed from: lambda$seekTo$5$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7373lambda$seekTo$5$androidxmedia3castRemoteCastPlayer(Player.Listener listener) {
        listener.onMediaMetadataChanged(this.mediaMetadata);
    }

    @Override // androidx.media3.common.Player
    public long getSeekBackIncrement() {
        return this.seekBackIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public long getSeekForwardIncrement() {
        return this.seekForwardIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public long getMaxSeekToPreviousPosition() {
        return this.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.common.Player
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters.value;
    }

    @Override // androidx.media3.common.Player
    public void stop() {
        this.playbackState = 1;
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        if (remoteMediaClient != null) {
            remoteMediaClient.stop();
        }
    }

    @Override // androidx.media3.common.Player
    public void release() {
        Api30Impl api30Impl;
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.3] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        if (Build.VERSION.SDK_INT >= 30 && (api30Impl = this.api30Impl) != null) {
            api30Impl.release();
        }
        CastContext castContext = this.castContext;
        if (castContext != null) {
            SessionManager sessionManager = castContext.getSessionManager();
            sessionManager.removeSessionManagerListener(this.statusListener, CastSession.class);
            sessionManager.endCurrentSession(false);
        }
    }

    @Override // androidx.media3.common.Player
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.remoteMediaClient == null) {
            return;
        }
        PlaybackParameters playbackParameters2 = new PlaybackParameters(Util.constrainValue(playbackParameters.speed, 0.5f, 2.0f));
        setPlaybackParametersAndNotifyIfChanged(playbackParameters2);
        this.listeners.flushEvents();
        PendingResult<RemoteMediaClient.MediaChannelResult> playbackRate = this.remoteMediaClient.setPlaybackRate(playbackParameters2.speed, null);
        this.playbackParameters.pendingResultCallback = new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: androidx.media3.cast.RemoteCastPlayer.2
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                if (RemoteCastPlayer.this.remoteMediaClient != null) {
                    RemoteCastPlayer.this.updatePlaybackRateAndNotifyIfChanged(this);
                    RemoteCastPlayer.this.listeners.flushEvents();
                }
            }
        };
        playbackRate.setResultCallback(this.playbackParameters.pendingResultCallback);
    }

    @Override // androidx.media3.common.Player
    public void setRepeatMode(int i) {
        if (this.remoteMediaClient == null) {
            return;
        }
        setRepeatModeAndNotifyIfChanged(i);
        this.listeners.flushEvents();
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResultQueueSetRepeatMode = this.remoteMediaClient.queueSetRepeatMode(getCastRepeatMode(i), null);
        this.repeatMode.pendingResultCallback = new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: androidx.media3.cast.RemoteCastPlayer.3
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                if (RemoteCastPlayer.this.remoteMediaClient != null) {
                    RemoteCastPlayer.this.updateRepeatModeAndNotifyIfChanged(this);
                    RemoteCastPlayer.this.listeners.flushEvents();
                }
            }
        };
        pendingResultQueueSetRepeatMode.setResultCallback(this.repeatMode.pendingResultCallback);
    }

    @Override // androidx.media3.common.Player
    public int getRepeatMode() {
        return this.repeatMode.value.intValue();
    }

    @Override // androidx.media3.common.Player
    public Tracks getCurrentTracks() {
        return this.currentTracks;
    }

    @Override // androidx.media3.common.Player
    public TrackSelectionParameters getTrackSelectionParameters() {
        return TrackSelectionParameters.DEFAULT;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getMediaMetadata() {
        return this.mediaMetadata;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getPlaylistMetadata() {
        return this.playlistMetadata;
    }

    @Override // androidx.media3.common.Player
    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        Preconditions.checkNotNull(mediaMetadata);
        if (mediaMetadata.equals(this.playlistMetadata)) {
            return;
        }
        this.playlistMetadata = mediaMetadata;
        this.listeners.sendEvent(15, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda0
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                this.f$0.m7374x1a43586e((Player.Listener) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setPlaylistMetadata$6$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7374x1a43586e(Player.Listener listener) {
        listener.onPlaylistMetadataChanged(this.playlistMetadata);
    }

    @Override // androidx.media3.common.Player
    public Timeline getCurrentTimeline() {
        return this.currentTimeline;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentPeriodIndex() {
        return getCurrentMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public int getCurrentMediaItemIndex() {
        int i = this.pendingSeekWindowIndex;
        return i != -1 ? i : this.currentWindowIndex;
    }

    @Override // androidx.media3.common.Player
    public long getDuration() {
        return getContentDuration();
    }

    @Override // androidx.media3.common.Player
    public long getCurrentPosition() {
        long j = this.pendingSeekPositionMs;
        if (j != C.TIME_UNSET) {
            return j;
        }
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        if (remoteMediaClient != null) {
            return remoteMediaClient.getApproximateStreamPosition();
        }
        return this.lastReportedPositionMs;
    }

    @Override // androidx.media3.common.Player
    public long getBufferedPosition() {
        return getCurrentPosition();
    }

    @Override // androidx.media3.common.Player
    public long getTotalBufferedDuration() {
        long bufferedPosition = getBufferedPosition();
        long currentPosition = getCurrentPosition();
        if (bufferedPosition == C.TIME_UNSET || currentPosition == C.TIME_UNSET) {
            return 0L;
        }
        return bufferedPosition - currentPosition;
    }

    @Override // androidx.media3.common.Player
    public long getContentPosition() {
        return getCurrentPosition();
    }

    @Override // androidx.media3.common.Player
    public long getContentBufferedPosition() {
        return getBufferedPosition();
    }

    @Override // androidx.media3.common.Player
    public AudioAttributes getAudioAttributes() {
        return AudioAttributes.DEFAULT;
    }

    @Override // androidx.media3.common.Player
    public void setVolume(float f) {
        if (this.remoteMediaClient == null) {
            return;
        }
        float fFloatValue = ((Float) RANGE_VOLUME.clamp(Float.valueOf(f))).floatValue();
        setVolumeAndNotifyIfChanged(fFloatValue);
        this.listeners.flushEvents();
        PendingResult<RemoteMediaClient.MediaChannelResult> streamVolume = this.remoteMediaClient.setStreamVolume(fFloatValue);
        this.volume.pendingResultCallback = new ResultCallback<RemoteMediaClient.MediaChannelResult>() { // from class: androidx.media3.cast.RemoteCastPlayer.4
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
                if (RemoteCastPlayer.this.remoteMediaClient != null) {
                    RemoteCastPlayer.this.updateVolumeAndNotifyIfChanged(this);
                    RemoteCastPlayer.this.listeners.flushEvents();
                }
            }
        };
        streamVolume.setResultCallback(this.volume.pendingResultCallback);
    }

    @Override // androidx.media3.common.Player
    public float getVolume() {
        return this.volume.value.floatValue();
    }

    @Override // androidx.media3.common.Player
    public VideoSize getVideoSize() {
        return VideoSize.UNKNOWN;
    }

    @Override // androidx.media3.common.Player
    public Size getSurfaceSize() {
        return Size.UNKNOWN;
    }

    @Override // androidx.media3.common.Player
    public CueGroup getCurrentCues() {
        return CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.common.Player
    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    @Override // androidx.media3.common.Player
    public int getDeviceVolume() {
        return this.deviceVolume;
    }

    @Override // androidx.media3.common.Player
    public boolean isDeviceMuted() {
        return this.isMuted;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceVolume(int i) {
        setDeviceVolume(i, 0);
    }

    @Override // androidx.media3.common.Player
    public void setDeviceVolume(int i, int i2) {
        if (this.castSession == null) {
            return;
        }
        int iIntValue = ((Integer) RANGE_DEVICE_VOLUME.clamp(Integer.valueOf(i))).intValue();
        try {
            this.castSession.setVolume(iIntValue / 20.0f);
            setDeviceVolumeAndNotifyIfChanged(iIntValue, this.isMuted);
            this.listeners.flushEvents();
        } catch (IOException e) {
            Log.w(TAG, "Ignoring setDeviceVolume due to exception", e);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void increaseDeviceVolume() {
        increaseDeviceVolume(0);
    }

    @Override // androidx.media3.common.Player
    public void increaseDeviceVolume(int i) {
        setDeviceVolume(getDeviceVolume() + 1, i);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void decreaseDeviceVolume() {
        decreaseDeviceVolume(0);
    }

    @Override // androidx.media3.common.Player
    public void decreaseDeviceVolume(int i) {
        setDeviceVolume(getDeviceVolume() - 1, i);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceMuted(boolean z) {
        setDeviceMuted(z, 0);
    }

    @Override // androidx.media3.common.Player
    public void setDeviceMuted(boolean z, int i) {
        CastSession castSession = this.castSession;
        if (castSession == null) {
            return;
        }
        try {
            castSession.setMute(z);
            setDeviceVolumeAndNotifyIfChanged(this.deviceVolume, z);
            this.listeners.flushEvents();
        } catch (IOException e) {
            Log.w(TAG, "Ignoring setDeviceMuted due to exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInternalStateAndNotifyIfChanged() {
        if (this.remoteMediaClient == null) {
            return;
        }
        int i = this.currentWindowIndex;
        MediaMetadata mediaMetadata = this.mediaMetadata;
        Object obj = !getCurrentTimeline().isEmpty() ? getCurrentTimeline().getPeriod(i, this.period, true).uid : null;
        updatePlayerStateAndNotifyIfChanged(null);
        updateDeviceVolumeAndNotifyIfChanged();
        updateRepeatModeAndNotifyIfChanged(null);
        updateVolumeAndNotifyIfChanged(null);
        updatePlaybackRateAndNotifyIfChanged(null);
        boolean zUpdateTimelineAndNotifyIfChanged = updateTimelineAndNotifyIfChanged();
        Timeline currentTimeline = getCurrentTimeline();
        this.currentWindowIndex = fetchCurrentWindowIndex(this.remoteMediaClient, currentTimeline);
        this.mediaMetadata = getMediaMetadataInternal();
        Object obj2 = currentTimeline.isEmpty() ? null : currentTimeline.getPeriod(this.currentWindowIndex, this.period, true).uid;
        if (!zUpdateTimelineAndNotifyIfChanged && !Objects.equals(obj, obj2) && this.pendingSeekCount == 0) {
            currentTimeline.getPeriod(i, this.period, true);
            currentTimeline.getWindow(i, this.window);
            long durationMs = this.window.getDurationMs();
            final Player.PositionInfo positionInfo = new Player.PositionInfo(this.window.uid, this.period.windowIndex, this.window.mediaItem, this.period.uid, this.period.windowIndex, durationMs, durationMs, -1, -1);
            currentTimeline.getPeriod(this.currentWindowIndex, this.period, true);
            currentTimeline.getWindow(this.currentWindowIndex, this.window);
            final Player.PositionInfo positionInfo2 = new Player.PositionInfo(this.window.uid, this.period.windowIndex, this.window.mediaItem, this.period.uid, this.period.windowIndex, this.window.getDefaultPositionMs(), this.window.getDefaultPositionMs(), -1, -1);
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda19
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj3) {
                    RemoteCastPlayer.lambda$updateInternalStateAndNotifyIfChanged$7(positionInfo, positionInfo2, (Player.Listener) obj3);
                }
            });
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda20
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj3) {
                    this.f$0.m7377xf54a012f((Player.Listener) obj3);
                }
            });
        }
        if (updateTracksAndSelectionsAndNotifyIfChanged()) {
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda21
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj3) {
                    this.f$0.m7378xbe4af870((Player.Listener) obj3);
                }
            });
        }
        if (!mediaMetadata.equals(this.mediaMetadata)) {
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda22
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj3) {
                    this.f$0.m7376x70791034((Player.Listener) obj3);
                }
            });
        }
        updateAvailableCommandsAndNotifyIfChanged();
        this.listeners.flushEvents();
    }

    static /* synthetic */ void lambda$updateInternalStateAndNotifyIfChanged$7(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(0);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, 0);
    }

    /* JADX INFO: renamed from: lambda$updateInternalStateAndNotifyIfChanged$8$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7377xf54a012f(Player.Listener listener) {
        listener.onMediaItemTransition(getCurrentMediaItem(), 1);
    }

    /* JADX INFO: renamed from: lambda$updateInternalStateAndNotifyIfChanged$9$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7378xbe4af870(Player.Listener listener) {
        listener.onTracksChanged(this.currentTracks);
    }

    /* JADX INFO: renamed from: lambda$updateInternalStateAndNotifyIfChanged$10$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7376x70791034(Player.Listener listener) {
        listener.onMediaMetadataChanged(this.mediaMetadata);
    }

    private MediaMetadata getMediaMetadataInternal() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        return currentMediaItem != null ? currentMediaItem.mediaMetadata : MediaMetadata.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"remoteMediaClient"})
    public void updatePlayerStateAndNotifyIfChanged(ResultCallback<?> resultCallback) {
        boolean zBooleanValue = this.playWhenReady.value.booleanValue();
        if (this.playWhenReady.acceptsUpdate(resultCallback)) {
            zBooleanValue = !this.remoteMediaClient.isPaused();
            this.playWhenReady.clearPendingResultCallback();
        }
        setPlayerStateAndNotifyIfChanged(zBooleanValue, zBooleanValue != this.playWhenReady.value.booleanValue() ? 4 : 1, fetchPlaybackState(this.remoteMediaClient));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"remoteMediaClient"})
    public void updatePlaybackRateAndNotifyIfChanged(ResultCallback<?> resultCallback) {
        float playbackRate;
        if (this.playbackParameters.acceptsUpdate(resultCallback)) {
            MediaStatus mediaStatus = this.remoteMediaClient.getMediaStatus();
            if (mediaStatus != null) {
                playbackRate = (float) mediaStatus.getPlaybackRate();
            } else {
                playbackRate = PlaybackParameters.DEFAULT.speed;
            }
            if (playbackRate > 0.0f) {
                setPlaybackParametersAndNotifyIfChanged(new PlaybackParameters(playbackRate));
            }
            this.playbackParameters.clearPendingResultCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"castSession"})
    public void updateDeviceVolumeAndNotifyIfChanged() {
        CastSession castSession = this.castSession;
        if (castSession != null) {
            setDeviceVolumeAndNotifyIfChanged(((Integer) RANGE_DEVICE_VOLUME.clamp(Integer.valueOf((int) Math.round(castSession.getVolume() * 20.0d)))).intValue(), this.castSession.isMute());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"remoteMediaClient"})
    public void updateVolumeAndNotifyIfChanged(ResultCallback<?> resultCallback) {
        if (this.volume.acceptsUpdate(resultCallback)) {
            setVolumeAndNotifyIfChanged(((Float) RANGE_VOLUME.clamp(Float.valueOf(fetchVolume(this.remoteMediaClient)))).floatValue());
            this.volume.clearPendingResultCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"remoteMediaClient"})
    public void updateRepeatModeAndNotifyIfChanged(ResultCallback<?> resultCallback) {
        if (this.repeatMode.acceptsUpdate(resultCallback)) {
            setRepeatModeAndNotifyIfChanged(fetchRepeatMode(this.remoteMediaClient));
            this.repeatMode.clearPendingResultCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateTimelineAndNotifyIfChanged() {
        boolean z = false;
        if (this.remoteMediaClient == null) {
            return false;
        }
        CastTimeline castTimeline = this.currentTimeline;
        int i = this.currentWindowIndex;
        if (updateTimeline()) {
            final CastTimeline castTimeline2 = this.currentTimeline;
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda11
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTimelineChanged(castTimeline2, 1);
                }
            });
            Timeline currentTimeline = getCurrentTimeline();
            boolean z2 = !castTimeline.isEmpty() && currentTimeline.getIndexOfPeriod(Util.castNonNull(castTimeline.getPeriod(i, this.period, true).uid)) == -1;
            if (z2) {
                final Player.PositionInfo positionInfo = this.pendingMediaItemRemovalPosition;
                if (positionInfo != null) {
                    this.pendingMediaItemRemovalPosition = null;
                } else {
                    castTimeline.getPeriod(i, this.period, true);
                    castTimeline.getWindow(this.period.windowIndex, this.window);
                    positionInfo = new Player.PositionInfo(this.window.uid, this.period.windowIndex, this.window.mediaItem, this.period.uid, this.period.windowIndex, getCurrentPosition(), getContentPosition(), -1, -1);
                }
                final Player.PositionInfo currentPositionInfo = getCurrentPositionInfo();
                this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda15
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        RemoteCastPlayer.lambda$updateTimelineAndNotifyIfChanged$12(positionInfo, currentPositionInfo, (Player.Listener) obj);
                    }
                });
            }
            z = currentTimeline.isEmpty() != castTimeline.isEmpty() || z2;
            if (z) {
                this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda16
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7379x6f9178de((Player.Listener) obj);
                    }
                });
            }
            updateAvailableCommandsAndNotifyIfChanged();
        }
        return z;
    }

    static /* synthetic */ void lambda$updateTimelineAndNotifyIfChanged$12(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(4);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, 4);
    }

    /* JADX INFO: renamed from: lambda$updateTimelineAndNotifyIfChanged$13$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7379x6f9178de(Player.Listener listener) {
        listener.onMediaItemTransition(getCurrentMediaItem(), 3);
    }

    private boolean updateTimeline() {
        CastTimeline castTimeline;
        CastTimeline castTimeline2 = this.currentTimeline;
        if (getMediaStatus() != null) {
            castTimeline = this.timelineTracker.getCastTimeline(this.remoteMediaClient);
        } else {
            castTimeline = CastTimeline.EMPTY_CAST_TIMELINE;
        }
        this.currentTimeline = castTimeline;
        boolean zEquals = castTimeline2.equals(castTimeline);
        boolean z = !zEquals;
        if (!zEquals) {
            this.currentWindowIndex = fetchCurrentWindowIndex(this.remoteMediaClient, this.currentTimeline);
        }
        return z;
    }

    private boolean updateTracksAndSelectionsAndNotifyIfChanged() {
        if (this.remoteMediaClient == null) {
            return false;
        }
        MediaStatus mediaStatus = getMediaStatus();
        MediaInfo mediaInfo = mediaStatus != null ? mediaStatus.getMediaInfo() : null;
        List<MediaTrack> mediaTracks = mediaInfo != null ? mediaInfo.getMediaTracks() : null;
        if (mediaTracks == null || mediaTracks.isEmpty()) {
            boolean z = !Tracks.EMPTY.equals(this.currentTracks);
            this.currentTracks = Tracks.EMPTY;
            return z;
        }
        long[] activeTrackIds = mediaStatus.getActiveTrackIds();
        if (activeTrackIds == null) {
            activeTrackIds = EMPTY_TRACK_ID_ARRAY;
        }
        Tracks.Group[] groupArr = new Tracks.Group[mediaTracks.size()];
        for (int i = 0; i < mediaTracks.size(); i++) {
            MediaTrack mediaTrack = mediaTracks.get(i);
            groupArr[i] = new Tracks.Group(CastUtils.mediaTrackToTrackGroup(String.valueOf(i), mediaTrack), false, new int[]{4}, new boolean[]{isTrackActive(mediaTrack.getId(), activeTrackIds)});
        }
        Tracks tracks = new Tracks(ImmutableList.copyOf(groupArr));
        if (tracks.equals(this.currentTracks)) {
            return false;
        }
        this.currentTracks = tracks;
        return true;
    }

    private void updateAvailableCommandsAndNotifyIfChanged() {
        if (this.castContext == null) {
            return;
        }
        Player.Commands commands = this.availableCommands;
        Player.Commands commandsBuild = Util.getAvailableCommands(this, PERMANENT_AVAILABLE_COMMANDS).buildUpon().addIf(22, isSetVolumeCommandAvailable()).addIf(24, isSetVolumeCommandAvailable()).build();
        this.availableCommands = commandsBuild;
        if (commandsBuild.equals(commands)) {
            return;
        }
        this.listeners.queueEvent(13, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda1
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                this.f$0.m7375xf93f1f7b((Player.Listener) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateAvailableCommandsAndNotifyIfChanged$14$androidx-media3-cast-RemoteCastPlayer, reason: not valid java name */
    /* synthetic */ void m7375xf93f1f7b(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.availableCommands);
    }

    private boolean isSetVolumeCommandAvailable() {
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        if (remoteMediaClient == null || (mediaStatus = remoteMediaClient.getMediaStatus()) == null) {
            return false;
        }
        return mediaStatus.isMediaCommandSupported(4L);
    }

    private void setMediaItemsInternal(List<MediaItem> list, int i, long j, int i2) {
        if (this.remoteMediaClient == null || list.isEmpty()) {
            return;
        }
        if (j == C.TIME_UNSET) {
            j = 0;
        }
        if (i == -1) {
            i = getCurrentMediaItemIndex();
            j = getCurrentPosition();
        }
        if (!getCurrentTimeline().isEmpty()) {
            this.pendingMediaItemRemovalPosition = getCurrentPositionInfo();
        }
        MediaQueueItem[] mediaQueueItems = toMediaQueueItems(list);
        this.timelineTracker.onMediaItemsSet(list, mediaQueueItems);
        this.remoteMediaClient.load(new MediaLoadRequestData.Builder().setAutoplay(Boolean.valueOf(getPlayWhenReady())).setQueueData(new MediaQueueData.Builder().setItems(Arrays.asList(mediaQueueItems)).setStartIndex(Math.min(i, list.size() - 1)).setRepeatMode(getCastRepeatMode(i2)).setStartTime(j).build()).setCurrentTime(j).build());
    }

    private void addMediaItemsInternal(List<MediaItem> list, int i) {
        if (this.remoteMediaClient == null || getMediaStatus() == null) {
            return;
        }
        MediaQueueItem[] mediaQueueItems = toMediaQueueItems(list);
        this.timelineTracker.onMediaItemsAdded(list, mediaQueueItems);
        this.remoteMediaClient.queueInsertItems(mediaQueueItems, i, null);
    }

    private void moveMediaItemsInternal(int[] iArr, int i, int i2) {
        if (this.remoteMediaClient == null || getMediaStatus() == null) {
            return;
        }
        if (i < i2) {
            i2 += iArr.length;
        }
        this.remoteMediaClient.queueReorderItems(iArr, i2 < this.currentTimeline.getWindowCount() ? ((Integer) this.currentTimeline.getWindow(i2, this.window).uid).intValue() : 0, null);
    }

    private PendingResult<RemoteMediaClient.MediaChannelResult> removeMediaItemsInternal(int[] iArr) {
        if (this.remoteMediaClient == null || getMediaStatus() == null) {
            return null;
        }
        Timeline currentTimeline = getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            Object objCastNonNull = Util.castNonNull(currentTimeline.getPeriod(getCurrentPeriodIndex(), this.period, true).uid);
            for (int i : iArr) {
                if (objCastNonNull.equals(Integer.valueOf(i))) {
                    this.pendingMediaItemRemovalPosition = getCurrentPositionInfo();
                    break;
                }
            }
        }
        return this.remoteMediaClient.queueRemoveItems(iArr, null);
    }

    private Player.PositionInfo getCurrentPositionInfo() {
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            obj = null;
            mediaItem = null;
            obj2 = null;
        } else {
            Object obj3 = currentTimeline.getPeriod(getCurrentPeriodIndex(), this.period, true).uid;
            obj = currentTimeline.getWindow(this.period.windowIndex, this.window).uid;
            obj2 = obj3;
            mediaItem = this.window.mediaItem;
        }
        return new Player.PositionInfo(obj, getCurrentMediaItemIndex(), mediaItem, obj2, getCurrentPeriodIndex(), getCurrentPosition(), getContentPosition(), -1, -1);
    }

    private void setDeviceVolumeAndNotifyIfChanged(final int i, final boolean z) {
        if (this.deviceVolume == i && this.isMuted == z) {
            return;
        }
        this.deviceVolume = i;
        this.isMuted = z;
        this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda7
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onDeviceVolumeChanged(i, z);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Integer] */
    private void setRepeatModeAndNotifyIfChanged(final int i) {
        if (this.repeatMode.value.intValue() != i) {
            this.repeatMode.value = Integer.valueOf(i);
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda12
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onRepeatModeChanged(i);
                }
            });
            updateAvailableCommandsAndNotifyIfChanged();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Float] */
    private void setVolumeAndNotifyIfChanged(final float f) {
        if (this.volume.value.floatValue() != f) {
            this.volume.value = Float.valueOf(f);
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda14
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVolumeChanged(f);
                }
            });
            updateAvailableCommandsAndNotifyIfChanged();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setPlaybackParametersAndNotifyIfChanged(final PlaybackParameters playbackParameters) {
        if (this.playbackParameters.value.equals(playbackParameters)) {
            return;
        }
        this.playbackParameters.value = playbackParameters;
        this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda6
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onPlaybackParametersChanged(playbackParameters);
            }
        });
        updateAvailableCommandsAndNotifyIfChanged();
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [T, java.lang.Boolean] */
    private void setPlayerStateAndNotifyIfChanged(final boolean z, final int i, final int i2) {
        boolean z2 = this.playbackState == 3 && this.playWhenReady.value.booleanValue();
        boolean z3 = this.playWhenReady.value.booleanValue() != z;
        boolean z4 = this.playbackState != i2;
        if (z3 || z4) {
            this.playbackState = i2;
            this.playWhenReady.value = Boolean.valueOf(z);
            this.listeners.queueEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda2
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayerStateChanged(z, i2);
                }
            });
            if (z4) {
                this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda3
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlaybackStateChanged(i2);
                    }
                });
            }
            if (z3) {
                this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda4
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlayWhenReadyChanged(z, i);
                    }
                });
            }
            final boolean z5 = i2 == 3 && z;
            if (z2 != z5) {
                this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$$ExternalSyntheticLambda5
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onIsPlayingChanged(z5);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCastSession(CastSession castSession) {
        CastSession castSession2 = this.castSession;
        if (castSession2 != null) {
            castSession2.removeCastListener(this.castListener);
        }
        if (castSession != null) {
            castSession.addCastListener(this.castListener);
        }
        this.castSession = castSession;
        RemoteMediaClient remoteMediaClient = castSession != null ? castSession.getRemoteMediaClient() : null;
        RemoteMediaClient remoteMediaClient2 = this.remoteMediaClient;
        if (remoteMediaClient2 == remoteMediaClient) {
            return;
        }
        if (remoteMediaClient2 != null) {
            remoteMediaClient2.unregisterCallback(this.statusListener);
            this.remoteMediaClient.removeProgressListener(this.statusListener);
            this.remoteMediaClient.getMediaQueue().unregisterCallback(this.mediaQueueCallback);
        }
        this.remoteMediaClient = remoteMediaClient;
        SessionAvailabilityListener sessionAvailabilityListener = this.sessionAvailabilityListener;
        if (remoteMediaClient != null) {
            if (sessionAvailabilityListener != null) {
                sessionAvailabilityListener.onCastSessionAvailable();
            }
            SessionAvailabilityListener sessionAvailabilityListener2 = this.internalSessionAvailabilityListener;
            if (sessionAvailabilityListener2 != null) {
                sessionAvailabilityListener2.onCastSessionAvailable();
            }
            remoteMediaClient.getMediaQueue().registerCallback(this.mediaQueueCallback);
            remoteMediaClient.registerCallback(this.statusListener);
            remoteMediaClient.addProgressListener(this.statusListener, 1000L);
            updateInternalStateAndNotifyIfChanged();
            return;
        }
        if (sessionAvailabilityListener != null) {
            sessionAvailabilityListener.onCastSessionUnavailable();
        }
        SessionAvailabilityListener sessionAvailabilityListener3 = this.internalSessionAvailabilityListener;
        if (sessionAvailabilityListener3 != null) {
            sessionAvailabilityListener3.onCastSessionUnavailable();
        }
    }

    private MediaStatus getMediaStatus() {
        RemoteMediaClient remoteMediaClient = this.remoteMediaClient;
        if (remoteMediaClient != null) {
            return remoteMediaClient.getMediaStatus();
        }
        return null;
    }

    private static CastContext getSharedInstantSafely(Context context) {
        try {
            return CastContext.getSharedInstance(context);
        } catch (RuntimeException e) {
            if (e instanceof IllegalStateException) {
                throw e;
            }
            Log.e(TAG, "Failed to initialize Cast. This may be due to Google Play services not being available, see https://support.google.com/googleplay/answer/9037938", e);
            return null;
        }
    }

    private static int fetchPlaybackState(RemoteMediaClient remoteMediaClient) {
        int playerState = remoteMediaClient.getPlayerState();
        if (playerState == 2 || playerState == 3) {
            return 3;
        }
        return (playerState == 4 || playerState == 5) ? 2 : 1;
    }

    private static int fetchRepeatMode(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        int i = 0;
        if (mediaStatus == null) {
            return 0;
        }
        int queueRepeatMode = mediaStatus.getQueueRepeatMode();
        if (queueRepeatMode != 0) {
            i = 2;
            if (queueRepeatMode != 1) {
                if (queueRepeatMode == 2) {
                    return 1;
                }
                if (queueRepeatMode != 3) {
                    throw new IllegalStateException();
                }
            }
        }
        return i;
    }

    private static float fetchVolume(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if (mediaStatus == null) {
            return 1.0f;
        }
        return (float) mediaStatus.getStreamVolume();
    }

    private static int fetchCurrentWindowIndex(RemoteMediaClient remoteMediaClient, Timeline timeline) {
        if (remoteMediaClient == null) {
            return 0;
        }
        MediaQueueItem currentItem = remoteMediaClient.getCurrentItem();
        int indexOfPeriod = currentItem != null ? timeline.getIndexOfPeriod(Integer.valueOf(currentItem.getItemId())) : -1;
        if (indexOfPeriod == -1) {
            return 0;
        }
        return indexOfPeriod;
    }

    private static boolean isTrackActive(long j, long[] jArr) {
        for (long j2 : jArr) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    private static int getCastRepeatMode(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException();
    }

    private MediaQueueItem[] toMediaQueueItems(List<MediaItem> list) {
        MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mediaQueueItemArr[i] = this.mediaItemConverter.toMediaQueueItem(list.get(i));
        }
        return mediaQueueItemArr;
    }

    private final class StatusListener extends RemoteMediaClient.Callback implements SessionManagerListener<CastSession>, RemoteMediaClient.ProgressListener {
        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onAdBreakStatusUpdated() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onMetadataUpdated() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onPreloadStatusUpdated() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onSendingRemoteMediaRequest() {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResuming(CastSession castSession, String str) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarting(CastSession castSession) {
        }

        private StatusListener() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
        public void onProgressUpdated(long j, long j2) {
            RemoteCastPlayer.this.lastReportedPositionMs = j;
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onStatusUpdated() {
            RemoteCastPlayer.this.updateInternalStateAndNotifyIfChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onQueueStatusUpdated() {
            RemoteCastPlayer.this.updateTimelineAndNotifyIfChanged();
            RemoteCastPlayer.this.listeners.flushEvents();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarted(CastSession castSession, String str) {
            RemoteCastPlayer.this.setCastSession(castSession);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumed(CastSession castSession, boolean z) {
            RemoteCastPlayer.this.setCastSession(castSession);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnded(CastSession castSession, int i) {
            RemoteCastPlayer.this.setCastSession(null);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionSuspended(CastSession castSession, int i) {
            RemoteCastPlayer.this.setCastSession(null);
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumeFailed(CastSession castSession, int i) {
            Log.e(RemoteCastPlayer.TAG, "Session resume failed. Error code " + i + ": " + CastUtils.getLogString(i));
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStartFailed(CastSession castSession, int i) {
            Log.e(RemoteCastPlayer.TAG, "Session start failed. Error code " + i + ": " + CastUtils.getLogString(i));
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnding(CastSession castSession) {
            RemoteCastPlayer.this.setCastSession(null);
        }
    }

    private final class SeekResultCallback implements ResultCallback<RemoteMediaClient.MediaChannelResult> {
        private SeekResultCallback() {
        }

        @Override // com.google.android.gms.common.api.ResultCallback
        public void onResult(RemoteMediaClient.MediaChannelResult mediaChannelResult) {
            int statusCode = mediaChannelResult.getStatus().getStatusCode();
            if (statusCode != 0 && statusCode != 2103) {
                Log.e(RemoteCastPlayer.TAG, "Seek failed. Error code " + statusCode + ": " + CastUtils.getLogString(statusCode));
            }
            if (RemoteCastPlayer.access$2006(RemoteCastPlayer.this) == 0) {
                RemoteCastPlayer remoteCastPlayer = RemoteCastPlayer.this;
                remoteCastPlayer.currentWindowIndex = remoteCastPlayer.pendingSeekWindowIndex;
                RemoteCastPlayer.this.pendingSeekWindowIndex = -1;
                RemoteCastPlayer.this.pendingSeekPositionMs = C.TIME_UNSET;
            }
        }
    }

    private static final class StateHolder<T> {
        public ResultCallback<RemoteMediaClient.MediaChannelResult> pendingResultCallback;
        public T value;

        public StateHolder(T t) {
            this.value = t;
        }

        public void clearPendingResultCallback() {
            this.pendingResultCallback = null;
        }

        public boolean acceptsUpdate(ResultCallback<?> resultCallback) {
            return this.pendingResultCallback == resultCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class Api30Impl {
        private final MediaRouter2$RouteCallback emptyRouteCallback;
        private final Handler handler = new Handler(Looper.getMainLooper());
        private final MediaRouter2 mediaRouter2;
        private final MediaRouter2$TransferCallback transferCallback;

        public Api30Impl(Context context) {
            this.mediaRouter2 = MediaRouter2.getInstance(context);
            this.transferCallback = new MediaRouter2TransferCallbackImpl();
            this.emptyRouteCallback = new MediaRouter2RouteCallbackImpl();
        }

        public void initialize() {
            MediaRouter2 mediaRouter2 = this.mediaRouter2;
            Handler handler = this.handler;
            Objects.requireNonNull(handler);
            mediaRouter2.registerTransferCallback(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), this.transferCallback);
            MediaRouter2 mediaRouter3 = this.mediaRouter2;
            Handler handler2 = this.handler;
            Objects.requireNonNull(handler2);
            mediaRouter3.registerRouteCallback(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler2), this.emptyRouteCallback, new RouteDiscoveryPreference.Builder(ImmutableList.of(), false).build());
        }

        public void release() {
            this.mediaRouter2.unregisterTransferCallback(this.transferCallback);
            this.mediaRouter2.unregisterRouteCallback(this.emptyRouteCallback);
            this.handler.removeCallbacksAndMessages(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateDeviceInfo() {
            DeviceInfo deviceInfo = RemoteCastPlayer.this.deviceInfo;
            final DeviceInfo deviceInfoFetchDeviceInfo = fetchDeviceInfo();
            RemoteCastPlayer.this.deviceInfo = deviceInfoFetchDeviceInfo;
            if (RemoteCastPlayer.this.deviceInfo.equals(deviceInfo)) {
                return;
            }
            RemoteCastPlayer.this.listeners.sendEvent(29, new ListenerSet.Event() { // from class: androidx.media3.cast.RemoteCastPlayer$Api30Impl$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceInfoChanged(deviceInfoFetchDeviceInfo);
                }
            });
        }

        public DeviceInfo fetchDeviceInfo() {
            List<MediaRouter2.RoutingController> controllers = this.mediaRouter2.getControllers();
            if (controllers.size() != 2) {
                return RemoteCastPlayer.DEVICE_INFO_REMOTE_EMPTY;
            }
            return new DeviceInfo.Builder(1).setMaxVolume(20).setRoutingControllerId(controllers.get(1).getId()).build();
        }

        private final class MediaRouter2RouteCallbackImpl extends MediaRouter2$RouteCallback {
            private MediaRouter2RouteCallbackImpl() {
            }
        }

        private final class MediaRouter2TransferCallbackImpl extends MediaRouter2$TransferCallback {
            private MediaRouter2TransferCallbackImpl() {
            }

            public void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
                Api30Impl.this.updateDeviceInfo();
            }

            public void onStop(MediaRouter2.RoutingController routingController) {
                Api30Impl.this.updateDeviceInfo();
            }
        }
    }

    private final class CastListener extends Cast.Listener {
        private CastListener() {
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public void onVolumeChanged() {
            RemoteCastPlayer.this.updateDeviceVolumeAndNotifyIfChanged();
            RemoteCastPlayer.this.listeners.flushEvents();
        }
    }

    private class MediaQueueCallback extends MediaQueue.Callback {
        private MediaQueueCallback() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public void mediaQueueChanged() {
            RemoteCastPlayer.this.updateTimelineAndNotifyIfChanged();
            RemoteCastPlayer.this.listeners.flushEvents();
        }
    }
}

package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
class MediaControllerImplLegacy implements MediaController.MediaControllerImpl {
    private static final String TAG = "MCImplLegacy";
    private static final long WAIT_TIME_MS_FOR_COMPAT_EXTRA_BINDER = 500;
    private final androidx.media3.common.util.BitmapLoader bitmapLoader;
    private MediaBrowserCompat browserCompat;
    private boolean connected;
    private final Bundle connectionHints;
    final Context context;
    private MediaControllerCompat controllerCompat;
    private final ControllerCompatCallback controllerCompatCallback;
    private boolean hasPendingExtrasChange;
    private final MediaController instance;
    private final ListenerSet<Player.Listener> listeners;
    private final long platformSessionCallbackAggregationTimeoutMs;
    private boolean released;
    private final SessionToken token;
    private LegacyPlayerInfo legacyPlayerInfo = new LegacyPlayerInfo();
    private LegacyPlayerInfo pendingLegacyPlayerInfo = new LegacyPlayerInfo();
    private ControllerInfo controllerInfo = new ControllerInfo();
    private long currentPositionMs = C.TIME_UNSET;
    private long lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
    private final ImmutableList<CommandButton> commandButtonsForMediaItems = ImmutableList.of();

    private static int calculateCurrentItemIndexAfterAddItems(int i, int i2, int i3) {
        return i < i2 ? i : i + i3;
    }

    private static int calculateCurrentItemIndexAfterRemoveItems(int i, int i2, int i3) {
        int i4 = i3 - i2;
        if (i < i2) {
            return i;
        }
        if (i < i3) {
            return -1;
        }
        return i - i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void ignoreFuture(Future<T> future) {
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public IMediaController getBinder() {
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdGroupIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdIndexInAdGroup() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentLiveOffset() {
        return C.TIME_UNSET;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getNextMediaItemIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackSuppressionReason() {
        return 0;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPreviousMediaItemIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public float getVolume() {
        return 1.0f;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isLoading() {
        return false;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
    }

    public MediaControllerImplLegacy(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j) {
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda22
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                this.f$0.m7739lambda$new$0$androidxmedia3sessionMediaControllerImplLegacy((Player.Listener) obj, flagSet);
            }
        });
        this.context = context;
        this.instance = mediaController;
        this.controllerCompatCallback = new ControllerCompatCallback(looper);
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.bitmapLoader = bitmapLoader;
        this.platformSessionCallbackAggregationTimeoutMs = j;
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7739lambda$new$0$androidxmedia3sessionMediaControllerImplLegacy(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    MediaController getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void connect() {
        if (this.token.getType() == 0) {
            connectToSession((MediaSessionCompat.Token) Preconditions.checkNotNull(this.token.getBinder()));
        } else {
            connectToService();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getConnectionHints() {
        return this.connectionHints;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void stop() {
        if (this.controllerInfo.playerInfo.playbackState == 1) {
            return;
        }
        PlayerInfo playerInfoCopyWithSessionPositionInfo = this.controllerInfo.playerInfo.copyWithSessionPositionInfo(createSessionPositionInfo(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo, this.controllerInfo.playerInfo.sessionPositionInfo.isPlayingAd, this.controllerInfo.playerInfo.sessionPositionInfo.durationMs, this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs, MediaUtils.calculateBufferedPercentage(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs, this.controllerInfo.playerInfo.sessionPositionInfo.durationMs), 0L));
        if (this.controllerInfo.playerInfo.playbackState != 1) {
            playerInfoCopyWithSessionPositionInfo = playerInfoCopyWithSessionPositionInfo.copyWithPlaybackState(1, this.controllerInfo.playerInfo.playerError);
        }
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithSessionPositionInfo, this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        this.controllerCompat.getTransportControls().stop();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        MediaBrowserCompat mediaBrowserCompat = this.browserCompat;
        if (mediaBrowserCompat != null) {
            mediaBrowserCompat.disconnect();
            this.browserCompat = null;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.controllerCompatCallback);
            this.controllerCompatCallback.release();
            this.controllerCompat = null;
        }
        this.connected = false;
        this.listeners.release();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionToken getConnectedToken() {
        if (this.connected) {
            return this.token;
        }
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isConnected() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void play() {
        setPlayWhenReady(true);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void pause() {
        setPlayWhenReady(false);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void prepare() {
        if (this.controllerInfo.playerInfo.playbackState != 1) {
            return;
        }
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackState(this.controllerInfo.playerInfo.timeline.isEmpty() ? 4 : 2, null), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (hasMedia()) {
            initializeLegacyPlaylist();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition() {
        seekToInternal(getCurrentMediaItemIndex(), 0L);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition(int i) {
        seekToInternal(i, 0L);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(long j) {
        seekToInternal(getCurrentMediaItemIndex(), j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(int i, long j) {
        seekToInternal(i, j);
    }

    private void seekToInternal(int i, long j) {
        Integer num;
        Integer num2;
        int i2;
        long j2;
        long j3;
        long j4;
        int i3 = i;
        long j5 = j;
        Preconditions.checkArgument(i3 >= 0);
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        Timeline timeline = this.controllerInfo.playerInfo.timeline;
        if ((timeline.isEmpty() || i3 < timeline.getWindowCount()) && !isPlayingAd()) {
            if (i3 != currentMediaItemIndex) {
                long queueId = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).getQueueId(i3);
                if (queueId != -1) {
                    this.controllerCompat.getTransportControls().skipToQueueItem(queueId);
                    num = 2;
                } else {
                    Log.w(TAG, "Cannot seek to new media item due to the missing queue Id at media item, mediaItemIndex=" + i3);
                    i3 = currentMediaItemIndex;
                    num = null;
                }
            } else {
                i3 = currentMediaItemIndex;
                num = null;
            }
            long currentPosition = getCurrentPosition();
            if (j5 == C.TIME_UNSET) {
                num2 = null;
                j5 = currentPosition;
            } else {
                this.controllerCompat.getTransportControls().seekTo(j5);
                num2 = 1;
            }
            if (num == null) {
                long bufferedPosition = getBufferedPosition();
                long duration = getDuration();
                long jMax = j5 < currentPosition ? j5 : Math.max(j5, bufferedPosition);
                j2 = jMax;
                i2 = duration == C.TIME_UNSET ? 0 : (int) ((100 * jMax) / duration);
                j3 = jMax - j5;
                j4 = duration;
            } else {
                i2 = 0;
                j2 = 0;
                j3 = 0;
                j4 = -9223372036854775807L;
            }
            PlayerInfo playerInfoCopyWithSessionPositionInfo = this.controllerInfo.playerInfo.copyWithSessionPositionInfo(createSessionPositionInfo(createPositionInfo(i3, !timeline.isEmpty() ? timeline.getWindow(i3, new Timeline.Window()).mediaItem : null, j5, false), false, j4, j2, i2, j3));
            if (playerInfoCopyWithSessionPositionInfo.playbackState != 1) {
                playerInfoCopyWithSessionPositionInfo = playerInfoCopyWithSessionPositionInfo.copyWithPlaybackState(2, null);
            }
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithSessionPositionInfo, this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), num2, num);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekBackIncrement() {
        return this.controllerInfo.playerInfo.seekBackIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekBack() {
        this.controllerCompat.getTransportControls().rewind();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekForwardIncrement() {
        return this.controllerInfo.playerInfo.seekForwardIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekForward() {
        this.controllerCompat.getTransportControls().fastForward();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.commandButtonsForMediaItems;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PendingIntent getSessionActivity() {
        return this.controllerCompat.getSessionActivity();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.controllerInfo.mediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCustomLayout() {
        return this.controllerInfo.mediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getSessionExtras() {
        return this.controllerInfo.sessionExtras;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackException getPlayerError() {
        return this.controllerInfo.playerInfo.playerError;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.durationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.controllerInfo.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getBufferedPosition() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getBufferedPercentage() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getTotalBufferedDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentDuration() {
        return getDuration();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentPosition() {
        return getCurrentPosition();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentBufferedPosition() {
        return getBufferedPosition();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlayingAd() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackParameters getPlaybackParameters() {
        return this.controllerInfo.playerInfo.playbackParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public AudioAttributes getAudioAttributes() {
        return this.controllerInfo.playerInfo.audioAttributes;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getAudioSessionId() {
        return this.controllerInfo.playerInfo.audioSessionId;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        if (str.equals(this.legacyPlayerInfo.mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID"))) {
            this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        }
        return Futures.immediateFuture(new SessionResult(0));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(Rating rating) {
        this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        return Futures.immediateFuture(new SessionResult(0));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (!playbackParameters.equals(getPlaybackParameters())) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackParameters(playbackParameters), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(playbackParameters.speed);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackSpeed(float f) {
        if (f != getPlaybackParameters().speed) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackParameters(new PlaybackParameters(f)), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(f);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        if (this.controllerCompat != null) {
            if (bundle.isEmpty()) {
                bundle = sessionCommand.customExtras;
            } else if (!sessionCommand.customExtras.isEmpty()) {
                Bundle bundle2 = new Bundle(sessionCommand.customExtras);
                bundle2.putAll(bundle);
                bundle = bundle2;
            }
            this.controllerCompat.getTransportControls().sendCustomAction(sessionCommand.customAction, bundle);
            return Futures.immediateFuture(new SessionResult(0));
        }
        return Futures.immediateFuture(new SessionResult(-100));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, MediaController.ProgressListener progressListener) {
        return sendCustomCommand(sessionCommand, bundle);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Timeline getCurrentTimeline() {
        return this.controllerInfo.playerInfo.timeline;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem) {
        setMediaItem(mediaItem, C.TIME_UNSET);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem, long j) {
        setMediaItems(ImmutableList.of(mediaItem), 0, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem, boolean z) {
        setMediaItem(mediaItem);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, 0, C.TIME_UNSET);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list, boolean z) {
        setMediaItems(list);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list, int i, long j) {
        if (list.isEmpty()) {
            clearMediaItems();
            return;
        }
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndSessionPositionInfo(QueueTimeline.DEFAULT.copyWithNewMediaItems(0, list), createSessionPositionInfo(createPositionInfo(i, list.get(i), j == C.TIME_UNSET ? 0L : j, false), false, C.TIME_UNSET, 0L, 0, 0L), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            initializeLegacyPlaylist();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        Log.w(TAG, "Session doesn't support setting playlist metadata");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getPlaylistMetadata() {
        return this.controllerInfo.playerInfo.playlistMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(MediaItem mediaItem) {
        addMediaItems(Integer.MAX_VALUE, Collections.singletonList(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(int i, MediaItem mediaItem) {
        addMediaItems(i, Collections.singletonList(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(List<MediaItem> list) {
        addMediaItems(Integer.MAX_VALUE, list);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(int i, List<MediaItem> list) {
        Preconditions.checkArgument(i >= 0);
        if (list.isEmpty()) {
            return;
        }
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        if (queueTimeline.isEmpty()) {
            setMediaItems(list);
            return;
        }
        int iMin = Math.min(i, getCurrentTimeline().getWindowCount());
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithNewMediaItems(iMin, list), calculateCurrentItemIndexAfterAddItems(getCurrentMediaItemIndex(), iMin, list.size()), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            addQueueItems(list, iMin);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItem(int i) {
        removeMediaItems(i, i + 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItems(int i, int i2) {
        Preconditions.checkArgument(i >= 0 && i2 >= i);
        int windowCount = getCurrentTimeline().getWindowCount();
        int iMin = Math.min(i2, windowCount);
        if (i >= windowCount || i == iMin) {
            return;
        }
        QueueTimeline queueTimelineCopyWithRemovedMediaItems = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).copyWithRemovedMediaItems(i, iMin);
        int iCalculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i, iMin);
        if (iCalculateCurrentItemIndexAfterRemoveItems == -1) {
            iCalculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i, 0, queueTimelineCopyWithRemovedMediaItems.getWindowCount() - 1);
            Log.w(TAG, "Currently playing item is removed. Assumes item at " + iCalculateCurrentItemIndexAfterRemoveItems + " is the new current item");
        }
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimelineCopyWithRemovedMediaItems, iCalculateCurrentItemIndexAfterRemoveItems, 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            while (i < iMin && i < this.legacyPlayerInfo.queue.size()) {
                this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i).getDescription());
                i++;
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItem(int i, int i2) {
        moveMediaItems(i, i + 1, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItems(int i, int i2, int i3) {
        Preconditions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        int windowCount = queueTimeline.getWindowCount();
        int iMin = Math.min(i2, windowCount);
        int i4 = iMin - i;
        int i5 = windowCount - i4;
        int i6 = i5 - 1;
        int iMin2 = Math.min(i3, i5);
        if (i >= windowCount || i == iMin || i == iMin2) {
            return;
        }
        int iCalculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i, iMin);
        if (iCalculateCurrentItemIndexAfterRemoveItems == -1) {
            iCalculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i, 0, i6);
            Log.w(TAG, "Currently playing item will be removed and added back to mimic move. Assumes item at " + iCalculateCurrentItemIndexAfterRemoveItems + " would be the new current item");
        }
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithMovedMediaItems(i, iMin, iMin2), calculateCurrentItemIndexAfterAddItems(iCalculateCurrentItemIndexAfterRemoveItems, iMin2, i4), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            ArrayList arrayList = new ArrayList();
            for (int i7 = 0; i7 < i4; i7++) {
                arrayList.add(this.legacyPlayerInfo.queue.get(i));
                this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i).getDescription());
            }
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                this.controllerCompat.addQueueItem(((MediaSessionCompat.QueueItem) arrayList.get(i8)).getDescription(), i8 + iMin2);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItem(int i, MediaItem mediaItem) {
        replaceMediaItems(i, i + 1, ImmutableList.of(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        Preconditions.checkArgument(i >= 0 && i <= i2);
        int windowCount = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).getWindowCount();
        if (i > windowCount) {
            return;
        }
        int iMin = Math.min(i2, windowCount);
        addMediaItems(iMin, list);
        removeMediaItems(i, iMin);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentPeriodIndex() {
        return getCurrentMediaItemIndex();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentMediaItemIndex() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasPreviousMediaItem() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasNextMediaItem() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPreviousMediaItem() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNextMediaItem() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPrevious() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNext() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getMaxSeekToPreviousPosition() {
        return this.controllerInfo.playerInfo.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getRepeatMode() {
        return this.controllerInfo.playerInfo.repeatMode;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setRepeatMode(int i) {
        if (i != getRepeatMode()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithRepeatMode(i), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getShuffleModeEnabled() {
        return this.controllerInfo.playerInfo.shuffleModeEnabled;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setShuffleModeEnabled(boolean z) {
        if (z != getShuffleModeEnabled()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithShuffleModeEnabled(z), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public VideoSize getVideoSize() {
        Log.w(TAG, "Session doesn't support getting VideoSize");
        return VideoSize.UNKNOWN;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Size getSurfaceSize() {
        Log.w(TAG, "Session doesn't support getting VideoSurfaceSize");
        return Size.UNKNOWN;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface() {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support setting Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support setting SurfaceHolder");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support clearing SurfaceHolder");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support setting SurfaceView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support clearing SurfaceView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support setting TextureView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support clearing TextureView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public CueGroup getCurrentCues() {
        Log.w(TAG, "Session doesn't support getting Cue");
        return CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVolume(float f) {
        Log.w(TAG, "Session doesn't support setting player volume");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void mute() {
        Log.w(TAG, "Session doesn't support muting the player");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void unmute() {
        Log.w(TAG, "Session doesn't support unmuting the player");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public DeviceInfo getDeviceInfo() {
        return this.controllerInfo.playerInfo.deviceInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getDeviceVolume() {
        if (this.controllerInfo.playerInfo.deviceInfo.playbackType == 1) {
            return this.controllerInfo.playerInfo.deviceVolume;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat != null) {
            return LegacyConversions.convertToDeviceVolume(mediaControllerCompat.getPlaybackInfo());
        }
        return 0;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isDeviceMuted() {
        if (this.controllerInfo.playerInfo.deviceInfo.playbackType == 1) {
            return this.controllerInfo.playerInfo.deviceMuted;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        return mediaControllerCompat != null && LegacyConversions.convertToIsDeviceMuted(mediaControllerCompat.getPlaybackInfo());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceVolume(int i) {
        setDeviceVolume(i, 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceVolume(int i, int i2) {
        DeviceInfo deviceInfo = getDeviceInfo();
        int i3 = deviceInfo.minVolume;
        int i4 = deviceInfo.maxVolume;
        if (i3 <= i && (i4 == 0 || i <= i4)) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(i, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.setVolumeTo(i, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void increaseDeviceVolume() {
        increaseDeviceVolume(1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void increaseDeviceVolume(int i) {
        int deviceVolume = getDeviceVolume();
        int i2 = getDeviceInfo().maxVolume;
        if (i2 == 0 || deviceVolume + 1 <= i2) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume + 1, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(1, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void decreaseDeviceVolume() {
        decreaseDeviceVolume(1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void decreaseDeviceVolume(int i) {
        int deviceVolume = getDeviceVolume() - 1;
        if (deviceVolume >= getDeviceInfo().minVolume) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(-1, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceMuted(boolean z) {
        setDeviceMuted(z, 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceMuted(boolean z, int i) {
        if (z != isDeviceMuted()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(getDeviceVolume(), z), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(z ? -100 : 100, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        Log.w(TAG, "Legacy session doesn't support setting audio attributes remotely");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlayWhenReady(boolean z) {
        if (this.controllerInfo.playerInfo.playWhenReady == z) {
            return;
        }
        this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.controllerInfo.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlayWhenReady(z, 1, 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, null), null, null);
        if (isPrepared() && hasMedia()) {
            MediaControllerCompat mediaControllerCompat = this.controllerCompat;
            if (z) {
                mediaControllerCompat.getTransportControls().play();
            } else {
                mediaControllerCompat.getTransportControls().pause();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getPlayWhenReady() {
        return this.controllerInfo.playerInfo.playWhenReady;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackState() {
        return this.controllerInfo.playerInfo.playbackState;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlaying() {
        return this.controllerInfo.playerInfo.isPlaying;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getMediaMetadata() {
        MediaItem currentMediaItem = this.controllerInfo.playerInfo.getCurrentMediaItem();
        return currentMediaItem == null ? MediaMetadata.EMPTY : currentMediaItem.mediaMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Player.Commands getAvailableCommands() {
        return this.controllerInfo.availablePlayerCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Tracks getCurrentTracks() {
        return Tracks.EMPTY;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public TrackSelectionParameters getTrackSelectionParameters() {
        return TrackSelectionParameters.DEFAULT;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return this.controllerInfo.availableSessionCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaBrowserCompat getBrowserCompat() {
        return this.browserCompat;
    }

    void onConnected() {
        if (this.released || this.connected) {
            return;
        }
        this.connected = true;
        handleNewLegacyParameters(true, new LegacyPlayerInfo(this.controllerCompat.getPlaybackInfo(), convertToSafePlaybackStateCompat(this.controllerCompat.getPlaybackState()), this.controllerCompat.getMetadata(), convertToNonNullQueueItemList(this.controllerCompat.getQueue()), this.controllerCompat.getQueueTitle(), this.controllerCompat.getRepeatMode(), this.controllerCompat.getShuffleMode(), this.controllerCompat.getExtras()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToSession(final MediaSessionCompat.Token token) {
        getInstance().runOnApplicationLooper(new Runnable() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7736xd2b261cf(token);
            }
        });
        getInstance().applicationHandler.postDelayed(new Runnable() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7737x163d7f90();
            }
        }, 500L);
    }

    /* JADX INFO: renamed from: lambda$connectToSession$1$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7736xd2b261cf(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this.context, token);
        this.controllerCompat = mediaControllerCompat;
        mediaControllerCompat.registerCallback(this.controllerCompatCallback, getInstance().applicationHandler);
    }

    /* JADX INFO: renamed from: lambda$connectToSession$2$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7737x163d7f90() {
        if (this.released || this.controllerCompat.isSessionReady()) {
            return;
        }
        onConnected();
    }

    private void connectToService() {
        getInstance().runOnApplicationLooper(new Runnable() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7735x77d5ff0();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$connectToService$3$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7735x77d5ff0() {
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(this.context, this.token.getComponentName(), new ConnectionCallback(), this.instance.getConnectionHints());
        this.browserCompat = mediaBrowserCompat;
        mediaBrowserCompat.connect();
    }

    private boolean isPrepared() {
        return this.controllerInfo.playerInfo.playbackState != 1;
    }

    private boolean hasMedia() {
        return !this.controllerInfo.playerInfo.timeline.isEmpty();
    }

    private void initializeLegacyPlaylist() {
        Timeline.Window window = new Timeline.Window();
        Preconditions.checkState(isPrepared() && hasMedia());
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        int i = this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
        MediaItem mediaItem = queueTimeline.getWindow(i, window).mediaItem;
        if (queueTimeline.getQueueId(i) != -1) {
            boolean z = this.controllerInfo.playerInfo.playWhenReady;
            MediaControllerCompat mediaControllerCompat = this.controllerCompat;
            if (z) {
                mediaControllerCompat.getTransportControls().play();
            } else {
                mediaControllerCompat.getTransportControls().prepare();
            }
        } else if (mediaItem.requestMetadata.mediaUri != null) {
            boolean z2 = this.controllerInfo.playerInfo.playWhenReady;
            MediaControllerCompat mediaControllerCompat2 = this.controllerCompat;
            if (z2) {
                mediaControllerCompat2.getTransportControls().playFromUri(mediaItem.requestMetadata.mediaUri, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            } else {
                mediaControllerCompat2.getTransportControls().prepareFromUri(mediaItem.requestMetadata.mediaUri, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            }
        } else {
            String str = mediaItem.requestMetadata.searchQuery;
            ControllerInfo controllerInfo = this.controllerInfo;
            if (str != null) {
                boolean z3 = controllerInfo.playerInfo.playWhenReady;
                MediaControllerCompat mediaControllerCompat3 = this.controllerCompat;
                if (z3) {
                    mediaControllerCompat3.getTransportControls().playFromSearch(mediaItem.requestMetadata.searchQuery, getOrEmptyBundle(mediaItem.requestMetadata.extras));
                } else {
                    mediaControllerCompat3.getTransportControls().prepareFromSearch(mediaItem.requestMetadata.searchQuery, getOrEmptyBundle(mediaItem.requestMetadata.extras));
                }
            } else {
                boolean z4 = controllerInfo.playerInfo.playWhenReady;
                MediaControllerCompat mediaControllerCompat4 = this.controllerCompat;
                if (z4) {
                    mediaControllerCompat4.getTransportControls().playFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
                } else {
                    mediaControllerCompat4.getTransportControls().prepareFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
                }
            }
        }
        if (this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs != 0) {
            this.controllerCompat.getTransportControls().seekTo(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs);
        }
        if (getAvailableCommands().contains(20)) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < queueTimeline.getWindowCount(); i2++) {
                if (i2 != i && queueTimeline.getQueueId(i2) == -1) {
                    arrayList.add(queueTimeline.getWindow(i2, window).mediaItem);
                }
            }
            addQueueItems(arrayList, 0);
        }
    }

    private void addQueueItems(final List<MediaItem> list, final int i) {
        final ArrayList arrayList = new ArrayList();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = new Runnable() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7734x7d622417(atomicInteger, list, arrayList, i);
            }
        };
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaMetadata mediaMetadata = list.get(i2).mediaMetadata;
            if (mediaMetadata.artworkData == null) {
                arrayList.add(null);
                runnable.run();
            } else {
                ListenableFuture<Bitmap> listenableFutureDecodeBitmap = this.bitmapLoader.decodeBitmap(mediaMetadata.artworkData);
                arrayList.add(listenableFutureDecodeBitmap);
                Handler handler = getInstance().applicationHandler;
                Objects.requireNonNull(handler);
                listenableFutureDecodeBitmap.addListener(runnable, new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler));
            }
        }
    }

    /* JADX INFO: renamed from: lambda$addQueueItems$4$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7734x7d622417(AtomicInteger atomicInteger, List list, List list2, int i) {
        if (atomicInteger.incrementAndGet() == list.size()) {
            handleBitmapFuturesAllCompletedAndAddQueueItems(list2, list, i);
        }
    }

    private void handleBitmapFuturesAllCompletedAndAddQueueItems(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2, int i) {
        Bitmap bitmap;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ListenableFuture<Bitmap> listenableFuture = list.get(i2);
            if (listenableFuture != null) {
                try {
                    bitmap = (Bitmap) Futures.getDone(listenableFuture);
                } catch (CancellationException | ExecutionException e) {
                    Log.d(TAG, "Failed to get bitmap", e);
                    bitmap = null;
                }
            } else {
                bitmap = null;
            }
            this.controllerCompat.addQueueItem(LegacyConversions.convertToMediaDescriptionCompat(list2.get(i2), bitmap), i + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewLegacyParameters(boolean z, final LegacyPlayerInfo legacyPlayerInfo) {
        if (this.released || !this.connected) {
            return;
        }
        ControllerInfo controllerInfoBuildNewControllerInfo = buildNewControllerInfo(z, this.legacyPlayerInfo, this.controllerInfo, legacyPlayerInfo, this.controllerCompat.getPackageName(), this.controllerCompat.getFlags(), this.controllerCompat.isSessionReady(), this.controllerCompat.getRatingType(), getInstance().getTimeDiffMs(), this.hasPendingExtrasChange, this.context);
        Pair<Integer, Integer> pairCalculateDiscontinuityAndTransitionReason = calculateDiscontinuityAndTransitionReason(this.legacyPlayerInfo, this.controllerInfo, legacyPlayerInfo, controllerInfoBuildNewControllerInfo, getInstance().getTimeDiffMs());
        updateControllerInfo(z, legacyPlayerInfo, true, controllerInfoBuildNewControllerInfo, (Integer) pairCalculateDiscontinuityAndTransitionReason.first, (Integer) pairCalculateDiscontinuityAndTransitionReason.second);
        if (this.hasPendingExtrasChange) {
            this.hasPendingExtrasChange = false;
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda21
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7738xb99f2e33(legacyPlayerInfo, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$handleNewLegacyParameters$5$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7738xb99f2e33(LegacyPlayerInfo legacyPlayerInfo, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), legacyPlayerInfo.sessionExtras);
    }

    private void updateStateMaskedControllerInfo(ControllerInfo controllerInfo, Integer num, Integer num2) {
        updateControllerInfo(false, this.legacyPlayerInfo, false, controllerInfo, num, num2);
    }

    private void updateControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo, boolean z2, final ControllerInfo controllerInfo, final Integer num, final Integer num2) {
        LegacyPlayerInfo legacyPlayerInfo2 = this.legacyPlayerInfo;
        final ControllerInfo controllerInfo2 = this.controllerInfo;
        if (legacyPlayerInfo2 != legacyPlayerInfo) {
            this.legacyPlayerInfo = new LegacyPlayerInfo(legacyPlayerInfo);
        }
        if (z2) {
            this.pendingLegacyPlayerInfo = this.legacyPlayerInfo;
        }
        this.controllerInfo = controllerInfo;
        if (z) {
            getInstance().notifyAccepted();
            if (controllerInfo2.mediaButtonPreferences.equals(controllerInfo.mediaButtonPreferences)) {
                return;
            }
            getInstance().applicationHandler.post(new Runnable() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda24
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7745xb5d10697(controllerInfo);
                }
            });
            return;
        }
        if (!controllerInfo2.playerInfo.timeline.equals(controllerInfo.playerInfo.timeline)) {
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda7
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    MediaControllerImplLegacy.ControllerInfo controllerInfo3 = controllerInfo;
                    ((Player.Listener) obj).onTimelineChanged(controllerInfo3.playerInfo.timeline, controllerInfo3.playerInfo.timelineChangeReason);
                }
            });
        }
        if (!TextUtils.equals(legacyPlayerInfo2.queueTitle, legacyPlayerInfo.queueTitle)) {
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda10
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(controllerInfo.playerInfo.playlistMetadata);
                }
            });
        }
        if (num != null) {
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda12
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPositionDiscontinuity(controllerInfo2.playerInfo.sessionPositionInfo.positionInfo, controllerInfo.playerInfo.sessionPositionInfo.positionInfo, num.intValue());
                }
            });
        }
        if (num2 != null) {
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda13
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaItemTransition(controllerInfo.playerInfo.getCurrentMediaItem(), num2.intValue());
                }
            });
        }
        if (!MediaUtils.areEqualError(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo.playbackStateCompat)) {
            final PlaybackException playbackExceptionConvertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo.playbackStateCompat, this.context);
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda14
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayerErrorChanged(playbackExceptionConvertToPlaybackException);
                }
            });
            if (playbackExceptionConvertToPlaybackException != null) {
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda15
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlayerError(playbackExceptionConvertToPlaybackException);
                    }
                });
            }
        }
        if (legacyPlayerInfo2.mediaMetadataCompat != legacyPlayerInfo.mediaMetadataCompat) {
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda16
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    this.f$0.m7740x30dfcbab((Player.Listener) obj);
                }
            });
        }
        if (controllerInfo2.playerInfo.playbackState != controllerInfo.playerInfo.playbackState) {
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda17
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackStateChanged(controllerInfo.playerInfo.playbackState);
                }
            });
        }
        if (controllerInfo2.playerInfo.playWhenReady != controllerInfo.playerInfo.playWhenReady) {
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda18
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayWhenReadyChanged(controllerInfo.playerInfo.playWhenReady, 4);
                }
            });
        }
        if (controllerInfo2.playerInfo.isPlaying != controllerInfo.playerInfo.isPlaying) {
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda25
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsPlayingChanged(controllerInfo.playerInfo.isPlaying);
                }
            });
        }
        if (!controllerInfo2.playerInfo.playbackParameters.equals(controllerInfo.playerInfo.playbackParameters)) {
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda26
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(controllerInfo.playerInfo.playbackParameters);
                }
            });
        }
        if (controllerInfo2.playerInfo.repeatMode != controllerInfo.playerInfo.repeatMode) {
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda27
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onRepeatModeChanged(controllerInfo.playerInfo.repeatMode);
                }
            });
        }
        if (controllerInfo2.playerInfo.shuffleModeEnabled != controllerInfo.playerInfo.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda28
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onShuffleModeEnabledChanged(controllerInfo.playerInfo.shuffleModeEnabled);
                }
            });
        }
        if (!controllerInfo2.playerInfo.audioAttributes.equals(controllerInfo.playerInfo.audioAttributes)) {
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(controllerInfo.playerInfo.audioAttributes);
                }
            });
        }
        if (controllerInfo2.playerInfo.audioSessionId != controllerInfo.playerInfo.audioSessionId) {
            this.listeners.queueEvent(21, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda2
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioSessionIdChanged(controllerInfo.playerInfo.audioSessionId);
                }
            });
        }
        if (!controllerInfo2.playerInfo.deviceInfo.equals(controllerInfo.playerInfo.deviceInfo)) {
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda3
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceInfoChanged(controllerInfo.playerInfo.deviceInfo);
                }
            });
        }
        if (controllerInfo2.playerInfo.deviceVolume != controllerInfo.playerInfo.deviceVolume || controllerInfo2.playerInfo.deviceMuted != controllerInfo.playerInfo.deviceMuted) {
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda4
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    MediaControllerImplLegacy.ControllerInfo controllerInfo3 = controllerInfo;
                    ((Player.Listener) obj).onDeviceVolumeChanged(controllerInfo3.playerInfo.deviceVolume, controllerInfo3.playerInfo.deviceMuted);
                }
            });
        }
        if (!controllerInfo2.availablePlayerCommands.equals(controllerInfo.availablePlayerCommands)) {
            this.listeners.queueEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda5
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAvailableCommandsChanged(controllerInfo.availablePlayerCommands);
                }
            });
        }
        if (!controllerInfo2.availableSessionCommands.equals(controllerInfo.availableSessionCommands)) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda6
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7741xe5cea18c(controllerInfo, (MediaController.Listener) obj);
                }
            });
        }
        if (!controllerInfo2.mediaButtonPreferences.equals(controllerInfo.mediaButtonPreferences)) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda8
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7742x2959bf4d(controllerInfo, (MediaController.Listener) obj);
                }
            });
        }
        if (controllerInfo.sessionError != null) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda9
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7743x6ce4dd0e(controllerInfo, (MediaController.Listener) obj);
                }
            });
        }
        this.listeners.flushEvents();
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$7$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7745xb5d10697(final ControllerInfo controllerInfo) {
        getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$$ExternalSyntheticLambda19
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7744x7245e8d6(controllerInfo, (MediaController.Listener) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$6$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7744x7245e8d6(ControllerInfo controllerInfo, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo.mediaButtonPreferences);
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$14$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7740x30dfcbab(Player.Listener listener) {
        listener.onMediaMetadataChanged(this.controllerInfo.playerInfo.mediaMetadata);
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$26$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7741xe5cea18c(ControllerInfo controllerInfo, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), controllerInfo.availableSessionCommands);
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$27$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7742x2959bf4d(ControllerInfo controllerInfo, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo.mediaButtonPreferences);
    }

    /* JADX INFO: renamed from: lambda$updateControllerInfo$28$androidx-media3-session-MediaControllerImplLegacy, reason: not valid java name */
    /* synthetic */ void m7743x6ce4dd0e(ControllerInfo controllerInfo, MediaController.Listener listener) {
        listener.onError(getInstance(), controllerInfo.sessionError);
    }

    private class ConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private ConnectionCallback() {
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            MediaBrowserCompat browserCompat = MediaControllerImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                MediaControllerImplLegacy.this.connectToSession(browserCompat.getSessionToken());
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class ControllerCompatCallback extends MediaControllerCompat.Callback {
        private static final int MSG_HANDLE_PENDING_UPDATES = 1;
        private final Handler pendingChangesHandler;

        public ControllerCompatCallback(Looper looper) {
            this.pendingChangesHandler = new Handler(looper, new Handler.Callback() { // from class: androidx.media3.session.MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda1
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return this.f$0.m7746x38d58084(message);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback, reason: not valid java name */
        /* synthetic */ boolean m7746x38d58084(Message message) {
            if (message.what == 1) {
                MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
                mediaControllerImplLegacy.handleNewLegacyParameters(false, mediaControllerImplLegacy.pendingLegacyPlayerInfo);
            }
            return true;
        }

        public void release() {
            this.pendingChangesHandler.removeCallbacksAndMessages(null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionReady() {
            boolean z = MediaControllerImplLegacy.this.connected;
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            if (z) {
                mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithExtraBinderGetters(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(MediaControllerImplLegacy.this.controllerCompat.getPlaybackState()), MediaControllerImplLegacy.this.controllerCompat.getRepeatMode(), MediaControllerImplLegacy.this.controllerCompat.getShuffleMode());
                onCaptioningEnabledChanged(MediaControllerImplLegacy.this.controllerCompat.isCaptioningEnabled());
                this.pendingChangesHandler.removeMessages(1);
                MediaControllerImplLegacy mediaControllerImplLegacy2 = MediaControllerImplLegacy.this;
                mediaControllerImplLegacy2.handleNewLegacyParameters(false, mediaControllerImplLegacy2.pendingLegacyPlayerInfo);
                return;
            }
            mediaControllerImplLegacy.onConnected();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionEvent(final String str, final Bundle bundle) {
            if (str == null) {
                return;
            }
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda2
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7748x2abd04a6(str, bundle, (MediaController.Listener) obj);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onSessionEvent$1$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback, reason: not valid java name */
        /* synthetic */ void m7748x2abd04a6(String str, Bundle bundle, MediaController.Listener listener) {
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(MediaControllerImplLegacy.this.getInstance(), new SessionCommand(str, bundle), bundle));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackStateCompat(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(playbackStateCompat));
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithMediaMetadataCompat(mediaMetadataCompat);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueue(MediaControllerImplLegacy.convertToNonNullQueueItemList(list));
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onQueueTitleChanged(CharSequence charSequence) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueueTitle(charSequence);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onExtrasChanged(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithSessionExtras(bundle);
            MediaControllerImplLegacy.this.hasPendingExtrasChange = true;
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo playbackInfo) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackInfoCompat(playbackInfo);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onCaptioningEnabledChanged(final boolean z) {
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7747x8ca133fa(z, (MediaController.Listener) obj);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onCaptioningEnabledChanged$2$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback, reason: not valid java name */
        /* synthetic */ void m7747x8ca133fa(boolean z, MediaController.Listener listener) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("androidx.media3.session.ARGUMENT_CAPTIONING_ENABLED", z);
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(MediaControllerImplLegacy.this.getInstance(), new SessionCommand("androidx.media3.session.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED", Bundle.EMPTY), bundle));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onRepeatModeChanged(int i) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithRepeatMode(i);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onShuffleModeChanged(int i) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithShuffleMode(i);
            startWaitingForPendingChanges();
        }

        private void startWaitingForPendingChanges() {
            if (this.pendingChangesHandler.hasMessages(1)) {
                return;
            }
            this.pendingChangesHandler.sendEmptyMessageDelayed(1, MediaControllerImplLegacy.this.platformSessionCallbackAggregationTimeoutMs);
        }
    }

    private static ControllerInfo buildNewControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo, ControllerInfo controllerInfo, LegacyPlayerInfo legacyPlayerInfo2, String str, long j, boolean z2, int i, long j2, boolean z3, Context context) {
        QueueTimeline queueTimelineCopy;
        MediaMetadata mediaMetadataConvertToMediaMetadata;
        int windowCount;
        MediaMetadata mediaMetadata;
        MediaMetadata mediaMetadataConvertToMediaMetadata2;
        SessionCommands sessionCommandsConvertToSessionCommands;
        ImmutableList<CommandButton> immutableListConvertToMediaButtonPreferences;
        AudioAttributes audioAttributes;
        int iConvertToPlaybackState;
        preserveExistingBitmapData(legacyPlayerInfo, legacyPlayerInfo2);
        boolean z4 = legacyPlayerInfo.queue != legacyPlayerInfo2.queue;
        if (z4) {
            queueTimelineCopy = QueueTimeline.create(legacyPlayerInfo2.queue);
        } else {
            queueTimelineCopy = ((QueueTimeline) controllerInfo.playerInfo.timeline).copy();
        }
        boolean z5 = legacyPlayerInfo.mediaMetadataCompat != legacyPlayerInfo2.mediaMetadataCompat || z;
        long activeQueueId = getActiveQueueId(legacyPlayerInfo.playbackStateCompat);
        long activeQueueId2 = getActiveQueueId(legacyPlayerInfo2.playbackStateCompat);
        boolean z6 = activeQueueId != activeQueueId2 || z;
        long jConvertToDurationMs = LegacyConversions.convertToDurationMs(legacyPlayerInfo2.mediaMetadataCompat);
        boolean z7 = z4;
        if (z5 || z6 || z7) {
            boolean z8 = z5;
            int iFindQueueItemIndex = findQueueItemIndex(legacyPlayerInfo2.queue, activeQueueId2);
            boolean z9 = legacyPlayerInfo2.mediaMetadataCompat != null;
            if (z9 && z8) {
                mediaMetadataConvertToMediaMetadata = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo2.mediaMetadataCompat, i);
            } else if (z9 || !z6) {
                mediaMetadataConvertToMediaMetadata = controllerInfo.playerInfo.mediaMetadata;
            } else if (iFindQueueItemIndex == -1) {
                mediaMetadataConvertToMediaMetadata = MediaMetadata.EMPTY;
            } else {
                mediaMetadataConvertToMediaMetadata = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo2.queue.get(iFindQueueItemIndex).getDescription(), i);
            }
            if (iFindQueueItemIndex == -1 && z8) {
                if (z9) {
                    Log.w(TAG, "Adding a fake MediaItem at the end of the list because there's no QueueItem with the active queue id and current Timeline should have currently playing MediaItem.");
                    queueTimelineCopy = queueTimelineCopy.copyWithFakeMediaItem(LegacyConversions.convertToMediaItem(legacyPlayerInfo2.mediaMetadataCompat, i), jConvertToDurationMs);
                    windowCount = queueTimelineCopy.getWindowCount() - 1;
                } else {
                    queueTimelineCopy = queueTimelineCopy.copyWithClearedFakeMediaItem();
                    windowCount = 0;
                }
            } else if (iFindQueueItemIndex != -1) {
                queueTimelineCopy = queueTimelineCopy.copyWithClearedFakeMediaItem();
                if (z9) {
                    queueTimelineCopy = queueTimelineCopy.copyWithNewMediaItem(iFindQueueItemIndex, LegacyConversions.convertToMediaItem(((MediaItem) Preconditions.checkNotNull(queueTimelineCopy.getMediaItemAt(iFindQueueItemIndex))).mediaId, legacyPlayerInfo2.mediaMetadataCompat, i), jConvertToDurationMs);
                }
                windowCount = iFindQueueItemIndex;
            } else {
                windowCount = 0;
            }
            mediaMetadata = mediaMetadataConvertToMediaMetadata;
        } else {
            windowCount = controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            mediaMetadata = controllerInfo.playerInfo.mediaMetadata;
        }
        int i2 = windowCount;
        QueueTimeline queueTimeline = queueTimelineCopy;
        Player.Commands commandsConvertToPlayerCommands = LegacyConversions.convertToPlayerCommands(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.playbackInfoCompat != null ? legacyPlayerInfo2.playbackInfoCompat.getVolumeControl() : 0, j, z2);
        if (legacyPlayerInfo.queueTitle == legacyPlayerInfo2.queueTitle) {
            mediaMetadataConvertToMediaMetadata2 = controllerInfo.playerInfo.playlistMetadata;
        } else {
            mediaMetadataConvertToMediaMetadata2 = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo2.queueTitle);
        }
        MediaMetadata mediaMetadata2 = mediaMetadataConvertToMediaMetadata2;
        int iConvertToRepeatMode = LegacyConversions.convertToRepeatMode(legacyPlayerInfo2.repeatMode);
        boolean zConvertToShuffleModeEnabled = LegacyConversions.convertToShuffleModeEnabled(legacyPlayerInfo2.shuffleMode);
        if (legacyPlayerInfo.playbackStateCompat != legacyPlayerInfo2.playbackStateCompat || z3) {
            sessionCommandsConvertToSessionCommands = LegacyConversions.convertToSessionCommands(legacyPlayerInfo2.playbackStateCompat, z2);
            immutableListConvertToMediaButtonPreferences = LegacyConversions.convertToMediaButtonPreferences(legacyPlayerInfo2.playbackStateCompat, commandsConvertToPlayerCommands, legacyPlayerInfo2.sessionExtras);
        } else {
            sessionCommandsConvertToSessionCommands = controllerInfo.availableSessionCommands;
            immutableListConvertToMediaButtonPreferences = controllerInfo.mediaButtonPreferences;
        }
        SessionCommands sessionCommands = sessionCommandsConvertToSessionCommands;
        ImmutableList<CommandButton> immutableList = immutableListConvertToMediaButtonPreferences;
        PlaybackException playbackExceptionConvertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo2.playbackStateCompat, context);
        SessionError sessionErrorConvertToSessionError = LegacyConversions.convertToSessionError(legacyPlayerInfo2.playbackStateCompat, context);
        long jConvertToCurrentPositionMs = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j2);
        long jConvertToBufferedPositionMs = LegacyConversions.convertToBufferedPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j2);
        int iConvertToBufferedPercentage = LegacyConversions.convertToBufferedPercentage(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j2);
        long jConvertToTotalBufferedDurationMs = LegacyConversions.convertToTotalBufferedDurationMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j2);
        boolean zConvertToIsPlayingAd = LegacyConversions.convertToIsPlayingAd(legacyPlayerInfo2.mediaMetadataCompat);
        PlaybackParameters playbackParametersConvertToPlaybackParameters = LegacyConversions.convertToPlaybackParameters(legacyPlayerInfo2.playbackStateCompat);
        if (legacyPlayerInfo2.playbackInfoCompat == null) {
            audioAttributes = AudioAttributes.DEFAULT;
        } else {
            audioAttributes = legacyPlayerInfo2.playbackInfoCompat.getAudioAttributes();
        }
        AudioAttributes audioAttributes2 = audioAttributes;
        boolean zConvertToPlayWhenReady = LegacyConversions.convertToPlayWhenReady(legacyPlayerInfo2.playbackStateCompat);
        try {
            iConvertToPlaybackState = LegacyConversions.convertToPlaybackState(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j2);
        } catch (LegacyConversions.ConversionException unused) {
            Log.e(TAG, String.format("Received invalid playback state %s from package %s. Keeping the previous state.", Integer.valueOf(legacyPlayerInfo2.playbackStateCompat.getState()), str));
            iConvertToPlaybackState = controllerInfo.playerInfo.playbackState;
        }
        return createControllerInfo(queueTimeline, mediaMetadata, i2, mediaMetadata2, iConvertToRepeatMode, zConvertToShuffleModeEnabled, sessionCommands, commandsConvertToPlayerCommands, immutableList, legacyPlayerInfo2.sessionExtras, playbackExceptionConvertToPlaybackException, sessionErrorConvertToSessionError, jConvertToDurationMs, jConvertToCurrentPositionMs, jConvertToBufferedPositionMs, iConvertToBufferedPercentage, jConvertToTotalBufferedDurationMs, zConvertToIsPlayingAd, playbackParametersConvertToPlaybackParameters, audioAttributes2, zConvertToPlayWhenReady, iConvertToPlaybackState, LegacyConversions.convertToIsPlaying(legacyPlayerInfo2.playbackStateCompat), LegacyConversions.convertToDeviceInfo(legacyPlayerInfo2.playbackInfoCompat), LegacyConversions.convertToDeviceVolume(legacyPlayerInfo2.playbackInfoCompat), LegacyConversions.convertToIsDeviceMuted(legacyPlayerInfo2.playbackInfoCompat), controllerInfo.playerInfo.seekBackIncrementMs, controllerInfo.playerInfo.seekForwardIncrementMs, controllerInfo.playerInfo.maxSeekToPreviousPositionMs);
    }

    private static Pair<Integer, Integer> calculateDiscontinuityAndTransitionReason(LegacyPlayerInfo legacyPlayerInfo, ControllerInfo controllerInfo, LegacyPlayerInfo legacyPlayerInfo2, ControllerInfo controllerInfo2, long j) {
        Integer num;
        Integer num2;
        int i;
        boolean zIsEmpty = controllerInfo.playerInfo.timeline.isEmpty();
        boolean zIsEmpty2 = controllerInfo2.playerInfo.timeline.isEmpty();
        Integer num3 = null;
        if (zIsEmpty && zIsEmpty2) {
            num = null;
        } else if (zIsEmpty && !zIsEmpty2) {
            num3 = 0;
            num = 3;
        } else {
            MediaItem mediaItem = (MediaItem) Preconditions.checkNotNull(controllerInfo.playerInfo.getCurrentMediaItem());
            if (!((QueueTimeline) controllerInfo2.playerInfo.timeline).contains(mediaItem)) {
                num3 = 4;
                num = 3;
            } else if (mediaItem.equals(controllerInfo2.playerInfo.getCurrentMediaItem())) {
                long jConvertToCurrentPositionMs = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo.playbackStateCompat, legacyPlayerInfo.mediaMetadataCompat, j);
                long jConvertToCurrentPositionMs2 = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j);
                if (jConvertToCurrentPositionMs2 == 0 && controllerInfo2.playerInfo.repeatMode == 1) {
                    i = 0;
                    num2 = 0;
                } else {
                    if (Math.abs(jConvertToCurrentPositionMs - jConvertToCurrentPositionMs2) > 100) {
                        i = 5;
                        num2 = null;
                    } else {
                        num2 = null;
                    }
                    num = num2;
                }
                num3 = i;
                num = num2;
            } else {
                num3 = 0;
                num = 1;
            }
        }
        return Pair.create(num3, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<MediaSessionCompat.QueueItem> convertToNonNullQueueItemList(List<MediaSessionCompat.QueueItem> list) {
        return list == null ? Collections.EMPTY_LIST : MediaUtils.removeNullElements(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlaybackStateCompat convertToSafePlaybackStateCompat(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return null;
        }
        if (playbackStateCompat.getPlaybackSpeed() > 0.0f) {
            return playbackStateCompat;
        }
        Log.w(TAG, "Adjusting playback speed to 1.0f because negative playback speed isn't supported.");
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), playbackStateCompat.getPosition(), 1.0f, playbackStateCompat.getLastPositionUpdateTime()).build();
    }

    private static Bundle getOrEmptyBundle(Bundle bundle) {
        return bundle == null ? Bundle.EMPTY : bundle;
    }

    private static long getActiveQueueId(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return -1L;
        }
        return playbackStateCompat.getActiveQueueItemId();
    }

    private static int findQueueItemIndex(List<MediaSessionCompat.QueueItem> list, long j) {
        if (list != null && j != -1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getQueueId() == j) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static ControllerInfo createControllerInfo(QueueTimeline queueTimeline, MediaMetadata mediaMetadata, int i, MediaMetadata mediaMetadata2, int i2, boolean z, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, Bundle bundle, PlaybackException playbackException, SessionError sessionError, long j, long j2, long j3, int i3, long j4, boolean z2, PlaybackParameters playbackParameters, AudioAttributes audioAttributes, boolean z3, int i4, boolean z4, DeviceInfo deviceInfo, int i5, boolean z5, long j5, long j6, long j7) {
        return new ControllerInfo(new PlayerInfo(playbackException, 0, new SessionPositionInfo(createPositionInfo(i, queueTimeline.getMediaItemAt(i), j2, z2), z2, SystemClock.elapsedRealtime(), j, j3, i3, j4, C.TIME_UNSET, j, j3), SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT_POSITION_INFO, 0, playbackParameters, i2, z, VideoSize.UNKNOWN, queueTimeline, 0, mediaMetadata2, 1.0f, 1.0f, audioAttributes, 0, CueGroup.EMPTY_TIME_ZERO, deviceInfo, i5, z5, z3, 1, 0, i4, z4, false, mediaMetadata, j5, j6, j7, Tracks.EMPTY, TrackSelectionParameters.DEFAULT), sessionCommands, commands, immutableList, bundle, sessionError);
    }

    private static Player.PositionInfo createPositionInfo(int i, MediaItem mediaItem, long j, boolean z) {
        return new Player.PositionInfo(null, i, mediaItem, null, i, j, j, z ? 0 : -1, z ? 0 : -1);
    }

    private static SessionPositionInfo createSessionPositionInfo(Player.PositionInfo positionInfo, boolean z, long j, long j2, int i, long j3) {
        return new SessionPositionInfo(positionInfo, z, SystemClock.elapsedRealtime(), j, j2, i, j3, C.TIME_UNSET, j, j2);
    }

    private static void preserveExistingBitmapData(LegacyPlayerInfo legacyPlayerInfo, LegacyPlayerInfo legacyPlayerInfo2) {
        MediaSessionCompat.QueueItem queueItem;
        if (legacyPlayerInfo.mediaMetadataCompat != null && legacyPlayerInfo2.mediaMetadataCompat != null) {
            legacyPlayerInfo2.mediaMetadataCompat.preserveArtworkBitmapData(legacyPlayerInfo.mediaMetadataCompat);
        }
        if (legacyPlayerInfo.queue != legacyPlayerInfo2.queue) {
            HashMap map = new HashMap();
            for (int i = 0; i < legacyPlayerInfo.queue.size(); i++) {
                MediaSessionCompat.QueueItem queueItem2 = legacyPlayerInfo.queue.get(i);
                if (queueItem2.getDescription().getIconBitmap() != null) {
                    map.put(Long.valueOf(queueItem2.getQueueId()), queueItem2);
                }
            }
            for (int i2 = 0; i2 < legacyPlayerInfo2.queue.size(); i2++) {
                MediaSessionCompat.QueueItem queueItem3 = legacyPlayerInfo2.queue.get(i2);
                if (queueItem3.getDescription().getIconBitmap() != null && (queueItem = (MediaSessionCompat.QueueItem) map.get(Long.valueOf(queueItem3.getQueueId()))) != null) {
                    queueItem3.getDescription().preserveIconBitmapData(queueItem.getDescription());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class LegacyPlayerInfo {
        public final MediaMetadataCompat mediaMetadataCompat;
        public final MediaControllerCompat.PlaybackInfo playbackInfoCompat;
        public final PlaybackStateCompat playbackStateCompat;
        public final List<MediaSessionCompat.QueueItem> queue;
        public final CharSequence queueTitle;
        public final int repeatMode;
        public final Bundle sessionExtras;
        public final int shuffleMode;

        public LegacyPlayerInfo() {
            this.playbackInfoCompat = null;
            this.playbackStateCompat = null;
            this.mediaMetadataCompat = null;
            this.queue = Collections.EMPTY_LIST;
            this.queueTitle = null;
            this.repeatMode = 0;
            this.shuffleMode = 0;
            this.sessionExtras = Bundle.EMPTY;
        }

        public LegacyPlayerInfo(MediaControllerCompat.PlaybackInfo playbackInfo, PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, List<MediaSessionCompat.QueueItem> list, CharSequence charSequence, int i, int i2, Bundle bundle) {
            this.playbackInfoCompat = playbackInfo;
            this.playbackStateCompat = playbackStateCompat;
            this.mediaMetadataCompat = mediaMetadataCompat;
            this.queue = (List) Preconditions.checkNotNull(list);
            this.queueTitle = charSequence;
            this.repeatMode = i;
            this.shuffleMode = i2;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
        }

        public LegacyPlayerInfo(LegacyPlayerInfo legacyPlayerInfo) {
            this.playbackInfoCompat = legacyPlayerInfo.playbackInfoCompat;
            this.playbackStateCompat = legacyPlayerInfo.playbackStateCompat;
            this.mediaMetadataCompat = legacyPlayerInfo.mediaMetadataCompat;
            this.queue = legacyPlayerInfo.queue;
            this.queueTitle = legacyPlayerInfo.queueTitle;
            this.repeatMode = legacyPlayerInfo.repeatMode;
            this.shuffleMode = legacyPlayerInfo.shuffleMode;
            this.sessionExtras = legacyPlayerInfo.sessionExtras;
        }

        public LegacyPlayerInfo copyWithExtraBinderGetters(PlaybackStateCompat playbackStateCompat, int i, int i2) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, i, i2, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackStateCompat(PlaybackStateCompat playbackStateCompat) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithMediaMetadataCompat(MediaMetadataCompat mediaMetadataCompat) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueue(List<MediaSessionCompat.QueueItem> list) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, list, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueueTitle(CharSequence charSequence) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, charSequence, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackInfoCompat(MediaControllerCompat.PlaybackInfo playbackInfo) {
            return new LegacyPlayerInfo(playbackInfo, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithRepeatMode(int i) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, i, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithShuffleMode(int i) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, i, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithSessionExtras(Bundle bundle) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ControllerInfo {
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final ImmutableList<CommandButton> mediaButtonPreferences;
        public final PlayerInfo playerInfo;
        public final SessionError sessionError;
        public final Bundle sessionExtras;

        public ControllerInfo() {
            this.playerInfo = PlayerInfo.DEFAULT.copyWithTimeline(QueueTimeline.DEFAULT);
            this.availableSessionCommands = SessionCommands.EMPTY;
            this.availablePlayerCommands = Player.Commands.EMPTY;
            this.mediaButtonPreferences = ImmutableList.of();
            this.sessionExtras = Bundle.EMPTY;
            this.sessionError = null;
        }

        public ControllerInfo(PlayerInfo playerInfo, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, Bundle bundle, SessionError sessionError) {
            this.playerInfo = playerInfo;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.mediaButtonPreferences = immutableList;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
            this.sessionError = sessionError;
        }
    }
}

package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
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
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.DoNotMock;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: loaded from: classes4.dex */
@DoNotMock
public class MediaController implements Player {
    protected static final long DEFAULT_PLATFORM_CALLBACK_AGGREGATION_TIMEOUT_MS = 100;
    public static final String KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG = "androidx.media3.session.MediaNotificationManager";
    public static final long RELEASE_UNBIND_TIMEOUT_MS = 30000;
    private static final String TAG = "MediaController";
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaController method is called from a wrong thread. See javadoc of MediaController for details.";
    final Handler applicationHandler;
    final ConnectionCallback connectionCallback;
    private boolean connectionNotified;

    @NotOnlyInitialized
    private final MediaControllerImpl impl;
    final Listener listener;
    private final int maxCommandsForMediaItems;
    private boolean released;
    private long timeDiffMs;
    private final Timeline.Window window;

    interface ConnectionCallback {
        void onAccepted();

        void onRejected();
    }

    interface MediaControllerImpl {
        void addListener(Player.Listener listener);

        void addMediaItem(int i, MediaItem mediaItem);

        void addMediaItem(MediaItem mediaItem);

        void addMediaItems(int i, List<MediaItem> list);

        void addMediaItems(List<MediaItem> list);

        void clearMediaItems();

        void clearVideoSurface();

        void clearVideoSurface(Surface surface);

        void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void clearVideoSurfaceView(SurfaceView surfaceView);

        void clearVideoTextureView(TextureView textureView);

        void connect();

        void decreaseDeviceVolume();

        void decreaseDeviceVolume(int i);

        AudioAttributes getAudioAttributes();

        int getAudioSessionId();

        Player.Commands getAvailableCommands();

        SessionCommands getAvailableSessionCommands();

        IMediaController getBinder();

        MediaBrowserCompat getBrowserCompat();

        int getBufferedPercentage();

        long getBufferedPosition();

        ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem);

        SessionToken getConnectedToken();

        Bundle getConnectionHints();

        long getContentBufferedPosition();

        long getContentDuration();

        long getContentPosition();

        Context getContext();

        int getCurrentAdGroupIndex();

        int getCurrentAdIndexInAdGroup();

        CueGroup getCurrentCues();

        long getCurrentLiveOffset();

        int getCurrentMediaItemIndex();

        int getCurrentPeriodIndex();

        long getCurrentPosition();

        Timeline getCurrentTimeline();

        Tracks getCurrentTracks();

        ImmutableList<CommandButton> getCustomLayout();

        DeviceInfo getDeviceInfo();

        int getDeviceVolume();

        long getDuration();

        long getMaxSeekToPreviousPosition();

        ImmutableList<CommandButton> getMediaButtonPreferences();

        MediaMetadata getMediaMetadata();

        int getNextMediaItemIndex();

        boolean getPlayWhenReady();

        PlaybackParameters getPlaybackParameters();

        int getPlaybackState();

        int getPlaybackSuppressionReason();

        PlaybackException getPlayerError();

        MediaMetadata getPlaylistMetadata();

        int getPreviousMediaItemIndex();

        int getRepeatMode();

        long getSeekBackIncrement();

        long getSeekForwardIncrement();

        PendingIntent getSessionActivity();

        Bundle getSessionExtras();

        boolean getShuffleModeEnabled();

        Size getSurfaceSize();

        long getTotalBufferedDuration();

        TrackSelectionParameters getTrackSelectionParameters();

        VideoSize getVideoSize();

        float getVolume();

        boolean hasNextMediaItem();

        boolean hasPreviousMediaItem();

        void increaseDeviceVolume();

        void increaseDeviceVolume(int i);

        boolean isConnected();

        boolean isDeviceMuted();

        boolean isLoading();

        boolean isPlaying();

        boolean isPlayingAd();

        void moveMediaItem(int i, int i2);

        void moveMediaItems(int i, int i2, int i3);

        void mute();

        void pause();

        void play();

        void prepare();

        void release();

        void removeListener(Player.Listener listener);

        void removeMediaItem(int i);

        void removeMediaItems(int i, int i2);

        void replaceMediaItem(int i, MediaItem mediaItem);

        void replaceMediaItems(int i, int i2, List<MediaItem> list);

        void seekBack();

        void seekForward();

        void seekTo(int i, long j);

        void seekTo(long j);

        void seekToDefaultPosition();

        void seekToDefaultPosition(int i);

        void seekToNext();

        void seekToNextMediaItem();

        void seekToPrevious();

        void seekToPreviousMediaItem();

        ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle);

        ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, ProgressListener progressListener);

        void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

        void setDeviceMuted(boolean z);

        void setDeviceMuted(boolean z, int i);

        void setDeviceVolume(int i);

        void setDeviceVolume(int i, int i2);

        void setMediaItem(MediaItem mediaItem);

        void setMediaItem(MediaItem mediaItem, long j);

        void setMediaItem(MediaItem mediaItem, boolean z);

        void setMediaItems(List<MediaItem> list);

        void setMediaItems(List<MediaItem> list, int i, long j);

        void setMediaItems(List<MediaItem> list, boolean z);

        void setPlayWhenReady(boolean z);

        void setPlaybackParameters(PlaybackParameters playbackParameters);

        void setPlaybackSpeed(float f);

        void setPlaylistMetadata(MediaMetadata mediaMetadata);

        ListenableFuture<SessionResult> setRating(Rating rating);

        ListenableFuture<SessionResult> setRating(String str, Rating rating);

        void setRepeatMode(int i);

        void setShuffleModeEnabled(boolean z);

        void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters);

        void setVideoSurface(Surface surface);

        void setVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void setVideoSurfaceView(SurfaceView surfaceView);

        void setVideoTextureView(TextureView textureView);

        void setVolume(float f);

        void stop();

        void unmute();
    }

    public interface ProgressListener {
        void onProgress(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2);
    }

    @Override // androidx.media3.common.Player
    public final boolean canAdvertiseSession() {
        return false;
    }

    @Override // androidx.media3.common.Player
    public final Object getCurrentManifest() {
        return null;
    }

    public static final class Builder {
        private androidx.media3.common.util.BitmapLoader bitmapLoader;
        private final Context context;
        private int maxCommandsForMediaItems;
        private final SessionToken token;
        private Bundle connectionHints = Bundle.EMPTY;
        private Listener listener = new Listener() { // from class: androidx.media3.session.MediaController.Builder.1
            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
                Listener.CC.$default$onAvailableSessionCommandsChanged(this, mediaController, sessionCommands);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ ListenableFuture onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
                return Listener.CC.$default$onCustomCommand(this, mediaController, sessionCommand, bundle);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
                Listener.CC.$default$onCustomLayoutChanged(this, mediaController, list);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onDisconnected(MediaController mediaController) {
                Listener.CC.$default$onDisconnected(this, mediaController);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
                Listener.CC.$default$onError(this, mediaController, sessionError);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
                Listener.CC.$default$onExtrasChanged(this, mediaController, bundle);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onMediaButtonPreferencesChanged(MediaController mediaController, List list) {
                Listener.CC.$default$onMediaButtonPreferencesChanged(this, mediaController, list);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
                Listener.CC.$default$onSessionActivityChanged(this, mediaController, pendingIntent);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ ListenableFuture onSetCustomLayout(MediaController mediaController, List list) {
                return Listener.CC.$default$onSetCustomLayout(this, mediaController, list);
            }
        };
        private Looper applicationLooper = Util.getCurrentOrMainLooper();
        private long platformSessionCallbackAggregationTimeoutMs = 100;

        public Builder(Context context, SessionToken sessionToken) {
            this.context = (Context) Preconditions.checkNotNull(context);
            this.token = (SessionToken) Preconditions.checkNotNull(sessionToken);
        }

        public Builder setConnectionHints(Bundle bundle) {
            this.connectionHints = new Bundle((Bundle) Preconditions.checkNotNull(bundle));
            return this;
        }

        public Builder setListener(Listener listener) {
            this.listener = (Listener) Preconditions.checkNotNull(listener);
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            this.applicationLooper = (Looper) Preconditions.checkNotNull(looper);
            return this;
        }

        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            this.bitmapLoader = (androidx.media3.common.util.BitmapLoader) Preconditions.checkNotNull(bitmapLoader);
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i) {
            Preconditions.checkArgument(i >= 0);
            this.maxCommandsForMediaItems = i;
            return this;
        }

        public Builder experimentalSetPlatformSessionCallbackAggregationTimeoutMs(long j) {
            this.platformSessionCallbackAggregationTimeoutMs = j;
            return this;
        }

        public ListenableFuture<MediaController> buildAsync() {
            final MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).build());
            }
            final MediaController mediaController = new MediaController(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems, this.platformSessionCallbackAggregationTimeoutMs);
            Util.postOrRun(new Handler(this.applicationLooper), new Runnable() { // from class: androidx.media3.session.MediaController$Builder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    mediaControllerHolder.setController(mediaController);
                }
            });
            return mediaControllerHolder;
        }
    }

    public interface Listener {
        void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands);

        ListenableFuture<SessionResult> onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle);

        void onCustomLayoutChanged(MediaController mediaController, List<CommandButton> list);

        void onDisconnected(MediaController mediaController);

        void onError(MediaController mediaController, SessionError sessionError);

        void onExtrasChanged(MediaController mediaController, Bundle bundle);

        void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list);

        void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent);

        ListenableFuture<SessionResult> onSetCustomLayout(MediaController mediaController, List<CommandButton> list);

        /* JADX INFO: renamed from: androidx.media3.session.MediaController$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onAvailableSessionCommandsChanged(Listener _this, MediaController mediaController, SessionCommands sessionCommands) {
            }

            public static void $default$onCustomLayoutChanged(Listener _this, MediaController mediaController, List list) {
            }

            public static void $default$onDisconnected(Listener _this, MediaController mediaController) {
            }

            public static void $default$onError(Listener _this, MediaController mediaController, SessionError sessionError) {
            }

            public static void $default$onExtrasChanged(Listener _this, MediaController mediaController, Bundle bundle) {
            }

            public static void $default$onMediaButtonPreferencesChanged(Listener _this, MediaController mediaController, List list) {
            }

            public static void $default$onSessionActivityChanged(Listener _this, MediaController mediaController, PendingIntent pendingIntent) {
            }

            public static ListenableFuture $default$onSetCustomLayout(Listener _this, MediaController mediaController, List list) {
                return Futures.immediateFuture(new SessionResult(-6));
            }

            public static ListenableFuture $default$onCustomCommand(Listener _this, MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
                return Futures.immediateFuture(new SessionResult(-6));
            }
        }
    }

    MediaController(Context context, SessionToken sessionToken, Bundle bundle, Listener listener, Looper looper, ConnectionCallback connectionCallback, androidx.media3.common.util.BitmapLoader bitmapLoader, int i, long j) {
        Preconditions.checkNotNull(context, "context must not be null");
        Preconditions.checkNotNull(sessionToken, "token must not be null");
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.3] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.window = new Timeline.Window();
        this.timeDiffMs = C.TIME_UNSET;
        this.listener = listener;
        this.applicationHandler = new Handler(looper);
        this.connectionCallback = connectionCallback;
        this.maxCommandsForMediaItems = i;
        MediaControllerImpl mediaControllerImplCreateImpl = createImpl(context, sessionToken, bundle, looper, bitmapLoader, j);
        this.impl = mediaControllerImplCreateImpl;
        mediaControllerImplCreateImpl.connect();
    }

    MediaControllerImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j) {
        if (sessionToken.isLegacySession()) {
            return new MediaControllerImplLegacy(context, this, sessionToken, bundle, looper, (androidx.media3.common.util.BitmapLoader) Preconditions.checkNotNull(bitmapLoader), j);
        }
        return new MediaControllerImplBase(context, this, sessionToken, bundle, looper);
    }

    @Override // androidx.media3.common.Player
    public final void stop() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring stop().");
        } else {
            this.impl.stop();
        }
    }

    @Override // androidx.media3.common.Player
    public final void release() {
        verifyApplicationThread();
        if (this.released) {
            return;
        }
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.3] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        this.released = true;
        this.applicationHandler.removeCallbacksAndMessages(null);
        try {
            this.impl.release();
        } catch (Exception e) {
            Log.d(TAG, "Exception while releasing impl", e);
        }
        if (this.connectionNotified) {
            notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaController$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7645lambda$release$0$androidxmedia3sessionMediaController((MediaController.Listener) obj);
                }
            });
        } else {
            this.connectionNotified = true;
            this.connectionCallback.onRejected();
        }
    }

    /* JADX INFO: renamed from: lambda$release$0$androidx-media3-session-MediaController, reason: not valid java name */
    /* synthetic */ void m7645lambda$release$0$androidxmedia3sessionMediaController(Listener listener) {
        listener.onDisconnected(this);
    }

    public static void releaseFuture(Future<? extends MediaController> future) {
        if (future.cancel(false)) {
            return;
        }
        try {
            ((MediaController) Futures.getDone(future)).release();
        } catch (CancellationException | ExecutionException e) {
            Log.w(TAG, "MediaController future failed (so we couldn't release it)", e);
        }
    }

    public final SessionToken getConnectedToken() {
        if (isConnected()) {
            return this.impl.getConnectedToken();
        }
        return null;
    }

    public final boolean isConnected() {
        return this.impl.isConnected();
    }

    public final ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.impl.getCommandButtonsForMediaItem(mediaItem);
    }

    @Override // androidx.media3.common.Player
    public final void play() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring play().");
        } else {
            this.impl.play();
        }
    }

    @Override // androidx.media3.common.Player
    public final void pause() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring pause().");
        } else {
            this.impl.pause();
        }
    }

    @Override // androidx.media3.common.Player
    public final void prepare() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring prepare().");
        } else {
            this.impl.prepare();
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekToDefaultPosition();
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekToDefaultPosition(i);
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekTo(long j) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekTo(j);
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekTo(int i, long j) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekTo(i, j);
        }
    }

    @Override // androidx.media3.common.Player
    public final long getSeekBackIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekBackIncrement();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final void seekBack() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekBack().");
        } else {
            this.impl.seekBack();
        }
    }

    @Override // androidx.media3.common.Player
    public final long getSeekForwardIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekForwardIncrement();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final void seekForward() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekForward().");
        } else {
            this.impl.seekForward();
        }
    }

    public final PendingIntent getSessionActivity() {
        if (isConnected()) {
            return this.impl.getSessionActivity();
        }
        return null;
    }

    @Override // androidx.media3.common.Player
    public final PlaybackException getPlayerError() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlayerError();
        }
        return null;
    }

    @Override // androidx.media3.common.Player
    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setPlayWhenReady(z);
        }
    }

    @Override // androidx.media3.common.Player
    public final boolean getPlayWhenReady() {
        verifyApplicationThread();
        return isConnected() && this.impl.getPlayWhenReady();
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackSuppressionReason();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackState() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackState();
        }
        return 1;
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlaying() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlaying();
    }

    @Override // androidx.media3.common.Player
    public final boolean isLoading() {
        verifyApplicationThread();
        return isConnected() && this.impl.isLoading();
    }

    @Override // androidx.media3.common.Player
    public final long getDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getDuration() : C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final long getBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final int getBufferedPercentage() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPercentage();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final long getTotalBufferedDuration() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getTotalBufferedDuration();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentLiveOffset() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentLiveOffset() : C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player
    public final long getContentDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getContentDuration() : C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player
    public final long getContentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final long getContentBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentBufferedPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlayingAd() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlayingAd();
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdGroupIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdIndexInAdGroup();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        Preconditions.checkNotNull(playbackParameters, "playbackParameters must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackParameters().");
        } else {
            this.impl.setPlaybackParameters(playbackParameters);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackSpeed(float f) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackSpeed().");
        } else {
            this.impl.setPlaybackSpeed(f);
        }
    }

    @Override // androidx.media3.common.Player
    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaybackParameters() : PlaybackParameters.DEFAULT;
    }

    @Override // androidx.media3.common.Player
    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        if (!isConnected()) {
            return AudioAttributes.DEFAULT;
        }
        return this.impl.getAudioAttributes();
    }

    @Override // androidx.media3.common.Player
    public final int getAudioSessionId() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getAudioSessionId();
        }
        return 0;
    }

    public final ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        verifyApplicationThread();
        Preconditions.checkNotNull(str, "mediaId must not be null");
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "mediaId must not be empty");
        Preconditions.checkNotNull(rating, "rating must not be null");
        if (isConnected()) {
            return this.impl.setRating(str, rating);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> setRating(Rating rating) {
        verifyApplicationThread();
        Preconditions.checkNotNull(rating, "rating must not be null");
        if (isConnected()) {
            return this.impl.setRating(rating);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        verifyApplicationThread();
        Preconditions.checkNotNull(sessionCommand, "command must not be null");
        Preconditions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        if (isConnected()) {
            return this.impl.sendCustomCommand(sessionCommand, bundle);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, ProgressListener progressListener) {
        verifyApplicationThread();
        Preconditions.checkNotNull(sessionCommand, "command must not be null");
        Preconditions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        if (isConnected()) {
            return this.impl.sendCustomCommand(sessionCommand, bundle, progressListener);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, MediaItem mediaItem, Bundle bundle) {
        return sendCustomCommand(sessionCommand, mediaItem, bundle, null);
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, MediaItem mediaItem, Bundle bundle, ProgressListener progressListener) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID", mediaItem.mediaId);
        return sendCustomCommand(sessionCommand, bundle2, progressListener);
    }

    public final ImmutableList<CommandButton> getCustomLayout() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCustomLayout() : ImmutableList.of();
    }

    public final ImmutableList<CommandButton> getMediaButtonPreferences() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getMediaButtonPreferences() : ImmutableList.of();
    }

    public final Bundle getSessionExtras() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSessionExtras() : Bundle.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTimeline() : Timeline.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        Preconditions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        } else {
            this.impl.setMediaItem(mediaItem);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem, long j) {
        verifyApplicationThread();
        Preconditions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        } else {
            this.impl.setMediaItem(mediaItem, j);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem, boolean z) {
        verifyApplicationThread();
        Preconditions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItem(mediaItem, z);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        Preconditions.checkNotNull(list, "mediaItems must not be null");
        for (int i = 0; i < list.size(); i++) {
            Preconditions.checkArgument(list.get(i) != null, "items must not contain null, index=%s", i);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        Preconditions.checkNotNull(list, "mediaItems must not be null");
        for (int i = 0; i < list.size(); i++) {
            Preconditions.checkArgument(list.get(i) != null, "items must not contain null, index=%s", i);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, z);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThread();
        Preconditions.checkNotNull(list, "mediaItems must not be null");
        for (int i2 = 0; i2 < list.size(); i2++) {
            Preconditions.checkArgument(list.get(i2) != null, "items must not contain null, index=%s", i2);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, i, j);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThread();
        Preconditions.checkNotNull(mediaMetadata, "playlistMetadata must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaylistMetadata().");
        } else {
            this.impl.setPlaylistMetadata(mediaMetadata);
        }
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaylistMetadata() : MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        } else {
            this.impl.addMediaItem(mediaItem);
        }
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        } else {
            this.impl.addMediaItem(i, mediaItem);
        }
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        } else {
            this.impl.addMediaItems(list);
        }
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        } else {
            this.impl.addMediaItems(i, list);
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItem(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItem().");
        } else {
            this.impl.removeMediaItem(i);
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItems(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItems().");
        } else {
            this.impl.removeMediaItems(i, i2);
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearMediaItems() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearMediaItems().");
        } else {
            this.impl.clearMediaItems();
        }
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItem(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItem().");
        } else {
            this.impl.moveMediaItem(i, i2);
        }
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItems().");
        } else {
            this.impl.moveMediaItems(i, i2, i3);
        }
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItem().");
        } else {
            this.impl.replaceMediaItem(i, mediaItem);
        }
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItems().");
        } else {
            this.impl.replaceMediaItems(i, i2, list);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowDynamic() {
        return isCurrentMediaItemDynamic();
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemDynamic() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowLive() {
        return isCurrentMediaItemLive();
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemLive() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isLive();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowSeekable() {
        return isCurrentMediaItemSeekable();
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemSeekable() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable;
    }

    @Override // androidx.media3.common.Player
    public final MediaItem getCurrentMediaItem() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
    }

    @Override // androidx.media3.common.Player
    public final int getMediaItemCount() {
        return getCurrentTimeline().getWindowCount();
    }

    @Override // androidx.media3.common.Player
    public final MediaItem getMediaItemAt(int i) {
        return getCurrentTimeline().getWindow(i, this.window).mediaItem;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPeriodIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getCurrentWindowIndex() {
        return getCurrentMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getPreviousWindowIndex() {
        return getPreviousMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public final int getPreviousMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPreviousMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getNextWindowIndex() {
        return getNextMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public final int getNextMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getNextMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final boolean hasPreviousMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasPreviousMediaItem();
    }

    @Override // androidx.media3.common.Player
    public final boolean hasNextMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasNextMediaItem();
    }

    @Override // androidx.media3.common.Player
    public final void seekToPreviousMediaItem() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPreviousMediaItem().");
        } else {
            this.impl.seekToPreviousMediaItem();
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToNextMediaItem() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNextMediaItem().");
        } else {
            this.impl.seekToNextMediaItem();
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToPrevious() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPrevious().");
        } else {
            this.impl.seekToPrevious();
        }
    }

    @Override // androidx.media3.common.Player
    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getMaxSeekToPreviousPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final void seekToNext() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNext().");
        } else {
            this.impl.seekToNext();
        }
    }

    @Override // androidx.media3.common.Player
    public final int getRepeatMode() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getRepeatMode();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final void setRepeatMode(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setRepeatMode().");
        } else {
            this.impl.setRepeatMode(i);
        }
    }

    @Override // androidx.media3.common.Player
    public final boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return isConnected() && this.impl.getShuffleModeEnabled();
    }

    @Override // androidx.media3.common.Player
    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setShuffleMode().");
        } else {
            this.impl.setShuffleModeEnabled(z);
        }
    }

    @Override // androidx.media3.common.Player
    public final VideoSize getVideoSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getVideoSize() : VideoSize.UNKNOWN;
    }

    @Override // androidx.media3.common.Player
    public final Size getSurfaceSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSurfaceSize() : Size.UNKNOWN;
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        } else {
            this.impl.clearVideoSurface();
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        } else {
            this.impl.clearVideoSurface(surface);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurface().");
        } else {
            this.impl.setVideoSurface(surface);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceHolder().");
        } else {
            this.impl.setVideoSurfaceHolder(surfaceHolder);
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceHolder().");
        } else {
            this.impl.clearVideoSurfaceHolder(surfaceHolder);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceView().");
        } else {
            this.impl.setVideoSurfaceView(surfaceView);
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceView().");
        } else {
            this.impl.clearVideoSurfaceView(surfaceView);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoTextureView().");
        } else {
            this.impl.setVideoTextureView(textureView);
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoTextureView().");
        } else {
            this.impl.clearVideoTextureView(textureView);
        }
    }

    @Override // androidx.media3.common.Player
    public final CueGroup getCurrentCues() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentCues() : CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.common.Player
    public final float getVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getVolume();
        }
        return 1.0f;
    }

    @Override // androidx.media3.common.Player
    public final void setVolume(float f) {
        verifyApplicationThread();
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "volume must be between 0 and 1");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVolume().");
        } else {
            this.impl.setVolume(f);
        }
    }

    @Override // androidx.media3.common.Player
    public final void mute() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring mute().");
        } else {
            this.impl.mute();
        }
    }

    @Override // androidx.media3.common.Player
    public final void unmute() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring unmute().");
        } else {
            this.impl.unmute();
        }
    }

    @Override // androidx.media3.common.Player
    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        if (!isConnected()) {
            return DeviceInfo.UNKNOWN;
        }
        return this.impl.getDeviceInfo();
    }

    @Override // androidx.media3.common.Player
    public final int getDeviceVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getDeviceVolume();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final boolean isDeviceMuted() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.isDeviceMuted();
        }
        return false;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        } else {
            this.impl.setDeviceVolume(i);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceVolume(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        } else {
            this.impl.setDeviceVolume(i, i2);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        } else {
            this.impl.increaseDeviceVolume();
        }
    }

    @Override // androidx.media3.common.Player
    public final void increaseDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        } else {
            this.impl.increaseDeviceVolume(i);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        } else {
            this.impl.decreaseDeviceVolume();
        }
    }

    @Override // androidx.media3.common.Player
    public final void decreaseDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        } else {
            this.impl.decreaseDeviceVolume(i);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        } else {
            this.impl.setDeviceMuted(z);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceMuted(boolean z, int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        } else {
            this.impl.setDeviceMuted(z, i);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setAudioAttributes().");
        } else {
            this.impl.setAudioAttributes(audioAttributes, z);
        }
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getMediaMetadata() : MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final Tracks getCurrentTracks() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTracks() : Tracks.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        if (!isConnected()) {
            return TrackSelectionParameters.DEFAULT;
        }
        return this.impl.getTrackSelectionParameters();
    }

    @Override // androidx.media3.common.Player
    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setTrackSelectionParameters().");
        }
        this.impl.setTrackSelectionParameters(trackSelectionParameters);
    }

    @Override // androidx.media3.common.Player
    public final Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    int getMaxCommandsForMediaItems() {
        return this.maxCommandsForMediaItems;
    }

    final long getTimeDiffMs() {
        return this.timeDiffMs;
    }

    final void setTimeDiffMs(long j) {
        verifyApplicationThread();
        this.timeDiffMs = j;
    }

    @Override // androidx.media3.common.Player
    public final void addListener(Player.Listener listener) {
        Preconditions.checkNotNull(listener, "listener must not be null");
        this.impl.addListener(listener);
    }

    @Override // androidx.media3.common.Player
    public final void removeListener(Player.Listener listener) {
        verifyApplicationThread();
        Preconditions.checkNotNull(listener, "listener must not be null");
        this.impl.removeListener(listener);
    }

    @Override // androidx.media3.common.Player
    public final boolean isCommandAvailable(int i) {
        return getAvailableCommands().contains(i);
    }

    @Override // androidx.media3.common.Player
    public final Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        if (!isConnected()) {
            return Player.Commands.EMPTY;
        }
        return this.impl.getAvailableCommands();
    }

    public final boolean isSessionCommandAvailable(int i) {
        return getAvailableSessionCommands().contains(i);
    }

    public final boolean isSessionCommandAvailable(SessionCommand sessionCommand) {
        return getAvailableSessionCommands().contains(sessionCommand);
    }

    public final SessionCommands getAvailableSessionCommands() {
        verifyApplicationThread();
        if (!isConnected()) {
            return SessionCommands.EMPTY;
        }
        return this.impl.getAvailableSessionCommands();
    }

    private static ListenableFuture<SessionResult> createDisconnectedFuture() {
        return Futures.immediateFuture(new SessionResult(-100));
    }

    final void runOnApplicationLooper(Runnable runnable) {
        Util.postOrRun(this.applicationHandler, runnable);
    }

    final void notifyControllerListener(Consumer<Listener> consumer) {
        Preconditions.checkState(Looper.myLooper() == getApplicationLooper());
        consumer.accept(this.listener);
    }

    final void notifyAccepted() {
        Preconditions.checkState(Looper.myLooper() == getApplicationLooper());
        Preconditions.checkState(!this.connectionNotified);
        this.connectionNotified = true;
        this.connectionCallback.onAccepted();
    }

    final IMediaController getBinder() {
        return this.impl.getBinder();
    }

    Bundle getConnectionHints() {
        return this.impl.getConnectionHints();
    }

    private void verifyApplicationThread() {
        Preconditions.checkState(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }
}

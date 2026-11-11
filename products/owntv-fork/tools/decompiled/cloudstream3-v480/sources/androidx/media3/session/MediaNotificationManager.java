package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.media3.common.AudioAttributes;
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
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes4.dex */
final class MediaNotificationManager implements Handler.Callback {
    private static final int MSG_USER_ENGAGED_TIMEOUT = 1;
    private static final String TAG = "MediaNtfMng";
    private final MediaNotification.ActionFactory actionFactory;
    private boolean isUserEngaged;
    private MediaNotification mediaNotification;
    private MediaNotification.Provider mediaNotificationProvider;
    private final MediaSessionService mediaSessionService;
    private final NotificationManagerCompat notificationManagerCompat;
    private final Intent startSelfIntent;
    private int totalNotificationCount;
    private final Handler mainHandler = Util.createHandler(Looper.getMainLooper(), this);
    private final Executor mainExecutor = new Executor() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda2
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.f$0.m7769lambda$new$0$androidxmedia3sessionMediaNotificationManager(runnable);
        }
    };
    private final Map<MediaSession, ControllerInfo> controllerMap = new HashMap();
    private boolean startedInForeground = false;
    private boolean isUserEngagedTimeoutEnabled = true;
    private long userEngagedTimeoutMs = 600000;
    int showNotificationForIdlePlayerMode = 3;

    public MediaNotificationManager(MediaSessionService mediaSessionService, MediaNotification.Provider provider, MediaNotification.ActionFactory actionFactory) {
        this.mediaSessionService = mediaSessionService;
        this.mediaNotificationProvider = provider;
        this.actionFactory = actionFactory;
        this.notificationManagerCompat = NotificationManagerCompat.from(mediaSessionService);
        this.startSelfIntent = new Intent(mediaSessionService, mediaSessionService.getClass());
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-media3-session-MediaNotificationManager, reason: not valid java name */
    /* synthetic */ void m7769lambda$new$0$androidxmedia3sessionMediaNotificationManager(Runnable runnable) {
        Util.postOrRun(this.mainHandler, runnable);
    }

    public void addSession(final MediaSession mediaSession) {
        if (this.controllerMap.containsKey(mediaSession)) {
            return;
        }
        final MediaControllerListener mediaControllerListener = new MediaControllerListener(this.mediaSessionService, mediaSession);
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaController.KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG, true);
        final ListenableFuture<MediaController> listenableFutureBuildAsync = new MediaController.Builder(this.mediaSessionService, mediaSession.getToken()).setConnectionHints(bundle).setListener(mediaControllerListener).setApplicationLooper(Looper.getMainLooper()).buildAsync();
        this.controllerMap.put(mediaSession, new ControllerInfo(listenableFutureBuildAsync));
        listenableFutureBuildAsync.addListener(new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7768x67573ed4(listenableFutureBuildAsync, mediaControllerListener, mediaSession);
            }
        }, this.mainExecutor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$addSession$1$androidx-media3-session-MediaNotificationManager, reason: not valid java name */
    /* synthetic */ void m7768x67573ed4(ListenableFuture listenableFuture, MediaControllerListener mediaControllerListener, MediaSession mediaSession) {
        try {
            MediaController mediaController = (MediaController) listenableFuture.get(0L, TimeUnit.MILLISECONDS);
            mediaControllerListener.onConnected(shouldShowNotification(mediaSession));
            mediaController.addListener(mediaControllerListener);
        } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException unused) {
            this.mediaSessionService.removeSession(mediaSession);
        }
    }

    public void removeSession(MediaSession mediaSession) {
        ControllerInfo controllerInfoRemove = this.controllerMap.remove(mediaSession);
        if (controllerInfoRemove != null) {
            MediaController.releaseFuture(controllerInfoRemove.controllerFuture);
        }
    }

    public void onCustomAction(final MediaSession mediaSession, final String str, final Bundle bundle) {
        final MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        if (connectedControllerForSession == null) {
            return;
        }
        Util.postOrRun(new Handler(mediaSession.getPlayer().getApplicationLooper()), new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7771x54793c21(mediaSession, str, bundle, connectedControllerForSession);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCustomAction$3$androidx-media3-session-MediaNotificationManager, reason: not valid java name */
    /* synthetic */ void m7771x54793c21(MediaSession mediaSession, final String str, final Bundle bundle, final MediaController mediaController) {
        if (this.mediaNotificationProvider.handleCustomCommand(mediaSession, str, bundle)) {
            return;
        }
        this.mainExecutor.execute(new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7770x62cf9602(mediaController, str, bundle);
            }
        });
    }

    public void setMediaNotificationProvider(MediaNotification.Provider provider) {
        this.mediaNotificationProvider = provider;
    }

    public void updateNotification(final MediaSession mediaSession, final boolean z) {
        if (!this.mediaSessionService.isSessionAdded(mediaSession) || !shouldShowNotification(mediaSession)) {
            removeNotification();
            return;
        }
        final int i = this.totalNotificationCount + 1;
        this.totalNotificationCount = i;
        final ImmutableList<CommandButton> mediaButtonPreferences = ((MediaController) Preconditions.checkNotNull(getConnectedControllerForSession(mediaSession))).getMediaButtonPreferences();
        final MediaNotification.Provider.Callback callback = new MediaNotification.Provider.Callback() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda5
            @Override // androidx.media3.session.MediaNotification.Provider.Callback
            public final void onNotificationChanged(MediaNotification mediaNotification) {
                this.f$0.m7773xb22eb051(i, mediaSession, mediaNotification);
            }
        };
        Util.postOrRun(new Handler(mediaSession.getPlayer().getApplicationLooper()), new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7775x9581fc8f(mediaSession, mediaButtonPreferences, callback, z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateNotification$5$androidx-media3-session-MediaNotificationManager, reason: not valid java name */
    /* synthetic */ void m7773xb22eb051(final int i, final MediaSession mediaSession, final MediaNotification mediaNotification) {
        this.mainExecutor.execute(new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7772xc0850a32(i, mediaSession, mediaNotification);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateNotification$7$androidx-media3-session-MediaNotificationManager, reason: not valid java name */
    /* synthetic */ void m7775x9581fc8f(final MediaSession mediaSession, ImmutableList immutableList, MediaNotification.Provider.Callback callback, final boolean z) {
        final MediaNotification mediaNotificationCreateNotification = this.mediaNotificationProvider.createNotification(mediaSession, immutableList, this.actionFactory, callback);
        this.mainExecutor.execute(new Runnable() { // from class: androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7774xa3d85670(mediaSession, mediaNotificationCreateNotification, z);
            }
        });
    }

    public boolean isStartedInForeground() {
        return this.startedInForeground;
    }

    public void setUserEngagedTimeoutMs(long j) {
        this.userEngagedTimeoutMs = j;
    }

    public void setShowNotificationForIdlePlayer(int i) {
        this.showNotificationForIdlePlayerMode = i;
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i2 = 0; i2 < sessions.size(); i2++) {
            this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i2), false);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i), false);
        }
        return true;
    }

    boolean shouldRunInForeground(boolean z) {
        boolean zIsAnySessionUserEngaged = isAnySessionUserEngaged(z);
        boolean z2 = this.isUserEngagedTimeoutEnabled && this.userEngagedTimeoutMs > 0;
        if (this.isUserEngaged && !zIsAnySessionUserEngaged && z2) {
            this.mainHandler.sendEmptyMessageDelayed(1, this.userEngagedTimeoutMs);
        } else if (zIsAnySessionUserEngaged) {
            this.mainHandler.removeMessages(1);
        }
        this.isUserEngaged = zIsAnySessionUserEngaged;
        return zIsAnySessionUserEngaged || this.mainHandler.hasMessages(1);
    }

    private boolean isAnySessionUserEngaged(boolean z) {
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            MediaController connectedControllerForSession = getConnectedControllerForSession(sessions.get(i));
            if (connectedControllerForSession != null && ((connectedControllerForSession.getPlayWhenReady() || z) && (connectedControllerForSession.getPlaybackState() == 3 || connectedControllerForSession.getPlaybackState() == 2))) {
                return true;
            }
        }
        return false;
    }

    void disableUserEngagedTimeout() {
        this.isUserEngagedTimeoutEnabled = false;
        if (this.mainHandler.hasMessages(1)) {
            this.mainHandler.removeMessages(1);
            List<MediaSession> sessions = this.mediaSessionService.getSessions();
            for (int i = 0; i < sessions.size(); i++) {
                this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onNotificationUpdated, reason: merged with bridge method [inline-methods] */
    public void m7772xc0850a32(int i, MediaSession mediaSession, MediaNotification mediaNotification) {
        if (i == this.totalNotificationCount) {
            m7774xa3d85670(mediaSession, mediaNotification, shouldRunInForeground(false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotificationDismissed(MediaSession mediaSession) {
        ControllerInfo controllerInfo = this.controllerMap.get(mediaSession);
        if (controllerInfo != null) {
            controllerInfo.wasNotificationDismissed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateNotificationInternal, reason: merged with bridge method [inline-methods] */
    public void m7774xa3d85670(MediaSession mediaSession, MediaNotification mediaNotification, boolean z) {
        mediaNotification.notification.extras.putParcelable(NotificationCompat.EXTRA_MEDIA_SESSION, mediaSession.getPlatformToken());
        this.mediaNotification = mediaNotification;
        if (z) {
            startForeground(mediaNotification);
        } else {
            this.notificationManagerCompat.notify(mediaNotification.notificationId, mediaNotification.notification);
            stopForeground(false);
        }
    }

    private void removeNotification() {
        stopForeground(true);
        MediaNotification mediaNotification = this.mediaNotification;
        if (mediaNotification != null) {
            this.notificationManagerCompat.cancel(mediaNotification.notificationId);
            this.totalNotificationCount++;
            this.mediaNotification = null;
        }
    }

    private boolean shouldShowNotification(MediaSession mediaSession) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        if (connectedControllerForSession == null || connectedControllerForSession.getCurrentTimeline().isEmpty()) {
            return false;
        }
        ControllerInfo controllerInfo = (ControllerInfo) Preconditions.checkNotNull(this.controllerMap.get(mediaSession));
        if (connectedControllerForSession.getPlaybackState() != 1) {
            controllerInfo.wasNotificationDismissed = false;
            controllerInfo.hasBeenPrepared = true;
            return true;
        }
        int i = this.showNotificationForIdlePlayerMode;
        if (i == 1) {
            return !controllerInfo.wasNotificationDismissed;
        }
        if (i == 2) {
            return false;
        }
        if (i == 3) {
            return !controllerInfo.wasNotificationDismissed && controllerInfo.hasBeenPrepared;
        }
        throw new IllegalStateException();
    }

    private MediaController getConnectedControllerForSession(MediaSession mediaSession) {
        ControllerInfo controllerInfo = this.controllerMap.get(mediaSession);
        if (controllerInfo == null || !controllerInfo.controllerFuture.isDone()) {
            return null;
        }
        try {
            return (MediaController) Futures.getDone(controllerInfo.controllerFuture);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendCustomCommandIfCommandIsAvailable, reason: merged with bridge method [inline-methods] */
    public void m7770x62cf9602(MediaController mediaController, final String str, Bundle bundle) {
        SessionCommand next;
        UnmodifiableIterator<SessionCommand> it = mediaController.getAvailableSessionCommands().commands.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.commandCode == 0 && next.customAction.equals(str)) {
                break;
            }
        }
        if (next != null || CommandButton.isPredefinedCustomCommandButtonCode(str)) {
            Futures.addCallback(mediaController.sendCustomCommand(new SessionCommand(str, bundle), Bundle.EMPTY), new FutureCallback<SessionResult>() { // from class: androidx.media3.session.MediaNotificationManager.1
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(SessionResult sessionResult) {
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    Log.w(MediaNotificationManager.TAG, "custom command " + str + " produced an error: " + th.getMessage(), th);
                }
            }, MoreExecutors.directExecutor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class MediaControllerListener implements MediaController.Listener, Player.Listener {
        private final MediaSessionService mediaSessionService;
        private final MediaSession session;

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

        @Override // androidx.media3.session.MediaController.Listener
        public /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
            MediaController.Listener.CC.$default$onCustomLayoutChanged(this, mediaController, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
            Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
            MediaController.Listener.CC.$default$onError(this, mediaController, sessionError);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
            MediaController.Listener.CC.$default$onExtrasChanged(this, mediaController, bundle);
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
        public /* synthetic */ void onMetadata(Metadata metadata) {
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
        public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
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

        @Override // androidx.media3.session.MediaController.Listener
        public /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
            MediaController.Listener.CC.$default$onSessionActivityChanged(this, mediaController, pendingIntent);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public /* synthetic */ ListenableFuture onSetCustomLayout(MediaController mediaController, List list) {
            return MediaController.Listener.CC.$default$onSetCustomLayout(this, mediaController, list);
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

        public MediaControllerListener(MediaSessionService mediaSessionService, MediaSession mediaSession) {
            this.mediaSessionService = mediaSessionService;
            this.session = mediaSession;
        }

        public void onConnected(boolean z) {
            if (z) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public ListenableFuture<SessionResult> onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
            int i;
            if (sessionCommand.customAction.equals(MediaNotification.NOTIFICATION_DISMISSED_EVENT_KEY)) {
                MediaNotificationManager.this.onNotificationDismissed(this.session);
                i = 0;
            } else {
                i = -6;
            }
            return Futures.immediateFuture(new SessionResult(i));
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onDisconnected(MediaController mediaController) {
            if (this.mediaSessionService.isSessionAdded(this.session)) {
                this.mediaSessionService.removeSession(this.session);
            }
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 14, 0)) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }
    }

    private void startForeground(MediaNotification mediaNotification) {
        ContextCompat.startForegroundService(this.mediaSessionService, this.startSelfIntent);
        Util.setForegroundServiceNotification(this.mediaSessionService, mediaNotification.notificationId, mediaNotification.notification, 2, "mediaPlayback");
        this.startedInForeground = true;
    }

    private void stopForeground(boolean z) {
        int i = Build.VERSION.SDK_INT;
        MediaSessionService mediaSessionService = this.mediaSessionService;
        if (i >= 24) {
            Api24.stopForeground(mediaSessionService, z);
        } else {
            mediaSessionService.stopForeground(z);
        }
        this.startedInForeground = false;
    }

    private static final class ControllerInfo {
        public final ListenableFuture<MediaController> controllerFuture;
        public boolean hasBeenPrepared;
        public boolean wasNotificationDismissed;

        public ControllerInfo(ListenableFuture<MediaController> listenableFuture) {
            this.controllerFuture = listenableFuture;
        }
    }

    private static class Api24 {
        public static void stopForeground(MediaSessionService mediaSessionService, boolean z) {
            mediaSessionService.stopForeground(z ? 1 : 2);
        }

        private Api24() {
        }
    }
}

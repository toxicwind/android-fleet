package androidx.media3.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.collection.ArraySet;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda12;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes4.dex */
class MediaControllerImplBase implements MediaController.MediaControllerImpl {
    private static final long RELEASE_TIMEOUT_MS = 30000;
    public static final String TAG = "MCImplBase";
    private SessionToken connectedToken;
    private final Bundle connectionHints;
    private final Context context;
    protected final MediaControllerStub controllerStub;
    private long currentPositionMs;
    private final IBinder.DeathRecipient deathRecipient;
    private final Handler fallbackPlaybackInfoUpdateHandler;
    private final FlushCommandQueueHandler flushCommandQueueHandler;
    private IMediaSession iSession;
    private final MediaController instance;
    private Player.Commands intersectedPlayerCommands;
    private long lastSetPlayWhenReadyCalledTimeMs;
    private final ListenerSet<Player.Listener> listeners;
    private final SparseArray<MediaController.ProgressListener> pendingCustomActionProgressListeners;
    private final ArraySet<Integer> pendingMaskingSequencedFutureNumbers;
    private PlayerInfo pendingPlayerInfo;
    private android.media.session.MediaController platformController;
    private Player.Commands playerCommandsFromPlayer;
    private boolean released;
    protected final SequencedFutureManager sequencedFutureManager;
    private SessionServiceConnection serviceConnection;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final SurfaceCallback surfaceCallback;
    private final SessionToken token;
    private Surface videoSurface;
    private SurfaceHolder videoSurfaceHolder;
    private TextureView videoTextureView;
    private PlayerInfo playerInfo = PlayerInfo.DEFAULT;
    private Size surfaceSize = Size.UNKNOWN;
    private SessionCommands sessionCommands = SessionCommands.EMPTY;
    private ImmutableList<CommandButton> customLayoutOriginal = ImmutableList.of();
    private ImmutableList<CommandButton> mediaButtonPreferencesOriginal = ImmutableList.of();
    private ImmutableList<CommandButton> resolvedMediaButtonPreferences = ImmutableList.of();
    private ImmutableList<CommandButton> resolvedCustomLayout = ImmutableList.of();
    private ImmutableMap<String, CommandButton> commandButtonsForMediaItemsMap = ImmutableMap.of();
    private Player.Commands playerCommandsFromSession = Player.Commands.EMPTY;

    /* JADX INFO: Access modifiers changed from: private */
    interface RemoteSessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    private static int convertRepeatModeForNavigation(int i) {
        if (i == 1) {
            return 0;
        }
        return i;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MediaControllerImplBase(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper) {
        Player.Commands commands = Player.Commands.EMPTY;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new ListenerSet.IterationFinishedEvent() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda96
            @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
            public final void invoke(Object obj, FlagSet flagSet) {
                this.f$0.m7664lambda$new$0$androidxmedia3sessionMediaControllerImplBase((Player.Listener) obj, flagSet);
            }
        });
        this.fallbackPlaybackInfoUpdateHandler = new Handler(looper);
        this.instance = mediaController;
        Preconditions.checkNotNull(context, "context must not be null");
        Preconditions.checkNotNull(sessionToken, "token must not be null");
        this.context = context;
        this.sequencedFutureManager = new SequencedFutureManager();
        this.controllerStub = new MediaControllerStub(this);
        this.pendingMaskingSequencedFutureNumbers = new ArraySet<>();
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.deathRecipient = new IBinder.DeathRecipient() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda97
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                this.f$0.m7665lambda$new$1$androidxmedia3sessionMediaControllerImplBase();
            }
        };
        this.surfaceCallback = new SurfaceCallback();
        this.sessionExtras = Bundle.EMPTY;
        this.serviceConnection = sessionToken.getType() != 0 ? new SessionServiceConnection(bundle) : null;
        this.flushCommandQueueHandler = new FlushCommandQueueHandler(looper);
        this.currentPositionMs = C.TIME_UNSET;
        this.lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
        this.pendingCustomActionProgressListeners = new SparseArray<>();
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7664lambda$new$0$androidxmedia3sessionMediaControllerImplBase(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* JADX INFO: renamed from: lambda$new$1$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7665lambda$new$1$androidxmedia3sessionMediaControllerImplBase() {
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase2));
    }

    MediaController getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void connect() {
        boolean zRequestConnectToService;
        if (this.token.getType() == 0) {
            this.serviceConnection = null;
            zRequestConnectToService = requestConnectToSession(this.connectionHints);
        } else {
            this.serviceConnection = new SessionServiceConnection(this.connectionHints);
            zRequestConnectToService = requestConnectToService();
        }
        if (zRequestConnectToService) {
            return;
        }
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase2));
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
        if (isPlayerCommandAvailable(3)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda112
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7729lambda$stop$2$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            PlayerInfo playerInfoCopyWithSessionPositionInfo = this.playerInfo.copyWithSessionPositionInfo(new SessionPositionInfo(this.playerInfo.sessionPositionInfo.positionInfo, this.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), this.playerInfo.sessionPositionInfo.durationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs, MediaUtils.calculateBufferedPercentage(this.playerInfo.sessionPositionInfo.positionInfo.positionMs, this.playerInfo.sessionPositionInfo.durationMs), 0L, this.playerInfo.sessionPositionInfo.currentLiveOffsetMs, this.playerInfo.sessionPositionInfo.contentDurationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs));
            this.playerInfo = playerInfoCopyWithSessionPositionInfo;
            if (playerInfoCopyWithSessionPositionInfo.playbackState != 1) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithPlaybackState(1, playerInfo.playerError);
                this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda114
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlaybackStateChanged(1);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$stop$2$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7729lambda$stop$2$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.stop(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        IMediaSession iMediaSession = this.iSession;
        if (this.released) {
            return;
        }
        this.released = true;
        this.connectedToken = null;
        this.fallbackPlaybackInfoUpdateHandler.removeCallbacksAndMessages(null);
        clearSurfacesAndCallbacks();
        this.flushCommandQueueHandler.release();
        this.iSession = null;
        if (iMediaSession != null) {
            int iObtainNextSequenceNumber = this.sequencedFutureManager.obtainNextSequenceNumber();
            try {
                iMediaSession.asBinder().unlinkToDeath(this.deathRecipient, 0);
                iMediaSession.release(this.controllerStub, iObtainNextSequenceNumber);
            } catch (RemoteException unused) {
            }
        }
        this.listeners.release();
        this.sequencedFutureManager.lazyRelease(30000L, new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda82
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7682lambda$release$4$androidxmedia3sessionMediaControllerImplBase();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$release$4$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7682lambda$release$4$androidxmedia3sessionMediaControllerImplBase() {
        SessionServiceConnection sessionServiceConnection = this.serviceConnection;
        if (sessionServiceConnection != null) {
            this.context.unbindService(sessionServiceConnection);
            this.serviceConnection = null;
        }
        this.controllerStub.destroy();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionToken getConnectedToken() {
        return this.connectedToken;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isConnected() {
        return this.iSession != null;
    }

    boolean isReleased() {
        return this.released;
    }

    private void dispatchRemoteSessionTaskWithPlayerCommand(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        ListenableFuture<SessionResult> listenableFutureDispatchRemoteSessionTask = dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
        try {
            LegacyConversions.getFutureResult(listenableFutureDispatchRemoteSessionTask, 3000L);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        } catch (TimeoutException e2) {
            if (listenableFutureDispatchRemoteSessionTask instanceof SequencedFutureManager.SequencedFuture) {
                int sequenceNumber = ((SequencedFutureManager.SequencedFuture) listenableFutureDispatchRemoteSessionTask).getSequenceNumber();
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-1));
            }
            Log.w(TAG, "Synchronous command takes too long on the session side.", e2);
        }
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(int i, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(i, null, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(0, sessionCommand, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommandInternal(int i, SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        IMediaSession sessionInterfaceWithSessionCommandIfAble;
        if (sessionCommand != null) {
            sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(sessionCommand);
        } else {
            sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(i);
        }
        return dispatchRemoteSessionTask(sessionInterfaceWithSessionCommandIfAble, remoteSessionTask, false);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTask(IMediaSession iMediaSession, RemoteSessionTask remoteSessionTask, boolean z) {
        if (iMediaSession != null) {
            notifyPlatformControllerAboutMedia3ChangeRequest();
            SequencedFutureManager.SequencedFuture sequencedFutureCreateSequencedFuture = this.sequencedFutureManager.createSequencedFuture(new SessionResult(1));
            int sequenceNumber = sequencedFutureCreateSequencedFuture.getSequenceNumber();
            if (z) {
                if (this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                    this.pendingPlayerInfo = this.playerInfo;
                }
                this.pendingMaskingSequencedFutureNumbers.add(Integer.valueOf(sequenceNumber));
            }
            try {
                remoteSessionTask.run(iMediaSession, sequenceNumber);
                return sequencedFutureCreateSequencedFuture;
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-100));
                return sequencedFutureCreateSequencedFuture;
            }
        }
        return Futures.immediateFuture(new SessionResult(-4));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void play() {
        if (!isPlayerCommandAvailable(1)) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        } else {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda111
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7680lambda$play$5$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            setPlayWhenReady(true, 1);
        }
    }

    /* JADX INFO: renamed from: lambda$play$5$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7680lambda$play$5$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.play(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void pause() {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda101
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7679lambda$pause$6$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            setPlayWhenReady(false, 1);
        }
    }

    /* JADX INFO: renamed from: lambda$pause$6$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7679lambda$pause$6$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.pause(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void prepare() {
        if (isPlayerCommandAvailable(2)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda115
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7681lambda$prepare$7$androidxmedia3sessionMediaControllerImplBase(iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackState == 1) {
                PlayerInfo playerInfo = this.playerInfo;
                updatePlayerInfo(playerInfo.copyWithPlaybackState(playerInfo.timeline.isEmpty() ? 4 : 2, null), null, null, null, null);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$prepare$7$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7681lambda$prepare$7$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.prepare(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition() {
        if (isPlayerCommandAvailable(4)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda44
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7691x4935e2a(iMediaSession, i);
                }
            });
            seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
        }
    }

    /* JADX INFO: renamed from: lambda$seekToDefaultPosition$8$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7691x4935e2a(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToDefaultPosition(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition(final int i) {
        if (isPlayerCommandAvailable(10)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7692x41cf82b(i, iMediaSession, i2);
                }
            });
            seekToInternal(i, C.TIME_UNSET);
        }
    }

    /* JADX INFO: renamed from: lambda$seekToDefaultPosition$9$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7692x41cf82b(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToDefaultPositionWithMediaItemIndex(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(final long j) {
        if (isPlayerCommandAvailable(5)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda34
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7689lambda$seekTo$10$androidxmedia3sessionMediaControllerImplBase(j, iMediaSession, i);
                }
            });
            seekToInternal(getCurrentMediaItemIndex(), j);
        }
    }

    /* JADX INFO: renamed from: lambda$seekTo$10$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7689lambda$seekTo$10$androidxmedia3sessionMediaControllerImplBase(long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekTo(this.controllerStub, i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(final int i, final long j) {
        if (isPlayerCommandAvailable(10)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda108
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7690lambda$seekTo$11$androidxmedia3sessionMediaControllerImplBase(i, j, iMediaSession, i2);
                }
            });
            seekToInternal(i, j);
        }
    }

    /* JADX INFO: renamed from: lambda$seekTo$11$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7690lambda$seekTo$11$androidxmedia3sessionMediaControllerImplBase(int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToWithMediaItemIndex(this.controllerStub, i2, i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekBackIncrement() {
        return this.playerInfo.seekBackIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekBack() {
        if (isPlayerCommandAvailable(11)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda98
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7687xa77bbc45(iMediaSession, i);
                }
            });
            seekToInternalByOffset(-getSeekBackIncrement());
        }
    }

    /* JADX INFO: renamed from: lambda$seekBack$12$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7687xa77bbc45(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekBack(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekForwardIncrement() {
        return this.playerInfo.seekForwardIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekForward() {
        if (isPlayerCommandAvailable(12)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda38
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7688xc4e101b2(iMediaSession, i);
                }
            });
            seekToInternalByOffset(getSeekForwardIncrement());
        }
    }

    /* JADX INFO: renamed from: lambda$seekForward$13$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7688xc4e101b2(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekForward(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlayWhenReady(final boolean z) {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda10
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7717x3c78c7d3(z, iMediaSession, i);
                }
            });
            setPlayWhenReady(z, 1);
        } else if (z) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        }
    }

    /* JADX INFO: renamed from: lambda$setPlayWhenReady$14$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7717x3c78c7d3(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlayWhenReady(this.controllerStub, i, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getPlayWhenReady() {
        return this.playerInfo.playWhenReady;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackSuppressionReason() {
        return this.playerInfo.playbackSuppressionReason;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackException getPlayerError() {
        return this.playerInfo.playerError;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackState() {
        return this.playerInfo.playbackState;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlaying() {
        return this.playerInfo.isPlaying;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isLoading() {
        return this.playerInfo.isLoading;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getDuration() {
        return this.playerInfo.sessionPositionInfo.durationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getBufferedPercentage() {
        return this.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getTotalBufferedDuration() {
        return this.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentLiveOffset() {
        return this.playerInfo.sessionPositionInfo.currentLiveOffsetMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentDuration() {
        return this.playerInfo.sessionPositionInfo.contentDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentPosition() {
        if (!this.playerInfo.sessionPositionInfo.isPlayingAd) {
            return getCurrentPosition();
        }
        return this.playerInfo.sessionPositionInfo.positionInfo.contentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.contentBufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlayingAd() {
        return this.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdGroupIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adGroupIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdIndexInAdGroup() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackParameters(final PlaybackParameters playbackParameters) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda32
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7718x7ad849fa(playbackParameters, iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackParameters.equals(playbackParameters)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParameters);
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda33
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(playbackParameters);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* JADX INFO: renamed from: lambda$setPlaybackParameters$15$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7718x7ad849fa(PlaybackParameters playbackParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackParameters(this.controllerStub, i, playbackParameters.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackParameters getPlaybackParameters() {
        return this.playerInfo.playbackParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackSpeed(final float f) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda79
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7719x786a6f3f(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.playbackParameters.speed != f) {
                final PlaybackParameters playbackParametersWithSpeed = this.playerInfo.playbackParameters.withSpeed(f);
                this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParametersWithSpeed);
                this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda81
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlaybackParametersChanged(playbackParametersWithSpeed);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setPlaybackSpeed$17$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7719x786a6f3f(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackSpeed(this.controllerStub, i, f);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public AudioAttributes getAudioAttributes() {
        return this.playerInfo.audioAttributes;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getAudioSessionId() {
        return this.playerInfo.audioSessionId;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(final String str, final Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda42
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                this.f$0.m7721x577a1446(str, rating, iMediaSession, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setRating$19$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7721x577a1446(String str, Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRatingWithMediaId(this.controllerStub, i, str, rating.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> setRating(final Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda24
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                this.f$0.m7722x4d4d505c(rating, iMediaSession, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setRating$20$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7722x4d4d505c(Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRating(this.controllerStub, i, rating.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle) {
        if (((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 7) {
            return sendCustomCommand(sessionCommand, bundle, null);
        }
        return dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda43
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                this.f$0.m7698xbdee5b2a(sessionCommand, bundle, iMediaSession, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$sendCustomCommand$21$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7698xbdee5b2a(SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.onCustomCommand(this.controllerStub, i, sessionCommand.toBundle(), bundle);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle, final MediaController.ProgressListener progressListener) {
        if (((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion() < 7) {
            return sendCustomCommand(sessionCommand, bundle);
        }
        return dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda105
            @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
            public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                this.f$0.m7699xbd77f52b(progressListener, sessionCommand, bundle, iMediaSession, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$sendCustomCommand$22$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7699xbd77f52b(MediaController.ProgressListener progressListener, SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i) throws RemoteException {
        if (progressListener != null) {
            this.pendingCustomActionProgressListeners.put(i, progressListener);
        }
        iMediaSession.onCustomCommandWithProgressUpdate(this.controllerStub, i, sessionCommand.toBundle(), bundle, progressListener != null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.resolvedMediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCustomLayout() {
        return this.resolvedCustomLayout;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        SessionCommands availableSessionCommands = getAvailableSessionCommands();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItemsMap.get(immutableList.get(i));
            if (commandButton != null && commandButton.sessionCommand != null && availableSessionCommands.contains(commandButton.sessionCommand)) {
                builder.add(commandButton);
            }
        }
        return builder.build();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Timeline getCurrentTimeline() {
        return this.playerInfo.timeline;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda41
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7711x8fed3bcf(mediaItem, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, true);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItem$23$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7711x8fed3bcf(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem, final long j) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda102
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7712x8f76d5d0(mediaItem, j, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, j, false);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItem$24$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7712x8f76d5d0(MediaItem mediaItem, long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithStartPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem, final boolean z) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda15
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7713x8f006fd1(mediaItem, z, iMediaSession, i);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, z);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItem$25$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7713x8f006fd1(MediaItem mediaItem, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithResetPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda37
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7714x4f9cf3e3(list, iMediaSession, i);
                }
            });
            setMediaItemsInternal(list, -1, C.TIME_UNSET, true);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItems$26$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7714x4f9cf3e3(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29())));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list, final boolean z) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda92
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7715x4f268de4(list, z, iMediaSession, i);
                }
            });
            setMediaItemsInternal(list, -1, C.TIME_UNSET, z);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItems$27$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7715x4f268de4(List list, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemsWithResetPosition(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29())), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list, final int i, final long j) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda22
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7716x4eb027e5(list, i, j, iMediaSession, i2);
                }
            });
            setMediaItemsInternal(list, i, j, false);
        }
    }

    /* JADX INFO: renamed from: lambda$setMediaItems$28$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7716x4eb027e5(List list, int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setMediaItemsWithStartIndex(this.controllerStub, i2, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29())), i, j);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaylistMetadata(final MediaMetadata mediaMetadata) {
        if (isPlayerCommandAvailable(19)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda39
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7720x8c9e18a1(mediaMetadata, iMediaSession, i);
                }
            });
            if (this.playerInfo.playlistMetadata.equals(mediaMetadata)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda40
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(mediaMetadata);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* JADX INFO: renamed from: lambda$setPlaylistMetadata$29$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7720x8c9e18a1(MediaMetadata mediaMetadata, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaylistMetadata(this.controllerStub, i, mediaMetadata.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getPlaylistMetadata() {
        return this.playerInfo.playlistMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda80
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7648x26d3e9ab(mediaItem, iMediaSession, i);
                }
            });
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), Collections.singletonList(mediaItem));
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItem$31$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7648x26d3e9ab(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(final int i, final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda11
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7649x265d83ac(i, mediaItem, iMediaSession, i2);
                }
            });
            addMediaItemsInternal(i, Collections.singletonList(mediaItem));
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItem$32$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7649x265d83ac(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemWithIndex(this.controllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda28
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7650x28610820(list, iMediaSession, i);
                }
            });
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), list);
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItems$33$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7650x28610820(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29())));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(final int i, final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda100
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7651x27eaa221(i, list, iMediaSession, i2);
                }
            });
            addMediaItemsInternal(i, list);
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItems$34$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7651x27eaa221(int i, List list, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemsWithIndex(this.controllerStub, i2, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29())));
    }

    private void addMediaItemsInternal(int i, List<MediaItem> list) {
        if (list.isEmpty()) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
        } else {
            updatePlayerInfo(maskPlayerInfoForAddedItems(this.playerInfo, Math.min(i, this.playerInfo.timeline.getWindowCount()), list, getCurrentPosition(), getContentPosition()), 0, null, null, this.playerInfo.timeline.isEmpty() ? 3 : null);
        }
    }

    private static PlayerInfo maskPlayerInfoForAddedItems(PlayerInfo playerInfo, int i, List<MediaItem> list, long j, long j2) {
        int size;
        int size2;
        int i2;
        Timeline timeline = playerInfo.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        for (int i4 = 0; i4 < timeline.getWindowCount(); i4++) {
            arrayList.add(timeline.getWindow(i4, new Timeline.Window()));
        }
        for (int i5 = 0; i5 < list.size(); i5++) {
            arrayList.add(i5 + i, createNewWindow(list.get(i5)));
        }
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (playerInfo.timeline.isEmpty()) {
            i2 = 0;
        } else {
            if (playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i) {
                size = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex + list.size();
            } else {
                size = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            }
            i3 = size;
            if (playerInfo.sessionPositionInfo.positionInfo.periodIndex >= i) {
                size2 = playerInfo.sessionPositionInfo.positionInfo.periodIndex + list.size();
            } else {
                size2 = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
            }
            i2 = size2;
        }
        return maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, i3, i2, j, j2, 5);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItem(final int i) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda89
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7683xcb0addec(i, iMediaSession, i2);
                }
            });
            removeMediaItemsInternal(i, i + 1);
        }
    }

    /* JADX INFO: renamed from: lambda$removeMediaItem$35$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7683xcb0addec(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.removeMediaItem(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItems(final int i, final int i2) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0 && i2 >= i);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda45
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                    this.f$0.m7684x42fed186(i, i2, iMediaSession, i3);
                }
            });
            removeMediaItemsInternal(i, i2);
        }
    }

    /* JADX INFO: renamed from: lambda$removeMediaItems$36$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7684x42fed186(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearMediaItems() {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda93
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7652x7db4f6b8(iMediaSession, i);
                }
            });
            removeMediaItemsInternal(0, Integer.MAX_VALUE);
        }
    }

    /* JADX INFO: renamed from: lambda$clearMediaItems$37$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7652x7db4f6b8(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.clearMediaItems(this.controllerStub, i);
    }

    private void removeMediaItemsInternal(int i, int i2) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int iMin = Math.min(i2, windowCount);
        if (i >= windowCount || i == iMin || windowCount == 0) {
            return;
        }
        boolean z = getCurrentMediaItemIndex() >= i && getCurrentMediaItemIndex() < iMin;
        updatePlayerInfo(maskPlayerInfoForRemovedItems(this.playerInfo, i, iMin, false, getCurrentPosition(), getContentPosition()), 0, null, z ? 4 : null, this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i && this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < iMin ? 3 : null);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0094 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0096  */
    /* JADX WARN: Code duplicated, block: B:35:0x00a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00af  */
    /* JADX WARN: Code duplicated, block: B:38:0x0106  */
    private static PlayerInfo maskPlayerInfoForRemovedItems(PlayerInfo playerInfo, int i, int i2, boolean z, long j, long j2) {
        int i3;
        Timeline timeline;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        PlayerInfo playerInfoMaskTimelineAndPositionInfo;
        Timeline timeline2 = playerInfo.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i10 = 0; i10 < timeline2.getWindowCount(); i10++) {
            if (i10 < i || i10 >= i2) {
                arrayList.add(timeline2.getWindow(i10, new Timeline.Window()));
            }
        }
        rebuildPeriods(timeline2, arrayList, arrayList2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        int newPeriodIndexWithoutRemovedPeriods = 0;
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(playerInfo);
        int i11 = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
        Timeline.Window window = new Timeline.Window();
        boolean z2 = currentMediaItemIndexInternal >= i && currentMediaItemIndexInternal < i2;
        if (timelineCreateMaskingTimeline.isEmpty()) {
            i3 = currentMediaItemIndexInternal;
            timeline = timeline2;
            i4 = i;
            i5 = i2;
            i7 = -1;
        } else {
            if (!z2) {
                i3 = currentMediaItemIndexInternal;
                timeline = timeline2;
                i4 = i;
                i5 = i2;
                if (i3 >= i5) {
                    i8 = i3 - (i5 - i4);
                    newPeriodIndexWithoutRemovedPeriods = getNewPeriodIndexWithoutRemovedPeriods(timeline, i11, i4, i5);
                } else {
                    i6 = i11;
                    i7 = i3;
                }
                if (z2) {
                    i9 = 4;
                    playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, i7, i6, j, j2, 4);
                } else if (i7 == -1) {
                    playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT, 4);
                    i9 = 4;
                } else if (z) {
                    i9 = 4;
                    playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, i7, i6, j, j2, 4);
                } else {
                    i9 = 4;
                    Timeline.Window window2 = timelineCreateMaskingTimeline.getWindow(i7, new Timeline.Window());
                    long defaultPositionMs = window2.getDefaultPositionMs();
                    long durationMs = window2.getDurationMs();
                    Player.PositionInfo positionInfo = new Player.PositionInfo(null, i7, window2.mediaItem, null, i6, defaultPositionMs, defaultPositionMs, -1, -1);
                    playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, positionInfo, new SessionPositionInfo(positionInfo, false, SystemClock.elapsedRealtime(), durationMs, defaultPositionMs, MediaUtils.calculateBufferedPercentage(defaultPositionMs, durationMs), 0L, C.TIME_UNSET, durationMs, defaultPositionMs), 4);
                }
                return playerInfoMaskTimelineAndPositionInfo.playbackState != 1 ? playerInfoMaskTimelineAndPositionInfo : playerInfoMaskTimelineAndPositionInfo;
            }
            int iResolveSubsequentMediaItemIndex = resolveSubsequentMediaItemIndex(playerInfo.repeatMode, playerInfo.shuffleModeEnabled, currentMediaItemIndexInternal, timeline2, i, i2);
            i3 = currentMediaItemIndexInternal;
            timeline = timeline2;
            i4 = i;
            i5 = i2;
            if (iResolveSubsequentMediaItemIndex == -1) {
                iResolveSubsequentMediaItemIndex = timelineCreateMaskingTimeline.getFirstWindowIndex(playerInfo.shuffleModeEnabled);
            } else if (iResolveSubsequentMediaItemIndex >= i5) {
                iResolveSubsequentMediaItemIndex -= i5 - i4;
            }
            i8 = iResolveSubsequentMediaItemIndex;
            newPeriodIndexWithoutRemovedPeriods = timelineCreateMaskingTimeline.getWindow(i8, window).firstPeriodIndex;
            i7 = i8;
        }
        i6 = newPeriodIndexWithoutRemovedPeriods;
        if (z2) {
            i9 = 4;
            playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, i7, i6, j, j2, 4);
        } else if (i7 == -1) {
            playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT, 4);
            i9 = 4;
        } else if (z) {
            i9 = 4;
            playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, i7, i6, j, j2, 4);
        } else {
            i9 = 4;
            Timeline.Window window3 = timelineCreateMaskingTimeline.getWindow(i7, new Timeline.Window());
            long defaultPositionMs2 = window3.getDefaultPositionMs();
            long durationMs2 = window3.getDurationMs();
            Player.PositionInfo positionInfo2 = new Player.PositionInfo(null, i7, window3.mediaItem, null, i6, defaultPositionMs2, defaultPositionMs2, -1, -1);
            playerInfoMaskTimelineAndPositionInfo = maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, positionInfo2, new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), durationMs2, defaultPositionMs2, MediaUtils.calculateBufferedPercentage(defaultPositionMs2, durationMs2), 0L, C.TIME_UNSET, durationMs2, defaultPositionMs2), 4);
        }
        return playerInfoMaskTimelineAndPositionInfo.playbackState != 1 ? playerInfoMaskTimelineAndPositionInfo : playerInfoMaskTimelineAndPositionInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItem(final int i, final int i2) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0 && i2 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda12
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                    this.f$0.m7661x942d10fc(i, i2, iMediaSession, i3);
                }
            });
            moveMediaItemsInternal(i, i + 1, i2);
        }
    }

    /* JADX INFO: renamed from: lambda$moveMediaItem$38$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7661x942d10fc(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.moveMediaItem(this.controllerStub, i3, i, i2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItems(final int i, final int i2, final int i3) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda85
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i4) throws RemoteException {
                    this.f$0.m7662xc7c2dc1c(i, i2, i3, iMediaSession, i4);
                }
            });
            moveMediaItemsInternal(i, i2, i3);
        }
    }

    /* JADX INFO: renamed from: lambda$moveMediaItems$39$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7662xc7c2dc1c(int i, int i2, int i3, IMediaSession iMediaSession, int i4) throws RemoteException {
        iMediaSession.moveMediaItems(this.controllerStub, i4, i, i2, i3);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItem(final int i, final MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda27
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7685x2048cfdc(i, mediaItem, iMediaSession, i2);
                }
            });
            replaceMediaItemsInternal(i, i + 1, ImmutableList.of(mediaItem));
        }
    }

    /* JADX INFO: renamed from: lambda$replaceMediaItem$40$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7685x2048cfdc(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        int interfaceVersion = ((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion();
        MediaControllerStub mediaControllerStub = this.controllerStub;
        if (interfaceVersion >= 2) {
            iMediaSession.replaceMediaItem(mediaControllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
        } else {
            iMediaSession.addMediaItemWithIndex(mediaControllerStub, i2, i + 1, mediaItem.toBundleIncludeLocalConfiguration());
            iMediaSession.removeMediaItem(this.controllerStub, i2, i);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItems(final int i, final int i2, final List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Preconditions.checkArgument(i >= 0 && i <= i2);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda124
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                    this.f$0.m7686xfe3de28a(list, i, i2, iMediaSession, i3);
                }
            });
            replaceMediaItemsInternal(i, i2, list);
        }
    }

    /* JADX INFO: renamed from: lambda$replaceMediaItems$41$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7686xfe3de28a(List list, int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        BundleListRetriever bundleListRetriever = new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda29()));
        int interfaceVersion = ((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion();
        MediaControllerStub mediaControllerStub = this.controllerStub;
        if (interfaceVersion >= 2) {
            iMediaSession.replaceMediaItems(mediaControllerStub, i3, i, i2, bundleListRetriever);
        } else {
            iMediaSession.addMediaItemsWithIndex(mediaControllerStub, i3, i2, bundleListRetriever);
            iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
        }
    }

    private void replaceMediaItemsInternal(int i, int i2, List<MediaItem> list) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        if (i > windowCount) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
            return;
        }
        int iMin = Math.min(i2, windowCount);
        PlayerInfo playerInfoMaskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(maskPlayerInfoForAddedItems(this.playerInfo, iMin, list, getCurrentPosition(), getContentPosition()), i, iMin, true, getCurrentPosition(), getContentPosition());
        boolean z = this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i && this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < iMin;
        updatePlayerInfo(playerInfoMaskPlayerInfoForRemovedItems, 0, null, z ? 4 : null, z ? 3 : null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentPeriodIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.periodIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentMediaItemIndex() {
        return getCurrentMediaItemIndexInternal(this.playerInfo);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPreviousMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getNextMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getNextWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPreviousMediaItem() {
        if (isPlayerCommandAvailable(6)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda91
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7696xb975faae(iMediaSession, i);
                }
            });
            if (getPreviousMediaItemIndex() != -1) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$seekToPreviousMediaItem$42$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7696xb975faae(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPreviousMediaItem(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNextMediaItem() {
        if (isPlayerCommandAvailable(8)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda16
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7694x38a1baab(iMediaSession, i);
                }
            });
            if (getNextMediaItemIndex() != -1) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$seekToNextMediaItem$43$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7694x38a1baab(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNextMediaItem(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPrevious() {
        if (isPlayerCommandAvailable(7)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda90
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7695x5c02aed9(iMediaSession, i);
                }
            });
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            boolean zHasPreviousMediaItem = hasPreviousMediaItem();
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                if (zHasPreviousMediaItem) {
                    seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
                }
            } else if (zHasPreviousMediaItem && getCurrentPosition() <= getMaxSeekToPreviousPosition()) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            } else {
                seekToInternal(getCurrentMediaItemIndex(), 0L);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$seekToPrevious$44$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7695x5c02aed9(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPrevious(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getMaxSeekToPreviousPosition() {
        return this.playerInfo.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNext() {
        if (isPlayerCommandAvailable(9)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda113
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7693x18d4375e(iMediaSession, i);
                }
            });
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            if (hasNextMediaItem()) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
                return;
            }
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$seekToNext$45$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7693x18d4375e(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNext(this.controllerStub, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getRepeatMode() {
        return this.playerInfo.repeatMode;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setRepeatMode(final int i) {
        if (isPlayerCommandAvailable(15)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda13
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7723xe07ed13f(i, iMediaSession, i2);
                }
            });
            if (this.playerInfo.repeatMode != i) {
                this.playerInfo = this.playerInfo.copyWithRepeatMode(i);
                this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda14
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onRepeatModeChanged(i);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setRepeatMode$46$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7723xe07ed13f(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setRepeatMode(this.controllerStub, i2, i);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getShuffleModeEnabled() {
        return this.playerInfo.shuffleModeEnabled;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setShuffleModeEnabled(final boolean z) {
        if (isPlayerCommandAvailable(14)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda86
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7724x9e3d615a(z, iMediaSession, i);
                }
            });
            if (this.playerInfo.shuffleModeEnabled != z) {
                this.playerInfo = this.playerInfo.copyWithShuffleModeEnabled(z);
                this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda87
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onShuffleModeEnabledChanged(z);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setShuffleModeEnabled$48$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7724x9e3d615a(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setShuffleModeEnabled(this.controllerStub, i, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public CueGroup getCurrentCues() {
        return this.playerInfo.cueGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public float getVolume() {
        return this.playerInfo.volume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVolume(final float f) {
        if (isPlayerCommandAvailable(24)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda118
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7728xcf4e4e1c(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.volume != f) {
                this.playerInfo = this.playerInfo.copyWithVolume(f);
                this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda119
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onVolumeChanged(f);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setVolume$50$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7728xcf4e4e1c(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVolume(this.controllerStub, i, f);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void mute() {
        if (isPlayerCommandAvailable(24)) {
            final float f = 0.0f;
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda8
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7663lambda$mute$52$androidxmedia3sessionMediaControllerImplBase(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.volume != 0.0f) {
                this.playerInfo = this.playerInfo.copyWithVolume(0.0f);
                this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda9
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onVolumeChanged(f);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$mute$52$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7663lambda$mute$52$androidxmedia3sessionMediaControllerImplBase(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        int interfaceVersion = ((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion();
        MediaControllerStub mediaControllerStub = this.controllerStub;
        if (interfaceVersion >= 6) {
            iMediaSession.mute(mediaControllerStub, i);
        } else {
            iMediaSession.setVolume(mediaControllerStub, i, f);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void unmute() {
        if (isPlayerCommandAvailable(24)) {
            final float f = this.playerInfo.unmuteVolume;
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda5
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7730lambda$unmute$54$androidxmedia3sessionMediaControllerImplBase(f, iMediaSession, i);
                }
            });
            if (this.playerInfo.volume == this.playerInfo.unmuteVolume || this.playerInfo.volume != 0.0f) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithVolume(f);
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda6
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVolumeChanged(f);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* JADX INFO: renamed from: lambda$unmute$54$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7730lambda$unmute$54$androidxmedia3sessionMediaControllerImplBase(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        int interfaceVersion = ((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion();
        MediaControllerStub mediaControllerStub = this.controllerStub;
        if (interfaceVersion >= 6) {
            iMediaSession.unmute(mediaControllerStub, i);
        } else {
            iMediaSession.setVolume(mediaControllerStub, i, f);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public DeviceInfo getDeviceInfo() {
        return this.playerInfo.deviceInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getDeviceVolume() {
        return this.playerInfo.deviceVolume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isDeviceMuted() {
        return this.playerInfo.deviceMuted;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(25)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda17
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7705x43e8e82c(i, iMediaSession, i2);
                }
            });
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume == i || deviceInfo.minVolume > i) {
                return;
            }
            if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda18
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7706x4372822d(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setDeviceVolume$56$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7705x43e8e82c(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceVolume(this.controllerStub, i2, i);
    }

    /* JADX INFO: renamed from: lambda$setDeviceVolume$57$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7706x4372822d(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceVolume(final int i, final int i2) {
        if (isPlayerCommandAvailable(33)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda77
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                    this.f$0.m7707x42fc1c2e(i, i2, iMediaSession, i3);
                }
            });
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume == i || deviceInfo.minVolume > i) {
                return;
            }
            if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda78
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7708x4285b62f(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setDeviceVolume$58$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7707x42fc1c2e(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.setDeviceVolumeWithFlags(this.controllerStub, i3, i, i2);
    }

    /* JADX INFO: renamed from: lambda$setDeviceVolume$59$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7708x4285b62f(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void increaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda103
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7657x77e5668b(iMediaSession, i);
                }
            });
            final int i = this.playerInfo.deviceVolume + 1;
            int i2 = getDeviceInfo().maxVolume;
            if (i2 == 0 || i <= i2) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda104
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7658x776f008c(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$increaseDeviceVolume$60$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7657x77e5668b(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.increaseDeviceVolume(this.controllerStub, i);
    }

    /* JADX INFO: renamed from: lambda$increaseDeviceVolume$61$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7658x776f008c(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void increaseDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda109
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7659x76f89a8d(i, iMediaSession, i2);
                }
            });
            final int i2 = this.playerInfo.deviceVolume + 1;
            int i3 = getDeviceInfo().maxVolume;
            if (i3 == 0 || i2 <= i3) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i2, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda110
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7660x7682348e(i2, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$increaseDeviceVolume$62$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7659x76f89a8d(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.increaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* JADX INFO: renamed from: lambda$increaseDeviceVolume$63$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7660x7682348e(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void decreaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda19
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7653x64de8b3(iMediaSession, i);
                }
            });
            final int i = this.playerInfo.deviceVolume - 1;
            if (i >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda20
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7654x5d782b4(i, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$decreaseDeviceVolume$64$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7653x64de8b3(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.decreaseDeviceVolume(this.controllerStub, i);
    }

    /* JADX INFO: renamed from: lambda$decreaseDeviceVolume$65$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7654x5d782b4(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void decreaseDeviceVolume(final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda30
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7655x5611cb5(i, iMediaSession, i2);
                }
            });
            final int i2 = this.playerInfo.deviceVolume - 1;
            if (i2 >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i2, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda31
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7656x4eab6b6(i2, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$decreaseDeviceVolume$66$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7655x5611cb5(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.decreaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* JADX INFO: renamed from: lambda$decreaseDeviceVolume$67$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7656x4eab6b6(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceMuted(final boolean z) {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda116
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7701x96970cb2(z, iMediaSession, i);
                }
            });
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda117
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7702x9620a6b3(z, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setDeviceMuted$68$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7701x96970cb2(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setDeviceMuted(this.controllerStub, i, z);
    }

    /* JADX INFO: renamed from: lambda$setDeviceMuted$69$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7702x9620a6b3(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceMuted(final boolean z, final int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda83
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i2) throws RemoteException {
                    this.f$0.m7703x8bf3e2c9(z, i, iMediaSession, i2);
                }
            });
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda84
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7704x8b7d7cca(z, (Player.Listener) obj);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setDeviceMuted$70$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7703x8bf3e2c9(boolean z, int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceMutedWithFlags(this.controllerStub, i2, z, i);
    }

    /* JADX INFO: renamed from: lambda$setDeviceMuted$71$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7704x8b7d7cca(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setAudioAttributes(final AudioAttributes audioAttributes, final boolean z) {
        if (isPlayerCommandAvailable(35)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda25
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7700xc6c2d033(audioAttributes, z, iMediaSession, i);
                }
            });
            if (this.playerInfo.audioAttributes.equals(audioAttributes)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithAudioAttributes(audioAttributes);
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda26
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(audioAttributes);
                }
            });
            this.listeners.flushEvents();
        }
    }

    /* JADX INFO: renamed from: lambda$setAudioAttributes$72$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7700xc6c2d033(AudioAttributes audioAttributes, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setAudioAttributes(this.controllerStub, i, audioAttributes.toBundle(), z);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public VideoSize getVideoSize() {
        return this.playerInfo.videoSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Size getSurfaceSize() {
        return this.surfaceSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface() {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            setVideoSurfaceWithSize(null, 0, 0);
            onSurfaceSizeChanged(0, 0);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27) && surface != null && this.videoSurface == surface) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            this.videoSurface = surface;
            int i = surface == null ? 0 : -1;
            setVideoSurfaceWithSize(surface, i, i);
            onSurfaceSizeChanged(i, i);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoSurfaceHolder == surfaceHolder) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoSurfaceHolder = surfaceHolder;
            surfaceHolder.addCallback(this.surfaceCallback);
            Surface surface = surfaceHolder.getSurface();
            if (surface != null && surface.isValid()) {
                this.videoSurface = surface;
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                setVideoSurfaceWithSize(surface, surfaceFrame.width(), surfaceFrame.height());
                onSurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
                return;
            }
            this.videoSurface = null;
            setVideoSurfaceWithSize(null, 0, 0);
            onSurfaceSizeChanged(0, 0);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27) && surfaceHolder != null && this.videoSurfaceHolder == surfaceHolder) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27)) {
            if (textureView == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoTextureView == textureView) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoTextureView = textureView;
            textureView.setSurfaceTextureListener(this.surfaceCallback);
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            if (surfaceTexture == null) {
                setVideoSurfaceWithSize(null, 0, 0);
                onSurfaceSizeChanged(0, 0);
            } else {
                Surface surface = new Surface(surfaceTexture);
                this.videoSurface = surface;
                setVideoSurfaceWithSize(surface, textureView.getWidth(), textureView.getHeight());
                onSurfaceSizeChanged(textureView.getWidth(), textureView.getHeight());
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27) && textureView != null && this.videoTextureView == textureView) {
            clearVideoSurface();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoSurfaceWithSize(final Surface surface, final int i, final int i2) {
        if (isConnected()) {
            if (((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 8) {
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda35
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                        this.f$0.m7726xad11541f(surface, i, i2, iMediaSession, i3);
                    }
                });
            } else {
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda36
                    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                    public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                        this.f$0.m7727xac9aee20(surface, iMediaSession, i3);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setVideoSurfaceWithSize$74$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7726xad11541f(Surface surface, int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.setVideoSurfaceWithSize(this.controllerStub, i3, surface, i, i2);
    }

    /* JADX INFO: renamed from: lambda$setVideoSurfaceWithSize$75$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7727xac9aee20(Surface surface, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, surface);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getMediaMetadata() {
        return this.playerInfo.mediaMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Player.Commands getAvailableCommands() {
        return this.intersectedPlayerCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Tracks getCurrentTracks() {
        return this.playerInfo.currentTracks;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.playerInfo.trackSelectionParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setTrackSelectionParameters(final TrackSelectionParameters trackSelectionParameters) {
        if (isPlayerCommandAvailable(29)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda21
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    this.f$0.m7725xd45f0bcf(trackSelectionParameters, iMediaSession, i);
                }
            });
            if (trackSelectionParameters != this.playerInfo.trackSelectionParameters) {
                this.playerInfo = this.playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
                this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda23
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onTrackSelectionParametersChanged(trackSelectionParameters);
                    }
                });
                this.listeners.flushEvents();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$setTrackSelectionParameters$76$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7725xd45f0bcf(TrackSelectionParameters trackSelectionParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setTrackSelectionParameters(this.controllerStub, i, trackSelectionParameters.toBundle());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return this.sessionCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public IMediaController getBinder() {
        return this.controllerStub;
    }

    private static Timeline createMaskingTimeline(List<Timeline.Window> list, List<Timeline.Period> list2) {
        return new Timeline.RemotableTimeline(new ImmutableList.Builder().addAll((Iterable) list).build(), new ImmutableList.Builder().addAll((Iterable) list2).build(), MediaUtils.generateUnshuffledIndices(list.size()));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x013d: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:2231) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    private void setMediaItemsInternal(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x013d: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:2231) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r44v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
        */

    private void moveMediaItemsInternal(int i, int i2, int i3) {
        int i4;
        int i5;
        Timeline timeline = this.playerInfo.timeline;
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int iMin = Math.min(i2, windowCount);
        int i6 = iMin - i;
        int iMin2 = Math.min(i3, windowCount - i6);
        if (i >= windowCount || i == iMin || i == iMin2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < windowCount; i7++) {
            arrayList.add(timeline.getWindow(i7, new Timeline.Window()));
        }
        Util.moveItems(arrayList, i, iMin, iMin2);
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (timelineCreateMaskingTimeline.isEmpty()) {
            return;
        }
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (currentMediaItemIndex >= i && currentMediaItemIndex < iMin) {
            i5 = (currentMediaItemIndex - i) + iMin2;
        } else {
            if (iMin > currentMediaItemIndex || iMin2 <= currentMediaItemIndex) {
                if (iMin <= currentMediaItemIndex || iMin2 > currentMediaItemIndex) {
                    i4 = currentMediaItemIndex;
                } else {
                    i5 = currentMediaItemIndex + i6;
                }
                Timeline.Window window = new Timeline.Window();
                updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, timelineCreateMaskingTimeline, i4, timelineCreateMaskingTimeline.getWindow(i4, window).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
            }
            i5 = currentMediaItemIndex - i6;
        }
        i4 = i5;
        Timeline.Window window2 = new Timeline.Window();
        updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, timelineCreateMaskingTimeline, i4, timelineCreateMaskingTimeline.getWindow(i4, window2).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window2).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
    }

    private void seekToInternalByOffset(long j) {
        long currentPosition = getCurrentPosition() + j;
        long duration = getDuration();
        if (duration != C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekToInternal(getCurrentMediaItemIndex(), Math.max(currentPosition, 0L));
    }

    private void seekToInternal(int i, long j) {
        int i2;
        int i3;
        PlayerInfo playerInfoMaskPositionInfo;
        Timeline timeline = this.playerInfo.timeline;
        if ((timeline.isEmpty() || i < timeline.getWindowCount()) && !isPlayingAd()) {
            int i4 = getPlaybackState() == 1 ? 1 : 2;
            PlayerInfo playerInfo = this.playerInfo;
            PlayerInfo playerInfoCopyWithPlaybackState = playerInfo.copyWithPlaybackState(i4, playerInfo.playerError);
            PeriodInfo periodInfo = getPeriodInfo(timeline, i, j);
            if (periodInfo == null) {
                i2 = 1;
                i3 = 2;
                Player.PositionInfo positionInfo = new Player.PositionInfo(null, i, null, null, i, j == C.TIME_UNSET ? 0L : j, j == C.TIME_UNSET ? 0L : j, -1, -1);
                PlayerInfo playerInfo2 = this.playerInfo;
                playerInfoMaskPositionInfo = maskTimelineAndPositionInfo(playerInfo2, playerInfo2.timeline, positionInfo, new SessionPositionInfo(positionInfo, this.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), this.playerInfo.sessionPositionInfo.durationMs, j == C.TIME_UNSET ? 0L : j, 0, 0L, this.playerInfo.sessionPositionInfo.currentLiveOffsetMs, this.playerInfo.sessionPositionInfo.contentDurationMs, j == C.TIME_UNSET ? 0L : j), 1);
            } else {
                i2 = 1;
                i3 = 2;
                playerInfoMaskPositionInfo = maskPositionInfo(playerInfoCopyWithPlaybackState, timeline, periodInfo);
            }
            int i5 = (this.playerInfo.timeline.isEmpty() || playerInfoMaskPositionInfo.sessionPositionInfo.positionInfo.mediaItemIndex == this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex) ? 0 : i2;
            if (i5 == 0 && playerInfoMaskPositionInfo.sessionPositionInfo.positionInfo.positionMs == this.playerInfo.sessionPositionInfo.positionInfo.positionMs) {
                return;
            }
            updatePlayerInfo(playerInfoMaskPositionInfo, null, null, Integer.valueOf(i2), i5 != 0 ? Integer.valueOf(i3) : null);
        }
    }

    private void setPlayWhenReady(boolean z, int i) {
        int playbackSuppressionReason = getPlaybackSuppressionReason();
        if (playbackSuppressionReason == 1) {
            playbackSuppressionReason = 0;
        }
        if (this.playerInfo.playWhenReady == z && this.playerInfo.playbackSuppressionReason == playbackSuppressionReason) {
            return;
        }
        this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
        updatePlayerInfo(this.playerInfo.copyWithPlayWhenReady(z, i, playbackSuppressionReason), null, Integer.valueOf(i), null, null);
    }

    private void updatePlayerInfo(PlayerInfo playerInfo, Integer num, Integer num2, Integer num3, Integer num4) {
        PlayerInfo playerInfo2 = this.playerInfo;
        this.playerInfo = playerInfo;
        notifyPlayerInfoListenersWithReasons(playerInfo2, playerInfo, num, num2, num3, num4);
    }

    private void notifyPlayerInfoListenersWithReasons(PlayerInfo playerInfo, final PlayerInfo playerInfo2, final Integer num, final Integer num2, final Integer num3, final Integer num4) {
        if (num != null) {
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTimelineChanged(playerInfo2.timeline, num.intValue());
                }
            });
        }
        if (num3 != null) {
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda58
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    PlayerInfo playerInfo3 = playerInfo2;
                    ((Player.Listener) obj).onPositionDiscontinuity(playerInfo3.oldPositionInfo, playerInfo3.newPositionInfo, num3.intValue());
                }
            });
        }
        final MediaItem currentMediaItem = playerInfo2.getCurrentMediaItem();
        if (num4 != null) {
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda67
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaItemTransition(currentMediaItem, num4.intValue());
                }
            });
        }
        PlaybackException playbackException = playerInfo.playerError;
        final PlaybackException playbackException2 = playerInfo2.playerError;
        if (playbackException != playbackException2 && (playbackException == null || !playbackException.errorInfoEquals(playbackException2))) {
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda68
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayerErrorChanged(playbackException2);
                }
            });
            if (playbackException2 != null) {
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda70
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        ((Player.Listener) obj).onPlayerError(playbackException2);
                    }
                });
            }
        }
        if (!playerInfo.currentTracks.equals(playerInfo2.currentTracks)) {
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda71
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTracksChanged(playerInfo2.currentTracks);
                }
            });
        }
        if (!playerInfo.mediaMetadata.equals(playerInfo2.mediaMetadata)) {
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda72
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMediaMetadataChanged(playerInfo2.mediaMetadata);
                }
            });
        }
        if (playerInfo.isLoading != playerInfo2.isLoading) {
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda73
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsLoadingChanged(playerInfo2.isLoading);
                }
            });
        }
        if (playerInfo.playbackState != playerInfo2.playbackState) {
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda74
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackStateChanged(playerInfo2.playbackState);
                }
            });
        }
        if (num2 != null) {
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda75
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlayWhenReadyChanged(playerInfo2.playWhenReady, num2.intValue());
                }
            });
        }
        if (playerInfo.playbackSuppressionReason != playerInfo2.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda47
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackSuppressionReasonChanged(playerInfo2.playbackSuppressionReason);
                }
            });
        }
        if (playerInfo.isPlaying != playerInfo2.isPlaying) {
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda49
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onIsPlayingChanged(playerInfo2.isPlaying);
                }
            });
        }
        if (!playerInfo.playbackParameters.equals(playerInfo2.playbackParameters)) {
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda50
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaybackParametersChanged(playerInfo2.playbackParameters);
                }
            });
        }
        if (playerInfo.repeatMode != playerInfo2.repeatMode) {
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda51
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onRepeatModeChanged(playerInfo2.repeatMode);
                }
            });
        }
        if (playerInfo.shuffleModeEnabled != playerInfo2.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda52
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onShuffleModeEnabledChanged(playerInfo2.shuffleModeEnabled);
                }
            });
        }
        if (!playerInfo.playlistMetadata.equals(playerInfo2.playlistMetadata)) {
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda53
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onPlaylistMetadataChanged(playerInfo2.playlistMetadata);
                }
            });
        }
        if (playerInfo.volume != playerInfo2.volume) {
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda54
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVolumeChanged(playerInfo2.volume);
                }
            });
        }
        if (!playerInfo.audioAttributes.equals(playerInfo2.audioAttributes)) {
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda55
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioAttributesChanged(playerInfo2.audioAttributes);
                }
            });
        }
        if (playerInfo.audioSessionId != playerInfo2.audioSessionId) {
            this.listeners.queueEvent(21, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda56
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onAudioSessionIdChanged(playerInfo2.audioSessionId);
                }
            });
        }
        if (!playerInfo.cueGroup.cues.equals(playerInfo2.cueGroup.cues)) {
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda57
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onCues(playerInfo2.cueGroup.cues);
                }
            });
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda59
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onCues(playerInfo2.cueGroup);
                }
            });
        }
        if (!playerInfo.deviceInfo.equals(playerInfo2.deviceInfo)) {
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda60
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceInfoChanged(playerInfo2.deviceInfo);
                }
            });
        }
        if (playerInfo.deviceVolume != playerInfo2.deviceVolume || playerInfo.deviceMuted != playerInfo2.deviceMuted) {
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda61
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    PlayerInfo playerInfo3 = playerInfo2;
                    ((Player.Listener) obj).onDeviceVolumeChanged(playerInfo3.deviceVolume, playerInfo3.deviceMuted);
                }
            });
        }
        if (!playerInfo.videoSize.equals(playerInfo2.videoSize)) {
            this.listeners.queueEvent(25, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda62
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onVideoSizeChanged(playerInfo2.videoSize);
                }
            });
        }
        if (playerInfo.seekBackIncrementMs != playerInfo2.seekBackIncrementMs) {
            this.listeners.queueEvent(16, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda63
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekBackIncrementChanged(playerInfo2.seekBackIncrementMs);
                }
            });
        }
        if (playerInfo.seekForwardIncrementMs != playerInfo2.seekForwardIncrementMs) {
            this.listeners.queueEvent(17, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda64
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onSeekForwardIncrementChanged(playerInfo2.seekForwardIncrementMs);
                }
            });
        }
        if (playerInfo.maxSeekToPreviousPositionMs != playerInfo2.maxSeekToPreviousPositionMs) {
            this.listeners.queueEvent(18, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda65
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onMaxSeekToPreviousPositionChanged(playerInfo2.maxSeekToPreviousPositionMs);
                }
            });
        }
        if (!playerInfo.trackSelectionParameters.equals(playerInfo2.trackSelectionParameters)) {
            this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda66
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onTrackSelectionParametersChanged(playerInfo2.trackSelectionParameters);
                }
            });
        }
        this.listeners.flushEvents();
    }

    private boolean requestConnectToService() {
        int i = Build.VERSION.SDK_INT >= 29 ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 1;
        Intent intent = new Intent(MediaSessionService.SERVICE_INTERFACE);
        intent.setClassName(this.token.getPackageName(), this.token.getServiceName());
        try {
            if (this.context.bindService(intent, this.serviceConnection, i)) {
                return true;
            }
            Log.w(TAG, "bind to " + this.token + " failed");
            return false;
        } catch (SecurityException e) {
            Log.w(TAG, "bind to " + this.token + " not allowed", e);
            return false;
        }
    }

    private boolean requestConnectToSession(Bundle bundle) {
        try {
            IMediaSession.Stub.asInterface((IBinder) Preconditions.checkNotNull(this.token.getBinder())).connect(this.controllerStub, this.sequencedFutureManager.obtainNextSequenceNumber(), new ConnectionRequest(this.context.getPackageName(), Process.myPid(), bundle, this.instance.getMaxCommandsForMediaItems()).toBundle());
            return true;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call connection request.", e);
            return false;
        }
    }

    private void clearSurfacesAndCallbacks() {
        TextureView textureView = this.videoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(null);
            this.videoTextureView = null;
        }
        SurfaceHolder surfaceHolder = this.videoSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.surfaceCallback);
            this.videoSurfaceHolder = null;
        }
        if (this.videoSurface != null) {
            this.videoSurface = null;
        }
    }

    IMediaSession getSessionInterfaceWithSessionCommandIfAble(int i) {
        Preconditions.checkArgument(i != 0);
        if (!this.sessionCommands.contains(i)) {
            Log.w(TAG, "Controller isn't allowed to call command, commandCode=" + i);
            return null;
        }
        return this.iSession;
    }

    IMediaSession getSessionInterfaceWithSessionCommandIfAble(SessionCommand sessionCommand) {
        Preconditions.checkArgument(sessionCommand.commandCode == 0);
        if (!this.sessionCommands.contains(sessionCommand) && !CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction)) {
            Log.w(TAG, "Controller isn't allowed to call custom session command:" + sessionCommand.customAction);
            return null;
        }
        return this.iSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        if (isConnected()) {
            updateSessionPositionInfoIfNeeded(sessionPositionInfo);
        }
    }

    <T> void setFutureResult(final int i, T t) {
        this.sequencedFutureManager.setFutureResult(i, t);
        getInstance().runOnApplicationLooper(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda107
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7710x8e360775(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setFutureResult$107$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7710x8e360775(int i) {
        this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(i));
        this.pendingCustomActionProgressListeners.delete(i);
        SessionToken sessionToken = this.connectedToken;
        if (sessionToken == null || sessionToken.getInterfaceVersion() >= 5 || !this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
            return;
        }
        this.fallbackPlaybackInfoUpdateHandler.postDelayed(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda69
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7709x8eac6d74();
            }
        }, 500L);
    }

    /* JADX INFO: renamed from: lambda$setFutureResult$106$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7709x8eac6d74() {
        PlayerInfo playerInfo = this.pendingPlayerInfo;
        if (playerInfo != null) {
            onPlayerInfoChanged(playerInfo, PlayerInfo.BundlingExclusions.NONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onConnected(ConnectionState connectionState) {
        if (this.iSession != null) {
            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
            getInstance().release();
            return;
        }
        this.iSession = connectionState.sessionBinder;
        this.sessionActivity = connectionState.sessionActivity;
        this.sessionCommands = connectionState.sessionCommands;
        this.playerCommandsFromSession = connectionState.playerCommandsFromSession;
        Player.Commands commands = connectionState.playerCommandsFromPlayer;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.customLayoutOriginal = connectionState.customLayout;
        ImmutableList<CommandButton> immutableList = connectionState.mediaButtonPreferences;
        this.mediaButtonPreferencesOriginal = immutableList;
        ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(immutableList, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, connectionState.sessionExtras);
        this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
        this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, this.customLayoutOriginal, connectionState.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        for (int i = 0; i < connectionState.commandButtonsForMediaItems.size(); i++) {
            CommandButton commandButton = connectionState.commandButtonsForMediaItems.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                builder.put(commandButton.sessionCommand.customAction, commandButton);
            }
        }
        this.commandButtonsForMediaItemsMap = builder.buildOrThrow();
        this.playerInfo = connectionState.playerInfo;
        android.media.session.MediaSession.Token platformToken = connectionState.platformToken == null ? this.token.getPlatformToken() : connectionState.platformToken;
        if (platformToken != null) {
            this.platformController = new android.media.session.MediaController(this.context, platformToken);
        }
        try {
            connectionState.sessionBinder.asBinder().linkToDeath(this.deathRecipient, 0);
            this.connectedToken = new SessionToken(this.token.getUid(), 0, connectionState.libraryVersion, connectionState.sessionInterfaceVersion, this.token.getPackageName(), connectionState.sessionBinder, connectionState.tokenExtras, platformToken);
            this.sessionExtras = connectionState.sessionExtras;
            getInstance().notifyAccepted();
        } catch (RemoteException unused) {
            getInstance().release();
        }
    }

    private void sendControllerResult(int i, SessionResult sessionResult) {
        IMediaSession iMediaSession = this.iSession;
        if (iMediaSession == null) {
            return;
        }
        try {
            iMediaSession.onControllerResult(this.controllerStub, i, sessionResult.toBundle());
        } catch (RemoteException unused) {
            Log.w(TAG, "Error in sending");
        }
    }

    private void sendControllerResultWhenReady(final int i, final ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda95
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7697xcbed3cfc(listenableFuture, i);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$sendControllerResultWhenReady$108$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7697xcbed3cfc(ListenableFuture listenableFuture, int i) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Preconditions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        } catch (CancellationException e2) {
            Log.w(TAG, "Session operation cancelled", e2);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        }
        sendControllerResult(i, sessionResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCustomCommand(final int i, final SessionCommand sessionCommand, final Bundle bundle) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda99
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7673xd22b0eb0(sessionCommand, bundle, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onCustomCommand$109$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7673xd22b0eb0(SessionCommand sessionCommand, Bundle bundle, int i, MediaController.Listener listener) {
        sendControllerResultWhenReady(i, (ListenableFuture) Preconditions.checkNotNull(listener.onCustomCommand(getInstance(), sessionCommand, bundle), "ControllerCallback#onCustomCommand() must not return null"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCustomCommandProgressUpdate(int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
        MediaController.ProgressListener progressListener;
        if (isConnected() && (progressListener = this.pendingCustomActionProgressListeners.get(i)) != null) {
            progressListener.onProgress(getInstance(), sessionCommand, bundle, bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPlayerInfoChanged(PlayerInfo playerInfo, PlayerInfo.BundlingExclusions bundlingExclusions) {
        if (isConnected()) {
            boolean z = ((SessionToken) Preconditions.checkNotNull(this.connectedToken)).getInterfaceVersion() < 6;
            PlayerInfo playerInfo2 = this.pendingPlayerInfo;
            if (playerInfo2 != null) {
                this.pendingPlayerInfo = MediaUtils.mergePlayerInfo(playerInfo2, playerInfo, bundlingExclusions, this.intersectedPlayerCommands, z, (SessionToken) Preconditions.checkNotNull(this.connectedToken));
                if (!this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                    return;
                }
                playerInfo = this.pendingPlayerInfo;
                bundlingExclusions = PlayerInfo.BundlingExclusions.NONE;
                this.pendingPlayerInfo = null;
            }
            PlayerInfo playerInfo3 = playerInfo;
            PlayerInfo playerInfo4 = this.playerInfo;
            PlayerInfo playerInfoMergePlayerInfo = MediaUtils.mergePlayerInfo(playerInfo4, playerInfo3, bundlingExclusions, this.intersectedPlayerCommands, z, (SessionToken) Preconditions.checkNotNull(this.connectedToken));
            this.playerInfo = playerInfoMergePlayerInfo;
            notifyPlayerInfoListenersWithReasons(playerInfo4, playerInfoMergePlayerInfo, !playerInfo4.timeline.equals(playerInfoMergePlayerInfo.timeline) ? Integer.valueOf(playerInfoMergePlayerInfo.timelineChangeReason) : null, (playerInfo4.playWhenReadyChangeReason == playerInfoMergePlayerInfo.playWhenReadyChangeReason && playerInfo4.playWhenReady == playerInfoMergePlayerInfo.playWhenReady) ? null : Integer.valueOf(playerInfoMergePlayerInfo.playWhenReadyChangeReason), (playerInfo4.oldPositionInfo.equals(playerInfo3.oldPositionInfo) && playerInfo4.newPositionInfo.equals(playerInfo3.newPositionInfo)) ? null : Integer.valueOf(playerInfoMergePlayerInfo.discontinuityReason), !Objects.equals(playerInfo4.getCurrentMediaItem(), playerInfoMergePlayerInfo.getCurrentMediaItem()) ? Integer.valueOf(playerInfoMergePlayerInfo.mediaItemTransitionReason) : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAvailableCommandsChangedFromSession(final SessionCommands sessionCommands, Player.Commands commands) {
        boolean z;
        boolean z2;
        if (isConnected()) {
            boolean zEquals = Objects.equals(this.playerCommandsFromSession, commands);
            boolean zEquals2 = Objects.equals(this.sessionCommands, sessionCommands);
            if (zEquals && zEquals2) {
                return;
            }
            this.sessionCommands = sessionCommands;
            boolean z3 = false;
            if (zEquals) {
                z = false;
            } else {
                this.playerCommandsFromSession = commands;
                Player.Commands commands2 = this.intersectedPlayerCommands;
                Player.Commands commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, this.playerCommandsFromPlayer);
                this.intersectedPlayerCommands = commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable;
                z = !Objects.equals(commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable, commands2);
            }
            if (!zEquals2 || z) {
                ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
                ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, sessionCommands, this.intersectedPlayerCommands);
                z2 = !this.resolvedMediaButtonPreferences.equals(immutableList);
                z3 = !this.resolvedCustomLayout.equals(immutableList2);
            } else {
                z2 = false;
            }
            if (z) {
                this.listeners.sendEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda121
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7669xca952a35((Player.Listener) obj);
                    }
                });
            }
            if (!zEquals2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda122
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7670xca1ec436(sessionCommands, (MediaController.Listener) obj);
                    }
                });
            }
            if (z3) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda123
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7671xc9a85e37((MediaController.Listener) obj);
                    }
                });
            }
            if (z2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda1
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7672xc931f838((MediaController.Listener) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromSession$110$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7669xca952a35(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromSession$111$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7670xca1ec436(SessionCommands sessionCommands, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), sessionCommands);
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromSession$112$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7671xc9a85e37(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromSession$113$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7672xc931f838(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAvailableCommandsChangedFromPlayer(Player.Commands commands) {
        boolean z;
        boolean z2;
        if (isConnected() && !Objects.equals(this.playerCommandsFromPlayer, commands)) {
            this.playerCommandsFromPlayer = commands;
            Player.Commands commands2 = this.intersectedPlayerCommands;
            Player.Commands commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
            this.intersectedPlayerCommands = commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable;
            if (Objects.equals(commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable, commands2)) {
                z = false;
                z2 = false;
            } else {
                ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
                ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
                z = !this.resolvedMediaButtonPreferences.equals(immutableList);
                z2 = !this.resolvedCustomLayout.equals(immutableList2);
                this.listeners.sendEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda2
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        this.f$0.m7666xda692e36((Player.Listener) obj);
                    }
                });
            }
            if (z2) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda3
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7667xd9f2c837((MediaController.Listener) obj);
                    }
                });
            }
            if (z) {
                getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda4
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7668xd97c6238((MediaController.Listener) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromPlayer$114$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7666xda692e36(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromPlayer$115$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7667xd9f2c837(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* JADX INFO: renamed from: lambda$onAvailableCommandsChangedFromPlayer$116$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7668xd97c6238(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSetCustomLayout(final int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.customLayoutOriginal = ImmutableList.copyOf((Collection) list);
            ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, list, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, list, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda106
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7676xda7fa130(z2, z, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onSetCustomLayout$117$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7676xda7fa130(boolean z, boolean z2, int i, MediaController.Listener listener) {
        ListenableFuture<SessionResult> listenableFuture = (ListenableFuture) Preconditions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSetMediaButtonPreferences(final int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.mediaButtonPreferencesOriginal = ImmutableList.copyOf((Collection) list);
            ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(list, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda88
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7677x536dd18(z2, z, i, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onSetMediaButtonPreferences$118$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7677x536dd18(boolean z, boolean z2, int i, MediaController.Listener listener) {
        ListenableFuture<SessionResult> listenableFuture = (ListenableFuture) Preconditions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    public void onExtrasChanged(final Bundle bundle) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            ImmutableList<CommandButton> immutableList2 = this.resolvedCustomLayout;
            this.sessionExtras = bundle;
            ImmutableList<CommandButton> immutableListResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, bundle);
            this.resolvedMediaButtonPreferences = immutableListResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(immutableListResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            final boolean z = !this.resolvedMediaButtonPreferences.equals(immutableList);
            final boolean z2 = !this.resolvedCustomLayout.equals(immutableList2);
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda76
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7675x7fe2386(bundle, z2, z, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onExtrasChanged$119$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7675x7fe2386(Bundle bundle, boolean z, boolean z2, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), bundle);
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z2) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
    }

    public void onSetSessionActivity(int i, final PendingIntent pendingIntent) {
        if (!isConnected() || Objects.equals(this.sessionActivity, pendingIntent)) {
            return;
        }
        this.sessionActivity = pendingIntent;
        getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda94
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7678x77563e46(pendingIntent, (MediaController.Listener) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onSetSessionActivity$120$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7678x77563e46(PendingIntent pendingIntent, MediaController.Listener listener) {
        listener.onSessionActivityChanged(getInstance(), pendingIntent);
    }

    public void onError(int i, final SessionError sessionError) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new Consumer() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda120
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7674xcbd48b54(sessionError, (MediaController.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onError$121$androidx-media3-session-MediaControllerImplBase, reason: not valid java name */
    /* synthetic */ void m7674xcbd48b54(SessionError sessionError, MediaController.Listener listener) {
        listener.onError(getInstance(), sessionError);
    }

    public void onSurfaceSizeChanged(final int i, final int i2) {
        if (this.surfaceSize.getWidth() == i && this.surfaceSize.getHeight() == i2) {
            return;
        }
        this.surfaceSize = new Size(i, i2);
        this.listeners.sendEvent(24, new ListenerSet.Event() { // from class: androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda7
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onSurfaceSizeChanged(i, i2);
            }
        });
    }

    public void onRenderedFirstFrame() {
        this.listeners.sendEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda12());
    }

    protected void notifyPlatformControllerAboutMedia3ChangeRequest() {
        android.media.session.MediaController mediaController;
        if (Build.VERSION.SDK_INT < 31 || (mediaController = this.platformController) == null) {
            return;
        }
        mediaController.getTransportControls().sendCustomAction("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST", (Bundle) null);
    }

    private void updateSessionPositionInfoIfNeeded(SessionPositionInfo sessionPositionInfo) {
        if (this.pendingMaskingSequencedFutureNumbers.isEmpty() && this.playerInfo.sessionPositionInfo.eventTimeMs < sessionPositionInfo.eventTimeMs && MediaUtils.areSessionPositionInfosInSamePeriodOrAd(sessionPositionInfo, this.playerInfo.sessionPositionInfo)) {
            this.playerInfo = this.playerInfo.copyWithSessionPositionInfo(sessionPositionInfo);
        }
    }

    private boolean isPlayerCommandAvailable(int i) {
        if (this.intersectedPlayerCommands.contains(i)) {
            return true;
        }
        Log.w(TAG, "Controller isn't allowed to call command= " + i);
        return false;
    }

    private PlayerInfo maskPositionInfo(PlayerInfo playerInfo, Timeline timeline, PeriodInfo periodInfo) {
        int i = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
        int i2 = periodInfo.index;
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period);
        Timeline.Period period2 = new Timeline.Period();
        timeline.getPeriod(i2, period2);
        boolean z = i != i2;
        long j = periodInfo.periodPositionUs;
        long jMsToUs = Util.msToUs(getCurrentPosition()) - period.getPositionInWindowUs();
        if (!z && j == jMsToUs) {
            return playerInfo;
        }
        Preconditions.checkState(playerInfo.sessionPositionInfo.positionInfo.adGroupIndex == -1);
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, period.windowIndex, playerInfo.sessionPositionInfo.positionInfo.mediaItem, null, i, Util.usToMs(period.positionInWindowUs + jMsToUs), Util.usToMs(period.positionInWindowUs + jMsToUs), -1, -1);
        timeline.getPeriod(i2, period2);
        Timeline.Window window = new Timeline.Window();
        timeline.getWindow(period2.windowIndex, window);
        long jUsToMs = Util.usToMs(period2.positionInWindowUs + j);
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(null, period2.windowIndex, window.mediaItem, null, i2, jUsToMs, jUsToMs, -1, -1);
        PlayerInfo playerInfoCopyWithPositionInfos = playerInfo.copyWithPositionInfos(positionInfo, positionInfo2, 1);
        if (z || j < jMsToUs) {
            return playerInfoCopyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), jUsToMs, MediaUtils.calculateBufferedPercentage(jUsToMs, window.getDurationMs()), 0L, C.TIME_UNSET, C.TIME_UNSET, jUsToMs));
        }
        long jMax = Math.max(0L, Util.msToUs(playerInfoCopyWithPositionInfos.sessionPositionInfo.totalBufferedDurationMs) - (j - jMsToUs));
        long jUsToMs2 = Util.usToMs(period2.positionInWindowUs + j + jMax);
        return playerInfoCopyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), jUsToMs2, MediaUtils.calculateBufferedPercentage(jUsToMs2, window.getDurationMs()), Util.usToMs(jMax), C.TIME_UNSET, C.TIME_UNSET, jUsToMs2));
    }

    private PeriodInfo getPeriodInfo(Timeline timeline, int i, long j) {
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        if (i == -1 || i >= timeline.getWindowCount()) {
            i = timeline.getFirstWindowIndex(getShuffleModeEnabled());
            j = timeline.getWindow(i, window).getDefaultPositionMs();
        }
        return getPeriodInfo(timeline, window, period, i, Util.msToUs(j));
    }

    private static PeriodInfo getPeriodInfo(Timeline timeline, Timeline.Window window, Timeline.Period period, int i, long j) {
        Preconditions.checkElementIndex(i, timeline.getWindowCount());
        timeline.getWindow(i, window);
        if (j == C.TIME_UNSET) {
            j = window.getDefaultPositionUs();
            if (j == C.TIME_UNSET) {
                return null;
            }
        }
        int i2 = window.firstPeriodIndex;
        timeline.getPeriod(i2, period);
        while (i2 < window.lastPeriodIndex && period.positionInWindowUs != j) {
            int i3 = i2 + 1;
            if (timeline.getPeriod(i3, period).positionInWindowUs > j) {
                break;
            }
            i2 = i3;
        }
        timeline.getPeriod(i2, period);
        return new PeriodInfo(i2, j - period.positionInWindowUs);
    }

    private static int getCurrentMediaItemIndexInternal(PlayerInfo playerInfo) {
        return playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, int i, int i2, long j, long j2, int i3) {
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, i, timeline.getWindow(i, new Timeline.Window()).mediaItem, null, i2, j, j2, playerInfo.sessionPositionInfo.positionInfo.adGroupIndex, playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup);
        return maskTimelineAndPositionInfo(playerInfo, timeline, positionInfo, new SessionPositionInfo(positionInfo, playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), playerInfo.sessionPositionInfo.durationMs, playerInfo.sessionPositionInfo.bufferedPositionMs, playerInfo.sessionPositionInfo.bufferedPercentage, playerInfo.sessionPositionInfo.totalBufferedDurationMs, playerInfo.sessionPositionInfo.currentLiveOffsetMs, playerInfo.sessionPositionInfo.contentDurationMs, playerInfo.sessionPositionInfo.contentBufferedPositionMs), i3);
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, Player.PositionInfo positionInfo, SessionPositionInfo sessionPositionInfo, int i) {
        return new PlayerInfo.Builder(playerInfo).setTimeline(timeline).setOldPositionInfo(playerInfo.sessionPositionInfo.positionInfo).setNewPositionInfo(positionInfo).setSessionPositionInfo(sessionPositionInfo).setDiscontinuityReason(i).build();
    }

    private static Timeline.Period getPeriodWithNewWindowIndex(Timeline timeline, int i, int i2) {
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period);
        period.windowIndex = i2;
        return period;
    }

    private static int getNewPeriodIndexWithoutRemovedPeriods(Timeline timeline, int i, int i2, int i3) {
        if (i == -1) {
            return i;
        }
        while (i2 < i3) {
            Timeline.Window window = new Timeline.Window();
            timeline.getWindow(i2, window);
            i -= (window.lastPeriodIndex - window.firstPeriodIndex) + 1;
            i2++;
        }
        return i;
    }

    private static Timeline.Window createNewWindow(MediaItem mediaItem) {
        return new Timeline.Window().set(0, mediaItem, null, 0L, 0L, 0L, true, false, null, 0L, C.TIME_UNSET, -1, -1, 0L);
    }

    private static Timeline.Period createNewPeriod(int i) {
        return new Timeline.Period().set(null, null, i, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
    }

    private static void rebuildPeriods(Timeline timeline, List<Timeline.Window> list, List<Timeline.Period> list2) {
        for (int i = 0; i < list.size(); i++) {
            Timeline.Window window = list.get(i);
            int i2 = window.firstPeriodIndex;
            int i3 = window.lastPeriodIndex;
            if (i2 == -1 || i3 == -1) {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size();
                list2.add(createNewPeriod(i));
            } else {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size() + (i3 - i2);
                while (i2 <= i3) {
                    list2.add(getPeriodWithNewWindowIndex(timeline, i2, i));
                    i2++;
                }
            }
        }
    }

    private static int resolveSubsequentMediaItemIndex(int i, boolean z, int i2, Timeline timeline, int i3, int i4) {
        int windowCount = timeline.getWindowCount();
        for (int i5 = 0; i5 < windowCount && (i2 = timeline.getNextWindowIndex(i2, i, z)) != -1; i5++) {
            if (i2 < i3 || i2 >= i4) {
                return i2;
            }
        }
        return -1;
    }

    private static ImmutableList<CommandButton> resolveMediaButtonPreferences(List<CommandButton> list, List<CommandButton> list2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            list = CommandButton.getMediaButtonPreferencesFromCustomLayout(list2, commands, bundle);
        }
        return CommandButton.copyWithUnavailableButtonsDisabled(list, sessionCommands, commands);
    }

    private static ImmutableList<CommandButton> resolveCustomLayout(List<CommandButton> list, List<CommandButton> list2, Bundle bundle, SessionCommands sessionCommands, Player.Commands commands) {
        if (!list2.isEmpty()) {
            return CommandButton.copyWithUnavailableButtonsDisabled(list2, sessionCommands, commands);
        }
        return CommandButton.getCustomLayoutFromMediaButtonPreferences(list, (bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") || commands.containsAny(6, 7)) ? false : true, (bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") || commands.containsAny(8, 9)) ? false : true);
    }

    private static Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable(Player.Commands commands, Player.Commands commands2) {
        Player.Commands commandsIntersect = MediaUtils.intersect(commands, commands2);
        return commandsIntersect.contains(32) ? commandsIntersect : commandsIntersect.buildUpon().add(32).build();
    }

    private class SessionServiceConnection implements ServiceConnection {
        private final Bundle connectionHints;

        public SessionServiceConnection(Bundle bundle) {
            this.connectionHints = bundle;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaController mediaControllerImplBase;
            MediaControllerImplBase$$ExternalSyntheticLambda48 mediaControllerImplBase$$ExternalSyntheticLambda48;
            try {
                try {
                    if (MediaControllerImplBase.this.token.getPackageName().equals(componentName.getPackageName())) {
                        IMediaSessionService iMediaSessionServiceAsInterface = IMediaSessionService.Stub.asInterface(iBinder);
                        if (iMediaSessionServiceAsInterface != null) {
                            iMediaSessionServiceAsInterface.connect(MediaControllerImplBase.this.controllerStub, new ConnectionRequest(MediaControllerImplBase.this.getContext().getPackageName(), Process.myPid(), this.connectionHints, MediaControllerImplBase.this.instance.getMaxCommandsForMediaItems()).toBundle());
                            return;
                        } else {
                            Log.e(MediaControllerImplBase.TAG, "Service interface is missing.");
                            mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
                            Objects.requireNonNull(mediaControllerImplBase2);
                            mediaControllerImplBase$$ExternalSyntheticLambda48 = new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase2);
                        }
                    } else {
                        Log.e(MediaControllerImplBase.TAG, "Expected connection to " + MediaControllerImplBase.this.token.getPackageName() + " but is connected to " + componentName);
                        mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                        MediaController mediaControllerImplBase3 = MediaControllerImplBase.this.getInstance();
                        Objects.requireNonNull(mediaControllerImplBase3);
                        mediaControllerImplBase$$ExternalSyntheticLambda48 = new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase3);
                    }
                } catch (RemoteException unused) {
                    Log.w(MediaControllerImplBase.TAG, "Service " + componentName + " has died prematurely");
                    mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                    MediaController mediaControllerImplBase4 = MediaControllerImplBase.this.getInstance();
                    Objects.requireNonNull(mediaControllerImplBase4);
                    mediaControllerImplBase$$ExternalSyntheticLambda48 = new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase4);
                }
                mediaControllerImplBase.runOnApplicationLooper(mediaControllerImplBase$$ExternalSyntheticLambda48);
            } catch (Throwable th) {
                MediaController mediaControllerImplBase5 = MediaControllerImplBase.this.getInstance();
                MediaController mediaControllerImplBase6 = MediaControllerImplBase.this.getInstance();
                Objects.requireNonNull(mediaControllerImplBase6);
                mediaControllerImplBase5.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase6));
                throw th;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase2));
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda48(mediaControllerImplBase2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SurfaceCallback implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {
        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private SurfaceCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = surfaceHolder.getSurface();
            Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
            MediaControllerImplBase mediaControllerImplBase = MediaControllerImplBase.this;
            mediaControllerImplBase.setVideoSurfaceWithSize(mediaControllerImplBase.videoSurface, surfaceFrame.width(), surfaceFrame.height());
            MediaControllerImplBase.this.onSurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, final int i2, final int i3) {
            if (MediaControllerImplBase.this.videoSurfaceHolder == surfaceHolder && MediaControllerImplBase.this.isConnected()) {
                if (((SessionToken) Preconditions.checkNotNull(MediaControllerImplBase.this.connectedToken)).getInterfaceVersion() >= 8) {
                    MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda0
                        @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                        public final void run(IMediaSession iMediaSession, int i4) throws RemoteException {
                            this.f$0.m7733x734eb4da(i2, i3, iMediaSession, i4);
                        }
                    });
                }
                MediaControllerImplBase.this.onSurfaceSizeChanged(i2, i3);
            }
        }

        /* JADX INFO: renamed from: lambda$surfaceChanged$0$androidx-media3-session-MediaControllerImplBase$SurfaceCallback, reason: not valid java name */
        /* synthetic */ void m7733x734eb4da(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
            iMediaSession.onSurfaceSizeChanged(MediaControllerImplBase.this.controllerStub, i3, i, i2);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = null;
            MediaControllerImplBase.this.setVideoSurfaceWithSize(null, 0, 0);
            MediaControllerImplBase.this.onSurfaceSizeChanged(0, 0);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (MediaControllerImplBase.this.videoTextureView == null || MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() != surfaceTexture) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = new Surface(surfaceTexture);
            MediaControllerImplBase mediaControllerImplBase = MediaControllerImplBase.this;
            mediaControllerImplBase.setVideoSurfaceWithSize(mediaControllerImplBase.videoSurface, i, i2);
            MediaControllerImplBase.this.onSurfaceSizeChanged(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int i, final int i2) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture && MediaControllerImplBase.this.isConnected()) {
                if (((SessionToken) Preconditions.checkNotNull(MediaControllerImplBase.this.connectedToken)).getInterfaceVersion() >= 8) {
                    MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new RemoteSessionTask() { // from class: androidx.media3.session.MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda1
                        @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                        public final void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                            this.f$0.m7732xa1f1d240(i, i2, iMediaSession, i3);
                        }
                    });
                }
                MediaControllerImplBase.this.onSurfaceSizeChanged(i, i2);
            }
        }

        /* JADX INFO: renamed from: lambda$onSurfaceTextureSizeChanged$1$androidx-media3-session-MediaControllerImplBase$SurfaceCallback, reason: not valid java name */
        /* synthetic */ void m7732xa1f1d240(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
            iMediaSession.onSurfaceSizeChanged(MediaControllerImplBase.this.controllerStub, i3, i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                MediaControllerImplBase.this.videoSurface = null;
                MediaControllerImplBase.this.setVideoSurfaceWithSize(null, 0, 0);
                MediaControllerImplBase.this.onSurfaceSizeChanged(0, 0);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class FlushCommandQueueHandler {
        private static final int MSG_FLUSH_COMMAND_QUEUE = 1;
        private final Handler handler;

        public FlushCommandQueueHandler(Looper looper) {
            this.handler = new Handler(looper, new Handler.Callback() { // from class: androidx.media3.session.MediaControllerImplBase$FlushCommandQueueHandler$$ExternalSyntheticLambda0
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return this.f$0.handleMessage(message);
                }
            });
        }

        public void sendFlushCommandQueueMessage() {
            if (MediaControllerImplBase.this.iSession == null || this.handler.hasMessages(1)) {
                return;
            }
            this.handler.sendEmptyMessage(1);
        }

        public void release() {
            if (this.handler.hasMessages(1)) {
                flushCommandQueue();
            }
            this.handler.removeCallbacksAndMessages(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                flushCommandQueue();
            }
            return true;
        }

        private void flushCommandQueue() {
            try {
                MediaControllerImplBase.this.iSession.flushCommandQueue(MediaControllerImplBase.this.controllerStub);
            } catch (RemoteException unused) {
                Log.w(MediaControllerImplBase.TAG, "Error in sending flushCommandQueue");
            }
        }
    }

    private static final class PeriodInfo {
        private final int index;
        private final long periodPositionUs;

        static /* synthetic */ int access$100(PeriodInfo periodInfo) {
            return periodInfo.index;
        }

        static /* synthetic */ long access$200(PeriodInfo periodInfo) {
            return periodInfo.periodPositionUs;
        }

        public PeriodInfo(int i, long j) {
            this.index = i;
            this.periodPositionUs = j;
        }
    }
}

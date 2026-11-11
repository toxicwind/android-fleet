package androidx.media3.session;

import android.app.PendingIntent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
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
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
final class MediaSessionStub extends IMediaSession.Stub {
    private static final String TAG = "MediaSessionStub";
    public static final int UNKNOWN_SEQUENCE_NUMBER = Integer.MIN_VALUE;
    public static final int VERSION_INT = 8;
    private final ConnectedControllersManager<IBinder> connectedControllersManager;
    private int nextUniqueTrackGroupIdPrefix;
    private final WeakReference<MediaSessionImpl> sessionImpl;
    private SurfaceHolderWithSize surfaceHolderWithSize;
    private final Set<MediaSession.ControllerInfo> pendingControllers = DesugarCollections.synchronizedSet(new HashSet());
    private ImmutableBiMap<TrackGroup, String> trackGroupIdMap = ImmutableBiMap.of();

    /* JADX INFO: Access modifiers changed from: private */
    interface ControllerPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface MediaItemPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List<MediaItem> list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface MediaItemsWithStartPositionPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface SessionTask<T, K extends MediaSessionImpl> {
        T run(K k, MediaSession.ControllerInfo controllerInfo, int i);
    }

    public MediaSessionStub(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    private static void sendSessionResult(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i, SessionResult sessionResult) {
        try {
            ((MediaSession.ControllerCb) Preconditions.checkNotNull(controllerInfo.getControllerCb())).onSessionResult(i, sessionResult);
            mediaSessionImpl.triggerPlayerInfoUpdate();
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to controller " + controllerInfo, e);
        }
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(final Consumer<PlayerWrapper> consumer) {
        return sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda74
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                consumer.accept(playerWrapper);
            }
        });
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(final ControllerPlayerTask controllerPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda88
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$sendSessionResultSuccess$1(controllerPlayerTask, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    static /* synthetic */ ListenableFuture lambda$sendSessionResultSuccess$1(ControllerPlayerTask controllerPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        controllerPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo);
        sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(0));
        return Futures.immediateVoidFuture();
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultWhenReady(final SessionTask<ListenableFuture<SessionResult>, K> sessionTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda76
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.handleSessionTaskWhenReady(mediaSessionImpl, controllerInfo, i, sessionTask, new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda83
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaSessionStub.lambda$sendSessionResultWhenReady$2(mediaSessionImpl, controllerInfo, i, (ListenableFuture) obj);
                    }
                });
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0023  */
    /* JADX WARN: Code duplicated, block: B:11:0x0025  */
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$sendSessionResultWhenReady$2(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i, ListenableFuture listenableFuture) {
        SessionResult sessionResult;
        int i2;
        try {
            sessionResult = (SessionResult) Preconditions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Session operation failed", e);
            if (e.getCause() instanceof UnsupportedOperationException) {
                i2 = -6;
            } else {
                i2 = -1;
            }
            sessionResult = new SessionResult(i2);
        } catch (CancellationException e2) {
            Log.w(TAG, "Session operation cancelled", e2);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Session operation failed", e);
            if (e.getCause() instanceof UnsupportedOperationException) {
                i2 = -6;
            } else {
                i2 = -1;
            }
            sessionResult = new SessionResult(i2);
        }
        sendSessionResult(mediaSessionImpl, controllerInfo, i, sessionResult);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWhenReady(final SessionTask<ListenableFuture<List<MediaItem>>, K> sessionTask, final MediaItemPlayerTask mediaItemPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda87
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$handleMediaItemsWhenReady$6(sessionTask, mediaItemPlayerTask, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    static /* synthetic */ ListenableFuture lambda$handleMediaItemsWhenReady$6(SessionTask sessionTask, final MediaItemPlayerTask mediaItemPlayerTask, final MediaSessionImpl mediaSessionImpl, final MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new AsyncFunction() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda26
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                MediaSessionImpl mediaSessionImpl2 = mediaSessionImpl;
                MediaSession.ControllerInfo controllerInfo2 = controllerInfo;
                return Util.postOrRunWithCompletion(mediaSessionImpl2.getApplicationHandler(), mediaSessionImpl2.callWithControllerForCurrentRequestSet(controllerInfo2, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda84
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.lambda$handleMediaItemsWhenReady$4(mediaSessionImpl2, mediaItemPlayerTask, controllerInfo2, list);
                    }
                }), new SessionResult(0));
            }
        });
    }

    static /* synthetic */ void lambda$handleMediaItemsWhenReady$4(MediaSessionImpl mediaSessionImpl, MediaItemPlayerTask mediaItemPlayerTask, MediaSession.ControllerInfo controllerInfo, List list) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo, list);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWithStartPositionWhenReady(final SessionTask<ListenableFuture<MediaSession.MediaItemsWithStartPosition>, K> sessionTask, final MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda70
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$9(sessionTask, mediaItemsWithStartPositionPlayerTask, mediaSessionImpl, controllerInfo, i);
            }
        };
    }

    static /* synthetic */ ListenableFuture lambda$handleMediaItemsWithStartPositionWhenReady$9(SessionTask sessionTask, final MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, final MediaSessionImpl mediaSessionImpl, final MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new AsyncFunction() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda14
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                MediaSessionImpl mediaSessionImpl2 = mediaSessionImpl;
                return Util.postOrRunWithCompletion(mediaSessionImpl2.getApplicationHandler(), mediaSessionImpl2.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda42
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$7(mediaSessionImpl2, mediaItemsWithStartPositionPlayerTask, mediaItemsWithStartPosition);
                    }
                }), new SessionResult(0));
            }
        });
    }

    static /* synthetic */ void lambda$handleMediaItemsWithStartPositionWhenReady$7(MediaSessionImpl mediaSessionImpl, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemsWithStartPositionPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), mediaItemsWithStartPosition);
    }

    private static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i, LibraryResult<?> libraryResult) {
        try {
            ((MediaSession.ControllerCb) Preconditions.checkNotNull(controllerInfo.getControllerCb())).onLibraryResult(i, libraryResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to browser " + controllerInfo, e);
        }
    }

    private static <V, K extends MediaLibrarySessionImpl> SessionTask<ListenableFuture<Void>, K> sendLibraryResultWhenReady(final SessionTask<ListenableFuture<LibraryResult<V>>, K> sessionTask) {
        return new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda75
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
                return MediaSessionStub.handleSessionTaskWhenReady((MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i, sessionTask, new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda11
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        MediaSessionStub.lambda$sendLibraryResultWhenReady$10(controllerInfo, i, (ListenableFuture) obj);
                    }
                });
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$sendLibraryResultWhenReady$10(MediaSession.ControllerInfo controllerInfo, int i, ListenableFuture listenableFuture) {
        LibraryResult libraryResultOfError;
        try {
            libraryResultOfError = (LibraryResult) Preconditions.checkNotNull((LibraryResult) listenableFuture.get(), "LibraryResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Library operation failed", e);
            libraryResultOfError = LibraryResult.ofError(-1);
        } catch (CancellationException e2) {
            Log.w(TAG, "Library operation cancelled", e2);
            libraryResultOfError = LibraryResult.ofError(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Library operation failed", e);
            libraryResultOfError = LibraryResult.ofError(-1);
        }
        sendLibraryResult(controllerInfo, i, libraryResultOfError);
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
        if (controller != null) {
            queueSessionTaskWithPlayerCommandForControllerInfo(controller, i, i2, sessionTask);
        }
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommandForControllerInfo(final MediaSession.ControllerInfo controllerInfo, final int i, final int i2, final SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda57
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m7839x89a6a664(controllerInfo, i2, mediaSessionImpl, i, sessionTask);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7839x89a6a664(final MediaSession.ControllerInfo controllerInfo, int i, final MediaSessionImpl mediaSessionImpl, final int i2, final SessionTask sessionTask) {
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, i)) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i2, new SessionResult(-4));
            return;
        }
        int iOnPlayerCommandRequestOnHandler = mediaSessionImpl.onPlayerCommandRequestOnHandler(controllerInfo, i);
        if (iOnPlayerCommandRequestOnHandler != 0) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i2, new SessionResult(iOnPlayerCommandRequestOnHandler));
        } else if (i == 27) {
            mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    sessionTask.run(mediaSessionImpl, controllerInfo, i2);
                }
            }).run();
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new ConnectedControllersManager.AsyncCommand() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda21
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final ListenableFuture run() {
                    return Futures.immediateVoidFuture();
                }
            });
        } else {
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new ConnectedControllersManager.AsyncCommand() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda23
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final ListenableFuture run() {
                    return MediaSessionStub.lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(sessionTask, mediaSessionImpl, controllerInfo, i2);
                }
            });
        }
    }

    static /* synthetic */ ListenableFuture lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return (ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, null, i2, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, SessionCommand sessionCommand, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, sessionCommand, 0, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, final int i, final SessionCommand sessionCommand, final int i2, final SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller == null) {
                    return;
                }
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda54
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m7835xc8132c6c(controller, sessionCommand, mediaSessionImpl, i, i2, sessionTask);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: lambda$dispatchSessionTaskWithSessionCommand$15$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7835xc8132c6c(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, MediaSessionImpl mediaSessionImpl, int i, int i2, SessionTask sessionTask) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            ConnectedControllersManager<IBinder> connectedControllersManager = this.connectedControllersManager;
            if (sessionCommand != null) {
                if (!connectedControllersManager.isSessionCommandAvailable(controllerInfo, sessionCommand)) {
                    sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-4));
                    return;
                }
            } else if (!connectedControllersManager.isSessionCommandAvailable(controllerInfo, i2)) {
                sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-4));
                return;
            }
            sessionTask.run(mediaSessionImpl, controllerInfo, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T, K extends MediaSessionImpl> ListenableFuture<Void> handleSessionTaskWhenReady(final K k, MediaSession.ControllerInfo controllerInfo, int i, SessionTask<ListenableFuture<T>, K> sessionTask, final Consumer<ListenableFuture<T>> consumer) {
        if (k.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        final ListenableFuture<T> listenableFutureRun = sessionTask.run(k, controllerInfo, i);
        final SettableFuture settableFutureCreate = SettableFuture.create();
        listenableFutureRun.addListener(new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                MediaSessionStub.lambda$handleSessionTaskWhenReady$16(k, settableFutureCreate, consumer, listenableFutureRun);
            }
        }, MoreExecutors.directExecutor());
        return settableFutureCreate;
    }

    static /* synthetic */ void lambda$handleSessionTaskWhenReady$16(MediaSessionImpl mediaSessionImpl, SettableFuture settableFuture, Consumer consumer, ListenableFuture listenableFuture) {
        if (mediaSessionImpl.isReleased()) {
            settableFuture.set(null);
            return;
        }
        try {
            consumer.accept(listenableFuture);
            settableFuture.set(null);
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    private int maybeCorrectMediaItemIndex(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper, int i) {
        return (playerWrapper.isCommandAvailable(17) && !this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17) && this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16)) ? i + playerWrapper.getCurrentMediaItemIndex() : i;
    }

    public void connect(final IMediaController iMediaController, final MediaSession.ControllerInfo controllerInfo) {
        if (iMediaController == null || controllerInfo == null) {
            SessionUtil.disconnectIMediaController(iMediaController);
            return;
        }
        final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            SessionUtil.disconnectIMediaController(iMediaController);
        } else {
            this.pendingControllers.add(controllerInfo);
            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda44
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f$0.m7833lambda$connect$17$androidxmedia3sessionMediaSessionStub(controllerInfo, mediaSessionImpl, iMediaController);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$connect$17$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7833lambda$connect$17$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo, MediaSessionImpl mediaSessionImpl, IMediaController iMediaController) throws Throwable {
        IMediaController iMediaController2;
        Player.Commands commands;
        boolean z = false;
        try {
            this.pendingControllers.remove(controllerInfo);
            if (mediaSessionImpl.isReleased()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            IBinder callbackBinder = ((Controller2Cb) Preconditions.checkNotNull((Controller2Cb) controllerInfo.getControllerCb())).getCallbackBinder();
            MediaSession.ConnectionResult connectionResultOnConnectOnHandler = mediaSessionImpl.onConnectOnHandler(controllerInfo);
            if (!connectionResultOnConnectOnHandler.isAccepted && !controllerInfo.isTrusted()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            if (!connectionResultOnConnectOnHandler.isAccepted) {
                connectionResultOnConnectOnHandler = MediaSession.ConnectionResult.accept(SessionCommands.EMPTY, Player.Commands.EMPTY);
            }
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                Log.w(TAG, "Controller " + controllerInfo + " has sent connection request multiple times");
            }
            this.connectedControllersManager.addController(callbackBinder, controllerInfo, connectionResultOnConnectOnHandler.availableSessionCommands, connectionResultOnConnectOnHandler.availablePlayerCommands);
            SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager == null) {
                Log.w(TAG, "Ignoring connection request from unknown controller info");
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            PlayerWrapper playerWrapper = mediaSessionImpl.getPlayerWrapper();
            PlayerInfo playerInfo = mediaSessionImpl.getPlayerInfo();
            PlaybackException playbackException = mediaSessionImpl.getPlaybackException();
            if (playbackException == null) {
                commands = connectionResultOnConnectOnHandler.availablePlayerCommands;
            } else {
                this.connectedControllersManager.setPlaybackException(controllerInfo, playbackException, connectionResultOnConnectOnHandler.availablePlayerCommands);
                playerInfo = MediaSessionImpl.createPlayerInfoForCustomPlaybackException(playerInfo, playbackException);
                commands = (Player.Commands) Preconditions.checkNotNull(MediaSessionImpl.createPlayerCommandsForCustomErrorState(connectionResultOnConnectOnHandler.availablePlayerCommands));
            }
            iMediaController2 = iMediaController;
            try {
                ConnectionState connectionState = new ConnectionState(MediaLibraryInfo.VERSION_INT, 8, this, connectionResultOnConnectOnHandler.sessionActivity != null ? connectionResultOnConnectOnHandler.sessionActivity : mediaSessionImpl.getSessionActivity(), connectionResultOnConnectOnHandler.customLayout != null ? connectionResultOnConnectOnHandler.customLayout : mediaSessionImpl.getCustomLayout(), connectionResultOnConnectOnHandler.mediaButtonPreferences != null ? connectionResultOnConnectOnHandler.mediaButtonPreferences : mediaSessionImpl.getMediaButtonPreferences(), mediaSessionImpl.getCommandButtonsForMediaItems(), connectionResultOnConnectOnHandler.availableSessionCommands, commands, playerWrapper.getAvailableCommands(), mediaSessionImpl.getToken().getExtras(), connectionResultOnConnectOnHandler.sessionExtras != null ? connectionResultOnConnectOnHandler.sessionExtras : mediaSessionImpl.getSessionExtras(), generateAndCacheUniqueTrackGroupIds(playerInfo), mediaSessionImpl.getPlatformToken());
                if (mediaSessionImpl.isReleased()) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                    return;
                }
                try {
                    iMediaController2.onConnected(sequencedFutureManager.obtainNextSequenceNumber(), iMediaController2 instanceof MediaControllerStub ? connectionState.toBundleInProcess() : connectionState.toBundleForRemoteProcess(controllerInfo.getInterfaceVersion()));
                    z = true;
                } catch (RemoteException unused) {
                }
                if (z) {
                    mediaSessionImpl.onPostConnectOnHandler(controllerInfo);
                }
                if (z) {
                    return;
                }
                SessionUtil.disconnectIMediaController(iMediaController2);
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iMediaController2 = iMediaController;
        }
    }

    public void release() {
        for (MediaSession.ControllerInfo controllerInfo : this.connectedControllersManager.getConnectedControllers()) {
            this.connectedControllersManager.removeController(controllerInfo);
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                controllerCb.onDisconnected(0);
            }
        }
        Iterator<MediaSession.ControllerInfo> it = this.pendingControllers.iterator();
        while (it.hasNext()) {
            MediaSession.ControllerCb controllerCb2 = it.next().getControllerCb();
            if (controllerCb2 != null) {
                controllerCb2.onDisconnected(0);
            }
        }
        this.pendingControllers.clear();
        this.sessionImpl.clear();
    }

    @Override // androidx.media3.session.IMediaSession
    public void connect(IMediaController iMediaController, int i, Bundle bundle) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (iMediaController == null || bundle == null || mediaSessionImpl == null) {
            SessionUtil.disconnectIMediaController(iMediaController);
            return;
        }
        try {
            ConnectionRequest connectionRequestFromBundle = ConnectionRequest.fromBundle(bundle);
            int callingUid = Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            String str = connectionRequestFromBundle.packageName;
            int iCheckPackageValidity = SessionUtil.checkPackageValidity(mediaSessionImpl.getContext(), str, callingUid);
            boolean z = true;
            if (iCheckPackageValidity == 1) {
                Log.w(TAG, "Ignoring connection from invalid package name " + str + " (uid=" + callingUid + ")");
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            if (callingPid == 0) {
                callingPid = connectionRequestFromBundle.pid;
            }
            try {
                MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(str, callingPid, callingUid);
                boolean zIsTrustedForMediaControl = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext()).isTrustedForMediaControl(remoteUserInfo);
                int i2 = connectionRequestFromBundle.libraryVersion;
                int i3 = connectionRequestFromBundle.controllerInterfaceVersion;
                Controller2Cb controller2Cb = new Controller2Cb(iMediaController, connectionRequestFromBundle.controllerInterfaceVersion);
                Bundle bundle2 = connectionRequestFromBundle.connectionHints;
                int i4 = connectionRequestFromBundle.maxCommandsForMediaItems;
                if (iCheckPackageValidity != 0) {
                    z = false;
                }
                connect(iMediaController, new MediaSession.ControllerInfo(remoteUserInfo, i2, i3, zIsTrustedForMediaControl, controller2Cb, bundle2, i4, z));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for ConnectionRequest", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void stop(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        stopForControllerInfo(controller, i);
    }

    public void stopForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 3, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda68
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).stop();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void release(final IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m7840lambda$release$18$androidxmedia3sessionMediaSessionStub(iMediaController);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: lambda$release$18$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7840lambda$release$18$androidxmedia3sessionMediaSessionStub(IMediaController iMediaController) {
        this.connectedControllersManager.removeController(iMediaController.asBinder());
    }

    @Override // androidx.media3.session.IMediaSession
    public void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            SessionResult sessionResultFromBundle = SessionResult.fromBundle(bundle);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(iMediaController.asBinder());
                if (sequencedFutureManager == null) {
                    return;
                }
                sequencedFutureManager.setFutureResult(i, sessionResultFromBundle);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void play(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        playForControllerInfo(controller, i);
    }

    public void playForControllerInfo(final MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda40
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7838x862bd0ab(controllerInfo, (PlayerWrapper) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$playForControllerInfo$19$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7838x862bd0ab(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            return;
        }
        mediaSessionImpl.handleMediaControllerPlayRequest(controllerInfo, false);
    }

    @Override // androidx.media3.session.IMediaSession
    public void pause(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        pauseForControllerInfo(controller, i);
    }

    public void pauseForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda18
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).pause();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void prepare(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 2, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda73
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).prepare();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPosition(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 4, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda66
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToDefaultPosition();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda12
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                this.f$0.m7845x6c0a2b2d(i2, playerWrapper, controllerInfo);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$seekToDefaultPositionWithMediaItemIndex$21$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7845x6c0a2b2d(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekToDefaultPosition(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekTo(IMediaController iMediaController, int i, final long j) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 5, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda89
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekTo(j);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToWithMediaItemIndex(IMediaController iMediaController, int i, final int i2, final long j) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda16
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                this.f$0.m7846x442a0fa1(i2, j, playerWrapper, controllerInfo);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$seekToWithMediaItemIndex$23$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7846x442a0fa1(int i, long j, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekTo(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), j);
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekBack(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekBackForControllerInfo(controller, i);
    }

    public void seekBackForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 11, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda34
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekBack();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekForward(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekForwardForControllerInfo(controller, i);
    }

    public void seekForwardForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 12, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda51
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekForward();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2) {
        onCustomCommandWithProgressUpdate(iMediaController, i, bundle, bundle2, false);
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommandWithProgressUpdate(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2, final boolean z) {
        final Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle2);
        if (iMediaController == null || bundle == null || bundleConvertToNullIfInvalid == null) {
            return;
        }
        try {
            final SessionCommand sessionCommandFromBundle = SessionCommand.fromBundle(bundle);
            if (CommandButton.isPredefinedCustomCommandButtonCode(sessionCommandFromBundle.customAction)) {
                dispatchCustomCommandAsPredefinedCommand(iMediaController, i, sessionCommandFromBundle);
            } else {
                dispatchSessionTaskWithSessionCommand(iMediaController, i, sessionCommandFromBundle, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda29
                    @Override // androidx.media3.session.MediaSessionStub.SessionTask
                    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                        return MediaSessionStub.lambda$onCustomCommandWithProgressUpdate$24(z, sessionCommandFromBundle, bundleConvertToNullIfInvalid, mediaSessionImpl, controllerInfo, i2);
                    }
                }));
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e);
        }
    }

    static /* synthetic */ ListenableFuture lambda$onCustomCommandWithProgressUpdate$24(boolean z, SessionCommand sessionCommand, Bundle bundle, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        Bundle bundle2;
        MediaSession.ControllerInfo controllerInfo2;
        SessionCommand sessionCommand2;
        MediaSessionImpl mediaSessionImpl2;
        ProgressReporter progressReporter;
        if (z) {
            sessionCommand2 = sessionCommand;
            mediaSessionImpl2 = mediaSessionImpl;
            bundle2 = bundle;
            controllerInfo2 = controllerInfo;
            progressReporter = new ProgressReporter(mediaSessionImpl2, controllerInfo2, i, sessionCommand2, bundle2);
        } else {
            bundle2 = bundle;
            controllerInfo2 = controllerInfo;
            sessionCommand2 = sessionCommand;
            mediaSessionImpl2 = mediaSessionImpl;
            progressReporter = null;
        }
        ListenableFuture<SessionResult> listenableFutureOnCustomCommandOnHandler = mediaSessionImpl2.onCustomCommandOnHandler(controllerInfo2, progressReporter, sessionCommand2, bundle2);
        if (progressReporter != null) {
            progressReporter.setFuture(listenableFutureOnCustomCommandOnHandler);
        }
        return listenableFutureOnCustomCommandOnHandler;
    }

    private void dispatchCustomCommandAsPredefinedCommand(final IMediaController iMediaController, final int i, final SessionCommand sessionCommand) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller == null) {
                    return;
                }
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda85
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m7834x7c297abc(controller, sessionCommand, mediaSessionImpl, i, iMediaController);
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: lambda$dispatchCustomCommandAsPredefinedCommand$27$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7834x7c297abc(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, MediaSessionImpl mediaSessionImpl, int i, IMediaController iMediaController) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            try {
                final CommandButton commandButtonConvertFromPredefinedCustomCommand = CommandButton.convertFromPredefinedCustomCommand(sessionCommand);
                if (!commandButtonConvertFromPredefinedCustomCommand.canExecuteAction()) {
                    Log.w(TAG, "Can't execute predefined custom command: " + sessionCommand.customAction);
                    sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-6));
                    return;
                }
                if (commandButtonConvertFromPredefinedCustomCommand.sessionCommand != null) {
                    Preconditions.checkState(commandButtonConvertFromPredefinedCustomCommand.sessionCommand.commandCode == 40010);
                    dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda49
                        @Override // androidx.media3.session.MediaSessionStub.SessionTask
                        public final Object run(MediaSessionImpl mediaSessionImpl2, MediaSession.ControllerInfo controllerInfo2, int i2) {
                            return mediaSessionImpl2.onSetRatingOnHandler(controllerInfo2, (Rating) Preconditions.checkNotNull(commandButtonConvertFromPredefinedCustomCommand.parameter));
                        }
                    }));
                    return;
                }
                if (commandButtonConvertFromPredefinedCustomCommand.isPlayRequestPlayerAction(mediaSessionImpl.getPlayerWrapper())) {
                    playForControllerInfo(controllerInfo, i);
                } else if (commandButtonConvertFromPredefinedCustomCommand.playerCommand == 31) {
                    setMediaItemItemWithResetPositionForControllerInfo(controllerInfo, i, (MediaItem) Preconditions.checkNotNull(commandButtonConvertFromPredefinedCustomCommand.parameter), true);
                } else {
                    queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, commandButtonConvertFromPredefinedCustomCommand.playerCommand, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda50
                        @Override // androidx.media3.common.util.Consumer
                        public final void accept(Object obj) {
                            commandButtonConvertFromPredefinedCustomCommand.executePlayerAction((PlayerWrapper) obj);
                        }
                    }));
                }
                this.connectedControllersManager.flushCommandQueue(controllerInfo);
            } catch (RuntimeException e) {
                Log.w(TAG, "Failed to convert predefined custom command: " + sessionCommand.customAction, e);
                sendSessionResult(mediaSessionImpl, controllerInfo, i, new SessionResult(-3));
            }
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRatingWithMediaId(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        if (iMediaController == null || str == null || bundle == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "setRatingWithMediaId(): Ignoring empty mediaId");
            return;
        }
        try {
            final Rating ratingFromBundle = Rating.fromBundle(bundle);
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda53
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, str, ratingFromBundle);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRating(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final Rating ratingFromBundle = Rating.fromBundle(bundle);
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda3
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, ratingFromBundle);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackSpeed(IMediaController iMediaController, int i, final float f) {
        if (iMediaController == null || f <= 0.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda15
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setPlaybackSpeed(f);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final PlaybackParameters playbackParametersFromBundle = PlaybackParameters.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda36
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setPlaybackParameters(playbackParametersFromBundle);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for PlaybackParameters", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        setMediaItemWithResetPosition(iMediaController, i, bundle, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, final long j) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda80
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItemFromBundle), 0, j);
                }
            }, new MediaSessionStub$$ExternalSyntheticLambda2())));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
            if (controller != null) {
                setMediaItemItemWithResetPositionForControllerInfo(controller, i, mediaItemFromBundle, z);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    private void setMediaItemItemWithResetPositionForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i, final MediaItem mediaItem, final boolean z) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda10
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo2, int i2) {
                MediaItem mediaItem2 = mediaItem;
                boolean z2 = z;
                return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo2, ImmutableList.of(mediaItem2), z2 ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), z2 ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
            }
        }, new MediaSessionStub$$ExternalSyntheticLambda2())));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        setMediaItemsWithResetPosition(iMediaController, i, iBinder, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, final boolean z) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            final ImmutableList immutableListFromBundleList = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    List list = immutableListFromBundleList;
                    boolean z2 = z;
                    return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, z2 ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), z2 ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
                }
            }, new MediaSessionStub$$ExternalSyntheticLambda2())));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, final int i2, final long j) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        if (i2 == -1 || i2 >= 0) {
            try {
                final ImmutableList immutableListFromBundleList = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(iBinder));
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda47
                    @Override // androidx.media3.session.MediaSessionStub.SessionTask
                    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                        return MediaSessionStub.lambda$setMediaItemsWithStartIndex$35(immutableListFromBundleList, i2, j, mediaSessionImpl, controllerInfo, i3);
                    }
                }, new MediaSessionStub$$ExternalSyntheticLambda2())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    static /* synthetic */ ListenableFuture lambda$setMediaItemsWithStartIndex$35(List list, int i, long j, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
        int currentMediaItemIndex = i == -1 ? mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex() : i;
        if (i == -1) {
            j = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, currentMediaItemIndex, j);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaMetadata mediaMetadataFromBundle = MediaMetadata.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 19, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda79
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setPlaylistMetadata(mediaMetadataFromBundle);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaMetadata", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda62
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItemFromBundle));
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda63
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    playerWrapper.addMediaItems(list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemWithIndex(IMediaController iMediaController, int i, final int i2, Bundle bundle) {
        if (iMediaController == null || bundle == null || i2 < 0) {
            return;
        }
        try {
            final MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda24
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItemFromBundle));
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda25
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    this.f$0.m7831x4d54c06e(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItemWithIndex$40$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7831x4d54c06e(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            final ImmutableList immutableListFromBundleList = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda60
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, immutableListFromBundleList);
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda61
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    playerWrapper.addMediaItems(list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemsWithIndex(IMediaController iMediaController, int i, final int i2, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i2 < 0) {
            return;
        }
        try {
            final ImmutableList immutableListFromBundleList = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda38
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, immutableListFromBundleList);
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda39
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    this.f$0.m7832xb692ae9d(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* JADX INFO: renamed from: lambda$addMediaItemsWithIndex$44$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7832xb692ae9d(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItem(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda77
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                this.f$0.m7841x82e782e6(i2, playerWrapper, controllerInfo);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$removeMediaItem$45$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7841x82e782e6(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItems(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0 || i3 < i2) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess(new ControllerPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda0
            @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
            public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
                this.f$0.m7842x4667088c(i2, i3, playerWrapper, controllerInfo);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$removeMediaItems$46$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7842x4667088c(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2));
    }

    @Override // androidx.media3.session.IMediaSession
    public void clearMediaItems(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda90
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).clearMediaItems();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItem(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0 || i3 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda82
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).moveMediaItem(i2, i3);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItems(IMediaController iMediaController, int i, final int i2, final int i3, final int i4) {
        if (iMediaController == null || i2 < 0 || i3 < i2 || i4 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda46
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).moveMediaItems(i2, i3, i4);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItem(IMediaController iMediaController, int i, final int i2, Bundle bundle) {
        if (iMediaController == null || bundle == null || i2 < 0) {
            return;
        }
        try {
            final MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda31
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i3) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItemFromBundle));
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda32
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    this.f$0.m7843x1e3cdef6(i2, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* JADX INFO: renamed from: lambda$replaceMediaItem$50$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7843x1e3cdef6(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        if (list.size() == 1) {
            playerWrapper.replaceMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), (MediaItem) list.get(0));
        } else {
            playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i + 1), list);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItems(IMediaController iMediaController, int i, final int i2, final int i3, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i2 < 0 || i3 < i2) {
            return;
        }
        try {
            final ImmutableList immutableListFromBundleList = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(iBinder));
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda8
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                    return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, immutableListFromBundleList);
                }
            }, new MediaItemPlayerTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda9
                @Override // androidx.media3.session.MediaSessionStub.MediaItemPlayerTask
                public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
                    this.f$0.m7844x94c48927(i2, i3, playerWrapper, controllerInfo, list);
                }
            })));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
        }
    }

    /* JADX INFO: renamed from: lambda$replaceMediaItems$52$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7844x94c48927(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2), list);
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPreviousMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 6, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda45
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToPreviousMediaItem();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNextMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 8, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda28
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToNextMediaItem();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPrevious(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToPreviousForControllerInfo(controller, i);
    }

    public void seekToPreviousForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 7, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda37
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToPrevious();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNext(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToNextForControllerInfo(controller, i);
    }

    public void seekToNextForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 9, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda52
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).seekToNext();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRepeatMode(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        if (i2 == 2 || i2 == 0 || i2 == 1) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 15, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda58
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setRepeatMode(i2);
                }
            }));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setShuffleModeEnabled(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 14, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda67
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setShuffleModeEnabled(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurface(IMediaController iMediaController, int i, final Surface surface) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda71
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7848x91f81868(surface, (PlayerWrapper) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$setVideoSurface$55$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7848x91f81868(Surface surface, PlayerWrapper playerWrapper) {
        if (((MediaSessionImpl) Preconditions.checkNotNull(this.sessionImpl.get())).shouldUseLegacySurfaceHandling()) {
            playerWrapper.setVideoSurface(surface);
            return;
        }
        if (surface == null) {
            playerWrapper.setVideoSurfaceHolder(null);
            this.surfaceHolderWithSize = null;
        } else {
            SurfaceHolderWithSize surfaceHolderWithSize = new SurfaceHolderWithSize(surface);
            this.surfaceHolderWithSize = surfaceHolderWithSize;
            playerWrapper.setVideoSurfaceHolder(surfaceHolderWithSize);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurfaceWithSize(IMediaController iMediaController, int i, final Surface surface, final int i2, final int i3) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda17
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7849x90cb6e0e(surface, i2, i3, (PlayerWrapper) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$setVideoSurfaceWithSize$56$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7849x90cb6e0e(Surface surface, int i, int i2, PlayerWrapper playerWrapper) {
        if (((MediaSessionImpl) Preconditions.checkNotNull(this.sessionImpl.get())).shouldUseLegacySurfaceHandling()) {
            playerWrapper.setVideoSurface(surface);
            return;
        }
        if (surface == null) {
            playerWrapper.setVideoSurfaceHolder(null);
            this.surfaceHolderWithSize = null;
        } else {
            SurfaceHolderWithSize surfaceHolderWithSize = new SurfaceHolderWithSize(surface, i, i2);
            this.surfaceHolderWithSize = surfaceHolderWithSize;
            playerWrapper.setVideoSurfaceHolder(surfaceHolderWithSize);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void onSurfaceSizeChanged(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda27
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m7837x94c1a151(i2, i3, (PlayerWrapper) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$onSurfaceSizeChanged$57$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7837x94c1a151(int i, int i2, PlayerWrapper playerWrapper) {
        SurfaceHolderWithSize surfaceHolderWithSize;
        if (((MediaSessionImpl) Preconditions.checkNotNull(this.sessionImpl.get())).shouldUseLegacySurfaceHandling() || (surfaceHolderWithSize = this.surfaceHolderWithSize) == null) {
            return;
        }
        surfaceHolderWithSize.setFixedSize(i, i2);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVolume(IMediaController iMediaController, int i, final float f) {
        if (iMediaController == null || f < 0.0f || f > 1.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 24, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda65
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setVolume(f);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void mute(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 24, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda7
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).mute();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void unmute(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 24, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda69
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).unmute();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolume(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 25, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda86
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2, final int i3) {
        if (iMediaController == null || i2 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 33, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda56
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceVolume(i2, i3);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda30
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).increaseDeviceVolume();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda41
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).increaseDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda55
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).decreaseDeviceVolume();
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda33
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).decreaseDeviceVolume(i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMuted(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda35
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceMuted(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMutedWithFlags(IMediaController iMediaController, int i, final boolean z, final int i2) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda78
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setDeviceMuted(z, i2);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, final boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final AudioAttributes audioAttributesFromBundle = AudioAttributes.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 35, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda59
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    ((PlayerWrapper) obj).setAudioAttributes(audioAttributesFromBundle, z);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for AudioAttributes", e);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlayWhenReady(IMediaController iMediaController, int i, final boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda13
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).setPlayWhenReady(z);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void flushCommandQueue(IMediaController iMediaController) {
        if (iMediaController == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller != null) {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda43
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m7836x9d70a25b(controller);
                        }
                    });
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: lambda$flushCommandQueue$69$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7836x9d70a25b(MediaSession.ControllerInfo controllerInfo) {
        this.connectedControllersManager.flushCommandQueue(controllerInfo);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            final TrackSelectionParameters trackSelectionParametersFromBundle = TrackSelectionParameters.fromBundle(bundle);
            queueSessionTaskWithPlayerCommand(iMediaController, i, 29, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda22
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7847x4b9930e8(trackSelectionParametersFromBundle, (PlayerWrapper) obj);
                }
            }));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for TrackSelectionParameters", e);
        }
    }

    /* JADX INFO: renamed from: lambda$setTrackSelectionParameters$70$androidx-media3-session-MediaSessionStub, reason: not valid java name */
    /* synthetic */ void m7847x4b9930e8(TrackSelectionParameters trackSelectionParameters, PlayerWrapper playerWrapper) {
        playerWrapper.setTrackSelectionParameters(updateOverridesUsingUniqueTrackGroupIds(trackSelectionParameters));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) {
        final MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, 50000, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda72
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetLibraryRootOnHandler(controllerInfo, libraryParamsFromBundle);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getItem(IMediaController iMediaController, int i, final String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getItem(): Ignoring empty mediaId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda48
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetItemOnHandler(controllerInfo, str);
                }
            }));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void getChildren(IMediaController iMediaController, int i, final String str, final int i2, final int i3, Bundle bundle) {
        final MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getChildren(): Ignoring empty parentId");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "getChildren(): Ignoring negative page");
            return;
        }
        if (i3 < 1) {
            Log.w(TAG, "getChildren(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda5
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetChildrenOnHandler(controllerInfo, str, i2, i3, libraryParamsFromBundle);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void search(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        final MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "search(): Ignoring empty query");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda91
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                return ((MediaLibrarySessionImpl) mediaSessionImpl).onSearchOnHandler(controllerInfo, str, libraryParamsFromBundle);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getSearchResult(IMediaController iMediaController, int i, final String str, final int i2, final int i3, Bundle bundle) {
        final MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getSearchResult(): Ignoring empty query");
            return;
        }
        if (i2 < 0) {
            Log.w(TAG, "getSearchResult(): Ignoring negative page");
            return;
        }
        if (i3 < 1) {
            Log.w(TAG, "getSearchResult(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda64
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i4) {
                return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetSearchResultOnHandler(controllerInfo, str, i2, i3, libraryParamsFromBundle);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void subscribe(IMediaController iMediaController, int i, final String str, Bundle bundle) {
        final MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "subscribe(): Ignoring empty parentId");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda81
            @Override // androidx.media3.session.MediaSessionStub.SessionTask
            public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                return ((MediaLibrarySessionImpl) mediaSessionImpl).onSubscribeOnHandler(controllerInfo, str, libraryParamsFromBundle);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void unsubscribe(IMediaController iMediaController, int i, final String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "unsubscribe(): Ignoring empty parentId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, sendLibraryResultWhenReady(new SessionTask() { // from class: androidx.media3.session.MediaSessionStub$$ExternalSyntheticLambda4
                @Override // androidx.media3.session.MediaSessionStub.SessionTask
                public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
                    return ((MediaLibrarySessionImpl) mediaSessionImpl).onUnsubscribeOnHandler(controllerInfo, str);
                }
            }));
        }
    }

    PlayerInfo generateAndCacheUniqueTrackGroupIds(PlayerInfo playerInfo) {
        ImmutableList<Tracks.Group> groups = playerInfo.currentTracks.getGroups();
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableBiMap.Builder builder2 = ImmutableBiMap.builder();
        for (int i = 0; i < groups.size(); i++) {
            Tracks.Group group = groups.get(i);
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            String strGenerateUniqueTrackGroupId = this.trackGroupIdMap.get(mediaTrackGroup);
            if (strGenerateUniqueTrackGroupId == null) {
                strGenerateUniqueTrackGroupId = generateUniqueTrackGroupId(mediaTrackGroup);
            }
            builder2.put(mediaTrackGroup, strGenerateUniqueTrackGroupId);
            builder.add(group.copyWithId(strGenerateUniqueTrackGroupId));
        }
        this.trackGroupIdMap = builder2.buildOrThrow();
        PlayerInfo playerInfoCopyWithCurrentTracks = playerInfo.copyWithCurrentTracks(new Tracks(builder.build()));
        if (playerInfoCopyWithCurrentTracks.trackSelectionParameters.overrides.isEmpty()) {
            return playerInfoCopyWithCurrentTracks;
        }
        TrackSelectionParameters.Builder builderClearOverrides = playerInfoCopyWithCurrentTracks.trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = playerInfoCopyWithCurrentTracks.trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = next.mediaTrackGroup;
            String str = this.trackGroupIdMap.get(trackGroup);
            if (str != null) {
                builderClearOverrides.addOverride(new TrackSelectionOverride(trackGroup.copyWithId(str), next.trackIndices));
            } else {
                builderClearOverrides.addOverride(next);
            }
        }
        return playerInfoCopyWithCurrentTracks.copyWithTrackSelectionParameters(builderClearOverrides.build());
    }

    private TrackSelectionParameters updateOverridesUsingUniqueTrackGroupIds(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters.overrides.isEmpty()) {
            return trackSelectionParameters;
        }
        TrackSelectionParameters.Builder builderClearOverrides = trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = this.trackGroupIdMap.inverse().get(next.mediaTrackGroup.id);
            if (trackGroup != null && next.mediaTrackGroup.length == trackGroup.length) {
                builderClearOverrides.addOverride(new TrackSelectionOverride(trackGroup, next.trackIndices));
            } else {
                builderClearOverrides.addOverride(next);
            }
        }
        return builderClearOverrides.build();
    }

    private String generateUniqueTrackGroupId(TrackGroup trackGroup) {
        StringBuilder sb = new StringBuilder();
        int i = this.nextUniqueTrackGroupIdPrefix;
        this.nextUniqueTrackGroupIdPrefix = i + 1;
        sb.append(Util.intToStringMaxRadix(i));
        sb.append("-");
        sb.append(trackGroup.id);
        return sb.toString();
    }

    static final class Controller2Cb implements MediaSession.ControllerCb {
        private final int controllerInterfaceVersion;
        private final IMediaController iController;

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAudioAttributesChanged(this, i, audioAttributes);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAudioSessionIdChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAudioSessionIdChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onDeviceInfoChanged(this, i, deviceInfo);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onDeviceVolumeChanged(int i, int i2, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onDeviceVolumeChanged(this, i, i2, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onIsLoadingChanged(int i, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onIsLoadingChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onIsPlayingChanged(int i, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onIsPlayingChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onMediaItemTransition(int i, MediaItem mediaItem, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onMediaItemTransition(this, i, mediaItem, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onMediaMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onMediaMetadataChanged(this, i, mediaMetadata);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlayWhenReadyChanged(int i, boolean z, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlayWhenReadyChanged(this, i, z, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlaybackParametersChanged(int i, PlaybackParameters playbackParameters) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlaybackParametersChanged(this, i, playbackParameters);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlaybackStateChanged(int i, int i2, PlaybackException playbackException) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlaybackStateChanged(this, i, i2, playbackException);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlaybackSuppressionReasonChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlayerChanged(int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlayerChanged(this, i, playerWrapper, playerWrapper2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlayerError(int i, PlaybackException playbackException) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlayerError(this, i, playbackException);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlaylistMetadataChanged(this, i, mediaMetadata);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPositionDiscontinuity(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPositionDiscontinuity(this, i, positionInfo, positionInfo2, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onRepeatModeChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onRepeatModeChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSeekBackIncrementChanged(int i, long j) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSeekBackIncrementChanged(this, i, j);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSeekForwardIncrementChanged(int i, long j) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSeekForwardIncrementChanged(this, i, j);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onShuffleModeEnabledChanged(int i, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onShuffleModeEnabledChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onTimelineChanged(int i, Timeline timeline, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onTimelineChanged(this, i, timeline, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onTrackSelectionParametersChanged(int i, TrackSelectionParameters trackSelectionParameters) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onTrackSelectionParametersChanged(this, i, trackSelectionParameters);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onTracksChanged(int i, Tracks tracks) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onTracksChanged(this, i, tracks);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onVideoSizeChanged(int i, VideoSize videoSize) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onVideoSizeChanged(this, i, videoSize);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onVolumeChanged(int i, float f) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onVolumeChanged(this, i, f);
        }

        public Controller2Cb(IMediaController iMediaController, int i) {
            this.iController = iMediaController;
            this.controllerInterfaceVersion = i;
        }

        public IBinder getCallbackBinder() {
            return this.iController.asBinder();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            this.iController.onSessionResult(i, sessionResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onLibraryResult(int i, LibraryResult<?> libraryResult) throws RemoteException {
            this.iController.onLibraryResult(i, libraryResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            Bundle bundleForRemoteProcess;
            Preconditions.checkState(this.controllerInterfaceVersion != 0);
            boolean z3 = z || !commands.contains(17);
            boolean z4 = z2 || !commands.contains(30);
            if (this.controllerInterfaceVersion >= 2) {
                PlayerInfo playerInfoFilterByAvailableCommands = playerInfo.filterByAvailableCommands(commands, z, z2);
                if (this.iController instanceof MediaControllerStub) {
                    bundleForRemoteProcess = playerInfoFilterByAvailableCommands.toBundleInProcess();
                } else {
                    bundleForRemoteProcess = playerInfoFilterByAvailableCommands.toBundleForRemoteProcess(this.controllerInterfaceVersion);
                }
                this.iController.onPlayerInfoChangedWithExclusions(i, bundleForRemoteProcess, new PlayerInfo.BundlingExclusions(z3, z4).toBundle());
                return;
            }
            this.iController.onPlayerInfoChanged(i, playerInfo.filterByAvailableCommands(commands, z, true).toBundleForRemoteProcess(this.controllerInterfaceVersion), z3);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setCustomLayout(int i, List<CommandButton> list) throws RemoteException {
            this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda3()));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setMediaButtonPreferences(int i, List<CommandButton> list) throws RemoteException {
            if (this.controllerInterfaceVersion >= 7) {
                this.iController.onSetMediaButtonPreferences(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda3()));
            } else {
                this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(CommandButton.getCustomLayoutFromMediaButtonPreferences(list, true, true), new ConnectionState$$ExternalSyntheticLambda3()));
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
            this.iController.onSessionActivityChanged(i, pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromSession(i, sessionCommands.toBundle(), commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromPlayer(i, commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            this.iController.onCustomCommand(i, sessionCommand.toBundle(), bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommandProgressUpdate(int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException {
            this.iController.onCustomCommandProgressUpdate(i, sessionCommand.toBundle(), bundle, bundle2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onChildrenChanged(i, str, i2, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onSearchResultChanged(i, str, i2, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int i) {
            SessionUtil.disconnectIMediaController(this.iController);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            this.iController.onPeriodicSessionPositionInfoChanged(i, sessionPositionInfo.filterByAvailableCommands(z, z2).toBundle(i2));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException {
            this.iController.onSurfaceSizeChanged(i, i2, i3);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRenderedFirstFrame(int i) throws RemoteException {
            this.iController.onRenderedFirstFrame(i);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException {
            this.iController.onExtrasChanged(i, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onError(int i, SessionError sessionError) throws RemoteException {
            this.iController.onError(i, sessionError.toBundle());
        }

        public int hashCode() {
            return ObjectsCompat.hash(getCallbackBinder());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Controller2Cb.class) {
                return false;
            }
            return Objects.equals(getCallbackBinder(), ((Controller2Cb) obj).getCallbackBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ProgressReporter implements MediaSession.ProgressReporter {
        private final SessionCommand command;
        private final MediaSession.ControllerInfo controller;
        private final int customCommandFutureSequence;
        private final Bundle extras;
        private ListenableFuture<SessionResult> future;
        private final MediaSessionImpl session;

        public ProgressReporter(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i, SessionCommand sessionCommand, Bundle bundle) {
            this.session = mediaSessionImpl;
            this.controller = controllerInfo;
            this.customCommandFutureSequence = i;
            this.command = sessionCommand;
            this.extras = bundle;
        }

        @Override // androidx.media3.session.MediaSession.ProgressReporter
        public void sendProgressUpdate(Bundle bundle) {
            ListenableFuture<SessionResult> listenableFuture = this.future;
            if ((listenableFuture == null || !listenableFuture.isDone()) && !this.session.isReleased()) {
                this.session.sendCustomCommandProgressUpdate(this.controller, this.customCommandFutureSequence, this.command, this.extras, bundle);
            }
        }

        public void setFuture(ListenableFuture<SessionResult> listenableFuture) {
            this.future = listenableFuture;
        }
    }

    static class SurfaceHolderWithSize implements SurfaceHolder {
        private SurfaceHolder.Callback callback;
        private final Surface surface;
        private final Rect surfaceFrame;

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return false;
        }

        @Override // android.view.SurfaceHolder
        public void setFormat(int i) {
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean z) {
        }

        @Override // android.view.SurfaceHolder
        public void setSizeFromLayout() {
        }

        @Override // android.view.SurfaceHolder
        public void setType(int i) {
        }

        @Override // android.view.SurfaceHolder
        public void unlockCanvasAndPost(Canvas canvas) {
        }

        SurfaceHolderWithSize(Surface surface) {
            this.surfaceFrame = new Rect();
            this.surface = surface;
        }

        SurfaceHolderWithSize(Surface surface, int i, int i2) {
            Rect rect = new Rect();
            this.surfaceFrame = rect;
            this.surface = surface;
            rect.set(0, 0, i, i2);
        }

        @Override // android.view.SurfaceHolder
        public void setFixedSize(int i, int i2) {
            this.surfaceFrame.set(0, 0, i, i2);
            SurfaceHolder.Callback callback = this.callback;
            if (callback != null) {
                callback.surfaceChanged(this, 1, i, i2);
            }
        }

        @Override // android.view.SurfaceHolder
        public void addCallback(SurfaceHolder.Callback callback) {
            this.callback = callback;
        }

        @Override // android.view.SurfaceHolder
        public void removeCallback(SurfaceHolder.Callback callback) {
            if (this.callback == callback) {
                this.callback = null;
            }
        }

        @Override // android.view.SurfaceHolder
        public Surface getSurface() {
            return this.surface;
        }

        @Override // android.view.SurfaceHolder
        public Rect getSurfaceFrame() {
            return this.surfaceFrame;
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas() {
            throw new UnsupportedOperationException();
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas(Rect rect) {
            throw new UnsupportedOperationException();
        }
    }
}

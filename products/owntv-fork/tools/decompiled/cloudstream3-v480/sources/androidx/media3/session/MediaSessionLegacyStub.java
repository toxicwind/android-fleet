package androidx.media3.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.core.os.EnvironmentCompat;
import androidx.core.util.ObjectsCompat;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
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
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.RatingCompat;
import androidx.media3.session.legacy.VolumeProviderCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 300000;
    private static final String DEFAULT_MEDIA_SESSION_TAG_DELIM = ".";
    private static final String DEFAULT_MEDIA_SESSION_TAG_PREFIX = "androidx.media3.session.id";
    private static final int PENDING_INTENT_FLAG_MUTABLE;
    private static final String TAG = "MediaSessionLegacyStub";
    private final AndroidAutoConnectionStateObserver androidAutoObserver;
    private Player.Commands availablePlayerCommands;
    private SessionCommands availableSessionCommands;
    private final ComponentName broadcastReceiverComponentName;
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final ConnectionTimeoutHandler connectionTimeoutHandler;
    private volatile long connectionTimeoutMs;
    private final ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast;
    private ImmutableList<CommandButton> customLayout;
    private PlaybackException customPlaybackException;
    private LegacyError legacyError;
    private Bundle legacyExtras;
    private final boolean mayNeedButtonReservationWorkaroundForSeekbar;
    private ImmutableList<CommandButton> mediaButtonPreferences;
    private FutureCallback<Bitmap> pendingBitmapLoadCallback;
    private final boolean playIfSuppressed;
    private Player.Commands playerCommandsForErrorState;
    private final MediaButtonReceiver runtimeBroadcastReceiver;
    private final MediaSessionCompat sessionCompat;
    private int sessionFlags;
    private final MediaSessionImpl sessionImpl;
    private final MediaSessionManager sessionManager;
    private VolumeProviderCompat volumeProviderCompat;

    /* JADX INFO: Access modifiers changed from: private */
    interface SessionTask {
        void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    private static long convertCommandToPlaybackStateActions(int i, boolean z) {
        if (i == 1) {
            return z ? 516L : 514L;
        }
        if (i == 2) {
            return 16384L;
        }
        if (i == 3) {
            return 1L;
        }
        if (i == 31) {
            return 240640L;
        }
        switch (i) {
            case 5:
                return 256L;
            case 6:
            case 7:
                return 16L;
            case 8:
            case 9:
                return 32L;
            case 10:
                return 4096L;
            case 11:
                return 8L;
            case 12:
                return 64L;
            case 13:
                return 4194304L;
            case 14:
                return 2621440L;
            case 15:
                return 262144L;
            default:
                return 0L;
        }
    }

    private static <T> void ignoreFuture(Future<T> future) {
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetCaptioningEnabled(boolean z) {
    }

    static {
        PENDING_INTENT_FLAG_MUTABLE = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x008f  */
    /* JADX WARN: Code duplicated, block: B:22:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:24:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:26:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:27:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:28:0x00db  */
    /* JADX WARN: Code duplicated, block: B:33:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:37:0x0102  */
    /* JADX WARN: Code duplicated, block: B:44:0x0124  */
    /* JADX WARN: Code duplicated, block: B:47:0x012c  */
    /* JADX WARN: Multi-variable type inference failed */
    public MediaSessionLegacyStub(MediaSessionImpl mediaSessionImpl, Uri uri, Handler handler, Bundle bundle, boolean z, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle2) {
        ComponentName serviceComponentByAction;
        boolean z2;
        Intent intent;
        PendingIntent broadcast;
        MediaSessionCompat mediaSessionCompat;
        PendingIntent pendingIntent;
        PendingIntent sessionActivity;
        Object[] objArr;
        this.sessionImpl = mediaSessionImpl;
        this.playIfSuppressed = z;
        this.customLayout = immutableList;
        this.mediaButtonPreferences = immutableList2;
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
        this.legacyExtras = new Bundle(bundle2);
        Context context = mediaSessionImpl.getContext();
        this.sessionManager = MediaSessionManager.getSessionManager(context);
        this.controllerLegacyCbForBroadcast = new ControllerLegacyCbForBroadcast();
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
        this.connectedControllersManager = connectedControllersManager;
        this.connectionTimeoutMs = 300000L;
        this.connectionTimeoutHandler = new ConnectionTimeoutHandler(mediaSessionImpl.getApplicationHandler().getLooper(), connectedControllersManager);
        boolean zMayNeedButtonReservationWorkaroundForSeekbar = mayNeedButtonReservationWorkaroundForSeekbar(context);
        this.mayNeedButtonReservationWorkaroundForSeekbar = zMayNeedButtonReservationWorkaroundForSeekbar;
        if (!immutableList2.isEmpty()) {
            updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        }
        ComponentName componentNameQueryPackageManagerForMediaButtonReceiver = queryPackageManagerForMediaButtonReceiver(context);
        this.broadcastReceiverComponentName = componentNameQueryPackageManagerForMediaButtonReceiver;
        if (componentNameQueryPackageManagerForMediaButtonReceiver == null || Build.VERSION.SDK_INT < 31) {
            serviceComponentByAction = getServiceComponentByAction(context, MediaLibraryService.SERVICE_INTERFACE);
            serviceComponentByAction = serviceComponentByAction == null ? getServiceComponentByAction(context, MediaSessionService.SERVICE_INTERFACE) : serviceComponentByAction;
            if (serviceComponentByAction != null && !Objects.equals(serviceComponentByAction, componentNameQueryPackageManagerForMediaButtonReceiver)) {
                z2 = true;
            }
            intent = new Intent("android.intent.action.MEDIA_BUTTON", uri);
            objArr = 0;
            if (serviceComponentByAction == null) {
                MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver(this, objArr == true ? 1 : 0);
                this.runtimeBroadcastReceiver = mediaButtonReceiver;
                IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
                intentFilter.addDataScheme((String) Util.castNonNull(uri.getScheme()));
                Util.registerReceiverNotExported(context, mediaButtonReceiver, intentFilter);
                intent.setPackage(context.getPackageName());
                broadcast = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                serviceComponentByAction = new ComponentName(context, context.getClass());
            } else {
                intent.setComponent(serviceComponentByAction);
                if (z2) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        broadcast = PendingIntent.getForegroundService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                    } else {
                        broadcast = PendingIntent.getService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                    }
                } else {
                    broadcast = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                }
                this.runtimeBroadcastReceiver = null;
            }
            String strJoin = TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, mediaSessionImpl.getId()});
            serviceComponentByAction = Build.VERSION.SDK_INT >= 31 ? null : serviceComponentByAction;
            if (Build.VERSION.SDK_INT < 31) {
                pendingIntent = broadcast;
            } else {
                pendingIntent = null;
            }
            mediaSessionCompat = new MediaSessionCompat(context, strJoin, serviceComponentByAction, pendingIntent, bundle);
            this.sessionCompat = mediaSessionCompat;
            if (Build.VERSION.SDK_INT >= 31 && componentNameQueryPackageManagerForMediaButtonReceiver != null) {
                Api31.setMediaButtonBroadcastReceiver(mediaSessionCompat, componentNameQueryPackageManagerForMediaButtonReceiver);
            }
            sessionActivity = mediaSessionImpl.getSessionActivity();
            if (sessionActivity != null) {
                mediaSessionCompat.setSessionActivity(sessionActivity);
            }
            mediaSessionCompat.setCallback(this, handler);
            this.androidAutoObserver = zMayNeedButtonReservationWorkaroundForSeekbar ? new AndroidAutoConnectionStateObserver(context, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.onAndroidAutoConnectionStateChanged();
                }
            }) : null;
        }
        serviceComponentByAction = componentNameQueryPackageManagerForMediaButtonReceiver;
        z2 = false;
        intent = new Intent("android.intent.action.MEDIA_BUTTON", uri);
        objArr = 0;
        if (serviceComponentByAction == null) {
            MediaButtonReceiver mediaButtonReceiver2 = new MediaButtonReceiver(this, objArr == true ? 1 : 0);
            this.runtimeBroadcastReceiver = mediaButtonReceiver2;
            IntentFilter intentFilter2 = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            intentFilter2.addDataScheme((String) Util.castNonNull(uri.getScheme()));
            Util.registerReceiverNotExported(context, mediaButtonReceiver2, intentFilter2);
            intent.setPackage(context.getPackageName());
            broadcast = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            serviceComponentByAction = new ComponentName(context, context.getClass());
        } else {
            intent.setComponent(serviceComponentByAction);
            if (z2) {
                if (Build.VERSION.SDK_INT >= 26) {
                    broadcast = PendingIntent.getForegroundService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                } else {
                    broadcast = PendingIntent.getService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
                }
            } else {
                broadcast = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            }
            this.runtimeBroadcastReceiver = null;
        }
        String strJoin2 = TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, mediaSessionImpl.getId()});
        if (Build.VERSION.SDK_INT >= 31) {
        }
        if (Build.VERSION.SDK_INT < 31) {
            pendingIntent = broadcast;
        } else {
            pendingIntent = null;
        }
        mediaSessionCompat = new MediaSessionCompat(context, strJoin2, serviceComponentByAction, pendingIntent, bundle);
        this.sessionCompat = mediaSessionCompat;
        if (Build.VERSION.SDK_INT >= 31) {
            Api31.setMediaButtonBroadcastReceiver(mediaSessionCompat, componentNameQueryPackageManagerForMediaButtonReceiver);
        }
        sessionActivity = mediaSessionImpl.getSessionActivity();
        if (sessionActivity != null) {
            mediaSessionCompat.setSessionActivity(sessionActivity);
        }
        mediaSessionCompat.setCallback(this, handler);
        this.androidAutoObserver = zMayNeedButtonReservationWorkaroundForSeekbar ? new AndroidAutoConnectionStateObserver(context, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onAndroidAutoConnectionStateChanged();
            }
        }) : null;
    }

    public void setAvailableCommands(SessionCommands sessionCommands, Player.Commands commands) {
        if (this.customPlaybackException != null) {
            return;
        }
        boolean z = this.availablePlayerCommands.contains(17) != commands.contains(17);
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
        if (!this.mediaButtonPreferences.isEmpty()) {
            updateCustomLayoutAndLegacyExtrasForMediaButtonPreferencesAndInformExtrasChanged();
        }
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        if (z) {
            updateLegacySessionPlaybackStateAndQueue(mediaSessionImpl.getPlayerWrapper());
        } else {
            updateLegacySessionPlaybackState(mediaSessionImpl.getPlayerWrapper());
        }
    }

    public MediaSession.ConnectionResult getPlatformConnectionResult(MediaSession mediaSession) {
        MediaSession.ConnectionResult.AcceptedResultBuilder availablePlayerCommands = new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession).setAvailableSessionCommands(this.availableSessionCommands).setAvailablePlayerCommands(this.availablePlayerCommands);
        if (!this.mediaButtonPreferences.isEmpty()) {
            availablePlayerCommands.setMediaButtonPreferences(this.mediaButtonPreferences);
        } else {
            availablePlayerCommands.setCustomLayout(this.customLayout);
        }
        return availablePlayerCommands.build();
    }

    public void setPlatformCustomLayout(ImmutableList<CommandButton> immutableList) {
        this.customLayout = immutableList;
    }

    public void setPlatformMediaButtonPreferences(ImmutableList<CommandButton> immutableList) {
        this.mediaButtonPreferences = immutableList;
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferencesAndInformExtrasChanged();
    }

    public void setPlaybackException(PlaybackException playbackException, Player.Commands commands) {
        Preconditions.checkArgument((playbackException == null && commands == null) || !(playbackException == null || commands == null));
        this.customPlaybackException = playbackException;
        this.playerCommandsForErrorState = commands;
        if (playbackException != null) {
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
            maybeUpdateFlags(this.sessionImpl.getPlayerWrapper());
        }
    }

    public void setLegacyError(LibraryResult<?> libraryResult, boolean z) {
        String str;
        int iConvertToLegacyErrorCode = LegacyConversions.convertToLegacyErrorCode(libraryResult.resultCode);
        LegacyError legacyError = this.legacyError;
        if (legacyError == null || legacyError.code != iConvertToLegacyErrorCode) {
            if (libraryResult.sessionError != null) {
                str = libraryResult.sessionError.message;
            } else {
                str = "no error message provided";
            }
            String str2 = str;
            Bundle bundle = Bundle.EMPTY;
            if (libraryResult.params != null && libraryResult.params.extras.containsKey("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT")) {
                bundle = libraryResult.params.extras;
            } else if (libraryResult.sessionError != null) {
                bundle = libraryResult.sessionError.extras;
            }
            this.legacyError = new LegacyError(z, iConvertToLegacyErrorCode, str2, bundle, null);
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
        }
    }

    public void clearLegacyErrorStatus() {
        if (this.legacyError != null) {
            this.legacyError = null;
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
        }
    }

    private static ComponentName queryPackageManagerForMediaButtonReceiver(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers.size() == 1) {
            ResolveInfo resolveInfo = listQueryBroadcastReceivers.get(0);
            return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }
        if (listQueryBroadcastReceivers.isEmpty()) {
            return null;
        }
        throw new IllegalStateException("Expected 1 broadcast receiver that handles android.intent.action.MEDIA_BUTTON, found " + listQueryBroadcastReceivers.size());
    }

    public void start() {
        this.sessionCompat.setActive(true);
    }

    public void release() {
        if (Build.VERSION.SDK_INT < 31) {
            if (this.broadcastReceiverComponentName == null) {
                setMediaButtonReceiver(this.sessionCompat, null);
            } else {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", this.sessionImpl.getUri());
                intent.setComponent(this.broadcastReceiverComponentName);
                setMediaButtonReceiver(this.sessionCompat, PendingIntent.getBroadcast(this.sessionImpl.getContext(), 0, intent, PENDING_INTENT_FLAG_MUTABLE));
            }
        }
        if (this.runtimeBroadcastReceiver != null) {
            this.sessionImpl.getContext().unregisterReceiver(this.runtimeBroadcastReceiver);
        }
        AndroidAutoConnectionStateObserver androidAutoConnectionStateObserver = this.androidAutoObserver;
        if (androidAutoConnectionStateObserver != null) {
            androidAutoConnectionStateObserver.release();
        }
        this.sessionCompat.release();
    }

    public MediaSessionCompat getSessionCompat() {
        return this.sessionCompat;
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCommand(String str, final Bundle bundle, final ResultReceiver resultReceiver) {
        Preconditions.checkNotNull(str);
        if (str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            return;
        }
        if (str.equals("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN") && resultReceiver != null) {
            resultReceiver.send(0, this.sessionImpl.getToken().toBundle());
        } else {
            final SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
            dispatchSessionTaskWithSessionCommand(sessionCommand, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda19
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7803xec90a203(sessionCommand, bundle, resultReceiver, controllerInfo);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onCommand$0$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7803xec90a203(SessionCommand sessionCommand, Bundle bundle, ResultReceiver resultReceiver, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        ListenableFuture<SessionResult> listenableFutureOnCustomCommandOnHandler = mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, null, sessionCommand, bundle);
        if (resultReceiver != null) {
            sendCustomCommandResultWhenReady(resultReceiver, listenableFutureOnCustomCommandOnHandler);
        } else {
            ignoreFuture(listenableFutureOnCustomCommandOnHandler);
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCustomAction(String str, final Bundle bundle) {
        if (str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            return;
        }
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        final SessionCommand sessionCommand = new SessionCommand(str, bundle);
        if (CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction)) {
            dispatchCustomCommandAsPredefinedCommand(sessionCommand);
        } else {
            dispatchSessionTaskWithSessionCommand(sessionCommand, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda7
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7804x6dc3132e(sessionCommand, bundle, controllerInfo);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onCustomAction$1$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7804x6dc3132e(SessionCommand sessionCommand, Bundle bundle, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ignoreFuture(this.sessionImpl.onCustomCommandOnHandler(controllerInfo, null, sessionCommand, bundle));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public boolean onMediaButtonEvent(Intent intent) {
        return this.sessionImpl.onMediaButtonEvent(new MediaSession.ControllerInfo((MediaSessionManager.RemoteUserInfo) Preconditions.checkNotNull(this.sessionCompat.getCurrentControllerInfo()), 0, 0, false, null, Bundle.EMPTY, 0, Build.VERSION.SDK_INT >= 33), intent);
    }

    void maybeUpdateFlags(PlayerWrapper playerWrapper) {
        int i = playerWrapper.isCommandAvailable(20) ? 4 : 0;
        if (this.sessionFlags != i) {
            this.sessionFlags = i;
            this.sessionCompat.setFlags(i);
        }
    }

    void handleMediaPlayPauseOnHandler(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        dispatchSessionTaskWithPlayerCommand(1, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda5
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7800x31f4e7a8(controllerInfo);
            }
        }, remoteUserInfo, true);
    }

    /* JADX INFO: renamed from: lambda$handleMediaPlayPauseOnHandler$2$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7800x31f4e7a8(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePlayPauseButtonAction(this.sessionImpl.getPlayerWrapper(), this.sessionImpl.shouldPlayIfSuppressed());
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepare() {
        dispatchSessionTaskWithPlayerCommand(2, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda20
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7807xfc59f964(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onPrepare$3$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7807xfc59f964(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().prepare();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, null, null, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, str, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, uri, null, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlay() {
        dispatchSessionTaskWithPlayRequest();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, null, null, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, str, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, uri, null, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPause() {
        dispatchSessionTaskWithPlayerCommand(1, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda1
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7806lambda$onPause$4$androidxmedia3sessionMediaSessionLegacyStub(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onPause$4$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7806lambda$onPause$4$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePauseButtonAction(this.sessionImpl.getPlayerWrapper());
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onStop() {
        dispatchSessionTaskWithPlayerCommand(3, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda23
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7819lambda$onStop$5$androidxmedia3sessionMediaSessionLegacyStub(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onStop$5$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7819lambda$onStop$5$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().stop();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSeekTo(final long j) {
        dispatchSessionTaskWithPlayerCommand(5, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda29
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7810lambda$onSeekTo$6$androidxmedia3sessionMediaSessionLegacyStub(j, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onSeekTo$6$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7810lambda$onSeekTo$6$androidxmedia3sessionMediaSessionLegacyStub(long j, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekTo(j);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToNext() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(9)) {
            dispatchSessionTaskWithPlayerCommand(9, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda27
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7814xf713cec2(controllerInfo);
                }
            }, this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(8, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda28
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7815x30de70a1(controllerInfo);
                }
            }, this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* JADX INFO: renamed from: lambda$onSkipToNext$7$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7814xf713cec2(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNext();
    }

    /* JADX INFO: renamed from: lambda$onSkipToNext$8$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7815x30de70a1(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNextMediaItem();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToPrevious() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(7)) {
            dispatchSessionTaskWithPlayerCommand(7, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda12
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7817x42d945fc(controllerInfo);
                }
            }, this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(6, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda13
                @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    this.f$0.m7816x6478fb88(controllerInfo);
                }
            }, this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* JADX INFO: renamed from: lambda$onSkipToPrevious$9$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7817x42d945fc(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPrevious();
    }

    /* JADX INFO: renamed from: lambda$onSkipToPrevious$10$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7816x6478fb88(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPreviousMediaItem();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetPlaybackSpeed(final float f) {
        if (f <= 0.0f) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(13, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda2
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7811x43347260(f, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onSetPlaybackSpeed$11$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7811x43347260(float f, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setPlaybackSpeed(f);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToQueueItem(final long j) {
        if (j < 0) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(10, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda6
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7818x5172da7(j, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onSkipToQueueItem$12$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7818x5172da7(long j, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToDefaultPosition((int) j);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onFastForward() {
        dispatchSessionTaskWithPlayerCommand(12, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda10
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7805xe2a98605(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onFastForward$13$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7805xe2a98605(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekForward();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRewind() {
        dispatchSessionTaskWithPlayerCommand(11, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda17
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7809x58ead3ce(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onRewind$14$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7809x58ead3ce(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekBack();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat ratingCompat) {
        onSetRating(ratingCompat, null);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        Rating ratingConvertToRating = LegacyConversions.convertToRating(ratingCompat);
        if (ratingConvertToRating == null) {
            Log.w(TAG, "Ignoring invalid RatingCompat " + ratingCompat);
            return;
        }
        dispatchSessionTaskWithSetRatingSessionCommand(ratingConvertToRating);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRepeatMode(final int i) {
        dispatchSessionTaskWithPlayerCommand(15, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda16
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7812x9ce90e1a(i, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onSetRepeatMode$15$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7812x9ce90e1a(int i, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setRepeatMode(LegacyConversions.convertToRepeatMode(i));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetShuffleMode(final int i) {
        dispatchSessionTaskWithPlayerCommand(14, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda30
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7813x31186b8b(i, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onSetShuffleMode$16$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7813x31186b8b(int i, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setShuffleModeEnabled(LegacyConversions.convertToShuffleModeEnabled(i));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        handleOnAddQueueItem(mediaDescriptionCompat, -1);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        handleOnAddQueueItem(mediaDescriptionCompat, i);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRemoveQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(20, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda21
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7808xa37fc898(mediaDescriptionCompat, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* JADX INFO: renamed from: lambda$onRemoveQueueItem$17$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7808xa37fc898(MediaDescriptionCompat mediaDescriptionCompat, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        String mediaId = mediaDescriptionCompat.getMediaId();
        if (TextUtils.isEmpty(mediaId)) {
            Log.w(TAG, "onRemoveQueueItem(): Media ID shouldn't be null");
            return;
        }
        PlayerWrapper playerWrapper = this.sessionImpl.getPlayerWrapper();
        if (!playerWrapper.isCommandAvailable(17)) {
            Log.w(TAG, "Can't remove item by ID without COMMAND_GET_TIMELINE being available");
            return;
        }
        Timeline currentTimeline = playerWrapper.getCurrentTimeline();
        Timeline.Window window = new Timeline.Window();
        for (int i = 0; i < currentTimeline.getWindowCount(); i++) {
            if (TextUtils.equals(currentTimeline.getWindow(i, window).mediaItem.mediaId, mediaId)) {
                playerWrapper.removeMediaItem(i);
                return;
            }
        }
    }

    public MediaSession.ControllerCb getControllerLegacyCbForBroadcast() {
        return this.controllerLegacyCbForBroadcast;
    }

    public ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    boolean canResumePlaybackOnStart() {
        return this.broadcastReceiverComponentName != null;
    }

    private void dispatchSessionTaskWithPlayRequest() {
        dispatchSessionTaskWithPlayerCommand(1, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda18
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7796x3c763484(controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), false);
    }

    /* JADX INFO: renamed from: lambda$dispatchSessionTaskWithPlayRequest$18$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7796x3c763484(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.handleMediaControllerPlayRequest(controllerInfo, true);
    }

    private void dispatchSessionTaskWithPlayerCommand(final int i, final SessionTask sessionTask, final MediaSessionManager.RemoteUserInfo remoteUserInfo, final boolean z) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (remoteUserInfo == null) {
            Log.d(TAG, "RemoteUserInfo is null, ignoring command=" + i);
            return;
        }
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7797xa9f680bc(i, remoteUserInfo, sessionTask, z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$dispatchSessionTaskWithPlayerCommand$20$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7797xa9f680bc(int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, final SessionTask sessionTask, boolean z) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (!this.sessionCompat.isActive()) {
            Log.w(TAG, "Ignore incoming player command before initialization. command=" + i + ", pid=" + remoteUserInfo.getPid());
            return;
        }
        final MediaSession.ControllerInfo controllerInfoTryGetController = tryGetController(remoteUserInfo);
        if (controllerInfoTryGetController == null) {
            return;
        }
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfoTryGetController, i)) {
            if (i != 1 || this.sessionImpl.getPlayerWrapper().getPlayWhenReady()) {
                return;
            }
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
            return;
        }
        if (this.sessionImpl.onPlayerCommandRequestOnHandler(controllerInfoTryGetController, i) != 0) {
            return;
        }
        this.sessionImpl.callWithControllerForCurrentRequestSet(controllerInfoTryGetController, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                MediaSessionLegacyStub.lambda$dispatchSessionTaskWithPlayerCommand$19(sessionTask, controllerInfoTryGetController);
            }
        }).run();
        if (z) {
            this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfoTryGetController, new Player.Commands.Builder().add(i).build());
        }
    }

    static /* synthetic */ void lambda$dispatchSessionTaskWithPlayerCommand$19(SessionTask sessionTask, MediaSession.ControllerInfo controllerInfo) {
        try {
            sessionTask.run(controllerInfo);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo, e);
        }
    }

    private void dispatchSessionTaskWithSetRatingSessionCommand(final Rating rating) {
        dispatchSessionTaskWithSessionCommandInternal(null, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda11
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7799x4230ddc5(rating, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo());
    }

    /* JADX INFO: renamed from: lambda$dispatchSessionTaskWithSetRatingSessionCommand$21$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7799x4230ddc5(Rating rating, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaItem currentMediaItemWithCommandCheck = this.sessionImpl.getPlayerWrapper().getCurrentMediaItemWithCommandCheck();
        if (currentMediaItemWithCommandCheck == null) {
            return;
        }
        ignoreFuture(this.sessionImpl.onSetRatingOnHandler(controllerInfo, currentMediaItemWithCommandCheck.mediaId, rating));
    }

    private void dispatchSessionTaskWithSessionCommand(SessionCommand sessionCommand, SessionTask sessionTask) {
        dispatchSessionTaskWithSessionCommandInternal(sessionCommand, 0, sessionTask, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommandInternal(final SessionCommand sessionCommand, final int i, final SessionTask sessionTask, final MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        Object objValueOf;
        if (remoteUserInfo == null) {
            StringBuilder sb = new StringBuilder("RemoteUserInfo is null, ignoring command=");
            if (sessionCommand == null) {
                objValueOf = sessionCommand;
                objValueOf = Integer.valueOf(i);
            }
            objValueOf = sessionCommand;
            sb.append(objValueOf);
            Log.d(TAG, sb.toString());
            return;
        }
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7798xc94c4056(sessionCommand, i, remoteUserInfo, sessionTask);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$dispatchSessionTaskWithSessionCommandInternal$22$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7798xc94c4056(SessionCommand sessionCommand, int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, SessionTask sessionTask) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (!this.sessionCompat.isActive()) {
            StringBuilder sb = new StringBuilder("Ignore incoming session command before initialization. command=");
            sb.append(sessionCommand == null ? Integer.valueOf(i) : sessionCommand.customAction);
            sb.append(", pid=");
            sb.append(remoteUserInfo.getPid());
            Log.w(TAG, sb.toString());
            return;
        }
        MediaSession.ControllerInfo controllerInfoTryGetController = tryGetController(remoteUserInfo);
        if (controllerInfoTryGetController == null) {
            return;
        }
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager = this.connectedControllersManager;
        if (sessionCommand != null) {
            if (!connectedControllersManager.isSessionCommandAvailable(controllerInfoTryGetController, sessionCommand)) {
                return;
            }
        } else if (!connectedControllersManager.isSessionCommandAvailable(controllerInfoTryGetController, i)) {
            return;
        }
        try {
            sessionTask.run(controllerInfoTryGetController);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfoTryGetController, e);
        }
    }

    private void dispatchCustomCommandAsPredefinedCommand(SessionCommand sessionCommand) {
        try {
            final CommandButton commandButtonConvertFromPredefinedCustomCommand = CommandButton.convertFromPredefinedCustomCommand(sessionCommand);
            if (!commandButtonConvertFromPredefinedCustomCommand.canExecuteAction()) {
                Log.w(TAG, "Can't execute predefined custom command: " + sessionCommand.customAction);
                return;
            }
            if (commandButtonConvertFromPredefinedCustomCommand.sessionCommand != null) {
                Preconditions.checkState(commandButtonConvertFromPredefinedCustomCommand.sessionCommand.commandCode == 40010);
                dispatchSessionTaskWithSetRatingSessionCommand((Rating) Preconditions.checkNotNull(commandButtonConvertFromPredefinedCustomCommand.parameter));
            } else if (commandButtonConvertFromPredefinedCustomCommand.isPlayRequestPlayerAction(this.sessionImpl.getPlayerWrapper())) {
                dispatchSessionTaskWithPlayRequest();
            } else if (commandButtonConvertFromPredefinedCustomCommand.playerCommand == 31) {
                handleMediaRequest((MediaItem) Preconditions.checkNotNull(commandButtonConvertFromPredefinedCustomCommand.parameter), false, false);
            } else {
                dispatchSessionTaskWithPlayerCommand(commandButtonConvertFromPredefinedCustomCommand.playerCommand, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda15
                    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                    public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                        this.f$0.m7795x12140c29(commandButtonConvertFromPredefinedCustomCommand, controllerInfo);
                    }
                }, this.sessionCompat.getCurrentControllerInfo(), true);
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "Failed to convert predefined custom command: " + sessionCommand.customAction, e);
        }
    }

    /* JADX INFO: renamed from: lambda$dispatchCustomCommandAsPredefinedCommand$23$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7795x12140c29(CommandButton commandButton, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        commandButton.executePlayerAction(this.sessionImpl.getPlayerWrapper());
    }

    private MediaSession.ControllerInfo tryGetController(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(remoteUserInfo);
        if (controller == null) {
            ControllerLegacyCb controllerLegacyCb = new ControllerLegacyCb(remoteUserInfo);
            MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.sessionManager.isTrustedForMediaControl(remoteUserInfo), controllerLegacyCb, Bundle.EMPTY, 0, Build.VERSION.SDK_INT >= 33);
            MediaSession.ConnectionResult connectionResultOnConnectOnHandler = this.sessionImpl.onConnectOnHandler(controllerInfo);
            if (!connectionResultOnConnectOnHandler.isAccepted) {
                controllerLegacyCb.onDisconnected(0);
                return null;
            }
            this.connectedControllersManager.addController(controllerInfo.getRemoteUserInfo(), controllerInfo, connectionResultOnConnectOnHandler.availableSessionCommands, connectionResultOnConnectOnHandler.availablePlayerCommands);
            this.sessionImpl.onPostConnectOnHandler(controllerInfo);
            controller = controllerInfo;
        }
        this.connectionTimeoutHandler.disconnectControllerAfterTimeout(controller, this.connectionTimeoutMs);
        return controller;
    }

    public void setLegacyControllerDisconnectTimeoutMs(long j) {
        this.connectionTimeoutMs = j;
    }

    public void updateLegacySessionPlaybackState(final PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7820x688b2aa7(playerWrapper);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateLegacySessionPlaybackState$24$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7820x688b2aa7(PlayerWrapper playerWrapper) {
        this.sessionCompat.setPlaybackState(createPlaybackStateCompat(playerWrapper));
    }

    public void updateLegacySessionPlaybackStateAndQueue(final PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7821x5e1fd4a0(playerWrapper);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateLegacySessionPlaybackStateAndQueue$25$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7821x5e1fd4a0(PlayerWrapper playerWrapper) {
        Timeline currentTimeline;
        this.sessionCompat.setPlaybackState(createPlaybackStateCompat(playerWrapper));
        ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast = this.controllerLegacyCbForBroadcast;
        if (playerWrapper.getAvailableCommands().contains(17)) {
            currentTimeline = playerWrapper.getCurrentTimeline();
        } else {
            currentTimeline = Timeline.EMPTY;
        }
        controllerLegacyCbForBroadcast.updateQueue(currentTimeline);
    }

    private void handleMediaRequest(MediaItem mediaItem, boolean z) {
        handleMediaRequest(mediaItem, true, z);
    }

    private void handleMediaRequest(final MediaItem mediaItem, final boolean z, final boolean z2) {
        dispatchSessionTaskWithPlayerCommand(31, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda3
            @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
            public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                this.f$0.m7801x5caac5e6(mediaItem, z, z2, controllerInfo);
            }
        }, this.sessionCompat.getCurrentControllerInfo(), false);
    }

    /* JADX INFO: renamed from: lambda$handleMediaRequest$26$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7801x5caac5e6(MediaItem mediaItem, boolean z, boolean z2, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Futures.addCallback(this.sessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItem), -1, C.TIME_UNSET), new AnonymousClass1(controllerInfo, z, z2), MoreExecutors.directExecutor());
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$1, reason: invalid class name */
    class AnonymousClass1 implements FutureCallback<MediaSession.MediaItemsWithStartPosition> {
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ boolean val$play;
        final /* synthetic */ boolean val$prepare;

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
        }

        AnonymousClass1(MediaSession.ControllerInfo controllerInfo, boolean z, boolean z2) {
            this.val$controller = controllerInfo;
            this.val$prepare = z;
            this.val$play = z2;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(final MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
            Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
            MediaSessionImpl mediaSessionImpl = MediaSessionLegacyStub.this.sessionImpl;
            final MediaSession.ControllerInfo controllerInfo = this.val$controller;
            final boolean z = this.val$prepare;
            final boolean z2 = this.val$play;
            Util.postOrRun(applicationHandler, mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7822xec13c618(mediaItemsWithStartPosition, z, z2, controllerInfo);
                }
            }));
        }

        /* JADX INFO: renamed from: lambda$onSuccess$0$androidx-media3-session-MediaSessionLegacyStub$1, reason: not valid java name */
        /* synthetic */ void m7822xec13c618(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, boolean z2, MediaSession.ControllerInfo controllerInfo) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaUtils.setMediaItemsWithStartIndexAndPosition(playerWrapper, mediaItemsWithStartPosition);
            int playbackState = playerWrapper.getPlaybackState();
            if (z) {
                if (playbackState == 1) {
                    playerWrapper.prepareIfCommandAvailable();
                } else if (playbackState == 4) {
                    playerWrapper.seekToDefaultPositionIfCommandAvailable();
                }
            }
            if (z2) {
                playerWrapper.playIfCommandAvailable();
            }
            MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().addAll(31, 2).addIf(1, z2).build());
        }
    }

    private void handleOnAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final int i) {
        if (mediaDescriptionCompat != null) {
            if (i == -1 || i >= 0) {
                dispatchSessionTaskWithPlayerCommand(20, new SessionTask() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda8
                    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
                    public final void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                        this.f$0.m7802x1f46445c(mediaDescriptionCompat, i, controllerInfo);
                    }
                }, this.sessionCompat.getCurrentControllerInfo(), false);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$handleOnAddQueueItem$27$androidx-media3-session-MediaSessionLegacyStub, reason: not valid java name */
    /* synthetic */ void m7802x1f46445c(MediaDescriptionCompat mediaDescriptionCompat, int i, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
            Log.w(TAG, "onAddQueueItem(): Media ID shouldn't be empty");
        } else {
            Futures.addCallback(this.sessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(LegacyConversions.convertToMediaItem(mediaDescriptionCompat))), new AnonymousClass2(controllerInfo, i), MoreExecutors.directExecutor());
        }
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$2, reason: invalid class name */
    class AnonymousClass2 implements FutureCallback<List<MediaItem>> {
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ int val$index;

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
        }

        AnonymousClass2(MediaSession.ControllerInfo controllerInfo, int i) {
            this.val$controller = controllerInfo;
            this.val$index = i;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(final List<MediaItem> list) {
            Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
            MediaSessionImpl mediaSessionImpl = MediaSessionLegacyStub.this.sessionImpl;
            final MediaSession.ControllerInfo controllerInfo = this.val$controller;
            final int i = this.val$index;
            Util.postOrRun(applicationHandler, mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7823xec13c619(i, list, controllerInfo);
                }
            }));
        }

        /* JADX INFO: renamed from: lambda$onSuccess$0$androidx-media3-session-MediaSessionLegacyStub$2, reason: not valid java name */
        /* synthetic */ void m7823xec13c619(int i, List list, MediaSession.ControllerInfo controllerInfo) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            if (i == -1) {
                mediaSessionLegacyStub.sessionImpl.getPlayerWrapper().addMediaItems(list);
            } else {
                mediaSessionLegacyStub.sessionImpl.getPlayerWrapper().addMediaItems(i, list);
            }
            MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().add(20).build());
        }
    }

    private static void sendCustomCommandResultWhenReady(final ResultReceiver resultReceiver, final ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                MediaSessionLegacyStub.lambda$sendCustomCommandResultWhenReady$28(listenableFuture, resultReceiver);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$sendCustomCommandResultWhenReady$28(ListenableFuture listenableFuture, ResultReceiver resultReceiver) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Preconditions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (InterruptedException e) {
            e = e;
            Log.w(TAG, "Custom command failed", e);
            sessionResult = new SessionResult(-1);
        } catch (CancellationException e2) {
            Log.w(TAG, "Custom command cancelled", e2);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e3) {
            e = e3;
            Log.w(TAG, "Custom command failed", e);
            sessionResult = new SessionResult(-1);
        }
        resultReceiver.send(sessionResult.resultCode, sessionResult.extras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setMetadata(MediaSessionCompat mediaSessionCompat, MediaMetadataCompat mediaMetadataCompat) {
        mediaSessionCompat.setMetadata(mediaMetadataCompat);
    }

    private static void setMediaButtonReceiver(MediaSessionCompat mediaSessionCompat, PendingIntent pendingIntent) {
        mediaSessionCompat.setMediaButtonReceiver(pendingIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setQueue(MediaSessionCompat mediaSessionCompat, List<MediaSessionCompat.QueueItem> list) {
        mediaSessionCompat.setQueue(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueueTitle(MediaSessionCompat mediaSessionCompat, CharSequence charSequence) {
        if (!isQueueEnabled()) {
            charSequence = null;
        }
        mediaSessionCompat.setQueueTitle(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isQueueEnabled() {
        return this.availablePlayerCommands.contains(17) && this.sessionImpl.getPlayerWrapper().getAvailableCommands().contains(17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAndroidAutoConnectionStateChanged() {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateCustomLayoutAndLegacyExtrasForMediaButtonPreferencesAndInformExtrasChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCustomLayoutAndLegacyExtrasForMediaButtonPreferencesAndInformExtrasChanged() {
        boolean z = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean z2 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false) == z && this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false) == z2) {
            return;
        }
        getSessionCompat().setExtras(this.legacyExtras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences() {
        ImmutableList<CommandButton> immutableList = this.mediaButtonPreferences;
        SessionCommands sessionCommands = this.availableSessionCommands;
        Player.Commands commands = this.playerCommandsForErrorState;
        if (commands == null) {
            commands = this.availablePlayerCommands;
        }
        this.customLayout = CommandButton.getCustomLayoutFromMediaButtonPreferences(CommandButton.copyWithUnavailableButtonsDisabled(immutableList, sessionCommands, commands), true, true);
        if (needsButtonReservationWorkaroundForSeekbar(this.androidAutoObserver)) {
            this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", (this.customLayout.isEmpty() || CommandButton.containsButtonForSlot(this.customLayout, 2)) ? false : true);
            this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        } else {
            this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", !CommandButton.containsButtonForSlot(this.customLayout, 2));
            this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", !CommandButton.containsButtonForSlot(this.customLayout, 3));
        }
    }

    private static MediaItem createMediaItemForMediaRequest(String str, Uri uri, String str2, Bundle bundle) {
        MediaItem.Builder builder = new MediaItem.Builder();
        if (str == null) {
            str = "";
        }
        return builder.setMediaId(str).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(uri).setSearchQuery(str2).setExtras(bundle).build()).build();
    }

    private static final class ControllerLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAudioAttributesChanged(this, i, audioAttributes);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAudioSessionIdChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAudioSessionIdChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAvailableCommandsChangedFromPlayer(this, i, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAvailableCommandsChangedFromSession(this, i, sessionCommands, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onChildrenChanged(this, i, str, i2, libraryParams);
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
        public /* synthetic */ void onDisconnected(int i) {
            MediaSession.ControllerCb.CC.$default$onDisconnected(this, i);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onError(int i, SessionError sessionError) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onError(this, i, sessionError);
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
        public /* synthetic */ void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onLibraryResult(this, i, libraryResult);
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
        public /* synthetic */ void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPeriodicSessionPositionInfoChanged(this, i, sessionPositionInfo, z, z2, i2);
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
        public /* synthetic */ void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlayerInfoChanged(this, i, playerInfo, commands, z, z2);
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
        public /* synthetic */ void onRenderedFirstFrame(int i) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onRenderedFirstFrame(this, i);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onRepeatModeChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onRepeatModeChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSearchResultChanged(this, i, str, i2, libraryParams);
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
        public /* synthetic */ void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSessionActivityChanged(this, i, pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSessionExtrasChanged(this, i, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSessionResult(this, i, sessionResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onShuffleModeEnabledChanged(int i, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onShuffleModeEnabledChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSurfaceSizeChanged(this, i, i2, i3);
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

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$sendCustomCommand(this, i, sessionCommand, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void sendCustomCommandProgressUpdate(int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$sendCustomCommandProgressUpdate(this, i, sessionCommand, bundle, bundle2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void setCustomLayout(int i, List list) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$setCustomLayout(this, i, list);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void setMediaButtonPreferences(int i, List list) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$setMediaButtonPreferences(this, i, list);
        }

        public ControllerLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.remoteUserInfo = remoteUserInfo;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ControllerLegacyCb.class) {
                return false;
            }
            return Objects.equals(this.remoteUserInfo, ((ControllerLegacyCb) obj).remoteUserInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class ControllerLegacyCbForBroadcast implements MediaSession.ControllerCb {
        private Uri lastMediaUri;
        private MediaMetadata lastMediaMetadata = MediaMetadata.EMPTY;
        private String lastMediaId = "";
        private long lastDurationMs = C.TIME_UNSET;

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAudioSessionIdChanged(int i, int i2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAudioSessionIdChanged(this, i, i2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onAvailableCommandsChangedFromSession(this, i, sessionCommands, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onChildrenChanged(this, i, str, i2, libraryParams);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int i) {
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onIsLoadingChanged(int i, boolean z) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onIsLoadingChanged(this, i, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onLibraryResult(this, i, libraryResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onPlayerInfoChanged(this, i, playerInfo, commands, z, z2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onRenderedFirstFrame(int i) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onRenderedFirstFrame(this, i);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSearchResultChanged(this, i, str, i2, libraryParams);
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
        public /* synthetic */ void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSessionResult(this, i, sessionResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$onSurfaceSizeChanged(this, i, i2, i3);
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

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public /* synthetic */ void sendCustomCommandProgressUpdate(int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException {
            MediaSession.ControllerCb.CC.$default$sendCustomCommandProgressUpdate(this, i, sessionCommand, bundle, bundle2);
        }

        public ControllerLegacyCbForBroadcast() {
        }

        public boolean skipLegacySessionPlaybackStateUpdates() {
            return MediaSessionLegacyStub.this.customPlaybackException != null;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper);
            MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerChanged(int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
            Timeline currentTimelineWithCommandCheck = playerWrapper2.getCurrentTimelineWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getCurrentTimelineWithCommandCheck(), currentTimelineWithCommandCheck)) {
                onTimelineChanged(i, currentTimelineWithCommandCheck, 0);
            }
            MediaMetadata playlistMetadataWithCommandCheck = playerWrapper2.getPlaylistMetadataWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getPlaylistMetadataWithCommandCheck(), playlistMetadataWithCommandCheck)) {
                onPlaylistMetadataChanged(i, playlistMetadataWithCommandCheck);
            }
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper2.getMediaMetadataWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getMediaMetadataWithCommandCheck(), mediaMetadataWithCommandCheck)) {
                onMediaMetadataChanged(i, mediaMetadataWithCommandCheck);
            }
            if (playerWrapper == null || playerWrapper.getShuffleModeEnabled() != playerWrapper2.getShuffleModeEnabled()) {
                onShuffleModeEnabledChanged(i, playerWrapper2.getShuffleModeEnabled());
            }
            if (playerWrapper == null || playerWrapper.getRepeatMode() != playerWrapper2.getRepeatMode()) {
                onRepeatModeChanged(i, playerWrapper2.getRepeatMode());
            }
            onDeviceInfoChanged(i, playerWrapper2.getDeviceInfo());
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper2);
            MediaItem currentMediaItemWithCommandCheck = playerWrapper2.getCurrentMediaItemWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getCurrentMediaItemWithCommandCheck(), currentMediaItemWithCommandCheck)) {
                onMediaItemTransition(i, currentMediaItemWithCommandCheck, 3);
            } else {
                if (skipLegacySessionPlaybackStateUpdates()) {
                    return;
                }
                MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper2);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerError(int i, PlaybackException playbackException) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setCustomLayout(int i, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setMediaButtonPreferences(int i, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionExtrasChanged(int i, Bundle bundle) {
            Preconditions.checkArgument(!bundle.containsKey(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT));
            Preconditions.checkArgument(!bundle.containsKey("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID"));
            MediaSessionLegacyStub.this.legacyExtras = new Bundle(bundle);
            if (!MediaSessionLegacyStub.this.mediaButtonPreferences.isEmpty()) {
                MediaSessionLegacyStub.this.updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
            }
            MediaSessionLegacyStub.this.sessionCompat.setExtras(MediaSessionLegacyStub.this.legacyExtras);
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) {
            MediaSessionLegacyStub.this.sessionCompat.setSessionActivity(pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onError(int i, SessionError sessionError) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.legacyError = new LegacyError(false, LegacyConversions.convertToLegacyErrorCode(sessionError.code), sessionError.message, sessionError.extras, null);
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(MediaSessionLegacyStub.this.createPlaybackStateCompat(playerWrapper));
            MediaSessionLegacyStub.this.legacyError = null;
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(MediaSessionLegacyStub.this.createPlaybackStateCompat(playerWrapper));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) {
            if (bundle.isEmpty()) {
                bundle = sessionCommand.customExtras;
            } else if (!sessionCommand.customExtras.isEmpty()) {
                Bundle bundle2 = new Bundle(sessionCommand.customExtras);
                bundle2.putAll(bundle);
                bundle = bundle2;
            }
            MediaSessionLegacyStub.this.sessionCompat.sendSessionEvent(sessionCommand.customAction, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayWhenReadyChanged(int i, boolean z, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackSuppressionReasonChanged(int i, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackStateChanged(int i, int i2, PlaybackException playbackException) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onIsPlayingChanged(int i, boolean z) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPositionDiscontinuity(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackParametersChanged(int i, PlaybackParameters playbackParameters) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaItemTransition(int i, MediaItem mediaItem, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateMetadataIfChanged();
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            if (mediaItem == null) {
                mediaSessionLegacyStub.sessionCompat.setRatingType(0);
            } else {
                mediaSessionLegacyStub.sessionCompat.setRatingType(LegacyConversions.getRatingCompatStyle(mediaItem.mediaMetadata.userRating));
            }
            MediaSessionLegacyStub mediaSessionLegacyStub2 = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub2.updateLegacySessionPlaybackState(mediaSessionLegacyStub2.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaMetadataChanged(int i, MediaMetadata mediaMetadata) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateMetadataIfChanged();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onTimelineChanged(int i, Timeline timeline, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateQueue(timeline);
            updateMetadataIfChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateQueue(Timeline timeline) {
            if (!MediaSessionLegacyStub.this.isQueueEnabled() || timeline.isEmpty()) {
                MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, null);
                return;
            }
            final List<MediaItem> listConvertToMediaItemList = LegacyConversions.convertToMediaItemList(timeline);
            final ArrayList arrayList = new ArrayList();
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            Runnable runnable = new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7824x58a50959(atomicInteger, listConvertToMediaItemList, arrayList);
                }
            };
            for (int i = 0; i < listConvertToMediaItemList.size(); i++) {
                MediaMetadata mediaMetadata = listConvertToMediaItemList.get(i).mediaMetadata;
                if (mediaMetadata.artworkData != null) {
                    ListenableFuture<Bitmap> listenableFutureDecodeBitmap = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                    arrayList.add(listenableFutureDecodeBitmap);
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    listenableFutureDecodeBitmap.addListener(runnable, new ConcurrencyHelpers$$ExternalSyntheticLambda0(applicationHandler));
                } else {
                    arrayList.add(null);
                    runnable.run();
                }
            }
        }

        /* JADX INFO: renamed from: lambda$updateQueue$0$androidx-media3-session-MediaSessionLegacyStub$ControllerLegacyCbForBroadcast, reason: not valid java name */
        /* synthetic */ void m7824x58a50959(AtomicInteger atomicInteger, List list, List list2) {
            if (atomicInteger.incrementAndGet() == list.size()) {
                handleBitmapFuturesAllCompletedAndSetQueue(list2, list);
            }
        }

        private void handleBitmapFuturesAllCompletedAndSetQueue(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2) {
            Bitmap bitmap;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                ListenableFuture<Bitmap> listenableFuture = list.get(i);
                if (listenableFuture != null) {
                    try {
                        bitmap = (Bitmap) Futures.getDone(listenableFuture);
                    } catch (CancellationException | ExecutionException e) {
                        Log.d(MediaSessionLegacyStub.TAG, "Failed to get bitmap", e);
                        bitmap = null;
                    }
                } else {
                    bitmap = null;
                }
                arrayList.add(LegacyConversions.convertToQueueItem(list2.get(i), i, bitmap));
            }
            MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, arrayList);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            CharSequence queueTitle = MediaSessionLegacyStub.this.sessionCompat.getController().getQueueTitle();
            CharSequence charSequence = mediaMetadata.title;
            if (TextUtils.equals(queueTitle, charSequence)) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.setQueueTitle(mediaSessionLegacyStub.sessionCompat, charSequence);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onShuffleModeEnabledChanged(int i, boolean z) {
            MediaSessionLegacyStub.this.sessionCompat.setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRepeatModeChanged(int i, int i2) throws RemoteException {
            MediaSessionLegacyStub.this.sessionCompat.setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i2));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) {
            if (MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().getDeviceInfo().playbackType == 0) {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToLocal(audioAttributes);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.volumeProviderCompat = MediaSessionLegacyStub.createVolumeProviderCompat(playerWrapper);
            VolumeProviderCompat volumeProviderCompat = MediaSessionLegacyStub.this.volumeProviderCompat;
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            if (volumeProviderCompat == null) {
                mediaSessionLegacyStub.sessionCompat.setPlaybackToLocal(playerWrapper.getAudioAttributesWithCommandCheck());
            } else {
                mediaSessionLegacyStub.sessionCompat.setPlaybackToRemote(MediaSessionLegacyStub.this.volumeProviderCompat);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceVolumeChanged(int i, int i2, boolean z) {
            if (MediaSessionLegacyStub.this.volumeProviderCompat != null) {
                VolumeProviderCompat volumeProviderCompat = MediaSessionLegacyStub.this.volumeProviderCompat;
                if (z) {
                    i2 = 0;
                }
                volumeProviderCompat.setCurrentVolume(i2);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        private void updateMetadataIfChanged() {
            final long j;
            final String str;
            final Uri uri;
            ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast;
            final MediaMetadata mediaMetadata;
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaItem currentMediaItemWithCommandCheck = playerWrapper.getCurrentMediaItemWithCommandCheck();
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper.getMediaMetadataWithCommandCheck();
            long durationWithCommandCheck = playerWrapper.isCurrentMediaItemLiveWithCommandCheck() ? C.TIME_UNSET : playerWrapper.getDurationWithCommandCheck();
            String str2 = currentMediaItemWithCommandCheck != null ? currentMediaItemWithCommandCheck.mediaId : "";
            Bitmap bitmap = null;
            Uri uri2 = (currentMediaItemWithCommandCheck == null || currentMediaItemWithCommandCheck.requestMetadata.mediaUri == null) ? null : currentMediaItemWithCommandCheck.requestMetadata.mediaUri;
            if (Objects.equals(this.lastMediaMetadata, mediaMetadataWithCommandCheck) && Objects.equals(this.lastMediaId, str2) && Objects.equals(this.lastMediaUri, uri2) && this.lastDurationMs == durationWithCommandCheck) {
                return;
            }
            this.lastMediaId = str2;
            this.lastMediaUri = uri2;
            this.lastMediaMetadata = mediaMetadataWithCommandCheck;
            this.lastDurationMs = durationWithCommandCheck;
            ListenableFuture<Bitmap> listenableFutureLoadBitmapFromMetadata = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().loadBitmapFromMetadata(mediaMetadataWithCommandCheck);
            if (listenableFutureLoadBitmapFromMetadata != null) {
                MediaSessionLegacyStub.this.pendingBitmapLoadCallback = null;
                if (!listenableFutureLoadBitmapFromMetadata.isDone()) {
                    j = durationWithCommandCheck;
                    uri = uri2;
                    mediaMetadata = mediaMetadataWithCommandCheck;
                    str = str2;
                    controllerLegacyCbForBroadcast = this;
                    MediaSessionLegacyStub.this.pendingBitmapLoadCallback = new FutureCallback<Bitmap>() { // from class: androidx.media3.session.MediaSessionLegacyStub.ControllerLegacyCbForBroadcast.1
                        @Override // com.google.common.util.concurrent.FutureCallback
                        public void onSuccess(Bitmap bitmap2) {
                            if (this != MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                return;
                            }
                            MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadata, str, uri, j, bitmap2));
                            MediaSessionLegacyStub.this.sessionImpl.onNotificationRefreshRequired();
                        }

                        @Override // com.google.common.util.concurrent.FutureCallback
                        public void onFailure(Throwable th) {
                            if (this != MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                return;
                            }
                            Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(th));
                        }
                    };
                    FutureCallback futureCallback = MediaSessionLegacyStub.this.pendingBitmapLoadCallback;
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    Futures.addCallback(listenableFutureLoadBitmapFromMetadata, futureCallback, new ConcurrencyHelpers$$ExternalSyntheticLambda0(applicationHandler));
                } else {
                    try {
                        bitmap = (Bitmap) Futures.getDone(listenableFutureLoadBitmapFromMetadata);
                    } catch (CancellationException | ExecutionException e) {
                        Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(e));
                    }
                    j = durationWithCommandCheck;
                    str = str2;
                    uri = uri2;
                    controllerLegacyCbForBroadcast = this;
                    mediaMetadata = mediaMetadataWithCommandCheck;
                }
            } else {
                j = durationWithCommandCheck;
                str = str2;
                uri = uri2;
                controllerLegacyCbForBroadcast = this;
                mediaMetadata = mediaMetadataWithCommandCheck;
            }
            MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadata, str, uri, j, bitmap));
        }
    }

    private static class ConnectionTimeoutHandler extends Handler {
        private static final int MSG_CONNECTION_TIMED_OUT = 1001;
        private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;

        public ConnectionTimeoutHandler(Looper looper, ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager) {
            super(looper);
            this.connectedControllersManager = connectedControllersManager;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) message.obj;
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                ((MediaSession.ControllerCb) Preconditions.checkNotNull(controllerInfo.getControllerCb())).onDisconnected(0);
                this.connectedControllersManager.removeController(controllerInfo);
            }
        }

        public void disconnectControllerAfterTimeout(MediaSession.ControllerInfo controllerInfo, long j) {
            removeMessages(1001, controllerInfo);
            sendMessageDelayed(obtainMessage(1001, controllerInfo), j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
        return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlaybackStateCompat createPlaybackStateCompat(PlayerWrapper playerWrapper) {
        Player.Commands commandsIntersect;
        Bundle bundle;
        LegacyError legacyError = this.legacyError;
        if (this.customPlaybackException == null && legacyError != null && legacyError.isFatal) {
            Bundle bundle2 = new Bundle(legacyError.extras);
            bundle2.putAll(this.legacyExtras);
            return new PlaybackStateCompat.Builder().setState(7, -1L, 0.0f, SystemClock.elapsedRealtime()).setActions(0L).setBufferedPosition(0L).setExtras(bundle2).setErrorMessage(legacyError.code, (CharSequence) Preconditions.checkNotNull(legacyError.message)).setExtras(legacyError.extras).build();
        }
        PlaybackException playerError = this.customPlaybackException;
        if (playerError == null) {
            playerError = playerWrapper.getPlayerError();
        }
        boolean z = playerWrapper.isCommandAvailable(16) && !playerWrapper.isCurrentMediaItemLive();
        boolean z2 = playerError != null || Util.shouldShowPlayButton(playerWrapper, this.playIfSuppressed);
        int iConvertToPlaybackStateCompatState = playerError != null ? 7 : LegacyConversions.convertToPlaybackStateCompatState(playerWrapper, z2);
        Player.Commands availableCommands = playerWrapper.getAvailableCommands();
        Player.Commands commands = this.playerCommandsForErrorState;
        if (commands != null) {
            commandsIntersect = MediaUtils.intersect(commands, availableCommands);
        } else {
            commandsIntersect = MediaUtils.intersect(this.availablePlayerCommands, availableCommands);
        }
        long jConvertCommandToPlaybackStateActions = 128;
        for (int i = 0; i < commandsIntersect.size(); i++) {
            jConvertCommandToPlaybackStateActions |= convertCommandToPlaybackStateActions(commandsIntersect.get(i), z2);
        }
        if (!this.mediaButtonPreferences.isEmpty() && CommandButton.containsButtonForSlot(this.customLayout, 2)) {
            jConvertCommandToPlaybackStateActions &= -17;
        }
        if (!this.mediaButtonPreferences.isEmpty() && CommandButton.containsButtonForSlot(this.customLayout, 3)) {
            jConvertCommandToPlaybackStateActions &= -33;
        }
        if (!z) {
            jConvertCommandToPlaybackStateActions &= -257;
        }
        long jConvertToQueueItemId = playerWrapper.isCommandAvailable(17) ? LegacyConversions.convertToQueueItemId(playerWrapper.getCurrentMediaItemIndex()) : -1L;
        float f = playerWrapper.getPlaybackParameters().speed;
        float f2 = (playerWrapper.isPlaying() && z) ? f : 0.0f;
        Bundle bundle3 = playerError != null ? new Bundle(playerError.extras) : new Bundle();
        if (playerError == null && legacyError != null) {
            bundle3.putAll(legacyError.extras);
        }
        bundle3.putAll(this.legacyExtras);
        bundle3.putFloat(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT, f);
        MediaItem currentMediaItemWithCommandCheck = playerWrapper.getCurrentMediaItemWithCommandCheck();
        if (currentMediaItemWithCommandCheck != null && !"".equals(currentMediaItemWithCommandCheck.mediaId)) {
            bundle3.putString("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID", currentMediaItemWithCommandCheck.mediaId);
        }
        long currentPosition = z ? playerWrapper.getCurrentPosition() : -1L;
        long bufferedPosition = z ? playerWrapper.getBufferedPosition() : -1L;
        PlaybackException playbackException = playerError;
        PlaybackStateCompat.Builder extras = new PlaybackStateCompat.Builder().setState(iConvertToPlaybackStateCompatState, currentPosition, f2, SystemClock.elapsedRealtime()).setActions(jConvertCommandToPlaybackStateActions).setActiveQueueItemId(jConvertToQueueItemId).setBufferedPosition(bufferedPosition).setExtras(bundle3);
        for (int i2 = 0; i2 < this.customLayout.size(); i2++) {
            CommandButton commandButton = this.customLayout.get(i2);
            SessionCommand sessionCommand = commandButton.sessionCommand;
            if (sessionCommand != null && commandButton.isEnabled && sessionCommand.commandCode == 0 && (CommandButton.isButtonCommandAvailable(commandButton, this.availableSessionCommands, commandsIntersect) || CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction))) {
                boolean z3 = commandButton.icon != 0;
                boolean z4 = commandButton.iconUri != null;
                if (z3 || z4 || !commandButton.extras.isEmpty()) {
                    bundle = new Bundle(sessionCommand.customExtras);
                } else {
                    bundle = sessionCommand.customExtras;
                }
                if (!commandButton.extras.isEmpty()) {
                    bundle.putAll(commandButton.extras);
                }
                if (z3) {
                    bundle.putInt(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_COMPAT, commandButton.icon);
                }
                if (z4) {
                    bundle.putString(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_URI_COMPAT, ((Uri) Preconditions.checkNotNull(commandButton.iconUri)).toString());
                }
                extras.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(sessionCommand.customAction, commandButton.displayName, commandButton.iconResId).setExtras(bundle).build());
            }
        }
        if (playbackException != null) {
            extras.setErrorMessage(LegacyConversions.convertToLegacyErrorCode(playbackException), playbackException.getMessage());
        } else if (legacyError != null) {
            extras.setErrorMessage(legacyError.code, legacyError.message);
        }
        return extras.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VolumeProviderCompat createVolumeProviderCompat(PlayerWrapper playerWrapper) {
        int i;
        if (playerWrapper.getDeviceInfo().playbackType == 0) {
            return null;
        }
        Player.Commands availableCommands = playerWrapper.getAvailableCommands();
        if (availableCommands.containsAny(26, 34)) {
            i = availableCommands.containsAny(25, 33) ? 2 : 1;
        } else {
            i = 0;
        }
        int i2 = i;
        Handler handler = new Handler(playerWrapper.getApplicationLooper());
        int deviceVolumeWithCommandCheck = playerWrapper.getDeviceVolumeWithCommandCheck();
        DeviceInfo deviceInfo = playerWrapper.getDeviceInfo();
        return new AnonymousClass3(i2, deviceInfo.maxVolume, deviceVolumeWithCommandCheck, deviceInfo.routingControllerId, handler, playerWrapper, 1);
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$3, reason: invalid class name */
    class AnonymousClass3 extends VolumeProviderCompat {
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ int val$legacyVolumeFlag;
        final /* synthetic */ PlayerWrapper val$player;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(int i, int i2, int i3, String str, Handler handler, PlayerWrapper playerWrapper, int i4) {
            super(i, i2, i3, str);
            this.val$handler = handler;
            this.val$player = playerWrapper;
            this.val$legacyVolumeFlag = i4;
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onSetVolumeTo(final int i) {
            Handler handler = this.val$handler;
            final PlayerWrapper playerWrapper = this.val$player;
            final int i2 = this.val$legacyVolumeFlag;
            Util.postOrRun(handler, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionLegacyStub.AnonymousClass3.lambda$onSetVolumeTo$0(playerWrapper, i, i2);
                }
            });
        }

        static /* synthetic */ void lambda$onSetVolumeTo$0(PlayerWrapper playerWrapper, int i, int i2) {
            if (playerWrapper.isCommandAvailable(25) || playerWrapper.isCommandAvailable(33)) {
                if (playerWrapper.isCommandAvailable(33)) {
                    playerWrapper.setDeviceVolume(i, i2);
                } else {
                    playerWrapper.setDeviceVolume(i);
                }
            }
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onAdjustVolume(final int i) {
            Handler handler = this.val$handler;
            final PlayerWrapper playerWrapper = this.val$player;
            final int i2 = this.val$legacyVolumeFlag;
            Util.postOrRun(handler, new Runnable() { // from class: androidx.media3.session.MediaSessionLegacyStub$3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionLegacyStub.AnonymousClass3.lambda$onAdjustVolume$1(playerWrapper, i, i2);
                }
            });
        }

        static /* synthetic */ void lambda$onAdjustVolume$1(PlayerWrapper playerWrapper, int i, int i2) {
            if (playerWrapper.isCommandAvailable(26) || playerWrapper.isCommandAvailable(34)) {
                if (i == -100) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.setDeviceMuted(true, i2);
                        return;
                    } else {
                        playerWrapper.setDeviceMuted(true);
                        return;
                    }
                }
                if (i == -1) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.decreaseDeviceVolume(i2);
                        return;
                    } else {
                        playerWrapper.decreaseDeviceVolume();
                        return;
                    }
                }
                if (i == 1) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.increaseDeviceVolume(i2);
                        return;
                    } else {
                        playerWrapper.increaseDeviceVolume();
                        return;
                    }
                }
                if (i == 100) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.setDeviceMuted(false, i2);
                        return;
                    } else {
                        playerWrapper.setDeviceMuted(false);
                        return;
                    }
                }
                if (i == 101) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.setDeviceMuted(!playerWrapper.isDeviceMutedWithCommandCheck(), i2);
                        return;
                    } else {
                        playerWrapper.setDeviceMuted(!playerWrapper.isDeviceMutedWithCommandCheck());
                        return;
                    }
                }
                Log.w("VolumeProviderCompat", "onAdjustVolume: Ignoring unknown direction: " + i);
            }
        }
    }

    private boolean needsButtonReservationWorkaroundForSeekbar(AndroidAutoConnectionStateObserver androidAutoConnectionStateObserver) {
        if (this.mayNeedButtonReservationWorkaroundForSeekbar) {
            return androidAutoConnectionStateObserver == null || !androidAutoConnectionStateObserver.isConnected();
        }
        return false;
    }

    private static boolean mayNeedButtonReservationWorkaroundForSeekbar(Context context) {
        if (Build.VERSION.SDK_INT >= 33 && !Util.isAutomotive(context)) {
            return Build.MANUFACTURER.equals("Google") || Build.MANUFACTURER.equals("motorola") || Build.MANUFACTURER.equals("vivo") || Build.MANUFACTURER.equals("Sony") || Build.MANUFACTURER.equals("Nothing") || Build.MANUFACTURER.equals(EnvironmentCompat.MEDIA_UNKNOWN);
        }
        return false;
    }

    private static final class LegacyError {
        public final int code;
        public final Bundle extras;
        public final boolean isFatal;
        public final String message;

        /* synthetic */ LegacyError(boolean z, int i, String str, Bundle bundle, AnonymousClass1 anonymousClass1) {
            this(z, i, str, bundle);
        }

        private LegacyError(boolean z, int i, String str, Bundle bundle) {
            this.isFatal = z;
            this.code = i;
            this.message = str;
            this.extras = bundle == null ? Bundle.EMPTY : bundle;
        }
    }

    private final class MediaButtonReceiver extends BroadcastReceiver {
        private MediaButtonReceiver() {
        }

        /* synthetic */ MediaButtonReceiver(MediaSessionLegacyStub mediaSessionLegacyStub, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            KeyEvent keyEvent;
            if (Objects.equals(intent.getAction(), "android.intent.action.MEDIA_BUTTON") && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
                MediaSessionLegacyStub.this.sessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
            }
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static void setMediaButtonBroadcastReceiver(MediaSessionCompat mediaSessionCompat, ComponentName componentName) {
            try {
                ((android.media.session.MediaSession) Preconditions.checkNotNull(mediaSessionCompat.getMediaSession())).setMediaButtonBroadcastReceiver(componentName);
            } catch (IllegalArgumentException e) {
                if (Build.MANUFACTURER.equals("motorola")) {
                    Log.e(MediaSessionLegacyStub.TAG, "caught IllegalArgumentException on a motorola device when attempting to set the media button broadcast receiver. See https://github.com/androidx/media/issues/1730 for details.", e);
                    return;
                }
                throw e;
            }
        }
    }
}

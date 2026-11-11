package androidx.media3.session;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.media.AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.base.Preconditions;
import j$.util.DesugarCollections;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaSessionService extends Service {
    public static final long DEFAULT_FOREGROUND_SERVICE_TIMEOUT_MS = 600000;
    public static final String SERVICE_INTERFACE = "androidx.media3.session.MediaSessionService";
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_AFTER_STOP_OR_ERROR = 3;
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_ALWAYS = 1;
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_NEVER = 2;
    private static final String TAG = "MSessionService";
    private DefaultActionFactory actionFactory;
    private Listener listener;
    private MediaNotificationManager mediaNotificationManager;
    private MediaSessionServiceStub stub;
    private final Object lock = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Map<String, MediaSession> sessions = new ArrayMap();
    private boolean defaultMethodCalled = false;

    public interface Listener {

        /* JADX INFO: renamed from: androidx.media3.session.MediaSessionService$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onForegroundServiceStartNotAllowedException(Listener _this) {
            }
        }

        void onForegroundServiceStartNotAllowedException();
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowNotificationForIdlePlayerMode {
    }

    public abstract MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.stub = new MediaSessionServiceStub(this);
    }

    public final void addSession(final MediaSession mediaSession) {
        MediaSession mediaSession2;
        Preconditions.checkNotNull(mediaSession, "session must not be null");
        boolean z = true;
        Preconditions.checkArgument(!mediaSession.isReleased(), "session is already released");
        synchronized (this.lock) {
            mediaSession2 = this.sessions.get(mediaSession.getId());
            if (mediaSession2 != null && mediaSession2 != mediaSession) {
                z = false;
            }
            Preconditions.checkArgument(z, "Session ID should be unique");
            this.sessions.put(mediaSession.getId(), mediaSession);
        }
        if (mediaSession2 == null) {
            Util.postOrRun(this.mainHandler, new Runnable() { // from class: androidx.media3.session.MediaSessionService$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7825lambda$addSession$0$androidxmedia3sessionMediaSessionService(mediaSession);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$addSession$0$androidx-media3-session-MediaSessionService, reason: not valid java name */
    /* synthetic */ void m7825lambda$addSession$0$androidxmedia3sessionMediaSessionService(MediaSession mediaSession) {
        getMediaNotificationManager().addSession(mediaSession);
        mediaSession.setListener(new MediaSessionListener());
    }

    public final void removeSession(final MediaSession mediaSession) {
        Preconditions.checkNotNull(mediaSession, "session must not be null");
        synchronized (this.lock) {
            Preconditions.checkArgument(this.sessions.containsKey(mediaSession.getId()), "session not found");
            this.sessions.remove(mediaSession.getId());
        }
        Util.postOrRun(this.mainHandler, new Runnable() { // from class: androidx.media3.session.MediaSessionService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7827x471eb3d0(mediaSession);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$removeSession$1$androidx-media3-session-MediaSessionService, reason: not valid java name */
    /* synthetic */ void m7827x471eb3d0(MediaSession mediaSession) {
        getMediaNotificationManager().removeSession(mediaSession);
        mediaSession.clearListener();
    }

    public final List<MediaSession> getSessions() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.sessions.values());
        }
        return arrayList;
    }

    public final boolean isSessionAdded(MediaSession mediaSession) {
        boolean zContainsKey;
        synchronized (this.lock) {
            zContainsKey = this.sessions.containsKey(mediaSession.getId());
        }
        return zContainsKey;
    }

    public final void setListener(Listener listener) {
        synchronized (this.lock) {
            this.listener = listener;
        }
    }

    public final void clearListener() {
        synchronized (this.lock) {
            this.listener = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String action;
        MediaSession mediaSessionOnGetSession;
        if (intent == null || (action = intent.getAction()) == null) {
            return null;
        }
        action.hashCode();
        if (action.equals(SERVICE_INTERFACE)) {
            return getServiceBinder();
        }
        if (!action.equals("android.media.browse.MediaBrowserService") || (mediaSessionOnGetSession = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo())) == null) {
            return null;
        }
        addSession(mediaSessionOnGetSession);
        return mediaSessionOnGetSession.getLegacyBrowserServiceBinder();
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i, int i2) {
        String customAction;
        if (intent == null) {
            return 1;
        }
        DefaultActionFactory actionFactory = getActionFactory();
        Uri data = intent.getData();
        MediaSession session = data != null ? MediaSession.getSession(data) : null;
        if (actionFactory.isMediaAction(intent)) {
            if (session == null) {
                session = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo());
                if (session == null) {
                    return 1;
                }
                addSession(session);
            }
            final MediaSessionImpl impl = session.getImpl();
            impl.getApplicationHandler().post(new Runnable() { // from class: androidx.media3.session.MediaSessionService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionService.lambda$onStartCommand$2(impl, intent);
                }
            });
        } else {
            if (session == null || !actionFactory.isCustomAction(intent) || (customAction = actionFactory.getCustomAction(intent)) == null) {
                return 1;
            }
            getMediaNotificationManager().onCustomAction(session, customAction, actionFactory.getCustomActionExtras(intent));
        }
        return 1;
    }

    static /* synthetic */ void lambda$onStartCommand$2(MediaSessionImpl mediaSessionImpl, Intent intent) {
        MediaSession.ControllerInfo mediaNotificationControllerInfo = mediaSessionImpl.getMediaNotificationControllerInfo();
        if (mediaNotificationControllerInfo == null) {
            mediaNotificationControllerInfo = createFallbackMediaButtonCaller(intent);
        }
        if (mediaSessionImpl.onMediaButtonEvent(mediaNotificationControllerInfo, intent)) {
            return;
        }
        Log.d(TAG, "Ignored unrecognized media button intent.");
    }

    private static MediaSession.ControllerInfo createFallbackMediaButtonCaller(Intent intent) {
        String packageName;
        ComponentName component = intent.getComponent();
        if (component != null) {
            packageName = component.getPackageName();
        } else {
            packageName = SERVICE_INTERFACE;
        }
        return new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(packageName, -1, -1), MediaLibraryInfo.VERSION_INT, 8, false, null, Bundle.EMPTY, 0, false);
    }

    public final void setForegroundServiceTimeoutMs(long j) {
        getMediaNotificationManager().setUserEngagedTimeoutMs(Util.constrainValue(j, 0L, 600000L));
    }

    public final void setShowNotificationForIdlePlayer(int i) {
        getMediaNotificationManager().setShowNotificationForIdlePlayer(i);
    }

    public final boolean isPlaybackOngoing() {
        return getMediaNotificationManager().isStartedInForeground();
    }

    public final void pauseAllPlayersAndStopSelf() {
        getMediaNotificationManager().disableUserEngagedTimeout();
        List<MediaSession> sessions = getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            sessions.get(i).getPlayer().setPlayWhenReady(false);
        }
        stopSelf();
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        if (isPlaybackOngoing() && isAnySessionPlaying()) {
            return;
        }
        pauseAllPlayersAndStopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MediaNotificationManager mediaNotificationManager = this.mediaNotificationManager;
        if (mediaNotificationManager != null) {
            mediaNotificationManager.disableUserEngagedTimeout();
        }
        MediaSessionServiceStub mediaSessionServiceStub = this.stub;
        if (mediaSessionServiceStub != null) {
            mediaSessionServiceStub.release();
            this.stub = null;
        }
    }

    @Deprecated
    public void onUpdateNotification(MediaSession mediaSession) {
        this.defaultMethodCalled = true;
    }

    public void onUpdateNotification(MediaSession mediaSession, boolean z) {
        onUpdateNotification(mediaSession);
        if (this.defaultMethodCalled) {
            getMediaNotificationManager().updateNotification(mediaSession, z);
        }
    }

    public final void triggerNotificationUpdate() {
        List<MediaSession> sessions = getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            onUpdateNotificationInternal(sessions.get(i), false);
        }
    }

    protected final void setMediaNotificationProvider(final MediaNotification.Provider provider) {
        Preconditions.checkNotNull(provider);
        Util.postOrRun(this.mainHandler, new Runnable() { // from class: androidx.media3.session.MediaSessionService$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7828xc8f44a78(provider);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setMediaNotificationProvider$3$androidx-media3-session-MediaSessionService, reason: not valid java name */
    /* synthetic */ void m7828xc8f44a78(MediaNotification.Provider provider) {
        getMediaNotificationManager(provider).setMediaNotificationProvider(provider);
    }

    IBinder getServiceBinder() {
        return ((MediaSessionServiceStub) Preconditions.checkNotNull(this.stub)).asBinder();
    }

    boolean onUpdateNotificationInternal(MediaSession mediaSession, boolean z) {
        try {
            onUpdateNotification(mediaSession, getMediaNotificationManager().shouldRunInForeground(z));
            return true;
        } catch (IllegalStateException e) {
            if (Build.VERSION.SDK_INT >= 31 && Api31.instanceOfForegroundServiceStartNotAllowedException(e)) {
                Log.e(TAG, "Failed to start foreground", e);
                onForegroundServiceStartNotAllowedException();
                return false;
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaNotificationManager getMediaNotificationManager() {
        return getMediaNotificationManager(null);
    }

    private MediaNotificationManager getMediaNotificationManager(MediaNotification.Provider provider) {
        if (this.mediaNotificationManager == null) {
            if (provider == null) {
                Preconditions.checkNotNull(getBaseContext(), "Accessing service context before onCreate()");
                provider = new DefaultMediaNotificationProvider.Builder(getApplicationContext()).build();
            }
            this.mediaNotificationManager = new MediaNotificationManager(this, provider, getActionFactory());
        }
        return this.mediaNotificationManager;
    }

    private DefaultActionFactory getActionFactory() {
        if (this.actionFactory == null) {
            this.actionFactory = new DefaultActionFactory(this);
        }
        return this.actionFactory;
    }

    private Listener getListener() {
        Listener listener;
        synchronized (this.lock) {
            listener = this.listener;
        }
        return listener;
    }

    private void onForegroundServiceStartNotAllowedException() {
        this.mainHandler.post(new Runnable() { // from class: androidx.media3.session.MediaSessionService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7826x9430fe8c();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onForegroundServiceStartNotAllowedException$4$androidx-media3-session-MediaSessionService, reason: not valid java name */
    /* synthetic */ void m7826x9430fe8c() {
        Listener listener = getListener();
        if (listener != null) {
            listener.onForegroundServiceStartNotAllowedException();
        }
    }

    private boolean isAnySessionPlaying() {
        List<MediaSession> sessions = getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getPlayer().isPlaying()) {
                return true;
            }
        }
        return false;
    }

    private final class MediaSessionListener implements MediaSession.Listener {
        private MediaSessionListener() {
        }

        @Override // androidx.media3.session.MediaSession.Listener
        public void onNotificationRefreshRequired(MediaSession mediaSession) {
            MediaSessionService.this.onUpdateNotificationInternal(mediaSession, false);
        }

        @Override // androidx.media3.session.MediaSession.Listener
        public boolean onPlayRequested(MediaSession mediaSession) {
            if (Build.VERSION.SDK_INT < 31 || Build.VERSION.SDK_INT >= 33 || MediaSessionService.this.getMediaNotificationManager().isStartedInForeground()) {
                return true;
            }
            return MediaSessionService.this.onUpdateNotificationInternal(mediaSession, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class MediaSessionServiceStub extends IMediaSessionService.Stub {
        private final Handler handler;
        private final Set<IMediaController> pendingControllers = DesugarCollections.synchronizedSet(new HashSet());
        private final WeakReference<MediaSessionService> serviceReference;

        public MediaSessionServiceStub(MediaSessionService mediaSessionService) {
            this.serviceReference = new WeakReference<>(mediaSessionService);
            this.handler = new Handler(mediaSessionService.getApplicationContext().getMainLooper());
        }

        @Override // androidx.media3.session.IMediaSessionService
        public void connect(final IMediaController iMediaController, Bundle bundle) {
            if (iMediaController == null || bundle == null) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            try {
                final ConnectionRequest connectionRequestFromBundle = ConnectionRequest.fromBundle(bundle);
                MediaSessionService mediaSessionService = this.serviceReference.get();
                if (mediaSessionService == null) {
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
                int callingPid = Binder.getCallingPid();
                int callingUid = Binder.getCallingUid();
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                if (callingPid == 0) {
                    callingPid = connectionRequestFromBundle.pid;
                }
                if (SessionUtil.checkPackageValidity(mediaSessionService, connectionRequestFromBundle.packageName, callingUid) != 0) {
                    Log.w(MediaSessionService.TAG, "Ignoring connection from invalid package name " + connectionRequestFromBundle.packageName + " (uid=" + callingUid + ")");
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
                final MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(connectionRequestFromBundle.packageName, callingPid, callingUid);
                final boolean zIsTrustedForMediaControl = MediaSessionManager.getSessionManager(mediaSessionService.getApplicationContext()).isTrustedForMediaControl(remoteUserInfo);
                this.pendingControllers.add(iMediaController);
                try {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.MediaSessionService$MediaSessionServiceStub$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m7829x7a28fad4(iMediaController, remoteUserInfo, connectionRequestFromBundle, zIsTrustedForMediaControl);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } catch (RuntimeException e) {
                Log.w(MediaSessionService.TAG, "Ignoring malformed Bundle for ConnectionRequest", e);
                SessionUtil.disconnectIMediaController(iMediaController);
            }
        }

        /* JADX INFO: renamed from: lambda$connect$0$androidx-media3-session-MediaSessionService$MediaSessionServiceStub, reason: not valid java name */
        /* synthetic */ void m7829x7a28fad4(IMediaController iMediaController, MediaSessionManager.RemoteUserInfo remoteUserInfo, ConnectionRequest connectionRequest, boolean z) {
            this.pendingControllers.remove(iMediaController);
            try {
                try {
                    MediaSessionService mediaSessionService = this.serviceReference.get();
                    if (mediaSessionService != null) {
                        MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, connectionRequest.libraryVersion, connectionRequest.controllerInterfaceVersion, z, new MediaSessionStub.Controller2Cb(iMediaController, connectionRequest.controllerInterfaceVersion), connectionRequest.connectionHints, connectionRequest.maxCommandsForMediaItems, true);
                        MediaSession mediaSessionOnGetSession = mediaSessionService.onGetSession(controllerInfo);
                        if (mediaSessionOnGetSession != null) {
                            mediaSessionService.addSession(mediaSessionOnGetSession);
                            mediaSessionOnGetSession.handleControllerConnectionFromService(iMediaController, controllerInfo);
                            return;
                        } else {
                            SessionUtil.disconnectIMediaController(iMediaController);
                            return;
                        }
                    }
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                } catch (Exception e) {
                    Log.w(MediaSessionService.TAG, "Failed to add a session to session service", e);
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
            } catch (Throwable th) {
                SessionUtil.disconnectIMediaController(iMediaController);
                throw th;
            }
            SessionUtil.disconnectIMediaController(iMediaController);
            throw th;
        }

        public void release() {
            this.serviceReference.clear();
            this.handler.removeCallbacksAndMessages(null);
            Iterator<IMediaController> it = this.pendingControllers.iterator();
            while (it.hasNext()) {
                SessionUtil.disconnectIMediaController(it.next());
            }
            this.pendingControllers.clear();
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static boolean instanceOfForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m7357m((Object) illegalStateException);
        }
    }
}

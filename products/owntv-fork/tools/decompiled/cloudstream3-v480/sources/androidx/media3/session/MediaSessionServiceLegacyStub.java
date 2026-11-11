package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
class MediaSessionServiceLegacyStub extends MediaBrowserServiceCompat {
    private static final String TAG = "MSSLegacyStub";
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final MediaSessionManager manager;
    private final MediaSessionImpl sessionImpl;

    public MediaSessionServiceLegacyStub(MediaSessionImpl mediaSessionImpl) {
        this.manager = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext());
        this.sessionImpl = mediaSessionImpl;
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public void initialize(MediaSessionCompat.Token token) {
        attachToBaseContext(this.sessionImpl.getContext());
        onCreate();
        setSessionToken(token);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaSessionManager.RemoteUserInfo currentBrowserInfo = getCurrentBrowserInfo();
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        final MediaSession.ControllerInfo controllerInfoCreateControllerInfo = createControllerInfo(currentBrowserInfo, bundle);
        final AtomicReference atomicReference = new AtomicReference();
        final ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaSessionServiceLegacyStub$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7830x22fee84d(atomicReference, controllerInfoCreateControllerInfo, conditionVariable);
            }
        });
        try {
            conditionVariable.block();
            MediaSession.ConnectionResult connectionResult = (MediaSession.ConnectionResult) atomicReference.get();
            if (!connectionResult.isAccepted) {
                return null;
            }
            this.connectedControllersManager.addController(currentBrowserInfo, controllerInfoCreateControllerInfo, connectionResult.availableSessionCommands, connectionResult.availablePlayerCommands);
            return MediaUtils.defaultBrowserRoot;
        } catch (InterruptedException e) {
            Log.e(TAG, "Couldn't get a result from onConnect", e);
            return null;
        }
    }

    /* JADX INFO: renamed from: lambda$onGetRoot$0$androidx-media3-session-MediaSessionServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7830x22fee84d(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, ConditionVariable conditionVariable) {
        atomicReference.set(this.sessionImpl.onConnectOnHandler(controllerInfo));
        conditionVariable.open();
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(null);
    }

    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.manager.isTrustedForMediaControl(remoteUserInfo), null, bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle), true);
    }

    public final MediaSessionManager getMediaSessionManager() {
        return this.manager;
    }

    public final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }
}

package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.util.ObjectsCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaRouteProviderService extends Service {
    static final boolean DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
    final MediaRouteProviderServiceImpl mImpl;
    MediaRouteProvider mProvider;
    private final MediaRouteProvider.Callback mProviderCallback;
    private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
    final Messenger mReceiveMessenger = new Messenger(this.mReceiveHandler);
    final PrivateHandler mPrivateHandler = new PrivateHandler();

    interface MediaRouteProviderServiceImpl {
        void attachBaseContext(Context context);

        MediaRouteProvider.Callback getProviderCallback();

        boolean onAddMemberRoute(Messenger messenger, int i, int i2, String str);

        IBinder onBind(Intent intent);

        void onBinderDied(Messenger messenger);

        boolean onCreateDynamicGroupRouteController(Messenger messenger, int i, int i2, String str);

        boolean onCreateRouteController(Messenger messenger, int i, int i2, String str, String str2);

        boolean onRegisterClient(Messenger messenger, int i, int i2, String str);

        boolean onReleaseRouteController(Messenger messenger, int i, int i2);

        boolean onRemoveMemberRoute(Messenger messenger, int i, int i2, String str);

        boolean onRouteControlRequest(Messenger messenger, int i, int i2, Intent intent);

        boolean onSelectRoute(Messenger messenger, int i, int i2);

        boolean onSetDiscoveryRequest(Messenger messenger, int i, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest);

        boolean onSetRouteVolume(Messenger messenger, int i, int i2, int i3);

        boolean onUnregisterClient(Messenger messenger, int i);

        boolean onUnselectRoute(Messenger messenger, int i, int i2, int i3);

        boolean onUpdateMemberRoutes(Messenger messenger, int i, int i2, List<String> list);

        boolean onUpdateRouteVolume(Messenger messenger, int i, int i2, int i3);
    }

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    @SuppressLint({"NewApi"})
    public MediaRouteProviderService() {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new MediaRouteProviderServiceImplApi30(this);
        } else {
            this.mImpl = new MediaRouteProviderServiceImplBase(this);
        }
        this.mProviderCallback = this.mImpl.getProviderCallback();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.mImpl.attachBaseContext(context);
    }

    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    void ensureProvider() {
        MediaRouteProvider mediaRouteProviderOnCreateMediaRouteProvider;
        if (this.mProvider != null || (mediaRouteProviderOnCreateMediaRouteProvider = onCreateMediaRouteProvider()) == null) {
            return;
        }
        String packageName = mediaRouteProviderOnCreateMediaRouteProvider.getMetadata().getPackageName();
        if (!packageName.equals(getPackageName())) {
            throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + packageName + ".  Service package name: " + getPackageName() + ".");
        }
        this.mProvider = mediaRouteProviderOnCreateMediaRouteProvider;
        mediaRouteProviderOnCreateMediaRouteProvider.setCallback(this.mProviderCallback);
    }

    @Override // android.app.Service
    public void onDestroy() {
        MediaRouteProvider mediaRouteProvider = this.mProvider;
        if (mediaRouteProvider != null) {
            mediaRouteProvider.setCallback(null);
        }
        super.onDestroy();
    }

    static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor mediaRouteProviderDescriptor, int i) {
        if (mediaRouteProviderDescriptor == null) {
            return null;
        }
        MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder(mediaRouteProviderDescriptor);
        builder.setRoutes(null);
        for (MediaRouteDescriptor mediaRouteDescriptor : mediaRouteProviderDescriptor.getRoutes()) {
            if (i >= mediaRouteDescriptor.getMinClientVersion() && i <= mediaRouteDescriptor.getMaxClientVersion()) {
                builder.addRoute(mediaRouteDescriptor);
            }
        }
        return builder.build().asBundle();
    }

    static void sendGenericFailure(Messenger messenger, int i) {
        if (i != 0) {
            sendReply(messenger, 0, i, 0, null, null);
        }
    }

    static void sendGenericSuccess(Messenger messenger, int i) {
        if (i != 0) {
            sendReply(messenger, 1, i, 0, null, null);
        }
    }

    static void sendReply(Messenger messenger, int i, int i2, int i3, Object obj, Bundle bundle) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.arg2 = i3;
        messageObtain.obj = obj;
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e) {
            Log.e("MediaRouteProviderSrv", "Could not send message to " + getClientId(messenger), e);
        }
    }

    static String getClientId(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }

    private final class PrivateHandler extends Handler {
        PrivateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            MediaRouteProviderService.this.mImpl.onBinderDied((Messenger) message.obj);
        }
    }

    private static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService mediaRouteProviderService) {
            this.mServiceRef = new WeakReference<>(mediaRouteProviderService);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x004e  */
        /* JADX WARN: Code duplicated, block: B:19:0x0052  */
        /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Instruction removed from duplicated block: B:19:0x0052, please report this as an issue */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            Messenger messenger = message.replyTo;
            if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                int i = message.what;
                int i2 = message.arg1;
                int i3 = message.arg2;
                Object obj = message.obj;
                Bundle bundlePeekData = message.peekData();
                if (i == 1 && Build.VERSION.SDK_INT >= 21) {
                    String[] packagesForUid = this.mServiceRef.get().getPackageManager().getPackagesForUid(message.sendingUid);
                    if (packagesForUid != null && packagesForUid.length > 0) {
                        str = packagesForUid[0];
                    }
                    if (processMessage(i, messenger, i2, i3, obj, bundlePeekData, str)) {
                        return;
                    }
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + i + ", requestId=" + i2 + ", arg=" + i3 + ", obj=" + obj + ", data=" + bundlePeekData);
                    }
                    MediaRouteProviderService.sendGenericFailure(messenger, i2);
                    return;
                }
                str = null;
                if (processMessage(i, messenger, i2, i3, obj, bundlePeekData, str)) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + i + ", requestId=" + i2 + ", arg=" + i3 + ", obj=" + obj + ", data=" + bundlePeekData);
                    }
                    MediaRouteProviderService.sendGenericFailure(messenger, i2);
                    return;
                }
                return;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", "Ignoring message without valid reply messenger.");
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private boolean processMessage(int i, Messenger messenger, int i2, int i3, Object obj, Bundle bundle, String str) {
            MediaRouteProviderService mediaRouteProviderService = this.mServiceRef.get();
            if (mediaRouteProviderService != null) {
                switch (i) {
                    case 1:
                        return mediaRouteProviderService.mImpl.onRegisterClient(messenger, i2, i3, str);
                    case 2:
                        return mediaRouteProviderService.mImpl.onUnregisterClient(messenger, i2);
                    case 3:
                        String string = bundle.getString("routeId");
                        String string2 = bundle.getString("routeGroupId");
                        if (string != null) {
                            return mediaRouteProviderService.mImpl.onCreateRouteController(messenger, i2, i3, string, string2);
                        }
                        break;
                    case 4:
                        return mediaRouteProviderService.mImpl.onReleaseRouteController(messenger, i2, i3);
                    case 5:
                        return mediaRouteProviderService.mImpl.onSelectRoute(messenger, i2, i3);
                    case 6:
                        return mediaRouteProviderService.mImpl.onUnselectRoute(messenger, i2, i3, bundle != null ? bundle.getInt("unselectReason", 0) : 0);
                    case 7:
                        int i4 = bundle.getInt("volume", -1);
                        if (i4 >= 0) {
                            return mediaRouteProviderService.mImpl.onSetRouteVolume(messenger, i2, i3, i4);
                        }
                        break;
                    case 8:
                        int i5 = bundle.getInt("volume", 0);
                        if (i5 != 0) {
                            return mediaRouteProviderService.mImpl.onUpdateRouteVolume(messenger, i2, i3, i5);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return mediaRouteProviderService.mImpl.onRouteControlRequest(messenger, i2, i3, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequestFromBundle = MediaRouteDiscoveryRequest.fromBundle((Bundle) obj);
                            MediaRouteProviderServiceImpl mediaRouteProviderServiceImpl = mediaRouteProviderService.mImpl;
                            if (mediaRouteDiscoveryRequestFromBundle == null || !mediaRouteDiscoveryRequestFromBundle.isValid()) {
                                mediaRouteDiscoveryRequestFromBundle = null;
                            }
                            return mediaRouteProviderServiceImpl.onSetDiscoveryRequest(messenger, i2, mediaRouteDiscoveryRequestFromBundle);
                        }
                        break;
                    case 11:
                        String string3 = bundle.getString("memberRouteId");
                        if (string3 != null) {
                            return mediaRouteProviderService.mImpl.onCreateDynamicGroupRouteController(messenger, i2, i3, string3);
                        }
                        break;
                    case 12:
                        String string4 = bundle.getString("memberRouteId");
                        if (string4 != null) {
                            return mediaRouteProviderService.mImpl.onAddMemberRoute(messenger, i2, i3, string4);
                        }
                        break;
                    case 13:
                        String string5 = bundle.getString("memberRouteId");
                        if (string5 != null) {
                            return mediaRouteProviderService.mImpl.onRemoveMemberRoute(messenger, i2, i3, string5);
                        }
                        break;
                    case 14:
                        ArrayList<String> stringArrayList = bundle.getStringArrayList("memberRouteIds");
                        if (stringArrayList != null) {
                            return mediaRouteProviderService.mImpl.onUpdateMemberRoutes(messenger, i2, i3, stringArrayList);
                        }
                        break;
                }
            }
            return false;
        }
    }

    static class MediaRouteProviderServiceImplBase implements MediaRouteProviderServiceImpl {
        MediaRouteDiscoveryRequest mBaseDiscoveryRequest;
        final ArrayList<ClientRecord> mClients = new ArrayList<>();
        MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
        final MediaRouteProviderService mService;

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
        }

        MediaRouteProviderServiceImplBase(MediaRouteProviderService mediaRouteProviderService) {
            this.mService = mediaRouteProviderService;
        }

        public MediaRouteProviderService getService() {
            return this.mService;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            if (!intent.getAction().equals("android.media.MediaRouteProviderService")) {
                return null;
            }
            this.mService.ensureProvider();
            if (this.mService.getMediaRouteProvider() != null) {
                return this.mService.mReceiveMessenger.getBinder();
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public MediaRouteProvider.Callback getProviderCallback() {
            return new ProviderCallbackBase();
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRegisterClient(Messenger messenger, int i, int i2, String str) {
            if (i2 < 1 || findClient(messenger) >= 0) {
                return false;
            }
            ClientRecord clientRecordCreateClientRecord = createClientRecord(messenger, i2, str);
            if (!clientRecordCreateClientRecord.register()) {
                return false;
            }
            this.mClients.add(clientRecordCreateClientRecord);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", clientRecordCreateClientRecord + ": Registered, version=" + i2);
            }
            if (i != 0) {
                MediaRouteProviderService.sendReply(messenger, 2, i, 2, MediaRouteProviderService.createDescriptorBundleForClientVersion(this.mService.getMediaRouteProvider().getDescriptor(), clientRecordCreateClientRecord.mVersion), null);
            }
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnregisterClient(Messenger messenger, int i) {
            int iFindClient = findClient(messenger);
            if (iFindClient < 0) {
                return false;
            }
            ClientRecord clientRecordRemove = this.mClients.remove(iFindClient);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", clientRecordRemove + ": Unregistered");
            }
            clientRecordRemove.dispose();
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void onBinderDied(Messenger messenger) {
            int iFindClient = findClient(messenger);
            if (iFindClient >= 0) {
                ClientRecord clientRecordRemove = this.mClients.remove(iFindClient);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", clientRecordRemove + ": Binder died");
                }
                clientRecordRemove.dispose();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateRouteController(Messenger messenger, int i, int i2, String str, String str2) {
            ClientRecord client = getClient(messenger);
            if (client == null || !client.createRouteController(str, str2, i2)) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route controller created, controllerId=" + i2 + ", routeId=" + str + ", routeGroupId=" + str2);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateDynamicGroupRouteController(Messenger messenger, int i, int i2, String str) {
            Bundle bundleCreateDynamicGroupRouteController;
            ClientRecord client = getClient(messenger);
            if (client == null || (bundleCreateDynamicGroupRouteController = client.createDynamicGroupRouteController(str, i2)) == null) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route controller created, controllerId=" + i2 + ", initialMemberRouteId=" + str);
            }
            MediaRouteProviderService.sendReply(messenger, 6, i, 2, bundleCreateDynamicGroupRouteController, null);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onAddMemberRoute(Messenger messenger, int i, int i2, String str) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i2);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onAddMemberRoute(str);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Added a member route, controllerId=" + i2 + ", memberId=" + str);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRemoveMemberRoute(Messenger messenger, int i, int i2, String str) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i2);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onRemoveMemberRoute(str);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Removed a member route, controllerId=" + i2 + ", memberId=" + str);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateMemberRoutes(Messenger messenger, int i, int i2, List<String> list) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i2);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onUpdateMemberRoutes(list);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Updated list of member routes, controllerId=" + i2 + ", memberIds=" + list);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onReleaseRouteController(Messenger messenger, int i, int i2) {
            ClientRecord client = getClient(messenger);
            if (client == null || !client.releaseRouteController(i2)) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route controller released, controllerId=" + i2);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSelectRoute(Messenger messenger, int i, int i2) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i2)) == null) {
                return false;
            }
            routeController.onSelect();
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route selected, controllerId=" + i2);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnselectRoute(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i2)) == null) {
                return false;
            }
            routeController.onUnselect(i3);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route unselected, controllerId=" + i2);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetRouteVolume(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i2)) == null) {
                return false;
            }
            routeController.onSetVolume(i3);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route volume changed, controllerId=" + i2 + ", volume=" + i3);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateRouteVolume(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i2)) == null) {
                return false;
            }
            routeController.onUpdateVolume(i3);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route volume updated, controllerId=" + i2 + ", delta=" + i3);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRouteControlRequest(final Messenger messenger, final int i, final int i2, final Intent intent) {
            MediaRouteProvider.RouteController routeController;
            final ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i2)) == null) {
                return false;
            }
            if (!routeController.onControlRequest(intent, i != 0 ? new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.1
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", client + ": Route control request succeeded, controllerId=" + i2 + ", intent=" + intent + ", data=" + bundle);
                    }
                    if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                        MediaRouteProviderService.sendReply(messenger, 3, i, 0, bundle, null);
                    }
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String str, Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", client + ": Route control request failed, controllerId=" + i2 + ", intent=" + intent + ", error=" + str + ", data=" + bundle);
                    }
                    if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                        if (str != null) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("error", str);
                            MediaRouteProviderService.sendReply(messenger, 4, i, 0, bundle, bundle2);
                            return;
                        }
                        MediaRouteProviderService.sendReply(messenger, 4, i, 0, bundle, null);
                    }
                }
            } : null)) {
                return false;
            }
            if (!MediaRouteProviderService.DEBUG) {
                return true;
            }
            Log.d("MediaRouteProviderSrv", client + ": Route control request delivered, controllerId=" + i2 + ", intent=" + intent);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetDiscoveryRequest(Messenger messenger, int i, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            boolean discoveryRequest = client.setDiscoveryRequest(mediaRouteDiscoveryRequest);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Set discovery request, request=" + mediaRouteDiscoveryRequest + ", actuallyChanged=" + discoveryRequest + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i);
            return true;
        }

        void sendDescriptorChanged(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            int size = this.mClients.size();
            for (int i = 0; i < size; i++) {
                ClientRecord clientRecord = this.mClients.get(i);
                MediaRouteProviderService.sendReply(clientRecord.mMessenger, 5, 0, 0, MediaRouteProviderService.createDescriptorBundleForClientVersion(mediaRouteProviderDescriptor, clientRecord.mVersion), null);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", clientRecord + ": Sent descriptor change event, descriptor=" + mediaRouteProviderDescriptor);
                }
            }
        }

        boolean setBaseDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            if (ObjectsCompat.equals(this.mBaseDiscoveryRequest, mediaRouteDiscoveryRequest)) {
                return false;
            }
            this.mBaseDiscoveryRequest = mediaRouteDiscoveryRequest;
            return updateCompositeDiscoveryRequest();
        }

        boolean updateCompositeDiscoveryRequest() {
            MediaRouteSelector.Builder builder;
            boolean zIsActiveScan;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mBaseDiscoveryRequest;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest2 = null;
            if (mediaRouteDiscoveryRequest != null) {
                zIsActiveScan = mediaRouteDiscoveryRequest.isActiveScan();
                builder = new MediaRouteSelector.Builder(this.mBaseDiscoveryRequest.getSelector());
            } else {
                builder = null;
                zIsActiveScan = false;
            }
            int size = this.mClients.size();
            for (int i = 0; i < size; i++) {
                MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest3 = this.mClients.get(i).mDiscoveryRequest;
                if (mediaRouteDiscoveryRequest3 != null && (!mediaRouteDiscoveryRequest3.getSelector().isEmpty() || mediaRouteDiscoveryRequest3.isActiveScan())) {
                    zIsActiveScan |= mediaRouteDiscoveryRequest3.isActiveScan();
                    if (mediaRouteDiscoveryRequest2 == null) {
                        mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest3;
                    } else {
                        if (builder == null) {
                            builder = new MediaRouteSelector.Builder(mediaRouteDiscoveryRequest2.getSelector());
                        }
                        builder.addSelector(mediaRouteDiscoveryRequest3.getSelector());
                    }
                }
            }
            if (builder != null) {
                mediaRouteDiscoveryRequest2 = new MediaRouteDiscoveryRequest(builder.build(), zIsActiveScan);
            }
            if (ObjectsCompat.equals(this.mCompositeDiscoveryRequest, mediaRouteDiscoveryRequest2)) {
                return false;
            }
            this.mCompositeDiscoveryRequest = mediaRouteDiscoveryRequest2;
            this.mService.getMediaRouteProvider().setDiscoveryRequest(mediaRouteDiscoveryRequest2);
            return true;
        }

        private ClientRecord getClient(Messenger messenger) {
            int iFindClient = findClient(messenger);
            if (iFindClient >= 0) {
                return this.mClients.get(iFindClient);
            }
            return null;
        }

        int findClient(Messenger messenger) {
            int size = this.mClients.size();
            for (int i = 0; i < size; i++) {
                if (this.mClients.get(i).hasMessenger(messenger)) {
                    return i;
                }
            }
            return -1;
        }

        class ClientRecord implements IBinder.DeathRecipient {
            public MediaRouteDiscoveryRequest mDiscoveryRequest;
            public final Messenger mMessenger;
            public final String mPackageName;
            public final int mVersion;
            final SparseArray<MediaRouteProvider.RouteController> mControllers = new SparseArray<>();
            final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord.1
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                    ClientRecord.this.sendDynamicRouteDescriptors(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            };

            ClientRecord(Messenger messenger, int i, String str) {
                this.mMessenger = messenger;
                this.mVersion = i;
                this.mPackageName = str;
            }

            public boolean register() {
                try {
                    this.mMessenger.getBinder().linkToDeath(this, 0);
                    return true;
                } catch (RemoteException unused) {
                    binderDied();
                    return false;
                }
            }

            public void dispose() {
                int size = this.mControllers.size();
                for (int i = 0; i < size; i++) {
                    this.mControllers.valueAt(i).onRelease();
                }
                this.mControllers.clear();
                this.mMessenger.getBinder().unlinkToDeath(this, 0);
                setDiscoveryRequest(null);
            }

            public boolean hasMessenger(Messenger messenger) {
                return this.mMessenger.getBinder() == messenger.getBinder();
            }

            public boolean createRouteController(String str, String str2, int i) {
                MediaRouteProvider.RouteController routeControllerOnCreateRouteController;
                if (this.mControllers.indexOfKey(i) >= 0) {
                    return false;
                }
                if (str2 == null) {
                    routeControllerOnCreateRouteController = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(str);
                } else {
                    routeControllerOnCreateRouteController = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(str, str2);
                }
                if (routeControllerOnCreateRouteController == null) {
                    return false;
                }
                this.mControllers.put(i, routeControllerOnCreateRouteController);
                return true;
            }

            public Bundle createDynamicGroupRouteController(String str, int i) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerOnCreateDynamicGroupRouteController;
                if (this.mControllers.indexOfKey(i) >= 0 || (dynamicGroupRouteControllerOnCreateDynamicGroupRouteController = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateDynamicGroupRouteController(str)) == null) {
                    return null;
                }
                dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(MediaRouteProviderServiceImplBase.this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
                this.mControllers.put(i, dynamicGroupRouteControllerOnCreateDynamicGroupRouteController);
                Bundle bundle = new Bundle();
                bundle.putString("groupableTitle", dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.getGroupableSelectionTitle());
                bundle.putString("transferableTitle", dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.getTransferableSectionTitle());
                return bundle;
            }

            public boolean releaseRouteController(int i) {
                MediaRouteProvider.RouteController routeController = this.mControllers.get(i);
                if (routeController == null) {
                    return false;
                }
                this.mControllers.remove(i);
                routeController.onRelease();
                return true;
            }

            public MediaRouteProvider.RouteController getRouteController(int i) {
                return this.mControllers.get(i);
            }

            public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
                if (ObjectsCompat.equals(this.mDiscoveryRequest, mediaRouteDiscoveryRequest)) {
                    return false;
                }
                this.mDiscoveryRequest = mediaRouteDiscoveryRequest;
                return MediaRouteProviderServiceImplBase.this.updateCompositeDiscoveryRequest();
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                MediaRouteProviderServiceImplBase.this.mService.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
            }

            public String toString() {
                return MediaRouteProviderService.getClientId(this.mMessenger);
            }

            void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                int iIndexOfValue = this.mControllers.indexOfValue(dynamicGroupRouteController);
                if (iIndexOfValue < 0) {
                    Log.w("MediaRouteProviderSrv", "Ignoring unknown dynamic group route controller: " + dynamicGroupRouteController);
                    return;
                }
                int iKeyAt = this.mControllers.keyAt(iIndexOfValue);
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toBundle());
                }
                Bundle bundle = new Bundle();
                if (mediaRouteDescriptor != null) {
                    bundle.putParcelable("groupRoute", mediaRouteDescriptor.asBundle());
                }
                bundle.putParcelableArrayList("dynamicRoutes", arrayList);
                MediaRouteProviderService.sendReply(this.mMessenger, 7, 0, iKeyAt, bundle, null);
            }
        }

        ClientRecord createClientRecord(Messenger messenger, int i, String str) {
            return new ClientRecord(messenger, i, str);
        }

        class ProviderCallbackBase extends MediaRouteProvider.Callback {
            ProviderCallbackBase() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                MediaRouteProviderServiceImplBase.this.sendDescriptorChanged(mediaRouteProviderDescriptor);
            }
        }
    }

    static class MediaRouteProviderServiceImplApi30 extends MediaRouteProviderServiceImplBase {
        final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener;
        MediaRoute2ProviderServiceAdapter mMR2ProviderServiceAdapter;
        final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$new$0$MediaRouteProviderService$MediaRouteProviderServiceImplApi30(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection collection) {
            this.mMR2ProviderServiceAdapter.setDynamicRouteDescriptor(dynamicGroupRouteController, mediaRouteDescriptor, collection);
        }

        MediaRouteProviderServiceImplApi30(MediaRouteProviderService mediaRouteProviderService) {
            super(mediaRouteProviderService);
            this.mRouteIdToControllerMap = new ArrayMap();
            this.mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRouteProviderService$MediaRouteProviderServiceImplApi30$5ZnMIBnILgRv9MPxLYf0aIsoP5k
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public final void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection collection) {
                    this.f$0.lambda$new$0$MediaRouteProviderService$MediaRouteProviderServiceImplApi30(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            };
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            this.mService.ensureProvider();
            if (this.mMR2ProviderServiceAdapter == null) {
                this.mMR2ProviderServiceAdapter = new MediaRoute2ProviderServiceAdapter(this);
                if (this.mService.getBaseContext() != null) {
                    this.mMR2ProviderServiceAdapter.attachBaseContext(this.mService);
                }
            }
            IBinder iBinderOnBind = super.onBind(intent);
            return iBinderOnBind != null ? iBinderOnBind : this.mMR2ProviderServiceAdapter.onBind(intent);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
            MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter = this.mMR2ProviderServiceAdapter;
            if (mediaRoute2ProviderServiceAdapter != null) {
                mediaRoute2ProviderServiceAdapter.attachBaseContext(context);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        void sendDescriptorChanged(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            super.sendDescriptorChanged(mediaRouteProviderDescriptor);
            this.mMR2ProviderServiceAdapter.setProviderDescriptor(mediaRouteProviderDescriptor);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        MediaRouteProviderServiceImplBase.ClientRecord createClientRecord(Messenger messenger, int i, String str) {
            return new ClientRecord(messenger, i, str);
        }

        MediaRouteProvider.RouteController getControllerForRouteId(String str) {
            return this.mRouteIdToControllerMap.get(str);
        }

        MediaRouteProvider.RouteController createRouteControllerWithoutClient(String str, String str2) {
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController;
            MediaRouteProvider.RouteController routeController = this.mRouteIdToControllerMap.get(str);
            if (routeController != null) {
                return routeController;
            }
            if (str2 == null) {
                routeControllerOnCreateRouteController = this.mService.getMediaRouteProvider().onCreateRouteController(str);
            } else {
                routeControllerOnCreateRouteController = this.mService.getMediaRouteProvider().onCreateRouteController(str, str2);
            }
            if (routeControllerOnCreateRouteController != null) {
                this.mRouteIdToControllerMap.put(str, routeControllerOnCreateRouteController);
            }
            return routeControllerOnCreateRouteController;
        }

        boolean releaseRouteControllerForRouteId(String str) {
            MediaRouteProvider.RouteController routeControllerRemove = this.mRouteIdToControllerMap.remove(str);
            if (routeControllerRemove == null) {
                return false;
            }
            routeControllerRemove.onUnselect(0);
            routeControllerRemove.onRelease();
            return true;
        }

        void setDynamicRoutesChangedListener(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
            dynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
        }

        class ClientRecord extends MediaRouteProviderServiceImplBase.ClientRecord {
            ClientRecord(Messenger messenger, int i, String str) {
                super(messenger, i, str);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean createRouteController(String str, String str2, int i) {
                MediaRouteProvider.RouteController routeController = MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.get(str);
                if (routeController != null) {
                    this.mControllers.put(i, routeController);
                    return true;
                }
                boolean zCreateRouteController = super.createRouteController(str, str2, i);
                if (str2 == null && zCreateRouteController && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.addRouteController(this.mControllers.get(i), i, this.mPackageName, str);
                }
                if (zCreateRouteController) {
                    MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.put(str, this.mControllers.get(i));
                }
                return zCreateRouteController;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDynamicGroupRouteController(String str, int i) {
                Bundle bundleCreateDynamicGroupRouteController = super.createDynamicGroupRouteController(str, i);
                if (bundleCreateDynamicGroupRouteController != null && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.addRouteController(this.mControllers.get(i), i, this.mPackageName, str);
                }
                return bundleCreateDynamicGroupRouteController;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean releaseRouteController(int i) {
                MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.removeRouteController(i);
                MediaRouteProvider.RouteController routeController = this.mControllers.get(i);
                if (routeController != null) {
                    for (Map.Entry<String, MediaRouteProvider.RouteController> entry : MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.entrySet()) {
                        if (entry.getValue() == routeController) {
                            MediaRouteProviderServiceImplApi30.this.mRouteIdToControllerMap.remove(entry.getKey());
                            break;
                        }
                    }
                }
                return super.releaseRouteController(i);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                super.sendDynamicRouteDescriptors(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter = MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter;
                if (mediaRoute2ProviderServiceAdapter != null) {
                    mediaRoute2ProviderServiceAdapter.setDynamicRouteDescriptor(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            }
        }
    }
}

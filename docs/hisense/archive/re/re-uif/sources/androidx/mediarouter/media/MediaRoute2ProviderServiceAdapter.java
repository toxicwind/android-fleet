package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
class MediaRoute2ProviderServiceAdapter extends MediaRoute2ProviderService {
    static final boolean DEBUG = Log.isLoggable("MR2ProviderService", 3);
    private volatile MediaRouteProviderDescriptor mProviderDescriptor;
    final MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mServiceImpl;
    private final Object mLock = new Object();
    final Map<String, MediaRouteProvider.DynamicGroupRouteController> mControllers = new ArrayMap();
    final SparseArray<String> mSessionIdMap = new SparseArray<>();
    final Map<String, Messenger> mMessengers = new ArrayMap();

    static /* synthetic */ MediaRoute2Info lambda$setProviderDescriptor$1(MediaRoute2Info mediaRoute2Info) {
        return mediaRoute2Info;
    }

    static /* synthetic */ MediaRoute2Info lambda$setProviderDescriptor$2(MediaRoute2Info mediaRoute2Info, MediaRoute2Info mediaRoute2Info2) {
        return mediaRoute2Info2;
    }

    MediaRoute2ProviderServiceAdapter(MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mediaRouteProviderServiceImplApi30) {
        this.mServiceImpl = mediaRouteProviderServiceImplApi30;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetRouteVolume(long j, String str, int i) {
        MediaRouteProvider.RouteController controllerForRouteId = this.mServiceImpl.getControllerForRouteId(str);
        if (controllerForRouteId == null) {
            Log.w("MR2ProviderService", "onSetRouteVolume: Couldn't find a controller for routeId=" + str);
            notifyRequestFailed(j, 3);
            return;
        }
        controllerForRouteId.onSetVolume(i);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetSessionVolume(long j, String str, int i) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a session");
            notifyRequestFailed(j, 4);
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController controller = getController(str);
        if (controller == null) {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a controller");
            notifyRequestFailed(j, 3);
        } else {
            controller.onSetVolume(i);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onCreateSession(long j, String str, String str2, Bundle bundle) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerProxy;
        MediaRouteProvider mediaRouteProvider = getMediaRouteProvider();
        MediaRouteDescriptor routeDescriptor = getRouteDescriptor(str2, "onCreateSession");
        if (routeDescriptor == null) {
            notifyRequestFailed(j, 3);
            return;
        }
        if (this.mProviderDescriptor.supportsDynamicGroupRoute()) {
            dynamicGroupRouteControllerProxy = mediaRouteProvider.onCreateDynamicGroupRouteController(str2);
            if (dynamicGroupRouteControllerProxy == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a dynamic controller");
                notifyRequestFailed(j, 1);
                return;
            }
        } else {
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController = mediaRouteProvider.onCreateRouteController(str2);
            if (routeControllerOnCreateRouteController == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a controller");
                notifyRequestFailed(j, 1);
                return;
            }
            dynamicGroupRouteControllerProxy = new DynamicGroupRouteControllerProxy(routeControllerOnCreateRouteController);
        }
        String strAssignSessionId = assignSessionId(dynamicGroupRouteControllerProxy);
        dynamicGroupRouteControllerProxy.onSelect();
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(strAssignSessionId, str).addSelectedRoute(str2).setName(routeDescriptor.getName()).setVolumeHandling(routeDescriptor.getVolumeHandling()).setVolume(routeDescriptor.getVolume()).setVolumeMax(routeDescriptor.getVolumeMax());
        Messenger messenger = new Messenger(new IncomingHandler(this, strAssignSessionId));
        this.mMessengers.put(strAssignSessionId, messenger);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", messenger);
        volumeMax.setControlHints(bundle2).build();
        notifySessionCreated(j, volumeMax.build());
        this.mServiceImpl.setDynamicRoutesChangedListener(dynamicGroupRouteControllerProxy);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onReleaseSession(long j, String str) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerRemove;
        RoutingSessionInfo sessionInfo = getSessionInfo(str);
        if (sessionInfo == null) {
            return;
        }
        updateMemberRouteControllers(null, sessionInfo, null);
        synchronized (this.mLock) {
            dynamicGroupRouteControllerRemove = this.mControllers.remove(str);
            this.mMessengers.remove(str);
        }
        if (dynamicGroupRouteControllerRemove == null) {
            Log.w("MR2ProviderService", "onReleaseSession: Couldn't find a controller");
            notifyRequestFailed(j, 4);
        } else {
            dynamicGroupRouteControllerRemove.onUnselect(0);
            dynamicGroupRouteControllerRemove.onRelease();
            notifySessionReleased(str);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSelectRoute(long j, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (getRouteDescriptor(str2, "onSelectRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(str);
            if (controller == null) {
                Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            } else {
                controller.onAddMemberRoute(str2);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDeselectRoute(long j, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (getRouteDescriptor(str2, "onDeselectRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(str);
            if (controller == null) {
                Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            } else {
                controller.onRemoveMemberRoute(str2);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onTransferToRoute(long j, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (getRouteDescriptor(str2, "onTransferToRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController controller = getController(str);
            if (controller == null) {
                Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            } else {
                controller.onUpdateMemberRoutes(Collections.singletonList(str2));
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDiscoveryPreferenceChanged(RouteDiscoveryPreference routeDiscoveryPreference) {
        MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
        builder.addControlCategories((Collection) routeDiscoveryPreference.getPreferredFeatures().stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$nHCoRGqeXOfAyZcF9QiL0A7Yx-o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toControlCategory((String) obj);
            }
        }).collect(Collectors.toList()));
        this.mServiceImpl.setBaseDiscoveryRequest(new MediaRouteDiscoveryRequest(builder.build(), routeDiscoveryPreference.shouldPerformActiveScan()));
    }

    public void setProviderDescriptor(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        this.mProviderDescriptor = mediaRouteProviderDescriptor;
        notifyRoutes(((Map) (mediaRouteProviderDescriptor == null ? Collections.emptyList() : mediaRouteProviderDescriptor.getRoutes()).stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$Nj9OYR6TduhgFYKcn-h4bkjilY0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toFwkMediaRoute2Info((MediaRouteDescriptor) obj);
            }
        }).filter(new Predicate() { // from class: androidx.mediarouter.media.-$$Lambda$8fo3R-Przkq5mg2wxR3lAN3cgNY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((MediaRoute2Info) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$zA6-X6GUoq4-2PKz5R8c7BbTrQE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaRoute2Info) obj).getId();
            }
        }, new Function() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$7qAEsKlKB806oaKa6yx0w_wmnBk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj;
                MediaRoute2ProviderServiceAdapter.lambda$setProviderDescriptor$1(mediaRoute2Info);
                return mediaRoute2Info;
            }
        }, new BinaryOperator() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRoute2ProviderServiceAdapter$kAB5NMaZMpZ3LLziosuBx-l4x40
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj2;
                MediaRoute2ProviderServiceAdapter.lambda$setProviderDescriptor$2((MediaRoute2Info) obj, mediaRoute2Info);
                return mediaRoute2Info;
            }
        }))).values());
    }

    private MediaRouteProvider.DynamicGroupRouteController getController(String str) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController;
        synchronized (this.mLock) {
            dynamicGroupRouteController = this.mControllers.get(str);
        }
        return dynamicGroupRouteController;
    }

    private MediaRouteDescriptor getRouteDescriptor(String str, String str2) {
        if (getMediaRouteProvider() == null || this.mProviderDescriptor == null) {
            Log.w("MR2ProviderService", str2 + ": no provider info");
            return null;
        }
        for (MediaRouteDescriptor mediaRouteDescriptor : this.mProviderDescriptor.getRoutes()) {
            if (TextUtils.equals(mediaRouteDescriptor.getId(), str)) {
                return mediaRouteDescriptor;
            }
        }
        Log.w("MR2ProviderService", str2 + ": Couldn't find a route : " + str);
        return null;
    }

    public void setDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
        String key;
        synchronized (this.mLock) {
            Iterator<Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController>> it = this.mControllers.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    key = null;
                    break;
                }
                Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController> next = it.next();
                if (next.getValue() == dynamicGroupRouteController) {
                    key = next.getKey();
                    break;
                }
            }
        }
        if (key == null) {
            Log.w("MR2ProviderService", "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo sessionInfo = getSessionInfo(key);
        if (sessionInfo == null) {
            Log.w("MR2ProviderService", "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo.Builder builderClearTransferableRoutes = new RoutingSessionInfo.Builder(sessionInfo).clearSelectedRoutes().clearSelectableRoutes().clearDeselectableRoutes().clearTransferableRoutes();
        if (mediaRouteDescriptor != null) {
            builderClearTransferableRoutes.setName(mediaRouteDescriptor.getName()).setVolume(mediaRouteDescriptor.getVolume()).setVolumeMax(mediaRouteDescriptor.getVolumeMax()).setVolumeHandling(mediaRouteDescriptor.getVolumeHandling());
        }
        for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
            String id = dynamicRouteDescriptor.getRouteDescriptor().getId();
            int i = dynamicRouteDescriptor.mSelectionState;
            if (i == 2 || i == 3) {
                builderClearTransferableRoutes.addSelectedRoute(id);
            }
            if (dynamicRouteDescriptor.isGroupable()) {
                builderClearTransferableRoutes.addSelectableRoute(id);
            }
            if (dynamicRouteDescriptor.isUnselectable()) {
                builderClearTransferableRoutes.addDeselectableRoute(id);
            }
            if (dynamicRouteDescriptor.isTransferable()) {
                builderClearTransferableRoutes.addTransferableRoute(id);
            }
        }
        RoutingSessionInfo routingSessionInfoBuild = builderClearTransferableRoutes.build();
        updateMemberRouteControllers(mediaRouteDescriptor.getId(), sessionInfo, routingSessionInfoBuild);
        notifySessionUpdated(routingSessionInfoBuild);
    }

    void onControlRequest(final Messenger messenger, final int i, final String str, final Intent intent) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onCustomCommand: Couldn't find a session");
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController controller = getController(str);
        if (controller == null) {
            Log.w("MR2ProviderService", "onControlRequest: Couldn't find a controller");
            notifyRequestFailed(i, 3);
        } else {
            controller.onControlRequest(intent, new MediaRouter.ControlRequestCallback(this) { // from class: androidx.mediarouter.media.MediaRoute2ProviderServiceAdapter.1
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle bundle) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d("MR2ProviderService", "Route control request succeeded, sessionId=" + str + ", intent=" + intent + ", data=" + bundle);
                    }
                    sendReply(messenger, 3, i, 0, bundle, null);
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String str2, Bundle bundle) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d("MR2ProviderService", "Route control request failed, sessionId=" + str + ", intent=" + intent + ", error=" + str2 + ", data=" + bundle);
                    }
                    if (str2 != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("error", str2);
                        sendReply(messenger, 4, i, 0, bundle, bundle2);
                        return;
                    }
                    sendReply(messenger, 4, i, 0, bundle, null);
                }

                void sendReply(Messenger messenger2, int i2, int i3, int i4, Object obj, Bundle bundle) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = i2;
                    messageObtain.arg1 = i3;
                    messageObtain.arg2 = i4;
                    messageObtain.obj = obj;
                    messageObtain.setData(bundle);
                    try {
                        messenger2.send(messageObtain);
                    } catch (DeadObjectException unused) {
                    } catch (RemoteException e) {
                        Log.e("MR2ProviderService", "Could not send message to the client.", e);
                    }
                }
            });
        }
    }

    void updateMemberRouteControllers(String str, RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
        List<String> selectedRoutes;
        List<String> selectedRoutes2;
        if (routingSessionInfo == null) {
            selectedRoutes = Collections.emptyList();
        } else {
            selectedRoutes = routingSessionInfo.getSelectedRoutes();
        }
        if (routingSessionInfo2 == null) {
            selectedRoutes2 = Collections.emptyList();
        } else {
            selectedRoutes2 = routingSessionInfo2.getSelectedRoutes();
        }
        for (String str2 : selectedRoutes2) {
            if (this.mServiceImpl.getControllerForRouteId(str2) == null) {
                this.mServiceImpl.createRouteControllerWithoutClient(str2, str).onSelect();
            }
        }
        for (String str3 : selectedRoutes) {
            if (!selectedRoutes2.contains(str3)) {
                this.mServiceImpl.releaseRouteControllerForRouteId(str3);
            }
        }
    }

    void addRouteController(MediaRouteProvider.RouteController routeController, int i, String str, String str2) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerProxy;
        MediaRouteDescriptor routeDescriptor = getRouteDescriptor(str2, "addRouteController");
        if (routeDescriptor == null) {
            return;
        }
        if (routeController instanceof MediaRouteProvider.DynamicGroupRouteController) {
            dynamicGroupRouteControllerProxy = (MediaRouteProvider.DynamicGroupRouteController) routeController;
        } else {
            dynamicGroupRouteControllerProxy = new DynamicGroupRouteControllerProxy(routeController);
        }
        String strAssignSessionId = assignSessionId(dynamicGroupRouteControllerProxy);
        this.mSessionIdMap.put(i, strAssignSessionId);
        notifySessionCreated(0L, new RoutingSessionInfo.Builder(strAssignSessionId, str).addSelectedRoute(str2).setName(routeDescriptor.getName()).setVolumeHandling(routeDescriptor.getVolumeHandling()).setVolume(routeDescriptor.getVolume()).setVolumeMax(routeDescriptor.getVolumeMax()).build());
    }

    void removeRouteController(int i) {
        String str = this.mSessionIdMap.get(i);
        if (str == null) {
            return;
        }
        this.mSessionIdMap.remove(i);
        synchronized (this.mLock) {
            this.mControllers.remove(str);
            notifySessionReleased(str);
        }
    }

    private MediaRouteProvider getMediaRouteProvider() {
        MediaRouteProviderService service = this.mServiceImpl.getService();
        if (service == null) {
            return null;
        }
        return service.getMediaRouteProvider();
    }

    private String assignSessionId(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
        String string;
        synchronized (this.mLock) {
            do {
                string = UUID.randomUUID().toString();
            } while (this.mControllers.containsKey(string));
            this.mControllers.put(string, dynamicGroupRouteController);
        }
        return string;
    }

    private static class DynamicGroupRouteControllerProxy extends MediaRouteProvider.DynamicGroupRouteController {
        private final MediaRouteProvider.RouteController mRouteController;

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> list) {
        }

        DynamicGroupRouteControllerProxy(MediaRouteProvider.RouteController routeController) {
            this.mRouteController = routeController;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRouteController.onRelease();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mRouteController.onSelect();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int i) {
            this.mRouteController.onUnselect(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i) {
            this.mRouteController.onSetVolume(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i) {
            this.mRouteController.onUpdateVolume(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback controlRequestCallback) {
            return this.mRouteController.onControlRequest(intent, controlRequestCallback);
        }
    }

    static class IncomingHandler extends Handler {
        private final MediaRoute2ProviderServiceAdapter mServiceAdapter;
        private final String mSessionId;

        IncomingHandler(MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter, String str) {
            super(Looper.myLooper());
            this.mServiceAdapter = mediaRoute2ProviderServiceAdapter;
            this.mSessionId = str;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Messenger messenger = message.replyTo;
            int i = message.what;
            int i2 = message.arg1;
            Object obj = message.obj;
            if (i == 9 && (obj instanceof Intent)) {
                this.mServiceAdapter.onControlRequest(messenger, i2, this.mSessionId, (Intent) obj);
            }
        }
    }
}

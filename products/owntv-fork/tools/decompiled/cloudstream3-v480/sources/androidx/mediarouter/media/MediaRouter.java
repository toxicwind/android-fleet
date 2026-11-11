package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Resources;
import android.media.RemoteControlClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.util.ObjectsCompat;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaRouter {
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    public static final int REASON_DISCONNECT_CALLED = 1;
    public static final int REASON_FAILED_TO_CREATE_DYNAMIC_GROUP_ROUTE_CONTROLLER = 6;
    public static final int REASON_REJECTED_FOR_SELECTED_ROUTE = 4;
    public static final int REASON_ROUTE_CONNECTION_TIMEOUT = 7;
    public static final int REASON_ROUTE_NOT_AVAILABLE = 2;
    public static final int REASON_ROUTE_NOT_ENABLED = 3;
    public static final int REASON_UNSUPPORTED_FOR_NON_DYNAMIC_CONTROLLER = 5;
    public static final int UNSELECT_REASON_DISCONNECTED = 1;
    public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
    public static final int UNSELECT_REASON_STOPPED = 2;
    public static final int UNSELECT_REASON_UNKNOWN = 0;
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords = new ArrayList<>();
    final Context mContext;
    static final String TAG = "AxMediaRouter";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public static abstract class ControlRequestCallback {
        public void onError(String str, Bundle bundle) {
        }

        public void onResult(Bundle bundle) {
        }
    }

    public interface OnPrepareTransferListener {
        ListenableFuture<Void> onPrepareTransfer(RouteInfo routeInfo, RouteInfo routeInfo2);
    }

    MediaRouter(Context context) {
        this.mContext = context;
    }

    public static MediaRouter getInstance(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (sGlobal == null) {
            sGlobal = new GlobalMediaRouter(context.getApplicationContext());
        }
        return sGlobal.getRouter(context);
    }

    public static void resetGlobalRouter() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return;
        }
        globalMediaRouter.reset();
        sGlobal = null;
    }

    static GlobalMediaRouter getGlobalRouter() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter != null) {
            return globalMediaRouter;
        }
        throw new IllegalStateException("getGlobalRouter cannot be called when sGlobal is null");
    }

    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        return getGlobalRouter().getRoutes();
    }

    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        return getGlobalRouter().getProviders();
    }

    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return getGlobalRouter().getDefaultRoute();
    }

    public RouteInfo getBluetoothRoute() {
        checkCallingThread();
        return getGlobalRouter().getBluetoothRoute();
    }

    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return getGlobalRouter().getSelectedRoute();
    }

    public List<GroupRouteInfo> getConnectedGroupRoutes() {
        checkCallingThread();
        return getGlobalRouter().getConnectedGroupRoutes();
    }

    public RouteInfo updateSelectedRoute(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "updateSelectedRoute: " + mediaRouteSelector);
        }
        GlobalMediaRouter globalRouter = getGlobalRouter();
        RouteInfo selectedRoute = globalRouter.getSelectedRoute();
        if (selectedRoute.isDefaultOrBluetooth() || selectedRoute.matchesSelector(mediaRouteSelector)) {
            return selectedRoute;
        }
        RouteInfo routeInfoChooseFallbackRoute = globalRouter.chooseFallbackRoute();
        globalRouter.selectRoute(routeInfoChooseFallbackRoute, 3, true);
        return routeInfoChooseFallbackRoute;
    }

    public void selectRoute(RouteInfo routeInfo) {
        routeInfo.select();
    }

    public void unselect(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        RouteInfo routeInfoChooseFallbackRoute = globalRouter.chooseFallbackRoute();
        if (globalRouter.getSelectedRoute() != routeInfoChooseFallbackRoute) {
            globalRouter.selectRoute(routeInfoChooseFallbackRoute, i, true);
        }
    }

    public void addRouteToSelectedGroup(RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().addRouteToSelectedGroup(routeInfo);
    }

    public void removeRouteFromSelectedGroup(RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().removeRouteFromSelectedGroup(routeInfo);
    }

    public void transferToRoute(RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().transferToRoute(routeInfo);
    }

    public boolean isRouteAvailable(MediaRouteSelector mediaRouteSelector, int i) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        return getGlobalRouter().isRouteAvailable(mediaRouteSelector, i);
    }

    public void addCallback(MediaRouteSelector mediaRouteSelector, Callback callback) {
        addCallback(mediaRouteSelector, callback, 0);
    }

    public void addCallback(MediaRouteSelector mediaRouteSelector, Callback callback, int i) {
        CallbackRecord callbackRecord;
        boolean z;
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addCallback: selector=" + mediaRouteSelector + ", callback=" + callback + ", flags=" + Integer.toHexString(i));
        }
        int iFindCallbackRecord = findCallbackRecord(callback);
        if (iFindCallbackRecord < 0) {
            callbackRecord = new CallbackRecord(this, callback);
            this.mCallbackRecords.add(callbackRecord);
        } else {
            callbackRecord = this.mCallbackRecords.get(iFindCallbackRecord);
        }
        boolean z2 = true;
        if (i != callbackRecord.mFlags) {
            callbackRecord.mFlags = i;
            z = true;
        } else {
            z = false;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if ((i & 1) != 0) {
            z = true;
        }
        callbackRecord.mTimestamp = jElapsedRealtime;
        if (callbackRecord.mSelector.contains(mediaRouteSelector)) {
            z2 = z;
        } else {
            callbackRecord.mSelector = new MediaRouteSelector.Builder(callbackRecord.mSelector).addSelector(mediaRouteSelector).build();
        }
        if (z2) {
            getGlobalRouter().updateDiscoveryRequest();
        }
    }

    public void removeCallback(Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "removeCallback: callback=" + callback);
        }
        int iFindCallbackRecord = findCallbackRecord(callback);
        if (iFindCallbackRecord >= 0) {
            this.mCallbackRecords.remove(iFindCallbackRecord);
            getGlobalRouter().updateDiscoveryRequest();
        }
    }

    private int findCallbackRecord(Callback callback) {
        int size = this.mCallbackRecords.size();
        for (int i = 0; i < size; i++) {
            if (this.mCallbackRecords.get(i).mCallback == callback) {
                return i;
            }
        }
        return -1;
    }

    public void setOnPrepareTransferListener(OnPrepareTransferListener onPrepareTransferListener) {
        checkCallingThread();
        getGlobalRouter().mOnPrepareTransferListener = onPrepareTransferListener;
    }

    public void addProvider(MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addProvider: " + mediaRouteProvider);
        }
        getGlobalRouter().addProvider(mediaRouteProvider);
    }

    public void removeProvider(MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "removeProvider: " + mediaRouteProvider);
        }
        getGlobalRouter().removeProvider(mediaRouteProvider);
    }

    @Deprecated
    public void addRemoteControlClient(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "addRemoteControlClient: " + obj);
        }
        getGlobalRouter().addRemoteControlClient((RemoteControlClient) obj);
    }

    @Deprecated
    public void removeRemoteControlClient(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "removeRemoteControlClient: " + obj);
        }
        getGlobalRouter().removeRemoteControlClient((RemoteControlClient) obj);
    }

    public void setMediaSession(Object obj) {
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "setMediaSession: " + obj);
        }
        getGlobalRouter().setMediaSession(obj);
    }

    public void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
        checkCallingThread();
        if (DEBUG) {
            Log.d(TAG, "setMediaSessionCompat: " + mediaSessionCompat);
        }
        getGlobalRouter().setMediaSessionCompat(mediaSessionCompat);
    }

    public MediaSessionCompat.Token getMediaSessionToken() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return null;
        }
        return globalMediaRouter.getMediaSessionToken();
    }

    public MediaRouterParams getRouterParams() {
        checkCallingThread();
        return getGlobalRouter().getRouterParams();
    }

    public void setRouterParams(MediaRouterParams mediaRouterParams) {
        checkCallingThread();
        getGlobalRouter().setRouterParams(mediaRouterParams);
    }

    public void setRouteListingPreference(RouteListingPreference routeListingPreference) {
        checkCallingThread();
        getGlobalRouter().setRouteListingPreference(routeListingPreference);
    }

    static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static boolean isMediaTransferEnabled() {
        if (sGlobal == null) {
            return false;
        }
        return getGlobalRouter().isMediaTransferEnabled();
    }

    public static boolean isGroupVolumeUxEnabled() {
        if (sGlobal == null) {
            return false;
        }
        return getGlobalRouter().isGroupVolumeUxEnabled();
    }

    static int getGlobalCallbackCount() {
        if (sGlobal == null) {
            return 0;
        }
        return getGlobalRouter().getCallbackCount();
    }

    static boolean isTransferToLocalEnabled() {
        return getGlobalRouter().isTransferToLocalEnabled();
    }

    public static class RouteInfo {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int CONNECTION_STATE_CONNECTED = 2;
        public static final int CONNECTION_STATE_CONNECTING = 1;
        public static final int CONNECTION_STATE_DISCONNECTED = 0;
        public static final int DEVICE_TYPE_AUDIO_VIDEO_RECEIVER = 4;
        public static final int DEVICE_TYPE_BLE_HEADSET = 22;
        public static final int DEVICE_TYPE_BLUETOOTH_A2DP = 3;
        public static final int DEVICE_TYPE_BUILTIN_SPEAKER = 12;
        public static final int DEVICE_TYPE_CAR = 9;
        public static final int DEVICE_TYPE_COMPUTER = 7;
        public static final int DEVICE_TYPE_DOCK = 19;
        public static final int DEVICE_TYPE_GAME_CONSOLE = 8;
        public static final int DEVICE_TYPE_GROUP = 1000;
        public static final int DEVICE_TYPE_HDMI = 16;
        public static final int DEVICE_TYPE_HDMI_ARC = 23;
        public static final int DEVICE_TYPE_HDMI_EARC = 24;
        public static final int DEVICE_TYPE_HEARING_AID = 21;
        public static final int DEVICE_TYPE_REMOTE_SPEAKER = 2;
        public static final int DEVICE_TYPE_SMARTPHONE = 11;
        public static final int DEVICE_TYPE_SMARTWATCH = 10;

        @Deprecated
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TABLET = 5;
        public static final int DEVICE_TYPE_TABLET_DOCKED = 6;
        public static final int DEVICE_TYPE_TV = 1;
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int DEVICE_TYPE_USB_ACCESSORY = 18;
        public static final int DEVICE_TYPE_USB_DEVICE = 17;
        public static final int DEVICE_TYPE_USB_HEADSET = 20;
        public static final int DEVICE_TYPE_WIRED_HEADPHONES = 14;
        public static final int DEVICE_TYPE_WIRED_HEADSET = 13;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
        static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
        private boolean mCanDisconnect;
        private int mConnectionState;
        private final ArrayList<IntentFilter> mControlFilters;
        private String mDescription;
        MediaRouteDescriptor mDescriptor;
        final String mDescriptorId;
        private int mDeviceType;
        boolean mEnabled;
        private Bundle mExtras;
        private Uri mIconUri;
        private final boolean mIsSystemRoute;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private int mPresentationDisplayId;
        private final ProviderInfo mProvider;
        protected List<RouteInfo> mSelectedRoutesInGroup;
        private IntentSender mSettingsIntent;
        final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DeviceType {
        }

        RouteInfo(ProviderInfo providerInfo, String str, String str2) {
            this(providerInfo, str, str2, false);
        }

        RouteInfo(ProviderInfo providerInfo, String str, String str2, boolean z) {
            this.mControlFilters = new ArrayList<>();
            this.mPresentationDisplayId = -1;
            this.mSelectedRoutesInGroup = new ArrayList();
            this.mProvider = providerInfo;
            this.mDescriptorId = str;
            this.mUniqueId = str2;
            this.mIsSystemRoute = z;
        }

        public ProviderInfo getProvider() {
            return this.mProvider;
        }

        public String getId() {
            return this.mUniqueId;
        }

        public String getName() {
            return this.mName;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public boolean isSystemRoute() {
            return this.mIsSystemRoute;
        }

        @Deprecated
        public boolean isConnecting() {
            return this.mConnectionState == 1;
        }

        public int getConnectionState() {
            return this.mConnectionState;
        }

        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getSelectedRoute() == this;
        }

        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getDefaultRoute() == this;
        }

        public boolean isBluetooth() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getBluetoothRoute() == this;
        }

        public boolean isDeviceSpeaker() {
            return isDefault() && TextUtils.equals(Resources.getSystem().getText(Resources.getSystem().getIdentifier("default_audio_route_name", TypedValues.Custom.S_STRING, "android")), this.mName);
        }

        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }

        public boolean matchesSelector(MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            MediaRouter.checkCallingThread();
            return mediaRouteSelector.matchesControlFilters(this.mControlFilters);
        }

        public boolean supportsControlCategory(String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            MediaRouter.checkCallingThread();
            Iterator<IntentFilter> it = this.mControlFilters.iterator();
            while (it.hasNext()) {
                if (it.next().hasCategory(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsControlAction(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (str2 == null) {
                throw new IllegalArgumentException("action must not be null");
            }
            MediaRouter.checkCallingThread();
            for (IntentFilter intentFilter : this.mControlFilters) {
                if (intentFilter.hasCategory(str) && intentFilter.hasAction(str2)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsControlRequest(Intent intent) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            ContentResolver contentResolver = MediaRouter.getGlobalRouter().getContentResolver();
            Iterator<IntentFilter> it = this.mControlFilters.iterator();
            while (it.hasNext()) {
                if (it.next().match(contentResolver, intent, true, MediaRouter.TAG) >= 0) {
                    return true;
                }
            }
            return false;
        }

        public void sendControlRequest(Intent intent, ControlRequestCallback controlRequestCallback) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().sendControlRequest(this, intent, controlRequestCallback);
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }

        public int getDeviceType() {
            return this.mDeviceType;
        }

        public boolean isDefaultOrBluetooth() {
            if (isDefault() || this.mDeviceType == 3) {
                return true;
            }
            return isSystemMediaRouteProvider(this) && supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        boolean isSelectable() {
            return this.mDescriptor != null && this.mEnabled;
        }

        private static boolean isSystemMediaRouteProvider(RouteInfo routeInfo) {
            return TextUtils.equals(routeInfo.getProviderInstance().getMetadata().getPackageName(), "android");
        }

        public int getVolumeHandling() {
            if (!isGroup() || MediaRouter.isGroupVolumeUxEnabled()) {
                return this.mVolumeHandling;
            }
            return 0;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getVolumeMax() {
            return this.mVolumeMax;
        }

        public boolean canDisconnect() {
            return this.mCanDisconnect;
        }

        public void requestSetVolume(int i) {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, i)));
        }

        public void requestUpdateVolume(int i) {
            MediaRouter.checkCallingThread();
            if (i != 0) {
                MediaRouter.getGlobalRouter().requestUpdateVolume(this, i);
            }
        }

        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
                this.mPresentationDisplay = MediaRouter.getGlobalRouter().getDisplay(this.mPresentationDisplayId);
            }
            return this.mPresentationDisplay;
        }

        public int getPresentationDisplayId() {
            return this.mPresentationDisplayId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public IntentSender getSettingsIntent() {
            return this.mSettingsIntent;
        }

        public void select() {
            select(true);
        }

        public void select(boolean z) {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().selectRoute(this, 3, z);
        }

        public void connect() {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().connectRoute(this);
        }

        public void disconnect() {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().disconnectRoute(this);
        }

        public GroupRouteInfo asGroup() {
            if (this instanceof GroupRouteInfo) {
                return (GroupRouteInfo) this;
            }
            return null;
        }

        public boolean isGroup() {
            return !this.mSelectedRoutesInGroup.isEmpty();
        }

        public List<RouteInfo> getSelectedRoutesInGroup() {
            return DesugarCollections.unmodifiableList(this.mSelectedRoutesInGroup);
        }

        public MediaRouteDescriptor getMediaRouteDescriptor() {
            return this.mDescriptor;
        }

        public MediaRouteProvider.DynamicGroupRouteController getDynamicGroupController() {
            MediaRouter.checkCallingThread();
            MediaRouteProvider.RouteController routeController = MediaRouter.getGlobalRouter().mSelectedRouteController;
            if (routeController instanceof MediaRouteProvider.DynamicGroupRouteController) {
                return (MediaRouteProvider.DynamicGroupRouteController) routeController;
            }
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("MediaRouter.RouteInfo{ uniqueId=");
            sb.append(this.mUniqueId);
            sb.append(", name=");
            sb.append(this.mName);
            sb.append(", description=");
            sb.append(this.mDescription);
            sb.append(", iconUri=");
            sb.append(this.mIconUri);
            sb.append(", enabled=");
            sb.append(this.mEnabled);
            sb.append(", isSystemRoute=");
            sb.append(this.mIsSystemRoute);
            sb.append(", connectionState=");
            sb.append(this.mConnectionState);
            sb.append(", canDisconnect=");
            sb.append(this.mCanDisconnect);
            sb.append(", playbackType=");
            sb.append(this.mPlaybackType);
            sb.append(", playbackStream=");
            sb.append(this.mPlaybackStream);
            sb.append(", deviceType=");
            sb.append(this.mDeviceType);
            sb.append(", volumeHandling=");
            sb.append(this.mVolumeHandling);
            sb.append(", volume=");
            sb.append(this.mVolume);
            sb.append(", volumeMax=");
            sb.append(this.mVolumeMax);
            sb.append(", presentationDisplayId=");
            sb.append(this.mPresentationDisplayId);
            sb.append(", extras=");
            sb.append(this.mExtras);
            sb.append(", settingsIntent=");
            sb.append(this.mSettingsIntent);
            sb.append(", providerPackageName=");
            sb.append(this.mProvider.getPackageName());
            if (isGroup()) {
                sb.append(", members=[");
                int size = this.mSelectedRoutesInGroup.size();
                for (int i = 0; i < size; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    if (this.mSelectedRoutesInGroup.get(i) != this) {
                        sb.append(this.mSelectedRoutesInGroup.get(i).getId());
                    }
                }
                sb.append(AbstractJsonLexerKt.END_LIST);
            }
            sb.append(" }");
            return sb.toString();
        }

        int maybeUpdateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            if (this.mDescriptor != mediaRouteDescriptor) {
                return updateDescriptor(mediaRouteDescriptor);
            }
            return 0;
        }

        private boolean isSameControlFilters(List<IntentFilter> list, List<IntentFilter> list2) {
            if (list == list2) {
                return true;
            }
            if (list != null && list2 != null) {
                ListIterator<IntentFilter> listIterator = list.listIterator();
                ListIterator<IntentFilter> listIterator2 = list2.listIterator();
                while (listIterator.hasNext() && listIterator2.hasNext()) {
                    if (!isSameControlFilter(listIterator.next(), listIterator2.next())) {
                        return false;
                    }
                }
                if (!listIterator.hasNext() && !listIterator2.hasNext()) {
                    return true;
                }
            }
            return false;
        }

        private boolean isSameControlFilter(IntentFilter intentFilter, IntentFilter intentFilter2) {
            int iCountActions;
            if (intentFilter == intentFilter2) {
                return true;
            }
            if (intentFilter == null || intentFilter2 == null || (iCountActions = intentFilter.countActions()) != intentFilter2.countActions()) {
                return false;
            }
            for (int i = 0; i < iCountActions; i++) {
                if (!intentFilter.getAction(i).equals(intentFilter2.getAction(i))) {
                    return false;
                }
            }
            int iCountCategories = intentFilter.countCategories();
            if (iCountCategories != intentFilter2.countCategories()) {
                return false;
            }
            for (int i2 = 0; i2 < iCountCategories; i2++) {
                if (!intentFilter.getCategory(i2).equals(intentFilter2.getCategory(i2))) {
                    return false;
                }
            }
            return true;
        }

        int updateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            int i;
            this.mDescriptor = mediaRouteDescriptor;
            if (mediaRouteDescriptor == null) {
                return 0;
            }
            if (ObjectsCompat.equals(this.mName, mediaRouteDescriptor.getName())) {
                i = 0;
            } else {
                this.mName = mediaRouteDescriptor.getName();
                i = 1;
            }
            if (!ObjectsCompat.equals(this.mDescription, mediaRouteDescriptor.getDescription())) {
                this.mDescription = mediaRouteDescriptor.getDescription();
                i = 1;
            }
            if (!ObjectsCompat.equals(this.mIconUri, mediaRouteDescriptor.getIconUri())) {
                this.mIconUri = mediaRouteDescriptor.getIconUri();
                i = 1;
            }
            if (this.mEnabled != mediaRouteDescriptor.isEnabled()) {
                this.mEnabled = mediaRouteDescriptor.isEnabled();
                i = 1;
            }
            if (this.mConnectionState != mediaRouteDescriptor.getConnectionState()) {
                this.mConnectionState = mediaRouteDescriptor.getConnectionState();
                i = 1;
            }
            if (!isSameControlFilters(this.mControlFilters, mediaRouteDescriptor.getControlFilters())) {
                this.mControlFilters.clear();
                this.mControlFilters.addAll(mediaRouteDescriptor.getControlFilters());
                i = 1;
            }
            if (this.mPlaybackType != mediaRouteDescriptor.getPlaybackType()) {
                this.mPlaybackType = mediaRouteDescriptor.getPlaybackType();
                i = 1;
            }
            if (this.mPlaybackStream != mediaRouteDescriptor.getPlaybackStream()) {
                this.mPlaybackStream = mediaRouteDescriptor.getPlaybackStream();
                i = 1;
            }
            if (this.mDeviceType != mediaRouteDescriptor.getDeviceType()) {
                this.mDeviceType = mediaRouteDescriptor.getDeviceType();
                i = 1;
            }
            int i2 = 3;
            if (this.mVolumeHandling != mediaRouteDescriptor.getVolumeHandling()) {
                this.mVolumeHandling = mediaRouteDescriptor.getVolumeHandling();
                i = 3;
            }
            if (this.mVolume != mediaRouteDescriptor.getVolume()) {
                this.mVolume = mediaRouteDescriptor.getVolume();
                i = 3;
            }
            if (this.mVolumeMax != mediaRouteDescriptor.getVolumeMax()) {
                this.mVolumeMax = mediaRouteDescriptor.getVolumeMax();
            } else {
                i2 = i;
            }
            if (this.mPresentationDisplayId != mediaRouteDescriptor.getPresentationDisplayId()) {
                this.mPresentationDisplayId = mediaRouteDescriptor.getPresentationDisplayId();
                this.mPresentationDisplay = null;
                i2 |= 5;
            }
            if (!ObjectsCompat.equals(this.mExtras, mediaRouteDescriptor.getExtras())) {
                this.mExtras = mediaRouteDescriptor.getExtras();
                i2 |= 1;
            }
            if (!ObjectsCompat.equals(this.mSettingsIntent, mediaRouteDescriptor.getSettingsActivity())) {
                this.mSettingsIntent = mediaRouteDescriptor.getSettingsActivity();
                i2 |= 1;
            }
            if (this.mCanDisconnect != mediaRouteDescriptor.canDisconnectAndKeepPlaying()) {
                this.mCanDisconnect = mediaRouteDescriptor.canDisconnectAndKeepPlaying();
                i2 |= 5;
            }
            List<String> groupMemberIds = mediaRouteDescriptor.getGroupMemberIds();
            ArrayList arrayList = new ArrayList();
            boolean z = groupMemberIds.size() != this.mSelectedRoutesInGroup.size();
            if (!groupMemberIds.isEmpty()) {
                GlobalMediaRouter globalRouter = MediaRouter.getGlobalRouter();
                Iterator<String> it = groupMemberIds.iterator();
                while (it.hasNext()) {
                    RouteInfo route = globalRouter.getRoute(globalRouter.getUniqueId(getProvider(), it.next()));
                    if (route != null) {
                        arrayList.add(route);
                        if (!z && !this.mSelectedRoutesInGroup.contains(route)) {
                            z = true;
                        }
                    }
                }
            }
            if (!z) {
                return i2;
            }
            this.mSelectedRoutesInGroup = arrayList;
            return i2 | 1;
        }

        String getDescriptorId() {
            return this.mDescriptorId;
        }

        public MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }

        RouteInfo findRouteByDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor) {
            return getProvider().findRouteByDescriptorId(dynamicRouteDescriptor.getRouteDescriptor().getId());
        }
    }

    public static class GroupRouteInfo extends RouteInfo {
        public static final int ADD_ROUTE_FAILED_REASON_ALREADY_IN_GROUP = 3;
        public static final int ADD_ROUTE_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 5;
        public static final int ADD_ROUTE_FAILED_REASON_NOT_GROUPABLE = 2;
        public static final int ADD_ROUTE_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 4;
        public static final int ADD_ROUTE_SUCCESSFUL = 1;
        public static final int REMOVE_ROUTE_FAILED_REASON_LAST_ROUTE_IN_GROUP = 4;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 6;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_IN_GROUP = 3;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_UNSELECTABLE = 2;
        public static final int REMOVE_ROUTE_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 5;
        public static final int REMOVE_ROUTE_SUCCESSFUL = 1;
        static final int UPDATE_ROUTES_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 4;
        static final int UPDATE_ROUTES_FAILED_REASON_NOT_TRANSFERABLE = 2;
        static final int UPDATE_ROUTES_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 3;
        static final int UPDATE_ROUTES_SUCCESSFUL = 1;
        private final Map<String, MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mRouteIdToDynamicRouteDescriptorMap;
        private final List<RouteInfo> mRoutesInGroup;

        GroupRouteInfo(ProviderInfo providerInfo, String str, String str2) {
            super(providerInfo, str, str2);
            this.mRoutesInGroup = new ArrayList();
            this.mRouteIdToDynamicRouteDescriptorMap = new ArrayMap();
        }

        public boolean isConnected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getConnectedGroupRoutes().contains(this);
        }

        public int addRoute(RouteInfo routeInfo) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().addRouteToGroup(this, routeInfo);
        }

        public int removeRoute(RouteInfo routeInfo) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().removeRouteFromGroup(this, routeInfo);
        }

        int updateRoutes(List<RouteInfo> list) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().updateRoutesForGroup(this, list);
        }

        public List<RouteInfo> getRoutesInGroup() {
            return DesugarCollections.unmodifiableList(this.mRoutesInGroup);
        }

        public int getSelectionState(RouteInfo routeInfo) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mRouteIdToDynamicRouteDescriptorMap.get(routeInfo.getId());
            if (dynamicRouteDescriptor != null) {
                return dynamicRouteDescriptor.getSelectionState();
            }
            return 4;
        }

        public boolean isUnselectable(RouteInfo routeInfo) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mRouteIdToDynamicRouteDescriptorMap.get(routeInfo.getId());
            return dynamicRouteDescriptor != null && dynamicRouteDescriptor.isUnselectable();
        }

        public boolean isGroupable(RouteInfo routeInfo) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mRouteIdToDynamicRouteDescriptorMap.get(routeInfo.getId());
            return dynamicRouteDescriptor != null && dynamicRouteDescriptor.isGroupable();
        }

        public boolean isTransferable(RouteInfo routeInfo) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mRouteIdToDynamicRouteDescriptorMap.get(routeInfo.getId());
            return dynamicRouteDescriptor != null && dynamicRouteDescriptor.isTransferable();
        }

        void updateDynamicDescriptors(Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            this.mSelectedRoutesInGroup.clear();
            this.mRoutesInGroup.clear();
            this.mRouteIdToDynamicRouteDescriptorMap.clear();
            for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
                RouteInfo routeInfoFindRouteByDynamicRouteDescriptor = findRouteByDynamicRouteDescriptor(dynamicRouteDescriptor);
                if (routeInfoFindRouteByDynamicRouteDescriptor != null) {
                    this.mRoutesInGroup.add(routeInfoFindRouteByDynamicRouteDescriptor);
                    this.mRouteIdToDynamicRouteDescriptorMap.put(routeInfoFindRouteByDynamicRouteDescriptor.getId(), dynamicRouteDescriptor);
                    if (dynamicRouteDescriptor.getSelectionState() == 2 || dynamicRouteDescriptor.getSelectionState() == 3) {
                        this.mSelectedRoutesInGroup.add(routeInfoFindRouteByDynamicRouteDescriptor);
                    }
                }
            }
            MediaRouter.getGlobalRouter().mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, this);
        }
    }

    public static final class ProviderInfo {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        final MediaRouteProvider mProviderInstance;
        final List<RouteInfo> mRoutes = new ArrayList();
        final boolean mTreatRouteDescriptorIdsAsUnique;

        ProviderInfo(MediaRouteProvider mediaRouteProvider, boolean z) {
            this.mProviderInstance = mediaRouteProvider;
            this.mMetadata = mediaRouteProvider.getMetadata();
            this.mTreatRouteDescriptorIdsAsUnique = z;
        }

        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }

        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }

        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }

        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return DesugarCollections.unmodifiableList(this.mRoutes);
        }

        boolean updateDescriptor(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (this.mDescriptor == mediaRouteProviderDescriptor) {
                return false;
            }
            this.mDescriptor = mediaRouteProviderDescriptor;
            return true;
        }

        int findRouteIndexByDescriptorId(String str) {
            int size = this.mRoutes.size();
            for (int i = 0; i < size; i++) {
                if (this.mRoutes.get(i).mDescriptorId.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        RouteInfo findRouteByDescriptorId(String str) {
            for (RouteInfo routeInfo : this.mRoutes) {
                if (routeInfo.mDescriptorId.equals(str)) {
                    return routeInfo;
                }
            }
            return null;
        }

        boolean supportsDynamicGroup() {
            MediaRouteProviderDescriptor mediaRouteProviderDescriptor = this.mDescriptor;
            return mediaRouteProviderDescriptor != null && mediaRouteProviderDescriptor.supportsDynamicGroupRoute();
        }

        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + getPackageName() + " }";
        }
    }

    public static abstract class Callback {
        public void onProviderAdded(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onProviderChanged(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onProviderRemoved(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteConnected(MediaRouter mediaRouter, RouteInfo routeInfo, RouteInfo routeInfo2) {
        }

        public void onRouteDisconnected(MediaRouter mediaRouter, RouteInfo routeInfo, RouteInfo routeInfo2, int i) {
        }

        public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        @Deprecated
        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        @Deprecated
        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteVolumeChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouterParamsChanged(MediaRouter mediaRouter, MediaRouterParams mediaRouterParams) {
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo, int i) {
            onRouteSelected(mediaRouter, routeInfo);
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo, int i, RouteInfo routeInfo2) {
            onRouteSelected(mediaRouter, routeInfo, i);
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo, int i) {
            onRouteUnselected(mediaRouter, routeInfo);
        }
    }

    static final class CallbackRecord {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;
        public long mTimestamp;

        public CallbackRecord(MediaRouter mediaRouter, Callback callback) {
            this.mRouter = mediaRouter;
            this.mCallback = callback;
        }

        public boolean filterRouteEvent(RouteInfo routeInfo, int i, RouteInfo routeInfo2, int i2) {
            if ((this.mFlags & 2) != 0 || routeInfo.matchesSelector(this.mSelector)) {
                return true;
            }
            if (MediaRouter.isTransferToLocalEnabled() && routeInfo.isDefaultOrBluetooth() && i == 262 && i2 == 3 && routeInfo2 != null) {
                return !routeInfo2.isDefaultOrBluetooth();
            }
            return false;
        }
    }

    static final class PrepareTransferNotifier {
        private static final long TRANSFER_TIMEOUT_MS = 15000;
        private final RouteInfo mFromRoute;
        final List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mMemberRoutes;
        final int mReason;
        private final RouteInfo mRequestedRoute;
        private final WeakReference<GlobalMediaRouter> mRouter;
        private final boolean mSyncMediaRoute1Provider;
        final RouteInfo mToRoute;
        final MediaRouteProvider.RouteController mToRouteController;
        private ListenableFuture<Void> mFuture = null;
        private boolean mFinished = false;
        private boolean mCanceled = false;

        PrepareTransferNotifier(GlobalMediaRouter globalMediaRouter, RouteInfo routeInfo, MediaRouteProvider.RouteController routeController, int i, boolean z, RouteInfo routeInfo2, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            this.mRouter = new WeakReference<>(globalMediaRouter);
            this.mToRoute = routeInfo;
            this.mToRouteController = routeController;
            this.mReason = i;
            this.mSyncMediaRoute1Provider = z;
            this.mFromRoute = globalMediaRouter.mSelectedRoute;
            this.mRequestedRoute = routeInfo2;
            this.mMemberRoutes = collection != null ? new ArrayList(collection) : null;
            globalMediaRouter.mCallbackHandler.postDelayed(new MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0(this), 15000L);
        }

        void setFuture(ListenableFuture<Void> listenableFuture) {
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null || globalMediaRouter.mTransferNotifier != this) {
                Log.w(MediaRouter.TAG, "Router is released. Cancel transfer");
                cancel();
            } else {
                if (this.mFuture != null) {
                    throw new IllegalStateException("future is already set");
                }
                this.mFuture = listenableFuture;
                MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0 mediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0 = new MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0(this);
                final GlobalMediaRouter.CallbackHandler callbackHandler = globalMediaRouter.mCallbackHandler;
                Objects.requireNonNull(callbackHandler);
                listenableFuture.addListener(mediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0, new Executor() { // from class: androidx.mediarouter.media.MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda1
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        callbackHandler.post(runnable);
                    }
                });
            }
        }

        void finishTransfer() {
            ListenableFuture<Void> listenableFuture;
            MediaRouter.checkCallingThread();
            if (this.mFinished || this.mCanceled) {
                return;
            }
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null || globalMediaRouter.mTransferNotifier != this || ((listenableFuture = this.mFuture) != null && listenableFuture.isCancelled())) {
                cancel();
                return;
            }
            this.mFinished = true;
            globalMediaRouter.mTransferNotifier = null;
            unselectFromRouteAndNotify();
            selectToRouteAndNotify();
        }

        void cancel() {
            if (this.mFinished || this.mCanceled) {
                return;
            }
            this.mCanceled = true;
            MediaRouteProvider.RouteController routeController = this.mToRouteController;
            if (routeController != null) {
                routeController.onUnselect(0);
                this.mToRouteController.onRelease();
            }
        }

        private void unselectFromRouteAndNotify() {
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null || globalMediaRouter.mSelectedRoute != this.mFromRoute) {
                return;
            }
            globalMediaRouter.mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED, this.mFromRoute, this.mReason);
            if (globalMediaRouter.mSelectedRouteController != null) {
                globalMediaRouter.mSelectedRouteController.onUnselect(this.mReason);
                globalMediaRouter.mSelectedRouteController.onRelease();
            }
            if (!globalMediaRouter.mRouteControllerMap.isEmpty()) {
                for (MediaRouteProvider.RouteController routeController : globalMediaRouter.mRouteControllerMap.values()) {
                    routeController.onUnselect(this.mReason);
                    routeController.onRelease();
                }
                globalMediaRouter.mRouteControllerMap.clear();
            }
            globalMediaRouter.mSelectedRouteController = null;
        }

        private void selectToRouteAndNotify() {
            GroupRouteInfo groupRouteInfoAsGroup;
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null) {
                return;
            }
            globalMediaRouter.mSelectedRoute = this.mToRoute;
            globalMediaRouter.mSelectedRouteController = this.mToRouteController;
            if (this.mRequestedRoute == null) {
                globalMediaRouter.mCallbackHandler.postRouteSelectedMessage(this.mFromRoute, this.mToRoute, this.mReason, this.mSyncMediaRoute1Provider);
            } else {
                globalMediaRouter.mCallbackHandler.postAnotherRouteSelectedMessage(this.mRequestedRoute, this.mToRoute, this.mReason, this.mSyncMediaRoute1Provider);
            }
            globalMediaRouter.mRouteControllerMap.clear();
            globalMediaRouter.maybeUpdateMemberRouteControllers();
            globalMediaRouter.updatePlaybackInfoFromSelectedRoute();
            if (this.mMemberRoutes == null || (groupRouteInfoAsGroup = globalMediaRouter.mSelectedRoute.asGroup()) == null) {
                return;
            }
            groupRouteInfoAsGroup.updateDynamicDescriptors(this.mMemberRoutes);
        }
    }
}

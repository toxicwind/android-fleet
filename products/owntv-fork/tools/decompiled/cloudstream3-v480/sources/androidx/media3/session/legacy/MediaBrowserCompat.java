package androidx.media3.session.legacy;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl impl;

    public static abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        Bundle getExtras();

        void getItem(String str, ItemCallback itemCallback);

        Bundle getNotifyChildrenChangedOptions();

        String getRoot();

        MediaSessionCompat.Token getSessionToken();

        boolean isConnected();

        void search(String str, Bundle bundle, SearchCallback searchCallback);

        void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback);

        void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback);

        void unsubscribe(String str, SubscriptionCallback subscriptionCallback);
    }

    interface MediaBrowserServiceCallbackImpl {
        void onLoadChildren(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    public static abstract class SearchCallback {
        public void onError(String str, Bundle bundle) {
        }

        public void onSearchResult(String str, Bundle bundle, List<MediaItem> list) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.impl = new MediaBrowserImplApi26(context, componentName, connectionCallback, bundle);
        } else {
            this.impl = new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
        }
    }

    public void connect() {
        Log.d(TAG, "Connecting to a MediaBrowserService.");
        this.impl.connect();
    }

    public void disconnect() {
        this.impl.disconnect();
    }

    public boolean isConnected() {
        return this.impl.isConnected();
    }

    public String getRoot() {
        return this.impl.getRoot();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.impl.getSessionToken();
    }

    public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.impl.subscribe(str, bundle, subscriptionCallback);
    }

    public void unsubscribe(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.impl.unsubscribe(str, null);
    }

    public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.impl.unsubscribe(str, subscriptionCallback);
    }

    public void getItem(String str, ItemCallback itemCallback) {
        this.impl.getItem(str, itemCallback);
    }

    public void search(String str, Bundle bundle, SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        this.impl.search(str, bundle, searchCallback);
    }

    public void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.impl.sendCustomAction(str, bundle, customActionCallback);
    }

    public Bundle getNotifyChildrenChangedOptions() {
        return this.impl.getNotifyChildrenChangedOptions();
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat description;
        private final int flags;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static MediaItem fromMediaItem(MediaBrowser.MediaItem mediaItem) {
            if (mediaItem == null) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(mediaItem.getDescription()), mediaItem.getFlags());
        }

        public static List<MediaItem> fromMediaItemList(List<MediaBrowser.MediaItem> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<MediaBrowser.MediaItem> it = list.iterator();
            while (it.hasNext()) {
                MediaItem mediaItemFromMediaItem = fromMediaItem(it.next());
                if (mediaItemFromMediaItem != null) {
                    arrayList.add(mediaItemFromMediaItem);
                }
            }
            return arrayList;
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.flags = i;
            this.description = mediaDescriptionCompat;
        }

        MediaItem(Parcel parcel) {
            this.flags = parcel.readInt();
            this.description = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.flags);
            this.description.writeToParcel(parcel, i);
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.flags + ", mDescription=" + this.description + AbstractJsonLexerKt.END_OBJ;
        }

        public int getFlags() {
            return this.flags;
        }

        public boolean isBrowsable() {
            return (this.flags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.flags & 2) != 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.description;
        }

        public String getMediaId() {
            return this.description.getMediaId();
        }
    }

    public static class ConnectionCallback {
        final MediaBrowser.ConnectionCallback connectionCallbackFwk = new ConnectionCallbackImpl();
        ConnectionCallbackInternal connectionCallbackInternal;

        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            this.connectionCallbackInternal = connectionCallbackInternal;
        }

        private class ConnectionCallbackImpl extends MediaBrowser.ConnectionCallback {
            ConnectionCallbackImpl() {
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                if (ConnectionCallback.this.connectionCallbackInternal != null) {
                    ConnectionCallback.this.connectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                if (ConnectionCallback.this.connectionCallbackInternal != null) {
                    ConnectionCallback.this.connectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                if (ConnectionCallback.this.connectionCallbackInternal != null) {
                    ConnectionCallback.this.connectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }
        }
    }

    public static abstract class SubscriptionCallback {
        final MediaBrowser.SubscriptionCallback subscriptionCallbackFwk;
        WeakReference<Subscription> subscriptionRef;
        final IBinder token = new Binder();

        public void onChildrenLoaded(String str, List<MediaItem> list) {
        }

        public void onChildrenLoaded(String str, List<MediaItem> list, Bundle bundle) {
        }

        public void onError(String str) {
        }

        public void onError(String str, Bundle bundle) {
        }

        public SubscriptionCallback() {
            if (Build.VERSION.SDK_INT >= 26) {
                this.subscriptionCallbackFwk = new SubscriptionCallbackApi26();
            } else {
                this.subscriptionCallbackFwk = new SubscriptionCallbackApi23();
            }
        }

        void setSubscription(Subscription subscription) {
            this.subscriptionRef = new WeakReference<>(subscription);
        }

        private class SubscriptionCallbackApi23 extends MediaBrowser.SubscriptionCallback {
            SubscriptionCallbackApi23() {
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
                Subscription subscription = SubscriptionCallback.this.subscriptionRef == null ? null : SubscriptionCallback.this.subscriptionRef.get();
                if (subscription == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List<MediaItem> list2 = (List) Preconditions.checkNotNull(MediaItem.fromMediaItemList(list));
                List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                List<Bundle> optionsList = subscription.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle bundle = optionsList.get(i);
                    SubscriptionCallback subscriptionCallback = SubscriptionCallback.this;
                    if (bundle == null) {
                        subscriptionCallback.onChildrenLoaded(str, list2);
                    } else {
                        subscriptionCallback.onChildrenLoaded(str, applyOptions(list2, bundle), bundle);
                    }
                }
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str) {
                SubscriptionCallback.this.onError(str);
            }

            List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
                int i = bundle.getInt("android.media.browse.extra.PAGE", -1);
                int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (i == -1 && i2 == -1) {
                    return list;
                }
                int i3 = i2 * i;
                int size = i3 + i2;
                if (i < 0 || i2 < 1 || i3 >= list.size()) {
                    return Collections.EMPTY_LIST;
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i3, size);
            }
        }

        private class SubscriptionCallbackApi26 extends SubscriptionCallbackApi23 {
            SubscriptionCallbackApi26() {
                super();
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), Util.convertToNullIfInvalid(bundle));
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str, Bundle bundle) {
                SubscriptionCallback.this.onError(str, Util.convertToNullIfInvalid(bundle));
            }
        }
    }

    public static abstract class ItemCallback {
        final MediaBrowser.ItemCallback itemCallbackFwk = new ItemCallbackImpl();

        public void onError(String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }

        private class ItemCallbackImpl extends MediaBrowser.ItemCallback {
            ItemCallbackImpl() {
            }

            @Override // android.media.browse.MediaBrowser.ItemCallback
            public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
                ItemCallback.this.onItemLoaded(MediaItem.fromMediaItem(mediaItem));
            }

            @Override // android.media.browse.MediaBrowser.ItemCallback
            public void onError(String str) {
                ItemCallback.this.onError(str);
            }
        }
    }

    static class MediaBrowserImplApi23 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {
        protected final MediaBrowser browserFwk;
        protected Messenger callbacksMessenger;
        final Context context;
        private MediaSessionCompat.Token mediaSessionToken;
        private Bundle notifyChildrenChangedOptions;
        protected final Bundle rootHints;
        protected ServiceBinderWrapper serviceBinderWrapper;
        protected int serviceVersion;
        protected final CallbackHandler handler = new CallbackHandler(this);
        private final ArrayMap<String, Subscription> subscriptions = new ArrayMap<>();

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionFailed() {
        }

        MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.context = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.rootHints = bundle2;
            bundle2.putInt("extra_client_version", 1);
            bundle2.putInt("extra_calling_pid", Process.myPid());
            connectionCallback.setInternalConnectionCallback(this);
            this.browserFwk = new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) Preconditions.checkNotNull(connectionCallback.connectionCallbackFwk), bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void connect() {
            this.browserFwk.connect();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void disconnect() {
            Messenger messenger;
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper != null && (messenger = this.callbacksMessenger) != null) {
                try {
                    serviceBinderWrapper.unregisterCallbackMessenger(messenger);
                } catch (RemoteException unused) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error unregistering client messenger.");
                }
            }
            this.browserFwk.disconnect();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public boolean isConnected() {
            return this.browserFwk.isConnected();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public String getRoot() {
            return this.browserFwk.getRoot();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getExtras() {
            return Util.convertToNullIfInvalid(this.browserFwk.getExtras());
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public MediaSessionCompat.Token getSessionToken() {
            if (this.mediaSessionToken == null) {
                this.mediaSessionToken = MediaSessionCompat.Token.fromToken(this.browserFwk.getSessionToken());
            }
            return this.mediaSessionToken;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.subscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.subscriptions.put(str, subscription);
            }
            subscriptionCallback.setSubscription(subscription);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper == null) {
                this.browserFwk.subscribe(str, subscriptionCallback.subscriptionCallbackFwk);
                return;
            }
            try {
                serviceBinderWrapper.addSubscription(str, subscriptionCallback.token, bundle2, (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
            } catch (RemoteException unused) {
                Log.i(MediaBrowserCompat.TAG, "Remote error subscribing media item: " + str);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.subscriptions.get(str);
            if (subscription == null) {
                return;
            }
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper != null) {
                try {
                    if (subscriptionCallback == null) {
                        serviceBinderWrapper.removeSubscription(str, null, (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
                    } else {
                        List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                        List<Bundle> optionsList = subscription.getOptionsList();
                        for (int size = callbacks.size() - 1; size >= 0; size--) {
                            if (callbacks.get(size) == subscriptionCallback) {
                                serviceBinderWrapper.removeSubscription(str, subscriptionCallback.token, (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
                                callbacks.remove(size);
                                optionsList.remove(size);
                            }
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d(MediaBrowserCompat.TAG, "removeSubscription failed with RemoteException parentId=" + str);
                }
            } else if (subscriptionCallback == null) {
                this.browserFwk.unsubscribe(str);
            } else {
                List<SubscriptionCallback> callbacks2 = subscription.getCallbacks();
                List<Bundle> optionsList2 = subscription.getOptionsList();
                for (int size2 = callbacks2.size() - 1; size2 >= 0; size2--) {
                    if (callbacks2.get(size2) == subscriptionCallback) {
                        callbacks2.remove(size2);
                        optionsList2.remove(size2);
                    }
                }
                if (callbacks2.isEmpty()) {
                    this.browserFwk.unsubscribe(str);
                }
            }
            if (subscription.isEmpty() || subscriptionCallback == null) {
                this.subscriptions.remove(str);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void getItem(final String str, final ItemCallback itemCallback) {
            if (this.serviceBinderWrapper == null) {
                this.browserFwk.getItem(str, itemCallback.itemCallbackFwk);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (!this.browserFwk.isConnected()) {
                Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
            } else {
                if (this.serviceBinderWrapper == null) {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.2
                        @Override // java.lang.Runnable
                        public void run() {
                            itemCallback.onError(str);
                        }
                    });
                    return;
                }
                try {
                    this.serviceBinderWrapper.getMediaItem(str, new ItemReceiver(str, itemCallback, this.handler), (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
                } catch (RemoteException unused) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error getting media item: " + str);
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.3
                        @Override // java.lang.Runnable
                        public void run() {
                            itemCallback.onError(str);
                        }
                    });
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void search(final String str, final Bundle bundle, final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            }
            if (this.serviceBinderWrapper == null) {
                Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support search.");
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.4
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
                return;
            }
            try {
                this.serviceBinderWrapper.search(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.handler), (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
            } catch (RemoteException e) {
                Log.i(MediaBrowserCompat.TAG, "Remote error searching items with query: " + str, e);
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.5
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void sendCustomAction(final String str, final Bundle bundle, final CustomActionCallback customActionCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
            }
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper == null) {
                Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support sendCustomAction.");
                if (customActionCallback != null) {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.6
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                    return;
                }
                return;
            }
            try {
                serviceBinderWrapper.sendCustomAction(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.handler), (Messenger) Preconditions.checkNotNull(this.callbacksMessenger));
            } catch (RemoteException e) {
                Log.i(MediaBrowserCompat.TAG, "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e);
                if (customActionCallback != null) {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.7
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnected() {
            try {
                Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(this.browserFwk.getExtras());
                if (bundleConvertToNullIfInvalid == null) {
                    return;
                }
                this.serviceVersion = bundleConvertToNullIfInvalid.getInt("extra_service_version", 0);
                IBinder binder = bundleConvertToNullIfInvalid.getBinder("extra_messenger");
                if (binder != null) {
                    ServiceBinderWrapper serviceBinderWrapper = new ServiceBinderWrapper(binder, this.rootHints);
                    this.serviceBinderWrapper = serviceBinderWrapper;
                    Messenger messenger = new Messenger(this.handler);
                    this.callbacksMessenger = messenger;
                    this.handler.setCallbacksMessenger(messenger);
                    try {
                        serviceBinderWrapper.registerCallbackMessenger(this.context, messenger);
                    } catch (RemoteException unused) {
                        Log.i(MediaBrowserCompat.TAG, "Remote error registering client messenger.");
                    }
                }
                IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(bundleConvertToNullIfInvalid.getBinder("extra_session_binder"));
                if (iMediaSessionAsInterface != null) {
                    this.mediaSessionToken = MediaSessionCompat.Token.fromToken(this.browserFwk.getSessionToken(), iMediaSessionAsInterface);
                }
            } catch (IllegalStateException e) {
                Log.e(MediaBrowserCompat.TAG, "Unexpected IllegalStateException", e);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionSuspended() {
            this.serviceBinderWrapper = null;
            this.callbacksMessenger = null;
            this.mediaSessionToken = null;
            this.handler.setCallbacksMessenger(null);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onLoadChildren(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) {
            if (this.callbacksMessenger != messenger) {
                return;
            }
            Subscription subscription = str == null ? null : this.subscriptions.get(str);
            if (subscription == null) {
                Log.d(MediaBrowserCompat.TAG, "onLoadChildren for id that isn't subscribed id=" + str);
                return;
            }
            SubscriptionCallback callback = subscription.getCallback(bundle);
            if (callback != null) {
                if (bundle == null) {
                    if (list == null) {
                        callback.onError(str);
                        return;
                    }
                    this.notifyChildrenChangedOptions = bundle2;
                    callback.onChildrenLoaded(str, list);
                    this.notifyChildrenChangedOptions = null;
                    return;
                }
                if (list == null) {
                    callback.onError(str, bundle);
                    return;
                }
                this.notifyChildrenChangedOptions = bundle2;
                callback.onChildrenLoaded(str, list, bundle);
                this.notifyChildrenChangedOptions = null;
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getNotifyChildrenChangedOptions() {
            return this.notifyChildrenChangedOptions;
        }
    }

    static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        MediaBrowserImplApi26(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23, androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            if (this.serviceBinderWrapper != null && this.serviceVersion >= 2) {
                super.subscribe(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                this.browserFwk.subscribe(str, subscriptionCallback.subscriptionCallbackFwk);
            } else {
                this.browserFwk.subscribe(str, bundle, subscriptionCallback.subscriptionCallbackFwk);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23, androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
            if (this.serviceBinderWrapper != null && this.serviceVersion >= 2) {
                super.unsubscribe(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                this.browserFwk.unsubscribe(str);
            } else {
                this.browserFwk.unsubscribe(str, subscriptionCallback.subscriptionCallbackFwk);
            }
        }
    }

    private static class Subscription {
        private final List<SubscriptionCallback> callbacks = new ArrayList();
        private final List<Bundle> optionsList = new ArrayList();

        public boolean isEmpty() {
            return this.callbacks.isEmpty();
        }

        public List<Bundle> getOptionsList() {
            return this.optionsList;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.callbacks;
        }

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i = 0; i < this.optionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.optionsList.get(i), bundle)) {
                    return this.callbacks.get(i);
                }
            }
            return null;
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i = 0; i < this.optionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.optionsList.get(i), bundle)) {
                    this.callbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.callbacks.add(subscriptionCallback);
            this.optionsList.add(bundle);
        }
    }

    private static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> callbackImplRef;
        private WeakReference<Messenger> callbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.callbackImplRef = new WeakReference<>(mediaBrowserServiceCallbackImpl);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.callbacksMessengerRef;
            if (weakReference == null) {
                return;
            }
            Messenger messenger = weakReference.get();
            MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl = this.callbackImplRef.get();
            if (messenger == null || mediaBrowserServiceCallbackImpl == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.ensureClassLoader(data);
            try {
                if (message.what == 3) {
                    mediaBrowserServiceCallbackImpl.onLoadChildren(messenger, data.getString("data_media_item_id"), LegacyParcelableUtil.convertList(data.getParcelableArrayList("data_media_item_list"), MediaItem.CREATOR), Util.convertToNullIfInvalid(data.getBundle("data_options")), Util.convertToNullIfInvalid(data.getBundle("data_notify_children_changed_options")));
                } else {
                    Log.w(MediaBrowserCompat.TAG, "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                }
            } catch (BadParcelableException unused) {
                Log.e(MediaBrowserCompat.TAG, "Could not unparcel the data.");
            }
        }

        void setCallbacksMessenger(Messenger messenger) {
            this.callbacksMessengerRef = new WeakReference<>(messenger);
        }
    }

    private static class ServiceBinderWrapper {
        private final Messenger messenger;
        private final Bundle rootHints;

        public ServiceBinderWrapper(IBinder iBinder, Bundle bundle) {
            this.messenger = new Messenger(iBinder);
            this.rootHints = bundle;
        }

        void addSubscription(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putBinder("data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            sendRequest(3, bundle2, messenger);
        }

        void removeSubscription(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            bundle.putBinder("data_callback_token", iBinder);
            sendRequest(4, bundle, messenger);
        }

        void getMediaItem(String str, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            bundle.putParcelable("data_result_receiver", LegacyParcelableUtil.convert(resultReceiver, ResultReceiver.CREATOR));
            sendRequest(5, bundle, messenger);
        }

        void registerCallbackMessenger(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putInt("data_calling_pid", Process.myPid());
            bundle.putBundle("data_root_hints", this.rootHints);
            sendRequest(6, bundle, messenger);
        }

        void unregisterCallbackMessenger(Messenger messenger) throws RemoteException {
            sendRequest(7, null, messenger);
        }

        void search(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_search_query", str);
            bundle2.putBundle("data_search_extras", bundle);
            bundle2.putParcelable("data_result_receiver", LegacyParcelableUtil.convert(resultReceiver, ResultReceiver.CREATOR));
            sendRequest(8, bundle2, messenger);
        }

        void sendCustomAction(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_custom_action", str);
            bundle2.putBundle("data_custom_action_extras", bundle);
            bundle2.putParcelable("data_result_receiver", LegacyParcelableUtil.convert(resultReceiver, ResultReceiver.CREATOR));
            sendRequest(9, bundle2, messenger);
        }

        private void sendRequest(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 1;
            if (bundle != null) {
                messageObtain.setData(bundle);
            }
            messageObtain.replyTo = messenger;
            this.messenger.send(messageObtain);
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        private final ItemCallback callback;
        private final String mediaId;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mediaId = str;
            this.callback = itemCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle);
            if (i != 0 || bundleConvertToNullIfInvalid == null || !bundleConvertToNullIfInvalid.containsKey("media_item")) {
                this.callback.onError(this.mediaId);
            } else {
                this.callback.onItemLoaded((MediaItem) LegacyParcelableUtil.convert(bundleConvertToNullIfInvalid.getParcelable("media_item"), MediaItem.CREATOR));
            }
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback callback;
        private final Bundle extras;
        private final String query;

        SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.query = str;
            this.extras = bundle;
            this.callback = searchCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle);
            if (i != 0 || bundleConvertToNullIfInvalid == null || !bundleConvertToNullIfInvalid.containsKey("search_results")) {
                this.callback.onError(this.query, this.extras);
                return;
            }
            Parcelable[] parcelableArray = bundleConvertToNullIfInvalid.getParcelableArray("search_results");
            if (parcelableArray != null) {
                ArrayList arrayList = new ArrayList(parcelableArray.length);
                for (Parcelable parcelable : parcelableArray) {
                    arrayList.add((MediaItem) LegacyParcelableUtil.convert(parcelable, MediaItem.CREATOR));
                }
                this.callback.onSearchResult(this.query, this.extras, arrayList);
                return;
            }
            this.callback.onError(this.query, this.extras);
        }
    }

    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String action;
        private final CustomActionCallback callback;
        private final Bundle extras;

        CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.action = str;
            this.extras = bundle;
            this.callback = customActionCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            if (this.callback == null) {
                return;
            }
            Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle);
            if (i == -1) {
                this.callback.onError(this.action, this.extras, bundleConvertToNullIfInvalid);
                return;
            }
            if (i == 0) {
                this.callback.onResult(this.action, this.extras, bundleConvertToNullIfInvalid);
                return;
            }
            if (i == 1) {
                CustomActionCallback customActionCallback = this.callback;
                String str = this.action;
                Bundle bundle2 = this.extras;
                if (bundle2 == null) {
                    bundle2 = Bundle.EMPTY;
                }
                if (bundleConvertToNullIfInvalid == null) {
                    bundleConvertToNullIfInvalid = Bundle.EMPTY;
                }
                customActionCallback.onProgressUpdate(str, bundle2, bundleConvertToNullIfInvalid);
                return;
            }
            Log.w(MediaBrowserCompat.TAG, "Unknown result code: " + i + " (extras=" + this.extras + ", resultData=" + bundleConvertToNullIfInvalid + ")");
        }
    }
}

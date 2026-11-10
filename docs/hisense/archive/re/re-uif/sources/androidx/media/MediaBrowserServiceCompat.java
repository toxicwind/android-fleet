package androidx.media;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSessionManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.core.util.Pair;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
    ConnectionRecord mCurConnection;
    private MediaBrowserServiceImpl mImpl;
    MediaSessionCompat.Token mSession;
    final ConnectionRecord mConnectionFromFwk = new ConnectionRecord("android.media.session.MediaController", -1, -1, null, null);
    final ArrayList<ConnectionRecord> mPendingConnections = new ArrayList<>();
    final ArrayMap<IBinder, ConnectionRecord> mConnections = new ArrayMap<>();
    final ServiceHandler mHandler = new ServiceHandler();

    interface MediaBrowserServiceImpl {
        IBinder onBind(Intent intent);

        void onCreate();
    }

    private interface ServiceCallbacks {
        IBinder asBinder();

        void onConnect(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException;

        void onConnectFailed() throws RemoteException;

        void onLoadChildren(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract BrowserRoot onGetRoot(String str, int i, Bundle bundle);

    public abstract void onLoadChildren(String str, Result<List<MediaBrowserCompat.MediaItem>> result);

    public void onSubscribe(String str, Bundle bundle) {
    }

    public void onUnsubscribe(String str) {
    }

    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl {
        private Messenger mMessenger;

        MediaBrowserServiceImplBase() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public IBinder onBind(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.mMessenger.getBinder();
            }
            return null;
        }
    }

    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl {
        Messenger mMessenger;
        final List<Bundle> mRootExtrasList = new ArrayList();
        MediaBrowserService mServiceFwk;

        MediaBrowserServiceImplApi21() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            MediaBrowserServiceApi21 mediaBrowserServiceApi21 = new MediaBrowserServiceApi21(MediaBrowserServiceCompat.this);
            this.mServiceFwk = mediaBrowserServiceApi21;
            mediaBrowserServiceApi21.onCreate();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public IBinder onBind(Intent intent) {
            return this.mServiceFwk.onBind(intent);
        }

        public BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
            int i2;
            Bundle extras;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                i2 = -1;
                extras = null;
            } else {
                bundle.remove("extra_client_version");
                this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
                extras = new Bundle();
                extras.putInt("extra_service_version", 2);
                BundleCompat.putBinder(extras, "extra_messenger", this.mMessenger.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.mSession;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    BundleCompat.putBinder(extras, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.mRootExtrasList.add(extras);
                }
                int i3 = bundle.getInt("extra_calling_pid", -1);
                bundle.remove("extra_calling_pid");
                i2 = i3;
            }
            ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(str, i2, i, bundle, null);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.mCurConnection = connectionRecord;
            BrowserRoot browserRootOnGetRoot = mediaBrowserServiceCompat.onGetRoot(str, i, bundle);
            MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat2.mCurConnection = null;
            if (browserRootOnGetRoot == null) {
                return null;
            }
            if (this.mMessenger != null) {
                mediaBrowserServiceCompat2.mPendingConnections.add(connectionRecord);
            }
            if (extras == null) {
                extras = browserRootOnGetRoot.getExtras();
            } else if (browserRootOnGetRoot.getExtras() != null) {
                extras.putAll(browserRootOnGetRoot.getExtras());
            }
            return new BrowserRoot(browserRootOnGetRoot.getRootId(), extras);
        }

        public void onLoadChildren(String str, final ResultWrapper<List<Parcel>> resultWrapper) {
            Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(this, str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.2
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                    ArrayList arrayList;
                    if (list != null) {
                        arrayList = new ArrayList();
                        for (MediaBrowserCompat.MediaItem mediaItem : list) {
                            Parcel parcelObtain = Parcel.obtain();
                            mediaItem.writeToParcel(parcelObtain, 0);
                            arrayList.add(parcelObtain);
                        }
                    } else {
                        arrayList = null;
                    }
                    resultWrapper.sendResult(arrayList);
                }
            };
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.mCurConnection = mediaBrowserServiceCompat.mConnectionFromFwk;
            mediaBrowserServiceCompat.onLoadChildren(str, result);
            MediaBrowserServiceCompat.this.mCurConnection = null;
        }

        class MediaBrowserServiceApi21 extends MediaBrowserService {
            MediaBrowserServiceApi21(Context context) {
                attachBaseContext(context);
            }

            @Override // android.service.media.MediaBrowserService
            @SuppressLint({"SyntheticAccessor"})
            public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                BrowserRoot browserRootOnGetRoot = MediaBrowserServiceImplApi21.this.onGetRoot(str, i, bundle == null ? null : new Bundle(bundle));
                if (browserRootOnGetRoot == null) {
                    return null;
                }
                return new MediaBrowserService.BrowserRoot(browserRootOnGetRoot.mRootId, browserRootOnGetRoot.mExtras);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
                MediaBrowserServiceImplApi21.this.onLoadChildren(str, new ResultWrapper<>(result));
            }
        }
    }

    class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21 {
        MediaBrowserServiceImplApi23() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            MediaBrowserServiceApi23 mediaBrowserServiceApi23 = new MediaBrowserServiceApi23(MediaBrowserServiceCompat.this);
            this.mServiceFwk = mediaBrowserServiceApi23;
            mediaBrowserServiceApi23.onCreate();
        }

        public void onLoadItem(String str, final ResultWrapper<Parcel> resultWrapper) {
            Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(this, str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                public void onResultSent(MediaBrowserCompat.MediaItem mediaItem) {
                    if (mediaItem == null) {
                        resultWrapper.sendResult(null);
                        return;
                    }
                    Parcel parcelObtain = Parcel.obtain();
                    mediaItem.writeToParcel(parcelObtain, 0);
                    resultWrapper.sendResult(parcelObtain);
                }
            };
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.mCurConnection = mediaBrowserServiceCompat.mConnectionFromFwk;
            mediaBrowserServiceCompat.onLoadItem(str, result);
            MediaBrowserServiceCompat.this.mCurConnection = null;
        }

        class MediaBrowserServiceApi23 extends MediaBrowserServiceImplApi21.MediaBrowserServiceApi21 {
            MediaBrowserServiceApi23(Context context) {
                super(context);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
                MediaBrowserServiceImplApi23.this.onLoadItem(str, new ResultWrapper<>(result));
            }
        }
    }

    class MediaBrowserServiceImplApi26 extends MediaBrowserServiceImplApi23 {
        MediaBrowserServiceImplApi26() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21, androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImpl
        public void onCreate() {
            MediaBrowserServiceApi26 mediaBrowserServiceApi26 = new MediaBrowserServiceApi26(MediaBrowserServiceCompat.this);
            this.mServiceFwk = mediaBrowserServiceApi26;
            mediaBrowserServiceApi26.onCreate();
        }

        public void onLoadChildren(String str, final ResultWrapper<List<Parcel>> resultWrapper, final Bundle bundle) {
            Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi26.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // androidx.media.MediaBrowserServiceCompat.Result
                public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                    if (list == null) {
                        resultWrapper.sendResult(null);
                        return;
                    }
                    if ((getFlags() & 1) != 0) {
                        list = MediaBrowserServiceCompat.this.applyOptions(list, bundle);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                    resultWrapper.sendResult(arrayList);
                }
            };
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.mCurConnection = mediaBrowserServiceCompat.mConnectionFromFwk;
            mediaBrowserServiceCompat.onLoadChildren(str, result, bundle);
            MediaBrowserServiceCompat.this.mCurConnection = null;
        }

        class MediaBrowserServiceApi26 extends MediaBrowserServiceImplApi23.MediaBrowserServiceApi23 {
            MediaBrowserServiceApi26(Context context) {
                super(context);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                MediaBrowserServiceImplApi26 mediaBrowserServiceImplApi26 = MediaBrowserServiceImplApi26.this;
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.mCurConnection = mediaBrowserServiceCompat.mConnectionFromFwk;
                mediaBrowserServiceImplApi26.onLoadChildren(str, new ResultWrapper<>(result), bundle);
                MediaBrowserServiceCompat.this.mCurConnection = null;
            }
        }
    }

    class MediaBrowserServiceImplApi28 extends MediaBrowserServiceImplApi26 {
        MediaBrowserServiceImplApi28(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
        }
    }

    private final class ServiceHandler extends Handler {
        private final ServiceBinderImpl mServiceBinderImpl;

        ServiceHandler() {
            this.mServiceBinderImpl = MediaBrowserServiceCompat.this.new ServiceBinderImpl();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.mServiceBinderImpl.connect(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 2:
                    this.mServiceBinderImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.mServiceBinderImpl.addSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), bundle2, new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 4:
                    this.mServiceBinderImpl.removeSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 5:
                    this.mServiceBinderImpl.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.mServiceBinderImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.mServiceBinderImpl.unregisterCallbacks(new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.mServiceBinderImpl.search(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.mServiceBinderImpl.sendCustomAction(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            int callingPid = Binder.getCallingPid();
            if (callingPid > 0) {
                data.putInt("data_calling_pid", callingPid);
            } else if (!data.containsKey("data_calling_pid")) {
                data.putInt("data_calling_pid", -1);
            }
            return super.sendMessageAtTime(message, j);
        }

        public void postOrRun(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }
    }

    private class ConnectionRecord implements IBinder.DeathRecipient {
        public final ServiceCallbacks callbacks;
        public final int pid;
        public final String pkg;
        public BrowserRoot root;
        public final HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions = new HashMap<>();
        public final int uid;

        ConnectionRecord(final String str, final int i, final int i2, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            this.pkg = str;
            this.pid = i;
            this.uid = i2;
            new Object(str, i, i2) { // from class: androidx.media.MediaSessionManager$RemoteUserInfo
                MediaSessionManager$RemoteUserInfoImpl mImpl;

                {
                    if (Build.VERSION.SDK_INT >= 28) {
                        this.mImpl = new MediaSessionManagerImplBase$RemoteUserInfoImplBase(str, i, i2) { // from class: androidx.media.MediaSessionManagerImplApi28$RemoteUserInfoImplApi28
                            {
                                super(str, i, i2);
                                new MediaSessionManager.RemoteUserInfo(str, i, i2);
                            }
                        };
                    } else {
                        this.mImpl = new MediaSessionManagerImplBase$RemoteUserInfoImplBase(str, i, i2);
                    }
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj instanceof MediaSessionManager$RemoteUserInfo) {
                        return this.mImpl.equals(((MediaSessionManager$RemoteUserInfo) obj).mImpl);
                    }
                    return false;
                }

                public int hashCode() {
                    return this.mImpl.hashCode();
                }
            };
            this.callbacks = serviceCallbacks;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.mHandler.post(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ConnectionRecord.1
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = ConnectionRecord.this;
                    MediaBrowserServiceCompat.this.mConnections.remove(connectionRecord.callbacks.asBinder());
                }
            });
        }
    }

    public static class Result<T> {
        private final Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendErrorCalled;
        private boolean mSendResultCalled;

        abstract void onResultSent(T t);

        Result(Object obj) {
            this.mDebug = obj;
        }

        public void sendResult(T t) {
            if (this.mSendResultCalled || this.mSendErrorCalled) {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
            }
            this.mSendResultCalled = true;
            onResultSent(t);
        }

        public void sendError(Bundle bundle) {
            if (this.mSendResultCalled || this.mSendErrorCalled) {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.mDebug);
            }
            this.mSendErrorCalled = true;
            onErrorSent(bundle);
        }

        boolean isDone() {
            return this.mDetachCalled || this.mSendResultCalled || this.mSendErrorCalled;
        }

        void setFlags(int i) {
            this.mFlags = i;
        }

        int getFlags() {
            return this.mFlags;
        }

        void onErrorSent(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.mDebug);
        }
    }

    private class ServiceBinderImpl {
        ServiceBinderImpl() {
        }

        public void connect(final String str, final int i, final int i2, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            if (!MediaBrowserServiceCompat.this.isValidPackage(str, i2)) {
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + i2 + " package=" + str);
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    IBinder iBinderAsBinder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(iBinderAsBinder);
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(str, i, i2, bundle, serviceCallbacks);
                    MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                    mediaBrowserServiceCompat.mCurConnection = connectionRecord;
                    BrowserRoot browserRootOnGetRoot = mediaBrowserServiceCompat.onGetRoot(str, i2, bundle);
                    connectionRecord.root = browserRootOnGetRoot;
                    MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                    mediaBrowserServiceCompat2.mCurConnection = null;
                    if (browserRootOnGetRoot == null) {
                        Log.i("MBServiceCompat", "No root for client " + str + " from service " + AnonymousClass1.class.getName());
                        try {
                            serviceCallbacks.onConnectFailed();
                            return;
                        } catch (RemoteException unused) {
                            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + str);
                            return;
                        }
                    }
                    try {
                        mediaBrowserServiceCompat2.mConnections.put(iBinderAsBinder, connectionRecord);
                        iBinderAsBinder.linkToDeath(connectionRecord, 0);
                        if (MediaBrowserServiceCompat.this.mSession != null) {
                            serviceCallbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                        }
                    } catch (RemoteException unused2) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + str);
                        MediaBrowserServiceCompat.this.mConnections.remove(iBinderAsBinder);
                    }
                }
            });
        }

        public void disconnect(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecordRemove = MediaBrowserServiceCompat.this.mConnections.remove(serviceCallbacks.asBinder());
                    if (connectionRecordRemove != null) {
                        connectionRecordRemove.callbacks.asBinder().unlinkToDeath(connectionRecordRemove, 0);
                    }
                }
            });
        }

        public void addSubscription(final String str, final IBinder iBinder, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + str);
                        return;
                    }
                    MediaBrowserServiceCompat.this.addSubscription(str, connectionRecord, iBinder, bundle);
                }
            });
        }

        public void removeSubscription(final String str, final IBinder iBinder, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + str);
                        return;
                    }
                    if (MediaBrowserServiceCompat.this.removeSubscription(str, connectionRecord, iBinder)) {
                        return;
                    }
                    Log.w("MBServiceCompat", "removeSubscription called for " + str + " which is not subscribed");
                }
            });
        }

        public void getMediaItem(final String str, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + str);
                        return;
                    }
                    MediaBrowserServiceCompat.this.performLoadItem(str, connectionRecord, resultReceiver);
                }
            });
        }

        public void registerCallbacks(final ServiceCallbacks serviceCallbacks, final String str, final int i, final int i2, final Bundle bundle) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    IBinder iBinderAsBinder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(iBinderAsBinder);
                    Iterator<ConnectionRecord> it = MediaBrowserServiceCompat.this.mPendingConnections.iterator();
                    ConnectionRecord connectionRecord = null;
                    while (it.hasNext()) {
                        ConnectionRecord next = it.next();
                        if (next.uid == i2) {
                            if (TextUtils.isEmpty(str) || i <= 0) {
                                connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(next.pkg, next.pid, next.uid, bundle, serviceCallbacks);
                            }
                            it.remove();
                        }
                    }
                    if (connectionRecord == null) {
                        connectionRecord = MediaBrowserServiceCompat.this.new ConnectionRecord(str, i, i2, bundle, serviceCallbacks);
                    }
                    MediaBrowserServiceCompat.this.mConnections.put(iBinderAsBinder, connectionRecord);
                    try {
                        iBinderAsBinder.linkToDeath(connectionRecord, 0);
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "IBinder is already dead.");
                    }
                }
            });
        }

        public void unregisterCallbacks(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    IBinder iBinderAsBinder = serviceCallbacks.asBinder();
                    ConnectionRecord connectionRecordRemove = MediaBrowserServiceCompat.this.mConnections.remove(iBinderAsBinder);
                    if (connectionRecordRemove != null) {
                        iBinderAsBinder.unlinkToDeath(connectionRecordRemove, 0);
                    }
                }
            });
        }

        public void search(final String str, final Bundle bundle, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "search for callback that isn't registered query=" + str);
                        return;
                    }
                    MediaBrowserServiceCompat.this.performSearch(str, bundle, connectionRecord, resultReceiver);
                }
            });
        }

        public void sendCustomAction(final String str, final Bundle bundle, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() { // from class: androidx.media.MediaBrowserServiceCompat.ServiceBinderImpl.9
                @Override // java.lang.Runnable
                public void run() {
                    ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + str + ", extras=" + bundle);
                        return;
                    }
                    MediaBrowserServiceCompat.this.performCustomAction(str, bundle, connectionRecord, resultReceiver);
                }
            });
        }
    }

    private static class ServiceCallbacksCompat implements ServiceCallbacks {
        final Messenger mCallbacks;

        ServiceCallbacksCompat(Messenger messenger) {
            this.mCallbacks = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public IBinder asBinder() {
            return this.mCallbacks.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void onConnect(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 2);
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putParcelable("data_media_session_token", token);
            bundle2.putBundle("data_root_hints", bundle);
            sendRequest(1, bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void onConnectFailed() throws RemoteException {
            sendRequest(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.ServiceCallbacks
        public void onLoadChildren(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            sendRequest(3, bundle3);
        }

        private void sendRequest(int i, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.mCallbacks.send(messageObtain);
        }
    }

    static class ResultWrapper<T> {
        MediaBrowserService.Result mResultFwk;

        ResultWrapper(MediaBrowserService.Result result) {
            this.mResultFwk = result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void sendResult(T t) {
            if (t instanceof List) {
                this.mResultFwk.sendResult(parcelListToItemList((List) t));
                return;
            }
            if (t instanceof Parcel) {
                Parcel parcel = (Parcel) t;
                parcel.setDataPosition(0);
                this.mResultFwk.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
                return;
            }
            this.mResultFwk.sendResult(null);
        }

        List<MediaBrowser.MediaItem> parcelListToItemList(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.mImpl = new MediaBrowserServiceImplApi28(this);
        } else if (i >= 26) {
            this.mImpl = new MediaBrowserServiceImplApi26();
        } else if (i >= 23) {
            this.mImpl = new MediaBrowserServiceImplApi23();
        } else if (i >= 21) {
            this.mImpl = new MediaBrowserServiceImplApi21();
        } else {
            this.mImpl = new MediaBrowserServiceImplBase();
        }
        this.mImpl.onCreate();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    public void onLoadChildren(String str, Result<List<MediaBrowserCompat.MediaItem>> result, Bundle bundle) {
        result.setFlags(1);
        onLoadChildren(str, result);
    }

    public void onLoadItem(String str, Result<MediaBrowserCompat.MediaItem> result) {
        result.setFlags(2);
        result.sendResult(null);
    }

    public void onSearch(String str, Bundle bundle, Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.setFlags(4);
        result.sendResult(null);
    }

    public void onCustomAction(String str, Bundle bundle, Result<Bundle> result) {
        result.sendError(null);
    }

    boolean isValidPackage(String str, int i) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    void addSubscription(String str, ConnectionRecord connectionRecord, IBinder iBinder, Bundle bundle) {
        List<Pair<IBinder, Bundle>> arrayList = connectionRecord.subscriptions.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (Pair<IBinder, Bundle> pair : arrayList) {
            if (iBinder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, pair.second)) {
                return;
            }
        }
        arrayList.add(new Pair<>(iBinder, bundle));
        connectionRecord.subscriptions.put(str, arrayList);
        performLoadChildren(str, connectionRecord, bundle, null);
        onSubscribe(str, bundle);
    }

    boolean removeSubscription(String str, ConnectionRecord connectionRecord, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                boolean z2 = connectionRecord.subscriptions.remove(str) != null;
                onUnsubscribe(str);
                return z2;
            }
            List<Pair<IBinder, Bundle>> list = connectionRecord.subscriptions.get(str);
            if (list != null) {
                Iterator<Pair<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().first) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    connectionRecord.subscriptions.remove(str);
                }
            }
            onUnsubscribe(str);
            return z;
        } catch (Throwable th) {
            onUnsubscribe(str);
            throw th;
        }
    }

    void performLoadChildren(final String str, final ConnectionRecord connectionRecord, final Bundle bundle, final Bundle bundle2) {
        Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(str) { // from class: androidx.media.MediaBrowserServiceCompat.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                if (MediaBrowserServiceCompat.this.mConnections.get(connectionRecord.callbacks.asBinder()) != connectionRecord) {
                    if (MediaBrowserServiceCompat.DEBUG) {
                        Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + connectionRecord.pkg + " id=" + str);
                        return;
                    }
                    return;
                }
                if ((getFlags() & 1) != 0) {
                    list = MediaBrowserServiceCompat.this.applyOptions(list, bundle);
                }
                try {
                    connectionRecord.callbacks.onLoadChildren(str, list, bundle, bundle2);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + str + " package=" + connectionRecord.pkg);
                }
            }
        };
        if (bundle == null) {
            onLoadChildren(str, result);
        } else {
            onLoadChildren(str, result, bundle);
        }
        if (result.isDone()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connectionRecord.pkg + " id=" + str);
    }

    List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i = bundle.getInt("android.media.browse.extra.PAGE", -1);
        int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if (i == -1 && i2 == -1) {
            return list;
        }
        int i3 = i2 * i;
        int size = i3 + i2;
        if (i < 0 || i2 < 1 || i3 >= list.size()) {
            return Collections.emptyList();
        }
        if (size > list.size()) {
            size = list.size();
        }
        return list.subList(i3, size);
    }

    void performLoadItem(String str, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(this, str) { // from class: androidx.media.MediaBrowserServiceCompat.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void onResultSent(MediaBrowserCompat.MediaItem mediaItem) {
                if ((getFlags() & 2) != 0) {
                    resultReceiver.send(-1, null);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("media_item", mediaItem);
                resultReceiver.send(0, bundle);
            }
        };
        onLoadItem(str, result);
        if (result.isDone()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    void performSearch(String str, Bundle bundle, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(this, str) { // from class: androidx.media.MediaBrowserServiceCompat.3
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                if ((getFlags() & 4) != 0 || list == null) {
                    resultReceiver.send(-1, null);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
                resultReceiver.send(0, bundle2);
            }
        };
        onSearch(str, bundle, result);
        if (result.isDone()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    void performCustomAction(String str, Bundle bundle, ConnectionRecord connectionRecord, final ResultReceiver resultReceiver) {
        Result<Bundle> result = new Result<Bundle>(this, str) { // from class: androidx.media.MediaBrowserServiceCompat.4
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void onResultSent(Bundle bundle2) {
                resultReceiver.send(0, bundle2);
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            void onErrorSent(Bundle bundle2) {
                resultReceiver.send(-1, bundle2);
            }
        };
        onCustomAction(str, bundle, result);
        if (result.isDone()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    public static final class BrowserRoot {
        private final Bundle mExtras;
        private final String mRootId;

        public BrowserRoot(String str, Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
            }
            this.mRootId = str;
            this.mExtras = bundle;
        }

        public String getRootId() {
            return this.mRootId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }
    }
}

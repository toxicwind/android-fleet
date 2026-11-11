package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class MediaBrowserImplLegacy extends MediaControllerImplLegacy implements MediaBrowser.MediaBrowserImpl {
    private static final String TAG = "MB2ImplLegacy";
    private final HashMap<MediaLibraryService.LibraryParams, MediaBrowserCompat> browserCompats;
    private ImmutableMap<String, CommandButton> commandButtonsForMediaItems;
    private final MediaBrowser instance;
    private final HashMap<String, List<SubscribeCallback>> subscribeCallbacks;

    MediaBrowserImplLegacy(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j) {
        super(context, mediaBrowser, sessionToken, bundle, looper, bitmapLoader, j);
        this.browserCompats = new HashMap<>();
        this.subscribeCallbacks = new HashMap<>();
        this.instance = mediaBrowser;
        this.commandButtonsForMediaItems = ImmutableMap.of();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.media3.session.MediaControllerImplLegacy
    public MediaBrowser getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        Iterator<MediaBrowserCompat> it = this.browserCompats.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect();
        }
        this.browserCompats.clear();
        super.release();
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        if (getBrowserCompat() != null) {
            return super.getAvailableSessionCommands().buildUpon().addAllLibraryCommands().build();
        }
        return super.getAvailableSessionCommands();
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItems.get(immutableList.get(i));
            if (commandButton != null && commandButton.sessionCommand != null) {
                builder.add(commandButton);
            }
        }
        return builder.build();
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(50000)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        SettableFuture settableFutureCreate = SettableFuture.create();
        MediaBrowserCompat browserCompat = getBrowserCompat(libraryParams);
        if (browserCompat != null) {
            settableFutureCreate.set(LibraryResult.ofItem(createRootMediaItem(browserCompat), null));
            return settableFutureCreate;
        }
        Bundle bundle = libraryParams == null ? new Bundle() : LegacyConversions.convertToRootHints(libraryParams);
        bundle.putInt("androidx.media.utils.MediaBrowserCompat.extras.CUSTOM_BROWSER_ACTION_LIMIT", getInstance().getMaxCommandsForMediaItems());
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(getContext(), getConnectedToken().getComponentName(), new GetLibraryRootCallback(settableFutureCreate, libraryParams), bundle);
        this.browserCompats.put(libraryParams, mediaBrowserCompat);
        mediaBrowserCompat.connect();
        return settableFutureCreate;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle bundleCreateOptionsForSubscription = createOptionsForSubscription(libraryParams);
        SettableFuture settableFutureCreate = SettableFuture.create();
        SubscribeCallback subscribeCallback = new SubscribeCallback(str, bundleCreateOptionsForSubscription, settableFutureCreate);
        List<SubscribeCallback> arrayList = this.subscribeCallbacks.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.subscribeCallbacks.put(str, arrayList);
        }
        arrayList.add(subscribeCallback);
        browserCompat.subscribe(str, bundleCreateOptionsForSubscription, subscribeCallback);
        return settableFutureCreate;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> unsubscribe(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-3));
        }
        for (int i = 0; i < list.size(); i++) {
            browserCompat.unsubscribe(str, list.get(i));
        }
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle bundleCreateOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        SettableFuture settableFutureCreate = SettableFuture.create();
        List<MediaBrowserCompat.MediaItem> childrenFromSubscription = getChildrenFromSubscription(str, i);
        evictChildrenFromSubscription(str);
        if (childrenFromSubscription != null) {
            settableFutureCreate.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(childrenFromSubscription), new MediaLibraryService.LibraryParams.Builder().setExtras(bundleCreateOptionsWithPagingInfo).build()));
            return settableFutureCreate;
        }
        browserCompat.subscribe(str, bundleCreateOptionsWithPagingInfo, new GetChildrenCallback(settableFutureCreate, str));
        return settableFutureCreate;
    }

    private List<MediaBrowserCompat.MediaItem> getChildrenFromSubscription(String str, int i) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).canServeGetChildrenRequest(str, i)) {
                return list.get(i2).receivedChildren;
            }
        }
        return null;
    }

    private void evictChildrenFromSubscription(String str) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).receivedChildren != null) {
                list.get(i).receivedChildren = null;
                return;
            }
        }
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getItem(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture settableFutureCreate = SettableFuture.create();
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.1
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onItemLoaded(MediaBrowserCompat.MediaItem mediaItem) {
                SettableFuture settableFuture = settableFutureCreate;
                if (mediaItem != null) {
                    settableFuture.set(LibraryResult.ofItem(LegacyConversions.convertToMediaItem(mediaItem), null));
                } else {
                    settableFuture.set(LibraryResult.ofError(-3));
                }
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onError(String str2) {
                settableFutureCreate.set(LibraryResult.ofError(-1));
            }
        });
        return settableFutureCreate;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        browserCompat.search(str, getExtras(libraryParams), new AnonymousClass2());
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaBrowserImplLegacy$2, reason: invalid class name */
    class AnonymousClass2 extends MediaBrowserCompat.SearchCallback {
        AnonymousClass2() {
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onSearchResult(final String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$2$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7642x87f18d83(str, list, (MediaBrowser.Listener) obj);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onSearchResult$0$androidx-media3-session-MediaBrowserImplLegacy$2, reason: not valid java name */
        /* synthetic */ void m7642x87f18d83(String str, List list, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), null);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onError(final String str, Bundle bundle) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$2$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7641x841a1c17(str, (MediaBrowser.Listener) obj);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onError$1$androidx-media3-session-MediaBrowserImplLegacy$2, reason: not valid java name */
        /* synthetic */ void m7641x841a1c17(String str, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, 0, null);
        }
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture settableFutureCreate = SettableFuture.create();
        Bundle bundleCreateOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        bundleCreateOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE", i);
        bundleCreateOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        browserCompat.search(str, bundleCreateOptionsWithPagingInfo, new MediaBrowserCompat.SearchCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.3
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onSearchResult(String str2, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
                settableFutureCreate.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onError(String str2, Bundle bundle) {
                settableFutureCreate.set(LibraryResult.ofError(-1));
            }
        });
        return settableFutureCreate;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        return sendCustomCommand(sessionCommand, bundle, null);
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public ListenableFuture<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle, final MediaController.ProgressListener progressListener) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (getAvailableSessionCommands().contains(sessionCommand)) {
            return super.sendCustomCommand(sessionCommand, bundle);
        }
        if (browserCompat != null) {
            final SettableFuture settableFutureCreate = SettableFuture.create();
            browserCompat.sendCustomAction(sessionCommand.customAction, bundle, new MediaBrowserCompat.CustomActionCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.4
                @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
                public void onProgressUpdate(String str, Bundle bundle2, Bundle bundle3) {
                    MediaController.ProgressListener progressListener2 = progressListener;
                    if (progressListener2 != null) {
                        progressListener2.onProgress(MediaBrowserImplLegacy.this.getInstance(), sessionCommand, bundle, bundle3);
                    }
                }

                @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
                public void onResult(String str, Bundle bundle2, Bundle bundle3) {
                    Bundle bundle4 = new Bundle(bundle2);
                    bundle4.putAll(bundle3);
                    settableFutureCreate.set(new SessionResult(0, bundle4));
                }

                @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
                public void onError(String str, Bundle bundle2, Bundle bundle3) {
                    Bundle bundle4 = new Bundle(bundle2);
                    bundle4.putAll(bundle3);
                    settableFutureCreate.set(new SessionResult(-1, bundle4));
                }
            });
            return settableFutureCreate;
        }
        return Futures.immediateFuture(new SessionResult(-4));
    }

    private MediaBrowserCompat getBrowserCompat(MediaLibraryService.LibraryParams libraryParams) {
        return this.browserCompats.get(libraryParams);
    }

    private static Bundle createOptionsForSubscription(MediaLibraryService.LibraryParams libraryParams) {
        return libraryParams == null ? new Bundle() : new Bundle(libraryParams.extras);
    }

    private static Bundle createOptionsWithPagingInfo(MediaLibraryService.LibraryParams libraryParams, int i, int i2) {
        Bundle bundleCreateOptionsForSubscription = createOptionsForSubscription(libraryParams);
        bundleCreateOptionsForSubscription.putInt("android.media.browse.extra.PAGE", i);
        bundleCreateOptionsForSubscription.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        return bundleCreateOptionsForSubscription;
    }

    private static Bundle getExtras(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams != null) {
            return libraryParams.extras;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaItem createRootMediaItem(MediaBrowserCompat mediaBrowserCompat) {
        String root = mediaBrowserCompat.getRoot();
        return new MediaItem.Builder().setMediaId(root).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(true).setMediaType(20).setIsPlayable(false).setExtras(mediaBrowserCompat.getExtras()).build()).build();
    }

    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        private final MediaLibraryService.LibraryParams params;
        private final SettableFuture<LibraryResult<MediaItem>> result;

        public GetLibraryRootCallback(SettableFuture<LibraryResult<MediaItem>> settableFuture, MediaLibraryService.LibraryParams libraryParams) {
            this.result = settableFuture;
            this.params = libraryParams;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            ArrayList parcelableArrayList;
            MediaBrowserCompat mediaBrowserCompat = (MediaBrowserCompat) MediaBrowserImplLegacy.this.browserCompats.get(this.params);
            if (mediaBrowserCompat == null) {
                this.result.set(LibraryResult.ofError(-1));
                return;
            }
            Bundle extras = mediaBrowserCompat.getExtras();
            if (extras != null && (parcelableArrayList = extras.getParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST")) != null) {
                ImmutableMap.Builder builderPutAll = null;
                for (int i = 0; i < parcelableArrayList.size(); i++) {
                    CommandButton commandButtonConvertCustomBrowseActionToCommandButton = LegacyConversions.convertCustomBrowseActionToCommandButton((Bundle) parcelableArrayList.get(i));
                    if (commandButtonConvertCustomBrowseActionToCommandButton != null) {
                        if (builderPutAll == null) {
                            builderPutAll = new ImmutableMap.Builder().putAll(MediaBrowserImplLegacy.this.commandButtonsForMediaItems);
                        }
                        builderPutAll.put(((SessionCommand) Preconditions.checkNotNull(commandButtonConvertCustomBrowseActionToCommandButton.sessionCommand)).customAction, commandButtonConvertCustomBrowseActionToCommandButton);
                    }
                }
                if (builderPutAll != null) {
                    MediaBrowserImplLegacy.this.commandButtonsForMediaItems = builderPutAll.buildKeepingLast();
                }
            }
            this.result.set(LibraryResult.ofItem(MediaBrowserImplLegacy.this.createRootMediaItem(mediaBrowserCompat), LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, extras)));
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            onConnectionFailed();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            this.result.set(LibraryResult.ofError(-3));
            MediaBrowserImplLegacy.this.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<Void>> future;
        private List<MediaBrowserCompat.MediaItem> receivedChildren;
        private final Bundle subscriptionOptions;
        private final String subscriptionParentId;

        public SubscribeCallback(String str, Bundle bundle, SettableFuture<LibraryResult<Void>> settableFuture) {
            this.subscriptionParentId = str;
            this.subscriptionOptions = bundle;
            this.future = settableFuture;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onError(this.subscriptionParentId, this.subscriptionOptions);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal(this.subscriptionParentId, this.subscriptionOptions);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        private void onErrorInternal(final String str, final Bundle bundle) {
            if (this.future.isDone()) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda1
                    @Override // androidx.media3.common.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m7644xeed82158(str, bundle, (MediaBrowser.Listener) obj);
                    }
                });
            }
            this.future.set(LibraryResult.ofError(-1));
        }

        /* JADX INFO: renamed from: lambda$onErrorInternal$0$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback, reason: not valid java name */
        /* synthetic */ void m7644xeed82158(String str, Bundle bundle, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, Integer.MAX_VALUE, new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build());
        }

        private void onChildrenLoadedInternal(final String str, final List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "SubscribeCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                return;
            }
            if (list == null) {
                onError(this.subscriptionParentId, this.subscriptionOptions);
                return;
            }
            final MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, browserCompat.getNotifyChildrenChangedOptions());
            this.receivedChildren = list;
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7643x14c2eea3(str, list, libraryParamsConvertToLibraryParams, (MediaBrowser.Listener) obj);
                }
            });
            this.future.set(LibraryResult.ofVoid());
        }

        /* JADX INFO: renamed from: lambda$onChildrenLoadedInternal$1$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback, reason: not valid java name */
        /* synthetic */ void m7643x14c2eea3(String str, List list, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), libraryParams);
        }

        public boolean canServeGetChildrenRequest(String str, int i) {
            return this.subscriptionParentId.equals(str) && this.receivedChildren != null && i == this.subscriptionOptions.getInt("android.media.browse.extra.PAGE", 0);
        }
    }

    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<ImmutableList<MediaItem>>> future;
        private final String parentId;

        public GetChildrenCallback(SettableFuture<LibraryResult<ImmutableList<MediaItem>>> settableFuture, String str) {
            this.future = settableFuture;
            this.parentId = str;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onErrorInternal();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(str, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(str, list);
        }

        private void onErrorInternal() {
            this.future.set(LibraryResult.ofError(-1));
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "GetChildrenCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                this.future.set(LibraryResult.ofError(-100));
                return;
            }
            browserCompat.unsubscribe(this.parentId, this);
            SettableFuture<LibraryResult<ImmutableList<MediaItem>>> settableFuture = this.future;
            if (list == null) {
                settableFuture.set(LibraryResult.ofError(-1));
            } else {
                settableFuture.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }
        }
    }
}

package androidx.media3.session;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
class MediaLibraryServiceLegacyStub extends MediaSessionServiceLegacyStub {
    private static final String TAG = "MLSLegacyStub";
    private final MediaSession.ControllerCb browserLegacyCbForBroadcast;
    private final MediaLibrarySessionImpl librarySessionImpl;

    private static <T> void ignoreFuture(Future<T> future) {
    }

    public MediaLibraryServiceLegacyStub(MediaLibrarySessionImpl mediaLibrarySessionImpl) {
        super(mediaLibrarySessionImpl);
        this.librarySessionImpl = mediaLibrarySessionImpl;
        this.browserLegacyCbForBroadcast = new BrowserLegacyCbForBroadcast();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.media3.session.MediaSessionServiceLegacyStub, androidx.media3.session.legacy.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        final MediaSession.ControllerInfo currentController;
        LibraryResult libraryResult;
        Bundle bundle2;
        if (super.onGetRoot(str, i, bundle) == null || (currentController = getCurrentController()) == null || !getConnectedControllersManager().isSessionCommandAvailable(currentController, 50000)) {
            return null;
        }
        final MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle);
        final AtomicReference atomicReference = new AtomicReference();
        final ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7753xa7534c8(atomicReference, currentController, libraryParamsConvertToLibraryParams, conditionVariable);
            }
        });
        try {
            conditionVariable.block();
            libraryResult = (LibraryResult) Preconditions.checkNotNull((LibraryResult) ((ListenableFuture) atomicReference.get()).get(), "LibraryResult must not be null");
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.e(TAG, "Couldn't get a result from onGetLibraryRoot", e);
            libraryResult = null;
        }
        if (libraryResult != null && libraryResult.resultCode == 0 && libraryResult.value != 0) {
            if (libraryResult.params != null) {
                bundle2 = LegacyConversions.convertToRootHints(libraryResult.params);
            } else {
                bundle2 = new Bundle();
            }
            ((Bundle) Preconditions.checkNotNull(bundle2)).putBoolean("android.media.browse.SEARCH_SUPPORTED", getConnectedControllersManager().isSessionCommandAvailable(currentController, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH));
            ImmutableList<CommandButton> commandButtonsForMediaItems = this.librarySessionImpl.getCommandButtonsForMediaItems();
            if (!commandButtonsForMediaItems.isEmpty()) {
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < commandButtonsForMediaItems.size(); i2++) {
                    CommandButton commandButton = commandButtonsForMediaItems.get(i2);
                    if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                        arrayList.add(LegacyConversions.convertToBundle(commandButton));
                    }
                }
                if (!arrayList.isEmpty()) {
                    bundle2.putParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST", arrayList);
                }
            }
            return new MediaBrowserServiceCompat.BrowserRoot(((MediaItem) libraryResult.value).mediaId, bundle2);
        }
        if (libraryResult == null || libraryResult.resultCode == 0) {
            return MediaUtils.defaultBrowserRoot;
        }
        return null;
    }

    /* JADX INFO: renamed from: lambda$onGetRoot$0$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7753xa7534c8(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams, ConditionVariable conditionVariable) {
        atomicReference.set(this.librarySessionImpl.onGetLibraryRootOnHandler(controllerInfo, libraryParams));
        conditionVariable.open();
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onSubscribe(final String str, final Bundle bundle) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSubscribe(): Ignoring empty id from " + currentController);
            return;
        }
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7757x33fceefb(currentController, bundle, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onSubscribe$1$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7757x33fceefb(MediaSession.ControllerInfo controllerInfo, Bundle bundle, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onSubscribeOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
        }
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onUnsubscribe(final String str) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onUnsubscribe(): Ignoring empty id from " + currentController);
            return;
        }
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7758xd6387fc3(currentController, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onUnsubscribe$2$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7758xd6387fc3(MediaSession.ControllerInfo controllerInfo, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onUnsubscribeOnHandler(controllerInfo, str));
        }
    }

    @Override // androidx.media3.session.MediaSessionServiceLegacyStub, androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, null);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(final String str, final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, final Bundle bundle) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onLoadChildren(): Ignoring empty parentId from " + currentController);
            result.sendResult(null);
            return;
        }
        result.detach();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7754x4e3aab0(currentController, result, bundle, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onLoadChildren$3$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7754x4e3aab0(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle, String str) {
        MediaSession.ControllerInfo controllerInfo2;
        String str2;
        MediaLibraryService.LibraryParams libraryParams;
        MediaLibraryService.LibraryParams libraryParams2 = null;
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            result.sendResult(null);
            return;
        }
        if (bundle != null) {
            bundle.setClassLoader(this.librarySessionImpl.getContext().getClassLoader());
            try {
                int i = bundle.getInt("android.media.browse.extra.PAGE");
                int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE");
                MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle);
                if (i < 0 || i2 <= 0) {
                    controllerInfo2 = controllerInfo;
                    str2 = str;
                    libraryParams = libraryParamsConvertToLibraryParams;
                } else {
                    try {
                        controllerInfo2 = controllerInfo;
                        try {
                            str2 = str;
                            try {
                                sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str, i, i2, libraryParamsConvertToLibraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
                                return;
                            } catch (BadParcelableException unused) {
                                libraryParams2 = libraryParamsConvertToLibraryParams;
                                libraryParams = libraryParams2;
                                sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str2, 0, Integer.MAX_VALUE, libraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
                            }
                        } catch (BadParcelableException unused2) {
                            str2 = str;
                        }
                    } catch (BadParcelableException unused3) {
                        controllerInfo2 = controllerInfo;
                        str2 = str;
                    }
                }
            } catch (BadParcelableException unused4) {
                controllerInfo2 = controllerInfo;
                str2 = str;
            }
        } else {
            controllerInfo2 = controllerInfo;
            str2 = str;
            libraryParams = libraryParams2;
        }
        sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str2, 0, Integer.MAX_VALUE, libraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadItem(final String str, final MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty itemId from " + currentController);
            result.sendResult(null);
            return;
        }
        result.detach();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7755xa4bca205(currentController, result, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onLoadItem$4$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7755xa4bca205(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            result.sendResult(null);
        } else {
            sendLibraryResultWithMediaItemWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetItemOnHandler(controllerInfo, str), createMediaItemToBrowserItemAsyncFunction()));
        }
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onSearch(final String str, final Bundle bundle, final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty query from " + currentController);
            result.sendResult(null);
            return;
        }
        if (currentController.getControllerCb() instanceof BrowserLegacyCb) {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7756xa75a61d5(currentController, result, str, bundle);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onSearch$5$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7756xa75a61d5(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str, Bundle bundle) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            result.sendResult(null);
            return;
        }
        ((BrowserLegacyCb) Preconditions.checkNotNull(controllerInfo.getControllerCb())).registerSearchRequest(controllerInfo, str, bundle, result);
        ignoreFuture(this.librarySessionImpl.onSearchOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onCustomAction(final String str, final Bundle bundle, final MediaBrowserServiceCompat.Result<Bundle> result) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendError(null);
        } else {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7752x888c5875(str, currentController, result, bundle);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onCustomAction$6$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7752x888c5875(String str, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle) {
        SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, sessionCommand)) {
            result.sendError(null);
            return;
        }
        ProgressReporter progressReporter = new ProgressReporter(this.librarySessionImpl, result);
        ListenableFuture<SessionResult> listenableFutureOnCustomCommandOnHandler = this.librarySessionImpl.onCustomCommandOnHandler(controllerInfo, progressReporter, sessionCommand, bundle);
        progressReporter.setFuture(listenableFutureOnCustomCommandOnHandler);
        sendCustomActionResultWhenReady(result, listenableFutureOnCustomCommandOnHandler);
    }

    @Override // androidx.media3.session.MediaSessionServiceLegacyStub
    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, getMediaSessionManager().isTrustedForMediaControl(remoteUserInfo), new BrowserLegacyCb(remoteUserInfo), bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle), true);
    }

    public MediaSession.ControllerCb getBrowserLegacyCbForBroadcast() {
        return this.browserLegacyCbForBroadcast;
    }

    private MediaSession.ControllerInfo getCurrentController() {
        return getConnectedControllersManager().getController(getCurrentBrowserInfo());
    }

    private static void sendCustomActionResultWhenReady(final MediaBrowserServiceCompat.Result<Bundle> result, final ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$sendCustomActionResultWhenReady$7(listenableFuture, result);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$sendCustomActionResultWhenReady$7(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        try {
            result.sendResult(((SessionResult) Preconditions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null")).extras);
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Custom action failed", e);
            result.sendError(null);
        }
    }

    private static void sendLibraryResultWithMediaItemWhenReady(final MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result, final ListenableFuture<MediaBrowserCompat.MediaItem> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$sendLibraryResultWithMediaItemWhenReady$8(listenableFuture, result);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$sendLibraryResultWithMediaItemWhenReady$8(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        try {
            result.sendResult((MediaBrowserCompat.MediaItem) listenableFuture.get());
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Library operation failed", e);
            result.sendResult(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendLibraryResultWithMediaItemsWhenReady(final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, final ListenableFuture<List<MediaBrowserCompat.MediaItem>> listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$sendLibraryResultWithMediaItemsWhenReady$9(listenableFuture, result);
            }
        }, MoreExecutors.directExecutor());
    }

    static /* synthetic */ void lambda$sendLibraryResultWithMediaItemsWhenReady$9(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        try {
            List list = (List) listenableFuture.get();
            result.sendResult(list == null ? null : MediaUtils.truncateListBySize(list, 262144));
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Library operation failed", e);
            result.sendResult(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncFunction<LibraryResult<ImmutableList<MediaItem>>, List<MediaBrowserCompat.MediaItem>> createMediaItemsToBrowserItemsAsyncFunction() {
        return new AsyncFunction() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda12
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m7751xa49f4399((LibraryResult) obj);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$createMediaItemsToBrowserItemsAsyncFunction$12$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ ListenableFuture m7751xa49f4399(LibraryResult libraryResult) throws Exception {
        Preconditions.checkNotNull(libraryResult, "LibraryResult must not be null");
        final SettableFuture settableFutureCreate = SettableFuture.create();
        if (libraryResult.resultCode != 0 || libraryResult.value == 0) {
            settableFutureCreate.set(null);
            return settableFutureCreate;
        }
        final ImmutableList immutableList = (ImmutableList) libraryResult.value;
        if (immutableList.isEmpty()) {
            settableFutureCreate.set(new ArrayList());
            return settableFutureCreate;
        }
        final ArrayList arrayList = new ArrayList();
        settableFutureCreate.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$createMediaItemsToBrowserItemsAsyncFunction$10(settableFutureCreate, arrayList);
            }
        }, MoreExecutors.directExecutor());
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7750x7b4aee58(atomicInteger, immutableList, arrayList, settableFutureCreate);
            }
        };
        for (int i = 0; i < immutableList.size(); i++) {
            MediaMetadata mediaMetadata = ((MediaItem) immutableList.get(i)).mediaMetadata;
            if (mediaMetadata.artworkData == null) {
                arrayList.add(null);
                runnable.run();
            } else {
                ListenableFuture<Bitmap> listenableFutureDecodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                arrayList.add(listenableFutureDecodeBitmap);
                listenableFutureDecodeBitmap.addListener(runnable, MoreExecutors.directExecutor());
            }
        }
        return settableFutureCreate;
    }

    static /* synthetic */ void lambda$createMediaItemsToBrowserItemsAsyncFunction$10(SettableFuture settableFuture, List list) {
        if (settableFuture.isCancelled()) {
            cancelAllFutures(list);
        }
    }

    /* JADX INFO: renamed from: lambda$createMediaItemsToBrowserItemsAsyncFunction$11$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ void m7750x7b4aee58(AtomicInteger atomicInteger, ImmutableList immutableList, List list, SettableFuture settableFuture) {
        if (atomicInteger.incrementAndGet() == immutableList.size()) {
            handleBitmapFuturesAllCompletedAndSetOutputFuture(list, immutableList, settableFuture);
        }
    }

    private void handleBitmapFuturesAllCompletedAndSetOutputFuture(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2, SettableFuture<List<MediaBrowserCompat.MediaItem>> settableFuture) {
        Bitmap bitmap;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ListenableFuture<Bitmap> listenableFuture = list.get(i);
            if (listenableFuture != null) {
                try {
                    bitmap = (Bitmap) Futures.getDone(listenableFuture);
                } catch (CancellationException | ExecutionException e) {
                    Log.d(TAG, "Failed to get bitmap", e);
                    bitmap = null;
                }
            } else {
                bitmap = null;
            }
            arrayList.add(LegacyConversions.convertToBrowserItem(list2.get(i), bitmap));
        }
        settableFuture.set(arrayList);
    }

    private static <T> void cancelAllFutures(List<ListenableFuture<T>> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                list.get(i).cancel(false);
            }
        }
    }

    private AsyncFunction<LibraryResult<MediaItem>, MediaBrowserCompat.MediaItem> createMediaItemToBrowserItemAsyncFunction() {
        return new AsyncFunction() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda3
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m7749xc5987e36((LibraryResult) obj);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: lambda$createMediaItemToBrowserItemAsyncFunction$15$androidx-media3-session-MediaLibraryServiceLegacyStub, reason: not valid java name */
    /* synthetic */ ListenableFuture m7749xc5987e36(LibraryResult libraryResult) throws Exception {
        Preconditions.checkNotNull(libraryResult, "LibraryResult must not be null");
        final SettableFuture settableFutureCreate = SettableFuture.create();
        if (libraryResult.resultCode != 0 || libraryResult.value == 0) {
            settableFutureCreate.set(null);
            return settableFutureCreate;
        }
        final MediaItem mediaItem = (MediaItem) libraryResult.value;
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        if (mediaMetadata.artworkData == null) {
            settableFutureCreate.set(LegacyConversions.convertToBrowserItem(mediaItem, null));
            return settableFutureCreate;
        }
        final ListenableFuture<Bitmap> listenableFutureDecodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
        settableFutureCreate.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$13(settableFutureCreate, listenableFutureDecodeBitmap);
            }
        }, MoreExecutors.directExecutor());
        listenableFutureDecodeBitmap.addListener(new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$14(listenableFutureDecodeBitmap, settableFutureCreate, mediaItem);
            }
        }, MoreExecutors.directExecutor());
        return settableFutureCreate;
    }

    static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$13(SettableFuture settableFuture, ListenableFuture listenableFuture) {
        if (settableFuture.isCancelled()) {
            listenableFuture.cancel(false);
        }
    }

    static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$14(ListenableFuture listenableFuture, SettableFuture settableFuture, MediaItem mediaItem) {
        Bitmap bitmap;
        try {
            bitmap = (Bitmap) Futures.getDone(listenableFuture);
        } catch (CancellationException | ExecutionException e) {
            Log.d(TAG, "failed to get bitmap", e);
            bitmap = null;
        }
        settableFuture.set(LegacyConversions.convertToBrowserItem(mediaItem, bitmap));
    }

    private static class SearchRequest {
        public final MediaSession.ControllerInfo controller;
        public final Bundle extras;
        public final String query;
        public final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        public final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result;

        public SearchRequest(MediaSession.ControllerInfo controllerInfo, MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            this.controller = controllerInfo;
            this.remoteUserInfo = remoteUserInfo;
            this.query = str;
            this.extras = bundle;
            this.result = result;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class BrowserLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        private final Object lock = new Object();
        private final List<SearchRequest> searchRequests = new ArrayList();

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

        public BrowserLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.remoteUserInfo = remoteUserInfo;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle = libraryParams != null ? libraryParams.extras : null;
            MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub = MediaLibraryServiceLegacyStub.this;
            MediaSessionManager.RemoteUserInfo remoteUserInfo = this.remoteUserInfo;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            mediaLibraryServiceLegacyStub.notifyChildrenChanged(remoteUserInfo, str, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            final ArrayList arrayList = new ArrayList();
            synchronized (this.lock) {
                for (int size = this.searchRequests.size() - 1; size >= 0; size--) {
                    SearchRequest searchRequest = this.searchRequests.get(size);
                    if (Objects.equals(this.remoteUserInfo, searchRequest.remoteUserInfo) && searchRequest.query.equals(str)) {
                        arrayList.add(searchRequest);
                        this.searchRequests.remove(size);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                Util.postOrRun(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.MediaLibraryServiceLegacyStub$BrowserLegacyCb$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m7759xc133ba0b(arrayList);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onSearchResultChanged$0$androidx-media3-session-MediaLibraryServiceLegacyStub$BrowserLegacyCb, reason: not valid java name */
        /* synthetic */ void m7759xc133ba0b(List list) {
            int i;
            int i2;
            int i3;
            int i4;
            for (int i5 = 0; i5 < list.size(); i5++) {
                SearchRequest searchRequest = (SearchRequest) list.get(i5);
                if (searchRequest.extras != null) {
                    try {
                        searchRequest.extras.setClassLoader(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext().getClassLoader());
                        i = searchRequest.extras.getInt("android.media.browse.extra.PAGE", -1);
                        i2 = searchRequest.extras.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                    } catch (BadParcelableException unused) {
                        searchRequest.result.sendResult(null);
                        return;
                    }
                } else {
                    i = 0;
                    i2 = Integer.MAX_VALUE;
                }
                if (i < 0 || i2 < 1) {
                    i3 = 0;
                    i4 = Integer.MAX_VALUE;
                } else {
                    i3 = i;
                    i4 = i2;
                }
                MediaLibraryServiceLegacyStub.sendLibraryResultWithMediaItemsWhenReady(searchRequest.result, Util.transformFutureAsync(MediaLibraryServiceLegacyStub.this.librarySessionImpl.onGetSearchResultOnHandler(searchRequest.controller, searchRequest.query, i3, i4, LegacyConversions.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext(), searchRequest.extras)), MediaLibraryServiceLegacyStub.this.createMediaItemsToBrowserItemsAsyncFunction()));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void registerSearchRequest(MediaSession.ControllerInfo controllerInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            synchronized (this.lock) {
                this.searchRequests.add(new SearchRequest(controllerInfo, controllerInfo.getRemoteUserInfo(), str, bundle, result));
            }
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof BrowserLegacyCb) {
                return Objects.equals(this.remoteUserInfo, ((BrowserLegacyCb) obj).remoteUserInfo);
            }
            return false;
        }
    }

    private final class BrowserLegacyCbForBroadcast implements MediaSession.ControllerCb {
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
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
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

        private BrowserLegacyCbForBroadcast() {
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            if (libraryParams == null || libraryParams.extras == null) {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str);
            } else {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str, (Bundle) Util.castNonNull(libraryParams.extras));
            }
        }
    }

    private static class ProgressReporter implements MediaSession.ProgressReporter {
        private ListenableFuture<SessionResult> future;
        private final MediaBrowserServiceCompat.Result<Bundle> result;
        private final MediaLibrarySessionImpl session;

        public ProgressReporter(MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaBrowserServiceCompat.Result<Bundle> result) {
            this.session = mediaLibrarySessionImpl;
            this.result = result;
        }

        @Override // androidx.media3.session.MediaSession.ProgressReporter
        public void sendProgressUpdate(Bundle bundle) {
            ListenableFuture<SessionResult> listenableFuture = this.future;
            if ((listenableFuture == null || !listenableFuture.isDone()) && !this.session.isReleased()) {
                this.result.sendProgressUpdate(bundle);
            }
        }

        public void setFuture(ListenableFuture<SessionResult> listenableFuture) {
            this.future = listenableFuture;
        }
    }
}

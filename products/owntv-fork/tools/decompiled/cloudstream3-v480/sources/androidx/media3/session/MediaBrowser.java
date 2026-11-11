package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaBrowser extends MediaController {
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaBrowser method is called from a wrong thread. See javadoc of MediaController for details.";

    @NotOnlyInitialized
    private MediaBrowserImpl impl;

    public interface Listener extends MediaController.Listener {

        /* JADX INFO: renamed from: androidx.media3.session.MediaBrowser$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onChildrenChanged(Listener _this, MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
            }

            public static void $default$onSearchResultChanged(Listener _this, MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
            }
        }

        void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams);

        void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams);
    }

    interface MediaBrowserImpl extends MediaController.MediaControllerImpl {
        ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<MediaItem>> getItem(String str);

        ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> unsubscribe(String str);
    }

    public static final class Builder {
        private androidx.media3.common.util.BitmapLoader bitmapLoader;
        private final Context context;
        private int maxCommandsForMediaItems;
        private final SessionToken token;
        private Bundle connectionHints = Bundle.EMPTY;
        private Listener listener = new Listener() { // from class: androidx.media3.session.MediaBrowser.Builder.1
            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
                MediaController.Listener.CC.$default$onAvailableSessionCommandsChanged(this, mediaController, sessionCommands);
            }

            @Override // androidx.media3.session.MediaBrowser.Listener
            public /* synthetic */ void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
                Listener.CC.$default$onChildrenChanged(this, mediaBrowser, str, i, libraryParams);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ ListenableFuture onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
                return MediaController.Listener.CC.$default$onCustomCommand(this, mediaController, sessionCommand, bundle);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
                MediaController.Listener.CC.$default$onCustomLayoutChanged(this, mediaController, list);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onDisconnected(MediaController mediaController) {
                MediaController.Listener.CC.$default$onDisconnected(this, mediaController);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
                MediaController.Listener.CC.$default$onError(this, mediaController, sessionError);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
                MediaController.Listener.CC.$default$onExtrasChanged(this, mediaController, bundle);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onMediaButtonPreferencesChanged(MediaController mediaController, List list) {
                MediaController.Listener.CC.$default$onMediaButtonPreferencesChanged(this, mediaController, list);
            }

            @Override // androidx.media3.session.MediaBrowser.Listener
            public /* synthetic */ void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
                Listener.CC.$default$onSearchResultChanged(this, mediaBrowser, str, i, libraryParams);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
                MediaController.Listener.CC.$default$onSessionActivityChanged(this, mediaController, pendingIntent);
            }

            @Override // androidx.media3.session.MediaController.Listener
            public /* synthetic */ ListenableFuture onSetCustomLayout(MediaController mediaController, List list) {
                return MediaController.Listener.CC.$default$onSetCustomLayout(this, mediaController, list);
            }
        };
        private Looper applicationLooper = Util.getCurrentOrMainLooper();
        private long platformSessionCallbackAggregationTimeoutMs = 100;

        public Builder(Context context, SessionToken sessionToken) {
            this.context = (Context) Preconditions.checkNotNull(context);
            this.token = (SessionToken) Preconditions.checkNotNull(sessionToken);
        }

        public Builder setConnectionHints(Bundle bundle) {
            this.connectionHints = new Bundle((Bundle) Preconditions.checkNotNull(bundle));
            return this;
        }

        public Builder setListener(Listener listener) {
            this.listener = (Listener) Preconditions.checkNotNull(listener);
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            this.applicationLooper = (Looper) Preconditions.checkNotNull(looper);
            return this;
        }

        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            this.bitmapLoader = (androidx.media3.common.util.BitmapLoader) Preconditions.checkNotNull(bitmapLoader);
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i) {
            Preconditions.checkArgument(i >= 0);
            this.maxCommandsForMediaItems = i;
            return this;
        }

        public Builder experimentalSetPlatformSessionCallbackAggregationTimeoutMs(long j) {
            this.platformSessionCallbackAggregationTimeoutMs = j;
            return this;
        }

        public ListenableFuture<MediaBrowser> buildAsync() {
            final MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).build());
            }
            final MediaBrowser mediaBrowser = new MediaBrowser(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems, this.platformSessionCallbackAggregationTimeoutMs);
            Util.postOrRun(new Handler(this.applicationLooper), new Runnable() { // from class: androidx.media3.session.MediaBrowser$Builder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    mediaControllerHolder.setController(mediaBrowser);
                }
            });
            return mediaControllerHolder;
        }
    }

    MediaBrowser(Context context, SessionToken sessionToken, Bundle bundle, Listener listener, Looper looper, MediaController.ConnectionCallback connectionCallback, androidx.media3.common.util.BitmapLoader bitmapLoader, int i, long j) {
        super(context, sessionToken, bundle, listener, looper, connectionCallback, bitmapLoader, i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.media3.session.MediaController
    public MediaBrowserImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j) {
        MediaBrowserImpl mediaBrowserImplBase;
        if (sessionToken.isLegacySession()) {
            mediaBrowserImplBase = new MediaBrowserImplLegacy(context, this, sessionToken, bundle, looper, (androidx.media3.common.util.BitmapLoader) Preconditions.checkNotNull(bitmapLoader), j);
        } else {
            mediaBrowserImplBase = new MediaBrowserImplBase(context, this, sessionToken, bundle, looper);
        }
        this.impl = mediaBrowserImplBase;
        return mediaBrowserImplBase;
    }

    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).getLibraryRoot(libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "parentId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).subscribe(str, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> unsubscribe(String str) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "parentId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).unsubscribe(str);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "parentId must not be empty");
        Preconditions.checkArgument(i >= 0, "page must not be negative");
        Preconditions.checkArgument(i2 >= 1, "pageSize must not be less than 1");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).getChildren(str, i, i2, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<MediaItem>> getItem(String str) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "mediaId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).getItem(str);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "query must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).search(str, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "query must not be empty");
        Preconditions.checkArgument(i >= 0, "page must not be negative");
        Preconditions.checkArgument(i2 >= 1, "pageSize must not be less than 1");
        if (isConnected()) {
            return ((MediaBrowserImpl) Preconditions.checkNotNull(this.impl)).getSearchResult(str, i, i2, libraryParams);
        }
        return createDisconnectedFuture();
    }

    private static <V> ListenableFuture<LibraryResult<V>> createDisconnectedFuture() {
        return Futures.immediateFuture(LibraryResult.ofError(-100));
    }

    private void verifyApplicationThread() {
        Preconditions.checkState(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }

    void notifyBrowserListener(final Consumer<Listener> consumer) {
        final Listener listener = (Listener) this.listener;
        if (listener != null) {
            Util.postOrRun(this.applicationHandler, new Runnable() { // from class: androidx.media3.session.MediaBrowser$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(listener);
                }
            });
        }
    }
}

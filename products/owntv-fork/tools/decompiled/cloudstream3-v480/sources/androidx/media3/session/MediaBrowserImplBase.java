package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes4.dex */
class MediaBrowserImplBase extends MediaControllerImplBase implements MediaBrowser.MediaBrowserImpl {
    private final MediaBrowser instance;

    private interface RemoteLibrarySessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    MediaBrowserImplBase(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper) {
        super(context, mediaBrowser, sessionToken, bundle, looper);
        this.instance = mediaBrowser;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.media3.session.MediaControllerImplBase
    public MediaBrowser getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(50000, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.1
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getLibraryRoot(mediaControllerStub, i, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> subscribe(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.2
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.subscribe(mediaControllerStub, i, str2, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> unsubscribe(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.3
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.unsubscribe(MediaBrowserImplBase.this.controllerStub, i, str);
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(final String str, final int i, final int i2, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.4
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                int i4 = i;
                int i5 = i2;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getChildren(mediaControllerStub, i3, str2, i4, i5, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<MediaItem>> getItem(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.5
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.getItem(MediaBrowserImplBase.this.controllerStub, i, str);
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<Void>> search(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.6
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.search(mediaControllerStub, i, str2, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(final String str, final int i, final int i2, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.7
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i3) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                int i4 = i;
                int i5 = i2;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getSearchResult(mediaControllerStub, i3, str2, i4, i5, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifySearchResultChanged(final String str, final int i, final MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplBase$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7640x7271beb5(str, i, libraryParams, (MediaBrowser.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$notifySearchResultChanged$0$androidx-media3-session-MediaBrowserImplBase, reason: not valid java name */
    /* synthetic */ void m7640x7271beb5(String str, int i, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onSearchResultChanged(getInstance(), str, i, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyChildrenChanged(final String str, final int i, final MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new Consumer() { // from class: androidx.media3.session.MediaBrowserImplBase$$ExternalSyntheticLambda1
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m7639x606d39ee(str, i, libraryParams, (MediaBrowser.Listener) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$notifyChildrenChanged$1$androidx-media3-session-MediaBrowserImplBase, reason: not valid java name */
    /* synthetic */ void m7639x606d39ee(String str, int i, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onChildrenChanged(getInstance(), str, i, libraryParams);
    }

    private <V> ListenableFuture<LibraryResult<V>> dispatchRemoteLibrarySessionTask(int i, RemoteLibrarySessionTask remoteLibrarySessionTask) {
        IMediaSession sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(i);
        if (sessionInterfaceWithSessionCommandIfAble != null) {
            notifyPlatformControllerAboutMedia3ChangeRequest();
            SequencedFutureManager.SequencedFuture sequencedFutureCreateSequencedFuture = this.sequencedFutureManager.createSequencedFuture(LibraryResult.ofError(1));
            try {
                remoteLibrarySessionTask.run(sessionInterfaceWithSessionCommandIfAble, sequencedFutureCreateSequencedFuture.getSequenceNumber());
                return sequencedFutureCreateSequencedFuture;
            } catch (RemoteException e) {
                Log.w(MediaControllerImplBase.TAG, "Cannot connect to the service or the session is gone", e);
                this.sequencedFutureManager.setFutureResult(sequencedFutureCreateSequencedFuture.getSequenceNumber(), LibraryResult.ofError(-100));
                return sequencedFutureCreateSequencedFuture;
            }
        }
        return Futures.immediateFuture(LibraryResult.ofError(-4));
    }
}

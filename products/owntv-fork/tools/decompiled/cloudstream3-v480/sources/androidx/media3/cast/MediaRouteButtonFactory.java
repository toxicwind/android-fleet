package androidx.media3.cast;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaRouteButtonFactory {
    public static ListenableFuture<MenuItem> setUpMediaRouteButton(final Context context, final Menu menu, final int i) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.media3.cast.MediaRouteButtonFactory$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return MediaRouteButtonFactory.lambda$setUpMediaRouteButton$0(context, menu, i, completer);
            }
        });
    }

    static /* synthetic */ Object lambda$setUpMediaRouteButton$0(Context context, Menu menu, int i, final CallbackToFutureAdapter.Completer completer) throws Exception {
        Task<MenuItem> upMediaRouteButton = CastButtonFactory.setUpMediaRouteButton(context, BackgroundExecutor.get(), menu, i);
        Objects.requireNonNull(completer);
        Task<MenuItem> taskAddOnSuccessListener = upMediaRouteButton.addOnSuccessListener(new OnSuccessListener() { // from class: androidx.media3.cast.MediaRouteButtonFactory$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                completer.set((MenuItem) obj);
            }
        });
        Objects.requireNonNull(completer);
        return taskAddOnSuccessListener.addOnFailureListener(new MediaRouteButtonFactory$$ExternalSyntheticLambda2(completer));
    }

    public static ListenableFuture<Void> setUpMediaRouteButton(final Context context, final MediaRouteButton mediaRouteButton) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.media3.cast.MediaRouteButtonFactory$$ExternalSyntheticLambda3
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return MediaRouteButtonFactory.lambda$setUpMediaRouteButton$2(context, mediaRouteButton, completer);
            }
        });
    }

    static /* synthetic */ Object lambda$setUpMediaRouteButton$2(Context context, MediaRouteButton mediaRouteButton, final CallbackToFutureAdapter.Completer completer) throws Exception {
        Task<Void> taskAddOnSuccessListener = CastButtonFactory.setUpMediaRouteButton(context, BackgroundExecutor.get(), mediaRouteButton).addOnSuccessListener(new OnSuccessListener() { // from class: androidx.media3.cast.MediaRouteButtonFactory$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                completer.set(null);
            }
        });
        Objects.requireNonNull(completer);
        return taskAddOnSuccessListener.addOnFailureListener(new MediaRouteButtonFactory$$ExternalSyntheticLambda2(completer));
    }

    private MediaRouteButtonFactory() {
    }
}

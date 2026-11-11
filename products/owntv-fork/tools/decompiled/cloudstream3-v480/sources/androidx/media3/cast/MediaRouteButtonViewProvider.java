package androidx.media3.cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.media3.common.ViewProvider;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaRouteButtonViewProvider implements ViewProvider {
    @Override // androidx.media3.common.ViewProvider
    public ListenableFuture<View> getView(ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final MediaRouteButton mediaRouteButton = (MediaRouteButton) LayoutInflater.from(context).inflate(R.layout.media_route_button_view, viewGroup, false);
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.media3.cast.MediaRouteButtonViewProvider$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Context context2 = context;
                MediaRouteButton mediaRouteButton2 = mediaRouteButton;
                return CastButtonFactory.setUpMediaRouteButton(context2, BackgroundExecutor.get(), mediaRouteButton2).addOnSuccessListener(new OnSuccessListener() { // from class: androidx.media3.cast.MediaRouteButtonViewProvider$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        completer.set(mediaRouteButton2);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: androidx.media3.cast.MediaRouteButtonViewProvider$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        completer.setException(exc);
                    }
                });
            }
        });
    }
}

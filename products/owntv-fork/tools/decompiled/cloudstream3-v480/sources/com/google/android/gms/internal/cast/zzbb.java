package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbb implements MediaRouter.OnPrepareTransferListener {
    private static final Logger zza = new Logger("MediaRouterOPTListener");
    private final zzbn zzb;
    private final Handler zzc = new zzet(Looper.getMainLooper());

    public zzbb(zzbn zzbnVar) {
        this.zzb = (zzbn) Preconditions.checkNotNull(zzbnVar);
    }

    @Override // androidx.mediarouter.media.MediaRouter.OnPrepareTransferListener
    public final ListenableFuture onPrepareTransfer(final MediaRouter.RouteInfo routeInfo, final MediaRouter.RouteInfo routeInfo2) {
        zza.d("Prepare transfer from Route(%s) to Route(%s)", routeInfo, routeInfo2);
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.google.android.gms.internal.cast.zzaz
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                zzbb zzbbVar = this.zza;
                return Boolean.valueOf(zzbbVar.zzc.post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzba
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbbVar.zzb.zzk(routeInfo, routeInfo, completer);
                    }
                }));
            }
        });
    }
}

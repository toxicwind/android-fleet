package androidx.media3.cast;

import androidx.mediarouter.media.MediaRouter;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: compiled from: MediaRouterCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J(\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J \u0010\u0019\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\u0004H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\f¨\u0006!"}, d2 = {"Landroidx/media3/cast/MediaRouterCallback;", "Landroidx/mediarouter/media/MediaRouter$Callback;", "onEvent", "Lkotlin/Function0;", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CancellableContinuation;)V", "isCancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "onRouteAdded", "router", "Landroidx/mediarouter/media/MediaRouter;", "info", "Landroidx/mediarouter/media/MediaRouter$RouteInfo;", "onRouteRemoved", "onRouteChanged", "onRouteSelected", "selectedRoute", "reason", "", "requestedRoute", "onRouteUnselected", "route", "onProviderAdded", "provider", "Landroidx/mediarouter/media/MediaRouter$ProviderInfo;", "onProviderRemoved", "onProviderChanged", "notifyOnEvent", "lib-cast_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class MediaRouterCallback extends MediaRouter.Callback {
    private final CancellableContinuation<?> continuation;
    private final AtomicBoolean isCancelled;
    private final Function0<Unit> onEvent;

    public MediaRouterCallback(Function0<Unit> onEvent, CancellableContinuation<?> continuation) {
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        this.onEvent = onEvent;
        this.continuation = continuation;
        this.isCancelled = new AtomicBoolean(false);
    }

    /* JADX INFO: renamed from: isCancelled, reason: from getter */
    public final AtomicBoolean getIsCancelled() {
        return this.isCancelled;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(info, "info");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(info, "info");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo info) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(info, "info");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo selectedRoute, int reason, MediaRouter.RouteInfo requestedRoute) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(selectedRoute, "selectedRoute");
        Intrinsics.checkNotNullParameter(requestedRoute, "requestedRoute");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route, int reason) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(route, "route");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onProviderAdded(MediaRouter router, MediaRouter.ProviderInfo provider) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(provider, "provider");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onProviderRemoved(MediaRouter router, MediaRouter.ProviderInfo provider) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(provider, "provider");
        notifyOnEvent();
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public void onProviderChanged(MediaRouter router, MediaRouter.ProviderInfo provider) {
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(provider, "provider");
        notifyOnEvent();
    }

    private final void notifyOnEvent() {
        try {
            if (this.isCancelled.get()) {
                return;
            }
            this.onEvent.invoke();
        } catch (Throwable th) {
            this.isCancelled.set(true);
            CancellableContinuation<?> cancellableContinuation = this.continuation;
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuation.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(th)));
        }
    }
}

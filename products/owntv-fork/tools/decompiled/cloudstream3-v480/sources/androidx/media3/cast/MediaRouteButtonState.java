package androidx.media3.cast;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaRouteButtonState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010 \u001a\u00020!H\u0086@¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020$H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00188F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006&"}, d2 = {"Landroidx/media3/cast/MediaRouteButtonState;", "", "context", "Landroid/content/Context;", "selector", "Landroidx/mediarouter/media/MediaRouteSelector;", "<init>", "(Landroid/content/Context;Landroidx/mediarouter/media/MediaRouteSelector;)V", "getContext", "()Landroid/content/Context;", "getSelector", "()Landroidx/mediarouter/media/MediaRouteSelector;", "mediaRouter", "Landroidx/mediarouter/media/MediaRouter;", "getMediaRouter", "()Landroidx/mediarouter/media/MediaRouter;", "<set-?>", "", "isConnectedToRemote", "()Z", "setConnectedToRemote", "(Z)V", "isConnectedToRemote$delegate", "Landroidx/compose/runtime/MutableState;", "", "connectionState", "getConnectionState", "()I", "setConnectionState", "(I)V", "connectionState$delegate", "Landroidx/compose/runtime/MutableIntState;", "observe", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateMediaRouteState", "", "Companion", "lib-cast_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MediaRouteButtonState {
    private static final String TAG = "MediaRouteButtonState";

    /* JADX INFO: renamed from: connectionState$delegate, reason: from kotlin metadata */
    private final MutableIntState connectionState;
    private final Context context;

    /* JADX INFO: renamed from: isConnectedToRemote$delegate, reason: from kotlin metadata */
    private final MutableState isConnectedToRemote;
    private final MediaRouter mediaRouter;
    private final MediaRouteSelector selector;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: androidx.media3.cast.MediaRouteButtonState$observe$1, reason: invalid class name */
    /* JADX INFO: compiled from: MediaRouteButtonState.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.media3.cast.MediaRouteButtonState", f = "MediaRouteButtonState.kt", i = {}, l = {79}, m = "observe", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRouteButtonState.this.observe(this);
        }
    }

    public MediaRouteButtonState(Context context, MediaRouteSelector selector) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selector, "selector");
        this.context = context;
        this.selector = selector;
        MediaRouter mediaRouter = MediaRouter.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(mediaRouter, "getInstance(...)");
        this.mediaRouter = mediaRouter;
        Companion companion = INSTANCE;
        this.isConnectedToRemote = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(companion.isConnectedToRemote(mediaRouter)), null, 2, null);
        this.connectionState = SnapshotIntStateKt.mutableIntStateOf(companion.getConnectionState(mediaRouter, isConnectedToRemote()));
    }

    public final Context getContext() {
        return this.context;
    }

    public final MediaRouteSelector getSelector() {
        return this.selector;
    }

    public final MediaRouter getMediaRouter() {
        return this.mediaRouter;
    }

    private final void setConnectedToRemote(boolean z) {
        this.isConnectedToRemote.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isConnectedToRemote() {
        return ((Boolean) this.isConnectedToRemote.getValue()).booleanValue();
    }

    private final void setConnectionState(int i) {
        this.connectionState.setIntValue(i);
    }

    public final int getConnectionState() {
        return this.connectionState.getIntValue();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object observe(Continuation<?> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            updateMediaRouteState();
            MediaRouter mediaRouter = this.mediaRouter;
            MediaRouteSelector mediaRouteSelector = this.selector;
            Function0 function0 = new Function0() { // from class: androidx.media3.cast.MediaRouteButtonState$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MediaRouteButtonState.observe$lambda$0(this.f$0);
                }
            };
            anonymousClass1.label = 1;
            if (MediaRouterCallbackKt.observeCallback(mediaRouter, mediaRouteSelector, function0, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }

    static final Unit observe$lambda$0(MediaRouteButtonState mediaRouteButtonState) {
        mediaRouteButtonState.updateMediaRouteState();
        return Unit.INSTANCE;
    }

    private final void updateMediaRouteState() {
        Companion companion = INSTANCE;
        setConnectedToRemote(companion.isConnectedToRemote(this.mediaRouter));
        setConnectionState(companion.getConnectionState(this.mediaRouter, isConnectedToRemote()));
    }

    /* JADX INFO: compiled from: MediaRouteButtonState.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/media3/cast/MediaRouteButtonState$Companion;", "", "<init>", "()V", "TAG", "", "isConnectedToRemote", "", "mediaRouter", "Landroidx/mediarouter/media/MediaRouter;", "getConnectionState", "", "lib-cast_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isConnectedToRemote(MediaRouter mediaRouter) {
            return !mediaRouter.getSelectedRoute().isSystemRoute();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getConnectionState(MediaRouter mediaRouter, boolean isConnectedToRemote) {
            if (isConnectedToRemote) {
                return mediaRouter.getSelectedRoute().getConnectionState();
            }
            return 0;
        }
    }
}

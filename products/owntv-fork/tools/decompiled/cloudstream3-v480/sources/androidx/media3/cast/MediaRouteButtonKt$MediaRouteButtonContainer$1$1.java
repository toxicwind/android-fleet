package androidx.media3.cast;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.Log;
import androidx.mediarouter.media.MediaRouteSelector;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaRouteButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.media3.cast.MediaRouteButtonKt$MediaRouteButtonContainer$1$1", f = "MediaRouteButton.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MediaRouteButtonKt$MediaRouteButtonContainer$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<MediaRouteSelector> $selector$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MediaRouteButtonKt$MediaRouteButtonContainer$1$1(Context context, MutableState<MediaRouteSelector> mutableState, Continuation<? super MediaRouteButtonKt$MediaRouteButtonContainer$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$selector$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaRouteButtonKt$MediaRouteButtonContainer$1$1(this.$context, this.$selector$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaRouteButtonKt$MediaRouteButtonContainer$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Task<CastContext> sharedInstance = CastContext.getSharedInstance(this.$context, BackgroundExecutor.get());
        final MutableState<MediaRouteSelector> mutableState = this.$selector$delegate;
        final Function1 function1 = new Function1() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButtonContainer$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return MediaRouteButtonKt$MediaRouteButtonContainer$1$1.invokeSuspend$lambda$0(mutableState, (CastContext) obj2);
            }
        };
        Task<CastContext> taskAddOnSuccessListener = sharedInstance.addOnSuccessListener(new OnSuccessListener() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButtonContainer$1$1$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj2) {
                function1.invoke(obj2);
            }
        });
        final MutableState<MediaRouteSelector> mutableState2 = this.$selector$delegate;
        taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: androidx.media3.cast.MediaRouteButtonKt$MediaRouteButtonContainer$1$1$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                MediaRouteButtonKt$MediaRouteButtonContainer$1$1.invokeSuspend$lambda$2(mutableState2, exc);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$0(MutableState mutableState, CastContext castContext) {
        MediaRouteSelector mergedSelector = castContext.getMergedSelector();
        if (mergedSelector == null) {
            mergedSelector = MediaRouteSelector.EMPTY;
        }
        mutableState.setValue(mergedSelector);
        return Unit.INSTANCE;
    }

    static final void invokeSuspend$lambda$2(MutableState mutableState, Exception exc) {
        Log.w("MediaRouteButton", "Failed to get CastContext", exc);
        mutableState.setValue(MediaRouteSelector.EMPTY);
    }
}

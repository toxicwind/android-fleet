package androidx.navigationevent.compose;

import androidx.navigationevent.NavigationEventDispatcher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RememberNavigationEventDispatcherOwner.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.navigationevent.compose.RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1", f = "RememberNavigationEventDispatcherOwner.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ NavigationEventDispatcher $localDispatcher;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1(NavigationEventDispatcher navigationEventDispatcher, boolean z, Continuation<? super RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1> continuation) {
        super(2, continuation);
        this.$localDispatcher = navigationEventDispatcher;
        this.$enabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1(this.$localDispatcher, this.$enabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RememberNavigationEventDispatcherOwnerKt$rememberNavigationEventDispatcherOwner$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$localDispatcher.setEnabled(this.$enabled);
        return Unit.INSTANCE;
    }
}

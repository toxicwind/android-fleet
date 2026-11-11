package androidx.lifecycle.compose;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RememberLifecycleOwner.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.lifecycle.compose.RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1", f = "RememberLifecycleOwner.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ComposeLifecycleOwner $localLifecycleOwner;
    final /* synthetic */ Lifecycle.State $maxLifecycle;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1(ComposeLifecycleOwner composeLifecycleOwner, Lifecycle.State state, Continuation<? super RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1> continuation) {
        super(2, continuation);
        this.$localLifecycleOwner = composeLifecycleOwner;
        this.$maxLifecycle = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1(this.$localLifecycleOwner, this.$maxLifecycle, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RememberLifecycleOwnerKt$rememberLifecycleOwner$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$localLifecycleOwner.setMaxLifecycleState(this.$maxLifecycle);
        return Unit.INSTANCE;
    }
}

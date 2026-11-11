package app.cash.zipline.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: CoroutineEventLoop.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.CoroutineEventLoop$DelayedJob$run$1", f = "CoroutineEventLoop.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, nl = {61}, s = {}, v = 2)
final class CoroutineEventLoop$DelayedJob$run$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CoroutineEventLoop.DelayedJob this$0;
    final /* synthetic */ CoroutineEventLoop this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutineEventLoop$DelayedJob$run$1(CoroutineEventLoop.DelayedJob delayedJob, CoroutineEventLoop coroutineEventLoop, Continuation<? super CoroutineEventLoop$DelayedJob$run$1> continuation) {
        super(2, continuation);
        this.this$0 = delayedJob;
        this.this$1 = coroutineEventLoop;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutineEventLoop$DelayedJob$run$1(this.this$0, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoroutineEventLoop$DelayedJob$run$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(this.this$0.getDelayMillis(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        CoroutineScopeKt.ensureActive(this.this$1.scope);
        this.this$1.guestService.runJob(this.this$0.getTimeoutId());
        this.this$1.jobs.remove(Boxing.boxInt(this.this$0.getTimeoutId()));
        return Unit.INSTANCE;
    }
}

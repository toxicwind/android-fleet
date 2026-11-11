package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.StateFlowSerializer$toStateFlow$1$collect$2", f = "flows.kt", i = {0}, l = {135}, m = "invokeSuspend", n = {"$this$channelFlow"}, nl = {140}, s = {"L$0"}, v = 2)
final class StateFlowSerializer$toStateFlow$1$collect$2<T> extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ StateFlowZiplineService<T> $this_toStateFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StateFlowSerializer$toStateFlow$1$collect$2(StateFlowZiplineService<T> stateFlowZiplineService, Continuation<? super StateFlowSerializer$toStateFlow$1$collect$2> continuation) {
        super(2, continuation);
        this.$this_toStateFlow = stateFlowZiplineService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StateFlowSerializer$toStateFlow$1$collect$2 stateFlowSerializer$toStateFlow$1$collect$2 = new StateFlowSerializer$toStateFlow$1$collect$2(this.$this_toStateFlow, continuation);
        stateFlowSerializer$toStateFlow$1$collect$2.L$0 = obj;
        return stateFlowSerializer$toStateFlow$1$collect$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        return ((StateFlowSerializer$toStateFlow$1$collect$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.label = 1;
            if (this.$this_toStateFlow.collect(new FlowZiplineCollector<T>() { // from class: app.cash.zipline.internal.bridge.StateFlowSerializer$toStateFlow$1$collect$2.1
                @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                public /* bridge */ void close() {
                    ZiplineService.CC.$default$close(this);
                }

                @Override // app.cash.zipline.internal.bridge.FlowZiplineCollector
                public Object emit(T t, Continuation<? super Unit> continuation) {
                    Object objSend = producerScope.send(t, continuation);
                    return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.FlowZiplineService$Companion$Adapter$GeneratedOutboundService", f = "flows.kt", i = {0, 0}, l = {29}, m = "collect", n = {"collector", "tmp0_callHandler"}, nl = {-1}, s = {"L$0", "L$1"}, v = 2)
final class FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowZiplineService.Companion.Adapter.GeneratedOutboundService<TS> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1(FlowZiplineService.Companion.Adapter.GeneratedOutboundService<TS> generatedOutboundService, Continuation<? super FlowZiplineService$Companion$Adapter$GeneratedOutboundService$collect$1> continuation) {
        super(continuation);
        this.this$0 = generatedOutboundService;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect(null, this);
    }
}

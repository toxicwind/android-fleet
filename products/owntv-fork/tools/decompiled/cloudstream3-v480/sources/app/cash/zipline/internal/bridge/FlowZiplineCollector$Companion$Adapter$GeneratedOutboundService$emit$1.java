package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService", f = "flows.kt", i = {0, 0}, l = {39}, m = "emit", n = {ES6Iterator.VALUE_PROPERTY, "tmp0_callHandler"}, nl = {-1}, s = {"L$0", "L$1"}, v = 2)
final class FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowZiplineCollector.Companion.Adapter.GeneratedOutboundService<TS> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1(FlowZiplineCollector.Companion.Adapter.GeneratedOutboundService<TS> generatedOutboundService, Continuation<? super FlowZiplineCollector$Companion$Adapter$GeneratedOutboundService$emit$1> continuation) {
        super(continuation);
        this.this$0 = generatedOutboundService;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}

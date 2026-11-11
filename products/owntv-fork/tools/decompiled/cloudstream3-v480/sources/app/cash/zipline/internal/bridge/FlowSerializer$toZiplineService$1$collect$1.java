package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.FlowSerializer$toZiplineService$1", f = "flows.kt", i = {0}, l = {64}, m = "collect", n = {"collector"}, nl = {66}, s = {"L$0"}, v = 2)
final class FlowSerializer$toZiplineService$1$collect$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowSerializer.C06711 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowSerializer$toZiplineService$1$collect$1(FlowSerializer.C06711 c06711, Continuation<? super FlowSerializer$toZiplineService$1$collect$1> continuation) {
        super(continuation);
        this.this$0 = c06711;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect(null, this);
    }
}

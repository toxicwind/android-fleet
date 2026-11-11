package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: flows.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.StateFlowSerializer$toStateFlow$1", f = "flows.kt", i = {0}, l = {140}, m = "collect", n = {"collector"}, nl = {141}, s = {"L$0"}, v = 2)
final class StateFlowSerializer$toStateFlow$1$collect$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StateFlowSerializer.AnonymousClass1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StateFlowSerializer$toStateFlow$1$collect$1(StateFlowSerializer.AnonymousClass1 anonymousClass1, Continuation<? super StateFlowSerializer$toStateFlow$1$collect$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect(null, this);
    }
}

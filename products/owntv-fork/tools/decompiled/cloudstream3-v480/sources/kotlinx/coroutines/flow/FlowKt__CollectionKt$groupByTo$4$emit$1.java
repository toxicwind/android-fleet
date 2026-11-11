package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: Collection.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt$groupByTo$4", f = "Collection.kt", i = {0, 1, 1, 1}, l = {358, 360}, m = "emit", n = {"element", "element", "key", "list"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
public final class FlowKt__CollectionKt$groupByTo$4$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__CollectionKt.C11214<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__CollectionKt$groupByTo$4$emit$1(FlowKt__CollectionKt.C11214<? super T> c11214, Continuation<? super FlowKt__CollectionKt$groupByTo$4$emit$1> continuation) {
        super(continuation);
        this.this$0 = c11214;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}

package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.mvvm.Resource;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/LoadResponse;", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$load$1$loadResponse$1", f = "ResultViewModel2.kt", i = {}, l = {2669}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class ResultViewModel2$load$1$loadResponse$1 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super LoadResponse>, Object> {
    final /* synthetic */ Resource<LoadResponse> $data;
    int label;
    final /* synthetic */ ResultViewModel2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ResultViewModel2$load$1$loadResponse$1(ResultViewModel2 resultViewModel2, Resource<? extends LoadResponse> resource, Continuation<? super ResultViewModel2$load$1$loadResponse$1> continuation) {
        super(3, continuation);
        this.this$0 = resultViewModel2;
        this.$data = resource;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super LoadResponse> continuation) {
        return new ResultViewModel2$load$1$loadResponse$1(this.this$0, this.$data, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.applyMeta((LoadResponse) ((Resource.Success) this.$data).getValue(), this.this$0.currentMeta, this.this$0.currentSync, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return ((Pair) obj).getFirst();
    }
}

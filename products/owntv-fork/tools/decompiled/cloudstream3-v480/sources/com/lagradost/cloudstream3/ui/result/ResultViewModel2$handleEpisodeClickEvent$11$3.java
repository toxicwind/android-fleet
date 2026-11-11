package com.lagradost.cloudstream3.ui.result;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "selectedAction", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$11$3", f = "ResultViewModel2.kt", i = {0}, l = {1629}, m = "invokeSuspend", n = {"selectedAction"}, nl = {1633}, s = {"L$0"}, v = 2)
final class ResultViewModel2$handleEpisodeClickEvent$11$3 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
    final /* synthetic */ EpisodeClickEvent $click;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ResultViewModel2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultViewModel2$handleEpisodeClickEvent$11$3(ResultViewModel2 resultViewModel2, EpisodeClickEvent episodeClickEvent, Continuation<? super ResultViewModel2$handleEpisodeClickEvent$11$3> continuation) {
        super(2, continuation);
        this.this$0 = resultViewModel2;
        this.$click = episodeClickEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ResultViewModel2$handleEpisodeClickEvent$11$3 resultViewModel2$handleEpisodeClickEvent$11$3 = new ResultViewModel2$handleEpisodeClickEvent$11$3(this.this$0, this.$click, continuation);
        resultViewModel2$handleEpisodeClickEvent$11$3.L$0 = obj;
        return resultViewModel2$handleEpisodeClickEvent$11$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Integer num, Continuation<? super Unit> continuation) {
        return ((ResultViewModel2$handleEpisodeClickEvent$11$3) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer num = (Integer) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (num != null) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(num);
                this.label = 1;
                if (this.this$0.handleEpisodeClickEvent(EpisodeClickEvent.copy$default(this.$click, null, num.intValue(), null, 5, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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

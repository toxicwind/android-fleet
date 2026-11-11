package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$applyMeta$out$1$3", f = "ResultViewModel2.kt", i = {}, l = {1763}, m = "invokeSuspend", n = {}, nl = {1764}, s = {}, v = 2)
final class ResultViewModel2$applyMeta$out$1$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ SyncAPI.SyncResult $meta;
    final /* synthetic */ LoadResponse $this_apply;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultViewModel2$applyMeta$out$1$3(SyncAPI.SyncResult syncResult, LoadResponse loadResponse, Continuation<? super ResultViewModel2$applyMeta$out$1$3> continuation) {
        super(1, continuation);
        this.$meta = syncResult;
        this.$this_apply = loadResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ResultViewModel2$applyMeta$out$1$3(this.$meta, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((ResultViewModel2$applyMeta$out$1$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$meta == null) {
                return Unit.INSTANCE;
            }
            this.label = 1;
            if (LoadResponse.Companion.addTrailer$default(LoadResponse.INSTANCE, this.$this_apply, (List) this.$meta.getTrailers(), (String) null, false, (Continuation) this, 6, (Object) null) == coroutine_suspended) {
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

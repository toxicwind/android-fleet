package com.lagradost.cloudstream3.plugins;

import android.widget.Toast;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: VotingApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/plugins/VotingApi;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.plugins.VotingApi$vote$3$2", f = "VotingApi.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class VotingApi$vote$3$2 extends SuspendLambda implements Function2<VotingApi, Continuation<? super Unit>, Object> {
    int label;

    VotingApi$vote$3$2(Continuation<? super VotingApi$vote$3$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VotingApi$vote$3$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(VotingApi votingApi, Continuation<? super Unit> continuation) {
        return ((VotingApi$vote$3$2) create(votingApi, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Toast.makeText(CloudStreamApp.INSTANCE.getContext(), R.string.already_voted, 0).show();
        return Unit.INSTANCE;
    }
}

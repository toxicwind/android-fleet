package com.lagradost.cloudstream3.ui.result;

import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentResultBinding;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ResultFragmentPhone.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/databinding/FragmentResultBinding;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onBindingCreated$12$1$1$1", f = "ResultFragmentPhone.kt", i = {}, l = {823}, m = "invokeSuspend", n = {}, nl = {824}, s = {}, v = 2)
final class ResultFragmentPhone$onBindingCreated$12$1$1$1 extends SuspendLambda implements Function3<CoroutineScope, FragmentResultBinding, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ResultFragmentPhone this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultFragmentPhone$onBindingCreated$12$1$1$1(ResultFragmentPhone resultFragmentPhone, Continuation<? super ResultFragmentPhone$onBindingCreated$12$1$1$1> continuation) {
        super(3, continuation);
        this.this$0 = resultFragmentPhone;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentResultBinding fragmentResultBinding, Continuation<? super Unit> continuation) {
        return new ResultFragmentPhone$onBindingCreated$12$1$1$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = QualityProfileDialog.INSTANCE.getAllDefaultSources(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final List list = (List) obj;
        final FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return Unit.INSTANCE;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.result.ResultFragmentPhone$onBindingCreated$12$1$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                new QualityProfileDialog(activity, R.style.DialogFullscreenPlayer, list).show();
            }
        });
        return Unit.INSTANCE;
    }
}

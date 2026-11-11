package com.lagradost.cloudstream3.ui.quicksearch;

import androidx.compose.runtime.ComposerKt;
import com.lagradost.cloudstream3.ui.search.SearchViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: QuickSearchFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/quicksearch/QuickSearchFragment;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$onBindingCreated$6$1", f = "QuickSearchFragment.kt", i = {}, l = {ComposerKt.reuseKey}, m = "invokeSuspend", n = {}, nl = {208}, s = {}, v = 2)
final class QuickSearchFragment$onBindingCreated$6$1 extends SuspendLambda implements Function3<CoroutineScope, QuickSearchFragment, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    int label;
    final /* synthetic */ QuickSearchFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QuickSearchFragment$onBindingCreated$6$1(QuickSearchFragment quickSearchFragment, String str, Continuation<? super QuickSearchFragment$onBindingCreated$6$1> continuation) {
        super(3, continuation);
        this.this$0 = quickSearchFragment;
        this.$name = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, QuickSearchFragment quickSearchFragment, Continuation<? super Unit> continuation) {
        return new QuickSearchFragment$onBindingCreated$6$1(this.this$0, this.$name, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SearchViewModel searchViewModel = this.this$0.searchViewModel;
            if (searchViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchViewModel");
                searchViewModel = null;
            }
            this.label = 1;
            if (searchViewModel.expandAndReturn(this.$name, this) == coroutine_suspended) {
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

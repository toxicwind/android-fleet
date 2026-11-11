package com.lagradost.cloudstream3.ui.search;

import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.ui.home.HomeViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SearchFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "name", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchFragment$onBindingCreated$masterAdapter$2$1", f = "SearchFragment.kt", i = {0}, l = {550}, m = "invokeSuspend", n = {"name"}, nl = {-1}, s = {"L$0"}, v = 2)
final class SearchFragment$onBindingCreated$masterAdapter$2$1 extends SuspendLambda implements Function2<String, Continuation<? super HomeViewModel.ExpandableHomepageList>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SearchFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchFragment$onBindingCreated$masterAdapter$2$1(SearchFragment searchFragment, Continuation<? super SearchFragment$onBindingCreated$masterAdapter$2$1> continuation) {
        super(2, continuation);
        this.this$0 = searchFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SearchFragment$onBindingCreated$masterAdapter$2$1 searchFragment$onBindingCreated$masterAdapter$2$1 = new SearchFragment$onBindingCreated$masterAdapter$2$1(this.this$0, continuation);
        searchFragment$onBindingCreated$masterAdapter$2$1.L$0 = obj;
        return searchFragment$onBindingCreated$masterAdapter$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(String str, Continuation<? super HomeViewModel.ExpandableHomepageList> continuation) {
        return ((SearchFragment$onBindingCreated$masterAdapter$2$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws DebugException {
        String str = (String) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.L$0 = SpillingKt.nullOutSpilledVariable(str);
        this.label = 1;
        Object objExpandAndReturn = this.this$0.getSearchViewModel().expandAndReturn(str, this);
        return objExpandAndReturn == coroutine_suspended ? coroutine_suspended : objExpandAndReturn;
    }
}

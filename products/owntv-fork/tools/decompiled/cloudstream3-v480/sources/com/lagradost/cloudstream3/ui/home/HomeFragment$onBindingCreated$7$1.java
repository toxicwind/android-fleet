package com.lagradost.cloudstream3.ui.home;

import com.lagradost.cloudstream3.mvvm.DebugException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeFragment$onBindingCreated$7$1", f = "HomeFragment.kt", i = {0}, l = {866}, m = "invokeSuspend", n = {"it"}, nl = {-1}, s = {"L$0"}, v = 2)
final class HomeFragment$onBindingCreated$7$1 extends SuspendLambda implements Function2<String, Continuation<? super HomeViewModel.ExpandableHomepageList>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeFragment$onBindingCreated$7$1(HomeFragment homeFragment, Continuation<? super HomeFragment$onBindingCreated$7$1> continuation) {
        super(2, continuation);
        this.this$0 = homeFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HomeFragment$onBindingCreated$7$1 homeFragment$onBindingCreated$7$1 = new HomeFragment$onBindingCreated$7$1(this.this$0, continuation);
        homeFragment$onBindingCreated$7$1.L$0 = obj;
        return homeFragment$onBindingCreated$7$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(String str, Continuation<? super HomeViewModel.ExpandableHomepageList> continuation) {
        return ((HomeFragment$onBindingCreated$7$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
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
        Object objExpandAndReturn = this.this$0.getHomeViewModel().expandAndReturn(str, this);
        return objExpandAndReturn == coroutine_suspended ? coroutine_suspended : objExpandAndReturn;
    }
}

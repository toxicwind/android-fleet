package com.lagradost.cloudstream3.ui.home;

import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0000*\u0001\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "com/lagradost/cloudstream3/ui/home/HomeFragment$Companion$loadHomepageList$5"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1", f = "HomeFragment.kt", i = {}, l = {HebrewProber.FINAL_KAF}, m = "invokeSuspend", n = {}, nl = {HebrewProber.NORMAL_KAF}, s = {}, v = 2)
final class HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1 extends SuspendLambda implements Function3<CoroutineScope, HomeFragment$Companion$loadHomepageList$5, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<String, Continuation<? super HomeViewModel.ExpandableHomepageList>, Object> $expandCallback;
    final /* synthetic */ RecyclerView $recyclerView;
    int label;
    final /* synthetic */ HomeFragment$Companion$loadHomepageList$5 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1(Function2<? super String, ? super Continuation<? super HomeViewModel.ExpandableHomepageList>, ? extends Object> function2, HomeFragment$Companion$loadHomepageList$5 homeFragment$Companion$loadHomepageList$5, RecyclerView recyclerView, Continuation<? super HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1> continuation) {
        super(3, continuation);
        this.$expandCallback = function2;
        this.this$0 = homeFragment$Companion$loadHomepageList$5;
        this.$recyclerView = recyclerView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, HomeFragment$Companion$loadHomepageList$5 homeFragment$Companion$loadHomepageList$5, Continuation<? super Unit> continuation) {
        return new HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1(this.$expandCallback, this.this$0, this.$recyclerView, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<String, Continuation<? super HomeViewModel.ExpandableHomepageList>, Object> function2 = this.$expandCallback;
            if (function2 != null) {
                String name = this.this$0.getName();
                this.label = 1;
                obj = function2.invoke(name, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HomeViewModel.ExpandableHomepageList expandableHomepageList = (HomeViewModel.ExpandableHomepageList) obj;
        if (expandableHomepageList != null) {
            RecyclerView.Adapter adapter = this.$recyclerView.getAdapter();
            SearchAdapter searchAdapter = adapter instanceof SearchAdapter ? (SearchAdapter) adapter : null;
            if (searchAdapter != null) {
                searchAdapter.setHasNext(expandableHomepageList.getHasNext());
                BaseAdapter.submitList$default(searchAdapter, expandableHomepageList.getList().getList(), null, 2, null);
            }
        }
        return Unit.INSTANCE;
    }
}

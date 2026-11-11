package com.lagradost.cloudstream3.ui.home;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.APIRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/LoadResponse;", "searchResponse", "Lcom/lagradost/cloudstream3/SearchResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$updatePreviewResponses$add$1", f = "HomeViewModel.kt", i = {0}, l = {305}, m = "invokeSuspend", n = {"searchResponse"}, nl = {-1}, s = {"L$0"}, v = 2)
final class HomeViewModel$updatePreviewResponses$add$1 extends SuspendLambda implements Function2<SearchResponse, Continuation<? super Resource<? extends LoadResponse>>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeViewModel$updatePreviewResponses$add$1(HomeViewModel homeViewModel, Continuation<? super HomeViewModel$updatePreviewResponses$add$1> continuation) {
        super(2, continuation);
        this.this$0 = homeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HomeViewModel$updatePreviewResponses$add$1 homeViewModel$updatePreviewResponses$add$1 = new HomeViewModel$updatePreviewResponses$add$1(this.this$0, continuation);
        homeViewModel$updatePreviewResponses$add$1.L$0 = obj;
        return homeViewModel$updatePreviewResponses$add$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SearchResponse searchResponse, Continuation<? super Resource<? extends LoadResponse>> continuation) {
        return ((HomeViewModel$updatePreviewResponses$add$1) create(searchResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SearchResponse searchResponse = (SearchResponse) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            APIRepository repo = this.this$0.getRepo();
            if (repo == null) {
                return null;
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(searchResponse);
            this.label = 1;
            obj = repo.load(searchResponse.getUrl(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (Resource) obj;
    }
}

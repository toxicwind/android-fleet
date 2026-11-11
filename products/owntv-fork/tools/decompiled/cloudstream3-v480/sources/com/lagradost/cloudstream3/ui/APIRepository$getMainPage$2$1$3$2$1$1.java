package com.lagradost.cloudstream3.ui;

import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MainPageRequest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: APIRepository.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/HomePageResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.APIRepository$getMainPage$2$1$3$2$1$1", f = "APIRepository.kt", i = {}, l = {185}, m = "invokeSuspend", n = {}, nl = {188}, s = {}, v = 2)
final class APIRepository$getMainPage$2$1$3$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HomePageResponse>, Object> {
    final /* synthetic */ MainPageData $data;
    final /* synthetic */ int $page;
    int label;
    final /* synthetic */ APIRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    APIRepository$getMainPage$2$1$3$2$1$1(APIRepository aPIRepository, int i, MainPageData mainPageData, Continuation<? super APIRepository$getMainPage$2$1$3$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = aPIRepository;
        this.$page = i;
        this.$data = mainPageData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new APIRepository$getMainPage$2$1$3$2$1$1(this.this$0, this.$page, this.$data, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HomePageResponse> continuation) {
        return ((APIRepository$getMainPage$2$1$3$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
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
        this.label = 1;
        Object mainPage = this.this$0.getApi().getMainPage(this.$page, new MainPageRequest(this.$data.getName(), this.$data.getData(), this.$data.getHorizontalImages()), this);
        return mainPage == coroutine_suspended ? coroutine_suspended : mainPage;
    }
}

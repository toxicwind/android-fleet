package com.lagradost.cloudstream3.services;

import com.lagradost.cloudstream3.EpisodeResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SubscriptionWorkManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/EpisodeResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.SubscriptionWorkManager$doWork$2$response$1", f = "SubscriptionWorkManager.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class SubscriptionWorkManager$doWork$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EpisodeResponse>, Object> {
    final /* synthetic */ MainAPI $api;
    final /* synthetic */ DataStoreHelper.SubscribedData $savedData;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SubscriptionWorkManager$doWork$2$response$1(MainAPI mainAPI, DataStoreHelper.SubscribedData subscribedData, Continuation<? super SubscriptionWorkManager$doWork$2$response$1> continuation) {
        super(2, continuation);
        this.$api = mainAPI;
        this.$savedData = subscribedData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SubscriptionWorkManager$doWork$2$response$1(this.$api, this.$savedData, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EpisodeResponse> continuation) {
        return ((SubscriptionWorkManager$doWork$2$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$api.load(this.$savedData.getUrl(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (obj instanceof EpisodeResponse) {
            return (EpisodeResponse) obj;
        }
        return null;
    }
}

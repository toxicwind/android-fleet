package com.lagradost.cloudstream3.services;

import android.graphics.Bitmap;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SubscriptionWorkManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/services/SubscriptionWorkManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.SubscriptionWorkManager$doWork$2$poster$1", f = "SubscriptionWorkManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class SubscriptionWorkManager$doWork$2$poster$1 extends SuspendLambda implements Function3<CoroutineScope, SubscriptionWorkManager, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ DataStoreHelper.SubscribedData $savedData;
    int label;
    final /* synthetic */ SubscriptionWorkManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SubscriptionWorkManager$doWork$2$poster$1(DataStoreHelper.SubscribedData subscribedData, SubscriptionWorkManager subscriptionWorkManager, Continuation<? super SubscriptionWorkManager$doWork$2$poster$1> continuation) {
        super(3, continuation);
        this.$savedData = subscribedData;
        this.this$0 = subscriptionWorkManager;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SubscriptionWorkManager subscriptionWorkManager, Continuation<? super Bitmap> continuation) {
        return new SubscriptionWorkManager$doWork$2$poster$1(this.$savedData, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        String posterUrl = this.$savedData.getPosterUrl();
        if (posterUrl == null) {
            return null;
        }
        return DownloadUtils.INSTANCE.getImageBitmapFromUrl$app(this.this$0.getContext(), posterUrl, this.$savedData.getPosterHeaders());
    }
}

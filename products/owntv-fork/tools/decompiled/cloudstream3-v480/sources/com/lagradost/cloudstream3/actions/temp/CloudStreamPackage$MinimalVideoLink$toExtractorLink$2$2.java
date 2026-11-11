package com.lagradost.cloudstream3.actions.temp;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CloudStreamPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2", f = "CloudStreamPackage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CloudStreamPackage.MinimalVideoLink this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2(CloudStreamPackage.MinimalVideoLink minimalVideoLink, Continuation<? super CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2> continuation) {
        super(2, continuation);
        this.this$0 = minimalVideoLink;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2 cloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2 = new CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2(this.this$0, continuation);
        cloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2.L$0 = obj;
        return cloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((CloudStreamPackage$MinimalVideoLink$toExtractorLink$2$2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setHeaders(this.this$0.getHeaders());
        Integer quality = this.this$0.getQuality();
        extractorLink.setQuality(quality != null ? quality.intValue() : Qualities.Unknown.getValue());
        return Unit.INSTANCE;
    }
}

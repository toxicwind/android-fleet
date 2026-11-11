package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: GoodstreamExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.GoodstreamExtractor$getUrl$2$1$1", f = "GoodstreamExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class GoodstreamExtractor$getUrl$2$1$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GoodstreamExtractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GoodstreamExtractor$getUrl$2$1$1(GoodstreamExtractor goodstreamExtractor, Continuation<? super GoodstreamExtractor$getUrl$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = goodstreamExtractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GoodstreamExtractor$getUrl$2$1$1 goodstreamExtractor$getUrl$2$1$1 = new GoodstreamExtractor$getUrl$2$1$1(this.this$0, continuation);
        goodstreamExtractor$getUrl$2$1$1.L$0 = obj;
        return goodstreamExtractor$getUrl$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((GoodstreamExtractor$getUrl$2$1$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.this$0.getMainUrl());
        return Unit.INSTANCE;
    }
}

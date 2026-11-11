package com.lagradost.cloudstream3.extractors.helper;

import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: GogoHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.GogoHelper$extractVidstream$2$invokeGogoSource$2", f = "GogoHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class GogoHelper$extractVidstream$2$invokeGogoSource$2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mainUrl;
    final /* synthetic */ GogoHelper.GogoSource $source;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GogoHelper$extractVidstream$2$invokeGogoSource$2(String str, GogoHelper.GogoSource gogoSource, Continuation<? super GogoHelper$extractVidstream$2$invokeGogoSource$2> continuation) {
        super(2, continuation);
        this.$mainUrl = str;
        this.$source = gogoSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GogoHelper$extractVidstream$2$invokeGogoSource$2 gogoHelper$extractVidstream$2$invokeGogoSource$2 = new GogoHelper$extractVidstream$2$invokeGogoSource$2(this.$mainUrl, this.$source, continuation);
        gogoHelper$extractVidstream$2$invokeGogoSource$2.L$0 = obj;
        return gogoHelper$extractVidstream$2$invokeGogoSource$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((GogoHelper$extractVidstream$2$invokeGogoSource$2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.$mainUrl);
        extractorLink.setQuality(ExtractorApiKt.getQualityFromName(this.$source.getLabel()));
        return Unit.INSTANCE;
    }
}

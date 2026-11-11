package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: VkExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.VkExtractor$getUrl$2$1$1", f = "VkExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class VkExtractor$getUrl$2$1$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $quality;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VkExtractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VkExtractor$getUrl$2$1$1(VkExtractor vkExtractor, String str, Continuation<? super VkExtractor$getUrl$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = vkExtractor;
        this.$quality = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VkExtractor$getUrl$2$1$1 vkExtractor$getUrl$2$1$1 = new VkExtractor$getUrl$2$1$1(this.this$0, this.$quality, continuation);
        vkExtractor$getUrl$2$1$1.L$0 = obj;
        return vkExtractor$getUrl$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((VkExtractor$getUrl$2$1$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.this$0.getMainUrl() + '/');
        extractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:144.0) Gecko/20100101 Firefox/144.0"), TuplesKt.to("Accept", "*/*"), TuplesKt.to("Accept-Language", "en-US,en;q=0.5"), TuplesKt.to("Referer", this.this$0.getMainUrl() + '/')));
        extractorLink.setQuality(ExtractorApiKt.getQualityFromName(this.$quality));
        return Unit.INSTANCE;
    }
}

package com.lagradost.cloudstream3.extractors.helper;

import com.lagradost.api.Log;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: AsianEmbedHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000b\u0010\u0002\u001a\u00070\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "it", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.AsianEmbedHelper$Companion$getUrls$2", f = "AsianEmbedHelper.kt", i = {0, 0}, l = {25}, m = "invokeSuspend", n = {"it", "datavid"}, nl = {26}, s = {"L$0", "L$1"}, v = 2)
final class AsianEmbedHelper$Companion$getUrls$2 extends SuspendLambda implements Function2<Element, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
    final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
    final /* synthetic */ String $url;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AsianEmbedHelper$Companion$getUrls$2(String str, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super AsianEmbedHelper$Companion$getUrls$2> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$subtitleCallback = function1;
        this.$callback = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AsianEmbedHelper$Companion$getUrls$2 asianEmbedHelper$Companion$getUrls$2 = new AsianEmbedHelper$Companion$getUrls$2(this.$url, this.$subtitleCallback, this.$callback, continuation);
        asianEmbedHelper$Companion$getUrls$2.L$0 = obj;
        return asianEmbedHelper$Companion$getUrls$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Element element, Continuation<? super Unit> continuation) {
        return ((AsianEmbedHelper$Companion$getUrls$2) create(element, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Exception {
        String str;
        Element element = (Element) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String strAttr = element.attr("data-video");
            Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
            if (!StringsKt.isBlank(strAttr)) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                this.L$1 = strAttr;
                this.label = 1;
                Object objLoadExtractor = ExtractorApiKt.loadExtractor(strAttr, this.$url, this.$subtitleCallback, this.$callback, this);
                if (objLoadExtractor == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objLoadExtractor;
                str = strAttr;
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        str = (String) this.L$1;
        ResultKt.throwOnFailure(obj);
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        Log.INSTANCE.i("AsianEmbed", "Result => (" + zBooleanValue + ") (datavid) " + str);
        return Unit.INSTANCE;
    }
}

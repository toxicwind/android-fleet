package com.lagradost.cloudstream3.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
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
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Streamlare.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Slmaxed$getUrl$2$1$1", f = "Streamlare.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class Slmaxed$getUrl$2$1$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ Slmaxed.Result $result;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Slmaxed$getUrl$2$1$1(String str, Slmaxed.Result result, Continuation<? super Slmaxed$getUrl$2$1$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$result = result;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Slmaxed$getUrl$2$1$1 slmaxed$getUrl$2$1$1 = new Slmaxed$getUrl$2$1$1(this.$url, this.$result, continuation);
        slmaxed$getUrl$2$1$1.L$0 = obj;
        return slmaxed$getUrl$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((Slmaxed$getUrl$2$1$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String strReplace;
        String string;
        Integer intOrNull;
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.$url);
        String label = this.$result.getLabel();
        extractorLink.setQuality((label == null || (strReplace = StringsKt.replace(label, TtmlNode.TAG_P, "", true)) == null || (string = StringsKt.trim((CharSequence) strReplace).toString()) == null || (intOrNull = StringsKt.toIntOrNull(string)) == null) ? Qualities.Unknown.getValue() : intOrNull.intValue());
        return Unit.INSTANCE;
    }
}

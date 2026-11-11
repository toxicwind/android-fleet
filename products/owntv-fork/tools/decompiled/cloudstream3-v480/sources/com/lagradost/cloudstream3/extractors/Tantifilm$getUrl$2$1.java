package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Tantifilm.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Tantifilm$getUrl$2$1", f = "Tantifilm.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class Tantifilm$getUrl$2$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ Tantifilm.TantifilmData $it;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Tantifilm this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Tantifilm$getUrl$2$1(Tantifilm tantifilm, Tantifilm.TantifilmData tantifilmData, Continuation<? super Tantifilm$getUrl$2$1> continuation) {
        super(2, continuation);
        this.this$0 = tantifilm;
        this.$it = tantifilmData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Tantifilm$getUrl$2$1 tantifilm$getUrl$2$1 = new Tantifilm$getUrl$2$1(this.this$0, this.$it, continuation);
        tantifilm$getUrl$2$1.L$0 = obj;
        return tantifilm$getUrl$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((Tantifilm$getUrl$2$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.this$0.getMainUrl());
        String label = this.$it.getLabel();
        StringBuilder sb = new StringBuilder();
        int length = label.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = label.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        extractorLink.setQuality(Integer.parseInt(sb.toString()));
        return Unit.INSTANCE;
    }
}

package com.lagradost.cloudstream3.extractors.helper;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: JWPlayerHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$2$1", f = "JWPlayerHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class JwPlayerHelper$extractStreamLinks$2$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JwPlayerHelper$extractStreamLinks$2$1(Map<String, String> map, Continuation<? super JwPlayerHelper$extractStreamLinks$2$1> continuation) {
        super(2, continuation);
        this.$headers = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        JwPlayerHelper$extractStreamLinks$2$1 jwPlayerHelper$extractStreamLinks$2$1 = new JwPlayerHelper$extractStreamLinks$2$1(this.$headers, continuation);
        jwPlayerHelper$extractStreamLinks$2$1.L$0 = obj;
        return jwPlayerHelper$extractStreamLinks$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((JwPlayerHelper$extractStreamLinks$2$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(extractorLink.getUrl());
        extractorLink.setHeaders(this.$headers);
        return Unit.INSTANCE;
    }
}

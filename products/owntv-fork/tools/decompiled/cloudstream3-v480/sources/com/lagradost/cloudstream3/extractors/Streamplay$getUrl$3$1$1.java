package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Streamplay.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Streamplay$getUrl$3$1$1", f = "Streamplay.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class Streamplay$getUrl$3$1$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mainServer;
    final /* synthetic */ Streamplay.Source $res;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Streamplay$getUrl$3$1$1(String str, Streamplay.Source source, Continuation<? super Streamplay$getUrl$3$1$1> continuation) {
        super(2, continuation);
        this.$mainServer = str;
        this.$res = source;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Streamplay$getUrl$3$1$1 streamplay$getUrl$3$1$1 = new Streamplay$getUrl$3$1$1(this.$mainServer, this.$res, continuation);
        streamplay$getUrl$3$1$1.L$0 = obj;
        return streamplay$getUrl$3$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((Streamplay$getUrl$3$1$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int value;
        ExtractorLink extractorLink = (ExtractorLink) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        extractorLink.setReferer(this.$mainServer + '/');
        String label = this.$res.getLabel();
        if (Intrinsics.areEqual(label, "HD")) {
            value = Qualities.P720.getValue();
        } else {
            value = Intrinsics.areEqual(label, "SD") ? Qualities.P480.getValue() : Qualities.Unknown.getValue();
        }
        extractorLink.setQuality(value);
        extractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Range", "bytes=0-")));
        return Unit.INSTANCE;
    }
}

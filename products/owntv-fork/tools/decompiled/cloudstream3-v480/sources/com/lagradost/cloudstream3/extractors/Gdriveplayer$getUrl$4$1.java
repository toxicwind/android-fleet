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
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Gdriveplayer.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Gdriveplayer$getUrl$4$1", f = "Gdriveplayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class Gdriveplayer$getUrl$4$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $quality;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Gdriveplayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Gdriveplayer$getUrl$4$1(Gdriveplayer gdriveplayer, String str, Continuation<? super Gdriveplayer$getUrl$4$1> continuation) {
        super(2, continuation);
        this.this$0 = gdriveplayer;
        this.$quality = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Gdriveplayer$getUrl$4$1 gdriveplayer$getUrl$4$1 = new Gdriveplayer$getUrl$4$1(this.this$0, this.$quality, continuation);
        gdriveplayer$getUrl$4$1.L$0 = obj;
        return gdriveplayer$getUrl$4$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((Gdriveplayer$getUrl$4$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
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
        Integer intOrNull = StringsKt.toIntOrNull(this.$quality);
        extractorLink.setQuality(intOrNull != null ? intOrNull.intValue() : Qualities.Unknown.getValue());
        extractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Range", "bytes=0-")));
        return Unit.INSTANCE;
    }
}

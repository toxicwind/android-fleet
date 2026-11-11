package com.lagradost.cloudstream3.extractors.helper;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: VstreamhubHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.VstreamhubHelper$Companion", f = "VstreamhubHelper.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {23, 33, 52}, m = "getUrls", n = {ImagesContract.URL, "subtitleCallback", "callback", ImagesContract.URL, "subtitleCallback", "callback", "doc", "$this$forEach$iv", "element$iv", "it", "startString", "innerText", "aa", "linkUrl", "$i$f$forEach", "$i$a$-forEach-VstreamhubHelper$Companion$getUrls$2", ImagesContract.URL, "subtitleCallback", "callback", "doc", "$this$forEach$iv", "element$iv", "it", "aa", "innerText", "startString", "bb", "datavid", "$i$f$forEach", "$i$a$-forEach-VstreamhubHelper$Companion$getUrls$2"}, nl = {24, 42, 57}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "I$1"}, v = 2)
final class VstreamhubHelper$Companion$getUrls$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VstreamhubHelper.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VstreamhubHelper$Companion$getUrls$1(VstreamhubHelper.Companion companion, Continuation<? super VstreamhubHelper$Companion$getUrls$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getUrls(null, null, null, this);
    }
}

package com.lagradost.cloudstream3.extractors.helper;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: AsianEmbedHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.AsianEmbedHelper$Companion", f = "AsianEmbedHelper.kt", i = {0, 0, 0, 1, 1, 1, 1, 1}, l = {18, 21}, m = "getUrls", n = {ImagesContract.URL, "subtitleCallback", "callback", ImagesContract.URL, "subtitleCallback", "callback", "doc", CloudStreamPackage.LINKS_EXTRA}, nl = {19, 30}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
final class AsianEmbedHelper$Companion$getUrls$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AsianEmbedHelper.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AsianEmbedHelper$Companion$getUrls$1(AsianEmbedHelper.Companion companion, Continuation<? super AsianEmbedHelper$Companion$getUrls$1> continuation) {
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

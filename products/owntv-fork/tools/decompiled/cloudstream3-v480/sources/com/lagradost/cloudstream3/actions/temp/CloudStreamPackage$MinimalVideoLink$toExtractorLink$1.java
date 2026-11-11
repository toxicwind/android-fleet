package com.lagradost.cloudstream3.actions.temp;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: CloudStreamPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink", f = "CloudStreamPackage.kt", i = {0, 0}, l = {81}, m = "toExtractorLink", n = {ImagesContract.URL, "$i$a$-let-CloudStreamPackage$MinimalVideoLink$toExtractorLink$2"}, nl = {93}, s = {"L$0", "I$0"}, v = 2)
final class CloudStreamPackage$MinimalVideoLink$toExtractorLink$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CloudStreamPackage.MinimalVideoLink this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CloudStreamPackage$MinimalVideoLink$toExtractorLink$1(CloudStreamPackage.MinimalVideoLink minimalVideoLink, Continuation<? super CloudStreamPackage$MinimalVideoLink$toExtractorLink$1> continuation) {
        super(continuation);
        this.this$0 = minimalVideoLink;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.toExtractorLink(this);
    }
}

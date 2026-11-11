package com.lagradost.cloudstream3.extractors.helper;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: WcoHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.WcoHelper$Companion", f = "WcoHelper.kt", i = {}, l = {40}, m = "getNewKeys", n = {}, nl = {41}, s = {}, v = 2)
final class WcoHelper$Companion$getNewKeys$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WcoHelper.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WcoHelper$Companion$getNewKeys$1(WcoHelper.Companion companion, Continuation<? super WcoHelper$Companion$getNewKeys$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getNewKeys(this);
    }
}

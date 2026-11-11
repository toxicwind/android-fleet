package com.lagradost.cloudstream3.syncproviders.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion", f = "AniListApi.kt", i = {0, 0, 0, 0, 0}, l = {302}, m = "getShowId", n = {"malId", "name", "year", "blackList", "blackListRegex"}, nl = {304}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
final class AniListApi$Companion$getShowId$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AniListApi.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AniListApi$Companion$getShowId$1(AniListApi.Companion companion, Continuation<? super AniListApi$Companion$getShowId$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getShowId(null, null, null, this);
    }
}

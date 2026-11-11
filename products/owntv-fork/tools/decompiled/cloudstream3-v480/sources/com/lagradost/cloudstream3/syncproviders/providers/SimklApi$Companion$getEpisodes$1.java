package com.lagradost.cloudstream3.syncproviders.providers;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SimklApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion", f = "SimklApi.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {545}, m = "getEpisodes", n = {"simklId", "type", "episodes", "hasEnded", "cacheKey", "cache", ImagesContract.URL}, nl = {546}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
final class SimklApi$Companion$getEpisodes$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SimklApi.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SimklApi$Companion$getEpisodes$1(SimklApi.Companion companion, Continuation<? super SimklApi$Companion$getEpisodes$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getEpisodes(null, null, null, null, this);
    }
}

package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SimklApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$SimklScoreBuilder$Builder", f = "SimklApi.kt", i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, l = {TypedValues.CycleType.TYPE_WAVE_PHASE, 440, 457, 481}, m = "execute", n = {"time", "headers", "time", "headers", "newStatus", "setStatus", "$i$a$-let-SimklApi$Companion$SimklScoreBuilder$Builder$execute$statusResponse$1", "time", "headers", "seasons", "episodes", "statusResponse", "$i$a$-let-SimklApi$Companion$SimklScoreBuilder$Builder$execute$episodeRemovalResponse$1", "time", "headers", "realScore", "statusResponse", "episodeRemovalResponse", "shouldRate"}, nl = {432, 453, 470, 497}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "I$2"}, v = 2)
final class SimklApi$Companion$SimklScoreBuilder$Builder$execute$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SimklApi.Companion.SimklScoreBuilder.Builder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SimklApi$Companion$SimklScoreBuilder$Builder$execute$1(SimklApi.Companion.SimklScoreBuilder.Builder builder, Continuation<? super SimklApi$Companion$SimklScoreBuilder$Builder$execute$1> continuation) {
        super(continuation);
        this.this$0 = builder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute(this);
    }
}

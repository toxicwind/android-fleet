package com.lagradost.cloudstream3.utils.videoskip;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.SkipAPI$Companion", f = "SkipAPI.kt", i = {0, 0, 0, 0, 0}, l = {85}, m = "videoStamps", n = {"data", "episode", "api", "episodeDurationMs", "hasNextEpisode"}, nl = {86}, s = {"L$0", "L$1", "L$3", "J$0", "Z$0"}, v = 2)
final class SkipAPI$Companion$videoStamps$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SkipAPI.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SkipAPI$Companion$videoStamps$1(SkipAPI.Companion companion, Continuation<? super SkipAPI$Companion$videoStamps$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.videoStamps(null, null, 0L, false, this);
    }
}

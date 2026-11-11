package com.lagradost.cloudstream3.utils.videoskip;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.SkipAPI$Companion$videoStamps$stamps$1", f = "SkipAPI.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class SkipAPI$Companion$videoStamps$stamps$1 extends SuspendLambda implements Function1<Continuation<? super List<? extends SkipStamp>>, Object> {
    final /* synthetic */ SkipAPI $api;
    final /* synthetic */ LoadResponse $data;
    final /* synthetic */ ResultEpisode $episode;
    final /* synthetic */ long $episodeDurationMs;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SkipAPI$Companion$videoStamps$stamps$1(SkipAPI skipAPI, LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super SkipAPI$Companion$videoStamps$stamps$1> continuation) {
        super(1, continuation);
        this.$api = skipAPI;
        this.$data = loadResponse;
        this.$episode = resultEpisode;
        this.$episodeDurationMs = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SkipAPI$Companion$videoStamps$stamps$1(this.$api, this.$data, this.$episode, this.$episodeDurationMs, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super List<? extends SkipStamp>> continuation) {
        return invoke2((Continuation<? super List<SkipStamp>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super List<SkipStamp>> continuation) {
        return ((SkipAPI$Companion$videoStamps$stamps$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        Object objStamps = this.$api.stamps(this.$data, this.$episode, this.$episodeDurationMs, this);
        return objStamps == coroutine_suspended ? coroutine_suspended : objStamps;
    }
}

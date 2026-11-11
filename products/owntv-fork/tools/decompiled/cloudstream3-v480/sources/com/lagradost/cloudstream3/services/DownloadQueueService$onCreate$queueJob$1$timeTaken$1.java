package com.lagradost.cloudstream3.services;

import com.lagradost.cloudstream3.plugins.PluginManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: DownloadQueueService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1$timeTaken$1", f = "DownloadQueueService.kt", i = {0, 0, 0}, l = {179}, m = "invokeSuspend", n = {"$i$f$measureTimeMillis", "start$iv", "$i$a$-measureTimeMillis-DownloadQueueService$onCreate$queueJob$1$timeTaken$1$1"}, nl = {181}, s = {"I$0", "J$0", "I$1"}, v = 2)
final class DownloadQueueService$onCreate$queueJob$1$timeTaken$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    int I$0;
    int I$1;
    long J$0;
    int label;

    DownloadQueueService$onCreate$queueJob$1$timeTaken$1(Continuation<? super DownloadQueueService$onCreate$queueJob$1$timeTaken$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadQueueService$onCreate$queueJob$1$timeTaken$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((DownloadQueueService$onCreate$queueJob$1$timeTaken$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long jCurrentTimeMillis;
        int i;
        int i2;
        long duration;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            jCurrentTimeMillis = System.currentTimeMillis();
            i = 0;
            i2 = 0;
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$1;
            jCurrentTimeMillis = this.J$0;
            i2 = this.I$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            if (!PluginManager.INSTANCE.getLoadedOnlinePlugins() || !PluginManager.INSTANCE.getLoadedLocalPlugins()) {
                Duration.Companion companion = Duration.INSTANCE;
                duration = DurationKt.toDuration(100, DurationUnit.MILLISECONDS);
                this.I$0 = i2;
                this.J$0 = jCurrentTimeMillis;
                this.I$1 = i;
                this.label = 1;
            } else {
                return Boxing.boxLong(System.currentTimeMillis() - jCurrentTimeMillis);
            }
        } while (DelayKt.m10470delayVtjQ1oo(duration, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}

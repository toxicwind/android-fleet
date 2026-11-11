package com.lagradost.cloudstream3.utils;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CastHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient$MediaChannelResult;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.CastHelper$awaitLinks$1$res$1", f = "CastHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class CastHelper$awaitLinks$1$res$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RemoteMediaClient.MediaChannelResult>, Object> {
    final /* synthetic */ PendingResult<RemoteMediaClient.MediaChannelResult> $pending;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CastHelper$awaitLinks$1$res$1(PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult, Continuation<? super CastHelper$awaitLinks$1$res$1> continuation) {
        super(2, continuation);
        this.$pending = pendingResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CastHelper$awaitLinks$1$res$1(this.$pending, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RemoteMediaClient.MediaChannelResult> continuation) {
        return ((CastHelper$awaitLinks$1$res$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.$pending.await();
    }
}

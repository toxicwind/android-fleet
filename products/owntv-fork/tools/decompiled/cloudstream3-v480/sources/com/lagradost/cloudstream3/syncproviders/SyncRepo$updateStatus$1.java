package com.lagradost.cloudstream3.syncproviders;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SyncRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SyncRepo", f = "SyncRepo.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {14, 14}, m = "updateStatus-0E7RQCE", n = {"id", "newStatus", "$this$updateStatus_0E7RQCE_u24lambda_u240", "$i$a$-runCatching-SyncRepo$updateStatus$2", "id", "newStatus", "$this$updateStatus_0E7RQCE_u24lambda_u240", "$i$a$-runCatching-SyncRepo$updateStatus$2"}, nl = {14, 15}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
final class SyncRepo$updateStatus$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncRepo$updateStatus$1(SyncRepo syncRepo, Continuation<? super SyncRepo$updateStatus$1> continuation) {
        super(continuation);
        this.this$0 = syncRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM8428updateStatus0E7RQCE = this.this$0.m8428updateStatus0E7RQCE(null, null, this);
        return objM8428updateStatus0E7RQCE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM8428updateStatus0E7RQCE : Result.m8935boximpl(objM8428updateStatus0E7RQCE);
    }
}

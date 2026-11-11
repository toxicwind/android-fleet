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
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SyncRepo", f = "SyncRepo.kt", i = {0, 0, 0, 1, 1, 1}, l = {24, 24}, m = "load-gIAlu-s", n = {"id", "$this$load_gIAlu_s_u24lambda_u240", "$i$a$-runCatching-SyncRepo$load$2", "id", "$this$load_gIAlu_s_u24lambda_u240", "$i$a$-runCatching-SyncRepo$load$2"}, nl = {24, 23}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0"}, v = 2)
final class SyncRepo$load$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncRepo$load$1(SyncRepo syncRepo, Continuation<? super SyncRepo$load$1> continuation) {
        super(continuation);
        this.this$0 = syncRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM8426loadgIAlus = this.this$0.m8426loadgIAlus(null, this);
        return objM8426loadgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM8426loadgIAlus : Result.m8935boximpl(objM8426loadgIAlus);
    }
}

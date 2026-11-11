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
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SyncRepo", f = "SyncRepo.kt", i = {0, 0, 1, 1}, l = {28, 28}, m = "library-IoAF18A", n = {"$this$library_IoAF18A_u24lambda_u240", "$i$a$-runCatching-SyncRepo$library$2", "$this$library_IoAF18A_u24lambda_u240", "$i$a$-runCatching-SyncRepo$library$2"}, nl = {28, 27}, s = {"L$0", "I$0", "L$0", "I$0"}, v = 2)
final class SyncRepo$library$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncRepo$library$1(SyncRepo syncRepo, Continuation<? super SyncRepo$library$1> continuation) {
        super(continuation);
        this.this$0 = syncRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM8425libraryIoAF18A = this.this$0.m8425libraryIoAF18A(this);
        return objM8425libraryIoAF18A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM8425libraryIoAF18A : Result.m8935boximpl(objM8425libraryIoAF18A);
    }
}

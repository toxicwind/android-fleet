package com.lagradost.cloudstream3.syncproviders;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SubtitleRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SubtitleRepo", f = "SubtitleRepo.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {49, 49}, m = "resource-gIAlu-s", n = {"data", "$this$resource_gIAlu_s_u24lambda_u240", "cached", "$i$a$-runCatching-SubtitleRepo$resource$2", "data", "$this$resource_gIAlu_s_u24lambda_u240", "cached", "$i$a$-runCatching-SubtitleRepo$resource$2"}, nl = {49, 50}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
final class SubtitleRepo$resource$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubtitleRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SubtitleRepo$resource$1(SubtitleRepo subtitleRepo, Continuation<? super SubtitleRepo$resource$1> continuation) {
        super(continuation);
        this.this$0 = subtitleRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM8423resourcegIAlus = this.this$0.m8423resourcegIAlus(null, this);
        return objM8423resourcegIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM8423resourcegIAlus : Result.m8935boximpl(objM8423resourcegIAlus);
    }
}

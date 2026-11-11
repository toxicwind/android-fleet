package com.lagradost.cloudstream3.syncproviders;

import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SubtitleRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SubtitleRepo", f = "SubtitleRepo.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {78, 78}, m = "search-gIAlu-s", n = {SearchIntents.EXTRA_QUERY, "$this$search_gIAlu_s_u24lambda_u240", "cached", "$i$a$-runCatching-SubtitleRepo$search$2", SearchIntents.EXTRA_QUERY, "$this$search_gIAlu_s_u24lambda_u240", "cached", "$i$a$-runCatching-SubtitleRepo$search$2"}, nl = {78, 81}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
final class SubtitleRepo$search$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubtitleRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SubtitleRepo$search$1(SubtitleRepo subtitleRepo, Continuation<? super SubtitleRepo$search$1> continuation) {
        super(continuation);
        this.this$0 = subtitleRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM8424searchgIAlus = this.this$0.m8424searchgIAlus(null, this);
        return objM8424searchgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM8424searchgIAlus : Result.m8935boximpl(objM8424searchgIAlus);
    }
}

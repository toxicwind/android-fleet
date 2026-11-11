package com.lagradost.cloudstream3.ui.home;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion", f = "HomeViewModel.kt", i = {1}, l = {63, 68}, m = "getResumeWatching", n = {"resumeWatching"}, nl = {68, 108}, s = {"L$0"}, v = 2)
final class HomeViewModel$Companion$getResumeWatching$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HomeViewModel.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeViewModel$Companion$getResumeWatching$1(HomeViewModel.Companion companion, Continuation<? super HomeViewModel$Companion$getResumeWatching$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getResumeWatching(this);
    }
}

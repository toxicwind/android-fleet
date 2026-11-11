package com.lagradost.cloudstream3.metaproviders;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: MyDramaList.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Media", f = "MyDramaList.kt", i = {}, l = {297}, m = "fetchRecommendations", n = {}, nl = {300}, s = {}, v = 2)
final class MyDramaListAPI$Media$fetchRecommendations$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MyDramaListAPI.Media this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MyDramaListAPI$Media$fetchRecommendations$1(MyDramaListAPI.Media media, Continuation<? super MyDramaListAPI$Media$fetchRecommendations$1> continuation) {
        super(continuation);
        this.this$0 = media;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchRecommendations(this);
    }
}

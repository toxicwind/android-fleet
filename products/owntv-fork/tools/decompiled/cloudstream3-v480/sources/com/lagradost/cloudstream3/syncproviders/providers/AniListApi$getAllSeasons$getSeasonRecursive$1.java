package com.lagradost.cloudstream3.syncproviders.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {847, 853}, m = "getAllSeasons$getSeasonRecursive", n = {"seasons", "id", "seasons", "season", "$this$forEach$iv", "element$iv", "it", "id", "$i$f$forEach", "$i$a$-forEach-AniListApi$getAllSeasons$getSeasonRecursive$2"}, nl = {848, 854}, s = {"L$0", "I$0", "L$0", "L$1", "L$2", "L$4", "L$5", "I$0", "I$1", "I$2"}, v = 2)
final class AniListApi$getAllSeasons$getSeasonRecursive$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;

    AniListApi$getAllSeasons$getSeasonRecursive$1(Continuation<? super AniListApi$getAllSeasons$getSeasonRecursive$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AniListApi.getAllSeasons$getSeasonRecursive(null, 0, this);
    }
}

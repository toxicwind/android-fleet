package com.lagradost.cloudstream3.extractors.helper;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: GogoHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.GogoHelper$extractVidstream$2", f = "GogoHelper.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {122, 130}, m = "invokeSuspend$invokeGogoSource", n = {"$mainApiName", "mainUrl", "source", "sourceCallback", "$mainApiName", "mainUrl", "source", "sourceCallback"}, nl = {127, 129}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 2)
final class GogoHelper$extractVidstream$2$invokeGogoSource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    GogoHelper$extractVidstream$2$invokeGogoSource$1(Continuation<? super GogoHelper$extractVidstream$2$invokeGogoSource$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return GogoHelper.AnonymousClass2.invokeSuspend$invokeGogoSource(null, null, null, null, this);
    }
}

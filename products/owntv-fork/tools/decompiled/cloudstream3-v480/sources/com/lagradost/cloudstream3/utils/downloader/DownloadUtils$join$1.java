package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DownloadUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadUtils", f = "DownloadUtils.kt", i = {0, 0, 0, 0, 0, 0}, l = {159}, m = "join$app", n = {"$this$join", "$this$forEach$iv", "element$iv", "job", "$i$f$forEach", "$i$a$-forEach-DownloadUtils$join$2"}, nl = {160}, s = {"L$0", "L$1", "L$3", "L$4", "I$0", "I$1"}, v = 2)
final class DownloadUtils$join$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DownloadUtils this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadUtils$join$1(DownloadUtils downloadUtils, Continuation<? super DownloadUtils$join$1> continuation) {
        super(continuation);
        this.this$0 = downloadUtils;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.join$app(null, this);
    }
}

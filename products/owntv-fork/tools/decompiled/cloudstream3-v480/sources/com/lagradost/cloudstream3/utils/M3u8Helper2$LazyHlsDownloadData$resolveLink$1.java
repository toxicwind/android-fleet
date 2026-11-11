package com.lagradost.cloudstream3.utils;

import com.google.android.gms.cast.HlsSegmentFormat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: M3u8Helper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData", f = "M3u8Helper.kt", i = {0, 0}, l = {231}, m = "resolveLink", n = {HlsSegmentFormat.TS, "index"}, nl = {232}, s = {"L$0", "I$0"}, v = 2)
final class M3u8Helper2$LazyHlsDownloadData$resolveLink$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ M3u8Helper2.LazyHlsDownloadData this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    M3u8Helper2$LazyHlsDownloadData$resolveLink$1(M3u8Helper2.LazyHlsDownloadData lazyHlsDownloadData, Continuation<? super M3u8Helper2$LazyHlsDownloadData$resolveLink$1> continuation) {
        super(continuation);
        this.this$0 = lazyHlsDownloadData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolveLink(0, this);
    }
}

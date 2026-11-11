package com.lagradost.cloudstream3.utils.downloader;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$LazyStreamDownloadData", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {865}, m = "resolveSafe", n = {"buffer", "callback", TtmlNode.END, "index", "retries", "start", CmcdData.OBJECT_TYPE_INIT_SEGMENT}, nl = {867}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "J$0", "I$2"}, v = 2)
final class VideoDownloadManager$LazyStreamDownloadData$resolveSafe$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoDownloadManager.LazyStreamDownloadData this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$LazyStreamDownloadData$resolveSafe$1(VideoDownloadManager.LazyStreamDownloadData lazyStreamDownloadData, Continuation<? super VideoDownloadManager$LazyStreamDownloadData$resolveSafe$1> continuation) {
        super(continuation);
        this.this$0 = lazyStreamDownloadData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolveSafe(0, 0, null, null, this);
    }
}

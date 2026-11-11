package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance", f = "DownloadManager.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2}, l = {1843, 1848, 1855}, m = "startDownload", n = {"info", "pkg", "dpkg", "$i$a$-let-VideoDownloadManager$EpisodeDownloadInstance$startDownload$2", "info", "pkg", "$this$startDownload_u24lambda_u241", "$i$a$-run-VideoDownloadManager$EpisodeDownloadInstance$startDownload$3", "info", "pkg"}, nl = {1846, 1853, 1859}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1"}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$startDownload$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$startDownload$1(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$startDownload$1> continuation) {
        super(continuation);
        this.this$0 = episodeDownloadInstance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.startDownload(null, null, this);
    }
}

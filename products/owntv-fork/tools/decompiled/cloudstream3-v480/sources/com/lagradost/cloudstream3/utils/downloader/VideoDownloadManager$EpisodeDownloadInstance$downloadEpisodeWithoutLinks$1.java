package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {2015, 2048}, m = "downloadEpisodeWithoutLinks", n = {"downloadItem", "generator", "currentLinks", "currentSubs", "meta", "downloadItem", "generator", "currentLinks", "currentSubs", "meta"}, nl = {2027, 2051}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1> continuation) {
        super(continuation);
        this.this$0 = episodeDownloadInstance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadEpisodeWithoutLinks(this);
    }
}

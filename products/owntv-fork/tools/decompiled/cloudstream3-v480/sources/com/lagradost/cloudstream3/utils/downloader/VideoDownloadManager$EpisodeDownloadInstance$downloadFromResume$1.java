package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1795, 1806}, m = "downloadFromResume", n = {"downloadResumePackage", "notificationCallback", "item", "link", "id", "index", "resume", "downloadResumePackage", "notificationCallback", "item", "link", "connectionResult", "id", "index", "resume"}, nl = {1794, 1817}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$3"}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$downloadFromResume$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$downloadFromResume$1(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$downloadFromResume$1> continuation) {
        super(continuation);
        this.this$0 = episodeDownloadInstance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadFromResume(null, null, this);
    }
}

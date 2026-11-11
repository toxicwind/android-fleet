package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$startDownload$5", f = "DownloadManager.kt", i = {}, l = {1880, 1883}, m = "invokeSuspend", n = {}, nl = {1882, 1884}, s = {}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$startDownload$5 extends SuspendLambda implements Function3<CoroutineScope, VideoDownloadManager.EpisodeDownloadInstance, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$startDownload$5(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$startDownload$5> continuation) {
        super(3, continuation);
        this.this$0 = episodeDownloadInstance;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super Unit> continuation) {
        return new VideoDownloadManager$EpisodeDownloadInstance$startDownload$5(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        if (r1.downloadFromResume(r4) == r0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0066, code lost:
    
        if (r4.this$0.downloadEpisodeWithoutLinks(r4) == r0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 == r3) goto L17
            if (r1 != r2) goto Lf
            goto L17
        Lf:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L17:
            kotlin.ResultKt.throwOnFailure(r5)
            goto La6
        L1c:
            kotlin.ResultKt.throwOnFailure(r5)
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r5 = r4.this$0
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r5 = r5.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadResumePackage r5 = r5.getResumePackage()
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r1 = r4.this$0
            if (r5 == 0) goto L39
            r5 = r4
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r4.label = r3
            java.lang.Object r4 = com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.EpisodeDownloadInstance.access$downloadFromResume(r1, r5)
            if (r4 != r0) goto La6
            goto L68
        L39:
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r5 = r1.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueItem r5 = r5.getDownloadItem()
            if (r5 == 0) goto L69
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r5 = r4.this$0
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r5 = r5.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueItem r5 = r5.getDownloadItem()
            java.util.List r5 = r5.getLinks()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L5b
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L69
        L5b:
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r5 = r4.this$0
            r1 = r4
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4.label = r2
            java.lang.Object r4 = com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.EpisodeDownloadInstance.access$downloadEpisodeWithoutLinks(r5, r1)
            if (r4 != r0) goto La6
        L68:
            return r0
        L69:
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r5 = r4.this$0
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r5 = r5.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueItem r5 = r5.getDownloadItem()
            if (r5 == 0) goto L7a
            java.util.List r5 = r5.getLinks()
            goto L7b
        L7a:
            r5 = 0
        L7b:
            if (r5 == 0) goto La6
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r5 = r4.this$0
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r0 = r5.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueItem r0 = r0.getDownloadItem()
            java.util.List r0 = r0.getLinks()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Set r0 = kotlin.collections.CollectionsKt.toSet(r0)
            java.util.List r0 = com.lagradost.cloudstream3.MainAPIKt.sortUrls(r0)
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance r4 = r4.this$0
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueWrapper r4 = r4.getDownloadQueueWrapper()
            com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadQueueItem r4 = r4.getDownloadItem()
            java.util.List r4 = r4.getSubs()
            com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.EpisodeDownloadInstance.access$downloadEpisodeWithLinks(r5, r0, r4)
        La6:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$startDownload$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

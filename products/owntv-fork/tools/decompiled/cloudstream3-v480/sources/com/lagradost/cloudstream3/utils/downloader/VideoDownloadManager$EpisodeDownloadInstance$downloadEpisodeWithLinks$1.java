package com.lagradost.cloudstream3.utils.downloader;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1", f = "DownloadManager.kt", i = {}, l = {1950}, m = "invokeSuspend", n = {}, nl = {1951}, s = {}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1 extends SuspendLambda implements Function3<CoroutineScope, VideoDownloadManager.EpisodeDownloadInstance, Continuation<? super Unit>, Object> {
    final /* synthetic */ DownloadObjects.DownloadItem $info;
    int label;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, DownloadObjects.DownloadItem downloadItem, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1> continuation) {
        super(3, continuation);
        this.this$0 = episodeDownloadInstance;
        this.$info = downloadItem;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super Unit> continuation) {
        return new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1(this.this$0, this.$info, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.startDownload(this.$info, null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

package com.lagradost.cloudstream3.utils.downloader;

import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6", f = "DownloadManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6 extends SuspendLambda implements Function2<VideoDownloadManager.EpisodeDownloadInstance, Continuation<? super Unit>, Object> {
    int label;

    VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6(Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super Unit> continuation) {
        return ((VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6) create(episodeDownloadInstance, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommonActivity.INSTANCE.showToast(R.string.download_started, Boxing.boxInt(0));
        return Unit.INSTANCE;
    }
}

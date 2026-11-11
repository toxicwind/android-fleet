package com.lagradost.cloudstream3.utils.downloader;

import android.app.Notification;
import android.content.Context;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadSingleEpisode$callback$1$1", f = "DownloadManager.kt", i = {}, l = {1499}, m = "invokeSuspend", n = {}, nl = {1512}, s = {}, v = 2)
final class VideoDownloadManager$downloadSingleEpisode$callback$1$1 extends SuspendLambda implements Function2<VideoDownloadManager, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ DownloadObjects.DownloadEpisodeMetadata $ep;
    final /* synthetic */ ExtractorLink $link;
    final /* synthetic */ DownloadObjects.CreateNotificationMetadata $meta;
    final /* synthetic */ Function2<Integer, Notification, Unit> $notificationCallback;
    final /* synthetic */ String $source;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    VideoDownloadManager$downloadSingleEpisode$callback$1$1(Context context, String str, ExtractorLink extractorLink, DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata, DownloadObjects.CreateNotificationMetadata createNotificationMetadata, Function2<? super Integer, ? super Notification, Unit> function2, Continuation<? super VideoDownloadManager$downloadSingleEpisode$callback$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$source = str;
        this.$link = extractorLink;
        this.$ep = downloadEpisodeMetadata;
        this.$meta = createNotificationMetadata;
        this.$notificationCallback = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoDownloadManager$downloadSingleEpisode$callback$1$1(this.$context, this.$source, this.$link, this.$ep, this.$meta, this.$notificationCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(VideoDownloadManager videoDownloadManager, Continuation<? super Unit> continuation) {
        return ((VideoDownloadManager$downloadSingleEpisode$callback$1$1) create(videoDownloadManager, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (VideoDownloadManager.INSTANCE.createDownloadNotification(this.$context, this.$source, this.$link.getName(), this.$ep, this.$meta.getType(), this.$meta.getBytesDownloaded(), this.$meta.getBytesTotal(), this.$notificationCallback, this.$meta.getHlsProgress(), this.$meta.getHlsTotal(), this.$meta.getBytesPerSecond(), this) == coroutine_suspended) {
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

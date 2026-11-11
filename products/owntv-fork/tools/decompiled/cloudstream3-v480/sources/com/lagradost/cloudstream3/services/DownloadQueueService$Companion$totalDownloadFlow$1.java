package com.lagradost.cloudstream3.services;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DownloadQueueService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "instances", "queue"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.DownloadQueueService$Companion$totalDownloadFlow$1", f = "DownloadQueueService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadQueueService$Companion$totalDownloadFlow$1 extends SuspendLambda implements Function3<List<? extends VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], Continuation<? super Pair<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[]>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    DownloadQueueService$Companion$totalDownloadFlow$1(Continuation<? super DownloadQueueService$Companion$totalDownloadFlow$1> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(List<? extends VideoDownloadManager.EpisodeDownloadInstance> list, DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr, Continuation<? super Pair<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[]>> continuation) {
        return invoke2((List<VideoDownloadManager.EpisodeDownloadInstance>) list, downloadQueueWrapperArr, (Continuation<? super Pair<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[]>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(List<VideoDownloadManager.EpisodeDownloadInstance> list, DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr, Continuation<? super Pair<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[]>> continuation) {
        DownloadQueueService$Companion$totalDownloadFlow$1 downloadQueueService$Companion$totalDownloadFlow$1 = new DownloadQueueService$Companion$totalDownloadFlow$1(continuation);
        downloadQueueService$Companion$totalDownloadFlow$1.L$0 = list;
        downloadQueueService$Companion$totalDownloadFlow$1.L$1 = downloadQueueWrapperArr;
        return downloadQueueService$Companion$totalDownloadFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list = (List) this.L$0;
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) this.L$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return TuplesKt.to(list, downloadQueueWrapperArr);
    }
}

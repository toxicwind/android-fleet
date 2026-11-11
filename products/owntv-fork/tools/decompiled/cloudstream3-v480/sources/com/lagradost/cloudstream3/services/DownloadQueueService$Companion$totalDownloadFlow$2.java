package com.lagradost.cloudstream3.services;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DownloadQueueService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00012\u001e\u0010\b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\n"}, d2 = {"<anonymous>", "Lkotlin/Triple;", "", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "", "", "<destruct>", "Lkotlin/Pair;", "currentDownloads"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.DownloadQueueService$Companion$totalDownloadFlow$2", f = "DownloadQueueService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadQueueService$Companion$totalDownloadFlow$2 extends SuspendLambda implements Function3<Pair<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[]>, Set<? extends Integer>, Continuation<? super Triple<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[], ? extends Set<? extends Integer>>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    DownloadQueueService$Companion$totalDownloadFlow$2(Continuation<? super DownloadQueueService$Companion$totalDownloadFlow$2> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Pair<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[]> pair, Set<? extends Integer> set, Continuation<? super Triple<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[], ? extends Set<? extends Integer>>> continuation) {
        return invoke2((Pair<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[]>) pair, (Set<Integer>) set, (Continuation<? super Triple<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], ? extends Set<Integer>>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Pair<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[]> pair, Set<Integer> set, Continuation<? super Triple<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], ? extends Set<Integer>>> continuation) {
        DownloadQueueService$Companion$totalDownloadFlow$2 downloadQueueService$Companion$totalDownloadFlow$2 = new DownloadQueueService$Companion$totalDownloadFlow$2(continuation);
        downloadQueueService$Companion$totalDownloadFlow$2.L$0 = pair;
        downloadQueueService$Companion$totalDownloadFlow$2.L$1 = set;
        return downloadQueueService$Companion$totalDownloadFlow$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        Set set = (Set) this.L$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Triple((List) pair.component1(), (DownloadObjects.DownloadQueueWrapper[]) pair.component2(), set);
    }
}

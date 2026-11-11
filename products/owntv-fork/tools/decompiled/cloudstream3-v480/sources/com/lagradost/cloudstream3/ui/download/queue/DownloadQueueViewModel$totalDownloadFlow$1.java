package com.lagradost.cloudstream3.ui.download.queue;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DownloadQueueViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterQueue;", "instances", "", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "queue", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel$totalDownloadFlow$1", f = "DownloadQueueViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadQueueViewModel$totalDownloadFlow$1 extends SuspendLambda implements Function3<List<? extends VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], Continuation<? super DownloadAdapterQueue>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    DownloadQueueViewModel$totalDownloadFlow$1(Continuation<? super DownloadQueueViewModel$totalDownloadFlow$1> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(List<? extends VideoDownloadManager.EpisodeDownloadInstance> list, DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr, Continuation<? super DownloadAdapterQueue> continuation) {
        return invoke2((List<VideoDownloadManager.EpisodeDownloadInstance>) list, downloadQueueWrapperArr, continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(List<VideoDownloadManager.EpisodeDownloadInstance> list, DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr, Continuation<? super DownloadAdapterQueue> continuation) {
        DownloadQueueViewModel$totalDownloadFlow$1 downloadQueueViewModel$totalDownloadFlow$1 = new DownloadQueueViewModel$totalDownloadFlow$1(continuation);
        downloadQueueViewModel$totalDownloadFlow$1.L$0 = list;
        downloadQueueViewModel$totalDownloadFlow$1.L$1 = downloadQueueWrapperArr;
        return downloadQueueViewModel$totalDownloadFlow$1.invokeSuspend(Unit.INSTANCE);
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
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((VideoDownloadManager.EpisodeDownloadInstance) it.next()).getDownloadQueueWrapper());
        }
        return new DownloadAdapterQueue(arrayList, ArraysKt.toList(downloadQueueWrapperArr));
    }
}

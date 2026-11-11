package com.lagradost.cloudstream3.ui.download.queue;

import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DownloadQueueViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterQueue;", "total", "<unused var>", "", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel$totalDownloadFlow$2", f = "DownloadQueueViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadQueueViewModel$totalDownloadFlow$2 extends SuspendLambda implements Function3<DownloadAdapterQueue, Set<? extends Integer>, Continuation<? super DownloadAdapterQueue>, Object> {
    /* synthetic */ Object L$0;
    int label;

    DownloadQueueViewModel$totalDownloadFlow$2(Continuation<? super DownloadQueueViewModel$totalDownloadFlow$2> continuation) {
        super(3, continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(DownloadAdapterQueue downloadAdapterQueue, Set<Integer> set, Continuation<? super DownloadAdapterQueue> continuation) {
        DownloadQueueViewModel$totalDownloadFlow$2 downloadQueueViewModel$totalDownloadFlow$2 = new DownloadQueueViewModel$totalDownloadFlow$2(continuation);
        downloadQueueViewModel$totalDownloadFlow$2.L$0 = downloadAdapterQueue;
        return downloadQueueViewModel$totalDownloadFlow$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(DownloadAdapterQueue downloadAdapterQueue, Set<? extends Integer> set, Continuation<? super DownloadAdapterQueue> continuation) {
        return invoke2(downloadAdapterQueue, (Set<Integer>) set, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        DownloadAdapterQueue downloadAdapterQueue = (DownloadAdapterQueue) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return downloadAdapterQueue;
    }
}

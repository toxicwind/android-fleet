package com.lagradost.cloudstream3.services;

import android.content.Context;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadQueueService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/services/DownloadQueueService;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1", f = "DownloadQueueService.kt", i = {0, 1, 1}, l = {176, 208}, m = "invokeSuspend", n = {"timeout", "timeTaken", "timeout"}, nl = {184, 248}, s = {"J$0", "L$0", "J$0"}, v = 2)
final class DownloadQueueService$onCreate$queueJob$1 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueService, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    long J$0;
    Object L$0;
    int label;
    final /* synthetic */ DownloadQueueService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadQueueService$onCreate$queueJob$1(Context context, DownloadQueueService downloadQueueService, Continuation<? super DownloadQueueService$onCreate$queueJob$1> continuation) {
        super(3, continuation);
        this.$context = context;
        this.this$0 = downloadQueueService;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, DownloadQueueService downloadQueueService, Continuation<? super Unit> continuation) {
        return new DownloadQueueService$onCreate$queueJob$1(this.$context, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e1, code lost:
    
        if (r1.collect(new com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1.AnonymousClass7(), r10) == r0) goto L36;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws com.lagradost.cloudstream3.mvvm.DebugException {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    static final Duration invokeSuspend$lambda$4(Triple triple) {
        long duration;
        List list = (List) triple.component1();
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) triple.component2();
        if (list.isEmpty() && downloadQueueWrapperArr.length == 0) {
            Duration.Companion companion = Duration.INSTANCE;
            duration = DurationKt.toDuration(500, DurationUnit.MILLISECONDS);
        } else {
            Duration.Companion companion2 = Duration.INSTANCE;
            duration = DurationKt.toDuration(0, DurationUnit.MILLISECONDS);
        }
        return Duration.m10316boximpl(duration);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1$6, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadQueueService.kt */
    @Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003H\n"}, d2 = {"<anonymous>", "", "<destruct>", "Lkotlin/Triple;", "", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.DownloadQueueService$onCreate$queueJob$1$6", f = "DownloadQueueService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<Triple<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[], ? extends Set<? extends Integer>>, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Triple<? extends List<? extends VideoDownloadManager.EpisodeDownloadInstance>, ? extends DownloadObjects.DownloadQueueWrapper[], ? extends Set<? extends Integer>> triple, Continuation<? super Boolean> continuation) {
            return invoke2((Triple<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], ? extends Set<Integer>>) triple, continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Triple<? extends List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], ? extends Set<Integer>> triple, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass6) create(triple, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x0034  */
        /* JADX WARN: Code duplicated, block: B:15:0x003c  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Triple triple = (Triple) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List list = (List) triple.component1();
            DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) triple.component2();
            boolean z = false;
            if (DownloadQueueService.INSTANCE.isRunning()) {
                if (list.isEmpty()) {
                    if (!(downloadQueueWrapperArr.length == 0)) {
                        if (MainActivity.INSTANCE.getLastError() == null) {
                            z = true;
                        }
                    }
                } else if (MainActivity.INSTANCE.getLastError() == null) {
                    z = true;
                }
            }
            return Boxing.boxBoolean(z);
        }
    }
}

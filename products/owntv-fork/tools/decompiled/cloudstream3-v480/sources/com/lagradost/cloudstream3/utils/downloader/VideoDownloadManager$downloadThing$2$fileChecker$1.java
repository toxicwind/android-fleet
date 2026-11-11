package com.lagradost.cloudstream3.utils.downloader;

import com.google.android.gms.cast.RemoteMediaPlayer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$fileChecker$1", f = "DownloadManager.kt", i = {0, 1, 1, 1}, l = {1088, RemoteMediaPlayer.STATUS_TIMED_OUT}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$withLock_u24default$iv", "$i$f$withLock"}, nl = {1089, 2103}, s = {"L$0", "L$0", "L$1", "I$0"}, v = 2)
final class VideoDownloadManager$downloadThing$2$fileChecker$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Mutex $fileMutex;
    final /* synthetic */ VideoDownloadManager.DownloadMetaData $metadata;
    final /* synthetic */ Ref.ObjectRef<DownloadObjects.StreamData> $stream;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$downloadThing$2$fileChecker$1(Ref.ObjectRef<DownloadObjects.StreamData> objectRef, Mutex mutex, VideoDownloadManager.DownloadMetaData downloadMetaData, Continuation<? super VideoDownloadManager$downloadThing$2$fileChecker$1> continuation) {
        super(2, continuation);
        this.$stream = objectRef;
        this.$fileMutex = mutex;
        this.$metadata = downloadMetaData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoDownloadManager$downloadThing$2$fileChecker$1 videoDownloadManager$downloadThing$2$fileChecker$1 = new VideoDownloadManager$downloadThing$2$fileChecker$1(this.$stream, this.$fileMutex, this.$metadata, continuation);
        videoDownloadManager$downloadThing$2$fileChecker$1.L$0 = obj;
        return videoDownloadManager$downloadThing$2$fileChecker$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoDownloadManager$downloadThing$2$fileChecker$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Mutex mutex;
        VideoDownloadManager.DownloadMetaData downloadMetaData;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0 || i == 1) {
                ResultKt.throwOnFailure(obj);
                do {
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        if (!this.$stream.element.getExists()) {
                            Mutex mutex2 = this.$fileMutex;
                            VideoDownloadManager.DownloadMetaData downloadMetaData2 = this.$metadata;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = mutex2;
                            this.L$2 = downloadMetaData2;
                            this.I$0 = 0;
                            this.label = 2;
                            if (mutex2.lock(null, this) == coroutine_suspended) {
                                break;
                            }
                            mutex = mutex2;
                            downloadMetaData = downloadMetaData2;
                        } else {
                            this.L$0 = coroutineScope;
                            this.label = 1;
                        }
                    }
                    return Unit.INSTANCE;
                } while (DelayKt.delay(5000L, this) != coroutine_suspended);
                return coroutine_suspended;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            downloadMetaData = (VideoDownloadManager.DownloadMetaData) this.L$2;
            mutex = (Mutex) this.L$1;
            ResultKt.throwOnFailure(obj);
            downloadMetaData.setType(VideoDownloadManager.DownloadType.IsStopped);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            mutex.unlock(null);
        }
    }
}

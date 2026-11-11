package com.lagradost.cloudstream3.utils.downloader;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1", f = "DownloadManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Boolean>>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $id;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1(int i, Context context, Continuation<? super VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1> continuation) {
        super(2, continuation);
        this.$id = i;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1(this.$id, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Boolean>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Boolean>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Integer, Boolean>> continuation) {
        return ((VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return TuplesKt.to(Boxing.boxInt(this.$id), Boxing.boxBoolean(VideoDownloadManager.INSTANCE.deleteFileAndUpdateSettings(this.$context, this.$id)));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}

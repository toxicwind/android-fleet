package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Set<Integer> $ids;
    final /* synthetic */ Set<Integer> $parentIds;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DownloadViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1(Context context, Set<Integer> set, DownloadViewModel downloadViewModel, Set<Integer> set2, Continuation<? super DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$ids = set;
        this.this$0 = downloadViewModel;
        this.$parentIds = set2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1 downloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1 = new DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1(this.$context, this.$ids, this.this$0, this.$parentIds, continuation);
        downloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1.L$0 = obj;
        return downloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
        Context context = this.$context;
        Set<Integer> set = this.$ids;
        final DownloadViewModel downloadViewModel = this.this$0;
        final Set<Integer> set2 = this.$parentIds;
        videoDownloadManager.deleteFilesAndUpdateSettings(context, set, coroutineScope, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1.invokeSuspend$lambda$0(downloadViewModel, set2, (Set) obj2);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$0(DownloadViewModel downloadViewModel, Set set, Set set2) {
        downloadViewModel.removeItems(SetsKt.plus(set2, (Iterable) set));
        return Unit.INSTANCE;
    }
}

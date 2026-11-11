package com.lagradost.cloudstream3.ui.download.queue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: DownloadQueueViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_childCards", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterQueue;", "childCards", "Landroidx/lifecycle/LiveData;", "getChildCards", "()Landroidx/lifecycle/LiveData;", "totalDownloadFlow", "Lkotlinx/coroutines/flow/Flow;", "updateChildList", "", "downloads", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadQueueViewModel extends ViewModel {
    private final MutableLiveData<DownloadAdapterQueue> _childCards;
    private final LiveData<DownloadAdapterQueue> childCards;
    private final Flow<DownloadAdapterQueue> totalDownloadFlow;

    public DownloadQueueViewModel() {
        MutableLiveData<DownloadAdapterQueue> mutableLiveData = new MutableLiveData<>();
        this._childCards = mutableLiveData;
        this.childCards = mutableLiveData;
        this.totalDownloadFlow = FlowKt.flowCombine(FlowKt.flowCombine(DownloadQueueService.INSTANCE.getDownloadInstances(), DownloadQueueManager.INSTANCE.getQueue(), new DownloadQueueViewModel$totalDownloadFlow$1(null)), VideoDownloadManager.INSTANCE.getCurrentDownloads(), new DownloadQueueViewModel$totalDownloadFlow$2(null));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final LiveData<DownloadAdapterQueue> getChildCards() {
        return this.childCards;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadQueueViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel$1", f = "DownloadQueueViewModel.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, nl = {37}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadQueueViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = DownloadQueueViewModel.this.totalDownloadFlow;
                final DownloadQueueViewModel downloadQueueViewModel = DownloadQueueViewModel.this;
                this.label = 1;
                if (flow.collect(new FlowCollector() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueViewModel.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((DownloadAdapterQueue) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(DownloadAdapterQueue downloadAdapterQueue, Continuation<? super Unit> continuation) {
                        downloadQueueViewModel.updateChildList(downloadAdapterQueue);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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

    public final void updateChildList(DownloadAdapterQueue downloads) {
        Intrinsics.checkNotNullParameter(downloads, "downloads");
        this._childCards.postValue(downloads);
    }
}

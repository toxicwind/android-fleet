package com.lagradost.cloudstream3.ui.home;

import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatching$1", f = "HomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class HomeViewModel$Companion$getResumeWatching$resumeWatching$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DownloadObjects.ResumeWatching>>, Object> {
    int label;

    HomeViewModel$Companion$getResumeWatching$resumeWatching$1(Continuation<? super HomeViewModel$Companion$getResumeWatching$resumeWatching$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeViewModel$Companion$getResumeWatching$resumeWatching$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DownloadObjects.ResumeWatching>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<DownloadObjects.ResumeWatching>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DownloadObjects.ResumeWatching>> continuation) {
        return ((HomeViewModel$Companion$getResumeWatching$resumeWatching$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<Integer> allResumeStateIds = DataStoreHelper.INSTANCE.getAllResumeStateIds();
        if (allResumeStateIds == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = allResumeStateIds.iterator();
        while (it.hasNext()) {
            DownloadObjects.ResumeWatching lastWatched = DataStoreHelper.INSTANCE.getLastWatched(Boxing.boxInt(((Number) it.next()).intValue()));
            if (lastWatched != null) {
                arrayList.add(lastWatched);
            }
        }
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatching$1$invokeSuspend$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(-((DownloadObjects.ResumeWatching) t).getUpdateTime()), Long.valueOf(-((DownloadObjects.ResumeWatching) t2).getUpdateTime()));
            }
        });
    }
}

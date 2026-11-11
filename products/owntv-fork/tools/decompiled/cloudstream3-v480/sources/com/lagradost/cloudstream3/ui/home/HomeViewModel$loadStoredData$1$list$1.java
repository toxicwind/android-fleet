package com.lagradost.cloudstream3.ui.home;

import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadStoredData$1$list$1", f = "HomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class HomeViewModel$loadStoredData$1$list$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DataStoreHelper.BookmarkedData>>, Object> {
    final /* synthetic */ Set<WatchType> $watchPrefNotNull;
    final /* synthetic */ List<Pair<Integer, WatchType>> $watchStatusIds;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    HomeViewModel$loadStoredData$1$list$1(List<? extends Pair<Integer, ? extends WatchType>> list, Set<? extends WatchType> set, Continuation<? super HomeViewModel$loadStoredData$1$list$1> continuation) {
        super(2, continuation);
        this.$watchStatusIds = list;
        this.$watchPrefNotNull = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeViewModel$loadStoredData$1$list$1(this.$watchStatusIds, this.$watchPrefNotNull, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DataStoreHelper.BookmarkedData>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<DataStoreHelper.BookmarkedData>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DataStoreHelper.BookmarkedData>> continuation) {
        return ((HomeViewModel$loadStoredData$1$list$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<Pair<Integer, WatchType>> list = this.$watchStatusIds;
        Set<WatchType> set = this.$watchPrefNotNull;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            if (set.contains(((Pair) obj2).getSecond())) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DataStoreHelper.BookmarkedData bookmarkedData = DataStoreHelper.INSTANCE.getBookmarkedData((Integer) ((Pair) it.next()).getFirst());
            if (bookmarkedData != null) {
                arrayList2.add(bookmarkedData);
            }
        }
        return CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadStoredData$1$list$1$invokeSuspend$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(-((DataStoreHelper.BookmarkedData) t).getLatestUpdatedTime()), Long.valueOf(-((DataStoreHelper.BookmarkedData) t2).getLatestUpdatedTime()));
            }
        });
    }
}

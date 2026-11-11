package com.lagradost.cloudstream3.ui.home;

import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0018\u00010\u0001*\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/ui/WatchType;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadStoredData$1$watchStatusIds$1", f = "HomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class HomeViewModel$loadStoredData$1$watchStatusIds$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Pair<? extends Integer, ? extends WatchType>>>, Object> {
    int label;

    HomeViewModel$loadStoredData$1$watchStatusIds$1(Continuation<? super HomeViewModel$loadStoredData$1$watchStatusIds$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeViewModel$loadStoredData$1$watchStatusIds$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Pair<? extends Integer, ? extends WatchType>>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<? extends Pair<Integer, ? extends WatchType>>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<? extends Pair<Integer, ? extends WatchType>>> continuation) {
        return ((HomeViewModel$loadStoredData$1$watchStatusIds$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<Integer> allWatchStateIds = DataStoreHelper.INSTANCE.getAllWatchStateIds();
        if (allWatchStateIds == null) {
            return null;
        }
        List<Integer> list = allWatchStateIds;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            arrayList.add(new Pair(Boxing.boxInt(iIntValue), DataStoreHelper.INSTANCE.getResultWatchState(iIntValue)));
        }
        return arrayList;
    }
}

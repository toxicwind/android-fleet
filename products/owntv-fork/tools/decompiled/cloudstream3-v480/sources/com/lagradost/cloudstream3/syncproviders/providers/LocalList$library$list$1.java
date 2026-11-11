package com.lagradost.cloudstream3.syncproviders.providers;

import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocalList.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n"}, d2 = {"<anonymous>", "", "", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/syncproviders/providers/LocalList;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.LocalList$library$list$1", f = "LocalList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class LocalList$library$list$1 extends SuspendLambda implements Function3<CoroutineScope, LocalList, Continuation<? super Map<Integer, ? extends List<? extends SyncAPI.LibraryItem>>>, Object> {
    final /* synthetic */ List<Pair<Integer, WatchType>> $watchStatusIds;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    LocalList$library$list$1(List<? extends Pair<Integer, ? extends WatchType>> list, Continuation<? super LocalList$library$list$1> continuation) {
        super(3, continuation);
        this.$watchStatusIds = list;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, LocalList localList, Continuation<? super Map<Integer, ? extends List<? extends SyncAPI.LibraryItem>>> continuation) {
        return invoke2(coroutineScope, localList, (Continuation<? super Map<Integer, ? extends List<SyncAPI.LibraryItem>>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, LocalList localList, Continuation<? super Map<Integer, ? extends List<SyncAPI.LibraryItem>>> continuation) {
        return new LocalList$library$list$1(this.$watchStatusIds, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean zIsLayout = Globals.INSTANCE.isLayout(2);
        EnumEntries<WatchType> entries = WatchType.getEntries();
        ArrayList arrayList = new ArrayList();
        for (WatchType watchType : entries) {
            if (watchType != WatchType.NONE) {
                arrayList.add(watchType);
            }
        }
        ArrayList arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Pair pair = TuplesKt.to(Boxing.boxInt(((WatchType) it.next()).getStringRes()), CollectionsKt.emptyList());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        Map mapPlus = MapsKt.plus(MapsKt.plus(linkedHashMap, MapsKt.mapOf(TuplesKt.to(Boxing.boxInt(R.string.favorites_list_name), CollectionsKt.emptyList()))), !zIsLayout ? MapsKt.mapOf(TuplesKt.to(Boxing.boxInt(R.string.subscription_list_name), CollectionsKt.emptyList())) : MapsKt.emptyMap());
        List<Pair<Integer, WatchType>> list = this.$watchStatusIds;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj2 : list) {
            Integer numBoxInt = Boxing.boxInt(((WatchType) ((Pair) obj2).getSecond()).getStringRes());
            Object obj3 = linkedHashMap2.get(numBoxInt);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap2.put(numBoxInt, obj3);
            }
            ((List) obj3).add(obj2);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            Object key = entry.getKey();
            Iterable<Pair> iterable = (Iterable) entry.getValue();
            ArrayList arrayList3 = new ArrayList();
            for (Pair pair2 : iterable) {
                DataStoreHelper.BookmarkedData bookmarkedData = DataStoreHelper.INSTANCE.getBookmarkedData((Integer) pair2.getFirst());
                SyncAPI.LibraryItem libraryItem = bookmarkedData != null ? bookmarkedData.toLibraryItem(String.valueOf(((Number) pair2.getFirst()).intValue())) : null;
                if (libraryItem != null) {
                    arrayList3.add(libraryItem);
                }
            }
            linkedHashMap3.put(key, arrayList3);
        }
        Integer numBoxInt2 = Boxing.boxInt(R.string.favorites_list_name);
        List<DataStoreHelper.FavoritesData> allFavorites = DataStoreHelper.INSTANCE.getAllFavorites();
        ArrayList arrayList4 = new ArrayList();
        Iterator<T> it2 = allFavorites.iterator();
        while (it2.hasNext()) {
            SyncAPI.LibraryItem libraryItem2 = ((DataStoreHelper.FavoritesData) it2.next()).toLibraryItem();
            if (libraryItem2 != null) {
                arrayList4.add(libraryItem2);
            }
        }
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(numBoxInt2, arrayList4));
        if (zIsLayout) {
            return MapsKt.plus(MapsKt.plus(mapPlus, linkedHashMap3), mapMapOf);
        }
        Integer numBoxInt3 = Boxing.boxInt(R.string.subscription_list_name);
        List<DataStoreHelper.SubscribedData> allSubscriptions = DataStoreHelper.INSTANCE.getAllSubscriptions();
        ArrayList arrayList5 = new ArrayList();
        Iterator<T> it3 = allSubscriptions.iterator();
        while (it3.hasNext()) {
            SyncAPI.LibraryItem libraryItem3 = ((DataStoreHelper.SubscribedData) it3.next()).toLibraryItem();
            if (libraryItem3 != null) {
                arrayList5.add(libraryItem3);
            }
        }
        return MapsKt.plus(MapsKt.plus(MapsKt.plus(mapPlus, linkedHashMap3), MapsKt.mapOf(TuplesKt.to(numBoxInt3, arrayList5))), mapMapOf);
    }
}

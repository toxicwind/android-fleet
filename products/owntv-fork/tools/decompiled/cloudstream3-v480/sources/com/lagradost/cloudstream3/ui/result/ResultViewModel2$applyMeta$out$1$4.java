package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.syncproviders.providers.Kitsu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$applyMeta$out$1$4", f = "ResultViewModel2.kt", i = {}, l = {1767}, m = "invokeSuspend", n = {}, nl = {1766}, s = {}, v = 2)
final class ResultViewModel2$applyMeta$out$1$4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadResponse $this_apply;
    final /* synthetic */ Ref.BooleanRef $updateEpisodes;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultViewModel2$applyMeta$out$1$4(LoadResponse loadResponse, Ref.BooleanRef booleanRef, Continuation<? super ResultViewModel2$applyMeta$out$1$4> continuation) {
        super(1, continuation);
        this.$this_apply = loadResponse;
        this.$updateEpisodes = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ResultViewModel2$applyMeta$out$1$4(this.$this_apply, this.$updateEpisodes, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((ResultViewModel2$applyMeta$out$1$4) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:84:0x01d0  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object episodesDetails;
        boolean z;
        List<Episode> mutableList;
        boolean z2;
        String str;
        Kitsu.KitsuResponse.Original original;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!(this.$this_apply instanceof AnimeLoadResponse)) {
                return Unit.INSTANCE;
            }
            this.label = 1;
            episodesDetails = Kitsu.INSTANCE.getEpisodesDetails(LoadResponse.INSTANCE.getMalId(this.$this_apply), LoadResponse.INSTANCE.getAniListId(this.$this_apply), false, this);
            if (episodesDetails == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            episodesDetails = obj;
        }
        Map map = (Map) episodesDetails;
        if (map == null || map.isEmpty()) {
            return Unit.INSTANCE;
        }
        Ref.BooleanRef booleanRef = this.$updateEpisodes;
        EnumEntries<DubStatus> entries = DubStatus.getEntries();
        LoadResponse loadResponse = this.$this_apply;
        int i3 = 10;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
        for (DubStatus dubStatus : entries) {
            AnimeLoadResponse animeLoadResponse = (AnimeLoadResponse) loadResponse;
            List<Episode> list = animeLoadResponse.getEpisodes().get(dubStatus);
            if (list != null) {
                List<Episode> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, i3));
                int i4 = i2;
                for (Object obj2 : list2) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Episode episode = (Episode) obj2;
                    Integer episode2 = episode.getEpisode();
                    if (episode2 == null) {
                        episode2 = Boxing.boxInt(i5);
                    }
                    episode.setEpisode(episode2);
                    arrayList2.add(episode);
                    i4 = i5;
                }
                List listSortedWith = CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$applyMeta$out$1$4$invokeSuspend$lambda$0$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Integer episode3 = ((Episode) t).getEpisode();
                        Integer numValueOf = Integer.valueOf(episode3 != null ? episode3.intValue() : 0);
                        Integer episode4 = ((Episode) t2).getEpisode();
                        return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(episode4 != null ? episode4.intValue() : 0));
                    }
                });
                if (listSortedWith != null) {
                    mutableList = CollectionsKt.toMutableList((Collection) listSortedWith);
                } else {
                    mutableList = null;
                }
            } else {
                mutableList = null;
            }
            List<Episode> list3 = mutableList;
            if (list3 == null || list3.isEmpty()) {
                z2 = false;
            } else {
                List<Episode> list4 = mutableList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, i3));
                Iterator<T> it = list4.iterator();
                while (it.hasNext()) {
                    Integer episode3 = ((Episode) it.next()).getEpisode();
                    Intrinsics.checkNotNull(episode3);
                    arrayList3.add(Boxing.boxInt(episode3.intValue()));
                }
                ArrayList arrayList4 = arrayList3;
                int i6 = i2;
                for (Map.Entry entry : map.entrySet()) {
                    int iIntValue = ((Number) entry.getKey()).intValue();
                    Kitsu.KitsuResponse.Node node = (Kitsu.KitsuResponse.Node) entry.getValue();
                    int iBinarySearch$default = CollectionsKt.binarySearch$default(arrayList4, Boxing.boxInt(iIntValue), 0, 0, 6, (Object) null);
                    Episode episode4 = (Episode) CollectionsKt.getOrNull(mutableList, iBinarySearch$default);
                    if (episode4 != null) {
                        i6++;
                        String description = episode4.getDescription();
                        if (description != null) {
                            str = description;
                        } else {
                            Kitsu.KitsuResponse.Description description2 = node.getDescription();
                            if (description2 != null) {
                                description = description2.getEn();
                                str = description;
                            } else {
                                str = null;
                            }
                        }
                        episode4.setDescription(str);
                        String name = episode4.getName();
                        if (name == null) {
                            Kitsu.KitsuResponse.Titles titles = node.getTitles();
                            name = titles != null ? titles.getCanonical() : null;
                        }
                        episode4.setName(name);
                        Integer episode5 = episode4.getEpisode();
                        if (episode5 == null && (episode5 = node.getNum()) == null) {
                            episode5 = (Integer) arrayList4.get(iBinarySearch$default);
                        }
                        episode4.setEpisode(episode5);
                        String posterUrl = episode4.getPosterUrl();
                        if (posterUrl == null) {
                            Kitsu.KitsuResponse.Thumbnail thumbnail = node.getThumbnail();
                            posterUrl = (thumbnail == null || (original = thumbnail.getOriginal()) == null) ? null : original.getUrl();
                        }
                        episode4.setPosterUrl(posterUrl);
                        Unit unit = Unit.INSTANCE;
                        mutableList.set(iBinarySearch$default, episode4);
                    }
                }
                animeLoadResponse.getEpisodes().put(dubStatus, mutableList);
                if (i6 > 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            arrayList.add(Boxing.boxBoolean(z2));
            i2 = 0;
            i3 = 10;
        }
        ArrayList arrayList5 = arrayList;
        if ((arrayList5 instanceof Collection) && arrayList5.isEmpty()) {
            z = false;
        } else {
            Iterator it2 = arrayList5.iterator();
            while (it2.hasNext()) {
                if (((Boolean) it2.next()).booleanValue()) {
                    z = true;
                }
            }
            z = false;
        }
        booleanRef.element = z;
        return Unit.INSTANCE;
    }
}

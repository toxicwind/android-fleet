package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.Tracker;
import com.lagradost.cloudstream3.TrackerType;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$applyMeta$out$1$2", f = "ResultViewModel2.kt", i = {1}, l = {1716, 1732}, m = "invokeSuspend", n = {"res"}, nl = {1732, 1735}, s = {"L$0"}, v = 2)
final class ResultViewModel2$applyMeta$out$1$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadResponse $this_apply;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultViewModel2$applyMeta$out$1$2(LoadResponse loadResponse, Continuation<? super ResultViewModel2$applyMeta$out$1$2> continuation) {
        super(1, continuation);
        this.$this_apply = loadResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ResultViewModel2$applyMeta$out$1$2(this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((ResultViewModel2$applyMeta$out$1$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x014b  */
    /* JADX WARN: Code duplicated, block: B:47:0x015f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0164  */
    /* JADX WARN: Code duplicated, block: B:51:0x0180  */
    /* JADX WARN: Code duplicated, block: B:61:0x01af  */
    /* JADX WARN: Code duplicated, block: B:63:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:66:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:71:0x01e7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:73:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:76:0x01ff A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x0201  */
    /* JADX WARN: Code duplicated, block: B:78:0x0206  */
    /* JADX WARN: Code duplicated, block: B:88:0x01d7 A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Tracker tracker;
        Pair[] pairArr;
        String cover;
        String strValueOf;
        int i;
        String aniId;
        LoadResponse loadResponse;
        int i2;
        LoadResponse loadResponse2;
        String posterUrl;
        String backgroundPosterUrl;
        String str;
        String str2;
        String str3;
        String str4;
        Integer malId;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 != 0) {
            if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                tracker = (Tracker) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            String str5 = (String) obj;
            pairArr = new Pair[3];
            String idPrefix = AccountManager.INSTANCE.getMalApi().getIdPrefix();
            cover = null;
            if (tracker != null || (malId = tracker.getMalId()) == null) {
                strValueOf = null;
            } else {
                strValueOf = String.valueOf(malId.intValue());
            }
            Pair pair = TuplesKt.to(idPrefix, strValueOf);
            pairArr[0] = pair;
            String idPrefix2 = AccountManager.INSTANCE.getAniListApi().getIdPrefix();
            if (tracker != null) {
                aniId = tracker.getAniId();
            } else {
                aniId = null;
            }
            pairArr[1] = TuplesKt.to(idPrefix2, aniId);
            pairArr[2] = TuplesKt.to(AccountManager.INSTANCE.getKitsuApi().getIdPrefix(), str5);
            loadResponse = this.$this_apply;
            for (i2 = 0; i2 < 3; i2++) {
                Pair pair2 = pairArr[i2];
                String str6 = (String) pair2.component1();
                str4 = (String) pair2.component2();
                String str7 = ((AnimeLoadResponse) loadResponse).getSyncData().get(str6);
                if (str4 == null && str7 != null && !Intrinsics.areEqual(str7, str4)) {
                    return Unit.INSTANCE;
                }
            }
            loadResponse2 = this.$this_apply;
            for (i = 0; i < 3; i++) {
                Pair pair3 = pairArr[i];
                str = (String) pair3.component1();
                str2 = (String) pair3.component2();
                if (str2 != null) {
                    AnimeLoadResponse animeLoadResponse = (AnimeLoadResponse) loadResponse2;
                    Map<String, String> syncData = animeLoadResponse.getSyncData();
                    str3 = animeLoadResponse.getSyncData().get(str);
                    if (str3 != null) {
                        str2 = str3;
                    }
                    syncData.put(str, str2);
                }
            }
            LoadResponse loadResponse3 = this.$this_apply;
            AnimeLoadResponse animeLoadResponse2 = (AnimeLoadResponse) loadResponse3;
            posterUrl = ((AnimeLoadResponse) loadResponse3).getPosterUrl();
            if (posterUrl == null) {
                if (tracker != null) {
                    posterUrl = tracker.getImage();
                } else {
                    posterUrl = null;
                }
            }
            animeLoadResponse2.setPosterUrl(posterUrl);
            LoadResponse loadResponse4 = this.$this_apply;
            AnimeLoadResponse animeLoadResponse3 = (AnimeLoadResponse) loadResponse4;
            backgroundPosterUrl = ((AnimeLoadResponse) loadResponse4).getBackgroundPosterUrl();
            if (backgroundPosterUrl == null) {
                cover = backgroundPosterUrl;
            } else if (tracker != null) {
                cover = tracker.getCover();
            }
            animeLoadResponse3.setBackgroundPosterUrl(cover);
            LoadResponse loadResponse5 = this.$this_apply;
            ((AnimeLoadResponse) loadResponse5).setLogoUrl(((AnimeLoadResponse) loadResponse5).getLogoUrl());
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        if (!(this.$this_apply instanceof AnimeLoadResponse)) {
            return Unit.INSTANCE;
        }
        if (LoadResponse.INSTANCE.getAniListId(this.$this_apply) != null && LoadResponse.INSTANCE.getKitsuId(this.$this_apply) != null && LoadResponse.INSTANCE.getMalId(this.$this_apply) != null) {
            return Unit.INSTANCE;
        }
        APIHolder aPIHolder = APIHolder.INSTANCE;
        List listListOfNotNull = CollectionsKt.listOfNotNull((Object[]) new String[]{((AnimeLoadResponse) this.$this_apply).getEngName(), ((AnimeLoadResponse) this.$this_apply).getName(), ((AnimeLoadResponse) this.$this_apply).getJapName()});
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : listListOfNotNull) {
            if (((String) obj2).length() > 2) {
                arrayList.add(obj2);
            }
        }
        List listDistinct = CollectionsKt.distinct(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDistinct, 10));
        Iterator it = listDistinct.iterator();
        while (it.hasNext()) {
            String lowerCase = ((String) it.next()).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            arrayList2.add(StringsKt.trim((CharSequence) new Regex("\\(?[ds]ub(bed)?\\)?(\\s|$)").replace(lowerCase, "")).toString());
        }
        this.label = 1;
        obj = aPIHolder.getTracker(arrayList2, TrackerType.INSTANCE.getTypes(((AnimeLoadResponse) this.$this_apply).getType()), ((AnimeLoadResponse) this.$this_apply).getYear(), this);
        if (obj != coroutine_suspended) {
        }
        return coroutine_suspended;
        Tracker tracker2 = (Tracker) obj;
        this.L$0 = tracker2;
        this.label = 2;
        Object animeIdByTitle = AccountManager.INSTANCE.getKitsuApi().getAnimeIdByTitle(((AnimeLoadResponse) this.$this_apply).getName(), this);
        if (animeIdByTitle != coroutine_suspended) {
            tracker = tracker2;
            obj = animeIdByTitle;
            String str8 = (String) obj;
            pairArr = new Pair[3];
            String idPrefix3 = AccountManager.INSTANCE.getMalApi().getIdPrefix();
            cover = null;
            if (tracker != null) {
                strValueOf = null;
            } else {
                strValueOf = null;
            }
            Pair pair4 = TuplesKt.to(idPrefix3, strValueOf);
            pairArr[0] = pair4;
            String idPrefix4 = AccountManager.INSTANCE.getAniListApi().getIdPrefix();
            if (tracker != null) {
                aniId = tracker.getAniId();
            } else {
                aniId = null;
            }
            pairArr[1] = TuplesKt.to(idPrefix4, aniId);
            pairArr[2] = TuplesKt.to(AccountManager.INSTANCE.getKitsuApi().getIdPrefix(), str8);
            loadResponse = this.$this_apply;
            while (i2 < 3) {
                Pair pair5 = pairArr[i2];
                String str9 = (String) pair5.component1();
                str4 = (String) pair5.component2();
                String str10 = ((AnimeLoadResponse) loadResponse).getSyncData().get(str9);
                if (str4 == null) {
                }
            }
            loadResponse2 = this.$this_apply;
            while (i < 3) {
                Pair pair6 = pairArr[i];
                str = (String) pair6.component1();
                str2 = (String) pair6.component2();
                if (str2 != null) {
                    AnimeLoadResponse animeLoadResponse4 = (AnimeLoadResponse) loadResponse2;
                    Map<String, String> syncData2 = animeLoadResponse4.getSyncData();
                    str3 = animeLoadResponse4.getSyncData().get(str);
                    if (str3 != null) {
                        str2 = str3;
                    }
                    syncData2.put(str, str2);
                }
            }
            LoadResponse loadResponse6 = this.$this_apply;
            AnimeLoadResponse animeLoadResponse5 = (AnimeLoadResponse) loadResponse6;
            posterUrl = ((AnimeLoadResponse) loadResponse6).getPosterUrl();
            if (posterUrl == null) {
                if (tracker != null) {
                    posterUrl = tracker.getImage();
                } else {
                    posterUrl = null;
                }
            }
            animeLoadResponse5.setPosterUrl(posterUrl);
            LoadResponse loadResponse7 = this.$this_apply;
            AnimeLoadResponse animeLoadResponse6 = (AnimeLoadResponse) loadResponse7;
            backgroundPosterUrl = ((AnimeLoadResponse) loadResponse7).getBackgroundPosterUrl();
            if (backgroundPosterUrl == null) {
                cover = backgroundPosterUrl;
            } else if (tracker != null) {
                cover = tracker.getCover();
            }
            animeLoadResponse6.setBackgroundPosterUrl(cover);
            LoadResponse loadResponse8 = this.$this_apply;
            ((AnimeLoadResponse) loadResponse8).setLogoUrl(((AnimeLoadResponse) loadResponse8).getLogoUrl());
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }
}

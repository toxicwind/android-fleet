package com.lagradost.cloudstream3.syncproviders;

import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.subtitles.SubtitleResource;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtitleRepo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0087@¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0087@¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "api", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;)V", "getApi", "()Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "resource", "Lkotlin/Result;", "Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;", "data", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "resource-gIAlu-s", "(Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "search-gIAlu-s", "(Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleRepo extends AuthRepo {
    public static final int CACHE_SIZE = 20;
    private static int resourceCacheIndex;
    private static int searchCacheIndex;
    private final SubtitleAPI api;
    private static final AtomicMutableList<Companion.SavedSearchResponse> searchCache = Coroutines.INSTANCE.atomicListOf(new Companion.SavedSearchResponse[0]);
    private static final AtomicMutableList<Companion.SavedResourceResponse> resourceCache = Coroutines.INSTANCE.atomicListOf(new Companion.SavedResourceResponse[0]);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleRepo(SubtitleAPI api) {
        super(api);
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthRepo
    public SubtitleAPI getApi() {
        return this.api;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: resource-gIAlu-s, reason: not valid java name */
    public final Object m8423resourcegIAlus(final AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super Result<SubtitleResource>> continuation) {
        SubtitleRepo$resource$1 subtitleRepo$resource$1;
        final SubtitleResource subtitleResource;
        int i;
        Object objFreshAuth;
        AbstractSubtitleEntities.SubtitleEntity subtitleEntity2;
        SubtitleAPI subtitleAPI;
        final AbstractSubtitleEntities.SubtitleEntity subtitleEntity3;
        if (continuation instanceof SubtitleRepo$resource$1) {
            subtitleRepo$resource$1 = (SubtitleRepo$resource$1) continuation;
            if ((subtitleRepo$resource$1.label & Integer.MIN_VALUE) != 0) {
                subtitleRepo$resource$1.label -= Integer.MIN_VALUE;
            } else {
                subtitleRepo$resource$1 = new SubtitleRepo$resource$1(this, continuation);
            }
        } else {
            subtitleRepo$resource$1 = new SubtitleRepo$resource$1(this, continuation);
        }
        Object obj = subtitleRepo$resource$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = subtitleRepo$resource$1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Result.Companion companion = Result.INSTANCE;
                SubtitleRepo subtitleRepo = this;
                subtitleResource = (SubtitleResource) resourceCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SubtitleRepo.resource_gIAlu_s$lambda$0$0(subtitleEntity);
                    }
                });
                if (subtitleResource == null) {
                    SubtitleAPI api = getApi();
                    subtitleRepo$resource$1.L$0 = subtitleEntity;
                    subtitleRepo$resource$1.L$1 = SpillingKt.nullOutSpilledVariable(this);
                    subtitleRepo$resource$1.L$2 = SpillingKt.nullOutSpilledVariable(subtitleResource);
                    subtitleRepo$resource$1.L$3 = api;
                    i = 0;
                    subtitleRepo$resource$1.I$0 = 0;
                    subtitleRepo$resource$1.label = 1;
                    objFreshAuth = freshAuth(subtitleRepo$resource$1);
                    if (objFreshAuth != coroutine_suspended) {
                        subtitleEntity2 = subtitleEntity;
                        subtitleAPI = api;
                    }
                    return coroutine_suspended;
                }
                return Result.m8936constructorimpl(subtitleResource);
            }
            if (i2 == 1) {
                int i3 = subtitleRepo$resource$1.I$0;
                subtitleAPI = (SubtitleAPI) subtitleRepo$resource$1.L$3;
                subtitleResource = (SubtitleResource) subtitleRepo$resource$1.L$2;
                SubtitleRepo subtitleRepo2 = (SubtitleRepo) subtitleRepo$resource$1.L$1;
                AbstractSubtitleEntities.SubtitleEntity subtitleEntity4 = (AbstractSubtitleEntities.SubtitleEntity) subtitleRepo$resource$1.L$0;
                ResultKt.throwOnFailure(obj);
                i = i3;
                this = subtitleRepo2;
                objFreshAuth = obj;
                subtitleEntity2 = subtitleEntity4;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = subtitleRepo$resource$1.I$0;
                subtitleEntity3 = (AbstractSubtitleEntities.SubtitleEntity) subtitleRepo$resource$1.L$0;
                ResultKt.throwOnFailure(obj);
            }
            subtitleResource = (SubtitleResource) obj;
            resourceCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SubtitleRepo.resource_gIAlu_s$lambda$0$1(subtitleResource, subtitleEntity3);
                }
            });
            return Result.m8936constructorimpl(subtitleResource);
            subtitleRepo$resource$1.L$0 = subtitleEntity2;
            subtitleRepo$resource$1.L$1 = SpillingKt.nullOutSpilledVariable(this);
            subtitleRepo$resource$1.L$2 = SpillingKt.nullOutSpilledVariable(subtitleResource);
            subtitleRepo$resource$1.L$3 = null;
            subtitleRepo$resource$1.I$0 = i;
            subtitleRepo$resource$1.label = 2;
            Object objResource = subtitleAPI.resource((AuthData) objFreshAuth, subtitleEntity2, subtitleRepo$resource$1);
            if (objResource != coroutine_suspended) {
                AbstractSubtitleEntities.SubtitleEntity subtitleEntity5 = subtitleEntity2;
                obj = objResource;
                subtitleEntity3 = subtitleEntity5;
                subtitleResource = (SubtitleResource) obj;
                resourceCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SubtitleRepo.resource_gIAlu_s$lambda$0$1(subtitleResource, subtitleEntity3);
                    }
                });
                return Result.m8936constructorimpl(subtitleResource);
            }
            return coroutine_suspended;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubtitleResource resource_gIAlu_s$lambda$0$0(AbstractSubtitleEntities.SubtitleEntity subtitleEntity) {
        for (Companion.SavedResourceResponse savedResourceResponse : resourceCache) {
            if (Intrinsics.areEqual(savedResourceResponse.getQuery(), subtitleEntity) && APIHolder.INSTANCE.getUnixTime() - savedResourceResponse.getUnixTime() < 1200) {
                return savedResourceResponse.getResponse();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object resource_gIAlu_s$lambda$0$1(SubtitleResource subtitleResource, AbstractSubtitleEntities.SubtitleEntity subtitleEntity) {
        Companion.SavedResourceResponse savedResourceResponse = new Companion.SavedResourceResponse(APIHolder.INSTANCE.getUnixTime(), subtitleResource, subtitleEntity);
        AtomicMutableList<Companion.SavedResourceResponse> atomicMutableList = resourceCache;
        if (atomicMutableList.size() > 20) {
            atomicMutableList.set(resourceCacheIndex, savedResourceResponse);
            resourceCacheIndex = (resourceCacheIndex + 1) % 20;
            return Unit.INSTANCE;
        }
        return Boolean.valueOf(atomicMutableList.add(savedResourceResponse));
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00b7 A[Catch: all -> 0x00e4, TryCatch #0 {all -> 0x00e4, blocks: (B:13:0x0037, B:31:0x00b3, B:33:0x00b7, B:34:0x00bb, B:36:0x00c5, B:38:0x00df, B:18:0x0057, B:27:0x0097, B:21:0x0063, B:23:0x0078), top: B:43:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c5 A[Catch: all -> 0x00e4, TryCatch #0 {all -> 0x00e4, blocks: (B:13:0x0037, B:31:0x00b3, B:33:0x00b7, B:34:0x00bb, B:36:0x00c5, B:38:0x00df, B:18:0x0057, B:27:0x0097, B:21:0x0063, B:23:0x0078), top: B:43:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: search-gIAlu-s, reason: not valid java name */
    public final Object m8424searchgIAlus(final AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super Result<? extends List<AbstractSubtitleEntities.SubtitleEntity>>> continuation) {
        SubtitleRepo$search$1 subtitleRepo$search$1;
        List list;
        int i;
        Object objFreshAuth;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch2;
        SubtitleAPI subtitleAPI;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch3;
        List listEmptyList;
        List list2;
        if (continuation instanceof SubtitleRepo$search$1) {
            subtitleRepo$search$1 = (SubtitleRepo$search$1) continuation;
            if ((subtitleRepo$search$1.label & Integer.MIN_VALUE) != 0) {
                subtitleRepo$search$1.label -= Integer.MIN_VALUE;
            } else {
                subtitleRepo$search$1 = new SubtitleRepo$search$1(this, continuation);
            }
        } else {
            subtitleRepo$search$1 = new SubtitleRepo$search$1(this, continuation);
        }
        Object obj = subtitleRepo$search$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = subtitleRepo$search$1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Result.Companion companion = Result.INSTANCE;
                SubtitleRepo subtitleRepo = this;
                list = (List) searchCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SubtitleRepo.search_gIAlu_s$lambda$0$0(this.f$0, subtitleSearch);
                    }
                });
                if (list == null) {
                    SubtitleAPI api = getApi();
                    subtitleRepo$search$1.L$0 = subtitleSearch;
                    subtitleRepo$search$1.L$1 = this;
                    subtitleRepo$search$1.L$2 = SpillingKt.nullOutSpilledVariable(list);
                    subtitleRepo$search$1.L$3 = api;
                    i = 0;
                    subtitleRepo$search$1.I$0 = 0;
                    subtitleRepo$search$1.label = 1;
                    objFreshAuth = freshAuth(subtitleRepo$search$1);
                    if (objFreshAuth != coroutine_suspended) {
                        subtitleSearch2 = subtitleSearch;
                        subtitleAPI = api;
                    }
                    return coroutine_suspended;
                }
                return Result.m8936constructorimpl(list);
            }
            if (i2 == 1) {
                int i3 = subtitleRepo$search$1.I$0;
                subtitleAPI = (SubtitleAPI) subtitleRepo$search$1.L$3;
                list = (List) subtitleRepo$search$1.L$2;
                SubtitleRepo subtitleRepo2 = (SubtitleRepo) subtitleRepo$search$1.L$1;
                AbstractSubtitleEntities.SubtitleSearch subtitleSearch4 = (AbstractSubtitleEntities.SubtitleSearch) subtitleRepo$search$1.L$0;
                ResultKt.throwOnFailure(obj);
                i = i3;
                this = subtitleRepo2;
                objFreshAuth = obj;
                subtitleSearch2 = subtitleSearch4;
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = subtitleRepo$search$1.I$0;
                this = (SubtitleRepo) subtitleRepo$search$1.L$1;
                AbstractSubtitleEntities.SubtitleSearch subtitleSearch5 = (AbstractSubtitleEntities.SubtitleSearch) subtitleRepo$search$1.L$0;
                ResultKt.throwOnFailure(obj);
                subtitleSearch3 = subtitleSearch5;
            }
            listEmptyList = (List) obj;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            list2 = listEmptyList;
            if (!list2.isEmpty()) {
                final Companion.SavedSearchResponse savedSearchResponse = new Companion.SavedSearchResponse(APIHolder.INSTANCE.getUnixTime(), list2, subtitleSearch3, this.getIdPrefix());
                searchCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SubtitleRepo.search_gIAlu_s$lambda$0$1(savedSearchResponse);
                    }
                });
            }
            list = list2;
            return Result.m8936constructorimpl(list);
            subtitleRepo$search$1.L$0 = subtitleSearch2;
            subtitleRepo$search$1.L$1 = this;
            subtitleRepo$search$1.L$2 = SpillingKt.nullOutSpilledVariable(list);
            subtitleRepo$search$1.L$3 = null;
            subtitleRepo$search$1.I$0 = i;
            subtitleRepo$search$1.label = 2;
            Object objSearch = subtitleAPI.search((AuthData) objFreshAuth, subtitleSearch2, subtitleRepo$search$1);
            if (objSearch != coroutine_suspended) {
                subtitleSearch3 = subtitleSearch2;
                obj = objSearch;
                listEmptyList = (List) obj;
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                list2 = listEmptyList;
                if (!list2.isEmpty()) {
                    final Companion.SavedSearchResponse savedSearchResponse2 = new Companion.SavedSearchResponse(APIHolder.INSTANCE.getUnixTime(), list2, subtitleSearch3, this.getIdPrefix());
                    searchCache.withLock(new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.SubtitleRepo$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SubtitleRepo.search_gIAlu_s$lambda$0$1(savedSearchResponse2);
                        }
                    });
                }
                list = list2;
                return Result.m8936constructorimpl(list);
            }
            return coroutine_suspended;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List search_gIAlu_s$lambda$0$0(SubtitleRepo subtitleRepo, AbstractSubtitleEntities.SubtitleSearch subtitleSearch) {
        for (Companion.SavedSearchResponse savedSearchResponse : searchCache) {
            if (Intrinsics.areEqual(savedSearchResponse.getIdPrefix(), subtitleRepo.getIdPrefix()) && Intrinsics.areEqual(savedSearchResponse.getQuery(), subtitleSearch) && APIHolder.INSTANCE.getUnixTime() - savedSearchResponse.getUnixTime() < 7200) {
                return savedSearchResponse.getResponse();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object search_gIAlu_s$lambda$0$1(Companion.SavedSearchResponse savedSearchResponse) {
        AtomicMutableList<Companion.SavedSearchResponse> atomicMutableList = searchCache;
        if (atomicMutableList.size() > 20) {
            atomicMutableList.set(searchCacheIndex, savedSearchResponse);
            searchCacheIndex = (searchCacheIndex + 1) % 20;
            return Unit.INSTANCE;
        }
        return Boolean.valueOf(atomicMutableList.add(savedSearchResponse));
    }
}

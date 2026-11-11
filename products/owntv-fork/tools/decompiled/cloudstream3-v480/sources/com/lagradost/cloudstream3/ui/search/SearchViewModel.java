package com.lagradost.cloudstream3.ui.search;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SearchResponseList;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.home.HomeViewModel;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: SearchViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010)\u001a\u00020\u001eJ2\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u000e2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0-2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/J\u0006\u00101\u001a\u00020\u001aJ\u000e\u00102\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u000eJ\u0006\u00103\u001a\u00020\u001eJ\u0018\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u00109J\u001c\u0010:\u001a\u00020\u00072\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070%H\u0002J2\u0010<\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u000e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0-2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\t8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000bR\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\t8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\t8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000bR\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e05X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_searchResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/ui/search/ExpandableSearchList;", "searchResponse", "Landroidx/lifecycle/LiveData;", "getSearchResponse", "()Landroidx/lifecycle/LiveData;", "_currentSearch", "", "", "currentSearch", "getCurrentSearch", "_currentHistory", "", "Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;", "currentHistory", "getCurrentHistory", "_searchSuggestions", "searchSuggestions", "getSearchSuggestions", "suggestionJob", "Lkotlinx/coroutines/Job;", "repos", "Lcom/lagradost/cloudstream3/ui/APIRepository;", "clearSearch", "", "lastQuery", "getLastQuery", "()Ljava/lang/String;", "setLastQuery", "(Ljava/lang/String;)V", "expandableSearches", "", "currentSearchIndex", "", "onGoingSearch", "reloadRepos", "searchAndCancel", SearchIntents.EXTRA_QUERY, "providersActive", "", "ignoreSettings", "", "isQuickSearch", "updateHistory", "fetchSuggestions", "clearSuggestions", "lock", "", "expandAndReturn", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bundleSearch", "lists", "search", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchViewModel extends ViewModel {
    private int currentSearchIndex;
    private String lastQuery;
    private Job onGoingSearch;
    private Job suggestionJob;
    private final MutableLiveData<Resource<ExpandableSearchList>> _searchResponse = new MutableLiveData<>();
    private final MutableLiveData<Map<String, ExpandableSearchList>> _currentSearch = new MutableLiveData<>();
    private final MutableLiveData<List<SearchHistoryItem>> _currentHistory = new MutableLiveData<>();
    private final MutableLiveData<List<String>> _searchSuggestions = new MutableLiveData<>();
    private List<APIRepository> repos = (List) APIHolder.INSTANCE.getApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SearchViewModel.repos$lambda$0();
        }
    });
    private final Map<String, ExpandableSearchList> expandableSearches = new LinkedHashMap();
    private final Set<String> lock = new LinkedHashSet();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$expandAndReturn$1, reason: invalid class name */
    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel", f = "SearchViewModel.kt", i = {0, 0, 0, 0, 0, 0}, l = {137}, m = "expandAndReturn", n = {"name", SearchIntents.EXTRA_QUERY, "repo", "current", "$i$a$-let-SearchViewModel$expandAndReturn$2", "nextPage"}, nl = {138}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SearchViewModel.this.expandAndReturn(null, this);
        }
    }

    public final LiveData<Resource<ExpandableSearchList>> getSearchResponse() {
        return this._searchResponse;
    }

    public final LiveData<Map<String, ExpandableSearchList>> getCurrentSearch() {
        return this._currentSearch;
    }

    public final LiveData<List<SearchHistoryItem>> getCurrentHistory() {
        return this._currentHistory;
    }

    public final LiveData<List<String>> getSearchSuggestions() {
        return this._searchSuggestions;
    }

    static final List repos$lambda$0() {
        AtomicList<MainAPI> apis = APIHolder.INSTANCE.getApis();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(apis, 10));
        Iterator<MainAPI> it = apis.iterator();
        while (it.hasNext()) {
            arrayList.add(new APIRepository(it.next()));
        }
        return arrayList;
    }

    public final void clearSearch() {
        this._searchResponse.postValue(new Resource.Success(new ExpandableSearchList(CollectionsKt.emptyList(), 0, false)));
        this._currentSearch.postValue(MapsKt.emptyMap());
        this.expandableSearches.clear();
    }

    public final String getLastQuery() {
        return this.lastQuery;
    }

    public final void setLastQuery(String str) {
        this.lastQuery = str;
    }

    static final List reloadRepos$lambda$0() {
        AtomicList<MainAPI> apis = APIHolder.INSTANCE.getApis();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(apis, 10));
        Iterator<MainAPI> it = apis.iterator();
        while (it.hasNext()) {
            arrayList.add(new APIRepository(it.next()));
        }
        return arrayList;
    }

    public final void reloadRepos() {
        this.repos = (List) APIHolder.INSTANCE.getApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SearchViewModel.reloadRepos$lambda$0();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void searchAndCancel$default(SearchViewModel searchViewModel, String str, Set set, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            set = SetsKt.emptySet();
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        searchViewModel.searchAndCancel(str, set, z, z2);
    }

    public final void searchAndCancel(String query, Set<String> providersActive, boolean ignoreSettings, boolean isQuickSearch) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(providersActive, "providersActive");
        this.currentSearchIndex++;
        Job job = this.onGoingSearch;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.onGoingSearch = search(query, providersActive, ignoreSettings, isQuickSearch);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1", f = "SearchViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09061 extends SuspendLambda implements Function3<CoroutineScope, SearchViewModel, Continuation<? super Unit>, Object> {
        int label;

        C09061(Continuation<? super C09061> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SearchViewModel searchViewModel, Continuation<? super Unit> continuation) {
            return SearchViewModel.this.new C09061(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:43:0x00e7  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List listEmptyList;
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<String> keys = CloudStreamApp.INSTANCE.getKeys(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/search_history");
            if (keys == null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str : keys) {
                    Context context = CloudStreamApp.INSTANCE.getContext();
                    Object obj2 = null;
                    if (context != null) {
                        try {
                            String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                            if (string != null) {
                                AppUtils appUtils = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SearchHistoryItem.INSTANCE.serializer());
                                } catch (Throwable th) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                }
                                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                                    try {
                                        Result.Companion companion3 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SearchHistoryItem.class), null, 2, null));
                                    } catch (Throwable th2) {
                                        Result.Companion companion4 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                                    }
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SearchHistoryItem>() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SearchHistoryItem>() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SearchHistoryItem>() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                    });
                                }
                                obj2 = objDecodeFromString;
                            }
                        } catch (Exception unused2) {
                        }
                    }
                    SearchHistoryItem searchHistoryItem = (SearchHistoryItem) obj2;
                    if (searchHistoryItem != null) {
                        arrayList.add(searchHistoryItem);
                    }
                }
                listEmptyList = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.search.SearchViewModel$updateHistory$1$invokeSuspend$$inlined$sortedByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((SearchHistoryItem) t2).getSearchedAt()), Long.valueOf(((SearchHistoryItem) t).getSearchedAt()));
                    }
                });
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
            }
            SearchViewModel.this._currentHistory.postValue(listEmptyList);
            return Unit.INSTANCE;
        }
    }

    public final Job updateHistory() {
        return Coroutines.INSTANCE.ioSafe(this, new C09061(null));
    }

    public final void fetchSuggestions(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        Job job = this.suggestionJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (StringsKt.isBlank(query) || query.length() < 2) {
            this._searchSuggestions.postValue(CollectionsKt.emptyList());
        } else {
            this.suggestionJob = Coroutines.INSTANCE.ioSafe(this, new C09041(query, this, null));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$fetchSuggestions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel$fetchSuggestions$1", f = "SearchViewModel.kt", i = {}, l = {107, 108}, m = "invokeSuspend", n = {}, nl = {108, 109}, s = {}, v = 2)
    static final class C09041 extends SuspendLambda implements Function3<CoroutineScope, SearchViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $query;
        int label;
        final /* synthetic */ SearchViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09041(String str, SearchViewModel searchViewModel, Continuation<? super C09041> continuation) {
            super(3, continuation);
            this.$query = str;
            this.this$0 = searchViewModel;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SearchViewModel searchViewModel, Continuation<? super Unit> continuation) {
            return new C09041(this.$query, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
        
            if (r6 == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r6)
                goto L3f
            L12:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L1a:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L2f
            L1e:
                kotlin.ResultKt.throwOnFailure(r6)
                r6 = r5
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r5.label = r3
                r3 = 300(0x12c, double:1.48E-321)
                java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r3, r6)
                if (r6 != r0) goto L2f
                goto L3e
            L2f:
                com.lagradost.cloudstream3.ui.search.SearchSuggestionApi r6 = com.lagradost.cloudstream3.ui.search.SearchSuggestionApi.INSTANCE
                java.lang.String r1 = r5.$query
                r3 = r5
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r5.label = r2
                java.lang.Object r6 = r6.getSuggestions(r1, r3)
                if (r6 != r0) goto L3f
            L3e:
                return r0
            L3f:
                java.util.List r6 = (java.util.List) r6
                com.lagradost.cloudstream3.ui.search.SearchViewModel r5 = r5.this$0
                androidx.lifecycle.MutableLiveData r5 = com.lagradost.cloudstream3.ui.search.SearchViewModel.access$get_searchSuggestions$p(r5)
                r5.postValue(r6)
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.search.SearchViewModel.C09041.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void clearSuggestions() {
        Job job = this.suggestionJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._searchSuggestions.postValue(CollectionsKt.emptyList());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:82:0x0216 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:83:0x0217  */
    public final Object expandAndReturn(String str, Continuation<? super HomeViewModel.ExpandableHomepageList> continuation) throws DebugException {
        AnonymousClass1 anonymousClass1;
        String str2;
        Object next;
        int currentPage;
        ExpandableSearchList expandableSearchList;
        String str3;
        ExpandableSearchList expandableSearchList2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.lock.contains(str) || (str2 = this.lastQuery) == null) {
                return null;
            }
            Iterator<T> it = this.repos.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((APIRepository) next).getName(), str));
            APIRepository aPIRepository = (APIRepository) next;
            if (aPIRepository == null) {
                return null;
            }
            this.lock.add(str);
            ExpandableSearchList expandableSearchList3 = this.expandableSearches.get(str);
            if (expandableSearchList3 != null) {
                if (!AppDebug.INSTANCE.isDebug() || expandableSearchList3.getHasNext()) {
                    currentPage = expandableSearchList3.getCurrentPage() + 1;
                    anonymousClass1.L$0 = str;
                    anonymousClass1.L$1 = str2;
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(aPIRepository);
                    anonymousClass1.L$3 = expandableSearchList3;
                    anonymousClass1.I$0 = 0;
                    anonymousClass1.I$1 = currentPage;
                    anonymousClass1.label = 1;
                    Object objSearch = aPIRepository.search(str2, currentPage, anonymousClass1);
                    if (objSearch == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    expandableSearchList = expandableSearchList3;
                    obj = objSearch;
                } else {
                    throw new DebugException("Expand called when not needed");
                }
            }
            str3 = str;
            this.lock.remove(str3);
            expandableSearchList2 = this.expandableSearches.get(str3);
            if (expandableSearchList2 == null) {
                return null;
            }
            return new HomeViewModel.ExpandableHomepageList(new HomePageList(str3, expandableSearchList2.getList(), false, 4, null), expandableSearchList2.getCurrentPage(), expandableSearchList2.getHasNext());
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = anonymousClass1.I$1;
        int i3 = anonymousClass1.I$0;
        expandableSearchList = (ExpandableSearchList) anonymousClass1.L$3;
        str2 = (String) anonymousClass1.L$1;
        String str4 = (String) anonymousClass1.L$0;
        ResultKt.throwOnFailure(obj);
        currentPage = i2;
        str = str4;
        Resource resource = (Resource) obj;
        if (resource instanceof Resource.Success) {
            SearchResponseList searchResponseList = (SearchResponseList) ((Resource.Success) resource).getValue();
            ExpandableSearchList expandableSearchList4 = this.expandableSearches.get(str);
            if (expandableSearchList4 != null) {
                expandableSearchList4.setHasNext(searchResponseList.getHasNext());
                expandableSearchList4.setCurrentPage(currentPage);
                if (AppDebug.INSTANCE.isDebug()) {
                    List<SearchResponse> items = searchResponseList.getItems();
                    if (!(items instanceof Collection) || !items.isEmpty()) {
                        loop0: for (SearchResponse searchResponse : items) {
                            List<SearchResponse> list = expandableSearchList4.getList();
                            if (!(list instanceof Collection) || !list.isEmpty()) {
                                Iterator<T> it2 = list.iterator();
                                while (it2.hasNext()) {
                                    if (Intrinsics.areEqual(((SearchResponse) it2.next()).getUrl(), searchResponse.getUrl())) {
                                        ArchComponentExtKt.logError(new DebugException("Expanded search contained an item that was previously already in the list.\nQuery = " + str2 + ", " + searchResponseList.getItems() + " = " + expandableSearchList4.getList()));
                                        break loop0;
                                    }
                                }
                            }
                        }
                    }
                }
                List listPlus = CollectionsKt.plus((Collection) expandableSearchList4.getList(), (Iterable) searchResponseList.getItems());
                HashSet hashSet = new HashSet();
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : listPlus) {
                    if (hashSet.add(((SearchResponse) obj2).getUrl())) {
                        arrayList.add(obj2);
                    }
                }
                expandableSearchList4.setList(arrayList);
            } else if (AppDebug.INSTANCE.isDebug()) {
                ArchComponentExtKt.logError(new DebugException("Expanded an item not in search load named " + str + ", current list is " + this.expandableSearches.keySet()));
            }
        } else {
            expandableSearchList.setHasNext(false);
        }
        this._searchResponse.postValue(new Resource.Success(bundleSearch(this.expandableSearches)));
        this._currentSearch.postValue(this.expandableSearches);
        str3 = str;
        this.lock.remove(str3);
        expandableSearchList2 = this.expandableSearches.get(str3);
        if (expandableSearchList2 == null) {
            return null;
        }
        return new HomeViewModel.ExpandableHomepageList(new HomePageList(str3, expandableSearchList2.getList(), false, 4, null), expandableSearchList2.getCurrentPage(), expandableSearchList2.getHasNext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExpandableSearchList bundleSearch(Map<String, ExpandableSearchList> lists) {
        if (lists.size() == 1) {
            return (ExpandableSearchList) CollectionsKt.first(lists.values());
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(lists.size());
        Iterator<Map.Entry<String, ExpandableSearchList>> it = lists.entrySet().iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getValue().getList());
        }
        ArrayList<List> arrayList3 = arrayList2;
        int i = 0;
        while (true) {
            int i2 = 0;
            for (List list : arrayList3) {
                if (list.size() > i) {
                    arrayList.add(list.get(i));
                    i2++;
                }
            }
            if (i2 == 0) {
                return new ExpandableSearchList(arrayList, 1, false);
            }
            i++;
        }
    }

    static /* synthetic */ Job search$default(SearchViewModel searchViewModel, String str, Set set, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return searchViewModel.search(str, set, z, z2);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1", f = "SearchViewModel.kt", i = {0}, l = {231}, m = "invokeSuspend", n = {"currentIndex"}, nl = {253}, s = {"I$0"}, v = 2)
    static final class C09051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $ignoreSettings;
        final /* synthetic */ boolean $isQuickSearch;
        final /* synthetic */ Set<String> $providersActive;
        final /* synthetic */ String $query;
        int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09051(String str, boolean z, boolean z2, Set<String> set, Continuation<? super C09051> continuation) {
            super(2, continuation);
            this.$query = str;
            this.$isQuickSearch = z;
            this.$ignoreSettings = z2;
            this.$providersActive = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchViewModel.this.new C09051(this.$query, this.$isQuickSearch, this.$ignoreSettings, this.$providersActive, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                int i2 = SearchViewModel.this.currentSearchIndex;
                if (this.$query.length() <= 1) {
                    SearchViewModel.this.clearSearch();
                    return Unit.INSTANCE;
                }
                if (!this.$isQuickSearch) {
                    String strValueOf = String.valueOf(this.$query.hashCode());
                    CloudStreamApp.INSTANCE.setKey(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/search_history", strValueOf, new SearchHistoryItem(System.currentTimeMillis(), this.$query, CollectionsKt.emptyList(), strValueOf));
                }
                SearchViewModel.this._searchResponse.postValue(new Resource.Loading(null, 1, null));
                SearchViewModel.this._currentSearch.postValue(MapsKt.emptyMap());
                SearchViewModel.this.expandableSearches.clear();
                SearchViewModel.this.setLastQuery(this.$query);
                this.I$0 = i2;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C01781(SearchViewModel.this, i2, this.$ignoreSettings, this.$providersActive, this.$isQuickSearch, this.$query, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SearchViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1$1", f = "SearchViewModel.kt", i = {}, l = {HebrewProber.FINAL_KAF}, m = "invokeSuspend", n = {}, nl = {HebrewProber.NORMAL_TSADI}, s = {}, v = 2)
        static final class C01781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $currentIndex;
            final /* synthetic */ boolean $ignoreSettings;
            final /* synthetic */ boolean $isQuickSearch;
            final /* synthetic */ Set<String> $providersActive;
            final /* synthetic */ String $query;
            int label;
            final /* synthetic */ SearchViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01781(SearchViewModel searchViewModel, int i, boolean z, Set<String> set, boolean z2, String str, Continuation<? super C01781> continuation) {
                super(2, continuation);
                this.this$0 = searchViewModel;
                this.$currentIndex = i;
                this.$ignoreSettings = z;
                this.$providersActive = set;
                this.$isQuickSearch = z2;
                this.$query = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01781(this.this$0, this.$currentIndex, this.$ignoreSettings, this.$providersActive, this.$isQuickSearch, this.$query, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List list = this.this$0.repos;
                    boolean z = this.$ignoreSettings;
                    Set<String> set = this.$providersActive;
                    boolean z2 = this.$isQuickSearch;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : list) {
                        APIRepository aPIRepository = (APIRepository) obj2;
                        if (z || set.isEmpty() || set.contains(aPIRepository.getName())) {
                            if (!z2 || aPIRepository.getHasQuickSearch()) {
                                arrayList.add(obj2);
                            }
                        }
                    }
                    this.label = 1;
                    if (ParCollectionsKt.amap(arrayList, new AnonymousClass2(this.$isQuickSearch, this.$query, this.this$0, this.$currentIndex, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (this.this$0.currentSearchIndex != this.$currentIndex) {
                    return Unit.INSTANCE;
                }
                this.this$0._currentSearch.postValue(this.this$0.expandableSearches);
                SearchViewModel searchViewModel = this.this$0;
                this.this$0._searchResponse.postValue(new Resource.Success(searchViewModel.bundleSearch(searchViewModel.expandableSearches)));
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: SearchViewModel.kt */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Lcom/lagradost/cloudstream3/ui/APIRepository;"}, k = 3, mv = {2, 3, 0}, xi = 48)
            @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchViewModel$search$1$1$2", f = "SearchViewModel.kt", i = {0, 1}, l = {HebrewProber.NORMAL_KAF, HebrewProber.NORMAL_KAF}, m = "invokeSuspend", n = {CmcdData.OBJECT_TYPE_AUDIO_ONLY, CmcdData.OBJECT_TYPE_AUDIO_ONLY}, nl = {HebrewProber.NORMAL_KAF, 236}, s = {"L$0", "L$0"}, v = 2)
            static final class AnonymousClass2 extends SuspendLambda implements Function2<APIRepository, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $currentIndex;
                final /* synthetic */ boolean $isQuickSearch;
                final /* synthetic */ String $query;
                /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ SearchViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(boolean z, String str, SearchViewModel searchViewModel, int i, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.$isQuickSearch = z;
                    this.$query = str;
                    this.this$0 = searchViewModel;
                    this.$currentIndex = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$isQuickSearch, this.$query, this.this$0, this.$currentIndex, continuation);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(APIRepository aPIRepository, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(aPIRepository, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
                
                    if (r6 == r1) goto L18;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
                
                    if (r6 == r1) goto L18;
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                    /*
                        r5 = this;
                        java.lang.Object r0 = r5.L$0
                        com.lagradost.cloudstream3.ui.APIRepository r0 = (com.lagradost.cloudstream3.ui.APIRepository) r0
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r5.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L22
                        if (r2 == r4) goto L1e
                        if (r2 != r3) goto L16
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L16:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L1e:
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L39
                    L22:
                        kotlin.ResultKt.throwOnFailure(r6)
                        boolean r6 = r5.$isQuickSearch
                        java.lang.String r2 = r5.$query
                        if (r6 == 0) goto L3c
                        r6 = r5
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                        r5.L$0 = r0
                        r5.label = r4
                        java.lang.Object r6 = r0.quickSearch(r2, r6)
                        if (r6 != r1) goto L39
                        goto L49
                    L39:
                        com.lagradost.cloudstream3.mvvm.Resource r6 = (com.lagradost.cloudstream3.mvvm.Resource) r6
                        goto L4c
                    L3c:
                        r6 = r5
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                        r5.L$0 = r0
                        r5.label = r3
                        java.lang.Object r6 = r0.search(r2, r4, r6)
                        if (r6 != r1) goto L4a
                    L49:
                        return r1
                    L4a:
                        com.lagradost.cloudstream3.mvvm.Resource r6 = (com.lagradost.cloudstream3.mvvm.Resource) r6
                    L4c:
                        com.lagradost.cloudstream3.ui.search.SearchViewModel r1 = r5.this$0
                        int r1 = com.lagradost.cloudstream3.ui.search.SearchViewModel.access$getCurrentSearchIndex$p(r1)
                        int r2 = r5.$currentIndex
                        if (r1 == r2) goto L59
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    L59:
                        boolean r1 = r6 instanceof com.lagradost.cloudstream3.mvvm.Resource.Success
                        if (r1 == 0) goto L7f
                        com.lagradost.cloudstream3.mvvm.Resource$Success r6 = (com.lagradost.cloudstream3.mvvm.Resource.Success) r6
                        java.lang.Object r6 = r6.getValue()
                        com.lagradost.cloudstream3.SearchResponseList r6 = (com.lagradost.cloudstream3.SearchResponseList) r6
                        com.lagradost.cloudstream3.ui.search.SearchViewModel r1 = r5.this$0
                        java.util.Map r1 = com.lagradost.cloudstream3.ui.search.SearchViewModel.access$getExpandableSearches$p(r1)
                        java.lang.String r0 = r0.getName()
                        com.lagradost.cloudstream3.ui.search.ExpandableSearchList r2 = new com.lagradost.cloudstream3.ui.search.ExpandableSearchList
                        java.util.List r3 = r6.getItems()
                        boolean r6 = r6.getHasNext()
                        r2.<init>(r3, r4, r6)
                        r1.put(r0, r2)
                    L7f:
                        com.lagradost.cloudstream3.ui.search.SearchViewModel r6 = r5.this$0
                        androidx.lifecycle.MutableLiveData r6 = com.lagradost.cloudstream3.ui.search.SearchViewModel.access$get_currentSearch$p(r6)
                        com.lagradost.cloudstream3.ui.search.SearchViewModel r5 = r5.this$0
                        java.util.Map r5 = com.lagradost.cloudstream3.ui.search.SearchViewModel.access$getExpandableSearches$p(r5)
                        r6.postValue(r5)
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.search.SearchViewModel.C09051.C01781.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        }
    }

    private final Job search(String query, Set<String> providersActive, boolean ignoreSettings, boolean isQuickSearch) {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C09051(query, isQuickSearch, ignoreSettings, providersActive, null), 3, null);
    }
}

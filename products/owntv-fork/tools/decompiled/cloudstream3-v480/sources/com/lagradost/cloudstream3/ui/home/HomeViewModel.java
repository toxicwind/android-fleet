package com.lagradost.cloudstream3.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.compose.runtime.ComposerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchHelper;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 q2\u00020\u0001:\u0002qrB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\b\u0010 \u001a\u00020\u000bH\u0002J\b\u00107\u001a\u000208H\u0002J\u0016\u00109\u001a\u0002082\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#J\u0010\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020?H\u0002J\u0018\u0010J\u001a\u0004\u0018\u00010B2\u0006\u0010K\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010LJ\u000e\u0010M\u001a\u0002082\u0006\u0010K\u001a\u00020\u0012J@\u0010N\u001a\u00020O2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020.0Q2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u0012022\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010T\u001a\u00020OH\u0082@¢\u0006\u0002\u0010UJ\u0006\u0010W\u001a\u00020\u0005J\u0010\u0010X\u001a\u0002082\u0006\u0010>\u001a\u00020?H\u0002J\u000e\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020[J\"\u0010^\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010B2\u0010\b\u0002\u0010`\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010]J\u0010\u0010a\u001a\u00020\u00052\u0006\u0010b\u001a\u00020(H\u0002J\u0010\u0010c\u001a\u00020\u00052\u0006\u0010d\u001a\u00020(H\u0002J\u0012\u0010e\u001a\u00020\u00052\b\b\u0002\u0010b\u001a\u00020(H\u0002J\u0012\u0010f\u001a\u00020\u00052\b\b\u0002\u0010b\u001a\u00020(H\u0002J\u0012\u0010g\u001a\u00020\u00052\b\b\u0002\u0010b\u001a\u00020(H\u0002J\b\u0010h\u001a\u00020\u0005H\u0014J\u000e\u0010i\u001a\u00020\u00052\u0006\u0010j\u001a\u00020\u0012J\u000e\u0010k\u001a\u00020\u00052\u0006\u0010l\u001a\u00020\u0012J\u0006\u00109\u001a\u00020\u0005J\u0006\u0010m\u001a\u00020\u0005J\u000e\u0010Y\u001a\u00020\u00052\u0006\u0010X\u001a\u00020nJ$\u0010=\u001a\u0002082\b\u0010o\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010d\u001a\u00020(2\b\b\u0002\u0010p\u001a\u00020(R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010!\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010%\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"0\u0014¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R&\u0010'\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\"0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010)\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\"0\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0016R\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010,\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\b0\"0-0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020.00X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u001202X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0014¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0016R/\u00105\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\b0\"0-0\u0014¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0016R\u0010\u0010;\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020B0AX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010C\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020B0D0-0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010E\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020B0D0-0\u0014¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0016R\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001202¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0010\u0010V\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\\\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020B\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010]\u0018\u00010\"0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010^\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020B\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010]\u0018\u00010\"0\u0014¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0016¨\u0006s"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "deleteResumeWatching", "", "deleteBookmarks", "list", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "repo", "Lcom/lagradost/cloudstream3/ui/APIRepository;", "getRepo", "()Lcom/lagradost/cloudstream3/ui/APIRepository;", "setRepo", "(Lcom/lagradost/cloudstream3/ui/APIRepository;)V", "_apiName", "Landroidx/lifecycle/MutableLiveData;", "", "apiName", "Landroidx/lifecycle/LiveData;", "getApiName", "()Landroidx/lifecycle/LiveData;", "_currentAccount", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "currentAccount", "getCurrentAccount", "()Landroidx/lifecycle/MutableLiveData;", "_randomItems", "randomItems", "getRandomItems", "currentShuffledList", "autoloadRepo", "_availableWatchStatusTypes", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/ui/WatchType;", "availableWatchStatusTypes", "getAvailableWatchStatusTypes", "_bookmarks", "", "bookmarks", "getBookmarks", "_resumeWatching", "_preview", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/LoadResponse;", "previewResponses", "Ljava/util/concurrent/CopyOnWriteArrayList;", "previewResponsesAdded", "", "resumeWatching", "getResumeWatching", "preview", "getPreview", "loadResumeWatching", "Lkotlinx/coroutines/Job;", "loadStoredData", "preferredWatchStatus", "onGoingLoad", "isCurrentlyLoadingName", "loadAndCancel", "api", "Lcom/lagradost/cloudstream3/MainAPI;", "expandable", "", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "_page", "", "page", "getPage", "lock", "getLock", "()Ljava/util/Set;", "expandAndReturn", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expand", "updatePreviewResponses", "", "current", "", "alreadyAdded", "shuffledList", ContentDisposition.Parameters.Size, "(Ljava/util/List;Ljava/util/Set;Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addJob", "loadMoreHomeScrollResponses", "load", "click", "callback", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "_popup", "Lkotlin/Function0;", "popup", "getPopup", "deleteCallback", "bookmarksUpdated", "unused", "afterPluginsLoaded", "forceReload", "afterMainPluginsLoaded", "reloadHome", "reloadAccount", "onCleared", "queryTextSubmit", SearchIntents.EXTRA_QUERY, "queryTextChange", "newText", "reloadStored", "Lcom/lagradost/cloudstream3/ui/home/LoadClickCallback;", "preferredApiName", "fromUI", "Companion", "ExpandableHomepageList", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeViewModel extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MutableLiveData<String> _apiName;
    private final MutableLiveData<Pair<Set<WatchType>, Set<WatchType>>> _availableWatchStatusTypes;
    private final MutableLiveData<Pair<Boolean, List<SearchResponse>>> _bookmarks;
    private final MutableLiveData<DataStoreHelper.Account> _currentAccount;
    private final MutableLiveData<Resource<Map<String, ExpandableHomepageList>>> _page;
    private final MutableLiveData<Pair<ExpandableHomepageList, Function0<Unit>>> _popup;
    private final MutableLiveData<Resource<Pair<Boolean, List<LoadResponse>>>> _preview;
    private final MutableLiveData<List<SearchResponse>> _randomItems;
    private final MutableLiveData<List<SearchResponse>> _resumeWatching;
    private Job addJob;
    private final LiveData<String> apiName;
    private final LiveData<Pair<Set<WatchType>, Set<WatchType>>> availableWatchStatusTypes;
    private final LiveData<Pair<Boolean, List<SearchResponse>>> bookmarks;
    private final MutableLiveData<DataStoreHelper.Account> currentAccount;
    private List<? extends SearchResponse> currentShuffledList;
    private final Map<String, ExpandableHomepageList> expandable;
    private String isCurrentlyLoadingName;
    private final Set<String> lock;
    private Job onGoingLoad;
    private final LiveData<Resource<Map<String, ExpandableHomepageList>>> page;
    private final LiveData<Pair<ExpandableHomepageList, Function0<Unit>>> popup;
    private final LiveData<Resource<Pair<Boolean, List<LoadResponse>>>> preview;
    private final CopyOnWriteArrayList<LoadResponse> previewResponses;
    private final Set<String> previewResponsesAdded;
    private final LiveData<List<SearchResponse>> randomItems;
    private APIRepository repo;
    private final LiveData<List<SearchResponse>> resumeWatching;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$expandAndReturn$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel", f = "HomeViewModel.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {239, 247}, m = "expandAndReturn", n = {"name", "$this$expandAndReturn_u24lambda_u240", "$i$a$-apply-HomeViewModel$expandAndReturn$2", "name", "$this$expandAndReturn_u24lambda_u240", "current", "$i$a$-apply-HomeViewModel$expandAndReturn$2", "$i$a$-let-HomeViewModel$expandAndReturn$2$1", "nextPage"}, nl = {SJISContextAnalysis.HIRAGANA_LOWBYTE_END, 248}, s = {"L$0", "L$2", "I$0", "L$0", "L$2", "L$3", "I$0", "I$1", "I$2"}, v = 2)
    static final class C08421 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08421(Continuation<? super C08421> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeViewModel.this.expandAndReturn(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$updatePreviewResponses$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel", f = "HomeViewModel.kt", i = {0, 0, 0, 0, 0, 0}, l = {304}, m = "updatePreviewResponses", n = {"current", "alreadyAdded", "shuffledList", "addItems", ContentDisposition.Parameters.Size, PeertubeParsingHelper.COUNT_KEY}, nl = {306}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 2)
    static final class C08531 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08531(Continuation<? super C08531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeViewModel.this.updatePreviewResponses(null, null, null, 0, this);
        }
    }

    public final void queryTextChange(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
    }

    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$Companion;", "", "<init>", "()V", "getResumeWatching", "", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$ResumeWatchingResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0072, code lost:
        
            if (r5 == r6) goto L21;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object getResumeWatching(kotlin.coroutines.Continuation<? super java.util.List<com.lagradost.cloudstream3.utils.DataStoreHelper.ResumeWatchingResult>> r6) {
            /*
                r5 = this;
                boolean r0 = r6 instanceof com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$1
                if (r0 == 0) goto L14
                r0 = r6
                com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$1 r0 = (com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r5 = r0.label
                int r5 = r5 - r2
                r0.label = r5
                goto L19
            L14:
                com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$1 r0 = new com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$1
                r0.<init>(r5, r6)
            L19:
                java.lang.Object r5 = r0.result
                java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r0.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L3e
                if (r1 == r4) goto L3a
                if (r1 != r3) goto L32
                java.lang.Object r6 = r0.L$0
                java.util.List r6 = (java.util.List) r6
                kotlin.ResultKt.throwOnFailure(r5)
                goto L75
            L32:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L3a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L57
            L3e:
                kotlin.ResultKt.throwOnFailure(r5)
                kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
                com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatching$1 r1 = new com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatching$1
                r1.<init>(r2)
                kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
                r0.label = r4
                java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r1, r0)
                if (r5 != r6) goto L57
                goto L74
            L57:
                java.util.List r5 = (java.util.List) r5
                kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
                com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1 r4 = new com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1
                r4.<init>(r5, r2)
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
                r0.L$0 = r5
                r0.label = r3
                java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r1, r4, r0)
                if (r5 != r6) goto L75
            L74:
                return r6
            L75:
                java.util.List r5 = (java.util.List) r5
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.home.HomeViewModel.Companion.getResumeWatching(kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public HomeViewModel() {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        this._apiName = mutableLiveData;
        this.apiName = mutableLiveData;
        MutableLiveData<DataStoreHelper.Account> mutableLiveData2 = new MutableLiveData<>();
        this._currentAccount = mutableLiveData2;
        this.currentAccount = mutableLiveData2;
        MutableLiveData<List<SearchResponse>> mutableLiveData3 = new MutableLiveData<>(null);
        this._randomItems = mutableLiveData3;
        this.randomItems = mutableLiveData3;
        this.currentShuffledList = CollectionsKt.emptyList();
        MutableLiveData<Pair<Set<WatchType>, Set<WatchType>>> mutableLiveData4 = new MutableLiveData<>();
        this._availableWatchStatusTypes = mutableLiveData4;
        this.availableWatchStatusTypes = mutableLiveData4;
        MutableLiveData<Pair<Boolean, List<SearchResponse>>> mutableLiveData5 = new MutableLiveData<>();
        this._bookmarks = mutableLiveData5;
        this.bookmarks = mutableLiveData5;
        MutableLiveData<List<SearchResponse>> mutableLiveData6 = new MutableLiveData<>();
        this._resumeWatching = mutableLiveData6;
        MutableLiveData<Resource<Pair<Boolean, List<LoadResponse>>>> mutableLiveData7 = new MutableLiveData<>();
        this._preview = mutableLiveData7;
        this.previewResponses = new CopyOnWriteArrayList<>();
        this.previewResponsesAdded = new LinkedHashSet();
        this.resumeWatching = mutableLiveData6;
        this.preview = mutableLiveData7;
        this.expandable = new LinkedHashMap();
        MutableLiveData<Resource<Map<String, ExpandableHomepageList>>> mutableLiveData8 = new MutableLiveData<>(new Resource.Loading(null, 1, null));
        this._page = mutableLiveData8;
        this.page = mutableLiveData8;
        this.lock = new LinkedHashSet();
        MutableLiveData<Pair<ExpandableHomepageList, Function0<Unit>>> mutableLiveData9 = new MutableLiveData<>(null);
        this._popup = mutableLiveData9;
        this.popup = mutableLiveData9;
        MainActivity.INSTANCE.getBookmarksUpdatedEvent().plusAssign(new AnonymousClass1(this));
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().plusAssign(new AnonymousClass2(this));
        MainActivity.INSTANCE.getMainPluginsLoadedEvent().plusAssign(new AnonymousClass3(this));
        MainActivity.INSTANCE.getReloadHomeEvent().plusAssign(new AnonymousClass4(this));
        MainActivity.INSTANCE.getReloadAccountEvent().plusAssign(new AnonymousClass5(this));
    }

    public final void deleteResumeWatching() {
        DataStoreHelper.INSTANCE.deleteAllResumeStateIds();
        loadResumeWatching();
    }

    public final void deleteBookmarks(List<? extends SearchResponse> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            DataStoreHelper.INSTANCE.deleteBookmarkedData(((SearchResponse) it.next()).getId());
        }
        loadStoredData();
    }

    public final APIRepository getRepo() {
        return this.repo;
    }

    public final void setRepo(APIRepository aPIRepository) {
        this.repo = aPIRepository;
    }

    public final LiveData<String> getApiName() {
        return this.apiName;
    }

    public final MutableLiveData<DataStoreHelper.Account> getCurrentAccount() {
        return this.currentAccount;
    }

    public final LiveData<List<SearchResponse>> getRandomItems() {
        return this.randomItems;
    }

    private final APIRepository autoloadRepo() {
        return new APIRepository((MainAPI) APIHolder.INSTANCE.getApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeViewModel.autoloadRepo$lambda$0();
            }
        }));
    }

    static final MainAPI autoloadRepo$lambda$0() {
        for (MainAPI mainAPI : APIHolder.INSTANCE.getApis()) {
            if (mainAPI.getHasMainPage()) {
                return mainAPI;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final LiveData<Pair<Set<WatchType>, Set<WatchType>>> getAvailableWatchStatusTypes() {
        return this.availableWatchStatusTypes;
    }

    public final LiveData<Pair<Boolean, List<SearchResponse>>> getBookmarks() {
        return this.bookmarks;
    }

    public final LiveData<List<SearchResponse>> getResumeWatching() {
        return this.resumeWatching;
    }

    public final LiveData<Resource<Pair<Boolean, List<LoadResponse>>>> getPreview() {
        return this.preview;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadResumeWatching$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadResumeWatching$1", f = "HomeViewModel.kt", i = {0}, l = {155}, m = "invokeSuspend", n = {"$this$launchSafe"}, nl = {156}, s = {"L$0"}, v = 2)
    static final class C08461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C08461(Continuation<? super C08461> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08461 c08461 = HomeViewModel.this.new C08461(continuation);
            c08461.L$0 = obj;
            return c08461;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = HomeViewModel.INSTANCE.getResumeWatching(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List list = (List) obj;
            if (Globals.INSTANCE.isLayout(2) && list != null && Build.VERSION.SDK_INT >= 26) {
                Coroutines.INSTANCE.ioSafe(coroutineScope, new C01711(list, null));
            }
            if (list != null) {
                HomeViewModel.this._resumeWatching.postValue(list);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadResumeWatching$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: HomeViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadResumeWatching$1$1", f = "HomeViewModel.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, nl = {160}, s = {}, v = 2)
        static final class C01711 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<DataStoreHelper.ResumeWatchingResult> $resumeWatchingResult;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01711(List<DataStoreHelper.ResumeWatchingResult> list, Continuation<? super C01711> continuation) {
                super(3, continuation);
                this.$resumeWatchingResult = list;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Unit> continuation) {
                return new C01711(this.$resumeWatchingResult, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Activity activity = CommonActivity.INSTANCE.getActivity();
                    if (activity != null) {
                        this.label = 1;
                        if (AppContextUtils.INSTANCE.addProgramsToContinueWatching(activity, this.$resumeWatchingResult, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
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
    }

    private final Job loadResumeWatching() {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08461(null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadStoredData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadStoredData$1", f = "HomeViewModel.kt", i = {1, 1, 1, 1}, l = {168, ComposerKt.providerMapsKey}, m = "invokeSuspend", n = {"watchStatusIds", "currentWatchTypes", "watchPrefNotNull", "length"}, nl = {172, 209}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C08471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Set<WatchType> $preferredWatchStatus;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08471(Set<? extends WatchType> set, Continuation<? super C08471> continuation) {
            super(2, continuation);
            this.$preferredWatchStatus = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C08471(this.$preferredWatchStatus, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x0178, code lost:
        
            if (r12 == r0) goto L39;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 405
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.home.HomeViewModel.C08471.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Job loadStoredData(Set<? extends WatchType> preferredWatchStatus) {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08471(preferredWatchStatus, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadAndCancel(MainAPI api) {
        Job job = this.onGoingLoad;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.isCurrentlyLoadingName = api.getName();
        this.onGoingLoad = load(api);
    }

    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J'\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "", "list", "Lcom/lagradost/cloudstream3/HomePageList;", "currentPage", "", "hasNext", "", "<init>", "(Lcom/lagradost/cloudstream3/HomePageList;IZ)V", "getList", "()Lcom/lagradost/cloudstream3/HomePageList;", "setList", "(Lcom/lagradost/cloudstream3/HomePageList;)V", "getCurrentPage", "()I", "setCurrentPage", "(I)V", "getHasNext", "()Z", "setHasNext", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ExpandableHomepageList {
        private int currentPage;
        private boolean hasNext;
        private HomePageList list;

        public static /* synthetic */ ExpandableHomepageList copy$default(ExpandableHomepageList expandableHomepageList, HomePageList homePageList, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                homePageList = expandableHomepageList.list;
            }
            if ((i2 & 2) != 0) {
                i = expandableHomepageList.currentPage;
            }
            if ((i2 & 4) != 0) {
                z = expandableHomepageList.hasNext;
            }
            return expandableHomepageList.copy(homePageList, i, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final HomePageList getList() {
            return this.list;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCurrentPage() {
            return this.currentPage;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getHasNext() {
            return this.hasNext;
        }

        public final ExpandableHomepageList copy(HomePageList list, int currentPage, boolean hasNext) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new ExpandableHomepageList(list, currentPage, hasNext);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExpandableHomepageList)) {
                return false;
            }
            ExpandableHomepageList expandableHomepageList = (ExpandableHomepageList) other;
            return Intrinsics.areEqual(this.list, expandableHomepageList.list) && this.currentPage == expandableHomepageList.currentPage && this.hasNext == expandableHomepageList.hasNext;
        }

        public int hashCode() {
            return (((this.list.hashCode() * 31) + this.currentPage) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.hasNext);
        }

        public String toString() {
            return "ExpandableHomepageList(list=" + this.list + ", currentPage=" + this.currentPage + ", hasNext=" + this.hasNext + ')';
        }

        public ExpandableHomepageList(HomePageList list, int i, boolean z) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this.currentPage = i;
            this.hasNext = z;
        }

        public final HomePageList getList() {
            return this.list;
        }

        public final void setList(HomePageList homePageList) {
            Intrinsics.checkNotNullParameter(homePageList, "<set-?>");
            this.list = homePageList;
        }

        public final int getCurrentPage() {
            return this.currentPage;
        }

        public final void setCurrentPage(int i) {
            this.currentPage = i;
        }

        public final boolean getHasNext() {
            return this.hasNext;
        }

        public final void setHasNext(boolean z) {
            this.hasNext = z;
        }
    }

    public final LiveData<Resource<Map<String, ExpandableHomepageList>>> getPage() {
        return this.page;
    }

    public final Set<String> getLock() {
        return this.lock;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0126 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:103:0x018f A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:105:0x01ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:? A[LOOP:3: B:73:0x0193->B:109:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x0239 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x0222 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:52:0x0116  */
    /* JADX WARN: Code duplicated, block: B:55:0x012c  */
    /* JADX WARN: Code duplicated, block: B:57:0x0140  */
    /* JADX WARN: Code duplicated, block: B:59:0x0152  */
    /* JADX WARN: Code duplicated, block: B:64:0x0167  */
    /* JADX WARN: Code duplicated, block: B:67:0x0171  */
    /* JADX WARN: Code duplicated, block: B:69:0x0185  */
    /* JADX WARN: Code duplicated, block: B:75:0x0199  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:81:0x0228  */
    /* JADX WARN: Code duplicated, block: B:88:0x0249  */
    /* JADX WARN: Code duplicated, block: B:90:0x0273  */
    /* JADX WARN: Code duplicated, block: B:97:0x0241 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:0x0241 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:88:0x0249, please report this as an issue */
    public final Object expandAndReturn(String str, Continuation<? super ExpandableHomepageList> continuation) throws DebugException {
        C08421 c08421;
        APIRepository aPIRepository;
        APIRepository aPIRepository2;
        int i;
        String str2;
        int i2;
        ExpandableHomepageList expandableHomepageList;
        Resource resource;
        String name;
        ExpandableHomepageList expandableHomepageList2;
        HashSet hashSet;
        ArrayList arrayList;
        List<SearchResponse> list;
        List<SearchResponse> list2;
        Iterator<T> it;
        if (continuation instanceof C08421) {
            c08421 = (C08421) continuation;
            if ((c08421.label & Integer.MIN_VALUE) != 0) {
                c08421.label -= Integer.MIN_VALUE;
            } else {
                c08421 = new C08421(continuation);
            }
        } else {
            c08421 = new C08421(continuation);
        }
        Object mainPage = c08421.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c08421.label;
        if (i3 != 0) {
            if (i3 == 1) {
                int i4 = c08421.I$0;
                aPIRepository = (APIRepository) c08421.L$2;
                aPIRepository2 = (APIRepository) c08421.L$1;
                String str3 = (String) c08421.L$0;
                ResultKt.throwOnFailure(mainPage);
                i = i4;
                str = str3;
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i2 = c08421.I$2;
                int i5 = c08421.I$1;
                int i6 = c08421.I$0;
                expandableHomepageList = (ExpandableHomepageList) c08421.L$3;
                str2 = (String) c08421.L$0;
                ResultKt.throwOnFailure(mainPage);
            }
            resource = (Resource) mainPage;
            if (resource instanceof Resource.Success) {
                expandableHomepageList.setHasNext(false);
            } else {
                for (HomePageResponse homePageResponse : CollectionsKt.filterNotNull((Iterable) ((Resource.Success) resource).getValue())) {
                    for (HomePageList homePageList : homePageResponse.getItems()) {
                        name = homePageList.getName();
                        expandableHomepageList2 = this.expandable.get(name);
                        if (expandableHomepageList2 != null) {
                            expandableHomepageList2.setHasNext(homePageResponse.getHasNext());
                            expandableHomepageList2.setCurrentPage(i2);
                            if (AppDebug.INSTANCE.isDebug()) {
                                list = homePageList.getList();
                                if ((list instanceof Collection) || !list.isEmpty()) {
                                    for (SearchResponse searchResponse : list) {
                                        list2 = expandableHomepageList2.getList().getList();
                                        if ((list2 instanceof Collection) || !list2.isEmpty()) {
                                            it = list2.iterator();
                                            while (it.hasNext()) {
                                                if (Intrinsics.areEqual(((SearchResponse) it.next()).getUrl(), searchResponse.getUrl())) {
                                                    ArchComponentExtKt.logError(new DebugException("Expanded contained an item that was previously already in the list\n" + expandableHomepageList2.getList().getName() + " = " + expandableHomepageList2.getList().getList() + '\n' + homePageList.getName() + " = " + homePageList.getList()));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            HomePageList list3 = expandableHomepageList2.getList();
                            list3.setList(CollectionsKt.plus((Collection) list3.getList(), (Iterable) homePageList.getList()));
                            List<SearchResponse> list4 = expandableHomepageList2.getList().getList();
                            hashSet = new HashSet();
                            arrayList = new ArrayList();
                            for (Object obj : list4) {
                                if (hashSet.add(((SearchResponse) obj).getUrl())) {
                                    arrayList.add(obj);
                                }
                            }
                            if (expandableHomepageList2 == null) {
                            }
                        }
                        if (AppDebug.INSTANCE.isDebug()) {
                            ArchComponentExtKt.logError(new DebugException("Expanded an item not in main load named " + name + ", current list is " + this.expandable.keySet()));
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            str = str2;
            this._page.postValue(new Resource.Success(this.expandable));
            this.lock.remove(str);
            return this.expandable.get(str);
        }
        ResultKt.throwOnFailure(mainPage);
        if (this.lock.contains(str)) {
            return null;
        }
        this.lock.add(str);
        aPIRepository = this.repo;
        if (aPIRepository != null) {
            c08421.L$0 = str;
            c08421.L$1 = aPIRepository;
            c08421.L$2 = aPIRepository;
            c08421.I$0 = 0;
            c08421.label = 1;
            if (aPIRepository.waitForHomeDelay(c08421) != coroutine_suspended) {
                aPIRepository2 = aPIRepository;
                i = 0;
            }
            return coroutine_suspended;
        }
        this.lock.remove(str);
        return this.expandable.get(str);
        ExpandableHomepageList expandableHomepageList3 = this.expandable.get(str);
        if (expandableHomepageList3 != null) {
            if (!AppDebug.INSTANCE.isDebug() || expandableHomepageList3.getHasNext()) {
                int currentPage = 1 + expandableHomepageList3.getCurrentPage();
                Iterator<MainPageData> it2 = aPIRepository.getMainPage().iterator();
                int i7 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i7 = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(it2.next().getName(), str)) {
                        break;
                    }
                    i7++;
                }
                Integer numBoxInt = Boxing.boxInt(i7);
                c08421.L$0 = str;
                c08421.L$1 = aPIRepository2;
                c08421.L$2 = SpillingKt.nullOutSpilledVariable(aPIRepository);
                c08421.L$3 = expandableHomepageList3;
                c08421.I$0 = i;
                c08421.I$1 = 0;
                c08421.I$2 = currentPage;
                c08421.label = 2;
                mainPage = aPIRepository.getMainPage(currentPage, numBoxInt, c08421);
                if (mainPage != coroutine_suspended) {
                    str2 = str;
                    i2 = currentPage;
                    expandableHomepageList = expandableHomepageList3;
                    resource = (Resource) mainPage;
                    if (resource instanceof Resource.Success) {
                        expandableHomepageList.setHasNext(false);
                    } else {
                        while (r13.hasNext()) {
                            while (r2.hasNext()) {
                                name = homePageList.getName();
                                expandableHomepageList2 = this.expandable.get(name);
                                if (expandableHomepageList2 != null) {
                                    expandableHomepageList2.setHasNext(homePageResponse.getHasNext());
                                    expandableHomepageList2.setCurrentPage(i2);
                                    if (AppDebug.INSTANCE.isDebug()) {
                                        list = homePageList.getList();
                                        if (list instanceof Collection) {
                                            while (r6.hasNext()) {
                                                list2 = expandableHomepageList2.getList().getList();
                                                if (list2 instanceof Collection) {
                                                }
                                                it = list2.iterator();
                                                while (it.hasNext()) {
                                                    if (Intrinsics.areEqual(((SearchResponse) it.next()).getUrl(), searchResponse.getUrl())) {
                                                        ArchComponentExtKt.logError(new DebugException("Expanded contained an item that was previously already in the list\n" + expandableHomepageList2.getList().getName() + " = " + expandableHomepageList2.getList().getList() + '\n' + homePageList.getName() + " = " + homePageList.getList()));
                                                        break;
                                                    }
                                                }
                                            }
                                        } else {
                                            while (r6.hasNext()) {
                                                list2 = expandableHomepageList2.getList().getList();
                                                if (list2 instanceof Collection) {
                                                }
                                                it = list2.iterator();
                                                while (it.hasNext()) {
                                                    if (Intrinsics.areEqual(((SearchResponse) it.next()).getUrl(), searchResponse.getUrl())) {
                                                        ArchComponentExtKt.logError(new DebugException("Expanded contained an item that was previously already in the list\n" + expandableHomepageList2.getList().getName() + " = " + expandableHomepageList2.getList().getList() + '\n' + homePageList.getName() + " = " + homePageList.getList()));
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    HomePageList list5 = expandableHomepageList2.getList();
                                    list5.setList(CollectionsKt.plus((Collection) list5.getList(), (Iterable) homePageList.getList()));
                                    List<SearchResponse> list6 = expandableHomepageList2.getList().getList();
                                    hashSet = new HashSet();
                                    arrayList = new ArrayList();
                                    while (r3.hasNext()) {
                                        if (hashSet.add(((SearchResponse) obj).getUrl())) {
                                            arrayList.add(obj);
                                        }
                                    }
                                    if (expandableHomepageList2 == null) {
                                    }
                                }
                                if (AppDebug.INSTANCE.isDebug()) {
                                    ArchComponentExtKt.logError(new DebugException("Expanded an item not in main load named " + name + ", current list is " + this.expandable.keySet()));
                                }
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    str = str2;
                }
                return coroutine_suspended;
            }
            throw new DebugException("Expand called when not needed");
        }
        this._page.postValue(new Resource.Success(this.expandable));
        this.lock.remove(str);
        return this.expandable.get(str);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$expand$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$expand$1", f = "HomeViewModel.kt", i = {}, l = {281}, m = "invokeSuspend", n = {}, nl = {282}, s = {}, v = 2)
    static final class C08411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $name;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08411(String str, Continuation<? super C08411> continuation) {
            super(2, continuation);
            this.$name = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeViewModel.this.new C08411(this.$name, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (HomeViewModel.this.expandAndReturn(this.$name, this) == coroutine_suspended) {
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

    public final Job expand(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08411(name, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object updatePreviewResponses(List<LoadResponse> list, Set<String> set, List<? extends SearchResponse> list2, int i, Continuation<? super Integer> continuation) {
        C08531 c08531;
        if (continuation instanceof C08531) {
            c08531 = (C08531) continuation;
            if ((c08531.label & Integer.MIN_VALUE) != 0) {
                c08531.label -= Integer.MIN_VALUE;
            } else {
                c08531 = new C08531(continuation);
            }
        } else {
            c08531 = new C08531(continuation);
        }
        Object objAmap = c08531.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c08531.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objAmap);
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            for (SearchResponse searchResponse : list2) {
                if (!set.contains(searchResponse.getUrl())) {
                    arrayList.add(searchResponse);
                    this.previewResponsesAdded.add(searchResponse.getUrl());
                    i3++;
                    if (i3 >= i) {
                        break;
                    }
                }
            }
            HomeViewModel$updatePreviewResponses$add$1 homeViewModel$updatePreviewResponses$add$1 = new HomeViewModel$updatePreviewResponses$add$1(this, null);
            c08531.L$0 = list;
            c08531.L$1 = SpillingKt.nullOutSpilledVariable(set);
            c08531.L$2 = SpillingKt.nullOutSpilledVariable(list2);
            c08531.L$3 = SpillingKt.nullOutSpilledVariable(arrayList);
            c08531.I$0 = i;
            c08531.I$1 = i3;
            c08531.label = 1;
            objAmap = ParCollectionsKt.amap(arrayList, homeViewModel$updatePreviewResponses$add$1, c08531);
            if (objAmap == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = c08531.I$1;
            int i5 = c08531.I$0;
            list = (List) c08531.L$0;
            ResultKt.throwOnFailure(objAmap);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Resource resource : (Iterable) objAmap) {
            LoadResponse loadResponse = (resource == null || !(resource instanceof Resource.Success)) ? null : (LoadResponse) ((Resource.Success) resource).getValue();
            if (loadResponse != null) {
                arrayList2.add(loadResponse);
            }
        }
        ArrayList arrayList3 = arrayList2;
        list.addAll(arrayList3);
        return Boxing.boxInt(arrayList3.size());
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadMoreHomeScrollResponses$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadMoreHomeScrollResponses$1", f = "HomeViewModel.kt", i = {}, l = {314}, m = "invokeSuspend", n = {}, nl = {315}, s = {}, v = 2)
    static final class C08451 extends SuspendLambda implements Function3<CoroutineScope, HomeViewModel, Continuation<? super Unit>, Object> {
        int label;

        C08451(Continuation<? super C08451> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, HomeViewModel homeViewModel, Continuation<? super Unit> continuation) {
            return HomeViewModel.this.new C08451(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HomeViewModel homeViewModel = HomeViewModel.this;
                this.label = 1;
                if (homeViewModel.updatePreviewResponses(homeViewModel.previewResponses, HomeViewModel.this.previewResponsesAdded, HomeViewModel.this.currentShuffledList, 1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            HomeViewModel.this._preview.postValue(new Resource.Success(TuplesKt.to(Boxing.boxBoolean(HomeViewModel.this.previewResponsesAdded.size() < HomeViewModel.this.currentShuffledList.size()), HomeViewModel.this.previewResponses)));
            return Unit.INSTANCE;
        }
    }

    public final void loadMoreHomeScrollResponses() {
        this.addJob = Coroutines.INSTANCE.ioSafe(this, new C08451(null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$load$1", f = "HomeViewModel.kt", i = {1, 1, 1, 1}, l = {341, 378}, m = "invokeSuspend", n = {"data", "items", "currentList", "randomItems"}, nl = {342, 385}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C08431 extends SuspendLambda implements Function3<CoroutineScope, HomeViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ MainAPI $api;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08431(MainAPI mainAPI, Continuation<? super C08431> continuation) {
            super(3, continuation);
            this.$api = mainAPI;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, HomeViewModel homeViewModel, Continuation<? super Unit> continuation) {
            return HomeViewModel.this.new C08431(this.$api, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:102:0x0306  */
        /* JADX WARN: Code duplicated, block: B:104:0x030a  */
        /* JADX WARN: Code duplicated, block: B:118:0x0173 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:120:0x015d A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:123:0x01c7 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:125:0x01ae A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:129:0x0219 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:131:0x0202 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:34:0x00bd A[Catch: Exception -> 0x0026, TRY_ENTER, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:37:0x00db A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:54:0x0163 A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:56:0x016b A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:57:0x0170  */
        /* JADX WARN: Code duplicated, block: B:62:0x019a A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:65:0x01b4 A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:71:0x01e0 A[Catch: Exception -> 0x0026, LOOP:4: B:69:0x01da->B:71:0x01e0, LOOP_END, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:75:0x0208 A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:80:0x022e A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:84:0x0245 A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:88:0x027e  */
        /* JADX WARN: Code duplicated, block: B:93:0x029b A[Catch: Exception -> 0x0026, TRY_ENTER, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:94:0x02ab A[Catch: Exception -> 0x0026, TryCatch #0 {Exception -> 0x0026, blocks: (B:7:0x0021, B:89:0x027f, B:90:0x028d, B:93:0x029b, B:99:0x02de, B:94:0x02ab, B:98:0x02ca, B:34:0x00bd, B:35:0x00d5, B:37:0x00db, B:39:0x00e3, B:41:0x00e9, B:42:0x00ef, B:44:0x00f5, B:46:0x0103, B:49:0x010c, B:51:0x0149, B:52:0x015d, B:54:0x0163, B:56:0x016b, B:59:0x0173, B:60:0x0177, B:62:0x019a, B:63:0x01ae, B:65:0x01b4, B:67:0x01c7, B:68:0x01cb, B:69:0x01da, B:71:0x01e0, B:72:0x01f0, B:73:0x0202, B:75:0x0208, B:77:0x0219, B:78:0x021d, B:80:0x022e, B:82:0x0236, B:85:0x024c, B:84:0x0245), top: B:109:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:96:0x02c7  */
        /* JADX WARN: Code duplicated, block: B:97:0x02c9  */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00b1, code lost:
        
            if (r2 == r0) goto L87;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 843
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.home.HomeViewModel.C08431.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final Job load(MainAPI api) {
        return Coroutines.INSTANCE.ioSafe(this, new C08431(api, null));
    }

    public final void click(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (callback.getAction() != 4) {
            SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        }
    }

    public final LiveData<Pair<ExpandableHomepageList, Function0<Unit>>> getPopup() {
        return this.popup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void popup$default(HomeViewModel homeViewModel, ExpandableHomepageList expandableHomepageList, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        homeViewModel.popup(expandableHomepageList, function0);
    }

    public final void popup(ExpandableHomepageList list, Function0<Unit> deleteCallback) {
        MutableLiveData<Pair<ExpandableHomepageList, Function0<Unit>>> mutableLiveData = this._popup;
        if (list == null) {
            mutableLiveData.postValue(null);
        } else {
            mutableLiveData.postValue(TuplesKt.to(list, deleteCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bookmarksUpdated(boolean unused) {
        reloadStored();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void afterPluginsLoaded(boolean forceReload) {
        loadAndCancel$default(this, DataStoreHelper.INSTANCE.getCurrentHomePage(), forceReload, false, 4, null);
    }

    static /* synthetic */ void afterMainPluginsLoaded$default(HomeViewModel homeViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        homeViewModel.afterMainPluginsLoaded(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void afterMainPluginsLoaded(boolean unused) {
        loadAndCancel$default(this, DataStoreHelper.INSTANCE.getCurrentHomePage(), false, false, 4, null);
    }

    static /* synthetic */ void reloadHome$default(HomeViewModel homeViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        homeViewModel.reloadHome(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadHome(boolean unused) {
        loadAndCancel$default(this, DataStoreHelper.INSTANCE.getCurrentHomePage(), true, false, 4, null);
    }

    static /* synthetic */ void reloadAccount$default(HomeViewModel homeViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        homeViewModel.reloadAccount(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadAccount(boolean unused) {
        this._currentAccount.postValue(DataStoreHelper.INSTANCE.getCurrentAccount());
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, HomeViewModel.class, "bookmarksUpdated", "bookmarksUpdated(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).bookmarksUpdated(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, HomeViewModel.class, "afterPluginsLoaded", "afterPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).afterPluginsLoaded(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass3(Object obj) {
            super(1, obj, HomeViewModel.class, "afterMainPluginsLoaded", "afterMainPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).afterMainPluginsLoaded(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$4, reason: invalid class name */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass4(Object obj) {
            super(1, obj, HomeViewModel.class, "reloadHome", "reloadHome(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).reloadHome(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$5, reason: invalid class name */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass5(Object obj) {
            super(1, obj, HomeViewModel.class, "reloadAccount", "reloadAccount(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).reloadAccount(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$onCleared$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08481 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08481(Object obj) {
            super(1, obj, HomeViewModel.class, "bookmarksUpdated", "bookmarksUpdated(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).bookmarksUpdated(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$onCleared$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08492 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08492(Object obj) {
            super(1, obj, HomeViewModel.class, "afterPluginsLoaded", "afterPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).afterPluginsLoaded(z);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        MainActivity.INSTANCE.getBookmarksUpdatedEvent().minusAssign(new C08481(this));
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().minusAssign(new C08492(this));
        MainActivity.INSTANCE.getMainPluginsLoadedEvent().minusAssign(new C08503(this));
        MainActivity.INSTANCE.getReloadHomeEvent().minusAssign(new C08514(this));
        MainActivity.INSTANCE.getReloadAccountEvent().minusAssign(new C08525(this));
        super.onCleared();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$onCleared$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08503 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08503(Object obj) {
            super(1, obj, HomeViewModel.class, "afterMainPluginsLoaded", "afterMainPluginsLoaded(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).afterMainPluginsLoaded(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$onCleared$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08514 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08514(Object obj) {
            super(1, obj, HomeViewModel.class, "reloadHome", "reloadHome(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).reloadHome(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$onCleared$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08525 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08525(Object obj) {
            super(1, obj, HomeViewModel.class, "reloadAccount", "reloadAccount(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((HomeViewModel) this.receiver).reloadAccount(z);
        }
    }

    public final void queryTextSubmit(String query) {
        String name;
        Intrinsics.checkNotNullParameter(query, "query");
        QuickSearchFragment.Companion companion = QuickSearchFragment.INSTANCE;
        APIRepository aPIRepository = this.repo;
        companion.pushSearch(query, (aPIRepository == null || (name = aPIRepository.getName()) == null) ? null : new String[]{name});
    }

    public final void loadStoredData() {
        EnumSet enumSetNoneOf = EnumSet.noneOf(WatchType.class);
        int[] homeBookmarkedList = DataStoreHelper.INSTANCE.getHomeBookmarkedList();
        ArrayList arrayList = new ArrayList(homeBookmarkedList.length);
        for (int i : homeBookmarkedList) {
            arrayList.add(WatchType.INSTANCE.fromInternalId(Integer.valueOf(i)));
        }
        enumSetNoneOf.addAll(arrayList);
        loadStoredData(enumSetNoneOf);
    }

    public final void reloadStored() {
        loadResumeWatching();
        loadStoredData();
    }

    public final void click(LoadClickCallback load) {
        Intrinsics.checkNotNullParameter(load, "load");
        AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, load.getResponse().getUrl(), load.getResponse().getApiName(), load.getResponse().getName(), load.getAction(), 0, 16, null);
    }

    public static /* synthetic */ Job loadAndCancel$default(HomeViewModel homeViewModel, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return homeViewModel.loadAndCancel(str, z, z2);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeViewModel$loadAndCancel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$loadAndCancel$1", f = "HomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08441 extends SuspendLambda implements Function3<CoroutineScope, HomeViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $forceReload;
        final /* synthetic */ boolean $fromUI;
        final /* synthetic */ String $preferredApiName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08441(boolean z, String str, boolean z2, Continuation<? super C08441> continuation) {
            super(3, continuation);
            this.$forceReload = z;
            this.$preferredApiName = str;
            this.$fromUI = z2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, HomeViewModel homeViewModel, Continuation<? super Unit> continuation) {
            return HomeViewModel.this.new C08441(this.$forceReload, this.$preferredApiName, this.$fromUI, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Resource<Map<String, ExpandableHomepageList>> value = HomeViewModel.this.getPage().getValue();
            String str = HomeViewModel.this.isCurrentlyLoadingName;
            if (!this.$forceReload && (((value instanceof Resource.Success) && !((Map) ((Resource.Success) value).getValue()).isEmpty()) || (str != null && Intrinsics.areEqual(str, this.$preferredApiName)))) {
                return Unit.INSTANCE;
            }
            MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(this.$preferredApiName);
            if (Intrinsics.areEqual(this.$preferredApiName, APIRepository.INSTANCE.getNoneApi().getName())) {
                if (this.$fromUI) {
                    DataStoreHelper.INSTANCE.setCurrentHomePage(APIRepository.INSTANCE.getNoneApi().getName());
                }
                HomeViewModel.this.loadAndCancel(APIRepository.INSTANCE.getNoneApi());
            } else if (Intrinsics.areEqual(this.$preferredApiName, APIRepository.INSTANCE.getRandomApi().getName())) {
                Context context = CloudStreamApp.INSTANCE.getContext();
                List listFilterProviderByPreferredMedia$default = context != null ? AppContextUtils.filterProviderByPreferredMedia$default(AppContextUtils.INSTANCE, context, false, 1, null) : null;
                if (listFilterProviderByPreferredMedia$default == null || listFilterProviderByPreferredMedia$default.isEmpty()) {
                    HomeViewModel.this.loadAndCancel(APIRepository.INSTANCE.getNoneApi());
                } else {
                    MainAPI mainAPI = (MainAPI) CollectionsKt.random(listFilterProviderByPreferredMedia$default, Random.INSTANCE);
                    HomeViewModel.this.loadAndCancel(mainAPI);
                    if (this.$fromUI) {
                        DataStoreHelper.INSTANCE.setCurrentHomePage(mainAPI.getName());
                    }
                }
            } else if (apiFromNameNull == null) {
                if (PluginManager.INSTANCE.getLoadedOnlinePlugins() || PluginManager.INSTANCE.isSafeMode()) {
                    HomeViewModel.this.loadAndCancel(APIRepository.INSTANCE.getNoneApi());
                } else {
                    HomeViewModel.this._page.postValue(new Resource.Loading(null, 1, null));
                    if (this.$preferredApiName != null) {
                        HomeViewModel.this._apiName.postValue(this.$preferredApiName);
                    }
                }
            } else {
                if (this.$fromUI) {
                    DataStoreHelper.INSTANCE.setCurrentHomePage(apiFromNameNull.getName());
                }
                HomeViewModel.this.loadAndCancel(apiFromNameNull);
            }
            HomeViewModel.reloadAccount$default(HomeViewModel.this, false, 1, null);
            return Unit.INSTANCE;
        }
    }

    public final Job loadAndCancel(String preferredApiName, boolean forceReload, boolean fromUI) {
        return Coroutines.INSTANCE.ioSafe(this, new C08441(forceReload, preferredApiName, fromUI, null));
    }
}

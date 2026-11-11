package com.lagradost.cloudstream3.ui.search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.databinding.FragmentSearchBinding;
import com.lagradost.cloudstream3.databinding.HomeSelectMainpageBinding;
import com.lagradost.cloudstream3.databinding.TvtypesChipsBinding;
import com.lagradost.cloudstream3.databinding.TvtypesChipsScrollBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.home.HomeFragment;
import com.lagradost.cloudstream3.ui.home.HomeViewModel;
import com.lagradost.cloudstream3.ui.home.ParentItemAdapter;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: SearchFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 82\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00018B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010.\u001a\u00020\u001d2\b\u0010/\u001a\u0004\u0018\u00010)J\u0012\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u0015H\u0016J\u001a\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSearchBinding;", "<init>", "()V", "searchViewModel", "Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;", "getSearchViewModel", "()Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;", "searchViewModel$delegate", "Lkotlin/Lazy;", "bottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "speechRecognizerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "pickLayout", "", "()Ljava/lang/Integer;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onResume", "onStop", "selectedSearchTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSelectedSearchTypes", "()Ljava/util/List;", "setSelectedSearchTypes", "(Ljava/util/List;)V", "selectedApis", "", "", "getSelectedApis", "()Ljava/util/Set;", "setSelectedApis", "(Ljava/util/Set;)V", "search", SearchIntents.EXTRA_QUERY, "reloadRepos", "Lkotlinx/coroutines/Job;", "success", "", "fixLayout", "view", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SEARCH_QUERY = "search_query";
    private BottomSheetDialog bottomSheetDialog;

    /* JADX INFO: renamed from: searchViewModel$delegate, reason: from kotlin metadata */
    private final Lazy searchViewModel;
    private Set<String> selectedApis;
    private List<TvType> selectedSearchTypes;
    private final ActivityResultLauncher<Intent> speechRecognizerLauncher;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<View, FragmentSearchBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, FragmentSearchBinding.class, "bind", "bind(Landroid/view/View;)Lcom/lagradost/cloudstream3/databinding/FragmentSearchBinding;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FragmentSearchBinding invoke(View p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSearchBinding.bind(p0);
        }
    }

    public SearchFragment() {
        super(new BaseFragment.BindingCreator.Bind(AnonymousClass1.INSTANCE));
        final SearchFragment searchFragment = this;
        final Function0 function0 = null;
        this.searchViewModel = FragmentViewModelLazyKt.createViewModelLazy(searchFragment, Reflection.getOrCreateKotlinClass(SearchViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return searchFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? searchFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return searchFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SearchFragment.speechRecognizerLauncher$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.speechRecognizerLauncher = activityResultLauncherRegisterForActivityResult;
        this.selectedSearchTypes = new ArrayList();
        this.selectedApis = new LinkedHashSet();
    }

    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchFragment$Companion;", "", "<init>", "()V", "filterSearchResponse", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "SEARCH_QUERY", "", "newInstance", "Landroid/os/Bundle;", SearchIntents.EXTRA_QUERY, "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v3 java.util.Set<com.lagradost.cloudstream3.DubStatus>, still in use, count: 1, list:
              (r1v3 java.util.Set<com.lagradost.cloudstream3.DubStatus>) from 0x0035: MOVE (r1v4 java.util.Set<com.lagradost.cloudstream3.DubStatus>) = (r1v3 java.util.Set<com.lagradost.cloudstream3.DubStatus>)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
            	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.simplifyMoveInsns(CodeShrinkVisitor.java:288)
            	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:49)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.processForceInlineInsns(RegionMakerVisitor.java:43)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:29)
            */
        public final java.util.List<com.lagradost.cloudstream3.SearchResponse> filterSearchResponse(java.util.List<? extends com.lagradost.cloudstream3.SearchResponse> r5) {
            /*
                r4 = this;
                java.lang.String r4 = "<this>"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                java.lang.Iterable r5 = (java.lang.Iterable) r5
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                java.util.Collection r4 = (java.util.Collection) r4
                java.util.Iterator r5 = r5.iterator()
            L12:
                boolean r0 = r5.hasNext()
                if (r0 == 0) goto L65
                java.lang.Object r0 = r5.next()
                r1 = r0
                com.lagradost.cloudstream3.SearchResponse r1 = (com.lagradost.cloudstream3.SearchResponse) r1
                boolean r2 = r1 instanceof com.lagradost.cloudstream3.AnimeSearchResponse
                if (r2 == 0) goto L61
                com.lagradost.cloudstream3.AnimeSearchResponse r1 = (com.lagradost.cloudstream3.AnimeSearchResponse) r1
                java.util.Set r1 = r1.getDubStatus()
                r2 = r1
                java.util.Collection r2 = (java.util.Collection) r2
                if (r2 == 0) goto L61
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L35
                goto L61
            L35:
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                boolean r2 = r1 instanceof java.util.Collection
                if (r2 == 0) goto L45
                r2 = r1
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L45
                goto L12
            L45:
                java.util.Iterator r1 = r1.iterator()
            L49:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L12
                java.lang.Object r2 = r1.next()
                com.lagradost.cloudstream3.DubStatus r2 = (com.lagradost.cloudstream3.DubStatus) r2
                com.lagradost.cloudstream3.ui.APIRepository$Companion r3 = com.lagradost.cloudstream3.ui.APIRepository.INSTANCE
                java.util.HashSet r3 = r3.getDubStatusActive()
                boolean r2 = r3.contains(r2)
                if (r2 == 0) goto L49
            L61:
                r4.add(r0)
                goto L12
            L65:
                java.util.List r4 = (java.util.List) r4
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.search.SearchFragment.Companion.filterSearchResponse(java.util.List):java.util.List");
        }

        public final Bundle newInstance(String query) {
            Intrinsics.checkNotNullParameter(query, "query");
            Bundle bundle = new Bundle();
            if (!StringsKt.isBlank(query)) {
                bundle.putString(SearchFragment.SEARCH_QUERY, query);
            }
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SearchViewModel getSearchViewModel() {
        return (SearchViewModel) this.searchViewModel.getValue();
    }

    static final void speechRecognizerLauncher$lambda$0(SearchFragment searchFragment, ActivityResult result) {
        SearchView searchView;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            ArrayList<String> stringArrayListExtra = data != null ? data.getStringArrayListExtra("android.speech.extra.RESULTS") : null;
            ArrayList<String> arrayList = stringArrayListExtra;
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            String str = stringArrayListExtra.get(0);
            FragmentSearchBinding binding = searchFragment.getBinding();
            if (binding == null || (searchView = binding.mainSearch) == null) {
                return;
            }
            searchView.setQuery(str, true);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public Integer pickLayout() {
        return Integer.valueOf(Globals.INSTANCE.isLayout(6) ? R.layout.fragment_search_tv : R.layout.fragment_search);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentActivity activity = getActivity();
        if (activity != null && (window = activity.getWindow()) != null) {
            window.setSoftInputMode(4);
        }
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            AppContextUtils.INSTANCE.ownShow(bottomSheetDialog);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        UIHelper.INSTANCE.hideKeyboard(this);
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            AppContextUtils.INSTANCE.ownHide(bottomSheetDialog);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, "SearchFragment");
        }
        super.onDestroyView();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchFragment$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09011 extends AdaptedFunctionReference implements Function1<Boolean, Unit> {
        C09011(Object obj) {
            super(1, obj, SearchFragment.class, "reloadRepos", "reloadRepos(Z)Lkotlinx/coroutines/Job;", 8);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((SearchFragment) this.receiver).reloadRepos(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().plusAssign(new C09011(this));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchFragment$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09021 extends AdaptedFunctionReference implements Function1<Boolean, Unit> {
        C09021(Object obj) {
            super(1, obj, SearchFragment.class, "reloadRepos", "reloadRepos(Z)Lkotlinx/coroutines/Job;", 8);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((SearchFragment) this.receiver).reloadRepos(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().minusAssign(new C09021(this));
    }

    public final List<TvType> getSelectedSearchTypes() {
        return this.selectedSearchTypes;
    }

    public final void setSelectedSearchTypes(List<TvType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.selectedSearchTypes = list;
    }

    public final Set<String> getSelectedApis() {
        return this.selectedApis;
    }

    public final void setSelectedApis(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.selectedApis = set;
    }

    public final void search(String query) {
        RecyclerView recyclerView;
        if (query == null) {
            return;
        }
        FragmentSearchBinding binding = getBinding();
        RecyclerView.Adapter adapter = (binding == null || (recyclerView = binding.searchMasterRecycler) == null) ? null : recyclerView.getAdapter();
        BaseAdapter baseAdapter = adapter instanceof BaseAdapter ? (BaseAdapter) adapter : null;
        if (baseAdapter != null) {
            baseAdapter.clearState();
        }
        Context context = getContext();
        if (context != null) {
            List listSorted = ArraysKt.sorted(TvType.values());
            ArrayList arrayList = new ArrayList();
            for (Object obj : listSorted) {
                if (((TvType) obj) != TvType.NSFW) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(String.valueOf(((TvType) it.next()).ordinal()));
            }
            Set<String> set = CollectionsKt.toSet(arrayList3);
            Set<String> stringSet = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(getString(R.string.prefer_media_type_key), set);
            if (stringSet != null) {
                Set<String> set2 = stringSet;
                if (set2.isEmpty()) {
                    set2 = set;
                }
                Set<String> set3 = set2;
                if (set3 != null) {
                    set = set3;
                }
            }
            ArrayList arrayList4 = new ArrayList();
            for (String str : set) {
                Intrinsics.checkNotNull(str);
                Integer intOrNull = StringsKt.toIntOrNull(str);
                if (intOrNull != null) {
                    intOrNull.intValue();
                } else {
                    intOrNull = null;
                }
                if (intOrNull != null) {
                    arrayList4.add(intOrNull);
                }
            }
            ArrayList arrayList5 = arrayList4;
            HashSet<String> apiSettings = AppContextUtils.INSTANCE.getApiSettings(context);
            Set<String> set4 = this.selectedApis;
            ArrayList arrayList6 = new ArrayList();
            for (Object obj2 : set4) {
                if (apiSettings.contains((String) obj2)) {
                    arrayList6.add(obj2);
                }
            }
            ArrayList<String> arrayList7 = arrayList6;
            ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList7, 10));
            for (String str2 : arrayList7) {
                MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(str2);
                arrayList8.add(TuplesKt.to(str2, apiFromNameNull != null ? apiFromNameNull.getSupportedTypes() : null));
            }
            ArrayList arrayList9 = new ArrayList();
            for (Object obj3 : arrayList8) {
                Set set5 = (Set) ((Pair) obj3).component2();
                if (set5 != null) {
                    Set set6 = set5;
                    if (!(set6 instanceof Collection) || !set6.isEmpty()) {
                        Iterator it2 = set6.iterator();
                        while (it2.hasNext()) {
                            if (arrayList5.contains(Integer.valueOf(((TvType) it2.next()).ordinal()))) {
                                arrayList9.add(obj3);
                                break;
                            }
                        }
                    }
                }
            }
            ArrayList arrayList10 = arrayList9;
            SearchViewModel searchViewModel = getSearchViewModel();
            ArrayList arrayList11 = new ArrayList();
            for (Object obj4 : arrayList10) {
                Set set7 = (Set) ((Pair) obj4).component2();
                if (set7 != null) {
                    Set set8 = set7;
                    if (!(set8 instanceof Collection) || !set8.isEmpty()) {
                        Iterator it3 = set8.iterator();
                        while (it3.hasNext()) {
                            if (this.selectedSearchTypes.contains((TvType) it3.next())) {
                                arrayList11.add(obj4);
                                break;
                            }
                        }
                    }
                }
            }
            ArrayList arrayList12 = arrayList11;
            if (!arrayList12.isEmpty()) {
                arrayList10 = arrayList12;
            }
            ArrayList arrayList13 = arrayList10;
            ArrayList arrayList14 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList13, 10));
            Iterator it4 = arrayList13.iterator();
            while (it4.hasNext()) {
                arrayList14.add((String) ((Pair) it4.next()).getFirst());
            }
            SearchViewModel.searchAndCancel$default(searchViewModel, query, CollectionsKt.toSet(arrayList14), false, false, 12, null);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchFragment$reloadRepos$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/ui/search/SearchFragment;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchFragment$reloadRepos$1", f = "SearchFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09031 extends SuspendLambda implements Function2<SearchFragment, Continuation<? super Unit>, Object> {
        int label;

        C09031(Continuation<? super C09031> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchFragment.this.new C09031(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SearchFragment searchFragment, Continuation<? super Unit> continuation) {
            return ((C09031) create(searchFragment, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TvtypesChipsScrollBinding tvtypesChipsScrollBinding;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SearchFragment.this.getSearchViewModel().reloadRepos();
                Context context = SearchFragment.this.getContext();
                if (context != null) {
                    TvtypesChipsBinding tvtypesChipsBinding = null;
                    List listFilterProviderByPreferredMedia$default = AppContextUtils.filterProviderByPreferredMedia$default(AppContextUtils.INSTANCE, context, false, 1, null);
                    if (listFilterProviderByPreferredMedia$default != null) {
                        final SearchFragment searchFragment = SearchFragment.this;
                        HomeFragment.Companion companion = HomeFragment.INSTANCE;
                        FragmentSearchBinding binding = searchFragment.getBinding();
                        if (binding != null && (tvtypesChipsScrollBinding = binding.tvtypesChipsScroll) != null) {
                            tvtypesChipsBinding = tvtypesChipsScrollBinding.tvtypesChips;
                        }
                        List<TvType> selectedSearchTypes = searchFragment.getSelectedSearchTypes();
                        ArrayList arrayList = new ArrayList();
                        Iterator it = listFilterProviderByPreferredMedia$default.iterator();
                        while (it.hasNext()) {
                            CollectionsKt.addAll(arrayList, ((MainAPI) it.next()).getSupportedTypes());
                        }
                        companion.bindChips(tvtypesChipsBinding, selectedSearchTypes, CollectionsKt.distinct(arrayList), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$reloadRepos$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return SearchFragment.C09031.invokeSuspend$lambda$0$1(searchFragment, (List) obj2);
                            }
                        });
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$1(SearchFragment searchFragment, List list) {
            SearchView searchView;
            CharSequence query;
            if (!Intrinsics.areEqual(CollectionsKt.toSet(searchFragment.getSelectedSearchTypes()), CollectionsKt.toSet(list))) {
                DataStoreHelper.INSTANCE.setSearchPreferenceTags(list);
                searchFragment.getSelectedSearchTypes().clear();
                searchFragment.getSelectedSearchTypes().addAll(list);
                FragmentSearchBinding binding = searchFragment.getBinding();
                searchFragment.search((binding == null || (searchView = binding.mainSearch) == null || (query = searchView.getQuery()) == null) ? null : query.toString());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job reloadRepos(boolean success) {
        return Coroutines.INSTANCE.main(this, new C09031(null));
    }

    static /* synthetic */ Job reloadRepos$default(SearchFragment searchFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return searchFragment.reloadRepos(z);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        AutofitRecyclerView autofitRecyclerView;
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
        HomeFragment.Companion companion = HomeFragment.INSTANCE;
        UIHelper uIHelper = UIHelper.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.setCurrentSpan(UIHelper.getSpanCount$default(uIHelper, context, false, 1, null));
        FragmentSearchBinding binding = getBinding();
        if (binding != null && (autofitRecyclerView = binding.searchAutofitResults) != null) {
            autofitRecyclerView.setSpanCount(HomeFragment.INSTANCE.getCurrentSpan());
        }
        HomeFragment.INSTANCE.getConfigEvent().invoke();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSearchBinding binding, Bundle savedInstanceState) {
        final String string;
        Intrinsics.checkNotNullParameter(binding, "binding");
        reloadRepos$default(this, false, 1, null);
        AutofitRecyclerView searchAutofitResults = binding.searchAutofitResults;
        Intrinsics.checkNotNullExpressionValue(searchAutofitResults, "searchAutofitResults");
        SearchAdapter searchAdapter = new SearchAdapter(searchAutofitResults, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$0$0((SearchClickCallback) obj);
            }
        }, 2, null);
        TextView textView = (TextView) binding.searchRoot.findViewById(androidx.appcompat.R.id.search_src_text);
        if (textView != null) {
            textView.setTag("tv_no_focus_tag");
        }
        AutofitRecyclerView searchAutofitResults2 = binding.searchAutofitResults;
        Intrinsics.checkNotNullExpressionValue(searchAutofitResults2, "searchAutofitResults");
        BaseAdapterKt.setRecycledViewPool(searchAutofitResults2, SearchAdapter.INSTANCE.getSharedPool());
        binding.searchAutofitResults.setAdapter(searchAdapter);
        binding.searchLoadingBar.setAlpha(0.0f);
        binding.voiceSearch.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchFragment.onBindingCreated$lambda$1(this.f$0, view);
            }
        });
        final ImageView imageView = (ImageView) binding.mainSearch.findViewById(androidx.appcompat.R.id.search_close_btn);
        this.selectedApis = CollectionsKt.toMutableSet(DataStoreHelper.INSTANCE.getSearchPreferenceProviders());
        binding.searchFilter.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchFragment.onBindingCreated$lambda$2(this.f$0, binding, view);
            }
        });
        Context context = getContext();
        SharedPreferences defaultSharedPreferences = context != null ? PreferenceManager.getDefaultSharedPreferences(context) : null;
        final boolean z = defaultSharedPreferences != null ? defaultSharedPreferences.getBoolean("advanced_search", true) : true;
        final boolean z2 = defaultSharedPreferences != null ? defaultSharedPreferences.getBoolean("search_suggestions_enabled", true) : true;
        this.selectedSearchTypes = CollectionsKt.toMutableList((Collection) DataStoreHelper.INSTANCE.getSearchPreferenceTags());
        if (!Globals.INSTANCE.isLayout(1)) {
            binding.searchFilter.setFocusable(true);
            binding.searchFilter.setFocusableInTouchMode(true);
        }
        if (Globals.INSTANCE.isLayout(1)) {
            binding.mainSearch.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z3) {
                    SearchFragment.onBindingCreated$lambda$4(this.f$0, view, z3);
                }
            });
        }
        binding.mainSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment.onBindingCreated.5
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                SearchFragment.this.search(query);
                SearchFragment.this.getSearchViewModel().clearSuggestions();
                UIHelper.INSTANCE.hideKeyboard(binding.mainSearch);
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                boolean zIsBlank = StringsKt.isBlank(newText);
                if (zIsBlank) {
                    SearchFragment.this.getSearchViewModel().clearSearch();
                    SearchFragment.this.getSearchViewModel().updateHistory();
                    SearchFragment.this.getSearchViewModel().clearSuggestions();
                } else if (z2) {
                    SearchFragment.this.getSearchViewModel().fetchSuggestions(newText);
                }
                FragmentSearchBinding fragmentSearchBinding = binding;
                boolean z3 = z;
                boolean z4 = z2;
                RecyclerView searchHistoryRecycler = fragmentSearchBinding.searchHistoryRecycler;
                Intrinsics.checkNotNullExpressionValue(searchHistoryRecycler, "searchHistoryRecycler");
                searchHistoryRecycler.setVisibility(zIsBlank ? 0 : 8);
                RecyclerView searchMasterRecycler = fragmentSearchBinding.searchMasterRecycler;
                Intrinsics.checkNotNullExpressionValue(searchMasterRecycler, "searchMasterRecycler");
                searchMasterRecycler.setVisibility(!zIsBlank && z3 ? 0 : 8);
                AutofitRecyclerView searchAutofitResults3 = fragmentSearchBinding.searchAutofitResults;
                Intrinsics.checkNotNullExpressionValue(searchAutofitResults3, "searchAutofitResults");
                searchAutofitResults3.setVisibility(!zIsBlank && !z3 ? 0 : 8);
                RecyclerView searchSuggestionsRecycler = fragmentSearchBinding.searchSuggestionsRecycler;
                Intrinsics.checkNotNullExpressionValue(searchSuggestionsRecycler, "searchSuggestionsRecycler");
                searchSuggestionsRecycler.setVisibility(!zIsBlank && z4 ? 0 : 8);
                return true;
            }
        });
        SearchFragment searchFragment = this;
        LifecycleKt.observe(searchFragment, getSearchViewModel().getSearchResponse(), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$5(imageView, binding, (Resource) obj);
            }
        });
        final ReentrantLock reentrantLock = new ReentrantLock();
        LifecycleKt.observe(searchFragment, getSearchViewModel().getCurrentSearch(), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$6(reentrantLock, binding, this, (Map) obj);
            }
        });
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(-335020403, new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$7((SearchClickCallback) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$8(this.f$0, (HomeViewModel.ExpandableHomepageList) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$9(this.f$0, (String) obj);
            }
        });
        SearchHistoryAdaptor searchHistoryAdaptor = new SearchHistoryAdaptor(new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$10(this.f$0, binding, (SearchHistoryCallback) obj);
            }
        });
        SearchSuggestionAdapter searchSuggestionAdapter = new SearchSuggestionAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$11(binding, this, (SearchSuggestionCallback) obj);
            }
        });
        binding.searchHistoryRecycler.setAdapter(searchHistoryAdaptor);
        LinearListLayoutKt.setLinearListLayout$default(binding.searchHistoryRecycler, false, 0, -2, 0, 0, 26, null);
        binding.searchSuggestionsRecycler.setAdapter(searchSuggestionAdapter);
        binding.searchSuggestionsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView searchMasterRecycler = binding.searchMasterRecycler;
        Intrinsics.checkNotNullExpressionValue(searchMasterRecycler, "searchMasterRecycler");
        BaseAdapterKt.setRecycledViewPool(searchMasterRecycler, ParentItemAdapter.INSTANCE.getSharedPool());
        binding.searchMasterRecycler.setAdapter(parentItemAdapter);
        binding.searchMasterRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString(SEARCH_QUERY)) == null) {
            string = savedInstanceState != null ? savedInstanceState.getString(SEARCH_QUERY) : null;
        }
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            string = MainActivity.INSTANCE.getNextSearchQuery();
        }
        if (string != null) {
            String str2 = string;
            if (!StringsKt.isBlank(str2)) {
                SearchView mainSearch = binding.mainSearch;
                Intrinsics.checkNotNullExpressionValue(mainSearch, "mainSearch");
                SearchView searchView = mainSearch;
                if (searchView.isLaidOut() && !searchView.isLayoutRequested()) {
                    binding.mainSearch.setQuery(str2, true);
                } else {
                    searchView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$onBindingCreated$lambda$12$0$$inlined$doOnLayout$1
                        @Override // android.view.View.OnLayoutChangeListener
                        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            view.removeOnLayoutChangeListener(this);
                            binding.mainSearch.setQuery(string, true);
                        }
                    });
                }
                Bundle arguments2 = getArguments();
                if (arguments2 != null) {
                    arguments2.remove(SEARCH_QUERY);
                }
                if (savedInstanceState != null) {
                    savedInstanceState.remove(SEARCH_QUERY);
                }
                MainActivity.INSTANCE.setNextSearchQuery(null);
            }
        }
        LifecycleKt.observe(searchFragment, getSearchViewModel().getCurrentHistory(), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$13(binding, (List) obj);
            }
        });
        LifecycleKt.observe(searchFragment, getSearchViewModel().getSearchSuggestions(), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$14(binding, this, (List) obj);
            }
        });
        getSearchViewModel().updateHistory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$0(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$1(SearchFragment searchFragment, View view) {
        Context context;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        try {
            if (!SpeechRecognizer.isRecognitionAvailable(context)) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.speech_recognition_unavailable, (Integer) null, 2, (Object) null);
            } else {
                Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
                intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
                intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
                intent.putExtra("android.speech.extra.PROMPT", context.getString(R.string.begin_speaking));
                searchFragment.speechRecognizerLauncher.launch(intent);
            }
        } catch (Throwable unused) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.speech_recognition_unavailable, (Integer) null, 2, (Object) null);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.util.List] */
    static final void onBindingCreated$lambda$2(final SearchFragment searchFragment, final FragmentSearchBinding fragmentSearchBinding, View view) {
        Context context;
        Set<String> mutableSet;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        final List<MainAPI> listFilterProviderByPreferredMedia = AppContextUtils.INSTANCE.filterProviderByPreferredMedia(context, false);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CollectionsKt.emptyList();
        if (searchFragment.selectedApis.isEmpty()) {
            List<MainAPI> list = listFilterProviderByPreferredMedia;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((MainAPI) it.next()).getName());
            }
            mutableSet = CollectionsKt.toMutableSet(arrayList);
        } else {
            mutableSet = searchFragment.selectedApis;
        }
        final Set<String> set = mutableSet;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.getBehavior().setState(3);
        HomeSelectMainpageBinding homeSelectMainpageBindingInflate = HomeSelectMainpageBinding.inflate(bottomSheetDialog.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(homeSelectMainpageBindingInflate, "inflate(...)");
        bottomSheetDialog.setContentView(homeSelectMainpageBindingInflate.getRoot());
        bottomSheetDialog.show();
        final Set set2 = CollectionsKt.toSet(searchFragment.selectedApis);
        final Set set3 = CollectionsKt.toSet(searchFragment.selectedSearchTypes);
        HashSet<String> apiProviderLangSettings = AppContextUtils.INSTANCE.getApiProviderLangSettings(context);
        final boolean z = apiProviderLangSettings.size() > 1 || apiProviderLangSettings.contains(MainAPIKt.AllLanguagesName);
        MaterialButton materialButton = (MaterialButton) bottomSheetDialog.findViewById(R.id.cancel_btt);
        MaterialButton materialButton2 = (MaterialButton) bottomSheetDialog.findViewById(R.id.apply_btt);
        final ListView listView = (ListView) bottomSheetDialog.findViewById(R.id.listview1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        if (listView != null) {
            listView.setAdapter((ListAdapter) arrayAdapter);
        }
        if (listView != null) {
            listView.setChoiceMode(2);
        }
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda15
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view2, int i, long j) {
                    SearchFragment.onBindingCreated$lambda$2$0$1$1(objectRef, set, listView, adapterView, view2, i, j);
                }
            });
        }
        HomeFragment.Companion companion = HomeFragment.INSTANCE;
        TvtypesChipsBinding tvtypesChipsBinding = homeSelectMainpageBindingInflate.tvtypesChipsScroll.tvtypesChips;
        List<TvType> list2 = searchFragment.selectedSearchTypes;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = listFilterProviderByPreferredMedia.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList2, ((MainAPI) it2.next()).getSupportedTypes());
        }
        companion.bindChips(tvtypesChipsBinding, list2, CollectionsKt.distinct(arrayList2), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.onBindingCreated$lambda$2$0$1$7(this.f$0, fragmentSearchBinding, arrayAdapter, objectRef, listFilterProviderByPreferredMedia, listView, set, z, (List) obj);
            }
        });
        if (materialButton != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SearchFragment.onBindingCreated$lambda$2$0$1$8(bottomSheetDialog, view2);
                }
            });
        }
        if (materialButton != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SearchFragment.onBindingCreated$lambda$2$0$1$9(bottomSheetDialog, view2);
                }
            });
        }
        if (materialButton2 != null) {
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SearchFragment.onBindingCreated$lambda$2$0$1$10(bottomSheetDialog, view2);
                }
            });
        }
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda20
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SearchFragment.onBindingCreated$lambda$2$0$1$11(set, searchFragment, set2, set3, fragmentSearchBinding, dialogInterface);
            }
        });
        onBindingCreated$lambda$2$0$1$updateList(arrayAdapter, objectRef, listFilterProviderByPreferredMedia, listView, set, z, CollectionsKt.toList(searchFragment.selectedSearchTypes));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0$1$1(Ref.ObjectRef objectRef, Set set, ListView listView, AdapterView adapterView, View view, int i, long j) {
        if (((Collection) objectRef.element).isEmpty()) {
            return;
        }
        String name = ((MainAPI) ((List) objectRef.element).get(i)).getName();
        if (set.contains(name)) {
            listView.setItemChecked(i, false);
            set.remove(name);
        } else {
            listView.setItemChecked(i, true);
            set.add(name);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00a9  */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, java.util.List] */
    private static final void onBindingCreated$lambda$2$0$1$updateList(ArrayAdapter<String> arrayAdapter, Ref.ObjectRef<List<MainAPI>> objectRef, List<? extends MainAPI> list, ListView listView, Set<String> set, boolean z, List<? extends TvType> list2) {
        String name;
        String str;
        DataStoreHelper.INSTANCE.setSearchPreferenceTags(list2);
        arrayAdapter.clear();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Set<TvType> supportedTypes = ((MainAPI) obj).getSupportedTypes();
            if (!(supportedTypes instanceof Collection) || !supportedTypes.isEmpty()) {
                Iterator<T> it = supportedTypes.iterator();
                while (it.hasNext()) {
                    if (list2.contains((TvType) it.next())) {
                        arrayList.add(obj);
                        break;
                    }
                }
            }
        }
        objectRef.element = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$onBindingCreated$lambda$2$0$1$updateList$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = ((MainAPI) t).getName().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = ((MainAPI) t2).getName().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        });
        List<MainAPI> list3 = objectRef.element;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (MainAPI mainAPI : list3) {
            if (z) {
                StringBuilder sb = new StringBuilder();
                String flagFromIso = SubtitleHelper.INSTANCE.getFlagFromIso(mainAPI.getLang());
                if (flagFromIso != null) {
                    str = flagFromIso + ' ';
                    if (str == null) {
                        str = "";
                    }
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(mainAPI.getName());
                name = sb.toString();
            } else {
                name = mainAPI.getName();
            }
            arrayList2.add(name);
        }
        ArrayList arrayList3 = arrayList2;
        List<MainAPI> list4 = objectRef.element;
        ArrayList<String> arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        Iterator<T> it2 = list4.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((MainAPI) it2.next()).getName());
        }
        int i = 0;
        for (String str2 : arrayList4) {
            int i2 = i + 1;
            if (listView != null) {
                listView.setItemChecked(i, set.contains(str2));
            }
            i = i2;
        }
        arrayAdapter.addAll(arrayList3);
        arrayAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$0$1$7(SearchFragment searchFragment, FragmentSearchBinding fragmentSearchBinding, ArrayAdapter arrayAdapter, Ref.ObjectRef objectRef, List list, ListView listView, Set set, boolean z, List list2) {
        Intrinsics.checkNotNullParameter(list2, "list");
        onBindingCreated$lambda$2$0$1$updateList(arrayAdapter, objectRef, list, listView, set, z, list2);
        if (!Intrinsics.areEqual(CollectionsKt.toSet(searchFragment.selectedSearchTypes), CollectionsKt.toSet(list2))) {
            searchFragment.selectedSearchTypes.clear();
            searchFragment.selectedSearchTypes.addAll(list2);
            HomeFragment.INSTANCE.updateChips(fragmentSearchBinding.tvtypesChipsScroll.tvtypesChips, searchFragment.selectedSearchTypes);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0$1$8(BottomSheetDialog bottomSheetDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0$1$9(BottomSheetDialog bottomSheetDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0$1$10(BottomSheetDialog bottomSheetDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$0$1$11(Set set, SearchFragment searchFragment, Set set2, Set set3, FragmentSearchBinding fragmentSearchBinding, DialogInterface dialogInterface) {
        DataStoreHelper.INSTANCE.setSearchPreferenceProviders(CollectionsKt.toList(set));
        searchFragment.selectedApis = set;
        if (Intrinsics.areEqual(set2, CollectionsKt.toSet(set)) && Intrinsics.areEqual(set3, CollectionsKt.toSet(searchFragment.selectedSearchTypes))) {
            return;
        }
        searchFragment.search(fragmentSearchBinding.mainSearch.getQuery().toString());
    }

    static final void onBindingCreated$lambda$4(SearchFragment searchFragment, View view, boolean z) {
        if (z) {
            return;
        }
        searchFragment.getSearchViewModel().clearSuggestions();
    }

    static final Unit onBindingCreated$lambda$5(ImageView imageView, FragmentSearchBinding fragmentSearchBinding, Resource it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof Resource.Success) {
            List<SearchResponse> list = ((ExpandableSearchList) ((Resource.Success) it).getValue()).getList();
            if (!list.isEmpty()) {
                RecyclerView.Adapter adapter = fragmentSearchBinding.searchAutofitResults.getAdapter();
                SearchAdapter searchAdapter = adapter instanceof SearchAdapter ? (SearchAdapter) adapter : null;
                if (searchAdapter != null) {
                    BaseAdapter.submitList$default(searchAdapter, list, null, 2, null);
                }
            }
            if (imageView != null) {
                imageView.setAlpha(1.0f);
            }
            fragmentSearchBinding.searchLoadingBar.setAlpha(0.0f);
        } else if (it instanceof Resource.Failure) {
            if (imageView != null) {
                imageView.setAlpha(1.0f);
            }
            fragmentSearchBinding.searchLoadingBar.setAlpha(0.0f);
        } else {
            if (!(it instanceof Resource.Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            if (imageView != null) {
                imageView.setAlpha(0.0f);
            }
            fragmentSearchBinding.searchLoadingBar.setAlpha(1.0f);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$6(ReentrantLock reentrantLock, FragmentSearchBinding fragmentSearchBinding, SearchFragment searchFragment, Map list) {
        List<SearchResponse> listFilterSearchResultByFilmQuality;
        Intrinsics.checkNotNullParameter(list, "list");
        try {
            try {
                reentrantLock.lock();
                final String[] strArr = (String[]) ArraysKt.reversedArray(DataStoreHelper.INSTANCE.getPinnedProviders());
                List listSortedWith = CollectionsKt.sortedWith(MapsKt.toList(list), new Comparator() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$onBindingCreated$lambda$6$$inlined$compareBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int iIndexOf = ArraysKt.indexOf(strArr, (String) ((Pair) t).component1());
                        Integer numValueOf = iIndexOf == -1 ? (Comparable) Integer.MAX_VALUE : Integer.valueOf(iIndexOf);
                        int iIndexOf2 = ArraysKt.indexOf(strArr, (String) ((Pair) t2).component1());
                        return ComparisonsKt.compareValues(numValueOf, iIndexOf2 == -1 ? (Comparable) Integer.MAX_VALUE : Integer.valueOf(iIndexOf2));
                    }
                });
                RecyclerView.Adapter adapter = fragmentSearchBinding.searchMasterRecycler.getAdapter();
                ParentItemAdapter parentItemAdapter = adapter instanceof ParentItemAdapter ? (ParentItemAdapter) adapter : null;
                if (parentItemAdapter != null) {
                    List<Pair> list2 = listSortedWith;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (Pair pair : list2) {
                        String str = (String) pair.component1();
                        ExpandableSearchList expandableSearchList = (ExpandableSearchList) pair.component2();
                        List<SearchResponse> list3 = expandableSearchList.getList();
                        Context context = searchFragment.getContext();
                        arrayList.add(new HomeViewModel.ExpandableHomepageList(new HomePageList(str, (context == null || (listFilterSearchResultByFilmQuality = AppContextUtils.INSTANCE.filterSearchResultByFilmQuality(context, list3)) == null) ? list3 : listFilterSearchResultByFilmQuality, false, 4, null), expandableSearchList.getCurrentPage(), expandableSearchList.getHasNext()));
                    }
                    BaseAdapter.submitList$default(parentItemAdapter, arrayList, null, 2, null);
                }
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
            return Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    static final Unit onBindingCreated$lambda$7(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$8(final SearchFragment searchFragment, HomeViewModel.ExpandableHomepageList item) {
        Intrinsics.checkNotNullParameter(item, "item");
        FragmentActivity activity = searchFragment.getActivity();
        searchFragment.bottomSheetDialog = activity != null ? HomeFragment.Companion.loadHomepageList$default(HomeFragment.INSTANCE, activity, item, null, new SearchFragment$onBindingCreated$masterAdapter$2$1(searchFragment, null), new Function0() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SearchFragment.onBindingCreated$lambda$8$0(this.f$0);
            }
        }, 2, null) : null;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$8$0(SearchFragment searchFragment) {
        searchFragment.bottomSheetDialog = null;
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$9(SearchFragment searchFragment, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Coroutines.INSTANCE.ioSafe(searchFragment, new SearchFragment$onBindingCreated$masterAdapter$3$1(searchFragment, name, null));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$10(final SearchFragment searchFragment, FragmentSearchBinding fragmentSearchBinding, SearchHistoryCallback click) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(click, "click");
        SearchHistoryItem item = click.getItem();
        int clickAction = click.getClickAction();
        if (clickAction != 0) {
            if (clickAction != 1) {
                if (clickAction == 2 && (activity = searchFragment.getActivity()) != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda21
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            SearchFragment.onBindingCreated$lambda$10$0$0(this.f$0, dialogInterface, i);
                        }
                    };
                    try {
                        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                        AlertDialog.Builder title = builder.setTitle(R.string.clear_history);
                        String string = activity.getString(R.string.delete_message);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        String str = String.format(string, Arrays.copyOf(new Object[]{activity.getString(R.string.history)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        AlertDialog alertDialogShow = title.setMessage(str).setPositiveButton(R.string.sort_clear, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
                        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
                    } catch (Exception e) {
                        ArchComponentExtKt.logError(e);
                    }
                }
            } else {
                if (item == null) {
                    return Unit.INSTANCE;
                }
                CloudStreamApp.INSTANCE.removeKey(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/search_history", item.getKey());
                searchFragment.getSearchViewModel().updateHistory();
            }
        } else {
            if (item == null) {
                return Unit.INSTANCE;
            }
            searchFragment.getSearchViewModel().clearSearch();
            if (!item.getType().isEmpty()) {
                HomeFragment.INSTANCE.updateChips(fragmentSearchBinding.tvtypesChipsScroll.tvtypesChips, CollectionsKt.toMutableList((Collection) item.getType()));
            }
            fragmentSearchBinding.mainSearch.setQuery(item.getSearchText(), true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$10$0$0(SearchFragment searchFragment, DialogInterface dialogInterface, int i) {
        if (i != -1) {
            return;
        }
        CloudStreamApp.INSTANCE.removeKeys(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/search_history");
        searchFragment.getSearchViewModel().updateHistory();
    }

    static final Unit onBindingCreated$lambda$11(FragmentSearchBinding fragmentSearchBinding, SearchFragment searchFragment, SearchSuggestionCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int clickAction = callback.getClickAction();
        if (clickAction == 0) {
            fragmentSearchBinding.mainSearch.setQuery(callback.getSuggestion(), true);
            searchFragment.getSearchViewModel().clearSuggestions();
        } else if (clickAction == 1) {
            fragmentSearchBinding.mainSearch.setQuery(callback.getSuggestion(), false);
        } else if (clickAction == 2) {
            searchFragment.getSearchViewModel().clearSuggestions();
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$13(FragmentSearchBinding fragmentSearchBinding, List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        RecyclerView.Adapter adapter = fragmentSearchBinding.searchHistoryRecycler.getAdapter();
        SearchHistoryAdaptor searchHistoryAdaptor = adapter instanceof SearchHistoryAdaptor ? (SearchHistoryAdaptor) adapter : null;
        if (searchHistoryAdaptor != null) {
            BaseAdapter.submitList$default(searchHistoryAdaptor, list, null, 2, null);
        }
        if (!list.isEmpty()) {
            fragmentSearchBinding.searchHistoryRecycler.scrollToPosition(0);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$14(FragmentSearchBinding fragmentSearchBinding, final SearchFragment searchFragment, List suggestions) {
        Intrinsics.checkNotNullParameter(suggestions, "suggestions");
        List list = suggestions;
        boolean zIsEmpty = list.isEmpty();
        RecyclerView searchSuggestionsRecycler = fragmentSearchBinding.searchSuggestionsRecycler;
        Intrinsics.checkNotNullExpressionValue(searchSuggestionsRecycler, "searchSuggestionsRecycler");
        searchSuggestionsRecycler.setVisibility(!zIsEmpty ? 0 : 8);
        RecyclerView.Adapter adapter = fragmentSearchBinding.searchSuggestionsRecycler.getAdapter();
        SearchSuggestionAdapter searchSuggestionAdapter = adapter instanceof SearchSuggestionAdapter ? (SearchSuggestionAdapter) adapter : null;
        if (searchSuggestionAdapter != null) {
            BaseAdapter.submitList$default(searchSuggestionAdapter, list, null, 2, null);
        }
        if (!Globals.INSTANCE.isLayout(1)) {
            if (!zIsEmpty) {
                fragmentSearchBinding.tvtypesChipsScroll.tvtypesChips.getRoot().setNextFocusDownId(R.id.search_suggestions_recycler);
                FragmentActivity activity = searchFragment.getActivity();
                if (activity != null) {
                    BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity, "SearchFragment", new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchFragment$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SearchFragment.onBindingCreated$lambda$14$0(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                        }
                    });
                }
            } else {
                fragmentSearchBinding.tvtypesChipsScroll.tvtypesChips.getRoot().setNextFocusDownId(R.id.search_history_recycler);
                FragmentActivity activity2 = searchFragment.getActivity();
                if (activity2 != null) {
                    BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity2, "SearchFragment");
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$14$0(SearchFragment searchFragment, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        searchFragment.getSearchViewModel().clearSuggestions();
        return Unit.INSTANCE;
    }
}

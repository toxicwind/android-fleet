package com.lagradost.cloudstream3.ui.quicksearch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.QuickSearchBinding;
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
import com.lagradost.cloudstream3.ui.search.ExpandableSearchList;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchHelper;
import com.lagradost.cloudstream3.ui.search.SearchViewModel;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: QuickSearchFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J \u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0019J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/ui/quicksearch/QuickSearchFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/QuickSearchBinding;", "<init>", "()V", QuickSearchFragment.PROVIDER_KEY, "", "", "searchViewModel", "Lcom/lagradost/cloudstream3/ui/search/SearchViewModel;", "bottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "fixLayout", "", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "search", "", "context", "Landroid/content/Context;", SearchIntents.EXTRA_QUERY, "isQuickSearch", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QuickSearchFragment extends BaseFragment<QuickSearchBinding> {
    public static final String AUTOSEARCH_KEY = "autosearch";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PROVIDER_KEY = "providers";
    private static Function1<? super SearchClickCallback, Unit> clickCallback;
    private BottomSheetDialog bottomSheetDialog;
    private Set<String> providers;
    private SearchViewModel searchViewModel;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: QuickSearchFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, QuickSearchBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, QuickSearchBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/QuickSearchBinding;", 0);
        }

        public final QuickSearchBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return QuickSearchBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ QuickSearchBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public QuickSearchFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    /* JADX INFO: compiled from: QuickSearchFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ3\u0010\u0007\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/quicksearch/QuickSearchFragment$Companion;", "", "<init>", "()V", "AUTOSEARCH_KEY", "", "PROVIDER_KEY", "pushSearch", "", "autoSearch", QuickSearchFragment.PROVIDER_KEY, "", "(Ljava/lang/String;[Ljava/lang/String;)V", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;Ljava/lang/String;[Ljava/lang/String;)V", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "getClickCallback", "()Lkotlin/jvm/functions/Function1;", "setClickCallback", "(Lkotlin/jvm/functions/Function1;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void pushSearch$default(Companion companion, String str, String[] strArr, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                strArr = null;
            }
            companion.pushSearch(str, strArr);
        }

        public final void pushSearch(String autoSearch, String[] providers) {
            pushSearch(CommonActivity.INSTANCE.getActivity(), autoSearch, providers);
        }

        public static /* synthetic */ void pushSearch$default(Companion companion, Activity activity, String str, String[] strArr, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                strArr = null;
            }
            companion.pushSearch(activity, str, strArr);
        }

        public final void pushSearch(Activity activity, String autoSearch, String[] providers) {
            UIHelper uIHelper = UIHelper.INSTANCE;
            int i = R.id.global_to_navigation_quick_search;
            Bundle bundle = new Bundle();
            if (providers != null) {
                bundle.putStringArray(QuickSearchFragment.PROVIDER_KEY, providers);
            }
            if (autoSearch != null) {
                bundle.putString(QuickSearchFragment.AUTOSEARCH_KEY, StringsKt.trim((CharSequence) StringsKt.removeSuffix(StringsKt.removeSuffix(StringsKt.removeSuffix(StringsKt.removeSuffix(StringsKt.trim((CharSequence) autoSearch).toString(), (CharSequence) "(DUB)"), (CharSequence) "(SUB)"), (CharSequence) "(Dub)"), (CharSequence) "(Sub)")).toString());
            }
            Unit unit = Unit.INSTANCE;
            UIHelper.navigate$default(uIHelper, activity, i, bundle, null, 4, null);
        }

        public final Function1<SearchClickCallback, Unit> getClickCallback() {
            return QuickSearchFragment.clickCallback;
        }

        public final void setClickCallback(Function1<? super SearchClickCallback, Unit> function1) {
            QuickSearchFragment.clickCallback = function1;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        AutofitRecyclerView autofitRecyclerView;
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        HomeFragment.Companion companion = HomeFragment.INSTANCE;
        UIHelper uIHelper = UIHelper.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.setCurrentSpan(UIHelper.getSpanCount$default(uIHelper, context, false, 1, null));
        QuickSearchBinding binding = getBinding();
        if (binding != null && (autofitRecyclerView = binding.quickSearchAutofitResults) != null) {
            autofitRecyclerView.setSpanCount(HomeFragment.INSTANCE.getCurrentSpan());
        }
        HomeFragment.INSTANCE.getConfigEvent().invoke();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentActivity activity = getActivity();
        if (activity != null && (window = activity.getWindow()) != null) {
            window.setSoftInputMode(4);
        }
        this.searchViewModel = (SearchViewModel) new ViewModelProvider(this).get(SearchViewModel.class);
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            AppContextUtils.INSTANCE.ownShow(bottomSheetDialog);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        clickCallback = null;
    }

    public final boolean search(Context context, String query, boolean isQuickSearch) {
        List<MainAPI> listFilterProviderByPreferredMedia;
        Intrinsics.checkNotNullParameter(query, "query");
        Set<String> set = this.providers;
        SearchViewModel searchViewModel = null;
        if (set == null) {
            if (context == null || (listFilterProviderByPreferredMedia = AppContextUtils.INSTANCE.filterProviderByPreferredMedia(context, false)) == null) {
                set = null;
            } else {
                List<MainAPI> list = listFilterProviderByPreferredMedia;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((MainAPI) it.next()).getName());
                }
                set = CollectionsKt.toSet(arrayList);
            }
        }
        if (set == null) {
            return false;
        }
        SearchViewModel searchViewModel2 = this.searchViewModel;
        if (searchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchViewModel");
        } else {
            searchViewModel = searchViewModel2;
        }
        searchViewModel.searchAndCancel(query, set, false, isQuickSearch);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0047  */
    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final QuickSearchBinding binding) {
        final boolean hasQuickSearch;
        String string;
        String[] stringArray;
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        if (arguments != null && (stringArray = arguments.getStringArray(PROVIDER_KEY)) != null) {
            this.providers = ArraysKt.toSet(stringArray);
        }
        Set<String> set = this.providers;
        boolean z = set != null && set.size() == 1;
        SearchViewModel searchViewModel = null;
        if (z) {
            APIHolder aPIHolder = APIHolder.INSTANCE;
            Set<String> set2 = this.providers;
            MainAPI apiFromNameNull = aPIHolder.getApiFromNameNull(set2 != null ? (String) CollectionsKt.first(set2) : null);
            if (apiFromNameNull != null) {
                hasQuickSearch = apiFromNameNull.getHasQuickSearch();
            } else {
                hasQuickSearch = false;
            }
        } else {
            hasQuickSearch = false;
        }
        Set<String> set3 = this.providers;
        final String str = set3 != null ? (String) CollectionsKt.firstOrNull(set3) : null;
        if (z && str != null) {
            AutofitRecyclerView autofitRecyclerView = binding.quickSearchAutofitResults;
            Intrinsics.checkNotNull(autofitRecyclerView);
            BaseAdapterKt.setRecycledViewPool(autofitRecyclerView, SearchAdapter.INSTANCE.getSharedPool());
            autofitRecyclerView.setAdapter(new SearchAdapter(autofitRecyclerView, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return QuickSearchFragment.onBindingCreated$lambda$1$0((SearchClickCallback) obj);
                }
            }, 2, null));
            binding.quickSearchAutofitResults.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment.onBindingCreated.3
                private int expandCount;

                public final int getExpandCount() {
                    return this.expandCount;
                }

                public final void setExpandCount(int i) {
                    this.expandCount = i;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    super.onScrollStateChanged(recyclerView, newState);
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (adapter instanceof SearchAdapter) {
                        SearchAdapter searchAdapter = (SearchAdapter) adapter;
                        int itemCount = searchAdapter.getItemCount();
                        boolean hasNext = searchAdapter.getHasNext();
                        if (AppContextUtils.INSTANCE.isRecyclerScrollable(recyclerView) || !hasNext || this.expandCount == itemCount) {
                            return;
                        }
                        this.expandCount = itemCount;
                        Coroutines.INSTANCE.ioSafe(this, new QuickSearchFragment$onBindingCreated$3$onScrollStateChanged$1(QuickSearchFragment.this, str, null));
                    }
                }
            });
            try {
                SearchView searchView = binding.quickSearch;
                String string2 = getString(R.string.search_hint_site);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String str2 = String.format(string2, Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                searchView.setQueryHint(str2);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        } else {
            RecyclerView quickSearchMasterRecycler = binding.quickSearchMasterRecycler;
            Intrinsics.checkNotNullExpressionValue(quickSearchMasterRecycler, "quickSearchMasterRecycler");
            BaseAdapterKt.setRecycledViewPool(quickSearchMasterRecycler, ParentItemAdapter.INSTANCE.getSharedPool());
            binding.quickSearchMasterRecycler.setAdapter(new ParentItemAdapter(388512278, new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return QuickSearchFragment.onBindingCreated$lambda$2((SearchClickCallback) obj);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return QuickSearchFragment.onBindingCreated$lambda$3(this.f$0, (HomeViewModel.ExpandableHomepageList) obj);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return QuickSearchFragment.onBindingCreated$lambda$4(this.f$0, (String) obj);
                }
            }));
            binding.quickSearchMasterRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
        AutofitRecyclerView quickSearchAutofitResults = binding.quickSearchAutofitResults;
        Intrinsics.checkNotNullExpressionValue(quickSearchAutofitResults, "quickSearchAutofitResults");
        quickSearchAutofitResults.setVisibility(z ? 0 : 8);
        RecyclerView quickSearchMasterRecycler2 = binding.quickSearchMasterRecycler;
        Intrinsics.checkNotNullExpressionValue(quickSearchMasterRecycler2, "quickSearchMasterRecycler");
        quickSearchMasterRecycler2.setVisibility(z ? 8 : 0);
        final ReentrantLock reentrantLock = new ReentrantLock();
        QuickSearchFragment quickSearchFragment = this;
        SearchViewModel searchViewModel2 = this.searchViewModel;
        if (searchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchViewModel");
            searchViewModel2 = null;
        }
        LifecycleKt.observe(quickSearchFragment, searchViewModel2.getCurrentSearch(), new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QuickSearchFragment.onBindingCreated$lambda$5(reentrantLock, binding, this, (Map) obj);
            }
        });
        final ImageView imageView = (ImageView) binding.quickSearch.findViewById(androidx.appcompat.R.id.search_close_btn);
        binding.quickSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment.onBindingCreated.8
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                QuickSearchFragment quickSearchFragment2 = QuickSearchFragment.this;
                if (!quickSearchFragment2.search(quickSearchFragment2.getContext(), query, false)) {
                    return true;
                }
                UIHelper.INSTANCE.hideKeyboard(binding.quickSearch);
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                if (hasQuickSearch) {
                    QuickSearchFragment quickSearchFragment2 = QuickSearchFragment.this;
                    quickSearchFragment2.search(quickSearchFragment2.getContext(), newText, true);
                }
                return true;
            }
        });
        binding.quickSearchLoadingBar.setAlpha(0.0f);
        SearchViewModel searchViewModel3 = this.searchViewModel;
        if (searchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchViewModel");
        } else {
            searchViewModel = searchViewModel3;
        }
        LifecycleKt.observe(quickSearchFragment, searchViewModel.getSearchResponse(), new Function1() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return QuickSearchFragment.onBindingCreated$lambda$6(imageView, binding, this, (Resource) obj);
            }
        });
        if (Globals.INSTANCE.isLayout(5)) {
            ImageView imageView2 = binding.quickSearchBack;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuickSearchFragment.onBindingCreated$lambda$7$0(this.f$0, view);
                }
            });
        }
        if (Globals.INSTANCE.isLayout(2)) {
            binding.quickSearch.requestFocus();
        }
        Bundle arguments2 = getArguments();
        if (arguments2 == null || (string = arguments2.getString(AUTOSEARCH_KEY)) == null) {
            return;
        }
        binding.quickSearch.setQuery(string, true);
        Bundle arguments3 = getArguments();
        if (arguments3 != null) {
            arguments3.remove(AUTOSEARCH_KEY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$1$0(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$2(SearchClickCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        SearchHelper.INSTANCE.handleSearchClickCallback(callback);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$3(final QuickSearchFragment quickSearchFragment, HomeViewModel.ExpandableHomepageList item) {
        Intrinsics.checkNotNullParameter(item, "item");
        FragmentActivity activity = quickSearchFragment.getActivity();
        quickSearchFragment.bottomSheetDialog = activity != null ? HomeFragment.Companion.loadHomepageList$default(HomeFragment.INSTANCE, activity, item, null, new QuickSearchFragment$onBindingCreated$5$1(quickSearchFragment, null), new Function0() { // from class: com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return QuickSearchFragment.onBindingCreated$lambda$3$0(this.f$0);
            }
        }, 2, null) : null;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$0(QuickSearchFragment quickSearchFragment) {
        quickSearchFragment.bottomSheetDialog = null;
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$4(QuickSearchFragment quickSearchFragment, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Coroutines.INSTANCE.ioSafe(quickSearchFragment, new QuickSearchFragment$onBindingCreated$6$1(quickSearchFragment, name, null));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(ReentrantLock reentrantLock, QuickSearchBinding quickSearchBinding, QuickSearchFragment quickSearchFragment, Map list) {
        List<SearchResponse> listFilterSearchResultByFilmQuality;
        Intrinsics.checkNotNullParameter(list, "list");
        try {
            try {
                reentrantLock.lock();
                RecyclerView.Adapter adapter = quickSearchBinding.quickSearchMasterRecycler.getAdapter();
                ParentItemAdapter parentItemAdapter = adapter instanceof ParentItemAdapter ? (ParentItemAdapter) adapter : null;
                if (parentItemAdapter != null) {
                    ArrayList arrayList = new ArrayList(list.size());
                    for (Map.Entry entry : list.entrySet()) {
                        List<SearchResponse> list2 = ((ExpandableSearchList) entry.getValue()).getList();
                        Context context = quickSearchFragment.getContext();
                        arrayList.add(new HomeViewModel.ExpandableHomepageList(new HomePageList((String) entry.getKey(), (context == null || (listFilterSearchResultByFilmQuality = AppContextUtils.INSTANCE.filterSearchResultByFilmQuality(context, list2)) == null) ? list2 : listFilterSearchResultByFilmQuality, false, 4, null), ((ExpandableSearchList) entry.getValue()).getCurrentPage(), ((ExpandableSearchList) entry.getValue()).getHasNext()));
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

    static final Unit onBindingCreated$lambda$6(ImageView imageView, QuickSearchBinding quickSearchBinding, QuickSearchFragment quickSearchFragment, Resource it) {
        List<SearchResponse> list;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof Resource.Success) {
            ExpandableSearchList expandableSearchList = (ExpandableSearchList) ((Resource.Success) it).getValue();
            RecyclerView.Adapter adapter = quickSearchBinding.quickSearchAutofitResults.getAdapter();
            SearchAdapter searchAdapter = adapter instanceof SearchAdapter ? (SearchAdapter) adapter : null;
            if (searchAdapter != null) {
                SearchAdapter searchAdapter2 = searchAdapter;
                Context context = quickSearchFragment.getContext();
                if (context == null || (list = AppContextUtils.INSTANCE.filterSearchResultByFilmQuality(context, expandableSearchList.getList())) == null) {
                    list = expandableSearchList.getList();
                }
                BaseAdapter.submitList$default(searchAdapter2, list, null, 2, null);
            }
            if (searchAdapter != null) {
                searchAdapter.setHasNext(expandableSearchList.getHasNext());
            }
            if (imageView != null) {
                imageView.setAlpha(1.0f);
            }
            quickSearchBinding.quickSearchLoadingBar.setAlpha(0.0f);
        } else if (it instanceof Resource.Failure) {
            if (imageView != null) {
                imageView.setAlpha(1.0f);
            }
            quickSearchBinding.quickSearchLoadingBar.setAlpha(0.0f);
        } else {
            if (!(it instanceof Resource.Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            if (imageView != null) {
                imageView.setAlpha(0.0f);
            }
            quickSearchBinding.quickSearchLoadingBar.setAlpha(1.0f);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$7$0(QuickSearchFragment quickSearchFragment, View view) {
        FragmentActivity activity = quickSearchFragment.getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }
}

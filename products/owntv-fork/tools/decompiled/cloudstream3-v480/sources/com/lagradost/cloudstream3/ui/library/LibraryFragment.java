package com.lagradost.cloudstream3.ui.library;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.FragmentLibraryBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: LibraryFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0017J \u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentLibraryBinding;", "<init>", "()V", "libraryViewModel", "Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;", "getLibraryViewModel", "()Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;", "libraryViewModel$delegate", "Lkotlin/Lazy;", "toggleRandomButton", "", "pickLayout", "", "()Ljava/lang/Integer;", "onSaveInstanceState", "", "outState", "Landroid/os/Bundle;", "updateRandomVisibility", "binding", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "savedInstanceState", "loadLibraryItem", "syncName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "syncId", "", "card", "Lcom/lagradost/cloudstream3/SearchResponse;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "sortChangeClickListener", "Landroid/view/View$OnClickListener;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LibraryFragment extends BaseFragment<FragmentLibraryBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String VIEWPAGER_ITEM_KEY = "viewpager_item";

    /* JADX INFO: renamed from: libraryViewModel$delegate, reason: from kotlin metadata */
    private final Lazy libraryViewModel;
    private final View.OnClickListener sortChangeClickListener;
    private boolean toggleRandomButton;

    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LibraryOpenerType.values().length];
            try {
                iArr[LibraryOpenerType.Default.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LibraryOpenerType.None.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LibraryOpenerType.Provider.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LibraryOpenerType.Browser.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LibraryOpenerType.Search.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.library.LibraryFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<View, FragmentLibraryBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, FragmentLibraryBinding.class, "bind", "bind(Landroid/view/View;)Lcom/lagradost/cloudstream3/databinding/FragmentLibraryBinding;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FragmentLibraryBinding invoke(View p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentLibraryBinding.bind(p0);
        }
    }

    public LibraryFragment() {
        super(new BaseFragment.BindingCreator.Bind(AnonymousClass1.INSTANCE));
        final LibraryFragment libraryFragment = this;
        final Function0 function0 = null;
        this.libraryViewModel = FragmentViewModelLazyKt.createViewModelLazy(libraryFragment, Reflection.getOrCreateKotlinClass(LibraryViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return libraryFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? libraryFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return libraryFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.sortChangeClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFragment.sortChangeClickListener$lambda$0(this.f$0, view);
            }
        };
    }

    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/lagradost/cloudstream3/ui/library/LibraryFragment;", "VIEWPAGER_ITEM_KEY", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LibraryFragment newInstance() {
            return new LibraryFragment();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LibraryViewModel getLibraryViewModel() {
        return (LibraryViewModel) this.libraryViewModel.getValue();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public Integer pickLayout() {
        return Integer.valueOf(Globals.INSTANCE.isLayout(1) ? R.layout.fragment_library : R.layout.fragment_library_tv);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        ViewPager2 viewPager2;
        Intrinsics.checkNotNullParameter(outState, "outState");
        FragmentLibraryBinding binding = getBinding();
        if (binding != null && (viewPager2 = binding.viewpager) != null) {
            outState.putInt(VIEWPAGER_ITEM_KEY, viewPager2.getCurrentItem());
        }
        super.onSaveInstanceState(outState);
    }

    private final void updateRandomVisibility(FragmentLibraryBinding binding) {
        List list;
        if (!this.toggleRandomButton) {
            ExtendedFloatingActionButton libraryRandom = binding.libraryRandom;
            Intrinsics.checkNotNullExpressionValue(libraryRandom, "libraryRandom");
            libraryRandom.setVisibility(8);
            ImageView libraryRandomButtonTv = binding.libraryRandomButtonTv;
            Intrinsics.checkNotNullExpressionValue(libraryRandomButtonTv, "libraryRandomButtonTv");
            libraryRandomButtonTv.setVisibility(8);
            return;
        }
        Integer value = getLibraryViewModel().getCurrentPage().getValue();
        int iIntValue = value != null ? value.intValue() : 0;
        Resource<List<SyncAPI.Page>> value2 = getLibraryViewModel().getPages().getValue();
        Resource.Success success = value2 instanceof Resource.Success ? (Resource.Success) value2 : null;
        if (success == null || (list = (List) success.getValue()) == null) {
            return;
        }
        boolean zIsEmpty = ((SyncAPI.Page) list.get(iIntValue)).getItems().isEmpty();
        boolean zIsLayout = Globals.INSTANCE.isLayout(1);
        ExtendedFloatingActionButton libraryRandom2 = binding.libraryRandom;
        Intrinsics.checkNotNullExpressionValue(libraryRandom2, "libraryRandom");
        libraryRandom2.setVisibility(zIsLayout && !zIsEmpty ? 0 : 8);
        ImageView libraryRandomButtonTv2 = binding.libraryRandomButtonTv;
        Intrinsics.checkNotNullExpressionValue(libraryRandomButtonTv2, "libraryRandomButtonTv");
        libraryRandomButtonTv2.setVisibility((zIsLayout || zIsEmpty) ? false : true ? 0 : 8);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), !Globals.INSTANCE.isLayout(1), false, false, false, 462, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentLibraryBinding binding, final Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(binding, "binding");
        binding.sortFab.setOnClickListener(this.sortChangeClickListener);
        binding.librarySort.setOnClickListener(this.sortChangeClickListener);
        TextView textView = (TextView) binding.libraryRoot.findViewById(androidx.appcompat.R.id.search_src_text);
        if (textView != null) {
            textView.setTag("tv_no_focus_tag");
            textView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda19
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    LibraryFragment.onBindingCreated$lambda$0$0(binding, view, z);
                }
            });
        }
        final Runnable runnable = new Runnable() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                this.getLibraryViewModel().sort(ListSorting.Query, binding.mainSearch.getQuery().toString());
            }
        };
        binding.mainSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment.onBindingCreated.2
            private boolean hasInitialized;

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                LibraryFragment.this.getLibraryViewModel().sort(ListSorting.Query, query);
                return true;
            }

            public final boolean getHasInitialized() {
                return this.hasInitialized;
            }

            public final void setHasInitialized(boolean z) {
                this.hasInitialized = z;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                if (!this.hasInitialized) {
                    this.hasInitialized = true;
                    return true;
                }
                binding.mainSearch.removeCallbacks(runnable);
                binding.mainSearch.postDelayed(runnable, 1000L);
                return true;
            }
        });
        getLibraryViewModel().reloadPages(false);
        binding.listSelector.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFragment.onBindingCreated$lambda$2(this.f$0, view);
            }
        });
        Context context = getContext();
        if (context != null) {
            this.toggleRandomButton = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.random_button_key), false);
            binding.libraryRandom.setVisibility(8);
            binding.libraryRandomButtonTv.setVisibility(8);
        }
        binding.providerSelector.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFragment.onBindingCreated$lambda$11(this.f$0, view);
            }
        });
        binding.viewpager.setPageTransformer(new LibraryScrollTransformer());
        binding.viewpager.setAdapter(new ViewpagerAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryFragment.onBindingCreated$lambda$12(binding, ((Boolean) obj).booleanValue());
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryFragment.onBindingCreated$lambda$13(this.f$0, (SearchClickCallback) obj);
            }
        }));
        binding.viewpager.setOffscreenPageLimit(2);
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        ViewPager2 viewpager = binding.viewpager;
        Intrinsics.checkNotNullExpressionValue(viewpager, "viewpager");
        AppContextUtils.reduceDragSensitivity$default(appContextUtils, viewpager, 0, 1, null);
        binding.searchBar.setExpanded(true);
        final Runnable runnable2 = new Runnable() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                LibraryFragment.onBindingCreated$lambda$15(binding, this);
            }
        };
        final Runnable runnable3 = new Runnable() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                LibraryFragment.onBindingCreated$lambda$16(binding);
            }
        };
        final Handler handler = new Handler(Looper.getMainLooper());
        LibraryFragment libraryFragment = this;
        LifecycleKt.observe(libraryFragment, getLibraryViewModel().getPages(), new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryFragment.onBindingCreated$lambda$17(handler, runnable2, binding, runnable3, this, savedInstanceState, (Resource) obj);
            }
        });
        LifecycleKt.observe(libraryFragment, getLibraryViewModel().getCurrentPage(), new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryFragment.onBindingCreated$lambda$18(this.f$0, binding, ((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0(FragmentLibraryBinding fragmentLibraryBinding, View view, boolean z) {
        fragmentLibraryBinding.searchBar.setExpanded(true);
    }

    static final void onBindingCreated$lambda$2(final LibraryFragment libraryFragment, View view) {
        final List<String> availableApiNames = libraryFragment.getLibraryViewModel().getAvailableApiNames();
        String value = libraryFragment.getLibraryViewModel().getCurrentApiName().getValue();
        FragmentActivity activity = libraryFragment.getActivity();
        if (activity != null) {
            int iIndexOf = CollectionsKt.indexOf((List<? extends String>) availableApiNames, value);
            UiText uiTextTxt = TextUtilKt.txt(R.string.select_library, new Object[0]);
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            SingleSelectionHelper.INSTANCE.showBottomDialog(activity, availableApiNames, iIndexOf, uiTextTxt.asString(context), false, new Function0() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryFragment.onBindingCreated$lambda$2$1(availableApiNames, libraryFragment, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$1(List list, LibraryFragment libraryFragment, int i) {
        String str = (String) CollectionsKt.getOrNull(list, i);
        if (str == null) {
            return Unit.INSTANCE;
        }
        libraryFragment.getLibraryViewModel().switchList(str);
        return Unit.INSTANCE;
    }

    static /* synthetic */ void onBindingCreated$showPluginSelectionDialog$default(Activity activity, String str, SyncIdName syncIdName, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        onBindingCreated$showPluginSelectionDialog(activity, str, syncIdName, str2);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0136  */
    /* JADX WARN: Code duplicated, block: B:43:0x014c A[Catch: Exception -> 0x015e, TRY_LEAVE, TryCatch #0 {Exception -> 0x015e, blocks: (B:19:0x00df, B:22:0x00eb, B:27:0x0105, B:34:0x0130, B:37:0x0137, B:43:0x014c, B:42:0x0147, B:33:0x0126, B:26:0x00fb, B:30:0x010c, B:39:0x013b, B:23:0x00ed), top: B:67:0x00df, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0163  */
    /* JADX WARN: Code duplicated, block: B:61:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:72:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static final void onBindingCreated$showPluginSelectionDialog(Activity activity, final String str, final SyncIdName syncIdName, String str2) {
        Object objDecodeFromString;
        int iIndexOf;
        int i;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        List listListOf;
        AtomicList<MainAPI> atomicListFilter = APIHolder.INSTANCE.getAllProviders().filter(new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LibraryFragment.onBindingCreated$showPluginSelectionDialog$lambda$4(syncIdName, (MainAPI) obj));
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicListFilter, 10));
        Iterator<MainAPI> it = atomicListFilter.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        ArrayList arrayList2 = arrayList;
        MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(str2);
        List listPlus = CollectionsKt.plus((Collection) arrayList2, (Iterable) ((apiFromNameNull == null || (listListOf = CollectionsKt.listOf(apiFromNameNull.getName())) == null) ? CollectionsKt.emptyList() : listListOf));
        final List listListOf2 = CollectionsKt.listOf((Object[]) new LibraryOpenerType[]{LibraryOpenerType.Default, LibraryOpenerType.None, LibraryOpenerType.Browser, LibraryOpenerType.Search});
        List list = listListOf2;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList3.add(TextUtilKt.txt(((LibraryOpenerType) it2.next()).getStringRes(), new Object[0]).asString(activity));
        }
        final List<String> listPlus2 = CollectionsKt.plus((Collection) arrayList3, (Iterable) listPlus);
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str3 = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/library_folder";
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str3, str), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(LibraryOpener.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$onBindingCreated$showPluginSelectionDialog$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$onBindingCreated$showPluginSelectionDialog$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$onBindingCreated$showPluginSelectionDialog$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(LibraryOpener.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$onBindingCreated$showPluginSelectionDialog$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else {
            objDecodeFromString = null;
        }
        LibraryOpener libraryOpener = (LibraryOpener) objDecodeFromString;
        if (libraryOpener != null) {
            if (libraryOpener.getOpenType() == LibraryOpenerType.Provider) {
                ProviderLibraryData providerData = libraryOpener.getProviderData();
                if ((providerData != null ? providerData.getApiName() : null) != null) {
                    Integer numValueOf = Integer.valueOf(listPlus.indexOf(libraryOpener.getProviderData().getApiName()));
                    Integer num = numValueOf.intValue() != -1 ? numValueOf : null;
                    if (num != null) {
                        iIndexOf = num.intValue() + listListOf2.size();
                    } else {
                        i = 0;
                    }
                } else {
                    iIndexOf = listListOf2.indexOf(libraryOpener.getOpenType());
                }
            } else {
                iIndexOf = listListOf2.indexOf(libraryOpener.getOpenType());
            }
            i = iIndexOf;
        } else {
            i = 0;
        }
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, listPlus2, i, TextUtilKt.txt(R.string.open_with, new Object[0]).asString(activity), false, new Function0() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LibraryFragment.onBindingCreated$showPluginSelectionDialog$lambda$10(listListOf2, listPlus2, str, ((Integer) obj).intValue());
            }
        });
    }

    static final boolean onBindingCreated$showPluginSelectionDialog$lambda$4(SyncIdName syncIdName, MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getSupportedSyncNames().contains(syncIdName);
    }

    static final Unit onBindingCreated$showPluginSelectionDialog$lambda$10(List list, List list2, String str, int i) {
        LibraryOpener libraryOpener;
        if (i < list.size()) {
            libraryOpener = new LibraryOpener((LibraryOpenerType) list.get(i), null);
        } else {
            libraryOpener = new LibraryOpener(LibraryOpenerType.Provider, new ProviderLibraryData((String) list2.get(i)));
        }
        CloudStreamApp.INSTANCE.setKey(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/library_folder", str, libraryOpener);
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$11(LibraryFragment libraryFragment, View view) {
        SyncIdName syncIdName;
        FragmentActivity activity;
        SyncRepo currentSyncApi = libraryFragment.getLibraryViewModel().getCurrentSyncApi();
        if (currentSyncApi == null || (syncIdName = currentSyncApi.getSyncIdName()) == null || (activity = libraryFragment.getActivity()) == null) {
            return;
        }
        onBindingCreated$showPluginSelectionDialog$default(activity, syncIdName.name(), syncIdName, null, 4, null);
    }

    static final Unit onBindingCreated$lambda$12(FragmentLibraryBinding fragmentLibraryBinding, boolean z) {
        if (z) {
            fragmentLibraryBinding.sortFab.shrink();
            fragmentLibraryBinding.libraryRandom.shrink();
        } else {
            fragmentLibraryBinding.sortFab.extend();
            fragmentLibraryBinding.libraryRandom.extend();
        }
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$15(FragmentLibraryBinding fragmentLibraryBinding, LibraryFragment libraryFragment) {
        GridView gridView = fragmentLibraryBinding.gridview;
        UIHelper uIHelper = UIHelper.INSTANCE;
        Context context = fragmentLibraryBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        gridView.setNumColumns(UIHelper.getSpanCount$default(uIHelper, context, false, 1, null));
        GridView gridView2 = fragmentLibraryBinding.gridview;
        Context context2 = libraryFragment.getContext();
        gridView2.setAdapter((ListAdapter) (context2 != null ? new LoadingPosterAdapter(context2, 18) : null));
        LinearLayout libraryLoadingOverlay = fragmentLibraryBinding.libraryLoadingOverlay;
        Intrinsics.checkNotNullExpressionValue(libraryLoadingOverlay, "libraryLoadingOverlay");
        libraryLoadingOverlay.setVisibility(0);
        fragmentLibraryBinding.libraryLoadingShimmer.startShimmer();
        TextView emptyListTextview = fragmentLibraryBinding.emptyListTextview;
        Intrinsics.checkNotNullExpressionValue(emptyListTextview, "emptyListTextview");
        emptyListTextview.setVisibility(8);
    }

    static final void onBindingCreated$lambda$16(FragmentLibraryBinding fragmentLibraryBinding) {
        fragmentLibraryBinding.gridview.setAdapter((ListAdapter) null);
        LinearLayout libraryLoadingOverlay = fragmentLibraryBinding.libraryLoadingOverlay;
        Intrinsics.checkNotNullExpressionValue(libraryLoadingOverlay, "libraryLoadingOverlay");
        libraryLoadingOverlay.setVisibility(8);
        fragmentLibraryBinding.libraryLoadingShimmer.stopShimmer();
    }

    static final Unit onBindingCreated$lambda$17(Handler handler, Runnable runnable, final FragmentLibraryBinding fragmentLibraryBinding, Runnable runnable2, final LibraryFragment libraryFragment, Bundle bundle, Resource resource) {
        boolean z;
        int i;
        Intrinsics.checkNotNullParameter(resource, "resource");
        if (resource instanceof Resource.Success) {
            handler.removeCallbacks(runnable);
            final List list = (List) ((Resource.Success) resource).getValue();
            List<SyncAPI.Page> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    if (!((SyncAPI.Page) it.next()).getItems().isEmpty()) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            TextView emptyListTextview = fragmentLibraryBinding.emptyListTextview;
            Intrinsics.checkNotNullExpressionValue(emptyListTextview, "emptyListTextview");
            emptyListTextview.setVisibility(z ? 0 : 8);
            if (z) {
                if (libraryFragment.getLibraryViewModel().getAvailableApiNames().size() > 1) {
                    fragmentLibraryBinding.emptyListTextview.setText(R.string.empty_library_logged_in_message);
                } else {
                    fragmentLibraryBinding.emptyListTextview.setText(R.string.empty_library_no_accounts_message);
                }
            }
            RecyclerView.Adapter adapter = fragmentLibraryBinding.viewpager.getAdapter();
            ViewpagerAdapter viewpagerAdapter = adapter instanceof ViewpagerAdapter ? (ViewpagerAdapter) adapter : null;
            if (viewpagerAdapter != null) {
                ViewpagerAdapter viewpagerAdapter2 = viewpagerAdapter;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (SyncAPI.Page page : list2) {
                    arrayList.add(SyncAPI.Page.copy$default(page, null, new CopyOnWriteArrayList(page.getItems()), 1, null));
                }
                BaseAdapter.submitList$default(viewpagerAdapter2, arrayList, null, 2, null);
            }
            View childAt = fragmentLibraryBinding.viewpager.getChildAt(0);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
            ((RecyclerView) childAt).setTag("tv_no_focus_tag");
            Integer value = libraryFragment.getLibraryViewModel().getCurrentPage().getValue();
            if (value != null) {
                fragmentLibraryBinding.viewpager.setCurrentItem(value.intValue(), false);
                fragmentLibraryBinding.searchBar.setExpanded(true);
            }
            if (libraryFragment.toggleRandomButton) {
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda14
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        LibraryFragment.onBindingCreated$lambda$17$1$3(this.f$0, list, view);
                    }
                };
                fragmentLibraryBinding.libraryRandom.setOnClickListener(onClickListener);
                fragmentLibraryBinding.libraryRandomButtonTv.setOnClickListener(onClickListener);
            }
            libraryFragment.updateRandomVisibility(fragmentLibraryBinding);
            handler.postDelayed(runnable2, 300L);
            if (bundle != null && (i = bundle.getInt(VIEWPAGER_ITEM_KEY)) >= 0) {
                fragmentLibraryBinding.viewpager.setCurrentItem(i, false);
                bundle.putInt(VIEWPAGER_ITEM_KEY, -1);
            }
            new TabLayoutMediator(fragmentLibraryBinding.libraryTabLayout, fragmentLibraryBinding.viewpager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda15
                @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                public final void onConfigureTab(TabLayout.Tab tab, int i2) {
                    LibraryFragment.onBindingCreated$lambda$17$1$7(list, libraryFragment, fragmentLibraryBinding, fragmentLibraryBinding, tab, i2);
                }
            }).attach();
            fragmentLibraryBinding.libraryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$onBindingCreated$9$1$6
                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabReselected(TabLayout.Tab tab) {
                }

                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabSelected(TabLayout.Tab tab) {
                    libraryFragment.getLibraryViewModel().switchPage(fragmentLibraryBinding.libraryTabLayout.getSelectedTabPosition());
                }
            });
        } else if (resource instanceof Resource.Loading) {
            handler.postDelayed(runnable, 200L);
        } else {
            if (!(resource instanceof Resource.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            runnable2.run();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$17$1$3(LibraryFragment libraryFragment, List list, View view) {
        SyncIdName syncIdName;
        SyncAPI.LibraryItem libraryItem;
        Integer value = libraryFragment.getLibraryViewModel().getCurrentPage().getValue();
        int iIntValue = value != null ? value.intValue() : 0;
        SyncRepo currentSyncApi = libraryFragment.getLibraryViewModel().getCurrentSyncApi();
        if (currentSyncApi == null || (syncIdName = currentSyncApi.getSyncIdName()) == null || (libraryItem = (SyncAPI.LibraryItem) CollectionsKt.randomOrNull(((SyncAPI.Page) list.get(iIntValue)).getItems(), Random.INSTANCE)) == null) {
            return;
        }
        libraryFragment.loadLibraryItem(syncIdName, libraryItem.getSyncId(), libraryItem);
    }

    private static final void onBindingCreated$lambda$17$1$hideViewpager(FragmentLibraryBinding fragmentLibraryBinding, int i) {
        if (i < 3) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        long j = i;
        long j2 = 50 * j;
        alphaAnimation.setDuration(j2);
        alphaAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(j2);
        alphaAnimation2.setStartOffset(j * 100);
        alphaAnimation2.setFillAfter(true);
        fragmentLibraryBinding.viewpager.startAnimation(alphaAnimation);
        fragmentLibraryBinding.viewpager.startAnimation(alphaAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$17$1$7(List list, LibraryFragment libraryFragment, final FragmentLibraryBinding fragmentLibraryBinding, final FragmentLibraryBinding fragmentLibraryBinding2, TabLayout.Tab tab, final int i) {
        UiText title;
        Intrinsics.checkNotNullParameter(tab, "tab");
        SyncAPI.Page page = (SyncAPI.Page) CollectionsKt.getOrNull(list, i);
        tab.setText((page == null || (title = page.getTitle()) == null) ? null : title.asStringNull(libraryFragment.getContext()));
        tab.view.setTag("tv_no_focus_tag");
        tab.view.setNextFocusDownId(R.id.search_result_root);
        tab.view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFragment.onBindingCreated$lambda$17$1$7$0(fragmentLibraryBinding, i, fragmentLibraryBinding2, view);
            }
        });
        tab.view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                LibraryFragment.onBindingCreated$lambda$17$1$7$1(fragmentLibraryBinding, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$17$1$7$0(FragmentLibraryBinding fragmentLibraryBinding, int i, FragmentLibraryBinding fragmentLibraryBinding2, View view) {
        onBindingCreated$lambda$17$1$hideViewpager(fragmentLibraryBinding2, Math.abs(i - fragmentLibraryBinding.viewpager.getCurrentItem()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$17$1$7$1(FragmentLibraryBinding fragmentLibraryBinding, View view, boolean z) {
        fragmentLibraryBinding.searchBar.setExpanded(true);
    }

    static final Unit onBindingCreated$lambda$18(LibraryFragment libraryFragment, FragmentLibraryBinding fragmentLibraryBinding, int i) {
        libraryFragment.updateRandomVisibility(fragmentLibraryBinding);
        ViewPager2 viewpager = fragmentLibraryBinding.viewpager;
        Intrinsics.checkNotNullExpressionValue(viewpager, "viewpager");
        List list = SequencesKt.toList(ViewKt.getAllViews(viewpager));
        ArrayList<AutofitRecyclerView> arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof AutofitRecyclerView) {
                arrayList.add(obj);
            }
        }
        for (AutofitRecyclerView autofitRecyclerView : arrayList) {
            autofitRecyclerView.setVisibility(Intrinsics.areEqual(autofitRecyclerView.getTag(), Integer.valueOf(i)) ? 0 : 8);
            autofitRecyclerView.setFocusable(Intrinsics.areEqual(autofitRecyclerView.getTag(), Integer.valueOf(i)));
            if (Intrinsics.areEqual(autofitRecyclerView.getTag(), Integer.valueOf(i))) {
                autofitRecyclerView.setDescendantFocusability(262144);
            } else {
                autofitRecyclerView.setDescendantFocusability(393216);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:122:0x012c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:22:0x0082  */
    /* JADX WARN: Code duplicated, block: B:29:0x0098 A[Catch: Exception -> 0x00aa, TRY_LEAVE, TryCatch #4 {Exception -> 0x00aa, blocks: (B:5:0x002b, B:8:0x0037, B:13:0x0051, B:20:0x007c, B:23:0x0083, B:29:0x0098, B:28:0x0093, B:19:0x0072, B:12:0x0047, B:16:0x0058, B:9:0x0039, B:25:0x0087), top: B:119:0x002b, inners: #1, #3, #9, #9 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0127  */
    /* JADX WARN: Code duplicated, block: B:59:0x013d A[Catch: Exception -> 0x014f, TRY_LEAVE, TryCatch #0 {Exception -> 0x014f, blocks: (B:35:0x00d0, B:38:0x00dc, B:43:0x00f6, B:50:0x0121, B:53:0x0128, B:59:0x013d, B:58:0x0138, B:49:0x0117, B:42:0x00ec, B:46:0x00fd, B:55:0x012c, B:39:0x00de), top: B:112:0x00d0, inners: #2, #6, #7, #10 }] */
    private final void loadLibraryItem(SyncIdName syncName, String syncId, SearchResponse card) {
        Object objDecodeFromString;
        Object objDecodeFromString2;
        String apiName;
        FragmentActivity activity;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/library_folder";
        String strName = syncName.name();
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strName), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(LibraryOpener.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer2 != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(LibraryOpener.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer2 != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
        } else {
            objDecodeFromString = null;
        }
        LibraryOpener libraryOpener = (LibraryOpener) objDecodeFromString;
        CloudStreamApp.Companion companion6 = CloudStreamApp.INSTANCE;
        String str2 = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/library_folder";
        Context context2 = companion6.getContext();
        if (context2 != null) {
            DataStore dataStore2 = DataStore.INSTANCE;
            try {
                String string2 = dataStore2.getSharedPrefs(context2).getString(dataStore2.getFolderName(str2, syncId), null);
                if (string2 == null) {
                    objDecodeFromString2 = null;
                } else {
                    AppUtils appUtils2 = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(LibraryOpener.INSTANCE.serializer());
                    } catch (Throwable th3) {
                        Result.Companion companion8 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                            } catch (SerializationException e2) {
                                ArchComponentExtKt.logError(e2);
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$2
                                });
                            } catch (Throwable unused3) {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$2
                                });
                            }
                        } else {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$2
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion9 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(LibraryOpener.class), null, 2, null));
                        } catch (Throwable th4) {
                            Result.Companion companion10 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                        } else {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<LibraryOpener>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$loadLibraryItem$$inlined$getKey$2
                            });
                        }
                    }
                }
            } catch (Exception unused4) {
            }
        } else {
            objDecodeFromString2 = null;
        }
        LibraryOpener libraryOpener2 = (LibraryOpener) objDecodeFromString2;
        if ((libraryOpener2 != null ? libraryOpener2.getOpenType() : null) == LibraryOpenerType.Default) {
            objDecodeFromString2 = null;
        }
        LibraryOpener libraryOpener3 = (LibraryOpener) objDecodeFromString2;
        if (libraryOpener3 != null) {
            libraryOpener = libraryOpener3;
        }
        LibraryOpenerType openType = libraryOpener != null ? libraryOpener.getOpenType() : null;
        int i = openType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[openType.ordinal()];
        if (i == -1 || i == 1) {
            if (APIHolder.INSTANCE.getApiFromNameNull(card.getApiName()) != null) {
                FragmentActivity activity2 = getActivity();
                if (activity2 != null) {
                    AppContextUtils.loadSearchResult$default(AppContextUtils.INSTANCE, activity2, card, 0, null, 6, null);
                    return;
                }
                return;
            }
            QuickSearchFragment.Companion.pushSearch$default(QuickSearchFragment.INSTANCE, getActivity(), card.getName(), null, 4, null);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    CloudStreamApp.Companion.openBrowser$default(CloudStreamApp.INSTANCE, card.getUrl(), false, null, 6, null);
                    return;
                } else {
                    if (i != 5) {
                        throw new NoWhenBranchMatchedException();
                    }
                    QuickSearchFragment.Companion.pushSearch$default(QuickSearchFragment.INSTANCE, getActivity(), card.getName(), null, 4, null);
                    return;
                }
            }
            ProviderLibraryData providerData = libraryOpener.getProviderData();
            if (providerData == null || (apiName = providerData.getApiName()) == null || (activity = getActivity()) == null) {
                return;
            }
            AppContextUtils.loadResult$default(AppContextUtils.INSTANCE, activity, card.getUrl(), apiName, card.getName(), 0, 0, 24, null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        ViewPager2 viewPager2;
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        FragmentLibraryBinding binding = getBinding();
        if (binding == null || (viewPager2 = binding.viewpager) == null || (adapter = viewPager2.getAdapter()) == null) {
            return;
        }
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
    }

    static final void sortChangeClickListener$lambda$0(final LibraryFragment libraryFragment, View view) {
        List<ListSorting> sortingMethods = libraryFragment.getLibraryViewModel().getSortingMethods();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(sortingMethods, 10));
        Iterator<T> it = sortingMethods.iterator();
        while (it.hasNext()) {
            UiText uiTextTxt = TextUtilKt.txt(((ListSorting) it.next()).getStringRes(), new Object[0]);
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            arrayList.add(uiTextTxt.asString(context));
        }
        ArrayList arrayList2 = arrayList;
        FragmentActivity activity = libraryFragment.getActivity();
        if (activity != null) {
            int iIndexOf = CollectionsKt.indexOf((List<? extends ListSorting>) libraryFragment.getLibraryViewModel().getSortingMethods(), libraryFragment.getLibraryViewModel().getCurrentSortingMethod());
            UiText uiTextTxt2 = TextUtilKt.txt(R.string.sort_by, new Object[0]);
            Context context2 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            SingleSelectionHelper.INSTANCE.showBottomDialog(activity, arrayList2, iIndexOf, uiTextTxt2.asString(context2), false, new Function0() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.library.LibraryFragment$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LibraryFragment.sortChangeClickListener$lambda$0$2(this.f$0, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sortChangeClickListener$lambda$0$2(LibraryFragment libraryFragment, int i) {
        LibraryViewModel.sort$default(libraryFragment.getLibraryViewModel(), libraryFragment.getLibraryViewModel().getSortingMethods().get(i), null, 2, null);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$13(LibraryFragment libraryFragment, SearchClickCallback searchClickCallback) throws DebugException {
        SyncIdName syncIdName;
        Intrinsics.checkNotNullParameter(searchClickCallback, "searchClickCallback");
        if (AppDebug.INSTANCE.isDebug() && !(searchClickCallback.getCard() instanceof SyncAPI.LibraryItem)) {
            throw new DebugException("searchClickCallback " + searchClickCallback.getCard() + " is not a LibraryItem");
        }
        SearchResponse card = searchClickCallback.getCard();
        Intrinsics.checkNotNull(card, "null cannot be cast to non-null type com.lagradost.cloudstream3.syncproviders.SyncAPI.LibraryItem");
        String syncId = ((SyncAPI.LibraryItem) card).getSyncId();
        SyncRepo currentSyncApi = libraryFragment.getLibraryViewModel().getCurrentSyncApi();
        if (currentSyncApi == null || (syncIdName = currentSyncApi.getSyncIdName()) == null) {
            return Unit.INSTANCE;
        }
        int action = searchClickCallback.getAction();
        if (action == 0) {
            libraryFragment.loadLibraryItem(syncIdName, syncId, searchClickCallback.getCard());
        } else if (action == 1) {
            FragmentActivity activity = libraryFragment.getActivity();
            MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
            if (mainActivity != null) {
                mainActivity.loadPopup(searchClickCallback.getCard(), false);
            }
        }
        return Unit.INSTANCE;
    }
}

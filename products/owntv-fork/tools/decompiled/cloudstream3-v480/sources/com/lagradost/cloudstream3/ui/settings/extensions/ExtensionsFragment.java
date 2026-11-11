package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AddRepoInputBinding;
import com.lagradost.cloudstream3.databinding.FragmentExtensionsBinding;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.settings.SettingsFragment;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ExtensionsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\u0012\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0002H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentExtensionsBinding;", "<init>", "()V", "extensionViewModel", "Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel;", "getExtensionViewModel", "()Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel;", "extensionViewModel$delegate", "Lkotlin/Lazy;", "pluginViewModel", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;", "getPluginViewModel", "()Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;", "pluginViewModel$delegate", "setLayoutWidth", "", "Landroid/view/View;", "weight", "", "onResume", "onStop", "reloadRepositories", "success", "", "fixLayout", "view", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ExtensionsFragment extends BaseFragment<FragmentExtensionsBinding> {

    /* JADX INFO: renamed from: extensionViewModel$delegate, reason: from kotlin metadata */
    private final Lazy extensionViewModel;

    /* JADX INFO: renamed from: pluginViewModel$delegate, reason: from kotlin metadata */
    private final Lazy pluginViewModel;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExtensionsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentExtensionsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentExtensionsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentExtensionsBinding;", 0);
        }

        public final FragmentExtensionsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentExtensionsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentExtensionsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public ExtensionsFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        final ExtensionsFragment extensionsFragment = this;
        final Function0 function0 = null;
        this.extensionViewModel = FragmentViewModelLazyKt.createViewModelLazy(extensionsFragment, Reflection.getOrCreateKotlinClass(ExtensionsViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return extensionsFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? extensionsFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return extensionsFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.pluginViewModel = FragmentViewModelLazyKt.createViewModelLazy(extensionsFragment, Reflection.getOrCreateKotlinClass(PluginsViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return extensionsFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? extensionsFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$special$$inlined$activityViewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return extensionsFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExtensionsViewModel getExtensionViewModel() {
        return (ExtensionsViewModel) this.extensionViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PluginsViewModel getPluginViewModel() {
        return (PluginsViewModel) this.pluginViewModel.getValue();
    }

    private final void setLayoutWidth(View view, int i) {
        view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, i));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtensionsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09071 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C09071(Object obj) {
            super(1, obj, ExtensionsFragment.class, "reloadRepositories", "reloadRepositories(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ExtensionsFragment) this.receiver).reloadRepositories(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().plusAssign(new C09071(this));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtensionsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09081 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C09081(Object obj) {
            super(1, obj, ExtensionsFragment.class, "reloadRepositories", "reloadRepositories(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ExtensionsFragment) this.receiver).reloadRepositories(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().minusAssign(new C09081(this));
    }

    static /* synthetic */ void reloadRepositories$default(ExtensionsFragment extensionsFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        extensionsFragment.reloadRepositories(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadRepositories(boolean success) {
        getExtensionViewModel().loadStats();
        getExtensionViewModel().loadRepositories();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        SettingsFragment.INSTANCE.setSystemBarsPadding(this);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentExtensionsBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        ExtensionsFragment extensionsFragment = this;
        SettingsFragment.INSTANCE.setUpToolbar(extensionsFragment, R.string.extensions);
        SettingsFragment.INSTANCE.setToolBarScrollFlags(extensionsFragment);
        final RecyclerView recyclerView = binding.repoRecyclerView;
        LinearListLayoutKt.setLinearListLayout(recyclerView, false, R.id.nav_rail_view, -2, R.id.settings_toolbar, R.id.plugin_storage_appbar);
        if (!Globals.INSTANCE.isLayout(2)) {
            final ExtendedFloatingActionButton extendedFloatingActionButton = binding.addRepoButton;
            extendedFloatingActionButton.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    ExtensionsFragment.onBindingCreated$lambda$0$0$0(recyclerView, extendedFloatingActionButton);
                }
            });
        }
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                ExtensionsFragment.onBindingCreated$lambda$0$1(binding, view, i, i2, i3, i4);
            }
        });
        recyclerView.setAdapter(new RepoAdapter(false, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ExtensionsFragment.onBindingCreated$lambda$0$2(this.f$0, (RepoAdapter) obj, (RepositoryData) obj2);
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ExtensionsFragment.onBindingCreated$lambda$0$3(recyclerView, binding, this, (RepoAdapter) obj, (RepositoryData) obj2);
            }
        }));
        ExtensionsFragment extensionsFragment2 = this;
        LifecycleKt.observe(extensionsFragment2, getExtensionViewModel().getRepositories(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExtensionsFragment.onBindingCreated$lambda$1(binding, this, (RepositoryData[]) obj);
            }
        });
        LifecycleKt.observeNullable(extensionsFragment2, getExtensionViewModel().getPluginStats(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExtensionsFragment.onBindingCreated$lambda$2(binding, this, (ExtensionsViewModel.PluginStats) obj);
            }
        });
        binding.pluginStorageAppbar.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExtensionsFragment.onBindingCreated$lambda$3(this.f$0, view);
            }
        });
        RecyclerView recyclerView2 = binding.pluginRecyclerView;
        LinearListLayoutKt.setLinearListLayout$default(recyclerView2, false, 0, -2, 0, -2, 10, null);
        Intrinsics.checkNotNull(recyclerView2);
        BaseAdapterKt.setRecycledViewPool(recyclerView2, PluginAdapter.INSTANCE.getSharedPool());
        recyclerView2.setAdapter(new PluginAdapter(true, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExtensionsFragment.onBindingCreated$lambda$4$0(this.f$0, (PluginWrapper) obj);
            }
        }));
        LifecycleKt.observe(extensionsFragment2, getPluginViewModel().getFilteredPlugins(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExtensionsFragment.onBindingCreated$lambda$5(binding, (Pair) obj);
            }
        });
        MaterialToolbar materialToolbar = binding.settingsToolbar;
        Menu menu = materialToolbar.getMenu();
        MenuItem menuItemFindItem = menu != null ? menu.findItem(R.id.search_button) : null;
        View actionView = menuItemFindItem != null ? menuItemFindItem.getActionView() : null;
        final SearchView searchView = actionView instanceof SearchView ? (SearchView) actionView : null;
        if (menuItemFindItem != null) {
            menuItemFindItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$7$1
                @Override // android.view.MenuItem.OnActionExpandListener
                public boolean onMenuItemActionCollapse(MenuItem p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    RecyclerView pluginRecyclerView = binding.pluginRecyclerView;
                    Intrinsics.checkNotNullExpressionValue(pluginRecyclerView, "pluginRecyclerView");
                    pluginRecyclerView.setVisibility(8);
                    RecyclerView repoRecyclerView = binding.repoRecyclerView;
                    Intrinsics.checkNotNullExpressionValue(repoRecyclerView, "repoRecyclerView");
                    repoRecyclerView.setVisibility(0);
                    return true;
                }

                @Override // android.view.MenuItem.OnActionExpandListener
                public boolean onMenuItemActionExpand(MenuItem p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    RecyclerView pluginRecyclerView = binding.pluginRecyclerView;
                    Intrinsics.checkNotNullExpressionValue(pluginRecyclerView, "pluginRecyclerView");
                    pluginRecyclerView.setVisibility(0);
                    RecyclerView repoRecyclerView = binding.repoRecyclerView;
                    Intrinsics.checkNotNullExpressionValue(repoRecyclerView, "repoRecyclerView");
                    repoRecyclerView.setVisibility(8);
                    return true;
                }
            });
        }
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExtensionsFragment.onBindingCreated$lambda$6$0(searchView, this, view);
            }
        });
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$7$3
                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextSubmit(String query) {
                    this.this$0.getPluginViewModel().search(query);
                    return true;
                }

                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextChange(String newText) {
                    this.this$0.getPluginViewModel().search(newText);
                    return true;
                }
            });
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExtensionsFragment.onBindingCreated$lambda$7(this.f$0, view);
            }
        };
        boolean zIsLayout = Globals.INSTANCE.isLayout(2);
        ExtendedFloatingActionButton addRepoButton = binding.addRepoButton;
        Intrinsics.checkNotNullExpressionValue(addRepoButton, "addRepoButton");
        addRepoButton.setVisibility(zIsLayout ? 8 : 0);
        LinearLayout addRepoButtonImageviewHolder = binding.addRepoButtonImageviewHolder;
        Intrinsics.checkNotNullExpressionValue(addRepoButtonImageviewHolder, "addRepoButtonImageviewHolder");
        addRepoButtonImageviewHolder.setVisibility(zIsLayout ? 0 : 8);
        binding.pluginStorageAppbar.setFocusableInTouchMode(zIsLayout);
        binding.addRepoButtonImageview.setFocusableInTouchMode(zIsLayout);
        binding.addRepoButton.setOnClickListener(onClickListener);
        binding.addRepoButtonImageview.setOnClickListener(onClickListener);
        reloadRepositories$default(this, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$0(RecyclerView recyclerView, ExtendedFloatingActionButton extendedFloatingActionButton) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int paddingTop = recyclerView.getPaddingTop();
        int paddingRight = recyclerView.getPaddingRight();
        int measuredHeight = extendedFloatingActionButton.getMeasuredHeight();
        Intrinsics.checkNotNull(extendedFloatingActionButton);
        ExtendedFloatingActionButton extendedFloatingActionButton2 = extendedFloatingActionButton;
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton2.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        int i = measuredHeight + (marginLayoutParams != null ? marginLayoutParams.topMargin : 0);
        ViewGroup.LayoutParams layoutParams2 = extendedFloatingActionButton2.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        recyclerView.setPadding(paddingLeft, paddingTop, paddingRight, i + (marginLayoutParams2 != null ? marginLayoutParams2.bottomMargin : 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1(FragmentExtensionsBinding fragmentExtensionsBinding, View view, int i, int i2, int i3, int i4) {
        int i5 = i2 - i4;
        if (i5 > 0) {
            fragmentExtensionsBinding.addRepoButton.shrink();
        } else if (i5 < -5) {
            fragmentExtensionsBinding.addRepoButton.extend();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$2(ExtensionsFragment extensionsFragment, RepoAdapter RepoAdapter, RepositoryData it) {
        Intrinsics.checkNotNullParameter(RepoAdapter, "$this$RepoAdapter");
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentKt.findNavController(extensionsFragment).navigate(R.id.navigation_settings_extensions_to_navigation_settings_plugins, PluginsFragment.INSTANCE.newInstance(it));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$3(RecyclerView recyclerView, FragmentExtensionsBinding fragmentExtensionsBinding, ExtensionsFragment extensionsFragment, RepoAdapter RepoAdapter, RepositoryData repo) {
        Intrinsics.checkNotNullParameter(RepoAdapter, "$this$RepoAdapter");
        Intrinsics.checkNotNullParameter(repo, "repo");
        Coroutines.INSTANCE.main(RepoAdapter, new ExtensionsFragment$onBindingCreated$1$4$1(recyclerView, fragmentExtensionsBinding, RepoAdapter, repo, extensionsFragment, null));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$1(FragmentExtensionsBinding fragmentExtensionsBinding, ExtensionsFragment extensionsFragment, RepositoryData[] repos) {
        Intrinsics.checkNotNullParameter(repos, "repos");
        RecyclerView repoRecyclerView = fragmentExtensionsBinding.repoRecyclerView;
        Intrinsics.checkNotNullExpressionValue(repoRecyclerView, "repoRecyclerView");
        repoRecyclerView.setVisibility(!(repos.length == 0) ? 0 : 8);
        LinearLayout blankRepoScreen = fragmentExtensionsBinding.blankRepoScreen;
        Intrinsics.checkNotNullExpressionValue(blankRepoScreen, "blankRepoScreen");
        blankRepoScreen.setVisibility(repos.length == 0 ? 0 : 8);
        RecyclerView.Adapter adapter = fragmentExtensionsBinding.repoRecyclerView.getAdapter();
        RepoAdapter repoAdapter = adapter instanceof RepoAdapter ? (RepoAdapter) adapter : null;
        if (repoAdapter != null) {
            BaseAdapter.submitList$default(repoAdapter, ArraysKt.toList(repos), null, 2, null);
        }
        extensionsFragment.getPluginViewModel().updatePluginList(fragmentExtensionsBinding.getRoot().getContext(), ArraysKt.toList(repos));
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$2(FragmentExtensionsBinding fragmentExtensionsBinding, ExtensionsFragment extensionsFragment, ExtensionsViewModel.PluginStats pluginStats) {
        if (pluginStats == null) {
            LinearLayout pluginStorageAppbar = fragmentExtensionsBinding.pluginStorageAppbar;
            Intrinsics.checkNotNullExpressionValue(pluginStorageAppbar, "pluginStorageAppbar");
            pluginStorageAppbar.setVisibility(8);
            return Unit.INSTANCE;
        }
        LinearLayout pluginStorageAppbar2 = fragmentExtensionsBinding.pluginStorageAppbar;
        Intrinsics.checkNotNullExpressionValue(pluginStorageAppbar2, "pluginStorageAppbar");
        pluginStorageAppbar2.setVisibility(0);
        if (pluginStats.getTotal() == 0) {
            View pluginDownload = fragmentExtensionsBinding.pluginDownload;
            Intrinsics.checkNotNullExpressionValue(pluginDownload, "pluginDownload");
            extensionsFragment.setLayoutWidth(pluginDownload, 1);
            View pluginDisabled = fragmentExtensionsBinding.pluginDisabled;
            Intrinsics.checkNotNullExpressionValue(pluginDisabled, "pluginDisabled");
            extensionsFragment.setLayoutWidth(pluginDisabled, 0);
            View pluginNotDownloaded = fragmentExtensionsBinding.pluginNotDownloaded;
            Intrinsics.checkNotNullExpressionValue(pluginNotDownloaded, "pluginNotDownloaded");
            extensionsFragment.setLayoutWidth(pluginNotDownloaded, 0);
        } else {
            View pluginDownload2 = fragmentExtensionsBinding.pluginDownload;
            Intrinsics.checkNotNullExpressionValue(pluginDownload2, "pluginDownload");
            extensionsFragment.setLayoutWidth(pluginDownload2, pluginStats.getDownloaded());
            View pluginDisabled2 = fragmentExtensionsBinding.pluginDisabled;
            Intrinsics.checkNotNullExpressionValue(pluginDisabled2, "pluginDisabled");
            extensionsFragment.setLayoutWidth(pluginDisabled2, pluginStats.getDisabled());
            View pluginNotDownloaded2 = fragmentExtensionsBinding.pluginNotDownloaded;
            Intrinsics.checkNotNullExpressionValue(pluginNotDownloaded2, "pluginNotDownloaded");
            extensionsFragment.setLayoutWidth(pluginNotDownloaded2, pluginStats.getNotDownloaded());
        }
        TextUtilKt.setText(fragmentExtensionsBinding.pluginNotDownloadedTxt, pluginStats.getNotDownloadedText());
        TextUtilKt.setText(fragmentExtensionsBinding.pluginDisabledTxt, pluginStats.getDisabledText());
        TextUtilKt.setText(fragmentExtensionsBinding.pluginDownloadTxt, pluginStats.getDownloadedText());
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$3(ExtensionsFragment extensionsFragment, View view) {
        NavController navControllerFindNavController = FragmentKt.findNavController(extensionsFragment);
        int i = R.id.navigation_settings_extensions_to_navigation_settings_plugins;
        PluginsFragment.Companion companion = PluginsFragment.INSTANCE;
        String string = extensionsFragment.getString(R.string.extensions);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        navControllerFindNavController.navigate(i, companion.newLocalInstance(string));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$4$0(ExtensionsFragment extensionsFragment, PluginWrapper it) {
        List<RepositoryData> listEmptyList;
        Intrinsics.checkNotNullParameter(it, "it");
        RepositoryData[] value = extensionsFragment.getExtensionViewModel().getRepositories().getValue();
        if (value == null || (listEmptyList = ArraysKt.toList(value)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        extensionsFragment.getPluginViewModel().handlePluginAction(extensionsFragment.getActivity(), listEmptyList, it, false);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(FragmentExtensionsBinding fragmentExtensionsBinding, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        boolean zBooleanValue = ((Boolean) pair.component1()).booleanValue();
        List list = (List) pair.component2();
        RecyclerView.Adapter adapter = fragmentExtensionsBinding.pluginRecyclerView.getAdapter();
        PluginAdapter pluginAdapter = adapter instanceof PluginAdapter ? (PluginAdapter) adapter : null;
        if (pluginAdapter != null) {
            BaseAdapter.submitList$default(pluginAdapter, list, null, 2, null);
        }
        if (zBooleanValue) {
            fragmentExtensionsBinding.pluginRecyclerView.scrollToPosition(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$6$0(SearchView searchView, ExtensionsFragment extensionsFragment, View view) {
        if (searchView != null && !searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            extensionsFragment.dispatchBackPressed();
        }
    }

    static final void onBindingCreated$lambda$7(final ExtensionsFragment extensionsFragment, View view) {
        ClipData primaryClip;
        ClipData.Item itemAt;
        CharSequence text;
        String string;
        Context context = extensionsFragment.getContext();
        if (context == null) {
            return;
        }
        final AddRepoInputBinding addRepoInputBindingInflate = AddRepoInputBinding.inflate(LayoutInflater.from(context), null, false);
        Intrinsics.checkNotNullExpressionValue(addRepoInputBindingInflate, "inflate(...)");
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.AlertDialogCustom).setView(addRepoInputBindingInflate.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        FragmentActivity activity = extensionsFragment.getActivity();
        Object systemService = activity != null ? activity.getSystemService("clipboard") : null;
        ClipboardManager clipboardManager = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
        if (clipboardManager != null && (primaryClip = clipboardManager.getPrimaryClip()) != null && (itemAt = primaryClip.getItemAt(0)) != null && (text = itemAt.getText()) != null && (string = text.toString()) != null) {
            String str = string;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) RepoAdapter.SHAREABLE_REPO_SEPARATOR, false, 2, (Object) null)) {
                List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{RepoAdapter.SHAREABLE_REPO_SEPARATOR}, false, 2, 2, (Object) null);
                String str2 = (String) listSplit$default.get(0);
                addRepoInputBindingInflate.repoUrlInput.setText(StringsKt.trim((CharSequence) listSplit$default.get(1)).toString());
                addRepoInputBindingInflate.repoNameInput.setText(StringsKt.trim((CharSequence) str2).toString());
            } else {
                addRepoInputBindingInflate.repoUrlInput.setText(str);
            }
        }
        addRepoInputBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ExtensionsFragment.onBindingCreated$lambda$7$1(addRepoInputBindingInflate, extensionsFragment, alertDialogCreate, view2);
            }
        });
        addRepoInputBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ExtensionsFragment.onBindingCreated$lambda$7$2(alertDialogCreate, extensionsFragment, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$7$1(AddRepoInputBinding addRepoInputBinding, ExtensionsFragment extensionsFragment, AlertDialog alertDialog, View view) {
        Editable text = addRepoInputBinding.repoNameInput.getText();
        String string = text != null ? text.toString() : null;
        Editable text2 = addRepoInputBinding.repoUrlInput.getText();
        String string2 = text2 != null ? text2.toString() : null;
        String str = string2;
        if (str == null || str.length() == 0) {
            CommonActivity.INSTANCE.showToast(R.string.error_invalid_url, (Integer) 0);
            return;
        }
        UIHelper uIHelper = UIHelper.INSTANCE;
        MaterialButton applyBtt = addRepoInputBinding.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        UIHelper.showProgress$default(uIHelper, applyBtt, 0, 1, null);
        Coroutines.INSTANCE.ioSafe(extensionsFragment, new ExtensionsFragment$onBindingCreated$addRepositoryClick$1$2$1(string2, string, extensionsFragment, alertDialog, addRepoInputBinding, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$7$2(AlertDialog alertDialog, ExtensionsFragment extensionsFragment, View view) {
        UIHelper.INSTANCE.dismissSafe(alertDialog, extensionsFragment.getActivity());
    }
}

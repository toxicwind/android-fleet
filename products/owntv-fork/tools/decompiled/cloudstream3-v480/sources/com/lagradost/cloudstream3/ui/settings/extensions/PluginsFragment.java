package com.lagradost.cloudstream3.ui.settings.extensions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.databinding.FragmentPluginsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.home.HomeFragment;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.settings.SettingsFragment;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: PluginsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentPluginsBinding;", "<init>", "()V", "pluginViewModel", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;", "onDestroyView", "", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginsFragment extends BaseFragment<FragmentPluginsBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private PluginsViewModel pluginViewModel;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: PluginsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentPluginsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentPluginsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentPluginsBinding;", 0);
        }

        public final FragmentPluginsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentPluginsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentPluginsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public PluginsFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        PluginsViewModel pluginsViewModel = this.pluginViewModel;
        if (pluginsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel = null;
        }
        pluginsViewModel.clear();
        super.onDestroyView();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        SettingsFragment.INSTANCE.setSystemBarsPadding(this);
    }

    /* JADX WARN: Code duplicated, block: B:109:0x00dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ee A[Catch: Exception -> 0x0100, TRY_LEAVE, TryCatch #0 {Exception -> 0x0100, blocks: (B:28:0x00a6, B:35:0x00d2, B:38:0x00d9, B:44:0x00ee, B:43:0x00e9, B:34:0x00c8, B:27:0x009c, B:31:0x00ad, B:40:0x00dd, B:24:0x008e), top: B:105:0x008e, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r4v1 */
    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentPluginsBinding binding) {
        final RepositoryData repositoryData;
        MenuItem menuItemFindItem;
        MenuItem menuItemFindItem2;
        String string;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        KSerializer kSerializer;
        Intrinsics.checkNotNullParameter(binding, "binding");
        PluginsViewModel pluginsViewModel = (PluginsViewModel) new ViewModelProvider(this).get(PluginsViewModel.class);
        this.pluginViewModel = pluginsViewModel;
        PluginsViewModel pluginsViewModel2 = 0;
        PluginsViewModel pluginsViewModel3 = null;
        if (pluginsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel = null;
        }
        pluginsViewModel.getTvTypes().clear();
        PluginsViewModel pluginsViewModel4 = this.pluginViewModel;
        if (pluginsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel4 = null;
        }
        pluginsViewModel4.setSelectedLanguages(CollectionsKt.emptyList());
        PluginsViewModel pluginsViewModel5 = this.pluginViewModel;
        if (pluginsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel5 = null;
        }
        pluginsViewModel5.clear();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            List list = CollectionsKt.toList(AppContextUtils.INSTANCE.getApiProviderLangSettings(activity));
            if (!list.contains(MainAPIKt.AllLanguagesName)) {
                PluginsViewModel pluginsViewModel6 = this.pluginViewModel;
                if (pluginsViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
                    pluginsViewModel6 = null;
                }
                pluginsViewModel6.setSelectedLanguages(CollectionsKt.plus((Collection) CollectionsKt.mutableListOf("none"), (Iterable) list));
            }
        }
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString("data")) == null) {
            repositoryData = null;
        } else {
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(RepositoryData.INSTANCE.serializer());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$lambda$1$$inlined$tryParseJson$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$lambda$1$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$lambda$1$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$lambda$1$$inlined$tryParseJson$1
                        });
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            repositoryData = (RepositoryData) objDecodeFromString;
        }
        Bundle arguments2 = getArguments();
        int i = 1;
        boolean z = false;
        final boolean z2 = arguments2 != null && arguments2.getBoolean(PluginsFragmentKt.PLUGINS_BUNDLE_LOCAL);
        Menu menu = binding.settingsToolbar.getMenu();
        MenuItem menuItemFindItem3 = menu != null ? menu.findItem(R.id.download_all) : null;
        if (repositoryData == null) {
            dispatchBackPressed();
            return;
        }
        PluginsFragment pluginsFragment = this;
        SettingsFragment.INSTANCE.setToolBarScrollFlags(pluginsFragment);
        SettingsFragment.INSTANCE.setUpToolbar(pluginsFragment, repositoryData.getName());
        MaterialToolbar materialToolbar = binding.settingsToolbar;
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda0
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return PluginsFragment.onBindingCreated$lambda$2$0(this.f$0, repositoryData, menuItem);
            }
        });
        Menu menu2 = materialToolbar.getMenu();
        View actionView = (menu2 == null || (menuItemFindItem2 = menu2.findItem(R.id.search_button)) == null) ? null : menuItemFindItem2.getActionView();
        final SearchView searchView = actionView instanceof SearchView ? (SearchView) actionView : null;
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginsFragment.onBindingCreated$lambda$2$1(searchView, this, view);
            }
        });
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$2$3
                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextSubmit(String query) {
                    PluginsViewModel pluginsViewModel7 = this.this$0.pluginViewModel;
                    if (pluginsViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
                        pluginsViewModel7 = null;
                    }
                    pluginsViewModel7.search(query);
                    return true;
                }

                @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextChange(String newText) {
                    PluginsViewModel pluginsViewModel7 = this.this$0.pluginViewModel;
                    if (pluginsViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
                        pluginsViewModel7 = null;
                    }
                    pluginsViewModel7.search(newText);
                    return true;
                }
            });
        }
        RecyclerView recyclerView = binding.pluginRecyclerView;
        LinearListLayoutKt.setLinearListLayout$default(recyclerView, false, 0, -2, 0, -2, 10, null);
        Intrinsics.checkNotNull(recyclerView);
        BaseAdapterKt.setRecycledViewPool(recyclerView, PluginAdapter.INSTANCE.getSharedPool());
        recyclerView.setAdapter(new PluginAdapter(z, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginsFragment.onBindingCreated$lambda$3$0(this.f$0, repositoryData, z2, (PluginWrapper) obj);
            }
        }, i, pluginsViewModel2));
        if (Globals.INSTANCE.isLayout(6)) {
            binding.pluginRecyclerView.setPadding(0, 0, 0, UIHelper.INSTANCE.getToPx(200));
        }
        PluginsFragment pluginsFragment2 = this;
        PluginsViewModel pluginsViewModel7 = this.pluginViewModel;
        if (pluginsViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel7 = null;
        }
        LifecycleKt.observe(pluginsFragment2, pluginsViewModel7.getFilteredPlugins(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginsFragment.onBindingCreated$lambda$4(binding, (Pair) obj);
            }
        });
        if (z2) {
            if (menuItemFindItem3 != null) {
                menuItemFindItem3.setVisible(false);
            }
            Menu menu3 = binding.settingsToolbar.getMenu();
            if (menu3 != null && (menuItemFindItem = menu3.findItem(R.id.lang_filter)) != null) {
                menuItemFindItem.setVisible(false);
            }
            PluginsViewModel pluginsViewModel8 = this.pluginViewModel;
            if (pluginsViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            } else {
                pluginsViewModel3 = pluginsViewModel8;
            }
            pluginsViewModel3.updatePluginListLocal();
            HorizontalScrollView root = binding.tvtypesChipsScroll.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            root.setVisibility(8);
            return;
        }
        PluginsViewModel pluginsViewModel9 = this.pluginViewModel;
        if (pluginsViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
        } else {
            pluginsViewModel2 = pluginsViewModel9;
        }
        pluginsViewModel2.updatePluginList(getContext(), CollectionsKt.listOf(repositoryData));
        HorizontalScrollView root2 = binding.tvtypesChipsScroll.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
        root2.setVisibility(0);
        if (menuItemFindItem3 != null) {
            menuItemFindItem3.setVisible(false);
        }
        HomeFragment.INSTANCE.bindChips(binding.tvtypesChipsScroll.tvtypesChips, CollectionsKt.emptyList(), CollectionsKt.toList(TvType.getEntries()), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginsFragment.onBindingCreated$lambda$5(this.f$0, (List) obj);
            }
        }, Integer.valueOf(R.id.plugin_recycler_view), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$0(final PluginsFragment pluginsFragment, RepositoryData repositoryData, MenuItem menuItem) {
        PluginsViewModel pluginsViewModel = null;
        Integer numValueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        int i = R.id.download_all;
        if (numValueOf != null && numValueOf.intValue() == i) {
            PluginsViewModel.Companion companion = PluginsViewModel.INSTANCE;
            FragmentActivity activity = pluginsFragment.getActivity();
            PluginsViewModel pluginsViewModel2 = pluginsFragment.pluginViewModel;
            if (pluginsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            } else {
                pluginsViewModel = pluginsViewModel2;
            }
            companion.downloadAll(activity, repositoryData, pluginsViewModel);
            return true;
        }
        int i2 = R.id.lang_filter;
        if (numValueOf == null || numValueOf.intValue() != i2) {
            return true;
        }
        PluginsViewModel pluginsViewModel3 = pluginsFragment.pluginViewModel;
        if (pluginsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel3 = null;
        }
        Set<String> pluginLanguages = pluginsViewModel3.getPluginLanguages();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(pluginLanguages, 10));
        for (String str : pluginLanguages) {
            String nameNextToFlagEmoji$default = SubtitleHelper.getNameNextToFlagEmoji$default(SubtitleHelper.INSTANCE, str, null, 2, null);
            if (nameNextToFlagEmoji$default == null) {
                nameNextToFlagEmoji$default = str;
            }
            arrayList.add(new Pair(str, nameNextToFlagEmoji$default));
        }
        final List mutableList = CollectionsKt.toMutableList((Collection) CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$onBindingCreated$lambda$2$0$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = StringsKt.substringAfter$default((String) ((Pair) t).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = StringsKt.substringAfter$default((String) ((Pair) t2).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }));
        if (mutableList.remove(new Pair("none", "none"))) {
            mutableList.add(0, new Pair("none", pluginsFragment.getString(R.string.no_data)));
        }
        PluginsViewModel pluginsViewModel4 = pluginsFragment.pluginViewModel;
        if (pluginsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
        } else {
            pluginsViewModel = pluginsViewModel4;
        }
        List<String> selectedLanguages = pluginsViewModel.getSelectedLanguages();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(selectedLanguages, 10));
        for (String str2 : selectedLanguages) {
            Iterator it = mutableList.iterator();
            int i3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i3 = -1;
                    break;
                }
                if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), str2)) {
                    break;
                }
                i3++;
            }
            arrayList2.add(Integer.valueOf(i3));
        }
        ArrayList arrayList3 = arrayList2;
        FragmentActivity activity2 = pluginsFragment.getActivity();
        if (activity2 == null) {
            return true;
        }
        SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
        FragmentActivity fragmentActivity = activity2;
        List list = mutableList;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList4.add((String) ((Pair) it2.next()).getSecond());
        }
        String string = pluginsFragment.getString(R.string.provider_lang_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        singleSelectionHelper.showMultiDialog(fragmentActivity, arrayList4, arrayList3, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginsFragment.onBindingCreated$lambda$2$0$5(this.f$0, mutableList, (List) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$0$5(PluginsFragment pluginsFragment, List list, List selectedList) {
        Intrinsics.checkNotNullParameter(selectedList, "selectedList");
        PluginsViewModel pluginsViewModel = pluginsFragment.pluginViewModel;
        PluginsViewModel pluginsViewModel2 = null;
        if (pluginsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel = null;
        }
        List list2 = selectedList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) list.get(((Number) it.next()).intValue())).getFirst());
        }
        pluginsViewModel.setSelectedLanguages(arrayList);
        PluginsViewModel pluginsViewModel3 = pluginsFragment.pluginViewModel;
        if (pluginsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
        } else {
            pluginsViewModel2 = pluginsViewModel3;
        }
        pluginsViewModel2.updateFilteredPlugins();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$1(SearchView searchView, PluginsFragment pluginsFragment, View view) {
        if (searchView != null && !searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            pluginsFragment.dispatchBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$3$0(PluginsFragment pluginsFragment, RepositoryData repositoryData, boolean z, PluginWrapper it) {
        Intrinsics.checkNotNullParameter(it, "it");
        PluginsViewModel pluginsViewModel = pluginsFragment.pluginViewModel;
        if (pluginsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel = null;
        }
        pluginsViewModel.handlePluginAction(pluginsFragment.getActivity(), CollectionsKt.listOf(repositoryData), it, z);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$4(FragmentPluginsBinding fragmentPluginsBinding, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        boolean zBooleanValue = ((Boolean) pair.component1()).booleanValue();
        List list = (List) pair.component2();
        RecyclerView.Adapter adapter = fragmentPluginsBinding.pluginRecyclerView.getAdapter();
        PluginAdapter pluginAdapter = adapter instanceof PluginAdapter ? (PluginAdapter) adapter : null;
        if (pluginAdapter != null) {
            BaseAdapter.submitList$default(pluginAdapter, list, null, 2, null);
        }
        if (zBooleanValue) {
            fragmentPluginsBinding.pluginRecyclerView.scrollToPosition(0);
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(PluginsFragment pluginsFragment, List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        PluginsViewModel pluginsViewModel = pluginsFragment.pluginViewModel;
        PluginsViewModel pluginsViewModel2 = null;
        if (pluginsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel = null;
        }
        pluginsViewModel.getTvTypes().clear();
        PluginsViewModel pluginsViewModel3 = pluginsFragment.pluginViewModel;
        if (pluginsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
            pluginsViewModel3 = null;
        }
        List<String> tvTypes = pluginsViewModel3.getTvTypes();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TvType) it.next()).name());
        }
        tvTypes.addAll(arrayList);
        PluginsViewModel pluginsViewModel4 = pluginsFragment.pluginViewModel;
        if (pluginsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pluginViewModel");
        } else {
            pluginsViewModel2 = pluginsViewModel4;
        }
        pluginsViewModel2.updateFilteredPlugins();
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: PluginsFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsFragment$Companion;", "", "<init>", "()V", "newInstance", "Landroid/os/Bundle;", "repositoryData", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "newLocalInstance", "name", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bundle newInstance(RepositoryData repositoryData) {
            Intrinsics.checkNotNullParameter(repositoryData, "repositoryData");
            Bundle bundle = new Bundle();
            bundle.putString("data", AppUtils.INSTANCE.toJson(repositoryData));
            bundle.putBoolean(PluginsFragmentKt.PLUGINS_BUNDLE_LOCAL, false);
            return bundle;
        }

        public final Bundle newLocalInstance(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Bundle bundle = new Bundle();
            bundle.putString("data", AppUtils.INSTANCE.toJson(new RepositoryData("", name, "")));
            bundle.putBoolean(PluginsFragmentKt.PLUGINS_BUNDLE_LOCAL, true);
            return bundle;
        }
    }
}

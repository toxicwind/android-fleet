package com.lagradost.cloudstream3.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.databinding.FragmentHomeBinding;
import com.lagradost.cloudstream3.databinding.HomeEpisodesExpandedBinding;
import com.lagradost.cloudstream3.databinding.HomeSelectMainpageBinding;
import com.lagradost.cloudstream3.databinding.TvtypesChipsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.account.AccountHelper;
import com.lagradost.cloudstream3.ui.account.AccountViewModel;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchHelper;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.EmptyEvent;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.TvChannelUtils;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 <2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001<B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0011H\u0016J\u001a\u00101\u001a\u00020\u00112\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020403J\u0010\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u001aH\u0016J\u0010\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0002H\u0017J\u0010\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020;H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u0006="}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentHomeBinding;", "<init>", "()V", "homeViewModel", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "getHomeViewModel", "()Lcom/lagradost/cloudstream3/ui/home/HomeViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "accountViewModel", "Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "getAccountViewModel", "()Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "accountViewModel$delegate", "addMovies", "", "cards", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "deleteAll", "pickLayout", "", "()Ljava/lang/Integer;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "apiChangeClickListener", "Landroid/view/View$OnClickListener;", "currentApiName", "", "toggleRandomButton", "", "bottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "homeMasterAdapter", "Lcom/lagradost/cloudstream3/ui/home/HomeParentItemAdapterPreview;", "lastSavedHomepage", "getLastSavedHomepage", "()Ljava/lang/String;", "setLastSavedHomepage", "(Ljava/lang/String;)V", "saveHomepageToTV", "page", "", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "fixLayout", "view", "onBindingCreated", "binding", "handleTvBackPress", "helper", "Lcom/lagradost/cloudstream3/utils/BackPressedCallbackHelper$CallbackHelper;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EmptyEvent configEvent = new EmptyEvent();
    private static int currentSpan = 1;
    private static final int errorProfilePic;
    private static final List<Integer> errorProfilePics;

    /* JADX INFO: renamed from: accountViewModel$delegate, reason: from kotlin metadata */
    private final Lazy accountViewModel;
    private final View.OnClickListener apiChangeClickListener;
    private BottomSheetDialog bottomSheetDialog;
    private String currentApiName;
    private HomeParentItemAdapterPreview homeMasterAdapter;

    /* JADX INFO: renamed from: homeViewModel$delegate, reason: from kotlin metadata */
    private final Lazy homeViewModel;
    private String lastSavedHomepage;
    private boolean toggleRandomButton;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<View, FragmentHomeBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, FragmentHomeBinding.class, "bind", "bind(Landroid/view/View;)Lcom/lagradost/cloudstream3/databinding/FragmentHomeBinding;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FragmentHomeBinding invoke(View p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentHomeBinding.bind(p0);
        }
    }

    public HomeFragment() {
        super(new BaseFragment.BindingCreator.Bind(AnonymousClass1.INSTANCE));
        final HomeFragment homeFragment = this;
        final Function0 function0 = null;
        this.homeViewModel = FragmentViewModelLazyKt.createViewModelLazy(homeFragment, Reflection.getOrCreateKotlinClass(HomeViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return homeFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? homeFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return homeFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.accountViewModel = FragmentViewModelLazyKt.createViewModelLazy(homeFragment, Reflection.getOrCreateKotlinClass(AccountViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return homeFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? homeFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$special$$inlined$activityViewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return homeFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.apiChangeClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.apiChangeClickListener$lambda$0(this.f$0, view);
            }
        };
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Ja\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182(\b\u0002\u0010\u001a\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\u0010\u001fJ\u0086\u0001\u0010 \u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000f0!0\u000f2\b\u0010$\u001a\u0004\u0018\u00010\"2\b\u0010%\u001a\u0004\u0018\u00010\"2\b\u0010&\u001a\u0004\u0018\u00010\"2\b\u0010'\u001a\u0004\u0018\u00010\"2\b\u0010(\u001a\u0004\u0018\u00010\"2\b\u0010)\u001a\u0004\u0018\u00010\"2\b\u0010*\u001a\u0004\u0018\u00010\"2\b\u0010+\u001a\u0004\u0018\u00010\"2\b\u0010,\u001a\u0004\u0018\u00010\"2\b\u0010-\u001a\u0004\u0018\u00010\"H\u0002J*\u0010 \u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000f0!0\u000f2\u0006\u0010.\u001a\u00020/H\u0002J\u001e\u00100\u001a\u00020\u00192\b\u0010.\u001a\u0004\u0018\u00010/2\f\u00101\u001a\b\u0012\u0004\u0012\u00020#0\u000fJ\u001e\u00102\u001a\u00020\u00192\b\u0010.\u001a\u0004\u0018\u00010/2\f\u00103\u001a\b\u0012\u0004\u0012\u00020#0\u000fJF\u00104\u001a\u00020\u00192\b\u0010.\u001a\u0004\u0018\u00010/2\f\u00103\u001a\b\u0012\u0004\u0012\u00020#0\u000f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020#0\u000f2\u0018\u00105\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000f\u0012\u0004\u0012\u00020\u001906J_\u00104\u001a\u00020\u00192\b\u0010.\u001a\u0004\u0018\u00010/2\f\u00103\u001a\b\u0012\u0004\u0012\u00020#0\u000f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020#0\u000f2\u0018\u00105\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000f\u0012\u0004\u0012\u00020\u0019062\b\u00107\u001a\u0004\u0018\u00010\t2\b\u00108\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u00109J(\u0010:\u001a\u00020\u0019*\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u001c2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001906R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006="}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeFragment$Companion;", "", "<init>", "()V", "configEvent", "Lcom/lagradost/cloudstream3/utils/EmptyEvent;", "getConfigEvent", "()Lcom/lagradost/cloudstream3/utils/EmptyEvent;", "currentSpan", "", "getCurrentSpan", "()I", "setCurrentSpan", "(I)V", "errorProfilePics", "", "errorProfilePic", "getErrorProfilePic", "loadHomepageList", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "Landroid/app/Activity;", "expand", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "deleteCallback", "Lkotlin/Function0;", "", "expandCallback", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "dismissCallback", "(Landroid/app/Activity;Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "getPairList", "Lkotlin/Pair;", "Lcom/google/android/material/chip/Chip;", "Lcom/lagradost/cloudstream3/TvType;", "anime", "cartoons", "tvs", "docs", "movies", "asian", "livestream", "torrent", "nsfw", "others", "header", "Lcom/lagradost/cloudstream3/databinding/TvtypesChipsBinding;", "validateChips", "validTypes", "updateChips", "selectedTypes", "bindChips", "callback", "Lkotlin/Function1;", "nextFocusDown", "nextFocusUp", "(Lcom/lagradost/cloudstream3/databinding/TvtypesChipsBinding;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;Ljava/lang/Integer;)V", "selectHomepage", "Landroid/content/Context;", "selectedApiName", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EmptyEvent getConfigEvent() {
            return HomeFragment.configEvent;
        }

        public final int getCurrentSpan() {
            return HomeFragment.currentSpan;
        }

        public final void setCurrentSpan(int i) {
            HomeFragment.currentSpan = i;
        }

        public final int getErrorProfilePic() {
            return HomeFragment.errorProfilePic;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BottomSheetDialog loadHomepageList$default(Companion companion, Activity activity, HomeViewModel.ExpandableHomepageList expandableHomepageList, Function0 function0, Function2 function2, Function0 function1, int i, Object obj) {
            if ((i & 2) != 0) {
                function0 = null;
            }
            if ((i & 4) != 0) {
                function2 = null;
            }
            return companion.loadHomepageList(activity, expandableHomepageList, function0, function2, function1);
        }

        public final BottomSheetDialog loadHomepageList(final Activity activity, HomeViewModel.ExpandableHomepageList expand, final Function0<Unit> function0, Function2<? super String, ? super Continuation<? super HomeViewModel.ExpandableHomepageList>, ? extends Object> function2, final Function0<Unit> dismissCallback) {
            final Activity activity2;
            Intrinsics.checkNotNullParameter(activity, "<this>");
            Intrinsics.checkNotNullParameter(expand, "expand");
            Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
            Activity activity3 = activity;
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity3);
            final HomeEpisodesExpandedBinding homeEpisodesExpandedBindingInflate = HomeEpisodesExpandedBinding.inflate(bottomSheetDialog.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(homeEpisodesExpandedBindingInflate, "inflate(...)");
            bottomSheetDialog.setContentView(homeEpisodesExpandedBindingInflate.getRoot());
            final HomePageList list = expand.getList();
            homeEpisodesExpandedBindingInflate.homeExpandedText.setText(list.getName());
            ImageView homeExpandedDelete = homeEpisodesExpandedBindingInflate.homeExpandedDelete;
            Intrinsics.checkNotNullExpressionValue(homeExpandedDelete, "homeExpandedDelete");
            homeExpandedDelete.setVisibility(function0 == null ? 8 : 0);
            if (function0 != null) {
                activity2 = activity;
                homeEpisodesExpandedBindingInflate.homeExpandedDelete.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeFragment.Companion.loadHomepageList$lambda$0(activity2, list, function0, bottomSheetDialog, activity, view);
                    }
                });
            } else {
                activity2 = activity;
            }
            homeEpisodesExpandedBindingInflate.homeExpandedDragDown.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UIHelper.INSTANCE.dismissSafe(bottomSheetDialog, activity2);
                }
            });
            homeEpisodesExpandedBindingInflate.homeExpandedRecycler.setSpanCount(UIHelper.INSTANCE.getSpanCount(activity3, list.isHorizontalImages()));
            AutofitRecyclerView homeExpandedRecycler = homeEpisodesExpandedBindingInflate.homeExpandedRecycler;
            Intrinsics.checkNotNullExpressionValue(homeExpandedRecycler, "homeExpandedRecycler");
            BaseAdapterKt.setRecycledViewPool(homeExpandedRecycler, SearchAdapter.INSTANCE.getSharedPool());
            AutofitRecyclerView autofitRecyclerView = homeEpisodesExpandedBindingInflate.homeExpandedRecycler;
            AutofitRecyclerView homeExpandedRecycler2 = homeEpisodesExpandedBindingInflate.homeExpandedRecycler;
            Intrinsics.checkNotNullExpressionValue(homeExpandedRecycler2, "homeExpandedRecycler");
            SearchAdapter searchAdapter = new SearchAdapter(homeExpandedRecycler2, list.isHorizontalImages(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeFragment.Companion.loadHomepageList$lambda$2(bottomSheetDialog, (SearchClickCallback) obj);
                }
            });
            BaseAdapter.submitList$default(searchAdapter, list.getList(), null, 2, null);
            searchAdapter.setHasNext(expand.getHasNext());
            autofitRecyclerView.setAdapter(searchAdapter);
            homeEpisodesExpandedBindingInflate.homeExpandedRecycler.addOnScrollListener(new HomeFragment$Companion$loadHomepageList$5(expand, function2));
            final Runnable runnable = new Runnable() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    HomeFragment.Companion.loadHomepageList$lambda$4(homeEpisodesExpandedBindingInflate, activity2, list);
                }
            };
            getConfigEvent().plusAssign(runnable);
            bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    HomeFragment.Companion.loadHomepageList$lambda$5(dismissCallback, runnable, dialogInterface);
                }
            });
            bottomSheetDialog.show();
            return bottomSheetDialog;
        }

        static final void loadHomepageList$lambda$0(Activity activity, HomePageList homePageList, final Function0 function0, final BottomSheetDialog bottomSheetDialog, final Activity activity2, View view) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        HomeFragment.Companion.loadHomepageList$lambda$0$0(function0, bottomSheetDialog, activity2, dialogInterface, i);
                    }
                };
                AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                AlertDialog.Builder title = builder.setTitle(R.string.clear_history);
                String string = activity.getString(R.string.delete_message);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{homePageList.getName()}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                AlertDialog alertDialogShow = title.setMessage(str).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
                AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void loadHomepageList$lambda$0$0(Function0 function0, BottomSheetDialog bottomSheetDialog, Activity activity, DialogInterface dialogInterface, int i) {
            if (i != -1) {
                return;
            }
            function0.invoke();
            UIHelper.INSTANCE.dismissSafe(bottomSheetDialog, activity);
        }

        static final Unit loadHomepageList$lambda$2(BottomSheetDialog bottomSheetDialog, SearchClickCallback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            SearchHelper.INSTANCE.handleSearchClickCallback(callback);
            if (callback.getAction() == 0 || callback.getAction() == 2) {
                AppContextUtils.INSTANCE.ownHide(bottomSheetDialog);
            }
            return Unit.INSTANCE;
        }

        static final void loadHomepageList$lambda$4(HomeEpisodesExpandedBinding homeEpisodesExpandedBinding, Activity activity, HomePageList homePageList) {
            homeEpisodesExpandedBinding.homeExpandedRecycler.setSpanCount(UIHelper.INSTANCE.getSpanCount(activity, homePageList.isHorizontalImages()));
            RecyclerView.Adapter adapter = homeEpisodesExpandedBinding.homeExpandedRecycler.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        static final void loadHomepageList$lambda$5(Function0 function0, Runnable runnable, DialogInterface dialogInterface) {
            function0.invoke();
            HomeFragment.INSTANCE.getConfigEvent().minusAssign(runnable);
        }

        private final List<Pair<Chip, List<TvType>>> getPairList(Chip anime, Chip cartoons, Chip tvs, Chip docs, Chip movies, Chip asian, Chip livestream, Chip torrent, Chip nsfw, Chip others) {
            return CollectionsKt.listOf((Object[]) new Pair[]{new Pair(movies, CollectionsKt.listOf(TvType.Movie)), new Pair(tvs, CollectionsKt.listOf(TvType.TvSeries)), new Pair(anime, CollectionsKt.listOf((Object[]) new TvType[]{TvType.Anime, TvType.OVA, TvType.AnimeMovie})), new Pair(asian, CollectionsKt.listOf(TvType.AsianDrama)), new Pair(cartoons, CollectionsKt.listOf(TvType.Cartoon)), new Pair(docs, CollectionsKt.listOf(TvType.Documentary)), new Pair(livestream, CollectionsKt.listOf(TvType.Live)), new Pair(torrent, CollectionsKt.listOf(TvType.Torrent)), new Pair(nsfw, CollectionsKt.listOf(TvType.NSFW)), new Pair(others, CollectionsKt.listOf(TvType.Others))});
        }

        private final List<Pair<Chip, List<TvType>>> getPairList(TvtypesChipsBinding header) {
            return getPairList(header.homeSelectAnime, header.homeSelectCartoons, header.homeSelectTvSeries, header.homeSelectDocumentaries, header.homeSelectMovies, header.homeSelectAsian, header.homeSelectLivestreams, header.homeSelectTorrents, header.homeSelectNsfw, header.homeSelectOthers);
        }

        public final void validateChips(TvtypesChipsBinding header, List<? extends TvType> validTypes) {
            boolean z;
            Intrinsics.checkNotNullParameter(validTypes, "validTypes");
            if (header == null) {
                return;
            }
            for (Pair<Chip, List<TvType>> pair : getPairList(header)) {
                Chip chipComponent1 = pair.component1();
                List<TvType> listComponent2 = pair.component2();
                List<? extends TvType> list = validTypes;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (listComponent2.contains((TvType) it.next())) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                    break;
                }
                if (chipComponent1 != null) {
                    chipComponent1.setVisibility(z ? 0 : 8);
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:23:0x005c A[EDGE_INSN: B:23:0x005c->B:24:0x005d BREAK  A[LOOP:1: B:18:0x0048->B:33:?]] */
        public final void updateChips(TvtypesChipsBinding header, List<? extends TvType> selectedTypes) {
            boolean z;
            Intrinsics.checkNotNullParameter(selectedTypes, "selectedTypes");
            if (header == null) {
                return;
            }
            for (Pair<Chip, List<TvType>> pair : getPairList(header)) {
                Chip chipComponent1 = pair.component1();
                List<TvType> listComponent2 = pair.component2();
                if (chipComponent1 != null) {
                    if (chipComponent1.getVisibility() != 0) {
                        z = false;
                        break;
                    }
                    List<? extends TvType> list = selectedTypes;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it = list.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (listComponent2.contains((TvType) it.next())) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                    } else {
                        z = false;
                        break;
                    }
                    chipComponent1.setChecked(z);
                }
            }
        }

        public final void bindChips(TvtypesChipsBinding header, List<? extends TvType> selectedTypes, List<? extends TvType> validTypes, Function1<? super List<? extends TvType>, Unit> callback) {
            Intrinsics.checkNotNullParameter(selectedTypes, "selectedTypes");
            Intrinsics.checkNotNullParameter(validTypes, "validTypes");
            Intrinsics.checkNotNullParameter(callback, "callback");
            bindChips(header, selectedTypes, validTypes, callback, null, null);
        }

        public final void bindChips(TvtypesChipsBinding header, List<? extends TvType> selectedTypes, List<? extends TvType> validTypes, final Function1<? super List<? extends TvType>, Unit> callback, Integer nextFocusDown, Integer nextFocusUp) {
            boolean z;
            Intrinsics.checkNotNullParameter(selectedTypes, "selectedTypes");
            Intrinsics.checkNotNullParameter(validTypes, "validTypes");
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (header == null) {
                return;
            }
            final List<Pair<Chip, List<TvType>>> pairList = getPairList(header);
            for (Pair<Chip, List<TvType>> pair : pairList) {
                Chip chipComponent1 = pair.component1();
                List<TvType> listComponent2 = pair.component2();
                List<? extends TvType> list = validTypes;
                boolean z2 = false;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (listComponent2.contains((TvType) it.next())) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                    break;
                }
                if (chipComponent1 != null) {
                    chipComponent1.setVisibility(z ? 0 : 8);
                }
                if (chipComponent1 != null) {
                    if (z) {
                        List<? extends TvType> list2 = selectedTypes;
                        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                            Iterator<T> it2 = list2.iterator();
                            while (it2.hasNext()) {
                                if (listComponent2.contains((TvType) it2.next())) {
                                    z2 = true;
                                    break;
                                }
                            }
                        }
                    }
                    chipComponent1.setChecked(z2);
                }
                if (chipComponent1 != null) {
                    chipComponent1.setFocusable(true);
                }
                if (Globals.INSTANCE.isLayout(2) && chipComponent1 != null) {
                    chipComponent1.setFocusableInTouchMode(true);
                }
                if (nextFocusDown != null && chipComponent1 != null) {
                    chipComponent1.setNextFocusDownId(nextFocusDown.intValue());
                }
                if (nextFocusUp != null && chipComponent1 != null) {
                    chipComponent1.setNextFocusUpId(nextFocusUp.intValue());
                }
                if (chipComponent1 != null) {
                    chipComponent1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda0
                        @Override // android.widget.CompoundButton.OnCheckedChangeListener
                        public final void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                            HomeFragment.Companion.bindChips$lambda$2(pairList, callback, compoundButton, z3);
                        }
                    });
                }
            }
        }

        static final void bindChips$lambda$2(List list, Function1 function1, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                Chip chip = (Chip) pair.component1();
                List list2 = (List) pair.component2();
                if (chip != null && chip.isChecked()) {
                    arrayList.addAll(list2);
                }
            }
            function1.invoke(arrayList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.HashSet] */
        /* JADX WARN: Type inference failed for: r0v7, types: [com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$selectHomepage$1$arrayAdapter$1] */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v2 */
        /* JADX WARN: Type inference failed for: r10v5 */
        /* JADX WARN: Type inference failed for: r11v0, types: [com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$selectHomepage$1$arrayAdapter$1] */
        /* JADX WARN: Type inference failed for: r19v0, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v17, types: [T, java.util.List] */
        public final void selectHomepage(final Context context, final String str, final Function1<? super String, Unit> callback) {
            ?? r10;
            Intrinsics.checkNotNullParameter(context, "<this>");
            Intrinsics.checkNotNullParameter(callback, "callback");
            final List mutableList = CollectionsKt.toMutableList((Collection) AppContextUtils.filterProviderByPreferredMedia$default(AppContextUtils.INSTANCE, context, false, 1, null));
            mutableList.add(0, APIRepository.INSTANCE.getRandomApi());
            mutableList.add(0, APIRepository.INSTANCE.getNoneApi());
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.getBehavior().setState(3);
            HomeSelectMainpageBinding homeSelectMainpageBindingInflate = HomeSelectMainpageBinding.inflate(bottomSheetDialog.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(homeSelectMainpageBindingInflate, "inflate(...)");
            bottomSheetDialog.setContentView(homeSelectMainpageBindingInflate.getRoot());
            bottomSheetDialog.show();
            HashSet<String> apiProviderLangSettings = AppContextUtils.INSTANCE.getApiProviderLangSettings(context);
            final boolean z = apiProviderLangSettings.size() > 1 || apiProviderLangSettings.contains(MainAPIKt.AllLanguagesName);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = str;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = new ArrayList();
            final List<? extends TvType> mutableList2 = CollectionsKt.toMutableList((Collection) DataStoreHelper.INSTANCE.getHomePreference());
            homeSelectMainpageBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.Companion.selectHomepage$lambda$0$1(bottomSheetDialog, view);
                }
            });
            homeSelectMainpageBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.Companion.selectHomepage$lambda$0$2(objectRef, str, callback, bottomSheetDialog, view);
                }
            });
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = ArraysKt.toHashSet(DataStoreHelper.INSTANCE.getPinnedProviders());
            final ListView listView = (ListView) bottomSheetDialog.findViewById(R.id.listview1);
            final int i = R.layout.sort_bottom_single_provider_choice;
            final ArrayList arrayList = new ArrayList();
            final ?? r0 = new ArrayAdapter<String>(context, i, arrayList) { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$selectHomepage$1$arrayAdapter$1
                @Override // android.widget.ArrayAdapter, android.widget.Adapter
                public View getView(int position, View convertView, ViewGroup parent) {
                    Intrinsics.checkNotNullParameter(parent, "parent");
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.sort_bottom_single_provider_choice, parent, false);
                    }
                    TextView textView = (TextView) convertView.findViewById(R.id.text1);
                    ImageView imageView = (ImageView) convertView.findViewById(R.id.pinicon);
                    String item = getItem(position);
                    if (textView != null) {
                        textView.setText(item);
                    }
                    imageView.setVisibility(objectRef3.element.contains(objectRef2.element.get(position).getName()) ? 0 : 8);
                    Intrinsics.checkNotNull(convertView);
                    return convertView;
                }
            };
            if (listView != null) {
                listView.setAdapter((ListAdapter) r0);
            }
            if (listView != null) {
                listView.setChoiceMode(1);
            }
            if (listView != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda11
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                        HomeFragment.Companion.selectHomepage$lambda$0$3(objectRef2, objectRef, callback, bottomSheetDialog, adapterView, view, i2, j);
                    }
                });
            }
            if (listView != null) {
                AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda1
                    @Override // android.widget.AdapterView.OnItemLongClickListener
                    public final boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j) {
                        return HomeFragment.Companion.selectHomepage$lambda$0$12(objectRef2, mutableList2, objectRef3, r0, mutableList, objectRef, listView, z, adapterView, view, i2, j);
                    }
                };
                r10 = r0;
                mutableList = mutableList;
                objectRef2 = objectRef2;
                listView.setOnItemLongClickListener(onItemLongClickListener);
            } else {
                r10 = r0;
            }
            Companion companion = HomeFragment.INSTANCE;
            TvtypesChipsBinding tvtypesChipsBinding = homeSelectMainpageBindingInflate.tvtypesChipsScroll.tvtypesChips;
            ArrayList arrayList2 = new ArrayList();
            Iterator it = mutableList.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList2, ((MainAPI) it.next()).getSupportedTypes());
            }
            final ?? r11 = r10;
            companion.bindChips(tvtypesChipsBinding, mutableList2, CollectionsKt.distinct(arrayList2), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeFragment.Companion.selectHomepage$lambda$0$14(mutableList2, objectRef3, r11, mutableList, objectRef2, objectRef, listView, z, (List) obj);
                }
            });
            selectHomepage$lambda$0$updateList(mutableList2, objectRef3, r11, mutableList, objectRef2, objectRef, listView, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void selectHomepage$lambda$0$1(BottomSheetDialog bottomSheetDialog, View view) {
            UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void selectHomepage$lambda$0$2(Ref.ObjectRef objectRef, String str, Function1 function1, BottomSheetDialog bottomSheetDialog, View view) {
            String str2;
            if (!Intrinsics.areEqual(objectRef.element, str) && (str2 = (String) objectRef.element) != null) {
                function1.invoke(str2);
            }
            UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.String] */
        public static final void selectHomepage$lambda$0$3(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Function1 function1, BottomSheetDialog bottomSheetDialog, AdapterView adapterView, View view, int i, long j) {
            if (((Collection) objectRef.element).isEmpty()) {
                return;
            }
            objectRef2.element = ((MainAPI) ((List) objectRef.element).get(i)).getName();
            function1.invoke(objectRef2.element);
            UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
        }

        /* JADX WARN: Code duplicated, block: B:46:0x0161  */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, java.util.HashSet] */
        /* JADX WARN: Type inference failed for: r7v7, types: [T, java.util.List] */
        private static final void selectHomepage$lambda$0$updateList(List<TvType> list, Ref.ObjectRef<HashSet<String>> objectRef, HomeFragment$Companion$selectHomepage$1$arrayAdapter$1 homeFragment$Companion$selectHomepage$1$arrayAdapter$1, List<MainAPI> list2, Ref.ObjectRef<List<MainAPI>> objectRef2, Ref.ObjectRef<String> objectRef3, ListView listView, boolean z) {
            String name;
            String str;
            DataStoreHelper.INSTANCE.setHomePreference(list);
            List list3 = ArraysKt.toList(DataStoreHelper.INSTANCE.getPinnedProviders());
            objectRef.element = CollectionsKt.toHashSet(list3);
            homeFragment$Companion$selectHomepage$1$arrayAdapter$1.clear();
            List<MainAPI> list4 = list2;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list4) {
                MainAPI mainAPI = (MainAPI) obj;
                if (mainAPI.getHasMainPage()) {
                    if (!objectRef.element.contains(mainAPI.getName())) {
                        Set<TvType> supportedTypes = mainAPI.getSupportedTypes();
                        if (!(supportedTypes instanceof Collection) || !supportedTypes.isEmpty()) {
                            Iterator<T> it = supportedTypes.iterator();
                            while (it.hasNext()) {
                                if (list.contains((TvType) it.next())) {
                                }
                            }
                        }
                    }
                    arrayList.add(obj);
                    break;
                }
            }
            List listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$Companion$selectHomepage$lambda$0$updateList$$inlined$sortedBy$1
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
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List<MainAPI> list5 = listSortedWith;
            for (MainAPI mainAPI2 : list5) {
                linkedHashMap.put(mainAPI2.getName(), mainAPI2);
            }
            List listAsReversed = CollectionsKt.asReversed(list3);
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = listAsReversed.iterator();
            while (it2.hasNext()) {
                MainAPI mainAPI3 = (MainAPI) linkedHashMap.get((String) it2.next());
                if (mainAPI3 != null) {
                    arrayList2.add(mainAPI3);
                }
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : list5) {
                if (!objectRef.element.contains(((MainAPI) obj2).getName())) {
                    arrayList4.add(obj2);
                }
            }
            ArrayList arrayList5 = new ArrayList();
            arrayList5.addAll(CollectionsKt.take(list4, 2));
            arrayList5.addAll(arrayList3);
            arrayList5.addAll(arrayList4);
            objectRef2.element = arrayList5;
            List<MainAPI> list6 = objectRef2.element;
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
            for (MainAPI mainAPI4 : list6) {
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    String flagFromIso = SubtitleHelper.INSTANCE.getFlagFromIso(mainAPI4.getLang());
                    if (flagFromIso != null) {
                        str = flagFromIso + ' ';
                        if (str == null) {
                            str = "";
                        }
                    } else {
                        str = "";
                    }
                    sb.append(str);
                    sb.append(mainAPI4.getName());
                    name = sb.toString();
                } else {
                    name = mainAPI4.getName();
                }
                arrayList6.add(name);
            }
            ArrayList arrayList7 = arrayList6;
            List<MainAPI> list7 = objectRef2.element;
            ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
            Iterator<T> it3 = list7.iterator();
            while (it3.hasNext()) {
                arrayList8.add(((MainAPI) it3.next()).getName());
            }
            int iIndexOf = CollectionsKt.indexOf((List<? extends String>) arrayList8, objectRef3.element);
            if (listView != null) {
                listView.setItemChecked(iIndexOf, true);
            }
            homeFragment$Companion$selectHomepage$1$arrayAdapter$1.addAll(arrayList7);
            homeFragment$Companion$selectHomepage$1$arrayAdapter$1.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean selectHomepage$lambda$0$12(Ref.ObjectRef objectRef, List list, Ref.ObjectRef objectRef2, HomeFragment$Companion$selectHomepage$1$arrayAdapter$1 homeFragment$Companion$selectHomepage$1$arrayAdapter$1, List list2, Ref.ObjectRef objectRef3, ListView listView, boolean z, AdapterView adapterView, View view, int i, long j) {
            if (!((Collection) objectRef.element).isEmpty() && i > 1) {
                List mutableList = ArraysKt.toMutableList(DataStoreHelper.INSTANCE.getPinnedProviders());
                String name = ((MainAPI) ((List) objectRef.element).get(i)).getName();
                if (mutableList.contains(name)) {
                    mutableList.remove(name);
                } else {
                    mutableList.add(name);
                }
                DataStoreHelper.INSTANCE.setPinnedProviders((String[]) mutableList.toArray(new String[0]));
                selectHomepage$lambda$0$updateList(list, objectRef2, homeFragment$Companion$selectHomepage$1$arrayAdapter$1, list2, objectRef, objectRef3, listView, z);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit selectHomepage$lambda$0$14(List list, Ref.ObjectRef objectRef, HomeFragment$Companion$selectHomepage$1$arrayAdapter$1 homeFragment$Companion$selectHomepage$1$arrayAdapter$1, List list2, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, ListView listView, boolean z, List list3) {
            Intrinsics.checkNotNullParameter(list3, "list");
            list.clear();
            list.addAll(list3);
            selectHomepage$lambda$0$updateList(list, objectRef, homeFragment$Companion$selectHomepage$1$arrayAdapter$1, list2, objectRef2, objectRef3, listView, z);
            return Unit.INSTANCE;
        }
    }

    static {
        List<Integer> listListOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.monke_benene), Integer.valueOf(R.drawable.monke_burrito), Integer.valueOf(R.drawable.monke_coco), Integer.valueOf(R.drawable.monke_cookie), Integer.valueOf(R.drawable.monke_flusdered), Integer.valueOf(R.drawable.monke_funny), Integer.valueOf(R.drawable.monke_like), Integer.valueOf(R.drawable.monke_party), Integer.valueOf(R.drawable.monke_sob), Integer.valueOf(R.drawable.monke_drink)});
        errorProfilePics = listListOf;
        errorProfilePic = ((Number) CollectionsKt.random(listListOf, Random.INSTANCE)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeViewModel getHomeViewModel() {
        return (HomeViewModel) this.homeViewModel.getValue();
    }

    private final AccountViewModel getAccountViewModel() {
        return (AccountViewModel) this.accountViewModel.getValue();
    }

    public final void addMovies(List<? extends SearchResponse> cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        Context context = getContext();
        if (context == null) {
            Log.INSTANCE.e("HomeFragment", "Context is null, aborting addMovies");
            return;
        }
        try {
            TvChannelUtils tvChannelUtils = TvChannelUtils.INSTANCE;
            String string = getString(R.string.app_name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Long channelId = tvChannelUtils.getChannelId(context, string);
            if (channelId != null) {
                Log.INSTANCE.d("HomeFragment", "Channel ID: " + channelId.longValue());
                TvChannelUtils.INSTANCE.addPrograms(context, channelId.longValue(), cards);
                return;
            }
            Log.INSTANCE.d("HomeFragment", "Channel does not exist");
        } catch (Exception e) {
            Log.INSTANCE.e("HomeFragment", "Error adding movies: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteAll() {
        Context context = getContext();
        if (context == null) {
            Log.INSTANCE.e("HomeFragment", "Context is null, aborting deleteAll");
            return;
        }
        try {
            TvChannelUtils tvChannelUtils = TvChannelUtils.INSTANCE;
            String string = getString(R.string.app_name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Long channelId = tvChannelUtils.getChannelId(context, string);
            if (channelId != null) {
                Log.INSTANCE.d("HomeFragment", "Channel ID: " + channelId.longValue());
                TvChannelUtils.INSTANCE.deleteStoredPrograms(context);
                return;
            }
            Log.INSTANCE.d("HomeFragment", "Channel does not exist");
        } catch (Exception e) {
            Log.INSTANCE.e("HomeFragment", "Error deleting programs: " + e.getMessage());
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public Integer pickLayout() {
        return Integer.valueOf(Globals.INSTANCE.isLayout(1) ? R.layout.fragment_home : R.layout.fragment_home_tv);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            AppContextUtils.INSTANCE.ownShow(bottomSheetDialog);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        FragmentActivity fragmentActivity = activity instanceof ComponentActivity ? activity : null;
        if (fragmentActivity != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(fragmentActivity, "HomeFragment_BackPress");
        }
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            AppContextUtils.INSTANCE.ownHide(bottomSheetDialog);
        }
        super.onDestroyView();
    }

    static final void apiChangeClickListener$lambda$0(final HomeFragment homeFragment, View view) {
        Companion companion = INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.selectHomepage(context, homeFragment.currentApiName, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeFragment.apiChangeClickListener$lambda$0$0(this.f$0, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit apiChangeClickListener$lambda$0$0(HomeFragment homeFragment, String api) {
        Intrinsics.checkNotNullParameter(api, "api");
        homeFragment.getHomeViewModel().loadAndCancel(api, true, true);
        return Unit.INSTANCE;
    }

    public final String getLastSavedHomepage() {
        return this.lastSavedHomepage;
    }

    public final void setLastSavedHomepage(String str) {
        this.lastSavedHomepage = str;
    }

    public final void saveHomepageToTV(Map<String, HomeViewModel.ExpandableHomepageList> page) {
        Map.Entry entry;
        Intrinsics.checkNotNullParameter(page, "page");
        if (Globals.INSTANCE.isLayout(1) || (entry = (Map.Entry) CollectionsKt.firstOrNull(page.entrySet())) == null) {
            return;
        }
        String str = (String) entry.getKey();
        HomeViewModel.ExpandableHomepageList expandableHomepageList = (HomeViewModel.ExpandableHomepageList) entry.getValue();
        if (Intrinsics.areEqual(str, this.lastSavedHomepage)) {
            return;
        }
        Log.INSTANCE.i("HomeFragment", "Adding programs " + str + " to TV");
        this.lastSavedHomepage = str;
        Coroutines.INSTANCE.ioSafe(this, new C08401(expandableHomepageList, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.home.HomeFragment$saveHomepageToTV$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/home/HomeFragment;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeFragment$saveHomepageToTV$1", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08401 extends SuspendLambda implements Function3<CoroutineScope, HomeFragment, Continuation<? super Unit>, Object> {
        final /* synthetic */ HomeViewModel.ExpandableHomepageList $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08401(HomeViewModel.ExpandableHomepageList expandableHomepageList, Continuation<? super C08401> continuation) {
            super(3, continuation);
            this.$data = expandableHomepageList;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, HomeFragment homeFragment, Continuation<? super Unit> continuation) {
            return HomeFragment.this.new C08401(this.$data, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                HomeFragment.this.deleteAll();
                HomeFragment.this.addMovies(this.$data.getList().getList());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 454, null);
        configEvent.invoke();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentHomeBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Context context = getContext();
        if (context != null) {
            HomeChildItemAdapter.Companion.updatePosterSize$default(HomeChildItemAdapter.INSTANCE, context, null, 2, null);
        }
        FragmentActivity activity = getActivity();
        FragmentActivity fragmentActivity = activity instanceof ComponentActivity ? activity : null;
        if (fragmentActivity != null) {
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(fragmentActivity, "HomeFragment_BackPress", new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HomeFragment.onBindingCreated$lambda$1(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        }
        binding.homeApiFab.setOnClickListener(this.apiChangeClickListener);
        binding.homeApiFab.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return HomeFragment.onBindingCreated$lambda$2$0(this.f$0, view);
            }
        });
        binding.homeChangeApi.setOnClickListener(this.apiChangeClickListener);
        binding.homeSwitchAccount.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.onBindingCreated$lambda$2$1(this.f$0, view);
            }
        });
        this.homeMasterAdapter = new HomeParentItemAdapterPreview(getHomeViewModel(), getAccountViewModel());
        RecyclerView homeMasterRecycler = binding.homeMasterRecycler;
        Intrinsics.checkNotNullExpressionValue(homeMasterRecycler, "homeMasterRecycler");
        BaseAdapterKt.setRecycledViewPool(homeMasterRecycler, ParentItemAdapter.INSTANCE.getSharedPool());
        binding.homeMasterRecycler.setAdapter(this.homeMasterAdapter);
        ExtendedFloatingActionButton homeApiFab = binding.homeApiFab;
        Intrinsics.checkNotNullExpressionValue(homeApiFab, "homeApiFab");
        homeApiFab.setVisibility(Globals.INSTANCE.isLayout(1) ? 0 : 8);
        binding.homePreviewReloadProvider.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.onBindingCreated$lambda$2$2(this.f$0, view);
            }
        });
        binding.homePreviewSearchButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.onBindingCreated$lambda$2$3(this.f$0, view);
            }
        });
        binding.homeMasterRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$onBindingCreated$3$5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                if (!Globals.INSTANCE.isLayout(1)) {
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(0);
                    View view = viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null;
                    LinearLayout homeApiHolder = binding.homeApiHolder;
                    Intrinsics.checkNotNullExpressionValue(homeApiHolder, "homeApiHolder");
                    if (view != null) {
                        int[] iArr = new int[2];
                        view.getLocationInWindow(iArr);
                        homeApiHolder.setVisibility(0);
                        homeApiHolder.setTranslationY(iArr[1] - UIHelper.INSTANCE.getToPx(60));
                    } else {
                        homeApiHolder.setVisibility(8);
                    }
                } else if (dy > 0) {
                    binding.homeApiFab.shrink();
                    binding.homeRandom.shrink();
                } else if (dy < -5 && Globals.INSTANCE.isLayout(1)) {
                    binding.homeApiFab.extend();
                    binding.homeRandom.extend();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        Context context2 = getContext();
        if (context2 != null) {
            this.toggleRandomButton = PreferenceManager.getDefaultSharedPreferences(context2).getBoolean(getString(R.string.random_button_key), false);
            binding.homeRandom.setVisibility(8);
            binding.homeRandomButtonTv.setVisibility(8);
        }
        HomeFragment homeFragment = this;
        LifecycleKt.observe(homeFragment, getHomeViewModel().getApiName(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeFragment.onBindingCreated$lambda$4(this.f$0, binding, (String) obj);
            }
        });
        LifecycleKt.observe(homeFragment, getHomeViewModel().getPage(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeFragment.onBindingCreated$lambda$5(binding, this, (Resource) obj);
            }
        });
        LifecycleKt.observeNullable(homeFragment, getHomeViewModel().getPopup(), new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeFragment.onBindingCreated$lambda$6(this.f$0, (Pair) obj);
            }
        });
        getHomeViewModel().reloadStored();
        HomeViewModel.loadAndCancel$default(getHomeViewModel(), DataStoreHelper.INSTANCE.getCurrentHomePage(), false, false, 4, null);
    }

    static final Unit onBindingCreated$lambda$1(HomeFragment homeFragment, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        homeFragment.handleTvBackPress(attachBackPressedCallback);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$0(HomeFragment homeFragment, View view) {
        if (Intrinsics.areEqual(homeFragment.currentApiName, APIRepository.INSTANCE.getNoneApi().getName())) {
            return false;
        }
        homeFragment.getHomeViewModel().loadAndCancel(homeFragment.currentApiName, true, true);
        CommonActivity.INSTANCE.showToast(R.string.action_reload, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$1(HomeFragment homeFragment, View view) {
        FragmentActivity activity = homeFragment.getActivity();
        if (activity != null) {
            AccountHelper.INSTANCE.showAccountSelectLinear(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$2(HomeFragment homeFragment, View view) {
        HomeViewModel homeViewModel = homeFragment.getHomeViewModel();
        String value = homeFragment.getHomeViewModel().getApiName().getValue();
        if (value == null) {
            value = APIRepository.INSTANCE.getNoneApi().getName();
        }
        homeViewModel.loadAndCancel(value, true, true);
        CommonActivity.INSTANCE.showToast(R.string.action_reload, (Integer) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$3(HomeFragment homeFragment, View view) {
        homeFragment.getHomeViewModel().queryTextSubmit("");
    }

    static final Unit onBindingCreated$lambda$4(HomeFragment homeFragment, FragmentHomeBinding fragmentHomeBinding, String apiName) {
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        homeFragment.currentApiName = apiName;
        String str = apiName;
        fragmentHomeBinding.homeApiFab.setText(str);
        fragmentHomeBinding.homeChangeApi.setText(str);
        ImageView homePreviewReloadProvider = fragmentHomeBinding.homePreviewReloadProvider;
        Intrinsics.checkNotNullExpressionValue(homePreviewReloadProvider, "homePreviewReloadProvider");
        homePreviewReloadProvider.setVisibility(Intrinsics.areEqual(apiName, APIRepository.INSTANCE.getNoneApi().getName()) ? 8 : 0);
        ImageView homePreviewSearchButton = fragmentHomeBinding.homePreviewSearchButton;
        Intrinsics.checkNotNullExpressionValue(homePreviewSearchButton, "homePreviewSearchButton");
        homePreviewSearchButton.setVisibility(Intrinsics.areEqual(apiName, APIRepository.INSTANCE.getNoneApi().getName()) ? 8 : 0);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$5(FragmentHomeBinding fragmentHomeBinding, final HomeFragment homeFragment, Resource data) {
        String errorString;
        Intrinsics.checkNotNullParameter(data, "data");
        boolean z = true;
        if (data instanceof Resource.Success) {
            Map<String, HomeViewModel.ExpandableHomepageList> map = (Map) ((Resource.Success) data).getValue();
            RecyclerView.Adapter adapter = fragmentHomeBinding.homeMasterRecycler.getAdapter();
            ParentItemAdapter parentItemAdapter = adapter instanceof ParentItemAdapter ? (ParentItemAdapter) adapter : null;
            if (parentItemAdapter != null) {
                ParentItemAdapter parentItemAdapter2 = parentItemAdapter;
                Collection<HomeViewModel.ExpandableHomepageList> collectionValues = map.values();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
                for (HomeViewModel.ExpandableHomepageList expandableHomepageList : collectionValues) {
                    arrayList.add(HomeViewModel.ExpandableHomepageList.copy$default(expandableHomepageList, HomePageList.copy$default(expandableHomepageList.getList(), null, CollectionsKt.toMutableList((Collection) expandableHomepageList.getList().getList()), false, 5, null), 0, false, 6, null));
                }
                BaseAdapter.submitList$default(parentItemAdapter2, arrayList, null, 2, null);
            }
            homeFragment.saveHomepageToTV(map);
            FrameLayout homeLoading = fragmentHomeBinding.homeLoading;
            Intrinsics.checkNotNullExpressionValue(homeLoading, "homeLoading");
            homeLoading.setVisibility(8);
            LinearLayout homeLoadingError = fragmentHomeBinding.homeLoadingError;
            Intrinsics.checkNotNullExpressionValue(homeLoadingError, "homeLoadingError");
            homeLoadingError.setVisibility(8);
            RecyclerView homeMasterRecycler = fragmentHomeBinding.homeMasterRecycler;
            Intrinsics.checkNotNullExpressionValue(homeMasterRecycler, "homeMasterRecycler");
            homeMasterRecycler.setVisibility(0);
            fragmentHomeBinding.homeLoadingShimmer.stopShimmer();
            if (homeFragment.toggleRandomButton) {
                Collection<HomeViewModel.ExpandableHomepageList> collectionValues2 = map.values();
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it = collectionValues2.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList2, ((HomeViewModel.ExpandableHomepageList) it.next()).getList().getList());
                }
                HashSet hashSet = new HashSet();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    if (hashSet.add(((SearchResponse) obj).getUrl())) {
                        arrayList3.add(obj);
                    }
                }
                final ArrayList arrayList4 = arrayList3;
                boolean zIsEmpty = arrayList4.isEmpty();
                boolean zIsLayout = Globals.INSTANCE.isLayout(1);
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeFragment.onBindingCreated$lambda$5$0$3(arrayList4, homeFragment, view);
                    }
                };
                ExtendedFloatingActionButton homeRandom = fragmentHomeBinding.homeRandom;
                Intrinsics.checkNotNullExpressionValue(homeRandom, "homeRandom");
                homeRandom.setVisibility(zIsLayout && !zIsEmpty ? 0 : 8);
                fragmentHomeBinding.homeRandom.setOnClickListener(onClickListener);
                ImageView homeRandomButtonTv = fragmentHomeBinding.homeRandomButtonTv;
                Intrinsics.checkNotNullExpressionValue(homeRandomButtonTv, "homeRandomButtonTv");
                homeRandomButtonTv.setVisibility((zIsLayout || zIsEmpty) ? false : true ? 0 : 8);
                fragmentHomeBinding.homeRandomButtonTv.setOnClickListener(onClickListener);
            } else {
                ExtendedFloatingActionButton homeRandom2 = fragmentHomeBinding.homeRandom;
                Intrinsics.checkNotNullExpressionValue(homeRandom2, "homeRandom");
                homeRandom2.setVisibility(8);
                ImageView homeRandomButtonTv2 = fragmentHomeBinding.homeRandomButtonTv;
                Intrinsics.checkNotNullExpressionValue(homeRandomButtonTv2, "homeRandomButtonTv");
                homeRandomButtonTv2.setVisibility(8);
            }
        } else if (data instanceof Resource.Failure) {
            fragmentHomeBinding.homeLoadingShimmer.stopShimmer();
            fragmentHomeBinding.homeReloadConnectionerror.setOnClickListener(homeFragment.apiChangeClickListener);
            fragmentHomeBinding.homeReloadConnectionOpenInBrowser.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.onBindingCreated$lambda$5$0$4(this.f$0, view);
                }
            });
            FrameLayout homeLoading2 = fragmentHomeBinding.homeLoading;
            Intrinsics.checkNotNullExpressionValue(homeLoading2, "homeLoading");
            homeLoading2.setVisibility(8);
            LinearLayout homeLoadingError2 = fragmentHomeBinding.homeLoadingError;
            Intrinsics.checkNotNullExpressionValue(homeLoadingError2, "homeLoadingError");
            homeLoadingError2.setVisibility(0);
            RecyclerView homeMasterRecycler2 = fragmentHomeBinding.homeMasterRecycler;
            Intrinsics.checkNotNullExpressionValue(homeMasterRecycler2, "homeMasterRecycler");
            homeMasterRecycler2.setVisibility(4);
            Context context = homeFragment.getContext();
            boolean z2 = (context == null || AppContextUtils.INSTANCE.isNetworkAvailable(context)) ? false : true;
            Resource.Failure failure = (Resource.Failure) data;
            boolean zIsNetworkError = failure.isNetworkError();
            MaterialButton homeReloadConnectionGoToDownloads = fragmentHomeBinding.homeReloadConnectionGoToDownloads;
            Intrinsics.checkNotNullExpressionValue(homeReloadConnectionGoToDownloads, "homeReloadConnectionGoToDownloads");
            MaterialButton materialButton = homeReloadConnectionGoToDownloads;
            if (!z2 && !zIsNetworkError) {
                z = false;
            }
            materialButton.setVisibility(z ? 0 : 8);
            MaterialButton homeReloadConnectionOpenInBrowser = fragmentHomeBinding.homeReloadConnectionOpenInBrowser;
            Intrinsics.checkNotNullExpressionValue(homeReloadConnectionOpenInBrowser, "homeReloadConnectionOpenInBrowser");
            homeReloadConnectionOpenInBrowser.setVisibility(z2 ? 8 : 0);
            TextView textView = fragmentHomeBinding.resultErrorText;
            if (z2) {
                errorString = homeFragment.getString(R.string.no_internet_connection);
            } else {
                errorString = failure.getErrorString();
            }
            textView.setText(errorString);
            fragmentHomeBinding.homeReloadConnectionGoToDownloads.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.onBindingCreated$lambda$5$0$5(this.f$0, view);
                }
            });
            RecyclerView.Adapter adapter2 = fragmentHomeBinding.homeMasterRecycler.getAdapter();
            ParentItemAdapter parentItemAdapter3 = adapter2 instanceof ParentItemAdapter ? (ParentItemAdapter) adapter2 : null;
            if (parentItemAdapter3 != null) {
                BaseAdapter.submitList$default(parentItemAdapter3, null, null, 2, null);
                parentItemAdapter3.clearState();
            }
        } else {
            if (!(data instanceof Resource.Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            fragmentHomeBinding.homeLoadingShimmer.startShimmer();
            FrameLayout homeLoading3 = fragmentHomeBinding.homeLoading;
            Intrinsics.checkNotNullExpressionValue(homeLoading3, "homeLoading");
            homeLoading3.setVisibility(0);
            LinearLayout homeLoadingError3 = fragmentHomeBinding.homeLoadingError;
            Intrinsics.checkNotNullExpressionValue(homeLoadingError3, "homeLoadingError");
            homeLoadingError3.setVisibility(8);
            RecyclerView homeMasterRecycler3 = fragmentHomeBinding.homeMasterRecycler;
            Intrinsics.checkNotNullExpressionValue(homeMasterRecycler3, "homeMasterRecycler");
            homeMasterRecycler3.setVisibility(4);
            RecyclerView.Adapter adapter3 = fragmentHomeBinding.homeMasterRecycler.getAdapter();
            ParentItemAdapter parentItemAdapter4 = adapter3 instanceof ParentItemAdapter ? (ParentItemAdapter) adapter3 : null;
            if (parentItemAdapter4 != null) {
                BaseAdapter.submitList$default(parentItemAdapter4, null, null, 2, null);
                parentItemAdapter4.clearState();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$5$0$3(List list, HomeFragment homeFragment, View view) {
        SearchResponse searchResponse = (SearchResponse) CollectionsKt.randomOrNull(list, Random.INSTANCE);
        if (searchResponse != null) {
            AppContextUtils.loadSearchResult$default(AppContextUtils.INSTANCE, homeFragment.getActivity(), searchResponse, 0, null, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$5$0$4(final HomeFragment homeFragment, View view) {
        final AtomicList<MainAPI> apis = APIHolder.INSTANCE.getApis();
        UIHelper uIHelper = UIHelper.INSTANCE;
        Intrinsics.checkNotNull(view);
        AtomicList<MainAPI> atomicList = apis;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicList, 10));
        int i = 0;
        for (MainAPI mainAPI : atomicList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new Pair(Integer.valueOf(i), mainAPI.getName()));
            i = i2;
        }
        uIHelper.popupMenuNoIconsAndNoStringRes(view, arrayList, new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeFragment.onBindingCreated$lambda$5$0$4$1(apis, homeFragment, (MenuItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$5$0$4$1(AtomicList atomicList, HomeFragment homeFragment, MenuItem popupMenuNoIconsAndNoStringRes) {
        Intrinsics.checkNotNullParameter(popupMenuNoIconsAndNoStringRes, "$this$popupMenuNoIconsAndNoStringRes");
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(((MainAPI) atomicList.get(popupMenuNoIconsAndNoStringRes.getItemId())).getMainUrl()));
            homeFragment.startActivity(intent);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$5$0$5(HomeFragment homeFragment, View view) {
        UIHelper.navigate$default(UIHelper.INSTANCE, homeFragment.getActivity(), R.id.navigation_downloads, null, null, 6, null);
    }

    static final Unit onBindingCreated$lambda$6(final HomeFragment homeFragment, Pair pair) {
        if (pair == null) {
            BottomSheetDialog bottomSheetDialog = homeFragment.bottomSheetDialog;
            if (bottomSheetDialog != null) {
                UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
            }
            homeFragment.bottomSheetDialog = null;
            return Unit.INSTANCE;
        }
        if (homeFragment.bottomSheetDialog != null) {
            return Unit.INSTANCE;
        }
        HomeViewModel.ExpandableHomepageList expandableHomepageList = (HomeViewModel.ExpandableHomepageList) pair.component1();
        Function0<Unit> function0 = (Function0) pair.component2();
        FragmentActivity activity = homeFragment.getActivity();
        homeFragment.bottomSheetDialog = activity != null ? INSTANCE.loadHomepageList(activity, expandableHomepageList, function0, new HomeFragment$onBindingCreated$7$1(homeFragment, null), new Function0() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HomeFragment.onBindingCreated$lambda$6$0(this.f$0);
            }
        }) : null;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$6$0(HomeFragment homeFragment) {
        HomeViewModel.popup$default(homeFragment.getHomeViewModel(), null, null, 2, null);
        homeFragment.bottomSheetDialog = null;
        return Unit.INSTANCE;
    }

    private final void handleTvBackPress(BackPressedCallbackHelper.CallbackHelper helper) {
        View currentFocus;
        View viewFindViewById;
        MaterialButton materialButton;
        RecyclerView recyclerView;
        if (!Globals.INSTANCE.isLayout(2)) {
            helper.runDefault();
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null || (currentFocus = activity.getCurrentFocus()) == null) {
            helper.runDefault();
            return;
        }
        for (ViewParent parent = currentFocus.getParent(); parent != null; parent = parent.getParent()) {
            if ((parent instanceof View) && ((View) parent).getId() == R.id.home_master_recycler) {
                FragmentHomeBinding binding = getBinding();
                if (binding != null && (recyclerView = binding.homeMasterRecycler) != null) {
                    recyclerView.scrollToPosition(0);
                }
                FragmentHomeBinding binding2 = getBinding();
                if (binding2 == null || (materialButton = binding2.homeChangeApi) == null) {
                    return;
                }
                materialButton.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.home.HomeFragment$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeFragment.handleTvBackPress$lambda$1(this.f$0);
                    }
                });
                return;
            }
        }
        if (currentFocus.getId() == R.id.home_change_api || currentFocus.getId() == R.id.home_preview_reload_provider || currentFocus.getId() == R.id.home_preview_search_button) {
            FragmentActivity activity2 = getActivity();
            if (activity2 == null || (viewFindViewById = activity2.findViewById(R.id.navigation_home)) == null) {
                return;
            }
            viewFindViewById.requestFocus();
            return;
        }
        helper.runDefault();
    }

    static final void handleTvBackPress$lambda$1(HomeFragment homeFragment) {
        MaterialButton materialButton;
        FragmentHomeBinding binding = homeFragment.getBinding();
        if (binding == null || (materialButton = binding.homeChangeApi) == null) {
            return;
        }
        materialButton.requestFocus();
    }
}

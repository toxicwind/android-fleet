package androidx.navigation.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.os.BundleKt;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelStore;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.savedstate.SavedStateRegistry;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavHostFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 12\u00020\u00012\u00020\u0002:\u00011B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006H\u0015J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0015J\u0010\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001fH\u0015J&\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\"\u0010+\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010,\u001a\u00020-2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u001bH\u0017J\b\u00100\u001a\u00020\u0016H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010&\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u00062"}, d2 = {"Landroidx/navigation/fragment/NavHostFragment;", "Landroidx/fragment/app/Fragment;", "Landroidx/navigation/NavHost;", "<init>", "()V", "navHostController", "Landroidx/navigation/NavHostController;", "getNavHostController$navigation_fragment_release", "()Landroidx/navigation/NavHostController;", "navHostController$delegate", "Lkotlin/Lazy;", "viewParent", "Landroid/view/View;", "graphId", "", "defaultNavHost", "", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateNavHostController", "onCreateNavController", "createFragmentNavigator", "Landroidx/navigation/Navigator;", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "containerId", "getContainerId", "()I", "onViewCreated", "view", "onInflate", "attrs", "Landroid/util/AttributeSet;", "onSaveInstanceState", "outState", "onDestroyView", "Companion", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class NavHostFragment extends Fragment implements NavHost {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost";
    public static final String KEY_GRAPH_ID = "android-support-nav:fragment:graphId";
    private static final String KEY_NAV_CONTROLLER_STATE = "android-support-nav:fragment:navControllerState";
    public static final String KEY_START_DESTINATION_ARGS = "android-support-nav:fragment:startDestinationArgs";
    private boolean defaultNavHost;
    private int graphId;

    /* JADX INFO: renamed from: navHostController$delegate, reason: from kotlin metadata */
    private final Lazy navHostController = LazyKt.lazy(new Function0() { // from class: androidx.navigation.fragment.NavHostFragment$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return NavHostFragment.navHostController_delegate$lambda$6(this.f$0);
        }
    });
    private View viewParent;

    @JvmStatic
    public static final NavHostFragment create(int i) {
        return INSTANCE.create(i);
    }

    @JvmStatic
    public static final NavHostFragment create(int i, Bundle bundle) {
        return INSTANCE.create(i, bundle);
    }

    @JvmStatic
    public static final NavController findNavController(Fragment fragment) {
        return INSTANCE.findNavController(fragment);
    }

    public final NavHostController getNavHostController$navigation_fragment_release() {
        return (NavHostController) this.navHostController.getValue();
    }

    static final NavHostController navHostController_delegate$lambda$6(final NavHostFragment navHostFragment) {
        Context context = navHostFragment.getContext();
        if (context == null) {
            throw new IllegalStateException("NavController cannot be created before the fragment is attached".toString());
        }
        final NavHostController navHostController = new NavHostController(context);
        navHostController.setLifecycleOwner(navHostFragment);
        ViewModelStore viewModelStore = navHostFragment.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "<get-viewModelStore>(...)");
        navHostController.setViewModelStore(viewModelStore);
        navHostFragment.onCreateNavHostController(navHostController);
        Bundle bundleConsumeRestoredStateForKey = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey(KEY_NAV_CONTROLLER_STATE);
        if (bundleConsumeRestoredStateForKey != null) {
            navHostController.restoreState(bundleConsumeRestoredStateForKey);
        }
        navHostFragment.getSavedStateRegistry().registerSavedStateProvider(KEY_NAV_CONTROLLER_STATE, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.navigation.fragment.NavHostFragment$$ExternalSyntheticLambda1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return NavHostFragment.navHostController_delegate$lambda$6$lambda$5$lambda$2(navHostController);
            }
        });
        Bundle bundleConsumeRestoredStateForKey2 = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey(KEY_GRAPH_ID);
        if (bundleConsumeRestoredStateForKey2 != null) {
            navHostFragment.graphId = bundleConsumeRestoredStateForKey2.getInt(KEY_GRAPH_ID);
        }
        navHostFragment.getSavedStateRegistry().registerSavedStateProvider(KEY_GRAPH_ID, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.navigation.fragment.NavHostFragment$$ExternalSyntheticLambda2
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return NavHostFragment.navHostController_delegate$lambda$6$lambda$5$lambda$4(this.f$0);
            }
        });
        int i = navHostFragment.graphId;
        if (i != 0) {
            navHostController.setGraph(i);
            return navHostController;
        }
        Bundle arguments = navHostFragment.getArguments();
        int i2 = arguments != null ? arguments.getInt(KEY_GRAPH_ID) : 0;
        Bundle bundle = arguments != null ? arguments.getBundle(KEY_START_DESTINATION_ARGS) : null;
        if (i2 != 0) {
            navHostController.setGraph(i2, bundle);
        }
        return navHostController;
    }

    static final Bundle navHostController_delegate$lambda$6$lambda$5$lambda$2(NavHostController navHostController) {
        Bundle bundleSaveState = navHostController.saveState();
        if (bundleSaveState != null) {
            return bundleSaveState;
        }
        Bundle EMPTY = Bundle.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return EMPTY;
    }

    static final Bundle navHostController_delegate$lambda$6$lambda$5$lambda$4(NavHostFragment navHostFragment) {
        int i = navHostFragment.graphId;
        if (i != 0) {
            return BundleKt.bundleOf(TuplesKt.to(KEY_GRAPH_ID, Integer.valueOf(i)));
        }
        Bundle bundle = Bundle.EMPTY;
        Intrinsics.checkNotNull(bundle);
        return bundle;
    }

    @Override // androidx.navigation.NavHost
    public final NavController getNavController() {
        return getNavHostController$navigation_fragment_release();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (this.defaultNavHost) {
            getParentFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        getNavHostController$navigation_fragment_release();
        if (savedInstanceState != null && savedInstanceState.getBoolean(KEY_DEFAULT_NAV_HOST, false)) {
            this.defaultNavHost = true;
            getParentFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
        }
        super.onCreate(savedInstanceState);
    }

    protected void onCreateNavHostController(NavHostController navHostController) {
        Intrinsics.checkNotNullParameter(navHostController, "navHostController");
        onCreateNavController(navHostController);
    }

    @Deprecated(message = "Override {@link #onCreateNavHostController(NavHostController)} to gain\n      access to the full {@link NavHostController} that is created by this NavHostFragment.")
    protected void onCreateNavController(NavController navController) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        NavigatorProvider navigatorProvider = navController.getNavigatorProvider();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        navigatorProvider.addNavigator(new DialogFragmentNavigator(contextRequireContext, childFragmentManager));
        navController.getNavigatorProvider().addNavigator(createFragmentNavigator());
    }

    @Deprecated(message = "Use {@link #onCreateNavController(NavController)}")
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        return new FragmentNavigator(contextRequireContext, childFragmentManager, getContainerId());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Context context = inflater.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        FragmentContainerView fragmentContainerView = new FragmentContainerView(context);
        fragmentContainerView.setId(getContainerId());
        return fragmentContainerView;
    }

    private final int getContainerId() {
        int id = getId();
        return (id == 0 || id == -1) ? R.id.nav_host_fragment_container : id;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (!(view instanceof ViewGroup)) {
            throw new IllegalStateException(("created host view " + view + " is not a ViewGroup").toString());
        }
        Navigation.setViewNavController(view, getNavHostController$navigation_fragment_release());
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getParent() != null) {
            Object parent = viewGroup.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            View view2 = (View) parent;
            this.viewParent = view2;
            Intrinsics.checkNotNull(view2);
            if (view2.getId() == getId()) {
                View view3 = this.viewParent;
                Intrinsics.checkNotNull(view3);
                Navigation.setViewNavController(view3, getNavHostController$navigation_fragment_release());
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        super.onInflate(context, attrs, savedInstanceState);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, androidx.navigation.R.styleable.NavHost);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(androidx.navigation.R.styleable.NavHost_navGraph, 0);
        if (resourceId != 0) {
            this.graphId = resourceId;
        }
        Unit unit = Unit.INSTANCE;
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attrs, R.styleable.NavHostFragment);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes2, "obtainStyledAttributes(...)");
        if (typedArrayObtainStyledAttributes2.getBoolean(R.styleable.NavHostFragment_defaultNavHost, false)) {
            this.defaultNavHost = true;
        }
        Unit unit2 = Unit.INSTANCE;
        typedArrayObtainStyledAttributes2.recycle();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        if (this.defaultNavHost) {
            outState.putBoolean(KEY_DEFAULT_NAV_HOST, true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        View view = this.viewParent;
        if (view != null && Navigation.findNavController(view) == getNavHostController$navigation_fragment_release()) {
            Navigation.setViewNavController(view, null);
        }
        this.viewParent = null;
    }

    /* JADX INFO: compiled from: NavHostFragment.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001e\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/navigation/fragment/NavHostFragment$Companion;", "", "<init>", "()V", "KEY_GRAPH_ID", "", "KEY_START_DESTINATION_ARGS", "KEY_NAV_CONTROLLER_STATE", "KEY_DEFAULT_NAV_HOST", "findNavController", "Landroidx/navigation/NavController;", "fragment", "Landroidx/fragment/app/Fragment;", "create", "Landroidx/navigation/fragment/NavHostFragment;", "graphResId", "", "startDestinationArgs", "Landroid/os/Bundle;", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final NavHostFragment create(int i) {
            return create$default(this, i, null, 2, null);
        }

        private Companion() {
        }

        @JvmStatic
        public final NavController findNavController(Fragment fragment) {
            Dialog dialog;
            Window window;
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            for (Fragment parentFragment = fragment; parentFragment != null; parentFragment = parentFragment.getParentFragment()) {
                if (parentFragment instanceof NavHostFragment) {
                    return ((NavHostFragment) parentFragment).getNavHostController$navigation_fragment_release();
                }
                Fragment primaryNavigationFragment = parentFragment.getParentFragmentManager().getPrimaryNavigationFragment();
                if (primaryNavigationFragment instanceof NavHostFragment) {
                    return ((NavHostFragment) primaryNavigationFragment).getNavHostController$navigation_fragment_release();
                }
            }
            View view = fragment.getView();
            if (view != null) {
                return Navigation.findNavController(view);
            }
            View decorView = null;
            DialogFragment dialogFragment = fragment instanceof DialogFragment ? (DialogFragment) fragment : null;
            if (dialogFragment != null && (dialog = dialogFragment.getDialog()) != null && (window = dialog.getWindow()) != null) {
                decorView = window.getDecorView();
            }
            if (decorView != null) {
                return Navigation.findNavController(decorView);
            }
            throw new IllegalStateException("Fragment " + fragment + " does not have a NavController set");
        }

        public static /* synthetic */ NavHostFragment create$default(Companion companion, int i, Bundle bundle, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                bundle = null;
            }
            return companion.create(i, bundle);
        }

        @JvmStatic
        public final NavHostFragment create(int graphResId, Bundle startDestinationArgs) {
            Bundle bundle;
            if (graphResId != 0) {
                bundle = new Bundle();
                bundle.putInt(NavHostFragment.KEY_GRAPH_ID, graphResId);
            } else {
                bundle = null;
            }
            if (startDestinationArgs != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putBundle(NavHostFragment.KEY_START_DESTINATION_ARGS, startDestinationArgs);
            }
            NavHostFragment navHostFragment = new NavHostFragment();
            if (bundle != null) {
                navHostFragment.setArguments(bundle);
            }
            return navHostFragment;
        }
    }
}

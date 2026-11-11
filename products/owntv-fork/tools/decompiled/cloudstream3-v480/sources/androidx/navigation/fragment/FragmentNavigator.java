package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.activity.BackEventCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.NavigatorState;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.HttpHeaders;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: FragmentNavigator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Navigator.Name("fragment")
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u0000 D2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004BCDEB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0002J%\u0010(\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\u0006\u0010%\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0002\b)J\u0018\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\fH\u0016J\b\u0010-\u001a\u00020\u0002H\u0016J*\u0010.\u001a\u00020'2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u000101H\u0017J*\u00102\u001a\u00020!2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u000107H\u0016J$\u00102\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00192\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u000107H\u0002J\u0010\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\u0019H\u0016J\u001a\u0010:\u001a\u00020;2\u0006\u0010%\u001a\u00020\u00192\b\u00104\u001a\u0004\u0018\u000105H\u0002J\n\u0010<\u001a\u0004\u0018\u000101H\u0016J\u0010\u0010=\u001a\u00020!2\u0006\u0010,\u001a\u000201H\u0016J$\u0010>\u001a\u00020!2\u0006\u0010?\u001a\u00020\u00102\b\b\u0002\u0010@\u001a\u00020\f2\b\b\u0002\u0010A\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\f0\u00130\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001d0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator;", "Landroidx/navigation/Navigator;", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "context", "Landroid/content/Context;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "containerId", "", "<init>", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;I)V", "isLoggingEnabled", "", "level", "savedIds", "", "", "pendingOps", "", "Lkotlin/Pair;", "getPendingOps$navigation_fragment_release", "()Ljava/util/List;", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "getBackStack$navigation_fragment_release", "()Lkotlinx/coroutines/flow/StateFlow;", "fragmentObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "fragmentViewObserver", "Lkotlin/Function1;", "onAttach", "", "state", "Landroidx/navigation/NavigatorState;", "attachObservers", "entry", "fragment", "Landroidx/fragment/app/Fragment;", "attachClearViewModel", "attachClearViewModel$navigation_fragment_release", "popBackStack", "popUpTo", "savedState", "createDestination", "instantiateFragment", "className", "args", "Landroid/os/Bundle;", "navigate", "entries", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "onLaunchSingleTop", "backStackEntry", "createFragmentTransaction", "Landroidx/fragment/app/FragmentTransaction;", "onSaveState", "onRestoreState", "addPendingOps", "id", "isPop", "deduplicate", HttpHeaders.Destination, "Extras", "Companion", "ClearEntryStateViewModel", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class FragmentNavigator extends Navigator<Destination> {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_SAVED_IDS = "androidx-nav-fragment:navigator:savedIds";
    private static final String TAG = "FragmentNavigator";
    private final int containerId;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final LifecycleEventObserver fragmentObserver;
    private final Function1<NavBackStackEntry, LifecycleEventObserver> fragmentViewObserver;
    private final List<Pair<String, Boolean>> pendingOps;
    private final Set<String> savedIds;

    public FragmentNavigator(Context context, FragmentManager fragmentManager, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = i;
        this.savedIds = new LinkedHashSet();
        this.pendingOps = new ArrayList();
        this.fragmentObserver = new LifecycleEventObserver() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FragmentNavigator.fragmentObserver$lambda$1(this.f$0, lifecycleOwner, event);
            }
        };
        this.fragmentViewObserver = new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FragmentNavigator.fragmentViewObserver$lambda$3(this.f$0, (NavBackStackEntry) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLoggingEnabled(int level) {
        return Log.isLoggable(FragmentManager.TAG, level) || Log.isLoggable(TAG, level);
    }

    public final List<Pair<String, Boolean>> getPendingOps$navigation_fragment_release() {
        return this.pendingOps;
    }

    public final StateFlow<List<NavBackStackEntry>> getBackStack$navigation_fragment_release() {
        return getState().getBackStack();
    }

    static final void fragmentObserver$lambda$1(FragmentNavigator fragmentNavigator, LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            Fragment fragment = (Fragment) source;
            Object obj = null;
            for (Object obj2 : fragmentNavigator.getState().getTransitionsInProgress().getValue()) {
                if (Intrinsics.areEqual(((NavBackStackEntry) obj2).getId(), fragment.getTag())) {
                    obj = obj2;
                }
            }
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
            if (navBackStackEntry != null) {
                if (fragmentNavigator.isLoggingEnabled(2)) {
                    Log.v(TAG, "Marking transition complete for entry " + navBackStackEntry + " due to fragment " + source + " lifecycle reaching DESTROYED");
                }
                fragmentNavigator.getState().markTransitionComplete(navBackStackEntry);
            }
        }
    }

    static final LifecycleEventObserver fragmentViewObserver$lambda$3(final FragmentNavigator fragmentNavigator, final NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        return new LifecycleEventObserver() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FragmentNavigator.fragmentViewObserver$lambda$3$lambda$2(this.f$0, entry, lifecycleOwner, event);
            }
        };
    }

    static final void fragmentViewObserver$lambda$3$lambda$2(FragmentNavigator fragmentNavigator, NavBackStackEntry navBackStackEntry, LifecycleOwner owner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_RESUME && fragmentNavigator.getState().getBackStack().getValue().contains(navBackStackEntry)) {
            if (fragmentNavigator.isLoggingEnabled(2)) {
                Log.v(TAG, "Marking transition complete for entry " + navBackStackEntry + " due to fragment " + owner + " view lifecycle reaching RESUMED");
            }
            fragmentNavigator.getState().markTransitionComplete(navBackStackEntry);
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (fragmentNavigator.isLoggingEnabled(2)) {
                Log.v(TAG, "Marking transition complete for entry " + navBackStackEntry + " due to fragment " + owner + " view lifecycle reaching DESTROYED");
            }
            fragmentNavigator.getState().markTransitionComplete(navBackStackEntry);
        }
    }

    @Override // androidx.navigation.Navigator
    public void onAttach(final NavigatorState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onAttach(state);
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "onAttach");
        }
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda5
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public final void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
                FragmentNavigator.onAttach$lambda$5(state, this, fragmentManager, fragment);
            }
        });
        this.fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: androidx.navigation.fragment.FragmentNavigator.onAttach.2
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public /* synthetic */ void onBackStackChangeCancelled() {
                FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeCancelled(this);
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public /* synthetic */ void onBackStackChangeProgressed(BackEventCompat backEventCompat) {
                FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeProgressed(this, backEventCompat);
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeStarted(Fragment fragment, boolean pop) {
                NavBackStackEntry navBackStackEntryPrevious;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                if (pop) {
                    List<NavBackStackEntry> value = state.getBackStack().getValue();
                    ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
                    do {
                        if (!listIterator.hasPrevious()) {
                            navBackStackEntryPrevious = null;
                            break;
                        }
                        navBackStackEntryPrevious = listIterator.previous();
                    } while (!Intrinsics.areEqual(navBackStackEntryPrevious.getId(), fragment.getTag()));
                    NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
                    if (this.isLoggingEnabled(2)) {
                        Log.v(FragmentNavigator.TAG, "OnBackStackChangedStarted for fragment " + fragment + " associated with entry " + navBackStackEntry);
                    }
                    if (navBackStackEntry != null) {
                        state.prepareForTransition(navBackStackEntry);
                    }
                }
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeCommitted(Fragment fragment, boolean pop) {
                Object obj;
                Object objPrevious;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                List listPlus = CollectionsKt.plus((Collection) state.getBackStack().getValue(), (Iterable) state.getTransitionsInProgress().getValue());
                ListIterator listIterator = listPlus.listIterator(listPlus.size());
                do {
                    obj = null;
                    if (!listIterator.hasPrevious()) {
                        objPrevious = null;
                        break;
                    }
                    objPrevious = listIterator.previous();
                } while (!Intrinsics.areEqual(((NavBackStackEntry) objPrevious).getId(), fragment.getTag()));
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) objPrevious;
                boolean z = pop && this.getPendingOps$navigation_fragment_release().isEmpty() && fragment.isRemoving();
                for (Object obj2 : this.getPendingOps$navigation_fragment_release()) {
                    if (Intrinsics.areEqual(((Pair) obj2).getFirst(), fragment.getTag())) {
                        obj = obj2;
                        break;
                    }
                }
                Pair pair = (Pair) obj;
                if (pair != null) {
                    this.getPendingOps$navigation_fragment_release().remove(pair);
                }
                if (!z && this.isLoggingEnabled(2)) {
                    Log.v(FragmentNavigator.TAG, "OnBackStackChangedCommitted for fragment " + fragment + " associated with entry " + navBackStackEntry);
                }
                boolean z2 = pair != null && ((Boolean) pair.getSecond()).booleanValue();
                if (!pop && !z2 && navBackStackEntry == null) {
                    throw new IllegalArgumentException(("The fragment " + fragment + " is unknown to the FragmentNavigator. Please use the navigate() function to add fragments to the FragmentNavigator managed FragmentManager.").toString());
                }
                if (navBackStackEntry != null) {
                    this.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, state);
                    if (z) {
                        if (this.isLoggingEnabled(2)) {
                            Log.v(FragmentNavigator.TAG, "OnBackStackChangedCommitted for fragment " + fragment + " popping associated entry " + navBackStackEntry + " via system back");
                        }
                        state.popWithTransition(navBackStackEntry, false);
                    }
                }
            }
        });
    }

    static final void onAttach$lambda$5(NavigatorState navigatorState, FragmentNavigator fragmentNavigator, FragmentManager fragmentManager, Fragment fragment) {
        NavBackStackEntry navBackStackEntryPrevious;
        Intrinsics.checkNotNullParameter(fragmentManager, "<unused var>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        List<NavBackStackEntry> value = navigatorState.getBackStack().getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        do {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
        } while (!Intrinsics.areEqual(navBackStackEntryPrevious.getId(), fragment.getTag()));
        NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
        if (fragmentNavigator.isLoggingEnabled(2)) {
            Log.v(TAG, "Attaching fragment " + fragment + " associated with entry " + navBackStackEntry + " to FragmentManager " + fragmentNavigator.fragmentManager);
        }
        if (navBackStackEntry != null) {
            fragmentNavigator.attachObservers(navBackStackEntry, fragment);
            fragmentNavigator.attachClearViewModel$navigation_fragment_release(fragment, navBackStackEntry, navigatorState);
        }
    }

    private final void attachObservers(final NavBackStackEntry entry, final Fragment fragment) {
        fragment.getViewLifecycleOwnerLiveData().observe(fragment, new FragmentNavigator$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FragmentNavigator.attachObservers$lambda$7(this.f$0, fragment, entry, (LifecycleOwner) obj);
            }
        }));
        fragment.getLifecycleRegistry().addObserver(this.fragmentObserver);
    }

    static final Unit attachObservers$lambda$7(FragmentNavigator fragmentNavigator, Fragment fragment, NavBackStackEntry navBackStackEntry, LifecycleOwner lifecycleOwner) {
        List<Pair<String, Boolean>> list = fragmentNavigator.pendingOps;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), fragment.getTag())) {
                    z = true;
                    break;
                }
            }
        }
        if (lifecycleOwner != null && !z) {
            Lifecycle lifecycleRegistry = fragment.getViewLifecycleOwner().getLifecycleRegistry();
            if (lifecycleRegistry.getState().isAtLeast(Lifecycle.State.CREATED)) {
                lifecycleRegistry.addObserver(fragmentNavigator.fragmentViewObserver.invoke(navBackStackEntry));
            }
        }
        return Unit.INSTANCE;
    }

    public final void attachClearViewModel$navigation_fragment_release(final Fragment fragment, final NavBackStackEntry entry, final NavigatorState state) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(state, "state");
        ViewModelStore viewModelStore = fragment.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "<get-viewModelStore>(...)");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(ClearEntryStateViewModel.class), new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FragmentNavigator.attachClearViewModel$lambda$9$lambda$8((CreationExtras) obj);
            }
        });
        ((ClearEntryStateViewModel) new ViewModelProvider(viewModelStore, initializerViewModelFactoryBuilder.build(), CreationExtras.Empty.INSTANCE).get(ClearEntryStateViewModel.class)).setCompleteTransition(new WeakReference<>(new Function0() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FragmentNavigator.attachClearViewModel$lambda$12(entry, state, this, fragment);
            }
        }));
    }

    static final ClearEntryStateViewModel attachClearViewModel$lambda$9$lambda$8(CreationExtras initializer) {
        Intrinsics.checkNotNullParameter(initializer, "$this$initializer");
        return new ClearEntryStateViewModel();
    }

    static final Unit attachClearViewModel$lambda$12(NavBackStackEntry navBackStackEntry, NavigatorState navigatorState, FragmentNavigator fragmentNavigator, Fragment fragment) {
        for (NavBackStackEntry navBackStackEntry2 : navigatorState.getTransitionsInProgress().getValue()) {
            if (fragmentNavigator.isLoggingEnabled(2)) {
                Log.v(TAG, "Marking transition complete for entry " + navBackStackEntry2 + " due to fragment " + fragment + " viewmodel being cleared");
            }
            navigatorState.markTransitionComplete(navBackStackEntry2);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.navigation.Navigator
    public void popBackStack(NavBackStackEntry popUpTo, boolean savedState) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return;
        }
        List<NavBackStackEntry> value = getState().getBackStack().getValue();
        int iIndexOf = value.indexOf(popUpTo);
        List<NavBackStackEntry> listSubList = value.subList(iIndexOf, value.size());
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.first((List) value);
        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) CollectionsKt.getOrNull(value, iIndexOf - 1);
        if (navBackStackEntry2 != null) {
            addPendingOps$default(this, navBackStackEntry2.getId(), false, false, 6, null);
        }
        List<NavBackStackEntry> list = listSubList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            NavBackStackEntry navBackStackEntry3 = (NavBackStackEntry) obj;
            if (SequencesKt.contains(SequencesKt.map(CollectionsKt.asSequence(this.pendingOps), new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return FragmentNavigator.popBackStack$lambda$14$lambda$13((Pair) obj2);
                }
            }), navBackStackEntry3.getId()) || !Intrinsics.areEqual(navBackStackEntry3.getId(), navBackStackEntry.getId())) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            addPendingOps$default(this, ((NavBackStackEntry) it.next()).getId(), true, false, 4, null);
        }
        if (savedState) {
            for (NavBackStackEntry navBackStackEntry4 : CollectionsKt.reversed(list)) {
                if (Intrinsics.areEqual(navBackStackEntry4, navBackStackEntry)) {
                    Log.i(TAG, "FragmentManager cannot save the state of the initial destination " + navBackStackEntry4);
                } else {
                    this.fragmentManager.saveBackStack(navBackStackEntry4.getId());
                    this.savedIds.add(navBackStackEntry4.getId());
                }
            }
        } else {
            this.fragmentManager.popBackStack(popUpTo.getId(), 1);
        }
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "Calling popWithTransition via popBackStack() on entry " + popUpTo + " with savedState " + savedState);
        }
        getState().popWithTransition(popUpTo, savedState);
    }

    static final String popBackStack$lambda$14$lambda$13(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (String) it.getFirst();
    }

    @Override // androidx.navigation.Navigator
    public Destination createDestination() {
        return new Destination(this);
    }

    @Deprecated(message = "Set a custom {@link androidx.fragment.app.FragmentFactory} via\n      {@link FragmentManager#setFragmentFactory(FragmentFactory)} to control\n      instantiation of Fragments.")
    public Fragment instantiateFragment(Context context, FragmentManager fragmentManager, String className, Bundle args) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(className, "className");
        Fragment fragmentInstantiate = fragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(fragmentInstantiate, "instantiate(...)");
        return fragmentInstantiate;
    }

    @Override // androidx.navigation.Navigator
    public void navigate(List<NavBackStackEntry> entries, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return;
        }
        Iterator<NavBackStackEntry> it = entries.iterator();
        while (it.hasNext()) {
            navigate(it.next(), navOptions, navigatorExtras);
        }
    }

    private final void navigate(NavBackStackEntry entry, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean zIsEmpty = getState().getBackStack().getValue().isEmpty();
        if (navOptions != null && !zIsEmpty && navOptions.getRestoreState() && this.savedIds.remove(entry.getId())) {
            this.fragmentManager.restoreBackStack(entry.getId());
            getState().pushWithTransition(entry);
            return;
        }
        FragmentTransaction fragmentTransactionCreateFragmentTransaction = createFragmentTransaction(entry, navOptions);
        if (!zIsEmpty) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) getState().getBackStack().getValue());
            if (navBackStackEntry != null) {
                addPendingOps$default(this, navBackStackEntry.getId(), false, false, 6, null);
            }
            addPendingOps$default(this, entry.getId(), false, false, 6, null);
            fragmentTransactionCreateFragmentTransaction.addToBackStack(entry.getId());
        }
        if (navigatorExtras instanceof Extras) {
            for (Map.Entry<View, String> entry2 : ((Extras) navigatorExtras).getSharedElements().entrySet()) {
                fragmentTransactionCreateFragmentTransaction.addSharedElement(entry2.getKey(), entry2.getValue());
            }
        }
        fragmentTransactionCreateFragmentTransaction.commit();
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "Calling pushWithTransition via navigate() on entry " + entry);
        }
        getState().pushWithTransition(entry);
    }

    @Override // androidx.navigation.Navigator
    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring onLaunchSingleTop() call: FragmentManager has already saved its state");
            return;
        }
        FragmentTransaction fragmentTransactionCreateFragmentTransaction = createFragmentTransaction(backStackEntry, null);
        List<NavBackStackEntry> value = getState().getBackStack().getValue();
        if (value.size() > 1) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.getOrNull(value, CollectionsKt.getLastIndex(value) - 1);
            if (navBackStackEntry != null) {
                addPendingOps$default(this, navBackStackEntry.getId(), false, false, 6, null);
            }
            addPendingOps$default(this, backStackEntry.getId(), true, false, 4, null);
            this.fragmentManager.popBackStack(backStackEntry.getId(), 1);
            addPendingOps$default(this, backStackEntry.getId(), false, false, 2, null);
            fragmentTransactionCreateFragmentTransaction.addToBackStack(backStackEntry.getId());
        }
        fragmentTransactionCreateFragmentTransaction.commit();
        getState().onLaunchSingleTop(backStackEntry);
    }

    private final FragmentTransaction createFragmentTransaction(NavBackStackEntry entry, NavOptions navOptions) {
        NavDestination destination = entry.getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.fragment.FragmentNavigator.Destination");
        Bundle arguments = entry.getArguments();
        String className = ((Destination) destination).getClassName();
        if (className.charAt(0) == '.') {
            className = this.context.getPackageName() + className;
        }
        Fragment fragmentInstantiate = this.fragmentManager.getFragmentFactory().instantiate(this.context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(fragmentInstantiate, "instantiate(...)");
        fragmentInstantiate.setArguments(arguments);
        FragmentTransaction fragmentTransactionBeginTransaction = this.fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        int enterAnim = navOptions != null ? navOptions.getEnterAnim() : -1;
        int exitAnim = navOptions != null ? navOptions.getExitAnim() : -1;
        int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : -1;
        int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : -1;
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            if (enterAnim == -1) {
                enterAnim = 0;
            }
            if (exitAnim == -1) {
                exitAnim = 0;
            }
            if (popEnterAnim == -1) {
                popEnterAnim = 0;
            }
            fragmentTransactionBeginTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim != -1 ? popExitAnim : 0);
        }
        fragmentTransactionBeginTransaction.replace(this.containerId, fragmentInstantiate, entry.getId());
        fragmentTransactionBeginTransaction.setPrimaryNavigationFragment(fragmentInstantiate);
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        return fragmentTransactionBeginTransaction;
    }

    @Override // androidx.navigation.Navigator
    public Bundle onSaveState() {
        if (this.savedIds.isEmpty()) {
            return null;
        }
        return BundleKt.bundleOf(TuplesKt.to(KEY_SAVED_IDS, new ArrayList(this.savedIds)));
    }

    @Override // androidx.navigation.Navigator
    public void onRestoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        ArrayList<String> stringArrayList = savedState.getStringArrayList(KEY_SAVED_IDS);
        if (stringArrayList != null) {
            this.savedIds.clear();
            CollectionsKt.addAll(this.savedIds, stringArrayList);
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0017J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Destination;", "Landroidx/navigation/NavDestination;", "fragmentNavigator", "Landroidx/navigation/Navigator;", "<init>", "(Landroidx/navigation/Navigator;)V", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "(Landroidx/navigation/NavigatorProvider;)V", "onInflate", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "setClassName", "className", "", "_className", "getClassName", "()Ljava/lang/String;", "toString", "equals", "", "other", "", "hashCode", "", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static class Destination extends NavDestination {
        private String _className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Destination(Navigator<? extends Destination> fragmentNavigator) {
            super(fragmentNavigator);
            Intrinsics.checkNotNullParameter(fragmentNavigator, "fragmentNavigator");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Destination(NavigatorProvider navigatorProvider) {
            this((Navigator<? extends Destination>) navigatorProvider.getNavigator(FragmentNavigator.class));
            Intrinsics.checkNotNullParameter(navigatorProvider, "navigatorProvider");
        }

        @Override // androidx.navigation.NavDestination
        public void onInflate(Context context, AttributeSet attrs) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(attrs, "attrs");
            super.onInflate(context, attrs);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attrs, R.styleable.FragmentNavigator);
            Intrinsics.checkNotNullExpressionValue(typedArrayObtainAttributes, "obtainAttributes(...)");
            String string = typedArrayObtainAttributes.getString(R.styleable.FragmentNavigator_android_name);
            if (string != null) {
                setClassName(string);
            }
            Unit unit = Unit.INSTANCE;
            typedArrayObtainAttributes.recycle();
        }

        public final Destination setClassName(String className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this._className = className;
            return this;
        }

        public final String getClassName() {
            String str = this._className;
            if (str == null) {
                throw new IllegalStateException("Fragment class was not set".toString());
            }
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            return str;
        }

        @Override // androidx.navigation.NavDestination
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this._className;
            if (str == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else {
                sb.append(str);
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @Override // androidx.navigation.NavDestination
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return other != null && (other instanceof Destination) && super.equals(other) && Intrinsics.areEqual(this._className, ((Destination) other)._className);
        }

        @Override // androidx.navigation.NavDestination
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this._className;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\rB\u001d\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\tj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Extras;", "Landroidx/navigation/Navigator$Extras;", "sharedElements", "", "Landroid/view/View;", "", "<init>", "(Ljava/util/Map;)V", "_sharedElements", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getSharedElements", "()Ljava/util/Map;", "Builder", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Extras implements Navigator.Extras {
        private final LinkedHashMap<View, String> _sharedElements;

        public Extras(Map<View, String> sharedElements) {
            Intrinsics.checkNotNullParameter(sharedElements, "sharedElements");
            LinkedHashMap<View, String> linkedHashMap = new LinkedHashMap<>();
            this._sharedElements = linkedHashMap;
            linkedHashMap.putAll(sharedElements);
        }

        public final Map<View, String> getSharedElements() {
            return MapsKt.toMap(this._sharedElements);
        }

        /* JADX INFO: compiled from: FragmentNavigator.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\u00002\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u000bJ\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0010R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Extras$Builder;", "", "<init>", "()V", "_sharedElements", "Ljava/util/LinkedHashMap;", "Landroid/view/View;", "", "Lkotlin/collections/LinkedHashMap;", "addSharedElements", "sharedElements", "", "addSharedElement", "sharedElement", "name", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/navigation/fragment/FragmentNavigator$Extras;", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Builder {
            private final LinkedHashMap<View, String> _sharedElements = new LinkedHashMap<>();

            public final Builder addSharedElements(Map<View, String> sharedElements) {
                Intrinsics.checkNotNullParameter(sharedElements, "sharedElements");
                for (Map.Entry<View, String> entry : sharedElements.entrySet()) {
                    addSharedElement(entry.getKey(), entry.getValue());
                }
                return this;
            }

            public final Builder addSharedElement(View sharedElement, String name) {
                Intrinsics.checkNotNullParameter(sharedElement, "sharedElement");
                Intrinsics.checkNotNullParameter(name, "name");
                this._sharedElements.put(sharedElement, name);
                return this;
            }

            public final Extras build() {
                return new Extras(this._sharedElements);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Companion;", "", "<init>", "()V", "TAG", "", "KEY_SAVED_IDS", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u0007H\u0014R&\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$ClearEntryStateViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "completeTransition", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function0;", "", "getCompleteTransition", "()Ljava/lang/ref/WeakReference;", "setCompleteTransition", "(Ljava/lang/ref/WeakReference;)V", "onCleared", "navigation-fragment_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ClearEntryStateViewModel extends ViewModel {
        public WeakReference<Function0<Unit>> completeTransition;

        public final WeakReference<Function0<Unit>> getCompleteTransition() {
            WeakReference<Function0<Unit>> weakReference = this.completeTransition;
            if (weakReference != null) {
                return weakReference;
            }
            Intrinsics.throwUninitializedPropertyAccessException("completeTransition");
            return null;
        }

        public final void setCompleteTransition(WeakReference<Function0<Unit>> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            this.completeTransition = weakReference;
        }

        @Override // androidx.lifecycle.ViewModel
        protected void onCleared() {
            super.onCleared();
            Function0<Unit> function0 = getCompleteTransition().get();
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    static /* synthetic */ void addPendingOps$default(FragmentNavigator fragmentNavigator, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addPendingOps");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        fragmentNavigator.addPendingOps(str, z, z2);
    }

    private final void addPendingOps(final String id, boolean isPop, boolean deduplicate) {
        if (deduplicate) {
            CollectionsKt.removeAll((List) this.pendingOps, new Function1() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FragmentNavigator.addPendingOps$lambda$16(id, (Pair) obj));
                }
            });
        }
        this.pendingOps.add(TuplesKt.to(id, Boolean.valueOf(isPop)));
    }

    static final boolean addPendingOps$lambda$16(String str, Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getFirst(), str);
    }
}

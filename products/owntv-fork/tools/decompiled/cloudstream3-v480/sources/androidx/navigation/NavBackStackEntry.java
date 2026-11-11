package androidx.navigation;

import android.app.Application;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.navigation.internal.NavBackStackEntryImpl;
import androidx.navigation.internal.NavContext;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavBackStackEntry.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 ^2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001^B_\b\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\u0004\b\u0013\u0010\u0014B#\b\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0000\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\u0004\b\u0013\u0010\u0017J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0007J\b\u0010A\u001a\u00020>H\u0007J\u0014\u0010U\u001a\u00020>2\n\u0010V\u001a\u00060\nj\u0002`\u000bH\u0007J\u0013\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0096\u0002J\b\u0010[\u001a\u00020\\H\u0016J\b\u0010]\u001a\u00020\u0011H\u0016R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001c\u0010\u0012\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0016\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b8FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b.\u0010 *\u0004\b,\u0010-R\u001b\u0010/\u001a\u0002008GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b1\u00102R\u001b\u00105\u001a\u0002068VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\b8\u00109*\u0004\b7\u0010-R$\u0010:\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r8G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010\"\"\u0004\b<\u0010$R\u001b\u0010B\u001a\u00020C8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\bE\u0010F*\u0004\bD\u0010-R\u001b\u0010G\u001a\u00020H8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\bJ\u0010K*\u0004\bI\u0010-R\u0014\u0010L\u001a\u00020M8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u001b\u0010P\u001a\u00020Q8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\bS\u0010T*\u0004\bR\u0010-¨\u0006_"}, d2 = {"Landroidx/navigation/NavBackStackEntry;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Landroidx/lifecycle/HasDefaultViewModelProviderFactory;", "Landroidx/savedstate/SavedStateRegistryOwner;", "context", "Landroidx/navigation/internal/NavContext;", "destination", "Landroidx/navigation/NavDestination;", "immutableArgs", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "hostLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", "viewModelStoreProvider", "Landroidx/navigation/NavViewModelStoreProvider;", "id", "", "savedState", "<init>", "(Landroidx/navigation/internal/NavContext;Landroidx/navigation/NavDestination;Landroid/os/Bundle;Landroidx/lifecycle/Lifecycle$State;Landroidx/navigation/NavViewModelStoreProvider;Ljava/lang/String;Landroid/os/Bundle;)V", "entry", "arguments", "(Landroidx/navigation/NavBackStackEntry;Landroid/os/Bundle;)V", "getContext$navigation_common_release", "()Landroidx/navigation/internal/NavContext;", ES6Iterator.VALUE_PROPERTY, "getDestination", "()Landroidx/navigation/NavDestination;", "setDestination", "(Landroidx/navigation/NavDestination;)V", "getImmutableArgs$navigation_common_release", "()Landroid/os/Bundle;", "getHostLifecycleState$navigation_common_release", "()Landroidx/lifecycle/Lifecycle$State;", "setHostLifecycleState$navigation_common_release", "(Landroidx/lifecycle/Lifecycle$State;)V", "getViewModelStoreProvider$navigation_common_release", "()Landroidx/navigation/NavViewModelStoreProvider;", "getId", "()Ljava/lang/String;", "getSavedState$navigation_common_release", "impl", "Landroidx/navigation/internal/NavBackStackEntryImpl;", "getArguments$delegate", "(Landroidx/navigation/NavBackStackEntry;)Ljava/lang/Object;", "getArguments", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getSavedStateHandle", "()Landroidx/lifecycle/SavedStateHandle;", "savedStateHandle$delegate", "Lkotlin/Lazy;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle$delegate", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "maxLifecycle", "getMaxLifecycle", "setMaxLifecycle", "handleLifecycleEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "updateState", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getViewModelStore$delegate", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "defaultViewModelProviderFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getDefaultViewModelProviderFactory$delegate", "getDefaultViewModelProviderFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "defaultViewModelCreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "getDefaultViewModelCreationExtras", "()Landroidx/lifecycle/viewmodel/CreationExtras;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry$delegate", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "saveState", "outBundle", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavBackStackEntry implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final NavContext context;
    private NavDestination destination;
    private Lifecycle.State hostLifecycleState;
    private final String id;
    private final Bundle immutableArgs;
    private final NavBackStackEntryImpl impl;
    private final Bundle savedState;

    /* JADX INFO: renamed from: savedStateHandle$delegate, reason: from kotlin metadata */
    private final Lazy savedStateHandle;
    private final NavViewModelStoreProvider viewModelStoreProvider;

    public /* synthetic */ NavBackStackEntry(NavContext navContext, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2, DefaultConstructorMarker defaultConstructorMarker) {
        this(navContext, navDestination, bundle, state, navViewModelStoreProvider, str, bundle2);
    }

    private NavBackStackEntry(NavContext navContext, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2) {
        this.context = navContext;
        this.destination = navDestination;
        this.immutableArgs = bundle;
        this.hostLifecycleState = state;
        this.viewModelStoreProvider = navViewModelStoreProvider;
        this.id = str;
        this.savedState = bundle2;
        this.impl = new NavBackStackEntryImpl(this);
        this.savedStateHandle = LazyKt.lazy(new Function0() { // from class: androidx.navigation.NavBackStackEntry$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.impl.getSavedStateHandle$navigation_common_release();
            }
        });
    }

    /* JADX INFO: renamed from: getContext$navigation_common_release, reason: from getter */
    public final NavContext getContext() {
        return this.context;
    }

    public final NavDestination getDestination() {
        return this.destination;
    }

    public final void setDestination(NavDestination navDestination) {
        Intrinsics.checkNotNullParameter(navDestination, "<set-?>");
        this.destination = navDestination;
    }

    /* JADX INFO: renamed from: getImmutableArgs$navigation_common_release, reason: from getter */
    public final Bundle getImmutableArgs() {
        return this.immutableArgs;
    }

    /* synthetic */ NavBackStackEntry(NavContext navContext, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(navContext, navDestination, (i & 4) != 0 ? null : bundle, (i & 8) != 0 ? Lifecycle.State.CREATED : state, (i & 16) != 0 ? null : navViewModelStoreProvider, (i & 32) != 0 ? INSTANCE.randomUUID$navigation_common_release() : str, (i & 64) != 0 ? null : bundle2);
    }

    /* JADX INFO: renamed from: getHostLifecycleState$navigation_common_release, reason: from getter */
    public final Lifecycle.State getHostLifecycleState() {
        return this.hostLifecycleState;
    }

    public final void setHostLifecycleState$navigation_common_release(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "<set-?>");
        this.hostLifecycleState = state;
    }

    /* JADX INFO: renamed from: getViewModelStoreProvider$navigation_common_release, reason: from getter */
    public final NavViewModelStoreProvider getViewModelStoreProvider() {
        return this.viewModelStoreProvider;
    }

    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: getSavedState$navigation_common_release, reason: from getter */
    public final Bundle getSavedState() {
        return this.savedState;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavBackStackEntry(NavBackStackEntry entry, Bundle bundle) {
        this(entry.context, entry.destination, bundle, entry.hostLifecycleState, entry.viewModelStoreProvider, entry.id, entry.savedState);
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.impl.setHostLifecycleState$navigation_common_release(entry.hostLifecycleState);
        this.impl.setMaxLifecycle$navigation_common_release(entry.getMaxLifecycle());
    }

    public /* synthetic */ NavBackStackEntry(NavBackStackEntry navBackStackEntry, Bundle bundle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(navBackStackEntry, (i & 2) != 0 ? navBackStackEntry.getArguments() : bundle);
    }

    /* JADX INFO: compiled from: NavBackStackEntry.android.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J^\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0010\b\u0002\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0007J\r\u0010\u0014\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Landroidx/navigation/NavBackStackEntry$Companion;", "", "<init>", "()V", "create", "Landroidx/navigation/NavBackStackEntry;", "context", "Landroidx/navigation/internal/NavContext;", "destination", "Landroidx/navigation/NavDestination;", "arguments", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "hostLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", "viewModelStoreProvider", "Landroidx/navigation/NavViewModelStoreProvider;", "id", "", "savedState", "randomUUID", "randomUUID$navigation_common_release", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ NavBackStackEntry create$default(Companion companion, NavContext navContext, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2, int i, Object obj) {
            if ((i & 4) != 0) {
                bundle = null;
            }
            if ((i & 8) != 0) {
                state = Lifecycle.State.CREATED;
            }
            if ((i & 16) != 0) {
                navViewModelStoreProvider = null;
            }
            if ((i & 32) != 0) {
                str = companion.randomUUID$navigation_common_release();
            }
            if ((i & 64) != 0) {
                bundle2 = null;
            }
            return companion.create(navContext, navDestination, bundle, state, navViewModelStoreProvider, str, bundle2);
        }

        public final NavBackStackEntry create(NavContext context, NavDestination destination, Bundle arguments, Lifecycle.State hostLifecycleState, NavViewModelStoreProvider viewModelStoreProvider, String id, Bundle savedState) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            Intrinsics.checkNotNullParameter(hostLifecycleState, "hostLifecycleState");
            Intrinsics.checkNotNullParameter(id, "id");
            return new NavBackStackEntry(context, destination, arguments, hostLifecycleState, viewModelStoreProvider, id, savedState, null);
        }

        public final String randomUUID$navigation_common_release() {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }

    public final Bundle getArguments() {
        return this.impl.getArguments$navigation_common_release();
    }

    public final SavedStateHandle getSavedStateHandle() {
        return (SavedStateHandle) this.savedStateHandle.getValue();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    /* JADX INFO: renamed from: getLifecycle */
    public Lifecycle getLifecycleRegistry() {
        return this.impl.getLifecycle();
    }

    public final Lifecycle.State getMaxLifecycle() {
        return this.impl.getMaxLifecycle();
    }

    public final void setMaxLifecycle(Lifecycle.State value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.impl.setMaxLifecycle$navigation_common_release(value);
    }

    public final void handleLifecycleEvent(Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.impl.handleLifecycleEvent$navigation_common_release(event);
    }

    public final void updateState() {
        this.impl.updateState$navigation_common_release();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        return this.impl.getViewModelStore$navigation_common_release();
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return this.impl.getDefaultViewModelProviderFactory();
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        MutableCreationExtras defaultViewModelCreationExtras$navigation_common_release = this.impl.getDefaultViewModelCreationExtras$navigation_common_release();
        NavContext navContext = this.context;
        Object application = navContext != null ? navContext.getApplication() : null;
        Application application2 = application instanceof Application ? (Application) application : null;
        if (application2 != null) {
            defaultViewModelCreationExtras$navigation_common_release.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, application2);
        }
        return defaultViewModelCreationExtras$navigation_common_release;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return this.impl.getSavedStateRegistry$navigation_common_release();
    }

    public final void saveState(Bundle outBundle) {
        Intrinsics.checkNotNullParameter(outBundle, "outBundle");
        this.impl.saveState$navigation_common_release(outBundle);
    }

    public boolean equals(Object other) {
        Set<String> setKeySet;
        if (other != null && (other instanceof NavBackStackEntry)) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) other;
            if (Intrinsics.areEqual(this.id, navBackStackEntry.id) && Intrinsics.areEqual(this.destination, navBackStackEntry.destination) && Intrinsics.areEqual(getLifecycleRegistry(), navBackStackEntry.getLifecycleRegistry()) && Intrinsics.areEqual(getSavedStateRegistry(), navBackStackEntry.getSavedStateRegistry())) {
                if (Intrinsics.areEqual(this.immutableArgs, navBackStackEntry.immutableArgs)) {
                    return true;
                }
                Bundle bundle = this.immutableArgs;
                if (bundle != null && (setKeySet = bundle.keySet()) != null) {
                    Set<String> set = setKeySet;
                    if ((set instanceof Collection) && set.isEmpty()) {
                        return true;
                    }
                    for (String str : set) {
                        Object obj = this.immutableArgs.get(str);
                        Bundle bundle2 = navBackStackEntry.immutableArgs;
                        if (!Intrinsics.areEqual(obj, bundle2 != null ? bundle2.get(str) : null)) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Set<String> setKeySet;
        int iHashCode = (this.id.hashCode() * 31) + this.destination.hashCode();
        Bundle bundle = this.immutableArgs;
        if (bundle != null && (setKeySet = bundle.keySet()) != null) {
            Iterator<T> it = setKeySet.iterator();
            while (it.hasNext()) {
                int i = iHashCode * 31;
                Object obj = this.immutableArgs.get((String) it.next());
                iHashCode = i + (obj != null ? obj.hashCode() : 0);
            }
        }
        return (((iHashCode * 31) + getLifecycleRegistry().hashCode()) * 31) + getSavedStateRegistry().hashCode();
    }

    public String toString() {
        return this.impl.toString();
    }
}

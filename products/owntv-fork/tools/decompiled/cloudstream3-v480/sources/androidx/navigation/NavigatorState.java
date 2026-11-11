package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.internal.SynchronizedObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavigatorState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0016J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0016J \u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0018\u00010 j\u0004\u0018\u0001`!H&J\u0018\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\rH\u0016J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\rH\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0017J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0017J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\tH\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006+"}, d2 = {"Landroidx/navigation/NavigatorState;", "", "<init>", "()V", "backStackLock", "Landroidx/navigation/internal/SynchronizedObject;", "_backStack", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "_transitionsInProgress", "", ES6Iterator.VALUE_PROPERTY, "", "isNavigating", "()Z", "setNavigating", "(Z)V", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "getBackStack", "()Lkotlinx/coroutines/flow/StateFlow;", "transitionsInProgress", "getTransitionsInProgress", "push", "", "backStackEntry", "pushWithTransition", "createBackStackEntry", "destination", "Landroidx/navigation/NavDestination;", "arguments", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "pop", "popUpTo", "saveState", "popWithTransition", "onLaunchSingleTop", "onLaunchSingleTopWithTransition", "markTransitionComplete", "entry", "prepareForTransition", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class NavigatorState {
    private final MutableStateFlow<List<NavBackStackEntry>> _backStack;
    private final MutableStateFlow<Set<NavBackStackEntry>> _transitionsInProgress;
    private final StateFlow<List<NavBackStackEntry>> backStack;
    private final SynchronizedObject backStackLock = new SynchronizedObject();
    private boolean isNavigating;
    private final StateFlow<Set<NavBackStackEntry>> transitionsInProgress;

    public abstract NavBackStackEntry createBackStackEntry(NavDestination destination, Bundle arguments);

    public NavigatorState() {
        MutableStateFlow<List<NavBackStackEntry>> MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._backStack = MutableStateFlow;
        MutableStateFlow<Set<NavBackStackEntry>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
        this._transitionsInProgress = MutableStateFlow2;
        this.backStack = FlowKt.asStateFlow(MutableStateFlow);
        this.transitionsInProgress = FlowKt.asStateFlow(MutableStateFlow2);
    }

    /* JADX INFO: renamed from: isNavigating, reason: from getter */
    public final boolean getIsNavigating() {
        return this.isNavigating;
    }

    public final void setNavigating(boolean z) {
        this.isNavigating = z;
    }

    public final StateFlow<List<NavBackStackEntry>> getBackStack() {
        return this.backStack;
    }

    public final StateFlow<Set<NavBackStackEntry>> getTransitionsInProgress() {
        return this.transitionsInProgress;
    }

    public void push(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        synchronized (this.backStackLock) {
            this._backStack.setValue(CollectionsKt.plus((Collection<? extends NavBackStackEntry>) this._backStack.getValue(), backStackEntry));
            Unit unit = Unit.INSTANCE;
        }
    }

    public void pushWithTransition(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        Set<NavBackStackEntry> value = this._transitionsInProgress.getValue();
        if (!(value instanceof Collection) || !value.isEmpty()) {
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                if (((NavBackStackEntry) it.next()) == backStackEntry) {
                    List<NavBackStackEntry> value2 = this.backStack.getValue();
                    if (!(value2 instanceof Collection) || !value2.isEmpty()) {
                        Iterator<T> it2 = value2.iterator();
                        while (it2.hasNext()) {
                            if (((NavBackStackEntry) it2.next()) == backStackEntry) {
                                return;
                            }
                        }
                        break;
                    }
                    break;
                }
            }
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) this.backStack.getValue());
        if (navBackStackEntry != null) {
            MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
            mutableStateFlow.setValue(SetsKt.plus(mutableStateFlow.getValue(), navBackStackEntry));
        }
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow2 = this._transitionsInProgress;
        mutableStateFlow2.setValue(SetsKt.plus(mutableStateFlow2.getValue(), backStackEntry));
        push(backStackEntry);
    }

    public void pop(NavBackStackEntry popUpTo, boolean saveState) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        synchronized (this.backStackLock) {
            MutableStateFlow mutableStateFlow = this._backStack;
            Iterable iterable = (Iterable) this._backStack.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (Intrinsics.areEqual((NavBackStackEntry) obj, popUpTo)) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            mutableStateFlow.setValue(arrayList);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void popWithTransition(NavBackStackEntry popUpTo, boolean saveState) {
        NavBackStackEntry navBackStackEntryPrevious;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Set<NavBackStackEntry> value = this._transitionsInProgress.getValue();
        if (!(value instanceof Collection) || !value.isEmpty()) {
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                if (((NavBackStackEntry) it.next()) == popUpTo) {
                    List<NavBackStackEntry> value2 = this.backStack.getValue();
                    if ((value2 instanceof Collection) && value2.isEmpty()) {
                        return;
                    }
                    Iterator<T> it2 = value2.iterator();
                    while (it2.hasNext()) {
                        if (((NavBackStackEntry) it2.next()) == popUpTo) {
                            break;
                        }
                    }
                    return;
                }
            }
        }
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
        mutableStateFlow.setValue(SetsKt.plus(mutableStateFlow.getValue(), popUpTo));
        List<NavBackStackEntry> value3 = this.backStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value3.listIterator(value3.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
            NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
            if (!Intrinsics.areEqual(navBackStackEntry, popUpTo) && this.backStack.getValue().lastIndexOf(navBackStackEntry) < this.backStack.getValue().lastIndexOf(popUpTo)) {
                break;
            }
        }
        NavBackStackEntry navBackStackEntry2 = navBackStackEntryPrevious;
        if (navBackStackEntry2 != null) {
            MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow2 = this._transitionsInProgress;
            mutableStateFlow2.setValue(SetsKt.plus(mutableStateFlow2.getValue(), navBackStackEntry2));
        }
        pop(popUpTo, saveState);
    }

    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        int iNextIndex;
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        synchronized (this.backStackLock) {
            List mutableList = CollectionsKt.toMutableList((Collection) getBackStack().getValue());
            ListIterator listIterator = mutableList.listIterator(mutableList.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    iNextIndex = -1;
                    break;
                } else if (Intrinsics.areEqual(((NavBackStackEntry) listIterator.previous()).getId(), backStackEntry.getId())) {
                    iNextIndex = listIterator.nextIndex();
                    break;
                }
            }
            mutableList.set(iNextIndex, backStackEntry);
            this._backStack.setValue(mutableList);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void onLaunchSingleTopWithTransition(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        List<NavBackStackEntry> value = this.backStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        while (listIterator.hasPrevious()) {
            NavBackStackEntry navBackStackEntryPrevious = listIterator.previous();
            if (Intrinsics.areEqual(navBackStackEntryPrevious.getId(), backStackEntry.getId())) {
                MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
                mutableStateFlow.setValue(SetsKt.plus((Set<? extends NavBackStackEntry>) SetsKt.plus(mutableStateFlow.getValue(), navBackStackEntryPrevious), backStackEntry));
                onLaunchSingleTop(backStackEntry);
                return;
            }
        }
        throw new NoSuchElementException("List contains no element matching the predicate.");
    }

    public void markTransitionComplete(NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
        mutableStateFlow.setValue(SetsKt.minus(mutableStateFlow.getValue(), entry));
    }

    public void prepareForTransition(NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        MutableStateFlow<Set<NavBackStackEntry>> mutableStateFlow = this._transitionsInProgress;
        mutableStateFlow.setValue(SetsKt.plus(mutableStateFlow.getValue(), entry));
    }
}

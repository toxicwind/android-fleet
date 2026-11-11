package androidx.navigation.internal;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavControllerViewModel;
import androidx.navigation.NavDestination;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavBackStackEntryStateImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0015\b\u0010\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u0011\u0010\u0018\u001a\u00060\tj\u0002`\nH\u0000¢\u0006\u0002\b\u0019J8\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u0013\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u00060\tj\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006$"}, d2 = {"Landroidx/navigation/internal/NavBackStackEntryStateImpl;", "", "entry", "Landroidx/navigation/NavBackStackEntry;", "destId", "", "<init>", "(Landroidx/navigation/NavBackStackEntry;I)V", "state", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "(Landroid/os/Bundle;)V", "id", "", "getId$navigation_runtime_release", "()Ljava/lang/String;", "destinationId", "getDestinationId$navigation_runtime_release", "()I", "args", "getArgs$navigation_runtime_release", "()Landroid/os/Bundle;", "savedState", "getSavedState$navigation_runtime_release", "writeToState", "writeToState$navigation_runtime_release", "instantiate", "context", "Landroidx/navigation/internal/NavContext;", "destination", "Landroidx/navigation/NavDestination;", "hostLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", "viewModel", "Landroidx/navigation/NavControllerViewModel;", "Companion", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavBackStackEntryStateImpl {
    public static final String KEY_ARGS = "nav-entry-state:args";
    public static final String KEY_DESTINATION_ID = "nav-entry-state:destination-id";
    public static final String KEY_ID = "nav-entry-state:id";
    public static final String KEY_SAVED_STATE = "nav-entry-state:saved-state";
    private final Bundle args;
    private final int destinationId;
    private final String id;
    private final Bundle savedState;

    /* JADX INFO: renamed from: getId$navigation_runtime_release, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: getDestinationId$navigation_runtime_release, reason: from getter */
    public final int getDestinationId() {
        return this.destinationId;
    }

    /* JADX INFO: renamed from: getArgs$navigation_runtime_release, reason: from getter */
    public final Bundle getArgs() {
        return this.args;
    }

    /* JADX INFO: renamed from: getSavedState$navigation_runtime_release, reason: from getter */
    public final Bundle getSavedState() {
        return this.savedState;
    }

    public NavBackStackEntryStateImpl(NavBackStackEntry entry, int i) {
        Pair[] pairArr;
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.id = entry.getId();
        this.destinationId = i;
        this.args = entry.getArguments();
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry2 : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry2.getKey(), entry2.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m8016constructorimpl(bundleBundleOf);
        this.savedState = bundleBundleOf;
        entry.saveState(bundleBundleOf);
    }

    public NavBackStackEntryStateImpl(Bundle state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.id = SavedStateReader.m8001getStringimpl(SavedStateReader.m7930constructorimpl(state), KEY_ID);
        this.destinationId = SavedStateReader.m7961getIntimpl(SavedStateReader.m7930constructorimpl(state), KEY_DESTINATION_ID);
        this.args = SavedStateReader.m7987getSavedStateimpl(SavedStateReader.m7930constructorimpl(state), KEY_ARGS);
        this.savedState = SavedStateReader.m7987getSavedStateimpl(SavedStateReader.m7930constructorimpl(state), KEY_SAVED_STATE);
    }

    public final NavBackStackEntry instantiate(NavContext context, NavDestination destination, Bundle args, Lifecycle.State hostLifecycleState, NavControllerViewModel viewModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(hostLifecycleState, "hostLifecycleState");
        return NavBackStackEntry.INSTANCE.create(context, destination, args, hostLifecycleState, viewModel, this.id, this.savedState);
    }

    public final Bundle writeToState$navigation_runtime_release() {
        Pair[] pairArr;
        Pair[] pairArr2;
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundleBundleOf);
        SavedStateWriter.m8049putStringimpl(bundleM8016constructorimpl, KEY_ID, this.id);
        SavedStateWriter.m8033putIntimpl(bundleM8016constructorimpl, KEY_DESTINATION_ID, this.destinationId);
        Bundle bundleBundleOf2 = this.args;
        if (bundleBundleOf2 == null) {
            Map mapEmptyMap2 = MapsKt.emptyMap();
            if (mapEmptyMap2.isEmpty()) {
                pairArr2 = new Pair[0];
            } else {
                ArrayList arrayList2 = new ArrayList(mapEmptyMap2.size());
                for (Map.Entry entry2 : mapEmptyMap2.entrySet()) {
                    arrayList2.add(TuplesKt.to((String) entry2.getKey(), entry2.getValue()));
                }
                pairArr2 = (Pair[]) arrayList2.toArray(new Pair[0]);
            }
            bundleBundleOf2 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr2, pairArr2.length));
            SavedStateWriter.m8016constructorimpl(bundleBundleOf2);
        }
        SavedStateWriter.m8043putSavedStateimpl(bundleM8016constructorimpl, KEY_ARGS, bundleBundleOf2);
        SavedStateWriter.m8043putSavedStateimpl(bundleM8016constructorimpl, KEY_SAVED_STATE, this.savedState);
        return bundleBundleOf;
    }
}

package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkConstraintsTracker.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000eH\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0014H\u0016J\u0010\u0010!\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\"\u001a\u00020\nH\u0003J\u001a\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010\u000eH\u0002J4\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\f2\u0016\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u0002`\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0006\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u0002`\u000b\u0012\u0004\u0012\u00020\f0\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006,"}, d2 = {"Landroidx/work/impl/constraints/SharedNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "<init>", "()V", "requestsLock", "", "requests", "", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "", "Landroidx/work/impl/constraints/OnConstraintState;", "Landroid/net/NetworkRequest;", "cachedCapabilities", "Landroid/net/NetworkCapabilities;", "getCachedCapabilities", "()Landroid/net/NetworkCapabilities;", "setCachedCapabilities", "(Landroid/net/NetworkCapabilities;)V", "capabilitiesInitialized", "", "getCapabilitiesInitialized", "()Z", "setCapabilitiesInitialized", "(Z)V", "isBlocked", "Ljava/lang/Boolean;", "onCapabilitiesChanged", "network", "Landroid/net/Network;", "networkCapabilities", "onBlockedStatusChanged", "blocked", "onLost", "dispatchOnConstraintState", "areNetworkConstraintsSatisfied", "request", "capabilities", "addCallback", "Lkotlin/Function0;", "connManager", "Landroid/net/ConnectivityManager;", "networkRequest", "onConstraintState", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class SharedNetworkCallback extends ConnectivityManager.NetworkCallback {
    private static NetworkCapabilities cachedCapabilities;
    private static boolean capabilitiesInitialized;
    private static Boolean isBlocked;
    public static final SharedNetworkCallback INSTANCE = new SharedNetworkCallback();
    private static final Object requestsLock = new Object();
    private static final Map<Function1<ConstraintsState, Unit>, NetworkRequest> requests = new LinkedHashMap();

    private SharedNetworkCallback() {
    }

    public final NetworkCapabilities getCachedCapabilities() {
        return cachedCapabilities;
    }

    public final void setCachedCapabilities(NetworkCapabilities networkCapabilities) {
        cachedCapabilities = networkCapabilities;
    }

    public final boolean getCapabilitiesInitialized() {
        return capabilitiesInitialized;
    }

    public final void setCapabilitiesInitialized(boolean z) {
        capabilitiesInitialized = z;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(network, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
        Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onCapabilitiesChanged callback");
        synchronized (requestsLock) {
            cachedCapabilities = networkCapabilities;
            capabilitiesInitialized = true;
            Unit unit = Unit.INSTANCE;
        }
        dispatchOnConstraintState();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onBlockedStatusChanged(Network network, boolean blocked) {
        Intrinsics.checkNotNullParameter(network, "network");
        Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onBlockedStatusChanged callback " + blocked);
        synchronized (requestsLock) {
            if (Intrinsics.areEqual(isBlocked, Boolean.valueOf(blocked))) {
                return;
            }
            isBlocked = Boolean.valueOf(blocked);
            Unit unit = Unit.INSTANCE;
            dispatchOnConstraintState();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController onLost callback");
        synchronized (requestsLock) {
            cachedCapabilities = null;
            Iterator<T> it = requests.keySet().iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(new ConstraintsState.ConstraintsNotMet(7));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void dispatchOnConstraintState() {
        ArrayList<Pair> arrayList = new ArrayList();
        synchronized (requestsLock) {
            if (capabilitiesInitialized && isBlocked != null) {
                Iterator<T> it = requests.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    arrayList.add(TuplesKt.to((Function1) entry.getKey(), INSTANCE.areNetworkConstraintsSatisfied((NetworkRequest) entry.getValue(), cachedCapabilities) ? ConstraintsState.ConstraintsMet.INSTANCE : new ConstraintsState.ConstraintsNotMet(7)));
                }
                Unit unit = Unit.INSTANCE;
                for (Pair pair : arrayList) {
                    ((Function1) pair.component1()).invoke((ConstraintsState) pair.component2());
                }
                return;
            }
            Logger.get().debug(WorkConstraintsTrackerKt.TAG, "Not dispatching constraint state yet: isBlocked=" + isBlocked + ", capabilitiesInitialized=" + capabilitiesInitialized);
        }
    }

    private final boolean areNetworkConstraintsSatisfied(NetworkRequest request, NetworkCapabilities capabilities) {
        Boolean bool = isBlocked;
        Intrinsics.checkNotNull(bool);
        return !bool.booleanValue() && request.canBeSatisfiedBy(capabilities);
    }

    public final Function0<Unit> addCallback(final ConnectivityManager connManager, NetworkRequest networkRequest, final Function1<? super ConstraintsState, Unit> onConstraintState) {
        ConstraintsState constraintsNotMet;
        Intrinsics.checkNotNullParameter(connManager, "connManager");
        Intrinsics.checkNotNullParameter(networkRequest, "networkRequest");
        Intrinsics.checkNotNullParameter(onConstraintState, "onConstraintState");
        synchronized (requestsLock) {
            Map<Function1<ConstraintsState, Unit>, NetworkRequest> map = requests;
            boolean zIsEmpty = map.isEmpty();
            map.put(onConstraintState, networkRequest);
            if (zIsEmpty) {
                Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController register shared callback");
                connManager.registerDefaultNetworkCallback(INSTANCE);
            } else {
                SharedNetworkCallback sharedNetworkCallback = INSTANCE;
                if (capabilitiesInitialized && isBlocked != null) {
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController send initial capabilities");
                    if (sharedNetworkCallback.areNetworkConstraintsSatisfied(networkRequest, cachedCapabilities)) {
                        constraintsNotMet = ConstraintsState.ConstraintsMet.INSTANCE;
                    } else {
                        constraintsNotMet = new ConstraintsState.ConstraintsNotMet(7);
                    }
                    onConstraintState.invoke(constraintsNotMet);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return new Function0() { // from class: androidx.work.impl.constraints.SharedNetworkCallback$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SharedNetworkCallback.addCallback$lambda$9(onConstraintState, connManager);
            }
        };
    }

    static final Unit addCallback$lambda$9(Function1 function1, ConnectivityManager connectivityManager) {
        synchronized (requestsLock) {
            Map<Function1<ConstraintsState, Unit>, NetworkRequest> map = requests;
            map.remove(function1);
            if (map.isEmpty()) {
                Logger.get().debug(WorkConstraintsTrackerKt.TAG, "NetworkRequestConstraintController unregister shared callback");
                connectivityManager.unregisterNetworkCallback(INSTANCE);
                isBlocked = null;
                cachedCapabilities = null;
                capabilitiesInitialized = false;
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}

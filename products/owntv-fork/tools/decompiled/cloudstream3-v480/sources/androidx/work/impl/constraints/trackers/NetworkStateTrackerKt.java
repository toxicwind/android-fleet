package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkStateTracker.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0000\"\u0013\u0010\u0007\u001a\u00070\b¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\n\u001a\u00020\u000b*\u00020\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\r¨\u0006\u0011"}, d2 = {"NetworkStateTracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroidx/work/impl/constraints/NetworkState;", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "TAG", "", "Lorg/jspecify/annotations/NonNull;", "isActiveNetworkValidated", "", "Landroid/net/ConnectivityManager;", "(Landroid/net/ConnectivityManager;)Z", "getActiveNetworkState", "connectivityManager", "isBlocked", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class NetworkStateTrackerKt {
    private static final String TAG;

    public static final ConstraintTracker<NetworkState> NetworkStateTracker(Context context, TaskExecutor taskExecutor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(taskExecutor, "taskExecutor");
        if (Build.VERSION.SDK_INT >= 24) {
            return new NetworkStateTrackerPre28(context, taskExecutor);
        }
        return new NetworkStateTrackerPre24(context, taskExecutor);
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("NetworkStateTracker");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(...)");
        TAG = strTagWithPrefix;
    }

    public static final boolean isActiveNetworkValidated(ConnectivityManager connectivityManager) {
        Intrinsics.checkNotNullParameter(connectivityManager, "<this>");
        try {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                return networkCapabilities.hasCapability(16);
            }
            return false;
        } catch (SecurityException e) {
            Logger.get().error(TAG, "Unable to validate active network", e);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001a  */
    public static final NetworkState getActiveNetworkState(ConnectivityManager connectivityManager, boolean z) {
        boolean z2;
        SecurityException securityException;
        boolean z3;
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean z4 = true;
            if (activeNetworkInfo != null) {
                try {
                    if (activeNetworkInfo.isConnected()) {
                        z3 = false;
                    } else {
                        z4 = false;
                        z3 = false;
                    }
                    boolean zIsActiveNetworkValidated = isActiveNetworkValidated(connectivityManager);
                    boolean zIsActiveNetworkMetered = ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager);
                    if (activeNetworkInfo != null && !activeNetworkInfo.isRoaming()) {
                        z3 = true;
                    }
                    z2 = z;
                    try {
                        return new NetworkState(z4, zIsActiveNetworkValidated, zIsActiveNetworkMetered, z3, z2);
                    } catch (SecurityException e) {
                        e = e;
                        securityException = e;
                        Logger.get().error(TAG, "Unable to get active network state", securityException);
                        return new NetworkState(false, false, false, true, z2);
                    }
                } catch (SecurityException e2) {
                    securityException = e2;
                    z2 = z;
                }
            } else {
                z4 = false;
                z3 = false;
                boolean zIsActiveNetworkValidated2 = isActiveNetworkValidated(connectivityManager);
                boolean zIsActiveNetworkMetered2 = ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager);
                if (activeNetworkInfo != null) {
                    z3 = true;
                }
                z2 = z;
                return new NetworkState(z4, zIsActiveNetworkValidated2, zIsActiveNetworkMetered2, z3, z2);
            }
        } catch (SecurityException e3) {
            e = e3;
            z2 = z;
        }
        Logger.get().error(TAG, "Unable to get active network state", securityException);
        return new NetworkState(false, false, false, true, z2);
    }
}

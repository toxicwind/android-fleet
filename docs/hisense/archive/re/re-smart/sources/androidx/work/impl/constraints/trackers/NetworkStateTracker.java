package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NetworkStateTracker extends ConstraintTracker<NetworkState> {
    public static final String TAG = Logger.tagWithPrefix("NetworkStateTracker");
    public NetworkStateBroadcastReceiver mBroadcastReceiver;
    public final ConnectivityManager mConnectivityManager;

    @RequiresApi(24)
    public NetworkStateCallback mNetworkCallback;

    private class NetworkStateBroadcastReceiver extends BroadcastReceiver {
        public NetworkStateBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null || !intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                return;
            }
            Logger.get().debug(NetworkStateTracker.TAG, "Network broadcast received", new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }
    }

    @RequiresApi(24)
    private class NetworkStateCallback extends ConnectivityManager.NetworkCallback {
        public NetworkStateCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities capabilities) {
            Logger.get().debug(NetworkStateTracker.TAG, String.format("Network capabilities changed: %s", capabilities), new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@NonNull Network network) {
            Logger.get().debug(NetworkStateTracker.TAG, "Network connection lost", new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }
    }

    public NetworkStateTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        this.mConnectivityManager = (ConnectivityManager) this.mAppContext.getSystemService("connectivity");
        int i = Build.VERSION.SDK_INT;
        this.mNetworkCallback = new NetworkStateCallback();
    }

    public static boolean isNetworkCallbackSupported() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public NetworkState getActiveNetworkState() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return new NetworkState(activeNetworkInfo != null && activeNetworkInfo.isConnected(), isActiveNetworkValidated(), ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager), (activeNetworkInfo == null || activeNetworkInfo.isRoaming()) ? false : true);
    }

    @VisibleForTesting
    public boolean isActiveNetworkValidated() {
        int i = Build.VERSION.SDK_INT;
        try {
            NetworkCapabilities networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mConnectivityManager.getActiveNetwork());
            return networkCapabilities != null && networkCapabilities.hasCapability(16);
        } catch (SecurityException e2) {
            Logger.get().error(TAG, "Unable to validate active network", e2);
            return false;
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void startTracking() {
        int i = Build.VERSION.SDK_INT;
        try {
            Logger.get().debug(TAG, "Registering network callback", new Throwable[0]);
            this.mConnectivityManager.registerDefaultNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException e2) {
            Logger.get().error(TAG, "Received exception while registering network callback", e2);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void stopTracking() {
        int i = Build.VERSION.SDK_INT;
        try {
            Logger.get().debug(TAG, "Unregistering network callback", new Throwable[0]);
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException e2) {
            Logger.get().error(TAG, "Received exception while unregistering network callback", e2);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public NetworkState getInitialState() {
        return getActiveNetworkState();
    }
}

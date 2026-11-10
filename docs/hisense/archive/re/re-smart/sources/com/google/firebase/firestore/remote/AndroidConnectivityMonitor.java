package com.google.firebase.firestore.remote;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidConnectivityMonitor implements ConnectivityMonitor {
    public static final String LOG_TAG = "AndroidConnectivityMonitor";
    public final List<Consumer<ConnectivityMonitor.NetworkStatus>> callbacks = new ArrayList();

    @Nullable
    public final ConnectivityManager connectivityManager;
    public final Context context;

    @Nullable
    public Runnable unregisterRunnable;

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(24)
    public class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        public DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(false);
        }

        public /* synthetic */ DefaultNetworkCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    public AndroidConnectivityMonitor(Context context) {
        Assert.hardAssert(context != null, "Context must be non-null", new Object[0]);
        this.context = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        configureBackgroundStateListener();
        configureNetworkMonitoring();
    }

    private void configureBackgroundStateListener() {
        Application application = (Application) this.context.getApplicationContext();
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NonNull Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NonNull Activity activity) {
            }
        });
        application.registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor.2
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(@NonNull Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i) {
                if (i == 20) {
                    atomicBoolean.set(true);
                }
            }
        });
    }

    private void configureNetworkMonitoring() {
        int i = Build.VERSION.SDK_INT;
        AnonymousClass1 anonymousClass1 = null;
        if (this.connectivityManager != null) {
            final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback(anonymousClass1);
            this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
            this.unregisterRunnable = new Runnable() { // from class: b.e.b.d.f.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f497a.a(defaultNetworkCallback);
                }
            };
        } else {
            final NetworkReceiver networkReceiver = new NetworkReceiver(anonymousClass1);
            this.context.registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.unregisterRunnable = new Runnable() { // from class: b.e.b.d.f.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f495a.a(networkReceiver);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void raiseCallbacks(boolean z) {
        synchronized (this.callbacks) {
            Iterator<Consumer<ConnectivityMonitor.NetworkStatus>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().accept(z ? ConnectivityMonitor.NetworkStatus.REACHABLE : ConnectivityMonitor.NetworkStatus.UNREACHABLE);
            }
        }
    }

    public /* synthetic */ void a(DefaultNetworkCallback defaultNetworkCallback) {
        this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
    }

    @Override // com.google.firebase.firestore.remote.ConnectivityMonitor
    public void addCallback(Consumer<ConnectivityMonitor.NetworkStatus> consumer) {
        synchronized (this.callbacks) {
            this.callbacks.add(consumer);
        }
    }

    public void raiseForegroundNotification() {
        Logger.debug(LOG_TAG, "App has entered the foreground.", new Object[0]);
        if (isConnected()) {
            raiseCallbacks(true);
        }
    }

    @Override // com.google.firebase.firestore.remote.ConnectivityMonitor
    public void shutdown() {
        Runnable runnable = this.unregisterRunnable;
        if (runnable != null) {
            runnable.run();
            this.unregisterRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public class NetworkReceiver extends BroadcastReceiver {
        public boolean wasConnected = false;

        public NetworkReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean zIsConnected = AndroidConnectivityMonitor.this.isConnected();
            if (AndroidConnectivityMonitor.this.isConnected() && !this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(true);
            } else if (!zIsConnected && this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(false);
            }
            this.wasConnected = zIsConnected;
        }

        public /* synthetic */ NetworkReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    public /* synthetic */ void a(NetworkReceiver networkReceiver) {
        this.context.unregisterReceiver(networkReceiver);
    }
}

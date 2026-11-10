package com.google.firebase.storage.internal;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zzb;
import com.google.android.gms.common.api.internal.zzd;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityLifecycleListener {
    public static final ActivityLifecycleListener instance = new ActivityLifecycleListener();
    public final Map<Object, LifecycleEntry> cookieMap = new HashMap();
    public final Object sync = new Object();

    private static class LifecycleEntry {

        @NonNull
        public final Activity activity;

        @NonNull
        public final Object cookie;

        @NonNull
        public final Runnable runnable;

        public LifecycleEntry(@NonNull Activity activity, @NonNull Runnable runnable, @NonNull Object obj) {
            this.activity = activity;
            this.runnable = runnable;
            this.cookie = obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LifecycleEntry)) {
                return false;
            }
            LifecycleEntry lifecycleEntry = (LifecycleEntry) obj;
            return lifecycleEntry.cookie.equals(this.cookie) && lifecycleEntry.runnable == this.runnable && lifecycleEntry.activity == this.activity;
        }

        @NonNull
        public Activity getActivity() {
            return this.activity;
        }

        @NonNull
        public Object getCookie() {
            return this.cookie;
        }

        @NonNull
        public Runnable getRunnable() {
            return this.runnable;
        }

        public int hashCode() {
            return this.cookie.hashCode();
        }
    }

    private static class OnStopCallback extends LifecycleCallback {
        public static final String TAG = "StorageOnStopCallback";
        public final List<LifecycleEntry> listeners;

        public OnStopCallback(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.listeners = new ArrayList();
            this.mLifecycleFragment.addCallback(TAG, this);
        }

        public static OnStopCallback getInstance(Activity activity) {
            LifecycleFragment lifecycleFragmentZzc;
            Preconditions.checkNotNull(activity, "Activity must not be null");
            if (activity instanceof FragmentActivity) {
                lifecycleFragmentZzc = zzd.zzc((FragmentActivity) activity);
            } else {
                if (!(activity instanceof Activity)) {
                    throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
                }
                lifecycleFragmentZzc = zzb.zzc(activity);
            }
            OnStopCallback onStopCallback = (OnStopCallback) lifecycleFragmentZzc.getCallbackOrNull(TAG, OnStopCallback.class);
            return onStopCallback == null ? new OnStopCallback(lifecycleFragmentZzc) : onStopCallback;
        }

        public void addEntry(LifecycleEntry lifecycleEntry) {
            synchronized (this.listeners) {
                this.listeners.add(lifecycleEntry);
            }
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            ArrayList<LifecycleEntry> arrayList;
            synchronized (this.listeners) {
                arrayList = new ArrayList(this.listeners);
                this.listeners.clear();
            }
            for (LifecycleEntry lifecycleEntry : arrayList) {
                if (lifecycleEntry != null) {
                    Log.d(TAG, "removing subscription from activity.");
                    lifecycleEntry.getRunnable().run();
                    ActivityLifecycleListener.instance.removeCookie(lifecycleEntry.getCookie());
                }
            }
        }

        public void removeEntry(LifecycleEntry lifecycleEntry) {
            synchronized (this.listeners) {
                this.listeners.remove(lifecycleEntry);
            }
        }
    }

    @NonNull
    public static ActivityLifecycleListener getInstance() {
        return instance;
    }

    public void removeCookie(@NonNull Object obj) {
        synchronized (this.sync) {
            LifecycleEntry lifecycleEntry = this.cookieMap.get(obj);
            if (lifecycleEntry != null) {
                OnStopCallback.getInstance(lifecycleEntry.getActivity()).removeEntry(lifecycleEntry);
            }
        }
    }

    public void runOnActivityStopped(@NonNull Activity activity, @NonNull Object obj, @NonNull Runnable runnable) {
        synchronized (this.sync) {
            LifecycleEntry lifecycleEntry = new LifecycleEntry(activity, runnable, obj);
            OnStopCallback.getInstance(activity).addEntry(lifecycleEntry);
            this.cookieMap.put(obj, lifecycleEntry);
        }
    }
}

package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    public final WeakReference<C0013zaa> zaco;

    @VisibleForTesting(otherwise = 2)
    public zaa(C0013zaa c0013zaa) {
        this.zaco = new WeakReference<>(c0013zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0013zaa c0013zaa = this.zaco.get();
        if (c0013zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0013zaa.zaa(runnable);
        return this;
    }

    public zaa(Activity activity) {
        this(C0013zaa.zaa(activity));
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    @VisibleForTesting(otherwise = 2)
    static class C0013zaa extends LifecycleCallback {
        public List<Runnable> zacn;

        public C0013zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacn = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        public static C0013zaa zaa(Activity activity) {
            C0013zaa c0013zaa;
            synchronized (activity) {
                LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
                c0013zaa = (C0013zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0013zaa.class);
                if (c0013zaa == null) {
                    c0013zaa = new C0013zaa(fragment);
                }
            }
            return c0013zaa;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacn;
                this.zacn = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacn.add(runnable);
        }
    }
}

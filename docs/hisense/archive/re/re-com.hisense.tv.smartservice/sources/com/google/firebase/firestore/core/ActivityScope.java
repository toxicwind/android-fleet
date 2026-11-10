package com.google.firebase.firestore.core;

import android.app.Activity;
import android.app.Fragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import b.a.a.a.a;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ActivityScope {
    public static final String FRAGMENT_TAG = "FirestoreOnStopObserverFragment";
    public static final String SUPPORT_FRAGMENT_TAG = "FirestoreOnStopObserverSupportFragment";

    /* JADX INFO: Access modifiers changed from: private */
    public static class CallbackList {
        public final List<Runnable> callbacks;

        public CallbackList() {
            this.callbacks = new ArrayList();
        }

        public synchronized void add(Runnable runnable) {
            this.callbacks.add(runnable);
        }

        public void run() {
            for (Runnable runnable : this.callbacks) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    public static class StopListenerFragment extends Fragment {
        public CallbackList callbacks = new CallbackList();

        @Override // android.app.Fragment
        public void onStop() {
            CallbackList callbackList;
            super.onStop();
            synchronized (this.callbacks) {
                callbackList = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbackList.run();
        }
    }

    public static class StopListenerSupportFragment extends androidx.fragment.app.Fragment {
        public CallbackList callbacks = new CallbackList();

        @Override // androidx.fragment.app.Fragment
        public void onStop() {
            CallbackList callbackList;
            this.mCalled = true;
            synchronized (this.callbacks) {
                callbackList = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbackList.run();
        }
    }

    public static /* synthetic */ void a(Activity activity, Runnable runnable) {
        StopListenerFragment stopListenerFragment = (StopListenerFragment) castFragment(StopListenerFragment.class, activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG), FRAGMENT_TAG);
        if (stopListenerFragment == null || stopListenerFragment.isRemoving()) {
            stopListenerFragment = new StopListenerFragment();
            activity.getFragmentManager().beginTransaction().add(stopListenerFragment, FRAGMENT_TAG).commitAllowingStateLoss();
            activity.getFragmentManager().executePendingTransactions();
        }
        stopListenerFragment.callbacks.add(runnable);
    }

    public static ListenerRegistration bind(@Nullable Activity activity, final ListenerRegistration listenerRegistration) {
        if (activity != null) {
            if (activity instanceof FragmentActivity) {
                Objects.requireNonNull(listenerRegistration);
                onFragmentActivityStopCallOnce((FragmentActivity) activity, new Runnable() { // from class: b.e.b.d.b.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        listenerRegistration.remove();
                    }
                });
            } else {
                Objects.requireNonNull(listenerRegistration);
                onActivityStopCallOnce(activity, new Runnable() { // from class: b.e.b.d.b.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        listenerRegistration.remove();
                    }
                });
            }
        }
        return listenerRegistration;
    }

    @Nullable
    public static <T> T castFragment(Class<T> cls, @Nullable Object obj, String str) {
        if (obj == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            StringBuilder sbB = a.b("Fragment with tag '", str, "' is a ");
            sbB.append(obj.getClass().getName());
            sbB.append(" but should be a ");
            sbB.append(cls.getName());
            throw new IllegalStateException(sbB.toString());
        }
    }

    public static void onActivityStopCallOnce(final Activity activity, final Runnable runnable) {
        Assert.hardAssert(!(activity instanceof FragmentActivity), "onActivityStopCallOnce must be called with a *non*-FragmentActivity Activity.", new Object[0]);
        activity.runOnUiThread(new Runnable() { // from class: b.e.b.d.b.b
            @Override // java.lang.Runnable
            public final void run() {
                ActivityScope.a(activity, runnable);
            }
        });
    }

    public static void onFragmentActivityStopCallOnce(final FragmentActivity fragmentActivity, final Runnable runnable) {
        fragmentActivity.runOnUiThread(new Runnable() { // from class: b.e.b.d.b.a
            @Override // java.lang.Runnable
            public final void run() {
                ActivityScope.a(fragmentActivity, runnable);
            }
        });
    }

    public static /* synthetic */ void a(FragmentActivity fragmentActivity, Runnable runnable) {
        StopListenerSupportFragment stopListenerSupportFragment = (StopListenerSupportFragment) castFragment(StopListenerSupportFragment.class, fragmentActivity.getSupportFragmentManager().findFragmentByTag(SUPPORT_FRAGMENT_TAG), SUPPORT_FRAGMENT_TAG);
        if (stopListenerSupportFragment == null || stopListenerSupportFragment.isRemoving()) {
            stopListenerSupportFragment = new StopListenerSupportFragment();
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(stopListenerSupportFragment, SUPPORT_FRAGMENT_TAG).commitAllowingStateLoss();
            fragmentActivity.getSupportFragmentManager().executePendingTransactions();
        }
        stopListenerSupportFragment.callbacks.add(runnable);
    }
}

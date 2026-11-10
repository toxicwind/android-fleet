package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class ActivityRecreator {
    public static final String LOG_TAG = "ActivityRecreator";
    public static final Handler mainHandler = new Handler(Looper.getMainLooper());
    public static final Class<?> activityThreadClass = getActivityThreadClass();
    public static final Field mainThreadField = getMainThreadField();
    public static final Field tokenField = getTokenField();
    public static final Method performStopActivity3ParamsMethod = getPerformStopActivity3Params(activityThreadClass);
    public static final Method performStopActivity2ParamsMethod = getPerformStopActivity2Params(activityThreadClass);
    public static final Method requestRelaunchActivityMethod = getRequestRelaunchActivityMethod(activityThreadClass);

    private static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
        public Object currentlyRecreatingToken;
        public Activity mActivity;
        public final int mRecreatingHashCode;
        public boolean mStarted = false;
        public boolean mDestroyed = false;
        public boolean mStopQueued = false;

        public LifecycleCheckCallbacks(@NonNull Activity aboutToRecreate) {
            this.mActivity = aboutToRecreate;
            this.mRecreatingHashCode = this.mActivity.hashCode();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity == activity) {
                this.mActivity = null;
                this.mDestroyed = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.mDestroyed || this.mStopQueued || this.mStarted || !ActivityRecreator.queueOnStopIfNecessary(this.currentlyRecreatingToken, this.mRecreatingHashCode, activity)) {
                return;
            }
            this.mStopQueued = true;
            this.currentlyRecreatingToken = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.mActivity == activity) {
                this.mStarted = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public static Class<?> getActivityThreadClass() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Field getMainThreadField() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method getPerformStopActivity2Params(Class<?> activityThreadClass2) {
        if (activityThreadClass2 == null) {
            return null;
        }
        try {
            Method declaredMethod = activityThreadClass2.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method getPerformStopActivity3Params(Class<?> activityThreadClass2) {
        if (activityThreadClass2 == null) {
            return null;
        }
        try {
            Method declaredMethod = activityThreadClass2.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method getRequestRelaunchActivityMethod(Class<?> activityThreadClass2) {
        if (needsRelaunchCall() && activityThreadClass2 != null) {
            try {
                Method declaredMethod = activityThreadClass2.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static Field getTokenField() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean needsRelaunchCall() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    public static boolean queueOnStopIfNecessary(Object currentlyRecreatingToken, int currentlyRecreatingHashCode, Activity activity) {
        try {
            final Object obj = tokenField.get(activity);
            if (obj == currentlyRecreatingToken && activity.hashCode() == currentlyRecreatingHashCode) {
                final Object obj2 = mainThreadField.get(activity);
                mainHandler.postAtFrontOfQueue(new Runnable() { // from class: androidx.core.app.ActivityRecreator.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (ActivityRecreator.performStopActivity3ParamsMethod != null) {
                                ActivityRecreator.performStopActivity3ParamsMethod.invoke(obj2, obj, false, "AppCompat recreation");
                            } else {
                                ActivityRecreator.performStopActivity2ParamsMethod.invoke(obj2, obj, false);
                            }
                        } catch (RuntimeException e2) {
                            if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                                throw e2;
                            }
                        } catch (Throwable th) {
                            Log.e(ActivityRecreator.LOG_TAG, "Exception while invoking performStopActivity", th);
                        }
                    }
                });
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e(LOG_TAG, "Exception while fetching field values", th);
            return false;
        }
    }

    public static boolean recreate(@NonNull final Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (needsRelaunchCall() && requestRelaunchActivityMethod == null) {
            return false;
        }
        if (performStopActivity2ParamsMethod == null && performStopActivity3ParamsMethod == null) {
            return false;
        }
        try {
            final Object obj2 = tokenField.get(activity);
            if (obj2 == null || (obj = mainThreadField.get(activity)) == null) {
                return false;
            }
            final Application application = activity.getApplication();
            final LifecycleCheckCallbacks lifecycleCheckCallbacks = new LifecycleCheckCallbacks(activity);
            application.registerActivityLifecycleCallbacks(lifecycleCheckCallbacks);
            mainHandler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.1
                @Override // java.lang.Runnable
                public void run() {
                    lifecycleCheckCallbacks.currentlyRecreatingToken = obj2;
                }
            });
            try {
                if (needsRelaunchCall()) {
                    requestRelaunchActivityMethod.invoke(obj, obj2, null, null, 0, false, null, null, false, false);
                } else {
                    activity.recreate();
                }
                return true;
            } finally {
                mainHandler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.2
                    @Override // java.lang.Runnable
                    public void run() {
                        application.unregisterActivityLifecycleCallbacks(lifecycleCheckCallbacks);
                    }
                });
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}

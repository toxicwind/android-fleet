package coil3.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import kotlin.Metadata;

/* JADX INFO: compiled from: utils.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b`\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0007H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcoil3/util/DefaultActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityStarted", "onActivityResumed", "onActivityPaused", "onActivityStopped", "onActivitySaveInstanceState", "outState", "onActivityDestroyed", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface DefaultActivityLifecycleCallbacks extends Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: coil3.util.DefaultActivityLifecycleCallbacks$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: utils.android.kt */
    public final /* synthetic */ class CC {
        public static void $default$onActivityCreated(DefaultActivityLifecycleCallbacks _this, Activity activity, Bundle bundle) {
        }

        public static void $default$onActivityDestroyed(DefaultActivityLifecycleCallbacks _this, Activity activity) {
        }

        public static void $default$onActivityPaused(DefaultActivityLifecycleCallbacks _this, Activity activity) {
        }

        public static void $default$onActivityResumed(DefaultActivityLifecycleCallbacks _this, Activity activity) {
        }

        public static void $default$onActivitySaveInstanceState(DefaultActivityLifecycleCallbacks _this, Activity activity, Bundle bundle) {
        }

        public static void $default$onActivityStarted(DefaultActivityLifecycleCallbacks _this, Activity activity) {
        }

        public static void $default$onActivityStopped(DefaultActivityLifecycleCallbacks _this, Activity activity) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityCreated(Activity activity, Bundle savedInstanceState);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityDestroyed(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityPaused(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityResumed(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivitySaveInstanceState(Activity activity, Bundle outState);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityStarted(Activity activity);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    void onActivityStopped(Activity activity);
}

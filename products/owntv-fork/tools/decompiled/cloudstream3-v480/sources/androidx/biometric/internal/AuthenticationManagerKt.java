package androidx.biometric.internal;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"isPermanentlyRemoved", "", "Landroidx/lifecycle/LifecycleOwner;", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationManagerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isPermanentlyRemoved(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        boolean z;
        boolean z2 = event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_DESTROY;
        if (lifecycleOwner instanceof Activity) {
            Activity activity = (Activity) lifecycleOwner;
            z = activity.isFinishing() || !activity.isChangingConfigurations();
        } else {
            if (lifecycleOwner instanceof Fragment) {
                Fragment fragment = (Fragment) lifecycleOwner;
                if (fragment.isRemoving()) {
                    if (fragment.getActivity() != null) {
                        FragmentActivity activity2 = fragment.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        if (!activity2.isChangingConfigurations()) {
                        }
                    }
                }
            }
        }
        return z2 && z;
    }
}

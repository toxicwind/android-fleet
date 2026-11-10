package androidx.core.view;

import android.os.Build;
import android.view.ScaleGestureDetector;

/* JADX INFO: loaded from: classes.dex */
public final class ScaleGestureDetectorCompat {
    @Deprecated
    public static boolean isQuickScaleEnabled(Object scaleGestureDetector) {
        return isQuickScaleEnabled((ScaleGestureDetector) scaleGestureDetector);
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object scaleGestureDetector, boolean enabled) {
        int i = Build.VERSION.SDK_INT;
        ((ScaleGestureDetector) scaleGestureDetector).setQuickScaleEnabled(enabled);
    }

    public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector) {
        int i = Build.VERSION.SDK_INT;
        return scaleGestureDetector.isQuickScaleEnabled();
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector, boolean enabled) {
        int i = Build.VERSION.SDK_INT;
        scaleGestureDetector.setQuickScaleEnabled(enabled);
    }
}

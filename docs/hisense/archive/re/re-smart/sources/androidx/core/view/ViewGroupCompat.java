package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupCompat {
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    public static int getLayoutMode(@NonNull ViewGroup group) {
        int i = Build.VERSION.SDK_INT;
        return group.getLayoutMode();
    }

    public static int getNestedScrollAxes(@NonNull ViewGroup group) {
        int i = Build.VERSION.SDK_INT;
        return group.getNestedScrollAxes();
    }

    public static boolean isTransitionGroup(@NonNull ViewGroup group) {
        int i = Build.VERSION.SDK_INT;
        return group.isTransitionGroup();
    }

    @Deprecated
    public static boolean onRequestSendAccessibilityEvent(ViewGroup group, View child, AccessibilityEvent event) {
        return group.onRequestSendAccessibilityEvent(child, event);
    }

    public static void setLayoutMode(@NonNull ViewGroup group, int mode) {
        int i = Build.VERSION.SDK_INT;
        group.setLayoutMode(mode);
    }

    @Deprecated
    public static void setMotionEventSplittingEnabled(ViewGroup group, boolean split) {
        group.setMotionEventSplittingEnabled(split);
    }

    public static void setTransitionGroup(@NonNull ViewGroup group, boolean isTransitionGroup) {
        int i = Build.VERSION.SDK_INT;
        group.setTransitionGroup(isTransitionGroup);
    }
}

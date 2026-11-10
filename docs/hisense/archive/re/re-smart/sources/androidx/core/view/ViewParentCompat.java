package androidx.core.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ViewParentCompat {
    public static final String TAG = "ViewParentCompat";
    public static int[] sTempNestedScrollConsumed;

    public static int[] getTempNestedScrollConsumed() {
        int[] iArr = sTempNestedScrollConsumed;
        if (iArr == null) {
            sTempNestedScrollConsumed = new int[2];
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
        }
        return sTempNestedScrollConsumed;
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent parent, View child, View source, int changeType) {
        int i = Build.VERSION.SDK_INT;
        parent.notifySubtreeAccessibilityStateChanged(child, source, changeType);
    }

    public static boolean onNestedFling(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
        int i = Build.VERSION.SDK_INT;
        try {
            return parent.onNestedFling(target, velocityX, velocityY, consumed);
        } catch (AbstractMethodError e2) {
            Log.e(TAG, "ViewParent " + parent + " does not implement interface method onNestedFling", e2);
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent parent, View target, float velocityX, float velocityY) {
        int i = Build.VERSION.SDK_INT;
        try {
            return parent.onNestedPreFling(target, velocityX, velocityY);
        } catch (AbstractMethodError e2) {
            Log.e(TAG, "ViewParent " + parent + " does not implement interface method onNestedPreFling", e2);
            return false;
        }
    }

    public static void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(parent, target, dx, dy, consumed, 0);
    }

    public static void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0, getTempNestedScrollConsumed());
    }

    public static void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes) {
        onNestedScrollAccepted(parent, child, target, nestedScrollAxes, 0);
    }

    public static boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(parent, child, target, nestedScrollAxes, 0);
    }

    public static void onStopNestedScroll(ViewParent parent, View target) {
        onStopNestedScroll(parent, target, 0);
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent parent, View child, AccessibilityEvent event) {
        return parent.requestSendAccessibilityEvent(child, event);
    }

    public static void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed, int type) {
        if (parent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) parent).onNestedPreScroll(target, dx, dy, consumed, type);
            return;
        }
        if (type == 0) {
            int i = Build.VERSION.SDK_INT;
            try {
                parent.onNestedPreScroll(target, dx, dy, consumed);
            } catch (AbstractMethodError e2) {
                Log.e(TAG, "ViewParent " + parent + " does not implement interface method onNestedPreScroll", e2);
            }
        }
    }

    public static void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (parent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) parent).onNestedScrollAccepted(child, target, nestedScrollAxes, type);
            return;
        }
        if (type == 0) {
            int i = Build.VERSION.SDK_INT;
            try {
                parent.onNestedScrollAccepted(child, target, nestedScrollAxes);
            } catch (AbstractMethodError e2) {
                Log.e(TAG, "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e2);
            }
        }
    }

    public static boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (parent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) parent).onStartNestedScroll(child, target, nestedScrollAxes, type);
        }
        if (type != 0) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        try {
            return parent.onStartNestedScroll(child, target, nestedScrollAxes);
        } catch (AbstractMethodError e2) {
            Log.e(TAG, "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e2);
            return false;
        }
    }

    public static void onStopNestedScroll(ViewParent parent, View target, int type) {
        if (parent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) parent).onStopNestedScroll(target, type);
            return;
        }
        if (type == 0) {
            int i = Build.VERSION.SDK_INT;
            try {
                parent.onStopNestedScroll(target);
            } catch (AbstractMethodError e2) {
                Log.e(TAG, "ViewParent " + parent + " does not implement interface method onStopNestedScroll", e2);
            }
        }
    }

    public static void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        onNestedScroll(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, getTempNestedScrollConsumed());
    }

    public static void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        if (parent instanceof NestedScrollingParent3) {
            ((NestedScrollingParent3) parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
            return;
        }
        consumed[0] = consumed[0] + dxUnconsumed;
        consumed[1] = consumed[1] + dyUnconsumed;
        if (parent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
            return;
        }
        if (type == 0) {
            int i = Build.VERSION.SDK_INT;
            try {
                parent.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            } catch (AbstractMethodError e2) {
                Log.e(TAG, "ViewParent " + parent + " does not implement interface method onNestedScroll", e2);
            }
        }
    }
}

package androidx.core.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityManagerCompat {

    @Deprecated
    public interface AccessibilityStateChangeListener {
        @Deprecated
        void onAccessibilityStateChanged(boolean enabled);
    }

    @Deprecated
    public static abstract class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
    }

    private static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager.AccessibilityStateChangeListener {
        public AccessibilityStateChangeListener mListener;

        public AccessibilityStateChangeListenerWrapper(@NonNull AccessibilityStateChangeListener listener) {
            this.mListener = listener;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof AccessibilityStateChangeListenerWrapper) {
                return this.mListener.equals(((AccessibilityStateChangeListenerWrapper) o).mListener);
            }
            return false;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean enabled) {
            this.mListener.onAccessibilityStateChanged(enabled);
        }
    }

    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean enabled);
    }

    @RequiresApi(19)
    private static final class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {
        public final TouchExplorationStateChangeListener mListener;

        public TouchExplorationStateChangeListenerWrapper(@NonNull TouchExplorationStateChangeListener listener) {
            this.mListener = listener;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof TouchExplorationStateChangeListenerWrapper) {
                return this.mListener.equals(((TouchExplorationStateChangeListenerWrapper) o).mListener);
            }
            return false;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean enabled) {
            this.mListener.onTouchExplorationStateChanged(enabled);
        }
    }

    @Deprecated
    public static boolean addAccessibilityStateChangeListener(AccessibilityManager manager, AccessibilityStateChangeListener listener) {
        if (listener == null) {
            return false;
        }
        return manager.addAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(listener));
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager manager, TouchExplorationStateChangeListener listener) {
        int i = Build.VERSION.SDK_INT;
        if (listener == null) {
            return false;
        }
        return manager.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(listener));
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager manager, int feedbackTypeFlags) {
        return manager.getEnabledAccessibilityServiceList(feedbackTypeFlags);
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager manager) {
        return manager.getInstalledAccessibilityServiceList();
    }

    @Deprecated
    public static boolean isTouchExplorationEnabled(AccessibilityManager manager) {
        return manager.isTouchExplorationEnabled();
    }

    @Deprecated
    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager manager, AccessibilityStateChangeListener listener) {
        if (listener == null) {
            return false;
        }
        return manager.removeAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(listener));
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager manager, TouchExplorationStateChangeListener listener) {
        int i = Build.VERSION.SDK_INT;
        if (listener == null) {
            return false;
        }
        return manager.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(listener));
    }
}

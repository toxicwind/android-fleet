package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    public static final int UNDEFINED = -1;
    public Object mInfo;

    public AccessibilityWindowInfoCompat(Object info) {
        this.mInfo = info;
    }

    public static AccessibilityWindowInfoCompat obtain() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }

    public static String typeToString(int type) {
        if (type == 1) {
            return "TYPE_APPLICATION";
        }
        if (type == 2) {
            return "TYPE_INPUT_METHOD";
        }
        if (type != 3) {
            return type != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY";
        }
        return "TYPE_SYSTEM";
    }

    public static AccessibilityWindowInfoCompat wrapNonNullInstance(Object object) {
        if (object != null) {
            return new AccessibilityWindowInfoCompat(object);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        Object obj2 = this.mInfo;
        if (obj2 == null) {
            if (accessibilityWindowInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!obj2.equals(accessibilityWindowInfoCompat.mInfo)) {
            return false;
        }
        return true;
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        int i = Build.VERSION.SDK_INT;
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getAnchor());
    }

    public void getBoundsInScreen(Rect outBounds) {
        int i = Build.VERSION.SDK_INT;
        ((AccessibilityWindowInfo) this.mInfo).getBoundsInScreen(outBounds);
    }

    public AccessibilityWindowInfoCompat getChild(int index) {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getChild(index));
    }

    public int getChildCount() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getChildCount();
    }

    public int getId() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getId();
    }

    public int getLayer() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getLayer();
    }

    public AccessibilityWindowInfoCompat getParent() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getParent());
    }

    public AccessibilityNodeInfoCompat getRoot() {
        int i = Build.VERSION.SDK_INT;
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getRoot());
    }

    public CharSequence getTitle() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getTitle();
    }

    public int getType() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getType();
    }

    public int hashCode() {
        Object obj = this.mInfo;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isAccessibilityFocused() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isAccessibilityFocused();
    }

    public boolean isActive() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isActive();
    }

    public boolean isFocused() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isFocused();
    }

    public void recycle() {
        int i = Build.VERSION.SDK_INT;
        ((AccessibilityWindowInfo) this.mInfo).recycle();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=");
        sb.append(getId());
        sb.append(", type=");
        sb.append(typeToString(getType()));
        sb.append(", layer=");
        sb.append(getLayer());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(isFocused());
        sb.append(", active=");
        sb.append(isActive());
        sb.append(", hasParent=");
        sb.append(getParent() != null);
        sb.append(", hasChildren=");
        sb.append(getChildCount() > 0);
        sb.append(']');
        return sb.toString();
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat info) {
        int i = Build.VERSION.SDK_INT;
        if (info == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo) info.mInfo));
    }
}

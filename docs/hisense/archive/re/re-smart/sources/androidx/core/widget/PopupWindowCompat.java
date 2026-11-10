package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class PopupWindowCompat {
    public static final String TAG = "PopupWindowCompatApi21";
    public static Method sGetWindowLayoutTypeMethod;
    public static boolean sGetWindowLayoutTypeMethodAttempted;
    public static Field sOverlapAnchorField;
    public static boolean sOverlapAnchorFieldAttempted;
    public static Method sSetWindowLayoutTypeMethod;
    public static boolean sSetWindowLayoutTypeMethodAttempted;

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow) {
        int i = Build.VERSION.SDK_INT;
        return popupWindow.getOverlapAnchor();
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow) {
        int i = Build.VERSION.SDK_INT;
        return popupWindow.getWindowLayoutType();
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean overlapAnchor) {
        int i = Build.VERSION.SDK_INT;
        popupWindow.setOverlapAnchor(overlapAnchor);
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow, int layoutType) {
        int i = Build.VERSION.SDK_INT;
        popupWindow.setWindowLayoutType(layoutType);
    }

    public static void showAsDropDown(@NonNull PopupWindow popup, @NonNull View anchor, int xoff, int yoff, int gravity) {
        int i = Build.VERSION.SDK_INT;
        popup.showAsDropDown(anchor, xoff, yoff, gravity);
    }
}

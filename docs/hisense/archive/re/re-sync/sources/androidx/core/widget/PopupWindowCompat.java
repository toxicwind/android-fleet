package androidx.core.widget;

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
        return popupWindow.getOverlapAnchor();
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow) {
        return popupWindow.getWindowLayoutType();
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean z) {
        popupWindow.setOverlapAnchor(z);
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow, int i) {
        popupWindow.setWindowLayoutType(i);
    }

    public static void showAsDropDown(@NonNull PopupWindow popupWindow, @NonNull View view, int i, int i2, int i3) {
        popupWindow.showAsDropDown(view, i, i2, i3);
    }
}

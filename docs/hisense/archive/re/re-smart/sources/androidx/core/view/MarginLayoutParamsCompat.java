package androidx.core.view;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class MarginLayoutParamsCompat {
    public static int getLayoutDirection(ViewGroup.MarginLayoutParams lp) {
        int i = Build.VERSION.SDK_INT;
        int layoutDirection = lp.getLayoutDirection();
        if (layoutDirection == 0 || layoutDirection == 1) {
            return layoutDirection;
        }
        return 0;
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams lp) {
        int i = Build.VERSION.SDK_INT;
        return lp.getMarginEnd();
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams lp) {
        int i = Build.VERSION.SDK_INT;
        return lp.getMarginStart();
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams lp) {
        int i = Build.VERSION.SDK_INT;
        return lp.isMarginRelative();
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams lp, int layoutDirection) {
        int i = Build.VERSION.SDK_INT;
        lp.resolveLayoutDirection(layoutDirection);
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams lp, int layoutDirection) {
        int i = Build.VERSION.SDK_INT;
        lp.setLayoutDirection(layoutDirection);
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams lp, int marginEnd) {
        int i = Build.VERSION.SDK_INT;
        lp.setMarginEnd(marginEnd);
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams lp, int marginStart) {
        int i = Build.VERSION.SDK_INT;
        lp.setMarginStart(marginStart);
    }
}

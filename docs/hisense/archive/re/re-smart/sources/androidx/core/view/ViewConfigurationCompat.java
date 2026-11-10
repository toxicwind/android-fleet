package androidx.core.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ViewConfigurationCompat {
    public static final String TAG = "ViewConfigCompat";
    public static Method sGetScaledScrollFactorMethod;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public static float getLegacyScrollFactor(ViewConfiguration config, Context context) {
        int i = Build.VERSION.SDK_INT;
        Method method = sGetScaledScrollFactorMethod;
        if (method != null) {
            try {
                return ((Integer) method.invoke(config, new Object[0])).intValue();
            } catch (Exception unused) {
                Log.i(TAG, "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
            return typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }

    public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration config, @NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return config.getScaledHorizontalScrollFactor();
    }

    public static int getScaledHoverSlop(ViewConfiguration config) {
        return Build.VERSION.SDK_INT >= 28 ? config.getScaledHoverSlop() : config.getScaledTouchSlop() / 2;
    }

    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration config) {
        return config.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration config, @NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return config.getScaledVerticalScrollFactor();
    }

    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration config) {
        return config.hasPermanentMenuKey();
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration config, @NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return config.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return identifier != 0 && resources.getBoolean(identifier);
    }
}

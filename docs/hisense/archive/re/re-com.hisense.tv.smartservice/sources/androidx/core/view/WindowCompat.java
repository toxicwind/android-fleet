package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.text.BidiFormatter;

/* JADX INFO: loaded from: classes.dex */
public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    @RequiresApi(16)
    private static class Impl16 {
        public static void setDecorFitsSystemWindows(@NonNull Window window, final boolean decorFitsSystemWindows) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(decorFitsSystemWindows ? systemUiVisibility & (-1793) : systemUiVisibility | BidiFormatter.DirectionalityEstimator.DIR_TYPE_CACHE_SIZE);
        }
    }

    @RequiresApi(30)
    private static class Impl30 {
        public static WindowInsetsControllerCompat getInsetsController(@NonNull Window window) {
            WindowInsetsController insetsController = window.getInsetsController();
            if (insetsController != null) {
                return new WindowInsetsControllerCompat(insetsController);
            }
            return null;
        }

        public static void setDecorFitsSystemWindows(@NonNull Window window, final boolean decorFitsSystemWindows) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows);
        }
    }

    @Nullable
    public static WindowInsetsControllerCompat getInsetsController(@NonNull Window window, @NonNull View view) {
        return Build.VERSION.SDK_INT >= 30 ? Impl30.getInsetsController(window) : new WindowInsetsControllerCompat(window, view);
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Window window, @IdRes int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) window.requireViewById(i);
        }
        T t = (T) window.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Window");
    }

    public static void setDecorFitsSystemWindows(@NonNull Window window, final boolean decorFitsSystemWindows) {
        if (Build.VERSION.SDK_INT >= 30) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows);
        } else {
            Impl16.setDecorFitsSystemWindows(window, decorFitsSystemWindows);
        }
    }
}

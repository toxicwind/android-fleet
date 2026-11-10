package androidx.core.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: loaded from: classes.dex */
public final class PathInterpolatorCompat {
    public static Interpolator create(Path path) {
        int i = Build.VERSION.SDK_INT;
        return new PathInterpolator(path);
    }

    public static Interpolator create(float controlX, float controlY) {
        int i = Build.VERSION.SDK_INT;
        return new PathInterpolator(controlX, controlY);
    }

    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        int i = Build.VERSION.SDK_INT;
        return new PathInterpolator(controlX1, controlY1, controlX2, controlY2);
    }
}

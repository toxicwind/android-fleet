package androidx.core.graphics;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class PaintCompat {
    public static final String EM_STRING = "m";
    public static final String TOFU_STRING = "\udfffd";
    public static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String string) {
        int i = Build.VERSION.SDK_INT;
        return paint.hasGlyph(string);
    }

    public static Pair<Rect, Rect> obtainEmptyRects() {
        Pair<Rect, Rect> pair = sRectThreadLocal.get();
        if (pair == null) {
            Pair<Rect, Rect> pair2 = new Pair<>(new Rect(), new Rect());
            sRectThreadLocal.set(pair2);
            return pair2;
        }
        pair.first.setEmpty();
        pair.second.setEmpty();
        return pair;
    }

    public static boolean setBlendMode(@NonNull Paint paint, @Nullable BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            paint.setBlendMode(blendModeCompat != null ? BlendModeUtils.obtainBlendModeFromCompat(blendModeCompat) : null);
            return true;
        }
        if (blendModeCompat == null) {
            paint.setXfermode(null);
            return true;
        }
        PorterDuff.Mode modeObtainPorterDuffFromCompat = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat);
        paint.setXfermode(modeObtainPorterDuffFromCompat != null ? new PorterDuffXfermode(modeObtainPorterDuffFromCompat) : null);
        return modeObtainPorterDuffFromCompat != null;
    }
}

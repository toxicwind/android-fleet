package androidx.core.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class PaintCompat {
    public static final String EM_STRING = "m";
    public static final String TOFU_STRING = "\udfffd";
    public static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        return paint.hasGlyph(str);
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
}

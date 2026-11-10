package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class DrawableCompat {
    public static final String TAG = "DrawableCompat";
    public static Method sGetLayoutDirectionMethod;
    public static boolean sGetLayoutDirectionMethodFetched;
    public static Method sSetLayoutDirectionMethod;
    public static boolean sSetLayoutDirectionMethodFetched;

    public static void applyTheme(@NonNull Drawable drawable, @NonNull Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        drawable.applyTheme(theme);
    }

    public static boolean canApplyTheme(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.canApplyTheme();
    }

    public static void clearColorFilter(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        drawable.clearColorFilter();
    }

    public static int getAlpha(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    public static ColorFilter getColorFilter(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getColorFilter();
    }

    public static int getLayoutDirection(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getLayoutDirection();
    }

    public static void inflate(@NonNull Drawable drawable, @NonNull Resources res, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int i = Build.VERSION.SDK_INT;
        drawable.inflate(res, parser, attrs, theme);
    }

    public static boolean isAutoMirrored(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }

    @Deprecated
    public static void jumpToCurrentState(@NonNull Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void setAutoMirrored(@NonNull Drawable drawable, boolean mirrored) {
        int i = Build.VERSION.SDK_INT;
        drawable.setAutoMirrored(mirrored);
    }

    public static void setHotspot(@NonNull Drawable drawable, float x, float y) {
        int i = Build.VERSION.SDK_INT;
        drawable.setHotspot(x, y);
    }

    public static void setHotspotBounds(@NonNull Drawable drawable, int left, int top, int right, int bottom) {
        int i = Build.VERSION.SDK_INT;
        drawable.setHotspotBounds(left, top, right, bottom);
    }

    public static boolean setLayoutDirection(@NonNull Drawable drawable, int layoutDirection) {
        int i = Build.VERSION.SDK_INT;
        return drawable.setLayoutDirection(layoutDirection);
    }

    public static void setTint(@NonNull Drawable drawable, @ColorInt int tint) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTint(tint);
    }

    public static void setTintList(@NonNull Drawable drawable, @Nullable ColorStateList tint) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintList(tint);
    }

    public static void setTintMode(@NonNull Drawable drawable, @NonNull PorterDuff.Mode tintMode) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintMode(tintMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T unwrap(@NonNull Drawable drawable) {
        return drawable instanceof WrappedDrawable ? (T) ((WrappedDrawable) drawable).getWrappedDrawable() : drawable;
    }

    public static Drawable wrap(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable;
    }
}

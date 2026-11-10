package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.CheckedTextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class CheckedTextViewCompat {
    public static final String TAG = "CheckedTextViewCompat";

    private static class Api14Impl {
        public static Field sCheckMarkDrawableField;
        public static boolean sResolved;

        @Nullable
        public static Drawable getCheckMarkDrawable(@NonNull CheckedTextView textView) {
            if (!sResolved) {
                try {
                    sCheckMarkDrawableField = CheckedTextView.class.getDeclaredField("mCheckMarkDrawable");
                    sCheckMarkDrawableField.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i(CheckedTextViewCompat.TAG, "Failed to retrieve mCheckMarkDrawable field", e2);
                }
                sResolved = true;
            }
            Field field = sCheckMarkDrawableField;
            if (field != null) {
                try {
                    return (Drawable) field.get(textView);
                } catch (IllegalAccessException e3) {
                    Log.i(CheckedTextViewCompat.TAG, "Failed to get check mark drawable via reflection", e3);
                    sCheckMarkDrawableField = null;
                }
            }
            return null;
        }
    }

    @RequiresApi(16)
    private static class Api16Impl {
        @Nullable
        public static Drawable getCheckMarkDrawable(@NonNull CheckedTextView textView) {
            return textView.getCheckMarkDrawable();
        }
    }

    @RequiresApi(21)
    private static class Api21Impl {
        @Nullable
        public static ColorStateList getCheckMarkTintList(@NonNull CheckedTextView textView) {
            return textView.getCheckMarkTintList();
        }

        @Nullable
        public static PorterDuff.Mode getCheckMarkTintMode(@NonNull CheckedTextView textView) {
            return textView.getCheckMarkTintMode();
        }

        public static void setCheckMarkTintList(@NonNull CheckedTextView textView, @Nullable ColorStateList tint) {
            textView.setCheckMarkTintList(tint);
        }

        public static void setCheckMarkTintMode(@NonNull CheckedTextView textView, @Nullable PorterDuff.Mode tintMode) {
            textView.setCheckMarkTintMode(tintMode);
        }
    }

    @Nullable
    public static Drawable getCheckMarkDrawable(@NonNull CheckedTextView textView) {
        int i = Build.VERSION.SDK_INT;
        return textView.getCheckMarkDrawable();
    }

    @Nullable
    public static ColorStateList getCheckMarkTintList(@NonNull CheckedTextView textView) {
        int i = Build.VERSION.SDK_INT;
        return textView.getCheckMarkTintList();
    }

    @Nullable
    public static PorterDuff.Mode getCheckMarkTintMode(@NonNull CheckedTextView textView) {
        int i = Build.VERSION.SDK_INT;
        return textView.getCheckMarkTintMode();
    }

    public static void setCheckMarkTintList(@NonNull CheckedTextView textView, @Nullable ColorStateList tint) {
        int i = Build.VERSION.SDK_INT;
        textView.setCheckMarkTintList(tint);
    }

    public static void setCheckMarkTintMode(@NonNull CheckedTextView textView, @Nullable PorterDuff.Mode tintMode) {
        int i = Build.VERSION.SDK_INT;
        textView.setCheckMarkTintMode(tintMode);
    }
}

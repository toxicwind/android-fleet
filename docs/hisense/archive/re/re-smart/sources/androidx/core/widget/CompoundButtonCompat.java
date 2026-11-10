package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class CompoundButtonCompat {
    public static final String TAG = "CompoundButtonCompat";
    public static Field sButtonDrawableField;
    public static boolean sButtonDrawableFieldFetched;

    @Nullable
    public static Drawable getButtonDrawable(@NonNull CompoundButton button) {
        int i = Build.VERSION.SDK_INT;
        return button.getButtonDrawable();
    }

    @Nullable
    public static ColorStateList getButtonTintList(@NonNull CompoundButton button) {
        int i = Build.VERSION.SDK_INT;
        return button.getButtonTintList();
    }

    @Nullable
    public static PorterDuff.Mode getButtonTintMode(@NonNull CompoundButton button) {
        int i = Build.VERSION.SDK_INT;
        return button.getButtonTintMode();
    }

    public static void setButtonTintList(@NonNull CompoundButton button, @Nullable ColorStateList tint) {
        int i = Build.VERSION.SDK_INT;
        button.setButtonTintList(tint);
    }

    public static void setButtonTintMode(@NonNull CompoundButton button, @Nullable PorterDuff.Mode tintMode) {
        int i = Build.VERSION.SDK_INT;
        button.setButtonTintMode(tintMode);
    }
}

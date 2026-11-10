package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class ImageViewCompat {
    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImageTintList();
    }

    @Nullable
    public static PorterDuff.Mode getImageTintMode(@NonNull ImageView view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImageTintMode();
    }

    public static void setImageTintList(@NonNull ImageView view, @Nullable ColorStateList tintList) {
        int i = Build.VERSION.SDK_INT;
        view.setImageTintList(tintList);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static void setImageTintMode(@NonNull ImageView view, @Nullable PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        view.setImageTintMode(mode);
        int i2 = Build.VERSION.SDK_INT;
    }
}

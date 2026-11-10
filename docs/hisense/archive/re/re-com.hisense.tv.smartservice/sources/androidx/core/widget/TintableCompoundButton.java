package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface TintableCompoundButton {
    @Nullable
    ColorStateList getSupportButtonTintList();

    @Nullable
    PorterDuff.Mode getSupportButtonTintMode();

    void setSupportButtonTintList(@Nullable ColorStateList tint);

    void setSupportButtonTintMode(@Nullable PorterDuff.Mode tintMode);
}

package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface TintableBackgroundView {
    @Nullable
    ColorStateList getSupportBackgroundTintList();

    @Nullable
    PorterDuff.Mode getSupportBackgroundTintMode();

    void setSupportBackgroundTintList(@Nullable ColorStateList tint);

    void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode tintMode);
}

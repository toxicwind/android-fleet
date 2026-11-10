package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public interface TintAwareDrawable {
    void setTint(@ColorInt int tint);

    void setTintList(ColorStateList tint);

    void setTintMode(PorterDuff.Mode tintMode);
}

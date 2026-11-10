package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.DrawableCompat;
import b.a.a.a.a;
import com.google.android.gms.common.util.DeviceProperties;

/* JADX INFO: loaded from: classes.dex */
public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context context) {
        super(context, null, R.attr.buttonStyle);
    }

    public static int zaa(int i, int i2, int i3, int i4) {
        if (i == 0) {
            return i2;
        }
        if (i == 1) {
            return i3;
        }
        if (i == 2) {
            return i4;
        }
        throw new IllegalStateException(a.a(33, "Unknown color scheme: ", i));
    }

    public final void configure(Resources resources, SignInButtonConfig signInButtonConfig) {
        configure(resources, signInButtonConfig.getButtonSize(), signInButtonConfig.getColorScheme());
    }

    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
    }

    public final void configure(Resources resources, int i, int i2) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i3 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i3);
        setMinWidth(i3);
        int i4 = com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark;
        int i5 = com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_light;
        int iZaa = zaa(i2, i4, i5, i5);
        int i6 = com.google.android.gms.base.R.drawable.common_google_signin_btn_text_dark;
        int i7 = com.google.android.gms.base.R.drawable.common_google_signin_btn_text_light;
        int iZaa2 = zaa(i2, i6, i7, i7);
        if (i == 0 || i == 1) {
            iZaa = iZaa2;
        } else if (i != 2) {
            throw new IllegalStateException(a.a(32, "Unknown button size: ", i));
        }
        Drawable drawable = resources.getDrawable(iZaa);
        DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawable, resources.getColorStateList(com.google.android.gms.base.R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(drawable);
        int i8 = com.google.android.gms.base.R.color.common_google_signin_btn_text_dark;
        int i9 = com.google.android.gms.base.R.color.common_google_signin_btn_text_light;
        ColorStateList colorStateList = resources.getColorStateList(zaa(i2, i8, i9, i9));
        Preconditions.checkNotNull(colorStateList);
        setTextColor(colorStateList);
        if (i == 0) {
            setText(resources.getString(com.google.android.gms.base.R.string.common_signin_button_text));
        } else if (i == 1) {
            setText(resources.getString(com.google.android.gms.base.R.string.common_signin_button_text_long));
        } else {
            if (i != 2) {
                throw new IllegalStateException(a.a(32, "Unknown button size: ", i));
            }
            setText((CharSequence) null);
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }
}

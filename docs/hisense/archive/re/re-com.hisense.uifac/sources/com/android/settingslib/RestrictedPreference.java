package com.android.settingslib;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedPreference extends TwoTargetPreference {
    RestrictedPreferenceHelper mHelper;

    public RestrictedPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mHelper = new RestrictedPreferenceHelper(context, this, attributeSet);
    }

    public RestrictedPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RestrictedPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }

    @Override // com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R$layout.restricted_icon;
    }
}

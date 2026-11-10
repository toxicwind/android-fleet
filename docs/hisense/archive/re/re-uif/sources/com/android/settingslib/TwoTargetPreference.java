package com.android.settingslib;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class TwoTargetPreference extends Preference {
    protected int getSecondTargetResId() {
        return 0;
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        setLayoutResource(R$layout.preference_two_target);
        context.getResources().getDimensionPixelSize(R$dimen.two_target_pref_small_icon_size);
        context.getResources().getDimensionPixelSize(R$dimen.two_target_pref_medium_icon_size);
        int secondTargetResId = getSecondTargetResId();
        if (secondTargetResId != 0) {
            setWidgetLayoutResource(secondTargetResId);
        }
    }
}

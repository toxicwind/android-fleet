package com.android.settingslib.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class BarChartPreference extends Preference {
    public BarChartPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setSelectable(false);
        setLayoutResource(R$layout.settings_bar_chart);
        getContext().getResources().getDimensionPixelSize(R$dimen.settings_bar_view_max_height);
    }
}

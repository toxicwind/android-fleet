package com.android.settingslib.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.CheckBoxPreference;

/* JADX INFO: loaded from: classes.dex */
public class RadioButtonPreference extends CheckBoxPreference {
    private OnClickListener mListener;

    public interface OnClickListener {
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListener = null;
        init();
    }

    private void init() {
        setWidgetLayoutResource(R$layout.preference_widget_radiobutton);
        setLayoutResource(R$layout.preference_radio);
        setIconSpaceReserved(false);
    }
}

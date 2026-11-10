package com.android.settingslib.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class ActionButtonsPreference extends Preference {
    public ActionButtonsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setLayoutResource(R$layout.settings_action_buttons);
        setSelectable(false);
    }
}

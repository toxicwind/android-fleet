package com.android.settingslib.widget.apppreference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.settingslib.widget.R$layout;

/* JADX INFO: loaded from: classes.dex */
public class AppPreference extends Preference {
    public AppPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R$layout.preference_app);
    }
}

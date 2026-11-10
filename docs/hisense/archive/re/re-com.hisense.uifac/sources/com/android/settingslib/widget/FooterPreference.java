package com.android.settingslib.widget;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import com.android.settingslib.R$attr;
import com.android.settingslib.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public class FooterPreference extends Preference {
    public FooterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.footerPreferenceStyle, R.attr.preferenceStyle));
        init();
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        setTitle(charSequence);
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return getTitle();
    }

    private void init() {
        if (getIcon() == null) {
            setIcon(R$drawable.ic_info_outline_24);
        }
        setOrder(2147483646);
        if (TextUtils.isEmpty(getKey())) {
            setKey("footer_preference");
        }
    }
}

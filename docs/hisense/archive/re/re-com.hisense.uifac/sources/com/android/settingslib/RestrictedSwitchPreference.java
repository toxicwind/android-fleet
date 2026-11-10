package com.android.settingslib;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.SwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedSwitchPreference extends SwitchPreference {
    RestrictedPreferenceHelper mHelper;
    boolean mUseAdditionalSummary;

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUseAdditionalSummary = false;
        setWidgetLayoutResource(R$layout.restricted_switch_widget);
        this.mHelper = new RestrictedPreferenceHelper(context, this, attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RestrictedSwitchPreference);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedSwitchPreference_useAdditionalSummary);
            if (typedValuePeekValue != null) {
                this.mUseAdditionalSummary = typedValuePeekValue.type == 18 && typedValuePeekValue.data != 0;
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedSwitchPreference_restrictedSwitchSummary);
            if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 3) {
                int i3 = typedValuePeekValue2.resourceId;
                if (i3 != 0) {
                    context.getText(i3);
                } else {
                    CharSequence charSequence = typedValuePeekValue2.string;
                }
            }
        }
        if (this.mUseAdditionalSummary) {
            setLayoutResource(R$layout.restricted_switch_preference);
            useAdminDisabledSummary(false);
        }
    }

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }

    public RestrictedSwitchPreference(Context context) {
        this(context, null);
    }

    public void useAdminDisabledSummary(boolean z) {
        this.mHelper.useAdminDisabledSummary(z);
    }
}

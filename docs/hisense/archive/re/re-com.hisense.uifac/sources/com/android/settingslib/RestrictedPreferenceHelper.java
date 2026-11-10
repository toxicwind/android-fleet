package com.android.settingslib;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedPreferenceHelper {
    private String mAttrUserRestriction;
    private final Context mContext;

    public void useAdminDisabledSummary(boolean z) {
    }

    public RestrictedPreferenceHelper(Context context, Preference preference, AttributeSet attributeSet) {
        CharSequence text;
        this.mAttrUserRestriction = null;
        this.mContext = context;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RestrictedPreference);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedPreference_userRestriction);
            if (typedValuePeekValue == null || typedValuePeekValue.type != 3) {
                text = null;
            } else {
                int i = typedValuePeekValue.resourceId;
                if (i != 0) {
                    text = context.getText(i);
                } else {
                    text = typedValuePeekValue.string;
                }
            }
            String string = text == null ? null : text.toString();
            this.mAttrUserRestriction = string;
            if (RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, string, UserHandle.myUserId())) {
                this.mAttrUserRestriction = null;
                return;
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedPreference_useAdminDisabledSummary);
            if (typedValuePeekValue2 == null || typedValuePeekValue2.type != 18) {
                return;
            }
            int i2 = typedValuePeekValue2.data;
        }
    }
}

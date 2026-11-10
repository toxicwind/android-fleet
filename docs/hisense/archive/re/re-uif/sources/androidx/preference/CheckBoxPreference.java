package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.core.content.res.TypedArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    private final Listener mListener;

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListener = new Listener();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CheckBoxPreference, i, i2);
        setSummaryOn(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOn, R$styleable.CheckBoxPreference_android_summaryOn));
        setSummaryOff(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOff, R$styleable.CheckBoxPreference_android_summaryOff));
        setDisableDependentsState(TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_disableDependentsState, R$styleable.CheckBoxPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!CheckBoxPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                CheckBoxPreference.this.setChecked(z);
            }
        }
    }
}

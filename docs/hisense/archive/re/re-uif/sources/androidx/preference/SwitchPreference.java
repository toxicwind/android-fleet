package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.core.content.res.TypedArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListener = new Listener();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreference, i, i2);
        setSummaryOn(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOn, R$styleable.SwitchPreference_android_summaryOn));
        setSummaryOff(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOff, R$styleable.SwitchPreference_android_summaryOff));
        setSwitchTextOn(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOn, R$styleable.SwitchPreference_android_switchTextOn));
        setSwitchTextOff(TypedArrayUtils.getString(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOff, R$styleable.SwitchPreference_android_switchTextOff));
        setDisableDependentsState(TypedArrayUtils.getBoolean(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_disableDependentsState, R$styleable.SwitchPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }

    public SwitchPreference(Context context) {
        this(context, null);
    }

    public void setSwitchTextOn(CharSequence charSequence) {
        this.mSwitchOn = charSequence;
        notifyChanged();
    }

    public void setSwitchTextOff(CharSequence charSequence) {
        this.mSwitchOff = charSequence;
        notifyChanged();
    }

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!SwitchPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                SwitchPreference.this.setChecked(z);
            }
        }
    }
}

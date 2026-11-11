package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/* JADX INFO: loaded from: classes6.dex */
public class ColorPickerPreference extends Preference implements ColorPickerDialogListener {
    private static final int SIZE_LARGE = 1;
    private static final int SIZE_NORMAL = 0;
    private boolean allowCustom;
    private boolean allowPresets;
    private int color;
    private int colorShape;
    private int dialogTitle;
    private int dialogType;
    private OnShowDialogListener onShowDialogListener;
    private int[] presets;
    private int previewSize;
    private boolean showAlphaSlider;
    private boolean showColorShades;
    private boolean showDialog;

    public interface OnShowDialogListener {
        void onShowColorPickerDialog(String str, int i);
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onDialogDismissed(int i) {
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.color = -16777216;
        init(attributeSet);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.color = -16777216;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        setPersistent(true);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPreference);
        this.showDialog = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showDialog, true);
        this.dialogType = typedArrayObtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_dialogType, 1);
        this.colorShape = typedArrayObtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_colorShape, 1);
        this.allowPresets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_allowPresets, true);
        this.allowCustom = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_allowCustom, true);
        this.showAlphaSlider = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showAlphaSlider, false);
        this.showColorShades = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPreference_cpv_showColorShades, true);
        this.previewSize = typedArrayObtainStyledAttributes.getInt(R.styleable.ColorPreference_cpv_previewSize, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ColorPreference_cpv_colorPresets, 0);
        this.dialogTitle = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ColorPreference_cpv_dialogTitle, R.string.cpv_default_title);
        if (resourceId != 0) {
            this.presets = getContext().getResources().getIntArray(resourceId);
        } else {
            this.presets = ColorPickerDialog.MATERIAL_COLORS;
        }
        int i = this.colorShape;
        int i2 = this.previewSize;
        if (i == 1) {
            setWidgetLayoutResource(i2 == 1 ? R.layout.cpv_preference_circle_large : R.layout.cpv_preference_circle);
        } else {
            setWidgetLayoutResource(i2 == 1 ? R.layout.cpv_preference_square_large : R.layout.cpv_preference_square);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        super.onClick();
        OnShowDialogListener onShowDialogListener = this.onShowDialogListener;
        if (onShowDialogListener != null) {
            onShowDialogListener.onShowColorPickerDialog((String) getTitle(), this.color);
        } else if (this.showDialog) {
            ColorPickerDialog colorPickerDialogCreate = ColorPickerDialog.newBuilder().setDialogType(this.dialogType).setDialogTitle(this.dialogTitle).setColorShape(this.colorShape).setPresets(this.presets).setAllowPresets(this.allowPresets).setAllowCustom(this.allowCustom).setShowAlphaSlider(this.showAlphaSlider).setShowColorShades(this.showColorShades).setColor(this.color).create();
            colorPickerDialogCreate.setColorPickerDialogListener(this);
            getActivity().getSupportFragmentManager().beginTransaction().add(colorPickerDialogCreate, getFragmentTag()).commitAllowingStateLoss();
        }
    }

    public FragmentActivity getActivity() {
        Context context = getContext();
        if (context instanceof FragmentActivity) {
            return (FragmentActivity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof FragmentActivity) {
                return (FragmentActivity) baseContext;
            }
        }
        throw new IllegalStateException("Error getting activity from context");
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        ColorPickerDialog colorPickerDialog;
        super.onAttached();
        if (!this.showDialog || (colorPickerDialog = (ColorPickerDialog) getActivity().getSupportFragmentManager().findFragmentByTag(getFragmentTag())) == null) {
            return;
        }
        colorPickerDialog.setColorPickerDialogListener(this);
    }

    public int getValue() {
        return this.color;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ColorPanelView colorPanelView = (ColorPanelView) preferenceViewHolder.itemView.findViewById(R.id.cpv_preference_preview_color_panel);
        if (colorPanelView != null) {
            colorPanelView.setColor(this.color);
        }
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        super.onSetInitialValue(obj);
        if (obj instanceof Integer) {
            int iIntValue = ((Integer) obj).intValue();
            this.color = iIntValue;
            persistInt(iIntValue);
            return;
        }
        this.color = getPersistedInt(-16777216);
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInteger(i, -16777216));
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerDialogListener
    public void onColorSelected(int i, int i2) {
        saveValue(i2);
    }

    public void saveValue(int i) {
        this.color = i;
        persistInt(i);
        notifyChanged();
        callChangeListener(Integer.valueOf(i));
    }

    public int[] getPresets() {
        return this.presets;
    }

    public void setPresets(int[] iArr) {
        this.presets = iArr;
    }

    public void setOnShowDialogListener(OnShowDialogListener onShowDialogListener) {
        this.onShowDialogListener = onShowDialogListener;
    }

    public String getFragmentTag() {
        return "color_" + getKey();
    }
}

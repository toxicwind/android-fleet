package com.jaredrummler.android.colorpicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class ColorPickerDialog extends DialogFragment implements ColorPickerView.OnColorChangedListener, TextWatcher {
    static final int ALPHA_THRESHOLD = 165;
    private static final String ARG_ALLOW_CUSTOM = "allowCustom";
    private static final String ARG_ALLOW_PRESETS = "allowPresets";
    private static final String ARG_ALPHA = "alpha";
    private static final String ARG_COLOR = "color";
    private static final String ARG_COLOR_SHAPE = "colorShape";
    private static final String ARG_CUSTOM_BUTTON_TEXT = "customButtonText";
    private static final String ARG_DIALOG_TITLE = "dialogTitle";
    private static final String ARG_ID = "id";
    private static final String ARG_PRESETS = "presets";
    private static final String ARG_PRESETS_BUTTON_TEXT = "presetsButtonText";
    private static final String ARG_SELECTED_BUTTON_TEXT = "selectedButtonText";
    private static final String ARG_SHOW_COLOR_SHADES = "showColorShades";
    private static final String ARG_TYPE = "dialogType";
    public static final int[] MATERIAL_COLORS = {-769226, -1499549, -54125, -6543440, -10011977, -12627531, -14575885, -16537100, -16728876, -16738680, -11751600, -7617718, -3285959, -5317, -16121, -26624, -8825528, -10453621, -6381922};
    private static final String TAG = "ColorPickerDialog";
    public static final int TYPE_CUSTOM = 0;
    public static final int TYPE_PRESETS = 1;
    ColorPaletteAdapter adapter;
    int color;
    ColorPickerView colorPicker;
    ColorPickerDialogListener colorPickerDialogListener;
    int colorShape;
    private int customButtonStringRes;
    int dialogId;
    int dialogType;
    private boolean fromEditText;
    EditText hexEditText;
    ColorPanelView newColorPanel;
    private final View.OnTouchListener onPickerTouchListener = new View.OnTouchListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == ColorPickerDialog.this.hexEditText || !ColorPickerDialog.this.hexEditText.hasFocus()) {
                return false;
            }
            ColorPickerDialog.this.hexEditText.clearFocus();
            ((InputMethodManager) ColorPickerDialog.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(ColorPickerDialog.this.hexEditText.getWindowToken(), 0);
            ColorPickerDialog.this.hexEditText.clearFocus();
            return true;
        }
    };
    int[] presets;
    private int presetsButtonStringRes;
    FrameLayout rootView;
    LinearLayout shadesLayout;
    boolean showAlphaSlider;
    boolean showColorShades;
    TextView transparencyPercText;
    SeekBar transparencySeekBar;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogType {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        int i;
        this.dialogId = getArguments().getInt("id");
        this.showAlphaSlider = getArguments().getBoolean("alpha");
        this.showColorShades = getArguments().getBoolean(ARG_SHOW_COLOR_SHADES);
        this.colorShape = getArguments().getInt(ARG_COLOR_SHAPE);
        if (bundle == null) {
            this.color = getArguments().getInt("color");
            this.dialogType = getArguments().getInt(ARG_TYPE);
        } else {
            this.color = bundle.getInt("color");
            this.dialogType = bundle.getInt(ARG_TYPE);
        }
        FrameLayout frameLayout = new FrameLayout(requireActivity());
        this.rootView = frameLayout;
        int i2 = this.dialogType;
        if (i2 == 0) {
            frameLayout.addView(createPickerView());
        } else if (i2 == 1) {
            frameLayout.addView(createPresetsView());
        }
        int i3 = getArguments().getInt(ARG_SELECTED_BUTTON_TEXT);
        if (i3 == 0) {
            i3 = R.string.cpv_select;
        }
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(requireActivity()).setView(this.rootView).setPositiveButton(i3, new DialogInterface.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i4) {
                this.f$0.m8383xe4e15bb2(dialogInterface, i4);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i4) {
                dialogInterface.dismiss();
            }
        });
        int i4 = getArguments().getInt(ARG_DIALOG_TITLE);
        if (i4 != 0) {
            negativeButton.setTitle(i4);
        }
        this.presetsButtonStringRes = getArguments().getInt(ARG_PRESETS_BUTTON_TEXT);
        this.customButtonStringRes = getArguments().getInt(ARG_CUSTOM_BUTTON_TEXT);
        if (this.dialogType == 0 && getArguments().getBoolean(ARG_ALLOW_PRESETS)) {
            i = this.presetsButtonStringRes;
            if (i == 0) {
                i = R.string.cpv_presets;
            }
        } else if (this.dialogType == 1 && getArguments().getBoolean(ARG_ALLOW_CUSTOM)) {
            i = this.customButtonStringRes;
            if (i == 0) {
                i = R.string.cpv_custom;
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            negativeButton.setNeutralButton(i, (DialogInterface.OnClickListener) null);
        }
        return negativeButton.create();
    }

    /* JADX INFO: renamed from: lambda$onCreateDialog$0$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8383xe4e15bb2(DialogInterface dialogInterface, int i) {
        onColorSelected(this.color);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AlertDialog alertDialog = (AlertDialog) getDialog();
        alertDialog.getWindow().clearFlags(131080);
        alertDialog.getWindow().setSoftInputMode(4);
        Button button = alertDialog.getButton(-3);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8384x408975aa(view);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onStart$2$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8384x408975aa(View view) {
        this.rootView.removeAllViews();
        int i = this.dialogType;
        if (i == 0) {
            this.dialogType = 1;
            Button button = (Button) view;
            int i2 = this.customButtonStringRes;
            if (i2 == 0) {
                i2 = R.string.cpv_custom;
            }
            button.setText(i2);
            this.rootView.addView(createPresetsView());
            return;
        }
        if (i != 1) {
            return;
        }
        this.dialogType = 0;
        Button button2 = (Button) view;
        int i3 = this.presetsButtonStringRes;
        if (i3 == 0) {
            i3 = R.string.cpv_presets;
        }
        button2.setText(i3);
        this.rootView.addView(createPickerView());
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogDismissed();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("color", this.color);
        bundle.putInt(ARG_TYPE, this.dialogType);
        super.onSaveInstanceState(bundle);
    }

    public void setColorPickerDialogListener(ColorPickerDialogListener colorPickerDialogListener) {
        this.colorPickerDialogListener = colorPickerDialogListener;
    }

    private static boolean isRTL(Context context) {
        return context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    View createPickerView() {
        int i;
        View viewInflate = View.inflate(getActivity(), R.layout.cpv_dialog_color_picker, null);
        this.colorPicker = (ColorPickerView) viewInflate.findViewById(R.id.cpv_color_picker_view);
        ColorPanelView colorPanelView = (ColorPanelView) viewInflate.findViewById(R.id.cpv_color_panel_old);
        this.newColorPanel = (ColorPanelView) viewInflate.findViewById(R.id.cpv_color_panel_new);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.cpv_arrow_right);
        boolean zIsRTL = isRTL(viewInflate.getContext());
        Context context = viewInflate.getContext();
        if (zIsRTL) {
            i = R.drawable.cpv_ic_arrow_left_black_24dp;
        } else {
            i = R.drawable.cpv_ic_arrow_right_black_24dp;
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(context, i));
        this.hexEditText = (EditText) viewInflate.findViewById(R.id.cpv_hex);
        try {
            TypedArray typedArrayObtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, new int[]{android.R.attr.textColorPrimary});
            int color = typedArrayObtainStyledAttributes.getColor(0, -16777216);
            typedArrayObtainStyledAttributes.recycle();
            imageView.setColorFilter(color);
        } catch (Exception unused) {
        }
        this.colorPicker.setAlphaSliderVisible(this.showAlphaSlider);
        colorPanelView.setColor(getArguments().getInt("color"));
        this.colorPicker.setColor(this.color, true);
        this.newColorPanel.setColor(this.color);
        setHex(this.color);
        if (!this.showAlphaSlider) {
            this.hexEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        }
        this.newColorPanel.setOnClickListener(new View.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m8380xbb07d383(view);
            }
        });
        viewInflate.setOnTouchListener(this.onPickerTouchListener);
        this.colorPicker.setOnColorChangedListener(this);
        this.hexEditText.addTextChangedListener(this);
        this.hexEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.m8381xbc3e2662(view, z);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: renamed from: lambda$createPickerView$3$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8380xbb07d383(View view) {
        int color = this.newColorPanel.getColor();
        int i = this.color;
        if (color == i) {
            onColorSelected(i);
            dismiss();
        }
    }

    /* JADX INFO: renamed from: lambda$createPickerView$4$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8381xbc3e2662(View view, boolean z) {
        if (z) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.hexEditText, 1);
        }
    }

    @Override // com.jaredrummler.android.colorpicker.ColorPickerView.OnColorChangedListener
    public void onColorChanged(int i) {
        this.color = i;
        ColorPanelView colorPanelView = this.newColorPanel;
        if (colorPanelView != null) {
            colorPanelView.setColor(i);
        }
        if (!this.fromEditText && this.hexEditText != null) {
            setHex(i);
            if (this.hexEditText.hasFocus()) {
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.hexEditText.getWindowToken(), 0);
                this.hexEditText.clearFocus();
            }
        }
        this.fromEditText = false;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        int colorString;
        if (!this.hexEditText.isFocused() || (colorString = parseColorString(editable.toString())) == this.colorPicker.getColor()) {
            return;
        }
        this.fromEditText = true;
        this.colorPicker.setColor(colorString, true);
    }

    private void setHex(int i) {
        boolean z = this.showAlphaSlider;
        EditText editText = this.hexEditText;
        if (z) {
            editText.setText(String.format("%08X", Integer.valueOf(i)));
        } else {
            editText.setText(String.format("%06X", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)));
        }
    }

    private int parseColorString(String str) throws NumberFormatException {
        int i;
        int i2;
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        int i3 = 255;
        int i4 = 0;
        if (str.isEmpty()) {
            i = 0;
            i2 = 0;
        } else if (str.length() <= 2) {
            i2 = Integer.parseInt(str, 16);
            i = 0;
        } else if (str.length() == 3) {
            i4 = Integer.parseInt(str.substring(0, 1), 16);
            i = Integer.parseInt(str.substring(1, 2), 16);
            i2 = Integer.parseInt(str.substring(2, 3), 16);
        } else if (str.length() == 4) {
            i = Integer.parseInt(str.substring(0, 2), 16);
            i2 = Integer.parseInt(str.substring(2, 4), 16);
        } else if (str.length() == 5) {
            i4 = Integer.parseInt(str.substring(0, 1), 16);
            i = Integer.parseInt(str.substring(1, 3), 16);
            i2 = Integer.parseInt(str.substring(3, 5), 16);
        } else if (str.length() == 6) {
            i4 = Integer.parseInt(str.substring(0, 2), 16);
            i = Integer.parseInt(str.substring(2, 4), 16);
            i2 = Integer.parseInt(str.substring(4, 6), 16);
        } else if (str.length() == 7) {
            int i5 = Integer.parseInt(str.substring(0, 1), 16);
            i4 = Integer.parseInt(str.substring(1, 3), 16);
            int i6 = Integer.parseInt(str.substring(3, 5), 16);
            i2 = Integer.parseInt(str.substring(5, 7), 16);
            i3 = i5;
            i = i6;
        } else if (str.length() == 8) {
            i3 = Integer.parseInt(str.substring(0, 2), 16);
            i4 = Integer.parseInt(str.substring(2, 4), 16);
            i = Integer.parseInt(str.substring(4, 6), 16);
            i2 = Integer.parseInt(str.substring(6, 8), 16);
        } else {
            i3 = -1;
            i = -1;
            i2 = -1;
            i4 = -1;
        }
        return Color.argb(i3, i4, i, i2);
    }

    View createPresetsView() {
        View viewInflate = View.inflate(getActivity(), R.layout.cpv_dialog_presets, null);
        this.shadesLayout = (LinearLayout) viewInflate.findViewById(R.id.shades_layout);
        this.transparencySeekBar = (SeekBar) viewInflate.findViewById(R.id.transparency_seekbar);
        this.transparencyPercText = (TextView) viewInflate.findViewById(R.id.transparency_text);
        GridView gridView = (GridView) viewInflate.findViewById(R.id.gridView);
        loadPresets();
        if (this.showColorShades) {
            createColorShades(this.color);
        } else {
            this.shadesLayout.setVisibility(8);
            viewInflate.findViewById(R.id.shades_divider).setVisibility(8);
        }
        ColorPaletteAdapter colorPaletteAdapter = new ColorPaletteAdapter(new ColorPaletteAdapter.OnColorSelectedListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda8
            @Override // com.jaredrummler.android.colorpicker.ColorPaletteAdapter.OnColorSelectedListener
            public final void onColorSelected(int i) {
                this.f$0.m8382x9c0f916d(i);
            }
        }, this.presets, getSelectedItemPosition(), this.colorShape);
        this.adapter = colorPaletteAdapter;
        gridView.setAdapter((ListAdapter) colorPaletteAdapter);
        if (this.showAlphaSlider) {
            setupTransparency();
            return viewInflate;
        }
        viewInflate.findViewById(R.id.transparency_layout).setVisibility(8);
        viewInflate.findViewById(R.id.transparency_title).setVisibility(8);
        return viewInflate;
    }

    /* JADX INFO: renamed from: lambda$createPresetsView$5$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8382x9c0f916d(int i) {
        int i2 = this.color;
        if (i2 == i) {
            onColorSelected(i2);
            dismiss();
        } else {
            this.color = i;
            if (this.showColorShades) {
                createColorShades(i);
            }
        }
    }

    private void loadPresets() {
        int iAlpha = Color.alpha(this.color);
        int[] intArray = getArguments().getIntArray(ARG_PRESETS);
        this.presets = intArray;
        if (intArray == null) {
            this.presets = MATERIAL_COLORS;
        }
        int[] iArr = this.presets;
        boolean z = iArr == MATERIAL_COLORS;
        this.presets = Arrays.copyOf(iArr, iArr.length);
        if (iAlpha != 255) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.presets;
                if (i >= iArr2.length) {
                    break;
                }
                int i2 = iArr2[i];
                this.presets[i] = Color.argb(iAlpha, Color.red(i2), Color.green(i2), Color.blue(i2));
                i++;
            }
        }
        this.presets = unshiftIfNotExists(this.presets, this.color);
        int i3 = getArguments().getInt("color");
        if (i3 != this.color) {
            this.presets = unshiftIfNotExists(this.presets, i3);
        }
        if (z) {
            int[] iArr3 = this.presets;
            if (iArr3.length == 19) {
                this.presets = pushIfNotExists(iArr3, Color.argb(iAlpha, 0, 0, 0));
            }
        }
    }

    void createColorShades(int i) {
        int i2;
        int[] colorShades = getColorShades(i);
        if (this.shadesLayout.getChildCount() != 0) {
            for (int i3 = 0; i3 < this.shadesLayout.getChildCount(); i3++) {
                FrameLayout frameLayout = (FrameLayout) this.shadesLayout.getChildAt(i3);
                ColorPanelView colorPanelView = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
                ImageView imageView = (ImageView) frameLayout.findViewById(R.id.cpv_color_image_view);
                colorPanelView.setColor(colorShades[i3]);
                colorPanelView.setTag(false);
                imageView.setImageDrawable(null);
            }
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.cpv_item_horizontal_padding);
        for (final int i4 : colorShades) {
            if (this.colorShape == 0) {
                i2 = R.layout.cpv_color_item_square;
            } else {
                i2 = R.layout.cpv_color_item_circle;
            }
            View viewInflate = View.inflate(getActivity(), i2, null);
            final ColorPanelView colorPanelView2 = (ColorPanelView) viewInflate.findViewById(R.id.cpv_color_panel_view);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) colorPanelView2.getLayoutParams();
            marginLayoutParams.rightMargin = dimensionPixelSize;
            marginLayoutParams.leftMargin = dimensionPixelSize;
            colorPanelView2.setLayoutParams(marginLayoutParams);
            colorPanelView2.setColor(i4);
            this.shadesLayout.addView(viewInflate);
            colorPanelView2.post(new Runnable() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    colorPanelView2.setColor(i4);
                }
            });
            colorPanelView2.setOnClickListener(new View.OnClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8379x21f5b63b(colorPanelView2, view);
                }
            });
            colorPanelView2.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog$$ExternalSyntheticLambda5
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return ColorPickerDialog.lambda$createColorShades$8(colorPanelView2, view);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$createColorShades$7$com-jaredrummler-android-colorpicker-ColorPickerDialog, reason: not valid java name */
    /* synthetic */ void m8379x21f5b63b(ColorPanelView colorPanelView, View view) {
        if ((view.getTag() instanceof Boolean) && ((Boolean) view.getTag()).booleanValue()) {
            onColorSelected(this.color);
            dismiss();
            return;
        }
        this.color = colorPanelView.getColor();
        this.adapter.selectNone();
        for (int i = 0; i < this.shadesLayout.getChildCount(); i++) {
            FrameLayout frameLayout = (FrameLayout) this.shadesLayout.getChildAt(i);
            ColorPanelView colorPanelView2 = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.cpv_color_image_view);
            imageView.setImageResource(colorPanelView2 == view ? R.drawable.cpv_preset_checked : 0);
            if ((colorPanelView2 == view && ColorUtils.calculateLuminance(colorPanelView2.getColor()) >= 0.65d) || Color.alpha(colorPanelView2.getColor()) <= 165) {
                imageView.setColorFilter(-16777216, PorterDuff.Mode.SRC_IN);
            } else {
                imageView.setColorFilter((ColorFilter) null);
            }
            colorPanelView2.setTag(Boolean.valueOf(colorPanelView2 == view));
        }
    }

    static /* synthetic */ boolean lambda$createColorShades$8(ColorPanelView colorPanelView, View view) {
        colorPanelView.showHint();
        return true;
    }

    private void onColorSelected(int i) {
        if (this.colorPickerDialogListener != null) {
            Log.w(TAG, "Using deprecated listener which may be remove in future releases");
            this.colorPickerDialogListener.onColorSelected(this.dialogId, i);
            return;
        }
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof ColorPickerDialogListener) {
            ((ColorPickerDialogListener) activity).onColorSelected(this.dialogId, i);
        } else {
            Log.e(TAG, "The activity must implement ColorPickerDialogListener");
        }
    }

    private void onDialogDismissed() {
        if (this.colorPickerDialogListener != null) {
            Log.w(TAG, "Using deprecated listener which may be remove in future releases");
            this.colorPickerDialogListener.onDialogDismissed(this.dialogId);
        } else {
            KeyEventDispatcher.Component activity = getActivity();
            if (activity instanceof ColorPickerDialogListener) {
                ((ColorPickerDialogListener) activity).onDialogDismissed(this.dialogId);
            }
        }
    }

    private static int shadeColor(int i, double d) {
        int iAlpha = Color.alpha(i);
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        double d2 = d >= 0.0d ? 255.0d : 0.0d;
        double dAbs = Math.abs(d);
        return Color.argb(iAlpha, (int) (Math.round((d2 - ((double) iRed)) * dAbs) + ((long) iRed)), (int) (Math.round((d2 - ((double) iGreen)) * dAbs) + ((long) iGreen)), (int) (Math.round((d2 - ((double) iBlue)) * dAbs) + ((long) iBlue)));
    }

    private int[] getColorShades(int i) {
        return new int[]{shadeColor(i, 0.9d), shadeColor(i, 0.7d), shadeColor(i, 0.5d), shadeColor(i, 0.333d), shadeColor(i, 0.166d), shadeColor(i, -0.125d), shadeColor(i, -0.25d), shadeColor(i, -0.375d), shadeColor(i, -0.5d), shadeColor(i, -0.675d), shadeColor(i, -0.7d), shadeColor(i, -0.775d)};
    }

    private void setupTransparency() {
        int iAlpha = 255 - Color.alpha(this.color);
        this.transparencySeekBar.setMax(255);
        this.transparencySeekBar.setProgress(iAlpha);
        this.transparencyPercText.setText(String.format(Locale.ENGLISH, "%d%%", Integer.valueOf((int) ((((double) iAlpha) * 100.0d) / 255.0d))));
        this.transparencySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jaredrummler.android.colorpicker.ColorPickerDialog.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ColorPickerDialog colorPickerDialog;
                int i2 = 0;
                ColorPickerDialog.this.transparencyPercText.setText(String.format(Locale.ENGLISH, "%d%%", Integer.valueOf((int) ((((double) i) * 100.0d) / 255.0d))));
                int i3 = 255 - i;
                int i4 = 0;
                while (true) {
                    int length = ColorPickerDialog.this.adapter.colors.length;
                    colorPickerDialog = ColorPickerDialog.this;
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = colorPickerDialog.adapter.colors[i4];
                    ColorPickerDialog.this.adapter.colors[i4] = Color.argb(i3, Color.red(i5), Color.green(i5), Color.blue(i5));
                    i4++;
                }
                colorPickerDialog.adapter.notifyDataSetChanged();
                while (true) {
                    int childCount = ColorPickerDialog.this.shadesLayout.getChildCount();
                    ColorPickerDialog colorPickerDialog2 = ColorPickerDialog.this;
                    if (i2 < childCount) {
                        FrameLayout frameLayout = (FrameLayout) colorPickerDialog2.shadesLayout.getChildAt(i2);
                        ColorPanelView colorPanelView = (ColorPanelView) frameLayout.findViewById(R.id.cpv_color_panel_view);
                        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.cpv_color_image_view);
                        if (frameLayout.getTag() == null) {
                            frameLayout.setTag(Integer.valueOf(colorPanelView.getBorderColor()));
                        }
                        int color = colorPanelView.getColor();
                        int iArgb = Color.argb(i3, Color.red(color), Color.green(color), Color.blue(color));
                        if (i3 <= 165) {
                            colorPanelView.setBorderColor(iArgb | (-16777216));
                        } else {
                            colorPanelView.setBorderColor(((Integer) frameLayout.getTag()).intValue());
                        }
                        if (colorPanelView.getTag() != null && ((Boolean) colorPanelView.getTag()).booleanValue()) {
                            if (i3 <= 165) {
                                imageView.setColorFilter(-16777216, PorterDuff.Mode.SRC_IN);
                            } else if (ColorUtils.calculateLuminance(iArgb) >= 0.65d) {
                                imageView.setColorFilter(-16777216, PorterDuff.Mode.SRC_IN);
                            } else {
                                imageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
                            }
                        }
                        colorPanelView.setColor(iArgb);
                        i2++;
                    } else {
                        ColorPickerDialog.this.color = Color.argb(i3, Color.red(colorPickerDialog2.color), Color.green(ColorPickerDialog.this.color), Color.blue(ColorPickerDialog.this.color));
                        return;
                    }
                }
            }
        });
    }

    private int[] unshiftIfNotExists(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return iArr;
            }
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + 1];
        iArr2[0] = i;
        System.arraycopy(iArr, 0, iArr2, 1, length);
        return iArr2;
    }

    private int[] pushIfNotExists(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return iArr;
            }
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + 1];
        iArr2[length] = i;
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    private int getSelectedItemPosition() {
        int i = 0;
        while (true) {
            int[] iArr = this.presets;
            if (i >= iArr.length) {
                return -1;
            }
            if (iArr[i] == this.color) {
                return i;
            }
            i++;
        }
    }

    public static final class Builder {
        ColorPickerDialogListener colorPickerDialogListener;
        int dialogTitle = R.string.cpv_default_title;
        int presetsButtonText = R.string.cpv_presets;
        int customButtonText = R.string.cpv_custom;
        int selectedButtonText = R.string.cpv_select;
        int dialogType = 1;
        int[] presets = ColorPickerDialog.MATERIAL_COLORS;
        int color = -16777216;
        int dialogId = 0;
        boolean showAlphaSlider = false;
        boolean allowPresets = true;
        boolean allowCustom = true;
        boolean showColorShades = true;
        int colorShape = 1;

        Builder() {
        }

        public Builder setDialogTitle(int i) {
            this.dialogTitle = i;
            return this;
        }

        public Builder setSelectedButtonText(int i) {
            this.selectedButtonText = i;
            return this;
        }

        public Builder setPresetsButtonText(int i) {
            this.presetsButtonText = i;
            return this;
        }

        public Builder setCustomButtonText(int i) {
            this.customButtonText = i;
            return this;
        }

        public Builder setDialogType(int i) {
            this.dialogType = i;
            return this;
        }

        public Builder setPresets(int[] iArr) {
            this.presets = iArr;
            return this;
        }

        public Builder setColor(int i) {
            this.color = i;
            return this;
        }

        public Builder setDialogId(int i) {
            this.dialogId = i;
            return this;
        }

        public Builder setShowAlphaSlider(boolean z) {
            this.showAlphaSlider = z;
            return this;
        }

        public Builder setAllowPresets(boolean z) {
            this.allowPresets = z;
            return this;
        }

        public Builder setAllowCustom(boolean z) {
            this.allowCustom = z;
            return this;
        }

        public Builder setShowColorShades(boolean z) {
            this.showColorShades = z;
            return this;
        }

        public Builder setColorShape(int i) {
            this.colorShape = i;
            return this;
        }

        public ColorPickerDialog create() {
            ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("id", this.dialogId);
            bundle.putInt(ColorPickerDialog.ARG_TYPE, this.dialogType);
            bundle.putInt("color", this.color);
            bundle.putIntArray(ColorPickerDialog.ARG_PRESETS, this.presets);
            bundle.putBoolean("alpha", this.showAlphaSlider);
            bundle.putBoolean(ColorPickerDialog.ARG_ALLOW_CUSTOM, this.allowCustom);
            bundle.putBoolean(ColorPickerDialog.ARG_ALLOW_PRESETS, this.allowPresets);
            bundle.putInt(ColorPickerDialog.ARG_DIALOG_TITLE, this.dialogTitle);
            bundle.putBoolean(ColorPickerDialog.ARG_SHOW_COLOR_SHADES, this.showColorShades);
            bundle.putInt(ColorPickerDialog.ARG_COLOR_SHAPE, this.colorShape);
            bundle.putInt(ColorPickerDialog.ARG_PRESETS_BUTTON_TEXT, this.presetsButtonText);
            bundle.putInt(ColorPickerDialog.ARG_CUSTOM_BUTTON_TEXT, this.customButtonText);
            bundle.putInt(ColorPickerDialog.ARG_SELECTED_BUTTON_TEXT, this.selectedButtonText);
            colorPickerDialog.setArguments(bundle);
            return colorPickerDialog;
        }

        public void show(FragmentActivity fragmentActivity) {
            create().show(fragmentActivity.getSupportFragmentManager(), "color-picker-dialog");
        }
    }
}

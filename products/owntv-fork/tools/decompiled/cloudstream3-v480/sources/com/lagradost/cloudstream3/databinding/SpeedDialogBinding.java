package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SpeedDialogBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final MaterialButton speed100;
    public final MaterialButton speed125;
    public final MaterialButton speed150;
    public final MaterialButton speed200;
    public final MaterialButton speed25;
    public final Slider speedBar;
    public final FloatingActionButton speedMinus;
    public final FloatingActionButton speedPlus;
    public final TextView speedText;

    private SpeedDialogBinding(LinearLayout linearLayout, MaterialButton materialButton, MaterialButton materialButton2, MaterialButton materialButton3, MaterialButton materialButton4, MaterialButton materialButton5, Slider slider, FloatingActionButton floatingActionButton, FloatingActionButton floatingActionButton2, TextView textView) {
        this.rootView = linearLayout;
        this.speed100 = materialButton;
        this.speed125 = materialButton2;
        this.speed150 = materialButton3;
        this.speed200 = materialButton4;
        this.speed25 = materialButton5;
        this.speedBar = slider;
        this.speedMinus = floatingActionButton;
        this.speedPlus = floatingActionButton2;
        this.speedText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SpeedDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SpeedDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.speed_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SpeedDialogBinding bind(View view) {
        int i = R.id.speed_100;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.speed_125;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.speed_150;
                MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton3 != null) {
                    i = R.id.speed_200;
                    MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton4 != null) {
                        i = R.id.speed_25;
                        MaterialButton materialButton5 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton5 != null) {
                            i = R.id.speed_bar;
                            Slider slider = (Slider) ViewBindings.findChildViewById(view, i);
                            if (slider != null) {
                                i = R.id.speed_minus;
                                FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, i);
                                if (floatingActionButton != null) {
                                    i = R.id.speed_plus;
                                    FloatingActionButton floatingActionButton2 = (FloatingActionButton) ViewBindings.findChildViewById(view, i);
                                    if (floatingActionButton2 != null) {
                                        i = R.id.speed_text;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            return new SpeedDialogBinding((LinearLayout) view, materialButton, materialButton2, materialButton3, materialButton4, materialButton5, slider, floatingActionButton, floatingActionButton2, textView);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

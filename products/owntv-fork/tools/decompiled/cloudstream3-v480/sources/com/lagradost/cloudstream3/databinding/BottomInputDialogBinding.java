package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class BottomInputDialogBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final EditText nginxTextInput;
    private final LinearLayout rootView;
    public final TextView text1;

    private BottomInputDialogBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, EditText editText, TextView textView) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.nginxTextInput = editText;
        this.text1 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomInputDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BottomInputDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.bottom_input_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomInputDialogBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.nginx_text_input;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.text1;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            return new BottomInputDialogBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, editText, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

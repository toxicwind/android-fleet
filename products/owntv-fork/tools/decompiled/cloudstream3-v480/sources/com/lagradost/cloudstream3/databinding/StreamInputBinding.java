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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class StreamInputBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final MaterialButton cancelBtt;
    public final SwitchMaterial hlsSwitch;
    private final LinearLayout rootView;
    public final EditText streamReferer;
    public final EditText streamUrl;
    public final TextView text1;

    private StreamInputBinding(LinearLayout linearLayout, MaterialButton materialButton, MaterialButton materialButton2, SwitchMaterial switchMaterial, EditText editText, EditText editText2, TextView textView) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.cancelBtt = materialButton2;
        this.hlsSwitch = switchMaterial;
        this.streamReferer = editText;
        this.streamUrl = editText2;
        this.text1 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static StreamInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static StreamInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.stream_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static StreamInputBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.cancel_btt;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.hls_switch;
                SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                if (switchMaterial != null) {
                    i = R.id.stream_referer;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.stream_url;
                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
                        if (editText2 != null) {
                            i = R.id.text1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                return new StreamInputBinding((LinearLayout) view, materialButton, materialButton2, switchMaterial, editText, editText2, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

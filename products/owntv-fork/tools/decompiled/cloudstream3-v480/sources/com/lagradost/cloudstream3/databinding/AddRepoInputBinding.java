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
public final class AddRepoInputBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final EditText repoNameInput;
    public final EditText repoUrlInput;
    private final LinearLayout rootView;
    public final TextView text1;
    public final TextView text2;

    private AddRepoInputBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, EditText editText, EditText editText2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.repoNameInput = editText;
        this.repoUrlInput = editText2;
        this.text1 = textView;
        this.text2 = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AddRepoInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AddRepoInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.add_repo_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AddRepoInputBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.repo_name_input;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.repo_url_input;
                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
                        if (editText2 != null) {
                            i = R.id.text1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.text2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    return new AddRepoInputBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, editText, editText2, textView, textView2);
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

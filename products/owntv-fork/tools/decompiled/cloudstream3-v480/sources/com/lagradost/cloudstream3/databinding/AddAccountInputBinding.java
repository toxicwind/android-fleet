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
public final class AddAccountInputBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final MaterialButton createAccount;
    public final EditText loginEmailInput;
    public final EditText loginPasswordInput;
    public final EditText loginServerInput;
    public final EditText loginUsernameInput;
    private final LinearLayout rootView;
    public final TextView text1;

    private AddAccountInputBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, MaterialButton materialButton3, EditText editText, EditText editText2, EditText editText3, EditText editText4, TextView textView) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.createAccount = materialButton3;
        this.loginEmailInput = editText;
        this.loginPasswordInput = editText2;
        this.loginServerInput = editText3;
        this.loginUsernameInput = editText4;
        this.text1 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AddAccountInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AddAccountInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.add_account_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AddAccountInputBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.create_account;
                    MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton3 != null) {
                        i = R.id.login_email_input;
                        EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                        if (editText != null) {
                            i = R.id.login_password_input;
                            EditText editText2 = (EditText) ViewBindings.findChildViewById(view, i);
                            if (editText2 != null) {
                                i = R.id.login_server_input;
                                EditText editText3 = (EditText) ViewBindings.findChildViewById(view, i);
                                if (editText3 != null) {
                                    i = R.id.login_username_input;
                                    EditText editText4 = (EditText) ViewBindings.findChildViewById(view, i);
                                    if (editText4 != null) {
                                        i = R.id.text1;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            return new AddAccountInputBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, materialButton3, editText, editText2, editText3, editText4, textView);
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

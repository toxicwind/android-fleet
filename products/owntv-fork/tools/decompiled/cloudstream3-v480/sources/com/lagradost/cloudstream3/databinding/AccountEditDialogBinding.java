package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class AccountEditDialogBinding implements ViewBinding {
    public final ImageView accountImage;
    public final EditText accountName;
    public final MaterialButton applyBtt;
    public final RelativeLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final MaterialButton deleteBtt;
    public final ImageView editProfilePhotoButton;
    public final CheckBox lockProfileCheckbox;
    private final LinearLayout rootView;
    public final TextView title;

    private AccountEditDialogBinding(LinearLayout linearLayout, ImageView imageView, EditText editText, MaterialButton materialButton, RelativeLayout relativeLayout, MaterialButton materialButton2, MaterialButton materialButton3, ImageView imageView2, CheckBox checkBox, TextView textView) {
        this.rootView = linearLayout;
        this.accountImage = imageView;
        this.accountName = editText;
        this.applyBtt = materialButton;
        this.applyBttHolder = relativeLayout;
        this.cancelBtt = materialButton2;
        this.deleteBtt = materialButton3;
        this.editProfilePhotoButton = imageView2;
        this.lockProfileCheckbox = checkBox;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AccountEditDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccountEditDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.account_edit_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AccountEditDialogBinding bind(View view) {
        int i = R.id.account_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.account_name;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
            if (editText != null) {
                i = R.id.apply_btt;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton != null) {
                    i = R.id.apply_btt_holder;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                    if (relativeLayout != null) {
                        i = R.id.cancel_btt;
                        MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton2 != null) {
                            i = R.id.delete_btt;
                            MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                            if (materialButton3 != null) {
                                i = R.id.edit_profile_photo_button;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.lockProfileCheckbox;
                                    CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
                                    if (checkBox != null) {
                                        i = R.id.title;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            return new AccountEditDialogBinding((LinearLayout) view, imageView, editText, materialButton, relativeLayout, materialButton2, materialButton3, imageView2, checkBox, textView);
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

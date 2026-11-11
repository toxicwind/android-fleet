package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerQualityProfileDialogBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final MaterialButton cancelBtt;
    public final TextView currentlySelectedProfileText;
    public final MaterialButton editBtt;
    public final LinearLayout profileButtonBar;
    public final LinearLayout profileTextBar;
    public final RecyclerView profilesRecyclerview;
    private final RelativeLayout rootView;
    public final LinearLayout selectedItemHolder;
    public final MaterialButton setDefaultBtt;
    public final MaterialButton useBtt;

    private PlayerQualityProfileDialogBinding(RelativeLayout relativeLayout, MaterialButton materialButton, MaterialButton materialButton2, TextView textView, MaterialButton materialButton3, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, LinearLayout linearLayout3, MaterialButton materialButton4, MaterialButton materialButton5) {
        this.rootView = relativeLayout;
        this.applyBtt = materialButton;
        this.cancelBtt = materialButton2;
        this.currentlySelectedProfileText = textView;
        this.editBtt = materialButton3;
        this.profileButtonBar = linearLayout;
        this.profileTextBar = linearLayout2;
        this.profilesRecyclerview = recyclerView;
        this.selectedItemHolder = linearLayout3;
        this.setDefaultBtt = materialButton4;
        this.useBtt = materialButton5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static PlayerQualityProfileDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerQualityProfileDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_quality_profile_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerQualityProfileDialogBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.cancel_btt;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.currently_selected_profile_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.edit_btt;
                    MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton3 != null) {
                        i = R.id.profile_button_bar;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.profile_text_bar;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.profiles_recyclerview;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView != null) {
                                    i = R.id.selected_item_holder;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout3 != null) {
                                        i = R.id.set_default_btt;
                                        MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                        if (materialButton4 != null) {
                                            i = R.id.use_btt;
                                            MaterialButton materialButton5 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                            if (materialButton5 != null) {
                                                return new PlayerQualityProfileDialogBinding((RelativeLayout) view, materialButton, materialButton2, textView, materialButton3, linearLayout, linearLayout2, recyclerView, linearLayout3, materialButton4, materialButton5);
                                            }
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

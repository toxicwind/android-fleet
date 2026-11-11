package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentSetupExtensionsBinding implements ViewBinding {
    public final LinearLayout applyBttHolder;
    public final LinearLayout blankRepoScreen;
    public final MaterialButton nextBtt;
    public final MaterialButton prevBtt;
    public final RecyclerView repoRecyclerView;
    private final LinearLayout rootView;
    public final LinearLayout setupRoot;

    private FragmentSetupExtensionsBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, MaterialButton materialButton, MaterialButton materialButton2, RecyclerView recyclerView, LinearLayout linearLayout4) {
        this.rootView = linearLayout;
        this.applyBttHolder = linearLayout2;
        this.blankRepoScreen = linearLayout3;
        this.nextBtt = materialButton;
        this.prevBtt = materialButton2;
        this.repoRecyclerView = recyclerView;
        this.setupRoot = linearLayout4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSetupExtensionsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSetupExtensionsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_setup_extensions, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSetupExtensionsBinding bind(View view) {
        int i = R.id.apply_btt_holder;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.blank_repo_screen;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout2 != null) {
                i = R.id.next_btt;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton != null) {
                    i = R.id.prev_btt;
                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton2 != null) {
                        i = R.id.repo_recycler_view;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            LinearLayout linearLayout3 = (LinearLayout) view;
                            return new FragmentSetupExtensionsBinding(linearLayout3, linearLayout, linearLayout2, materialButton, materialButton2, recyclerView, linearLayout3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

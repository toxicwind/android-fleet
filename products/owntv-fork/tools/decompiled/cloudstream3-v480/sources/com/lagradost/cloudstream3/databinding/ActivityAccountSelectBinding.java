package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class ActivityAccountSelectBinding implements ViewBinding {
    public final AutofitRecyclerView accountRecyclerView;
    public final AppCompatImageView editAccountButton;
    private final FrameLayout rootView;
    public final TextView title;

    private ActivityAccountSelectBinding(FrameLayout frameLayout, AutofitRecyclerView autofitRecyclerView, AppCompatImageView appCompatImageView, TextView textView) {
        this.rootView = frameLayout;
        this.accountRecyclerView = autofitRecyclerView;
        this.editAccountButton = appCompatImageView;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAccountSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAccountSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_account_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAccountSelectBinding bind(View view) {
        int i = R.id.account_recycler_view;
        AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
        if (autofitRecyclerView != null) {
            i = R.id.edit_account_button;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new ActivityAccountSelectBinding((FrameLayout) view, autofitRecyclerView, appCompatImageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

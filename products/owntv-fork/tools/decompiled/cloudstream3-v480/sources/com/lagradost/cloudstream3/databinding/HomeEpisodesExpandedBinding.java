package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class HomeEpisodesExpandedBinding implements ViewBinding {
    public final ImageView homeExpandedDelete;
    public final FrameLayout homeExpandedDragDown;
    public final AutofitRecyclerView homeExpandedRecycler;
    public final TextView homeExpandedText;
    private final LinearLayout rootView;

    private HomeEpisodesExpandedBinding(LinearLayout linearLayout, ImageView imageView, FrameLayout frameLayout, AutofitRecyclerView autofitRecyclerView, TextView textView) {
        this.rootView = linearLayout;
        this.homeExpandedDelete = imageView;
        this.homeExpandedDragDown = frameLayout;
        this.homeExpandedRecycler = autofitRecyclerView;
        this.homeExpandedText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static HomeEpisodesExpandedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeEpisodesExpandedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_episodes_expanded, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeEpisodesExpandedBinding bind(View view) {
        int i = R.id.home_expanded_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.home_expanded_drag_down;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.home_expanded_recycler;
                AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
                if (autofitRecyclerView != null) {
                    i = R.id.home_expanded_text;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        return new HomeEpisodesExpandedBinding((LinearLayout) view, imageView, frameLayout, autofitRecyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

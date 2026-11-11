package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class BottomLoadingBinding implements ViewBinding {
    public final ShimmerFrameLayout homeLoadingShimmer;
    public final MaterialButton overlayLoadingSkipButton;
    public final ContentLoadingProgressBar progressBar;
    private final LinearLayout rootView;

    private BottomLoadingBinding(LinearLayout linearLayout, ShimmerFrameLayout shimmerFrameLayout, MaterialButton materialButton, ContentLoadingProgressBar contentLoadingProgressBar) {
        this.rootView = linearLayout;
        this.homeLoadingShimmer = shimmerFrameLayout;
        this.overlayLoadingSkipButton = materialButton;
        this.progressBar = contentLoadingProgressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BottomLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.bottom_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomLoadingBinding bind(View view) {
        int i = R.id.home_loading_shimmer;
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
        if (shimmerFrameLayout != null) {
            i = R.id.overlay_loading_skip_button;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                i = R.id.progressBar;
                ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                if (contentLoadingProgressBar != null) {
                    return new BottomLoadingBinding((LinearLayout) view, shimmerFrameLayout, materialButton, contentLoadingProgressBar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

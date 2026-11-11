package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.PercentageCropImageView;

/* JADX INFO: loaded from: classes6.dex */
public final class HomeScrollViewTvBinding implements ViewBinding {
    public final PercentageCropImageView homeScrollPreview;
    private final FrameLayout rootView;
    public final View titleShadow;

    private HomeScrollViewTvBinding(FrameLayout frameLayout, PercentageCropImageView percentageCropImageView, View view) {
        this.rootView = frameLayout;
        this.homeScrollPreview = percentageCropImageView;
        this.titleShadow = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HomeScrollViewTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeScrollViewTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_scroll_view_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeScrollViewTvBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.home_scroll_preview;
        PercentageCropImageView percentageCropImageView = (PercentageCropImageView) ViewBindings.findChildViewById(view, i);
        if (percentageCropImageView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.title_shadow))) != null) {
            return new HomeScrollViewTvBinding((FrameLayout) view, percentageCropImageView, viewFindChildViewById);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class HomeScrollViewBinding implements ViewBinding {
    public final ImageView homePreviewLogo;
    public final ImageView homeScrollPreview;
    public final TextView homeScrollPreviewTags;
    public final TextView homeScrollPreviewTitle;
    private final FrameLayout rootView;
    public final View titleShadow;
    public final View titleShadowTop;

    private HomeScrollViewBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, View view, View view2) {
        this.rootView = frameLayout;
        this.homePreviewLogo = imageView;
        this.homeScrollPreview = imageView2;
        this.homeScrollPreviewTags = textView;
        this.homeScrollPreviewTitle = textView2;
        this.titleShadow = view;
        this.titleShadowTop = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HomeScrollViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeScrollViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_scroll_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeScrollViewBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.home_preview_logo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.home_scroll_preview;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.home_scroll_preview_tags;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.home_scroll_preview_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.title_shadow))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.title_shadow_top))) != null) {
                        return new HomeScrollViewBinding((FrameLayout) view, imageView, imageView2, textView, textView2, viewFindChildViewById, viewFindChildViewById2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

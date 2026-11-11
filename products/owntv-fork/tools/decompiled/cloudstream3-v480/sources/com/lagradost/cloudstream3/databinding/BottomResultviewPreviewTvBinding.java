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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class BottomResultviewPreviewTvBinding implements ViewBinding {
    public final MaterialButton resultviewPreviewBookmark;
    public final TextView resultviewPreviewDescription;
    public final ImageView resultviewPreviewFavorite;
    public final FrameLayout resultviewPreviewLoading;
    public final ShimmerFrameLayout resultviewPreviewLoadingShimmer;
    public final TextView resultviewPreviewMetaDuration;
    public final TextView resultviewPreviewMetaRating;
    public final TextView resultviewPreviewMetaStatus;
    public final TextView resultviewPreviewMetaType;
    public final TextView resultviewPreviewMetaYear;
    public final MaterialButton resultviewPreviewMoreInfo;
    public final ImageView resultviewPreviewPoster;
    public final LinearLayout resultviewPreviewResult;
    public final ImageView resultviewPreviewSubscribe;
    public final TextView resultviewPreviewTitle;
    private final FrameLayout rootView;

    private BottomResultviewPreviewTvBinding(FrameLayout frameLayout, MaterialButton materialButton, TextView textView, ImageView imageView, FrameLayout frameLayout2, ShimmerFrameLayout shimmerFrameLayout, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, MaterialButton materialButton2, ImageView imageView2, LinearLayout linearLayout, ImageView imageView3, TextView textView7) {
        this.rootView = frameLayout;
        this.resultviewPreviewBookmark = materialButton;
        this.resultviewPreviewDescription = textView;
        this.resultviewPreviewFavorite = imageView;
        this.resultviewPreviewLoading = frameLayout2;
        this.resultviewPreviewLoadingShimmer = shimmerFrameLayout;
        this.resultviewPreviewMetaDuration = textView2;
        this.resultviewPreviewMetaRating = textView3;
        this.resultviewPreviewMetaStatus = textView4;
        this.resultviewPreviewMetaType = textView5;
        this.resultviewPreviewMetaYear = textView6;
        this.resultviewPreviewMoreInfo = materialButton2;
        this.resultviewPreviewPoster = imageView2;
        this.resultviewPreviewResult = linearLayout;
        this.resultviewPreviewSubscribe = imageView3;
        this.resultviewPreviewTitle = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static BottomResultviewPreviewTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BottomResultviewPreviewTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.bottom_resultview_preview_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomResultviewPreviewTvBinding bind(View view) {
        int i = R.id.resultview_preview_bookmark;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.resultview_preview_description;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.resultview_preview_favorite;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.resultview_preview_loading;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout != null) {
                        i = R.id.resultview_preview_loading_shimmer;
                        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                        if (shimmerFrameLayout != null) {
                            i = R.id.resultview_preview_meta_duration;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.resultview_preview_meta_rating;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    i = R.id.resultview_preview_meta_status;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView4 != null) {
                                        i = R.id.resultview_preview_meta_type;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView5 != null) {
                                            i = R.id.resultview_preview_meta_year;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView6 != null) {
                                                i = R.id.resultview_preview_more_info;
                                                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                if (materialButton2 != null) {
                                                    i = R.id.resultview_preview_poster;
                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView2 != null) {
                                                        i = R.id.resultview_preview_result;
                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                        if (linearLayout != null) {
                                                            i = R.id.resultview_preview_subscribe;
                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                            if (imageView3 != null) {
                                                                i = R.id.resultview_preview_title;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView7 != null) {
                                                                    return new BottomResultviewPreviewTvBinding((FrameLayout) view, materialButton, textView, imageView, frameLayout, shimmerFrameLayout, textView2, textView3, textView4, textView5, textView6, materialButton2, imageView2, linearLayout, imageView3, textView7);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

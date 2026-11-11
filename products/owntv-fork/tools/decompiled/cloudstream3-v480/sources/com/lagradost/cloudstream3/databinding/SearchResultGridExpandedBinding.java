package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SearchResultGridExpandedBinding implements ViewBinding {
    public final CardView backgroundCard;
    public final TextView episodeText;
    public final TextView imageText;
    public final ImageView imageView;
    private final LinearLayout rootView;
    public final LinearLayout searchResultRoot;
    public final TextView textFlag;
    public final TextView textIsDub;
    public final TextView textIsSub;
    public final TextView textQuality;
    public final TextView textRating;
    public final ImageView titleShadow;
    public final ContentLoadingProgressBar watchProgress;

    private SearchResultGridExpandedBinding(LinearLayout linearLayout, CardView cardView, TextView textView, TextView textView2, ImageView imageView, LinearLayout linearLayout2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, ImageView imageView2, ContentLoadingProgressBar contentLoadingProgressBar) {
        this.rootView = linearLayout;
        this.backgroundCard = cardView;
        this.episodeText = textView;
        this.imageText = textView2;
        this.imageView = imageView;
        this.searchResultRoot = linearLayout2;
        this.textFlag = textView3;
        this.textIsDub = textView4;
        this.textIsSub = textView5;
        this.textQuality = textView6;
        this.textRating = textView7;
        this.titleShadow = imageView2;
        this.watchProgress = contentLoadingProgressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SearchResultGridExpandedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchResultGridExpandedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.search_result_grid_expanded, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SearchResultGridExpandedBinding bind(View view) {
        int i = R.id.background_card;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.episode_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.imageText;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.imageView;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        LinearLayout linearLayout = (LinearLayout) view;
                        i = R.id.text_flag;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.text_is_dub;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.text_is_sub;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null) {
                                    i = R.id.text_quality;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView6 != null) {
                                        i = R.id.text_rating;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView7 != null) {
                                            i = R.id.title_shadow;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView2 != null) {
                                                i = R.id.watchProgress;
                                                ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                                                if (contentLoadingProgressBar != null) {
                                                    return new SearchResultGridExpandedBinding(linearLayout, cardView, textView, textView2, imageView, linearLayout, textView3, textView4, textView5, textView6, textView7, imageView2, contentLoadingProgressBar);
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

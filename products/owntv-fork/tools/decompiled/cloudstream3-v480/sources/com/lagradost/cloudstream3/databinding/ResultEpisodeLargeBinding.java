package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;

/* JADX INFO: loaded from: classes6.dex */
public final class ResultEpisodeLargeBinding implements ViewBinding {
    public final PieFetchButton downloadButton;
    public final TextView episodeDate;
    public final TextView episodeDescript;
    public final MaterialButton episodeFiller;
    public final LinearLayout episodeHolder;
    public final CardView episodeHolderLarge;
    public final LinearLayout episodeLinHolder;
    public final ShapeableImageView episodePlayIcon;
    public final ImageView episodePoster;
    public final CircularProgressIndicator episodeProgress;
    public final TextView episodeRating;
    public final TextView episodeRuntime;
    public final TextView episodeText;
    public final ImageView episodeUpcomingIcon;
    private final CardView rootView;
    public final FrameLayout watchProgressContainer;

    private ResultEpisodeLargeBinding(CardView cardView, PieFetchButton pieFetchButton, TextView textView, TextView textView2, MaterialButton materialButton, LinearLayout linearLayout, CardView cardView2, LinearLayout linearLayout2, ShapeableImageView shapeableImageView, ImageView imageView, CircularProgressIndicator circularProgressIndicator, TextView textView3, TextView textView4, TextView textView5, ImageView imageView2, FrameLayout frameLayout) {
        this.rootView = cardView;
        this.downloadButton = pieFetchButton;
        this.episodeDate = textView;
        this.episodeDescript = textView2;
        this.episodeFiller = materialButton;
        this.episodeHolder = linearLayout;
        this.episodeHolderLarge = cardView2;
        this.episodeLinHolder = linearLayout2;
        this.episodePlayIcon = shapeableImageView;
        this.episodePoster = imageView;
        this.episodeProgress = circularProgressIndicator;
        this.episodeRating = textView3;
        this.episodeRuntime = textView4;
        this.episodeText = textView5;
        this.episodeUpcomingIcon = imageView2;
        this.watchProgressContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ResultEpisodeLargeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResultEpisodeLargeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.result_episode_large, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ResultEpisodeLargeBinding bind(View view) {
        int i = R.id.download_button;
        PieFetchButton pieFetchButton = (PieFetchButton) ViewBindings.findChildViewById(view, i);
        if (pieFetchButton != null) {
            i = R.id.episode_date;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.episode_descript;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.episode_filler;
                    MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton != null) {
                        i = R.id.episode_holder;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            CardView cardView = (CardView) view;
                            i = R.id.episode_lin_holder;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.episode_play_icon;
                                ShapeableImageView shapeableImageView = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                if (shapeableImageView != null) {
                                    i = R.id.episode_poster;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView != null) {
                                        i = R.id.episode_progress;
                                        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                                        if (circularProgressIndicator != null) {
                                            i = R.id.episode_rating;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView3 != null) {
                                                i = R.id.episode_runtime;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView4 != null) {
                                                    i = R.id.episode_text;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView5 != null) {
                                                        i = R.id.episode_upcoming_icon;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView2 != null) {
                                                            i = R.id.watch_progress_container;
                                                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                            if (frameLayout != null) {
                                                                return new ResultEpisodeLargeBinding(cardView, pieFetchButton, textView, textView2, materialButton, linearLayout, cardView, linearLayout2, shapeableImageView, imageView, circularProgressIndicator, textView3, textView4, textView5, imageView2, frameLayout);
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

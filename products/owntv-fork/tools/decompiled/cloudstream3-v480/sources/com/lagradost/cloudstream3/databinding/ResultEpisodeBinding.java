package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;

/* JADX INFO: loaded from: classes6.dex */
public final class ResultEpisodeBinding implements ViewBinding {
    public final PieFetchButton downloadButton;
    public final MaterialButton episodeFiller;
    public final CardView episodeHolder;
    public final ImageView episodePlayIcon;
    public final CircularProgressIndicator episodeProgress;
    public final TextView episodeText;
    private final CardView rootView;
    public final FrameLayout watchProgressContainer;

    private ResultEpisodeBinding(CardView cardView, PieFetchButton pieFetchButton, MaterialButton materialButton, CardView cardView2, ImageView imageView, CircularProgressIndicator circularProgressIndicator, TextView textView, FrameLayout frameLayout) {
        this.rootView = cardView;
        this.downloadButton = pieFetchButton;
        this.episodeFiller = materialButton;
        this.episodeHolder = cardView2;
        this.episodePlayIcon = imageView;
        this.episodeProgress = circularProgressIndicator;
        this.episodeText = textView;
        this.watchProgressContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ResultEpisodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResultEpisodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.result_episode, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ResultEpisodeBinding bind(View view) {
        int i = R.id.download_button;
        PieFetchButton pieFetchButton = (PieFetchButton) ViewBindings.findChildViewById(view, i);
        if (pieFetchButton != null) {
            i = R.id.episode_filler;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                CardView cardView = (CardView) view;
                i = R.id.episode_play_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.episode_progress;
                    CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                    if (circularProgressIndicator != null) {
                        i = R.id.episode_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.watch_progress_container;
                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                            if (frameLayout != null) {
                                return new ResultEpisodeBinding(cardView, pieFetchButton, materialButton, cardView, imageView, circularProgressIndicator, textView, frameLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

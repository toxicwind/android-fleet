package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadHeaderEpisodeBinding implements ViewBinding {
    public final CheckBox deleteCheckbox;
    public final PieFetchButton downloadButton;
    public final CircularProgressIndicator downloadHeaderEpisodeProgress;
    public final ImageView downloadHeaderGotoChild;
    public final TextView downloadHeaderInfo;
    public final ImageView downloadHeaderPoster;
    public final TextView downloadHeaderTitle;
    public final CardView episodeHolder;
    public final ShapeableImageView playIcon;
    private final CardView rootView;
    public final FrameLayout watchProgressContainer;

    private DownloadHeaderEpisodeBinding(CardView cardView, CheckBox checkBox, PieFetchButton pieFetchButton, CircularProgressIndicator circularProgressIndicator, ImageView imageView, TextView textView, ImageView imageView2, TextView textView2, CardView cardView2, ShapeableImageView shapeableImageView, FrameLayout frameLayout) {
        this.rootView = cardView;
        this.deleteCheckbox = checkBox;
        this.downloadButton = pieFetchButton;
        this.downloadHeaderEpisodeProgress = circularProgressIndicator;
        this.downloadHeaderGotoChild = imageView;
        this.downloadHeaderInfo = textView;
        this.downloadHeaderPoster = imageView2;
        this.downloadHeaderTitle = textView2;
        this.episodeHolder = cardView2;
        this.playIcon = shapeableImageView;
        this.watchProgressContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static DownloadHeaderEpisodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadHeaderEpisodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_header_episode, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadHeaderEpisodeBinding bind(View view) {
        int i = R.id.delete_checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
        if (checkBox != null) {
            i = R.id.download_button;
            PieFetchButton pieFetchButton = (PieFetchButton) ViewBindings.findChildViewById(view, i);
            if (pieFetchButton != null) {
                i = R.id.download_header_episode_progress;
                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                if (circularProgressIndicator != null) {
                    i = R.id.download_header_goto_child;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.download_header_info;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.download_header_poster;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView2 != null) {
                                i = R.id.download_header_title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    CardView cardView = (CardView) view;
                                    i = R.id.play_icon;
                                    ShapeableImageView shapeableImageView = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                    if (shapeableImageView != null) {
                                        i = R.id.watch_progress_container;
                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                        if (frameLayout != null) {
                                            return new DownloadHeaderEpisodeBinding(cardView, checkBox, pieFetchButton, circularProgressIndicator, imageView, textView, imageView2, textView2, cardView, shapeableImageView, frameLayout);
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

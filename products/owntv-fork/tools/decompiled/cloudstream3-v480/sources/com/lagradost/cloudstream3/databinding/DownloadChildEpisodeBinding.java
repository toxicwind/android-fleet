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
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadChildEpisodeBinding implements ViewBinding {
    public final CheckBox deleteCheckbox;
    public final PieFetchButton downloadButton;
    public final CardView downloadChildEpisodeHolder;
    public final ImageView downloadChildEpisodePlay;
    public final CircularProgressIndicator downloadChildEpisodeProgress;
    public final TextView downloadChildEpisodeText;
    public final TextView downloadChildEpisodeTextExtra;
    private final CardView rootView;
    public final FrameLayout watchProgressContainer;

    private DownloadChildEpisodeBinding(CardView cardView, CheckBox checkBox, PieFetchButton pieFetchButton, CardView cardView2, ImageView imageView, CircularProgressIndicator circularProgressIndicator, TextView textView, TextView textView2, FrameLayout frameLayout) {
        this.rootView = cardView;
        this.deleteCheckbox = checkBox;
        this.downloadButton = pieFetchButton;
        this.downloadChildEpisodeHolder = cardView2;
        this.downloadChildEpisodePlay = imageView;
        this.downloadChildEpisodeProgress = circularProgressIndicator;
        this.downloadChildEpisodeText = textView;
        this.downloadChildEpisodeTextExtra = textView2;
        this.watchProgressContainer = frameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static DownloadChildEpisodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadChildEpisodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_child_episode, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadChildEpisodeBinding bind(View view) {
        int i = R.id.delete_checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
        if (checkBox != null) {
            i = R.id.download_button;
            PieFetchButton pieFetchButton = (PieFetchButton) ViewBindings.findChildViewById(view, i);
            if (pieFetchButton != null) {
                CardView cardView = (CardView) view;
                i = R.id.download_child_episode_play;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.download_child_episode_progress;
                    CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                    if (circularProgressIndicator != null) {
                        i = R.id.download_child_episode_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.download_child_episode_text_extra;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.watch_progress_container;
                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                if (frameLayout != null) {
                                    return new DownloadChildEpisodeBinding(cardView, checkBox, pieFetchButton, cardView, imageView, circularProgressIndicator, textView, textView2, frameLayout);
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

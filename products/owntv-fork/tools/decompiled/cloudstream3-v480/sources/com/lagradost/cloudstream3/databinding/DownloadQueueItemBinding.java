package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadQueueItemBinding implements ViewBinding {
    public final CheckBox deleteCheckbox;
    public final PieFetchButton downloadButton;
    public final CardView downloadChildEpisodeHolder;
    public final ImageView downloadChildEpisodePlay;
    public final TextView downloadChildEpisodeText;
    public final TextView downloadChildEpisodeTextExtra;
    public final LinearLayout rootHolder;
    private final LinearLayout rootView;
    public final LinearLayout separatorHolder;

    private DownloadQueueItemBinding(LinearLayout linearLayout, CheckBox checkBox, PieFetchButton pieFetchButton, CardView cardView, ImageView imageView, TextView textView, TextView textView2, LinearLayout linearLayout2, LinearLayout linearLayout3) {
        this.rootView = linearLayout;
        this.deleteCheckbox = checkBox;
        this.downloadButton = pieFetchButton;
        this.downloadChildEpisodeHolder = cardView;
        this.downloadChildEpisodePlay = imageView;
        this.downloadChildEpisodeText = textView;
        this.downloadChildEpisodeTextExtra = textView2;
        this.rootHolder = linearLayout2;
        this.separatorHolder = linearLayout3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DownloadQueueItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadQueueItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_queue_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadQueueItemBinding bind(View view) {
        int i = R.id.delete_checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
        if (checkBox != null) {
            i = R.id.download_button;
            PieFetchButton pieFetchButton = (PieFetchButton) ViewBindings.findChildViewById(view, i);
            if (pieFetchButton != null) {
                i = R.id.download_child_episode_holder;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.download_child_episode_play;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.download_child_episode_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.download_child_episode_text_extra;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                LinearLayout linearLayout = (LinearLayout) view;
                                i = R.id.separator_holder;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout2 != null) {
                                    return new DownloadQueueItemBinding(linearLayout, checkBox, pieFetchButton, cardView, imageView, textView, textView2, linearLayout, linearLayout2);
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

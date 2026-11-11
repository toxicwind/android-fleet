package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerQualityProfileItemBinding implements ViewBinding {
    public final CardView cardView;
    public final View outline;
    public final ImageView profileImageBackground;
    public final TextView profileText;
    private final ConstraintLayout rootView;
    public final TextView textIsDownloadData;
    public final TextView textIsMobileData;
    public final TextView textIsWifi;

    private PlayerQualityProfileItemBinding(ConstraintLayout constraintLayout, CardView cardView, View view, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = constraintLayout;
        this.cardView = cardView;
        this.outline = view;
        this.profileImageBackground = imageView;
        this.profileText = textView;
        this.textIsDownloadData = textView2;
        this.textIsMobileData = textView3;
        this.textIsWifi = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PlayerQualityProfileItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerQualityProfileItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_quality_profile_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerQualityProfileItemBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.card_view;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.outline))) != null) {
            i = R.id.profile_image_background;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.profile_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.text_is_download_data;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.text_is_mobile_data;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.text_is_wifi;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                return new PlayerQualityProfileItemBinding((ConstraintLayout) view, cardView, viewFindChildViewById, imageView, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

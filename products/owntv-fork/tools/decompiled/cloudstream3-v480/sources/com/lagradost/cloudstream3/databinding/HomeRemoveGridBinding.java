package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class HomeRemoveGridBinding implements ViewBinding {
    public final CardView backgroundCard;
    public final TextView imageText;
    private final CardView rootView;
    public final ImageView titleShadow;

    private HomeRemoveGridBinding(CardView cardView, CardView cardView2, TextView textView, ImageView imageView) {
        this.rootView = cardView;
        this.backgroundCard = cardView2;
        this.imageText = textView;
        this.titleShadow = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static HomeRemoveGridBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeRemoveGridBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_remove_grid, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeRemoveGridBinding bind(View view) {
        CardView cardView = (CardView) view;
        int i = R.id.imageText;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.title_shadow;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                return new HomeRemoveGridBinding(cardView, cardView, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

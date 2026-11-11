package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class RailFooterBinding implements ViewBinding {
    public final CardView navFooterProfileCard;
    public final ImageView navFooterProfilePic;
    public final LinearLayout navFooterRoot;
    private final LinearLayout rootView;

    private RailFooterBinding(LinearLayout linearLayout, CardView cardView, ImageView imageView, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.navFooterProfileCard = cardView;
        this.navFooterProfilePic = imageView;
        this.navFooterRoot = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static RailFooterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static RailFooterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.rail_footer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RailFooterBinding bind(View view) {
        int i = R.id.nav_footer_profile_card;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.nav_footer_profile_pic;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                LinearLayout linearLayout = (LinearLayout) view;
                return new RailFooterBinding(linearLayout, cardView, imageView, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

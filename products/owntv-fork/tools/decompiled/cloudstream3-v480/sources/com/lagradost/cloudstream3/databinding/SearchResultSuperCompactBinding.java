package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SearchResultSuperCompactBinding implements ViewBinding {
    public final CardView backgroundCard;
    public final TextView imageText;
    public final TextView imageTextExtra;
    public final TextView imageTextProvider;
    public final ImageView imageView;
    private final LinearLayout rootView;

    private SearchResultSuperCompactBinding(LinearLayout linearLayout, CardView cardView, TextView textView, TextView textView2, TextView textView3, ImageView imageView) {
        this.rootView = linearLayout;
        this.backgroundCard = cardView;
        this.imageText = textView;
        this.imageTextExtra = textView2;
        this.imageTextProvider = textView3;
        this.imageView = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SearchResultSuperCompactBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchResultSuperCompactBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.search_result_super_compact, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SearchResultSuperCompactBinding bind(View view) {
        int i = R.id.background_card;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.imageText;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.imageTextExtra;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.imageTextProvider;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R.id.imageView;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            return new SearchResultSuperCompactBinding((LinearLayout) view, cardView, textView, textView2, textView3, imageView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

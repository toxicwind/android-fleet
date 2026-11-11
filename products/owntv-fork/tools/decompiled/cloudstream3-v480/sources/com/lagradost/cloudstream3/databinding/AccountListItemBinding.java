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
public final class AccountListItemBinding implements ViewBinding {
    public final ImageView accountImage;
    public final TextView accountName;
    public final CardView cardView;
    public final ImageView lockIcon;
    public final View outline;
    private final CardView rootView;

    private AccountListItemBinding(CardView cardView, ImageView imageView, TextView textView, CardView cardView2, ImageView imageView2, View view) {
        this.rootView = cardView;
        this.accountImage = imageView;
        this.accountName = textView;
        this.cardView = cardView2;
        this.lockIcon = imageView2;
        this.outline = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static AccountListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccountListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.account_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AccountListItemBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.account_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.account_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                CardView cardView = (CardView) view;
                i = R.id.lock_icon;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.outline))) != null) {
                    return new AccountListItemBinding(cardView, imageView, textView, cardView, imageView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

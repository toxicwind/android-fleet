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
public final class AccountListItemEditBinding implements ViewBinding {
    public final ImageView accountImage;
    public final TextView accountName;
    public final CardView cardView;
    public final ImageView lockIcon;
    public final View outline;
    public final ImageView pencilIcon;
    private final CardView rootView;

    private AccountListItemEditBinding(CardView cardView, ImageView imageView, TextView textView, CardView cardView2, ImageView imageView2, View view, ImageView imageView3) {
        this.rootView = cardView;
        this.accountImage = imageView;
        this.accountName = textView;
        this.cardView = cardView2;
        this.lockIcon = imageView2;
        this.outline = view;
        this.pencilIcon = imageView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static AccountListItemEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccountListItemEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.account_list_item_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AccountListItemEditBinding bind(View view) {
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
                    i = R.id.pencil_icon;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView3 != null) {
                        return new AccountListItemEditBinding(cardView, imageView, textView, cardView, imageView2, viewFindChildViewById, imageView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

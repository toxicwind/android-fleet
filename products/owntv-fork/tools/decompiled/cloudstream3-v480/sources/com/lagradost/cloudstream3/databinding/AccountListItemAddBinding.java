package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class AccountListItemAddBinding implements ViewBinding {
    public final CardView cardView;
    private final CardView rootView;

    private AccountListItemAddBinding(CardView cardView, CardView cardView2) {
        this.rootView = cardView;
        this.cardView = cardView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static AccountListItemAddBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccountListItemAddBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.account_list_item_add, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AccountListItemAddBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        CardView cardView = (CardView) view;
        return new AccountListItemAddBinding(cardView, cardView);
    }
}

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
public final class AccountManagmentBinding implements ViewBinding {
    public final TextView accountLogout;
    public final ImageView accountMainProfilePicture;
    public final CardView accountMainProfilePictureHolder;
    public final TextView accountName;
    public final TextView accountSite;
    public final TextView accountSwitchAccount;
    private final LinearLayout rootView;

    private AccountManagmentBinding(LinearLayout linearLayout, TextView textView, ImageView imageView, CardView cardView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.accountLogout = textView;
        this.accountMainProfilePicture = imageView;
        this.accountMainProfilePictureHolder = cardView;
        this.accountName = textView2;
        this.accountSite = textView3;
        this.accountSwitchAccount = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AccountManagmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccountManagmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.account_managment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AccountManagmentBinding bind(View view) {
        int i = R.id.account_logout;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.account_main_profile_picture;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.account_main_profile_picture_holder;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.account_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.account_site;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.account_switch_account;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                return new AccountManagmentBinding((LinearLayout) view, textView, imageView, cardView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerPrioritizeItemBinding implements ViewBinding {
    public final ImageView addButton;
    public final TextView priorityNumber;
    public final TextView priorityText;
    private final RelativeLayout rootView;
    public final ImageView subtractButton;

    private PlayerPrioritizeItemBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2, ImageView imageView2) {
        this.rootView = relativeLayout;
        this.addButton = imageView;
        this.priorityNumber = textView;
        this.priorityText = textView2;
        this.subtractButton = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static PlayerPrioritizeItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerPrioritizeItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_prioritize_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerPrioritizeItemBinding bind(View view) {
        int i = R.id.add_button;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.priority_number;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.priority_text;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.subtract_button;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        return new PlayerPrioritizeItemBinding((RelativeLayout) view, imageView, textView, textView2, imageView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

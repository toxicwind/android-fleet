package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SortBottomSingleProviderChoiceBinding implements ViewBinding {
    public final ImageView pinicon;
    private final FrameLayout rootView;
    public final TextView text1;

    private SortBottomSingleProviderChoiceBinding(FrameLayout frameLayout, ImageView imageView, TextView textView) {
        this.rootView = frameLayout;
        this.pinicon = imageView;
        this.text1 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static SortBottomSingleProviderChoiceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SortBottomSingleProviderChoiceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.sort_bottom_single_provider_choice, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SortBottomSingleProviderChoiceBinding bind(View view) {
        int i = R.id.pinicon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.text1;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new SortBottomSingleProviderChoiceBinding((FrameLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class CustomPreferenceCategoryMaterialBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView summary;
    public final TextView title;

    private CustomPreferenceCategoryMaterialBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.summary = textView;
        this.title = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CustomPreferenceCategoryMaterialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomPreferenceCategoryMaterialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_preference_category_material, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomPreferenceCategoryMaterialBinding bind(View view) {
        int i = android.R.id.summary;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, android.R.id.summary);
        if (textView != null) {
            i = android.R.id.title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, android.R.id.title);
            if (textView2 != null) {
                return new CustomPreferenceCategoryMaterialBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

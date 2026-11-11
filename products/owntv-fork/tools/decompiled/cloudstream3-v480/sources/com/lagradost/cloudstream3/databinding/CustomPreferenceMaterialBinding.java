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
public final class CustomPreferenceMaterialBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView summary;
    public final TextView title;
    public final LinearLayout widgetFrame;

    private CustomPreferenceMaterialBinding(LinearLayout linearLayout, TextView textView, TextView textView2, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.summary = textView;
        this.title = textView2;
        this.widgetFrame = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CustomPreferenceMaterialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomPreferenceMaterialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_preference_material, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomPreferenceMaterialBinding bind(View view) {
        int i = android.R.id.summary;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, android.R.id.summary);
        if (textView != null) {
            i = android.R.id.title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, android.R.id.title);
            if (textView2 != null) {
                i = android.R.id.widget_frame;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, android.R.id.widget_frame);
                if (linearLayout != null) {
                    return new CustomPreferenceMaterialBinding((LinearLayout) view, textView, textView2, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class CustomPreferenceWidgetSeekbarBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final SeekBar seekbar;
    public final TextView seekbarValue;
    public final TextView summary;
    public final TextView title;

    private CustomPreferenceWidgetSeekbarBinding(LinearLayout linearLayout, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.seekbar = seekBar;
        this.seekbarValue = textView;
        this.summary = textView2;
        this.title = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CustomPreferenceWidgetSeekbarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomPreferenceWidgetSeekbarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_preference_widget_seekbar, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomPreferenceWidgetSeekbarBinding bind(View view) {
        int i = R.id.seekbar;
        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
        if (seekBar != null) {
            i = R.id.seekbar_value;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = android.R.id.summary;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, android.R.id.summary);
                if (textView2 != null) {
                    i = android.R.id.title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, android.R.id.title);
                    if (textView3 != null) {
                        return new CustomPreferenceWidgetSeekbarBinding((LinearLayout) view, seekBar, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

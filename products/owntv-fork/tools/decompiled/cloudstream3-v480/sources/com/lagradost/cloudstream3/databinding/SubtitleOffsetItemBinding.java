package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SubtitleOffsetItemBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ContentLoadingProgressBar subtitleProgress;
    public final TextView subtitleText;

    private SubtitleOffsetItemBinding(LinearLayout linearLayout, ContentLoadingProgressBar contentLoadingProgressBar, TextView textView) {
        this.rootView = linearLayout;
        this.subtitleProgress = contentLoadingProgressBar;
        this.subtitleText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SubtitleOffsetItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SubtitleOffsetItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.subtitle_offset_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SubtitleOffsetItemBinding bind(View view) {
        int i = R.id.subtitle_progress;
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
        if (contentLoadingProgressBar != null) {
            i = R.id.subtitle_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new SubtitleOffsetItemBinding((LinearLayout) view, contentLoadingProgressBar, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

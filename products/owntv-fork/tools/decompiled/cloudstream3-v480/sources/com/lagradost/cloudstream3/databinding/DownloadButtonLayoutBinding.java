package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadButtonLayoutBinding implements ViewBinding {
    public final MaterialButton downloadMovieButton;
    public final TextView resultMovieDownloadText;
    public final TextView resultMovieDownloadTextPrecentage;
    private final FrameLayout rootView;

    private DownloadButtonLayoutBinding(FrameLayout frameLayout, MaterialButton materialButton, TextView textView, TextView textView2) {
        this.rootView = frameLayout;
        this.downloadMovieButton = materialButton;
        this.resultMovieDownloadText = textView;
        this.resultMovieDownloadTextPrecentage = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DownloadButtonLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadButtonLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_button_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadButtonLayoutBinding bind(View view) {
        int i = R.id.download_movie_button;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.result_movie_download_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.result_movie_download_text_precentage;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new DownloadButtonLayoutBinding((FrameLayout) view, materialButton, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

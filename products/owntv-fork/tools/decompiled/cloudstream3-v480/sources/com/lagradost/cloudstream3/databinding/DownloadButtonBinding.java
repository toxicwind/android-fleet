package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.DownloadButton;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadButtonBinding implements ViewBinding {
    public final DownloadButton resultDownloadMovie;
    private final DownloadButton rootView;

    private DownloadButtonBinding(DownloadButton downloadButton, DownloadButton downloadButton2) {
        this.rootView = downloadButton;
        this.resultDownloadMovie = downloadButton2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DownloadButton getRoot() {
        return this.rootView;
    }

    public static DownloadButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_button, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadButtonBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        DownloadButton downloadButton = (DownloadButton) view;
        return new DownloadButtonBinding(downloadButton, downloadButton);
    }
}

package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class DownloadButtonViewBinding implements ViewBinding {
    public final ImageView imageDownloadStatus;
    public final ContentLoadingProgressBar progressDownloaded;
    public final View progressDownloadedBackground;
    private final FrameLayout rootView;

    private DownloadButtonViewBinding(FrameLayout frameLayout, ImageView imageView, ContentLoadingProgressBar contentLoadingProgressBar, View view) {
        this.rootView = frameLayout;
        this.imageDownloadStatus = imageView;
        this.progressDownloaded = contentLoadingProgressBar;
        this.progressDownloadedBackground = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DownloadButtonViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DownloadButtonViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.download_button_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DownloadButtonViewBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.image_download_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.progress_downloaded;
            ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
            if (contentLoadingProgressBar != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.progress_downloaded_background))) != null) {
                return new DownloadButtonViewBinding((FrameLayout) view, imageView, contentLoadingProgressBar, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}

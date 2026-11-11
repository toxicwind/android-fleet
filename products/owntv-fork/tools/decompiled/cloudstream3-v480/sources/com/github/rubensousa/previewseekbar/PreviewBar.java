package com.github.rubensousa.previewseekbar;

import android.widget.FrameLayout;
import com.github.rubensousa.previewseekbar.animator.PreviewAnimator;

/* JADX INFO: loaded from: classes5.dex */
public interface PreviewBar {

    public interface OnPreviewVisibilityListener {
        void onVisibilityChanged(PreviewBar previewBar, boolean z);
    }

    public interface OnScrubListener {
        void onScrubMove(PreviewBar previewBar, int i, boolean z);

        void onScrubStart(PreviewBar previewBar);

        void onScrubStop(PreviewBar previewBar);
    }

    void addOnPreviewVisibilityListener(OnPreviewVisibilityListener onPreviewVisibilityListener);

    void addOnScrubListener(OnScrubListener onScrubListener);

    void attachPreviewView(FrameLayout frameLayout);

    int getMax();

    int getProgress();

    int getScrubberColor();

    int getThumbOffset();

    void hidePreview();

    boolean isPreviewEnabled();

    boolean isShowingPreview();

    void removeOnPreviewVisibilityListener(OnPreviewVisibilityListener onPreviewVisibilityListener);

    void removeOnScrubListener(OnScrubListener onScrubListener);

    void setAutoHidePreview(boolean z);

    void setPreviewAnimationEnabled(boolean z);

    void setPreviewAnimator(PreviewAnimator previewAnimator);

    void setPreviewEnabled(boolean z);

    void setPreviewLoader(PreviewLoader previewLoader);

    void setPreviewThumbTint(int i);

    void setPreviewThumbTintResource(int i);

    void showPreview();
}

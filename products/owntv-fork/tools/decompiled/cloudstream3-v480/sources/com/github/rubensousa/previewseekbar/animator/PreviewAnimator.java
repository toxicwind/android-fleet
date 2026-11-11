package com.github.rubensousa.previewseekbar.animator;

import android.widget.FrameLayout;
import com.github.rubensousa.previewseekbar.PreviewBar;

/* JADX INFO: loaded from: classes5.dex */
public interface PreviewAnimator {
    void cancel(FrameLayout frameLayout, PreviewBar previewBar);

    void hide(FrameLayout frameLayout, PreviewBar previewBar);

    void move(FrameLayout frameLayout, PreviewBar previewBar);

    void show(FrameLayout frameLayout, PreviewBar previewBar);
}

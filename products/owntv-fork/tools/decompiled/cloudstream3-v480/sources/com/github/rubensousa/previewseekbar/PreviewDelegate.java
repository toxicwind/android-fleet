package com.github.rubensousa.previewseekbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.github.rubensousa.previewseekbar.animator.PreviewAnimator;
import com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewDelegate {
    private boolean isUserScrubbing;
    private PreviewBar previewBar;
    private boolean previewEnabled;
    private PreviewLoader previewLoader;
    private FrameLayout previewView;
    private boolean previewViewAttached;
    private boolean showingPreview;
    private List<PreviewBar.OnScrubListener> scrubListeners = new ArrayList();
    private List<PreviewBar.OnPreviewVisibilityListener> visibilityListeners = new ArrayList();
    private boolean animationEnabled = true;
    private boolean previewAutoHide = true;
    private PreviewAnimator animator = new PreviewMorphAnimator();

    public PreviewDelegate(PreviewBar previewBar) {
        this.previewBar = previewBar;
    }

    public static FrameLayout findPreviewView(ViewGroup viewGroup, int i) {
        if (i == -1) {
            return null;
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getId() == i && (childAt instanceof FrameLayout)) {
                return (FrameLayout) childAt;
            }
        }
        return null;
    }

    public void onScrubStart() {
        Iterator<PreviewBar.OnScrubListener> it = this.scrubListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrubStart(this.previewBar);
        }
    }

    public void onScrubMove(int i, boolean z) {
        if (this.previewViewAttached) {
            this.previewView.setX(updatePreviewX(i, this.previewBar.getMax()));
            if (this.animationEnabled) {
                this.animator.move(this.previewView, this.previewBar);
            }
            if (!this.isUserScrubbing && z && this.previewEnabled) {
                this.isUserScrubbing = true;
                show();
            }
            Iterator<PreviewBar.OnScrubListener> it = this.scrubListeners.iterator();
            while (it.hasNext()) {
                it.next().onScrubMove(this.previewBar, i, z);
            }
            PreviewLoader previewLoader = this.previewLoader;
            if (previewLoader == null || !this.showingPreview) {
                return;
            }
            previewLoader.loadPreview(i, this.previewBar.getMax());
        }
    }

    public void show() {
        if (!this.showingPreview && this.previewViewAttached && this.previewEnabled) {
            boolean z = this.animationEnabled;
            PreviewAnimator previewAnimator = this.animator;
            if (z) {
                previewAnimator.show(this.previewView, this.previewBar);
            } else {
                previewAnimator.cancel(this.previewView, this.previewBar);
                this.previewView.setVisibility(0);
            }
            this.showingPreview = true;
            Iterator<PreviewBar.OnPreviewVisibilityListener> it = this.visibilityListeners.iterator();
            while (it.hasNext()) {
                it.next().onVisibilityChanged(this.previewBar, true);
            }
        }
    }

    public void onScrubStop() {
        this.isUserScrubbing = false;
        if (this.previewAutoHide) {
            hide();
        }
        Iterator<PreviewBar.OnScrubListener> it = this.scrubListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrubStop(this.previewBar);
        }
    }

    public void setPreviewLoader(PreviewLoader previewLoader) {
        this.previewLoader = previewLoader;
    }

    public void setAnimator(PreviewAnimator previewAnimator) {
        this.animator = previewAnimator;
    }

    public boolean isPreviewEnabled() {
        return this.previewEnabled;
    }

    public boolean isShowingPreview() {
        return this.showingPreview;
    }

    public void hide() {
        if (this.showingPreview && this.previewViewAttached) {
            boolean z = this.animationEnabled;
            PreviewAnimator previewAnimator = this.animator;
            if (z) {
                previewAnimator.hide(this.previewView, this.previewBar);
            } else {
                previewAnimator.cancel(this.previewView, this.previewBar);
                this.previewView.setVisibility(4);
            }
            this.showingPreview = false;
            Iterator<PreviewBar.OnPreviewVisibilityListener> it = this.visibilityListeners.iterator();
            while (it.hasNext()) {
                it.next().onVisibilityChanged(this.previewBar, false);
            }
        }
    }

    public void setPreviewEnabled(boolean z) {
        this.previewEnabled = z;
    }

    public void setAnimationEnabled(boolean z) {
        this.animationEnabled = z;
    }

    public void setAutoHidePreview(boolean z) {
        this.previewAutoHide = z;
    }

    public void attachPreviewView(FrameLayout frameLayout) {
        this.previewView = frameLayout;
        frameLayout.setVisibility(4);
        this.previewViewAttached = true;
    }

    public boolean isPreviewViewAttached() {
        return this.previewViewAttached;
    }

    public boolean isUserScrubbing() {
        return this.isUserScrubbing;
    }

    public void updateProgress(int i, int i2) {
        if (!isShowingPreview() || isUserScrubbing()) {
            return;
        }
        onScrubMove(i, false);
    }

    public void addOnScrubListener(PreviewBar.OnScrubListener onScrubListener) {
        if (this.scrubListeners.contains(onScrubListener)) {
            return;
        }
        this.scrubListeners.add(onScrubListener);
    }

    public void removeOnScrubListener(PreviewBar.OnScrubListener onScrubListener) {
        this.scrubListeners.remove(onScrubListener);
    }

    public void addOnPreviewVisibilityListener(PreviewBar.OnPreviewVisibilityListener onPreviewVisibilityListener) {
        if (this.visibilityListeners.contains(onPreviewVisibilityListener)) {
            return;
        }
        this.visibilityListeners.add(onPreviewVisibilityListener);
    }

    private int updatePreviewX(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        ViewGroup viewGroup = (ViewGroup) this.previewView.getParent();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.previewView.getLayoutParams();
        float f = i / i2;
        int left = this.previewView.getLeft();
        int width = (viewGroup.getWidth() - viewGroup.getPaddingRight()) - marginLayoutParams.rightMargin;
        float thumbOffset = this.previewBar.getThumbOffset();
        float left2 = ((View) this.previewBar).getLeft() + thumbOffset;
        float right = (left2 + (((((View) this.previewBar).getRight() - thumbOffset) - left2) * f)) - (this.previewView.getWidth() / 2.0f);
        float width2 = this.previewView.getWidth() + right;
        float f2 = left;
        if (right < f2 || width2 > width) {
            return right < f2 ? left : width - this.previewView.getWidth();
        }
        return (int) right;
    }

    public void removeOnPreviewVisibilityListener(PreviewBar.OnPreviewVisibilityListener onPreviewVisibilityListener) {
        this.visibilityListeners.remove(onPreviewVisibilityListener);
    }
}

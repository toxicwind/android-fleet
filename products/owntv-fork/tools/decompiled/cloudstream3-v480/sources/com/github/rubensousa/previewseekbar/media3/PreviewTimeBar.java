package com.github.rubensousa.previewseekbar.media3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.media3.ui.DefaultTimeBar;
import androidx.media3.ui.TimeBar;
import com.github.rubensousa.previewseekbar.PreviewBar;
import com.github.rubensousa.previewseekbar.PreviewDelegate;
import com.github.rubensousa.previewseekbar.PreviewLoader;
import com.github.rubensousa.previewseekbar.animator.PreviewAnimator;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewTimeBar extends DefaultTimeBar implements PreviewBar {
    private PreviewDelegate delegate;
    private int duration;
    private int previewId;
    private int scrubProgress;
    private int scrubberColor;
    private int scrubberPadding;

    public PreviewTimeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, androidx.media3.ui.R.styleable.DefaultTimeBar, 0, 0);
        this.scrubberColor = typedArrayObtainStyledAttributes.getInt(androidx.media3.ui.R.styleable.DefaultTimeBar_scrubber_color, -1);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(androidx.media3.ui.R.styleable.DefaultTimeBar_scrubber_drawable);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.media3.ui.R.styleable.DefaultTimeBar_scrubber_enabled_size, dpToPx(context.getResources().getDisplayMetrics(), 12));
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.media3.ui.R.styleable.DefaultTimeBar_scrubber_disabled_size, dpToPx(context.getResources().getDisplayMetrics(), 0));
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.media3.ui.R.styleable.DefaultTimeBar_scrubber_dragged_size, dpToPx(context.getResources().getDisplayMetrics(), 16));
        if (drawable != null) {
            this.scrubberPadding = (drawable.getMinimumWidth() + 1) / 2;
        } else {
            this.scrubberPadding = (Math.max(dimensionPixelSize2, Math.max(dimensionPixelSize, dimensionPixelSize3)) + 1) / 2;
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PreviewTimeBar, 0, 0);
        this.previewId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.PreviewTimeBar_previewFrameLayout, -1);
        PreviewDelegate previewDelegate = new PreviewDelegate(this);
        this.delegate = previewDelegate;
        previewDelegate.setPreviewEnabled(isEnabled());
        this.delegate.setAnimationEnabled(typedArrayObtainStyledAttributes2.getBoolean(R.styleable.PreviewTimeBar_previewAnimationEnabled, true));
        this.delegate.setPreviewEnabled(typedArrayObtainStyledAttributes2.getBoolean(R.styleable.PreviewTimeBar_previewEnabled, true));
        this.delegate.setAutoHidePreview(typedArrayObtainStyledAttributes2.getBoolean(R.styleable.PreviewTimeBar_previewAutoHide, true));
        typedArrayObtainStyledAttributes2.recycle();
        addListener(new TimeBarDefaultOnScrubListener());
    }

    @Override // androidx.media3.ui.DefaultTimeBar, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        FrameLayout frameLayoutFindPreviewView;
        super.onLayout(z, i, i2, i3, i4);
        if (this.delegate.isPreviewViewAttached() || isInEditMode() || (frameLayoutFindPreviewView = PreviewDelegate.findPreviewView((ViewGroup) getParent(), this.previewId)) == null) {
            return;
        }
        this.delegate.attachPreviewView(frameLayoutFindPreviewView);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewThumbTint(int i) {
        setScrubberColor(i);
        this.scrubberColor = i;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewThumbTintResource(int i) {
        setPreviewThumbTint(ContextCompat.getColor(getContext(), i));
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewLoader(PreviewLoader previewLoader) {
        this.delegate.setPreviewLoader(previewLoader);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void attachPreviewView(FrameLayout frameLayout) {
        this.delegate.attachPreviewView(frameLayout);
    }

    @Override // androidx.media3.ui.DefaultTimeBar, androidx.media3.ui.TimeBar
    public void setDuration(long j) {
        super.setDuration(j);
        int i = (int) j;
        if (i != this.duration) {
            this.duration = i;
            this.delegate.updateProgress(getProgress(), i);
        }
    }

    @Override // androidx.media3.ui.DefaultTimeBar, androidx.media3.ui.TimeBar
    public void setPosition(long j) {
        super.setPosition(j);
        int i = (int) j;
        if (i != this.scrubProgress) {
            this.scrubProgress = i;
            this.delegate.updateProgress(i, this.duration);
        }
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public boolean isShowingPreview() {
        return this.delegate.isShowingPreview();
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public boolean isPreviewEnabled() {
        return this.delegate.isPreviewEnabled();
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewEnabled(boolean z) {
        this.delegate.setPreviewEnabled(z);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void showPreview() {
        this.delegate.show();
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void hidePreview() {
        this.delegate.hide();
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setAutoHidePreview(boolean z) {
        this.delegate.setAutoHidePreview(z);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public int getProgress() {
        return this.scrubProgress;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public int getMax() {
        return this.duration;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public int getThumbOffset() {
        return this.scrubberPadding;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public int getScrubberColor() {
        return this.scrubberColor;
    }

    @Override // androidx.media3.ui.DefaultTimeBar
    public void setScrubberColor(int i) {
        super.setScrubberColor(i);
        this.scrubberColor = i;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void addOnScrubListener(PreviewBar.OnScrubListener onScrubListener) {
        this.delegate.addOnScrubListener(onScrubListener);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void removeOnScrubListener(PreviewBar.OnScrubListener onScrubListener) {
        this.delegate.removeOnScrubListener(onScrubListener);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void addOnPreviewVisibilityListener(PreviewBar.OnPreviewVisibilityListener onPreviewVisibilityListener) {
        this.delegate.addOnPreviewVisibilityListener(onPreviewVisibilityListener);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void removeOnPreviewVisibilityListener(PreviewBar.OnPreviewVisibilityListener onPreviewVisibilityListener) {
        this.delegate.removeOnPreviewVisibilityListener(onPreviewVisibilityListener);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewAnimator(PreviewAnimator previewAnimator) {
        this.delegate.setAnimator(previewAnimator);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewAnimationEnabled(boolean z) {
        this.delegate.setAnimationEnabled(z);
    }

    private int dpToPx(DisplayMetrics displayMetrics, int i) {
        return (int) ((i * displayMetrics.density) + 0.5f);
    }

    private class TimeBarDefaultOnScrubListener implements TimeBar.OnScrubListener {
        private TimeBarDefaultOnScrubListener() {
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long j) {
            PreviewTimeBar.this.scrubProgress = (int) j;
            PreviewTimeBar.this.delegate.onScrubStart();
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long j) {
            int i = (int) j;
            PreviewTimeBar.this.scrubProgress = i;
            PreviewTimeBar.this.delegate.onScrubMove(i, true);
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            PreviewTimeBar.this.scrubProgress = (int) j;
            PreviewTimeBar.this.delegate.onScrubStop();
        }
    }
}

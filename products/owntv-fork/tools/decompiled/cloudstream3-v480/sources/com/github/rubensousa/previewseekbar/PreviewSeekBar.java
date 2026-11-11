package com.github.rubensousa.previewseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.github.rubensousa.previewseekbar.animator.PreviewAnimator;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewSeekBar extends AppCompatSeekBar implements PreviewBar {
    private PreviewDelegate delegate;
    private int previewId;
    private int scrubberColor;

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
    }

    public PreviewSeekBar(Context context) {
        this(context, null);
    }

    public PreviewSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.seekBarStyle);
    }

    public PreviewSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.previewId = -1;
        this.scrubberColor = 0;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.delegate = new PreviewDelegate(this);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PreviewSeekBar, 0, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{androidx.appcompat.R.attr.colorAccent});
        int color = typedArrayObtainStyledAttributes2.getColor(0, 0);
        typedArrayObtainStyledAttributes2.recycle();
        this.previewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PreviewSeekBar_previewFrameLayout, -1);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.PreviewSeekBar_previewThumbTint, color);
        this.scrubberColor = color2;
        setPreviewThumbTint(color2);
        this.delegate.setAnimationEnabled(typedArrayObtainStyledAttributes.getBoolean(R.styleable.PreviewSeekBar_previewAnimationEnabled, true));
        this.delegate.setPreviewEnabled(typedArrayObtainStyledAttributes.getBoolean(R.styleable.PreviewSeekBar_previewEnabled, true));
        this.delegate.setAutoHidePreview(typedArrayObtainStyledAttributes.getBoolean(R.styleable.PreviewSeekBar_previewAutoHide, true));
        typedArrayObtainStyledAttributes.recycle();
        super.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.github.rubensousa.previewseekbar.PreviewSeekBar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PreviewSeekBar.this.delegate.onScrubMove(i, z);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                PreviewSeekBar.this.delegate.onScrubStart();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                PreviewSeekBar.this.delegate.onScrubStop();
            }
        });
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        FrameLayout frameLayoutFindPreviewView;
        super.onLayout(z, i, i2, i3, i4);
        if (this.delegate.isPreviewViewAttached() || isInEditMode() || (frameLayoutFindPreviewView = PreviewDelegate.findPreviewView((ViewGroup) getParent(), this.previewId)) == null) {
            return;
        }
        this.delegate.attachPreviewView(frameLayoutFindPreviewView);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        super.setProgress(i);
        PreviewDelegate previewDelegate = this.delegate;
        if (previewDelegate != null) {
            previewDelegate.updateProgress(i, getMax());
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar
    public synchronized void setMax(int i) {
        super.setMax(i);
        PreviewDelegate previewDelegate = this.delegate;
        if (previewDelegate != null) {
            previewDelegate.updateProgress(getProgress(), getMax());
        }
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void attachPreviewView(FrameLayout frameLayout) {
        this.delegate.attachPreviewView(frameLayout);
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewThumbTint(int i) {
        Drawable drawableWrap = DrawableCompat.wrap(getThumb());
        DrawableCompat.setTint(drawableWrap, i);
        setThumb(drawableWrap);
        this.scrubberColor = i;
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public void setPreviewThumbTintResource(int i) {
        setPreviewThumbTint(ContextCompat.getColor(getContext(), i));
    }

    @Override // com.github.rubensousa.previewseekbar.PreviewBar
    public int getScrubberColor() {
        return this.scrubberColor;
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
    public void setPreviewLoader(PreviewLoader previewLoader) {
        this.delegate.setPreviewLoader(previewLoader);
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

    public void setProgressTint(int i) {
        Drawable drawableWrap = DrawableCompat.wrap(getProgressDrawable());
        DrawableCompat.setTint(drawableWrap, i);
        setProgressDrawable(drawableWrap);
    }

    public void setProgressTintResource(int i) {
        setProgressTint(ContextCompat.getColor(getContext(), i));
    }
}

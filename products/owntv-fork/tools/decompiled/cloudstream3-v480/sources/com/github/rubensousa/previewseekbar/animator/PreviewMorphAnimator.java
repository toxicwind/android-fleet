package com.github.rubensousa.previewseekbar.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import com.github.rubensousa.previewseekbar.PreviewBar;
import com.github.rubensousa.previewseekbar.R;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewMorphAnimator implements PreviewAnimator {
    private static final int MORPH_HIDE_DURATION = 125;
    private static final int MORPH_SHOW_DURATION = 125;
    private static final int TRANSLATION_HIDE_DURATION = 100;
    private static final int TRANSLATION_SHOW_DURATION = 100;
    private long hideTranslationDuration;
    private boolean isHiding;
    private boolean isMorphingToHide;
    private boolean isMorphingToShow;
    private boolean isMovingToHide;
    private boolean isMovingToShow;
    private boolean isShowing;
    private Animator morphAnimator;
    private long morphHideDuration;
    private long morphShowDuration;
    private long showTranslationDuration;
    private ValueAnimator translationAnimator;

    public PreviewMorphAnimator() {
        this(100L, 125L, 125L, 100L);
    }

    public PreviewMorphAnimator(long j, long j2, long j3, long j4) {
        this.showTranslationDuration = j;
        this.morphShowDuration = j2;
        this.morphHideDuration = j3;
        this.hideTranslationDuration = j4;
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void cancel(FrameLayout frameLayout, PreviewBar previewBar) {
        View orCreateOverlayView = getOrCreateOverlayView(frameLayout);
        View orCreateMorphView = getOrCreateMorphView(frameLayout, previewBar);
        orCreateOverlayView.setVisibility(4);
        orCreateMorphView.setVisibility(4);
        cancelPendingAnimations(frameLayout, orCreateOverlayView, orCreateMorphView);
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void move(FrameLayout frameLayout, PreviewBar previewBar) {
        float morphStartX;
        if (this.isMovingToHide || this.isMovingToShow) {
            View orCreateMorphView = getOrCreateMorphView(frameLayout, previewBar);
            if (this.isMovingToShow) {
                morphStartX = getMorphEndX(frameLayout, previewBar);
            } else {
                morphStartX = getMorphStartX(previewBar, getOffset(previewBar));
            }
            ValueAnimator valueAnimator = this.translationAnimator;
            if (valueAnimator != null) {
                valueAnimator.removeAllUpdateListeners();
                this.translationAnimator.cancel();
            }
            orCreateMorphView.setX(morphStartX);
        }
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void show(FrameLayout frameLayout, PreviewBar previewBar) {
        if (previewBar.getMax() == 0 || this.isShowing) {
            return;
        }
        this.isHiding = false;
        this.isShowing = true;
        View orCreateOverlayView = getOrCreateOverlayView(frameLayout);
        View orCreateMorphView = getOrCreateMorphView(frameLayout, previewBar);
        cancelPendingAnimations(frameLayout, orCreateOverlayView, orCreateMorphView);
        if (this.isMovingToHide || this.isMorphingToHide) {
            this.isMovingToHide = false;
            this.isMorphingToHide = false;
            startCircularReveal(frameLayout, orCreateOverlayView, orCreateMorphView);
            return;
        }
        tintViews(previewBar, orCreateMorphView, orCreateOverlayView);
        orCreateMorphView.setY(getMorphStartY(previewBar));
        orCreateMorphView.setX(getMorphStartX(previewBar, getOffset(previewBar)));
        orCreateMorphView.setScaleX(0.0f);
        orCreateMorphView.setScaleY(0.0f);
        orCreateMorphView.setAlpha(1.0f);
        startShowTranslation(frameLayout, previewBar, orCreateOverlayView, orCreateMorphView);
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void hide(FrameLayout frameLayout, PreviewBar previewBar) {
        if (this.isHiding) {
            return;
        }
        this.isShowing = false;
        this.isHiding = true;
        View orCreateMorphView = getOrCreateMorphView(frameLayout, previewBar);
        View orCreateOverlayView = getOrCreateOverlayView(frameLayout);
        cancelPendingAnimations(frameLayout, orCreateOverlayView, orCreateMorphView);
        if (this.isMovingToShow) {
            this.isMovingToShow = false;
            startHideTranslation(frameLayout, previewBar, orCreateOverlayView, orCreateMorphView);
            return;
        }
        if (this.isMorphingToShow) {
            this.isMorphingToShow = false;
            startHideTranslation(frameLayout, previewBar, orCreateOverlayView, orCreateMorphView);
            return;
        }
        tintViews(previewBar, orCreateMorphView, orCreateOverlayView);
        orCreateOverlayView.setVisibility(0);
        frameLayout.setVisibility(0);
        float morphScale = getMorphScale(frameLayout, orCreateMorphView);
        orCreateMorphView.setX(getMorphEndX(frameLayout, previewBar));
        orCreateMorphView.setY(getMorphEndY(frameLayout, orCreateMorphView));
        orCreateMorphView.setScaleX(morphScale);
        orCreateMorphView.setScaleY(morphScale);
        orCreateMorphView.setVisibility(4);
        if (frameLayout.isAttachedToWindow()) {
            startReverseCircularReveal(frameLayout, previewBar, orCreateOverlayView, orCreateMorphView);
        }
    }

    private void startShowTranslation(final FrameLayout frameLayout, PreviewBar previewBar, final View view, final View view2) {
        this.isMovingToShow = true;
        float morphScale = getMorphScale(frameLayout, view2);
        view.setVisibility(4);
        frameLayout.setVisibility(4);
        view2.setVisibility(0);
        animateMorphViewX(view2, getMorphEndX(frameLayout, previewBar), this.showTranslationDuration);
        view2.animate().y(getMorphEndY(frameLayout, view2)).scaleY(morphScale).scaleX(morphScale).setDuration(this.showTranslationDuration).setInterpolator(new AccelerateInterpolator()).setListener(new AnimatorListenerAdapter() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PreviewMorphAnimator.this.isMovingToShow = false;
                view.setAlpha(1.0f);
                if (frameLayout.isAttachedToWindow()) {
                    PreviewMorphAnimator.this.startCircularReveal(frameLayout, view, view2);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCircularReveal(FrameLayout frameLayout, final View view, View view2) {
        this.isMorphingToShow = true;
        float height = frameLayout.getHeight() / 2.0f;
        float radius = getRadius(frameLayout);
        long j = this.morphShowDuration;
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(frameLayout, getCenterX(frameLayout), getCenterY(frameLayout), height, radius);
        this.morphAnimator = animatorCreateCircularReveal;
        animatorCreateCircularReveal.setTarget(frameLayout);
        this.morphAnimator.setInterpolator(new AccelerateInterpolator());
        this.morphAnimator.setDuration(j);
        this.morphAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PreviewMorphAnimator.this.isMorphingToShow = false;
                PreviewMorphAnimator.this.isShowing = false;
                view.setAlpha(0.0f);
                view.setVisibility(4);
            }
        });
        this.morphAnimator.start();
        frameLayout.setVisibility(0);
        view.setVisibility(0);
        view2.setVisibility(4);
        view.animate().alpha(0.0f).setDuration(this.morphShowDuration / 2);
    }

    private void startReverseCircularReveal(final FrameLayout frameLayout, final PreviewBar previewBar, final View view, final View view2) {
        this.isMorphingToHide = true;
        float radius = getRadius(frameLayout);
        float height = frameLayout.getHeight() / 2.0f;
        long j = this.morphHideDuration;
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(frameLayout, getCenterX(frameLayout), getCenterY(frameLayout), radius, height);
        this.morphAnimator = animatorCreateCircularReveal;
        animatorCreateCircularReveal.setDuration(j);
        this.morphAnimator.setInterpolator(new AccelerateInterpolator());
        this.morphAnimator.setTarget(frameLayout);
        this.morphAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PreviewMorphAnimator.this.isMorphingToHide = false;
                PreviewMorphAnimator.this.startHideTranslation(frameLayout, previewBar, view, view2);
            }
        });
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration(this.morphHideDuration / 2).setInterpolator(new AccelerateInterpolator()).start();
        this.morphAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startHideTranslation(FrameLayout frameLayout, PreviewBar previewBar, View view, final View view2) {
        this.isMovingToHide = true;
        view.setVisibility(4);
        frameLayout.setVisibility(4);
        view2.setVisibility(0);
        animateMorphViewX(view2, getMorphStartX(previewBar, getOffset(previewBar)), this.hideTranslationDuration);
        view2.animate().y(getMorphStartY(previewBar)).scaleY(0.0f).scaleX(0.0f).setDuration(this.hideTranslationDuration).setInterpolator(new AccelerateInterpolator()).setListener(new AnimatorListenerAdapter() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PreviewMorphAnimator.this.isMovingToHide = false;
                PreviewMorphAnimator.this.isHiding = false;
                view2.setVisibility(4);
            }
        }).start();
    }

    private void animateMorphViewX(final View view, float f, long j) {
        ValueAnimator valueAnimator = this.translationAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
            this.translationAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(view.getX(), f);
        this.translationAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewMorphAnimator.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                view.setX(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.translationAnimator.setDuration(j);
        this.translationAnimator.setInterpolator(new AccelerateInterpolator());
        this.translationAnimator.start();
    }

    private void cancelPendingAnimations(FrameLayout frameLayout, View view, View view2) {
        Animator animator = this.morphAnimator;
        if (animator != null) {
            animator.removeAllListeners();
            this.morphAnimator.cancel();
            this.morphAnimator = null;
        }
        ValueAnimator valueAnimator = this.translationAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
            this.translationAnimator.cancel();
            this.translationAnimator = null;
        }
        frameLayout.animate().setListener(null);
        frameLayout.animate().cancel();
        view.animate().setListener(null);
        view.animate().cancel();
        view2.animate().setListener(null);
        view2.animate().cancel();
    }

    private View getOrCreateOverlayView(FrameLayout frameLayout) {
        View viewFindViewById = frameLayout.findViewById(R.id.previewSeekBarOverlayViewId);
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        View view = new View(frameLayout.getContext());
        view.setVisibility(4);
        view.setId(R.id.previewSeekBarOverlayViewId);
        frameLayout.addView(view, new FrameLayout.LayoutParams(-1, -1));
        return view;
    }

    private View getOrCreateMorphView(FrameLayout frameLayout, PreviewBar previewBar) {
        ViewGroup viewGroup = (ViewGroup) frameLayout.getParent();
        View viewFindViewById = viewGroup.findViewById(R.id.previewSeekBarMorphViewId);
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        View view = new View(frameLayout.getContext());
        view.setVisibility(4);
        view.setBackgroundResource(R.drawable.previewseekbar_morph);
        view.setId(R.id.previewSeekBarMorphViewId);
        viewGroup.addView(view, new ViewGroup.LayoutParams(previewBar.getThumbOffset(), previewBar.getThumbOffset()));
        return view;
    }

    private void tintViews(PreviewBar previewBar, View view, View view2) {
        int scrubberColor = previewBar.getScrubberColor();
        if (view.getBackgroundTintList() == null || view.getBackgroundTintList().getDefaultColor() != scrubberColor) {
            Drawable drawableWrap = DrawableCompat.wrap(view.getBackground());
            DrawableCompat.setTint(drawableWrap, scrubberColor);
            view.setBackground(drawableWrap);
            view2.setBackgroundColor(scrubberColor);
        }
    }

    private float getOffset(PreviewBar previewBar) {
        if (previewBar.getMax() == 0) {
            return 0.0f;
        }
        return previewBar.getProgress() / previewBar.getMax();
    }

    private float getMorphScale(FrameLayout frameLayout, View view) {
        return frameLayout.getHeight() / view.getLayoutParams().height;
    }

    private float getRadius(View view) {
        return (float) Math.hypot(view.getWidth() / 2.0f, view.getHeight() / 2.0f);
    }

    private int getCenterX(View view) {
        return view.getWidth() / 2;
    }

    private int getCenterY(View view) {
        return view.getHeight() / 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private float getMorphStartX(PreviewBar previewBar, float f) {
        float thumbOffset = previewBar.getThumbOffset();
        View view = (View) previewBar;
        float left = view.getLeft() + thumbOffset;
        return (left + (((view.getRight() - thumbOffset) - left) * f)) - (thumbOffset / 2.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private float getMorphStartY(PreviewBar previewBar) {
        return ((View) previewBar).getY() + previewBar.getThumbOffset();
    }

    private float getMorphEndX(FrameLayout frameLayout, PreviewBar previewBar) {
        return (frameLayout.getX() + (frameLayout.getWidth() / 2.0f)) - (previewBar.getThumbOffset() / 2.0f);
    }

    private float getMorphEndY(FrameLayout frameLayout, View view) {
        return ((int) (frameLayout.getY() + (frameLayout.getHeight() / 2.0f))) - (view.getHeight() / 2.0f);
    }
}

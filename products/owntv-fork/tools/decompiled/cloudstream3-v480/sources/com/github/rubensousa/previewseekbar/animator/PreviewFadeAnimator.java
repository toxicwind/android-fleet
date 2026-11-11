package com.github.rubensousa.previewseekbar.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.FrameLayout;
import com.github.rubensousa.previewseekbar.PreviewBar;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewFadeAnimator implements PreviewAnimator {
    private static final int FADE_DURATION = 350;
    private long hideDuration;
    private long showDuration;

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void move(FrameLayout frameLayout, PreviewBar previewBar) {
    }

    public PreviewFadeAnimator() {
        this(350L, 350L);
    }

    public PreviewFadeAnimator(long j, long j2) {
        this.showDuration = j;
        this.hideDuration = j2;
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void show(FrameLayout frameLayout, PreviewBar previewBar) {
        frameLayout.animate().setListener(null);
        frameLayout.animate().cancel();
        frameLayout.setAlpha(0.0f);
        frameLayout.setVisibility(0);
        frameLayout.animate().setDuration(this.showDuration).alpha(1.0f);
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void hide(final FrameLayout frameLayout, PreviewBar previewBar) {
        frameLayout.animate().setListener(null);
        frameLayout.animate().cancel();
        frameLayout.setVisibility(0);
        frameLayout.setAlpha(1.0f);
        frameLayout.animate().setDuration(this.hideDuration).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: com.github.rubensousa.previewseekbar.animator.PreviewFadeAnimator.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                frameLayout.setAlpha(1.0f);
                frameLayout.setVisibility(4);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                frameLayout.setAlpha(1.0f);
                frameLayout.setVisibility(4);
            }
        });
    }

    @Override // com.github.rubensousa.previewseekbar.animator.PreviewAnimator
    public void cancel(FrameLayout frameLayout, PreviewBar previewBar) {
        frameLayout.animate().setListener(null);
        frameLayout.animate().cancel();
    }
}

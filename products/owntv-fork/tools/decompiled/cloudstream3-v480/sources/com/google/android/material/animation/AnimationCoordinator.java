package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class AnimationCoordinator {
    private final List<Animator> durationAnimations = new ArrayList();
    private final List<DynamicAnimation<?>> dynamicAnimations = new ArrayList();
    private final List<Listener> listeners = new ArrayList();
    private int animationsRunning = 0;
    private boolean started = false;

    public interface Listener {
        void onAnimationsEnd();

        void onAnimationsStart();
    }

    public void addAnimator(Animator animator) {
        this.durationAnimations.add(animator);
    }

    public void addDynamicAnimation(DynamicAnimation<?> dynamicAnimation) {
        this.dynamicAnimations.add(dynamicAnimation);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void clear() {
        ArrayList arrayList = new ArrayList(this.durationAnimations);
        this.durationAnimations.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Animator) it.next()).end();
        }
        ArrayList<DynamicAnimation> arrayList2 = new ArrayList(this.dynamicAnimations);
        this.dynamicAnimations.clear();
        for (DynamicAnimation dynamicAnimation : arrayList2) {
            if (dynamicAnimation instanceof SpringAnimation) {
                SpringAnimation springAnimation = (SpringAnimation) dynamicAnimation;
                if (springAnimation.canSkipToEnd()) {
                    springAnimation.skipToEnd();
                } else {
                    springAnimation.cancel();
                }
            } else {
                dynamicAnimation.cancel();
            }
        }
        this.listeners.clear();
        this.animationsRunning = 0;
        this.started = false;
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAnimationsStart();
        }
        this.animationsRunning = this.dynamicAnimations.size();
        if (!this.durationAnimations.isEmpty()) {
            this.animationsRunning++;
        }
        if (this.animationsRunning == 0) {
            notifyAnimationsEnd();
            return;
        }
        DynamicAnimation.OnAnimationEndListener onAnimationEndListener = new DynamicAnimation.OnAnimationEndListener() { // from class: com.google.android.material.animation.AnimationCoordinator.1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                dynamicAnimation.removeEndListener(this);
                AnimationCoordinator.this.onAnimationFinished();
            }
        };
        for (DynamicAnimation<?> dynamicAnimation : this.dynamicAnimations) {
            dynamicAnimation.addEndListener(onAnimationEndListener);
            dynamicAnimation.start();
        }
        if (this.durationAnimations.isEmpty()) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, new ArrayList(this.durationAnimations));
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.animation.AnimationCoordinator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimationCoordinator.this.onAnimationFinished();
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimationFinished() {
        int i = this.animationsRunning - 1;
        this.animationsRunning = i;
        if (i == 0) {
            notifyAnimationsEnd();
        }
    }

    private void notifyAnimationsEnd() {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAnimationsEnd();
        }
        this.started = false;
    }
}

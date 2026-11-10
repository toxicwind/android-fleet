package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPropertyAnimatorCompat {
    public static final int LISTENER_TAG_ID = 2113929216;
    public WeakReference<View> mView;
    public Runnable mStartAction = null;
    public Runnable mEndAction = null;
    public int mOldLayerType = -1;

    static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
        public boolean mAnimEndCalled;
        public ViewPropertyAnimatorCompat mVpa;

        public ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat vpa) {
            this.mVpa = vpa;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view) {
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationCancel(view);
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        @SuppressLint({"WrongConstant"})
        public void onAnimationEnd(View view) {
            int i = this.mVpa.mOldLayerType;
            if (i > -1) {
                view.setLayerType(i, null);
                this.mVpa.mOldLayerType = -1;
            }
            int i2 = Build.VERSION.SDK_INT;
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
            Runnable runnable = viewPropertyAnimatorCompat.mEndAction;
            if (runnable != null) {
                viewPropertyAnimatorCompat.mEndAction = null;
                runnable.run();
            }
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationEnd(view);
            }
            this.mAnimEndCalled = true;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
            this.mAnimEndCalled = false;
            if (this.mVpa.mOldLayerType > -1) {
                view.setLayerType(2, null);
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
            Runnable runnable = viewPropertyAnimatorCompat.mStartAction;
            if (runnable != null) {
                viewPropertyAnimatorCompat.mStartAction = null;
                runnable.run();
            }
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(view);
            }
        }
    }

    public ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference<>(view);
    }

    private void setListenerInternal(final View view, final ViewPropertyAnimatorListener listener) {
        if (listener != null) {
            view.animate().setListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.ViewPropertyAnimatorCompat.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    listener.onAnimationCancel(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationEnd(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    listener.onAnimationStart(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public ViewPropertyAnimatorCompat alpha(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().alpha(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().alphaBy(value);
        }
        return this;
    }

    public void cancel() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long getDuration() {
        View view = this.mView.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public Interpolator getInterpolator() {
        View view = this.mView.get();
        if (view == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        return (Interpolator) view.animate().getInterpolator();
    }

    public long getStartDelay() {
        View view = this.mView.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0L;
    }

    public ViewPropertyAnimatorCompat rotation(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotation(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationYBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleYBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setDuration(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setInterpolator(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(final ViewPropertyAnimatorListener listener) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            setListenerInternal(view, listener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setStartDelay(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener listener) {
        final View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().setUpdateListener(listener != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.ViewPropertyAnimatorCompat.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    listener.onAnimationUpdate(view);
                }
            } : null);
        }
        return this;
    }

    public void start() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationX(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationYBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float value) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().translationZ(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().translationZBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().withEndAction(runnable);
        }
        return this;
    }

    @SuppressLint({"WrongConstant"})
    public ViewPropertyAnimatorCompat withLayer() {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().withLayer();
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().withStartAction(runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat x(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().x(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().xBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat y(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().y(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().yBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat z(float value) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().z(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float value) {
        View view = this.mView.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            view.animate().zBy(value);
        }
        return this;
    }
}

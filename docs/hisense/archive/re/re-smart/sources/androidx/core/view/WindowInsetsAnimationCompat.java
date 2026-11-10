package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.graphics.Insets;
import b.a.a.a.a;
import com.google.common.collect.LinkedHashMultimap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {
    public static final boolean DEBUG = false;
    public static final String TAG = "WindowInsetsAnimCompat";
    public Impl mImpl;

    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;
        public WindowInsets mDispachedInsets;
        public final int mDispatchMode;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface DispatchMode {
        }

        public Callback(int dispatchMode) {
            this.mDispatchMode = dispatchMode;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public void onEnd(@NonNull WindowInsetsAnimationCompat animation) {
        }

        public void onPrepare(@NonNull WindowInsetsAnimationCompat animation) {
        }

        @NonNull
        public abstract WindowInsetsCompat onProgress(@NonNull WindowInsetsCompat insets, @NonNull List<WindowInsetsAnimationCompat> runningAnimations);

        @NonNull
        public BoundsCompat onStart(@NonNull WindowInsetsAnimationCompat animation, @NonNull BoundsCompat bounds) {
            return bounds;
        }
    }

    private static class Impl {
        public float mAlpha;
        public final long mDurationMillis;
        public float mFraction;

        @Nullable
        public final Interpolator mInterpolator;
        public final int mTypeMask;

        public Impl(int typeMask, @Nullable Interpolator interpolator, long durationMillis) {
            this.mTypeMask = typeMask;
            this.mInterpolator = interpolator;
            this.mDurationMillis = durationMillis;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public long getDurationMillis() {
            return this.mDurationMillis;
        }

        public float getFraction() {
            return this.mFraction;
        }

        public float getInterpolatedFraction() {
            Interpolator interpolator = this.mInterpolator;
            return interpolator != null ? interpolator.getInterpolation(this.mFraction) : this.mFraction;
        }

        @Nullable
        public Interpolator getInterpolator() {
            return this.mInterpolator;
        }

        public int getTypeMask() {
            return this.mTypeMask;
        }

        public void setAlpha(float alpha) {
            this.mAlpha = alpha;
        }

        public void setFraction(float fraction) {
            this.mFraction = fraction;
        }
    }

    @RequiresApi(21)
    private static class Impl21 extends Impl {

        @RequiresApi(21)
        private static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
            public static final int COMPAT_ANIMATION_DURATION = 160;
            public final Callback mCallback;
            public WindowInsetsCompat mLastInsets;

            public Impl21OnApplyWindowInsetsListener(@NonNull View view, @NonNull Callback callback) {
                WindowInsetsCompat windowInsetsCompatBuild;
                this.mCallback = callback;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                if (rootWindowInsets != null) {
                    int i = Build.VERSION.SDK_INT;
                    windowInsetsCompatBuild = (i >= 30 ? new WindowInsetsCompat.BuilderImpl30(rootWindowInsets) : i >= 29 ? new WindowInsetsCompat.BuilderImpl29(rootWindowInsets) : new WindowInsetsCompat.BuilderImpl20(rootWindowInsets)).build();
                } else {
                    windowInsetsCompatBuild = null;
                }
                this.mLastInsets = windowInsetsCompatBuild;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(final View v, final WindowInsets insets) {
                if (!v.isLaidOut()) {
                    this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(insets, v);
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(insets, v);
                if (this.mLastInsets == null) {
                    this.mLastInsets = ViewCompat.getRootWindowInsets(v);
                }
                if (this.mLastInsets == null) {
                    this.mLastInsets = windowInsetsCompat;
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                Callback callback = Impl21.getCallback(v);
                if (callback != null && Objects.equals(callback.mDispachedInsets, insets)) {
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final int iBuildAnimationMask = Impl21.buildAnimationMask(windowInsetsCompat, this.mLastInsets);
                if (iBuildAnimationMask == 0) {
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final WindowInsetsCompat windowInsetsCompat2 = this.mLastInsets;
                final WindowInsetsAnimationCompat windowInsetsAnimationCompat = new WindowInsetsAnimationCompat(iBuildAnimationMask, new DecelerateInterpolator(), 160L);
                windowInsetsAnimationCompat.setFraction(0.0f);
                final ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(windowInsetsAnimationCompat.getDurationMillis());
                final BoundsCompat boundsCompatComputeAnimationBounds = Impl21.computeAnimationBounds(windowInsetsCompat, windowInsetsCompat2, iBuildAnimationMask);
                Impl21.dispatchOnPrepare(v, windowInsetsAnimationCompat, insets, false);
                duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator animator) {
                        windowInsetsAnimationCompat.setFraction(animator.getAnimatedFraction());
                        Impl21.dispatchOnProgress(v, Impl21.interpolateInsets(windowInsetsCompat, windowInsetsCompat2, windowInsetsAnimationCompat.getInterpolatedFraction(), iBuildAnimationMask), Collections.singletonList(windowInsetsAnimationCompat));
                    }
                });
                duration.addListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        windowInsetsAnimationCompat.setFraction(1.0f);
                        Impl21.dispatchOnEnd(v, windowInsetsAnimationCompat);
                    }
                });
                OneShotPreDrawListener.add(v, new Runnable() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Impl21.dispatchOnStart(v, windowInsetsAnimationCompat, boundsCompatComputeAnimationBounds);
                        duration.start();
                    }
                });
                this.mLastInsets = windowInsetsCompat;
                return Impl21.forwardToViewIfNeeded(v, insets);
            }
        }

        public Impl21(int typeMask, @Nullable Interpolator interpolator, long durationMillis) {
            super(typeMask, interpolator, durationMillis);
        }

        @SuppressLint({"WrongConstant"})
        public static int buildAnimationMask(@NonNull WindowInsetsCompat targetInsets, @NonNull WindowInsetsCompat currentInsets) {
            int i = 0;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if (!targetInsets.getInsets(i2).equals(currentInsets.getInsets(i2))) {
                    i |= i2;
                }
            }
            return i;
        }

        @NonNull
        public static BoundsCompat computeAnimationBounds(@NonNull WindowInsetsCompat targetInsets, @NonNull WindowInsetsCompat startingInsets, int mask) {
            Insets insets = targetInsets.getInsets(mask);
            Insets insets2 = startingInsets.getInsets(mask);
            return new BoundsCompat(Insets.of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom)), Insets.of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom)));
        }

        @NonNull
        public static View.OnApplyWindowInsetsListener createProxyListener(@NonNull View view, @NonNull final Callback callback) {
            return new Impl21OnApplyWindowInsetsListener(view, callback);
        }

        public static void dispatchOnEnd(@NonNull View v, @NonNull WindowInsetsAnimationCompat anim) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.onEnd(anim);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnEnd(viewGroup.getChildAt(i), anim);
                }
            }
        }

        public static void dispatchOnPrepare(View v, WindowInsetsAnimationCompat anim, WindowInsets insets, boolean stopDispatch) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.mDispachedInsets = insets;
                if (!stopDispatch) {
                    callback.onPrepare(anim);
                    stopDispatch = callback.getDispatchMode() == 0;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnPrepare(viewGroup.getChildAt(i), anim, insets, stopDispatch);
                }
            }
        }

        public static void dispatchOnProgress(@NonNull View v, @NonNull WindowInsetsCompat interpolateInsets, @NonNull List<WindowInsetsAnimationCompat> runningAnimations) {
            Callback callback = getCallback(v);
            if (callback != null) {
                interpolateInsets = callback.onProgress(interpolateInsets, runningAnimations);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnProgress(viewGroup.getChildAt(i), interpolateInsets, runningAnimations);
                }
            }
        }

        public static void dispatchOnStart(View v, WindowInsetsAnimationCompat anim, BoundsCompat animationBounds) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.onStart(anim, animationBounds);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnStart(viewGroup.getChildAt(i), anim, animationBounds);
                }
            }
        }

        @NonNull
        public static WindowInsets forwardToViewIfNeeded(@NonNull View v, @NonNull WindowInsets insets) {
            return v.getTag(R.id.tag_on_apply_window_listener) != null ? insets : v.onApplyWindowInsets(insets);
        }

        @Nullable
        public static Callback getCallback(View child) {
            Object tag = child.getTag(R.id.tag_window_insets_animation_callback);
            if (tag instanceof Impl21OnApplyWindowInsetsListener) {
                return ((Impl21OnApplyWindowInsetsListener) tag).mCallback;
            }
            return null;
        }

        @SuppressLint({"WrongConstant"})
        public static WindowInsetsCompat interpolateInsets(WindowInsetsCompat target, WindowInsetsCompat starting, float fraction, int typeMask) {
            int i = Build.VERSION.SDK_INT;
            WindowInsetsCompat.BuilderImpl builderImpl30 = i >= 30 ? new WindowInsetsCompat.BuilderImpl30(target) : i >= 29 ? new WindowInsetsCompat.BuilderImpl29(target) : new WindowInsetsCompat.BuilderImpl20(target);
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((typeMask & i2) == 0) {
                    builderImpl30.setInsets(i2, target.getInsets(i2));
                } else {
                    Insets insets = target.getInsets(i2);
                    Insets insets2 = starting.getInsets(i2);
                    float f2 = 1.0f - fraction;
                    builderImpl30.setInsets(i2, WindowInsetsCompat.insetInsets(insets, (int) (((double) ((insets.left - insets2.left) * f2)) + 0.5d), (int) (((double) ((insets.top - insets2.top) * f2)) + 0.5d), (int) (((double) ((insets.right - insets2.right) * f2)) + 0.5d), (int) (((double) ((insets.bottom - insets2.bottom) * f2)) + 0.5d)));
                }
            }
            return builderImpl30.build();
        }

        public static void setCallback(@NonNull final View view, @Nullable final Callback callback) {
            Object tag = view.getTag(R.id.tag_on_apply_window_listener);
            if (callback == null) {
                view.setTag(R.id.tag_window_insets_animation_callback, null);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(null);
                    return;
                }
                return;
            }
            View.OnApplyWindowInsetsListener impl21OnApplyWindowInsetsListener = new Impl21OnApplyWindowInsetsListener(view, callback);
            view.setTag(R.id.tag_window_insets_animation_callback, impl21OnApplyWindowInsetsListener);
            if (tag == null) {
                view.setOnApplyWindowInsetsListener(impl21OnApplyWindowInsetsListener);
            }
        }
    }

    public WindowInsetsAnimationCompat(int typeMask, @Nullable Interpolator interpolator, long durationMillis) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(typeMask, interpolator, durationMillis);
        } else {
            this.mImpl = new Impl21(typeMask, interpolator, durationMillis);
        }
    }

    public static void setCallback(@NonNull View view, @Nullable Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            Impl30.setCallback(view, callback);
        } else {
            Impl21.setCallback(view, callback);
        }
    }

    @RequiresApi(30)
    public static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)
    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public long getDurationMillis() {
        return this.mImpl.getDurationMillis();
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)
    public float getFraction() {
        return this.mImpl.getFraction();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    @Nullable
    public Interpolator getInterpolator() {
        return this.mImpl.getInterpolator();
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public void setAlpha(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float alpha) {
        this.mImpl.setAlpha(alpha);
    }

    public void setFraction(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float fraction) {
        this.mImpl.setFraction(fraction);
    }

    @RequiresApi(30)
    private static class Impl30 extends Impl {

        @NonNull
        public final WindowInsetsAnimation mWrapped;

        @RequiresApi(30)
        private static class ProxyCallback extends WindowInsetsAnimation.Callback {
            public final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> mAnimations;
            public final Callback mCompat;
            public List<WindowInsetsAnimationCompat> mRORunningAnimations;
            public ArrayList<WindowInsetsAnimationCompat> mTmpRunningAnimations;

            public ProxyCallback(@NonNull final Callback compat) {
                super(compat.getDispatchMode());
                this.mAnimations = new HashMap<>();
                this.mCompat = compat;
            }

            @NonNull
            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(@NonNull WindowInsetsAnimation animation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = this.mAnimations.get(animation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat windowInsetsAnimationCompat2 = new WindowInsetsAnimationCompat(animation);
                this.mAnimations.put(animation, windowInsetsAnimationCompat2);
                return windowInsetsAnimationCompat2;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(@NonNull WindowInsetsAnimation animation) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(animation));
                this.mAnimations.remove(animation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(@NonNull WindowInsetsAnimation animation) {
                this.mCompat.onPrepare(getWindowInsetsAnimationCompat(animation));
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsets onProgress(@NonNull WindowInsets insets, @NonNull List<WindowInsetsAnimation> runningAnimations) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.mTmpRunningAnimations;
                if (arrayList == null) {
                    this.mTmpRunningAnimations = new ArrayList<>(runningAnimations.size());
                    this.mRORunningAnimations = Collections.unmodifiableList(this.mTmpRunningAnimations);
                } else {
                    arrayList.clear();
                }
                for (int size = runningAnimations.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = runningAnimations.get(size);
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat = getWindowInsetsAnimationCompat(windowInsetsAnimation);
                    windowInsetsAnimationCompat.setFraction(windowInsetsAnimation.getFraction());
                    this.mTmpRunningAnimations.add(windowInsetsAnimationCompat);
                }
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(insets), this.mRORunningAnimations).toWindowInsets();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsetsAnimation.Bounds onStart(@NonNull WindowInsetsAnimation animation, @NonNull WindowInsetsAnimation.Bounds bounds) {
                return this.mCompat.onStart(getWindowInsetsAnimationCompat(animation), new BoundsCompat(bounds)).toBounds();
            }
        }

        public Impl30(@NonNull WindowInsetsAnimation wrapped) {
            super(0, null, 0L);
            this.mWrapped = wrapped;
        }

        @NonNull
        public static WindowInsetsAnimation.Bounds createPlatformBounds(@NonNull BoundsCompat bounds) {
            return new WindowInsetsAnimation.Bounds(bounds.getLowerBound().toPlatformInsets(), bounds.getUpperBound().toPlatformInsets());
        }

        @NonNull
        public static Insets getHigherBounds(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }

        @NonNull
        public static Insets getLowerBounds(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static void setCallback(@NonNull View view, @Nullable Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new ProxyCallback(callback) : null);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public long getDurationMillis() {
            return this.mWrapped.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getFraction() {
            return this.mWrapped.getFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        @Nullable
        public Interpolator getInterpolator() {
            return this.mWrapped.getInterpolator();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void setFraction(float fraction) {
            this.mWrapped.setFraction(fraction);
        }

        public Impl30(int typeMask, Interpolator interpolator, long durationMillis) {
            WindowInsetsAnimation windowInsetsAnimation = new WindowInsetsAnimation(typeMask, interpolator, durationMillis);
            super(0, null, 0L);
            this.mWrapped = windowInsetsAnimation;
        }
    }

    public static final class BoundsCompat {
        public final Insets mLowerBound;
        public final Insets mUpperBound;

        public BoundsCompat(@NonNull Insets lowerBound, @NonNull Insets upperBound) {
            this.mLowerBound = lowerBound;
            this.mUpperBound = upperBound;
        }

        @NonNull
        @RequiresApi(30)
        public static BoundsCompat toBoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        @NonNull
        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        @NonNull
        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        @NonNull
        public BoundsCompat inset(@NonNull Insets insets) {
            return new BoundsCompat(WindowInsetsCompat.insetInsets(this.mLowerBound, insets.left, insets.top, insets.right, insets.bottom), WindowInsetsCompat.insetInsets(this.mUpperBound, insets.left, insets.top, insets.right, insets.bottom));
        }

        @NonNull
        @RequiresApi(30)
        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public String toString() {
            StringBuilder sbA = a.a("Bounds{lower=");
            sbA.append(this.mLowerBound);
            sbA.append(" upper=");
            return a.a(sbA, this.mUpperBound, "}");
        }

        @RequiresApi(30)
        public BoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            this.mLowerBound = Insets.toCompatInsets(bounds.getLowerBound());
            this.mUpperBound = Insets.toCompatInsets(bounds.getUpperBound());
        }
    }

    @RequiresApi(30)
    public WindowInsetsAnimationCompat(@NonNull WindowInsetsAnimation animation) {
        this(0, null, 0L);
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(animation);
        }
    }
}

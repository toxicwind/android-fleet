package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    public static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    public static final int DEFAULT_EDGE_TYPE = 1;
    public static final float DEFAULT_MAXIMUM_EDGE = Float.MAX_VALUE;
    public static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    public static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    public static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    public static final int DEFAULT_RAMP_UP_DURATION = 500;
    public static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    public static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    public static final int HORIZONTAL = 0;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    public static final int VERTICAL = 1;
    public int mActivationDelay;
    public boolean mAlreadyDelayed;
    public boolean mAnimating;
    public int mEdgeType;
    public boolean mEnabled;
    public boolean mExclusive;
    public boolean mNeedsCancel;
    public boolean mNeedsReset;
    public Runnable mRunnable;
    public final View mTarget;
    public final ClampedScroller mScroller = new ClampedScroller();
    public final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    public float[] mRelativeEdges = {0.0f, 0.0f};
    public float[] mMaximumEdges = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] mRelativeVelocity = {0.0f, 0.0f};
    public float[] mMinimumVelocity = {0.0f, 0.0f};
    public float[] mMaximumVelocity = {Float.MAX_VALUE, Float.MAX_VALUE};

    private static class ClampedScroller {
        public int mEffectiveRampDown;
        public int mRampDownDuration;
        public int mRampUpDuration;
        public float mStopValue;
        public float mTargetVelocityX;
        public float mTargetVelocityY;
        public long mStartTime = Long.MIN_VALUE;
        public long mStopTime = -1;
        public long mDeltaTime = 0;
        public int mDeltaX = 0;
        public int mDeltaY = 0;

        private float getValueAt(long currentTime) {
            if (currentTime < this.mStartTime) {
                return 0.0f;
            }
            long j = this.mStopTime;
            if (j < 0 || currentTime < j) {
                return AutoScrollHelper.constrain((currentTime - this.mStartTime) / this.mRampUpDuration, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.mStopValue;
            return (AutoScrollHelper.constrain((currentTime - j) / this.mEffectiveRampDown, 0.0f, 1.0f) * f2) + (1.0f - f2);
        }

        private float interpolateValue(float value) {
            return (value * 4.0f) + ((-4.0f) * value * value);
        }

        public void computeScrollDelta() {
            if (this.mDeltaTime == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fInterpolateValue = interpolateValue(getValueAt(jCurrentAnimationTimeMillis));
            long j = jCurrentAnimationTimeMillis - this.mDeltaTime;
            this.mDeltaTime = jCurrentAnimationTimeMillis;
            float f2 = j * fInterpolateValue;
            this.mDeltaX = (int) (this.mTargetVelocityX * f2);
            this.mDeltaY = (int) (f2 * this.mTargetVelocityY);
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }

        public int getHorizontalDirection() {
            float f2 = this.mTargetVelocityX;
            return (int) (f2 / Math.abs(f2));
        }

        public int getVerticalDirection() {
            float f2 = this.mTargetVelocityY;
            return (int) (f2 / Math.abs(f2));
        }

        public boolean isFinished() {
            return this.mStopTime > 0 && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + ((long) this.mEffectiveRampDown);
        }

        public void requestStop() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.constrain((int) (jCurrentAnimationTimeMillis - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = getValueAt(jCurrentAnimationTimeMillis);
            this.mStopTime = jCurrentAnimationTimeMillis;
        }

        public void setRampDownDuration(int durationMillis) {
            this.mRampDownDuration = durationMillis;
        }

        public void setRampUpDuration(int durationMillis) {
            this.mRampUpDuration = durationMillis;
        }

        public void setTargetVelocity(float x, float y) {
            this.mTargetVelocityX = x;
            this.mTargetVelocityY = y;
        }

        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1L;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }

    private class ScrollAnimationRunnable implements Runnable {
        public ScrollAnimationRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
            if (autoScrollHelper.mAnimating) {
                if (autoScrollHelper.mNeedsReset) {
                    autoScrollHelper.mNeedsReset = false;
                    autoScrollHelper.mScroller.start();
                }
                ClampedScroller clampedScroller = AutoScrollHelper.this.mScroller;
                if (clampedScroller.isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
                    AutoScrollHelper.this.mAnimating = false;
                    return;
                }
                AutoScrollHelper autoScrollHelper2 = AutoScrollHelper.this;
                if (autoScrollHelper2.mNeedsCancel) {
                    autoScrollHelper2.mNeedsCancel = false;
                    autoScrollHelper2.cancelTargetTouch();
                }
                clampedScroller.computeScrollDelta();
                AutoScrollHelper.this.scrollTargetBy(clampedScroller.getDeltaX(), clampedScroller.getDeltaY());
                ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
            }
        }
    }

    public AutoScrollHelper(@NonNull View target) {
        this.mTarget = target;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        setMaximumVelocity(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        setMinimumVelocity(f4, f4);
        setEdgeType(1);
        setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    private float computeTargetVelocity(int direction, float coordinate, float srcSize, float dstSize) {
        float edgeValue = getEdgeValue(this.mRelativeEdges[direction], srcSize, this.mMaximumEdges[direction], coordinate);
        if (edgeValue == 0.0f) {
            return 0.0f;
        }
        float f2 = this.mRelativeVelocity[direction];
        float f3 = this.mMinimumVelocity[direction];
        float f4 = this.mMaximumVelocity[direction];
        float f5 = f2 * dstSize;
        return edgeValue > 0.0f ? constrain(edgeValue * f5, f3, f4) : -constrain((-edgeValue) * f5, f3, f4);
    }

    public static float constrain(float value, float min, float max) {
        if (value > max) {
            return max;
        }
        return value < min ? min : value;
    }

    public static int constrain(int value, int min, int max) {
        if (value > max) {
            return max;
        }
        return value < min ? min : value;
    }

    private float constrainEdgeValue(float current, float leading) {
        if (leading == 0.0f) {
            return 0.0f;
        }
        int i = this.mEdgeType;
        if (i == 0 || i == 1) {
            if (current < leading) {
                if (current >= 0.0f) {
                    return 1.0f - (current / leading);
                }
                if (this.mAnimating && this.mEdgeType == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && current < 0.0f) {
            return current / (-leading);
        }
        return 0.0f;
    }

    private float getEdgeValue(float relativeValue, float size, float maxValue, float current) {
        float interpolation;
        float fConstrain = constrain(relativeValue * size, 0.0f, maxValue);
        float fConstrainEdgeValue = constrainEdgeValue(size - current, fConstrain) - constrainEdgeValue(current, fConstrain);
        if (fConstrainEdgeValue < 0.0f) {
            interpolation = -this.mEdgeInterpolator.getInterpolation(-fConstrainEdgeValue);
        } else {
            if (fConstrainEdgeValue <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.mEdgeInterpolator.getInterpolation(fConstrainEdgeValue);
        }
        return constrain(interpolation, -1.0f, 1.0f);
    }

    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
        } else {
            this.mScroller.requestStop();
        }
    }

    private void startAnimating() {
        int i;
        if (this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable();
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (this.mAlreadyDelayed || (i = this.mActivationDelay) <= 0) {
            this.mRunnable.run();
        } else {
            ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, i);
        }
        this.mAlreadyDelayed = true;
    }

    public abstract boolean canTargetScrollHorizontally(int direction);

    public abstract boolean canTargetScrollVertically(int direction);

    public void cancelTargetTouch() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.mTarget.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isExclusive() {
        return this.mExclusive;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (!this.mEnabled) {
            return false;
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                requestStop();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    requestStop();
                }
            }
            return this.mExclusive && this.mAnimating;
        }
        this.mNeedsCancel = true;
        this.mAlreadyDelayed = false;
        this.mScroller.setTargetVelocity(computeTargetVelocity(0, event.getX(), v.getWidth(), this.mTarget.getWidth()), computeTargetVelocity(1, event.getY(), v.getHeight(), this.mTarget.getHeight()));
        if (!this.mAnimating && shouldAnimate()) {
            startAnimating();
        }
        if (this.mExclusive) {
            return false;
        }
    }

    public abstract void scrollTargetBy(int deltaX, int deltaY);

    @NonNull
    public AutoScrollHelper setActivationDelay(int delayMillis) {
        this.mActivationDelay = delayMillis;
        return this;
    }

    @NonNull
    public AutoScrollHelper setEdgeType(int type) {
        this.mEdgeType = type;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean enabled) {
        if (this.mEnabled && !enabled) {
            requestStop();
        }
        this.mEnabled = enabled;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean exclusive) {
        this.mExclusive = exclusive;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumEdges(float horizontalMax, float verticalMax) {
        float[] fArr = this.mMaximumEdges;
        fArr[0] = horizontalMax;
        fArr[1] = verticalMax;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumVelocity(float horizontalMax, float verticalMax) {
        float[] fArr = this.mMaximumVelocity;
        fArr[0] = horizontalMax / 1000.0f;
        fArr[1] = verticalMax / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMinimumVelocity(float horizontalMin, float verticalMin) {
        float[] fArr = this.mMinimumVelocity;
        fArr[0] = horizontalMin / 1000.0f;
        fArr[1] = verticalMin / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampDownDuration(int durationMillis) {
        this.mScroller.setRampDownDuration(durationMillis);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampUpDuration(int durationMillis) {
        this.mScroller.setRampUpDuration(durationMillis);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeEdges(float horizontal, float vertical) {
        float[] fArr = this.mRelativeEdges;
        fArr[0] = horizontal;
        fArr[1] = vertical;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeVelocity(float horizontal, float vertical) {
        float[] fArr = this.mRelativeVelocity;
        fArr[0] = horizontal / 1000.0f;
        fArr[1] = vertical / 1000.0f;
        return this;
    }

    public boolean shouldAnimate() {
        ClampedScroller clampedScroller = this.mScroller;
        int verticalDirection = clampedScroller.getVerticalDirection();
        int horizontalDirection = clampedScroller.getHorizontalDirection();
        return (verticalDirection != 0 && canTargetScrollVertically(verticalDirection)) || (horizontalDirection != 0 && canTargetScrollHorizontally(horizontalDirection));
    }
}

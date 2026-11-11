package com.facebook.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes5.dex */
public class Shimmer {
    private static final int COMPONENT_COUNT = 4;
    long repeatDelay;
    final float[] positions = new float[4];
    final int[] colors = new int[4];
    final RectF bounds = new RectF();
    int direction = 0;
    int highlightColor = -1;
    int baseColor = 1291845631;
    int shape = 0;
    int fixedWidth = 0;
    int fixedHeight = 0;
    float widthRatio = 1.0f;
    float heightRatio = 1.0f;
    float intensity = 0.0f;
    float dropoff = 0.5f;
    float tilt = 20.0f;
    boolean clipToChildren = true;
    boolean autoStart = true;
    boolean alphaShimmer = true;
    int repeatCount = -1;
    int repeatMode = 1;
    long animationDuration = 1000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        public static final int BOTTOM_TO_TOP = 3;
        public static final int LEFT_TO_RIGHT = 0;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int TOP_TO_BOTTOM = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
        public static final int LINEAR = 0;
        public static final int RADIAL = 1;
    }

    Shimmer() {
    }

    int width(int i) {
        int i2 = this.fixedWidth;
        return i2 > 0 ? i2 : Math.round(this.widthRatio * i);
    }

    int height(int i) {
        int i2 = this.fixedHeight;
        return i2 > 0 ? i2 : Math.round(this.heightRatio * i);
    }

    void updateColors() {
        int i = this.shape;
        int[] iArr = this.colors;
        if (i != 1) {
            int i2 = this.baseColor;
            iArr[0] = i2;
            int i3 = this.highlightColor;
            iArr[1] = i3;
            iArr[2] = i3;
            iArr[3] = i2;
            return;
        }
        int i4 = this.highlightColor;
        iArr[0] = i4;
        iArr[1] = i4;
        int i5 = this.baseColor;
        iArr[2] = i5;
        iArr[3] = i5;
    }

    void updatePositions() {
        int i = this.shape;
        float[] fArr = this.positions;
        if (i != 1) {
            fArr[0] = Math.max(((1.0f - this.intensity) - this.dropoff) / 2.0f, 0.0f);
            this.positions[1] = Math.max(((1.0f - this.intensity) - 0.001f) / 2.0f, 0.0f);
            this.positions[2] = Math.min(((this.intensity + 1.0f) + 0.001f) / 2.0f, 1.0f);
            this.positions[3] = Math.min(((this.intensity + 1.0f) + this.dropoff) / 2.0f, 1.0f);
            return;
        }
        fArr[0] = 0.0f;
        fArr[1] = Math.min(this.intensity, 1.0f);
        this.positions[2] = Math.min(this.intensity + this.dropoff, 1.0f);
        this.positions[3] = 1.0f;
    }

    void updateBounds(int i, int i2) {
        double dMax = Math.max(i, i2);
        int iRound = Math.round(((float) ((dMax / Math.sin(1.5707963267948966d - Math.toRadians(this.tilt % 90.0f))) - dMax)) / 2.0f) * 3;
        float f = -iRound;
        this.bounds.set(f, f, width(i) + iRound, height(i2) + iRound);
    }

    public static abstract class Builder<T extends Builder<T>> {
        final Shimmer mShimmer = new Shimmer();

        protected abstract T getThis();

        public T consumeAttributes(Context context, AttributeSet attributeSet) {
            return (T) consumeAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0));
        }

        T consumeAttributes(TypedArray typedArray) {
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_clip_to_children)) {
                setClipToChildren(typedArray.getBoolean(R.styleable.ShimmerFrameLayout_shimmer_clip_to_children, this.mShimmer.clipToChildren));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_auto_start)) {
                setAutoStart(typedArray.getBoolean(R.styleable.ShimmerFrameLayout_shimmer_auto_start, this.mShimmer.autoStart));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_base_alpha)) {
                setBaseAlpha(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_base_alpha, 0.3f));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha)) {
                setHighlightAlpha(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha, 1.0f));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_duration)) {
                setDuration(typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_duration, (int) this.mShimmer.animationDuration));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_count)) {
                setRepeatCount(typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_repeat_count, this.mShimmer.repeatCount));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_delay)) {
                setRepeatDelay(typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_repeat_delay, (int) this.mShimmer.repeatDelay));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_mode)) {
                setRepeatMode(typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_repeat_mode, this.mShimmer.repeatMode));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_direction)) {
                int i = typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_direction, this.mShimmer.direction);
                if (i == 1) {
                    setDirection(1);
                } else if (i == 2) {
                    setDirection(2);
                } else if (i != 3) {
                    setDirection(0);
                } else {
                    setDirection(3);
                }
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_shape)) {
                if (typedArray.getInt(R.styleable.ShimmerFrameLayout_shimmer_shape, this.mShimmer.shape) != 1) {
                    setShape(0);
                } else {
                    setShape(1);
                }
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_dropoff)) {
                setDropoff(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_dropoff, this.mShimmer.dropoff));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_fixed_width)) {
                setFixedWidth(typedArray.getDimensionPixelSize(R.styleable.ShimmerFrameLayout_shimmer_fixed_width, this.mShimmer.fixedWidth));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_fixed_height)) {
                setFixedHeight(typedArray.getDimensionPixelSize(R.styleable.ShimmerFrameLayout_shimmer_fixed_height, this.mShimmer.fixedHeight));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_intensity)) {
                setIntensity(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_intensity, this.mShimmer.intensity));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_width_ratio)) {
                setWidthRatio(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_width_ratio, this.mShimmer.widthRatio));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_height_ratio)) {
                setHeightRatio(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_height_ratio, this.mShimmer.heightRatio));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_tilt)) {
                setTilt(typedArray.getFloat(R.styleable.ShimmerFrameLayout_shimmer_tilt, this.mShimmer.tilt));
            }
            return (T) getThis();
        }

        public T copyFrom(Shimmer shimmer) {
            setDirection(shimmer.direction);
            setShape(shimmer.shape);
            setFixedWidth(shimmer.fixedWidth);
            setFixedHeight(shimmer.fixedHeight);
            setWidthRatio(shimmer.widthRatio);
            setHeightRatio(shimmer.heightRatio);
            setIntensity(shimmer.intensity);
            setDropoff(shimmer.dropoff);
            setTilt(shimmer.tilt);
            setClipToChildren(shimmer.clipToChildren);
            setAutoStart(shimmer.autoStart);
            setRepeatCount(shimmer.repeatCount);
            setRepeatMode(shimmer.repeatMode);
            setRepeatDelay(shimmer.repeatDelay);
            setDuration(shimmer.animationDuration);
            this.mShimmer.baseColor = shimmer.baseColor;
            this.mShimmer.highlightColor = shimmer.highlightColor;
            return (T) getThis();
        }

        public T setDirection(int i) {
            this.mShimmer.direction = i;
            return (T) getThis();
        }

        public T setShape(int i) {
            this.mShimmer.shape = i;
            return (T) getThis();
        }

        public T setFixedWidth(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid width: " + i);
            }
            this.mShimmer.fixedWidth = i;
            return (T) getThis();
        }

        public T setFixedHeight(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid height: " + i);
            }
            this.mShimmer.fixedHeight = i;
            return (T) getThis();
        }

        public T setWidthRatio(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid width ratio: " + f);
            }
            this.mShimmer.widthRatio = f;
            return (T) getThis();
        }

        public T setHeightRatio(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid height ratio: " + f);
            }
            this.mShimmer.heightRatio = f;
            return (T) getThis();
        }

        public T setIntensity(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid intensity value: " + f);
            }
            this.mShimmer.intensity = f;
            return (T) getThis();
        }

        public T setDropoff(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("Given invalid dropoff value: " + f);
            }
            this.mShimmer.dropoff = f;
            return (T) getThis();
        }

        public T setTilt(float f) {
            this.mShimmer.tilt = f;
            return (T) getThis();
        }

        public T setBaseAlpha(float f) {
            int iClamp = (int) (clamp(0.0f, 1.0f, f) * 255.0f);
            Shimmer shimmer = this.mShimmer;
            shimmer.baseColor = (iClamp << 24) | (shimmer.baseColor & ViewCompat.MEASURED_SIZE_MASK);
            return (T) getThis();
        }

        public T setHighlightAlpha(float f) {
            int iClamp = (int) (clamp(0.0f, 1.0f, f) * 255.0f);
            Shimmer shimmer = this.mShimmer;
            shimmer.highlightColor = (iClamp << 24) | (shimmer.highlightColor & ViewCompat.MEASURED_SIZE_MASK);
            return (T) getThis();
        }

        public T setClipToChildren(boolean z) {
            this.mShimmer.clipToChildren = z;
            return (T) getThis();
        }

        public T setAutoStart(boolean z) {
            this.mShimmer.autoStart = z;
            return (T) getThis();
        }

        public T setRepeatCount(int i) {
            this.mShimmer.repeatCount = i;
            return (T) getThis();
        }

        public T setRepeatMode(int i) {
            this.mShimmer.repeatMode = i;
            return (T) getThis();
        }

        public T setRepeatDelay(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative repeat delay: " + j);
            }
            this.mShimmer.repeatDelay = j;
            return (T) getThis();
        }

        public T setDuration(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative duration: " + j);
            }
            this.mShimmer.animationDuration = j;
            return (T) getThis();
        }

        public Shimmer build() {
            this.mShimmer.updateColors();
            this.mShimmer.updatePositions();
            return this.mShimmer;
        }

        private static float clamp(float f, float f2, float f3) {
            return Math.min(f2, Math.max(f, f3));
        }
    }

    public static class AlphaHighlightBuilder extends Builder<AlphaHighlightBuilder> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.shimmer.Shimmer.Builder
        public AlphaHighlightBuilder getThis() {
            return this;
        }

        public AlphaHighlightBuilder() {
            this.mShimmer.alphaShimmer = true;
        }
    }

    public static class ColorHighlightBuilder extends Builder<ColorHighlightBuilder> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.shimmer.Shimmer.Builder
        public ColorHighlightBuilder getThis() {
            return this;
        }

        public ColorHighlightBuilder() {
            this.mShimmer.alphaShimmer = false;
        }

        public ColorHighlightBuilder setHighlightColor(int i) {
            this.mShimmer.highlightColor = i;
            return getThis();
        }

        public ColorHighlightBuilder setBaseColor(int i) {
            this.mShimmer.baseColor = (i & ViewCompat.MEASURED_SIZE_MASK) | (this.mShimmer.baseColor & (-16777216));
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.facebook.shimmer.Shimmer.Builder
        public ColorHighlightBuilder consumeAttributes(TypedArray typedArray) {
            super.consumeAttributes(typedArray);
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_base_color)) {
                setBaseColor(typedArray.getColor(R.styleable.ShimmerFrameLayout_shimmer_base_color, this.mShimmer.baseColor));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_highlight_color)) {
                setHighlightColor(typedArray.getColor(R.styleable.ShimmerFrameLayout_shimmer_highlight_color, this.mShimmer.highlightColor));
            }
            return getThis();
        }
    }
}

package com.lagradost.cloudstream3.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PercentageCropImageView.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bB%\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\u000bJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0014J\u0012\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\nH\u0016J\u0006\u0010%\u001a\u00020\u0019J\u001a\u0010&\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR$\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/utils/PercentageCropImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mCropYCenterOffsetPct", "", "Ljava/lang/Float;", "mCropXCenterOffsetPct", "cropYCenterOffsetPct", "getCropYCenterOffsetPct", "()F", "setCropYCenterOffsetPct", "(F)V", "cropXCenterOffsetPct", "getCropXCenterOffsetPct", "setCropXCenterOffsetPct", "myConfigureBounds", "", "setFrame", "", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "setImageDrawable", "d", "Landroid/graphics/drawable/Drawable;", "setImageResource", "resId", "redraw", "initAttrs", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PercentageCropImageView extends AppCompatImageView {
    private Float mCropXCenterOffsetPct;
    private Float mCropYCenterOffsetPct;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PercentageCropImageView(Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PercentageCropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNull(context);
        initAttrs(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PercentageCropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNull(context);
        initAttrs(context, attributeSet);
    }

    public final float getCropYCenterOffsetPct() {
        Float f = this.mCropYCenterOffsetPct;
        Intrinsics.checkNotNull(f);
        return f.floatValue();
    }

    public final void setCropYCenterOffsetPct(float f) {
        if (f > 1.0d) {
            throw new IllegalArgumentException("Value too large: Must be <= 1.0".toString());
        }
        this.mCropYCenterOffsetPct = Float.valueOf(f);
    }

    public final float getCropXCenterOffsetPct() {
        Float f = this.mCropXCenterOffsetPct;
        Intrinsics.checkNotNull(f);
        return f.floatValue();
    }

    public final void setCropXCenterOffsetPct(float f) {
        if (f > 1.0d) {
            throw new IllegalArgumentException("Value too large: Must be <= 1.0".toString());
        }
        this.mCropXCenterOffsetPct = Float.valueOf(f);
    }

    private final void myConfigureBounds() {
        Drawable drawable;
        float fFloatValue;
        float f;
        float f2;
        float fFloatValue2;
        if (getScaleType() != ImageView.ScaleType.MATRIX || (drawable = getDrawable()) == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Matrix matrix = new Matrix();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        float f3 = 0.0f;
        if (intrinsicWidth * height > width * intrinsicHeight) {
            Float f4 = this.mCropXCenterOffsetPct;
            if (f4 != null) {
                Intrinsics.checkNotNull(f4);
                fFloatValue2 = f4.floatValue();
            } else {
                fFloatValue2 = 0.5f;
            }
            f2 = height / intrinsicHeight;
            float f5 = (width - (intrinsicWidth * f2)) * fFloatValue2;
            f = 0.0f;
            f3 = f5;
        } else {
            Float f6 = this.mCropYCenterOffsetPct;
            if (f6 != null) {
                Intrinsics.checkNotNull(f6);
                fFloatValue = f6.floatValue();
            } else {
                fFloatValue = 0.0f;
            }
            float f7 = width / intrinsicWidth;
            f = (height - (intrinsicHeight * f7)) * fFloatValue;
            f2 = f7;
        }
        matrix.setScale(f2, f2);
        matrix.postTranslate((int) (f3 + 0.5f), (int) (f + 0.5f));
        setImageMatrix(matrix);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int l, int t, int r, int b) {
        boolean frame = super.setFrame(l, t, r, b);
        myConfigureBounds();
        return frame;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable d) {
        super.setImageDrawable(d);
        myConfigureBounds();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        myConfigureBounds();
    }

    public final void redraw() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            setImageDrawable(null);
            setImageDrawable(drawable);
        }
    }

    private final void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        int[] PercentageCropImageView = R.styleable.PercentageCropImageView;
        Intrinsics.checkNotNullExpressionValue(PercentageCropImageView, "PercentageCropImageView");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, PercentageCropImageView, 0, 0);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.PercentageCropImageView_cropYCenterOffsetPct)) {
                this.mCropYCenterOffsetPct = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(R.styleable.PercentageCropImageView_cropYCenterOffsetPct, 0.5f));
            }
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.PercentageCropImageView_cropXCenterOffsetPct)) {
                this.mCropXCenterOffsetPct = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(R.styleable.PercentageCropImageView_cropXCenterOffsetPct, 0.5f));
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}

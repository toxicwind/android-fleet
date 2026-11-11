package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class ColorPanelView extends View {
    private static final int DEFAULT_BORDER_COLOR = -9539986;
    private Paint alphaPaint;
    private Drawable alphaPattern;
    private int borderColor;
    private Paint borderPaint;
    private int borderWidthPx;
    private RectF centerRect;
    private int color;
    private Paint colorPaint;
    private Rect colorRect;
    private Rect drawingRect;
    private int focusBorderWidthPx;
    private int focusColor;
    private Paint focusPaint;
    private Paint originalPaint;
    private int shape;
    private boolean showOldColor;

    public ColorPanelView(Context context) {
        this(context, null);
    }

    public ColorPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPanelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.centerRect = new RectF();
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.focusColor = DEFAULT_BORDER_COLOR;
        this.color = -16777216;
        init(context, attributeSet);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("color", this.color);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.color = bundle.getInt("color");
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        invalidate();
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPanelView);
        this.shape = typedArrayObtainStyledAttributes.getInt(R.styleable.ColorPanelView_cpv_colorShape, 1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPanelView_cpv_showOldColor, false);
        this.showOldColor = z;
        if (z && this.shape != 1) {
            throw new IllegalStateException("Color preview is only available in circle mode");
        }
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.ColorPanelView_cpv_borderColor, DEFAULT_BORDER_COLOR);
        this.borderColor = color;
        if (color == DEFAULT_BORDER_COLOR) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(new TypedValue().data, new int[]{android.R.attr.textColorSecondary});
            this.borderColor = typedArrayObtainStyledAttributes2.getColor(0, this.borderColor);
            typedArrayObtainStyledAttributes2.recycle();
        }
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.ColorPanelView_cpv_focusBorderColor, DEFAULT_BORDER_COLOR);
        this.focusColor = color2;
        if (color2 == DEFAULT_BORDER_COLOR) {
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(new TypedValue().data, new int[]{R.attr.cpv_focusBorderColor});
            this.focusColor = typedArrayObtainStyledAttributes3.getColor(0, this.focusColor);
            typedArrayObtainStyledAttributes3.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        this.borderWidthPx = DrawingUtils.dpToPx(context, 1.0f);
        this.focusBorderWidthPx = DrawingUtils.dpToPx(context, 2.0f);
        Paint paint = new Paint();
        this.borderPaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.focusPaint = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.colorPaint = paint3;
        paint3.setAntiAlias(true);
        if (this.showOldColor) {
            this.originalPaint = new Paint();
        }
        if (this.shape == 1) {
            Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.cpv_alpha)).getBitmap();
            Paint paint4 = new Paint();
            this.alphaPaint = paint4;
            paint4.setAntiAlias(true);
            this.alphaPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.borderPaint.setColor(this.borderColor);
        this.focusPaint.setColor(this.focusColor);
        this.colorPaint.setColor(this.color);
        int i = this.shape;
        if (i == 0) {
            canvas.drawRect(this.drawingRect, this.borderPaint);
            if (this.borderWidthPx > 0) {
                canvas.drawRect(this.drawingRect, this.borderPaint);
            }
            Drawable drawable = this.alphaPattern;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            canvas.drawRect(this.colorRect, this.colorPaint);
            return;
        }
        if (i == 1) {
            int measuredWidth = getMeasuredWidth() / 2;
            boolean zIsFocused = isFocused();
            int i2 = measuredWidth - this.borderWidthPx;
            if (zIsFocused) {
                i2 -= this.focusBorderWidthPx;
            }
            int i3 = zIsFocused ? measuredWidth - this.focusBorderWidthPx : measuredWidth;
            if (zIsFocused && this.focusBorderWidthPx > 0) {
                canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, measuredWidth, this.focusPaint);
            }
            if (this.borderWidthPx > 0) {
                canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, i3, this.borderPaint);
            }
            if (Color.alpha(this.color) < 255) {
                canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, i2, this.alphaPaint);
            }
            if (this.showOldColor) {
                canvas.drawArc(this.centerRect, 90.0f, 180.0f, true, this.originalPaint);
                canvas.drawArc(this.centerRect, 270.0f, 180.0f, true, this.colorPaint);
            } else {
                canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, i2, this.colorPaint);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.shape;
        if (i3 == 0) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        } else if (i3 == 1) {
            super.onMeasure(i, i);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.shape == 0 || this.showOldColor) {
            Rect rect = new Rect();
            this.drawingRect = rect;
            rect.left = getPaddingLeft();
            this.drawingRect.right = i - getPaddingRight();
            this.drawingRect.top = getPaddingTop();
            this.drawingRect.bottom = i2 - getPaddingBottom();
            if (this.showOldColor) {
                setUpCenterRect();
            } else {
                setUpColorRect();
            }
        }
    }

    private void setUpCenterRect() {
        Rect rect = this.drawingRect;
        this.centerRect = new RectF(rect.left + this.borderWidthPx, rect.top + this.borderWidthPx, rect.right - this.borderWidthPx, rect.bottom - this.borderWidthPx);
    }

    private void setUpColorRect() {
        Rect rect = this.drawingRect;
        this.colorRect = new Rect(rect.left + this.borderWidthPx, rect.top + this.borderWidthPx, rect.right - this.borderWidthPx, rect.bottom - this.borderWidthPx);
        AlphaPatternDrawable alphaPatternDrawable = new AlphaPatternDrawable(DrawingUtils.dpToPx(getContext(), 4.0f));
        this.alphaPattern = alphaPatternDrawable;
        alphaPatternDrawable.setBounds(this.colorRect.left, this.colorRect.top, this.colorRect.right, this.colorRect.bottom);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        invalidate();
    }

    public void setOriginalColor(int i) {
        Paint paint = this.originalPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public int getShape() {
        return this.shape;
    }

    public void setShape(int i) {
        this.shape = i;
        invalidate();
    }

    public void showHint() {
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i = iArr[1] + (height / 2);
        int i2 = iArr[0] + (width / 2);
        if (ViewCompat.getLayoutDirection(this) == 0) {
            i2 = context.getResources().getDisplayMetrics().widthPixels - i2;
        }
        StringBuilder sb = new StringBuilder("#");
        int iAlpha = Color.alpha(this.color);
        int i3 = this.color;
        if (iAlpha != 255) {
            sb.append(Integer.toHexString(i3).toUpperCase(Locale.ENGLISH));
        } else {
            sb.append(String.format("%06X", Integer.valueOf(i3 & ViewCompat.MEASURED_SIZE_MASK)).toUpperCase(Locale.ENGLISH));
        }
        Toast toastMakeText = Toast.makeText(context, sb.toString(), 0);
        if (i < rect.height()) {
            toastMakeText.setGravity(8388661, i2, (iArr[1] + height) - rect.top);
        } else {
            toastMakeText.setGravity(81, 0, height);
        }
        toastMakeText.show();
    }
}

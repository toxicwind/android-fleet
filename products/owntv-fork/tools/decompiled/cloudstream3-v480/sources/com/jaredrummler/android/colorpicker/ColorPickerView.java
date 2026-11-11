package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.text.util.LocalePreferences;

/* JADX INFO: loaded from: classes6.dex */
public class ColorPickerView extends View {
    private static final int ALPHA_PANEL_HEIGHT_DP = 20;
    private static final int BORDER_WIDTH_PX = 1;
    private static final int CIRCLE_TRACKER_RADIUS_DP = 5;
    private static final int DEFAULT_BORDER_COLOR = -9539986;
    private static final int DEFAULT_SLIDER_COLOR = -4342339;
    private static final int HUE_PANEL_WIDTH_DP = 30;
    private static final int PANEL_SPACING_DP = 10;
    private static final int SLIDER_TRACKER_OFFSET_DP = 2;
    private static final int SLIDER_TRACKER_SIZE_DP = 4;
    private int alpha;
    private Paint alphaPaint;
    private int alphaPanelHeightPx;
    private AlphaPatternDrawable alphaPatternDrawable;
    private Rect alphaRect;
    private Shader alphaShader;
    private String alphaSliderText;
    private Paint alphaTextPaint;
    private int borderColor;
    private Paint borderPaint;
    private int circleTrackerRadiusPx;
    private Rect drawingRect;
    private float hue;
    private Paint hueAlphaTrackerPaint;
    private BitmapCache hueBackgroundCache;
    private int huePanelWidthPx;
    private Rect hueRect;
    private int mRequiredPadding;
    private OnColorChangedListener onColorChangedListener;
    private int panelSpacingPx;
    private float sat;
    private Shader satShader;
    private BitmapCache satValBackgroundCache;
    private Paint satValPaint;
    private Rect satValRect;
    private Paint satValTrackerPaint;
    private boolean showAlphaPanel;
    private int sliderTrackerColor;
    private int sliderTrackerOffsetPx;
    private int sliderTrackerSizePx;
    private Point startTouchPoint;
    private float val;
    private Shader valShader;

    public interface OnColorChangedListener {
        void onColorChanged(int i);
    }

    public ColorPickerView(Context context) {
        this(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.alpha = 255;
        this.hue = 360.0f;
        this.sat = 0.0f;
        this.val = 0.0f;
        this.showAlphaPanel = false;
        this.alphaSliderText = null;
        this.sliderTrackerColor = DEFAULT_SLIDER_COLOR;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.startTouchPoint = null;
        init(context, attributeSet);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("alpha", this.alpha);
        bundle.putFloat("hue", this.hue);
        bundle.putFloat(LocalePreferences.FirstDayOfWeek.SATURDAY, this.sat);
        bundle.putFloat("val", this.val);
        bundle.putBoolean("show_alpha", this.showAlphaPanel);
        bundle.putString("alpha_text", this.alphaSliderText);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.alpha = bundle.getInt("alpha");
            this.hue = bundle.getFloat("hue");
            this.sat = bundle.getFloat(LocalePreferences.FirstDayOfWeek.SATURDAY);
            this.val = bundle.getFloat("val");
            this.showAlphaPanel = bundle.getBoolean("show_alpha");
            this.alphaSliderText = bundle.getString("alpha_text");
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPickerView);
        this.showAlphaPanel = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_cpv_alphaChannelVisible, false);
        this.alphaSliderText = typedArrayObtainStyledAttributes.getString(R.styleable.ColorPickerView_cpv_alphaChannelText);
        this.sliderTrackerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ColorPickerView_cpv_sliderColor, DEFAULT_SLIDER_COLOR);
        this.borderColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ColorPickerView_cpv_borderColor, DEFAULT_BORDER_COLOR);
        typedArrayObtainStyledAttributes.recycle();
        applyThemeColors(context);
        this.huePanelWidthPx = DrawingUtils.dpToPx(getContext(), 30.0f);
        this.alphaPanelHeightPx = DrawingUtils.dpToPx(getContext(), 20.0f);
        this.panelSpacingPx = DrawingUtils.dpToPx(getContext(), 10.0f);
        this.circleTrackerRadiusPx = DrawingUtils.dpToPx(getContext(), 5.0f);
        this.sliderTrackerSizePx = DrawingUtils.dpToPx(getContext(), 4.0f);
        this.sliderTrackerOffsetPx = DrawingUtils.dpToPx(getContext(), 2.0f);
        this.mRequiredPadding = getResources().getDimensionPixelSize(R.dimen.cpv_required_padding);
        initPaintTools();
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void applyThemeColors(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[]{android.R.attr.textColorSecondary});
        if (this.borderColor == DEFAULT_BORDER_COLOR) {
            this.borderColor = typedArrayObtainStyledAttributes.getColor(0, DEFAULT_BORDER_COLOR);
        }
        if (this.sliderTrackerColor == DEFAULT_SLIDER_COLOR) {
            this.sliderTrackerColor = typedArrayObtainStyledAttributes.getColor(0, DEFAULT_SLIDER_COLOR);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initPaintTools() {
        this.satValPaint = new Paint();
        this.satValTrackerPaint = new Paint();
        this.hueAlphaTrackerPaint = new Paint();
        this.alphaPaint = new Paint();
        this.alphaTextPaint = new Paint();
        this.borderPaint = new Paint();
        this.satValTrackerPaint.setStyle(Paint.Style.STROKE);
        this.satValTrackerPaint.setStrokeWidth(DrawingUtils.dpToPx(getContext(), 2.0f));
        this.satValTrackerPaint.setAntiAlias(true);
        this.hueAlphaTrackerPaint.setColor(this.sliderTrackerColor);
        this.hueAlphaTrackerPaint.setStyle(Paint.Style.STROKE);
        this.hueAlphaTrackerPaint.setStrokeWidth(DrawingUtils.dpToPx(getContext(), 2.0f));
        this.hueAlphaTrackerPaint.setAntiAlias(true);
        this.alphaTextPaint.setColor(-14935012);
        this.alphaTextPaint.setTextSize(DrawingUtils.dpToPx(getContext(), 14.0f));
        this.alphaTextPaint.setAntiAlias(true);
        this.alphaTextPaint.setTextAlign(Paint.Align.CENTER);
        this.alphaTextPaint.setFakeBoldText(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.drawingRect.width() <= 0 || this.drawingRect.height() <= 0) {
            return;
        }
        drawSatValPanel(canvas);
        drawHuePanel(canvas);
        drawAlphaPanel(canvas);
    }

    private void drawSatValPanel(Canvas canvas) {
        Rect rect = this.satValRect;
        this.borderPaint.setColor(this.borderColor);
        canvas.drawRect(this.drawingRect.left, this.drawingRect.top, rect.right + 1, rect.bottom + 1, this.borderPaint);
        if (this.valShader == null) {
            this.valShader = new LinearGradient(rect.left, rect.top, rect.left, rect.bottom, -1, -16777216, Shader.TileMode.CLAMP);
        }
        BitmapCache bitmapCache = this.satValBackgroundCache;
        if (bitmapCache == null || bitmapCache.value != this.hue) {
            if (this.satValBackgroundCache == null) {
                this.satValBackgroundCache = new BitmapCache();
            }
            if (this.satValBackgroundCache.bitmap == null) {
                this.satValBackgroundCache.bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            }
            if (this.satValBackgroundCache.canvas == null) {
                this.satValBackgroundCache.canvas = new Canvas(this.satValBackgroundCache.bitmap);
            }
            this.satShader = new LinearGradient(rect.left, rect.top, rect.right, rect.top, -1, Color.HSVToColor(new float[]{this.hue, 1.0f, 1.0f}), Shader.TileMode.CLAMP);
            this.satValPaint.setShader(new ComposeShader(this.valShader, this.satShader, PorterDuff.Mode.MULTIPLY));
            this.satValBackgroundCache.canvas.drawRect(0.0f, 0.0f, this.satValBackgroundCache.bitmap.getWidth(), this.satValBackgroundCache.bitmap.getHeight(), this.satValPaint);
            this.satValBackgroundCache.value = this.hue;
        }
        canvas.drawBitmap(this.satValBackgroundCache.bitmap, (Rect) null, rect, (Paint) null);
        Point pointSatValToPoint = satValToPoint(this.sat, this.val);
        this.satValTrackerPaint.setColor(-16777216);
        canvas.drawCircle(pointSatValToPoint.x, pointSatValToPoint.y, this.circleTrackerRadiusPx - DrawingUtils.dpToPx(getContext(), 1.0f), this.satValTrackerPaint);
        this.satValTrackerPaint.setColor(-2236963);
        canvas.drawCircle(pointSatValToPoint.x, pointSatValToPoint.y, this.circleTrackerRadiusPx, this.satValTrackerPaint);
    }

    private void drawHuePanel(Canvas canvas) {
        Rect rect = this.hueRect;
        this.borderPaint.setColor(this.borderColor);
        canvas.drawRect(rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1, this.borderPaint);
        if (this.hueBackgroundCache == null) {
            BitmapCache bitmapCache = new BitmapCache();
            this.hueBackgroundCache = bitmapCache;
            bitmapCache.bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            this.hueBackgroundCache.canvas = new Canvas(this.hueBackgroundCache.bitmap);
            int iHeight = (int) (rect.height() + 0.5f);
            int[] iArr = new int[iHeight];
            float f = 360.0f;
            for (int i = 0; i < iHeight; i++) {
                iArr[i] = Color.HSVToColor(new float[]{f, 1.0f, 1.0f});
                f -= 360.0f / iHeight;
            }
            Paint paint = new Paint();
            paint.setStrokeWidth(0.0f);
            for (int i2 = 0; i2 < iHeight; i2++) {
                paint.setColor(iArr[i2]);
                float f2 = i2;
                this.hueBackgroundCache.canvas.drawLine(0.0f, f2, this.hueBackgroundCache.bitmap.getWidth(), f2, paint);
            }
        }
        canvas.drawBitmap(this.hueBackgroundCache.bitmap, (Rect) null, rect, (Paint) null);
        Point pointHueToPoint = hueToPoint(this.hue);
        RectF rectF = new RectF();
        rectF.left = rect.left - this.sliderTrackerOffsetPx;
        rectF.right = rect.right + this.sliderTrackerOffsetPx;
        rectF.top = pointHueToPoint.y - (this.sliderTrackerSizePx / 2.0f);
        rectF.bottom = pointHueToPoint.y + (this.sliderTrackerSizePx / 2.0f);
        canvas.drawRoundRect(rectF, 2.0f, 2.0f, this.hueAlphaTrackerPaint);
    }

    private void drawAlphaPanel(Canvas canvas) {
        Rect rect;
        if (!this.showAlphaPanel || (rect = this.alphaRect) == null || this.alphaPatternDrawable == null) {
            return;
        }
        this.borderPaint.setColor(this.borderColor);
        canvas.drawRect(rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1, this.borderPaint);
        this.alphaPatternDrawable.draw(canvas);
        float[] fArr = {this.hue, this.sat, this.val};
        LinearGradient linearGradient = new LinearGradient(rect.left, rect.top, rect.right, rect.top, Color.HSVToColor(fArr), Color.HSVToColor(0, fArr), Shader.TileMode.CLAMP);
        this.alphaShader = linearGradient;
        this.alphaPaint.setShader(linearGradient);
        canvas.drawRect(rect, this.alphaPaint);
        String str = this.alphaSliderText;
        if (str != null && !str.isEmpty()) {
            canvas.drawText(this.alphaSliderText, rect.centerX(), rect.centerY() + DrawingUtils.dpToPx(getContext(), 4.0f), this.alphaTextPaint);
        }
        Point pointAlphaToPoint = alphaToPoint(this.alpha);
        RectF rectF = new RectF();
        rectF.left = pointAlphaToPoint.x - (this.sliderTrackerSizePx / 2.0f);
        rectF.right = pointAlphaToPoint.x + (this.sliderTrackerSizePx / 2.0f);
        rectF.top = rect.top - this.sliderTrackerOffsetPx;
        rectF.bottom = rect.bottom + this.sliderTrackerOffsetPx;
        canvas.drawRoundRect(rectF, 2.0f, 2.0f, this.hueAlphaTrackerPaint);
    }

    private Point hueToPoint(float f) {
        Rect rect = this.hueRect;
        float fHeight = rect.height();
        Point point = new Point();
        point.y = (int) ((fHeight - ((f * fHeight) / 360.0f)) + rect.top);
        point.x = rect.left;
        return point;
    }

    private Point satValToPoint(float f, float f2) {
        Rect rect = this.satValRect;
        float fHeight = rect.height();
        float fWidth = rect.width();
        Point point = new Point();
        point.x = (int) ((f * fWidth) + rect.left);
        point.y = (int) (((1.0f - f2) * fHeight) + rect.top);
        return point;
    }

    private Point alphaToPoint(int i) {
        Rect rect = this.alphaRect;
        float fWidth = rect.width();
        Point point = new Point();
        point.x = (int) ((fWidth - ((i * fWidth) / 255.0f)) + rect.left);
        point.y = rect.top;
        return point;
    }

    private float[] pointToSatVal(float f, float f2) {
        float f3;
        Rect rect = this.satValRect;
        float fWidth = rect.width();
        float fHeight = rect.height();
        float f4 = 0.0f;
        if (f < rect.left) {
            f3 = 0.0f;
        } else {
            f3 = f > ((float) rect.right) ? fWidth : f - rect.left;
        }
        if (f2 >= rect.top) {
            f4 = f2 > ((float) rect.bottom) ? fHeight : f2 - rect.top;
        }
        return new float[]{(1.0f / fWidth) * f3, 1.0f - ((1.0f / fHeight) * f4)};
    }

    private float pointToHue(float f) {
        float f2;
        Rect rect = this.hueRect;
        float fHeight = rect.height();
        if (f < rect.top) {
            f2 = 0.0f;
        } else {
            f2 = f > ((float) rect.bottom) ? fHeight : f - rect.top;
        }
        return 360.0f - ((f2 * 360.0f) / fHeight);
    }

    private int pointToAlpha(int i) {
        int i2;
        Rect rect = this.alphaRect;
        int iWidth = rect.width();
        if (i < rect.left) {
            i2 = 0;
        } else {
            i2 = i > rect.right ? iWidth : i - rect.left;
        }
        return 255 - ((i2 * 255) / iWidth);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zMoveTrackersIfNeeded;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTouchPoint = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            zMoveTrackersIfNeeded = moveTrackersIfNeeded(motionEvent);
        } else if (action != 1) {
            zMoveTrackersIfNeeded = action != 2 ? false : moveTrackersIfNeeded(motionEvent);
        } else {
            this.startTouchPoint = null;
            zMoveTrackersIfNeeded = moveTrackersIfNeeded(motionEvent);
        }
        if (zMoveTrackersIfNeeded) {
            OnColorChangedListener onColorChangedListener = this.onColorChangedListener;
            if (onColorChangedListener != null) {
                onColorChangedListener.onColorChanged(Color.HSVToColor(this.alpha, new float[]{this.hue, this.sat, this.val}));
            }
            invalidate();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean moveTrackersIfNeeded(MotionEvent motionEvent) {
        Point point = this.startTouchPoint;
        if (point == null) {
            return false;
        }
        int i = point.x;
        int i2 = this.startTouchPoint.y;
        if (this.hueRect.contains(i, i2)) {
            this.hue = pointToHue(motionEvent.getY());
            return true;
        }
        if (this.satValRect.contains(i, i2)) {
            float[] fArrPointToSatVal = pointToSatVal(motionEvent.getX(), motionEvent.getY());
            this.sat = fArrPointToSatVal[0];
            this.val = fArrPointToSatVal[1];
            return true;
        }
        Rect rect = this.alphaRect;
        if (rect == null || !rect.contains(i, i2)) {
            return false;
        }
        this.alpha = pointToAlpha((int) motionEvent.getX());
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x005a  */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i2) - getPaddingBottom()) - getPaddingTop();
        if (mode != 1073741824 && mode2 != 1073741824) {
            int i3 = this.panelSpacingPx;
            int i4 = this.huePanelWidthPx;
            int i5 = size2 + i3 + i4;
            int i6 = (size - i3) - i4;
            if (this.showAlphaPanel) {
                int i7 = this.alphaPanelHeightPx;
                i5 -= i3 + i7;
                i6 += i3 + i7;
            }
            boolean z = i5 <= size;
            boolean z2 = i6 <= size2;
            if (z && z2) {
                size2 = i6;
            } else if (!z2 && z) {
                size = i5;
            } else if (!z && z2) {
                size2 = i6;
            }
        } else if (mode == 1073741824 && mode2 != 1073741824) {
            int i8 = this.panelSpacingPx;
            int i9 = (size - i8) - this.huePanelWidthPx;
            if (this.showAlphaPanel) {
                i9 += i8 + this.alphaPanelHeightPx;
            }
            size2 = Math.min(i9, size2);
        } else if (mode2 == 1073741824 && mode != 1073741824) {
            int i10 = this.panelSpacingPx;
            int i11 = size2 + i10 + this.huePanelWidthPx;
            if (this.showAlphaPanel) {
                i11 -= i10 + this.alphaPanelHeightPx;
            }
            size = Math.min(i11, size);
        }
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size2 + getPaddingTop() + getPaddingBottom());
    }

    private int getPreferredWidth() {
        return DrawingUtils.dpToPx(getContext(), 200.0f) + this.huePanelWidthPx + this.panelSpacingPx;
    }

    private int getPreferredHeight() {
        int iDpToPx = DrawingUtils.dpToPx(getContext(), 200.0f);
        return this.showAlphaPanel ? iDpToPx + this.panelSpacingPx + this.alphaPanelHeightPx : iDpToPx;
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return Math.max(super.getPaddingTop(), this.mRequiredPadding);
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return Math.max(super.getPaddingBottom(), this.mRequiredPadding);
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return Math.max(super.getPaddingLeft(), this.mRequiredPadding);
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return Math.max(super.getPaddingRight(), this.mRequiredPadding);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Rect rect = new Rect();
        this.drawingRect = rect;
        rect.left = getPaddingLeft();
        this.drawingRect.right = i - getPaddingRight();
        this.drawingRect.top = getPaddingTop();
        this.drawingRect.bottom = i2 - getPaddingBottom();
        this.valShader = null;
        this.satShader = null;
        this.alphaShader = null;
        this.satValBackgroundCache = null;
        this.hueBackgroundCache = null;
        setUpSatValRect();
        setUpHueRect();
        setUpAlphaRect();
    }

    private void setUpSatValRect() {
        Rect rect = this.drawingRect;
        int i = rect.left + 1;
        int i2 = rect.top + 1;
        int i3 = rect.bottom - 1;
        int i4 = rect.right - 1;
        int i5 = this.panelSpacingPx;
        int i6 = (i4 - i5) - this.huePanelWidthPx;
        if (this.showAlphaPanel) {
            i3 -= this.alphaPanelHeightPx + i5;
        }
        this.satValRect = new Rect(i, i2, i6, i3);
    }

    private void setUpHueRect() {
        Rect rect = this.drawingRect;
        this.hueRect = new Rect((rect.right - this.huePanelWidthPx) + 1, rect.top + 1, rect.right - 1, (rect.bottom - 1) - (this.showAlphaPanel ? this.panelSpacingPx + this.alphaPanelHeightPx : 0));
    }

    private void setUpAlphaRect() {
        if (this.showAlphaPanel) {
            Rect rect = this.drawingRect;
            this.alphaRect = new Rect(rect.left + 1, (rect.bottom - this.alphaPanelHeightPx) + 1, rect.right - 1, rect.bottom - 1);
            AlphaPatternDrawable alphaPatternDrawable = new AlphaPatternDrawable(DrawingUtils.dpToPx(getContext(), 4.0f));
            this.alphaPatternDrawable = alphaPatternDrawable;
            alphaPatternDrawable.setBounds(this.alphaRect.left, this.alphaRect.top, this.alphaRect.right, this.alphaRect.bottom);
        }
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.onColorChangedListener = onColorChangedListener;
    }

    public int getColor() {
        return Color.HSVToColor(this.alpha, new float[]{this.hue, this.sat, this.val});
    }

    public void setColor(int i) {
        setColor(i, false);
    }

    public void setColor(int i, boolean z) {
        OnColorChangedListener onColorChangedListener;
        int iAlpha = Color.alpha(i);
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        this.alpha = iAlpha;
        float f = fArr[0];
        this.hue = f;
        float f2 = fArr[1];
        this.sat = f2;
        float f3 = fArr[2];
        this.val = f3;
        if (z && (onColorChangedListener = this.onColorChangedListener) != null) {
            onColorChangedListener.onColorChanged(Color.HSVToColor(iAlpha, new float[]{f, f2, f3}));
        }
        invalidate();
    }

    public void setAlphaSliderVisible(boolean z) {
        if (this.showAlphaPanel != z) {
            this.showAlphaPanel = z;
            this.valShader = null;
            this.satShader = null;
            this.alphaShader = null;
            this.hueBackgroundCache = null;
            this.satValBackgroundCache = null;
            requestLayout();
        }
    }

    public int getSliderTrackerColor() {
        return this.sliderTrackerColor;
    }

    public void setSliderTrackerColor(int i) {
        this.sliderTrackerColor = i;
        this.hueAlphaTrackerPaint.setColor(i);
        invalidate();
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setAlphaSliderText(int i) {
        setAlphaSliderText(getContext().getString(i));
    }

    public String getAlphaSliderText() {
        return this.alphaSliderText;
    }

    public void setAlphaSliderText(String str) {
        this.alphaSliderText = str;
        invalidate();
    }

    private static class BitmapCache {
        public Bitmap bitmap;
        public Canvas canvas;
        public float value;

        private BitmapCache() {
        }
    }
}

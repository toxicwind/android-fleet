package com.android.launcher3.icons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class IconNormalizer {
    private final RectF mAdaptiveIconBounds;
    private float mAdaptiveIconScale;
    private final Bitmap mBitmap;
    private final Rect mBounds;
    private final Canvas mCanvas;
    private boolean mEnableShapeDetection;
    private final float[] mLeftBorder;
    private final Matrix mMatrix;
    private final int mMaxSize;
    private final Paint mPaintMaskShape;
    private final Paint mPaintMaskShapeOutline;
    private final byte[] mPixels;
    private final float[] mRightBorder;
    private final Path mShapePath;

    IconNormalizer(Context context, int i, boolean z) {
        int i2 = i * 2;
        this.mMaxSize = i2;
        this.mBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ALPHA_8);
        this.mCanvas = new Canvas(this.mBitmap);
        int i3 = this.mMaxSize;
        this.mPixels = new byte[i3 * i3];
        this.mLeftBorder = new float[i3];
        this.mRightBorder = new float[i3];
        this.mBounds = new Rect();
        this.mAdaptiveIconBounds = new RectF();
        Paint paint = new Paint();
        this.mPaintMaskShape = paint;
        paint.setColor(-65536);
        this.mPaintMaskShape.setStyle(Paint.Style.FILL);
        this.mPaintMaskShape.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        Paint paint2 = new Paint();
        this.mPaintMaskShapeOutline = paint2;
        paint2.setStrokeWidth(context.getResources().getDisplayMetrics().density * 2.0f);
        this.mPaintMaskShapeOutline.setStyle(Paint.Style.STROKE);
        this.mPaintMaskShapeOutline.setColor(-16777216);
        this.mPaintMaskShapeOutline.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mShapePath = new Path();
        this.mMatrix = new Matrix();
        this.mAdaptiveIconScale = 0.0f;
        this.mEnableShapeDetection = z;
    }

    private static float getScale(float f, float f2, float f3) {
        float f4 = f / f2;
        float f5 = f4 < 0.7853982f ? 0.6597222f : ((1.0f - f4) * 0.040449437f) + 0.6510417f;
        float f6 = f / f3;
        if (f6 > f5) {
            return (float) Math.sqrt(f5 / f6);
        }
        return 1.0f;
    }

    @TargetApi(26)
    public static float normalizeAdaptiveIcon(Drawable drawable, int i, RectF rectF) {
        Rect rect = new Rect(drawable.getBounds());
        drawable.setBounds(0, 0, i, i);
        Path iconMask = ((AdaptiveIconDrawable) drawable).getIconMask();
        Region region = new Region();
        region.setPath(iconMask, new Region(0, 0, i, i));
        Rect bounds = region.getBounds();
        int area = GraphicsUtils.getArea(region);
        if (rectF != null) {
            float f = i;
            rectF.set(bounds.left / f, bounds.top / f, 1.0f - (bounds.right / f), 1.0f - (bounds.bottom / f));
        }
        drawable.setBounds(rect);
        float f2 = area;
        return getScale(f2, f2, i * i);
    }

    private boolean isShape(Path path) {
        if (Math.abs((this.mBounds.width() / this.mBounds.height()) - 1.0f) > 0.05f) {
            return false;
        }
        this.mMatrix.reset();
        this.mMatrix.setScale(this.mBounds.width(), this.mBounds.height());
        Matrix matrix = this.mMatrix;
        Rect rect = this.mBounds;
        matrix.postTranslate(rect.left, rect.top);
        path.transform(this.mMatrix, this.mShapePath);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShape);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShapeOutline);
        return isTransparentBitmap();
    }

    private boolean isTransparentBitmap() {
        Rect rect;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.mPixels);
        byteBufferWrap.rewind();
        this.mBitmap.copyPixelsToBuffer(byteBufferWrap);
        Rect rect2 = this.mBounds;
        int i = rect2.top;
        int i2 = this.mMaxSize;
        int i3 = i * i2;
        int i4 = i2 - rect2.right;
        int i5 = 0;
        while (true) {
            rect = this.mBounds;
            if (i >= rect.bottom) {
                break;
            }
            int i6 = rect.left;
            int i7 = i3 + i6;
            while (i6 < this.mBounds.right) {
                if ((this.mPixels[i7] & 255) > 40) {
                    i5++;
                }
                i7++;
                i6++;
            }
            i3 = i7 + i4;
            i++;
        }
        return ((float) i5) / ((float) (rect.width() * this.mBounds.height())) < 0.005f;
    }

    public synchronized float getScale(Drawable drawable, RectF rectF, Path path, boolean[] zArr) {
        if (BaseIconFactory.ATLEAST_OREO && (drawable instanceof AdaptiveIconDrawable)) {
            if (this.mAdaptiveIconScale == 0.0f) {
                this.mAdaptiveIconScale = normalizeAdaptiveIcon(drawable, this.mMaxSize, this.mAdaptiveIconBounds);
            }
            if (rectF != null) {
                rectF.set(this.mAdaptiveIconBounds);
            }
            return this.mAdaptiveIconScale;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            if (intrinsicWidth <= 0 || intrinsicWidth > this.mMaxSize) {
                intrinsicWidth = this.mMaxSize;
            }
            if (intrinsicHeight <= 0 || intrinsicHeight > this.mMaxSize) {
                intrinsicHeight = this.mMaxSize;
            }
        } else if (intrinsicWidth > this.mMaxSize || intrinsicHeight > this.mMaxSize) {
            int iMax = Math.max(intrinsicWidth, intrinsicHeight);
            intrinsicWidth = (this.mMaxSize * intrinsicWidth) / iMax;
            intrinsicHeight = (this.mMaxSize * intrinsicHeight) / iMax;
        }
        int i = 0;
        this.mBitmap.eraseColor(0);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(this.mCanvas);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.mPixels);
        byteBufferWrap.rewind();
        this.mBitmap.copyPixelsToBuffer(byteBufferWrap);
        int iMin = this.mMaxSize + 1;
        int i2 = this.mMaxSize - intrinsicWidth;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        int iMax2 = -1;
        int i6 = -1;
        while (i3 < intrinsicHeight) {
            int i7 = -1;
            int i8 = -1;
            for (int i9 = i; i9 < intrinsicWidth; i9++) {
                if ((this.mPixels[i4] & 255) > 40) {
                    if (i8 == -1) {
                        i8 = i9;
                    }
                    i7 = i9;
                }
                i4++;
            }
            i4 += i2;
            this.mLeftBorder[i3] = i8;
            this.mRightBorder[i3] = i7;
            if (i8 != -1) {
                if (i5 == -1) {
                    i5 = i3;
                }
                iMin = Math.min(iMin, i8);
                iMax2 = Math.max(iMax2, i7);
                i6 = i3;
            }
            i3++;
            i = 0;
        }
        if (i5 != -1 && iMax2 != -1) {
            convertToConvexArray(this.mLeftBorder, 1, i5, i6);
            convertToConvexArray(this.mRightBorder, -1, i5, i6);
            float f = 0.0f;
            for (int i10 = 0; i10 < intrinsicHeight; i10++) {
                if (this.mLeftBorder[i10] > -1.0f) {
                    f += (this.mRightBorder[i10] - this.mLeftBorder[i10]) + 1.0f;
                }
            }
            this.mBounds.left = iMin;
            this.mBounds.right = iMax2;
            this.mBounds.top = i5;
            this.mBounds.bottom = i6;
            if (rectF != null) {
                float f2 = intrinsicWidth;
                float f3 = intrinsicHeight;
                rectF.set(this.mBounds.left / f2, this.mBounds.top / f3, 1.0f - (this.mBounds.right / f2), 1.0f - (this.mBounds.bottom / f3));
            }
            if (zArr != null && this.mEnableShapeDetection && zArr.length > 0) {
                zArr[0] = isShape(path);
            }
            return getScale(f, ((i6 + 1) - i5) * ((iMax2 + 1) - iMin), intrinsicWidth * intrinsicHeight);
        }
        return 1.0f;
    }

    private static void convertToConvexArray(float[] fArr, int i, int i2, int i3) {
        float[] fArr2 = new float[fArr.length - 1];
        int i4 = -1;
        float f = Float.MAX_VALUE;
        for (int i5 = i2 + 1; i5 <= i3; i5++) {
            if (fArr[i5] > -1.0f) {
                if (f != Float.MAX_VALUE) {
                    float f2 = ((fArr[i5] - fArr[i4]) / (i5 - i4)) - f;
                    float f3 = i;
                    if (f2 * f3 < 0.0f) {
                        while (i4 > i2) {
                            i4--;
                            if ((((fArr[i5] - fArr[i4]) / (i5 - i4)) - fArr2[i4]) * f3 >= 0.0f) {
                                break;
                            }
                        }
                    }
                } else {
                    i4 = i2;
                }
                f = (fArr[i5] - fArr[i4]) / (i5 - i4);
                for (int i6 = i4; i6 < i5; i6++) {
                    fArr2[i6] = f;
                    fArr[i6] = fArr[i4] + ((i6 - i4) * f);
                }
                i4 = i5;
            }
        }
    }
}

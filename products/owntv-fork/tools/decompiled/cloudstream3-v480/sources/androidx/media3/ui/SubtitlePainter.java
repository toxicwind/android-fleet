package androidx.media3.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Log;
import com.google.common.base.Preconditions;
import j$.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes5.dex */
final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private int backgroundColor;
    private final Paint bitmapPaint;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private StaticLayout edgeLayout;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private int textTop;
    private int windowColor;
    private final Paint windowPaint;

    public SubtitlePainter(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{android.R.attr.lineSpacingExtra, android.R.attr.lineSpacingMultiplier}, 0, 0);
        this.spacingAdd = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        float fRound = Math.round((context.getResources().getDisplayMetrics().densityDpi * 2.0f) / 160.0f);
        this.outlineWidth = fRound;
        this.shadowRadius = fRound;
        this.shadowOffset = fRound;
        TextPaint textPaint = new TextPaint();
        this.textPaint = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.windowPaint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.bitmapPaint = paint2;
        paint2.setAntiAlias(true);
        paint2.setFilterBitmap(true);
    }

    public void draw(Cue cue, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        int i5;
        boolean z = cue.bitmap == null;
        if (!z) {
            i5 = -16777216;
        } else if (TextUtils.isEmpty(cue.text)) {
            return;
        } else {
            i5 = cue.windowColorSet ? cue.windowColor : captionStyleCompat.windowColor;
        }
        if (areCharSequencesEqual(this.cueText, cue.text) && Objects.equals(this.cueTextAlignment, cue.textAlignment) && this.cueBitmap == cue.bitmap && this.cueLine == cue.line && this.cueLineType == cue.lineType && Objects.equals(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue.lineAnchor)) && this.cuePosition == cue.position && Objects.equals(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue.positionAnchor)) && this.cueSize == cue.size && this.cueBitmapHeight == cue.bitmapHeight && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == i5 && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Objects.equals(this.textPaint.getTypeface(), captionStyleCompat.typeface) && this.defaultTextSizePx == f && this.cueTextSizePx == f2 && this.bottomPaddingFraction == f3 && this.parentLeft == i && this.parentTop == i2 && this.parentRight == i3 && this.parentBottom == i4) {
            drawLayout(canvas, z);
            return;
        }
        this.cueText = BidiUtils.containsRtl(cue.text) ? BidiUtils.wrapText(cue.text) : cue.text;
        this.cueTextAlignment = cue.textAlignment;
        this.cueBitmap = cue.bitmap;
        this.cueLine = cue.line;
        this.cueLineType = cue.lineType;
        this.cueLineAnchor = cue.lineAnchor;
        this.cuePosition = cue.position;
        this.cuePositionAnchor = cue.positionAnchor;
        this.cueSize = cue.size;
        this.cueBitmapHeight = cue.bitmapHeight;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = i5;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.defaultTextSizePx = f;
        this.cueTextSizePx = f2;
        this.bottomPaddingFraction = f3;
        this.parentLeft = i;
        this.parentTop = i2;
        this.parentRight = i3;
        this.parentBottom = i4;
        if (z) {
            Preconditions.checkNotNull(this.cueText);
            setupTextLayout();
        } else {
            Preconditions.checkNotNull(this.cueBitmap);
            setupBitmapLayout();
        }
        drawLayout(canvas, z);
    }

    @RequiresNonNull({"cueText"})
    private void setupTextLayout() {
        SpannableStringBuilder spannableStringBuilder;
        int iMax;
        int iMin;
        int iRound;
        CharSequence charSequence = this.cueText;
        if (charSequence instanceof SpannableStringBuilder) {
            spannableStringBuilder = (SpannableStringBuilder) charSequence;
        } else {
            spannableStringBuilder = new SpannableStringBuilder(this.cueText);
        }
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        int i = this.parentRight - this.parentLeft;
        int i2 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i3 = (int) ((this.defaultTextSizePx * INNER_PADDING_RATIO) + 0.5f);
        int i4 = i3 * 2;
        int i5 = i - i4;
        float f = this.cueSize;
        float f2 = -3.4028235E38f;
        if (f != -3.4028235E38f) {
            i5 = (int) (i5 * f);
        }
        int i6 = i5;
        String str = TAG;
        if (i6 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        if (this.cueTextSizePx > 0.0f) {
            spannableStringBuilder2.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder2.length(), 16711680);
        }
        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(spannableStringBuilder2);
        if (this.edgeType == 1) {
            ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) spannableStringBuilder3.getSpans(0, spannableStringBuilder3.length(), ForegroundColorSpan.class);
            int length = foregroundColorSpanArr.length;
            int i7 = 0;
            while (i7 < length) {
                spannableStringBuilder3.removeSpan(foregroundColorSpanArr[i7]);
                i7++;
                f2 = f2;
            }
        }
        float f3 = f2;
        if (Color.alpha(this.backgroundColor) > 0) {
            int i8 = this.edgeType;
            if (i8 == 0 || i8 == 2) {
                spannableStringBuilder2.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder2.length(), 16711680);
            } else {
                spannableStringBuilder3.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder3.length(), 16711680);
            }
        }
        Layout.Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        }
        Layout.Alignment alignment2 = alignment;
        StaticLayout staticLayout = new StaticLayout(spannableStringBuilder2, this.textPaint, i6, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = staticLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int iMax2 = 0;
        int i9 = 0;
        while (i9 < lineCount) {
            iMax2 = Math.max((int) Math.ceil(this.textLayout.getLineWidth(i9)), iMax2);
            i9++;
            str = str;
        }
        String str2 = str;
        if (this.cueSize == f3 || iMax2 >= i6) {
            i6 = iMax2;
        }
        int i10 = i6 + i4;
        float f4 = this.cuePosition;
        if (f4 != f3) {
            int iRound2 = Math.round(i * f4);
            int i11 = this.parentLeft;
            int i12 = iRound2 + i11;
            int i13 = this.cuePositionAnchor;
            if (i13 == 1) {
                i12 = ((i12 * 2) - i10) / 2;
            } else if (i13 == 2) {
                i12 -= i10;
            }
            iMax = Math.max(i12, i11);
            iMin = Math.min(i10 + iMax, this.parentRight);
        } else {
            iMax = ((i - i10) / 2) + this.parentLeft;
            iMin = iMax + i10;
        }
        int i14 = iMin - iMax;
        if (i14 <= 0) {
            Log.w(str2, "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        float f5 = this.cueLine;
        if (f5 != f3) {
            if (this.cueLineType == 0) {
                iRound = Math.round(i2 * f5) + this.parentTop;
                int i15 = this.cueLineAnchor;
                if (i15 == 2) {
                    iRound -= height;
                } else if (i15 == 1) {
                    iRound = ((iRound * 2) - height) / 2;
                }
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f6 = this.cueLine;
                if (f6 >= 0.0f) {
                    iRound = Math.round(f6 * lineBottom) + this.parentTop;
                } else {
                    iRound = Math.round((f6 + 1.0f) * lineBottom) + this.parentBottom;
                    iRound -= height;
                }
            }
            int i16 = iRound + height;
            int i17 = this.parentBottom;
            if (i16 > i17) {
                iRound = i17 - height;
            } else {
                int i18 = this.parentTop;
                if (iRound < i18) {
                    iRound = i18;
                }
            }
        } else {
            iRound = (this.parentBottom - height) - ((int) (i2 * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(spannableStringBuilder2, this.textPaint, i14, alignment2, this.spacingMult, this.spacingAdd, true);
        this.edgeLayout = new StaticLayout(spannableStringBuilder3, this.textPaint, i14, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLeft = iMax;
        this.textTop = iRound;
        this.textPaddingX = i3;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0056  */
    /* JADX WARN: Code duplicated, block: B:16:0x0059 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:17:0x005b  */
    @RequiresNonNull({"cueBitmap"})
    private void setupBitmapLayout() {
        int iRound;
        float f;
        int i;
        float f2;
        Bitmap bitmap = this.cueBitmap;
        int i2 = this.parentRight;
        int i3 = this.parentLeft;
        int i4 = this.parentBottom;
        int i5 = this.parentTop;
        float f3 = i2 - i3;
        float f4 = i3 + (this.cuePosition * f3);
        float f5 = i4 - i5;
        float f6 = i5 + (this.cueLine * f5);
        int iRound2 = Math.round(f3 * this.cueSize);
        float f7 = this.cueBitmapHeight;
        if (f7 != -3.4028235E38f) {
            iRound = Math.round(f5 * f7);
        } else {
            iRound = Math.round(iRound2 * (bitmap.getHeight() / bitmap.getWidth()));
        }
        int i6 = this.cuePositionAnchor;
        if (i6 != 2) {
            if (i6 == 1) {
                f = iRound2 / 2;
            }
            int iRound3 = Math.round(f4);
            i = this.cueLineAnchor;
            if (i == 2) {
                if (i == 1) {
                    f2 = iRound / 2;
                }
                int iRound4 = Math.round(f6);
                this.bitmapRect = new Rect(iRound3, iRound4, iRound2 + iRound3, iRound + iRound4);
            }
            f2 = iRound;
            f6 -= f2;
            int iRound5 = Math.round(f6);
            this.bitmapRect = new Rect(iRound3, iRound5, iRound2 + iRound3, iRound + iRound5);
        }
        f = iRound2;
        f4 -= f;
        int iRound6 = Math.round(f4);
        i = this.cueLineAnchor;
        if (i == 2) {
            if (i == 1) {
                f2 = iRound / 2;
            }
            int iRound7 = Math.round(f6);
            this.bitmapRect = new Rect(iRound6, iRound7, iRound2 + iRound6, iRound + iRound7);
        }
        f2 = iRound;
        f6 -= f2;
        int iRound8 = Math.round(f6);
        this.bitmapRect = new Rect(iRound6, iRound8, iRound2 + iRound6, iRound + iRound8);
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
            return;
        }
        Preconditions.checkNotNull(this.bitmapRect);
        Preconditions.checkNotNull(this.cueBitmap);
        drawBitmapLayout(canvas);
    }

    private void drawTextLayout(Canvas canvas) {
        Canvas canvas2;
        StaticLayout staticLayout = this.textLayout;
        StaticLayout staticLayout2 = this.edgeLayout;
        if (staticLayout == null || staticLayout2 == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(this.textLeft, this.textTop);
        if (Color.alpha(this.windowColor) > 0) {
            this.windowPaint.setColor(this.windowColor);
            canvas2 = canvas;
            canvas2.drawRect(-this.textPaddingX, 0.0f, staticLayout.getWidth() + this.textPaddingX, staticLayout.getHeight(), this.windowPaint);
        } else {
            canvas2 = canvas;
        }
        int i = this.edgeType;
        if (i == 1) {
            this.textPaint.setStrokeJoin(Paint.Join.ROUND);
            this.textPaint.setStrokeWidth(this.outlineWidth);
            this.textPaint.setColor(this.edgeColor);
            this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            staticLayout2.draw(canvas2);
        } else if (i == 2) {
            TextPaint textPaint = this.textPaint;
            float f = this.shadowRadius;
            float f2 = this.shadowOffset;
            textPaint.setShadowLayer(f, f2, f2, this.edgeColor);
        } else if (i == 3 || i == 4) {
            boolean z = i == 3;
            int i2 = z ? -1 : this.edgeColor;
            int i3 = z ? this.edgeColor : -1;
            float f3 = this.shadowRadius / 2.0f;
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            float f4 = -f3;
            this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i2);
            staticLayout2.draw(canvas2);
            this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i3);
        }
        this.textPaint.setColor(this.foregroundColor);
        this.textPaint.setStyle(Paint.Style.FILL);
        staticLayout.draw(canvas2);
        this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        canvas2.restoreToCount(iSave);
    }

    @RequiresNonNull({"cueBitmap", "bitmapRect"})
    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, this.bitmapPaint);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != charSequence2) {
            return charSequence != null && charSequence.equals(charSequence2);
        }
        return true;
    }
}

package com.jaredrummler.android.colorpicker;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes6.dex */
public class AlphaPatternDrawable extends Drawable {
    private Rect bounds;
    private final Paint patternPaint;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public AlphaPatternDrawable(int i) {
        this(i, -1, -3421237);
    }

    public AlphaPatternDrawable(int i, int i2, int i3) {
        i = i < 1 ? 1 : i;
        int i4 = i * 2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i4, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, i, i);
        paint.setColor(i2);
        canvas.drawRect(rect, paint);
        rect.offset(i, i);
        canvas.drawRect(rect, paint);
        paint.setColor(i3);
        int i5 = -i;
        rect.offset(i5, 0);
        canvas.drawRect(rect, paint);
        rect.offset(i, i5);
        canvas.drawRect(rect, paint);
        BitmapShader bitmapShader = new BitmapShader(bitmapCreateBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Paint paint2 = new Paint();
        this.patternPaint = paint2;
        paint2.setShader(bitmapShader);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect rect = this.bounds;
        Paint paint = this.patternPaint;
        if (rect == null) {
            canvas.drawPaint(paint);
        } else {
            canvas.drawRect(rect, paint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        throw new UnsupportedOperationException("Alpha is not supported by this drawable.");
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException("ColorFilter is not supported by this drawable.");
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.bounds = new Rect(rect);
    }
}

package qrcode.render.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BitmapGraphics.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0004J(\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J8\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J8\u0010%\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J0\u0010&\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\u0010\u0010'\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J@\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J8\u0010*\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J8\u0010+\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J0\u0010,\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J\"\u0010-\u001a\u00020 2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J \u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\b\u00102\u001a\u00020\u0006H\u0016J\u0018\u00103\u001a\u00020/2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0003H\u0016J\u0010\u00107\u001a\u0002082\u0006\u00104\u001a\u000205H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lqrcode/render/graphics/BitmapGraphics;", "Lqrcode/render/graphics/AndroidDrawingInterface;", "width", "", "height", "image", "Landroid/graphics/Bitmap;", "<init>", "(IILandroid/graphics/Bitmap;)V", "getWidth", "()I", "getHeight", "getImage", "()Landroid/graphics/Bitmap;", "canvas", "Landroid/graphics/Canvas;", "getCanvas", "()Landroid/graphics/Canvas;", "paintCache", "", "Landroid/graphics/Paint;", "paintFromCache", "color", "paintStyle", "Landroid/graphics/Paint$Style;", "thickness", "", "rectF", "Landroid/graphics/RectF;", "x", "y", "drawLine", "", "x1", "y1", "x2", "y2", "drawRect", "fillRect", "fill", "drawRoundRect", "borderRadius", "fillRoundRect", "drawEllipse", "fillEllipse", "drawImage", "rawData", "", "drawBitmap", "img", "nativeImage", "getBytes", "format", "", "quality", "toCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class BitmapGraphics implements AndroidDrawingInterface {
    private final Canvas canvas;
    private final int height;
    private final Bitmap image;
    private final Map<Integer, Paint> paintCache;
    private final int width;

    public BitmapGraphics(int i, int i2, Bitmap image) {
        Intrinsics.checkNotNullParameter(image, "image");
        this.width = i;
        this.height = i2;
        this.image = image;
        this.canvas = new Canvas(image);
        this.paintCache = new LinkedHashMap();
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public /* synthetic */ BitmapGraphics(int i, int i2, Bitmap bitmap, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888) : bitmap);
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final Canvas getCanvas() {
        return this.canvas;
    }

    public static /* synthetic */ Paint paintFromCache$default(BitmapGraphics bitmapGraphics, int i, Paint.Style style, double d, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paintFromCache");
        }
        if ((i2 & 2) != 0) {
            style = Paint.Style.FILL;
        }
        if ((i2 & 4) != 0) {
            d = 0.0d;
        }
        return bitmapGraphics.paintFromCache(i, style, d);
    }

    protected final Paint paintFromCache(int color, Paint.Style paintStyle, double thickness) {
        Intrinsics.checkNotNullParameter(paintStyle, "paintStyle");
        Map<Integer, Paint> map = this.paintCache;
        Integer numValueOf = Integer.valueOf(color);
        Paint paint = map.get(numValueOf);
        if (paint == null) {
            paint = new Paint();
            paint.setColor(color);
            map.put(numValueOf, paint);
        }
        Paint paint2 = paint;
        if (paint2.getStyle() != paintStyle) {
            paint2.setStyle(paintStyle);
        }
        paint2.setStrokeWidth((float) thickness);
        return paint2;
    }

    private final RectF rectF(int x, int y, int width, int height) {
        return new RectF(x, y, x + width, y + height);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawLine(int x1, int y1, int x2, int y2, int color, double thickness) {
        this.canvas.drawLine(x1, y1, x2, y2, paintFromCache(color, Paint.Style.STROKE, thickness));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawRect(int x, int y, int width, int height, int color, double thickness) {
        int iRoundToInt = MathKt.roundToInt(thickness / 2.0d);
        this.canvas.drawRect(new Rect(x + iRoundToInt, y + iRoundToInt, (x + width) - iRoundToInt, (y + height) - iRoundToInt), paintFromCache(color, Paint.Style.STROKE, thickness));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillRect(int x, int y, int width, int height, int color) {
        this.canvas.drawRect(new Rect(x, y, width + x, height + y), paintFromCache$default(this, color, null, 0.0d, 6, null));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fill(int color) {
        fillRect(0, 0, this.width, this.height, color);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawRoundRect(int x, int y, int width, int height, int borderRadius, int color, double thickness) {
        float f = borderRadius;
        int iRoundToInt = MathKt.roundToInt(thickness / 2.0d);
        int i = x + iRoundToInt;
        int i2 = y + iRoundToInt;
        int i3 = iRoundToInt * 2;
        this.canvas.drawRoundRect(rectF(i, i2, width - i3, height - i3), f, f, paintFromCache(color, Paint.Style.STROKE, thickness));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillRoundRect(int x, int y, int width, int height, int borderRadius, int color) {
        float f = borderRadius;
        this.canvas.drawRoundRect(rectF(x, y, width, height), f, f, paintFromCache$default(this, color, null, 0.0d, 6, null));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawEllipse(int x, int y, int width, int height, int color, double thickness) {
        this.canvas.drawOval(rectF(x, y, width, height), paintFromCache(color, Paint.Style.STROKE, thickness));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillEllipse(int x, int y, int width, int height, int color) {
        this.canvas.drawOval(rectF(x, y, width, height), paintFromCache$default(this, color, null, 0.0d, 6, null));
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawImage(byte[] rawData, int x, int y) {
        if (rawData != null) {
            if (rawData.length == 0) {
                return;
            }
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(rawData, 0, rawData.length);
            Intrinsics.checkNotNull(bitmapDecodeByteArray);
            drawBitmap(bitmapDecodeByteArray, x, y);
        }
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawBitmap(Bitmap img, int x, int y) {
        Intrinsics.checkNotNullParameter(img, "img");
        this.canvas.drawBitmap(img, x, y, (Paint) null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public Bitmap nativeImage() {
        return this.image;
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public byte[] getBytes(String format, int quality) {
        Intrinsics.checkNotNullParameter(format, "format");
        Bitmap.CompressFormat compressFormat = toCompressFormat(format);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.image.compress(compressFormat, RangesKt.coerceIn(quality, 0, 100), byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final Bitmap.CompressFormat toCompressFormat(String format) {
        try {
            String upperCase = format.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return Bitmap.CompressFormat.valueOf(upperCase);
        } catch (Throwable unused) {
            return Bitmap.CompressFormat.PNG;
        }
    }
}

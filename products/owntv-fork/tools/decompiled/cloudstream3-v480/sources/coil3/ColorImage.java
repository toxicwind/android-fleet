package coil3;

import android.graphics.Canvas;
import android.graphics.Paint;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColorImage.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0016J\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcoil3/ColorImage;", "Lcoil3/Image;", "color", "", "width", "height", ContentDisposition.Parameters.Size, "", "shareable", "", "<init>", "(IIIJZ)V", "getColor", "()I", "getWidth", "getHeight", "getSize", "()J", "getShareable", "()Z", "lazyPaint", "Landroid/graphics/Paint;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "Lcoil3/Canvas;", "equals", "other", "", "hashCode", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ColorImage implements Image {
    private final int color;
    private final int height;
    private Paint lazyPaint;
    private final boolean shareable;
    private final long size;
    private final int width;

    public ColorImage() {
        this(0, 0, 0, 0L, false, 31, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ColorImage)) {
            return false;
        }
        ColorImage colorImage = (ColorImage) other;
        return this.color == colorImage.color && this.width == colorImage.width && this.height == colorImage.height && this.size == colorImage.size && this.shareable == colorImage.shareable;
    }

    public int hashCode() {
        return (((((((this.color * 31) + this.width) * 31) + this.height) * 31) + UByte$$ExternalSyntheticBackport0.m(this.size)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.shareable);
    }

    public String toString() {
        return "ColorImage(color=" + this.color + ", width=" + this.width + ", height=" + this.height + ", size=" + this.size + ", shareable=" + this.shareable + ')';
    }

    public ColorImage(int i, int i2, int i3, long j, boolean z) {
        this.color = i;
        this.width = i2;
        this.height = i3;
        this.size = j;
        this.shareable = z;
    }

    public /* synthetic */ ColorImage(int i, int i2, int i3, long j, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? -16777216 : i, (i4 & 2) != 0 ? -1 : i2, (i4 & 4) != 0 ? -1 : i3, (i4 & 8) != 0 ? 0L : j, (i4 & 16) != 0 ? true : z);
    }

    public final int getColor() {
        return this.color;
    }

    @Override // coil3.Image
    public int getWidth() {
        return this.width;
    }

    @Override // coil3.Image
    public int getHeight() {
        return this.height;
    }

    @Override // coil3.Image
    public long getSize() {
        return this.size;
    }

    @Override // coil3.Image
    public boolean getShareable() {
        return this.shareable;
    }

    @Override // coil3.Image
    public void draw(Canvas canvas) {
        Paint paint = this.lazyPaint;
        if (paint == null) {
            paint = new Paint();
            paint.setColor(this.color);
            this.lazyPaint = paint;
        }
        Paint paint2 = paint;
        if (getWidth() >= 0 && getHeight() >= 0) {
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), paint2);
        } else {
            canvas.drawPaint(paint2);
        }
    }
}

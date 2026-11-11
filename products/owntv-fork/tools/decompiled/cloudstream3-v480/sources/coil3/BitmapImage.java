package coil3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import coil3.util.BitmapsKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Image.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0016J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013¨\u0006!"}, d2 = {"Lcoil3/BitmapImage;", "Lcoil3/Image;", "bitmap", "Landroid/graphics/Bitmap;", "shareable", "", "<init>", "(Landroid/graphics/Bitmap;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getShareable", "()Z", ContentDisposition.Parameters.Size, "", "getSize", "()J", "width", "", "getWidth", "()I", "height", "getHeight", "draw", "", "canvas", "Landroid/graphics/Canvas;", "Lcoil3/Canvas;", "equals", "other", "", "hashCode", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BitmapImage implements Image {
    private final Bitmap bitmap;
    private final boolean shareable;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BitmapImage)) {
            return false;
        }
        BitmapImage bitmapImage = (BitmapImage) other;
        return Intrinsics.areEqual(this.bitmap, bitmapImage.bitmap) && this.shareable == bitmapImage.shareable;
    }

    public int hashCode() {
        return (this.bitmap.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.shareable);
    }

    public String toString() {
        return "BitmapImage(bitmap=" + this.bitmap + ", shareable=" + this.shareable + ')';
    }

    public BitmapImage(Bitmap bitmap, boolean z) {
        this.bitmap = bitmap;
        this.shareable = z;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    @Override // coil3.Image
    public boolean getShareable() {
        return this.shareable;
    }

    @Override // coil3.Image
    public long getSize() {
        return BitmapsKt.getAllocationByteCountCompat(this.bitmap);
    }

    @Override // coil3.Image
    public int getWidth() {
        return this.bitmap.getWidth();
    }

    @Override // coil3.Image
    public int getHeight() {
        return this.bitmap.getHeight();
    }

    @Override // coil3.Image
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
    }
}

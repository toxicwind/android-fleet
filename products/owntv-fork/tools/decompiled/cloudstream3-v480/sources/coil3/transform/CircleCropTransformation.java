package coil3.transform;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.tvprovider.media.tv.TvContractCompat;
import coil3.size.Size;
import coil3.util.BitmapsKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CircleCropTransformation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcoil3/transform/CircleCropTransformation;", "Lcoil3/transform/Transformation;", "<init>", "()V", "cacheKey", "", "getCacheKey", "()Ljava/lang/String;", "transform", "Landroid/graphics/Bitmap;", TvContractCompat.PARAM_INPUT, ContentDisposition.Parameters.Size, "Lcoil3/size/Size;", "(Landroid/graphics/Bitmap;Lcoil3/size/Size;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CircleCropTransformation extends Transformation {
    private final String cacheKey = String.valueOf(Reflection.getOrCreateKotlinClass(getClass()).getQualifiedName());

    @Override // coil3.transform.Transformation
    public String getCacheKey() {
        return this.cacheKey;
    }

    @Override // coil3.transform.Transformation
    public Object transform(Bitmap bitmap, Size size, Continuation<? super Bitmap> continuation) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, BitmapsKt.getSafeConfig(bitmap));
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paintNewScaledShaderPaint = TransformationsKt.newScaledShaderPaint(bitmap, iMin, iMin);
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paintNewScaledShaderPaint);
        return bitmapCreateBitmap;
    }
}

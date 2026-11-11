package coil3.transform;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.tvprovider.media.tv.TvContractCompat;
import coil3.decode.DecodeUtils;
import coil3.size.Dimension;
import coil3.size.Scale;
import coil3.size.Size;
import coil3.size.SizeKt;
import coil3.util.BitmapsKt;
import coil3.util.IntPair;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: RoundedCornersTransformation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB\u0013\b\u0016\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\nJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcoil3/transform/RoundedCornersTransformation;", "Lcoil3/transform/Transformation;", "topLeft", "", "topRight", "bottomLeft", "bottomRight", "<init>", "(FFFF)V", "radius", "(F)V", "cacheKey", "", "getCacheKey", "()Ljava/lang/String;", "transform", "Landroid/graphics/Bitmap;", TvContractCompat.PARAM_INPUT, ContentDisposition.Parameters.Size, "Lcoil3/size/Size;", "(Landroid/graphics/Bitmap;Lcoil3/size/Size;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateOutputSize", "Lcoil3/util/IntPair;", "calculateOutputSize-nmZnaPc", "(Landroid/graphics/Bitmap;Lcoil3/size/Size;)J", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoundedCornersTransformation extends Transformation {
    private final float bottomLeft;
    private final float bottomRight;
    private final String cacheKey;
    private final float topLeft;
    private final float topRight;

    public RoundedCornersTransformation() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 15, null);
    }

    public /* synthetic */ RoundedCornersTransformation(float f, float f2, float f3, float f4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0.0f : f2, (i & 4) != 0 ? 0.0f : f3, (i & 8) != 0 ? 0.0f : f4);
    }

    public RoundedCornersTransformation(float f, float f2, float f3, float f4) {
        this.topLeft = f;
        this.topRight = f2;
        this.bottomLeft = f3;
        this.bottomRight = f4;
        if (f < 0.0f || f2 < 0.0f || f3 < 0.0f || f4 < 0.0f) {
            throw new IllegalArgumentException("All radii must be >= 0.".toString());
        }
        this.cacheKey = Reflection.getOrCreateKotlinClass(getClass()).getQualifiedName() + '-' + f + AbstractJsonLexerKt.COMMA + f2 + AbstractJsonLexerKt.COMMA + f3 + AbstractJsonLexerKt.COMMA + f4;
    }

    public RoundedCornersTransformation(float f) {
        this(f, f, f, f);
    }

    @Override // coil3.transform.Transformation
    public String getCacheKey() {
        return this.cacheKey;
    }

    @Override // coil3.transform.Transformation
    public Object transform(Bitmap bitmap, Size size, Continuation<? super Bitmap> continuation) {
        long jM8140calculateOutputSizenmZnaPc = m8140calculateOutputSizenmZnaPc(bitmap, size);
        int iM8148getFirstimpl = IntPair.m8148getFirstimpl(jM8140calculateOutputSizenmZnaPc);
        int iM8149getSecondimpl = IntPair.m8149getSecondimpl(jM8140calculateOutputSizenmZnaPc);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iM8148getFirstimpl, iM8149getSecondimpl, BitmapsKt.getSafeConfig(bitmap));
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paintNewScaledShaderPaint = TransformationsKt.newScaledShaderPaint(bitmap, iM8148getFirstimpl, iM8149getSecondimpl);
        float f = this.topLeft;
        float f2 = this.topRight;
        if (f == f2) {
            float f3 = this.bottomLeft;
            if (f2 == f3 && f3 == this.bottomRight) {
                canvas.drawRoundRect(0.0f, 0.0f, iM8148getFirstimpl, iM8149getSecondimpl, f, f, paintNewScaledShaderPaint);
                return bitmapCreateBitmap;
            }
        }
        float f4 = this.bottomRight;
        float f5 = this.bottomLeft;
        float[] fArr = {f, f, f2, f2, f4, f4, f5, f5};
        RectF rectF = new RectF(0.0f, 0.0f, iM8148getFirstimpl, iM8149getSecondimpl);
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paintNewScaledShaderPaint);
        return bitmapCreateBitmap;
    }

    /* JADX INFO: renamed from: calculateOutputSize-nmZnaPc, reason: not valid java name */
    private final long m8140calculateOutputSizenmZnaPc(Bitmap input, Size size) {
        if (SizeKt.isOriginal(size)) {
            return IntPair.m8144constructorimpl(input.getWidth(), input.getHeight());
        }
        Dimension width = size.getWidth();
        Dimension height = size.getHeight();
        if ((width instanceof Dimension.Pixels) && (height instanceof Dimension.Pixels)) {
            return IntPair.m8144constructorimpl(((Dimension.Pixels) width).m8129unboximpl(), ((Dimension.Pixels) height).m8129unboximpl());
        }
        int width2 = input.getWidth();
        int height2 = input.getHeight();
        Dimension width3 = size.getWidth();
        int iM8129unboximpl = width3 instanceof Dimension.Pixels ? ((Dimension.Pixels) width3).m8129unboximpl() : Integer.MIN_VALUE;
        Dimension height3 = size.getHeight();
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width2, height2, iM8129unboximpl, height3 instanceof Dimension.Pixels ? ((Dimension.Pixels) height3).m8129unboximpl() : Integer.MIN_VALUE, Scale.FILL);
        return IntPair.m8144constructorimpl(MathKt.roundToInt(((double) input.getWidth()) * dComputeSizeMultiplier), MathKt.roundToInt(dComputeSizeMultiplier * ((double) input.getHeight())));
    }
}

package coil3.transform;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.tvprovider.media.tv.TvContractCompat;
import coil3.decode.DecodeUtils;
import coil3.size.Scale;
import kotlin.Metadata;

/* JADX INFO: compiled from: transformations.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¨\u0006\u0007"}, d2 = {"newScaledShaderPaint", "Landroid/graphics/Paint;", TvContractCompat.PARAM_INPUT, "Landroid/graphics/Bitmap;", "outputWidth", "", "outputHeight", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class TransformationsKt {
    public static final Paint newScaledShaderPaint(Bitmap bitmap, int i, int i2) {
        Matrix matrix = new Matrix();
        float fComputeSizeMultiplier = (float) DecodeUtils.computeSizeMultiplier(bitmap.getWidth(), bitmap.getHeight(), i, i2, Scale.FILL);
        matrix.setTranslate((i - (bitmap.getWidth() * fComputeSizeMultiplier)) / 2.0f, (i2 - (bitmap.getHeight() * fComputeSizeMultiplier)) / 2.0f);
        matrix.preScale(fComputeSizeMultiplier, fComputeSizeMultiplier);
        Paint paint = new Paint(3);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        return paint;
    }
}

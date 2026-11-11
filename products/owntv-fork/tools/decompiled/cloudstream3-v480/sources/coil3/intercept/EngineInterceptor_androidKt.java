package coil3.intercept;

import android.graphics.Bitmap;
import coil3.BitmapImage;
import coil3.Image;
import coil3.Image_androidKt;
import coil3.request.ImageRequests_androidKt;
import coil3.request.Options;
import coil3.size.Precision;
import coil3.transform.Transformation;
import coil3.util.BitmapsKt;
import coil3.util.DrawableUtils;
import coil3.util.Logger;
import coil3.util.Utils_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EngineInterceptor.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0000¨\u0006\u000b"}, d2 = {"convertImageToBitmap", "Landroid/graphics/Bitmap;", "image", "Lcoil3/Image;", "options", "Lcoil3/request/Options;", "transformations", "", "Lcoil3/transform/Transformation;", "logger", "Lcoil3/util/Logger;", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class EngineInterceptor_androidKt {
    public static final Bitmap convertImageToBitmap(Image image, Options options, List<? extends Transformation> list, Logger logger) {
        if (image instanceof BitmapImage) {
            Bitmap bitmap = ((BitmapImage) image).getBitmap();
            Bitmap.Config safeConfig = BitmapsKt.getSafeConfig(bitmap);
            if (ArraysKt.contains(Utils_androidKt.getVALID_TRANSFORMATION_CONFIGS(), safeConfig)) {
                return bitmap;
            }
            if (logger != null) {
                Logger.Level level = Logger.Level.Info;
                if (logger.getMinLevel().compareTo(level) <= 0) {
                    logger.log(EngineInterceptor.TAG, level, "Converting bitmap with config " + safeConfig + " to apply transformations: " + list + '.', null);
                }
            }
        } else if (logger != null) {
            Logger.Level level2 = Logger.Level.Info;
            if (logger.getMinLevel().compareTo(level2) <= 0) {
                logger.log(EngineInterceptor.TAG, level2, "Converting image of type " + Reflection.getOrCreateKotlinClass(image.getClass()).getQualifiedName() + " to apply transformations: " + list + '.', null);
            }
        }
        return DrawableUtils.INSTANCE.convertToBitmap(Image_androidKt.asDrawable(image, options.getContext().getResources()), ImageRequests_androidKt.getBitmapConfig(options), options.getSize(), options.getScale(), options.getPrecision() == Precision.INEXACT);
    }
}

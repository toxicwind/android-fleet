package coil3;

import coil3.decode.ExifOrientationStrategy;
import kotlin.Metadata;

/* JADX INFO: compiled from: imageLoaders.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0014\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\"\u0018\u0010\u0000\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\t\u001a\u00020\u000b*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u000f\u001a\u00020\u0011*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0015\u001a\u00020\u0017*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"bitmapFactoryMaxParallelism", "Lcoil3/ImageLoader$Builder;", "maxParallelism", "", "Lcoil3/RealImageLoader$Options;", "getBitmapFactoryMaxParallelism", "(Lcoil3/RealImageLoader$Options;)I", "bitmapFactoryMaxParallelismKey", "Lcoil3/Extras$Key;", "bitmapFactoryExifOrientationStrategy", "strategy", "Lcoil3/decode/ExifOrientationStrategy;", "getBitmapFactoryExifOrientationStrategy", "(Lcoil3/RealImageLoader$Options;)Lcoil3/decode/ExifOrientationStrategy;", "bitmapFactoryExifOrientationStrategyKey", "imageDecoderEnabled", "enabled", "", "getImageDecoderEnabled", "(Lcoil3/RealImageLoader$Options;)Z", "imageDecoderEnabledKey", "memoryCacheMaxSizePercentWhileInBackground", "percent", "", "getMemoryCacheMaxSizePercentWhileInBackground", "(Lcoil3/RealImageLoader$Options;)D", "memoryCacheMaxSizePercentWhileInBackgroundKey", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ImageLoaders_androidKt {
    private static final Extras.Key<Integer> bitmapFactoryMaxParallelismKey = new Extras.Key<>(4);
    private static final Extras.Key<ExifOrientationStrategy> bitmapFactoryExifOrientationStrategyKey = new Extras.Key<>(ExifOrientationStrategy.RESPECT_PERFORMANCE);
    private static final Extras.Key<Boolean> imageDecoderEnabledKey = new Extras.Key<>(true);
    private static final Extras.Key<Double> memoryCacheMaxSizePercentWhileInBackgroundKey = new Extras.Key<>(Double.valueOf(1.0d));

    public static final ImageLoader.Builder bitmapFactoryMaxParallelism(ImageLoader.Builder builder, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxParallelism must be > 0.".toString());
        }
        builder.getExtras().set(bitmapFactoryMaxParallelismKey, Integer.valueOf(i));
        return builder;
    }

    public static final int getBitmapFactoryMaxParallelism(RealImageLoader.Options options) {
        return ((Number) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), bitmapFactoryMaxParallelismKey)).intValue();
    }

    public static final ImageLoader.Builder bitmapFactoryExifOrientationStrategy(ImageLoader.Builder builder, ExifOrientationStrategy exifOrientationStrategy) {
        builder.getExtras().set(bitmapFactoryExifOrientationStrategyKey, exifOrientationStrategy);
        return builder;
    }

    public static final ExifOrientationStrategy getBitmapFactoryExifOrientationStrategy(RealImageLoader.Options options) {
        return (ExifOrientationStrategy) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), bitmapFactoryExifOrientationStrategyKey);
    }

    public static final ImageLoader.Builder imageDecoderEnabled(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(imageDecoderEnabledKey, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getImageDecoderEnabled(RealImageLoader.Options options) {
        return ((Boolean) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), imageDecoderEnabledKey)).booleanValue();
    }

    public static final ImageLoader.Builder memoryCacheMaxSizePercentWhileInBackground(ImageLoader.Builder builder, double d) {
        if (0.0d > d || d > 1.0d) {
            throw new IllegalArgumentException("percent must be in the range [0.0, 1.0].".toString());
        }
        builder.getExtras().set(memoryCacheMaxSizePercentWhileInBackgroundKey, Double.valueOf(d));
        return builder;
    }

    public static final double getMemoryCacheMaxSizePercentWhileInBackground(RealImageLoader.Options options) {
        return ((Number) ExtrasKt.getOrDefault(options.getDefaults().getExtras(), memoryCacheMaxSizePercentWhileInBackgroundKey)).doubleValue();
    }
}

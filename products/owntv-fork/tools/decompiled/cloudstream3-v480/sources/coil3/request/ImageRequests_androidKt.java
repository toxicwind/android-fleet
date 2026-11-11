package coil3.request;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.compose.ui.graphics.Api26Bitmap$$ExternalSyntheticApiModelOutline0;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import coil3.Extras;
import coil3.ExtrasKt;
import coil3.ImageLoader;
import coil3.Image_androidKt;
import coil3.target.ImageViewTarget;
import coil3.transition.CrossfadeTransition;
import coil3.transition.Transition;
import coil3.util.ContextsKt;
import coil3.util.Utils_androidKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: imageRequests.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a%\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\f\"\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\u001a\u001a\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0007\u001a\u0012\u0010\u001c\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0006\u001a\u0012\u0010\u001c\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0006\u001a\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002\u001a\u0012\u0010$\u001a\u00020\u0001*\u00020\u00012\u0006\u0010%\u001a\u00020 \u001a\u0012\u0010$\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010%\u001a\u00020 \u001a\u0012\u0010)\u001a\u00020\u0001*\u00020\u00012\u0006\u0010*\u001a\u00020+\u001a\u0012\u0010)\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010*\u001a\u00020+\u001a\u0014\u00100\u001a\u00020\u0001*\u00020\u00012\u0006\u00100\u001a\u000201H\u0007\u001a\u0014\u00100\u001a\u00020\u001e*\u00020\u001e2\u0006\u00100\u001a\u000201H\u0007\u001a\u0012\u00106\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u000208\u001a\u0012\u00106\u001a\u00020\u001e*\u00020\u001e2\u0006\u00107\u001a\u000208\u001a\u0014\u0010=\u001a\u00020\u0001*\u00020\u00012\b\u0010>\u001a\u0004\u0018\u00010?\u001a\u0014\u0010=\u001a\u00020\u0001*\u00020\u00012\b\u0010=\u001a\u0004\u0018\u00010@\u001a\u0014\u0010E\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u000208H\u0007\u001a\u0014\u0010E\u001a\u00020\u001e*\u00020\u001e2\u0006\u00107\u001a\u000208H\u0007\u001a\u0012\u0010H\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u000208\u001a\u0012\u0010H\u001a\u00020\u001e*\u00020\u001e2\u0006\u00107\u001a\u000208\u001a\u0012\u0010K\u001a\u00020\u0001*\u00020\u00012\u0006\u00107\u001a\u000208\u001a\u0012\u0010K\u001a\u00020\u001e*\u00020\u001e2\u0006\u00107\u001a\u000208\"$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\u000f*\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\u000f*\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0016\u001a\u0004\b\u0013\u0010\u0017\"*\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000f0\u0018*\u00020\u00198FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u001a\u001a\u0004\b\u0013\u0010\u001b\"\u0015\u0010!\u001a\u00020\u0006*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0015\u0010$\u001a\u00020 *\u00020\u00108F¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u001b\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\b&\u0010\u001b\"\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020 0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010)\u001a\u00020+*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b,\u0010-\"\u0015\u0010)\u001a\u00020+*\u00020\u00158F¢\u0006\u0006\u001a\u0004\b,\u0010.\"\u001b\u0010)\u001a\b\u0012\u0004\u0012\u00020+0\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\b,\u0010\u001b\"\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020+0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u00100\u001a\u0004\u0018\u000101*\u00020\u00108G¢\u0006\u0006\u001a\u0004\b2\u00103\"\u0017\u00100\u001a\u0004\u0018\u000101*\u00020\u00158G¢\u0006\u0006\u001a\u0004\b2\u00104\"\u001d\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u0018*\u00020\u00198G¢\u0006\u0006\u001a\u0004\b2\u0010\u001b\"\u0016\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u00106\u001a\u000208*\u00020\u00108F¢\u0006\u0006\u001a\u0004\b9\u0010:\"\u0015\u00106\u001a\u000208*\u00020\u00158F¢\u0006\u0006\u001a\u0004\b9\u0010;\"\u001b\u00106\u001a\b\u0012\u0004\u0012\u0002080\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\b9\u0010\u001b\"\u0014\u0010<\u001a\b\u0012\u0004\u0012\u0002080\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010=\u001a\u0004\u0018\u00010@*\u00020\u00108F¢\u0006\u0006\u001a\u0004\bA\u0010B\"\u0017\u0010=\u001a\u0004\u0018\u00010@*\u00020\u00158F¢\u0006\u0006\u001a\u0004\bA\u0010C\"\u001d\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010@0\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\bA\u0010\u001b\"\u0016\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010@0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001e\u0010E\u001a\u000208*\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\bF\u0010\u0012\u001a\u0004\bG\u0010:\"\u001e\u0010E\u001a\u000208*\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\bF\u0010\u0016\u001a\u0004\bG\u0010;\"$\u0010E\u001a\b\u0012\u0004\u0012\u0002080\u0018*\u00020\u00198FX\u0087\u0004¢\u0006\f\u0012\u0004\bF\u0010\u001a\u001a\u0004\bG\u0010\u001b\"\u0015\u0010H\u001a\u000208*\u00020\u00108F¢\u0006\u0006\u001a\u0004\bI\u0010:\"\u0015\u0010H\u001a\u000208*\u00020\u00158F¢\u0006\u0006\u001a\u0004\bI\u0010;\"\u001b\u0010H\u001a\b\u0012\u0004\u0012\u0002080\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\bI\u0010\u001b\"\u0014\u0010J\u001a\b\u0012\u0004\u0012\u0002080\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010K\u001a\u000208*\u00020\u00108F¢\u0006\u0006\u001a\u0004\bL\u0010:\"\u0015\u0010K\u001a\u000208*\u00020\u00158F¢\u0006\u0006\u001a\u0004\bL\u0010;\"\u001b\u0010K\u001a\b\u0012\u0004\u0012\u0002080\u0018*\u00020\u00198F¢\u0006\u0006\u001a\u0004\bL\u0010\u001b\"\u0014\u0010M\u001a\b\u0012\u0004\u0012\u0002080\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {TypedValues.AttributesType.S_TARGET, "Lcoil3/request/ImageRequest$Builder;", "imageView", "Landroid/widget/ImageView;", "placeholder", "drawableResId", "", "drawable", "Landroid/graphics/drawable/Drawable;", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "fallback", "transformations", "", "Lcoil3/transform/Transformation;", "(Lcoil3/request/ImageRequest$Builder;[Lcoil3/transform/Transformation;)Lcoil3/request/ImageRequest$Builder;", "", "Lcoil3/request/ImageRequest;", "getTransformations$annotations", "(Lcoil3/request/ImageRequest;)V", "getTransformations", "(Lcoil3/request/ImageRequest;)Ljava/util/List;", "Lcoil3/request/Options;", "(Lcoil3/request/Options;)V", "(Lcoil3/request/Options;)Ljava/util/List;", "Lcoil3/Extras$Key;", "Lcoil3/Extras$Key$Companion;", "(Lcoil3/Extras$Key$Companion;)V", "(Lcoil3/Extras$Key$Companion;)Lcoil3/Extras$Key;", "crossfade", "durationMillis", "Lcoil3/ImageLoader$Builder;", "newCrossfadeTransitionFactory", "Lcoil3/transition/Transition$Factory;", "crossfadeMillis", "getCrossfadeMillis", "(Lcoil3/request/ImageRequest;)I", "transitionFactory", "factory", "getTransitionFactory", "(Lcoil3/request/ImageRequest;)Lcoil3/transition/Transition$Factory;", "transitionFactoryKey", "bitmapConfig", "config", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "(Lcoil3/request/ImageRequest;)Landroid/graphics/Bitmap$Config;", "(Lcoil3/request/Options;)Landroid/graphics/Bitmap$Config;", "bitmapConfigKey", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(Lcoil3/request/ImageRequest;)Landroid/graphics/ColorSpace;", "(Lcoil3/request/Options;)Landroid/graphics/ColorSpace;", "colorSpaceKey", "premultipliedAlpha", "enable", "", "getPremultipliedAlpha", "(Lcoil3/request/ImageRequest;)Z", "(Lcoil3/request/Options;)Z", "premultipliedAlphaKey", "lifecycle", "owner", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "(Lcoil3/request/ImageRequest;)Landroidx/lifecycle/Lifecycle;", "(Lcoil3/request/Options;)Landroidx/lifecycle/Lifecycle;", "lifecycleKey", "allowConversionToBitmap", "getAllowConversionToBitmap$annotations", "getAllowConversionToBitmap", "allowHardware", "getAllowHardware", "allowHardwareKey", "allowRgb565", "getAllowRgb565", "allowRgb565Key", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ImageRequests_androidKt {
    private static final Extras.Key<Transition.Factory> transitionFactoryKey = new Extras.Key<>(Transition.Factory.NONE);
    private static final Extras.Key<Bitmap.Config> bitmapConfigKey = new Extras.Key<>(Utils_androidKt.getDEFAULT_BITMAP_CONFIG());
    private static final Extras.Key<ColorSpace> colorSpaceKey = new Extras.Key<>(Utils_androidKt.getNULL_COLOR_SPACE());
    private static final Extras.Key<Boolean> premultipliedAlphaKey = new Extras.Key<>(true);
    private static final Extras.Key<Lifecycle> lifecycleKey = new Extras.Key<>(null);
    private static final Extras.Key<Boolean> allowHardwareKey = new Extras.Key<>(true);
    private static final Extras.Key<Boolean> allowRgb565Key = new Extras.Key<>(false);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getAllowConversionToBitmap$annotations(Extras.Key.Companion companion) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getAllowConversionToBitmap$annotations(ImageRequest imageRequest) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getAllowConversionToBitmap$annotations(Options options) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getTransformations$annotations(Extras.Key.Companion companion) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getTransformations$annotations(ImageRequest imageRequest) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static /* synthetic */ void getTransformations$annotations(Options options) {
    }

    public static final ImageRequest.Builder target(ImageRequest.Builder builder, ImageView imageView) {
        return builder.target(new ImageViewTarget(imageView));
    }

    public static final ImageRequest.Builder placeholder(ImageRequest.Builder builder, final int i) {
        return builder.placeholder(new Function1() { // from class: coil3.request.ImageRequests_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Image_androidKt.asImage(ContextsKt.getDrawableCompat(((ImageRequest) obj).getContext(), i));
            }
        });
    }

    public static final ImageRequest.Builder placeholder(ImageRequest.Builder builder, Drawable drawable) {
        return builder.placeholder(drawable != null ? Image_androidKt.asImage(drawable) : null);
    }

    public static final ImageRequest.Builder error(ImageRequest.Builder builder, final int i) {
        return builder.error(new Function1() { // from class: coil3.request.ImageRequests_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Image_androidKt.asImage(ContextsKt.getDrawableCompat(((ImageRequest) obj).getContext(), i));
            }
        });
    }

    public static final ImageRequest.Builder error(ImageRequest.Builder builder, Drawable drawable) {
        return builder.error(drawable != null ? Image_androidKt.asImage(drawable) : null);
    }

    public static final ImageRequest.Builder fallback(ImageRequest.Builder builder, final int i) {
        return builder.fallback(new Function1() { // from class: coil3.request.ImageRequests_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Image_androidKt.asImage(ContextsKt.getDrawableCompat(((ImageRequest) obj).getContext(), i));
            }
        });
    }

    public static final ImageRequest.Builder fallback(ImageRequest.Builder builder, Drawable drawable) {
        return builder.fallback(drawable != null ? Image_androidKt.asImage(drawable) : null);
    }

    public static final ImageRequest.Builder crossfade(ImageRequest.Builder builder, int i) {
        return transitionFactory(builder, newCrossfadeTransitionFactory(i));
    }

    public static final ImageLoader.Builder crossfade(ImageLoader.Builder builder, int i) {
        return transitionFactory(builder, newCrossfadeTransitionFactory(i));
    }

    private static final Transition.Factory newCrossfadeTransitionFactory(int i) {
        if (i > 0) {
            return new CrossfadeTransition.Factory(i, false, 2, null);
        }
        return Transition.Factory.NONE;
    }

    public static final int getCrossfadeMillis(ImageRequest imageRequest) {
        Transition.Factory transitionFactory = getTransitionFactory(imageRequest);
        CrossfadeTransition.Factory factory = transitionFactory instanceof CrossfadeTransition.Factory ? (CrossfadeTransition.Factory) transitionFactory : null;
        if (factory != null) {
            return factory.getDurationMillis();
        }
        return 0;
    }

    public static final ImageRequest.Builder transitionFactory(ImageRequest.Builder builder, Transition.Factory factory) {
        builder.getExtras().set(transitionFactoryKey, factory);
        return builder;
    }

    public static final ImageLoader.Builder transitionFactory(ImageLoader.Builder builder, Transition.Factory factory) {
        builder.getExtras().set(transitionFactoryKey, factory);
        return builder;
    }

    public static final Transition.Factory getTransitionFactory(ImageRequest imageRequest) {
        return (Transition.Factory) ExtrasKt.getExtra(imageRequest, transitionFactoryKey);
    }

    public static final Extras.Key<Transition.Factory> getTransitionFactory(Extras.Key.Companion companion) {
        return transitionFactoryKey;
    }

    public static final ImageRequest.Builder bitmapConfig(ImageRequest.Builder builder, Bitmap.Config config) {
        builder.getExtras().set(bitmapConfigKey, config);
        return builder;
    }

    public static final ImageLoader.Builder bitmapConfig(ImageLoader.Builder builder, Bitmap.Config config) {
        builder.getExtras().set(bitmapConfigKey, config);
        return builder;
    }

    public static final Bitmap.Config getBitmapConfig(ImageRequest imageRequest) {
        return (Bitmap.Config) ExtrasKt.getExtra(imageRequest, bitmapConfigKey);
    }

    public static final Bitmap.Config getBitmapConfig(Options options) {
        return (Bitmap.Config) ExtrasKt.getExtra(options, bitmapConfigKey);
    }

    public static final Extras.Key<Bitmap.Config> getBitmapConfig(Extras.Key.Companion companion) {
        return bitmapConfigKey;
    }

    public static final ImageRequest.Builder colorSpace(ImageRequest.Builder builder, ColorSpace colorSpace) {
        builder.getExtras().set(colorSpaceKey, colorSpace);
        return builder;
    }

    public static final ImageLoader.Builder colorSpace(ImageLoader.Builder builder, ColorSpace colorSpace) {
        builder.getExtras().set(colorSpaceKey, colorSpace);
        return builder;
    }

    public static final ColorSpace getColorSpace(ImageRequest imageRequest) {
        return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4028m(ExtrasKt.getExtra(imageRequest, colorSpaceKey));
    }

    public static final ColorSpace getColorSpace(Options options) {
        return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4028m(ExtrasKt.getExtra(options, colorSpaceKey));
    }

    public static final Extras.Key<ColorSpace> getColorSpace(Extras.Key.Companion companion) {
        return colorSpaceKey;
    }

    public static final ImageRequest.Builder premultipliedAlpha(ImageRequest.Builder builder, boolean z) {
        builder.getExtras().set(premultipliedAlphaKey, Boolean.valueOf(z));
        return builder;
    }

    public static final ImageLoader.Builder premultipliedAlpha(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(premultipliedAlphaKey, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getPremultipliedAlpha(ImageRequest imageRequest) {
        return ((Boolean) ExtrasKt.getExtra(imageRequest, premultipliedAlphaKey)).booleanValue();
    }

    public static final boolean getPremultipliedAlpha(Options options) {
        return ((Boolean) ExtrasKt.getExtra(options, premultipliedAlphaKey)).booleanValue();
    }

    public static final Extras.Key<Boolean> getPremultipliedAlpha(Extras.Key.Companion companion) {
        return premultipliedAlphaKey;
    }

    public static final ImageRequest.Builder lifecycle(ImageRequest.Builder builder, LifecycleOwner lifecycleOwner) {
        return lifecycle(builder, lifecycleOwner != null ? lifecycleOwner.getLifecycle() : null);
    }

    public static final ImageRequest.Builder lifecycle(ImageRequest.Builder builder, Lifecycle lifecycle) {
        builder.getExtras().set(lifecycleKey, lifecycle);
        return builder;
    }

    public static final Lifecycle getLifecycle(ImageRequest imageRequest) {
        return (Lifecycle) ExtrasKt.getExtra(imageRequest, lifecycleKey);
    }

    public static final Lifecycle getLifecycle(Options options) {
        return (Lifecycle) ExtrasKt.getExtra(options, lifecycleKey);
    }

    public static final Extras.Key<Lifecycle> getLifecycle(Extras.Key.Companion companion) {
        return lifecycleKey;
    }

    public static final ImageRequest.Builder allowHardware(ImageRequest.Builder builder, boolean z) {
        builder.getExtras().set(allowHardwareKey, Boolean.valueOf(z));
        return builder;
    }

    public static final ImageLoader.Builder allowHardware(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(allowHardwareKey, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getAllowHardware(ImageRequest imageRequest) {
        return ((Boolean) ExtrasKt.getExtra(imageRequest, allowHardwareKey)).booleanValue();
    }

    public static final boolean getAllowHardware(Options options) {
        return ((Boolean) ExtrasKt.getExtra(options, allowHardwareKey)).booleanValue();
    }

    public static final Extras.Key<Boolean> getAllowHardware(Extras.Key.Companion companion) {
        return allowHardwareKey;
    }

    public static final ImageRequest.Builder allowRgb565(ImageRequest.Builder builder, boolean z) {
        builder.getExtras().set(allowRgb565Key, Boolean.valueOf(z));
        return builder;
    }

    public static final ImageLoader.Builder allowRgb565(ImageLoader.Builder builder, boolean z) {
        builder.getExtras().set(allowRgb565Key, Boolean.valueOf(z));
        return builder;
    }

    public static final boolean getAllowRgb565(ImageRequest imageRequest) {
        return ((Boolean) ExtrasKt.getExtra(imageRequest, allowRgb565Key)).booleanValue();
    }

    public static final boolean getAllowRgb565(Options options) {
        return ((Boolean) ExtrasKt.getExtra(options, allowRgb565Key)).booleanValue();
    }

    public static final Extras.Key<Boolean> getAllowRgb565(Extras.Key.Companion companion) {
        return allowRgb565Key;
    }
}

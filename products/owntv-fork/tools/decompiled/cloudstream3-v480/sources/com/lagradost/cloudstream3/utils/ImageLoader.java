package com.lagradost.cloudstream3.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import coil3.ComponentRegistry;
import coil3.EventListener;
import coil3.SingletonImageLoader;
import coil3.Uri;
import coil3.decode.BitmapFactoryDecoder;
import coil3.disk.DiskCache;
import coil3.memory.MemoryCache;
import coil3.network.ImageRequestsKt;
import coil3.network.NetworkHeaders;
import coil3.network.okhttp.OkHttpNetworkFetcher;
import coil3.request.CachePolicy;
import coil3.request.ErrorResult;
import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import coil3.util.CoilUtils;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.network.RequestsHelperKt;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okio.Path;

/* JADX INFO: compiled from: ImageModuleCoil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0000¢\u0006\u0002\b\u000bJ\u0011\u0010\f\u001a\u00020\r*\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJI\u0010\u0010\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00142\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u001c2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018JG\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00142\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018JG\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u001d2\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00142\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u001e2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018J6\u0010\u001b\u001a\u00020\r*\u00020\u00112\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u001f2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018¢\u0006\u0002\u0010 J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010!2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\"2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010#2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018J/\u0010\u001b\u001a\u00020\r*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010$2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\u0002\b\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ImageLoader;", "", "<init>", "()V", "TAG", "", "buildImageLoader", "Lcoil3/ImageLoader;", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "buildImageLoader$app", "setupCoilLogger", "", "Lcoil3/ImageLoader$Builder;", "setupCoilLogger$app", "loadImageInternal", "Landroid/widget/ImageView;", "imageData", "headers", "", "builder", "Lkotlin/Function1;", "Lcoil3/request/ImageRequest$Builder;", "Lkotlin/ExtensionFunctionType;", "hasPotentialBrokenHardware", "", "loadImage", "Lcom/lagradost/cloudstream3/utils/UiImage;", "Landroid/net/Uri;", "Ljava/io/File;", "", "(Landroid/widget/ImageView;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/Bitmap;", "", "Ljava/nio/ByteBuffer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ImageLoader {
    public static final ImageLoader INSTANCE = new ImageLoader();
    private static final String TAG = "CoilImgLoader";

    private ImageLoader() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final coil3.ImageLoader buildImageLoader$app(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean zHasPotentialBrokenHardware = hasPotentialBrokenHardware();
        coil3.ImageLoader.Builder builderDiskCache = ImageRequests_androidKt.allowHardware(ImageRequests_androidKt.crossfade(new coil3.ImageLoader.Builder(context), 200), Build.VERSION.SDK_INT >= 28 && !zHasPotentialBrokenHardware).diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).memoryCache(new Function0() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new MemoryCache.Builder().maxSizePercent(context, 0.1d).strongReferencesEnabled(false).build();
            }
        }).diskCache(new Function0() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ImageLoader.buildImageLoader$lambda$1(context);
            }
        });
        ComponentRegistry.Builder builder = new ComponentRegistry.Builder();
        builder.add(OkHttpNetworkFetcher.factory((Function0<? extends Call.Factory>) new Function0() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ImageLoader.buildImageLoader$lambda$2$0(context);
            }
        }), Reflection.getOrCreateKotlinClass(Uri.class));
        if (zHasPotentialBrokenHardware) {
            builder.add(new BitmapFactoryDecoder.Factory(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0));
        }
        coil3.ImageLoader.Builder builderComponents = builderDiskCache.components(builder.build());
        if (zHasPotentialBrokenHardware) {
            ImageRequests_androidKt.bitmapConfig(builderComponents, Bitmap.Config.ARGB_8888);
        }
        INSTANCE.setupCoilLogger$app(builderComponents);
        return builderComponents.build();
    }

    static final DiskCache buildImageLoader$lambda$1(Context context) {
        DiskCache.Builder builder = new DiskCache.Builder();
        Path.Companion companion = Path.INSTANCE;
        File cacheDir = context.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
        return builder.directory(Path.Companion.get$default(companion, FilesKt.resolve(cacheDir, "cs3_image_cache"), false, 1, (Object) null)).maxSizeBytes(536870912L).maxSizePercent(0.04d).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Call.Factory buildImageLoader$lambda$2$0(Context context) {
        return RequestsHelperKt.buildDefaultClient(context);
    }

    public final void setupCoilLogger$app(coil3.ImageLoader.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.eventListener(new EventListener() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$setupCoilLogger$1
            @Override // coil3.EventListener, coil3.request.ImageRequest.Listener
            public void onError(ImageRequest request, ErrorResult result) {
                Intrinsics.checkNotNullParameter(request, "request");
                Intrinsics.checkNotNullParameter(result, "result");
                super.onError(request, result);
                StringBuilder sb = new StringBuilder("Image load error: ");
                String message = result.getThrowable().getMessage();
                sb.append(message != null ? message : result.getThrowable());
                Log.e("CoilImgLoader", sb.toString());
                Log.e("CoilImgLoader", "  URL: " + request.getData());
                Log.e("CoilImgLoader", "  allowHardware: " + ImageRequests_androidKt.getAllowHardware(request));
                Log.e("CoilImgLoader", "  hardware: " + Build.HARDWARE + ", board: " + Build.BOARD);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void loadImageInternal$default(ImageLoader imageLoader, ImageView imageView, Object obj, Map map, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            map = null;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return ImageLoader.loadImageInternal$lambda$0((ImageRequest.Builder) obj3);
                }
            };
        }
        imageLoader.loadImageInternal(imageView, obj, map, function1);
    }

    static final Unit loadImageInternal$lambda$0(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0023  */
    /* JADX WARN: Code duplicated, block: B:16:0x0033  */
    /* JADX WARN: Code duplicated, block: B:6:0x0013  */
    private final boolean hasPotentialBrokenHardware() {
        String lowerCase;
        String lowerCase2;
        String lowerCase3;
        String str = Build.HARDWARE;
        String str2 = "";
        if (str != null) {
            lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase == null) {
                lowerCase = "";
            }
        } else {
            lowerCase = "";
        }
        String str3 = Build.BOARD;
        if (str3 != null) {
            lowerCase2 = str3.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (lowerCase2 == null) {
                lowerCase2 = "";
            }
        } else {
            lowerCase2 = "";
        }
        String str4 = Build.MODEL;
        if (str4 != null) {
            lowerCase3 = str4.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
            if (lowerCase3 == null) {
                lowerCase3 = "";
            }
        } else {
            lowerCase3 = "";
        }
        String str5 = Build.MANUFACTURER;
        if (str5 != null) {
            String lowerCase4 = str5.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
            if (lowerCase4 != null) {
                str2 = lowerCase4;
            }
        }
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"sun50iw9", "h713", "allwinner", "sunxi"});
        List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"hy320", "hy300", "a10plus", "magcubic", "sinoy", "android tv box"});
        List<String> list = listListOf;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (String str6 : list) {
                if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) str6, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) str6, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) str6, false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        List list2 = listListOf2;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) it.next(), false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, UiImage uiImage, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$0((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, uiImage, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$0(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, UiImage uiImage, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (uiImage instanceof UiImage.Image) {
            UiImage.Image image = (UiImage.Image) uiImage;
            loadImageInternal(imageView, image.getUrl(), image.getHeaders(), builder);
        } else if (uiImage instanceof UiImage.Bitmap) {
            loadImageInternal$default(this, imageView, ((UiImage.Bitmap) uiImage).getBitmap(), null, builder, 2, null);
        } else if (uiImage instanceof UiImage.Drawable) {
            loadImageInternal$default(this, imageView, Integer.valueOf(((UiImage.Drawable) uiImage).getResId()), null, builder, 2, null);
        } else {
            if (uiImage != null) {
                throw new NoWhenBranchMatchedException();
            }
            loadImageInternal$default(this, imageView, null, null, builder, 2, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, String str, Map map, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            map = null;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$1((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, str, (Map<String, String>) map, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$1(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, String str, Map<String, String> map, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal(imageView, str, map, builder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, android.net.Uri uri, Map map, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            map = null;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$2((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, uri, (Map<String, String>) map, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$2(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, android.net.Uri uri, Map<String, String> map, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal(imageView, uri, map, builder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, File file, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$3((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, file, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$3(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, File file, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, file, null, builder, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, Integer num, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$4((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, num, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$4(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, Integer num, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, num, null, builder, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, Drawable drawable, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$5((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, drawable, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$5(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, Drawable drawable, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, drawable, null, builder, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, Bitmap bitmap, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$6((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, bitmap, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$6(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, Bitmap bitmap, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, bitmap, null, builder, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, byte[] bArr, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$7((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, bArr, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$7(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, byte[] bArr, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, bArr, null, builder, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadImage$default(ImageLoader imageLoader, ImageView imageView, ByteBuffer byteBuffer, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.ImageLoader$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ImageLoader.loadImage$lambda$8((ImageRequest.Builder) obj2);
                }
            };
        }
        imageLoader.loadImage(imageView, byteBuffer, (Function1<? super ImageRequest.Builder, Unit>) function1);
    }

    static final Unit loadImage$lambda$8(ImageRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return Unit.INSTANCE;
    }

    public final void loadImage(ImageView imageView, ByteBuffer byteBuffer, Function1<? super ImageRequest.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        loadImageInternal$default(this, imageView, byteBuffer, null, builder, 2, null);
    }

    private final void loadImageInternal(ImageView imageView, Object obj, Map<String, String> map, Function1<? super ImageRequest.Builder, Unit> function1) {
        CoilUtils.dispose(imageView);
        if (obj == null) {
            return;
        }
        if (obj instanceof Integer) {
            imageView.setImageResource(((Number) obj).intValue());
            return;
        }
        Context context = imageView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        coil3.ImageLoader imageLoader = SingletonImageLoader.get(context);
        ImageRequest.Builder builderTarget = ImageRequests_androidKt.target(new ImageRequest.Builder(imageView.getContext()).data(obj), imageView);
        NetworkHeaders.Builder builder = new NetworkHeaders.Builder();
        builder.set("User-Agent", MainAPIKt.USER_AGENT);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.set(entry.getKey(), entry.getValue());
            }
        }
        ImageRequestsKt.httpHeaders(builderTarget, builder.build());
        function1.invoke(builderTarget);
        imageLoader.enqueue(builderTarget.build());
    }
}

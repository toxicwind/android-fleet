package coil3.util;

import coil3.ComponentRegistry;
import coil3.EventListener;
import coil3.Image;
import coil3.Uri;
import coil3.decode.DataSource;
import coil3.decode.Decoder;
import coil3.fetch.Fetcher;
import coil3.intercept.Interceptor;
import coil3.intercept.RealInterceptorChain;
import coil3.key.Keyer;
import coil3.request.ErrorResult;
import coil3.request.ImageRequest;
import coil3.request.NullRequestDataException;
import coil3.request.Options;
import java.io.Closeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010\u0005\u001a\u00020\u0006*\u00060\u0007j\u0002`\bH\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u0006*\u00060\tj\u0002`\nH\u0000\u001a0\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a*\u0010\u001a\u001a\u00020\u001b*\u00020\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0018\u00010\u001dH\u0000\u001a\u0016\u0010\u001a\u001a\u00020\u001b*\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0000\u001a\f\u0010/\u001a\u00020(*\u000200H\u0000\u001a\u0010\u00102\u001a\u00020(2\u0006\u00103\u001a\u000204H\u0000\u001a\u0018\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u000209H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u000e\u0010\"\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010'\u001a\u00020(*\u00020)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010*\"\u0018\u0010+\u001a\u00020,*\u00020)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u000e\u00101\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\" \u0010:\u001a\u0004\u0018\u00010;*\u00020<8@X\u0080\u0004¢\u0006\f\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010@¨\u0006A"}, d2 = {"emoji", "", "Lcoil3/decode/DataSource;", "getEmoji", "(Lcoil3/decode/DataSource;)Ljava/lang/String;", "closeQuietly", "", "Ljava/io/Closeable;", "Lokio/Closeable;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "EMPTY_IMAGE_FACTORY", "Lkotlin/Function1;", "Lcoil3/request/ImageRequest;", "Lcoil3/Image;", "getEMPTY_IMAGE_FACTORY", "()Lkotlin/jvm/functions/Function1;", "key", "Lcoil3/ComponentRegistry;", "data", "", "options", "Lcoil3/request/Options;", "logger", "Lcoil3/util/Logger;", "tag", "addFirst", "Lcoil3/ComponentRegistry$Builder;", "pair", "Lkotlin/Pair;", "Lcoil3/fetch/Fetcher$Factory;", "Lkotlin/reflect/KClass;", "factory", "Lcoil3/decode/Decoder$Factory;", "MIME_TYPE_JPEG", "MIME_TYPE_WEBP", "MIME_TYPE_HEIC", "MIME_TYPE_HEIF", "MIME_TYPE_XML", "isPlaceholderCached", "", "Lcoil3/intercept/Interceptor$Chain;", "(Lcoil3/intercept/Interceptor$Chain;)Z", "eventListener", "Lcoil3/EventListener;", "getEventListener", "(Lcoil3/intercept/Interceptor$Chain;)Lcoil3/EventListener;", "isMinOrMax", "", "SCHEME_FILE", "isFileUri", "uri", "Lcoil3/Uri;", "ErrorResult", "Lcoil3/request/ErrorResult;", "request", "throwable", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/CoroutineContext;", "getDispatcher$annotations", "(Lkotlin/coroutines/CoroutineContext;)V", "getDispatcher", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineDispatcher;", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    private static final Function1<ImageRequest, Image> EMPTY_IMAGE_FACTORY = new Function1() { // from class: coil3.util.UtilsKt$EMPTY_IMAGE_FACTORY$1
        @Override // kotlin.jvm.functions.Function1
        public final Void invoke(ImageRequest imageRequest) {
            return null;
        }
    };
    public static final String MIME_TYPE_HEIC = "image/heic";
    public static final String MIME_TYPE_HEIF = "image/heif";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_WEBP = "image/webp";
    public static final String MIME_TYPE_XML = "text/xml";
    public static final String SCHEME_FILE = "file";

    /* JADX INFO: compiled from: utils.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.values().length];
            try {
                iArr[DataSource.MEMORY_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DataSource.MEMORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DataSource.DISK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DataSource.NETWORK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getDispatcher$annotations(CoroutineContext coroutineContext) {
    }

    public static final boolean isMinOrMax(int i) {
        return i == Integer.MIN_VALUE || i == Integer.MAX_VALUE;
    }

    public static final String getEmoji(DataSource dataSource) {
        int i = WhenMappings.$EnumSwitchMapping$0[dataSource.ordinal()];
        if (i == 1 || i == 2) {
            return "🧠";
        }
        if (i == 3) {
            return "💾";
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return "☁️";
    }

    public static final void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final void closeQuietly(AutoCloseable autoCloseable) {
        try {
            UByte$$ExternalSyntheticBackport0.m9014m((Object) autoCloseable);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final Function1<ImageRequest, Image> getEMPTY_IMAGE_FACTORY() {
        return EMPTY_IMAGE_FACTORY;
    }

    public static final String key(ComponentRegistry componentRegistry, Object obj, Options options, Logger logger, String str) {
        List<Pair<Keyer<? extends Object>, KClass<? extends Object>>> keyers = componentRegistry.getKeyers();
        int size = keyers.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            Pair<Keyer<? extends Object>, KClass<? extends Object>> pair = keyers.get(i);
            Keyer<? extends Object> keyerComponent1 = pair.component1();
            if (pair.component2().isInstance(obj)) {
                Intrinsics.checkNotNull(keyerComponent1, "null cannot be cast to non-null type coil3.key.Keyer<kotlin.Any>");
                String strKey = keyerComponent1.key(obj, options);
                if (strKey != null) {
                    return strKey;
                }
                z = true;
            }
        }
        if (!z && logger != null) {
            Logger.Level level = Logger.Level.Warn;
            if (logger.getMinLevel().compareTo(level) <= 0) {
                logger.log(str, level, "No keyer is registered for data with type '" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + "'. Register Keyer<" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + "> in the component registry to cache the output image in the memory cache.", null);
            }
        }
        return null;
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, final Pair<? extends Fetcher.Factory<?>, ? extends KClass<?>> pair) {
        if (pair != null) {
            builder.getLazyFetcherFactories$coil_core_release().add(0, new Function0() { // from class: coil3.util.UtilsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return kotlin.collections.CollectionsKt.listOf(pair);
                }
            });
        }
        return builder;
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, final Decoder.Factory factory) {
        if (factory != null) {
            builder.getLazyDecoderFactories$coil_core_release().add(0, new Function0() { // from class: coil3.util.UtilsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return kotlin.collections.CollectionsKt.listOf(factory);
                }
            });
        }
        return builder;
    }

    public static final boolean isPlaceholderCached(Interceptor.Chain chain) {
        return (chain instanceof RealInterceptorChain) && ((RealInterceptorChain) chain).getIsPlaceholderCached();
    }

    public static final EventListener getEventListener(Interceptor.Chain chain) {
        return chain instanceof RealInterceptorChain ? ((RealInterceptorChain) chain).getEventListener() : EventListener.NONE;
    }

    public static final boolean isFileUri(Uri uri) {
        return ((uri.getScheme() != null && !Intrinsics.areEqual(uri.getScheme(), SCHEME_FILE)) || uri.getPath() == null || Utils_androidKt.isAssetUri(uri)) ? false : true;
    }

    public static final ErrorResult ErrorResult(ImageRequest imageRequest, Throwable th) {
        Image imageError;
        if (!(th instanceof NullRequestDataException) || (imageError = imageRequest.fallback()) == null) {
            imageError = imageRequest.error();
        }
        return new ErrorResult(imageError, imageRequest, th);
    }

    public static final CoroutineDispatcher getDispatcher(CoroutineContext coroutineContext) {
        return (CoroutineDispatcher) coroutineContext.get(CoroutineDispatcher.INSTANCE);
    }
}

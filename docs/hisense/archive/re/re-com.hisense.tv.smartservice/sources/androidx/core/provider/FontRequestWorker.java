package androidx.core.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Consumer;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public class FontRequestWorker {
    public static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    public static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    public static final Object LOCK = new Object();

    @GuardedBy("LOCK")
    public static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();

    public static String createCacheId(@NonNull FontRequest request, int style) {
        return request.getId() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + style;
    }

    @SuppressLint({"WrongConstant"})
    public static int getFontFamilyResultStatus(@NonNull FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() != 0) {
            return fontFamilyResult.getStatusCode() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
        if (fonts != null && fonts.length != 0) {
            i = 0;
            for (FontsContractCompat.FontInfo fontInfo : fonts) {
                int resultCode = fontInfo.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    }
                    return resultCode;
                }
            }
        }
        return i;
    }

    @NonNull
    public static TypefaceResult getFontSync(@NonNull final String cacheId, @NonNull final Context context, @NonNull final FontRequest request, int style) {
        Typeface typeface = sTypefaceCache.get(cacheId);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult fontFamilyResult = FontProvider.getFontFamilyResult(context, request, null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(fontFamilyResult);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface typefaceCreateFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fontFamilyResult.getFonts(), style);
            if (typefaceCreateFromFontInfo == null) {
                return new TypefaceResult(-3);
            }
            sTypefaceCache.put(cacheId, typefaceCreateFromFontInfo);
            return new TypefaceResult(typefaceCreateFromFontInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    public static Typeface requestFontAsync(@NonNull final Context context, @NonNull final FontRequest request, final int style, @Nullable final Executor executor, @NonNull final CallbackWithHandler callback) {
        final String strCreateCacheId = createCacheId(request, style);
        Typeface typeface = sTypefaceCache.get(strCreateCacheId);
        if (typeface != null) {
            callback.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        }
        Consumer<TypefaceResult> consumer = new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.2
            @Override // androidx.core.util.Consumer
            public void accept(TypefaceResult typefaceResult) {
                callback.onTypefaceResult(typefaceResult);
            }
        };
        synchronized (LOCK) {
            ArrayList<Consumer<TypefaceResult>> arrayList = PENDING_REPLIES.get(strCreateCacheId);
            if (arrayList != null) {
                arrayList.add(consumer);
                return null;
            }
            ArrayList<Consumer<TypefaceResult>> arrayList2 = new ArrayList<>();
            arrayList2.add(consumer);
            PENDING_REPLIES.put(strCreateCacheId, arrayList2);
            Callable<TypefaceResult> callable = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    return FontRequestWorker.getFontSync(strCreateCacheId, context, request, style);
                }
            };
            if (executor == null) {
                executor = DEFAULT_EXECUTOR_SERVICE;
            }
            RequestExecutor.execute(executor, callable, new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.4
                @Override // androidx.core.util.Consumer
                public void accept(TypefaceResult typefaceResult) {
                    synchronized (FontRequestWorker.LOCK) {
                        ArrayList<Consumer<TypefaceResult>> arrayList3 = FontRequestWorker.PENDING_REPLIES.get(strCreateCacheId);
                        if (arrayList3 == null) {
                            return;
                        }
                        FontRequestWorker.PENDING_REPLIES.remove(strCreateCacheId);
                        for (int i = 0; i < arrayList3.size(); i++) {
                            arrayList3.get(i).accept(typefaceResult);
                        }
                    }
                }
            });
            return null;
        }
    }

    public static Typeface requestFontSync(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final CallbackWithHandler callback, final int style, int timeoutInMillis) {
        final String strCreateCacheId = createCacheId(request, style);
        Typeface typeface = sTypefaceCache.get(strCreateCacheId);
        if (typeface != null) {
            callback.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        }
        if (timeoutInMillis == -1) {
            TypefaceResult fontSync = getFontSync(strCreateCacheId, context, request, style);
            callback.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        }
        try {
            TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    return FontRequestWorker.getFontSync(strCreateCacheId, context, request, style);
                }
            }, timeoutInMillis);
            callback.onTypefaceResult(typefaceResult);
            return typefaceResult.mTypeface;
        } catch (InterruptedException unused) {
            callback.onTypefaceResult(new TypefaceResult(-3));
            return null;
        }
    }

    public static void resetTypefaceCache() {
        sTypefaceCache.evictAll();
    }

    static final class TypefaceResult {
        public final int mResult;
        public final Typeface mTypeface;

        public TypefaceResult(int result) {
            this.mTypeface = null;
            this.mResult = result;
        }

        @SuppressLint({"WrongConstant"})
        public boolean isSuccess() {
            return this.mResult == 0;
        }

        @SuppressLint({"WrongConstant"})
        public TypefaceResult(@NonNull Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }
    }
}

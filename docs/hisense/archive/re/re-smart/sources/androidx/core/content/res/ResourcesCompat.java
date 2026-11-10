package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.annotation.AnyRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.ObjectsCompat;
import b.a.a.a.a;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class ResourcesCompat {

    @AnyRes
    public static final int ID_NULL = 0;
    public static final String TAG = "ResourcesCompat";
    public static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();
    public static final WeakHashMap<ColorStateListCacheKey, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    public static final Object sColorStateCacheLock = new Object();

    private static class ColorStateListCacheEntry {
        public final Configuration mConfiguration;
        public final ColorStateList mValue;

        public ColorStateListCacheEntry(@NonNull ColorStateList value, @NonNull Configuration configuration) {
            this.mValue = value;
            this.mConfiguration = configuration;
        }
    }

    private static final class ColorStateListCacheKey {
        public final Resources mResources;

        @Nullable
        public final Resources.Theme mTheme;

        public ColorStateListCacheKey(@NonNull Resources resources, @Nullable Resources.Theme theme) {
            this.mResources = resources;
            this.mTheme = theme;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || ColorStateListCacheKey.class != o.getClass()) {
                return false;
            }
            ColorStateListCacheKey colorStateListCacheKey = (ColorStateListCacheKey) o;
            return this.mResources.equals(colorStateListCacheKey.mResources) && ObjectsCompat.equals(this.mTheme, colorStateListCacheKey.mTheme);
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mResources, this.mTheme);
        }
    }

    public static abstract class FontCallback {
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public static Handler getHandler(@Nullable Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void callbackFailAsync(final int reason, @Nullable Handler handler) {
            getHandler(handler).post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(reason);
                }
            });
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void callbackSuccessAsync(final Typeface typeface, @Nullable Handler handler) {
            getHandler(handler).post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public abstract void onFontRetrievalFailed(int reason);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);
    }

    @RequiresApi(29)
    static class ImplApi29 {
        public static float getFloat(@NonNull Resources res, @DimenRes int id) {
            return res.getFloat(id);
        }
    }

    public static final class ThemeCompat {

        @RequiresApi(23)
        static class ImplApi23 {
            public static Method sRebaseMethod;
            public static boolean sRebaseMethodFetched;
            public static final Object sRebaseMethodLock = new Object();

            /* JADX WARN: Code duplicated, block: B:32:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            public static void rebase(@NonNull Resources.Theme theme) {
                synchronized (sRebaseMethodLock) {
                    if (!sRebaseMethodFetched) {
                        try {
                            sRebaseMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                            sRebaseMethod.setAccessible(true);
                        } catch (NoSuchMethodException e2) {
                            Log.i(ResourcesCompat.TAG, "Failed to retrieve rebase() method", e2);
                        }
                        sRebaseMethodFetched = true;
                        if (sRebaseMethod != null) {
                            try {
                                sRebaseMethod.invoke(theme, new Object[0]);
                            } catch (IllegalAccessException | InvocationTargetException e3) {
                                Log.i(ResourcesCompat.TAG, "Failed to invoke rebase() method via reflection", e3);
                                sRebaseMethod = null;
                            }
                        }
                    } else if (sRebaseMethod != null) {
                        sRebaseMethod.invoke(theme, new Object[0]);
                    }
                    throw th;
                }
            }
        }

        @RequiresApi(29)
        static class ImplApi29 {
            public static void rebase(@NonNull Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void rebase(@NonNull Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                theme.rebase();
            } else {
                ImplApi23.rebase(theme);
            }
        }
    }

    public static void addColorStateListToCache(@NonNull ColorStateListCacheKey key, @ColorRes int resId, @NonNull ColorStateList value) {
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> sparseArray = sColorStateCaches.get(key);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                sColorStateCaches.put(key, sparseArray);
            }
            sparseArray.append(resId, new ColorStateListCacheEntry(value, key.mResources.getConfiguration()));
        }
    }

    @Nullable
    public static ColorStateList getCachedColorStateList(@NonNull ColorStateListCacheKey key, @ColorRes int resId) {
        ColorStateListCacheEntry colorStateListCacheEntry;
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> sparseArray = sColorStateCaches.get(key);
            if (sparseArray != null && sparseArray.size() > 0 && (colorStateListCacheEntry = sparseArray.get(resId)) != null) {
                if (colorStateListCacheEntry.mConfiguration.equals(key.mResources.getConfiguration())) {
                    return colorStateListCacheEntry.mValue;
                }
                sparseArray.remove(resId);
            }
            return null;
        }
    }

    @Nullable
    public static Typeface getCachedFont(@NonNull Context context, @FontRes int id) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false, true);
    }

    @ColorInt
    public static int getColor(@NonNull Resources res, @ColorRes int id, @Nullable Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        return res.getColor(id, theme);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources res, @ColorRes int id, @Nullable Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        return res.getColorStateList(id, theme);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources res, @DrawableRes int id, @Nullable Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        return res.getDrawable(id, theme);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources res, @DrawableRes int id, int density, @Nullable Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        return res.getDrawableForDensity(id, density, theme);
    }

    public static float getFloat(@NonNull Resources res, @DimenRes int id) {
        if (Build.VERSION.SDK_INT >= 29) {
            return res.getFloat(id);
        }
        TypedValue typedValue = getTypedValue();
        res.getValue(id, typedValue, true);
        if (typedValue.type == 4) {
            return typedValue.getFloat();
        }
        StringBuilder sbA = a.a("Resource ID #0x");
        sbA.append(Integer.toHexString(id));
        sbA.append(" type #0x");
        sbA.append(Integer.toHexString(typedValue.type));
        sbA.append(" is not valid");
        throw new Resources.NotFoundException(sbA.toString());
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int id) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false, false);
    }

    @NonNull
    public static TypedValue getTypedValue() {
        TypedValue typedValue = sTempTypedValue.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        sTempTypedValue.set(typedValue2);
        return typedValue2;
    }

    @Nullable
    public static ColorStateList inflateColorStateList(Resources resources, int resId, @Nullable Resources.Theme theme) {
        if (isColorInt(resources, resId)) {
            return null;
        }
        try {
            return ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(resId), theme);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    public static boolean isColorInt(@NonNull Resources resources, @ColorRes int resId) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(resId, typedValue, true);
        int i = typedValue.type;
        return i >= 28 && i <= 31;
    }

    public static Typeface loadFont(@NonNull Context context, int id, TypedValue value, int style, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean isRequestFromLayoutInflator, boolean isCachedOnly) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typefaceLoadFont = loadFont(context, resources, value, id, style, fontCallback, handler, isRequestFromLayoutInflator, isCachedOnly);
        if (typefaceLoadFont != null || fontCallback != null || isCachedOnly) {
            return typefaceLoadFont;
        }
        StringBuilder sbA = a.a("Font resource ID #0x");
        sbA.append(Integer.toHexString(id));
        sbA.append(" could not be retrieved.");
        throw new Resources.NotFoundException(sbA.toString());
    }

    public static void getFont(@NonNull Context context, @FontRes int id, @NonNull FontCallback fontCallback, @Nullable Handler handler) {
        if (fontCallback != null) {
            if (context.isRestricted()) {
                fontCallback.callbackFailAsync(-4, handler);
                return;
            } else {
                loadFont(context, id, new TypedValue(), 0, fontCallback, handler, false, false);
                return;
            }
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00a6  */
    public static Typeface loadFont(@NonNull Context context, Resources wrapper, TypedValue value, int id, int style, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean isRequestFromLayoutInflator, boolean isCachedOnly) {
        CharSequence charSequence = value.string;
        if (charSequence != null) {
            String string = charSequence.toString();
            if (!string.startsWith("res/")) {
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
            Typeface typefaceFindFromCache = TypefaceCompat.findFromCache(wrapper, id, style);
            if (typefaceFindFromCache != null) {
                if (fontCallback != null) {
                    fontCallback.callbackSuccessAsync(typefaceFindFromCache, handler);
                }
                return typefaceFindFromCache;
            }
            if (isCachedOnly) {
                return null;
            }
            try {
                if (string.toLowerCase().endsWith(".xml")) {
                    FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse(wrapper.getXml(id), wrapper);
                    if (familyResourceEntry == null) {
                        Log.e(TAG, "Failed to find font-family tag");
                        if (fontCallback != null) {
                            fontCallback.callbackFailAsync(-3, handler);
                        }
                        return null;
                    }
                    return TypefaceCompat.createFromResourcesFamilyXml(context, familyResourceEntry, wrapper, id, style, fontCallback, handler, isRequestFromLayoutInflator);
                }
                Typeface typefaceCreateFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, wrapper, id, string, style);
                if (fontCallback != null) {
                    if (typefaceCreateFromResourcesFontFile != null) {
                        fontCallback.callbackSuccessAsync(typefaceCreateFromResourcesFontFile, handler);
                    } else {
                        fontCallback.callbackFailAsync(-3, handler);
                    }
                }
                return typefaceCreateFromResourcesFontFile;
            } catch (IOException e2) {
                Log.e(TAG, "Failed to read xml resource " + string, e2);
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            } catch (XmlPullParserException e3) {
                Log.e(TAG, "Failed to parse xml resource " + string, e3);
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
        }
        StringBuilder sbA = a.a("Resource \"");
        sbA.append(wrapper.getResourceName(id));
        sbA.append("\" (");
        sbA.append(Integer.toHexString(id));
        sbA.append(") is not a Font: ");
        sbA.append(value);
        throw new Resources.NotFoundException(sbA.toString());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface getFont(@NonNull Context context, @FontRes int id, TypedValue value, int style, @Nullable FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, fontCallback, null, true, false);
    }
}

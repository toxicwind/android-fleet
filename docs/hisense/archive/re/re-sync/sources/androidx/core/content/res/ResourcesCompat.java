package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import b.a.a.a.a;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class ResourcesCompat {
    public static final String TAG = "ResourcesCompat";

    public static abstract class FontCallback {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void callbackFailAsync(final int i, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(i);
                }
            });
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void callbackSuccessAsync(final Typeface typeface, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: androidx.core.content.res.ResourcesCompat.FontCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) {
        return resources.getColor(i, theme);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) {
        return resources.getColorStateList(i, theme);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i, @Nullable Resources.Theme theme) {
        return resources.getDrawable(i, theme);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i, int i2, @Nullable Resources.Theme theme) {
        return resources.getDrawableForDensity(i, i2, theme);
    }

    public static float getFloat(@NonNull Resources resources, @DimenRes int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type == 4) {
            return typedValue.getFloat();
        }
        StringBuilder sbC = a.c("Resource ID #0x");
        sbC.append(Integer.toHexString(i));
        sbC.append(" type #0x");
        sbC.append(Integer.toHexString(typedValue.type));
        sbC.append(" is not valid");
        throw new Resources.NotFoundException(sbC.toString());
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, new TypedValue(), 0, null, null, false);
    }

    public static Typeface loadFont(@NonNull Context context, int i, TypedValue typedValue, int i2, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface typefaceLoadFont = loadFont(context, resources, typedValue, i, i2, fontCallback, handler, z);
        if (typefaceLoadFont != null || fontCallback != null) {
            return typefaceLoadFont;
        }
        StringBuilder sbC = a.c("Font resource ID #0x");
        sbC.append(Integer.toHexString(i));
        sbC.append(" could not be retrieved.");
        throw new Resources.NotFoundException(sbC.toString());
    }

    public static void getFont(@NonNull Context context, @FontRes int i, @NonNull FontCallback fontCallback, @Nullable Handler handler) {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
        } else {
            loadFont(context, i, new TypedValue(), 0, fontCallback, handler, false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a3  */
    public static Typeface loadFont(@NonNull Context context, Resources resources, TypedValue typedValue, int i, int i2, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        CharSequence charSequence = typedValue.string;
        if (charSequence != null) {
            String string = charSequence.toString();
            if (!string.startsWith("res/")) {
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
            Typeface typefaceFindFromCache = TypefaceCompat.findFromCache(resources, i, i2);
            if (typefaceFindFromCache != null) {
                if (fontCallback != null) {
                    fontCallback.callbackSuccessAsync(typefaceFindFromCache, handler);
                }
                return typefaceFindFromCache;
            }
            try {
                if (string.toLowerCase().endsWith(ActivityChooserModel.HISTORY_FILE_EXTENSION)) {
                    FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse(resources.getXml(i), resources);
                    if (familyResourceEntry == null) {
                        Log.e(TAG, "Failed to find font-family tag");
                        if (fontCallback != null) {
                            fontCallback.callbackFailAsync(-3, handler);
                        }
                        return null;
                    }
                    return TypefaceCompat.createFromResourcesFamilyXml(context, familyResourceEntry, resources, i, i2, fontCallback, handler, z);
                }
                Typeface typefaceCreateFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, i, string, i2);
                if (fontCallback != null) {
                    if (typefaceCreateFromResourcesFontFile != null) {
                        fontCallback.callbackSuccessAsync(typefaceCreateFromResourcesFontFile, handler);
                    } else {
                        fontCallback.callbackFailAsync(-3, handler);
                    }
                }
                return typefaceCreateFromResourcesFontFile;
            } catch (IOException e) {
                Log.e(TAG, "Failed to read xml resource " + string, e);
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            } catch (XmlPullParserException e2) {
                Log.e(TAG, "Failed to parse xml resource " + string, e2);
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
        }
        StringBuilder sbC = a.c("Resource \"");
        sbC.append(resources.getResourceName(i));
        sbC.append("\" (");
        sbC.append(Integer.toHexString(i));
        sbC.append(") is not a Font: ");
        sbC.append(typedValue);
        throw new Resources.NotFoundException(sbC.toString());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface getFont(@NonNull Context context, @FontRes int i, TypedValue typedValue, int i2, @Nullable FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i, typedValue, i2, fontCallback, null, true);
    }
}

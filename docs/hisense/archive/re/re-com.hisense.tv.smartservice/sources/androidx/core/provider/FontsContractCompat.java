package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FontsContractCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final String PARCEL_FONT_RESULTS = "font_results";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        public final FontInfo[] mFonts;
        public final int mStatusCode;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public FontFamilyResult(int statusCode, @Nullable FontInfo[] fonts) {
            this.mStatusCode = statusCode;
            this.mFonts = fonts;
        }

        public static FontFamilyResult create(int statusCode, @Nullable FontInfo[] fonts) {
            return new FontFamilyResult(statusCode, fonts);
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public static class FontInfo {
        public final boolean mItalic;
        public final int mResultCode;
        public final int mTtcIndex;
        public final Uri mUri;
        public final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public FontInfo(@NonNull Uri uri, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int ttcIndex, @IntRange(from = 1, to = 1000) int weight, boolean italic, int resultCode) {
            if (uri == null) {
                throw new NullPointerException();
            }
            this.mUri = uri;
            this.mTtcIndex = ttcIndex;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mResultCode = resultCode;
        }

        public static FontInfo create(@NonNull Uri uri, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int ttcIndex, @IntRange(from = 1, to = 1000) int weight, boolean italic, int resultCode) {
            return new FontInfo(uri, ttcIndex, weight, italic, resultCode);
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public static final int RESULT_OK = 0;
        public static final int RESULT_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int reason) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fonts) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fonts, 0);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest request) {
        return FontProvider.getFontFamilyResult(context, request, cancellationSignal);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static Typeface getFontSync(final Context context, final FontRequest request, @Nullable final ResourcesCompat.FontCallback fontCallback, @Nullable final Handler handler, boolean isBlockingFetch, int timeout, final int style) {
        return requestFont(context, request, style, isBlockingFetch, timeout, ResourcesCompat.FontCallback.getHandler(handler), new TypefaceCompat.ResourcesCallbackAdapter(fontCallback));
    }

    @VisibleForTesting
    @Deprecated
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest request, @Nullable Resources resources) {
        return FontProvider.getProvider(packageManager, request, resources);
    }

    @Deprecated
    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fonts, CancellationSignal cancellationSignal) {
        return TypefaceCompatUtil.readFontInfoIntoByteBuffer(context, fonts, cancellationSignal);
    }

    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final FontRequestCallback callback, @NonNull final Handler handler) {
        CallbackWithHandler callbackWithHandler = new CallbackWithHandler(callback);
        FontRequestWorker.requestFontAsync(context.getApplicationContext(), request, 0, new RequestExecutor.HandlerExecutor(handler), callbackWithHandler);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static void resetCache() {
        FontRequestWorker.sTypefaceCache.evictAll();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    public static void resetTypefaceCache() {
        FontRequestWorker.sTypefaceCache.evictAll();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Typeface requestFont(@NonNull final Context context, @NonNull final FontRequest request, final int style, boolean isBlockingFetch, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int timeout, @NonNull final Handler handler, @NonNull final FontRequestCallback callback) {
        CallbackWithHandler callbackWithHandler = new CallbackWithHandler(callback, handler);
        if (isBlockingFetch) {
            return FontRequestWorker.requestFontSync(context, request, callbackWithHandler, style, timeout);
        }
        return FontRequestWorker.requestFontAsync(context, request, style, null, callbackWithHandler);
    }
}

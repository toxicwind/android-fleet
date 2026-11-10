package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.Xml;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.provider.FontRequest;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FontResourcesParserCompat {
    public static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    public static final int ITALIC = 1;
    public static final int NORMAL_WEIGHT = 400;

    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {

        @NonNull
        public final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(@NonNull FontFileResourceEntry[] entries) {
            this.mEntries = entries;
        }

        @NonNull
        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static final class FontFileResourceEntry {

        @NonNull
        public final String mFileName;
        public boolean mItalic;
        public int mResourceId;
        public int mTtcIndex;
        public String mVariationSettings;
        public int mWeight;

        public FontFileResourceEntry(@NonNull String fileName, int weight, boolean italic, @Nullable String variationSettings, int ttcIndex, int resourceId) {
            this.mFileName = fileName;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mVariationSettings = variationSettings;
            this.mTtcIndex = ttcIndex;
            this.mResourceId = resourceId;
        }

        @NonNull
        public String getFileName() {
            return this.mFileName;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @Nullable
        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static int getType(TypedArray typedArray, int index) {
        int i = Build.VERSION.SDK_INT;
        return typedArray.getType(index);
    }

    @Nullable
    public static FamilyResourceEntry parse(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = parser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return readFamilies(parser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List<List<byte[]>> readCerts(Resources resources, @ArrayRes int certsId) {
        if (certsId == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(certsId);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (getType(typedArrayObtainTypedArray, 0) == 1) {
                for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i, 0);
                    if (resourceId != 0) {
                        arrayList.add(toByteArrayList(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(toByteArrayList(resources.getStringArray(certsId)));
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    @Nullable
    public static FamilyResourceEntry readFamilies(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        parser.require(2, null, "font-family");
        if (parser.getName().equals("font-family")) {
            return readFamily(parser, resources);
        }
        skip(parser);
        return null;
    }

    @Nullable
    public static FamilyResourceEntry readFamily(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.FontFamily);
        String string = typedArrayObtainAttributes.getString(R.styleable.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(R.styleable.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(R.styleable.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = typedArrayObtainAttributes.getString(R.styleable.FontFamily_fontProviderSystemFontFamily);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (parser.next() != 3) {
                skip(parser);
            }
            return new ProviderResourceEntry(new FontRequest(string, string2, string3, readCerts(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (parser.getName().equals(MediaType.FONT_TYPE)) {
                    arrayList.add(readFont(parser, resources));
                } else {
                    skip(parser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[arrayList.size()]));
    }

    public static FontFileResourceEntry readFont(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.FontFamilyFont);
        int i = typedArrayObtainAttributes.getInt(typedArrayObtainAttributes.hasValue(R.styleable.FontFamilyFont_fontWeight) ? R.styleable.FontFamilyFont_fontWeight : R.styleable.FontFamilyFont_android_fontWeight, 400);
        boolean z = 1 == typedArrayObtainAttributes.getInt(typedArrayObtainAttributes.hasValue(R.styleable.FontFamilyFont_fontStyle) ? R.styleable.FontFamilyFont_fontStyle : R.styleable.FontFamilyFont_android_fontStyle, 0);
        int i2 = typedArrayObtainAttributes.hasValue(R.styleable.FontFamilyFont_ttcIndex) ? R.styleable.FontFamilyFont_ttcIndex : R.styleable.FontFamilyFont_android_ttcIndex;
        String string = typedArrayObtainAttributes.getString(typedArrayObtainAttributes.hasValue(R.styleable.FontFamilyFont_fontVariationSettings) ? R.styleable.FontFamilyFont_fontVariationSettings : R.styleable.FontFamilyFont_android_fontVariationSettings);
        int i3 = typedArrayObtainAttributes.getInt(i2, 0);
        int i4 = typedArrayObtainAttributes.hasValue(R.styleable.FontFamilyFont_font) ? R.styleable.FontFamilyFont_font : R.styleable.FontFamilyFont_android_font;
        int resourceId = typedArrayObtainAttributes.getResourceId(i4, 0);
        String string2 = typedArrayObtainAttributes.getString(i4);
        typedArrayObtainAttributes.recycle();
        while (parser.next() != 3) {
            skip(parser);
        }
        return new FontFileResourceEntry(string2, i, z, string, i3, resourceId);
    }

    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = parser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    public static List<byte[]> toByteArrayList(String[] stringArray) {
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    public static final class ProviderResourceEntry implements FamilyResourceEntry {

        @NonNull
        public final FontRequest mRequest;
        public final int mStrategy;

        @Nullable
        public final String mSystemFontFamilyName;
        public final int mTimeoutMs;

        public ProviderResourceEntry(@NonNull FontRequest request, int strategy, int timeoutMs) {
            this.mRequest = request;
            this.mStrategy = strategy;
            this.mTimeoutMs = timeoutMs;
            this.mSystemFontFamilyName = null;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        @NonNull
        public FontRequest getRequest() {
            return this.mRequest;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public String getSystemFontFamilyName() {
            return this.mSystemFontFamilyName;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public ProviderResourceEntry(@NonNull FontRequest request, int strategy, int timeoutMs, @Nullable String systemFontFamilyName) {
            this.mRequest = request;
            this.mStrategy = strategy;
            this.mTimeoutMs = timeoutMs;
            this.mSystemFontFamilyName = systemFontFamilyName;
        }
    }
}
